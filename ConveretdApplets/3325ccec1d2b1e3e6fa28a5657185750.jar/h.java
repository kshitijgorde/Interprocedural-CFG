import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class h extends Canvas
{
    public String a6;
    public Image a5;
    
    public h() {
        this.a6 = "IRClogo.gif";
        this.a5 = Toolkit.getDefaultToolkit().getImage(this.a6);
    }
    
    public h(final URL url) {
        this.a6 = "IRClogo.gif";
        try {
            this.a5 = Toolkit.getDefaultToolkit().getImage(new URL(String.valueOf(url.toString()) + this.a6));
        }
        catch (MalformedURLException ex) {
            System.out.println("Unable to load logo image from this URL");
        }
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void paint(final Graphics graphics) {
        graphics.drawImage(this.a5, 0, 0, this);
    }
}
