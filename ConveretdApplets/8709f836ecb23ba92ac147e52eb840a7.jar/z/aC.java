// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.util.EventObject;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.image.ImageObserver;
import java.awt.image.BufferedImage;
import java.awt.Cursor;
import java.awt.event.MouseListener;
import javax.swing.JLabel;

public final class aC extends JLabel implements MouseListener
{
    private I a;
    private String b;
    
    public aC(final bg bg) {
        if (!bg.b().equals("gbutton")) {
            throw new RuntimeException("Invalid config");
        }
        this.setCursor(Cursor.getPredefinedCursor(12));
        this.addMouseListener(this);
        this.setOpaque(false);
        this.b = bg.c("action");
        final String c = bg.c("style");
        String s;
        if ((s = bg.c("caption")).charAt(0) == '$') {
            s = G.c(s.substring(1));
        }
        final ImageIcon a;
        final int iconWidth = (a = V.a(bg.i("image"))).getIconWidth();
        final int iconHeight = a.getIconHeight();
        final Image image;
        final Graphics graphics;
        (graphics = ((BufferedImage)(image = new BufferedImage(iconWidth, iconHeight, 2))).getGraphics()).drawImage(a.getImage(), 0, 0, null);
        graphics.setFont(al.a(c));
        graphics.setColor(al.a(c, "color"));
        final Rectangle2D stringBounds;
        final int height = (stringBounds = graphics.getFontMetrics().getStringBounds(s, graphics)).getBounds().height;
        final int width;
        int n;
        int b;
        if ((width = stringBounds.getBounds().width) > iconWidth || height > iconHeight) {
            System.out.println("Caption too big for button: " + s);
            n = 0;
            b = 0;
        }
        else {
            n = iconWidth / 2 - width / 2;
            b = bg.b("textY");
        }
        graphics.drawString(s, n, b);
        graphics.dispose();
        this.setIcon(new ImageIcon(image));
    }
    
    public final void a(final I a) {
        this.a = a;
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
        if (this.a != null) {
            this.a.a(new T(this, this.b));
        }
    }
}
