procedure 'CheckDrivers',
  description: 'Checks for installed drivers as well as JDBC driver jars.',
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

    formalParameter 'resource',
      defaultValue: 'local',
      description: 'The resource where Datical is installed',
      required: '1',
      type: 'entry'

      step 'checkDrivers',
        description: '',
        command: new File(pluginDir + "/dsl/procedures/checkDrivers/steps/checkDrivers.pl").text,
        postProcessor: '',
        resourceName: '$[resource]',
        shell: 'ec-perl'
}
