// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.masker;

import java.io.File;
import javax.swing.filechooser.FileFilter;

final class PNGFileFilter extends FileFilter
{
    public boolean accept(final File f) {
        if (f == null) {
            return false;
        }
        if (f.isDirectory()) {
            return true;
        }
        final String name = f.getName().toLowerCase();
        return name.endsWith(".png");
    }
    
    public String getDescription() {
        return "*.png image files";
    }
}
