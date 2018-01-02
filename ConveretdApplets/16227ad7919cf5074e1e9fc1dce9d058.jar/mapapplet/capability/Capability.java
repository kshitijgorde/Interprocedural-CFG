// 
// Decompiled by Procyon v0.5.30
// 

package mapapplet.capability;

import org.w3c.dom.NodeList;
import java.awt.Image;
import mapapplet.imageload.ErrorMessage;
import mapapplet.imageload.ImageMessage;
import java.util.Observable;
import java.awt.CheckboxMenuItem;
import java.awt.event.MouseEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.PopupMenu;
import mapapplet.PanelButton;
import java.util.Hashtable;
import java.awt.Graphics;
import java.net.MalformedURLException;
import org.w3c.dom.Document;
import java.io.InputStream;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.EntityResolver;
import org.apache.xerces.parsers.DOMParser;
import java.net.URL;
import java.awt.MenuItem;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.util.Vector;
import mapapplet.MapProjection;
import mapapplet.Main;
import java.util.Observer;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import mapapplet.Module;

public final class Capability implements Module, ActionListener, ItemListener, Observer
{
    private static final String name = "capability";
    private final Main parent;
    private MapProjection prj;
    private boolean isInitialised;
    private boolean isEnabled;
    private String getMethod;
    private boolean ogcCompl;
    private MyMenu popup;
    private final Vector wmsServers;
    Vector wmsImages;
    private Vector layerTransparencies;
    private final String imagesPrefix = "WMSImage_";
    private boolean mapReloaded;
    
    public Capability(final Object par) {
        this.isEnabled = false;
        this.getMethod = "browse";
        this.ogcCompl = false;
        this.mapReloaded = false;
        this.parent = (Main)par;
        Main.imgLoader.addObserver(this);
        this.wmsServers = new Vector();
        this.wmsImages = new Vector();
        this.setLayerTransparencies(new Vector());
        int i = 0;
        if (this.getParent().getParameter("capability_serverurl" + i) != null) {
            this.popup = new MyMenu();
            while (this.getParent().getParameter("capability_serverurl" + i) != null) {
                try {
                    this.wmsServers.addElement(this.getParent().getParameter("capability_serverurl" + i++));
                    this.setCapabilities(this.wmsServers.lastElement() + "&request=GetCapabilities");
                }
                catch (IOException ex) {
                    System.err.println("!Error setting new capabilities from " + this.wmsServers.elementAt(this.wmsServers.size() - 1) + "&request=GetCapabilities" + ". " + ex.getMessage());
                    this.wmsServers.removeElementAt(this.wmsServers.size() - 1);
                    ex.printStackTrace();
                }
                catch (SAXException ex2) {
                    System.err.println("Error parsing project XML: " + ex2.getMessage());
                    this.wmsServers.removeElementAt(this.wmsServers.size() - 1);
                }
            }
            final MenuItem resetItem = new MenuItem("Reset");
            resetItem.addActionListener(this);
            this.popup.add(resetItem);
            this.getParent().toolbar.addButton(this.getParent().getCurLocation() + "map_images/icon_zoomin_2.gif", this.getParent().getCurLocation() + "map_images/icon_zoomin_1.gif", "GIS ZoomIn", "capability", false, "Zoom");
            this.getParent().toolbar.addButton(this.getParent().getCurLocation() + "map_images/icon_zoomout_2.gif", this.getParent().getCurLocation() + "map_images/icon_zoomout_1.gif", "GIS ZoomOut", "capability", false, "Zoom");
            this.getParent().toolbar.addButton(this.getParent().getCurLocation() + "map_images/icon_zoomtorect_2.gif", this.getParent().getCurLocation() + "map_images/icon_zoomtorect_1.gif", "GIS ZoomToRect", "capability", false, "Zoom");
            this.getParent().toolbar.addButton(this.getParent().getCurLocation() + "map_images/icon_zoomtopoint_2.gif", this.getParent().getCurLocation() + "map_images/icon_zoomtopoint_1.gif", "GIS Recenter", "capability", false, "Zoom");
            this.getParent().toolbar.addButton(this.getParent().getCurLocation() + "map_images/icon_zoomfit_2.gif", this.getParent().getCurLocation() + "map_images/icon_zoomfit_1.gif", "GIS ZoomToGlobal", "capability", false, "Zoom");
            this.getParent().toolbar.addButton(this.getParent().getCurLocation() + "map_images/icon_transp_2.gif", this.getParent().getCurLocation() + "map_images/icon_transp_1.gif", "GIS Transparencies Menu", "capability", false, "ZoomMenu");
            this.getParent().toolbar.addButton(this.getParent().getCurLocation() + "map_images/icon_layer_2.gif", this.getParent().getCurLocation() + "map_images/icon_layer_1.gif", "GIS Layers Menu", "capability", false, "ZoomMenu");
            final String getMethodTempStr = this.getParent().getParameter("capability_zoommethod");
            if (getMethodTempStr != null && getMethodTempStr.length() > 0) {
                this.getMethod = getMethodTempStr;
                if (this.getMethod.toLowerCase().equals("getmap")) {
                    this.ogcCompl = true;
                }
            }
            this.isEnabled = true;
            return;
        }
        System.err.println("Capability: error reading the capability_serverurl parameter.");
    }
    
    private void setCapabilities(final String projectUrl) throws MalformedURLException, IOException, SAXException {
        System.out.println("projectUrl = " + projectUrl);
        URL xmlUrl = null;
        InputStream input = null;
        xmlUrl = new URL(Main.replaceSpaces(projectUrl));
        input = xmlUrl.openStream();
        Document doc = null;
        final DOMParser parser = new DOMParser();
        parser.setEntityResolver(new NoOpEntityResolver());
        parser.parse(new InputSource(input));
        doc = parser.getDocument();
        input.close();
        final Node serviceNode = getChild(doc, "Service");
        final String mapName = getChild(serviceNode, "Title").getFirstChild().getNodeValue();
        final Node capabilityNode = getChild(doc, "Capability");
        final Node layerNode = getChild(capabilityNode, "Layer");
        final Vector layersList = getChildren(layerNode, "Layer");
        if (layersList.size() <= 0) {
            throw new SAXException("Error parsing capabilities: no Layer nodes found.");
        }
        final LayersMenu subMenu = new LayersMenu(mapName, 1);
        if (this.popup.subMenus.size() == 0) {
            this.getLayerTransparencies().addElement(new Float(subMenu.getLayerTransparency()));
        }
        else {
            this.getLayerTransparencies().addElement(null);
        }
        for (int i = 0; i < layersList.size(); ++i) {
            final Element layerElement = layersList.elementAt(i);
            final String layerDisplay = getChild(layerElement, "Title").getFirstChild().getNodeValue();
            final String layerName = getChild(layerElement, "Name").getFirstChild().getNodeValue();
            String layerStyle = null;
            if (getChild(layerElement, "Style") != null) {
                final Node styleNode = getChild(layerElement, "Style");
                layerStyle = getChild(styleNode, "Name").getFirstChild().getNodeValue();
            }
            if (layerDisplay != null && layerName != null) {
                if (layerDisplay.toLowerCase().indexOf("default") >= 0) {
                    subMenu.getDefaultQuery().append((subMenu.getDefaultQuery().length() > 0) ? "," : ("" + layerName));
                }
                else {
                    final LayerMenuItem menuItem = new LayerMenuItem(layerDisplay, layerName, layerStyle, subMenu);
                    subMenu.add(menuItem);
                    if (menuItem != null) {
                        menuItem.addItemListener(this);
                    }
                }
            }
        }
        this.popup.add(subMenu);
    }
    
    public String name() {
        return "capability";
    }
    
    public void paint(final Graphics g) {
    }
    
    public void newMap(final MapProjection proj) {
        this.setPrj(proj);
        if (this.wmsServers.size() > 0) {
            this.isInitialised = true;
        }
    }
    
    public void changedButton(final Hashtable buttons) {
    }
    
    public void pressedButton(final PanelButton button) {
        if (!this.isEnabled) {
            return;
        }
        if (button.getID().compareTo("GIS Transparencies Menu") == 0) {
            final Vector layerNames = new Vector();
            for (int i = 0; i < this.popup.subMenus.size(); ++i) {
                if (this.popup.subMenus.elementAt(i).getClass().getName().equals("mapapplet.capability.LayersMenu")) {
                    final MyMenu item = this.popup.subMenus.elementAt(i);
                    layerNames.addElement(item.getLabel());
                }
            }
            final TransparencyDialog dialog = new TransparencyDialog(layerNames, this.getLayerTransparencies(), this);
            dialog.pack();
            dialog.show();
            dialog.setModal(true);
        }
        if (button.getID().compareTo("GIS Layers Menu") == 0) {
            button.add(this.popup);
            this.popup.show(button, 25, 25);
        }
        if (button.getID().compareTo("GIS ZoomIn") == 0) {
            this.setNewMap(2, false, null);
        }
        if (button.getID().compareTo("GIS ZoomOut") == 0) {
            this.setNewMap(-2, false, null);
        }
        if (button.getID().compareTo("GIS ZoomToRect") == 0) {
            this.setNewMap(1, true, null);
        }
        if (button.getID().compareTo("GIS Recenter") == 0) {
            this.setNewMap(1, false, null);
        }
        if (button.getID().compareTo("GIS ZoomToGlobal") == 0) {
            this.zoomToGlobal();
        }
    }
    
    private void zoomToGlobal() {
        if (this.ogcCompl) {
            this.setNewMap(1, false, this.getParent().getParameter("GlobalMapExtend").replace(' ', ','));
        }
        else {
            final String newUrl = this.wmsServers.elementAt(0) + this.getLayers(0) + "&imgext=";
            this.getParent().waiter(true);
            this.getParent().setParam("Zoom.zoom", newUrl);
        }
    }
    
    public boolean isEnabled() {
        return this.isEnabled;
    }
    
    public boolean isPaintable() {
        return false;
    }
    
    public String queryParam(final String paramName) {
        return null;
    }
    
    public boolean setParam(final String paramName, final String value) {
        if (!this.isEnabled) {
            return false;
        }
        if (paramName.compareTo("Capability.setProject") == 0) {
            System.out.println(this.getParent().queryParam("Zoom.GetZoomPointPlus"));
            try {
                this.wmsServers.addElement(value);
                this.setCapabilities(value + "&request=GetCapabilities");
            }
            catch (IOException ex) {
                this.wmsServers.removeElementAt(this.wmsServers.size() - 1);
                System.err.println("Error setting new capabilities from " + value + ". " + ex.getMessage());
                ex.printStackTrace();
                return true;
            }
            catch (SAXException ex2) {
                this.wmsServers.removeElementAt(this.wmsServers.size() - 1);
                System.err.println("Error parsing project XML: " + ex2.getMessage());
                return true;
            }
            return true;
        }
        if (paramName.compareTo("Capability.zoomToRect") == 0) {
            this.setNewMap(1, true, value);
            return true;
        }
        if (paramName.compareTo("Capability.moveTo") == 0) {
            int newX = this.parent.map.getSize().width / 2;
            int newY = this.parent.map.getSize().height / 2;
            if (value.indexOf("right") >= 0) {
                newX = this.parent.map.getSize().width;
            }
            if (value.indexOf("left") >= 0) {
                newX = 0;
            }
            if (value.indexOf("down") >= 0) {
                newY = this.parent.map.getSize().height;
            }
            if (value.indexOf("up") >= 0) {
                newY = 0;
            }
            final String extent = this.calculateExtend(this.getParent().data.getExtend(), 1.0f, null, newX + " " + newY);
            this.setNewMap(1, false, extent);
            return true;
        }
        return false;
    }
    
    public boolean call(final String methodName) {
        return false;
    }
    
    public void actionPerformed(final ActionEvent e) {
        if (e.getSource().getClass().isInstance(new MenuItem()) && ((MenuItem)e.getSource()).getLabel().equals("Reset") && this.isInitialised) {
            for (int i = 0; i < this.popup.subMenus.size(); ++i) {
                if (this.popup.subMenus.elementAt(i).getClass().getName().equals("mapapplet.capability.LayersMenu")) {
                    final LayersMenu subMenu = this.popup.subMenus.elementAt(i);
                    for (int j = 0; j < subMenu.subMenus.size(); ++j) {
                        final LayerMenuItem item = subMenu.subMenus.elementAt(j);
                        item.setState(false);
                    }
                }
            }
            this.setNewMap(1, false, null);
        }
    }
    
    public void itemStateChanged(final ItemEvent e) {
        if (e.getSource().getClass().isInstance(new LayerMenuItem())) {
            final LayerMenuItem item = (LayerMenuItem)e.getSource();
            if (this.isInitialised && item.parent.getClass().isInstance(new LayersMenu())) {
                final LayersMenu menu = item.parent;
                final int index = this.popup.subMenus.indexOf(menu);
                if (index > 0 && !menu.haveSelectedElements()) {
                    this.getLayerTransparencies().setElementAt(null, index);
                }
            }
            if (this.isInitialised) {
                this.setNewMap(1, false, null);
            }
        }
    }
    
    public void mouseClicked(final MouseEvent e) {
    }
    
    public void mousePressed(final MouseEvent e) {
        this.maybeShowPopup(e);
    }
    
    public void mouseReleased(final MouseEvent e) {
        this.maybeShowPopup(e);
    }
    
    public void mouseEntered(final MouseEvent e) {
    }
    
    public void mouseExited(final MouseEvent e) {
    }
    
    public void mouseDragged(final MouseEvent e) {
    }
    
    public void mouseMoved(final MouseEvent e) {
    }
    
    private void maybeShowPopup(final MouseEvent e) {
        if (!this.isEnabled) {
            return;
        }
        if (e.isPopupTrigger()) {
            e.getComponent().add(this.popup);
            this.popup.show(e.getComponent(), e.getX(), e.getY());
        }
    }
    
    private String getLayers(final int subMenuNum) {
        String returnLayers = "";
        final LayersMenu subMenu = this.popup.subMenus.elementAt(subMenuNum);
        if (this.ogcCompl) {
            returnLayers = "&LAYERS=" + (Object)subMenu.getDefaultQuery();
            boolean firstSet = false;
            if (subMenuNum == 0) {
                firstSet = (subMenu.getDefaultQuery().length() > 0);
            }
            for (int i = 0; i < subMenu.subMenus.size(); ++i) {
                final MenuItem item = subMenu.subMenus.elementAt(i);
                if (item.getClass().isInstance(new LayerMenuItem()) && ((CheckboxMenuItem)item).getState()) {
                    if (firstSet) {
                        returnLayers += ",";
                    }
                    returnLayers += ((LayerMenuItem)item).layerQuery;
                    firstSet = true;
                }
            }
        }
        else {
            for (int j = 0; j < subMenu.subMenus.size(); ++j) {
                final MenuItem item2 = subMenu.subMenus.elementAt(j);
                if (item2.getClass().isInstance(new LayerMenuItem()) && ((CheckboxMenuItem)item2).getState()) {
                    returnLayers = returnLayers + "&layer=" + ((LayerMenuItem)item2).layerName;
                }
            }
        }
        return returnLayers;
    }
    
    private String getStyles(final int subMenuNum) {
        String returnStyles = "";
        final LayersMenu subMenu = this.popup.subMenus.elementAt(subMenuNum);
        if (this.ogcCompl) {
            boolean firstSet = false;
            for (int i = 0; i < subMenu.subMenus.size(); ++i) {
                final MenuItem item = subMenu.subMenus.elementAt(i);
                if (item.getClass().isInstance(new LayerMenuItem()) && ((CheckboxMenuItem)item).getState() && ((LayerMenuItem)item).layerStyle != null) {
                    if (firstSet) {
                        returnStyles += ",";
                    }
                    else {
                        returnStyles = "&STYLES=";
                    }
                    returnStyles += ((LayerMenuItem)item).layerStyle;
                    firstSet = true;
                }
            }
        }
        return returnStyles;
    }
    
    private void setNewMap(final int zoomFactor, final boolean zoomToBox, final String extent) {
        this.getParent().waiter(true);
        if (this.ogcCompl) {
            this.mapReloaded = false;
            final String newUrl = this.makeImageUrl(this.wmsServers.elementAt(0), extent, zoomFactor, zoomToBox, 0);
            this.downloadOtherImages(extent, zoomFactor, zoomToBox);
            this.getParent().setParam("Zoom.zoomDirectlyOGC", newUrl);
        }
        else {
            String newUrl = this.wmsServers.elementAt(0) + "&mode=" + this.getMethod + this.getLayers(0);
            if (zoomToBox) {
                final String zoomFactorStr = "&zoom=" + zoomFactor;
                newUrl = newUrl + zoomFactorStr + "&imgbox=";
            }
            else {
                final String zoomFactorStr = "&zoom=" + zoomFactor;
                newUrl = newUrl + zoomFactorStr + "&imgxy=";
            }
            System.out.println("Center point!: " + this.getParent().queryParam("Zoom.GetZoomPointPlus"));
            this.getParent().waiter(true);
            this.getParent().map.repaint();
            if ("browse".equals(this.getMethod)) {
                this.getParent().setParam("Zoom.zoom", newUrl);
            }
            else if ("map".equals(this.getMethod)) {
                this.getParent().setParam("Zoom.zoomDirectly", newUrl);
            }
        }
    }
    
    private String makeImageUrl(final String prefix, final String extent, final int zoomFactor, final boolean zoomToBox, final int subMenuNum) {
        String newUrl = prefix + "&REQUEST=" + this.getMethod + this.getLayers(subMenuNum);
        newUrl += this.getStyles(subMenuNum);
        if (this.getParent().data.initialized) {
            newUrl = Main.replaceSpaces(newUrl);
            if (extent != null) {
                newUrl = newUrl + "&BBOX=" + extent;
            }
            else {
                newUrl = newUrl + "&BBOX=" + this.calculateExtend(this.getParent().data.getExtend(), zoomFactor, zoomToBox ? this.getParent().queryParam("Zoom.GetZoomAreaPlus") : null, zoomToBox ? null : this.getParent().queryParam("Zoom.GetZoomPointPlus"));
            }
            newUrl += "&VERSION=1.1.0";
        }
        else {
            System.err.println("Error zooming the map: applet is not initialized.");
            this.getParent().waiter(false);
            this.getParent().map.repaint();
        }
        return newUrl;
    }
    
    private void downloadOtherImages(final String extent, final int zoomFactor, final boolean zoomToBox) {
        (this.wmsImages = new Vector()).setSize(this.wmsServers.size());
        this.getLayerTransparencies().setSize(this.wmsServers.size());
        this.getParent().map.setLayersVector(this.wmsImages, this.getLayerTransparencies());
        for (int i = 1; i < this.wmsServers.size(); ++i) {
            final LayersMenu subMenu = this.popup.subMenus.elementAt(i);
            boolean selectedItem = false;
            for (int j = 0; j < subMenu.subMenus.size(); ++j) {
                final MenuItem item = subMenu.subMenus.elementAt(j);
                if (item.getClass().isInstance(new LayerMenuItem()) && ((CheckboxMenuItem)item).getState()) {
                    selectedItem = true;
                }
            }
            if (selectedItem) {
                String newUrl = this.makeImageUrl(this.wmsServers.elementAt(i), extent, zoomFactor, zoomToBox, i);
                newUrl = newUrl + "&WIDTH=" + this.getParent().data.getMapSize().width + "&HEIGHT=" + this.getParent().data.getMapSize().height + "&FORMAT=image/png";
                System.out.println("Trying to load other image :\n" + newUrl + "\n");
                Main.imgLoader.loadImage(newUrl, "WMSImage_" + i);
            }
        }
    }
    
    private String calculateExtend(final String curMapExt, float zoomFactor, String selectedBoxStr, String centerPointCoordStr) {
        float maplonleft = Main.atof(Main.getToken(curMapExt, 1));
        float maplatdown = Main.atof(Main.getToken(curMapExt, 2));
        float maplonright = Main.atof(Main.getToken(curMapExt, 3));
        float maplatup = Main.atof(Main.getToken(curMapExt, 4));
        final float geoMapWidth = maplonright - maplonleft;
        final float geoMapHeight = maplatup - maplatdown;
        zoomFactor = ((zoomFactor > 0.0f) ? (1.0f / zoomFactor) : Math.abs(zoomFactor));
        if (selectedBoxStr != null && selectedBoxStr.length() > 0 && !"-1+-1+-1+-1".equals(selectedBoxStr)) {
            selectedBoxStr = selectedBoxStr.replace('+', ' ');
            final int boxleft = Main.atoi(Main.getToken(selectedBoxStr, 1));
            final int boxup = Main.atoi(Main.getToken(selectedBoxStr, 2));
            final int boxright = Main.atoi(Main.getToken(selectedBoxStr, 3));
            final int boxdown = Main.atoi(Main.getToken(selectedBoxStr, 4));
            float boxleftg = this.getPrj().xy2ll(boxleft, boxup)[1];
            float boxupg = this.getPrj().xy2ll(boxleft, boxup)[0];
            float boxrightg = this.getPrj().xy2ll(boxright, boxdown)[1];
            float boxdowng = this.getPrj().xy2ll(boxright, boxdown)[0];
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
            centerPointCoordStr = centerPointCoordStr.replace('+', ' ');
            final float centerPointX = Main.atoi(Main.getToken(centerPointCoordStr, 1));
            final float centerPointY = Main.atoi(Main.getToken(centerPointCoordStr, 2));
            System.out.println("Original center point px: " + centerPointX + " " + centerPointY);
            final float centerPointGeoCoordLat = this.getPrj().xy2ll(centerPointX, centerPointY)[0];
            final float centerPointGeoCoordLon = this.getPrj().xy2ll(centerPointX, centerPointY)[1];
            maplonleft = centerPointGeoCoordLon - geoMapWidth * zoomFactor / 2.0f;
            maplonright = centerPointGeoCoordLon + geoMapWidth * zoomFactor / 2.0f;
            maplatup = centerPointGeoCoordLat + geoMapHeight * zoomFactor / 2.0f;
            maplatdown = centerPointGeoCoordLat - geoMapHeight * zoomFactor / 2.0f;
            System.out.println("Calculated center point: " + centerPointX + " " + centerPointY + " " + centerPointGeoCoordLat + " " + centerPointGeoCoordLon);
        }
        return maplonleft + "," + maplatdown + "," + maplonright + "," + maplatup;
    }
    
    public synchronized void update(final Observable o, final Object arg) {
        if (arg.getClass().getName().compareTo("mapapplet.imageload.ImageMessage") == 0) {
            final ImageMessage mesg = (ImageMessage)arg;
            if (mesg.ID.startsWith("WMSImage_")) {
                System.out.println("Downloaded image: " + mesg.ID);
                final int imageNumber = Integer.decode(mesg.ID.substring("WMSImage_".length()));
                final Image bufImg = mesg.image;
                this.wmsImages.setElementAt(bufImg, imageNumber);
                if (this.getLayerTransparencies().elementAt(imageNumber) == null) {
                    this.getLayerTransparencies().setElementAt(new Float(1.0f), imageNumber);
                }
                this.getParent().map.setLayersVector(this.wmsImages, this.getLayerTransparencies());
                if (this.mapReloaded) {
                    this.getParent().map.combineMapImages();
                }
            }
            else if (((ImageMessage)arg).ID == "MainMapImage") {
                this.mapReloaded = true;
            }
        }
        else if (arg.getClass().getName().compareTo("mapapplet.imageload.ErrorMessage") == 0) {
            final ErrorMessage mesg2 = (ErrorMessage)arg;
            if (mesg2.ID.startsWith("WMSImage_")) {
                final int imageNumber = Integer.decode(mesg2.ID.substring("WMSImage_".length()));
                this.wmsImages.setElementAt(null, imageNumber);
                this.getLayerTransparencies().setElementAt(null, imageNumber);
            }
        }
    }
    
    public Main getParent() {
        return this.parent;
    }
    
    public Vector getLayerTransparencies() {
        return this.layerTransparencies;
    }
    
    public void setLayerTransparencies(final Vector layerTransparencies) {
        this.layerTransparencies = layerTransparencies;
    }
    
    public MapProjection getPrj() {
        return this.prj;
    }
    
    public void setPrj(final MapProjection prj) {
        this.prj = prj;
    }
    
    public static Node getChild(final Node from, final String nodeName) {
        boolean parseDocument = false;
        NodeList children = null;
        if (from.getNodeName().equals("#document")) {
            parseDocument = true;
        }
        else {
            children = from.getChildNodes();
        }
        if (!parseDocument && children == null) {
            System.err.println("Error parsing " + from.getNodeName() + " node: found no children.");
            return null;
        }
        if (!parseDocument) {
            for (int i = 0; i < children.getLength(); ++i) {
                if (children.item(i).getNodeName().equalsIgnoreCase(nodeName)) {
                    return children.item(i);
                }
            }
        }
        else {
            for (Node firstChild = ((Document)from).getDocumentElement().getFirstChild(); firstChild != null; firstChild = firstChild.getNextSibling()) {
                if (firstChild.getNodeName().equalsIgnoreCase(nodeName)) {
                    return firstChild;
                }
            }
        }
        return null;
    }
    
    public static Vector getChildren(final Node from, final String nodeName) {
        final Vector nodesList = new Vector();
        final NodeList children = from.getChildNodes();
        for (int i = 0; i < children.getLength(); ++i) {
            if (children.item(i).getNodeName().equalsIgnoreCase(nodeName)) {
                nodesList.addElement(children.item(i));
            }
        }
        return nodesList;
    }
}
