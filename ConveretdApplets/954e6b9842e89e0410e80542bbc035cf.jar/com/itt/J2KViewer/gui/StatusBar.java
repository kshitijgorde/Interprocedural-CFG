// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import com.itt.J2KViewer.georvm.coords.Utm_Coord_3d;
import com.itt.J2KViewer.util.SecurityUtil;
import java.text.DecimalFormat;
import com.itt.J2KViewer.georvm.Projection;
import com.itt.J2KViewer.util.ViewerConst;
import java.awt.Point;
import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.BoxLayout;
import java.awt.event.MouseListener;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.JPopupMenu;
import javax.swing.JLabel;
import com.itt.J2KViewer.georvm.NITFGeoUtils;
import com.itt.J2KViewer.controller.PropertyManager;
import com.itt.J2KViewer.controller.ViewCentral;
import com.itt.J2KViewer.util.Log;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import javax.swing.JPanel;

public class StatusBar extends JPanel implements PropertyChangeListener, ActionListener
{
    private static final long serialVersionUID = 1L;
    private String homeDir;
    private Log log;
    private ViewCentral viewCentral;
    private PropertyManager propertyManager;
    private NITFGeoUtils geoloc;
    private JLabel pixelLoc;
    private JLabel rawValues;
    private JLabel rgbValues;
    private JLabel geographicLoc;
    private JLabel streamStatus;
    private JLabel zoom;
    private JLabel classStatus;
    private JLabel secure;
    private JPopupMenu statusMenu;
    private static String PIXEL_LOC;
    private static String RAW_VALUES;
    private static String RGB_VALUES;
    private static String GEO_LOC;
    private static String STREAM_STATUS;
    private static String ZOOM_LEVEL;
    private static String CLASS_LEVEL;
    private static String SSL;
    private StatusItem[] barItems;
    private PopupListener popupListener;
    static /* synthetic */ Class class$com$itt$J2KViewer$gui$StatusBar;
    
    public StatusBar(final ViewCentral viewCentral) {
        this.homeDir = System.getProperty("user.home");
        this.log = new Log((StatusBar.class$com$itt$J2KViewer$gui$StatusBar == null) ? (StatusBar.class$com$itt$J2KViewer$gui$StatusBar = class$("com.itt.J2KViewer.gui.StatusBar")) : StatusBar.class$com$itt$J2KViewer$gui$StatusBar);
        this.viewCentral = null;
        this.propertyManager = null;
        this.geoloc = null;
        this.barItems = new StatusItem[8];
        this.popupListener = new PopupListener();
        this.viewCentral = viewCentral;
        this.propertyManager = viewCentral.getPropertyManager();
        viewCentral.eventController().addPropertyChangeListener(this);
        this.initPanel();
    }
    
    private void initPanel() {
        this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(0), BorderFactory.createEmptyBorder(0, 2, 0, 2)));
        this.addMouseListener(this.popupListener);
        this.pixelLoc = this.createStatusItem();
        this.rawValues = this.createStatusItem();
        this.rgbValues = this.createStatusItem();
        this.geographicLoc = this.createStatusItem();
        this.streamStatus = this.createStatusItem();
        this.zoom = this.createStatusItem();
        this.classStatus = this.createStatusItem();
        this.secure = this.createStatusItem();
        this.createTooltips();
        this.setLayout(new BoxLayout(this, 0));
        this.addItem(this.pixelLoc, StatusBar.PIXEL_LOC, 0, true);
        this.addItem(this.rawValues, StatusBar.RAW_VALUES, 1, true);
        this.addItem(this.rgbValues, StatusBar.RGB_VALUES, 2, true);
        this.addItem(this.geographicLoc, StatusBar.GEO_LOC, 3, true);
        this.addItem(this.streamStatus, StatusBar.STREAM_STATUS, 4, true);
        this.addItem(this.zoom, StatusBar.ZOOM_LEVEL, 5, true);
        this.addItem(this.secure, StatusBar.SSL, 6, true);
        this.addItem(this.classStatus, StatusBar.CLASS_LEVEL, 7, !this.viewCentral.getPropertyManager().isShowSecurityBanner());
        this.loadState();
        this.createPopupMenu();
        this.reset();
    }
    
    private JLabel createStatusItem() {
        final Dimension preferredSize = new Dimension(500, 20);
        final Dimension minimumSize = new Dimension(20, 20);
        final JLabel label = new JLabel();
        label.setPreferredSize(preferredSize);
        label.setMinimumSize(minimumSize);
        label.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(1), BorderFactory.createEmptyBorder(0, 2, 0, 2)));
        label.addMouseListener(this.popupListener);
        return label;
    }
    
    private void addItem(final Component component, final String s, final int n, final boolean b) {
        this.barItems[n] = new StatusItem(s, n, component, b);
        if (b) {
            this.add(component, n);
        }
    }
    
    private void reset() {
        this.pixelLoc.setText(" ");
        this.rawValues.setText(" ");
        this.rgbValues.setText(" ");
        this.geographicLoc.setText(" ");
        this.streamStatus.setText(" ");
        this.zoom.setText(" ");
        this.classStatus.setText(" ");
        this.secure.setText(" ");
    }
    
    public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
        final String propertyName = propertyChangeEvent.getPropertyName();
        if (propertyName.equals("Open")) {
            this.handleOpen(propertyChangeEvent);
        }
        else if (propertyName.equals("MouseMoved")) {
            this.handleMouseMoved(propertyChangeEvent);
        }
        else if (propertyName.equals("DataValuesChanged")) {
            this.handleRawValuesChanged(propertyChangeEvent);
        }
        else if (propertyName.equals("RGBValuesChanged")) {
            this.handleRgbValuesChanged(propertyChangeEvent);
        }
        else if (propertyName.equals("DiscardedZoomLevels")) {
            this.handleZoom();
        }
        else if (propertyName.equals("KduStatus")) {
            this.handleStreamStatus(propertyChangeEvent);
        }
        else if (propertyName.equals("XmlDataParsed")) {
            this.handleSecurityClass();
        }
    }
    
    private void handleOpen(final PropertyChangeEvent propertyChangeEvent) {
        if (this.propertyManager.isOpen()) {
            this.geoloc = this.viewCentral.getNITFGeoUtils();
        }
        else {
            this.geoloc = null;
            this.reset();
        }
        if (this.viewCentral.getPropertyManager().isSslEnabled()) {
            this.secure.setOpaque(true);
            this.secure.setText("Secure Streaming");
            this.secure.setBackground(new Color(255, 229, 131));
        }
        else {
            this.barItems[6].selected = false;
            this.rebuildStatusBar();
            this.invalidate();
            this.repaint();
            this.validate();
        }
    }
    
    private void handleMouseMoved(final PropertyChangeEvent propertyChangeEvent) {
        final Point point = (Point)propertyChangeEvent.getNewValue();
        String string = " ";
        String text = " ";
        if (this.propertyManager.isOpen()) {
            if (point.x >= 0 && point.y >= 0) {
                string = String.valueOf(point.x) + "," + String.valueOf(point.y);
                if (this.geoloc != null && this.geoloc.isReady()) {
                    final int geographicDisplayFormat = this.propertyManager.getGeographicDisplayFormat();
                    String s;
                    if (geographicDisplayFormat == 2) {
                        s = this.formatGeoCoordDMSString(point.x, point.y);
                    }
                    else if (geographicDisplayFormat == 1) {
                        s = this.formatGeoCoordDDMString(point.x, point.y);
                    }
                    else {
                        s = this.formatGeoCoordString(point.x, point.y);
                    }
                    final Projection proj = this.viewCentral.getPropertyManager().getProj();
                    if (proj != null) {
                        if (proj == ViewerConst.UTM_PROJECTION) {
                            text = this.formatUTMCoordString(point.x, point.y);
                        }
                        else if (proj == ViewerConst.MGRS_PROJECTION) {
                            text = this.formatMGRSCoordString(point.x, point.y);
                        }
                        else if (proj == ViewerConst.GEOGRAPHIC_PROJECTION) {
                            text = s;
                        }
                        else {
                            text = "N/A";
                        }
                    }
                }
            }
            this.pixelLoc.setText("Pixel: " + string);
            this.geographicLoc.setText(text);
        }
    }
    
    private void handleStreamStatus(final PropertyChangeEvent propertyChangeEvent) {
        this.streamStatus.setText(propertyChangeEvent.getNewValue().toString());
    }
    
    private void handleRawValuesChanged(final PropertyChangeEvent propertyChangeEvent) {
        String s = " ";
        final int[] array = (int[])propertyChangeEvent.getNewValue();
        if (array != null) {
            if (this.propertyManager.isShowRGB()) {
                s = array[0] + "," + array[1] + "," + array[2];
            }
            else {
                s = Integer.toString(array[0]);
            }
        }
        this.rawValues.setText("Data: " + s);
    }
    
    private void handleRgbValuesChanged(final PropertyChangeEvent propertyChangeEvent) {
        String s = " ";
        final int[] array = (int[])propertyChangeEvent.getNewValue();
        if (array != null) {
            if (this.propertyManager.isShowRGB()) {
                s = array[0] + "," + array[1] + "," + array[2];
            }
            else {
                s = Integer.toString(array[0]);
            }
        }
        this.rgbValues.setText("Display: " + s);
    }
    
    private void handleZoom() {
        final int discardedZoomLevels = this.propertyManager.getDiscardedZoomLevels();
        String s;
        double n2;
        if (discardedZoomLevels >= 0) {
            final int n = 1 << discardedZoomLevels;
            s = "1:" + n;
            n2 = 1.0 / n;
        }
        else {
            final int n3 = 1 << -discardedZoomLevels;
            s = n3 + "x";
            n2 = n3;
        }
        this.zoom.setText(new DecimalFormat("##0.#").format(n2 * 100.0) + "% " + s);
    }
    
    public void handleSecurityClass() {
        final String securityClassification = SecurityUtil.getSecurityClassification(this.viewCentral, "FILE");
        final String securityReleasability = SecurityUtil.getSecurityReleasability(this.viewCentral, "FILE");
        this.classStatus.setOpaque(true);
        this.classStatus.setText(SecurityUtil.getClassificationString(securityClassification));
        this.classStatus.setBackground(SecurityUtil.getClassificationColor(securityClassification));
        this.classStatus.setToolTipText("<html><b>Security Classification: </b>" + SecurityUtil.getClassificationString(securityClassification) + "<br>" + "<b>Releasability: </b>" + securityReleasability + "</html>");
    }
    
    private String formatGeoCoordString(final int n, final int n2) {
        if (this.geoloc != null && this.geoloc.isReady()) {
            final double[] geodeticLocation = this.geoloc.getGeodeticLocation(n, n2);
            final DecimalFormat decimalFormat = new DecimalFormat("##0.000000");
            final StringBuffer sb = new StringBuffer();
            sb.append(decimalFormat.format((geodeticLocation[1] >= 0.0) ? geodeticLocation[1] : (geodeticLocation[1] * -1.0))).append((geodeticLocation[1] >= 0.0) ? "N" : "S").append(" : ").append(decimalFormat.format((geodeticLocation[0] >= 0.0) ? geodeticLocation[0] : (geodeticLocation[0] * -1.0))).append((geodeticLocation[0] >= 0.0) ? "E" : "W");
            return sb.toString();
        }
        return "";
    }
    
    private String formatGeoCoordDMSString(final int n, final int n2) {
        if (this.geoloc != null && this.geoloc.isReady()) {
            final double[] geodeticLocation = this.geoloc.getGeodeticLocation(n, n2);
            final DecimalFormat decimalFormat = new DecimalFormat("##0.00");
            final double abs = Math.abs(geodeticLocation[0]);
            final double abs2 = Math.abs(geodeticLocation[1]);
            final double n3 = abs2 % 1.0;
            final double n4 = abs % 1.0;
            final int n5 = (int)(n3 * 60.0);
            final int n6 = (int)(n4 * 60.0);
            final double n7 = Math.round(n3 * 60.0 % 1.0 * 60.0 * 100.0) / 100.0;
            final double n8 = Math.round(n4 * 60.0 % 1.0 * 60.0 * 100.0) / 100.0;
            final String string = Double.toString(abs2);
            final String string2 = Double.toString(abs);
            return "".concat(string.substring(0, string.indexOf("."))).concat(String.valueOf('°')).concat(Integer.toString(n5)).concat("'").concat(decimalFormat.format(n7)).concat("\"").concat((geodeticLocation[1] >= 0.0) ? "N" : "S").concat("  ").concat(string2.substring(0, string2.indexOf("."))).concat(String.valueOf('°')).concat(Integer.toString(n6)).concat("'").concat(decimalFormat.format(n8)).concat("\"").concat((geodeticLocation[0] >= 0.0) ? "E" : "W").concat(" ");
        }
        return "";
    }
    
    private String formatGeoCoordDDMString(final int n, final int n2) {
        if (this.geoloc != null && this.geoloc.isReady()) {
            final double[] geodeticLocation = this.geoloc.getGeodeticLocation(n, n2);
            final DecimalFormat decimalFormat = new DecimalFormat("000.");
            final DecimalFormat decimalFormat2 = new DecimalFormat("00.");
            final DecimalFormat decimalFormat3 = new DecimalFormat("00.000");
            final double abs = Math.abs(geodeticLocation[0]);
            final double abs2 = Math.abs(geodeticLocation[1]);
            final double n3 = abs2 % 1.0;
            final double n4 = abs % 1.0;
            final double n5 = n3 * 60.0;
            final double n6 = n4 * 60.0;
            final String format = decimalFormat2.format((int)abs2);
            final String format2 = decimalFormat.format((int)abs);
            return "".concat(format.substring(0, format.indexOf("."))).concat(decimalFormat3.format(n5)).concat((geodeticLocation[1] >= 0.0) ? "N" : "S").concat(" ").concat(format2.substring(0, format2.indexOf("."))).concat(decimalFormat3.format(n6)).concat((geodeticLocation[0] >= 0.0) ? "E" : "W");
        }
        return "";
    }
    
    private String formatUTMCoordString(final int n, final int n2) {
        if (this.geoloc != null && this.geoloc.isReady()) {
            final Utm_Coord_3d utmLocation = this.geoloc.getUTMLocation(n, n2);
            final double[] array = { utmLocation.x, utmLocation.y };
            final DecimalFormat decimalFormat = new DecimalFormat("##0.00");
            final StringBuffer sb = new StringBuffer();
            final double n3 = array[1];
            final double n4 = array[0];
            final double[] latLonEastNorth = this.geoloc.getLatLonEastNorth("UpperLeft");
            if (this.geoloc.getICORDS() == 'N' || latLonEastNorth[1] > 0.0) {
                sb.append("E: ").append(decimalFormat.format(n4)).append(" N: ").append(decimalFormat.format(n3));
            }
            else if (this.geoloc.getICORDS() == 'S' || latLonEastNorth[1] <= 0.0) {
                sb.append("E: ").append(decimalFormat.format(n4)).append(" S: ").append(decimalFormat.format(n3));
            }
            return sb.toString();
        }
        return "";
    }
    
    private String formatMGRSCoordString(final int n, final int n2) {
        if (this.geoloc != null && this.geoloc.isReady()) {
            return this.geoloc.getMGRSLocation(n, n2);
        }
        return "";
    }
    
    private void createPopupMenu() {
        this.statusMenu = new JPopupMenu();
        for (int i = 0; i < this.barItems.length; ++i) {
            final JCheckBoxMenuItem checkBoxMenuItem = new JCheckBoxMenuItem(this.barItems[i].name);
            checkBoxMenuItem.setSelected(this.barItems[i].selected);
            checkBoxMenuItem.addActionListener(this);
            this.statusMenu.add(checkBoxMenuItem);
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final JMenuItem menuItem = (JMenuItem)actionEvent.getSource();
        final String text = menuItem.getText();
        int i = 0;
        while (i < this.barItems.length) {
            if (text.equals(this.barItems[i].name)) {
                if (menuItem.isSelected()) {
                    this.barItems[i].selected = true;
                    break;
                }
                this.barItems[i].selected = false;
                break;
            }
            else {
                ++i;
            }
        }
        this.rebuildStatusBar();
        this.invalidate();
        this.repaint();
        this.validate();
        this.saveState();
    }
    
    private void rebuildStatusBar() {
        this.removeAll();
        for (int i = 0; i < this.barItems.length; ++i) {
            if (this.barItems[i].selected) {
                this.add(this.barItems[i].component);
            }
        }
        if (this.getComponentCount() == 2) {
            this.add(this.createStatusItem(), 1);
        }
    }
    
    public void saveState() {
        final HashMap<String, Boolean> hashMap = new HashMap<String, Boolean>();
        for (int i = 0; i < this.barItems.length; ++i) {
            hashMap.put(this.barItems[i].name, new Boolean(this.barItems[i].selected));
        }
        try {
            final FileOutputStream fileOutputStream = new FileOutputStream(this.homeDir + "/" + "EnterpriseViewer" + "/status_bar" + ".state");
            final ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(hashMap);
            objectOutputStream.close();
            fileOutputStream.close();
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void loadState() {
        try {
            final FileInputStream fileInputStream = new FileInputStream(this.homeDir + "/" + "EnterpriseViewer" + "/status_bar" + ".state");
            final ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            final HashMap hashMap = (HashMap)objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            if (hashMap != null) {
                for (int i = 0; i < this.barItems.length; ++i) {
                    this.barItems[i].selected = hashMap.get(this.barItems[i].name);
                }
                this.rebuildStatusBar();
            }
        }
        catch (Exception ex) {
            this.log.info("Unable to load status bar state");
        }
    }
    
    public void createTooltips() {
        this.pixelLoc.setToolTipText("Mouse location in 1:1 dimensions");
        this.geographicLoc.setToolTipText("Geographic Location");
        this.rawValues.setToolTipText("Raw Data Values");
        this.rgbValues.setToolTipText("RGB Display Values");
        this.streamStatus.setToolTipText("Stream Status");
        this.zoom.setToolTipText("Zoom Level");
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
    
    static {
        StatusBar.PIXEL_LOC = "Pixel Location";
        StatusBar.RAW_VALUES = "Raw Data Values";
        StatusBar.RGB_VALUES = "RGB Values";
        StatusBar.GEO_LOC = "Geographic Location";
        StatusBar.STREAM_STATUS = "Stream Status";
        StatusBar.ZOOM_LEVEL = "Zoom Level";
        StatusBar.CLASS_LEVEL = "Classification";
        StatusBar.SSL = "Secure Streaming";
    }
    
    class PopupListener extends MouseAdapter
    {
        public void mousePressed(final MouseEvent mouseEvent) {
            this.maybeShowPopup(mouseEvent);
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            this.maybeShowPopup(mouseEvent);
        }
        
        private void maybeShowPopup(final MouseEvent mouseEvent) {
            if (mouseEvent.isPopupTrigger()) {
                StatusBar.this.statusMenu.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
            }
        }
    }
    
    class StatusItem
    {
        public String name;
        public int position;
        public Component component;
        public boolean selected;
        
        StatusItem(final String name, final int position, final Component component, final boolean selected) {
            this.name = name;
            this.position = position;
            this.component = component;
            this.selected = selected;
        }
    }
}
