// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.util;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class FileSelection
{
    private File selectedFile;
    private FileFilter selectedFileFilter;
    
    public FileSelection(final File selectedFile, final FileFilter selectedFileFilter) {
        this.selectedFile = null;
        this.selectedFileFilter = null;
        this.selectedFile = selectedFile;
        this.selectedFileFilter = selectedFileFilter;
    }
    
    public File getSelectedFile() {
        return this.selectedFile;
    }
    
    public FileFilter getSelectedFileFilter() {
        return this.selectedFileFilter;
    }
}
