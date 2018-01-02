import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class AlertFrame extends Frame
{
    public AlertFrame(final String s) {
        this.setVisible(false);
        new AlertDialog(this, s).setVisible(true);
        this.dispose();
    }
}
