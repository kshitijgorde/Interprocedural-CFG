// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.model.api.upload;

import jmaster.jumploader.model.api.common.IAttributeSet;
import jmaster.jumploader.model.api.common.ITransferProgress;
import jmaster.jumploader.model.api.file.IFile;

public interface IUploadFile extends IFile
{
    public static final int TYPE_FILE = 0;
    public static final int TYPE_DOCUMENT = 1;
    public static final int STATUS_READY = 0;
    public static final int STATUS_UPLOADING = 1;
    public static final int STATUS_FINISHED = 2;
    public static final int STATUS_FAILED = 3;
    public static final int STATUS_PREPROCESSING = 4;
    public static final int STATUS_DOWNLOADING = 5;
    public static final int STATUS_EDITING = 6;
    
    int getType();
    
    int getStatus();
    
    Exception getError();
    
    ITransferProgress getTransferProgress();
    
    boolean isReady();
    
    boolean isPreprocessing();
    
    boolean isServerProcessing();
    
    boolean isUploading();
    
    boolean isFinished();
    
    boolean isFailed();
    
    int getIndex();
    
    boolean isRemovable();
    
    boolean isRetryable();
    
    boolean isStoppable();
    
    int getUploadedPartitionCount();
    
    void setUploadedPartitionCount(final int p0);
    
    boolean isEditableImage();
    
    String getResponseContent();
    
    IAttributeSet getResponseHeaders();
    
    void setLength(final long p0);
    
    boolean isTempFile();
    
    boolean isDownloading();
    
    boolean isEditing();
    
    String getKey();
    
    void setKey(final String p0);
    
    boolean isDocumentFileWasLocked();
    
    void setDocumentFileWasLocked(final boolean p0);
}
