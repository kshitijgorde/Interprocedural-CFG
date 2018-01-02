import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Jtimer extends Applet
{
    private static final String PARAM_NAME_FRAME = "frame";
    private static final String PARAM_NAME_COPY = "copyright";
    private static final String PARAM_VALUE_COPY = "Jtimer.  Copyright (c) 1997 CodeHouse.  www.codehouse.com";
    private static final String MSG_BAD_COPY = "Copyright parameter for this program is\nmissing or invalid. This program will\nterminate.\n\nFor assistance, contact:\n\nsupport@codehouse.com\nwww.codehouse.com\n";
    private static final String DEFAULT_AUDIO = "bark.au";
    static final String DLG_CAP = "Jtimer";
    
    public static void main(final String[] array) {
        new MainContainer((array.length > 0) ? array[0] : "bark.au").frame(true);
    }
    
    public void init() {
        super.init();
        if (this.getParameter("copyright") == null || !this.getParameter("copyright").equals("Jtimer.  Copyright (c) 1997 CodeHouse.  www.codehouse.com")) {
            new MsgDlg("Jtimer", "Copyright parameter for this program is\nmissing or invalid. This program will\nterminate.\n\nFor assistance, contact:\n\nsupport@codehouse.com\nwww.codehouse.com\n");
            System.exit(1);
        }
        else {
            System.out.println("Jtimer.  Copyright (c) 1997 CodeHouse.  www.codehouse.com");
        }
        final String parameter = this.getParameter("frame");
        if (parameter != null && parameter.toLowerCase().equals("true")) {
            new MainContainer("bark.au").frame(false);
            return;
        }
        this.setBackground(Color.black);
        this.setLayout(new BorderLayout());
        this.add(new MainContainer("bark.au"), "Center");
    }
}
