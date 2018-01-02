// 
// Decompiled by Procyon v0.5.30
// 

package speedometer;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import java.net.URL;
import java.applet.Applet;
import java.awt.Component;
import javax.swing.JApplet;

public class SpeedometerApplet extends JApplet
{
    private static final long B = 3256445819594224697L;
    public static final String C = "downloadUrl";
    public static final String D = "downloadTimeSec";
    public static final String F = "uploadUrl";
    public static final String E = "uploadTimeSec";
    public static final String K = "uploadDataLength";
    public static final String J = "target";
    protected C I;
    protected B G;
    protected String H;
    protected boolean A;
    
    public void init() {
        (this.I = new C()).A(this);
        this.getContentPane().add(this.I);
        (this.G = new B()).A(this.I);
        this.H = this.getParameter("target");
        this.G.A(this.getParameter("downloadUrl"));
        this.G.B(Integer.parseInt(this.getParameter("downloadTimeSec")));
        this.G.C(this.getParameter("uploadUrl"));
        this.G.A(Integer.parseInt(this.getParameter("uploadTimeSec")));
        this.G.A(Long.parseLong(this.getParameter("uploadDataLength")));
        speedometer.A.A(this);
    }
    
    public String getAppletInfo() {
        return "Speedometer 1.0";
    }
    
    public void A(final String s) {
        try {
            this.getAppletContext().showDocument(new URL(s), this.H);
        }
        catch (Exception ex) {}
    }
    
    protected boolean C(final String s) {
        final String parameter = this.getParameter(s);
        return parameter != null && parameter.trim().length() > 0;
    }
    
    protected void B(final String s) {
        System.out.println(s);
    }
    
    public static void A(final String[] array) {
        try {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    final C c = new C();
                    final B b = new B();
                    b.A(c);
                    b.A("http://www.apache.org/dist/httpd/binaries/win32/apache_2.0.55-win32-x86-no_ssl.exe");
                    b.A("http://apache.datanet.ee/tomcat/tomcat-5/v5.5.14-beta/bin/apache-tomcat-5.5.14.zip");
                    b.B(10);
                    b.C("http://www.bplspeedtest.com/simple_speed_test/uploadexample.asp");
                    b.A(10);
                    JFrame.setDefaultLookAndFeelDecorated(true);
                    final JFrame frame = new JFrame();
                    frame.setTitle("Speedometer applet");
                    frame.getContentPane().add(c);
                    frame.setDefaultCloseOperation(3);
                    frame.pack();
                    frame.setVisible(true);
                }
            });
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
