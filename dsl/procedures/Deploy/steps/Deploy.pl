#############################################################################
#
# Copyright Copyright 2017 Datical Inc.
#
#############################################################################
use strict;
use English;

# Check for the OS Type
my $osIsWindows = $^O =~ /MSWin/;

my $hammerPath="";
if ($osIsWindows) {
  $hammerPath='$[daticalInstallPath]\hammer.bat';
} else {
  $hammerPath='$[daticalInstallPath]/hammer';
}

system($hammerPath . ' --project \"$[daticalProjectPath]\" --drivers \"$[daticalPluginsPath]\" deploy $[daticalDeploymentStep]');
