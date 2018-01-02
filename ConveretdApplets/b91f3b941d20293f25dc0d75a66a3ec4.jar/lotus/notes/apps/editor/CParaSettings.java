// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.editor;

class CParaSettings implements Cloneable
{
    private int cAlignment;
    private int cFirst;
    private int cRest;
    private int cRight;
    private char cBullet;
    private int cSpaceAbove;
    private int cOverride;
    private boolean cRevert;
    private static final int PS_ALIGN = 1;
    private static final int PS_FIRST = 2;
    private static final int PS_REST = 4;
    private static final int PS_RIGHT = 8;
    private static final int PS_BULLET = 16;
    private static final int PS_SPACEABOVE = 32;
    
    CParaSettings() {
        this.cAlignment = 0;
    }
    
    public Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException ex) {
            return null;
        }
    }
    
    void duplicate(final CParaSettings cParaSettings) {
        cParaSettings.cAlignment = this.cAlignment;
        cParaSettings.cFirst = this.cFirst;
        cParaSettings.cRest = this.cRest;
        cParaSettings.cRight = this.cRight;
        cParaSettings.cBullet = this.cBullet;
        cParaSettings.cSpaceAbove = this.cSpaceAbove;
        cParaSettings.cOverride = this.cOverride;
        cParaSettings.cRevert = this.cRevert;
    }
    
    void setAlignment(final int cAlignment) {
        this.cAlignment = cAlignment;
        this.cOverride |= 0x1;
    }
    
    final int getAlignment() {
        return this.cAlignment;
    }
    
    void setFirstIndent(final int cFirst) {
        this.cFirst = cFirst;
        this.cOverride |= 0x2;
    }
    
    final int getFirstIndent() {
        return this.cFirst;
    }
    
    void setRestIndent(final int cRest) {
        this.cRest = cRest;
        this.cOverride |= 0x4;
    }
    
    final int getRestIndent() {
        return this.cRest;
    }
    
    void setRightIndent(final int cRight) {
        this.cRight = cRight;
        this.cOverride |= 0x8;
    }
    
    final int getRightIndent() {
        return this.cRight;
    }
    
    void setBullet(final char cBullet) {
        this.cBullet = cBullet;
        this.cOverride |= 0x10;
    }
    
    final char getBullet() {
        return this.cBullet;
    }
    
    void setSpaceAbove(final int cSpaceAbove) {
        this.cSpaceAbove = cSpaceAbove;
        this.cOverride |= 0x20;
    }
    
    final int getSpaceAbove() {
        return this.cSpaceAbove;
    }
    
    void setNormal() {
        this.cRevert = true;
    }
    
    void apply(final CParaSettings cParaSettings) {
        if (this.cRevert) {
            cParaSettings.cOverride = 0;
            return;
        }
        if ((this.cOverride & 0x10) != 0x0) {
            cParaSettings.setBullet(this.cBullet);
        }
        if ((this.cOverride & 0x1) != 0x0) {
            cParaSettings.setAlignment(this.cAlignment);
        }
        if ((this.cOverride & 0x2) != 0x0) {
            cParaSettings.setFirstIndent(this.cFirst);
        }
        if ((this.cOverride & 0x4) != 0x0) {
            cParaSettings.setRestIndent(this.cRest);
        }
        if ((this.cOverride & 0x8) != 0x0) {
            cParaSettings.setRightIndent(this.cRight);
        }
        if ((this.cOverride & 0x20) != 0x0) {
            cParaSettings.setSpaceAbove(this.cSpaceAbove);
        }
    }
    
    final boolean isAnythingOverridden() {
        return this.cOverride != 0;
    }
    
    void cleanup(final CParaSettings cParaSettings) {
        final int cOverride = cParaSettings.cOverride;
        if ((cOverride & 0x1) != 0x0 && cParaSettings.cAlignment == this.cAlignment) {
            cParaSettings.cOverride &= 0xFFFFFFFE;
        }
        if ((cOverride & 0x2) != 0x0 && cParaSettings.cFirst == this.cFirst) {
            cParaSettings.cOverride &= 0xFFFFFFFD;
        }
        if ((cOverride & 0x4) != 0x0 && cParaSettings.cRest == this.cRest) {
            cParaSettings.cOverride &= 0xFFFFFFFB;
        }
        if ((cOverride & 0x8) != 0x0 && cParaSettings.cRight == this.cRight) {
            cParaSettings.cOverride &= 0xFFFFFFF7;
        }
        if ((cOverride & 0x10) != 0x0 && cParaSettings.cBullet == this.cBullet) {
            cParaSettings.cOverride &= 0xFFFFFFEF;
        }
        if ((cOverride & 0x20) != 0x0 && cParaSettings.cSpaceAbove == this.cSpaceAbove) {
            cParaSettings.cOverride &= 0xFFFFFFDF;
        }
    }
}
