// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.viewpanel;

class NumFormat implements DesignConstants
{
    public int digits;
    public int format;
    public boolean isParens;
    public boolean isPunctuated;
    public boolean isPercent;
    public boolean isVarying;
    
    NumFormat() {
        this.digits = 0;
        this.format = 13;
        this.isParens = false;
        this.isPunctuated = false;
        this.isPercent = false;
        this.isVarying = false;
    }
}
