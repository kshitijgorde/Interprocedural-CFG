// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.api.upload;

import jmaster.jumploader.model.api.upload.IUploadFile;
import jmaster.jumploader.view.api.IGenericViewListener;

public interface IUploadViewListener extends IGenericViewListener
{
    void uploadViewStartAction(final IUploadView p0);
    
    void uploadViewStopAction(final IUploadView p0);
    
    void uploadViewStopFilesAction(final IUploadView p0, final IUploadFile[] p1);
    
    void uploadViewRetryFilesAction(final IUploadView p0, final IUploadFile[] p1);
    
    void uploadViewAddFilesAction(final IUploadView p0, final String[] p1);
    
    void uploadViewRemoveFilesAction(final IUploadView p0, final IUploadFile[] p1);
}
