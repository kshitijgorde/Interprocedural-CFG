import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class CMEditor extends Applet
{
    public void init() {
        this.setBackground(Color.cyan);
        final CMFrame cmFrame = new CMFrame(this);
        cmFrame.setSize(700, 550);
        cmFrame.setLocation(20, 20);
        cmFrame.setResizable(false);
        cmFrame.setVisible(true);
        cmFrame.show();
    }
}
