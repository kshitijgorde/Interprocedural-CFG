// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.upload.list.renderer;

import jmaster.jumploader.view.impl.file.list.renderer.FileSizeRenderer;

public class UploadFileSizeRenderer extends FileSizeRenderer
{
    public void prepare() {
        if (this.getModel().H().isUploadViewListShowFileSize()) {
            super.prepare();
        }
        else {
            this.setVisible(false);
        }
    }
}
