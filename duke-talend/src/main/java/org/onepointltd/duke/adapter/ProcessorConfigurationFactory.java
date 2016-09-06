package org.onepointltd.duke.adapter;

import no.priv.garshol.duke.Configuration;
import no.priv.garshol.duke.ConfigurationImpl;
import no.priv.garshol.duke.Database;
import no.priv.garshol.duke.databases.es.ElasticSearchDatabase;

/**
 * Factory used to create the database index adapter.
 */
public class ProcessorConfigurationFactory {

    public static ProcessorConfiguration createInstance(ProcessorParameters processorParameters) {
        ConfigurationImpl config = new ConfigurationImpl();
        config.setProperties(processorParameters.getProps());
        config.setThreshold(processorParameters.getConfigThreshold());
        if (processorParameters.getMaybeThreshold() > 0.0) {
            config.setMaybeThreshold(processorParameters.getMaybeThreshold());
        }
        Database db;
        switch (processorParameters.getSearchDatabase()) {
            case ELASTIC_SEARCH:
                db = new ElasticSearchDatabase.Builder()
                        .setTAddresses(processorParameters.gettAddresses())
                        .setOverwrite(processorParameters.isOverwrite())
                        .setEditDistance(processorParameters.getEditDistance())
                        .setLogESQueries(processorParameters.isLogESQueries())
                        .setIndexName(processorParameters.getIndexName())
                        .setMaxSearchHits(processorParameters.getMaxSearchHits())
                        .setCluster(processorParameters.getElasticSearchCluster())
                        .build();
                break;
            default:
                throw new IllegalArgumentException("Database not supported yet");
        }
        db.setConfiguration(config);
        config.addDatabase(db);
        return new ProcessorConfiguration(config, db);
    }

    public static class ProcessorConfiguration {

        private final Configuration configuration;

        private final Database database;

        public ProcessorConfiguration(Configuration configuration, Database database) {
            this.configuration = configuration;
            this.database = database;
        }

        public Configuration getConfiguration() {
            return configuration;
        }

        public Database getDatabase() {
            return database;
        }
    }
}
