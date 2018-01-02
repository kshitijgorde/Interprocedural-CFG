// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.util;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class FileChooserFilter extends FileFilter
{
    private String ext;
    private String desc;
    
    public FileChooserFilter(final String ext, final String desc) {
        this.ext = ext;
        this.desc = desc;
    }
    
    public boolean accept(final File file) {
        return !file.isFile() || file.getName().toLowerCase().endsWith(this.ext);
    }
    
    public String getExtension() {
        return this.ext;
    }
    
    public String getDescription() {
        return this.desc;
    }
}
