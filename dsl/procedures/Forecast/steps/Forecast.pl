#############################################################################
#
# Copyright Copyright 2017 Datical Inc.
#
#############################################################################
use strict;
use English;

# Check for the OS Type
my $osIsWindows = $^O =~ /MSWin/;

#
# Parameters
#
my $daticalLabels='$[daticalLabels]';

#
# Global variables
#
my $hammerPath="";
my $labelOption="";

if ($osIsWindows) {
  $hammerPath='$[daticalInstallPath]\hammer.bat';
} else {
  $hammerPath='$[daticalInstallPath]/hammer';
}


if ($daticalLabels ne "") {
  $labelOption="--labels=\"$daticalLabels\"";
}
# Need single quote to deal with \ in Windows path
system($hammerPath .
        ' --project \"$[daticalProjectPath]\" --drivers \"$[daticalPluginsPath]\" forecast $[daticalDeploymentStep] ' .
        " $labelOption");
