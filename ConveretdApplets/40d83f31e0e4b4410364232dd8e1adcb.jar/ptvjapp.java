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

public class ptvjapp
{
    static Frame mainFrame;
    static Frame myframe;
    
    public static void main(final String[] array) {
        System.runFinalizersOnExit(true);
        new ptvjapp(array);
        ptvjapp.mainFrame = ptvjapp.myframe;
    }
    
    public ptvjapp(final String[] array) {
        int int1 = 320;
        int int2 = 200;
        final ptviewer ptviewer = new ptviewer();
        (ptvjapp.myframe = new Frame()).add("Center", ptviewer);
        ptvjapp.myframe.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                final Applet applet = (Applet)windowEvent.getWindow().getComponent(0);
                applet.stop();
                applet.destroy();
                if (windowEvent.getWindow() == ptvjapp.mainFrame) {
                    System.exit(0);
                    return;
                }
                windowEvent.getWindow().dispose();
            }
        });
        String s;
        if (array.length == 0) {
            s = "default.html";
        }
        else {
            s = array[0];
        }
        ptviewer.setStub(new ptstub(s, ptviewer, ptvjapp.myframe));
        final String parameter;
        if ((parameter = ptviewer.getParameter("width")) != null) {
            int1 = Integer.parseInt(parameter);
        }
        final String parameter2;
        if ((parameter2 = ptviewer.getParameter("height")) != null) {
            int2 = Integer.parseInt(parameter2);
        }
        ptvjapp.myframe.resize(int1, int2);
        final String parameter3;
        if ((parameter3 = ptviewer.getParameter("name")) != null) {
            ptvjapp.myframe.setTitle(parameter3);
        }
        ptvjapp.myframe.setVisible(true);
        final Dimension size = ptviewer.getSize();
        if (size.width != int1 || size.height != int2) {
            ptvjapp.myframe.resize(int1 + int1 - size.width, int2 + int2 - size.height);
            ptviewer.resize(int1, int2);
        }
        ptviewer.init();
        ptviewer.start();
        ptvjapp.myframe.addComponentListener(new ComponentAdapter() {
            public void componentResized(final ComponentEvent componentEvent) {
                final Applet applet = (Applet)((Frame)componentEvent.getComponent()).getComponent(0);
                applet.stop();
                applet.start();
            }
        });
    }
    
    static {
        ptvjapp.mainFrame = null;
    }
}
