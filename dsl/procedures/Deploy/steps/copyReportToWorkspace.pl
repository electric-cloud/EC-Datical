use File::Copy;

#############################################################################
#
# Copyright Copyright 2017 Datical Inc.
#
#############################################################################

$[/plugins/EC-Admin/project/scripts/perlHeaderJSON]

copy ('$[/myJob/deployReport]', ".") || die ("cannot copy the deploy Report: $!");

# to allow for non default workspace
$ec->setProperty("/myJob/artifactsDirectory", ".");

# Add report to job detail page
$ec->setProperty("/myJob/report-urls/deployReport", "/commander/jobSteps/$[jobStepId]/deployReport.html");
$ec->setProperty ("summary", "<html>&#160;&#160;&#160;<a href=\"deployReport.html\">Deploy Report</a></html>");

# Add report to Stage sumary if started from  a pipeline
if (getP('/myPipelineStageRuntime/projectName')) { # Add to Stage summary if running from a pipeline
    $ec->setProperty("/myPipelineStageRuntime/ec_summary/Datical Deploy Report",
        "<html><a href=\"/commander/jobSteps/$[jobStepId]/deployReport.html">Datical Deploy Report</a></html");
}

$[/plugins/EC-Admin/project/scripts/perlLibJSON]
