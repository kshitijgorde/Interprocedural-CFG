// 
// Decompiled by Procyon v0.5.30
// 

package com.syynx.lissi.metapanel;

import java.awt.Cursor;
import java.util.Enumeration;
import java.awt.geom.Point2D;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.net.URL;
import java.net.URLEncoder;
import java.io.InputStream;
import java.awt.Toolkit;
import java.io.BufferedInputStream;
import java.awt.MediaTracker;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.geom.Rectangle2D;
import java.util.Hashtable;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import java.awt.Point;
import com.syynx.lissi.RadialGraphApplet;
import com.syynx.lissi.Colors;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class FloatWin extends JPanel implements MouseListener, MouseMotionListener, Colors, PersonDataEvents
{
    public String person;
    public String id;
    int m_xoff;
    int m_yoff;
    public PersonData data;
    public RadialGraphApplet owner;
    Point location;
    private int expandedHeight;
    public boolean active;
    public String initial;
    public String firstname;
    private String publications;
    public String lastname;
    private String memberid;
    private String edges;
    private boolean collapsed;
    static final Color opaque;
    static final Color transparent;
    private PersonDataPanel pdpan;
    public static final Font fntHeadline;
    public static final Font fntLabel;
    public static final Font fntName;
    public static final Font fntLabel2;
    private Image imageC;
    private Image imageM;
    Hashtable<Rectangle2D, String> links;
    
    static {
        opaque = new Color(255, 255, 255);
        transparent = new Color(255, 255, 255, 120);
        fntHeadline = new Font("SansSerif", 1, 11);
        fntLabel = new Font("SansSerif", 0, 11);
        fntName = new Font("SansSerif", 1, 11);
        fntLabel2 = new Font("SansSerif", 0, 10);
    }
    
    public FloatWin(final RadialGraphApplet owner) {
        super(null, true);
        this.person = "";
        this.id = "";
        this.m_xoff = 0;
        this.m_yoff = 0;
        this.expandedHeight = 0;
        this.collapsed = false;
        this.owner = owner;
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.data = PersonData.newInstance(this);
        (this.pdpan = new PersonDataPanel(this)).addMouseMotionListener(this);
        this.pdpan.addMouseListener(this);
        this.pdpan.setOpaque(false);
        this.add(this.pdpan);
        this.links = new Hashtable<Rectangle2D, String>();
        try {
            final MediaTracker mC = new MediaTracker(this);
            final InputStream isC = this.getClass().getResourceAsStream("Computed.JPG");
            final BufferedInputStream bisC = new BufferedInputStream(isC);
            final byte[] byBufC = new byte[10000];
            final int byteReadC = bisC.read(byBufC, 0, 10000);
            mC.addImage(this.imageC = Toolkit.getDefaultToolkit().createImage(byBufC), 0);
            mC.waitForAll();
            final MediaTracker mM = new MediaTracker(this);
            final InputStream isM = this.getClass().getResourceAsStream("Member.JPG");
            final BufferedInputStream bisM = new BufferedInputStream(isM);
            final byte[] byBufM = new byte[10000];
            final int byteReadM = bisM.read(byBufM, 0, 10000);
            mM.addImage(this.imageM = Toolkit.getDefaultToolkit().createImage(byBufM), 0);
            mM.waitForAll();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void personDataReady() {
        if (!this.person.equals("")) {
            this.setVisible(true);
            this.lastname = this.data.name;
            this.firstname = this.data.forename;
            this.memberid = this.data.memberid;
            this.initial = this.data.initial;
            this.active = true;
            this.validate();
        }
        this.repaint();
    }
    
    public void displayData(final String person, final String id, final String publications, final String edges) {
        this.person = person;
        this.id = id;
        this.publications = publications;
        this.edges = edges;
        try {
            final String personEnc = URLEncoder.encode(id, "UTF-8");
            this.data.load(String.valueOf(this.owner.getParameter("URLPersonData")) + personEnc);
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
        this.links.clear();
        this.pdpan.reset();
    }
    
    public void showDocument(final URL url) {
        this.owner.getAppletContext().showDocument(url, "_self");
    }
    
    public void drawLink(final String text, final String url, final int x, final int y, final Graphics2D g) {
        final Font f = g.getFont();
        g.drawString(text, x, y);
        final Rectangle2D rBound = f.getStringBounds(text, g.getFontRenderContext());
        this.links.put(new Rectangle2D.Double(x, y - rBound.getHeight(), rBound.getWidth(), rBound.getHeight()), url);
    }
    
    public void setBounds(final int x, final int y, final int width, final int height) {
        this.pdpan.setBounds(0, 100, width, height - 100);
        this.expandedHeight = height;
        int _height = height;
        if (this.collapsed) {
            _height = 20;
        }
        super.setBounds(x, y, width, _height);
    }
    
    public void load(final String person, final String id) {
        this.owner.load(person, id);
    }
    
    private void setCollapsed(final boolean collapse) {
        this.collapsed = collapse;
        final Rectangle r = this.getBounds();
        if (collapse) {
            super.setBounds(r.x, r.y, r.width, 20);
        }
        else {
            super.setBounds(r.x, r.y, r.width, this.expandedHeight);
        }
    }
    
    public void paint(final Graphics g) {
        final float trans = 1.0f;
        g.setColor(new Color(109, 179, 53));
        g.setFont(FloatWin.fntName);
        g.drawString(String.valueOf(this.lastname) + ", " + this.firstname, 40, 38);
        g.setColor(Color.black);
        g.setFont(FloatWin.fntLabel2);
        g.drawString("Publications:", 10, 68);
        g.drawString(this.publications, 80, 68);
        g.drawString("Connections:", 10, 83);
        g.drawString(this.edges, 80, 83);
        try {
            g.setFont(FloatWin.fntHeadline);
            g.setColor(FloatWin.colorLink);
            final String personEnc = URLEncoder.encode(this.id, "UTF-8");
            this.drawLink("full details >>", String.valueOf(this.owner.getParameter("URLPersonDetail")) + personEnc, 250, 58, (Graphics2D)g);
            g.drawString("open network >>", 250, 38);
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
        if (this.memberid.equalsIgnoreCase("0")) {
            g.drawImage(this.imageC, 10, 25, null);
        }
        else {
            g.drawImage(this.imageM, 10, 25, null);
        }
        super.paint(g);
        g.setColor(Color.white);
        g.drawRect(0, 20, this.getWidth() - 1, this.getHeight() - 21);
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public void mouseClicked(final MouseEvent e) {
        final Enumeration en = this.links.keys();
        final Rectangle2D onRect = new Rectangle2D.Double(250.0, 18.0, 108.0, 23.0);
        if (e.getX() > this.getWidth() - 20 && e.getY() < 20) {
            this.setCollapsed(!this.collapsed);
        }
        if (onRect.contains(e.getPoint())) {
            try {
                this.owner.load(this.person, this.id);
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
                        this.showDocument(new URL(this.links.get(lRect)));
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
        this.active = true;
        this.repaint();
    }
    
    public void mouseExited(final MouseEvent e) {
        this.active = false;
        this.repaint();
    }
    
    public void mousePressed(final MouseEvent e) {
        this.m_xoff = e.getX();
        this.m_yoff = e.getY();
    }
    
    public void mouseReleased(final MouseEvent e) {
    }
    
    public void mouseMoved(final MouseEvent e) {
        final Enumeration en = this.links.keys();
        final Rectangle2D onRect = new Rectangle2D.Double(250.0, 18.0, 108.0, 23.0);
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
