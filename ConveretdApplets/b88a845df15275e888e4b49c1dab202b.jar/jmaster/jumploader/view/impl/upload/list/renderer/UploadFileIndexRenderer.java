// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.upload.list.renderer;

import jmaster.util.swing.icon.TextIcon;

public class UploadFileIndexRenderer extends AbstractUploadFileRendererComponent
{
    public UploadFileIndexRenderer() {
        final TextIcon textIcon = new TextIcon();
        this.Q = textIcon;
        this.R = textIcon;
    }
    
    public void prepare() {
        this.setText("" + (this.getUploadFile().getIndex() + 1));
        super.prepare();
    }
}
