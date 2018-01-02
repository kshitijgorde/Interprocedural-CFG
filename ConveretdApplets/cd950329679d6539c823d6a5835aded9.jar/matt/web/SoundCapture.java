// 
// Decompiled by Procyon v0.5.30
// 

package matt.web;

import java.awt.Component;
import javax.swing.JApplet;

public class SoundCapture extends JApplet
{
    CapturePlayback cpPanel;
    
    public SoundCapture() {
        (this.cpPanel = new CapturePlayback()).setApplet(this);
        this.add(this.cpPanel);
    }
    
    public void init() {
    }
}
