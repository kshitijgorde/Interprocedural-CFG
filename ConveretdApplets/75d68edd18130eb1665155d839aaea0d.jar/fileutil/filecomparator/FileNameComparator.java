// 
// Decompiled by Procyon v0.5.30
// 

package fileutil.filecomparator;

import fileutil.FileUtil;
import fileutil.SFile;
import java.util.Comparator;

public class FileNameComparator implements Comparator<SFile>
{
    private boolean _isASC;
    
    public FileNameComparator(final boolean isASC) {
        this._isASC = isASC;
    }
    
    public int compare(final SFile sFile, final SFile sFile2) {
        if (!sFile.isFile() && sFile2.isFile()) {
            return -1;
        }
        if (sFile.isFile() && !sFile2.isFile()) {
            return 1;
        }
        return this._isASC ? this.compareName(sFile, sFile2) : this.compareName(sFile2, sFile);
    }
    
    private int compareName(final SFile sFile, final SFile sFile2) {
        return FileUtil.LOCALE.compare(sFile.getAbsolutePath(), sFile2.getAbsolutePath());
    }
}
