import java.awt.event.ActionEvent;
import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.Button;
import java.awt.event.KeyAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

public class d extends KeyAdapter
{
    public static boolean ac;
    public Button ab;
    public Button aa;
    
    public d(final Button ab, final Button aa) {
        this.ab = ab;
        this.aa = aa;
    }
    
    public final void k(final Button button) {
        if (d.ac) {
            button.dispatchEvent(new KeyEvent(button, 401, System.currentTimeMillis(), 0, 10, '\n'));
        }
        else {
            button.dispatchEvent(new ActionEvent(button, 1001, button.getActionCommand()));
        }
    }
    
    public final void keyReleased(final KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case 10: {
                if (this.ab != null) {
                    this.k(this.ab);
                    break;
                }
                break;
            }
            case 27: {
                if (this.aa != null) {
                    this.k(this.aa);
                    break;
                }
                break;
            }
        }
    }
    
    static {
        try {
            d.ac = (System.getProperty("mrj.version") != null);
        }
        catch (Exception ex) {}
    }
}
