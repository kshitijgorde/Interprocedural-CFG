// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.upload.list;

import jmaster.jumploader.model.api.file.IFile;
import jmaster.jumploader.model.api.A.C;
import jmaster.jumploader.model.api.upload.IUploadFile;
import jmaster.jumploader.model.api.upload.IUploader;
import jmaster.util.log.A;
import jmaster.jumploader.model.api.upload.B;
import jmaster.util.swing.easylist.EasyListModel;

public class UploadFileListModel extends EasyListModel implements B, jmaster.jumploader.model.api.C.B
{
    private static final long R = -8817704934334671585L;
    protected A Q;
    private jmaster.jumploader.model.api.B P;
    
    public UploadFileListModel(final jmaster.jumploader.model.api.B p) {
        this.Q = jmaster.util.log.B.getInstance().getLog(this.getClass());
        this.P = p;
        p.D().addListener(this);
        p.A().A(this);
        this.init();
    }
    
    public void init() {
        if (!jmaster.util.C.B.A()) {
            jmaster.util.C.B.C(this, "init");
            return;
        }
        super.clear();
        final IUploader d = this.P.D();
        if (d != null) {
            for (int i = 0; i < d.getFileCount(); ++i) {
                this.addItem(d.getFile(i));
            }
        }
    }
    
    public IUploadFile getUploadFile(final int n) {
        return (IUploadFile)this.getItem(n);
    }
    
    public void uploaderFileAdditionEnabledChanged(final IUploader uploader) {
    }
    
    public void uploaderFileRemovalEnabledChanged(final IUploader uploader) {
    }
    
    public void uploaderFileAdded(final IUploader uploader, final IUploadFile uploadFile) {
        this.addItem(uploadFile);
    }
    
    public void uploaderFileRemoved(final IUploader uploader, final IUploadFile uploadFile) {
        this.removeItem(uploadFile);
    }
    
    public void uploaderFileMoved(final IUploader uploader, final IUploadFile uploadFile, final int n) {
        this.removeItem(uploadFile);
        this.addItem(uploadFile.getIndex(), uploadFile);
    }
    
    public void uploaderFilesReset(final IUploader uploader) {
        this.init();
    }
    
    public void uploaderFileStatusChanged(final IUploader uploader, final IUploadFile uploadFile) {
        this.updateItem(uploadFile);
    }
    
    public void uploaderStatusChanged(final IUploader uploader) {
    }
    
    public void uploaderUploadEnabledChanged(final IUploader uploader) {
    }
    
    public void uploaderFileAddFailed(final IUploader uploader, final C c) {
    }
    
    public void uploaderFileUpdated(final IUploader uploader, final IUploadFile uploadFile) {
        this.updateItem(uploadFile);
    }
    
    public void thumbnailManagerThumbnailChanged(final jmaster.jumploader.model.api.C.A a, final IFile file, final jmaster.jumploader.model.api.C.C c) {
        this.updateItem(file);
    }
    
    public void thumbnailManagerStatusChanged(final jmaster.jumploader.model.api.C.A a) {
    }
}
