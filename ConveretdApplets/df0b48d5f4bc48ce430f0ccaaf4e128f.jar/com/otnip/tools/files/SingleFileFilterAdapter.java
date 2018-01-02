// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.tools.files;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class SingleFileFilterAdapter extends FileFilter implements java.io.FileFilter
{
    private String extension;
    private String description;
    
    public SingleFileFilterAdapter(final String extension, final String description) {
        this.extension = null;
        this.description = null;
        this.extension = extension;
        this.description = description;
    }
    
    public String getExtension() {
        return this.extension;
    }
    
    public boolean accept(final File file) {
        return this.extension.equalsIgnoreCase(FileTools.getExtension(file)) || file.isDirectory();
    }
    
    public String getDescription() {
        return this.description;
    }
}
