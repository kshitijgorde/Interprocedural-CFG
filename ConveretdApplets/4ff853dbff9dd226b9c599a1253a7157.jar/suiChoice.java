import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Point;
import java.awt.Component;
import java.awt.event.WindowListener;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.Window;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class suiChoice extends Canvas
{
    int itemN;
    suiChoiceitem[] citem;
    int width;
    int height;
    int count;
    boolean press;
    protected transient Window window;
    
    suiChoice(final int itemN, final int width, final int height) {
        this.press = false;
        this.itemN = itemN;
        this.citem = new suiChoiceitem[itemN];
        this.addMouseListener(new MouseEventHandler());
        this.width = width;
        this.height = height;
        this.setBackground(Color.white);
    }
    
    public void addItem(final String s) {
        this.citem[this.count] = new suiChoiceitem(s);
        if (this.count == 0) {
            this.citem[this.count].select = true;
        }
        ++this.count;
    }
    
    public void addItem(final String s, final Image image) {
        this.citem[this.count] = new suiChoiceitem(s, image);
        if (this.count == 0) {
            this.citem[this.count].select = true;
        }
        ++this.count;
    }
    
    public int countItems() {
        return this.count;
    }
    
    public int getSelectedItem() {
        for (int i = 0; i < this.count; ++i) {
            if (this.citem[i].select) {
                return i;
            }
        }
        return -1;
    }
    
    public void select(final int n) {
        for (int i = 0; i < this.count; ++i) {
            this.citem[i].select = false;
        }
        this.citem[n].select = true;
    }
    
    public Dimension minimizeSize() {
        return new Dimension(this.width, this.height);
    }
    
    public Dimension preferredSize() {
        return new Dimension(this.width, this.height);
    }
    
    public void paint(final Graphics graphics) {
        final int height = graphics.getFontMetrics().getHeight();
        final int descent = graphics.getFontMetrics().getDescent();
        if (!this.press) {
            graphics.setColor(new Color(120, 120, 120));
            graphics.drawLine(0, 0, this.width - 1, 0);
            graphics.drawLine(0, 0, 0, this.height - 1);
            graphics.drawLine(this.width - 4, 3, this.width - 4, this.height - 4);
            graphics.drawLine(this.width - 4, this.height - 4, this.width - 4 - this.height + 6, this.height - 4);
            graphics.setColor(new Color(220, 220, 220));
            graphics.drawLine(0, this.height - 1, this.width - 1, this.height - 1);
            graphics.drawLine(this.width - 1, 0, this.width - 1, this.height - 1);
            graphics.setColor(new Color(60, 60, 60));
            graphics.drawLine(1, 1, this.width - 2, 1);
            graphics.drawLine(1, 1, 1, this.height - 2);
            graphics.drawLine(this.width - 3, 2, this.width - 3, this.height - 3);
            graphics.drawLine(this.width - 3, this.height - 3, this.width - 3 - this.height + 4, this.height - 3);
            graphics.setColor(new Color(192, 192, 192));
            graphics.drawLine(1, this.height - 2, this.width - 2, this.height - 2);
            graphics.drawLine(this.width - 2, 1, this.width - 2, this.height - 2);
            graphics.drawLine(this.width - 3, 2, this.width - 3 - this.height + 4, 2);
            graphics.drawLine(this.width - 3 - this.height + 4, 2, this.width - 3 - this.height + 4, this.height - 3);
            graphics.fillRect(this.width - 5 - this.height + 8, 4, this.height - 7, this.height - 7);
            graphics.setColor(Color.black);
            graphics.fillPolygon(new int[] { this.width - 5 - this.height / 2 + 4, this.width - 5 - this.height / 2 + 8, this.width - 5 - this.height / 2 }, new int[] { this.height / 2 + 2, this.height / 2 - 2, this.height / 2 - 2 }, 3);
        }
        else {
            graphics.setColor(new Color(120, 120, 120));
            graphics.drawLine(0, 0, this.width - 1, 0);
            graphics.drawLine(0, 0, 0, this.height - 1);
            graphics.setColor(new Color(220, 220, 220));
            graphics.drawLine(0, this.height - 1, this.width - 1, this.height - 1);
            graphics.drawLine(this.width - 1, 0, this.width - 1, this.height - 1);
            graphics.drawLine(this.width - 4, 3, this.width - 4, this.height - 4);
            graphics.drawLine(this.width - 4, this.height - 4, this.width - 4 - this.height + 6, this.height - 4);
            graphics.setColor(new Color(60, 60, 60));
            graphics.drawLine(1, 1, this.width - 2, 1);
            graphics.drawLine(1, 1, 1, this.height - 2);
            graphics.drawLine(this.width - 3, 2, this.width - 3 - this.height + 4, 2);
            graphics.drawLine(this.width - 3 - this.height + 4, 2, this.width - 3 - this.height + 4, this.height - 3);
            graphics.setColor(new Color(192, 192, 192));
            graphics.drawLine(1, this.height - 2, this.width - 2, this.height - 2);
            graphics.drawLine(this.width - 2, 1, this.width - 2, this.height - 2);
            graphics.drawLine(this.width - 3, 2, this.width - 3, this.height - 3);
            graphics.drawLine(this.width - 3, this.height - 3, this.width - 3 - this.height + 4, this.height - 3);
            graphics.fillRect(this.width - 5 - this.height + 7, 3, this.height - 6, this.height - 7);
            graphics.setColor(Color.black);
            graphics.fillPolygon(new int[] { this.width - 5 - this.height / 2 + 4, this.width - 5 - this.height / 2 + 8, this.width - 5 - this.height / 2 }, new int[] { this.height / 2 + 2, this.height / 2 - 2, this.height / 2 - 2 }, 3);
        }
        int i = 0;
        while (i < this.count) {
            if (this.citem[i].select) {
                if (this.citem[i].image != null) {
                    graphics.drawImage(this.citem[i].image, 5, this.height / 2 - this.citem[i].image.getHeight(this) / 2, this);
                    graphics.drawString(this.citem[i].title, this.citem[i].image.getWidth(this) + 7, this.height / 2 + height / 2 - descent);
                    return;
                }
                graphics.drawString(this.citem[i].title, 5, this.height / 2 + height / 2 - descent);
            }
            else {
                ++i;
            }
        }
    }
    
    class MouseEventHandler extends MouseAdapter
    {
        public void mousePressed(final MouseEvent mouseEvent) {
            Component component;
            for (component = suiChoice.this; !(component instanceof Frame); component = component.getParent()) {}
            suiChoice.this.press = true;
            suiChoice.this.repaint();
            ((Frame)component).addWindowListener(new WindowEventHandler());
            final Point locationOnScreen = suiChoice.this.getLocationOnScreen();
            locationOnScreen.y += suiChoice.this.height;
            if (suiChoice.this.window == null) {
                suiChoice.this.window = new suiChoicewindow((Frame)component, locationOnScreen, suiChoice.this.width, suiChoice.this.height * suiChoice.this.count, suiChoice.this.height, suiChoice.this);
                return;
            }
            if (!suiChoice.this.window.isShowing()) {
                suiChoice.this.window = new suiChoicewindow((Frame)component, locationOnScreen, suiChoice.this.width, suiChoice.this.height * suiChoice.this.count, suiChoice.this.height, suiChoice.this);
                return;
            }
            suiChoice.this.window.dispose();
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            suiChoice.this.press = false;
            suiChoice.this.repaint();
        }
        
        class WindowEventHandler extends WindowAdapter
        {
            public void windowClosed(final WindowEvent windowEvent) {
                suiChoice.this.window.dispose();
            }
            
            public void windowIconified(final WindowEvent windowEvent) {
                suiChoice.this.window.dispose();
            }
        }
    }
}
