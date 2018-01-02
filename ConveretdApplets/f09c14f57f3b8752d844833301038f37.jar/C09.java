import java.io.IOException;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.Font;
import java.awt.Label;
import java.applet.Applet;
import java.io.InputStream;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class C09 extends Frame
{
    public static String c;
    C45 e;
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.hide();
            return true;
        }
        return super.handleEvent(event);
    }
    
    public void b() {
        this.e.h();
        this.dispose();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public C09(final InputStream inputStream, final String s, final String s2, final String s3, final Applet applet) throws IOException {
        super(" EXPOCAD " + ((s2 == null) ? "No Drawing Specified" : s2));
        final Label label = new Label("Status                                                       ");
        label.setFont(new Font("TimesRoman", 0, 12));
        (this.e = new C45(inputStream, s, s3, applet, label)).r(this);
        this.add("Center", this.e);
        this.add("South", label);
        this.move(0, 0);
        this.show();
        int n = -1;
        int int1 = -1;
        if (applet != null) {
            if (applet.getParameter("frame_width") != null) {
                try {
                    n = Integer.parseInt(applet.getParameter("frame_width"));
                }
                catch (NumberFormatException ex) {
                    System.out.println("ERROR " + applet.getParameter("frame_width") + " not valid");
                }
            }
            if (applet.getParameter("frame_height") != null) {
                try {
                    int1 = Integer.parseInt(applet.getParameter("frame_height"));
                }
                catch (NumberFormatException ex2) {
                    System.out.println("ERROR " + applet.getParameter("frame_height") + " not valid");
                }
            }
        }
        if (n == -1 || int1 == -1) {
            final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            if (screenSize != null) {
                n = screenSize.width;
                int1 = screenSize.height - 30;
            }
        }
        if (n < 0) {
            n = 800;
        }
        if (int1 < 0) {
            int1 = 500;
        }
        this.resize(n, int1);
        this.invalidate();
        this.validate();
        if (s2 == null) {
            this.toBack();
        }
    }
    
    public void setTitle(final String s) {
        super.setTitle(" EXPOCAD " + s);
    }
    
    static {
        C09.c = "Copyright (c) 1998 - Arnona Internet Software inc. All Rights Reserved";
    }
}
