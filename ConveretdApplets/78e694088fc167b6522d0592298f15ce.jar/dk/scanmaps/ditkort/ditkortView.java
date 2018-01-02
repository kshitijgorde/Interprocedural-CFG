// 
// Decompiled by Procyon v0.5.30
// 

package dk.scanmaps.ditkort;

import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.MouseListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.net.MalformedURLException;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.net.URL;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class ditkortView extends JPanel implements ActionListener
{
    static final long serialVersionUID = 0L;
    private URL c_imageArrowN;
    private URL c_imageArrowNE;
    private URL c_imageArrowE;
    private URL c_imageArrowSE;
    private URL c_imageArrowS;
    private URL c_imageArrowSW;
    private URL c_imageArrowW;
    private URL c_imageArrowNW;
    private JLabel c_ditkortLabel;
    private Boolean c_mapShown;
    public Boolean c_kortUdsnitShown;
    formatCheckboxes checkboxes;
    public int scrollFactor;
    private Login c_l;
    private String c_mapurl;
    public String c_serviceNamePrint;
    public String c_serviceNamePrintLayer;
    private String c_serviceNameLayer;
    private String c_serviceName;
    public float c_centerX;
    public float c_centerY;
    public float c_zoomCoordX;
    public float c_zoomCoordY;
    public float c_wholeMapCenterX;
    public float c_wholeMapCenterY;
    public float c_zoomViewCenterX;
    public float c_zoomViewCenterY;
    private int c_mapSizeX;
    private int c_mapSizeY;
    private int c_navigationImageULX;
    private int c_navigationImageULY;
    private int c_navigationImagePixelSize;
    public float c_pixelSize;
    public int c_pixelPrmm;
    public int c_widthInmm;
    public int c_heightInmm;
    private JPanel topButtons;
    private JPanel bottomButtons;
    public JButton nwButton;
    public JButton nButton;
    public JButton neButton;
    public JButton eButton;
    public JButton seButton;
    public JButton sButton;
    public JButton swButton;
    public JButton wButton;
    public float ULX;
    public float ULY;
    public float LRX;
    public float LRY;
    private int c_updateMapCounter;
    private CResourceManager myResource;
    
    public int getPrintWidth() {
        return this.c_widthInmm * this.c_pixelPrmm;
    }
    
    public int getPrintHeight() {
        return this.c_heightInmm * this.c_pixelPrmm;
    }
    
    public ditkortView() {
        this.c_ditkortLabel = new JLabel();
        this.c_mapShown = new Boolean(false);
        this.c_kortUdsnitShown = new Boolean(false);
        this.scrollFactor = 4;
        this.c_l = new Login();
        this.c_serviceNamePrint = Constant.servicename_DTK25;
        this.c_serviceNamePrintLayer = Constant.layerName_DTK25;
        this.c_serviceNameLayer = this.c_serviceNamePrintLayer;
        this.c_serviceName = this.c_serviceNamePrint;
        this.c_centerX = 588450.0f;
        this.c_centerY = 6139571.0f;
        this.c_mapSizeX = 564;
        this.c_mapSizeY = 450;
        this.c_navigationImageULX = 433234;
        this.c_navigationImageULY = 6406491;
        this.c_navigationImagePixelSize = 2022;
        this.c_pixelSize = 1.25f;
        this.c_pixelPrmm = 20;
        this.c_widthInmm = 564;
        this.c_heightInmm = 450;
        this.c_updateMapCounter = 0;
        this.myResource = CResourceManager.instance();
        try {
            final String urlBase = Constant.imageURL;
            this.c_imageArrowN = new URL(String.valueOf(urlBase) + "arrow_n.gif");
            this.c_imageArrowNE = new URL(String.valueOf(urlBase) + "arrow_ne.gif");
            this.c_imageArrowE = new URL(String.valueOf(urlBase) + "arrow_e.gif");
            this.c_imageArrowSE = new URL(String.valueOf(urlBase) + "arrow_se.gif");
            this.c_imageArrowS = new URL(String.valueOf(urlBase) + "arrow_s.gif");
            this.c_imageArrowSW = new URL(String.valueOf(urlBase) + "arrow_sw.gif");
            this.c_imageArrowW = new URL(String.valueOf(urlBase) + "arrow_w.gif");
            this.c_imageArrowNW = new URL(String.valueOf(urlBase) + "arrow_nw.gif");
        }
        catch (MalformedURLException e) {
            System.err.println("Couldn't set arrow icons");
        }
        final Dimension ditkortDim = new Dimension(564, 450);
        this.c_ditkortLabel.setMinimumSize(ditkortDim);
        this.c_ditkortLabel.setMaximumSize(ditkortDim);
        this.c_ditkortLabel.setPreferredSize(ditkortDim);
        this.setMapURL(this.c_serviceName, this.c_serviceNameLayer);
        this.setLayout(new BorderLayout());
        this.createDitkortView();
    }
    
    public void changeLocaleInDitkortView() {
        this.nwButton.setToolTipText(this.myResource.getResource("ditkortview.knap.tip.nw"));
        this.nButton.setToolTipText(this.myResource.getResource("ditkortview.knap.tip.n"));
        this.neButton.setToolTipText(this.myResource.getResource("ditkortview.knap.tip.ne"));
        this.eButton.setToolTipText(this.myResource.getResource("ditkortview.knap.tip.e"));
        this.seButton.setToolTipText(this.myResource.getResource("ditkortview.knap.tip.se"));
        this.sButton.setToolTipText(this.myResource.getResource("ditkortview.knap.tip.s"));
        this.swButton.setToolTipText(this.myResource.getResource("ditkortview.knap.tip.sw"));
        this.wButton.setToolTipText(this.myResource.getResource("ditkortview.knap.tip.w"));
    }
    
    public void setMapURL(final String serviceName, final String serviceNameLayer) {
        this.c_serviceName = serviceName;
        this.c_serviceNameLayer = serviceNameLayer;
        this.c_mapurl = "http://kortforsyningen.kms.dk/service?servicename=" + this.c_serviceName + "&" + "service=WMS1&" + "version=1.1.1&" + "request=GetMap&" + "srs=EPSG:25832&" + "format=" + Constant.imageFormat + "&" + "width=" + this.c_mapSizeX + "&" + "height=" + this.c_mapSizeY + "&" + "exceptions=application/vnd.ogc.se_xml&" + "layers=" + this.c_serviceNameLayer;
    }
    
    public void setMapURL(final String serviceName, final String serviceNameLayer, final String specialRegion) {
        this.c_serviceName = serviceName;
        this.c_serviceNameLayer = serviceNameLayer;
        if (specialRegion.equals("2513")) {
            this.c_mapSizeY = 360;
        }
        else if (specialRegion.equals("none")) {
            this.c_mapSizeY = 450;
        }
        this.c_mapurl = "http://kortforsyningen.kms.dk/service?servicename=" + this.c_serviceName + "&" + "service=WMS1&" + "version=1.1.1&" + "request=GetMap&" + "srs=EPSG:25832&" + "format=" + Constant.imageFormat + "&" + "width=" + this.c_mapSizeX + "&" + "height=" + this.c_mapSizeY + "&" + "exceptions=application/vnd.ogc.se_xml&" + "layers=" + this.c_serviceNameLayer;
    }
    
    public String getMapURL() {
        return this.c_mapurl;
    }
    
    public float getCenterX() {
        return this.c_centerX;
    }
    
    public float getCenterY() {
        return this.c_centerY;
    }
    
    public boolean isMapShown() {
        return this.c_mapShown && this.c_mapShown;
    }
    
    public void actionPerformed(final ActionEvent e) {
    }
    
    public void disableButtons() {
        this.nwButton.setEnabled(false);
        this.nButton.setEnabled(false);
        this.neButton.setEnabled(false);
        this.eButton.setEnabled(false);
        this.seButton.setEnabled(false);
        this.sButton.setEnabled(false);
        this.swButton.setEnabled(false);
        this.wButton.setEnabled(false);
    }
    
    public void enableButtons() {
        this.nwButton.setEnabled(true);
        this.nButton.setEnabled(true);
        this.neButton.setEnabled(true);
        this.eButton.setEnabled(true);
        this.seButton.setEnabled(true);
        this.sButton.setEnabled(true);
        this.swButton.setEnabled(true);
        this.wButton.setEnabled(true);
    }
    
    private void createDitkortView() {
        (this.nwButton = new JButton(new ImageIcon(this.c_imageArrowNW))).setPreferredSize(new Dimension(50, 20));
        this.nwButton.setMaximumSize(new Dimension(50, 20));
        this.nwButton.setMinimumSize(new Dimension(50, 20));
        this.nwButton.setActionCommand("moveUpLeft");
        this.nwButton.setToolTipText(this.myResource.getResource("ditkortview.knap.tip.nw"));
        this.nwButton.addActionListener(this);
        this.nwButton.addMouseListener(new myMouseListener());
        (this.nButton = new JButton(new ImageIcon(this.c_imageArrowN))).setPreferredSize(new Dimension(504, 20));
        this.nButton.setActionCommand("moveUp");
        this.nButton.setToolTipText(this.myResource.getResource("ditkortview.knap.tip.n"));
        this.nButton.addActionListener(this);
        this.nButton.addMouseListener(new myMouseListener());
        (this.neButton = new JButton(new ImageIcon(this.c_imageArrowNE))).setPreferredSize(new Dimension(50, 20));
        this.neButton.setActionCommand("moveUpRight");
        this.neButton.setToolTipText(this.myResource.getResource("ditkortview.knap.tip.ne"));
        this.neButton.addActionListener(this);
        this.neButton.addMouseListener(new myMouseListener());
        (this.topButtons = new JPanel(new FlowLayout(3, 0, 0))).add(this.nwButton);
        this.topButtons.add(this.nButton);
        this.topButtons.add(this.neButton);
        this.add(this.topButtons, "First");
        (this.eButton = new JButton(new ImageIcon(this.c_imageArrowE))).setPreferredSize(new Dimension(20, 20));
        this.eButton.setMaximumSize(new Dimension(20, 20));
        this.eButton.setMinimumSize(new Dimension(20, 20));
        this.eButton.setActionCommand("moveRight");
        this.eButton.setToolTipText(this.myResource.getResource("ditkortview.knap.tip.e"));
        this.eButton.addActionListener(this);
        this.eButton.addMouseListener(new myMouseListener());
        this.add(this.eButton, "After");
        (this.seButton = new JButton(new ImageIcon(this.c_imageArrowSE))).setPreferredSize(new Dimension(50, 20));
        this.seButton.setActionCommand("moveDownRight");
        this.seButton.setToolTipText(this.myResource.getResource("ditkortview.knap.tip.se"));
        this.seButton.addActionListener(this);
        this.seButton.addMouseListener(new myMouseListener());
        (this.sButton = new JButton(new ImageIcon(this.c_imageArrowS))).setPreferredSize(new Dimension(504, 20));
        this.sButton.setToolTipText(this.myResource.getResource("ditkortview.knap.tip.s"));
        this.sButton.setActionCommand("moveDown");
        this.sButton.addActionListener(this);
        this.sButton.addMouseListener(new myMouseListener());
        (this.swButton = new JButton(new ImageIcon(this.c_imageArrowSW))).setPreferredSize(new Dimension(50, 20));
        this.swButton.setActionCommand("moveDownLeft");
        this.swButton.setToolTipText(this.myResource.getResource("ditkortview.knap.tip.sw"));
        this.swButton.addActionListener(this);
        this.swButton.addMouseListener(new myMouseListener());
        (this.bottomButtons = new JPanel(new FlowLayout(3, 0, 0))).add(this.swButton);
        this.bottomButtons.add(this.sButton);
        this.bottomButtons.add(this.seButton);
        this.add(this.bottomButtons, "Last");
        (this.wButton = new JButton(new ImageIcon(this.c_imageArrowW))).setPreferredSize(new Dimension(20, 20));
        this.wButton.setMaximumSize(new Dimension(20, 20));
        this.wButton.setMinimumSize(new Dimension(20, 20));
        this.wButton.setActionCommand("moveLeft");
        this.wButton.setToolTipText(this.myResource.getResource("ditkortview.knap.tip.w"));
        this.wButton.addActionListener(this);
        this.wButton.addMouseListener(new myMouseListener());
        this.add(this.wButton, "Before");
        this.c_ditkortLabel.setAlignmentX(0.0f);
        this.add(this.c_ditkortLabel, "Center");
    }
    
    public String getTicket() {
        return this.c_l.getTicket();
    }
    
    public void setMapDimension(final int width, final int height) {
        this.c_mapSizeX = width;
        this.c_mapSizeY = height;
    }
    
    public boolean showZoomViewOne() {
        this.c_wholeMapCenterX = this.c_centerX;
        this.c_wholeMapCenterY = this.c_centerY;
        this.c_zoomViewCenterX = this.c_centerX - this.getPrintWidth() / 4 * this.c_pixelSize;
        this.c_zoomViewCenterY = this.c_centerY + this.getPrintHeight() / 4 * this.c_pixelSize;
        this.c_centerX = this.c_zoomViewCenterX;
        this.c_centerY = this.c_zoomViewCenterY;
        this.showZoomViewMap();
        this.c_centerX = this.c_wholeMapCenterX;
        this.c_centerY = this.c_wholeMapCenterY;
        this.c_kortUdsnitShown = true;
        this.resetMapCoords();
        return true;
    }
    
    public boolean showZoomViewTwo() {
        this.c_wholeMapCenterX = this.c_centerX;
        this.c_wholeMapCenterY = this.c_centerY;
        this.c_zoomViewCenterX = this.c_centerX + this.getPrintWidth() / 4 * this.c_pixelSize;
        this.c_zoomViewCenterY = this.c_centerY + this.getPrintHeight() / 4 * this.c_pixelSize;
        this.c_centerX = this.c_zoomViewCenterX;
        this.c_centerY = this.c_zoomViewCenterY;
        this.showZoomViewMap();
        this.c_centerX = this.c_wholeMapCenterX;
        this.c_centerY = this.c_wholeMapCenterY;
        this.c_kortUdsnitShown = true;
        this.resetMapCoords();
        return true;
    }
    
    public boolean showZoomViewThree() {
        this.c_wholeMapCenterX = this.c_centerX;
        this.c_wholeMapCenterY = this.c_centerY;
        this.c_zoomViewCenterX = this.c_centerX - this.getPrintWidth() / 4 * this.c_pixelSize;
        this.c_zoomViewCenterY = this.c_centerY - this.getPrintHeight() / 4 * this.c_pixelSize;
        this.c_centerX = this.c_zoomViewCenterX;
        this.c_centerY = this.c_zoomViewCenterY;
        this.showZoomViewMap();
        this.c_centerX = this.c_wholeMapCenterX;
        this.c_centerY = this.c_wholeMapCenterY;
        this.c_kortUdsnitShown = true;
        this.resetMapCoords();
        return true;
    }
    
    public boolean showZoomViewFour() {
        this.c_wholeMapCenterX = this.c_centerX;
        this.c_wholeMapCenterY = this.c_centerY;
        this.c_zoomViewCenterX = this.c_centerX + this.getPrintWidth() / 4 * this.c_pixelSize;
        this.c_zoomViewCenterY = this.c_centerY - this.getPrintHeight() / 4 * this.c_pixelSize;
        this.c_centerX = this.c_zoomViewCenterX;
        this.c_centerY = this.c_zoomViewCenterY;
        this.showZoomViewMap();
        this.c_centerX = this.c_wholeMapCenterX;
        this.c_centerY = this.c_wholeMapCenterY;
        this.c_kortUdsnitShown = true;
        this.resetMapCoords();
        return true;
    }
    
    private boolean showZoomViewMap() {
        this.c_mapShown = true;
        this.ULX = this.c_centerX - this.getPrintWidth() / 2 * this.c_pixelSize / 2.0f;
        this.ULY = this.c_centerY + this.getPrintHeight() / 2 * this.c_pixelSize / 2.0f;
        this.LRX = this.c_centerX + this.getPrintWidth() / 2 * this.c_pixelSize / 2.0f - 1.0f;
        this.LRY = this.c_centerY - this.getPrintHeight() / 2 * this.c_pixelSize / 2.0f + 1.0f;
        try {
            final URL mapUrl = new URL(String.valueOf(this.c_mapurl) + "&ticket=" + this.getTicket() + "&bbox=" + this.ULX + "," + this.LRY + "," + this.LRX + "," + this.ULY);
            if (Constant.debugMode) {
                System.out.println(String.valueOf(this.c_mapurl) + "&ticket=" + this.getTicket() + "&bbox=" + this.ULX + "," + this.LRY + "," + this.LRX + "," + this.ULY);
            }
            this.c_ditkortLabel.setIcon(new ImageIcon(mapUrl));
            this.updateDitkortIfNeeded();
            return true;
        }
        catch (Exception e) {
            System.err.println("Couldn't update the zoomed image!");
            return false;
        }
    }
    
    private void updateDitkortIfNeeded() {
        if (this.c_ditkortLabel.getIcon().getIconWidth() <= 0) {
            if (this.c_updateMapCounter < 3) {
                this.updateMap();
                ++this.c_updateMapCounter;
            }
        }
        else {
            this.c_updateMapCounter = 0;
        }
    }
    
    public void moveUpLeft() {
        this.c_centerY += this.getPrintHeight() / this.scrollFactor * this.c_pixelSize;
        this.c_centerX -= this.getPrintWidth() / this.scrollFactor * this.c_pixelSize;
        this.updateMap();
    }
    
    public void moveUp() {
        this.c_centerY += this.getPrintHeight() / this.scrollFactor * this.c_pixelSize;
        this.updateMap();
    }
    
    public void moveUpRight() {
        this.c_centerY += this.getPrintHeight() / this.scrollFactor * this.c_pixelSize;
        this.c_centerX += this.getPrintWidth() / this.scrollFactor * this.c_pixelSize;
        this.updateMap();
    }
    
    public void moveRight() {
        this.c_centerX += this.getPrintWidth() / this.scrollFactor * this.c_pixelSize;
        this.updateMap();
    }
    
    public void moveDownRight() {
        this.c_centerY -= this.getPrintHeight() / this.scrollFactor * this.c_pixelSize;
        this.c_centerX += this.getPrintWidth() / this.scrollFactor * this.c_pixelSize;
        this.updateMap();
    }
    
    public void moveDown() {
        this.c_centerY -= this.getPrintHeight() / this.scrollFactor * this.c_pixelSize;
        this.updateMap();
    }
    
    public void moveDownLeft() {
        this.c_centerY -= this.getPrintHeight() / this.scrollFactor * this.c_pixelSize;
        this.c_centerX -= this.getPrintWidth() / this.scrollFactor * this.c_pixelSize;
        this.updateMap();
    }
    
    public void moveLeft() {
        this.c_centerX -= this.getPrintWidth() / this.scrollFactor * this.c_pixelSize;
        this.updateMap();
    }
    
    public void ditkortUpdateCenter(final int cursorX, final int cursorY) {
        if (!this.c_kortUdsnitShown) {
            this.c_centerX += (cursorX - 20 - this.c_mapSizeX / 2) * (this.getPrintWidth() / this.c_mapSizeX * this.c_pixelSize);
            this.c_centerY -= (cursorY - 20 - this.c_mapSizeY / 2) * (this.getPrintHeight() / this.c_mapSizeY * this.c_pixelSize);
        }
        else {
            this.c_centerX = this.c_zoomViewCenterX + (cursorX - 20 - this.c_mapSizeX / 2) * (this.getPrintWidth() / this.c_mapSizeX * this.c_pixelSize) / 2.0f;
            this.c_centerY = this.c_zoomViewCenterY - (cursorY - 20 - this.c_mapSizeY / 2) * (this.getPrintHeight() / this.c_mapSizeY * this.c_pixelSize) / 2.0f;
        }
        this.ditkortUpdateZoomCoord(cursorX, cursorY);
    }
    
    public void ditkortUpdateZoomCoord(final int cursorX, final int cursorY) {
        if (!this.c_kortUdsnitShown) {
            this.c_zoomCoordX = this.c_centerX + (cursorX + 3 - this.c_mapSizeX / 2) * (this.getPrintWidth() / this.c_mapSizeX * this.c_pixelSize);
            this.c_zoomCoordY = this.c_centerY - (cursorY - this.c_mapSizeY / 2) * (this.getPrintHeight() / this.c_mapSizeY * this.c_pixelSize);
        }
        else {
            this.c_zoomCoordX = this.c_zoomViewCenterX + (cursorX + 3 - this.c_mapSizeX / 2) * (this.getPrintWidth() / this.c_mapSizeX * this.c_pixelSize) / 2.0f;
            this.c_zoomCoordY = this.c_zoomViewCenterY - (cursorY - this.c_mapSizeY / 2) * (this.getPrintHeight() / this.c_mapSizeY * this.c_pixelSize) / 2.0f;
        }
    }
    
    public void navigationskortUpdateCenter(final int cursorX, final int cursorY) {
        this.c_centerX = this.c_navigationImageULX + this.c_navigationImagePixelSize * cursorX;
        this.c_centerY = this.c_navigationImageULY - this.c_navigationImagePixelSize * cursorY;
    }
    
    public void resetMapCoords() {
        this.ULX = this.c_centerX - this.getPrintWidth() / 2 * this.c_pixelSize;
        this.ULY = this.c_centerY + this.getPrintHeight() / 2 * this.c_pixelSize;
        this.LRX = this.c_centerX + this.getPrintWidth() / 2 * this.c_pixelSize - 1.0f;
        this.LRY = this.c_centerY - this.getPrintHeight() / 2 * this.c_pixelSize + 1.0f;
    }
    
    public boolean updateMap() {
        if (this.checkboxes != null) {
            this.updateMap("", this.checkboxes.isUTMgridSelected(), this.checkboxes.isLongLatGridSelected());
        }
        return true;
    }
    
    public void setFormatCheckBoxes(final formatCheckboxes checkboxes) {
        this.checkboxes = checkboxes;
    }
    
    public void setKMSRequestHeight(final int value) {
        final String search = Integer.toString(this.c_mapSizeY);
        final String sub = Integer.toString(value);
        String result = "";
        final int i = this.c_mapurl.indexOf(search);
        if (i != -1) {
            result = this.c_mapurl.substring(0, i);
            result = String.valueOf(result) + sub;
            result = String.valueOf(result) + this.c_mapurl.substring(i + search.length());
            this.c_mapurl = result;
            this.c_mapSizeY = value;
        }
    }
    
    public boolean updateMap(final String specialRegion, final boolean utm, final boolean latlong) {
        this.c_mapShown = true;
        this.resetMapCoords();
        this.c_ditkortLabel.removeAll();
        String grids = "";
        this.setKMSRequestHeight(Constant.DEFAULT_HEIGHT);
        if (!Constant.debugMode) {
            if (specialRegion.equals("2513")) {
                if (Constant.debugMode) {
                    System.out.println("Jeg er i 2513");
                }
                this.setKMSRequestHeight(360);
                this.ULX = 669750.0f;
                this.LRY = 6139326.0f;
                this.LRX = 740610.0f;
                this.ULY = 6184325.0f;
            }
            else if (specialRegion.equals("2512")) {
                if (Constant.debugMode) {
                    System.out.println("Jeg er i 2512");
                }
                this.setKMSRequestHeight(431);
                this.ULX = 669750.0f;
                this.LRY = 6094326.0f;
                this.LRX = 728999.0f;
                this.ULY = 6139645.0f;
            }
            else if (specialRegion.equals("2113")) {
                if (Constant.debugMode) {
                    System.out.println("Jeg er i 2113");
                }
                this.setKMSRequestHeight(428);
                this.ULX = 441244.0f;
                this.LRY = 6139326.0f;
                this.LRX = 500549.0f;
                this.ULY = 6184325.0f;
            }
            else if (specialRegion.equals("2114")) {
                if (Constant.debugMode) {
                    System.out.println("Jeg er i 2114");
                }
                this.setKMSRequestHeight(441);
                this.ULX = 443070.0f;
                this.LRY = 6184326.0f;
                this.LRX = 500549.0f;
                this.ULY = 6229325.0f;
            }
            else if (specialRegion.equals("2514")) {
                if (Constant.debugMode) {
                    System.out.println("Jeg er i 2514");
                }
                this.setKMSRequestHeight(437);
                this.ULX = 669750.0f;
                this.LRY = 6184326.0f;
                this.LRX = 727609.0f;
                this.ULY = 6229325.0f;
            }
            else {
                this.setKMSRequestHeight(Constant.DEFAULT_HEIGHT);
            }
        }
        else {
            this.setKMSRequestHeight(Constant.DEFAULT_HEIGHT);
        }
        try {
            if ((utm || latlong) && this.c_serviceName.equals(Constant.servicename_DTK25)) {
                if (utm) {
                    grids = String.valueOf(grids) + ",1km_net";
                }
                if (latlong) {
                    grids = String.valueOf(grids) + ",latlong";
                }
            }
            else if ((utm || latlong) && this.c_serviceName.equals(Constant.servicename_DTK100)) {
                if (utm) {
                    grids = String.valueOf(grids) + ",5km_net";
                }
                if (latlong) {
                    grids = String.valueOf(grids) + ",latlong";
                }
            }
            final String url = String.valueOf(this.c_mapurl) + grids + "&ticket=" + this.getTicket() + "&styles=&bbox=" + this.ULX + "," + this.LRY + "," + this.LRX + "," + this.ULY;
            if (Constant.debugMode) {
                System.out.println("updatemap: " + url);
            }
            final URL mapUrl = new URL(url);
            final Image image = ImageIO.read(mapUrl).getScaledInstance(564, 450, 4);
            final ImageIcon map = new ImageIcon(image);
            this.c_ditkortLabel.setIcon(map);
            this.updateDitkortIfNeeded();
            return true;
        }
        catch (MalformedURLException e2) {
            return false;
        }
        catch (Exception e) {
            if (Constant.debugMode) {
                System.err.println("Method updateMap threw an exception: " + e.getMessage());
                e.printStackTrace(System.err);
            }
            return false;
        }
    }
    
    public void showImage() {
        try {
            this.c_ditkortLabel.removeAll();
            final URL mapURL = new URL(String.valueOf(Constant.imageURL) + "/vaelgnytfelt.jpg");
            this.c_ditkortLabel.setIcon(new ImageIcon(mapURL));
        }
        catch (Exception e) {
            System.err.println("Unable to fetch vaelgnytfelt.jpg: " + e.getMessage());
        }
    }
    
    public void setServiceNamePrint(final String c_serviceNamePrint) {
        this.c_serviceNamePrint = c_serviceNamePrint;
    }
    
    public void setServiceNamePrintLayer(final String c_serviceNamePrintLayer) {
        this.c_serviceNamePrintLayer = c_serviceNamePrintLayer;
    }
    
    class myMouseListener implements MouseListener
    {
        Cursor previousCursor;
        
        public void mouseEntered(final MouseEvent e) {
            this.previousCursor = ditkortView.this.getCursor();
            ditkortView.this.setCursor(Cursor.getPredefinedCursor(0));
        }
        
        public void mouseExited(final MouseEvent e) {
            ditkortView.this.setCursor(this.previousCursor);
        }
        
        public void mousePressed(final MouseEvent e) {
        }
        
        public void mouseReleased(final MouseEvent e) {
        }
        
        public void mouseMouseExited(final MouseEvent e) {
        }
        
        public void mouseClicked(final MouseEvent e) {
        }
    }
}
