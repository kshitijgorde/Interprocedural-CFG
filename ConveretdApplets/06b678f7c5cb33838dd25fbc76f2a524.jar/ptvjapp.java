import java.awt.Dimension;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import java.applet.AppletStub;
import java.awt.event.WindowListener;
import java.applet.Applet;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Component;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class ptvjapp extends Frame
{
    public static void main(final String[] array) {
        System.runFinalizersOnExit(true);
        String s;
        if (array.length == 0) {
            s = "default.html";
        }
        else {
            s = array[0];
        }
        if (s != null) {
            new ptvjapp(s);
        }
    }
    
    public ptvjapp(final String s) {
        int int1 = 320;
        int int2 = 200;
        final ptviewer ptviewer = new ptviewer();
        this.add("Center", ptviewer);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                final Applet applet = (Applet)windowEvent.getWindow().getComponent(0);
                applet.stop();
                applet.destroy();
                System.exit(0);
            }
        });
        ptviewer.setStub(new ptstub(s, this));
        final String parameter = ptviewer.getParameter("width");
        if (parameter != null) {
            System.out.println("width" + int1);
            int1 = Integer.parseInt(parameter);
        }
        final String parameter2 = ptviewer.getParameter("height");
        if (parameter2 != null) {
            System.out.println("height" + int2);
            int2 = Integer.parseInt(parameter2);
        }
        this.resize(int1, int2);
        final String parameter3;
        if ((parameter3 = ptviewer.getParameter("name")) != null) {
            this.setTitle(parameter3);
        }
        this.setVisible(true);
        final Dimension size = ptviewer.getSize();
        if (size.width != int1 || size.height != int2) {
            size.width = int1 + int1 - size.width;
            size.height = int2 + int2 - size.height;
            this.resize(size);
            ptviewer.resize(int1, int2);
        }
        ptviewer.init();
        ptviewer.start();
        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(final ComponentEvent componentEvent) {
                final Applet applet = (Applet)((Frame)componentEvent.getComponent()).getComponent(0);
                applet.stop();
                applet.start();
            }
        });
    }
}
