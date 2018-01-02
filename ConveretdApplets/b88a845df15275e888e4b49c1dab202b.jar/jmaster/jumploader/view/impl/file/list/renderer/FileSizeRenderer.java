// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.file.list.renderer;

import jmaster.util.swing.icon.TextIcon;

public class FileSizeRenderer extends AbstractFileRendererComponent
{
    public FileSizeRenderer() {
        final TextIcon textIcon = new TextIcon();
        this.Q = textIcon;
        this.R = textIcon;
    }
    
    public void prepare() {
        String text = null;
        if (this.getFile().getLength() > 0L) {
            text = this.M.getLengthFormatted(this.getFile().getLength());
            if (this.getFile().getCompressionRatio() != null) {
                text = text + " (" + this.M.getPercentFormatted(this.getFile().getCompressionRatio() * 100.0) + ")";
            }
        }
        this.setText(text);
        super.prepare();
    }
}
