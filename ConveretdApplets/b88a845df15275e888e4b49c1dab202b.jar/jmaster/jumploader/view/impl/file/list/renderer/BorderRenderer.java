// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.file.list.renderer;

import java.awt.Insets;
import jmaster.util.swing.icon.GradientIcon;

public class BorderRenderer extends AbstractFileRendererComponent
{
    private GradientIcon \u00cb;
    protected Insets \u00ca;
    
    public BorderRenderer() {
        this.\u00cb = new GradientIcon();
        this.\u00ca = new Insets(0, 0, 0, 0);
        this.R = this.\u00cb;
    }
    
    public int getBeginColor() {
        return this.\u00cb.getBeginColor();
    }
    
    public int getBorderColor() {
        return this.\u00cb.getBorderColor();
    }
    
    public int getBorderWidth() {
        return this.\u00cb.getBorderWidth();
    }
    
    public int getEndColor() {
        return this.\u00cb.getEndColor();
    }
    
    public void setAlpha(final float alpha) {
        this.\u00cb.setAlpha(alpha);
    }
    
    public void setBeginColor(final int beginColor) {
        this.\u00cb.setBeginColor(beginColor);
    }
    
    public void setBorderColor(final int borderColor) {
        this.\u00cb.setBorderColor(borderColor);
    }
    
    public void setBorderWidth(final int borderWidth) {
        this.\u00cb.setBorderWidth(borderWidth);
    }
    
    public void setEndColor(final int endColor) {
        this.\u00cb.setEndColor(endColor);
    }
    
    public Insets getInsets() {
        return this.\u00ca;
    }
    
    public void setInsets(final Insets \u00ea) {
        this.\u00ca = \u00ea;
    }
    
    public void prepare() {
        this.\u00cb.setSize(this.getListCellRendererComponent().getWidth() - this.\u00ca.left - this.\u00ca.right, this.getListCellRendererComponent().getHeight() - this.\u00ca.top - this.\u00ca.bottom);
        this.\u00cb.setHorizontalSpace(this.\u00ca.left);
        this.\u00cb.setVerticalSpace(this.\u00ca.top);
        super.prepare();
    }
}
