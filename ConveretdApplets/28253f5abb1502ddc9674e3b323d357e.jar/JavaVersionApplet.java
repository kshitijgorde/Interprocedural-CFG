import java.net.MalformedURLException;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class JavaVersionApplet extends Applet
{
    public static int javaVersionNumber;
    float version;
    float targetVersion;
    String redirectURLString;
    String target;
    
    public void init() {
        final String parameter = this.getParameter("targetVersion");
        this.targetVersion = -1.0f;
        if (parameter != null) {
            try {
                this.targetVersion = new Float(parameter);
                this.println("Java " + this.targetVersion);
            }
            catch (NumberFormatException ex) {
                this.println("The Java version " + parameter + " cannot be recognised as a number!");
            }
        }
        else {
            this.println("Java target version must be specified in the applet parameters.  E.G.\n<PARAM NAME=targetVersion VALUE=1.3>");
        }
        this.redirectURLString = this.getParameter("redirect");
        if (this.redirectURLString == null) {
            this.println("The redirect url must be given in the applet parameters.  E.G.\n<PARAM NAME=redirect VALUE=javaversion.html>");
        }
        this.target = this.getParameter("target");
        if (this.target == null) {
            this.target = "_top";
        }
        this.version = getVersion();
        this.println("" + getVersion());
        if (this.version < this.targetVersion) {
            this.redirect();
        }
    }
    
    private void redirect() {
        this.println("01 version " + this.version + " will not run " + this.targetVersion);
        if (this.redirectURLString.startsWith("http")) {
            try {
                this.getAppletContext().showDocument(new URL(this.redirectURLString), this.target);
            }
            catch (MalformedURLException ex) {
                this.println("Could not recognise redirect URL '" + this.redirectURLString + "'");
            }
        }
        else {
            final URL resource = this.getClass().getResource(this.redirectURLString);
            if (resource == null) {
                try {
                    this.getAppletContext().showDocument(new URL(this.getCodeBase().toString() + this.redirectURLString), this.target);
                }
                catch (MalformedURLException ex2) {
                    this.println("Could not recognise redirect URL '" + this.redirectURLString + "'");
                }
            }
            else {
                this.getAppletContext().showDocument(resource, this.target);
            }
        }
    }
    
    public static float getVersion() {
        try {
            JavaVersionApplet.javaVersionNumber = 10;
            Class.forName("java.lang.Void");
            ++JavaVersionApplet.javaVersionNumber;
            Class.forName("java.lang.ThreadLocal");
            ++JavaVersionApplet.javaVersionNumber;
            Class.forName("java.lang.StrictMath");
            ++JavaVersionApplet.javaVersionNumber;
            Class.forName("java.lang.CharSequence");
            ++JavaVersionApplet.javaVersionNumber;
            Class.forName("java.lang.Appendable");
            ++JavaVersionApplet.javaVersionNumber;
        }
        catch (ClassNotFoundException ex) {}
        return JavaVersionApplet.javaVersionNumber / 10.0f;
    }
    
    public final void println(final String s) {
        System.out.println(s);
        this.showStatus(s);
    }
    
    static {
        JavaVersionApplet.javaVersionNumber = 10;
    }
}
