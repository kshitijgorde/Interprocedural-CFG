import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class CardImage extends Canvas implements MouseListener, Runnable
{
    private VJpoker details;
    private int rnum;
    private Thread Card;
    public boolean hold;
    private boolean pressed;
    private int[] picker;
    private Image buffer;
    private Graphics gContext;
    
    public CardImage(final VJpoker details) {
        this.hold = false;
        this.pressed = false;
        this.details = details;
        this.picker = new int[10];
        this.setForeground(Color.white);
        this.setBackground(Color.white);
        this.buffer = this.details.Card_img[52];
    }
    
    public void setHold(final boolean hold) {
        this.hold = hold;
    }
    
    public boolean getHold() {
        return this.hold;
    }
    
    public void stop() {
        if (this.Card != null) {
            this.Card = null;
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (this.details.draw_Num > 1 && this.details.draw_Num < 3) {
            this.details.click.play();
            this.pressed = true;
            this.buffer = this.createImage(71, 96);
            this.gContext = this.buffer.getGraphics();
            if (!this.hold) {
                this.hold = true;
            }
            else {
                this.hold = false;
            }
            this.gContext.drawImage(this.details.Card_img[this.rnum], 0, 0, this);
            this.repaint();
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (this.details.draw_Num > 1 && this.details.draw_Num < 4) {
            this.pressed = false;
            this.repaint();
        }
    }
    
    public void setCard(final int rnum) {
        this.details.btn_Deal.setEnabled(false);
        if (this.Card == null || !this.Card.isAlive()) {
            this.rnum = rnum;
            (this.Card = new Thread(this)).start();
            return;
        }
        this.gContext.drawImage(this.details.Card_img[this.rnum], 0, 0, this);
        this.repaint();
    }
    
    public void run() {
        this.buffer = this.createImage(71, 96);
        this.gContext = this.buffer.getGraphics();
        for (int i = 0; i < this.picker.length; ++i) {
            this.picker[i] = (int)(Math.random() * 52.0);
        }
        this.gContext.drawImage(this.details.Card_img[this.picker[0]], 0, 0, this);
        for (int j = 1; j < this.picker.length; ++j) {
            this.repaint();
            try {
                Thread.sleep(30L);
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            this.gContext.drawImage(this.details.Card_img[this.picker[j]], 0, 0, this);
        }
        try {
            Thread.sleep(30L);
        }
        catch (InterruptedException ex2) {
            ex2.printStackTrace();
        }
        this.gContext.drawImage(this.details.Card_img[this.rnum], 0, 0, this);
        this.buffer = this.details.Card_img[this.rnum];
        this.repaint();
        this.gContext = null;
        if (this.Card != null) {
            this.Card = null;
        }
        this.details.btn_Deal.setEnabled(true);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (!this.pressed) {
            graphics.setColor(Color.lightGray);
        }
        else {
            graphics.setColor(Color.darkGray);
        }
        graphics.drawLine(0, 0, 0, 98);
        graphics.drawLine(1, 1, 1, 97);
        graphics.drawLine(0, 0, 73, 0);
        graphics.drawLine(1, 1, 72, 1);
        if (!this.pressed) {
            graphics.setColor(Color.darkGray);
        }
        else {
            graphics.setColor(Color.lightGray);
        }
        graphics.drawLine(74, 99, 0, 99);
        graphics.drawLine(73, 98, 1, 98);
        graphics.drawLine(74, 99, 74, 0);
        graphics.drawLine(73, 98, 73, 1);
        graphics.drawImage(this.buffer, 2, 2, this);
        if (this.hold) {
            final Font font = new Font("Helvetica", 1, 20);
            graphics.setColor(Color.blue);
            graphics.setFont(font);
            graphics.drawString("HOLD", 10, 55);
        }
    }
}
