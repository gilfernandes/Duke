package org.onepointltd.duke.adapter.provider;

import no.priv.garshol.duke.CompactRecord;
import no.priv.garshol.duke.Record;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Creates records for testing.
 */
public class RecordProvider {

    public static final String SAMPLE_ID = "g1";

    public static Collection<Record> createIdNameRecords() {
        Collection<Record> records = new ArrayList<>();
        records.add(createFirstSimpleRecord());
        records.add(createSimpleRecord("g2", "Gill"));
        records.add(createSimpleRecord("s1", "Sasha"));
        records.add(createSimpleRecord("g3", "Gill"));
        records.add(createSimpleRecord("g4", "gill"));
        records.add(createSimpleRecord("g5", "gilles"));
        records.add(createSimpleRecord("g6", "gill s"));
        records.add(createSimpleRecord("g7", "gill's"));
        records.add(createSimpleRecord("g8", "gi ll"));
        records.add(createSimpleRecord("g9", "gil Fernandes"));
        records.add(createSimpleRecord("g10", "gil  Fernandes"));
        return records;
    }

    private static CompactRecord createSimpleRecord(String id, String name) {
        CompactRecord record = new CompactRecord();
        record.addValue(PropertyProvider.ID_PROP, id);
        record.addValue(PropertyProvider.NAME_PROP, name);
        return record;
    }

    private static CompactRecord createFullNameRecord(String id, String name, String surname) {
        CompactRecord record = createSimpleRecord(id, name);
        record.addValue(PropertyProvider.SURNAME_PROP, surname);
        return record;
    }

    public static CompactRecord createFirstSimpleRecord() {
        CompactRecord rec = new CompactRecord();
        rec.addValue(PropertyProvider.ID_PROP, SAMPLE_ID);
        rec.addValue(PropertyProvider.NAME_PROP, "Gil");
        return rec;
    }

    public static CompactRecord createFirstSimplePartialRecord() {
        CompactRecord rec = new CompactRecord();
        rec.addValue(PropertyProvider.NAME_PROP, "Gil");
        return rec;
    }

    public static CompactRecord createSecondSimplePartialRecord() {
        return createNameRecord("Gill");
    }

    public static CompactRecord createNameRecord(String name) {
        CompactRecord rec = new CompactRecord();
        rec.addValue(PropertyProvider.NAME_PROP, name);
        return rec;
    }

    public static Collection<Record> createFullNameRecords() {
        Collection<Record> records = new ArrayList<>();
        records.add(createFullNameRecord("g1", "Gil", "Fernandes"));
        records.add(createFullNameRecord("g2", "Gil", "Fernandez"));
        records.add(createFullNameRecord("g3", "Gil", "Hernandez"));
        records.add(createFullNameRecord("g4", "Gil", "Fernandes"));
        records.add(createFullNameRecord("g5", "gil ", "Fernandes"));
        return records;
    }

    public static Collection<Record> createTalendRecords() {
        Collection<Record> records = new ArrayList<>();
        records.add(createExtendedUserRecord("10L", "Gill", "Fernandez", "development", true));
        records.add(createExtendedUserRecord("11L", "Sacha", "Polev", "architecture", true));
        return records;
    }

    private static Record createExtendedUserRecord(String id, String givenName, String lastName,
                                                   String department, boolean male) {
        CompactRecord rec = new CompactRecord();
        rec.addValue(PropertyProvider.ID_NUMBER, id);
        rec.addValue(PropertyProvider.GIVEN_NAME, givenName);
        rec.addValue(PropertyProvider.LAST_NAME, lastName);
        rec.addValue(PropertyProvider.DEPARTMENT, department);
        rec.addValue(PropertyProvider.MALE, Boolean.toString(male));
        return rec;
    }
}
