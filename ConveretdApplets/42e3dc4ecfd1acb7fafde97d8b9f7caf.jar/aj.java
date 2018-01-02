import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

public class aj extends KeyAdapter
{
    private ah a;
    
    public aj(final ah a) {
        this.a = a;
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
        this.a.a(keyEvent);
    }
}
