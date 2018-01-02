import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class SoundArea extends ImageMapArea
{
    String sound;
    
    public void handleArg(final String sound) {
        this.sound = sound;
        super.terminal = false;
    }
    
    public void highlight(final Graphics graphics, final boolean b) {
        if (b && !super.active) {
            super.parent.play(super.parent.getDocumentBase(), this.sound);
        }
        super.highlight(graphics, b);
    }
}
