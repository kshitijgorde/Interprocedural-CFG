import java.util.Calendar;
import java.text.SimpleDateFormat;
import javax.swing.tree.MutableTreeNode;
import java.awt.Container;
import java.awt.Component;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.swing.SwingUtilities;
import javax.swing.RepaintManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager;
import java.applet.Applet;
import netscape.javascript.JSObject;
import javax.swing.text.html.parser.ParserDelegator;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class EPApplet extends JApplet implements rp_Q
{
    rp_au a;
    private boolean a;
    private static int a;
    
    public EPApplet() {
        this.a = null;
    }
    
    public void init() {
        try {
            if (EPApplet.a == 123456) {
                System.out.println("Applet already running.");
                this.a();
                return;
            }
            EPApplet.a = 123456;
            new ParserDelegator();
            rp_gd.b();
            this.a = true;
            String property;
            if ((property = System.getProperty("java.version")) == null) {
                property = "";
            }
            rp_C.a(2, "Java version: " + property);
            boolean a;
            if (property.compareTo("1.5") < 0) {
                final JSObject window = JSObject.getWindow((Applet)this);
                rp_C.a(2, "Java 1.5 or higher required. (" + property + "installed)");
                window.eval("wrongJavaVersion(\"" + property + "\")");
                a = false;
            }
            else {
                a = this.a;
            }
            if (!a) {
                this.a();
                return;
            }
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            }
            catch (UnsupportedLookAndFeelException ex) {}
            catch (ClassNotFoundException ex2) {}
            catch (InstantiationException ex3) {}
            catch (IllegalAccessException ex4) {}
        }
        catch (Throwable t) {
            this.a();
        }
    }
    
    public void destroy() {
        System.out.println("Applet.destroy");
        try {
            RepaintManager.setCurrentManager(null);
        }
        catch (Throwable t) {
            System.err.println("Error closing repaint manager due to: " + t.getMessage());
        }
        rp_au.a();
        EPApplet.a = 0;
    }
    
    public void start() {
        try {
            final rp_dH rp_dH;
            (rp_dH = new rp_dH(this)).a("CookieProp");
            try {
                SwingUtilities.invokeAndWait(new rp_fo(this, rp_dH));
                rp_C.a(4, "Should indicate loading complete now!");
                JSObject.getWindow((Applet)this).eval("javaAppletLoaded();");
            }
            catch (Exception ex) {
                System.err.println("createGUI didn't successfully complete");
                System.out.println("Exx: " + ex + " cause: " + ex.getMessage());
                final StringWriter stringWriter = new StringWriter();
                final PrintWriter printWriter = new PrintWriter(stringWriter);
                ex.printStackTrace(printWriter);
                printWriter.flush();
                System.out.println("Error with init stack: " + stringWriter.toString());
                this.a();
                return;
            }
            EPApplet.a = 0;
            try {
                SwingUtilities.invokeLater(new rp_fp(this));
            }
            catch (Exception ex2) {
                System.err.println("MainPanel.onStart didn't successfully complete");
                this.a();
            }
        }
        catch (Throwable t) {
            this.a();
        }
    }
    
    public void stop() {
        rp_C.a(10, "Applet.stop()");
        EPApplet.a = 0;
    }
    
    public final String a(final String s) {
        return this.getParameter(s);
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
    
    public void doCommand(final String s) {
        rp_C.a(4, "EPApplet.doCommand: " + s);
        this.a.a(s);
    }
    
    public int isModified() {
        if (rp_au.a.a.a) {
            return 1;
        }
        return 0;
    }
    
    public boolean isJavaVersionOk() {
        System.out.println("is java version ok returning: " + this.a);
        return this.a;
    }
    
    public String saveOnExit() {
        try {
            final rp_dH rp_dH;
            final boolean a = (rp_dH = (rp_dH)rp_au.a.a()).a((String)null, true);
            rp_C.a(4, "User logged:" + a);
            if (a) {
                final rp_v a2;
                if ((a2 = rp_au.a.a) != null && !rp_dH.a) {
                    rp_au.a(a2);
                    final rp_fx a3;
                    if ((a3 = rp_au.a).a == null) {
                        return "";
                    }
                    return a3.a.a;
                }
                else {
                    final String a4;
                    if ((a4 = this.a()) != null) {
                        rp_C.a(4, "saveOnExit() is returning " + a4);
                        return a4;
                    }
                }
            }
            else {
                final StringBuffer sb = new StringBuffer();
                final String[] split;
                if (rp_au.a("cmNothing") && (split = sb.toString().split("\n")).length > 1) {
                    System.out.println("storing temp-plan name:" + split[1]);
                    rp_dH.a("temp-plan", split[1]);
                }
            }
            return "";
        }
        catch (Exception ex) {
            System.err.println("Error with saveOnExit due to: " + ex.getMessage());
            return "";
        }
    }
    
    private String a() {
        String format;
        try {
            final rp_v a2;
            rp_v a;
            if ((a = (a2 = this.a.a().a()).a("SavedOnExit")) == null) {
                if (!rp_au.a.a().a(rp_aw.g, new rp_v[] { a2 }, "SavedOnExit").a()) {
                    return null;
                }
                a = new rp_v("SavedOnExit", -9, -1);
                a2.add(a);
            }
            if (!a.a()) {
                return null;
            }
            format = new SimpleDateFormat("yyyyMMdd-HHmm").format(Calendar.getInstance().getTime());
            final rp_v rp_v = new rp_v(format, "");
            a.add(rp_v);
            rp_au.a(rp_v);
        }
        catch (Exception ex) {
            return null;
        }
        return format;
    }
    
    private void a() {
        rp_C.a(1, "Should indicate loading FAILED now!");
        JSObject.getWindow((Applet)this).eval("javaAppletFailed();");
    }
}
