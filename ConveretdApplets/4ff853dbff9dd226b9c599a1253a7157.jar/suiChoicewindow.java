import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.Point;
import java.awt.Frame;
import java.awt.Window;

// 
// Decompiled by Procyon v0.5.30
// 

class suiChoicewindow extends Window
{
    suiChoice sch;
    int width;
    int height;
    int iHeight;
    int show;
    
    suiChoicewindow(final Frame frame, final Point point, final int width, final int height, final int iHeight, final suiChoice sch) {
        super(frame);
        this.setBackground(Color.white);
        this.setFont(sch.getFont());
        this.addMouseListener(new MouseEventHandler());
        this.addMouseMotionListener(new MouseMotionEventHandler());
        this.setLocation(point.x, point.y);
        this.setSize(width, height);
        this.sch = sch;
        this.width = width;
        this.height = height;
        this.iHeight = iHeight;
        for (int i = 0; i < sch.count; ++i) {
            if (sch.citem[i].select) {
                this.show = i;
                break;
            }
        }
        this.show();
    }
    
    public void paint(final Graphics graphics) {
        final int height = graphics.getFontMetrics().getHeight();
        final int descent = graphics.getFontMetrics().getDescent();
        graphics.drawRect(0, 0, this.getSize().width - 1, this.getSize().height - 1);
        for (int i = 0; i < this.sch.count; ++i) {
            graphics.setColor(Color.black);
            if (i == this.show) {
                graphics.setColor(new Color(100, 100, 220));
                graphics.fillRect(0, i * this.iHeight, this.width - 1, this.iHeight - 1);
                graphics.setColor(Color.white);
            }
            if (this.sch.citem[i].image != null) {
                graphics.drawImage(this.sch.citem[i].image, 5, this.iHeight * i + this.iHeight / 2 - this.sch.citem[i].image.getHeight(this) / 2, this);
                graphics.drawString(this.sch.citem[i].title, this.sch.citem[i].image.getWidth(this) + 7, this.iHeight * i + this.iHeight / 2 + height / 2 - descent);
            }
            else {
                graphics.drawString(this.sch.citem[i].title, 5, this.iHeight * i + this.iHeight / 2 + height / 2 - descent);
            }
        }
    }
    
    class MouseEventHandler extends MouseAdapter
    {
        public void mousePressed(final MouseEvent mouseEvent) {
            for (int i = 0; i < suiChoicewindow.this.sch.count; ++i) {
                suiChoicewindow.this.sch.citem[i].select = false;
            }
            suiChoicewindow.this.sch.citem[suiChoicewindow.this.show].select = true;
            suiChoicewindow.this.sch.repaint();
            suiChoicewindow.this.repaint();
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            suiChoicewindow.this.dispose();
        }
    }
    
    class MouseMotionEventHandler extends MouseMotionAdapter
    {
        public void mouseMoved(final MouseEvent mouseEvent) {
            if (mouseEvent.getY() / suiChoicewindow.this.iHeight != suiChoicewindow.this.show) {
                suiChoicewindow.this.show = mouseEvent.getY() / suiChoicewindow.this.iHeight;
                suiChoicewindow.this.repaint();
            }
        }
    }
}
