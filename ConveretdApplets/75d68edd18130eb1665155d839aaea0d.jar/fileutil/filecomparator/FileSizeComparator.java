// 
// Decompiled by Procyon v0.5.30
// 

package fileutil.filecomparator;

import fileutil.SpaceUtil;
import fileutil.FileUtil;
import fileutil.SFile;
import java.util.Comparator;

public class FileSizeComparator implements Comparator<SFile>
{
    private boolean _isASC;
    private boolean _isRoot;
    
    public FileSizeComparator(final boolean isASC, final boolean isRoot) {
        this._isASC = isASC;
        this._isRoot = isRoot;
    }
    
    public int compare(final SFile sFile, final SFile sFile2) {
        if (this._isRoot) {
            return this._isASC ? this.compareDiskSize(sFile, sFile2) : this.compareDiskSize(sFile2, sFile);
        }
        if (!sFile.isFile() && !sFile2.isFile()) {
            return FileUtil.LOCALE.compare(sFile.getName(), sFile2.getName());
        }
        return this._isASC ? this.compareSize(sFile, sFile2) : this.compareSize(sFile2, sFile);
    }
    
    private int compareSize(final SFile sFile, final SFile sFile2) {
        if (!sFile.isFile() && sFile2.isFile()) {
            return -1;
        }
        if (sFile.isFile() && !sFile2.isFile()) {
            return 1;
        }
        return (sFile.length() < sFile2.length()) ? -1 : 1;
    }
    
    private int compareDiskSize(final SFile sFile, final SFile sFile2) {
        if (SpaceUtil._isRootAccessible) {
            return (SpaceUtil.getTotalSpace(sFile) < SpaceUtil.getTotalSpace(sFile2)) ? -1 : 1;
        }
        return FileUtil.LOCALE.compare(sFile.getName(), sFile2.getName());
    }
}
