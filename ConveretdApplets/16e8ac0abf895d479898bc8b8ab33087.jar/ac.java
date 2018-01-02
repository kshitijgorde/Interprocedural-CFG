import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

public final class ac extends KeyAdapter
{
    public final x a;
    
    public ac(final x a) {
        this.a = a;
    }
    
    public final void keyPressed(final KeyEvent keyEvent) {
        x.a(this.a, keyEvent);
    }
}
