// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.file.list.renderer;

import jmaster.jumploader.view.impl.upload.UploadView;

public class AddActionRenderer extends AbstractFileRendererComponent
{
    public void prepare() {
        super.prepare();
        this.setActionEnabled(this.getModel().D().canAddFile(this.getFile().getPath()));
    }
    
    protected void A() {
        ((UploadView)this.U.getUploadView()).fireAddFilesAction(new String[] { this.getFile().getPath() });
    }
}
