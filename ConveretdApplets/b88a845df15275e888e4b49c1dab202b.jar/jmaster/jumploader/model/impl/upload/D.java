// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.model.impl.upload;

import jmaster.jumploader.app.JumpLoaderApplet;
import java.util.MissingResourceException;
import jmaster.jumploader.model.api.upload.IUploadFile;
import jmaster.jumploader.model.api.A.C;
import jmaster.jumploader.model.api.upload.IUploader;
import jmaster.util.log.A;
import jmaster.jumploader.model.api.upload.B;

public abstract class D implements B
{
    protected A \u015c;
    protected jmaster.jumploader.model.api.B \u015b;
    
    public D() {
        this.\u015c = jmaster.util.log.B.getInstance().getLog(this.getClass());
    }
    
    public jmaster.jumploader.model.api.B S() {
        return this.\u015b;
    }
    
    public void A(final jmaster.jumploader.model.api.B \u015b) {
        this.\u015b = \u015b;
    }
    
    public void uploaderFileAddFailed(final IUploader uploader, final C c) {
    }
    
    public void uploaderFileAdded(final IUploader uploader, final IUploadFile uploadFile) {
    }
    
    public void uploaderFileAdditionEnabledChanged(final IUploader uploader) {
    }
    
    public void uploaderFileMoved(final IUploader uploader, final IUploadFile uploadFile, final int n) {
    }
    
    public void uploaderFileRemovalEnabledChanged(final IUploader uploader) {
    }
    
    public void uploaderFileRemoved(final IUploader uploader, final IUploadFile uploadFile) {
    }
    
    public void uploaderFileStatusChanged(final IUploader uploader, final IUploadFile uploadFile) {
    }
    
    public void uploaderFileUpdated(final IUploader uploader, final IUploadFile uploadFile) {
    }
    
    public void uploaderFilesReset(final IUploader uploader) {
    }
    
    public void uploaderStatusChanged(final IUploader uploader) {
    }
    
    public void uploaderUploadEnabledChanged(final IUploader uploader) {
    }
    
    public void U() {
        try {
            jmaster.util.property.C.A().A(this, jmaster.util.property.B.C().G(this.R()), null);
        }
        catch (MissingResourceException ex) {}
        final JumpLoaderApplet applet = this.\u015b.E().getApplet();
        if (applet != null) {
            applet.injectProperties(this, this.T());
        }
    }
    
    public abstract String R();
    
    public abstract String T();
}
