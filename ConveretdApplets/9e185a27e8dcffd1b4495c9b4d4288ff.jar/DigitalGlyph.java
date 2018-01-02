import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public interface DigitalGlyph
{
    void setColour(final Color p0);
    
    void setOrigin(final int p0, final int p1);
    
    void setBorder(final boolean p0);
    
    boolean setDigitalCharacter(final char p0);
}
