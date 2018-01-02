// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.jsface.api;

import jmaster.jumploader.model.api.A.C;
import jmaster.jumploader.model.api.common.IListSelection;
import jmaster.jumploader.model.api.common.IAttributeSet;
import jmaster.jumploader.model.api.common.ITransferProgress;
import jmaster.jumploader.model.api.upload.IUploadFile;

public interface IJSUploader
{
    public static final int STATUS_READY = 0;
    public static final int STATUS_UPLOADING = 1;
    
    void destroy();
    
    boolean isFileAdditionEnabled();
    
    void setFileAdditionEnabled(final boolean p0);
    
    boolean isFileRemovalEnabled();
    
    void setFileRemovalEnabled(final boolean p0);
    
    int getFileCount();
    
    IUploadFile getFile(final String p0);
    
    IUploadFile[] getAllFiles();
    
    IUploadFile getFileByPath(final String p0);
    
    int getFileCountByStatus(final String p0);
    
    IUploadFile[] getFilesByStatus(final String p0);
    
    int indexOfFile(final IUploadFile p0);
    
    long getFilesLength();
    
    String addFile(final String p0);
    
    String addFile(final String p0, final String p1);
    
    String addFile(final String p0, final String p1, final String p2);
    
    String removeFile(final IUploadFile p0);
    
    String removeFileAt(final String p0);
    
    int getStatus();
    
    boolean isReady();
    
    boolean isUploading();
    
    String startUpload();
    
    String stopUpload();
    
    boolean canStartUpload();
    
    boolean canStopUpload();
    
    String stopFileUpload(final IUploadFile p0);
    
    String stopFileUploadAt(final String p0);
    
    String retryFileUpload(final IUploadFile p0);
    
    String retryFileUploadAt(final String p0);
    
    ITransferProgress getTransferProgress();
    
    IAttributeSet getAttributeSet();
    
    IListSelection getSelection();
    
    void setUploadEnabled(final boolean p0);
    
    boolean isUploadEnabled();
    
    IUploadFile processDocument(final String p0, final String p1, final String p2, final String p3, final String p4) throws C;
    
    IUploadFile processDocument(final String p0, final String p1, final String p2, final String p3) throws C;
    
    boolean isDownloading();
}
