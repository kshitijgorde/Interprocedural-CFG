// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.eck.umb;

import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.JMenuBar;
import java.awt.Container;
import edu.hws.eck.umb.util.I18n;
import javax.swing.JFrame;

public class MandelbrotFrame extends JFrame
{
    public MandelbrotFrame() {
        this(false);
    }
    
    public MandelbrotFrame(final boolean b) {
        super(I18n.tr("mandelbrotFrame.title", new Object[0]));
        final MandelbrotPanel contentPane = new MandelbrotPanel();
        final MandelbrotDisplay display = contentPane.getDisplay();
        this.setContentPane(contentPane);
        this.setJMenuBar(new MandelbrotMenus(display, this, contentPane.getStatusBar(), b));
        this.pack();
        this.setLocation(20, 60);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosed(final WindowEvent windowEvent) {
                display.closing();
            }
        });
    }
}
