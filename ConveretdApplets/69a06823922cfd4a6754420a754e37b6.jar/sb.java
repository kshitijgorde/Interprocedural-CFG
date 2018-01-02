import java.io.IOException;
import java.util.Iterator;
import java.io.InputStream;
import java.awt.Toolkit;
import java.awt.Image;
import sun.applet.AppletAudioClip;
import java.net.URL;
import java.util.Enumeration;
import java.applet.Applet;
import java.util.Hashtable;
import java.applet.AudioClip;
import java.applet.AppletContext;

// 
// Decompiled by Procyon v0.5.30
// 

public class sb implements AppletContext, AudioClip
{
    protected static sb a;
    protected Hashtable b;
    private static final String[] z;
    
    protected sb() {
        this.b = new Hashtable();
    }
    
    public static synchronized sb a() {
        if (sb.a == null) {
            sb.a = new sb();
        }
        return sb.a;
    }
    
    public void a(final Applet applet, final String s) {
        this.b.put(s, applet);
    }
    
    public Applet getApplet(final String s) {
        return this.b.get(s);
    }
    
    public Enumeration getApplets() {
        return this.b.elements();
    }
    
    public AudioClip getAudioClip(final URL url) {
        try {
            return new AppletAudioClip(url);
        }
        catch (Exception ex) {
            return this;
        }
    }
    
    public Image getImage(final URL url) {
        return Toolkit.getDefaultToolkit().getImage(url);
    }
    
    public InputStream getStream(final String s) {
        return null;
    }
    
    public Iterator getStreamKeys() {
        return null;
    }
    
    public void setStream(final String s, final InputStream inputStream) throws IOException {
    }
    
    public void showDocument(final URL url) {
        System.out.println(sb.z[0] + url);
    }
    
    public void showDocument(final URL url, final String s) {
        System.out.println(sb.z[0] + url + sb.z[1] + s);
    }
    
    public void showStatus(final String s) {
        System.out.println(s);
    }
    
    public void play() {
    }
    
    public void loop() {
    }
    
    public void stop() {
    }
    
    static {
        final String[] z2 = new String[2];
        final int n = 0;
        final char[] charArray = "|4F;^Ou\\ \u001bX=G8\u001bO:K:VN;\\oTEo\b".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = '+';
                    break;
                }
                case 1: {
                    c2 = 'U';
                    break;
                }
                case 2: {
                    c2 = '(';
                    break;
                }
                case 3: {
                    c2 = 'O';
                    break;
                }
                default: {
                    c2 = ';';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z2[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "\u000b<Fo]Y4E*\u001b".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = '+';
                    break;
                }
                case 1: {
                    c4 = 'U';
                    break;
                }
                case 2: {
                    c4 = '(';
                    break;
                }
                case 3: {
                    c4 = 'O';
                    break;
                }
                default: {
                    c4 = ';';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z2[n4] = new String(charArray2).intern();
        z = z2;
    }
}
