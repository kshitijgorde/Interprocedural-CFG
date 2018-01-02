// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.tools.files;

import java.io.File;
import java.util.StringTokenizer;
import java.util.ArrayList;
import javax.swing.filechooser.FileFilter;

public class MultiFileFilterAdapter extends FileFilter implements java.io.FileFilter
{
    private ArrayList<String> extensions;
    private String description;
    
    public MultiFileFilterAdapter(final String extensions) {
        this.extensions = new ArrayList<String>();
        this.description = "Multi-File Filter Adapter";
        this.setExtention(extensions);
    }
    
    public MultiFileFilterAdapter(final String extensions, final String description) {
        this.extensions = new ArrayList<String>();
        this.description = "Multi-File Filter Adapter";
        this.setExtention(extensions);
        this.description = description;
    }
    
    private void setExtention(final String extensions) {
        final StringTokenizer str = new StringTokenizer(extensions, ", ");
        this.description = "All Supported Types {";
        while (str.hasMoreTokens()) {
            final String extension = str.nextToken().toLowerCase();
            this.extensions.add(extension);
            this.description = this.description + "." + extension;
            if (str.hasMoreTokens()) {
                this.description += ", ";
            }
        }
        this.description += "}";
    }
    
    public boolean accept(final File file) {
        final String ext = FileTools.getExtension(file).toLowerCase();
        return this.extensions.contains(ext) || file.isDirectory();
    }
    
    public String[] getExtensions() {
        return this.extensions.toArray(new String[this.extensions.size()]);
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public SingleFileFilterAdapter[] toSingleFileFilterArray() {
        final SingleFileFilterAdapter[] singleFilterAdaptors = new SingleFileFilterAdapter[this.extensions.size()];
        for (int i = 0; i < this.extensions.size(); ++i) {
            final String ext = this.extensions.get(i);
            singleFilterAdaptors[i] = new SingleFileFilterAdapter(ext, FileTools.getDescription(ext) + "  (." + ext + ")");
        }
        return singleFilterAdaptors;
    }
}
