import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Polygon;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class OCanv extends Canvas implements Runnable
{
    public int width;
    public int height;
    public boolean showRect;
    public int rectX;
    public int rectY;
    public int rectWidth;
    public int rectHeight;
    private OCAD apl;
    private OMaps maps;
    OMap map;
    public int ofsX;
    public int ofsY;
    public int tileWidth;
    public int tileHeight;
    public int iMap;
    public int iMapOld;
    public double xPtr;
    public double yPtr;
    Image image;
    Thread thread;
    boolean pointerShown;
    boolean blink;
    Polygon pointerPoly;
    Color pointerCol;
    int iString;
    double valString;
    
    public OCanv() {
        this.showRect = false;
        this.thread = null;
    }
    
    public OCanv(final OCAD apl, final OMaps maps) {
        this.showRect = false;
        this.thread = null;
        this.apl = apl;
        this.maps = maps;
    }
    
    public double screenToWorldX(final int x) {
        return this.maps.x + (x - this.width / 2) * this.map.scale * 0.0254 / this.maps.resol;
    }
    
    public double screenToWorldY(final int y) {
        return this.maps.y - (y - this.height / 2) * this.map.scale * 0.0254 / this.maps.resol;
    }
    
    public int worldToScreenX(final double x) {
        return (int)(this.width / 2 + (x - this.maps.x) / this.map.scale * this.maps.resol / 0.0254);
    }
    
    public int worldToScreenY(final double y) {
        return (int)(this.height / 2 - (y - this.maps.y) / this.map.scale * this.maps.resol / 0.0254);
    }
    
    public void resetPointer() {
        this.repaint();
        this.pointerPoly = new Polygon();
    }
    
    public void setPointerColor(final int red, final int green, final int blue) {
        this.pointerCol = new Color(red, green, blue);
    }
    
    public void drawPointer(final int x, final int y) {
        this.pointerPoly.addPoint(x, y);
    }
    
    public void setScrollX(final int x) {
        this.maps.x = this.map.x + (x + this.width / 2 - this.map.nCol * this.tileWidth / 2) * this.map.scale * 0.0254 / this.maps.resol;
    }
    
    public void setScrollY(final int y) {
        this.maps.y = this.map.y - (y + this.height / 2 - this.map.nRow * this.tileHeight / 2) * this.map.scale * 0.0254 / this.maps.resol;
    }
    
    public void getMap(final boolean setScrollbars) {
        this.map = this.maps.getMap(this.iMap);
        this.ofsX = (int)(this.map.nCol * this.tileWidth / 2 - this.width / 2 + (this.maps.x - this.map.x) / this.map.scale * this.maps.resol / 0.0254);
        this.ofsY = (int)(this.map.nRow * this.tileHeight / 2 - this.height / 2 - (this.maps.y - this.map.y) / this.map.scale * this.maps.resol / 0.0254);
        for (int j = 0; j < this.map.nRow; ++j) {
            if (j * this.tileHeight < this.ofsY + this.height && (j + 1) * this.tileHeight > this.ofsY) {
                for (int i = 0; i < this.map.nCol; ++i) {
                    if (i * this.tileWidth < this.ofsX + this.width && (i + 1) * this.tileWidth > this.ofsX) {
                        this.maps.getImage(this.map.firstNum + j * this.map.nCol + i);
                    }
                }
            }
        }
        if (setScrollbars) {
            this.apl.hScroll.setValues(this.ofsX, this.width, 0, this.map.nCol * this.tileWidth);
            this.apl.hScroll.setUnitIncrement(20);
            this.apl.hScroll.setBlockIncrement(200);
            this.apl.vScroll.setValues(this.ofsY, this.height, 0, this.map.nRow * this.tileHeight);
            this.apl.vScroll.setUnitIncrement(20);
            this.apl.vScroll.setBlockIncrement(200);
        }
        this.iMapOld = this.iMap;
    }
    
    public void drawRect() {
        if (this.rectWidth != 0 && this.rectHeight != 0) {
            final Graphics g = this.getGraphics();
            g.setXORMode(Color.white);
            int x = this.rectX;
            int y = this.rectY;
            int w = this.rectWidth;
            int h = this.rectHeight;
            if (this.rectWidth < 0) {
                w = -w;
                x -= w;
            }
            if (this.rectHeight < 0) {
                h = -h;
                y -= h;
            }
            g.drawRect(x, y, w, h);
        }
    }
    
    public void showPointer() {
        this.pointerShown = true;
        if (this.thread == null) {
            (this.thread = new Thread(this)).start();
        }
    }
    
    public void hidePointer() {
        this.pointerShown = false;
    }
    
    public void zoomIn() {
        if (this.iMap < this.maps.mapCount() - 1) {
            ++this.iMap;
        }
        this.getMap(true);
        this.repaint();
    }
    
    public void zoomOut() {
        if (this.iMap > 0) {
            --this.iMap;
        }
        if (this.iMap == 0) {
            this.maps.x = this.maps.getMap(0).x;
            this.maps.y = this.maps.getMap(0).y;
        }
        this.getMap(true);
        this.repaint();
    }
    
    public void entireMap() {
        this.iMap = 0;
        this.maps.x = this.maps.getMap(0).x;
        this.maps.y = this.maps.getMap(0).y;
        this.getMap(true);
        this.repaint();
    }
    
    public void setView(final int zoom, final double x, final double y, final boolean showM) {
        if (zoom > this.maps.mapCount() - 1) {
            this.iMap = this.maps.mapCount() - 1;
        }
        else {
            this.iMap = zoom;
        }
        this.maps.x = x;
        this.maps.y = y;
        this.xPtr = x;
        this.yPtr = y;
        this.getMap(true);
        this.repaint();
        if (showM) {
            this.showPointer();
        }
        else {
            this.hidePointer();
        }
    }
    
    boolean nextNum(final String s) {
        int p;
        for (p = this.iString; p < s.length() && (s.charAt(p) < '0' || s.charAt(p) > '9') && s.charAt(p) != '-' && s.charAt(p) != '.'; ++p) {}
        this.iString = p;
        while (p < s.length() && ((s.charAt(p) >= '0' && s.charAt(p) <= '9') || s.charAt(p) == '-' || s.charAt(p) == '.')) {
            ++p;
        }
        if (p > this.iString) {
            this.valString = Double.valueOf(s.substring(this.iString, p));
            this.iString = p;
            return true;
        }
        return false;
    }
    
    public void setViewFromString(final String s, final boolean showM) {
        int iM = 0;
        double x = 0.0;
        this.iString = 0;
        if (this.nextNum(s)) {
            iM = (int)this.valString;
        }
        if (this.nextNum(s)) {
            x = this.valString;
        }
        if (this.nextNum(s)) {
            this.iMap = iM;
            this.maps.x = x;
            this.maps.y = this.valString;
            this.xPtr = this.maps.x;
            this.yPtr = this.maps.y;
            this.getMap(true);
            this.repaint();
            if (showM) {
                this.showPointer();
            }
            else {
                this.hidePointer();
            }
        }
        else {
            this.hidePointer();
        }
    }
    
    public void run() {
        while (this.thread != null) {
            final boolean oldBlink = this.blink;
            final Rectangle rc = new Rectangle(this.pointerPoly.getBounds());
            if (this.pointerShown || this.blink) {
                this.blink = !this.blink;
            }
            if (this.pointerShown || oldBlink) {
                final int x0 = this.worldToScreenX(this.xPtr);
                final int y0 = this.worldToScreenY(this.yPtr);
                int x2 = this.pointerPoly.xpoints[0];
                int x3 = this.pointerPoly.xpoints[0];
                int y2 = this.pointerPoly.ypoints[0];
                int y3 = this.pointerPoly.ypoints[0];
                for (int i = 1; i < this.pointerPoly.npoints; ++i) {
                    if (this.pointerPoly.xpoints[i] < x2) {
                        x2 = this.pointerPoly.xpoints[i];
                    }
                    if (this.pointerPoly.xpoints[i] > x3) {
                        x3 = this.pointerPoly.xpoints[i];
                    }
                    if (this.pointerPoly.ypoints[i] < y2) {
                        y2 = this.pointerPoly.ypoints[i];
                    }
                    if (this.pointerPoly.ypoints[i] > y3) {
                        y3 = this.pointerPoly.ypoints[i];
                    }
                }
                if (x2 + x0 <= this.width && x3 + x0 >= 0 && y2 + y0 <= this.height && y3 + y0 >= 0) {
                    this.repaint(x2 + x0, y2 + y0, x3 - x2, y3 - y2);
                }
            }
            try {
                Thread.sleep(800L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void paint(final Graphics g) {
        for (int j = 0; j < this.map.nRow; ++j) {
            if (j * this.tileHeight < this.ofsY + this.height && (j + 1) * this.tileHeight > this.ofsY) {
                for (int i = 0; i < this.map.nCol; ++i) {
                    if (i * this.tileWidth < this.ofsX + this.width && (i + 1) * this.tileWidth > this.ofsX) {
                        g.drawImage(this.maps.getImage(this.map.firstNum + j * this.map.nCol + i), i * this.tileWidth - this.ofsX, j * this.tileHeight - this.ofsY, this);
                    }
                }
            }
        }
        this.apl.links.paintHotspots(g);
        if (this.rectWidth != 0 && this.rectHeight != 0) {
            g.drawRect(this.rectX, this.rectY, this.rectWidth, this.rectHeight);
        }
        if (this.blink) {
            final int x0 = this.worldToScreenX(this.xPtr);
            final int y0 = this.worldToScreenY(this.yPtr);
            int x2 = this.pointerPoly.xpoints[0];
            int x3 = this.pointerPoly.xpoints[0];
            int y2 = this.pointerPoly.ypoints[0];
            int y3 = this.pointerPoly.ypoints[0];
            for (int i = 1; i < this.pointerPoly.npoints; ++i) {
                if (this.pointerPoly.xpoints[i] < x2) {
                    x2 = this.pointerPoly.xpoints[i];
                }
                if (this.pointerPoly.xpoints[i] > x3) {
                    x3 = this.pointerPoly.xpoints[i];
                }
                if (this.pointerPoly.ypoints[i] < y2) {
                    y2 = this.pointerPoly.ypoints[i];
                }
                if (this.pointerPoly.ypoints[i] > y3) {
                    y3 = this.pointerPoly.ypoints[i];
                }
            }
            if (x2 + x0 <= this.width && x3 + x0 >= 0 && y2 + y0 <= this.height && y3 + y0 >= 0) {
                this.pointerPoly.translate(x0, y0);
                g.setColor(this.pointerCol);
                g.fillPolygon(this.pointerPoly);
                this.pointerPoly.translate(-x0, -y0);
            }
        }
    }
    
    public boolean imageUpdate(final Image img, final int infoflags, final int x, final int y, final int width, final int height) {
        if (infoflags == 32) {
            this.repaint();
        }
        return true;
    }
}
