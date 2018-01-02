// 
// Decompiled by Procyon v0.5.30
// 

package dk.scanmaps.ditkort;

import java.awt.event.ItemEvent;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import javax.swing.JPanel;

public class formatCheckboxes extends JPanel implements ItemListener
{
    static final long serialVersionUID = 0L;
    private JCheckBox UTMgrid;
    private JCheckBox longLatGrid;
    private boolean UTMgridSelected;
    private boolean longLatGridSelected;
    CResourceManager myResources;
    zoomView c_zoomView;
    ditkortView c_ditkortView;
    ditkortView c_ditkortViewNavngiv;
    
    public formatCheckboxes(final zoomView z, final ditkortView dv1, final ditkortView dv2) {
        this.UTMgridSelected = false;
        this.longLatGridSelected = false;
        this.myResources = CResourceManager.instance();
        this.c_zoomView = z;
        this.c_ditkortView = dv1;
        this.c_ditkortViewNavngiv = dv2;
        this.UTMgrid = new JCheckBox(this.myResources.getResource("marker.EtKmUTMnet"));
        this.longLatGrid = new JCheckBox(this.myResources.getResource("marker.LatitudeAltitude"));
        this.setLayout(new BoxLayout(this, 3));
        this.UTMgrid.setAlignmentX(0.0f);
        this.longLatGrid.setAlignmentX(0.0f);
        final Dimension dim = new Dimension(700, 45);
        this.setPreferredSize(dim);
        this.setMinimumSize(dim);
        this.setMaximumSize(dim);
        this.UTMgrid.addItemListener(this);
        this.longLatGrid.addItemListener(this);
        this.add(this.UTMgrid);
        this.add(this.longLatGrid);
    }
    
    public void showCheckboxes(final boolean show) {
        this.UTMgrid.setEnabled(show);
        this.UTMgrid.setVisible(show);
        this.longLatGrid.setEnabled(show);
        this.longLatGrid.setVisible(show);
    }
    
    public void setUTMText(final String mapscale) {
        if (mapscale.equals(Constant.servicename_DTK25)) {
            this.UTMgrid.setText(this.myResources.getResource("marker.EtKmUTMnet"));
        }
        else if (mapscale.equals(Constant.servicename_DTK100)) {
            this.UTMgrid.setText(this.myResources.getResource("marker.FemKmUTMnet"));
        }
    }
    
    public void setLongLatText() {
        this.longLatGrid.setText(this.myResources.getResource("marker.LatitudeAltitude"));
    }
    
    public void itemStateChanged(final ItemEvent e) {
        final Object source = e.getItemSelectable();
        if (source == this.UTMgrid) {
            this.UTMgridSelected = true;
        }
        else if (source == this.longLatGrid) {
            this.longLatGridSelected = true;
        }
        if (e.getStateChange() == 2) {
            if (source == this.UTMgrid) {
                this.UTMgridSelected = false;
            }
            else if (source == this.longLatGrid) {
                this.longLatGridSelected = false;
            }
        }
        if (Constant.debugMode) {
            System.out.println("UTMgridSelected is " + this.UTMgridSelected);
            System.out.println("longLatGridSelected is " + this.longLatGridSelected);
        }
        this.c_ditkortView.updateMap();
        this.c_ditkortViewNavngiv.updateMap();
        this.c_zoomView.updateMap(this.c_ditkortView.c_centerX, this.c_ditkortView.c_centerY, this.UTMgridSelected, this.longLatGridSelected);
    }
    
    public boolean isUTMgridSelected() {
        return this.UTMgridSelected;
    }
    
    public boolean isLongLatGridSelected() {
        return this.longLatGridSelected;
    }
}
