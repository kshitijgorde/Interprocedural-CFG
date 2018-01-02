import java.awt.Graphics;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Window;

// 
// Decompiled by Procyon v0.5.30
// 

public class dg extends Window implements MouseListener
{
    private dh[] a;
    private av b;
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        for (int i = 0; i < this.a.length; ++i) {
            if (this.a[i].a(mouseEvent.getX(), mouseEvent.getY())) {
                p.a(this.b, this.a[i].e);
                break;
            }
        }
        this.dispose();
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public dg(final Frame frame, final av b) {
        super(frame);
        this.a = new dh[16];
        this.b = b;
        this.setSize(60, 160);
        this.addMouseListener(this);
        int n = 0;
        for (int i = 0; i < this.a.length; ++i) {
            this.a[i] = new dh(this.getSize().width / 2, this.getSize().height / 8);
            if ((i & 0x1) == 0x0) {
                this.a[i].a = 0;
                this.a[i].b = n;
            }
            else {
                this.a[i].a = this.a[i].c;
                this.a[i].b = n;
                n += this.a[i].d;
            }
        }
        this.a[0].e = Color.decode("#FF9999");
        this.a[1].e = Color.decode("#FF9966");
        this.a[2].e = Color.decode("#FFFF66");
        this.a[3].e = Color.decode("#99FF99");
        this.a[4].e = Color.decode("#99CCFF");
        this.a[5].e = Color.decode("#CC99FF");
        this.a[6].e = Color.decode("#99FFF2");
        this.a[7].e = Color.decode("#FA6EC0");
        this.a[8].e = Color.decode("#91ACEA");
        this.a[9].e = Color.decode("#938B67");
        this.a[10].e = Color.decode("#52FD07");
        this.a[11].e = Color.decode("#34BC9A");
        this.a[12].e = Color.decode("#9A1CC4");
        this.a[13].e = Color.decode("#F52020");
        this.a[14].e = Color.decode("#CCF606");
        this.a[15].e = Color.decode("#84A3A4");
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        for (int i = 0; i < this.a.length; ++i) {
            this.a[i].a(graphics);
        }
        graphics.setColor(Color.black);
        graphics.drawRect(0, 0, this.getSize().width - 1, this.getSize().height - 1);
        super.paint(graphics);
    }
}
