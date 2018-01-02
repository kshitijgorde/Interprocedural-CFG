// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.swing.filetree;

import java.io.File;
import javax.swing.filechooser.FileSystemView;
import java.util.Comparator;

public class DefaultFileComparator implements Comparator
{
    protected FileSystemView A;
    
    public DefaultFileComparator() {
        this.A = FileSystemView.getFileSystemView();
    }
    
    public int compare(final Object o, final Object o2) {
        final File file = (File)o;
        final boolean fileSystem = this.A.isFileSystem(file);
        final boolean b = fileSystem && file.isDirectory();
        final boolean b2 = fileSystem && file.isFile();
        final File file2 = (File)o2;
        final boolean fileSystem2 = this.A.isFileSystem(file2);
        final boolean b3 = fileSystem2 && file2.isDirectory();
        final boolean b4 = fileSystem2 && file2.isFile();
        int compareToIgnoreCase;
        if (!fileSystem && fileSystem2) {
            compareToIgnoreCase = -1;
        }
        else if (fileSystem && !fileSystem2) {
            compareToIgnoreCase = 1;
        }
        else if (b && b4) {
            compareToIgnoreCase = -1;
        }
        else if (b2 && b3) {
            compareToIgnoreCase = 1;
        }
        else {
            compareToIgnoreCase = file.getName().compareToIgnoreCase(file2.getName());
        }
        return compareToIgnoreCase;
    }
}
