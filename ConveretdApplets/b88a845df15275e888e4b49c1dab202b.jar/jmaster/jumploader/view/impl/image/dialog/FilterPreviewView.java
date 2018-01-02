// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.image.dialog;

import javax.swing.JList;
import jmaster.jumploader.model.api.B;
import java.awt.image.BufferedImage;
import jmaster.jumploader.view.api.main.IMainView;
import jmaster.jumploader.view.impl.GenericView;

public class FilterPreviewView extends GenericView
{
    private static final long \u00fc = 132263772915943773L;
    private static final String \u00f9 = "filterPreviewView";
    protected IMainView \u00f6;
    protected ImagePreviewRenderer \u00fb;
    protected BufferedImage \u00f8;
    protected BufferedImage \u00fa;
    
    public FilterPreviewView(final B b, final IMainView mainView) {
        super(b);
        this.\u00fb = new ImagePreviewRenderer(b, mainView);
        this.I.injectProperties(this, "filterPreviewView");
        this.I.injectProperties(this.\u00fb, "filterPreviewView", "preview");
    }
    
    public BufferedImage getImage() {
        return null;
    }
    
    public void setImage(final BufferedImage \u00f8) {
        this.\u00f8 = \u00f8;
        this.\u00fb.getListCellRendererComponent(null, \u00f8, -1, false, false);
    }
    
    public void setPreviewImage(final BufferedImage \u00fa) {
        this.\u00fa = \u00fa;
        this.\u00fb.getListCellRendererComponent(null, \u00fa, -1, false, false);
        this.\u00fb.repaint();
    }
}
