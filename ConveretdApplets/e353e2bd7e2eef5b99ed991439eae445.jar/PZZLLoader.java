import java.net.URLConnection;
import java.io.IOException;
import java.io.DataInputStream;
import java.net.MalformedURLException;
import java.awt.Component;
import java.net.URL;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.AppletStub;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class PZZLLoader extends Applet implements AppletStub, Runnable
{
    Class \u00f3;
    PZZLApplet \u00f4;
    int \u00f5;
    String \u00f6;
    PZZLLoaderThread \u00f8;
    Thread \u00f9;
    Image \u00fa;
    Graphics oS;
    String \u00fb;
    String \u00fc;
    String \u00fd;
    String \u00fe;
    String initialisationData;
    boolean \u00ff;
    boolean \u0100;
    boolean \u0101;
    
    public String getVersion() {
        return "3.0";
    }
    
    public void init() {
        this.setLayout(null);
        this.setBackground(Color.white);
        final URL documentBase = this.getDocumentBase();
        this.\u00fb = this.getParameter("ai");
        if (this.\u00fb != null) {
            this.\u00fb = \u00f3(this.\u00fb);
            this.\u00f6 = \u00f4(this.\u00fb);
            this.\u00fc = \u00f5(this.\u00fb);
            if (this.\u00fc.equals("nytia.pzzl.com")) {
                this.\u00fc = "nyt01.pzzl.com";
            }
            if (this.\u00fc.equals("nyti<.pzzl.com")) {
                this.\u00fc = "nyt02.pzzl.com";
            }
            this.\u00fd = \u00f6(this.\u00fb);
            this.\u00fe = \u00f8(this.\u00fb);
            this.\u0101 = true;
        }
        else {
            this.\u0101 = false;
        }
        if (this.\u0101) {
            this.\u00ff = true;
            this.\u00fa = this.createImage(this.getSize().width, this.getSize().height);
            this.oS = this.\u00fa.getGraphics();
            if (this.chk(this.\u00fc, this.\u00fd, this.\u00fe, this.\u00fb, documentBase)) {
                this.\u0100 = true;
                return;
            }
            this.\u0100 = false;
        }
    }
    
    public void start() {
        if (this.\u0101) {
            if (this.\u00ff) {
                this.\u00f8 = new PZZLLoaderThread("3.0", this.getGraphics(), this.oS, this.\u00fa, this.getSize().width, this.getSize().height, this.\u00f5, this);
                this.\u00f9 = new Thread(this);
                this.\u00f8.start();
                this.\u00f9.start();
                return;
            }
            this.\u00f4.start();
        }
    }
    
    public void run() {
        if (!this.\u0101) {
            System.out.println("ERROR = [init data not loaded]");
            this.\u00f8.setErrorMessage("Unable to start applet.", "Please report to support@pzzl.com");
            return;
        }
        if (!this.\u0100) {
            System.out.println("ERROR = [loading not allowed]]");
            this.\u00f8.setErrorMessage("The applet can not be loaded.", "Please report to support@pzzl.com");
            return;
        }
        try {
            this.\u00f3 = Class.forName(this.\u00f6);
            try {
                (this.\u00f4 = this.\u00f3.newInstance()).setStub(this);
                this.add(this.\u00f4);
                this.\u00f4.hide();
                this.\u00f4.setSize(this.getSize().width, this.getSize().height);
                this.\u00f4.setHost(this.\u00fc);
                this.\u00f4.setServletContext(this.\u00fd);
                this.\u00f4.setServlet(this.\u00fe);
                this.\u00f4.setInitialisationData(this.initialisationData);
                this.\u00f4.init();
                if (this.\u00f4.appletInitResult != 0) {
                    System.out.println("ERROR = [initialisation failed]");
                    this.\u00f8.setErrorMessage("An error has occurred.", "Please report to support@pzzl.com");
                    return;
                }
                this.\u00ff = false;
                this.\u00f8.stop();
                this.\u00f8 = null;
                this.\u00f4.show();
                this.\u00f4.repaint();
                this.\u00f4.start();
            }
            catch (InstantiationException ex) {
                System.out.println("ERROR = [" + ex.toString() + "]");
                this.\u00f8.setErrorMessage("An error has occured.", "Please report to support@pzzl.com");
            }
            catch (IllegalAccessException ex2) {
                System.out.println("ERROR = [" + ex2.toString() + "]");
                this.\u00f8.setErrorMessage("An error has occured.", "Please report to support@pzzl.com");
            }
            catch (Exception ex3) {
                System.out.println("ERROR = [" + ex3.toString() + "]");
                this.\u00f8.setErrorMessage("An error has occured.", "Please report to support@pzzl.com");
            }
        }
        catch (ClassNotFoundException ex4) {
            System.out.println("ERROR = [" + ex4.toString() + "]");
            this.\u00f8.setErrorMessage("The applet can not be found.", "Please report to support@pzzl.com");
        }
    }
    
    public void stop() {
        if (this.\u00f8 != null) {
            this.\u00f8.stop();
            this.\u00f8 = null;
        }
        if (this.\u00f9 != null) {
            this.\u00f9.stop();
            this.\u00f9 = null;
        }
        if (this.\u00f8 == null && this.\u00f4 != null && this.\u00f4.isVisible()) {
            this.\u00f4.stop();
        }
    }
    
    public void destroy() {
        if (this.\u00f8 == null && this.\u00f4 != null && this.\u00f4.isVisible()) {
            this.\u00f4.destroy();
        }
    }
    
    public void appletResize(final int n, final int n2) {
        this.resize(n, n2);
    }
    
    private static String \u00f3(final String s) {
        return \u00f9(s).replace('5', '.').replace('4', '>').replace('2', '<').replace('1', 'a').replace('3', 'e').replace('6', 'o').replace('0', 'i').replace('7', '/');
    }
    
    public static String getValue(final String s, final String s2, final String s3) {
        final int n = s.indexOf(s2) + 3;
        final int index = s.indexOf(s3);
        if (n > 0 && index > 0) {
            return s.substring(n, index);
        }
        return null;
    }
    
    private static String \u00f4(final String s) {
        return getValue(s, "<a>", "</a>");
    }
    
    private static String \u00f5(final String s) {
        return getValue(s, "<h>", "</h>");
    }
    
    private static String \u00f6(final String s) {
        return getValue(s, "<c>", "</c>");
    }
    
    private static String \u00f8(final String s) {
        return getValue(s, "<s>", "</s>");
    }
    
    private static String \u00f9(final String s) {
        String string = "";
        for (int i = 0; i < s.length(); ++i) {
            string = String.valueOf(string) + s.substring(s.length() - i - 1, s.length() - i);
        }
        return string;
    }
    
    public boolean chk(final String s, final String s2, final String s3, final String s4, final URL url) {
        return this.allowed(s, s2, s3, s4, url);
    }
    
    public boolean allowed(final String s, final String s2, final String s3, final String s4, final URL url) {
        final String string = "/" + s2 + "/" + s3 + "?pm=init&db=" + url + "&ai=" + s4;
        URL url2;
        try {
            url2 = new URL("http", s, 80, string);
        }
        catch (MalformedURLException ex2) {
            return false;
        }
        try {
            final URLConnection openConnection = url2.openConnection();
            openConnection.setUseCaches(false);
            final DataInputStream dataInputStream = new DataInputStream(openConnection.getInputStream());
            final String line = dataInputStream.readLine();
            dataInputStream.close();
            if (!this.setInitialisationData(line)) {
                return false;
            }
        }
        catch (IOException ex) {
            System.out.println("ERROR! Connecting to servlet [" + ex.toString() + "]");
            return false;
        }
        return true;
    }
    
    private boolean setInitialisationData(final String initialisationData) {
        this.initialisationData = initialisationData;
        return initialisationData.substring(0, 4).equals("<OK>");
    }
    
    public PZZLLoader() {
        this.\u00f5 = 100;
    }
}
