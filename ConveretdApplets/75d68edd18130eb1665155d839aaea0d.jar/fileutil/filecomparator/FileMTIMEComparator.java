// 
// Decompiled by Procyon v0.5.30
// 

package fileutil.filecomparator;

import fileutil.SFile;
import java.util.Comparator;

public class FileMTIMEComparator implements Comparator<SFile>
{
    private boolean _isASC;
    
    public FileMTIMEComparator(final boolean isASC) {
        this._isASC = isASC;
    }
    
    public int compare(final SFile sFile, final SFile sFile2) {
        return this._isASC ? this.compareMTIME(sFile, sFile2) : this.compareMTIME(sFile2, sFile);
    }
    
    private int compareMTIME(final SFile sFile, final SFile sFile2) {
        return (sFile.lastModified() < sFile2.lastModified()) ? -1 : 1;
    }
}
