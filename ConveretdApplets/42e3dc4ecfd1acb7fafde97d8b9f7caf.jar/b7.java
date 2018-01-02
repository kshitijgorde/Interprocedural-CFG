import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

public class b7 extends KeyAdapter
{
    private be a;
    
    public b7(final be a) {
        this.a = a;
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (keyEvent.getKeyChar() == 'x') {
            new Thread(new b8(this.a)).start();
        }
        else if (keyEvent.getKeyChar() == '+' || keyEvent.getKeyChar() == '=') {
            this.a.a(true);
        }
        else if (keyEvent.getKeyChar() == '-' || keyEvent.getKeyChar() == '_') {
            this.a.a(false);
        }
    }
}
