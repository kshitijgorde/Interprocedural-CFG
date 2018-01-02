// 
// Decompiled by Procyon v0.5.30
// 

package dk.scanmaps.ditkort;

import java.awt.Component;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class navigationskortView extends JPanel
{
    static final long serialVersionUID = 0L;
    private NavigationMapLabel c_navMapLabel;
    private static final float c_pixelSize = 1.25f;
    private static final int c_mainImageSizeX = 564;
    private static final int c_mainImageSizeY = 450;
    private static final int c_navImagePixelSize = 2022;
    private static final int c_navImageULX = 429190;
    private static final int c_navImageULY = 6408389;
    private int c_centerX;
    private int c_centerY;
    private int cx;
    private int cy;
    CResourceManager myResource;
    
    public navigationskortView() {
        this.c_navMapLabel = new NavigationMapLabel();
        this.c_centerX = 588450;
        this.c_centerY = 6139571;
        this.cx = 0;
        this.cy = 0;
        this.myResource = CResourceManager.instance();
        this.setLayout(new BoxLayout(this, 3));
        this.createAndShowGUI();
    }
    
    public int getCenterX() {
        return this.c_centerX;
    }
    
    public int getCenterY() {
        return this.c_centerY;
    }
    
    public void setHotSpotSizeFactor(final int HotSpotSizeFactor) {
        this.c_navMapLabel.setHotSpotSizeFactor(HotSpotSizeFactor);
    }
    
    public void setHotSpotSize(final String HotSpotSizeX, final String HotSpotSizeY) {
        this.c_navMapLabel.setHotSpotSize(HotSpotSizeX, HotSpotSizeY);
    }
    
    public void updateHotspotPosition(final int centerX, final int centerY) {
        this.c_centerX = centerX;
        this.c_centerY = centerY;
        if (Constant.coordsValues) {
            System.out.println("updateHotSpotPosition. centerX: " + this.c_centerX + " centerY: " + this.c_centerY);
        }
        this.cx = (int)((centerX - 352.5f - 429190.0f) / 2022.0f);
        this.cy = (int)((-centerY - 281.25f + 6408389.0f) / 2022.0f);
        this.cx += 4;
        this.cy += 28;
        this.c_navMapLabel.drawBox(this.cx, this.cy);
    }
    
    public void createAndShowGUI() {
        this.c_navMapLabel.setSize(new Dimension(500, 500));
        this.c_navMapLabel.setBorder(BorderFactory.createTitledBorder(this.myResource.getResource("findsted.navigationskort")));
        this.c_navMapLabel.setAlignmentX(0.0f);
        this.add(this.c_navMapLabel);
        this.updateHotspotPosition(this.c_centerX, this.c_centerY);
    }
    
    public void setVisibility(final boolean set) {
        this.c_navMapLabel.setEnabled(set);
    }
}
