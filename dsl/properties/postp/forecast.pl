push (@::gMatchers,
    {
      id      => "forecastReport",
      pattern => q{Report ready at (.*)},
      action  => q{    setProperty("/myJob/forecastReport", "$1");
        }
    }
);
