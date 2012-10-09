package jcma;

import java.io.Serializable;

public class Country implements Serializable {
// ------------------------------ FIELDS ------------------------------

    private String id;

    private String name;

// --------------------------- CONSTRUCTORS ---------------------------

    public Country(String id, String name)
    {
        this.id = id;
        this.name = name;
    }

// --------------------- GETTER / SETTER METHODS ---------------------

    public String getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

// ------------------------ CANONICAL METHODS ------------------------

    @Override
    public String toString()
    {
        return "Country{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            '}';
    }
}
