package org.onepointltd.duke.adapter.match;

import no.priv.garshol.duke.Record;

import java.util.ArrayList;
import java.util.List;

/**
 * Extension of ListMatchListener which captures also the maybe matches in a list.
 */
public class ListMaybeMatchListener extends ListMatchListener {

    private final List<MatchResult> maybeResultList;

    public ListMaybeMatchListener() {
        super();
        maybeResultList = new ArrayList<>();
    }

    @Override
    public void matchesPerhaps(Record r1, Record r2, double confidence) {
        super.matchesPerhaps(r1, r2, confidence);
        maybeResultList.add(new MatchResult(r1, r2, confidence, true));
    }

    public List<MatchResult> getMaybeResultList() {
        return maybeResultList;
    }

    @Override
    public void clear() {
        super.clear();
        maybeResultList.clear();
    }
}
