// 
// Decompiled by Procyon v0.5.30
// 

package gui;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class SimpleFileFilter extends FileFilter
{
    private String m_strDesc;
    private String m_strExtension;
    private int filterType;
    
    public int getFilterType() {
        return this.filterType;
    }
    
    public SimpleFileFilter() {
        this.m_strDesc = "Public X.509 Certificate (*.cer)";
        this.m_strExtension = ".cer";
    }
    
    public boolean accept(final File file) {
        return file.isDirectory() || file.getName().endsWith(this.m_strExtension);
    }
    
    public String getDescription() {
        return this.m_strDesc;
    }
}
