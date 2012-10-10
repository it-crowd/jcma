package jcma.security;

import pl.com.it_crowd.utils.config.PBESpec;

public class PBESpecImpl implements PBESpec {
// ------------------------ INTERFACE METHODS ------------------------


// --------------------- Interface PBESpec ---------------------

    @Override
    public String getAlgorithm()
    {
        return "PBEWithMD5AndDES";
    }

    @Override
    public int getIterationCount()
    {
        return 4;
    }

    @Override
    public String getPassword()
    {
        return "selen";
    }

    @Override
    public String getSalt()
    {
        return "asd";
    }
}
