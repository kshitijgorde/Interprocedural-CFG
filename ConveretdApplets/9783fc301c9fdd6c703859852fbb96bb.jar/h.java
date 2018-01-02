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
    public String ba;
    public Image a9;
    
    public h() {
        this.ba = "IRClogo.gif";
        this.a9 = Toolkit.getDefaultToolkit().getImage(this.ba);
    }
    
    public h(final URL url) {
        this.ba = "IRClogo.gif";
        try {
            this.a9 = Toolkit.getDefaultToolkit().getImage(new URL(String.valueOf(url.toString()) + this.ba));
        }
        catch (MalformedURLException ex) {
            System.out.println("Unable to load logo image from this URL");
        }
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void paint(final Graphics graphics) {
        graphics.drawImage(this.a9, 0, 0, this);
    }
}
