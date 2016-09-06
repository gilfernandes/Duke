package org.onepointltd.duke.adapter;

import no.priv.garshol.duke.Processor;
import no.priv.garshol.duke.matchers.AbstractMatchListener;

/**
 * Factory used to create the deduplication object.
 */
public class ProcessorFactory {

    public static Processor createInstance(ProcessorParameters parameter,
                                           AbstractMatchListener... listeners) {
        final ProcessorConfigurationFactory.ProcessorConfiguration processorConfiguration =
                ProcessorConfigurationFactory.createInstance(parameter);
        final Processor processor = new Processor(processorConfiguration.getConfiguration(),
                processorConfiguration.getDatabase());
        if(listeners != null) {
            for (AbstractMatchListener listener : listeners) {
                processor.addMatchListener(listener);
            }
        }
        return processor;
    }


}
