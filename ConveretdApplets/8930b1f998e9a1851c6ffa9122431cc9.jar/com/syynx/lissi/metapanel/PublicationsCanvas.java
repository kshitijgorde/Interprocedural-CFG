// 
// Decompiled by Procyon v0.5.30
// 

package com.syynx.lissi.metapanel;

import java.awt.Cursor;
import java.util.Enumeration;
import java.net.URL;
import java.awt.geom.Point2D;
import java.awt.event.MouseEvent;
import java.text.BreakIterator;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.geom.Rectangle2D;
import java.util.Hashtable;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import com.syynx.lissi.Colors;
import javax.swing.JPanel;

public class PublicationsCanvas extends JPanel implements Colors, MouseListener, MouseMotionListener
{
    PersonDataPanel owner;
    int offset;
    Hashtable<Rectangle2D, String> links;
    
    public PublicationsCanvas(final PersonDataPanel parent) {
        super(null);
        this.offset = 0;
        this.owner = parent;
        this.links = new Hashtable<Rectangle2D, String>();
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
    }
    
    public void reset() {
        this.links.clear();
        this.repaint();
    }
    
    public void paint(final Graphics g) {
        g.setColor(PublicationsCanvas.colorPanelBG);
        g.setFont(new Font("SansSerif", 1, 11));
        g.setColor(Color.white);
        g.setColor(PublicationsCanvas.colorLink);
        this.offset = 0;
        this.offset = this.renderPublications(460, 30, 220, 3, (Graphics2D)g);
    }
    
    public int renderPublications(final int x, int y, final int width, final int limit, final Graphics2D g) {
        g.setColor(PublicationsCanvas.colorPanelFrame);
        g.setFont(new Font("SansSerif", 1, 11));
        g.drawString("last " + Integer.toString(limit) + " publications", x, y);
        g.drawLine(x, y + 3, width + x - 15, y + 3);
        g.setColor(PublicationsCanvas.colorLink);
        g.setFont(new Font("SansSerif", 0, 11));
        y += 8;
        for (int i = 0; i < Math.min(this.owner.getData().publications.size(), limit); ++i) {
            final Hashtable h = this.owner.getData().publications.get(i);
            g.setFont(new Font("SansSerif", 1, 11));
            y += this.drawStringNewline(x, y + 9, width, h.get("title"), g);
            g.setFont(new Font("SansSerif", 0, 11));
            y += this.drawStringNewline(x, y + 9, width, h.get("pubdate"), g);
            g.setFont(new Font("SansSerif", 1, 9));
            this.drawLink("abstract >>", h.get("linkout"), x + width - 50, y + 9, g);
            g.setFont(new Font("SansSerif", 0, 11));
            y += 19;
        }
        return y;
    }
    
    public void drawLink(final String text, final String url, final int x, final int y, final Graphics2D g) {
        final Font f = g.getFont();
        g.drawString(text, x, y);
        final Rectangle2D rBound = f.getStringBounds(text, g.getFontRenderContext());
        this.links.put(new Rectangle2D.Double(x, y - rBound.getHeight(), rBound.getWidth(), rBound.getHeight()), url);
    }
    
    public int drawStringNewline(final int x, final int y, final int w, final String s, final Graphics2D g) {
        final BreakIterator boundary = BreakIterator.getWordInstance();
        boundary.setText(s);
        int start = boundary.first();
        int lineStart = 0;
        int yOff = 0;
        final Font f = g.getFont();
        for (int end = boundary.next(); end != -1; end = boundary.next()) {
            final Rectangle2D rBound = f.getStringBounds(s.substring(lineStart, end), g.getFontRenderContext());
            if (rBound.getWidth() > w) {
                g.drawString(s.substring(lineStart, start), x, y + yOff);
                yOff += (int)rBound.getHeight();
                lineStart = end + 1;
            }
            start = end;
        }
        if (lineStart < s.length()) {
            final Rectangle2D rBound = f.getStringBounds(s.substring(lineStart, s.length()), g.getFontRenderContext());
            g.drawString(s.substring(lineStart, s.length()), x, y + yOff);
            yOff += (int)rBound.getHeight();
        }
        return yOff;
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public void mouseClicked(final MouseEvent e) {
        final Enumeration en = this.links.keys();
        final Rectangle2D onRect = new Rectangle2D.Double(30.0, 585.0, 108.0, 23.0);
        if (onRect.contains(e.getPoint())) {
            try {
                this.owner.load(this.owner.getPerson(), this.owner.getID());
            }
            catch (Exception ex) {
                System.out.println(ex.toString());
            }
        }
        else {
            while (en.hasMoreElements()) {
                final Rectangle2D lRect = en.nextElement();
                if (lRect.contains(e.getPoint())) {
                    try {
                        this.owner.showDocument(new URL(this.links.get(lRect)));
                    }
                    catch (Exception ex) {
                        System.out.println(ex.toString());
                    }
                    break;
                }
            }
        }
    }
    
    public void mouseEntered(final MouseEvent e) {
    }
    
    public void mouseExited(final MouseEvent e) {
    }
    
    public void mousePressed(final MouseEvent e) {
    }
    
    public void mouseReleased(final MouseEvent e) {
    }
    
    public void mouseMoved(final MouseEvent e) {
        final Enumeration en = this.links.keys();
        final Rectangle2D onRect = new Rectangle2D.Double(30.0, 585.0, 108.0, 23.0);
        if (onRect.contains(e.getPoint())) {
            final Cursor normalCursor = new Cursor(12);
            this.setCursor(normalCursor);
            return;
        }
        while (en.hasMoreElements()) {
            final Rectangle2D lRect = en.nextElement();
            if (lRect.contains(e.getPoint())) {
                final Cursor normalCursor = new Cursor(12);
                this.setCursor(normalCursor);
                return;
            }
        }
        final Cursor normalCursor = new Cursor(0);
        this.setCursor(normalCursor);
    }
    
    public void mouseDragged(final MouseEvent e) {
    }
}
