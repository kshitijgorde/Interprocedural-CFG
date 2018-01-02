import java.awt.Point;
import epic.geophys.Longitude;
import epic.geophys.Latitude;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import epic.geophys.GeoPoint;
import java.awt.Font;
import java.awt.Event;
import java.awt.Color;
import java.awt.Cursor;
import epic.geophys.MapImageScale;
import java.awt.Label;
import java.util.Vector;
import java.awt.Rectangle;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class EWBMapCanvas extends Canvas implements Runnable
{
    Image imageMap;
    int imgW;
    int imgH;
    int startX;
    int startOffset;
    int startY;
    int selectedW;
    int selectedH;
    Rectangle regionRect;
    boolean hasSelected;
    int offset;
    Vector rectV;
    Label mapInfo;
    MapImageScale mapScale;
    static final String infoText = "Mouse-Drag to select region.";
    EWBLocTextPanel locTextPanel;
    private Cursor oldCursor;
    Color RustyRed;
    int topY;
    int botY;
    int leftX;
    int rghtX;
    int dx;
    int dy;
    int lonpxl;
    int latpxl;
    static int SELECT;
    static int MOVE;
    static int WEST;
    static int NORTH;
    static int EAST;
    static int SOUTH;
    static int SW;
    static int NW;
    static int SE;
    static int NE;
    int mouseStatus;
    Rectangle defRect;
    ImageLonTime lonTime;
    ImageLatTime latTime;
    String type;
    Thread myThread;
    
    static {
        EWBMapCanvas.SELECT = 0;
        EWBMapCanvas.MOVE = 1;
        EWBMapCanvas.WEST = 2;
        EWBMapCanvas.NORTH = 3;
        EWBMapCanvas.EAST = 4;
        EWBMapCanvas.SOUTH = 5;
        EWBMapCanvas.SW = 6;
        EWBMapCanvas.NW = 7;
        EWBMapCanvas.SE = 8;
        EWBMapCanvas.NE = 9;
    }
    
    public EWBMapCanvas(final Image image, final int n, final Vector vector, final Label label, final MapImageScale mapImageScale, final EWBLocTextPanel ewbLocTextPanel, final ImageLatTime latTime, final Rectangle rectangle, final String s) {
        this.RustyRed = new Color(153, 0, 0);
        this.topY = 0;
        this.botY = 0;
        this.leftX = 0;
        this.rghtX = 0;
        this.dx = 0;
        this.dy = 0;
        this.lonpxl = 0;
        this.latpxl = 0;
        this.mouseStatus = EWBMapCanvas.SELECT;
        this.myThread = null;
        this.latTime = latTime;
        this.initMapCanvas(image, n, vector, label, mapImageScale, ewbLocTextPanel, rectangle, s);
    }
    
    public EWBMapCanvas(final Image image, final int n, final Vector vector, final Label label, final MapImageScale mapImageScale, final EWBLocTextPanel ewbLocTextPanel, final ImageLonTime lonTime, final Rectangle rectangle, final String s) {
        this.RustyRed = new Color(153, 0, 0);
        this.topY = 0;
        this.botY = 0;
        this.leftX = 0;
        this.rghtX = 0;
        this.dx = 0;
        this.dy = 0;
        this.lonpxl = 0;
        this.latpxl = 0;
        this.mouseStatus = EWBMapCanvas.SELECT;
        this.myThread = null;
        this.lonTime = lonTime;
        this.initMapCanvas(image, n, vector, label, mapImageScale, ewbLocTextPanel, rectangle, s);
    }
    
    public EWBMapCanvas(final Image image, final int n, final Vector vector, final Label label, final MapImageScale mapImageScale, final EWBLocTextPanel ewbLocTextPanel, final Rectangle rectangle, final String s) {
        this.RustyRed = new Color(153, 0, 0);
        this.topY = 0;
        this.botY = 0;
        this.leftX = 0;
        this.rghtX = 0;
        this.dx = 0;
        this.dy = 0;
        this.lonpxl = 0;
        this.latpxl = 0;
        this.mouseStatus = EWBMapCanvas.SELECT;
        this.myThread = null;
        this.initMapCanvas(image, n, vector, label, mapImageScale, ewbLocTextPanel, rectangle, s);
    }
    
    private int getMouseStatus(final Rectangle rectangle, final Event event) {
        if (event.x > rectangle.x + 3 && event.x < rectangle.x + rectangle.width - 3 && event.y > rectangle.y + 3 && event.y < rectangle.y + rectangle.height - 3) {
            return EWBMapCanvas.MOVE;
        }
        if (event.x >= rectangle.x - 2 && event.x <= rectangle.x + 3 && event.y >= rectangle.y + 3 && event.y <= rectangle.y + rectangle.height - 3) {
            return EWBMapCanvas.WEST;
        }
        if (event.x >= rectangle.x + rectangle.width - 2 && event.x <= rectangle.x + rectangle.width + 3 && event.y >= rectangle.y + 3 && event.y <= rectangle.y + rectangle.height - 3) {
            return EWBMapCanvas.EAST;
        }
        if (event.x >= rectangle.x + 3 && event.x <= rectangle.x + rectangle.width - 3 && event.y >= rectangle.y - 2 && event.y <= rectangle.y + 3) {
            return EWBMapCanvas.NORTH;
        }
        if (event.x >= rectangle.x + 3 && event.x <= rectangle.x + rectangle.width - 3 && event.y >= rectangle.y + rectangle.height - 2 && event.y <= rectangle.y + rectangle.height + 3) {
            return EWBMapCanvas.SOUTH;
        }
        if (event.x >= rectangle.x + rectangle.width - 2 && event.x <= rectangle.x + rectangle.width + 3 && event.y >= rectangle.y + rectangle.height - 2 && event.y <= rectangle.y + rectangle.height + 3) {
            return EWBMapCanvas.SE;
        }
        if (event.x >= rectangle.x - 2 && event.x <= rectangle.x + 3 && event.y >= rectangle.y + rectangle.height - 2 && event.y <= rectangle.y + rectangle.height + 3) {
            return EWBMapCanvas.SW;
        }
        if (event.x >= rectangle.x - 2 && event.x <= rectangle.x + 3 && event.y >= rectangle.y - 2 && event.y <= rectangle.y + 3) {
            return EWBMapCanvas.NW;
        }
        if (event.x >= rectangle.x + rectangle.width - 2 && event.x <= rectangle.x + rectangle.width + 3 && event.y >= rectangle.y - 2 && event.y <= rectangle.y + 3) {
            return EWBMapCanvas.NE;
        }
        return EWBMapCanvas.SELECT;
    }
    
    public boolean handleEvent(final Event event) {
        this.mapInfo.setForeground(Color.blue);
        this.mapInfo.setFont(new Font("Helvetica", 1, 12));
        this.oldCursor = this.getCursor();
        if (event.x < this.defRect.x || event.x > this.defRect.x + this.defRect.width || event.y < this.defRect.y || event.y > this.defRect.y + this.defRect.height) {
            this.mapInfo.setText("Mouse-Drag to select region.");
            return true;
        }
        switch (event.id) {
            case 501: {
                if (this.mouseStatus == EWBMapCanvas.SELECT) {
                    this.startX = event.x;
                    this.startY = event.y;
                    this.setCursor(Cursor.getPredefinedCursor(1));
                    this.regionRect = new Rectangle(this.startX, this.startY, 0, 0);
                    this.hasSelected = false;
                }
                else {
                    this.startX = this.leftX;
                    this.startY = this.topY;
                    this.resetCursor(this.mouseStatus);
                    this.hasSelected = true;
                    if (this.mouseStatus == EWBMapCanvas.MOVE) {
                        this.dx = event.x - this.leftX;
                        this.dy = event.y - this.topY;
                    }
                    else if (this.mouseStatus == EWBMapCanvas.SW) {
                        this.startX = this.leftX + this.selectedW;
                    }
                    else if (this.mouseStatus == EWBMapCanvas.NE) {
                        this.startY = this.topY + this.selectedH;
                    }
                    else if (this.mouseStatus == EWBMapCanvas.NW) {
                        this.startY = this.topY + this.selectedH;
                        this.startX = this.leftX + this.selectedW;
                    }
                }
                this.repaint();
                return true;
            }
            case 502: {
                this.regionRect.x = this.leftX;
                this.regionRect.y = this.topY;
                this.regionRect.width = this.selectedW;
                this.regionRect.height = this.selectedH;
                final GeoPoint geoPoint = this.mapScale.toGeoPoint(this.regionRect.x - this.offset, this.regionRect.y);
                final GeoPoint geoPoint2 = this.mapScale.toGeoPoint(this.regionRect.x - this.offset + this.regionRect.width, this.regionRect.y + this.regionRect.height);
                if (this.type.equals("yt")) {
                    this.locTextPanel.setText4Time(geoPoint.Lat().toString(""), geoPoint2.Lat().toString(""), geoPoint.Lon().LonValue(), geoPoint2.Lon().LonValue());
                }
                else if (this.type.equals("tx")) {
                    this.locTextPanel.setText4Time(geoPoint.Lat().LatValue(), geoPoint2.Lat().LatValue(), geoPoint.Lon().toString(""), geoPoint2.Lon().toString(""));
                }
                else {
                    this.locTextPanel.setText4(geoPoint.Lat().toString(""), geoPoint2.Lat().toString(""), geoPoint.Lon().toString(""), geoPoint2.Lon().toString(""));
                }
                this.resetCursor(this.mouseStatus = this.getMouseStatus(this.regionRect, event));
                if (this.regionRect.width > 0 && this.regionRect.height > 0) {
                    this.hasSelected = true;
                }
                return true;
            }
            case 506: {
                this.lonpxl = event.x - this.offset;
                this.latpxl = event.y;
                if (this.mouseStatus == EWBMapCanvas.SOUTH) {
                    this.selectedH = event.y - this.topY;
                    if (this.selectedH < 0) {
                        this.mouseStatus = EWBMapCanvas.NORTH;
                    }
                }
                else if (this.mouseStatus == EWBMapCanvas.NORTH) {
                    this.selectedH = this.selectedH + this.topY - event.y;
                    this.topY = event.y;
                    if (this.selectedH < 0) {
                        this.mouseStatus = EWBMapCanvas.SOUTH;
                    }
                }
                else if (this.mouseStatus == EWBMapCanvas.EAST) {
                    this.selectedW = event.x - this.leftX;
                    if (this.selectedW < 0) {
                        this.mouseStatus = EWBMapCanvas.WEST;
                    }
                }
                else if (this.mouseStatus == EWBMapCanvas.WEST) {
                    this.selectedW = this.selectedW + this.leftX - event.x;
                    this.leftX = event.x;
                    if (this.selectedW < 0) {
                        this.mouseStatus = EWBMapCanvas.EAST;
                    }
                }
                else if (this.mouseStatus == EWBMapCanvas.MOVE) {
                    this.leftX = event.x - this.dx;
                    this.topY = event.y - this.dy;
                }
                else {
                    this.topY = Math.min(this.startY, event.y);
                    this.botY = Math.max(this.startY, event.y);
                    this.leftX = Math.min(this.startX, event.x);
                    this.rghtX = Math.max(this.startX, event.x);
                    this.selectedW = this.rghtX - this.leftX;
                    this.selectedH = this.botY - this.topY;
                }
                this.regionRect = new Rectangle(this.leftX, this.topY, this.selectedW, this.selectedH);
                final GeoPoint geoPoint3 = this.mapScale.toGeoPoint(this.lonpxl, this.latpxl);
                String text;
                if (this.type.equals("yt")) {
                    text = "Date: " + this.lonTime.getDateStr(geoPoint3.Lon().LonValue()) + ",  Lat: " + geoPoint3.Lat().toString("");
                }
                else if (this.type.equals("tx")) {
                    text = "Lon: " + geoPoint3.Lon().toString("") + ",  Date: " + this.latTime.getDateStr(geoPoint3.Lat().LatValue());
                }
                else {
                    text = geoPoint3.toString("", "");
                }
                this.mapInfo.setText(text);
                this.repaint();
                return true;
            }
            case 503: {
                final GeoPoint geoPoint4 = this.mapScale.toGeoPoint(event.x - this.offset, event.y);
                String text2;
                if (this.type.equals("yt")) {
                    text2 = "Date: " + this.lonTime.getDateStr(geoPoint4.Lon().LonValue()) + ",  Lat: " + geoPoint4.Lat().toString("");
                }
                else if (this.type.equals("tx")) {
                    text2 = "Lon: " + geoPoint4.Lon().toString("") + ",  Date: " + this.latTime.getDateStr(geoPoint4.Lat().LatValue());
                }
                else {
                    text2 = geoPoint4.toString("", "");
                }
                this.mapInfo.setText(text2);
                this.resetCursor(this.mouseStatus = this.getMouseStatus(this.regionRect, event));
                return true;
            }
            case 505: {
                this.resetCursor(this.mouseStatus = this.getMouseStatus(this.regionRect, event));
                this.mapInfo.setText("Mouse-Drag to select region.");
                return true;
            }
            case 201: {
                System.exit(0);
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    private void initMapCanvas(final Image imageMap, final int offset, final Vector rectV, final Label mapInfo, final MapImageScale mapScale, final EWBLocTextPanel locTextPanel, final Rectangle defRect, final String type) {
        this.type = type;
        this.resize(imageMap.getWidth(this), imageMap.getHeight(this));
        this.setBackground(Color.white);
        this.imageMap = imageMap;
        this.offset = offset;
        this.rectV = rectV;
        this.regionRect = new Rectangle(0, 0, 0, 0);
        this.mapInfo = mapInfo;
        this.mapScale = mapScale;
        this.locTextPanel = locTextPanel;
        this.defRect = defRect;
        this.imgW = imageMap.getWidth(this);
        this.imgH = imageMap.getHeight(this);
    }
    
    public void paint(final Graphics graphics) {
        final int width = this.imageMap.getWidth(this);
        final int height = this.imageMap.getHeight(this);
        if (this.offset > 0) {
            graphics.clipRect(0, 0, width, height);
            graphics.translate(this.offset, 0);
            graphics.drawImage(this.imageMap, 0, 0, width, height, this);
            graphics.translate(-width, 0);
            graphics.drawImage(this.imageMap, 0, 0, width, height, this);
        }
        else {
            graphics.drawImage(this.imageMap, 0, 0, this);
        }
        if (this.offset != 0) {
            graphics.translate(width - this.offset, 0);
        }
        if (this.regionRect.width > 0 && this.regionRect.height > 0) {
            graphics.setColor(this.RustyRed);
            graphics.fillRect(this.regionRect.x - 2, this.regionRect.y - 2, 5, 5);
            graphics.fillRect(this.regionRect.x + this.regionRect.width - 2, this.regionRect.y + this.regionRect.height - 2, 5, 5);
            graphics.fillRect(this.regionRect.x - 2, this.regionRect.y + this.regionRect.height - 2, 5, 5);
            graphics.fillRect(this.regionRect.x + this.regionRect.width - 2, this.regionRect.y - 2, 5, 5);
            graphics.fillRect(this.regionRect.x + this.regionRect.width / 2 - 2, this.regionRect.y + this.regionRect.height - 2, 5, 5);
            graphics.fillRect(this.regionRect.x + this.regionRect.width / 2 - 2, this.regionRect.y - 2, 5, 5);
            graphics.fillRect(this.regionRect.x + this.regionRect.width - 2, this.regionRect.y + this.regionRect.height / 2 - 2, 5, 5);
            graphics.fillRect(this.regionRect.x - 2, this.regionRect.y + this.regionRect.height / 2 - 2, 5, 5);
        }
        graphics.setColor(Color.white);
        if (this.rectV.size() > 1) {
            for (int i = 0; i < this.rectV.size(); ++i) {
                final Rectangle rectangle = this.rectV.elementAt(i);
                graphics.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
                if (rectangle.width + rectangle.x > width) {
                    graphics.drawRect(-2, rectangle.y, rectangle.x + rectangle.width - width, rectangle.height);
                }
                if (this.regionRect.x < 0) {
                    graphics.drawRect(width + rectangle.x, rectangle.y, Math.abs(rectangle.x), rectangle.height);
                }
            }
        }
        graphics.setColor(this.RustyRed);
        graphics.drawRect(this.regionRect.x, this.regionRect.y, this.regionRect.width, this.regionRect.height);
        if (this.regionRect.width + this.regionRect.x > width) {
            graphics.drawRect(-2, this.regionRect.y, this.regionRect.x + this.regionRect.width - width, this.regionRect.height);
        }
        if (this.regionRect.x < 0) {
            graphics.drawRect(width + this.regionRect.x, this.regionRect.y, Math.abs(this.regionRect.x), this.regionRect.height);
        }
    }
    
    private void resetCursor(final int n) {
        if (n == EWBMapCanvas.MOVE) {
            this.setCursor(Cursor.getPredefinedCursor(13));
        }
        else if (n == EWBMapCanvas.NORTH) {
            this.setCursor(Cursor.getPredefinedCursor(8));
        }
        else if (n == EWBMapCanvas.SOUTH) {
            this.setCursor(Cursor.getPredefinedCursor(9));
        }
        else if (n == EWBMapCanvas.WEST) {
            this.setCursor(Cursor.getPredefinedCursor(10));
        }
        else if (n == EWBMapCanvas.EAST) {
            this.setCursor(Cursor.getPredefinedCursor(11));
        }
        else if (n == EWBMapCanvas.NE) {
            this.setCursor(Cursor.getPredefinedCursor(7));
        }
        else if (n == EWBMapCanvas.NW) {
            this.setCursor(Cursor.getPredefinedCursor(6));
        }
        else if (n == EWBMapCanvas.SE) {
            this.setCursor(Cursor.getPredefinedCursor(5));
        }
        else if (n == EWBMapCanvas.SW) {
            this.setCursor(Cursor.getPredefinedCursor(4));
        }
        else {
            this.setCursor(Cursor.getPredefinedCursor(1));
        }
    }
    
    public void run() {
    }
    
    public void setDefault() {
        this.regionRect = new Rectangle(0, 0, 0, 0);
        this.repaint();
    }
    
    public void setValues(final Latitude latitude, final Latitude latitude2, final Longitude longitude, final Longitude longitude2) {
        final Point point = this.mapScale.toPoint(longitude, latitude);
        final Point point2 = this.mapScale.toPoint(longitude2, latitude2);
        this.regionRect = new Rectangle(point.x, point.y, point2.x - point.x, point2.y - point.y);
        this.leftX = this.regionRect.x;
        this.topY = this.regionRect.y;
        this.selectedH = this.regionRect.height;
        this.selectedW = this.regionRect.width;
        this.repaint();
    }
    
    public void start() {
        if (this.myThread == null) {
            (this.myThread = new Thread(this)).setName("ImageMap");
            this.myThread.start();
        }
    }
    
    public synchronized void stop() {
        this.myThread = null;
        this.notify();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
