# Data that drives the create step picker registration for this plugin.
my %Check_Drivers = ( 
  label       => "EC-Datical - Check Drivers", 
  procedure   => "Check Drivers", 
  description => "Checks for installed drivers as well as JDBC driver jars.", 
  category    => "Database" 
);

my %Deploy = ( 
  label       => "EC-Datical - Deploy", 
  procedure   => "Deploy", 
  description => "Deploys all change sets that have not been applied to the indicated database reference.", 
  category    => "Database" 
);

my %Forecast = ( 
  label       => "EC-Datical - Forecast", 
  procedure   => "Forecast", 
  description => "Simulates all change sets that have not been applied to the indicated database reference.", 
  category    => "Database" 
);

@::createStepPickerSteps = (\%Check_Drivers, \%Deploy, \%Forecast);
