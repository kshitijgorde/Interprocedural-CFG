// 
// Decompiled by Procyon v0.5.30
// 

package fileutil.filefilter;

import fileutil.SFile;

public class UploadFileFilter implements SFileFilter
{
    public boolean accept(final SFile sFile) {
        return sFile.canRead() && (sFile.isFile() || sFile.isDirectory());
    }
}
