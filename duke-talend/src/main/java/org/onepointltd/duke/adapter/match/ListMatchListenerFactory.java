package org.onepointltd.duke.adapter.match;

/**
 * Factory used to create the list match listener based on the
 */
public class ListMatchListenerFactory {

    public static ListMatchListener createInstance(boolean captureMaybe) {
        return captureMaybe ? new ListMaybeMatchListener() : new ListMatchListener();
    }
}
