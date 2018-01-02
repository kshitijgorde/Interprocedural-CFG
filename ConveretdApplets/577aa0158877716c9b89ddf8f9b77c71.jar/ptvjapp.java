import java.awt.Dimension;
import java.awt.event.ComponentListener;
import java.applet.AppletStub;
import java.awt.event.WindowListener;
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
        this.addWindowListener(new ptvjapp$1(this));
        ptviewer.setStub(new ptstub(s, this));
        final String parameter;
        if ((parameter = ptviewer.getParameter("width")) != null) {
            System.out.println("width" + 320);
            int1 = Integer.parseInt(parameter);
        }
        final String parameter2;
        if ((parameter2 = ptviewer.getParameter("height")) != null) {
            System.out.println("height" + 200);
            int2 = Integer.parseInt(parameter2);
        }
        this.resize(int1, int2);
        final String parameter3;
        if ((parameter3 = ptviewer.getParameter("name")) != null) {
            this.setTitle(parameter3);
        }
        this.setVisible(true);
        final Dimension size;
        if ((size = ptviewer.getSize()).width != int1 || size.height != int2) {
            final Dimension dimension = size;
            final int n = int1;
            dimension.width = n + n - size.width;
            final Dimension dimension2 = size;
            final int n2 = int2;
            dimension2.height = n2 + n2 - size.height;
            this.resize(size);
            ptviewer.resize(int1, int2);
        }
        ptviewer.init();
        ptviewer.start();
        this.addComponentListener(new ptvjapp$2(this));
    }
}
