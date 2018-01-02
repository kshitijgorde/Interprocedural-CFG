// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.model.api.upload;

import java.io.File;
import jmaster.jumploader.model.api.common.IListSelection;
import jmaster.jumploader.model.api.common.IAttributeSet;
import jmaster.jumploader.model.api.common.ITransferProgress;

public interface IUploader
{
    public static final int STATUS_READY = 0;
    public static final int STATUS_UPLOADING = 1;
    public static final String ATTR_MAIN_FILE = "mainFile";
    public static final String ATTR_ROTATE_ANGLE = "rotateAngle";
    
    void addListener(final B p0);
    
    void removeListener(final B p0);
    
    void addAddListener(final C p0);
    
    void removeAddListener(final C p0);
    
    boolean isFileAdditionEnabled();
    
    void setFileAdditionEnabled(final boolean p0);
    
    boolean isFileRemovalEnabled();
    
    void setFileRemovalEnabled(final boolean p0);
    
    int getFileCount();
    
    IUploadFile getFile(final int p0);
    
    IUploadFile[] getAllFiles();
    
    IUploadFile getFileByPath(final String p0);
    
    int getFileCountByStatus(final int p0);
    
    IUploadFile[] getFilesByStatus(final int p0);
    
    int indexOfFile(final IUploadFile p0);
    
    long getFilesLength();
    
    IUploadFile addFile(final String p0) throws jmaster.jumploader.model.api.A.C;
    
    IUploadFile addFile(final String p0, final int p1) throws jmaster.jumploader.model.api.A.C;
    
    IUploadFile addFile(final String p0, final int p1, final String p2) throws jmaster.jumploader.model.api.A.C;
    
    void removeFile(final IUploadFile p0) throws jmaster.jumploader.model.api.A.C;
    
    void moveFiles(final IUploadFile[] p0, final int p1) throws jmaster.jumploader.model.api.A.C;
    
    int getStatus();
    
    boolean isReady();
    
    boolean isUploading();
    
    int getUploadThreadCount();
    
    D getUploadThread(final int p0);
    
    void startUpload() throws jmaster.jumploader.model.api.A.C;
    
    void stopUpload() throws jmaster.jumploader.model.api.A.C;
    
    boolean canStartUpload();
    
    boolean canStopUpload();
    
    void stopFileUpload(final IUploadFile p0) throws jmaster.jumploader.model.api.A.C;
    
    void retryFileUpload(final IUploadFile p0) throws jmaster.jumploader.model.api.A.C;
    
    ITransferProgress getTransferProgress();
    
    void destroy();
    
    IAttributeSet getAttributeSet();
    
    IListSelection getSelection();
    
    boolean isUploadEnabled();
    
    void setUploadEnabled(final boolean p0);
    
    boolean canAddFile(final String p0);
    
    void updateFile(final IUploadFile p0, final File p1, final boolean p2);
    
    void setMainFile(final IUploadFile p0) throws jmaster.jumploader.model.api.A.C;
    
    void updateFileStatus(final IUploadFile p0, final int p1);
    
    void applyTransformations(final IUploadFile p0) throws jmaster.jumploader.model.api.A.C;
    
    void metadataChanged(final IUploadFile p0);
    
    boolean isAddingFiles();
    
    void stopAddingFiles();
    
    IUploadFile getAddFileCurrent();
    
    IUploadFile processDocument(final String p0, final String p1, final String p2, final String p3, final String p4) throws jmaster.jumploader.model.api.A.C;
    
    boolean isDownloading();
    
    void processDocument(final IUploadFile p0);
    
    boolean isDestroyed();
}
