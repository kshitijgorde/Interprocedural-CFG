// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.songscanner;

import java.util.Iterator;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.util.List;
import java.io.FileFilter;

public class ScanFilter implements FileFilter
{
    private List<String> acceptedExtensions;
    private List<File> exclusions;
    
    public ScanFilter(final String... validExtensions) {
        this(new ArrayList<File>(), validExtensions);
    }
    
    public ScanFilter(final List<File> exclusions, final String... validExtensions) {
        this.exclusions = exclusions;
        this.acceptedExtensions = new ArrayList<String>();
        for (String type : validExtensions) {
            type = type.toLowerCase().replaceAll("\\.", "");
            this.acceptedExtensions.add(type);
        }
    }
    
    public boolean accept(final File file) {
        final String[] split = file.getName().split("\\.");
        final String extension = split[split.length - 1];
        final boolean validFolder = file.isDirectory() && !this.isExcluded(file);
        final boolean validFile = !this.isLink(file) && !this.isExcluded(file) && this.acceptedExtensions.contains(extension.toLowerCase());
        return validFolder || validFile;
    }
    
    private boolean isLink(final File file) {
        try {
            return !file.equals(file.getCanonicalFile());
        }
        catch (IOException e) {
            return false;
        }
    }
    
    private boolean isExcluded(final File file) {
        for (final File f : this.exclusions) {
            if (file.getAbsolutePath().contains(f.getAbsolutePath() + File.separator)) {
                return true;
            }
        }
        return false;
    }
}
