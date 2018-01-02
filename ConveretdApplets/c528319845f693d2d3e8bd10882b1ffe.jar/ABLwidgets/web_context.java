// 
// Decompiled by Procyon v0.5.30
// 

package ABLwidgets;

import java.awt.Image;
import java.awt.AWTError;
import java.net.MalformedURLException;
import java.awt.Toolkit;
import java.net.URL;
import java.applet.AppletContext;
import java.applet.Applet;

public class web_context
{
    private Applet a;
    private AppletContext b;
    private URL c;
    private Toolkit d;
    
    public web_context(final Applet a) {
        this.a = a;
        this.b = a.getAppletContext();
    }
    
    public web_context(final String s) {
        try {
            this.c = new URL(s);
        }
        catch (MalformedURLException ex) {
            throw new RuntimeException("Malformed URL: " + s);
        }
        try {
            this.d = Toolkit.getDefaultToolkit();
        }
        catch (AWTError awtError) {
            throw new RuntimeException("No toolkit: " + awtError);
        }
    }
    
    public boolean a() {
        return this.a != null;
    }
    
    public URL b() {
        if (this.a != null) {
            return this.a.getCodeBase();
        }
        return this.c;
    }
    
    public URL c() {
        if (this.a != null) {
            return this.a.getDocumentBase();
        }
        return this.c;
    }
    
    public Image a(final URL url) {
        if (this.a != null) {
            return this.a.getImage(url);
        }
        URL url2;
        try {
            url2 = new URL(this.c, url.toString());
        }
        catch (MalformedURLException ex) {
            throw new RuntimeException("Malformed image URL: " + url + " relative to: " + this.c);
        }
        return this.d.getImage(url2);
    }
    
    public Image a(final URL url, final String s) {
        if (this.a != null) {
            return this.a.getImage(url, s);
        }
        URL url2;
        try {
            url2 = new URL(this.c, url.toString());
        }
        catch (MalformedURLException ex) {
            throw new RuntimeException("Malformed image URL: " + url + " relative to: " + this.c);
        }
        try {
            url2 = new URL(url2, s);
        }
        catch (MalformedURLException ex2) {
            throw new RuntimeException("Malformed image name: " + s + " relative to: " + url2);
        }
        return this.d.getImage(url2);
    }
    
    public void b(final URL url, final String s) {
        if (this.b != null) {
            this.b.showDocument(url, s);
            return;
        }
        throw new RuntimeException("showDocument requested when not in applet");
    }
}
