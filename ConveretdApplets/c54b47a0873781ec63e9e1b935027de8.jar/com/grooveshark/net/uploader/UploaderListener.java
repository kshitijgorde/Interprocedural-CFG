// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.net.uploader;

import java.io.File;

public interface UploaderListener
{
    void uploadStarted(final int p0);
    
    void fileUploaded(final File p0);
    
    void fileSkipped(final File p0);
    
    void uploadCompleted();
}
