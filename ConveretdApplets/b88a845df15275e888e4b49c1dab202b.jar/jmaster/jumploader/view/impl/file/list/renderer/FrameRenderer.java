// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.file.list.renderer;

import javax.swing.ImageIcon;
import java.awt.Insets;
import jmaster.util.swing.icon.FrameIcon;

public class FrameRenderer extends AbstractFileRendererComponent
{
    protected FrameIcon ¥;
    protected Insets ¤;
    
    public FrameRenderer() {
        this.¥ = new FrameIcon();
        this.¤ = new Insets(0, 0, 0, 0);
        this.R = this.¥;
    }
    
    public float getAlpha() {
        return this.¥.getAlpha();
    }
    
    public ImageIcon getIconB() {
        return this.¥.getIconB();
    }
    
    public ImageIcon getIconC() {
        return this.¥.getIconC();
    }
    
    public ImageIcon getIconL() {
        return this.¥.getIconL();
    }
    
    public ImageIcon getIconLB() {
        return this.¥.getIconLB();
    }
    
    public ImageIcon getIconLT() {
        return this.¥.getIconLT();
    }
    
    public ImageIcon getIconR() {
        return this.¥.getIconR();
    }
    
    public ImageIcon getIconRB() {
        return this.¥.getIconRB();
    }
    
    public void setAlpha(final float alpha) {
        this.¥.setAlpha(alpha);
    }
    
    public void setIconB(final ImageIcon iconB) {
        this.¥.setIconB(iconB);
    }
    
    public void setIconC(final ImageIcon iconC) {
        this.¥.setIconC(iconC);
    }
    
    public void setIconL(final ImageIcon iconL) {
        this.¥.setIconL(iconL);
    }
    
    public void setIconLB(final ImageIcon iconLB) {
        this.¥.setIconLB(iconLB);
    }
    
    public void setIconLT(final ImageIcon iconLT) {
        this.¥.setIconLT(iconLT);
    }
    
    public void setIconR(final ImageIcon iconR) {
        this.¥.setIconR(iconR);
    }
    
    public void setIconRB(final ImageIcon iconRB) {
        this.¥.setIconRB(iconRB);
    }
    
    public void setIconRT(final ImageIcon iconRT) {
        this.¥.setIconRT(iconRT);
    }
    
    public void setIconT(final ImageIcon iconT) {
        this.¥.setIconT(iconT);
    }
    
    public Insets getInsets() {
        return this.¤;
    }
    
    public void setInsets(final Insets ¤) {
        this.¤ = ¤;
    }
    
    public void prepare() {
        this.¥.setAnchor("NW");
        if (this.getListCellRendererComponent() != null) {
            this.¥.setSize(this.getListCellRendererComponent().getWidth() - this.¤.left - this.¤.right, this.getListCellRendererComponent().getHeight() - this.¤.top - this.¤.bottom);
        }
        this.¥.setHorizontalSpace(this.¤.left);
        this.¥.setVerticalSpace(this.¤.top);
        super.prepare();
    }
}
