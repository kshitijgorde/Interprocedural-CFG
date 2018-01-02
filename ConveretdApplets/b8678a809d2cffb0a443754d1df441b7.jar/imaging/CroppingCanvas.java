// 
// Decompiled by Procyon v0.5.30
// 

package imaging;

import javax.swing.JOptionPane;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import imaging.filters.CropFilter;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.image.WritableRaster;
import java.awt.event.ActionEvent;
import java.util.Hashtable;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class CroppingCanvas extends JPanel implements ActionListener, MouseListener, MouseMotionListener
{
    private static final long serialVersionUID = -78977137226932602L;
    private static final String MESSG = "To accept this crop click OK, or Cancel to discard this and try again.";
    BufferedImage image;
    BufferedImage origImage;
    Dimension size;
    File file;
    Rectangle currentRect;
    Graphics offscreen;
    boolean showClip;
    int x;
    int y;
    int width;
    int height;
    
    public CroppingCanvas() {
        this.file = new File("D:/home5.jpg");
        this.x = 0;
        this.y = 0;
        this.width = 0;
        this.height = 0;
        this.setSize(924, 768);
        this.setBackground(Color.black);
        this.showClip = true;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    public void loadImage(final BufferedImage pImage) {
        this.origImage = pImage;
        this.image = new BufferedImage(this.origImage.getColorModel(), this.origImage.getRaster(), this.origImage.isAlphaPremultiplied(), null);
        this.setSize(this.size = new Dimension(this.origImage.getWidth(), this.origImage.getHeight()));
        this.repaint();
    }
    
    @Override
    public void actionPerformed(final ActionEvent ae) {
        this.repaintoffscreen();
    }
    
    public void repaintoffscreen() {
        this.image = new BufferedImage(this.origImage.getColorModel(), (WritableRaster)this.origImage.getData(), this.origImage.isAlphaPremultiplied(), null);
        this.offscreen = this.image.getGraphics();
        if (this.currentRect != null) {
            final Rectangle box = this.getDrawableRect(this.currentRect, this.size);
            this.offscreen.drawRect(box.x, box.y, box.width - 1, box.height - 1);
            this.offscreen.drawRect(this.currentRect.x, this.currentRect.y, this.currentRect.width - 1, this.currentRect.height - 1);
        }
        this.repaint();
    }
    
    @Override
    public void mousePressed(final MouseEvent me) {
        if (this.currentRect == null || (!this.hitsPoint(me.getX(), me.getY(), this.currentRect.x, this.currentRect.y) && !this.hitsPoint(me.getX(), me.getY(), this.currentRect.x, this.currentRect.y + this.currentRect.height - 1) && !this.hitsPoint(me.getX(), me.getY(), this.currentRect.x + this.currentRect.width - 1, this.currentRect.y) && !this.hitsPoint(me.getX(), me.getY(), this.currentRect.x + this.currentRect.width - 1, this.currentRect.y + this.currentRect.height - 1))) {
            this.currentRect = new Rectangle(me.getX(), me.getY(), 0, 0);
            this.repaintoffscreen();
        }
    }
    
    @Override
    public void mouseDragged(final MouseEvent me) {
        if (this.hitsPoint(me.getX(), me.getY(), this.currentRect.x, this.currentRect.y)) {
            this.width += this.currentRect.x - me.getX();
            this.height += this.currentRect.y - me.getY();
            this.currentRect.x = me.getX();
            this.currentRect.y = me.getY();
        }
        else if (this.hitsPoint(me.getX(), me.getY(), this.currentRect.x, this.currentRect.y + this.currentRect.height - 1)) {
            this.width += this.currentRect.x - me.getX();
            this.height = me.getY() - this.currentRect.y;
            this.currentRect.x = me.getX();
        }
        else if (this.hitsPoint(me.getX(), me.getY(), this.currentRect.x + this.currentRect.width - 1, this.currentRect.y)) {
            this.width = me.getX() - this.currentRect.x;
            this.height += this.currentRect.y - me.getY();
            this.currentRect.y = me.getY();
        }
        else if (this.hitsPoint(me.getX(), me.getY(), this.currentRect.x + this.currentRect.width - 1, this.currentRect.y + this.currentRect.height - 1)) {
            this.width = me.getX() - this.currentRect.x;
            this.height = me.getY() - this.currentRect.y;
        }
        else {
            this.width = me.getX() - this.currentRect.x;
            this.height = me.getY() - this.currentRect.y;
        }
        this.currentRect.setSize(this.width, this.height);
        this.repaintoffscreen();
    }
    
    @Override
    public void mouseReleased(final MouseEvent me) {
    }
    
    @Override
    public void mouseMoved(final MouseEvent me) {
        if (this.currentRect != null) {
            if (this.hitsPoint(me.getX(), me.getY(), this.currentRect.x, this.currentRect.y) || this.hitsPoint(me.getX(), me.getY(), this.currentRect.x + this.currentRect.width - 1, this.currentRect.y) || this.hitsPoint(me.getX(), me.getY(), this.currentRect.x, this.currentRect.y + this.currentRect.height - 1) || this.hitsPoint(me.getX(), me.getY(), this.currentRect.x + this.currentRect.width - 1, this.currentRect.y + this.currentRect.height - 1)) {
                this.setCursor(Cursor.getPredefinedCursor(13));
            }
            else {
                this.setCursor(Cursor.getPredefinedCursor(0));
            }
        }
    }
    
    @Override
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    @Override
    public void paint(final Graphics g) {
        if (this.image != null) {
            g.drawImage(this.image, 0, 0, this);
        }
        else {
            g.setColor(Color.gray);
            g.clearRect(0, 0, this.getWidth(), this.getHeight());
        }
    }
    
    protected boolean hitsPoint(final int pointX, final int pointY, final int x, final int y) {
        return Math.sqrt((pointX - x) * (pointX - x) + (pointY - y) * (pointY - y)) <= 5.0;
    }
    
    private Rectangle getDrawableRect(final Rectangle originalRect, final Dimension drawingArea) {
        this.x = originalRect.x;
        this.y = originalRect.y;
        this.width = originalRect.width;
        this.height = originalRect.height;
        if (this.width < 0) {
            this.width = 0 - this.width;
            this.x = this.x - this.width + 1;
            if (this.x < 0) {
                this.width += this.x;
                this.x = 0;
            }
        }
        if (this.height < 0) {
            this.height = 0 - this.height;
            this.y = this.y - this.height + 1;
            if (this.y < 0) {
                this.height += this.y;
                this.y = 0;
            }
        }
        if (this.x + this.width > drawingArea.width) {
            this.width = drawingArea.width - this.x;
        }
        if (this.y + this.height > drawingArea.height) {
            this.height = drawingArea.height - this.y;
        }
        return new Rectangle(this.x, this.y, this.width, this.height);
    }
    
    public JPanel getUIPanel() {
        final JCheckBox clipBox = new JCheckBox("show clip", this.showClip);
        clipBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                CroppingCanvas.this.showClip = clipBox.isSelected();
                CroppingCanvas.this.repaint();
            }
        });
        final JButton clip = new JButton("clip image");
        clip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                CroppingCanvas.this.clipImage();
            }
        });
        final JPanel panel = new JPanel();
        panel.add(clipBox);
        panel.add(clip);
        return panel;
    }
    
    public Dimension clipImage() {
        if (this.width <= 0 && this.height <= 0) {
            return null;
        }
        final BufferedImage temp = new CropFilter(this.x, this.y, this.width, this.height).filter(this.image, null);
        this.image = new BufferedImage(temp.getColorModel(), temp.getRaster(), temp.isAlphaPremultiplied(), null);
        final JLabel icon = new JLabel(new ImageIcon(this.image));
        final JLabel instruct = new JLabel("To accept this crop click OK, or Cancel to discard this and try again.");
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(icon, "North");
        panel.add(instruct, "South");
        final int opted = JOptionPane.showConfirmDialog(this, panel, null, 2, -1);
        if (opted == 0) {
            this.origImage = temp;
            this.setSize(this.width, this.height);
            final Dimension dimdim = new Dimension(this.width, this.height);
            this.width = 0;
            this.height = 0;
            return dimdim;
        }
        this.image = new BufferedImage(this.origImage.getColorModel(), this.origImage.getRaster(), this.origImage.isAlphaPremultiplied(), null);
        this.width = 0;
        this.height = 0;
        return null;
    }
    
    private void setClipedImageAsOriginalInage(final BufferedImage clipped) {
        this.origImage = clipped;
        this.image = clipped;
        this.size = new Dimension(this.origImage.getWidth(), this.origImage.getHeight());
        this.width = (int)this.size.getWidth();
        this.height = (int)this.size.getHeight();
        this.repaint();
    }
    
    @Override
    public void mouseEntered(final MouseEvent me) {
    }
    
    @Override
    public void mouseExited(final MouseEvent me) {
    }
    
    @Override
    public void mouseClicked(final MouseEvent me) {
    }
    
    public BufferedImage getImage() {
        return this.origImage;
    }
    
    public Dimension getDimension() {
        return null;
    }
}
