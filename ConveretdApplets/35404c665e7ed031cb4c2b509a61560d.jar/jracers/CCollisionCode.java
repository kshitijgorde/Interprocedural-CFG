// 
// Decompiled by Procyon v0.5.30
// 

package jracers;

public class CCollisionCode
{
    public boolean upperLeftTireHit;
    public boolean lowerLeftTireHit;
    public boolean upperRightTireHit;
    public boolean lowerRightTireHit;
    public int xShift;
    public int yShift;
    
    public CCollisionCode() {
        this.upperLeftTireHit = false;
        this.lowerLeftTireHit = false;
        this.upperRightTireHit = false;
        this.lowerRightTireHit = false;
        this.xShift = 0;
        this.yShift = 0;
    }
    
    public boolean isAnythingHit() {
        return this.upperLeftTireHit || this.lowerLeftTireHit || this.upperRightTireHit || this.lowerRightTireHit;
    }
    
    public boolean isLeftHit() {
        return (this.upperLeftTireHit || this.lowerLeftTireHit) && !this.upperRightTireHit && !this.lowerRightTireHit;
    }
    
    public boolean isRightHit() {
        return (this.upperRightTireHit || this.lowerRightTireHit) && !this.upperLeftTireHit && !this.lowerLeftTireHit;
    }
    
    public boolean isUpperHit() {
        return this.upperLeftTireHit && this.upperRightTireHit;
    }
    
    public boolean isLowerHit() {
        return this.lowerLeftTireHit && this.lowerRightTireHit;
    }
    
    public void init() {
        this.upperLeftTireHit = false;
        this.lowerLeftTireHit = false;
        this.upperRightTireHit = false;
        this.lowerRightTireHit = false;
        this.xShift = 0;
        this.yShift = 0;
    }
}
