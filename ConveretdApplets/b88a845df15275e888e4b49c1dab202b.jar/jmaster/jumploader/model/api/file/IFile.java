// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.model.api.file;

import jmaster.jumploader.model.api.common.IAttributeSet;
import java.io.File;

public interface IFile
{
    String getId();
    
    void setId(final String p0);
    
    File getFile();
    
    String getPath();
    
    String getName();
    
    long getLength();
    
    boolean isDirectory();
    
    boolean isFile();
    
    boolean isFileSystem();
    
    boolean isDrive();
    
    IAttributeSet getAttributeSet();
    
    String getOriginalFilePath();
    
    Double getCompressionRatio();
    
    String getMimeType();
    
    boolean isHidden();
    
    boolean isChecked();
    
    void setChecked(final boolean p0);
    
    boolean isCheckedUpdatable();
    
    void setCheckedUpdatable(final boolean p0);
}
