// 
// Decompiled by Procyon v0.5.30
// 

package edu.davidson.graphics;

import java.awt.Component;

public class EtchedBorder extends Border
{
    public EtchedBorder() {
        this(new HintPanel());
    }
    
    public EtchedBorder(final Component component) {
        this(component, Border._defaultThickness, Border._defaultGap);
    }
    
    public EtchedBorder(final Component component, final int n) {
        this(component, n, Border._defaultGap);
    }
    
    public EtchedBorder(final Component component, final int n, final int n2) {
        super(component, n, n2);
    }
    
    public void etchedIn() {
        ((EtchedRectangle)this.border()).etchedIn();
    }
    
    public void etchedOut() {
        ((EtchedRectangle)this.border()).etchedOut();
    }
    
    public void paintEtchedIn() {
        ((EtchedRectangle)this.border()).paintEtchedIn();
    }
    
    public void paintEtchedOut() {
        ((EtchedRectangle)this.border()).paintEtchedOut();
    }
    
    public boolean isEtchedIn() {
        return ((EtchedRectangle)this.border()).isEtchedIn();
    }
    
    protected String paramString() {
        return String.valueOf(String.valueOf(super.paramString())).concat(String.valueOf(String.valueOf(this.border())));
    }
    
    protected DrawnRectangle border() {
        if (super.border == null) {
            super.border = new EtchedRectangle(this, super.thickness);
        }
        return super.border;
    }
    
    public void setEtchedIn(final boolean b) {
        if (b) {
            this.paintEtchedIn();
        }
        else {
            this.paintEtchedOut();
        }
    }
}
