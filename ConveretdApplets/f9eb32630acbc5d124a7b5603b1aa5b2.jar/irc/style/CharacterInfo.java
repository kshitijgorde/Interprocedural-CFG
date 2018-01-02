// 
// Decompiled by Procyon v0.5.30
// 

package irc.style;

import java.awt.Color;

class CharacterInfo
{
    public Color frontColor;
    public Color backColor;
    public boolean isBold;
    public boolean isUnderline;
    public boolean isReverse;
    public boolean isTransparent;
    
    public CharacterInfo() {
        this.frontColor = Color.black;
        this.backColor = Color.white;
        this.isBold = false;
        this.isUnderline = false;
        this.isReverse = false;
        this.isTransparent = true;
    }
    
    public CharacterInfo(final CharacterInfo characterInfo) {
        this.frontColor = characterInfo.frontColor;
        this.backColor = characterInfo.backColor;
        this.isBold = characterInfo.isBold;
        this.isUnderline = characterInfo.isUnderline;
        this.isReverse = characterInfo.isReverse;
        this.isTransparent = characterInfo.isTransparent;
    }
    
    public boolean equals(final Object o) {
        if (!(o instanceof CharacterInfo)) {
            return false;
        }
        final CharacterInfo characterInfo = (CharacterInfo)o;
        return this.frontColor.equals(characterInfo.frontColor) && this.backColor.equals(characterInfo.backColor) && this.isBold == characterInfo.isBold && this.isUnderline == characterInfo.isUnderline && this.isTransparent == characterInfo.isTransparent;
    }
    
    public int hashCode() {
        int n = 0;
        if (this.isBold) {
            ++n;
        }
        if (this.isUnderline) {
            ++n;
        }
        if (this.isTransparent) {
            ++n;
        }
        return n + this.frontColor.hashCode() + this.backColor.hashCode();
    }
}
