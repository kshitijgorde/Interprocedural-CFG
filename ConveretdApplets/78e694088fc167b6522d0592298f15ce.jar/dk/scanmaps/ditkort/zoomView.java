// 
// Decompiled by Procyon v0.5.30
// 

package dk.scanmaps.ditkort;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.net.URL;
import java.awt.Component;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class zoomView extends JPanel
{
    static final long serialVersionUID = 0L;
    private JLabel zoomLabel;
    private Login c_l;
    private String c_mapurl;
    private String c_serviceNamePrint;
    private String c_serviceName;
    private String c_serviceNamePrintLayer;
    private String c_serviceNameScreen;
    private String c_serviceNameLayer;
    public int c_mapSizeX;
    public int c_mapSizeY;
    private float c_centerX;
    private float c_centerY;
    public float c_pixelSize;
    private int c_updateMapCounter;
    CResourceManager myResource;
    
    public zoomView(final float centerX, final float centerY) {
        this.zoomLabel = new JLabel();
        this.c_l = new Login();
        this.c_serviceNamePrint = Constant.servicename_DTK25;
        this.c_serviceName = this.c_serviceNamePrint;
        this.c_serviceNamePrintLayer = Constant.layerName_DTK25;
        this.c_serviceNameScreen = "DTK_Kort25_klassisk";
        this.c_mapSizeX = 234;
        this.c_mapSizeY = 186;
        this.c_centerX = 588450.0f;
        this.c_centerY = 6139571.0f;
        this.c_pixelSize = 1.25f;
        this.c_updateMapCounter = 0;
        this.myResource = CResourceManager.instance();
        this.setToolTipText(this.myResource.getResource("zoomview.udsnit.tip"));
        this.c_pixelSize *= 4.0f;
        if (this.c_serviceName.equals(this.c_serviceNameScreen)) {
            this.c_serviceNameLayer = "";
        }
        else if (this.c_serviceName.equals(this.c_serviceNamePrint)) {
            this.c_serviceNameLayer = this.c_serviceNamePrintLayer;
        }
        this.c_mapurl = this.getMapURL();
        this.setLayout(new BoxLayout(this, 3));
        this.zoomLabel.setBorder(BorderFactory.createTitledBorder(this.myResource.getResource("findsted.udsnit")));
        this.add(this.zoomLabel);
        this.updateMap(centerX, centerY, false, false);
    }
    
    public void changeLocaleInZoomView() {
        this.zoomLabel.setBorder(BorderFactory.createTitledBorder(this.myResource.getResource("findsted.udsnit")));
    }
    
    public void setVisibility(final boolean set) {
        this.zoomLabel.setEnabled(set);
    }
    
    private String getMapURL() {
        return this.c_mapurl = "http://kortforsyningen.kms.dk/service?servicename=" + this.c_serviceName + "&" + "service=WMS&" + "version=1.1.1&" + "request=GetMap&" + "srs=EPSG:25832&" + "format=" + Constant.imageFormat + "&" + "width=" + this.c_mapSizeX + "&" + "height=" + this.c_mapSizeY + "&" + "exceptions=application/vnd.ogc.se_xml&" + "&styles=&layers=" + this.c_serviceNameLayer;
    }
    
    public void setMapURL(final String serviceName, final String serviceNameLayer) {
        this.c_serviceName = serviceName;
        this.c_serviceNameLayer = serviceNameLayer;
        this.c_mapurl = "http://kortforsyningen.kms.dk/service?servicename=" + this.c_serviceName + "&" + "service=WMS&" + "version=1.1.1&" + "request=GetMap&" + "srs=EPSG:25832&" + "format=" + Constant.imageFormat + "&" + "width=" + this.c_mapSizeX + "&" + "height=" + this.c_mapSizeY + "&" + "exceptions=application/vnd.ogc.se_xml&" + "&styles=&layers=" + this.c_serviceNameLayer;
    }
    
    public void setZoomFactor(final int scale) {
        this.c_pixelSize = 1.25f * scale;
    }
    
    public boolean updateMap(final float centerX, final float centerY, final boolean utm, final boolean latlong) {
        String grids = "";
        final int sizeX = this.c_mapSizeX;
        final int sizeY = this.c_mapSizeY;
        this.c_centerX = centerX;
        this.c_centerY = centerY;
        final float ULX = this.c_centerX - sizeX / 2 * this.c_pixelSize;
        final float ULY = this.c_centerY + sizeY / 2 * this.c_pixelSize;
        final float LRX = this.c_centerX + sizeX / 2 * this.c_pixelSize - 1.0f;
        final float LRY = this.c_centerY - sizeY / 2 * this.c_pixelSize + 1.0f;
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
        try {
            final URL mapUrl = new URL(String.valueOf(this.c_mapurl) + grids + "&ticket=" + this.c_l.getTicket() + "&styles=" + "&bbox=" + ULX + "," + LRY + "," + LRX + "," + ULY);
            this.zoomLabel.setIcon(new ImageIcon(mapUrl));
            if (Constant.debugMode) {
                System.out.println("URL of the zoomview " + mapUrl);
            }
            this.updateZoomIfNeeded();
            return true;
        }
        catch (Exception e) {
            if (Constant.debugMode) {
                System.err.println("Couldn't updateMap!");
            }
            return false;
        }
    }
    
    private void updateZoomIfNeeded() {
        if (this.zoomLabel.getIcon().getIconWidth() <= 0) {
            if (this.c_updateMapCounter < 3) {
                this.updateMap(this.c_centerX, this.c_centerY, false, false);
                ++this.c_updateMapCounter;
            }
        }
        else {
            this.c_updateMapCounter = 0;
        }
    }
}
