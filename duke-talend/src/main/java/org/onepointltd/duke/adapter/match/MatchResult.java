package org.onepointltd.duke.adapter.match;

import no.priv.garshol.duke.Record;

/**
 * Contains the result of a match.
 */
public class MatchResult {

    private final Record r1;

    private final Record r2;

    private final double confidence;

    private final boolean maybe;

    public MatchResult(Record r1, Record r2, double confidence) {
        this(r1, r2, confidence, false);
    }

    public MatchResult(Record r1, Record r2, double confidence, boolean maybe) {
        this.r1 = r1;
        this.r2 = r2;
        this.confidence = confidence;
        this.maybe = maybe;
    }

    public Record getR1() {
        return r1;
    }

    public Record getR2() {
        return r2;
    }

    public double getConfidence() {
        return confidence;
    }

    public boolean isMaybe() {
        return maybe;
    }

}
