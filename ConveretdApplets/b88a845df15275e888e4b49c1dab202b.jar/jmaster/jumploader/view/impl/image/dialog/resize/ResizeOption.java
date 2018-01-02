// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.image.dialog.resize;

public class ResizeOption
{
    protected String C;
    protected int B;
    protected int A;
    
    public int getHeight() {
        return this.A;
    }
    
    public void setHeight(final int a) {
        this.A = a;
    }
    
    public String getText() {
        return this.C;
    }
    
    public void setText(final String c) {
        this.C = c;
    }
    
    public int getWidth() {
        return this.B;
    }
    
    public void setWidth(final int b) {
        this.B = b;
    }
    
    public String toString() {
        return this.C;
    }
}
