package org.onepointltd.duke.adapter;

import no.priv.garshol.duke.Database;
import no.priv.garshol.duke.Record;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.onepointltd.duke.adapter.provider.PropertyProvider;
import org.onepointltd.duke.adapter.provider.RecordProvider;

import java.util.Collection;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class DatabaseTest {

    private Database database;

    private ProcessorParameters processorParameters;

    @Before
    public void setUp() throws Exception {
        processorParameters = new ProcessorParameters(
                SearchDatabase.ELASTIC_SEARCH,
                PropertyProvider.createSimplePropertiesLevenshtein(), 0.45,
                "localhost:9300", false);
        processorParameters.setElasticSearchCluster("bla");
        initDatabase(0);
    }

    @After
    public void tearDown() {
        database.close();
    }

    private void initDatabase(int editDistance) {
        processorParameters.setEditDistance(editDistance);
        database = ProcessorConfigurationFactory.createInstance(processorParameters).getDatabase();
    }

    @Test
    public void whenIndex_RecordShouldBeFound() throws Exception {
        createRecordsAndCheck();
        Collection<Record> matchRecords = database.findCandidateMatches(RecordProvider.createFirstSimplePartialRecord());
        assertThat(matchRecords.size()).isEqualTo(3);
    }

    @Test
    public void whenIndexFuzzy_RecordShouldBeFound() throws Exception {
        initDatabase(1);
        createRecordsAndCheck();
        Collection<Record> matchRecords = database.findCandidateMatches(RecordProvider.createFirstSimplePartialRecord());
        assertThat(matchRecords.size()).isEqualTo(8);
    }

    @Test
    public void whenIndex2_RecordShouldBeFoundThreeTimes() throws Exception {
        createRecordsAndCheck();
        Collection<Record> matchRecords = database.findCandidateMatches(RecordProvider.createSecondSimplePartialRecord());
        assertThat(matchRecords.size()).isEqualTo(4);
    }

    @Test
    public void whenIndex3_RecordShouldBeFound() throws Exception {
        createRecordsAndCheck();
        Collection<Record> matchRecords = database.findCandidateMatches(RecordProvider.createNameRecord("Gilles"));
        assertThat(matchRecords.size()).isEqualTo(1);
    }

    @Test
    public void whenIndexId_ShouldBeFound() {
        createRecordsAndCheck();
        assertThat(database.findRecordById("g1")).isNotNull();
        assertThat(database.findRecordById("s1")).isNotNull();
        assertThat(database.findRecordById("g2")).isNotNull();
        assertThat(database.findRecordById("g3")).isNotNull();
        assertThat(database.findRecordById("g4")).isNotNull();
        assertThat(database.findRecordById("g5")).isNotNull();
        assertThat(database.findRecordById("g6")).isNotNull();
        assertThat(database.findRecordById("g7")).isNotNull();
        assertThat(database.findRecordById("g8")).isNotNull();
        assertThat(database.findRecordById("g9")).isNotNull();
        assertThat(database.findRecordById("g10")).isNotNull();
    }

    private void createRecordsAndCheck() {
        Collection<Record> records = RecordProvider.createIdNameRecords();
        for(Record record : records) {
            database.index(record);
        }
        database.commit();
        assertThat(database.findRecordById(RecordProvider.SAMPLE_ID)).isNotNull();
    }
}