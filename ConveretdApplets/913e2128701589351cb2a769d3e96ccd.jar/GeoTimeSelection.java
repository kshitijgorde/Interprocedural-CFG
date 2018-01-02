import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.image.ImageObserver;
import java.awt.Point;
import java.awt.TextField;
import java.awt.Event;
import epic.geophys.LonInvalidException;
import epic.geophys.Longitude;
import epic.geophys.LatInvalidException;
import epic.geophys.Latitude;
import java.util.Calendar;
import java.util.Vector;
import epic.geophys.GeoPoint;
import java.awt.Rectangle;
import epic.geophys.MapImageScale;
import java.awt.Label;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class GeoTimeSelection extends Applet
{
    private Image imageMap;
    private Image imageBtn;
    private int imgW;
    private int imgH;
    private Label mapInfo;
    private static final String infoText = "Mouse-Drag to select region. ";
    private int offset;
    private MapImageScale mapScale;
    private Rectangle regionRect;
    private Rectangle defaultRect;
    private GeoPoint nw;
    private GeoPoint se;
    private EWBMapCanvas mapCanvas;
    private EWBLocTextPanel locTextPanel;
    private EWBImgBtnCanvas btnImgCanvas;
    private Vector rectV;
    private EWBLocationStr loc;
    private EWBLocationStr defaultLoc;
    Calendar cal;
    private ImageLonTime lonTime;
    private ImageLatTime latTime;
    private String type;
    
    public GeoTimeSelection() {
        this.cal = Calendar.getInstance();
    }
    
    public Latitude getLatitude(final String s) {
        try {
            return new Latitude(s);
        }
        catch (LatInvalidException ex) {
            return null;
        }
    }
    
    public Longitude getLongitude(final String s) {
        try {
            return new Longitude(s);
        }
        catch (LonInvalidException ex) {
            return null;
        }
    }
    
    public String getMaxDate() {
        if (this.type.equals("yt")) {
            return this.lonTime.toDateStr2(this.locTextPanel.getEastText());
        }
        if (this.type.equals("tx")) {
            return this.latTime.toDateStr2(this.locTextPanel.getNorthText());
        }
        return null;
    }
    
    public double getMaxLat() {
        String s;
        if (this.type.equals("tx")) {
            s = this.latTime.dateToLatStr(this.locTextPanel.getNorthText());
        }
        else {
            s = this.locTextPanel.getNorthText();
        }
        return new GeoPoint(this.getLongitude(this.locTextPanel.getWestText()), this.getLatitude(s)).Lat().LatValue();
    }
    
    public double getMaxLon() {
        String s;
        if (this.type.equals("yt")) {
            s = this.lonTime.dateToLonStr(this.locTextPanel.getEastText());
        }
        else {
            s = this.locTextPanel.getEastText();
        }
        return new GeoPoint(this.getLongitude(s), this.getLatitude(this.locTextPanel.getSouthText())).Lon().LonValue();
    }
    
    public String getMinDate() {
        if (this.type.equals("yt")) {
            return this.lonTime.toDateStr2(this.locTextPanel.getWestText());
        }
        if (this.type.equals("tx")) {
            return this.latTime.toDateStr2(this.locTextPanel.getSouthText());
        }
        return null;
    }
    
    public double getMinLat() {
        String s;
        if (this.type.equals("tx")) {
            s = this.latTime.dateToLatStr(this.locTextPanel.getSouthText());
        }
        else {
            s = this.locTextPanel.getSouthText();
        }
        return new GeoPoint(this.getLongitude(this.locTextPanel.getEastText()), this.getLatitude(s)).Lat().LatValue();
    }
    
    public double getMinLon() {
        String s;
        if (this.type.equals("yt")) {
            s = this.lonTime.dateToLonStr(this.locTextPanel.getWestText());
        }
        else {
            s = this.locTextPanel.getWestText();
        }
        return new GeoPoint(this.getLongitude(s), this.getLatitude(this.locTextPanel.getNorthText())).Lon().LonValue();
    }
    
    public String getType() {
        return this.type;
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                if (event.target instanceof TextField) {
                    this.locTextPanel.getValues();
                }
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
    
    public void init() {
        this.type = this.getParameter("type");
        final int intValue = new Integer(this.getParameter("left"));
        final int intValue2 = new Integer(this.getParameter("right"));
        final int intValue3 = new Integer(this.getParameter("top"));
        final int intValue4 = new Integer(this.getParameter("bottom"));
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        double n7;
        double n8;
        double n9;
        double n10;
        String s;
        String s2;
        String s3;
        String s4;
        if (this.type.equals("yt")) {
            n7 = new Double(this.getParameter("north"));
            n8 = new Double(this.getParameter("south"));
            n9 = -180.0;
            n10 = 180.0;
            s = this.getParameter("maxlat");
            s2 = this.getParameter("minlat");
            s3 = "180 W";
            s4 = "180 E";
            this.offset = 0;
            n = (int)(Object)new Double(this.getParameter("startYear"));
            n2 = (int)(Object)new Double(this.getParameter("startMonth"));
            n3 = (int)(Object)new Double(this.getParameter("startDay"));
            n4 = (int)(Object)new Double(this.getParameter("endYear"));
            n5 = (int)(Object)new Double(this.getParameter("endMonth"));
            n6 = (int)(Object)new Double(this.getParameter("endDay"));
            this.lonTime = new ImageLonTime(n, n2, n3, n4, n5, n6);
        }
        else if (this.type.equals("tx")) {
            n9 = new Double(this.getParameter("west"));
            n10 = new Double(this.getParameter("east"));
            n7 = 90.0;
            n8 = -90.0;
            s4 = this.getParameter("maxlon");
            s3 = this.getParameter("minlon");
            s2 = "90 S";
            s = "90 N";
            this.offset = 0;
            n = (int)(Object)new Double(this.getParameter("startYear"));
            n2 = (int)(Object)new Double(this.getParameter("startMonth"));
            n3 = (int)(Object)new Double(this.getParameter("startDay"));
            n4 = (int)(Object)new Double(this.getParameter("endYear"));
            n5 = (int)(Object)new Double(this.getParameter("endMonth"));
            n6 = (int)(Object)new Double(this.getParameter("endDay"));
            this.latTime = new ImageLatTime(n, n2, n3, n4, n5, n6);
        }
        else {
            n7 = new Double(this.getParameter("north"));
            n8 = new Double(this.getParameter("south"));
            n9 = new Double(this.getParameter("west"));
            n10 = new Double(this.getParameter("east"));
            s4 = this.getParameter("maxlon");
            s3 = this.getParameter("minlon");
            s = this.getParameter("maxlat");
            s2 = this.getParameter("minlat");
            this.offset = new Integer(this.getParameter("offset"));
        }
        this.mapScale = new MapImageScale(new Point(intValue, intValue3), new Point(intValue2, intValue4), new GeoPoint(n9, n7), new GeoPoint(n10, n8));
        this.imageMap = this.getImage(this.getCodeBase(), this.getParameter("mapimagesrc"));
        this.imgW = this.imageMap.getWidth(this);
        this.imgH = this.imageMap.getHeight(this);
        this.imageBtn = this.getImage(this.getCodeBase(), this.getParameter("btnimagesrc"));
        final MediaTracker mediaTracker = new MediaTracker(this);
        try {
            mediaTracker.addImage(this.imageMap, 0);
            mediaTracker.addImage(this.imageBtn, 1);
            mediaTracker.waitForAll(0L);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        (this.mapInfo = new Label("Mouse-Drag to select region. ")).setForeground(Color.blue);
        this.mapInfo.setFont(new Font("Helvetica", 1, 12));
        if (this.type.equals("yt") || this.type.equals("tx")) {
            this.defaultLoc = new EWBLocationStr(s, s2, s4, s3, String.valueOf(n) + "-" + n2 + "-" + n3, String.valueOf(n4) + "-" + n5 + "-" + n6);
        }
        else {
            this.defaultLoc = new EWBLocationStr(s, s2, s4, s3);
        }
        this.rectV = new Vector();
        for (int intValue5 = new Integer(this.getParameter("numDatasets")), i = 1; i <= intValue5; ++i) {
            EWBLocationStr defaultLoc;
            if (this.type.equals("yt") || this.type.equals("tx")) {
                defaultLoc = this.defaultLoc;
            }
            else {
                defaultLoc = new EWBLocationStr(this.getParameter("maxlat" + new Integer(i).toString()), this.getParameter("minlat" + new Integer(i).toString()), this.getParameter("minlon" + new Integer(i).toString()), this.getParameter("maxlon" + new Integer(i).toString()));
            }
            this.rectV.addElement(this.setRect(defaultLoc, this.mapScale, this.imgW, this.offset));
        }
        this.setBackground(Color.white);
        this.regionRect = new Rectangle(0, 0, 0, 0);
        this.defaultRect = new Rectangle(intValue, intValue3, intValue2 - intValue, intValue4 - intValue3);
        if (this.type.equals("yt")) {
            this.locTextPanel = new EWBLocTextPanel(this.defaultLoc, this.lonTime);
            this.mapCanvas = new EWBMapCanvas(this.imageMap, this.offset, this.rectV, this.mapInfo, this.mapScale, this.locTextPanel, this.lonTime, this.defaultRect, this.type);
        }
        else if (this.type.equals("tx")) {
            this.locTextPanel = new EWBLocTextPanel(this.defaultLoc, this.latTime);
            this.mapCanvas = new EWBMapCanvas(this.imageMap, this.offset, this.rectV, this.mapInfo, this.mapScale, this.locTextPanel, this.latTime, this.defaultRect, this.type);
        }
        else {
            this.locTextPanel = new EWBLocTextPanel(this.defaultLoc);
            this.mapCanvas = new EWBMapCanvas(this.imageMap, this.offset, this.rectV, this.mapInfo, this.mapScale, this.locTextPanel, this.defaultRect, this.type);
        }
        this.locTextPanel.init(this.mapCanvas);
        this.btnImgCanvas = new EWBImgBtnCanvas(this.imageBtn, this.locTextPanel, this.mapCanvas);
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        panel.add("Center", this.mapCanvas);
        panel.add("South", this.mapInfo);
        final Panel panel2 = new Panel();
        panel2.setLayout(new BorderLayout(0, 10));
        panel2.add("Center", this.locTextPanel.makeLayout(this.btnImgCanvas));
        this.setLayout(new FlowLayout(0, 10, 0));
        this.add(panel);
        this.add(panel2);
    }
    
    public void setDefault() {
        this.mapCanvas.setDefault();
        this.locTextPanel.setDefault();
    }
    
    public Rectangle setRect(final EWBLocationStr ewbLocationStr, final MapImageScale mapImageScale, final int n, final int n2) {
        final GeoPoint geoPoint = new GeoPoint(this.getLongitude(ewbLocationStr.w), this.getLatitude(ewbLocationStr.n));
        final GeoPoint geoPoint2 = new GeoPoint(this.getLongitude(ewbLocationStr.e), this.getLatitude(ewbLocationStr.s));
        final Point point = mapImageScale.toPoint(geoPoint);
        final Point point2 = mapImageScale.toPoint(geoPoint2);
        final int x = point.x;
        final int y = point.y;
        int x2 = point2.x;
        final int y2 = point2.y;
        if (x2 < x) {
            x2 += n;
        }
        int n3;
        if (x >= n2) {
            n3 = x - n2;
        }
        else {
            n3 = x - n2 + n;
        }
        return new Rectangle(n3, y, Math.abs(x2 - x) + 1, Math.abs(y2 - y) + 1);
    }
}
