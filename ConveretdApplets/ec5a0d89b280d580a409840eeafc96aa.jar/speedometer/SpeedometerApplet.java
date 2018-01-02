// 
// Decompiled by Procyon v0.5.30
// 

package speedometer;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import java.net.URL;
import speedometer.A.F;
import java.applet.Applet;
import java.awt.Component;
import javax.swing.JApplet;

public class SpeedometerApplet extends JApplet
{
    private static final long C = 3256445819594224697L;
    public static final String E = "downloadUrl";
    public static final String F = "downloadTimeSec";
    public static final String I = "uploadUrl";
    public static final String H = "uploadTimeSec";
    public static final String P = "uploadDataLength";
    public static final String O = "target";
    public static final String G = "cmdTest.text";
    public static final String N = "cmdTestUpload.text";
    public static final String A = "cmdTestDownload.text";
    public static final String L = "lbDownload.text";
    public static final String D = "lbUpload.text";
    protected C M;
    protected B J;
    protected String K;
    protected boolean B;
    
    public void init() {
        final F d = speedometer.A.C.A().D("resources/MainView.properties");
        if (this.C("cmdTest.text")) {
            d.setProperty("cmdTest.text", this.getParameter("cmdTest.text"));
        }
        if (this.C("cmdTestUpload.text")) {
            d.setProperty("cmdTestUpload.text", this.getParameter("cmdTestUpload.text"));
        }
        if (this.C("cmdTestDownload.text")) {
            d.setProperty("cmdTestDownload.text", this.getParameter("cmdTestDownload.text"));
        }
        if (this.C("lbDownload.text")) {
            d.setProperty("lbDownload.text", this.getParameter("lbDownload.text"));
        }
        if (this.C("lbUpload.text")) {
            d.setProperty("lbUpload.text", this.getParameter("lbUpload.text"));
        }
        (this.M = new C()).A(this);
        this.getContentPane().add(this.M);
        (this.J = new B()).A(this.M);
        this.K = this.getParameter("target");
        this.J.A(this.getParameter("downloadUrl"));
        this.J.B(Integer.parseInt(this.getParameter("downloadTimeSec")));
        this.J.C(this.getParameter("uploadUrl"));
        this.J.A(Integer.parseInt(this.getParameter("uploadTimeSec")));
        this.J.A(Long.parseLong(this.getParameter("uploadDataLength")));
        speedometer.A.A(this);
    }
    
    public String getAppletInfo() {
        return "Speedometer 1.1";
    }
    
    public void A(final String s) {
        try {
            this.getAppletContext().showDocument(new URL(s), this.K);
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
