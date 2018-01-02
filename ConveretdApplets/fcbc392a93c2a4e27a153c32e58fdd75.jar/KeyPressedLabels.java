import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;
import java.util.Vector;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class KeyPressedLabels extends Applet implements KeyListener, MouseListener
{
    private static final int FONT_SIZE = 14;
    private static final int FONT_STYLE = 0;
    private static final String FONT_NAME = "Courier";
    private int canvasHeight;
    private int canvasWidth;
    private int chWid;
    private Font font;
    private Vector labelList;
    private Graphics backgroundGraphics;
    private Image backgroundImage;
    
    public void init() {
        final Dimension size = this.getSize();
        this.canvasHeight = size.height;
        this.canvasWidth = size.width;
        (this.labelList = new Vector()).addElement(new Label(this.canvasWidth / 2, this.canvasHeight / 2));
        this.setBackground(Color.white);
        this.setForeground(Color.black);
        final Font font = new Font("Courier", 0, 14);
        this.setFont(font);
        this.chWid = this.getFontMetrics(font).charWidth('W');
        this.backgroundImage = this.createImage(this.canvasWidth, this.canvasHeight);
        this.backgroundGraphics = this.backgroundImage.getGraphics();
        this.addKeyListener(this);
        this.addMouseListener(this);
    }
    
    public void start() {
    }
    
    public void stop() {
    }
    
    public void paint(final Graphics graphics) {
        this.backgroundGraphics.setColor(Color.white);
        this.backgroundGraphics.fillRect(0, 0, this.canvasWidth, this.canvasHeight);
        this.backgroundGraphics.setColor(Color.black);
        this.backgroundGraphics.setFont(this.font);
        int x = 0;
        int y = 0;
        char[] chars = null;
        for (int size = this.labelList.size(), i = 0; i < size; ++i) {
            final Label label = this.labelList.elementAt(i);
            x = label.getX();
            y = label.getY();
            chars = label.getChars();
            if (chars != null) {
                this.backgroundGraphics.drawChars(chars, 0, chars.length, x, y);
            }
        }
        if (chars != null) {
            x += chars.length * this.chWid;
        }
        this.drawArrowhead(this.backgroundGraphics, x, y);
        graphics.drawImage(this.backgroundImage, 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        final char keyChar = keyEvent.getKeyChar();
        if (keyChar < ' ' || keyChar > '~') {
            return;
        }
        this.labelList.lastElement().appendChar(keyChar);
        this.repaint();
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.labelList.addElement(new Label(mouseEvent.getX(), mouseEvent.getY()));
        this.repaint();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    private void drawArrowhead(final Graphics graphics, final int n, final int n2) {
        graphics.drawLine(n, n2, n - 2, n2 + 6);
        graphics.drawLine(n, n2, n + 2, n2 + 6);
    }
}
