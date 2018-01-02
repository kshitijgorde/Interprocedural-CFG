// 
// Decompiled by Procyon v0.5.30
// 

package medusa;

import java.io.File;
import java.io.FilenameFilter;

class DataFileFilter implements FilenameFilter
{
    String[] acceptedNames;
    
    public DataFileFilter() {
        (this.acceptedNames = new String[2])[0] = ".dat";
        this.acceptedNames[1] = ".txt";
    }
    
    public DataFileFilter(final String[] acceptedNames) {
        this.acceptedNames = acceptedNames;
    }
    
    public boolean accept(final File file, final String s) {
        for (int i = 0; i < this.acceptedNames.length; ++i) {
            if (s.endsWith(this.acceptedNames[i])) {
                return true;
            }
        }
        return false;
    }
}
