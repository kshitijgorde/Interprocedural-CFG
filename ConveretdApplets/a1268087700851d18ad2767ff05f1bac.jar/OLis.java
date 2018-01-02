import java.awt.event.AdjustmentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class OLis implements ActionListener, MouseListener, MouseMotionListener, AdjustmentListener
{
    private OCAD apl;
    private OMaps maps;
    private OCanv canv;
    private OLinks links;
    private int startX;
    private int startY;
    private boolean pressed;
    Cursor defaultCursor;
    Cursor handCursor;
    
    public OLis() {
        this.defaultCursor = new Cursor(0);
        this.handCursor = new Cursor(12);
    }
    
    public OLis(final OCAD apl, final OMaps maps, final OCanv canv, final OLinks links) {
        this.defaultCursor = new Cursor(0);
        this.handCursor = new Cursor(12);
        this.apl = apl;
        this.maps = maps;
        this.canv = canv;
        this.links = links;
    }
    
    public void actionPerformed(final ActionEvent e) {
        if (this.canv.iMap > 0) {
            final OCanv canv = this.canv;
            --canv.iMap;
            if (this.canv.iMap == 0) {
                this.maps.x = this.maps.getMap(0).x;
                this.maps.y = this.maps.getMap(0).y;
            }
            this.canv.getMap(true);
            this.canv.repaint();
        }
    }
    
    public void mouseClicked(final MouseEvent e) {
    }
    
    public void mouseEntered(final MouseEvent e) {
    }
    
    public void mouseExited(final MouseEvent e) {
    }
    
    public void mouseReleased(final MouseEvent e) {
        if (this.pressed) {
            this.canv.drawRect();
            this.canv.rectWidth = 0;
            this.canv.rectHeight = 0;
            final int x = this.startX + e.getX() >> 1;
            final int y = this.startY + e.getY() >> 1;
            int w = e.getX() - this.startX;
            if (w < 0) {
                w = -w;
            }
            int h = e.getY() - this.startY;
            if (h < 0) {
                h = -h;
            }
            if (w < 4 && h < 4) {
                if (!this.links.showDoc(x, y)) {
                    this.maps.x = this.canv.screenToWorldX(x);
                    this.maps.y = this.canv.screenToWorldY(y);
                    if (this.canv.iMap < this.maps.mapCount() - 1) {
                        final OCanv canv = this.canv;
                        ++canv.iMap;
                    }
                }
            }
            else {
                this.maps.x = this.canv.screenToWorldX(x);
                this.maps.y = this.canv.screenToWorldY(y);
                if (this.canv.iMap < this.maps.mapCount() - 1) {
                    final int i0 = this.canv.iMap;
                    if (this.canv.iMap < this.maps.mapCount() - 1) {
                        final OCanv canv2 = this.canv;
                        ++canv2.iMap;
                    }
                    if (w > 3 || h > 3) {
                        for (int j = this.canv.iMap; j < this.maps.mapCount(); ++j) {
                            if (w < this.canv.width >> j - i0 && h < this.canv.height >> j - i0) {
                                this.canv.iMap = j;
                            }
                        }
                    }
                }
            }
            this.canv.getMap(true);
            this.canv.repaint();
        }
        this.pressed = false;
    }
    
    public void mousePressed(final MouseEvent e) {
        this.pressed = true;
        this.startX = e.getX();
        this.startY = e.getY();
        this.canv.rectX = e.getX();
        this.canv.rectY = e.getY();
        this.canv.rectWidth = 0;
        this.canv.rectHeight = 0;
        this.pressed = true;
    }
    
    public void mouseMoved(final MouseEvent e) {
        if (this.links.cursorHot(e.getX(), e.getY())) {
            this.canv.setCursor(this.handCursor);
        }
        else {
            this.canv.setCursor(this.defaultCursor);
        }
    }
    
    public void mouseDragged(final MouseEvent e) {
        if (this.pressed) {
            this.canv.drawRect();
            this.canv.rectWidth = e.getX() - this.startX;
            this.canv.rectHeight = e.getY() - this.startY;
            this.canv.drawRect();
        }
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent e) {
        if (e.getAdjustable().getOrientation() == 0) {
            this.canv.setScrollX(e.getValue());
        }
        else {
            this.canv.setScrollY(e.getValue());
        }
        this.canv.getMap(false);
        this.canv.repaint();
    }
}
