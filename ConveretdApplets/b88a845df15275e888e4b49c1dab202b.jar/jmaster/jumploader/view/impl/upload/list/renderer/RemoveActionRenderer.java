// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.upload.list.renderer;

import jmaster.jumploader.model.api.upload.IUploadFile;
import jmaster.jumploader.model.api.A.C;

public class RemoveActionRenderer extends AbstractUploadFileRendererComponent
{
    public void prepare() {
        this.setVisible(this.getUploadFile().isRemovable());
        super.prepare();
    }
    
    protected void A() {
        final IUploadFile uploadFile = this.getUploadFile();
        try {
            this.V.D().removeFile(uploadFile);
        }
        catch (C c) {
            this.F.E("Failed to remove file: " + uploadFile);
        }
    }
}
