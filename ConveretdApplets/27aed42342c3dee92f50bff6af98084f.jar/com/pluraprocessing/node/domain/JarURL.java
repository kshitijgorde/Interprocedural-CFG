// 
// Decompiled by Procyon v0.5.30
// 

package com.pluraprocessing.node.domain;

public class JarURL
{
    private byte[] jarBytes;
    private long timeToLoad;
    
    public JarURL(final byte[] jarBytes, final long timeToLoad) {
        this.jarBytes = jarBytes;
        this.timeToLoad = timeToLoad;
    }
    
    public byte[] getJarBytes() {
        return this.jarBytes;
    }
    
    public void setJarBytes(final byte[] jarBytes) {
        this.jarBytes = jarBytes;
    }
    
    public long getTimeToLoad() {
        return this.timeToLoad;
    }
    
    public void setTimeToLoad(final long timeToLoad) {
        this.timeToLoad = timeToLoad;
    }
}
