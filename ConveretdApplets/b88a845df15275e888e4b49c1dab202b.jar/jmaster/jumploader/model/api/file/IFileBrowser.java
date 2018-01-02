// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.model.api.file;

import java.io.File;

public interface IFileBrowser
{
    void addListener(final IFileBrowserListener p0);
    
    void removeListener(final IFileBrowserListener p0);
    
    File getPath();
    
    void setPath(final File p0);
    
    int getFileCount();
    
    IFile getFile(final int p0);
}
