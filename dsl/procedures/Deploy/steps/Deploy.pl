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
my $daticalPipelineName='$[daticalPipeline]';
my $daticalLabels='$[daticalLabels]';

#
# Global variables
#
my $hammerPath="";
my $pipelineOption="";
my $labelOption="";

if ($osIsWindows) {
  $hammerPath='$[daticalInstallPath]\hammer.bat';
} else {
  $hammerPath='$[daticalInstallPath]/hammer';
}

if ($daticalPipelineName ne "") {
  $pipelineOption="--pipeline=\"$daticalPipelineName\"";
}

if ($daticalLabels ne "") {
  $labelOption="--labels=\"$daticalLabels\"";
}
# Need single quote to deal with \ in Windows path
system($hammerPath .
       ' --project \"$[daticalProjectPath]\" --drivers \"$[daticalPluginsPath]\" deploy $[daticalDeploymentStep] $pipelineOption  ' .
       " $labelOption");
