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
import java.awt.Font;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.geom.Rectangle2D;
import java.util.Hashtable;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import com.syynx.lissi.Colors;
import javax.swing.JPanel;

public class PersonDataCanvas extends JPanel implements Colors, MouseListener, MouseMotionListener
{
    PersonDataPanel owner;
    int offset;
    Hashtable<Rectangle2D, String> links;
    
    public PersonDataCanvas(final PersonDataPanel parent) {
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
        g.setColor(PersonDataCanvas.colorPanelBG);
        g.setFont(new Font("SansSerif", 1, 11));
        this.offset = this.renderResearchProfile(8, 30, 220, 5, (Graphics2D)g);
        this.offset = this.renderPubsPerYear(230, 30, 220, 6, (Graphics2D)g);
    }
    
    public int renderPubsPerYear(final int x, int y, final int width, final int years, final Graphics2D g) {
        int max = 0;
        double relation = 1.0;
        final int maxH = 40;
        final int fontH = 11;
        final Object[] c = this.owner.getData().pubsperyear.keySet().toArray();
        g.setColor(PersonDataCanvas.colorPanelFrame);
        g.setFont(new Font("SansSerif", 1, 11));
        g.drawString("publication timeline", x, y);
        g.drawLine(x, y + 3, width + x - 15, y + 3);
        g.setColor(PersonDataCanvas.colorLink);
        g.setFont(new Font("SansSerif", 0, 11));
        y += 8;
        g.setColor(PersonDataCanvas.colorListBG);
        g.fillRect(x, y + 40 + 2, width, 12);
        g.setColor(PersonDataCanvas.colorPanelFrame);
        for (int i = 0; i < Math.min(years, c.length); ++i) {
            max = Math.max(Integer.parseInt(this.owner.getData().pubsperyear.get(c[i])), max);
        }
        relation = 30.0 / max;
        for (int i = 0; i < Math.min(years, c.length); ++i) {
            final String year = (String)c[i];
            g.setColor(PersonDataCanvas.colorLink);
            g.fillRect(x + width / years * i + 2, y + (40 - (int)(Integer.parseInt(this.owner.getData().pubsperyear.get(year)) * relation)), width / years - 14, (int)(Integer.parseInt(this.owner.getData().pubsperyear.get(year)) * relation));
            g.setColor(PersonDataCanvas.colorPanelFrame);
            g.drawString(this.owner.getData().pubsperyear.get(year), x + width / years * i + 3, y + (38 - (int)(Integer.parseInt(this.owner.getData().pubsperyear.get(year)) * relation)));
            g.drawString(year.substring(2, 4), x + 3 + width / years * i, y + 40 + 11 + 1);
        }
        return y + 40 + 11 + 1;
    }
    
    public int renderResearchProfile(final int x, int y, final int width, final int limit, final Graphics2D g) {
        double relation = 1.0;
        g.setColor(PersonDataCanvas.colorPanelFrame);
        g.setFont(new Font("SansSerif", 1, 11));
        g.drawString("research profile", 8, y);
        g.drawLine(x, y + 3, width - 15, y + 3);
        y += 8;
        g.setColor(PersonDataCanvas.colorLink);
        Hashtable h = this.owner.getData().concepts.get(0);
        relation = 50.0 / Math.round(Double.parseDouble(h.get("rank")) * 10.0);
        for (int i = 0; i < Math.min(this.owner.getData().concepts.size(), limit); ++i) {
            h = this.owner.getData().concepts.get(i);
            g.setFont(new Font("SansSerif", 0, 9));
            final String rank = Long.toString(Math.round(Double.parseDouble(h.get("rank")) * 10.0));
            g.setColor(PersonDataCanvas.colorLink);
            g.fillRect(x, y + 2, (int)(Integer.parseInt(rank) * relation), 8);
            String name = h.get("name");
            if (name.length() > 30) {
                name = String.valueOf(name.substring(0, 27)) + "...";
            }
            g.drawString(name, x + 75, y + 9);
            y += 12;
        }
        return y;
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
