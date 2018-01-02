import java.awt.event.MouseEvent;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class LMButton extends Canvas implements MouseListener
{
    private LoveMeter loveMeter;
    private Image bufferImage;
    private Graphics bufferGraphics;
    private Dimension bufferSize;
    private Image image;
    private Color[] colors;
    private Font font;
    private boolean keepDownState;
    private int actionIndex;
    private String label;
    private int mouseState;
    
    public LMButton(final LoveMeter loveMeter, final Image image, final Color[] colors, final String fontStyle, final boolean keepDownState, final int actionIndex, final String label, final boolean downState) {
        this.bufferSize = null;
        this.mouseState = 0;
        this.loveMeter = loveMeter;
        this.image = image;
        this.colors = colors;
        this.setBackground(colors[0]);
        this.font = new Font(fontStyle, 0, 14);
        this.keepDownState = keepDownState;
        this.actionIndex = actionIndex;
        this.label = label;
        if (keepDownState && downState) {
            this.mouseState = 3;
        }
        this.addMouseListener(this);
    }
    
    public void resetDownState() {
        this.mouseState = 0;
        this.repaint();
    }
    
    public void setLabel(final String label) {
        this.label = label;
        this.repaint();
    }
    
    private void confirmBuffer() {
        final Dimension size = this.getSize();
        if (this.bufferSize == null || !this.bufferSize.equals(size)) {
            this.bufferImage = this.createImage(size.width, size.height);
            (this.bufferGraphics = this.bufferImage.getGraphics()).setFont(this.font);
            this.bufferSize = new Dimension(size);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        this.confirmBuffer();
        final int tempMouseState = (this.mouseState == 3) ? 2 : this.mouseState;
        if (this.image == null) {
            this.bufferGraphics.setColor(this.colors[0]);
            this.bufferGraphics.fillRect(0, 0, this.bufferSize.width, this.bufferSize.height);
        }
        else if (this.bufferSize.width < 2 * this.bufferSize.height) {
            this.bufferGraphics.drawImage(this.image, 0, 0, this.bufferSize.width / 2, this.bufferSize.height, 0, tempMouseState * 20, 20, 20 + tempMouseState * 20, this);
            this.bufferGraphics.drawImage(this.image, this.bufferSize.width / 2, 0, this.bufferSize.width, this.bufferSize.height, 40, tempMouseState * 20, 60, 20 + tempMouseState * 20, this);
        }
        else {
            this.bufferGraphics.drawImage(this.image, 0, 0, this.bufferSize.height, this.bufferSize.height, 0, tempMouseState * 20, 20, 20 + tempMouseState * 20, this);
            this.bufferGraphics.drawImage(this.image, this.bufferSize.height, 0, this.bufferSize.width - this.bufferSize.height, this.bufferSize.height, 20, tempMouseState * 20, 40, 20 + tempMouseState * 20, this);
            this.bufferGraphics.drawImage(this.image, this.bufferSize.width - this.bufferSize.height, 0, this.bufferSize.width, this.bufferSize.height, 40, tempMouseState * 20, 60, 20 + tempMouseState * 20, this);
        }
        this.bufferGraphics.setColor(this.colors[tempMouseState + 1]);
        final FontMetrics fontMetrics = this.bufferGraphics.getFontMetrics();
        this.bufferGraphics.drawString(this.label, (this.bufferSize.width - fontMetrics.stringWidth(this.label)) / 2, 17 - fontMetrics.getDescent());
        graphics.drawImage(this.bufferImage, 0, 0, this);
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        if (this.mouseState < 3) {
            this.mouseState = 1;
            this.repaint();
        }
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        if (this.mouseState < 3) {
            this.mouseState = 0;
            this.repaint();
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (this.mouseState < 2) {
            this.mouseState = 2;
            this.repaint();
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (this.mouseState == 2) {
            this.loveMeter.actionPerformed(this.actionIndex);
            if (this.keepDownState) {
                this.mouseState = 3;
            }
            else {
                this.mouseState = 1;
            }
            this.repaint();
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
}
