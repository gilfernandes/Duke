package org.onepointltd.duke.adapter.provider;

import no.priv.garshol.duke.Comparator;
import no.priv.garshol.duke.Property;
import no.priv.garshol.duke.PropertyImpl;
import no.priv.garshol.duke.comparators.ExactComparator;
import no.priv.garshol.duke.comparators.JaroWinkler;
import no.priv.garshol.duke.comparators.Levenshtein;

import java.util.ArrayList;
import java.util.List;

/**
 * Used to provide properties for testing.
 */
public class PropertyProvider {

    public static final String ID_PROP = "ID";

    public static final String NAME_PROP = "NAME";

    public static final String SURNAME_PROP = "SURNAME";
    public static final String ID_NUMBER = "id_number";
    public static final String GIVEN_NAME = "givenName";
    public static final String LAST_NAME = "lastName";
    public static final String DEPARTMENT = "department";
    public static final String MALE = "male";

    public static List<Property> createSimplePropertiesExact() {
        return createSimpleProperties(new ExactComparator());
    }

    public static List<Property> createSimplePropertiesLevenshtein() {
        return createSimpleProperties(new Levenshtein());
    }

    private static List<Property> createSimpleProperties(Comparator comp) {
        List<Property> props = new ArrayList<>();
        props.add(new PropertyImpl(ID_PROP));
        final PropertyImpl property = new PropertyImpl(NAME_PROP, comp, 0.3, 0.8);
        property.setLookupBehaviour(Property.Lookup.DEFAULT);
        props.add(property);
        return props;
    }

    public static List<Property> createCompleteNameProperties() {
        final Levenshtein comp = new Levenshtein();
        List<Property> surnameProperties = createSimpleProperties(comp);
        final PropertyImpl surnameProperty = new PropertyImpl(SURNAME_PROP, comp, 0.3, 0.8);
        surnameProperty.setLookupBehaviour(Property.Lookup.DEFAULT);
        surnameProperties.add(surnameProperty);
        return surnameProperties;
    }

    public static List<Property> createTalendProperties() {
        final JaroWinkler comp = new JaroWinkler();
        List<Property> props = new ArrayList<>();
        props.add(new PropertyImpl(ID_NUMBER));

        final PropertyImpl givenNameProp = new PropertyImpl(GIVEN_NAME, comp, 0.3, 0.8);
        givenNameProp.setLookupBehaviour(Property.Lookup.REQUIRED);
        props.add(givenNameProp);
        final PropertyImpl lastNameProperty = new PropertyImpl(LAST_NAME, comp, 0.3, 0.8);
        givenNameProp.setLookupBehaviour(Property.Lookup.TRUE);
        props.add(lastNameProperty);
        props.add(new PropertyImpl(DEPARTMENT, comp, 0.3, 0.8));
        props.add(new PropertyImpl(MALE, comp, 0.3, 0.8));
        return props;
    }
}
