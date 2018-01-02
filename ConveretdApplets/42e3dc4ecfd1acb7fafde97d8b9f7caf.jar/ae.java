import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

public class ae extends KeyAdapter
{
    private v a;
    
    public ae(final v a) {
        this.a = a;
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 10) {
            this.a.c();
        }
    }
}
