// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.model.api.upload;

import jmaster.jumploader.model.api.A.C;

public interface B
{
    void uploaderFileAdditionEnabledChanged(final IUploader p0);
    
    void uploaderFileRemovalEnabledChanged(final IUploader p0);
    
    void uploaderUploadEnabledChanged(final IUploader p0);
    
    void uploaderFilesReset(final IUploader p0);
    
    void uploaderFileAdded(final IUploader p0, final IUploadFile p1);
    
    void uploaderFileRemoved(final IUploader p0, final IUploadFile p1);
    
    void uploaderFileMoved(final IUploader p0, final IUploadFile p1, final int p2);
    
    void uploaderFileStatusChanged(final IUploader p0, final IUploadFile p1);
    
    void uploaderFileUpdated(final IUploader p0, final IUploadFile p1);
    
    void uploaderStatusChanged(final IUploader p0);
    
    void uploaderFileAddFailed(final IUploader p0, final C p1);
}
