use File::Copy;

#############################################################################
#
# Copyright 2017 Datical Inc.
#
#############################################################################
use strict;
use English;
use ElectricCommander;
use Data::Dumper;
$| = 1;

my $DEBUG=0;

# Create a single instance of the Perl access to ElectricCommander
my $ec = new ElectricCommander({'format' => "json"});

# Check for the OS Type
my $osIsWindows = $^O =~ /MSWin/;
copy ('$[/myJob/forecastReport]', ".") || die ("cannot copy the forecast Report: $!");

# to allow for non default workspace
$ec->setProperty("/myJob/artifactsDirectory", ".");
$ec->setProperty("/myJob/report-urls/forecastReport", "/commander/jobSteps/$[jobStepId]/forecastReport.html");
$ec->setProperty ("summary", "<html>&#160;&#160;&#160;<a href=\"forecastReport.html\">forecast Report</a></html>");
