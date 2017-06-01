procedure 'deployPackager',
  description: 'Import SQL scripts intoDatical from a SCM system.',
{
    formalParameter 'daticalInstallPath',
      defaultValue: 'C:\\Users\\Administrator\\DaticalDB\\repl',
      description: '''The path on the resource where DaticalDB is installed. This path is used to provide where "hammer.bat" located.
  E.g., C:\\Users\\Administrator\\DaticalDB\\rep''',
      required: '1',
      type: 'entry'

    formalParameter 'daticalPluginsPath',
      defaultValue: 'C:\\Users\\Administrator\\DaticalDB\\plugins',
      description: '''Path where Datical plugins are installed. This path is is used for "--driver" argument.
  E.g., C:\\Users\\Administrator\\DaticalDB\\plugins''',
      required: '1',
      type: 'entry'

    formalParameter 'daticalProjectPath',
      defaultValue: 'C:\\Users\\Administrator\\datical\\HelloWorld',
      description: 'The path on the resources for the Datical project. E.g., C:\\Users\\Administrator\\datical\\HelloWorld',
      required: '1',
      type: 'entry'

    formalParameter 'daticalPipeline',
      defaultValue: '$[/myPipeline]',
      description: 'The name of the Datical pipeline',
      required: '1',
      type: 'entry'

      formalParameter 'options',
        description: 'Additional options to pass to the deployPackager.groovy',
        required: '0',
        type: 'entry'

    formalParameter 'resource',
      defaultValue: 'local',
      description: 'The resource where Datical is installed',
      required: '1',
      type: 'entry'

      step 'deployPackager',
        description: '',
        command: new File(pluginDir + "/dsl/procedures/deployPackager/steps/deployPackager.pl").text,
        postProcessor: '',
        resourceName: '$[resource]',
        shell: 'ec-perl'
}
