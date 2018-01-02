// 
// Decompiled by Procyon v0.5.30
// 

package sGraphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Component;

public class EtchedRectangle extends DrawnRectangle
{
    protected static Etching _defaultEtching;
    private Etching etching;
    
    public EtchedRectangle(final Component component) {
        this(component, EtchedRectangle._defaultEtching, DrawnRectangle._defaultThickness, 0, 0, 0, 0);
    }
    
    public EtchedRectangle(final Component component, final int n) {
        this(component, EtchedRectangle._defaultEtching, n, 0, 0, 0, 0);
    }
    
    public EtchedRectangle(final Component component, final int n, final int n2, final int n3, final int n4) {
        this(component, EtchedRectangle._defaultEtching, DrawnRectangle._defaultThickness, n, n2, n3, n4);
    }
    
    public EtchedRectangle(final Component component, final int n, final int n2, final int n3, final int n4, final int n5) {
        this(component, EtchedRectangle._defaultEtching, n, n2, n3, n4, n5);
    }
    
    public EtchedRectangle(final Component component, final Etching etching, final int n, final int n2, final int n3, final int n4, final int n5) {
        super(component, n, n2, n3, n4, n5);
        this.etching = etching;
    }
    
    public void etchedIn() {
        this.etching = Etching.IN;
    }
    
    public void etchedOut() {
        this.etching = Etching.OUT;
    }
    
    public boolean isEtchedIn() {
        return this.etching == Etching.IN;
    }
    
    public void paint() {
        if (this.etching == Etching.IN) {
            this.paintEtchedIn();
        }
        else {
            this.paintEtchedOut();
        }
    }
    
    public void paintEtchedIn() {
        final Graphics graphics = super.drawInto.getGraphics();
        if (graphics != null) {
            this.paintEtched(graphics, this.getLineColor(), this.brighter());
        }
        this.etchedIn();
    }
    
    public void paintEtchedOut() {
        final Graphics graphics = super.drawInto.getGraphics();
        if (graphics != null) {
            this.paintEtched(graphics, this.brighter(), this.getLineColor());
        }
        this.etchedOut();
    }
    
    public String paramString() {
        return String.valueOf(String.valueOf(super.paramString()).concat(String.valueOf(","))).concat(String.valueOf(this.etching));
    }
    
    private void paintEtched(final Graphics graphics, final Color color, final Color color2) {
        final int thickness = this.getThickness();
        final int n = super.width - thickness;
        final int n2 = super.height - thickness;
        graphics.setColor(color);
        for (int i = 0; i < thickness / 2; ++i) {
            graphics.drawRect(super.x + i, super.y + i, n, n2);
        }
        graphics.setColor(color2);
        for (int j = 0; j < thickness / 2; ++j) {
            graphics.drawRect(super.x + thickness / 2 + j, super.y + thickness / 2 + j, n, n2);
        }
        graphics.dispose();
    }
    
    static {
        EtchedRectangle._defaultEtching = Etching.IN;
    }
}
