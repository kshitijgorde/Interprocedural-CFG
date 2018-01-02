// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.gui;

import java.awt.event.ComponentEvent;
import com.itt.J2KViewer.util.ViewerConst;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import com.itt.J2KViewer.controller.ViewCentral;
import java.awt.event.ComponentListener;
import javax.swing.JSplitPane;

public class InfoPane extends JSplitPane implements ComponentListener
{
    private static final long serialVersionUID = 1L;
    private ViewCentral viewCentral;
    private OverviewImagePanel overviewImagePanel;
    private GeolocationPanel geolocationPanel;
    private StatusPanel statusPanel;
    private DynamicRangePanel dynamicRangePanel;
    private JScrollPane overviewScrollPane;
    
    public InfoPane(final ViewCentral viewCentral) {
        super(0);
        this.overviewImagePanel = null;
        this.geolocationPanel = null;
        this.statusPanel = null;
        this.dynamicRangePanel = null;
        this.viewCentral = viewCentral;
        this.initInfoPane();
    }
    
    public OverviewImagePanel getOverviewImagePanel() {
        return this.overviewImagePanel;
    }
    
    private void initInfoPane() {
        final JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.overviewImagePanel = new OverviewImagePanel(this.viewCentral);
        this.viewCentral.setOverviewImagePanel(this.overviewImagePanel);
        (this.overviewScrollPane = new JScrollPane(this.overviewImagePanel)).setPreferredSize(ViewerConst.INFO_PANEL_SIZE);
        this.overviewScrollPane.addComponentListener(this);
        this.setTopComponent(new InfoPanel("Overview Image", this.overviewScrollPane, this.viewCentral));
        this.geolocationPanel = new GeolocationPanel(this.viewCentral);
        this.statusPanel = new StatusPanel(this.viewCentral);
        this.dynamicRangePanel = new DynamicRangePanel(this.viewCentral);
        final InfoPanel infoPanel = new InfoPanel("Location", this.geolocationPanel, this.viewCentral);
        final InfoPanel infoPanel2 = new InfoPanel("Status", this.statusPanel, this.viewCentral);
        final InfoPanel infoPanel3 = new InfoPanel("Dynamic Range", this.dynamicRangePanel, this.viewCentral);
        final boolean gridx = false;
        int n = 0;
        gridBagConstraints.gridx = (gridx ? 1 : 0);
        gridBagConstraints.gridy = n;
        gridBagConstraints.anchor = 18;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 2;
        panel.add(infoPanel, gridBagConstraints);
        ++n;
        gridBagConstraints.gridy = n;
        panel.add(infoPanel2, gridBagConstraints);
        ++n;
        gridBagConstraints.gridy = n;
        panel.add(infoPanel3, gridBagConstraints);
        ++n;
        gridBagConstraints.gridy = n;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        panel.add(new JPanel(), gridBagConstraints);
        final JScrollPane bottomComponent = new JScrollPane(panel);
        bottomComponent.setHorizontalScrollBarPolicy(31);
        this.setBottomComponent(bottomComponent);
        this.setDividerLocation(ViewerConst.INFO_PANEL_SIZE.height);
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
        if (componentEvent.getComponent() == this.overviewScrollPane) {
            this.setDividerLocation(28);
        }
    }
    
    public void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
        if (componentEvent.getComponent() == this.overviewScrollPane) {
            this.setDividerLocation(-1);
        }
    }
}
