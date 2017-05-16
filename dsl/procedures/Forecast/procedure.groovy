
procedure 'Forecast',
  description: 'Simulates all change sets that have not been applied to the indicated database reference.',
{
  formalParameter 'daticalDeploymentStep',
    description: 'The name of the Deployment Step in DaticalDB',
    required: '1',
    type: 'entry'

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

  formalParameter 'resource',
    defaultValue: 'local',
    description: 'The resource where Datical is installed',
    required: '1',
    type: 'entry'

  step 'Forecast',
    description: 'c:\\Users\\Administrator\\DaticalDB\\repl\\hammer.bat --drivers=C:\\Users\\Administrator\\DaticalDB\\plugins Forecast Dev',
    command: new File(pluginDir + "/dsl/procedures/Forecast/steps/Forecast.pl").text,
    postProcessor: 'postp --loadProperty /myProject/postp/forecast',
    resourceName: '$[resource]',
    shell: ''

  step 'copyReportToWorkspace',
    command: new File(pluginDir + "/dsl/procedures/Forecast/steps/copyReportToWorkspace.pl").text,
    resourceName: '$[resource]',
    shell: 'ec-perl'
}
