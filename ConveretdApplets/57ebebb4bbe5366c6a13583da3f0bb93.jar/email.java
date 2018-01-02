import java.awt.Graphics;
import java.net.MalformedURLException;
import java.util.StringTokenizer;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class email extends Applet implements Runnable
{
    boolean exists;
    Thread th;
    String p;
    String refPage;
    
    public email() {
        this.exists = true;
        this.th = null;
        this.p = null;
        this.refPage = null;
    }
    
    public void init() {
        this.setBackground(Color.black);
        this.p = this.getParameter("bgcolor");
        if (this.p != null) {
            try {
                this.setBackground(new Color(Integer.parseInt(this.p.substring(1, this.p.length()), 16)));
            }
            catch (Exception ex) {}
        }
        this.p = this.getParameter("key");
        this.refPage = this.getParameter("page");
    }
    
    public void run() {
        if (this.p != null) {
            this.sendEmail(this.p, this.refPage);
        }
    }
    
    public void sendEmail(final String s, String encode) {
        if (encode != null) {
            if (encode.length() > 0) {
                encode = URLEncoder.encode("(" + encode + ")");
            }
        }
        URL url;
        try {
            url = new URL(this.getCodeBase(), "xmbvdqbsjlmqpdr.txt");
        }
        catch (Exception ex) {
            this.showStatus("Error - Invalid email database file URL");
            return;
        }
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
        }
        catch (Exception ex2) {
            return;
        }
        try {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final StringTokenizer stringTokenizer = new StringTokenizer(line, "|");
                if (stringTokenizer.hasMoreTokens() && stringTokenizer.nextToken().equals(s) && stringTokenizer.hasMoreTokens()) {
                    try {
                        this.getAppletContext().showDocument(new URL("mailto:" + stringTokenizer.nextToken().trim()));
                    }
                    catch (MalformedURLException ex3) {
                        this.showStatus("Error - Invalid email address given");
                    }
                }
            }
        }
        catch (Exception ex4) {
            this.showStatus("Error reading email database file");
        }
    }
    
    public void start() {
        if (this.th == null && this.p != null) {
            (this.th = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.th != null) {
            this.th = null;
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
