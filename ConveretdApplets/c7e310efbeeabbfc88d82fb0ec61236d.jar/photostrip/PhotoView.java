// 
// Decompiled by Procyon v0.5.30
// 

package photostrip;

import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Color;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import javax.swing.event.MouseInputListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.URL;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;

public class PhotoView extends JComponent
{
    private int size;
    private PhotoResource photo;
    private boolean highlight;
    
    public PhotoView(final PhotoResource photo, final int size, final URLShower shower) {
        this.highlight = false;
        this.size = size;
        (this.photo = photo).setCallback(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.out.println("done loading: " + e.paramString() + " = " + photo.getImage());
                PhotoView.this.repaint();
            }
        });
        photo.startLoading();
        final MouseInputListener mouse = new MouseInputAdapter() {
            public void mouseClicked(final MouseEvent arg0) {
                try {
                    shower.showDocument(new URL(photo.getPageURL()));
                }
                catch (MalformedURLException ex) {
                    Logger.getLogger(PhotoView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            public void mouseEntered(final MouseEvent arg0) {
                PhotoView.this.highlight = true;
                PhotoView.this.repaint();
            }
            
            public void mouseExited(final MouseEvent arg0) {
                PhotoView.this.highlight = false;
                PhotoView.this.repaint();
            }
        };
        this.addMouseListener(mouse);
        this.addMouseMotionListener(mouse);
    }
    
    protected void paintComponent(final Graphics gfx) {
        super.paintComponent(gfx);
        final Graphics2D g = (Graphics2D)gfx;
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        if (this.photo.isLoaded()) {
            final BufferedImage img = this.photo.getImage();
            int sx = 0;
            int sy = 0;
            int sw = img.getWidth();
            int sh = img.getHeight();
            if (img.getWidth() > img.getHeight()) {
                int off = img.getWidth() - img.getHeight();
                off /= 2;
                sx += off;
                sw -= off;
            }
            if (img.getWidth() < img.getHeight()) {
                int off = img.getHeight() - img.getWidth();
                off /= 2;
                sy += off;
                sh -= off;
            }
            g.drawImage(img, 0, 0, this.size, this.size, sx, sy, sw, sh, null);
        }
        if (this.highlight) {
            g.setColor(new Color(255, 255, 255, 100));
            g.fillRect(1, 1, this.size - 2, this.size - 2);
        }
        g.setColor(Color.GRAY);
        g.drawRect(0, 0, this.size - 1, this.size - 1);
    }
}
