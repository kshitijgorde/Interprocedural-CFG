// 
// Decompiled by Procyon v0.5.30
// 

public class SortItem
{
    private int _intRed;
    private int _intBlue;
    private int _intRedPos;
    private int _intBluePos;
    private int _intRedHeight;
    private int _intBlueHeight;
    
    public SortItem(final int red, final int blue) {
        this._intRed = red;
        this._intBlue = blue;
        this._intRedPos = -1;
        this._intBluePos = -1;
        this._intRedHeight = -1;
        this._intBlueHeight = -1;
    }
    
    public SortItem(final int red, final int blue, final int redHeight, final int blueHeight) {
        this._intRed = red;
        this._intBlue = blue;
        this._intRedPos = -1;
        this._intBluePos = -1;
        this._intRedHeight = redHeight;
        this._intBlueHeight = blueHeight;
    }
    
    public SortItem(final int red, final int blue, final int redPos, final int bluePos, final int redHeight, final int blueHeight) {
        this._intRed = red;
        this._intBlue = blue;
        this._intRedPos = redPos;
        this._intBluePos = bluePos;
        this._intRedHeight = redHeight;
        this._intBlueHeight = blueHeight;
    }
    
    public void setRed(final SortingPanel panel) {
        panel.setColorRed(this._intRed);
        if (this._intRedHeight < 0) {
            return;
        }
        panel.setHeight((this._intRedPos < 0) ? this._intRed : this._intRedPos, this._intRedHeight);
    }
    
    public void setBlue(final SortingPanel panel) {
        panel.setColorBlue(this._intBlue);
        if (this._intBlueHeight < 0) {
            return;
        }
        panel.setHeight((this._intBluePos < 0) ? this._intBlue : this._intBluePos, this._intBlueHeight);
    }
    
    public int getRed() {
        return this._intRed;
    }
    
    public int getBlue() {
        return this._intBlue;
    }
}
