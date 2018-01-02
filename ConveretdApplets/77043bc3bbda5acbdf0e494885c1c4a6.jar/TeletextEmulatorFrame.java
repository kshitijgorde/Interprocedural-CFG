import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Component;
import java.applet.AppletContext;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class TeletextEmulatorFrame extends Frame
{
    public TeletextEmulatorFrame() {
        super("TeletextEmulatorFrame");
        final TeletextEmulator teletextEmulator = new TeletextEmulator("localhost/site/jeditext/teletext/tmc", null);
        this.add(teletextEmulator);
        teletextEmulator.render();
        teletextEmulator.repaint();
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                System.exit(0);
            }
        });
    }
}