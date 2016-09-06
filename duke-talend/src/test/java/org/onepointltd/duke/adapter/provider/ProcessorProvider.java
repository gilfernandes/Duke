package org.onepointltd.duke.adapter.provider;

import no.priv.garshol.duke.Processor;
import no.priv.garshol.duke.Property;
import org.onepointltd.duke.adapter.ProcessorFactory;
import org.onepointltd.duke.adapter.ProcessorParameters;
import org.onepointltd.duke.adapter.SearchDatabase;
import org.onepointltd.duke.adapter.match.ListMatchListener;
import org.onepointltd.duke.adapter.match.ListMatchListenerFactory;

import java.util.List;

/**
 * Creates databases for test purposes.
 */
public class ProcessorProvider {

    public static ProcessorConfig createBasicConfig(int editDistance) {
        return createBase(editDistance, null, PropertyProvider.createCompleteNameProperties());
    }

    public static ProcessorConfig createTalendConfig(int editDistance) {
        return createBase(editDistance, "talend", PropertyProvider.createTalendProperties());
    }

    private static ProcessorConfig createBase(int editDistance, String index, List<Property> completeNameProperties) {
        final ProcessorParameters processorParameters = new ProcessorParameters(
                SearchDatabase.ELASTIC_SEARCH,
                completeNameProperties, 0.45,
                "localhost:9300", true);
        processorParameters.setEditDistance(editDistance);
        if(index != null) {
            processorParameters.setIndexName(index);
        }
        processorParameters.setLogESQueries(true);
        ListMatchListener maybeMatchListener =  ListMatchListenerFactory.createInstance(true);
        Processor processor = ProcessorFactory.createInstance(processorParameters, maybeMatchListener);
        final ProcessorConfig processorConfig = new ProcessorConfig(maybeMatchListener, processor);
        return processorConfig;
    }

    public static class ProcessorConfig {

        private final ListMatchListener listMaybeMatchListener;

        private final Processor processor;

        public ProcessorConfig(ListMatchListener listMaybeMatchListener, Processor processor) {
            this.listMaybeMatchListener = listMaybeMatchListener;
            this.processor = processor;
        }

        public ListMatchListener getListMaybeMatchListener() {
            return listMaybeMatchListener;
        }

        public Processor getProcessor() {
            return processor;
        }
    }
}
