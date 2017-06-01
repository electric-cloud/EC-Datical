
procedure 'Deploy',
  description: 'Deploys all change sets that have not been applied to the indicated database reference.',
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

  step 'Deploy',
    description: 'c:\\Users\\Administrator\\DaticalDB\\repl\\hammer.bat --drivers=C:\\Users\\Administrator\\DaticalDB\\plugins deploy Dev',
    command: new File(pluginDir + "/dsl/procedures/Deploy/steps/Deploy.pl").text,
    postProcessor: 'postp --loadProperty /myProject/postp/deploy',
    resourceName: '$[resource]',
    shell: 'ec-perl'

  step 'copyReportToWorkspace',
    command: new File(pluginDir + "/dsl/procedures/Deploy/steps/copyReportToWorkspace.pl").text,
    resourceName: '$[resource]',
    shell: 'ec-perl'
}
