// 
// Decompiled by Procyon v0.5.30
// 

package fileutil.filecomparator;

import fileutil.FileUtil;
import fileutil.SFile;
import java.util.Comparator;

public class FileTypeComparator implements Comparator<SFile>
{
    private boolean _isASC;
    private boolean _isRoot;
    
    public FileTypeComparator(final boolean isASC, final boolean isRoot) {
        this._isASC = isASC;
        this._isRoot = isRoot;
    }
    
    public int compare(final SFile sFile, final SFile sFile2) {
        if (!this._isRoot) {
            if (!sFile.isFile() && sFile2.isFile()) {
                return -1;
            }
            if (sFile.isFile() && !sFile2.isFile()) {
                return 1;
            }
            if (!sFile.isFile() && !sFile2.isFile()) {
                return -1;
            }
        }
        return this._isASC ? this.compareType(sFile, sFile2) : this.compareType(sFile2, sFile);
    }
    
    private int compareType(final SFile sFile, final SFile sFile2) {
        return FileUtil.LOCALE.compare(sFile.getTypeDescription(), sFile2.getTypeDescription());
    }
}
