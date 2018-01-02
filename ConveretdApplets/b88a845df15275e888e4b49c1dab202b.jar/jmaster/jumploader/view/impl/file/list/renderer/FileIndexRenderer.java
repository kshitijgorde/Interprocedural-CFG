// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.file.list.renderer;

import jmaster.util.swing.icon.TextIcon;

public class FileIndexRenderer extends AbstractFileRendererComponent
{
    public FileIndexRenderer() {
        final TextIcon textIcon = new TextIcon();
        this.Q = textIcon;
        this.R = textIcon;
    }
    
    public void prepare() {
        this.setText("" + (this.E + 1));
        super.prepare();
    }
}
