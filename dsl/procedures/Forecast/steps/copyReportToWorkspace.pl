use File::Copy;

#############################################################################
#
# Copyright 2017 Datical Inc.
#
#############################################################################

$[/plugins/EC-Admin/project/scripts/perlHeaderJSON]


copy ('$[/myJob/forecastReport]', ".") || die ("cannot copy the forecast Report: $!");

# to allow for non default workspace
$ec->setProperty("/myJob/artifactsDirectory", ".");

# Add report to job detail page
$ec->setProperty("/myJob/report-urls/forecastReport", "/commander/jobSteps/$[jobStepId]/forecastReport.html");
$ec->setProperty ("summary", "<html>&#160;&#160;&#160;<a href=\"forecastReport.html\">forecast Report</a></html>");

# Add report to Stage sumary if started from  a pipeline
if (getP('/myPipelineStageRuntime/projectName')) { # Add to Stage summary if running from a pipeline
    $ec->setProperty("/myPipelineStageRuntime/ec_summary/Datical Forecast Report",
        "<html><a href=\"/commander/jobSteps/$[jobStepId]/forecastReport.html\">Datical Forecast Report</a></html");
}

$[/plugins/EC-Admin/project/scripts/perlLibJSON]
