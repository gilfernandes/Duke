package org.onepointltd.duke.adapter;

import no.priv.garshol.duke.Property;

import java.util.List;

/**
 * Parameter object class used to create database index adapters.
 */
public class ProcessorParameters {

    private final SearchDatabase searchDatabase;
    private final List<Property> props;
    private final double configThreshold;
    private double maybeThreshold = 0.5;
    private final String tAddresses;
    private final boolean overwrite;
    private int editDistance = 1;
    private String indexName;
    private String elasticSearchCluster = "elasticsearch";
    private int maxSearchHits = 100;
    private boolean logESQueries;

    public ProcessorParameters(SearchDatabase searchDatabase, List<Property> props,
                               double configThreshold,
                               String tAddresses, boolean overwrite) {
        this.searchDatabase = searchDatabase;
        this.props = props;
        this.configThreshold = configThreshold;
        this.tAddresses = tAddresses;
        this.overwrite = overwrite;
    }

    public SearchDatabase getSearchDatabase() {
        return searchDatabase;
    }

    public List<Property> getProps() {
        return props;
    }

    public double getConfigThreshold() {
        return configThreshold;
    }

    public String gettAddresses() {
        return tAddresses;
    }

    public boolean isOverwrite() {
        return overwrite;
    }

    public void setMaybeThreshold(double maybeThreshold) {
        this.maybeThreshold = maybeThreshold;
    }

    public double getMaybeThreshold() {
        return maybeThreshold;
    }

    public void setEditDistance(int editDistance) {
        this.editDistance = editDistance;
    }

    public int getEditDistance() {
        return editDistance;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public String getElasticSearchCluster() {
        return elasticSearchCluster;
    }

    public void setElasticSearchCluster(String elasticSearchCluster) {
        this.elasticSearchCluster = elasticSearchCluster;
    }

    public void setMaxSearchHits(int maxSearchHits) {
        this.maxSearchHits = maxSearchHits;
    }

    public int getMaxSearchHits() {
        return maxSearchHits;
    }

    public boolean isLogESQueries() {
        return logESQueries;
    }

    public void setLogESQueries(boolean logESQueries) {
        this.logESQueries = logESQueries;
    }
}
