import java.awt.AWTEvent;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.Button;
import java.awt.event.WindowAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

public class e extends WindowAdapter
{
    public Button ad;
    
    public e(final Button ad) {
        this.ad = ad;
    }
    
    public final void windowClosing(final WindowEvent windowEvent) {
        this.ad.dispatchEvent(new ActionEvent(this.ad, 1001, this.ad.getActionCommand()));
    }
}
