import java.awt.Graphics;
import java.io.InputStream;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.DataInputStream;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.net.URL;
import java.awt.Button;
import java.awt.Scrollbar;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class OCAD extends Applet
{
    OMaps maps;
    OCanv canv;
    OLinks links;
    boolean isStandalone;
    int width;
    int height;
    int indent;
    int aplType;
    String fileName;
    Scrollbar hScroll;
    Scrollbar vScroll;
    Button zoomOut;
    protected URL fileURL;
    
    public String getParameter(final String key, final String def) {
        return this.isStandalone ? System.getProperty(key, def) : ((this.getParameter(key) != null) ? this.getParameter(key) : def);
    }
    
    public OCAD() {
        this.isStandalone = true;
        this.width = 400;
        this.height = 300;
        this.indent = 0;
        this.aplType = 0;
        this.hScroll = new Scrollbar(0);
        this.vScroll = new Scrollbar(1);
        this.zoomOut = null;
    }
    
    public void init() {
        this.setLayout(null);
        this.setBackground(Color.white);
        this.maps = new OMaps(this);
        this.add(this.canv = new OCanv(this, this.maps));
        this.links = new OLinks(this, this.maps, this.canv);
        final OLis listener = new OLis(this, this.maps, this.canv, this.links);
        this.canv.addMouseListener(listener);
        this.canv.addMouseMotionListener(listener);
        this.add(this.hScroll);
        this.hScroll.addAdjustmentListener(listener);
        this.add(this.vScroll);
        this.vScroll.addAdjustmentListener(listener);
        this.validate();
        this.fileName = String.valueOf(String.valueOf(this.getCodeBase())).concat(String.valueOf(String.valueOf(this.getParameter("FileName"))));
        try {
            final URL url = new URL(String.valueOf(String.valueOf(this.fileName)).concat(".oim"));
            try {
                final InputStream input = url.openStream();
                final DataInputStream in = new DataInputStream(input);
                final short ocadMark = in.readShort();
                final short fileType = in.readShort();
                final short version = in.readShort();
                final short subver = in.readShort();
                this.canv.tileWidth = in.readShort();
                this.canv.tileHeight = in.readShort();
                this.width = in.readShort();
                this.height = in.readShort();
                this.aplType = in.readShort();
                this.maps.resol = in.readShort();
                this.canv.width = this.width;
                this.canv.height = this.height;
                if (this.maps.mapCount() == 0) {
                    while (in.available() > 2) {
                        final short cmd = in.readShort();
                        if (cmd != 1) {
                            break;
                        }
                        this.maps.addMap(in);
                    }
                }
                this.canv.iMapOld = -1;
                this.canv.iMap = 0;
                this.maps.x = this.maps.getMap(this.canv.iMap).x;
                this.maps.y = this.maps.getMap(this.canv.iMap).y;
                this.canv.setBounds(1, 1, this.width, this.height);
                this.canv.resetPointer();
                this.canv.setPointerColor(255, 0, 0);
                this.canv.drawPointer(0, 0);
                this.canv.drawPointer(0, 14);
                this.canv.drawPointer(4, 10);
                this.canv.drawPointer(13, 18);
                this.canv.drawPointer(18, 13);
                this.canv.drawPointer(10, 4);
                this.canv.drawPointer(14, 0);
                this.canv.getMap(true);
                input.close();
            }
            catch (FileNotFoundException e) {
                System.out.println("Error: ".concat(String.valueOf(String.valueOf(e.getMessage()))));
            }
            catch (IOException e2) {
                System.out.println("Error: ".concat(String.valueOf(String.valueOf(e2.getMessage()))));
            }
        }
        catch (MalformedURLException e3) {
            System.out.println("Error: ".concat(String.valueOf(String.valueOf(e3.getMessage()))));
        }
        if (this.aplType > 0) {
            final String s = this.getParameter("ZoomOut");
            if (s != null) {
                this.zoomOut = new Button(s);
            }
            else {
                this.zoomOut = new Button("Zoom out");
            }
            this.add(this.zoomOut);
            this.zoomOut.addActionListener(listener);
            this.zoomOut.setBounds(1, this.height + 1, 100, 19);
            this.hScroll.setBounds(100, this.height + 1, this.width - 98, 19);
        }
        else {
            this.hScroll.setBounds(1, this.height + 1, this.width, 19);
        }
        this.vScroll.setBounds(this.width + 1, 1, 19, this.height);
    }
    
    public void paint(final Graphics g) {
        this.canv.repaint();
    }
    
    public String getAppletInfo() {
        return "OCAD Applet";
    }
    
    public String[][] getParameterInfo() {
        return null;
    }
    
    public void resetPointer() {
        this.canv.resetPointer();
    }
    
    public void setPointerColor(final int red, final int green, final int blue) {
        this.canv.setPointerColor(red, green, blue);
    }
    
    public void drawPointer(final int x, final int y) {
        this.canv.drawPointer(x, y);
    }
    
    public void zoomIn() {
        this.canv.zoomIn();
    }
    
    public void zoomOut() {
        this.canv.zoomOut();
    }
    
    public void entireMap() {
        this.canv.entireMap();
    }
    
    public void setView(final int zoom, final double x, final double y) {
        this.canv.setView(zoom, x, y, false);
    }
    
    public void setViewFromString(final String s) {
        this.canv.setViewFromString(s, false);
    }
    
    public void setMark(final int zoom, final double x, final double y) {
        this.canv.setView(zoom, x, y, true);
    }
    
    public void setPointer(final int zoom, final double x, final double y) {
        this.canv.setView(zoom, x, y, true);
    }
    
    public void setMarkFromString(final String s) {
        this.canv.setViewFromString(s, true);
    }
    
    public void setPointerFromString(final String s) {
        this.canv.setViewFromString(s, true);
    }
    
    public void hidePointer() {
        this.canv.hidePointer();
    }
    
    public void resetHotspot() {
        this.links.resetHotspot();
    }
    
    public void setHotspotColor(final int red, final int green, final int blue) {
        this.links.setHotspotColor(red, green, blue);
    }
    
    public void drawHotspot(final int x, final int y) {
        this.links.drawHotspot(x, y);
    }
    
    public void clearHotspots() {
        this.links.clearHotspots();
    }
    
    public void addHotspot(final double x, final double y, final String s) {
        this.links.addHotspot(x, y, s);
    }
    
    public int getZoom() {
        return this.canv.iMap;
    }
    
    public int getXPos() {
        return (int)this.maps.x;
    }
    
    public int getYPos() {
        return (int)this.maps.y;
    }
}
