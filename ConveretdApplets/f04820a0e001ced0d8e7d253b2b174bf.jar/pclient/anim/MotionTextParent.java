// 
// Decompiled by Procyon v0.5.30
// 

package pclient.anim;

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;

public abstract class MotionTextParent extends AnimRenderer
{
    private static final boolean TIME_MEASURE = true;
    private long timer;
    
    public MotionTextParent() {
        this.timer = 0L;
    }
    
    public abstract void prepare(final Thread p0);
    
    public abstract long runOneStep(final Graphics p0);
    
    public abstract void setPosition(final int p0, final int p1);
    
    public abstract void setArea(final int p0, final int p1);
    
    public abstract void setForeground(final Color p0);
    
    public abstract void setBackground(final Color p0);
    
    public abstract void setFont(final Font p0);
    
    public abstract void setFontCode(final int p0);
    
    public abstract void setShadow();
    
    protected void debugTime() {
        this.timer = System.currentTimeMillis();
    }
    
    protected void debugEndTime() {
        this.debugEndTime("");
    }
    
    protected void debugEndTime(final String s) {
        final long n = System.currentTimeMillis() - this.timer;
    }
}
