import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.TextField;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.Container;

// 
// Decompiled by Procyon v0.5.30
// 

public class BMTTextField extends Container implements MouseListener, KeyListener, FocusListener
{
    private boolean active;
    private TextField tf;
    private final int width = 333;
    private final int height = 35;
    
    BMTTextField(final String s) {
        this.active = false;
        this.setLayout(null);
        (this.tf = new TextField(s)).setSize(333, 35);
        this.tf.setLocation(0, 0);
        this.tf.setVisible(false);
        this.add(this.tf);
        this.tf.addKeyListener(this);
        this.tf.addFocusListener(this);
        this.setSize(333, 35);
        this.addMouseListener(this);
    }
    
    public void setText(final String text) {
        this.tf.setText(text);
        this.repaint();
    }
    
    public String getText() {
        return this.tf.getText();
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, 332, 34);
        if (this.active) {
            graphics.setColor(new Color(255, 0, 0));
        }
        else {
            graphics.setColor(new Color(0, 0, 255));
        }
        graphics.drawRect(0, 0, 332, 34);
        final String text = this.tf.getText();
        graphics.setFont(new Font("Helvetiva", 1, 14));
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        graphics.drawString(text, (333 - fontMetrics.stringWidth(text)) / 2, (35 - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent());
        super.paint(graphics);
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.active = true;
        this.repaint();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.active = false;
        this.repaint();
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.tf.setVisible(true);
        this.tf.requestFocus();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 10) {
            this.tf.setVisible(false);
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void focusGained(final FocusEvent focusEvent) {
    }
    
    public void focusLost(final FocusEvent focusEvent) {
        this.tf.setVisible(false);
    }
}
