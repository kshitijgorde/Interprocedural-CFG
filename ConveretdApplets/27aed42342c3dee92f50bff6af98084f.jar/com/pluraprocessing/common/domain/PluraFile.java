// 
// Decompiled by Procyon v0.5.30
// 

package com.pluraprocessing.common.domain;

import java.util.UUID;

public class PluraFile
{
    private UUID id;
    private int nodeServerId;
    private byte[] fileContent;
    private String baseDirectory;
    private boolean isWrittenToDisk;
    private boolean failed;
    
    public PluraFile(final UUID id, final int nodeServerId, final byte[] fileContent, final String baseDirectory) {
        this.id = id;
        this.nodeServerId = nodeServerId;
        this.fileContent = fileContent;
        this.baseDirectory = baseDirectory;
        this.failed = false;
        this.isWrittenToDisk = false;
    }
    
    public boolean isWrittenToDisk() {
        return this.isWrittenToDisk;
    }
    
    public void setWrittenToDisk(final boolean isWrittenToDisk) {
        this.isWrittenToDisk = isWrittenToDisk;
    }
    
    public UUID getId() {
        return this.id;
    }
    
    public void setId(final UUID id) {
        this.id = id;
    }
    
    public int getNodeServerId() {
        return this.nodeServerId;
    }
    
    public void setNodeServerId(final int nodeServerId) {
        this.nodeServerId = nodeServerId;
    }
    
    public byte[] getFileContent() {
        return this.fileContent;
    }
    
    public void setFileContent(final byte[] fileContent) {
        this.fileContent = fileContent;
    }
    
    public String getBaseDirectory() {
        return this.baseDirectory;
    }
    
    public void setBaseDirectory(final String baseDirectory) {
        this.baseDirectory = baseDirectory;
    }
    
    public boolean isFailed() {
        return this.failed;
    }
    
    public void setFailed(final boolean failed) {
        this.failed = failed;
    }
}
