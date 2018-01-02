// 
// Decompiled by Procyon v0.5.30
// 

package sexy.gui;

public class SexyColor implements Cloneable
{
    public int mRed;
    public int mGreen;
    public int mBlue;
    public int mAlpha;
    
    public int ToInt() {
        return this.mAlpha << 24 | this.mRed << 16 | this.mGreen << 8 | this.mBlue;
    }
    
    public SexyColor(final int mRed, final int mGreen, final int mBlue, final int mAlpha) {
        this.mRed = mRed;
        this.mGreen = mGreen;
        this.mBlue = mBlue;
        this.mAlpha = mAlpha;
    }
    
    public SexyColor(final int mRed, final int mGreen, final int mBlue) {
        this.mRed = mRed;
        this.mGreen = mGreen;
        this.mBlue = mBlue;
        this.mAlpha = 255;
    }
    
    public synchronized Object clone() throws CloneNotSupportedException {
        return new SexyColor(this.mRed, this.mGreen, this.mBlue, this.mAlpha);
    }
}
