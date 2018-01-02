// 
// Decompiled by Procyon v0.5.30
// 

package fileutil.filefilter;

import fileutil.SFile;
import action.list.ListHandler;

public class FileNameAndTypeFilter implements SFileFilter
{
    private ListHandler.LIST_TYPE _ListType;
    private String _strQuery;
    
    public FileNameAndTypeFilter(final ListHandler.LIST_TYPE listType, final String strQuery) {
        this._strQuery = strQuery;
        this._ListType = listType;
    }
    
    public boolean accept(final SFile sFile) {
        if (sFile.isHidden() || (!sFile.isFile() && !sFile.isDirectory())) {
            return false;
        }
        if (this._ListType == ListHandler.LIST_TYPE.DIR_TYPE) {
            if (sFile.isFile()) {
                return false;
            }
        }
        else if (this._ListType == ListHandler.LIST_TYPE.FILE_TYPE && sFile.isDirectory()) {
            return false;
        }
        return this._strQuery == "" || -1 != this.indexOfIgnoreCase(sFile.getName(), this._strQuery);
    }
    
    public int indexOfIgnoreCase(final String s, final String s2) {
        for (int n = 0, length = s2.length(); s.length() > n + length - 1; ++n) {
            if (s.regionMatches(true, n, s2, 0, length)) {
                return n;
            }
        }
        return -1;
    }
}
