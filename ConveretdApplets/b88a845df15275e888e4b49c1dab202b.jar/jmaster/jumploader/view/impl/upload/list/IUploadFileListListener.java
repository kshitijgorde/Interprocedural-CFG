// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.upload.list;

import jmaster.jumploader.model.api.upload.IUploadFile;

public interface IUploadFileListListener
{
    void uflRemoveFilesAction(final UploadFileList p0, final IUploadFile[] p1);
    
    void uflAddFilesAction(final UploadFileList p0, final String[] p1);
    
    void uflStopUploadAction(final UploadFileList p0, final IUploadFile[] p1);
    
    void uflRetryUploadAction(final UploadFileList p0, final IUploadFile[] p1);
}
