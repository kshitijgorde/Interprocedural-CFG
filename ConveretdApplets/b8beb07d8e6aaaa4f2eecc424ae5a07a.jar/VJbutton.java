import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class VJbutton extends Canvas implements MouseListener
{
    private boolean enabled;
    private int value;
    private boolean selected;
    private Image buffer;
    private Graphics gContext;
    private VJpoker details;
    
    public VJbutton(final VJpoker details, final int value, final boolean selected) {
        this.enabled = true;
        this.details = details;
        this.value = value;
        this.selected = selected;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (this.enabled) {
            if (this.selected) {
                this.selected = false;
            }
            else {
                this.selected = true;
            }
            if (this.selected) {
                this.details.bet_Value = this.value;
            }
            for (int i = 0; i < 3; ++i) {
                if (this.details.btn_Bet[i].getValue() != this.details.bet_Value) {
                    this.details.btn_Bet[i].setSelected(false);
                }
            }
            if (this.selected) {
                this.details.click.play();
            }
            this.details.lbl_Bet.setText(String.valueOf(this.details.bet_Value * this.details.bet_Column));
            this.details.lbl_Balance.setText(String.valueOf(this.details.balance - this.details.bet_Value * this.details.bet_Column));
            this.repaint();
        }
    }
    
    public void setSelected(final boolean selected) {
        this.selected = selected;
        this.repaint();
    }
    
    public void draw() {
        this.buffer = this.createImage(40, 30);
        (this.gContext = this.buffer.getGraphics()).setColor(Color.lightGray);
        this.gContext.fillRect(0, 0, this.getSize().width, this.getSize().height);
        if (!this.selected) {
            this.gContext.setColor(Color.white);
        }
        else {
            this.gContext.setColor(Color.darkGray);
        }
        this.gContext.drawLine(0, 0, 0, 30);
        this.gContext.drawLine(1, 1, 1, 29);
        this.gContext.drawLine(0, 0, 40, 0);
        this.gContext.drawLine(1, 1, 39, 1);
        if (!this.selected) {
            this.gContext.setColor(Color.darkGray);
        }
        else {
            this.gContext.setColor(Color.white);
        }
        this.gContext.drawLine(40, 30, 0, 30);
        this.gContext.drawLine(39, 29, 1, 29);
        this.gContext.drawLine(40, 30, 40, 0);
        this.gContext.drawLine(39, 29, 39, 1);
        final String string = "$" + String.valueOf(this.value);
        this.gContext.setFont(new Font("Helvetica", 1, 14));
        if (this.selected) {
            this.gContext.setColor(Color.white);
            this.gContext.drawString(string, 11, 21);
            this.gContext.setColor(Color.darkGray);
            this.gContext.drawString(string, 10, 20);
            return;
        }
        this.gContext.setColor(Color.black);
        this.gContext.drawString(string, 10, 20);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        this.draw();
        graphics.drawImage(this.buffer, 0, 0, this);
    }
}
