// 
// Decompiled by Procyon v0.5.30
// 

package jlog.jim;

import java.awt.Frame;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.FileDialog;

class $WGC extends FileDialog
{
    void $B4C() {
        final Toolkit toolkit = this.getToolkit();
        if (toolkit != null) {
            final Dimension screenSize = toolkit.getScreenSize();
            if (screenSize != null) {
                final Dimension size = this.getSize();
                size.width = Math.max(size.width, 600);
                size.height = Math.max(size.height, 350);
                this.setLocation(Math.max((screenSize.width - size.width) / 2, 0), Math.max(screenSize.height - size.height, 0));
            }
        }
    }
    
    public $WGC(final Frame frame, final String s) {
        super(frame, s);
    }
    
    public $WGC(final Frame frame, final String s, final int n) {
        super(frame, s, n);
    }
    
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        if (visible) {
            this.$B4C();
        }
    }
    
    public void show() {
        this.$B4C();
        super.show();
    }
}
