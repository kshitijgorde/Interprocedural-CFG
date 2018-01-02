// 
// Decompiled by Procyon v0.5.30
// 

package DCL.util;

import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import java.net.URLConnection;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.awt.Toolkit;
import java.net.URL;
import java.applet.Applet;

public class CompatibilityTest2 extends Applet
{
    public void init() {
        boolean b = false;
        a("CT: init() entry");
        if (this.getParameter("isMac") != null) {
            b = true;
        }
        if (System.getProperty("java.vendor").indexOf("Microsoft") != -1) {
            b = true;
        }
        if (this.getParameter("scriptingOff") != null) {
            a("CT: Run test now with no scripting");
            b = true;
        }
        if (b) {
            this.runTest();
        }
        a("CT: init() exit");
    }
    
    public void runTest() {
        a("CT: runTest() entry");
        long long1 = 5000L;
        final String parameter = this.getParameter("timeout");
        if (parameter != null) {
            try {
                long1 = Long.parseLong(parameter);
            }
            catch (NumberFormatException ex) {
                long1 = 5000L;
            }
        }
        final long n = System.currentTimeMillis() + long1;
        try {
            Thread.sleep(1000L);
        }
        catch (InterruptedException ex2) {}
        final String property = System.getProperty("java.version");
        final String property2 = System.getProperty("java.vendor");
        FirewallTest firewallTest = null;
        Thread thread = null;
        if (this.getParameter("runFirewallTest") != null) {
            firewallTest = new FirewallTest(this.getCodeBase().getHost());
            thread = new Thread(firewallTest);
            a("CT: Firewall test starting");
            thread.start();
        }
        final String parameter2 = this.getParameter("speedtestfile");
        long n2 = 0L;
        a("CT: Bandwidth test starting");
        final long currentTimeMillis = System.currentTimeMillis();
        if (parameter2 != null && parameter2.length() > 0) {
            try {
                final URLConnection openConnection = new URL(this.getDocumentBase(), parameter2).openConnection();
                openConnection.getContent();
                final long n3 = System.currentTimeMillis() - currentTimeMillis;
                n2 = openConnection.getContentLength() * 1000;
                n2 /= n3;
            }
            catch (Exception ex3) {}
            a("CT: Bandwidth test finished");
        }
        String s = "0";
        String substring = "0";
        String substring2 = "0";
        if (this.getParameter("runVideoTest") != null) {
            a("CT: JMF test starting");
            try {
                if (Class.forName("JMFInit") != null) {
                    final Class<?> forName = Class.forName("javax.media.Manager");
                    if (forName != null) {
                        a("CT: JMF present, checking version");
                        s = (String)forName.getMethod("getVersion", (Class[])null).invoke(null, (Object[])null);
                        a("CT: JMF version " + s);
                        final int index = s.indexOf(".");
                        substring = s.substring(0, index);
                        substring2 = s.substring(index + 1, s.indexOf(".", index + 1));
                    }
                }
            }
            catch (Throwable t) {
                a("CT: JMF not found");
            }
            a("CT: JMF test finished");
        }
        final int pixelSize = Toolkit.getDefaultToolkit().getColorModel().getPixelSize();
        if (firewallTest != null) {
            try {
                while (System.currentTimeMillis() < n && firewallTest != null && !firewallTest.a()) {
                    Thread.sleep(200L);
                }
            }
            catch (InterruptedException ex4) {}
        }
        int b = 0;
        if (firewallTest != null) {
            if (!firewallTest.a()) {
                a("CT: Firewall test took too long - assuming firewall exists");
                thread.stop();
                b = 1;
            }
            else {
                a("CT: Firewall test finished");
                b = firewallTest.b();
            }
        }
        final String encode = URLEncoder.encode(property2);
        final String parameter3 = this.getParameter("collector");
        if (parameter3 != null) {
            final StringBuffer sb = new StringBuffer(parameter3);
            if (this.getParameter("jmfInstall") == null) {
                sb.append("&javaVersion=" + property);
                sb.append("&javaVendor=" + encode);
                sb.append("&jmfVersion=" + s);
                sb.append("&jmfMajorVersion=" + substring);
                sb.append("&jmfMinorVersion=" + substring2);
                sb.append("&firewall=" + b);
                sb.append("&speed=" + n2);
                sb.append("&colorDepth=" + pixelSize);
                sb.append("&checked=YES");
                sb.append("&speedcookie=" + this.getParameter("speedCookie"));
            }
            String parameter4 = this.getParameter("redirectTarget");
            if (parameter4 == null || parameter4.length() == 0) {
                parameter4 = "_self";
            }
            if (this.getParameter("debugNoRedirect") == null) {
                try {
                    a("CT: Redirecting to results page");
                    this.getAppletContext().showDocument(new URL(this.getDocumentBase(), sb.toString()), parameter4);
                }
                catch (MalformedURLException ex5) {}
            }
        }
        a("CT: runTest() exit");
    }
    
    public void destroy() {
    }
    
    private static String a() {
        return new SimpleDateFormat("MMM dd HH:mm:ss").format(new GregorianCalendar().getTime());
    }
    
    private static final void a(final String s) {
        System.out.println(a() + " " + s);
    }
}
