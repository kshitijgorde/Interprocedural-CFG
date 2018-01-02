// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.image.dialog.blur;

import javax.swing.JPanel;
import java.awt.Frame;
import jmaster.jumploader.view.impl.image.dialog.GenericDialog;

public class FilterGblurDialog extends GenericDialog
{
    private static final long G = -1972407319937337803L;
    public static final String PREFIX = "filterGblurDialog";
    
    public FilterGblurDialog(final Frame frame, final FilterGblurView filterGblurView) {
        super(frame, filterGblurView);
        this.D.injectProperties(this, "filterGblurDialog");
    }
}
