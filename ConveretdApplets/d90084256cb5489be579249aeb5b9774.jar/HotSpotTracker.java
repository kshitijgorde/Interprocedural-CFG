import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Image;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class HotSpotTracker
{
    int m_x;
    int m_y;
    boolean m_mouseDown;
    HotSpotInfo m_hotSpot;
    Vector m_hotSpots;
    HotSpotSink m_hotSpotSink;
    
    HotSpotTracker(final HotSpotSink hotSpotSink) {
        this.m_hotSpotSink = hotSpotSink;
        this.m_hotSpots = new Vector();
    }
    
    final void addHotSpot(final HotSpotInfo hotSpotInfo) {
        this.m_hotSpots.addElement(hotSpotInfo);
    }
    
    final void mouseEnter(final int n, final int n2) {
        this.overHotSpot(this.mapHotSpot(n, n2));
    }
    
    final void mouseExit(final int n, final int n2) {
        this.overHotSpot(null);
    }
    
    final void mouseMove(final int n, final int n2) {
        this.overHotSpot(this.mapHotSpot(n, n2));
    }
    
    final void mouseDrag(final int n, final int n2) {
        this.overHotSpot(this.mapHotSpot(n, n2));
    }
    
    final void mouseDown(final int n, final int n2) {
        this.m_mouseDown = true;
        this.m_hotSpotSink.repaint();
    }
    
    final void mouseUp(final int n, final int n2) {
        this.m_mouseDown = false;
        this.m_hotSpotSink.repaint();
        if (this.mapHotSpot(n, n2) != this.m_hotSpot) {
            return;
        }
        if (this.m_hotSpot != null) {
            this.m_hotSpotSink.clickHotSpot(this.m_hotSpot);
        }
    }
    
    final void drawHotSpot(final Image image) {
        if (this.m_hotSpot != null) {
            final Rectangle bounds = this.m_hotSpot.getBounds();
            final Graphics graphics = image.getGraphics();
            graphics.setColor(Color.lightGray);
            graphics.draw3DRect(bounds.x, bounds.y, bounds.width - 1, bounds.height - 1, !this.m_mouseDown);
            graphics.dispose();
        }
    }
    
    final void processMouseMove(final int n, final int n2) {
        this.overHotSpot(this.mapHotSpot(n, n2));
    }
    
    final void overHotSpot(final HotSpotInfo hotSpot) {
        if (this.m_hotSpot == hotSpot) {
            return;
        }
        this.m_hotSpotSink.repaint();
        if (this.m_hotSpot != null) {
            this.m_hotSpotSink.exitHotSpot(this.m_hotSpot);
        }
        this.m_hotSpot = hotSpot;
        if (this.m_hotSpot != null) {
            this.m_hotSpotSink.enterHotSpot(this.m_hotSpot);
        }
    }
    
    final HotSpotInfo mapHotSpot(final int n, final int n2) {
        for (int size = this.m_hotSpots.size(), i = 0; i < size; ++i) {
            final HotSpotInfo hotSpotInfo = this.m_hotSpots.elementAt(i);
            if (hotSpotInfo.getBounds().inside(n, n2)) {
                return hotSpotInfo;
            }
        }
        return null;
    }
}
