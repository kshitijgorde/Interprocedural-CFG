// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.gui;

import java.text.DecimalFormat;
import java.awt.Rectangle;
import java.beans.PropertyChangeEvent;
import java.awt.Component;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import com.itt.J2KViewer.controller.ViewCentral;
import javax.swing.JLabel;
import com.itt.J2KViewer.controller.PropertyManager;
import java.beans.PropertyChangeListener;
import javax.swing.JPanel;

public class StatusPanel extends JPanel implements PropertyChangeListener
{
    private static final long serialVersionUID = 1L;
    private PropertyManager propertyManager;
    private JLabel dimensionsLabel;
    private JLabel qualityLabel;
    private JLabel levelsLabel;
    private JLabel zoomLabel;
    private JLabel streamLabel;
    private JLabel bytesLabel;
    private JLabel bandsLabel;
    private JLabel dimensionsValue;
    private JLabel qualityValue;
    private JLabel levelsValue;
    private JLabel zoomValue;
    private JLabel streamValue;
    private JLabel bytesValue;
    private JLabel bandsValue;
    private JPanel spacer;
    
    public StatusPanel(final ViewCentral viewCentral) {
        this.propertyManager = null;
        this.propertyManager = viewCentral.getPropertyManager();
        viewCentral.eventController().addPropertyChangeListener(this);
        this.initPanel();
    }
    
    private void initPanel() {
        this.dimensionsLabel = new JLabel("Dimensions:");
        this.qualityLabel = new JLabel("Quality:");
        this.levelsLabel = new JLabel("Levels:");
        this.zoomLabel = new JLabel("Zoom:");
        this.streamLabel = new JLabel("Stream:");
        this.bytesLabel = new JLabel("Bytes:");
        this.bandsLabel = new JLabel("Bands:");
        this.dimensionsValue = new JLabel();
        this.qualityValue = new JLabel();
        this.levelsValue = new JLabel();
        this.zoomValue = new JLabel();
        this.streamValue = new JLabel();
        this.bytesValue = new JLabel();
        this.bandsValue = new JLabel();
        this.spacer = new JPanel();
        this.setLayout(new GridBagLayout());
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.anchor = 13;
        gridBagConstraints.fill = 0;
        gridBagConstraints.insets = new Insets(0, 2, 0, 2);
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.anchor = 17;
        gridBagConstraints2.gridwidth = 0;
        gridBagConstraints2.insets = new Insets(0, 2, 0, 2);
        final GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.weightx = 1.0;
        gridBagConstraints3.anchor = 17;
        gridBagConstraints3.gridwidth = 0;
        gridBagConstraints3.fill = 1;
        int gridy = 0;
        gridBagConstraints.gridy = gridy;
        this.add(this.dimensionsLabel, gridBagConstraints);
        gridBagConstraints2.gridy = gridy;
        this.add(this.dimensionsValue, gridBagConstraints2);
        ++gridy;
        gridBagConstraints.gridy = gridy;
        this.add(this.qualityLabel, gridBagConstraints);
        gridBagConstraints2.gridy = gridy;
        this.add(this.qualityValue, gridBagConstraints2);
        ++gridy;
        gridBagConstraints.gridy = gridy;
        this.add(this.levelsLabel, gridBagConstraints);
        gridBagConstraints2.gridy = gridy;
        this.add(this.levelsValue, gridBagConstraints2);
        ++gridy;
        gridBagConstraints.gridy = gridy;
        this.add(this.zoomLabel, gridBagConstraints);
        gridBagConstraints2.gridy = gridy;
        this.add(this.zoomValue, gridBagConstraints2);
        ++gridy;
        gridBagConstraints.gridy = gridy;
        this.add(this.streamLabel, gridBagConstraints);
        gridBagConstraints2.gridy = gridy;
        this.add(this.streamValue, gridBagConstraints2);
        ++gridy;
        gridBagConstraints.gridy = gridy;
        this.add(this.bytesLabel, gridBagConstraints);
        gridBagConstraints2.gridy = gridy;
        this.add(this.bytesValue, gridBagConstraints2);
        ++gridy;
        gridBagConstraints.gridy = gridy;
        this.add(this.bandsLabel, gridBagConstraints);
        gridBagConstraints2.gridy = gridy;
        this.add(this.bandsValue, gridBagConstraints2);
        ++gridy;
        gridBagConstraints3.gridy = gridy;
        this.add(this.spacer, gridBagConstraints3);
        this.reset();
    }
    
    private void reset() {
        this.dimensionsValue.setText("");
        this.qualityValue.setText("");
        this.levelsValue.setText("");
        this.zoomValue.setText("");
        this.streamValue.setText("");
        this.bytesValue.setText("");
        this.bandsValue.setText("");
    }
    
    public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
        final String propertyName = propertyChangeEvent.getPropertyName();
        if (propertyName.equals("Open")) {
            this.handleOpen();
        }
        else if (this.propertyManager.isOpen()) {
            if (propertyName.equals("TotalDimensions")) {
                this.handleTotalDimensions();
            }
            else if (propertyName.equals("QualityLayers")) {
                this.handleQuality();
            }
            else if (propertyName.equals("DiscardedZoomLevels")) {
                this.handleZoom();
            }
            else if (propertyName.equals("TotalBytes")) {
                this.handleBytesChanged();
            }
            else if (propertyName.equals("BytesTransferred")) {
                this.handleBytesChanged();
            }
            else if (propertyName.equals("KduStatus")) {
                this.streamValue.setText(propertyChangeEvent.getNewValue().toString());
            }
            else if (propertyName.equals("RGBMap")) {
                this.handleRGBMap();
            }
        }
    }
    
    private void handleOpen() {
        if (this.propertyManager.isOpen()) {
            this.handleTotalDimensions();
            this.handleQuality();
            this.handleZoom();
            this.handleBytesChanged();
            this.handleRGBMap();
        }
        else {
            this.reset();
        }
    }
    
    private void handleTotalDimensions() {
        final Rectangle totalDimensions = this.propertyManager.getTotalDimensions();
        this.dimensionsValue.setText(totalDimensions.width + "W X " + totalDimensions.height + "H");
    }
    
    private void handleQuality() {
        this.qualityValue.setText(this.propertyManager.getQualityLayers() + "/" + this.propertyManager.getTotalQualityLayers());
    }
    
    private void handleZoom() {
        final int maxDiscardedZoomLevels = this.propertyManager.getMaxDiscardedZoomLevels();
        final int discardedZoomLevels = this.propertyManager.getDiscardedZoomLevels();
        this.levelsValue.setText(discardedZoomLevels + "/" + maxDiscardedZoomLevels);
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
        this.zoomValue.setText(new DecimalFormat("##0.#").format(n2 * 100.0) + "% " + s);
    }
    
    private void handleBytesChanged() {
        final long totalBytesAsKB = this.propertyManager.getTotalBytesAsKB();
        final long bytesTransferredAsKB = this.propertyManager.getBytesTransferredAsKB();
        String text;
        if (totalBytesAsKB > 0L) {
            final double min = Math.min(100.0, this.propertyManager.getBytesTransferred() / this.propertyManager.getTotalBytes() * 100.0);
            final DecimalFormat decimalFormat = new DecimalFormat("##0.00");
            final StringBuffer sb = new StringBuffer();
            sb.append(String.valueOf(bytesTransferredAsKB)).append(" / ").append(String.valueOf(totalBytesAsKB)).append(" KB (").append(decimalFormat.format(min)).append("%)");
            text = sb.toString();
        }
        else {
            text = String.valueOf(bytesTransferredAsKB) + " KB";
        }
        this.bytesValue.setText(text);
    }
    
    private void handleRGBMap() {
        final int[] rgbMap = this.propertyManager.getRGBMap();
        String text = "";
        if (rgbMap.length == 3) {
            text = "R:" + (rgbMap[0] + 1) + " G:" + (rgbMap[1] + 1) + " B:" + (rgbMap[2] + 1);
        }
        else if (rgbMap.length == 1) {
            text = "R:" + (rgbMap[0] + 1) + " G:" + (rgbMap[0] + 1) + " B:" + (rgbMap[0] + 1);
        }
        this.bandsValue.setText(text);
    }
}
