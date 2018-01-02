// 
// Decompiled by Procyon v0.5.30
// 

package com.pluraprocessing.node.domain;

import java.net.URL;
import java.util.List;

public class PluraClassLoaderVersion
{
    private List<URL> urls;
    private String versionId;
    private int numberTimesNoWork;
    private int noWorkThreshold;
    private boolean isTimeSensitive;
    
    public PluraClassLoaderVersion(final List<URL> urls, final String version, final int minThreshold) {
        this.numberTimesNoWork = 1;
        this.noWorkThreshold = 0;
        this.isTimeSensitive = false;
        this.urls = urls;
        this.versionId = version;
        this.noWorkThreshold = minThreshold;
    }
    
    public List<URL> getUrls() {
        return this.urls;
    }
    
    public void setUrls(final List<URL> urls) {
        this.urls = urls;
    }
    
    public String getVersionId() {
        return this.versionId;
    }
    
    public void setVersionId(final String versionId) {
        this.versionId = versionId;
    }
    
    public int getNumberTimesNoWork() {
        return this.numberTimesNoWork;
    }
    
    public void setNumberTimesNoWork(final int numberTimesNoWork) {
        this.numberTimesNoWork = numberTimesNoWork;
    }
    
    public int getNoWorkThreshold() {
        return this.noWorkThreshold;
    }
    
    public boolean isTimeSensitive() {
        return this.isTimeSensitive;
    }
    
    public void setTimeSensitive(final boolean isTimeSensitive) {
        this.isTimeSensitive = isTimeSensitive;
    }
    
    public void incrementNoWorkThreshold(final long ms) {
        this.noWorkThreshold += (int)(ms / 500L);
    }
}
