// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.gui;

import java.util.HashMap;
import com.itt.J2KViewer.georvm.coords.Utm_Coord_3d;
import java.text.DecimalFormat;
import java.awt.Point;
import com.itt.J2KViewer.util.ViewerConst;
import java.beans.PropertyChangeEvent;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.beans.PropertyVetoException;
import com.itt.J2KViewer.georvm.Projection;
import java.awt.Component;
import javax.swing.AbstractButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import com.itt.J2KViewer.georvm.NITFGeoUtils;
import com.itt.J2KViewer.controller.PropertyManager;
import com.itt.J2KViewer.controller.ViewCentral;
import com.itt.J2KViewer.util.Log;
import java.beans.PropertyChangeListener;
import javax.swing.JPanel;

public class GeolocationPanel extends JPanel implements PropertyChangeListener
{
    private static final long serialVersionUID = 1L;
    private Log log;
    private ViewCentral viewCentral;
    private PropertyManager propertyManager;
    private NITFGeoUtils geoloc;
    private JLabel usingLabel;
    private JLabel usingValue;
    private JLabel cornersLabel;
    private JLabel radioButtonLabel;
    private JRadioButton dmsRadioButton;
    private JRadioButton ddRadioButton;
    private JRadioButton ddmRadioButton;
    private ButtonGroup buttonGroup;
    private JPanel radioButtonPanel;
    private JButton cornersButton;
    private JLabel pixelLabel;
    private JLabel rawDataLabel;
    private JLabel rgbDataLabel;
    private JLabel locLabel;
    private JLabel projectionValueLabel;
    private JComboBox projectionValue;
    private JLabel emptyLabel;
    private JLabel emptyLabel2;
    private JLabel emptyLabel3;
    private JLabel ulLabel;
    private JLabel urLabel;
    private JLabel llLabel;
    private JLabel lrLabel;
    private JLabel pixelValue;
    private JLabel rawDataValue;
    private JLabel rgbDataValue;
    private JLabel locValue;
    private JLabel emptyValue;
    private JLabel emptyValue2;
    private JLabel emptyValue3;
    private JLabel ulValue;
    private JLabel urValue;
    private JLabel llValue;
    private JLabel lrValue;
    private JLabel spacer;
    static /* synthetic */ Class class$com$itt$J2KViewer$gui$GeolocationPanel;
    
    public GeolocationPanel(final ViewCentral viewCentral) {
        this.log = new Log((GeolocationPanel.class$com$itt$J2KViewer$gui$GeolocationPanel == null) ? (GeolocationPanel.class$com$itt$J2KViewer$gui$GeolocationPanel = class$("com.itt.J2KViewer.gui.GeolocationPanel")) : GeolocationPanel.class$com$itt$J2KViewer$gui$GeolocationPanel);
        this.viewCentral = null;
        this.propertyManager = null;
        this.geoloc = null;
        this.viewCentral = viewCentral;
        this.propertyManager = viewCentral.getPropertyManager();
        viewCentral.eventController().addPropertyChangeListener(this);
        this.initPanel();
    }
    
    private void initPanel() {
        this.cornersLabel = new JLabel("Corners:");
        this.radioButtonLabel = new JLabel(" ");
        (this.ddRadioButton = new JRadioButton("DD")).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                GeolocationPanel.this.propertyManager.setGeographicDisplayFormat(0);
            }
        });
        (this.dmsRadioButton = new JRadioButton("D/M/S")).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                GeolocationPanel.this.propertyManager.setGeographicDisplayFormat(2);
            }
        });
        (this.ddmRadioButton = new JRadioButton("D/DM")).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                GeolocationPanel.this.propertyManager.setGeographicDisplayFormat(1);
            }
        });
        (this.buttonGroup = new ButtonGroup()).add(this.ddRadioButton);
        this.buttonGroup.add(this.ddmRadioButton);
        this.buttonGroup.add(this.dmsRadioButton);
        this.ddRadioButton.setSelected(true);
        (this.radioButtonPanel = new JPanel()).add(this.ddRadioButton);
        this.radioButtonPanel.add(this.ddmRadioButton);
        this.radioButtonPanel.add(this.dmsRadioButton);
        (this.cornersButton = new JButton("Show")).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                GeolocationPanel.this.cornersButtonActionPerformed(actionEvent);
            }
        });
        this.pixelLabel = new JLabel("Pixel:");
        this.rawDataLabel = new JLabel("Data:");
        this.rgbDataLabel = new JLabel("Display:");
        this.usingLabel = new JLabel("Using:");
        this.locLabel = new JLabel("Location:");
        this.projectionValueLabel = new JLabel(" ");
        this.emptyLabel = new JLabel(" ");
        this.emptyLabel2 = new JLabel(" ");
        (this.emptyLabel3 = new JLabel(" ")).setVisible(false);
        (this.ulLabel = new JLabel("UL:")).setVisible(false);
        (this.urLabel = new JLabel("UR:")).setVisible(false);
        (this.llLabel = new JLabel("LL:")).setVisible(false);
        (this.lrLabel = new JLabel("LR:")).setVisible(false);
        this.pixelValue = new JLabel();
        this.rawDataValue = new JLabel();
        this.rgbDataValue = new JLabel();
        this.usingValue = new JLabel();
        this.locValue = new JLabel();
        this.projectionValue = new JComboBox();
        this.emptyValue = new JLabel(" ");
        this.emptyValue2 = new JLabel(" ");
        (this.emptyValue3 = new JLabel(" ")).setVisible(false);
        this.projectionValue.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                final Projection proj = (Projection)((JComboBox)actionEvent.getSource()).getSelectedItem();
                try {
                    GeolocationPanel.this.viewCentral.getPropertyManager().setProj(proj);
                }
                catch (PropertyVetoException ex) {
                    GeolocationPanel.this.log.info("Could not set selected projection", ex);
                }
            }
        });
        (this.ulValue = new JLabel()).setVisible(false);
        (this.urValue = new JLabel()).setVisible(false);
        (this.llValue = new JLabel()).setVisible(false);
        (this.lrValue = new JLabel()).setVisible(false);
        this.spacer = new JLabel("                                                                     ");
        this.setLayout(new GridBagLayout());
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.anchor = 13;
        gridBagConstraints.fill = 0;
        gridBagConstraints.insets = new Insets(0, 2, 0, 2);
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        gridBagConstraints2.weightx = 0.0;
        gridBagConstraints2.anchor = 17;
        gridBagConstraints2.insets = new Insets(0, 2, 0, 2);
        final GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.weightx = 1.0;
        gridBagConstraints3.anchor = 17;
        gridBagConstraints3.gridwidth = 0;
        gridBagConstraints3.fill = 1;
        int gridy = 0;
        ++gridy;
        gridBagConstraints.gridy = gridy;
        this.add(this.pixelLabel, gridBagConstraints);
        gridBagConstraints2.gridy = gridy;
        gridBagConstraints2.gridx = 1;
        this.add(this.pixelValue, gridBagConstraints2);
        ++gridy;
        gridBagConstraints.gridy = gridy;
        this.add(this.rawDataLabel, gridBagConstraints);
        gridBagConstraints2.gridy = gridy;
        gridBagConstraints2.gridx = 1;
        this.add(this.rawDataValue, gridBagConstraints2);
        ++gridy;
        gridBagConstraints.gridy = gridy;
        this.add(this.rgbDataLabel, gridBagConstraints);
        gridBagConstraints2.gridy = gridy;
        gridBagConstraints2.gridx = 1;
        this.add(this.rgbDataValue, gridBagConstraints2);
        ++gridy;
        gridBagConstraints.gridy = gridy;
        this.add(this.emptyLabel, gridBagConstraints);
        gridBagConstraints2.gridy = gridy;
        gridBagConstraints2.gridx = 1;
        this.add(this.emptyValue, gridBagConstraints2);
        ++gridy;
        gridBagConstraints.gridy = gridy;
        this.add(this.usingLabel, gridBagConstraints);
        gridBagConstraints2.gridy = gridy;
        gridBagConstraints2.gridx = 1;
        this.add(this.usingValue, gridBagConstraints2);
        ++gridy;
        gridBagConstraints.gridy = gridy;
        this.add(this.locLabel, gridBagConstraints);
        gridBagConstraints2.gridy = gridy;
        gridBagConstraints2.gridx = 1;
        this.add(this.locValue, gridBagConstraints2);
        ++gridy;
        gridBagConstraints.gridy = gridy;
        this.add(this.projectionValueLabel, gridBagConstraints);
        gridBagConstraints2.gridy = gridy;
        gridBagConstraints2.gridx = 1;
        this.add(this.projectionValue, gridBagConstraints2);
        ++gridy;
        gridBagConstraints.gridy = gridy;
        this.add(this.radioButtonLabel, gridBagConstraints);
        gridBagConstraints2.gridy = gridy;
        gridBagConstraints2.gridx = 1;
        this.add(this.radioButtonPanel, gridBagConstraints2);
        ++gridy;
        gridBagConstraints.gridy = gridy;
        this.add(this.emptyLabel2, gridBagConstraints);
        gridBagConstraints2.gridy = gridy;
        gridBagConstraints2.gridx = 1;
        this.add(this.emptyValue2, gridBagConstraints2);
        ++gridy;
        gridBagConstraints.gridy = gridy;
        this.add(this.cornersLabel, gridBagConstraints);
        gridBagConstraints2.gridy = gridy;
        gridBagConstraints2.gridx = 1;
        this.add(this.cornersButton, gridBagConstraints2);
        ++gridy;
        gridBagConstraints.gridy = gridy;
        this.add(this.emptyLabel3, gridBagConstraints);
        gridBagConstraints2.gridy = gridy;
        gridBagConstraints2.gridx = 1;
        this.add(this.emptyValue3, gridBagConstraints2);
        ++gridy;
        gridBagConstraints.gridy = gridy;
        this.add(this.ulLabel, gridBagConstraints);
        gridBagConstraints2.gridy = gridy;
        gridBagConstraints2.gridx = 1;
        this.add(this.ulValue, gridBagConstraints2);
        ++gridy;
        gridBagConstraints.gridy = gridy;
        this.add(this.urLabel, gridBagConstraints);
        gridBagConstraints2.gridy = gridy;
        gridBagConstraints2.gridx = 1;
        this.add(this.urValue, gridBagConstraints2);
        ++gridy;
        gridBagConstraints.gridy = gridy;
        this.add(this.llLabel, gridBagConstraints);
        gridBagConstraints2.gridy = gridy;
        gridBagConstraints2.gridx = 1;
        this.add(this.llValue, gridBagConstraints2);
        ++gridy;
        gridBagConstraints.gridy = gridy;
        this.add(this.lrLabel, gridBagConstraints);
        gridBagConstraints2.gridy = gridy;
        gridBagConstraints2.gridx = 1;
        this.add(this.lrValue, gridBagConstraints2);
        ++gridy;
        gridBagConstraints3.gridy = gridy;
        this.add(this.spacer, gridBagConstraints3);
        this.reset();
    }
    
    private void reset() {
        this.pixelValue.setText("");
        this.rawDataValue.setText("");
        this.rgbDataValue.setText("");
        this.locValue.setText("");
        this.projectionValue.setModel(new DefaultComboBoxModel());
        this.ulValue.setText("");
        this.urValue.setText("");
        this.llValue.setText("");
        this.lrValue.setText("");
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
            this.handleRGBValuesChanged(propertyChangeEvent);
        }
        else if (propertyName.equals("DisplayedProjection")) {
            this.handleProjChanged(propertyChangeEvent);
        }
    }
    
    private void handleOpen(final PropertyChangeEvent propertyChangeEvent) {
        this.geoloc = this.viewCentral.getNITFGeoUtils();
        if (this.propertyManager.isOpen() && this.geoloc != null && this.geoloc.isReady()) {
            if (this.geoloc.hasRPC()) {
                this.usingValue.setText("WGS-84, RPCs (" + this.geoloc.CETAG + ")");
            }
            else {
                this.usingValue.setText("WGS-84, Interpolation from corners");
            }
            this.setCornerCoordLabels(this.geoloc.getCornerStrings());
            this.projectionValue.setModel(new DefaultComboBoxModel<Projection>(new Projection[] { ViewerConst.GEOGRAPHIC_PROJECTION, ViewerConst.UTM_PROJECTION, ViewerConst.MGRS_PROJECTION }));
            this.projectionValue.setEnabled(true);
            final Projection proj = this.propertyManager.getProj();
            if (proj.toString().equals(ViewerConst.UTM_PROJECTION.toString())) {
                this.projectionValue.setSelectedItem(ViewerConst.UTM_PROJECTION);
            }
            else if (proj.toString().equals(ViewerConst.GEOGRAPHIC_PROJECTION.toString())) {
                this.projectionValue.setSelectedItem(ViewerConst.GEOGRAPHIC_PROJECTION);
            }
            else if (proj.toString().equals(ViewerConst.MGRS_PROJECTION.toString())) {
                this.projectionValue.setSelectedItem(ViewerConst.MGRS_PROJECTION);
            }
        }
        else {
            this.reset();
        }
    }
    
    private void handleMouseMoved(final PropertyChangeEvent propertyChangeEvent) {
        final Point point = (Point)propertyChangeEvent.getNewValue();
        String string = " ";
        String text = " ";
        if (point.x >= 0 && point.y >= 0) {
            string = String.valueOf(point.x) + "," + String.valueOf(point.y);
            if (this.geoloc != null && this.geoloc.isReady()) {
                final String geographicString = this.getGeographicString(point);
                final Projection projection = (Projection)this.projectionValue.getSelectedItem();
                if (projection == ViewerConst.GEOGRAPHIC_PROJECTION) {
                    text = geographicString;
                }
                else if (projection == ViewerConst.UTM_PROJECTION) {
                    text = this.formatUTMCoordString(point.x, point.y);
                }
                else if (projection == ViewerConst.MGRS_PROJECTION) {
                    text = this.formatMGRSCoordString(point.x, point.y);
                }
                else {
                    text = " ";
                }
            }
        }
        this.pixelValue.setText(string);
        this.locValue.setText(text);
    }
    
    private void handleRawValuesChanged(final PropertyChangeEvent propertyChangeEvent) {
        String text = "";
        final int[] array = (int[])propertyChangeEvent.getNewValue();
        if (array != null) {
            for (int i = 0; i < array.length; ++i) {
                if (i > 0) {
                    text += ",";
                }
                text += array[i];
            }
        }
        this.rawDataValue.setText(text);
    }
    
    private void handleRGBValuesChanged(final PropertyChangeEvent propertyChangeEvent) {
        String text = " ";
        final int[] array = (int[])propertyChangeEvent.getNewValue();
        if (array != null) {
            if (this.propertyManager.isShowRGB()) {
                text = array[0] + "," + array[1] + "," + array[2];
            }
            else {
                text = String.valueOf(array[0]);
            }
        }
        this.rgbDataValue.setText(text);
    }
    
    private void handleProjChanged(final PropertyChangeEvent propertyChangeEvent) {
        this.projectionValue.setSelectedItem(this.viewCentral.getPropertyManager().getProj());
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
    
    public void setCornerCoordLabels(final HashMap hashMap) {
        if (this.ulValue != null && hashMap != null) {
            this.ulValue.setText(hashMap.get("UpperLeft"));
            this.urValue.setText(hashMap.get("UpperRight"));
            this.llValue.setText(hashMap.get("LowerLeft"));
            this.lrValue.setText(hashMap.get("LowerRight"));
        }
    }
    
    protected void cornersButtonActionPerformed(final ActionEvent actionEvent) {
        final boolean visible = this.ulLabel.isVisible();
        if (visible) {
            this.cornersButton.setText("Show");
        }
        else {
            this.cornersButton.setText("Hide");
        }
        this.emptyLabel3.setVisible(!visible);
        this.emptyValue3.setVisible(!visible);
        this.ulLabel.setVisible(!visible);
        this.urLabel.setVisible(!visible);
        this.llLabel.setVisible(!visible);
        this.lrLabel.setVisible(!visible);
        this.ulValue.setVisible(!visible);
        this.urValue.setVisible(!visible);
        this.llValue.setVisible(!visible);
        this.lrValue.setVisible(!visible);
        this.updateUI();
    }
    
    private String getGeographicString(final Point point) {
        String s;
        if (this.dmsRadioButton.isSelected()) {
            s = this.formatGeoCoordDMSString(point.x, point.y);
        }
        else if (this.ddmRadioButton.isSelected()) {
            s = this.formatGeoCoordDDMString(point.x, point.y);
        }
        else {
            s = this.formatGeoCoordString(point.x, point.y);
        }
        return s;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
}
