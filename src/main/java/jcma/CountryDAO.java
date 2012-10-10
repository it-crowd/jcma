package jcma;

import javax.faces.bean.ApplicationScoped;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
//TODO try without Serializable and see what happens during deployment
public class CountryDAO implements Serializable {
// ------------------------------ FIELDS ------------------------------

    private final Map<String, Country> countries = new HashMap<String, Country>();

// --------------------------- CONSTRUCTORS ---------------------------

    public CountryDAO()
    {
        final List<Country> list = Arrays.asList(new Country("pl", "Poland"), new Country("uk", "United Kingdom"), new Country("us", "USA"));
        for (Country country : list) {
            countries.put(country.getId(), country);
        }
    }

// -------------------------- OTHER METHODS --------------------------

    public Collection<Country> getCountries()
    {
        return countries.values();
    }

    public Country getCountryById(String id)
    {
        return countries.get(id);
    }
}
