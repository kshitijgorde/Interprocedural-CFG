// 
// Decompiled by Procyon v0.5.30
// 

package mapapplet;

import java.awt.event.MouseEvent;
import java.net.URLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Hashtable;
import java.awt.Color;
import java.awt.Graphics;

public class MapZoom implements Module
{
    private static final String name = "zoom";
    private Object parent;
    private MapPoint zoomCorner1;
    private MapPoint zoomCorner2;
    private MapPoint zoomPoint;
    private MapProjection prj;
    private boolean isEnabled;
    public boolean isInitialised;
    private String tempReference;
    
    public MapZoom(final Object par) {
        this.tempReference = "";
        this.parent = par;
        this.isEnabled = false;
        this.isInitialised = false;
        this.zoomCorner1 = new MapPoint();
        this.zoomCorner2 = new MapPoint();
        this.zoomPoint = new MapPoint();
        ((Main)this.parent).toolbar.addButton(((Main)this.parent).getCurLocation() + "map_images/icon_zoom_2.gif", ((Main)this.parent).getCurLocation() + "map_images/icon_zoom_1.gif", "Zoom", "zoom", true, "Mode");
    }
    
    public void paint(final Graphics g) {
        if (this.isInitialised) {
            this.drawDottedRect(this.zoomCorner1.getX(), this.zoomCorner1.getY(), this.zoomCorner2.getX(), this.zoomCorner2.getY(), g);
            g.setColor(Color.red);
            g.fillOval(this.zoomPoint.getX() - 2, this.zoomPoint.getY() - 2, 4, 4);
        }
    }
    
    public void changedButton(final Hashtable buttons) {
        if (buttons.get("Zoom").checked) {
            this.isEnabled = true;
        }
        else {
            this.isEnabled = false;
        }
        this.repaintMap();
    }
    
    public void pressedButton(final PanelButton button) {
    }
    
    public void newMap(final MapProjection project) {
        this.prj = project;
        this.isInitialised = true;
        this.zoomCorner1.setX(0);
        this.zoomCorner1.setY(0);
        this.zoomCorner2.setX(((Main)this.parent).map.getSize().width);
        this.zoomCorner2.setY(((Main)this.parent).map.getSize().height);
        this.zoomPoint.setX(((Main)this.parent).map.getSize().width / 2);
        this.zoomPoint.setY(((Main)this.parent).map.getSize().height / 2);
        this.zoomPoint.calculateLL(this.prj);
        this.zoomCorner1.calculateLL(this.prj);
        this.zoomCorner2.calculateLL(this.prj);
    }
    
    public String name() {
        return "zoom";
    }
    
    public String queryParam(final String paramName) {
        if (paramName.compareTo("Zoom.GetZoomArea") == 0) {
            if (this.zoomCorner1.getX() < 0) {
                this.zoomCorner1.setX(0);
            }
            if (this.zoomCorner1.getX() > ((Main)this.parent).map.getSize().width) {
                this.zoomCorner1.setX(((Main)this.parent).map.getSize().width);
            }
            if (this.zoomCorner2.getX() < 0) {
                this.zoomCorner2.setX(0);
            }
            if (this.zoomCorner2.getX() > ((Main)this.parent).map.getSize().width) {
                this.zoomCorner2.setX(((Main)this.parent).map.getSize().width);
            }
            if (this.zoomCorner1.getY() < 0) {
                this.zoomCorner1.setY(0);
            }
            if (this.zoomCorner1.getY() > ((Main)this.parent).map.getSize().height) {
                this.zoomCorner1.setY(((Main)this.parent).map.getSize().height);
            }
            if (this.zoomCorner2.getY() < 0) {
                this.zoomCorner2.setY(0);
            }
            if (this.zoomCorner2.getY() > ((Main)this.parent).map.getSize().height) {
                this.zoomCorner2.setY(((Main)this.parent).map.getSize().height);
            }
            final String s = Math.min(this.zoomCorner1.getX(), this.zoomCorner2.getX()) + " " + Math.min(this.zoomCorner1.getY(), this.zoomCorner2.getY()) + " " + Math.max(this.zoomCorner1.getX(), this.zoomCorner2.getX()) + " " + Math.max(this.zoomCorner1.getY(), this.zoomCorner2.getY());
            return s;
        }
        if (paramName.compareTo("Zoom.GetZoomAreaPlus") == 0) {
            if (this.zoomCorner1.getX() < 0) {
                this.zoomCorner1.setX(0);
            }
            if (this.zoomCorner1.getX() > ((Main)this.parent).map.getSize().width) {
                this.zoomCorner1.setX(((Main)this.parent).map.getSize().width);
            }
            if (this.zoomCorner2.getX() < 0) {
                this.zoomCorner2.setX(0);
            }
            if (this.zoomCorner2.getX() > ((Main)this.parent).map.getSize().width) {
                this.zoomCorner2.setX(((Main)this.parent).map.getSize().width);
            }
            if (this.zoomCorner1.getY() < 0) {
                this.zoomCorner1.setY(0);
            }
            if (this.zoomCorner1.getY() > ((Main)this.parent).map.getSize().height) {
                this.zoomCorner1.setY(((Main)this.parent).map.getSize().height);
            }
            if (this.zoomCorner2.getY() < 0) {
                this.zoomCorner2.setY(0);
            }
            if (this.zoomCorner2.getY() > ((Main)this.parent).map.getSize().height) {
                this.zoomCorner2.setY(((Main)this.parent).map.getSize().height);
            }
            final String s = Math.min(this.zoomCorner1.getX(), this.zoomCorner2.getX()) + "+" + Math.min(this.zoomCorner1.getY(), this.zoomCorner2.getY()) + "+" + Math.max(this.zoomCorner1.getX(), this.zoomCorner2.getX()) + "+" + Math.max(this.zoomCorner1.getY(), this.zoomCorner2.getY());
            return s;
        }
        if (paramName.compareTo("Zoom.GetZoomPoint") == 0) {
            final String s = this.zoomPoint.getX() + " " + this.zoomPoint.getY();
            return s;
        }
        if (paramName.compareTo("Zoom.GetZoomPointPlus") == 0) {
            final String s = this.zoomPoint.getX() + "+" + this.zoomPoint.getY();
            return s;
        }
        if (paramName.compareTo("Zoom.getReference") == 0) {
            return this.tempReference;
        }
        return null;
    }
    
    public boolean setParam(final String paramName, final String value) {
        if (paramName.compareTo("Zoom.zoom") == 0) {
            return this.simpleZoom(value);
        }
        if (paramName.compareTo("Zoom.zoomDirectly") == 0) {
            return this.zoomDirectly(value);
        }
        return paramName.compareTo("Zoom.zoomDirectlyOGC") == 0 && this.zoomDirectlyOGC(value);
    }
    
    private boolean zoomDirectlyOGC(String value) {
        value = value + "&WIDTH=" + ((Main)this.parent).data.getMapSize().width + "&HEIGHT=" + ((Main)this.parent).data.getMapSize().height + "&FORMAT=image/png";
        System.out.println("Trying to load image through zoomDirectlyOGC:\n" + value + "\n");
        final String mapext = Main.getUrlParameter(value, "BBOX").replace(',', ' ');
        ((Main)this.parent).newMap(value, mapext);
        return true;
    }
    
    private boolean zoomDirectly(String value) {
        String mapext = ((Main)this.parent).data.extend;
        final StringBuffer text = new StringBuffer();
        if (((Main)this.parent).data.initialized) {
            value = Main.replaceSpaces(value);
            if (!value.endsWith("&")) {
                if (value.endsWith("&imgext=")) {
                    value = value.concat(((Main)this.parent).getParameter("GlobalMapExtend").replace(' ', '+'));
                    value = value.concat("&imgxy=" + this.queryParam("Zoom.GetZoomPointPlus"));
                }
                else {
                    if (value.endsWith("&imgbox=")) {
                        value = value.concat(this.queryParam("Zoom.GetZoomAreaPlus"));
                    }
                    else if (value.endsWith("&imgxy=")) {
                        value = value.concat(this.queryParam("Zoom.GetZoomPointPlus"));
                    }
                    value = value.concat("&imgext=" + ((Main)this.parent).data.extend.replace(' ', '+'));
                }
            }
            mapext = this.calculateExtend(value);
            System.out.println("Trying to load image through zoomDirectly:\n" + value + "\n");
            ((Main)this.parent).newMap(value, mapext);
        }
        else {
            System.err.println("Error zooming the map: applet is not initialized.");
            ((Main)this.parent).waiter(false);
            ((Main)this.parent).map.repaint();
        }
        return true;
    }
    
    private boolean simpleZoom(String value) {
        String mapext = "";
        String mapimg = "";
        String mapref = "";
        URL pageUrl = null;
        final StringBuffer text = new StringBuffer();
        if (((Main)this.parent).data.initialized) {
            try {
                value = Main.replaceSpaces(value);
                if (!value.endsWith("&")) {
                    if (value.endsWith("&imgext=")) {
                        value = value.concat(((Main)this.parent).getParameter("GlobalMapExtend").replace(' ', '+'));
                        value = value.concat("&imgxy=" + this.queryParam("Zoom.GetZoomPointPlus"));
                    }
                    else {
                        if (value.endsWith("&imgbox=")) {
                            value = value.concat(this.queryParam("Zoom.GetZoomAreaPlus"));
                        }
                        else if (value.endsWith("&imgxy=")) {
                            value = value.concat(this.queryParam("Zoom.GetZoomPointPlus"));
                        }
                        value = value.concat("&imgext=" + ((Main)this.parent).data.extend.replace(' ', '+'));
                    }
                }
                pageUrl = new URL(value);
                System.out.println("Trying to load image through simpleZoom:\n" + value + "\n");
                final URLConnection con = pageUrl.openConnection();
                try {
                    con.connect();
                }
                catch (Exception ex) {
                    System.err.println("Zoom.zoom error: can't connect to the URL specified. " + value);
                    ex.getMessage();
                    ex.printStackTrace();
                    ((Main)this.parent).statusBar.setText("Error loading image.");
                    ((Main)this.parent).waiter(false);
                    return true;
                }
                final BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String line = reader.readLine();
                text.append(line);
                do {
                    if (line.indexOf("<mapext>") >= 0) {
                        mapext = line.substring(line.indexOf("<mapext>") + 8, line.indexOf("</mapext>"));
                    }
                    if (line.indexOf("<mapimg>") >= 0) {
                        mapimg = line.substring(line.indexOf("<mapimg>") + 8, line.indexOf("</mapimg>"));
                    }
                    if (line.indexOf("<mapref>") >= 0) {
                        mapref = line.substring(line.indexOf("<mapref>") + 8, line.indexOf("</mapref>"));
                    }
                    text.append(line);
                    line = reader.readLine();
                } while (line != null);
                if (mapref.length() > 0) {
                    this.tempReference = mapref;
                }
                reader.close();
            }
            catch (MalformedURLException ex2) {
                ex2.printStackTrace();
            }
            catch (IOException ex3) {
                ex3.printStackTrace();
            }
            if (mapext == null || mapimg == null || mapref == null || mapext.length() <= 0 || mapimg.length() <= 0 || mapref.length() <= 0) {
                System.out.println("Mapserver error");
                System.out.println("mapext: " + mapext + "\nmapimg: " + mapimg + "\nmapref: " + mapref);
                System.out.println("URL: " + value);
                System.out.println("Returned text:");
                System.out.println(text);
                ((Main)this.parent).waiter(false);
                ((Main)this.parent).map.repaint();
                return true;
            }
            System.out.println(" Compare: original " + mapext + "; calculated: " + this.calculateExtend(value));
            mapimg = pageUrl.getProtocol() + "://" + pageUrl.getHost() + mapimg;
            ((Main)this.parent).newMap(mapimg, mapext);
        }
        else {
            System.err.println("Error zooming the map: applet is not initialized.");
            ((Main)this.parent).waiter(false);
            ((Main)this.parent).map.repaint();
        }
        return true;
    }
    
    public boolean call(final String methodName) {
        return false;
    }
    
    private void drawDottedRect(final int ix1, final int iy1, final int ix2, final int iy2, final Graphics g) {
        this.drawDottedLine(ix1, iy1, ix2, iy1, g);
        this.drawDottedLine(ix1, iy1, ix1, iy2, g);
        this.drawDottedLine(ix1, iy2, ix2, iy2, g);
        this.drawDottedLine(ix2, iy1, ix2, iy2, g);
    }
    
    private void drawDottedLine(int ix1, int iy1, int ix2, int iy2, final Graphics g) {
        if (ix1 > ix2) {
            final int tmp = ix1;
            ix1 = ix2;
            ix2 = tmp;
        }
        if (iy1 > iy2) {
            final int tmp = iy1;
            iy1 = iy2;
            iy2 = tmp;
        }
        int coord = 0;
        final int strokelen = 5;
        g.setColor(Color.red);
        if (ix1 == ix2) {
            for (coord = iy1; coord + 2 * strokelen < iy2; coord += strokelen * 2) {
                g.drawLine(ix1, coord, ix2, coord + strokelen);
            }
            g.drawLine(ix1, coord, ix2, iy2);
        }
        else {
            for (coord = ix1; coord + 2 * strokelen < ix2; coord += strokelen * 2) {
                g.drawLine(coord, iy1, coord + strokelen, iy2);
            }
            g.drawLine(coord, iy1, ix2, iy2);
        }
    }
    
    public void mouseMoved(final MouseEvent e) {
    }
    
    public void mouseDragged(final MouseEvent e) {
        if (e.getComponent().getName().compareTo("Map") == 0 && this.isEnabled() && this.isInitialised && (e.getModifiers() & 0x10) != 0x0) {
            this.zoomCorner2.setX(e.getX());
            this.zoomCorner2.setY(e.getY());
            this.zoomCorner1.calculateLL(this.prj);
            this.zoomCorner2.calculateLL(this.prj);
            this.repaintMap();
        }
    }
    
    public void mouseExited(final MouseEvent e) {
    }
    
    public void mouseEntered(final MouseEvent e) {
    }
    
    public void mouseReleased(final MouseEvent e) {
    }
    
    public void mousePressed(final MouseEvent e) {
        if (e.getComponent().getName().compareTo("Map") == 0 && this.isEnabled() && this.isInitialised) {
            this.zoomCorner1.setX(e.getX());
            this.zoomCorner1.setY(e.getY());
            this.zoomCorner2.setX(e.getX());
            this.zoomCorner2.setY(e.getY());
            this.zoomCorner1.calculateLL(this.prj);
            this.zoomCorner2.calculateLL(this.prj);
            this.repaintMap();
        }
    }
    
    public void mouseClicked(final MouseEvent e) {
        if (e.getComponent().getName().compareTo("Map") == 0 && this.isEnabled() && this.isInitialised && (e.getModifiers() & 0x10) != 0x0) {
            this.zoomPoint.setX(e.getX());
            this.zoomPoint.setY(e.getY());
            this.zoomPoint.calculateLL(this.prj);
            this.repaintMap();
        }
    }
    
    public boolean isEnabled() {
        return this.isEnabled;
    }
    
    public boolean isPaintable() {
        return this.isEnabled;
    }
    
    public void repaintMap() {
        ((Main)this.parent).map.repaint();
    }
    
    private String calculateExtend(final String request) {
        final String curMapExt = ((Main)this.parent).data.extend;
        float maplonleft = Main.atof(Main.getToken(curMapExt, 1));
        float maplatdown = Main.atof(Main.getToken(curMapExt, 2));
        float maplonright = Main.atof(Main.getToken(curMapExt, 3));
        float maplatup = Main.atof(Main.getToken(curMapExt, 4));
        String extendStr = Main.getUrlParameter(request, "imgext");
        if (extendStr != null || extendStr.length() > 0) {
            System.out.println("Extend: " + extendStr);
            extendStr = extendStr.replace('+', ' ');
            maplonleft = Main.atof(Main.getToken(extendStr, 1));
            maplatdown = Main.atof(Main.getToken(extendStr, 2));
            maplonright = Main.atof(Main.getToken(extendStr, 3));
            maplatup = Main.atof(Main.getToken(extendStr, 4));
            ((Main)this.parent).data.setExtend(maplonleft + " " + maplatdown + " " + maplonright + " " + maplatup);
            ((Main)this.parent).data.calculatePrj();
            this.prj = ((Main)this.parent).data.prj;
        }
        final float geoMapWidth = maplonright - maplonleft;
        final float geoMapHeight = maplatup - maplatdown;
        float zoomFactor = 1.0f;
        if (Main.getUrlParameter(request, "zoom") != null) {
            try {
                zoomFactor = Integer.parseInt(Main.getUrlParameter(request, "zoom"));
            }
            catch (NumberFormatException ex) {
                System.err.println("MapZoom.calculateExtend   Error parsing zoom factor in URL: " + request);
                return null;
            }
        }
        zoomFactor = ((zoomFactor > 0.0f) ? (1.0f / zoomFactor) : Math.abs(zoomFactor));
        String selectedBoxStr = Main.getUrlParameter(request, "imgbox");
        if (selectedBoxStr != null && selectedBoxStr.length() > 0 && !selectedBoxStr.equals("-1+-1+-1+-1")) {
            selectedBoxStr = selectedBoxStr.replace('+', ' ');
            final int boxleft = Main.atoi(Main.getToken(selectedBoxStr, 1));
            final int boxup = Main.atoi(Main.getToken(selectedBoxStr, 2));
            final int boxright = Main.atoi(Main.getToken(selectedBoxStr, 3));
            final int boxdown = Main.atoi(Main.getToken(selectedBoxStr, 4));
            float boxleftg = this.prj.xy2ll(boxleft, boxup)[1];
            float boxupg = this.prj.xy2ll(boxleft, boxup)[0];
            float boxrightg = this.prj.xy2ll(boxright, boxdown)[1];
            float boxdowng = this.prj.xy2ll(boxright, boxdown)[0];
            float boxWidth = boxrightg - boxleftg;
            float boxHeight = boxupg - boxdowng;
            final boolean growHorisontal = boxWidth / boxHeight < geoMapWidth / geoMapHeight;
            final float boxCenterLat = (boxupg + boxdowng) / 2.0f;
            final float boxCenterLon = (boxrightg + boxleftg) / 2.0f;
            if (growHorisontal) {
                boxWidth = boxHeight * geoMapWidth / geoMapHeight;
                boxleftg = boxCenterLon - boxWidth / 2.0f;
                boxrightg = boxCenterLon + boxWidth / 2.0f;
            }
            else {
                boxHeight = boxWidth * geoMapHeight / geoMapWidth;
                boxupg = boxCenterLat + boxHeight / 2.0f;
                boxdowng = boxCenterLat - boxHeight / 2.0f;
            }
            System.out.println("New dimensions: " + boxWidth + " " + boxHeight + " " + boxleftg + " " + boxupg + " " + boxrightg + " " + boxdowng);
            maplonleft = boxleftg;
            maplonright = boxrightg;
            maplatup = boxupg;
            maplatdown = boxdowng;
        }
        else {
            String centerPointCoordStr = Main.getUrlParameter(request, "imgxy");
            if (centerPointCoordStr == null || centerPointCoordStr.length() == 0) {
                System.err.println("MapZoom.calculateExtend   Error parsing center point in URL: " + request);
                return null;
            }
            centerPointCoordStr = centerPointCoordStr.replace('+', ' ');
            final float centerPointX = Main.atoi(Main.getToken(centerPointCoordStr, 1));
            final float centerPointY = Main.atoi(Main.getToken(centerPointCoordStr, 2));
            System.out.println("Original center point px: " + centerPointX + " " + centerPointY);
            final float centerPointGeoCoordLat = this.prj.xy2ll(centerPointX, centerPointY)[0];
            final float centerPointGeoCoordLon = this.prj.xy2ll(centerPointX, centerPointY)[1];
            maplonleft = centerPointGeoCoordLon - geoMapWidth * zoomFactor / 2.0f;
            maplonright = centerPointGeoCoordLon + geoMapWidth * zoomFactor / 2.0f;
            maplatup = centerPointGeoCoordLat + geoMapHeight * zoomFactor / 2.0f;
            maplatdown = centerPointGeoCoordLat - geoMapHeight * zoomFactor / 2.0f;
            System.out.println("Calculated center point: " + centerPointX + " " + centerPointY + " " + centerPointGeoCoordLat + " " + centerPointGeoCoordLon);
        }
        return maplonleft + " " + maplatdown + " " + maplonright + " " + maplatup;
    }
}
