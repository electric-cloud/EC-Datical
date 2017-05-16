push (@::gMatchers,
    {
      id      => "deployReport",
      pattern => q{Report ready at (.*)},
      action  => q{    setProperty("/myJob/deployReport", "$1");
        }
    }
);
