// 
// Decompiled by Procyon v0.5.30
// 

package jnlpapp;

import java.io.FileNotFoundException;
import java.util.Vector;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JOptionPane;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager;
import java.awt.Container;
import java.awt.Component;
import javax.swing.JFrame;

public class JNLPEPApp extends JFrame implements rp_Q
{
    public rp_au a;
    
    public final String a(final String s) {
        return System.getProperty(s);
    }
    
    public final void a(final Component glassPane) {
        this.setGlassPane(glassPane);
    }
    
    public final void a(final rp_au a) {
        this.a = a;
    }
    
    public final Container a() {
        return this.getContentPane();
    }
    
    public static void main(final String[] array) {
        System.out.println("JNLP App Launched");
        try {
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            }
            catch (UnsupportedLookAndFeelException ex3) {}
            catch (ClassNotFoundException ex4) {}
            catch (InstantiationException ex5) {}
            catch (IllegalAccessException ex6) {}
        }
        catch (Throwable t) {
            a(t);
        }
        final JNLPEPApp jnlpepApp;
        (jnlpepApp = new JNLPEPApp()).setTitle("Easy Plan Pro");
        final JNLPEPApp jnlpepApp2 = jnlpepApp;
        try {
            final rp_dh rp_dh = new rp_dh(jnlpepApp2);
            try {
                SwingUtilities.invokeAndWait(new rp_dn(jnlpepApp2, rp_dh));
            }
            catch (Exception ex) {
                System.err.println("createGUI didn't successfully complete");
                System.out.println("Exx: " + ex + " cause: " + ex.getMessage());
                final StringWriter stringWriter = new StringWriter();
                final PrintWriter printWriter = new PrintWriter(stringWriter);
                ex.printStackTrace(printWriter);
                printWriter.flush();
                System.out.println("Error with init stack: " + stringWriter.toString());
                a(ex);
                return;
            }
            try {
                SwingUtilities.invokeLater(new rp_do(jnlpepApp2));
            }
            catch (Exception ex2) {
                System.err.println("MainPanel.onStart didn't successfully complete");
                a(ex2);
            }
        }
        catch (Throwable t2) {
            a(t2);
        }
    }
    
    private static void a(final Throwable t) {
        t.printStackTrace();
        JOptionPane.showMessageDialog(null, "Failed to launch EasyPlan Pro", "Failed to launch!", 0);
        System.exit(0);
    }
    
    public static void a(final rp_bZ rp_bZ, final rp_Q rp_Q) {
        final rp_dF a = new rp_dF();
        rp_bZ.a = a;
        final String a2 = rp_Q.a("cookie_contents");
        rp_C.a(4, "Initializing gui (hardcoded cookie: " + a2 + ")");
        if (a2 != null && !a2.trim().equals("")) {
            rp_C.a(4, "cookie_contents: " + a2);
            rp_bZ.a(a2);
        }
        rp_Q.a(a);
        b(rp_bZ, rp_Q);
        final rp_aJ a3;
        if ((a3 = a(rp_bZ, rp_Q)) == null) {
            return;
        }
        final String a4;
        final boolean b = (a4 = rp_Q.a("allow_roomplan_import")) != null && a4.equalsIgnoreCase("yes");
        rp_C.a(4, "Param: Allow opening EP1 set to: " + b);
        a3.a(b);
        final String a5;
        final boolean b2 = (a5 = rp_Q.a("ask_for_user_picked_items")) != null && a5.equalsIgnoreCase("yes");
        rp_C.a(4, "Param: bAskForPickedItems: " + b2);
        a3.b(b2);
        final String a7;
        final Color[] a6;
        if ((a6 = rp_C.a(a7 = rp_Q.a("default_template_color"))) != null) {
            rp_C.a(4, "Param: default_template_color: " + a7);
            a3.a(a6[0], a6[1]);
        }
        rp_bZ.a((Object)null);
        final String a8 = rp_Q.a("language");
        final String a9 = rp_Q.a("country");
        rp_C.a(10, "create MainPanel [" + a8 + ";" + a9 + "]");
        rp_au rp_au;
        if (a8 != null && a9 != null) {
            rp_au = rp_au.a(a3, a8, a9);
        }
        else {
            rp_au = rp_au.a(a3);
        }
        rp_Q.a(rp_au);
        final rp_dF rp_dF = a;
        final rp_fx a10 = rp_au.a;
        final rp_N a11 = rp_au.a.a;
        final rp_fx a12 = a10;
        final rp_dF rp_dF2 = rp_dF;
        rp_dF.a = a11;
        rp_dF2.a = a12;
        rp_au.setBackground(Color.white);
        rp_bZ.a(rp_au.a);
        rp_Q.a().setLayout(new BorderLayout());
        rp_Q.a().add(rp_au, "Center");
        final String a13;
        if ((a13 = rp_Q.a("start_with_tempload")) != null) {
            a13.length();
        }
        rp_C.a(4, "Param: Start with temp: " + a13);
        boolean b3 = false;
        final String b4;
        if ((b4 = rp_bZ.b("temp-plan", "")) != null && b4.length() > 0) {
            b3 = true;
        }
        final String a14;
        if ((a14 = rp_Q.a("start_with_name")) != null) {
            try {
                final String decode = URLDecoder.decode(a14, "UTF-8");
                rp_C.a(4, "Param: Start with name: " + decode);
                a3.a("start_with_name", decode);
                final String a15 = rp_Q.a("start_with_folder_id");
                rp_C.a(4, "Param: Start with folder id: " + a15);
                if (a15 != null && a15.length() > 0) {
                    try {
                        Integer.parseInt(a15);
                        a3.a("start_with_folder_id", a15);
                    }
                    catch (NumberFormatException ex) {
                        rp_C.a(1, "Wrong applet parameter start_with_folder_id : " + a15);
                        a3.a("start_with_name", (String)null);
                    }
                }
            }
            catch (UnsupportedEncodingException ex2) {}
        }
        final String a16;
        rp_bZ.a = ((a16 = rp_Q.a("user_restricted")) != null && a16.equalsIgnoreCase("yes"));
        final String a17;
        rp_bZ.b = ((a17 = rp_Q.a("can_create_user")) != null && a17.equalsIgnoreCase("yes"));
        final String a18;
        rp_bZ.c = ((a18 = rp_Q.a("eep_only")) != null && a18.equalsIgnoreCase("yes"));
        final String a19;
        rp_bZ.d = ((a19 = rp_Q.a("solo_account")) != null && a19.equalsIgnoreCase("yes"));
        final String a20;
        if ((a20 = rp_Q.a("allow_request_info")) != null) {
            a20.equalsIgnoreCase("yes");
        }
        rp_C.a(10, "will initiate mainPanel");
        rp_au.a(b3, a13);
        rp_au.validate();
        rp_au.f();
    }
    
    private static rp_aJ a(final rp_bZ rp_bZ, final rp_Q rp_Q) {
        rp_C.a(10, "Loading additional properties:");
        final Vector<String> vector = new Vector<String>();
        for (int i = 1; i < 10; ++i) {
            final String a;
            if ((a = rp_Q.a("prop" + i)) != null) {
                rp_C.a(4, "Additional property file:" + a);
                vector.addElement(a);
            }
        }
        final String a2;
        if ((a2 = rp_Q.a("color_schema")) != null) {
            vector.addElement(a2);
        }
        rp_aJ rp_aJ;
        try {
            rp_aJ = new rp_aJ(rp_bZ, vector);
        }
        catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        final String a3;
        if ((a3 = rp_Q.a("srv_p_servlet")) != null) {
            rp_aJ.a("srv_p_servlet", a3);
        }
        final String a4;
        if ((a4 = rp_Q.a("srv_print_page")) != null) {
            rp_aJ.a("srv_print_page", a4);
        }
        final String a5;
        if ((a5 = rp_Q.a("account_mgmt")) != null) {
            rp_aJ.a("account_mgmt", a5);
        }
        final Vector a6;
        if ((a6 = rp_bZ.a()) != null) {
            for (int j = 0; j < a6.size(); ++j) {
                final String s;
                final int index;
                if ((index = (s = a6.elementAt(j)).indexOf(61)) >= 1 && index != s.length() - 1) {
                    rp_aJ.a(s.substring(0, index), s.substring(index + 1));
                }
            }
        }
        rp_aJ.a();
        return rp_aJ;
    }
    
    private static void b(final rp_bZ rp_bZ, final rp_Q rp_Q) {
        String[] array = null;
        for (int i = 1; i < 10; ++i) {
            final String a;
            if ((a = rp_Q.a("res" + i)) != null) {
                if (array == null) {
                    array = new String[10];
                }
                array[i] = a;
            }
        }
        rp_bZ.a(array);
    }
}
