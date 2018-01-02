// 
// Decompiled by Procyon v0.5.30
// 

package mika.application;

import mika.system.S_TextReader;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;

public abstract class GameModeBase extends Mode
{
    Image m_imgMainBackground;
    
    public void setMainBackground(final Image imgMainBackground) {
        this.m_imgMainBackground = imgMainBackground;
    }
    
    public Image getMainBackground() {
        return this.m_imgMainBackground;
    }
    
    public synchronized void update(final Graphics graphics) {
        this.paint(this.getGraphics());
    }
    
    public synchronized void paint(final Graphics graphics) {
        if (this.getScreenBuffer() != null) {
            graphics.drawImage(this.getScreenBuffer(), 0, 0, this);
        }
        this.notifyAll();
    }
    
    public abstract void load(final S_TextReader p0) throws Exception;
    
    public abstract int run();
}
