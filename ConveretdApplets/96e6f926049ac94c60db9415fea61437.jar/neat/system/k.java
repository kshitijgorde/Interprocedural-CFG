// 
// Decompiled by Procyon v0.5.30
// 

package neat.system;

import java.awt.event.MouseEvent;

public abstract class k extends g
{
    public long c;
    public int d;
    public int e;
    public boolean f;
    public boolean g;
    public boolean h;
    public static boolean i;
    
    protected void a(final MouseEvent mouseEvent) {
        this.c = mouseEvent.getWhen();
        this.d = mouseEvent.getX();
        this.e = mouseEvent.getY();
        final int modifiers = mouseEvent.getModifiers();
        this.f = ((modifiers & 0x10) != 0x0);
        this.g = ((modifiers & 0x8) != 0x0);
        this.h = ((modifiers & 0x4) != 0x0);
    }
    
    protected void a(final k k) {
        this.c = k.c;
        this.d = k.d;
        this.e = k.e;
        this.f = k.f;
        this.g = k.g;
        this.h = k.h;
    }
}
