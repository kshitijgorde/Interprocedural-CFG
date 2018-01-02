// 
// Decompiled by Procyon v0.5.30
// 

package dk.scanmaps.ditkort;

import java.awt.event.ActionEvent;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Component;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.imageio.ImageIO;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Image;
import java.net.URL;
import javax.swing.JEditorPane;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class DanmarksKort extends JPanel implements ActionListener
{
    static final long serialVersionUID = 0L;
    private String baseurl;
    private boolean showDrawBox;
    private JEditorPane mapUsage;
    private URL helpURL;
    private Image image;
    public int x;
    public int y;
    public int px;
    public int py;
    private Point point;
    private int GRIDSIZE_FACTOR;
    private int specialRegion;
    private String servicename;
    private URL mapUrl;
    
    public DanmarksKort(final String servicename) {
        this.baseurl = Constant.imageURL;
        this.showDrawBox = false;
        this.helpURL = null;
        this.x = 0;
        this.y = 0;
        this.px = 0;
        this.py = 0;
        this.point = new Point();
        this.GRIDSIZE_FACTOR = 1;
        this.specialRegion = 0;
        this.mapUrl = null;
        this.servicename = servicename;
        try {
            this.setLayout(new FlowLayout(2));
            if (Constant.debugMode) {
                System.out.println("Danmarkskort, servicename is: " + servicename);
            }
            this.mapUrl = new URL(String.valueOf(this.baseurl) + servicename + ".png");
            this.image = ImageIO.read(this.mapUrl);
            this.mapUsage = this.createEditorPane(servicename);
        }
        catch (MalformedURLException e) {
            if (Constant.debugMode) {
                System.out.println("mapUrl is: " + this.mapUrl.getHost() + this.mapUrl.getPath());
                System.err.println("Malformed URL " + e.getMessage());
            }
        }
        catch (IOException e2) {
            if (Constant.debugMode) {
                System.out.println("mapUrl is: " + this.mapUrl.getHost() + this.mapUrl.getPath());
                System.err.println("IOException in DanmarksKort: " + e2.getMessage());
            }
        }
        this.add(this.mapUsage);
        this.repaint();
    }
    
    public void setOverviewMap(final String servicename) {
        try {
            this.image.flush();
            if (Constant.debugMode) {
                System.out.println("servicename is: " + servicename);
            }
            final URL mapUrl = new URL(String.valueOf(this.baseurl) + servicename + ".png");
            this.image = ImageIO.read(mapUrl);
        }
        catch (Exception e) {
            System.err.println("Could not load map of Denmark");
        }
    }
    
    private JEditorPane createEditorPane(final String servicename) {
        (this.mapUsage = new JEditorPane()).setContentType("text/html; charset=ISO-8859-1");
        this.mapUsage.setPreferredSize(new Dimension(300, 600));
        this.mapUsage.setBackground(new Color(238, 238, 238));
        this.mapUsage.setEditable(false);
        this.mapUsage.setFocusable(false);
        this.setTextForOverviewMap(servicename);
        return this.mapUsage;
    }
    
    public void setTextForOverviewMap(final String servicename) {
        try {
            this.helpURL = new URL(String.valueOf(Constant.base) + "/java/" + CResourceManager.instance().getLocale() + "_" + servicename.toLowerCase() + ".html");
            if (Constant.debugMode) {
                System.out.println("In setTextForOverviewMap, helpURL is: " + this.helpURL);
            }
        }
        catch (MalformedURLException e) {
            if (Constant.debugMode) {
                System.err.println("setTextForOverviewMap threw an exception");
                e.printStackTrace(System.err);
            }
        }
        try {
            this.mapUsage.setPage(this.helpURL);
        }
        catch (IOException e2) {
            if (Constant.debugMode) {
                System.err.println("setTextForOverviewMap threw an exception");
                e2.printStackTrace(System.err);
            }
        }
    }
    
    public Polygon get2113() {
        final Polygon s = new Polygon();
        s.addPoint(this.point.x, this.point.y);
        s.addPoint(this.point.x + (int)(Constant.DANMARKSKORT_GRIDSIZE_X * 4.0f), this.point.y);
        s.addPoint(this.point.x + (int)(Constant.DANMARKSKORT_GRIDSIZE_X * 4.0f), this.point.y + (int)(Constant.DANMARKSKORT_GRIDSIZE_Y * 4.0f));
        s.addPoint(this.point.x, this.point.y + (int)(Constant.DANMARKSKORT_GRIDSIZE_Y * 4.0f));
        s.addPoint(this.point.x, this.point.y + (int)(Constant.DANMARKSKORT_GRIDSIZE_Y * 3.0f - 6.0f));
        s.addPoint(this.point.x - 9, this.point.y + (int)(Constant.DANMARKSKORT_GRIDSIZE_Y * 3.0f - 6.0f));
        s.addPoint(this.point.x - 9, this.point.y + (int)(Constant.DANMARKSKORT_GRIDSIZE_Y * 2.0f - 9.0f));
        s.addPoint(this.point.x, this.point.y + (int)(Constant.DANMARKSKORT_GRIDSIZE_Y * 2.0f - 9.0f));
        return s;
    }
    
    public Polygon get2513() {
        final Polygon s = new Polygon();
        s.addPoint(this.point.x, this.point.y);
        s.addPoint(this.point.x + (int)(Constant.DANMARKSKORT_GRIDSIZE_X * 5.0f), this.point.y);
        s.addPoint(this.point.x + (int)(Constant.DANMARKSKORT_GRIDSIZE_X * 5.0f), this.point.y + (int)(Constant.DANMARKSKORT_GRIDSIZE_Y * 2.0f));
        s.addPoint(this.point.x + (int)(Constant.DANMARKSKORT_GRIDSIZE_X * 4.0f), this.point.y + (int)(Constant.DANMARKSKORT_GRIDSIZE_Y * 2.0f));
        s.addPoint(this.point.x + (int)(Constant.DANMARKSKORT_GRIDSIZE_X * 4.0f), this.point.y + (int)(Constant.DANMARKSKORT_GRIDSIZE_Y * 4.0f));
        s.addPoint(this.point.x, this.point.y + (int)(Constant.DANMARKSKORT_GRIDSIZE_Y * 4.0f));
        return s;
    }
    
    public Polygon get2114() {
        final Polygon s = new Polygon();
        s.addPoint(this.point.x, this.point.y);
        s.addPoint(this.point.x + (int)(Constant.DANMARKSKORT_GRIDSIZE_X * 4.0f), this.point.y);
        s.addPoint(this.point.x + (int)(Constant.DANMARKSKORT_GRIDSIZE_X * 4.0f), this.point.y + (int)(Constant.DANMARKSKORT_GRIDSIZE_Y * 4.0f));
        s.addPoint(this.point.x, this.point.y + (int)(Constant.DANMARKSKORT_GRIDSIZE_Y * 4.0f));
        s.addPoint(this.point.x, this.point.y + (int)(Constant.DANMARKSKORT_GRIDSIZE_Y * 2.0f));
        s.addPoint(this.point.x - 2, this.point.y + (int)(Constant.DANMARKSKORT_GRIDSIZE_Y * 2.0f));
        s.addPoint(this.point.x - 2, this.point.y + (int)Constant.DANMARKSKORT_GRIDSIZE_Y);
        s.addPoint(this.point.x, this.point.y + (int)Constant.DANMARKSKORT_GRIDSIZE_Y);
        return s;
    }
    
    public void showClickedBox(final boolean showDrawBox) {
        this.showDrawBox = showDrawBox;
    }
    
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        if (this.image != null) {
            g.drawImage(this.image, 0, 0, 516, 600, this);
        }
        if (!this.servicename.equals(Constant.servicename_H1870) && this.point != null && this.showDrawBox) {
            if (Constant.debugMode) {
                System.out.println("in DKKort, point.x: " + this.point.x + " point.y: " + this.point.y);
            }
            g.setColor(Color.BLACK);
            ((Graphics2D)g).setStroke(new BasicStroke(2.0f));
            if (this.specialRegion == 2113) {
                g.drawPolygon(this.get2113());
            }
            else if (this.specialRegion == 2114) {
                g.drawPolygon(this.get2114());
            }
            else if (this.specialRegion == 2513) {
                g.drawPolygon(this.get2513());
            }
            else {
                g.drawRect(this.point.x, this.point.y, (int)(Constant.DANMARKSKORT_GRIDSIZE_X * this.GRIDSIZE_FACTOR), (int)(Constant.DANMARKSKORT_GRIDSIZE_Y * this.GRIDSIZE_FACTOR));
            }
        }
    }
    
    public void setGridsizeFactor(final int GRIDSIZE_FACTOR) {
        this.GRIDSIZE_FACTOR = GRIDSIZE_FACTOR;
    }
    
    public void setSpecialRegion(final int specialRegion) {
        this.specialRegion = specialRegion;
    }
    
    public int getSpecialRegion() {
        return this.specialRegion;
    }
    
    public void drawBox(final int cursorX, final int cursorY, final int doNothing) {
        this.point.x = cursorX;
        this.point.y = cursorY;
        this.repaint();
    }
    
    public void actionPerformed(final ActionEvent arg0) {
    }
}
