package org.onepointltd.duke.adapter;

import no.priv.garshol.duke.Processor;
import no.priv.garshol.duke.Record;
import no.priv.garshol.duke.databases.es.ElasticSearchDatabase;
import org.junit.Test;
import org.onepointltd.duke.adapter.match.ListMatchListener;
import org.onepointltd.duke.adapter.match.MatchResult;
import org.onepointltd.duke.adapter.provider.ProcessorProvider;
import org.onepointltd.duke.adapter.provider.RecordProvider;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProcessorTest {

    private Processor processor;

    private ListMatchListener maybeMatchListener;

    @Test
    public void whenDeduplication_ShouldFindDuplicates() {
        createConfig(0);
        checkDuplicates(6);
    }

    @Test
    public void whenDeduplicationFuzzy_ShouldFindDuplicates() {
        createConfig(1);
        checkDuplicates(14);
    }

    @Test
    public void whenMatchSingleResult_ShouldFindDuplicates() {
        createTalendConfig(1);
        Collection<Record> recs = RecordProvider.createTalendRecords();
        for(Record rec : recs) {
            processor.matchSingleRecord(rec, true, processor.getDatabase());
        }
        List<MatchResult> matches = maybeMatchListener.getMatches();
        assertThat(matches).isNotNull();
        assertThat(matches.size()).isNotZero();
        for(MatchResult mr : maybeMatchListener.getMatches()) {
            System.out.printf("Given Name (orig): %s - Given name (candidate): %s%n",
                    mr.getR1().getValue("givenName"), mr.getR2().getValue("givenName"));
        }
    }

    private void createTalendConfig(int editDistance) {
        ProcessorProvider.ProcessorConfig config = ProcessorProvider.createTalendConfig(editDistance);
        maybeMatchListener = config.getListMaybeMatchListener();
        processor = config.getProcessor();
        ((ElasticSearchDatabase) processor.getDatabase()).init();
    }

    private void createConfig(int editDistance) {
        ProcessorProvider.ProcessorConfig config = ProcessorProvider.createBasicConfig(editDistance);
        maybeMatchListener = config.getListMaybeMatchListener();
        processor = config.getProcessor();
    }

    private void checkDuplicates(int expectedMatches) {
        final Collection<Record> fullNameRecords = RecordProvider.createFullNameRecords();
        processor.deduplicate(fullNameRecords);
        final List<MatchResult> matches = maybeMatchListener.getMatches();
        assertThat(maybeMatchListener.getRecordCount()).isEqualTo(5);
        assertThat(matches.size()).isEqualTo(expectedMatches);
        printMatchResult();
    }

    private void printMatchResult() {
        for(MatchResult mr : maybeMatchListener.getMatches()) {
            System.out.printf("%s: %s: %s%n", mr.getR1(), mr.getR2(), mr.getConfidence());
        }
    }
}
