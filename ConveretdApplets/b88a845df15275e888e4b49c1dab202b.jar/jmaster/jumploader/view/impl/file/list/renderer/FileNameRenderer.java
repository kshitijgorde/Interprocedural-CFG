// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.file.list.renderer;

import jmaster.util.swing.icon.TextIcon;

public class FileNameRenderer extends AbstractFileRendererComponent
{
    public FileNameRenderer() {
        final TextIcon textIcon = new TextIcon();
        this.Q = textIcon;
        this.R = textIcon;
    }
    
    public void prepare() {
        this.setActionEnabled(true);
        this.A = 1.0f;
        this.setText(this.getFile().getName());
        this.setToolTipText(this.getFile().getName());
        super.prepare();
    }
}
