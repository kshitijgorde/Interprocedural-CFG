// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.upload.list.renderer;

import jmaster.jumploader.model.api.A.C;
import jmaster.util.C.B;
import jmaster.jumploader.model.api.upload.IUploadFile;

public class StopActionRenderer extends AbstractUploadFileRendererComponent
{
    public void prepare() {
        final IUploadFile uploadFile = this.getUploadFile();
        this.setVisible(this.getModel().H().isUploadViewListShowStop() && uploadFile.isStoppable());
        this.setActionEnabled(uploadFile.isStoppable());
        super.prepare();
    }
    
    protected void A() {
        jmaster.util.C.B.A(this, "stopFile");
    }
    
    public void stopFile() {
        try {
            this.V.D().stopFileUpload(this.getUploadFile());
        }
        catch (C c) {
            this.F.E(c, c);
        }
    }
}
