// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.upload.list.renderer;

import jmaster.jumploader.model.api.A.C;
import jmaster.util.C.B;
import jmaster.jumploader.model.api.upload.IUploadFile;

public class RetryActionRenderer extends AbstractUploadFileRendererComponent
{
    public void prepare() {
        final IUploadFile uploadFile = this.getUploadFile();
        this.setVisible(this.getModel().H().isUploadViewListShowRetry() && uploadFile.isRetryable());
        super.prepare();
    }
    
    protected void A() {
        jmaster.util.C.B.A(this, "retryFile");
    }
    
    public void retryFile() {
        try {
            this.V.D().retryFileUpload(this.getUploadFile());
        }
        catch (C c) {
            this.F.E(c, c);
        }
    }
}
