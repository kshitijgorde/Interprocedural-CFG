import java.net.URLConnection;
import java.io.IOException;
import java.io.DataInputStream;
import java.net.MalformedURLException;
import java.awt.Component;
import java.net.URL;
import java.awt.Color;
import java.awt.LayoutManager;
import java.util.Date;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.AppletStub;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class PZZLLoader extends Applet implements AppletStub, Runnable
{
    Class \u0170;
    PZZLApplet \u0171;
    int \u0172;
    String \u0173;
    PZZLLoaderThread \u0174;
    Thread \u0175;
    Image \u0176;
    Graphics oS;
    String \u0177;
    String \u0118;
    String \u0178;
    String \u0179;
    String initialisationData;
    boolean \u017a;
    boolean \u017b;
    boolean \u017c;
    Date \u017d;
    Date \u017e;
    
    public String getVersion() {
        return "3.0";
    }
    
    public void init() {
        this.\u017d = new Date();
        this.setLayout(null);
        this.setBackground(Color.white);
        final URL documentBase = this.getDocumentBase();
        this.\u0177 = this.getParameter("ai");
        if (this.\u0177 != null) {
            this.\u0177 = \u0170(this.\u0177);
            this.\u0173 = \u0171(this.\u0177);
            this.\u0118 = \u0172(this.\u0177);
            this.\u0178 = \u0173(this.\u0177);
            this.\u0179 = \u0174(this.\u0177);
            this.\u017c = true;
        }
        else {
            this.\u017c = false;
        }
        if (this.\u017c) {
            this.\u017a = true;
            this.\u0176 = this.createImage(this.getSize().width, this.getSize().height);
            this.oS = this.\u0176.getGraphics();
            if (this.chk(this.\u0118, this.\u0178, this.\u0179, this.\u0177, documentBase)) {
                this.\u017b = true;
                return;
            }
            this.\u017b = false;
        }
    }
    
    public void start() {
        if (this.\u017c) {
            if (this.\u017a) {
                this.\u0174 = new PZZLLoaderThread("3.0", this.getGraphics(), this.oS, this.\u0176, this.getSize().width, this.getSize().height, this.\u0172, this);
                this.\u0175 = new Thread(this);
                this.\u0174.start();
                this.\u0175.start();
                return;
            }
            this.\u0171.start();
        }
    }
    
    public void run() {
        if (!this.\u017c) {
            System.out.println("ERROR = [init data not loaded]");
            this.\u0174.setErrorMessage("Unable to start applet.", "Please report to support@pzzl.com");
            return;
        }
        if (!this.\u017b) {
            System.out.println("ERROR = [loading not allowed]]");
            this.\u0174.setErrorMessage("The applet can not be loaded.", "Please report to support@pzzl.com");
            return;
        }
        try {
            this.\u0170 = Class.forName(this.\u0173);
            try {
                (this.\u0171 = this.\u0170.newInstance()).setStub(this);
                this.add(this.\u0171);
                this.\u0171.hide();
                this.\u0171.setSize(this.getSize().width, this.getSize().height);
                this.\u0171.setHost(this.\u0118);
                this.\u0171.setServletContext(this.\u0178);
                this.\u0171.setServlet(this.\u0179);
                this.\u0171.setInitialisationData(this.initialisationData);
                this.\u0171.init();
                if (this.\u0171.appletInitResult != 0) {
                    System.out.println("ERROR = [initialisation failed]");
                    this.\u0174.setErrorMessage("An error has occurred.", "Please report to support@pzzl.com");
                    return;
                }
                this.\u017a = false;
                this.\u0174.stop();
                this.\u0174 = null;
                this.\u0171.show();
                this.\u0171.start();
            }
            catch (InstantiationException ex) {
                System.out.println("ERROR = [" + ex.toString() + "]");
                this.\u0174.setErrorMessage("An error has occured.", "Please report to support@pzzl.com");
            }
            catch (IllegalAccessException ex2) {
                System.out.println("ERROR = [" + ex2.toString() + "]");
                this.\u0174.setErrorMessage("An error has occured.", "Please report to support@pzzl.com");
            }
            catch (Exception ex3) {
                System.out.println("ERROR = [" + ex3.toString() + "]");
                this.\u0174.setErrorMessage("An error has occured.", "Please report to support@pzzl.com");
            }
        }
        catch (ClassNotFoundException ex4) {
            System.out.println("ERROR = [" + ex4.toString() + "]");
            this.\u0174.setErrorMessage("The applet can not be found.", "Please report to support@pzzl.com");
        }
        this.\u017e = new Date();
        System.out.println("LT=[" + (this.\u017e.getTime() - this.\u017d.getTime()) + "]");
    }
    
    public void stop() {
        if (this.\u0174 != null) {
            this.\u0174.stop();
            this.\u0174 = null;
        }
        if (this.\u0175 != null) {
            this.\u0175.stop();
            this.\u0175 = null;
        }
        if (this.\u0174 == null && this.\u0171 != null && this.\u0171.isVisible()) {
            this.\u0171.stop();
        }
    }
    
    public void destroy() {
        if (this.\u0174 == null && this.\u0171 != null && this.\u0171.isVisible()) {
            this.\u0171.destroy();
        }
    }
    
    public void appletResize(final int n, final int n2) {
        this.resize(n, n2);
    }
    
    private static String \u0170(final String s) {
        return \u0175(s).replace('5', '.').replace('4', '>').replace('2', '<').replace('1', 'a').replace('3', 'e').replace('6', 'o').replace('0', 'i').replace('7', '/');
    }
    
    public static String getValue(final String s, final String s2, final String s3) {
        final int n = s.indexOf(s2) + 3;
        final int index = s.indexOf(s3);
        if (n > 0 && index > 0) {
            return s.substring(n, index);
        }
        return null;
    }
    
    private static String \u0171(final String s) {
        return getValue(s, "<a>", "</a>");
    }
    
    private static String \u0172(final String s) {
        String value = getValue(s, "<h>", "</h>");
        if (!value.equals("localhost")) {
            value = "69.89.15.125";
        }
        return value;
    }
    
    private static String \u0173(final String s) {
        return getValue(s, "<c>", "</c>");
    }
    
    private static String \u0174(final String s) {
        return getValue(s, "<s>", "</s>");
    }
    
    private static String \u0175(final String s) {
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
        this.\u0172 = 100;
    }
}
