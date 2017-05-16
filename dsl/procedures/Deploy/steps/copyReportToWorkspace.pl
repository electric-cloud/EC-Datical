use File::Copy;

#############################################################################
#
# Copyright Copyright 2017 Datical Inc.
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
copy ('$[/myJob/deployReport]', ".") || die ("cannot copy the deploy Report: $!");

# to allow for non default workspace
$ec->setProperty("/myJob/artifactsDirectory", ".");
$ec->setProperty("/myJob/report-urls/deployReport", "/commander/jobSteps/$[jobStepId]/deployReport.html");
$ec->setProperty ("summary", "<html>&#160;&#160;&#160;<a href=\"deployReport.html\">Deploy Report</a></html>");
