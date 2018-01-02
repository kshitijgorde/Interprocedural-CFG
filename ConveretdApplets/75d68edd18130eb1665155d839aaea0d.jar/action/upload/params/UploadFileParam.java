// 
// Decompiled by Procyon v0.5.30
// 

package action.upload.params;

import fileutil.SFile;

public class UploadFileParam
{
    private SFile _File;
    private String _RemoteDir;
    
    public UploadFileParam(final String remoteDir, final SFile file) {
        this._File = file;
        this._RemoteDir = remoteDir;
    }
    
    public SFile getFile() {
        return this._File;
    }
    
    public String getRemoteDir() {
        return this._RemoteDir;
    }
}
