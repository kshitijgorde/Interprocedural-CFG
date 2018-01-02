// 
// Decompiled by Procyon v0.5.30
// 

package am.gevardan.applet;

import java.net.MalformedURLException;
import java.net.URL;
import java.applet.Applet;

public class JVMDetector extends Applet
{
    private boolean isStandalone;
    String text;
    String url;
    
    public String getParameter(final String key, final String def) {
        return this.isStandalone ? System.getProperty(key, def) : ((this.getParameter(key) != null) ? this.getParameter(key) : def);
    }
    
    public JVMDetector() {
        this.isStandalone = false;
        this.text = null;
        this.url = null;
    }
    
    public void init() {
        try {
            this.jbInit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void jbInit() throws Exception {
        if (System.getProperty("os.name").startsWith("Mac OS")) {
            this.openFile(this.getParameter("sufficiently_new"));
            if (this.getParameter("mac_os_vm_version").compareTo(System.getProperty("mrj.version")) > 0) {
                this.openFile("mac_os.html");
            }
            else {
                this.runTour();
            }
        }
        else if (System.getProperty("os.name").startsWith("Mac OS X")) {
            this.openFile(this.getParameter("sufficiently_new"));
            if (this.getParameter("mac_osx_vm_version").compareTo(System.getProperty("java.version")) > 0) {
                this.openFile("mac_osx.html");
            }
            else {
                this.runTour();
            }
        }
        else if (System.getProperty("os.name").startsWith("Windows")) {
            if (System.getProperty("java.vendor").startsWith("Microsoft")) {
                this.openFile("ms_jvm.html");
            }
            else {
                final String sun = this.getParameter("sun_jvm_version");
                final String jvm = System.getProperty("java.version");
                if (sun.compareTo(jvm) > 0) {
                    this.openFile("sun_jvm.html");
                }
                else {
                    this.runTour();
                }
            }
        }
        else if (System.getProperty("os.name").startsWith("Linux")) {
            if (this.getParameter("sun_jvm_version").compareTo(System.getProperty("java.version")) > 0) {
                this.openFile("sun_jvm.html");
            }
            else {
                this.runTour();
            }
        }
        else {
            this.openFile("unknown_jvm.html");
        }
    }
    
    public void openFile(final String fileName) {
        final String url = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.getDocumentBase().getProtocol()))).append(":").append(this.getDocumentBase().getFile().substring(0, this.getDocumentBase().getFile().lastIndexOf("/") + 1)).append(fileName)));
        try {
            this.getAppletContext().showDocument(new URL(url));
        }
        catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void runTour() {
        final String url = this.getParameter("sufficiently_new");
        if (url.startsWith("http:")) {
            try {
                this.getAppletContext().showDocument(new URL(url));
            }
            catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
        }
        else {
            final String url2 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.getDocumentBase().getProtocol()))).append("://").append(this.getDocumentBase().getHost()).append(this.getDocumentBase().getFile().substring(0, this.getDocumentBase().getFile().lastIndexOf("/") + 1)).append(url)));
            try {
                this.getAppletContext().showDocument(new URL(url2));
            }
            catch (MalformedURLException ex2) {
                ex2.printStackTrace();
            }
        }
    }
    
    public String getAppletInfo() {
        return "Java Virtual Machine Detector";
    }
    
    public static void main(final String[] args) {
        final JVMDetector applet = new JVMDetector();
        applet.isStandalone = true;
        applet.init();
        applet.start();
    }
}
