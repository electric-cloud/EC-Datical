package com.electriccloud.commander.dsl.util

import groovy.io.FileType
import groovy.util.XmlSlurper
import java.io.File

import org.codehaus.groovy.control.CompilerConfiguration
import com.electriccloud.commander.dsl.DslDelegatingScript

abstract class BasePlugin extends DslDelegatingScript {

  def stepPicker (String pickerLabel, String pluginKey, String procedureName, String category, String description  = '') {
    // This is important, if the step-picker property does not have a description,
    // then the plugin lookup when defining procedure step fails with a GWT/JavaScript error.
    String propDescription = description ?: procedureName

    property "/server/ec_customEditors/pickerStep/$pickerLabel",
      value:
        """<step>
            <project>/plugins/$pluginKey/project</project>
            <procedure>$procedureName</procedure>
            <category>$category</category>
          </step>
        """.stripIndent(),
      description: propDescription

  }

  def determinePluginCategory(String pluginDir) {
		File pluginXml = new File("$pluginDir/META-INF", 'plugin.xml')
		def pluginRoot = new XmlSlurper().parseText(pluginXml.text)
		pluginRoot.category?: 'Utilities'
	}

  def shouldAddStepPicker(def pluginName, def procedureName) {
    def prop = getProperty("/projects/${pluginName}/procedures/${procedureName}/standardStepPicker", suppressNoSuchPropertyException: true)
    def value = prop?.value
    // If the property is not set, then we add the step-picker by default
    // If the property is set, then we check if the user requested stepPicker not be added.
    return value == null || (value != 'false' && value != '0')
  }

  def setupCustomEditorData(String pluginDir, String pluginName, String pluginKey) {
    String pluginCategory = determinePluginCategory(pluginDir)

    getProcedures(pluginName).each { proc ->
/*      getFormalParameters (pluginName, procedureName: proc.procedureName).each { param ->
        property 'ec_customEditorData', procedureName: proc.procedureName, {

          property 'parameters', {

            property param.formalParameterName, {
              formType = 'standard'
              if ('checkbox'.equals(param.type)) {
                checkedValue = 'true'
                initiallyChecked = '0'
                uncheckedValue = 'false'
              }
            }
          }
        }
      }
*/
      def addStepPicker = true // shouldAddStepPicker(pluginName, proc.procedureName)

      if (addStepPicker) {
        def prop = getProperty("/projects/${pluginName}/procedures/${proc.procedureName}/stepPicker/label", suppressNoSuchPropertyException: true)
        def label = prop? prop.value : "$pluginKey - ${proc.procedureName}"
        prop = getProperty("/projects/${pluginName}/procedures/${proc.procedureName}/stepPicker/description", suppressNoSuchPropertyException: true)
        def description = prop? prop.value: "TODO:Proc description"

        stepPicker (label, pluginKey, proc.procedureName, pluginCategory, 'description')
      }

    }
  }

  def loadProcedures(String pluginDir, String pluginKey, String pluginName) {
    // Loop over the sub-directories in the procedures directory
    // and evaluate procedures if a procedure.groovy file exists

    File procsDir = new File(pluginDir, 'dsl/procedures')
    procsDir.eachDir {

      it.eachFile FileType.FILES, {
        if (it.name == 'procedure.groovy') {
          loadProcedure(pluginDir, pluginKey, pluginName, it.absolutePath)
        }
      }
    }

    // plugin boiler-plate
    setupCustomEditorData(pluginDir, pluginName, pluginKey)
  }

  def loadProcedure(String pluginDir, String pluginKey, String pluginName, String dslFile) {
    evalInlineDsl(dslFile, [pluginKey: pluginKey, pluginName: pluginName, pluginDir: pluginDir])
  }

  //Helper function to load another dsl script and evaluate it in-context
  def evalInlineDsl(String dslFile, Map bindingMap) {
    CompilerConfiguration cc = new CompilerConfiguration();
    cc.setScriptBaseClass(DelegatingScript.class.getName());
    GroovyShell sh = new GroovyShell(this.class.classLoader, bindingMap? new Binding(bindingMap) : new Binding(), cc);
    DelegatingScript script = (DelegatingScript)sh.parse(new File(dslFile))
    script.setDelegate(this);
    script.run();
  }
}
