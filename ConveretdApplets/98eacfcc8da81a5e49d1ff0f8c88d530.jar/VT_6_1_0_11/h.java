// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.border.SoftBevelBorder;
import com.hw.client.util.a;
import java.util.StringTokenizer;
import java.util.Vector;
import java.awt.GraphicsEnvironment;
import java.applet.Applet;
import java.awt.dnd.DragSourceListener;
import java.awt.dnd.DragGestureListener;
import java.awt.Font;
import javax.swing.border.Border;
import javax.swing.Icon;
import com.hw.client.util.c;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;

public final class h
{
    private static String b;
    static Color a;
    private static Color c;
    private static Color d;
    private static Color e;
    private static Color f;
    private static Color g;
    private static LineBorder h;
    
    public static void a(final JButton button) {
        button.setRolloverEnabled(true);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setHorizontalTextPosition(0);
        button.setVerticalTextPosition(3);
    }
    
    public static JButton a(final String s, final String text, final String toolTipText, final String actionCommand) {
        final cP cp;
        (cp = new cP()).setIcon(com.hw.client.util.c.a(s));
        a(cp);
        if (toolTipText != null) {
            cp.setToolTipText(toolTipText);
        }
        if (actionCommand != null) {
            cp.setActionCommand(actionCommand);
        }
        if (text != null) {
            cp.setText(text);
        }
        cp.setBorder(null);
        cp.setFont(new Font(VT_6_1_0_11.h.b, 0, 10));
        return cp;
    }
    
    public static JButton a(final String s, final String text, final String toolTipText, final String actionCommand, final DragGestureListener dragGestureListener, final DragSourceListener dragSourceListener) {
        final k k;
        (k = new k(dragGestureListener, dragSourceListener)).setIcon(com.hw.client.util.c.a(s));
        a(k);
        if (toolTipText != null) {
            k.setToolTipText(toolTipText);
        }
        if (actionCommand != null) {
            k.setActionCommand(actionCommand);
        }
        if (text != null) {
            k.setText(text);
        }
        k.setBorder(null);
        k.setFont(new Font(VT_6_1_0_11.h.b, 0, 10));
        return k;
    }
    
    public static String a() {
        return VT_6_1_0_11.h.b;
    }
    
    public static void a(final Applet applet) {
        final String[] availableFontFamilyNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        final Vector<String> vector = new Vector<String>();
        for (int i = 0; i < availableFontFamilyNames.length; ++i) {
            vector.addElement(availableFontFamilyNames[i]);
        }
        String s;
        if ((s = applet.getParameter("font")) != null) {
            s = s.trim();
        }
        if (s == null || s.length() == 0) {
            s = "Arial Unicode MS, Dialog";
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",;");
        while (stringTokenizer.hasMoreTokens()) {
            final String trim = stringTokenizer.nextToken().trim();
            if (vector.contains(trim)) {
                VT_6_1_0_11.h.b = trim;
            }
        }
        com.hw.client.util.a.d("VTUI.setDefaultFontName defaultFontList=" + s + " - defaultFontName=" + VT_6_1_0_11.h.b);
    }
    
    public static Color b() {
        return VT_6_1_0_11.h.c;
    }
    
    public static Color c() {
        return VT_6_1_0_11.h.d;
    }
    
    public static Color d() {
        return VT_6_1_0_11.h.e;
    }
    
    public static Color e() {
        return VT_6_1_0_11.h.f;
    }
    
    public static Border f() {
        return new SoftBevelBorder(1, VT_6_1_0_11.h.c, VT_6_1_0_11.h.c, VT_6_1_0_11.h.a, VT_6_1_0_11.h.a);
    }
    
    public static Border g() {
        return new SoftBevelBorder(1, VT_6_1_0_11.h.a, VT_6_1_0_11.h.a, VT_6_1_0_11.h.a, VT_6_1_0_11.h.a);
    }
    
    public static Border h() {
        return new LineBorder(VT_6_1_0_11.h.a);
    }
    
    public static Border i() {
        return VT_6_1_0_11.h.h;
    }
    
    public static JPanel j() {
        final JPanel panel;
        (panel = new JPanel()).setMinimumSize(new Dimension(50, 1));
        panel.setMaximumSize(new Dimension(5000, 1));
        panel.setSize(new Dimension(500, 1));
        panel.setPreferredSize(new Dimension(500, 1));
        return panel;
    }
    
    static {
        VT_6_1_0_11.h.b = "Dialog";
        VT_6_1_0_11.h.a = ca.a("#BCBCBC", Color.darkGray);
        VT_6_1_0_11.h.c = ca.a("#E6E9EE", Color.lightGray);
        ca.a("#EFEFEF", Color.lightGray);
        VT_6_1_0_11.h.d = ca.a("#F2F3F9", Color.lightGray);
        VT_6_1_0_11.h.e = ca.a("#D8DDE4", Color.darkGray);
        ca.a("#F1F1F7", Color.lightGray);
        VT_6_1_0_11.h.f = ca.a("#E5E5E5", Color.lightGray);
        VT_6_1_0_11.h.g = ca.a("#78879A", Color.darkGray);
        VT_6_1_0_11.h.h = new LineBorder(VT_6_1_0_11.h.g, 1, false);
        new LineBorder(VT_6_1_0_11.h.f, 1, false);
    }
}
