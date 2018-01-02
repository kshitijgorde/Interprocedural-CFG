// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap;

import java.awt.event.MouseEvent;
import java.awt.event.FocusEvent;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.AWTEvent;
import java.awt.event.ActionEvent;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.awt.Canvas;

public class ImageButton extends Canvas implements MouseListener
{
    private static final long serialVersionUID = 5316652312929879055L;
    private Image normal;
    private Image pressed;
    private Image current;
    private boolean down;
    private boolean isswitch;
    private boolean nopush;
    private final int width;
    private final int height;
    private final String text;
    private Color borderColor;
    private ActionListener actionListener;
    
    public ImageButton(final Image image, final Image image2, final int width, final int n) {
        this.down = false;
        this.isswitch = false;
        this.nopush = false;
        this.width = width;
        this.height = 25;
        this.text = null;
        this.down = false;
        this.setSize(this.width, this.height);
        this.a(image, image2);
        this.addMouseListener(this);
    }
    
    public ImageButton(final int width, final int n, final String text) {
        this.down = false;
        this.isswitch = false;
        this.nopush = false;
        this.width = width;
        this.height = 25;
        this.down = false;
        this.normal = null;
        this.pressed = null;
        this.text = text;
        this.current = null;
        this.b();
        this.addMouseListener(this);
    }
    
    public final void a(final Color borderColor) {
        this.borderColor = borderColor;
    }
    
    public final void a(final Image image, final Image pressed) {
        this.normal = image;
        this.pressed = pressed;
        this.current = image;
        if (image != null) {
            this.prepareImage(image, this.width, this.height, this);
        }
        if (pressed != null) {
            this.prepareImage(pressed, this.width, this.height, this);
        }
        this.b();
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (n == 9 || n == 1005 || n == 1007) {
            this.transferFocus();
            return true;
        }
        return false;
    }
    
    public final void a(final ActionListener actionListener) {
        this.actionListener = actionListener;
    }
    
    private void a() {
        if (this.getParent() != null) {
            this.getParent().dispatchEvent(new ActionEvent(this, 0, ""));
        }
        if (this.actionListener != null) {
            this.actionListener.actionPerformed(new ActionEvent(this, 0, ""));
        }
    }
    
    private void b() {
        final Graphics graphics;
        if ((graphics = this.getGraphics()) != null) {
            this.paint(graphics);
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.current != null && (this.checkImage(this.current, this.width, this.height, this) & 0x23) != 0x0) {
            graphics.clearRect(0, 0, this.width, this.height);
            graphics.drawImage(this.current, 0, 0, this.width, this.height, this);
        }
        else if (this.text != null) {
            graphics.drawString(this.text, 4, 2 * this.height / 3);
        }
        final boolean b = this.nopush || !this.down;
        graphics.setColor(this.borderColor);
        graphics.draw3DRect(0, 0, this.width - 1, this.height - 1, b);
        graphics.setColor(this.borderColor);
        graphics.draw3DRect(1, 1, this.width - 3, this.height - 3, b);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(this.width, this.height);
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(this.width, this.height);
    }
    
    public void processFocusEvent(final FocusEvent focusEvent) {
        final int id;
        if ((id = focusEvent.getID()) == 1005) {
            this.b();
            return;
        }
        if (id == 1004) {
            this.b();
        }
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        int n6 = -1;
        if ((n & 0x30) != 0x0) {
            n6 = 0;
        }
        else if ((n & 0x8) != 0x0) {
            n6 = 100;
        }
        if (n6 >= 0) {
            this.repaint(n6, 0, 0, this.width, this.height);
        }
        return (n & 0xA0) == 0x0;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (!this.isswitch) {
            this.down = true;
            this.current = this.pressed;
            this.b();
            return;
        }
        if (this.down) {
            this.down = false;
            this.current = this.normal;
        }
        else {
            this.down = true;
            this.current = this.pressed;
        }
        this.b();
        this.a();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (this.down && !this.isswitch) {
            this.down = false;
            this.current = this.normal;
            this.b();
            this.a();
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
}
