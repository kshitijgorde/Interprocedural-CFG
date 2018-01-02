// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.image.dialog.resize;

import javax.swing.JPanel;
import java.awt.Frame;
import jmaster.jumploader.view.impl.image.dialog.GenericDialog;

public class ResizeDialog extends GenericDialog
{
    private static final long H = -4944190912232941615L;
    public static final String PREFIX = "resizeDialog";
    
    public ResizeDialog(final Frame frame, final ResizeView resizeView) {
        super(frame, resizeView);
        this.D.injectProperties(this, "resizeDialog");
    }
}
