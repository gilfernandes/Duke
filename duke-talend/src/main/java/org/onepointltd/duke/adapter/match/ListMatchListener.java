package org.onepointltd.duke.adapter.match;

import no.priv.garshol.duke.Record;
import no.priv.garshol.duke.matchers.AbstractMatchListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Listener which saves all matching results into a
 */
public class ListMatchListener extends AbstractMatchListener {

    private final List<MatchResult> matches;
    private int records;
    private int noMatch;
    private int maybes;

    public ListMatchListener() {
        this.matches = new ArrayList<>();
    }

    public List<MatchResult> getMatches() {
        return matches;
    }

    public int getRecordCount() {
        return records;
    }

    public int getNoMatchCount() {
        return noMatch;
    }

    public int getMaybeCount() {
        return maybes;
    }

    public List<MatchResult> getMaybeResultList() {
        return Collections.emptyList();
    }

    public void batchReady(int size) {
        records += size;
    }

    public void matches(Record r1, Record r2, double confidence) {
        matches.add(new MatchResult(r1, r2, confidence));
    }

    public void matchesPerhaps(Record r1, Record r2, double confidence) {
        maybes++;
    }

    public void noMatchFor(Record r) {
        noMatch++;
    }

    public void clear() {
        matches.clear();
        records = 0;
        noMatch = 0;
        maybes = 0;
    }
}
