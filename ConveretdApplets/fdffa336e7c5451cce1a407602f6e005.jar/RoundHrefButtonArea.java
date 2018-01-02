import java.awt.image.ImageFilter;

// 
// Decompiled by Procyon v0.5.30
// 

class RoundHrefButtonArea extends HrefButtonArea
{
    public void makeImages() {
        super.upImage = super.parent.getHighlight(super.X, super.Y, super.W, super.H, new RoundButtonFilter(false, super.parent.hlpercent, super.border, super.W, super.H));
        super.downImage = super.parent.getHighlight(super.X, super.Y, super.W, super.H, new RoundButtonFilter(true, super.parent.hlpercent, super.border, super.W, super.H));
    }
}
