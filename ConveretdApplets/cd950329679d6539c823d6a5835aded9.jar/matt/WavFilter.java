// 
// Decompiled by Procyon v0.5.30
// 

package matt;

import java.io.File;
import java.io.FilenameFilter;
import javax.swing.filechooser.FileFilter;

class WavFilter extends FileFilter implements FilenameFilter
{
    public boolean accept(final File dir, final String name) {
        return name.contains(".wav") || name.contains(".WAV") || name.contains(".mp3") || name.contains(".MP3");
    }
    
    public boolean accept(final File f) {
        return f.isDirectory() || f.getName().toUpperCase().indexOf(".WAV") > -1;
    }
    
    public String getDescription() {
        return "*.WAV";
    }
}
