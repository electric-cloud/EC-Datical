import groovy.transform.BaseScript
import com.electriccloud.commander.dsl.util.BasePlugin

//noinspection GroovyUnusedAssignment
@BaseScript BasePlugin baseScript

// Variables available for use in DSL code
def pluginName = args.pluginName
def pluginKey = getProject("/plugins/$pluginName/project").pluginKey
def pluginDir = getProperty("/server/settings/pluginsDirectory").value + "/" + pluginName

project pluginName, {

	description = 'ElectricFlow integration with DaticalDB.'
	ec_visibility = 'pickListOnly'

	property 'version', value = '1.1.0'
	property 'postp', {
		property 'deploy', value: new File(pluginDir + "/dsl/properties/postp/deploy.pl").text
		property 'forecast', value: new File(pluginDir + "/dsl/properties/postp/forecast.pl").text
	}

	loadProcedures(pluginDir, pluginKey, pluginName)
}
