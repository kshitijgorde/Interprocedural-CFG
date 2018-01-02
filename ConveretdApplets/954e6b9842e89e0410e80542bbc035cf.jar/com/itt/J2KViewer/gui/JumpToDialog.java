// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.gui;

import java.beans.PropertyChangeEvent;
import java.awt.event.FocusEvent;
import com.itt.J2KViewer.georvm.coords.Utm_Coord_3d;
import com.itt.J2KViewer.controller.DimensionManager;
import java.beans.PropertyVetoException;
import com.itt.J2KViewer.util.GeoJumpUtil;
import com.itt.J2KViewer.util.Helper;
import com.itt.J2KViewer.controller.PropertyManager;
import java.awt.FlowLayout;
import javax.swing.AbstractButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.itt.J2KViewer.util.ViewerConst;
import com.itt.J2KViewer.georvm.Projection;
import java.awt.GridBagConstraints;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Frame;
import java.text.DecimalFormat;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import com.itt.J2KViewer.georvm.NITFGeoUtils;
import java.awt.Component;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import com.itt.J2KViewer.controller.ViewCentral;
import com.itt.J2KViewer.util.Log;
import java.beans.PropertyChangeListener;
import java.awt.event.FocusListener;
import javax.swing.JDialog;

public class JumpToDialog extends JDialog implements FocusListener, PropertyChangeListener
{
    private static final long serialVersionUID = 1L;
    private static Log log;
    private ViewCentral viewCentral;
    private JPanel locationPanel;
    private JPanel btnPanel;
    private JButton cancelBtn;
    private JLabel lblPixelHeading;
    private JLabel lblPixelX;
    private JLabel lblPixelXRange;
    private JLabel lblPixelY;
    private JLabel lblPixelYRange;
    private JButton okBtn;
    private JTextField pixelX;
    private JTextField pixelY;
    private JLabel lblGeolocationHeading;
    private JLabel lblProjType;
    private JComboBox projType;
    private JLabel lblCoord1;
    private JLabel lblCoord2;
    private JTextField coord1;
    private JTextField coord2;
    private JLabel lblZoomLevelHeading;
    private JLabel lblZoomLevel;
    private JComboBox zoomCombo;
    private JCheckBox checkCrosshair;
    private Rectangle totalDimensions;
    private Rectangle viewPort;
    private Point currentLocation;
    private boolean showGeolocation;
    private Component lastFocusComponent;
    private NITFGeoUtils geoUtils;
    private JRadioButton ddRadioButton;
    private JLabel radioButtonLabel;
    private JRadioButton dmsRadioButton;
    private JRadioButton ddmRadioButton;
    private ButtonGroup buttonGroup;
    private JPanel radioButtonPanel;
    private JTextField coord1b;
    private JTextField coord2b;
    private JTextField coord1c;
    private JTextField coord2c;
    private JLabel degreesLbl;
    private JLabel minutesLbl;
    private JLabel secondsLbl;
    private int selectedGeoFormat;
    DecimalFormat df;
    static /* synthetic */ Class class$com$itt$J2KViewer$gui$JumpToDialog;
    
    public JumpToDialog(final Frame frame, final boolean b, final ViewCentral viewCentral) {
        super(frame, b);
        this.viewCentral = null;
        this.showGeolocation = false;
        this.degreesLbl = new JLabel("Degrees");
        this.minutesLbl = new JLabel("Minutes");
        this.secondsLbl = new JLabel("Seconds");
        this.selectedGeoFormat = 0;
        this.df = new DecimalFormat("##0.000000");
        this.viewCentral = viewCentral;
        this.geoUtils = viewCentral.getNITFGeoUtils();
        if (this.geoUtils != null && this.geoUtils.isReady()) {
            this.showGeolocation = true;
        }
        this.initComponents();
        if (this.showGeolocation) {
            this.adjustCoordDisplay();
        }
        this.getRootPane().setDefaultButton(this.okBtn);
    }
    
    private void initComponents() {
        final PropertyManager propertyManager = this.viewCentral.getPropertyManager();
        final boolean showCrosshair = propertyManager.isShowCrosshair();
        this.totalDimensions = propertyManager.getTotalDimensions();
        this.viewPort = propertyManager.getViewPort();
        this.currentLocation = new Point(this.viewPort.x + this.viewPort.width / 2, this.viewPort.y + this.viewPort.height / 2);
        int n = 0;
        final boolean gridx = false;
        this.setDefaultCloseOperation(2);
        this.setTitle("Jump To Location");
        (this.locationPanel = new JPanel()).setLayout(new GridBagLayout());
        this.locationPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        this.lblPixelHeading = new JLabel("Enter a pixel location:");
        this.lblPixelX = new JLabel("x:");
        this.lblPixelXRange = new JLabel("(0 - " + (this.totalDimensions.width - 1) + ")");
        this.lblPixelY = new JLabel("y:");
        this.lblPixelYRange = new JLabel("(0 - " + (this.totalDimensions.height - 1) + ")");
        (this.pixelX = new JTextField("", 6)).setToolTipText("Enter the column (x) location of the pixel");
        this.pixelX.addFocusListener(this);
        this.pixelX.addKeyListener(new KeyAdapter() {
            public void keyTyped(final KeyEvent keyEvent) {
                JumpToDialog.this.txtKeyTyped(keyEvent, true, true, true);
            }
        });
        (this.pixelY = new JTextField("", 6)).setToolTipText("Enter the row (y) location of the pixel");
        this.pixelY.addFocusListener(this);
        this.pixelY.addKeyListener(new KeyAdapter() {
            public void keyTyped(final KeyEvent keyEvent) {
                JumpToDialog.this.txtKeyTyped(keyEvent, true, true, true);
            }
        });
        this.pixelX.setText(String.valueOf(this.currentLocation.x));
        this.pixelY.setText(String.valueOf(this.currentLocation.y));
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.insets = new Insets(5, 0, 5, 5);
        gridBagConstraints.gridy = n;
        gridBagConstraints.gridx = (gridx ? 1 : 0);
        this.locationPanel.add(this.lblPixelHeading, gridBagConstraints);
        ++n;
        int gridx2 = 0;
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        gridBagConstraints2.anchor = 13;
        gridBagConstraints2.insets = new Insets(5, 0, 5, 5);
        gridBagConstraints2.gridy = n;
        gridBagConstraints2.gridx = gridx2;
        this.locationPanel.add(this.lblPixelX, gridBagConstraints2);
        ++gridx2;
        final GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
        gridBagConstraints3.anchor = 17;
        gridBagConstraints3.gridwidth = 0;
        gridBagConstraints3.insets = new Insets(5, 0, 5, 5);
        gridBagConstraints3.gridy = n;
        gridBagConstraints3.gridx = gridx2;
        this.locationPanel.add(this.pixelX, gridBagConstraints3);
        ++gridx2;
        final GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
        gridBagConstraints4.anchor = 10;
        gridBagConstraints4.gridwidth = 2;
        gridBagConstraints4.insets = new Insets(5, 0, 5, 5);
        gridBagConstraints4.gridy = n;
        gridBagConstraints4.gridx = gridx2;
        this.locationPanel.add(this.lblPixelXRange, gridBagConstraints4);
        ++n;
        int gridx3 = 0;
        final GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
        gridBagConstraints5.anchor = 13;
        gridBagConstraints5.insets = new Insets(5, 0, 5, 5);
        gridBagConstraints5.gridy = n;
        gridBagConstraints5.gridx = gridx3;
        this.locationPanel.add(this.lblPixelY, gridBagConstraints5);
        ++gridx3;
        final GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
        gridBagConstraints6.anchor = 17;
        gridBagConstraints6.gridwidth = 0;
        gridBagConstraints6.insets = new Insets(5, 0, 5, 5);
        gridBagConstraints6.gridy = n;
        gridBagConstraints6.gridx = gridx3;
        this.locationPanel.add(this.pixelY, gridBagConstraints6);
        ++gridx3;
        final GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
        gridBagConstraints7.anchor = 10;
        gridBagConstraints7.gridwidth = 2;
        gridBagConstraints7.insets = new Insets(5, 0, 5, 5);
        gridBagConstraints7.gridy = n;
        gridBagConstraints7.gridx = gridx3;
        this.locationPanel.add(this.lblPixelYRange, gridBagConstraints7);
        if (this.showGeolocation) {
            this.lblGeolocationHeading = new JLabel("Or a geographic location:");
            this.lblProjType = new JLabel("Projection:");
            this.projType = new JComboBox((E[])new Projection[] { ViewerConst.GEOGRAPHIC_PROJECTION, ViewerConst.UTM_PROJECTION, ViewerConst.MGRS_PROJECTION });
            final Projection proj = propertyManager.getProj();
            if (proj.toString().equals(ViewerConst.UTM_PROJECTION.toString())) {
                this.projType.setSelectedItem(ViewerConst.UTM_PROJECTION);
            }
            else if (proj.toString().equals(ViewerConst.GEOGRAPHIC_PROJECTION.toString())) {
                this.projType.setSelectedItem(ViewerConst.GEOGRAPHIC_PROJECTION);
            }
            else if (proj.toString().equals(ViewerConst.MGRS_PROJECTION.toString())) {
                this.projType.setSelectedItem(ViewerConst.MGRS_PROJECTION);
            }
            this.projType.addFocusListener(this);
            this.projType.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent actionEvent) {
                    JumpToDialog.this.adjustCoordDisplay();
                }
            });
            this.projType.setToolTipText("Select a projection format for entering a location");
            this.radioButtonLabel = new JLabel("Format:");
            (this.ddRadioButton = new JRadioButton("DD")).addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent actionEvent) {
                    JumpToDialog.this.selectedGeoFormat = 0;
                    JumpToDialog.this.formatGeoLocationFields(0);
                }
            });
            (this.dmsRadioButton = new JRadioButton("D/M/S")).addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent actionEvent) {
                    JumpToDialog.this.selectedGeoFormat = 2;
                    JumpToDialog.this.formatGeoLocationFields(2);
                }
            });
            (this.ddmRadioButton = new JRadioButton("D/DM")).addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent actionEvent) {
                    JumpToDialog.this.selectedGeoFormat = 1;
                    JumpToDialog.this.formatGeoLocationFields(1);
                }
            });
            (this.buttonGroup = new ButtonGroup()).add(this.ddRadioButton);
            this.buttonGroup.add(this.ddmRadioButton);
            this.buttonGroup.add(this.dmsRadioButton);
            this.ddRadioButton.setSelected(true);
            (this.radioButtonPanel = new JPanel()).add(this.ddRadioButton);
            this.radioButtonPanel.add(this.ddmRadioButton);
            this.radioButtonPanel.add(this.dmsRadioButton);
            this.lblCoord1 = new JLabel();
            (this.coord1 = new JTextField("", 10)).setEditable(true);
            this.coord1.addFocusListener(this);
            this.coord1.addKeyListener(new KeyAdapter() {
                public void keyTyped(final KeyEvent keyEvent) {
                    JumpToDialog.this.txtKeyTyped(keyEvent, false, false, true);
                }
            });
            (this.coord1b = new JTextField("", 5)).addFocusListener(this);
            this.coord1b.setEditable(true);
            this.coord1b.setVisible(false);
            this.coord1b.addKeyListener(new KeyAdapter() {
                public void keyTyped(final KeyEvent keyEvent) {
                    JumpToDialog.this.txtKeyTyped(keyEvent, true, false, true);
                }
            });
            (this.coord1c = new JTextField("", 5)).addFocusListener(this);
            this.coord1c.setEditable(true);
            this.coord1c.setVisible(false);
            this.coord1c.addKeyListener(new KeyAdapter() {
                public void keyTyped(final KeyEvent keyEvent) {
                    JumpToDialog.this.txtKeyTyped(keyEvent, true, false, true);
                }
            });
            this.lblCoord2 = new JLabel();
            (this.coord2 = new JTextField("", 10)).setEditable(true);
            this.coord2.addFocusListener(this);
            this.coord2.addKeyListener(new KeyAdapter() {
                public void keyTyped(final KeyEvent keyEvent) {
                    JumpToDialog.this.txtKeyTyped(keyEvent, false, false, true);
                }
            });
            (this.coord2b = new JTextField("", 5)).addFocusListener(this);
            this.coord2b.setEditable(true);
            this.coord2b.setVisible(false);
            this.coord2b.addKeyListener(new KeyAdapter() {
                public void keyTyped(final KeyEvent keyEvent) {
                    JumpToDialog.this.txtKeyTyped(keyEvent, true, false, true);
                }
            });
            (this.coord2c = new JTextField("", 5)).addFocusListener(this);
            this.coord2c.setEditable(true);
            this.coord2c.setVisible(false);
            this.coord2c.addKeyListener(new KeyAdapter() {
                public void keyTyped(final KeyEvent keyEvent) {
                    JumpToDialog.this.txtKeyTyped(keyEvent, true, false, true);
                }
            });
            n += 2;
            final boolean gridx4 = false;
            final GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
            gridBagConstraints8.anchor = 17;
            gridBagConstraints8.gridwidth = 0;
            gridBagConstraints8.insets = new Insets(5, 0, 5, 5);
            gridBagConstraints8.gridy = n;
            gridBagConstraints8.gridx = (gridx4 ? 1 : 0);
            this.locationPanel.add(this.lblGeolocationHeading, gridBagConstraints8);
            ++n;
            int n2 = 0;
            final GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
            gridBagConstraints9.anchor = 13;
            gridBagConstraints9.insets = new Insets(5, 0, 5, 5);
            gridBagConstraints9.gridy = n;
            gridBagConstraints9.gridx = n2;
            this.locationPanel.add(this.lblProjType, gridBagConstraints9);
            ++n2;
            final GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
            gridBagConstraints10.anchor = 17;
            gridBagConstraints10.gridwidth = 0;
            gridBagConstraints10.insets = new Insets(5, 0, 5, 5);
            gridBagConstraints10.gridy = n;
            gridBagConstraints10.gridx = n2;
            this.locationPanel.add(this.projType, gridBagConstraints10);
            ++n;
            int n3 = 0;
            final GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
            gridBagConstraints11.anchor = 17;
            gridBagConstraints11.gridwidth = 0;
            gridBagConstraints11.insets = new Insets(5, 0, 5, 5);
            gridBagConstraints11.gridy = n;
            gridBagConstraints11.gridx = n3;
            this.locationPanel.add(this.radioButtonLabel, gridBagConstraints11);
            ++n3;
            final GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
            gridBagConstraints12.anchor = 17;
            gridBagConstraints12.gridwidth = 0;
            gridBagConstraints12.insets = new Insets(5, 0, 5, 5);
            gridBagConstraints12.gridy = n;
            gridBagConstraints12.gridx = n3;
            this.locationPanel.add(this.radioButtonPanel, gridBagConstraints12);
            ++n;
            int gridx5 = 1;
            final GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
            gridBagConstraints13.anchor = 17;
            gridBagConstraints13.insets = new Insets(0, 0, 5, 5);
            gridBagConstraints13.gridy = n;
            gridBagConstraints13.gridx = gridx5;
            this.locationPanel.add(this.degreesLbl, gridBagConstraints13);
            ++gridx5;
            final GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
            gridBagConstraints14.anchor = 17;
            gridBagConstraints14.insets = new Insets(0, 0, 5, 5);
            gridBagConstraints14.gridy = n;
            gridBagConstraints14.gridx = gridx5;
            this.locationPanel.add(this.minutesLbl, gridBagConstraints14);
            ++gridx5;
            final GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
            gridBagConstraints15.anchor = 17;
            gridBagConstraints15.insets = new Insets(0, 0, 5, 5);
            gridBagConstraints15.gridy = n;
            gridBagConstraints15.gridx = gridx5;
            this.locationPanel.add(this.secondsLbl, gridBagConstraints15);
            ++n;
            int n4 = 0;
            final GridBagConstraints gridBagConstraints16 = new GridBagConstraints();
            gridBagConstraints16.anchor = 13;
            gridBagConstraints16.insets = new Insets(5, 0, 5, 5);
            gridBagConstraints16.gridy = n;
            gridBagConstraints16.gridx = n4;
            this.locationPanel.add(this.lblCoord1, gridBagConstraints16);
            ++n4;
            final GridBagConstraints gridBagConstraints17 = new GridBagConstraints();
            gridBagConstraints17.anchor = 17;
            gridBagConstraints17.insets = new Insets(5, 0, 5, 5);
            gridBagConstraints17.gridy = n;
            gridBagConstraints17.gridx = n4;
            this.locationPanel.add(this.coord1, gridBagConstraints17);
            ++n4;
            final GridBagConstraints gridBagConstraints18 = new GridBagConstraints();
            gridBagConstraints18.anchor = 17;
            gridBagConstraints18.insets = new Insets(5, 0, 5, 5);
            gridBagConstraints18.gridy = n;
            gridBagConstraints18.gridx = n4;
            this.locationPanel.add(this.coord1b, gridBagConstraints18);
            ++n4;
            final GridBagConstraints gridBagConstraints19 = new GridBagConstraints();
            gridBagConstraints19.anchor = 17;
            gridBagConstraints19.insets = new Insets(5, 0, 5, 5);
            gridBagConstraints19.gridy = n;
            gridBagConstraints19.gridx = n4;
            this.locationPanel.add(this.coord1c, gridBagConstraints19);
            ++n;
            int n5 = 0;
            final GridBagConstraints gridBagConstraints20 = new GridBagConstraints();
            gridBagConstraints20.anchor = 13;
            gridBagConstraints20.insets = new Insets(5, 0, 5, 5);
            gridBagConstraints20.gridy = n;
            gridBagConstraints20.gridx = n5;
            this.locationPanel.add(this.lblCoord2, gridBagConstraints20);
            ++n5;
            final GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
            gridBagConstraints21.anchor = 17;
            gridBagConstraints21.insets = new Insets(5, 0, 5, 5);
            gridBagConstraints21.gridy = n;
            gridBagConstraints21.gridx = n5;
            this.locationPanel.add(this.coord2, gridBagConstraints21);
            ++n5;
            final GridBagConstraints gridBagConstraints22 = new GridBagConstraints();
            gridBagConstraints22.anchor = 17;
            gridBagConstraints22.insets = new Insets(5, 0, 5, 5);
            gridBagConstraints22.gridy = n;
            gridBagConstraints22.gridx = n5;
            this.locationPanel.add(this.coord2b, gridBagConstraints22);
            ++n5;
            final GridBagConstraints gridBagConstraints23 = new GridBagConstraints();
            gridBagConstraints23.anchor = 17;
            gridBagConstraints23.insets = new Insets(5, 0, 5, 5);
            gridBagConstraints23.gridy = n;
            gridBagConstraints23.gridx = n5;
            this.locationPanel.add(this.coord2c, gridBagConstraints23);
        }
        this.lblZoomLevelHeading = new JLabel("Zoom Level:");
        this.lblZoomLevel = new JLabel("1:");
        final int maxDiscardedZoomLevels = propertyManager.getMaxDiscardedZoomLevels();
        final String[] array = new String[maxDiscardedZoomLevels + 3];
        array[0] = "4x";
        array[1] = "2x";
        for (int i = 0; i < maxDiscardedZoomLevels + 1; ++i) {
            array[i + 2] = "1:" + Integer.toString((int)Math.pow(2.0, i));
        }
        (this.zoomCombo = new JComboBox(array)).setSelectedIndex(propertyManager.getDiscardedZoomLevels() + 2);
        n += 2;
        final boolean gridx6 = false;
        final GridBagConstraints gridBagConstraints24 = new GridBagConstraints();
        gridBagConstraints24.anchor = 17;
        gridBagConstraints24.gridwidth = 0;
        gridBagConstraints24.insets = new Insets(5, 0, 5, 5);
        gridBagConstraints24.gridy = n;
        gridBagConstraints24.gridx = (gridx6 ? 1 : 0);
        this.locationPanel.add(this.lblZoomLevelHeading, gridBagConstraints24);
        ++n;
        final boolean gridx7 = true;
        final GridBagConstraints gridBagConstraints25 = new GridBagConstraints();
        gridBagConstraints25.gridwidth = 0;
        gridBagConstraints25.anchor = 17;
        gridBagConstraints25.insets = new Insets(5, 0, 5, 5);
        gridBagConstraints25.gridy = n;
        gridBagConstraints25.gridx = (gridx7 ? 1 : 0);
        this.locationPanel.add(this.zoomCombo, gridBagConstraints25);
        (this.checkCrosshair = new JCheckBox("Display Crosshair")).setSelected(showCrosshair);
        ++n;
        final boolean gridx8 = true;
        final GridBagConstraints gridBagConstraints26 = new GridBagConstraints();
        gridBagConstraints26.gridwidth = 0;
        gridBagConstraints26.anchor = 17;
        gridBagConstraints26.insets = new Insets(5, 0, 5, 5);
        gridBagConstraints26.gridy = n;
        gridBagConstraints26.gridx = (gridx8 ? 1 : 0);
        this.locationPanel.add(this.checkCrosshair, gridBagConstraints26);
        this.getContentPane().add(this.locationPanel, "Center");
        (this.btnPanel = new JPanel()).setLayout(new FlowLayout(2));
        this.btnPanel.setBorder(new EmptyBorder(new Insets(3, 3, 3, 3)));
        (this.okBtn = new JButton()).setMnemonic('O');
        this.okBtn.setText("OK");
        this.okBtn.setToolTipText("Close dialog and save changes");
        this.okBtn.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                JumpToDialog.this.okBtnActionPerformed(actionEvent);
            }
        });
        this.btnPanel.add(this.okBtn);
        (this.cancelBtn = new JButton()).setMnemonic('C');
        this.cancelBtn.setText("Cancel");
        this.cancelBtn.setToolTipText("Close dialog and cancel changes");
        this.cancelBtn.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                JumpToDialog.this.cancelBtnActionPerformed(actionEvent);
            }
        });
        this.btnPanel.add(this.cancelBtn);
        this.getContentPane().add(this.btnPanel, "South");
        this.pack();
    }
    
    private void txtKeyTyped(final KeyEvent keyEvent, final boolean b, final boolean b2, final boolean b3) {
        final int keyChar2;
        int keyChar = keyChar2 = keyEvent.getKeyChar();
        if (keyChar2 >= 97 && keyChar2 <= 122) {
            keyChar = (char)(keyChar2 - 32);
            keyEvent.setKeyChar((char)keyChar);
        }
        if (!Helper.validateChar((char)keyChar, b, b2, b3)) {
            keyEvent.consume();
        }
    }
    
    private void cancelBtnActionPerformed(final ActionEvent actionEvent) {
        this.dispose();
    }
    
    private void okBtnActionPerformed(final ActionEvent actionEvent) {
        System.out.println("Jumping...");
        int n = 0;
        int n2 = 0;
        final DimensionManager dimensionManager = this.viewCentral.getDimensionManager();
        this.viewCentral.getNITFGeoUtils();
        Label_1000: {
            if (this.lastFocusComponent != this.projType && this.lastFocusComponent != this.coord1 && this.lastFocusComponent != this.coord2 && this.lastFocusComponent != this.coord1b && this.lastFocusComponent != this.coord2b && this.lastFocusComponent != this.coord1c && this.lastFocusComponent != this.coord2c) {
                final String trim = this.pixelX.getText().trim();
                final String trim2 = this.pixelY.getText().trim();
                if (trim.length() > 0 && trim2.length() > 0) {
                    try {
                        n = Integer.parseInt(trim);
                        n2 = Integer.parseInt(trim2);
                        break Label_1000;
                    }
                    catch (NumberFormatException ex) {
                        JumpToDialog.log.warn("Error parsing pixel values.", ex);
                        this.viewCentral.reportError(this, "Format Error", "The pixel location entered contains an invalid integer. Please try again.");
                        return;
                    }
                }
                this.viewCentral.reportError(this, "Format Error", "The pixel location entered contains a blank entry. Please try again.");
                return;
            }
            final String trim3 = this.coord1.getText().trim();
            final String trim4 = this.coord2.getText().trim();
            final String trim5 = this.coord1b.getText().trim();
            final String trim6 = this.coord2b.getText().trim();
            final String trim7 = this.coord1c.getText().trim();
            final String trim8 = this.coord2c.getText().trim();
            final Projection projection = (Projection)this.projType.getSelectedItem();
            final GeoJumpUtil geoJumpUtil = new GeoJumpUtil(this.viewCentral);
            if ((trim3.length() <= 0 || trim4.length() <= 0) && (trim3.length() <= 0 || projection != ViewerConst.MGRS_PROJECTION)) {
                this.viewCentral.reportError(this, "Format Error", "The geolocation entered contains a blank entry. Please try again.");
                return;
            }
            if (projection != null) {
                if (projection == ViewerConst.GEOGRAPHIC_PROJECTION) {
                    double double5 = 0.0;
                    double double6 = 0.0;
                    Label_0635: {
                        if (this.selectedGeoFormat == 1) {
                            try {
                                System.out.println("Jumping with DDM format");
                                final double double1 = Double.parseDouble(trim3);
                                final double double2 = Double.parseDouble(trim4);
                                final double double3 = Double.parseDouble(trim5);
                                final double double4 = Double.parseDouble(trim6);
                                if (double1 < 0.0) {
                                    double5 = double1 - double3 / 60.0;
                                }
                                else {
                                    double5 = double1 + double3 / 60.0;
                                }
                                if (double2 < 0.0) {
                                    double6 = double2 - double4 / 60.0;
                                }
                                else {
                                    double6 = double2 + double4 / 60.0;
                                }
                                break Label_0635;
                            }
                            catch (NumberFormatException ex2) {
                                JumpToDialog.log.warn("Error parsing lat/lon values.", ex2);
                                this.viewCentral.reportError(this, "Format Error", "The geolocation value contains an invalid number. Please try again.");
                                return;
                            }
                        }
                        if (this.selectedGeoFormat == 2) {
                            try {
                                System.out.println("Jumping with DMS format");
                                final double double7 = Double.parseDouble(trim3);
                                final double double8 = Double.parseDouble(trim4);
                                final double double9 = Double.parseDouble(trim5);
                                final double double10 = Double.parseDouble(trim6);
                                final double double11 = Double.parseDouble(trim7);
                                final double double12 = Double.parseDouble(trim8);
                                if (double7 < 0.0) {
                                    double5 = double7 - double9 / 60.0 - double11 / 3600.0;
                                }
                                else {
                                    double5 = double7 + double9 / 60.0 + double11 / 3600.0;
                                }
                                if (double8 < 0.0) {
                                    double6 = double8 - double10 / 60.0 - double12 / 3600.0;
                                }
                                else {
                                    double6 = double8 + double10 / 60.0 + double12 / 3600.0;
                                }
                                break Label_0635;
                            }
                            catch (NumberFormatException ex3) {
                                JumpToDialog.log.warn("Error parsing lat/lon values.", ex3);
                                this.viewCentral.reportError(this, "Format Error", "The geolocation value contains an invalid number. Please try again.");
                                return;
                            }
                        }
                        System.out.println("Jumping with DD format");
                        try {
                            double5 = Double.parseDouble(trim3);
                            double6 = Double.parseDouble(trim4);
                        }
                        catch (NumberFormatException ex4) {
                            JumpToDialog.log.warn("Error parsing lat/lon values.", ex4);
                            this.viewCentral.reportError(this, "Format Error", "The geolocation value contains an invalid number. Please try again.");
                            return;
                        }
                    }
                    System.out.println(double5 + ", " + double6);
                    final Point latLonToPixel = geoJumpUtil.latLonToPixel(double5, double6);
                    if (latLonToPixel.x == -1 || latLonToPixel.y == -1) {
                        return;
                    }
                    n = latLonToPixel.x;
                    n2 = latLonToPixel.y;
                }
                else if (projection == ViewerConst.UTM_PROJECTION) {
                    double double13;
                    double double14;
                    try {
                        double13 = Double.parseDouble(trim3);
                        double14 = Double.parseDouble(trim4);
                    }
                    catch (NumberFormatException ex5) {
                        JumpToDialog.log.warn("Error parsing easting/northing values.", ex5);
                        this.viewCentral.reportError(this, "Format Error", "The geolocation value contains an invalid number. Please try again.");
                        return;
                    }
                    final Point eastNorthToPixel = geoJumpUtil.eastNorthToPixel(double13, double14);
                    if (eastNorthToPixel.x == -1 || eastNorthToPixel.y == -1) {
                        return;
                    }
                    n = eastNorthToPixel.x;
                    n2 = eastNorthToPixel.y;
                }
                else if (projection == ViewerConst.MGRS_PROJECTION) {
                    String s;
                    try {
                        s = trim3;
                    }
                    catch (Exception ex6) {
                        JumpToDialog.log.warn("Error collecting MGRS string.", ex6);
                        this.viewCentral.reportError(this, "Format Error", "The MGRS string could not be collected. Please try again.");
                        return;
                    }
                    final Point mgrsToPixel = geoJumpUtil.mgrsToPixel(s);
                    if (mgrsToPixel.x == -1 || mgrsToPixel.y == -1) {
                        return;
                    }
                    n = mgrsToPixel.x;
                    n2 = mgrsToPixel.y;
                }
            }
        }
        if (n >= 0 && n < this.totalDimensions.width && n2 >= 0 && n2 < this.totalDimensions.height) {
            dimensionManager.setDisplayWindowCenter(dimensionManager.imageToDisplay(new Point(n, n2)));
            final int discardedZoomLevels = this.zoomCombo.getSelectedIndex() - 2;
            try {
                this.viewCentral.getPropertyManager().setShowCrosshair(this.checkCrosshair.isSelected());
                this.viewCentral.getPropertyManager().setDiscardedZoomLevels(discardedZoomLevels);
                this.viewCentral.getPropertyManager().setUserLocation(new Point(n, n2));
            }
            catch (PropertyVetoException ex7) {
                JumpToDialog.log.error("Unable to clear the user Jump To location.", ex7);
            }
            this.dispose();
        }
        else {
            this.viewCentral.reportError(this, "Out of Range", "The location entered is not within the bounds of the image.");
        }
    }
    
    private double[] getGeoCoords(final int n, final int n2) {
        double[] geodeticLocation = null;
        final NITFGeoUtils nitfGeoUtils = this.viewCentral.getNITFGeoUtils();
        if (nitfGeoUtils != null && nitfGeoUtils.isReady()) {
            geodeticLocation = nitfGeoUtils.getGeodeticLocation(n, n2);
        }
        return geodeticLocation;
    }
    
    private double[] getUTMCoords(final int n, final int n2) {
        double[] array = null;
        final NITFGeoUtils nitfGeoUtils = this.viewCentral.getNITFGeoUtils();
        if (nitfGeoUtils != null && nitfGeoUtils.isReady()) {
            final Utm_Coord_3d utmLocation = nitfGeoUtils.getUTMLocation(n, n2);
            array = new double[] { utmLocation.x, utmLocation.y };
        }
        return array;
    }
    
    private String getMGRSCoords(final int n, final int n2) {
        String mgrsLocation = "";
        final NITFGeoUtils nitfGeoUtils = this.viewCentral.getNITFGeoUtils();
        if (nitfGeoUtils != null && nitfGeoUtils.isReady()) {
            mgrsLocation = nitfGeoUtils.getMGRSLocation(n, n2);
        }
        return mgrsLocation;
    }
    
    private double[] getRPCCoords(final int n, final int n2) {
        double[] rpcLocation = null;
        final NITFGeoUtils nitfGeoUtils = this.viewCentral.getNITFGeoUtils();
        if (nitfGeoUtils != null && nitfGeoUtils.isReady()) {
            rpcLocation = nitfGeoUtils.rpcLocation(n, n2, nitfGeoUtils);
        }
        return rpcLocation;
    }
    
    private String[] formatGeoCoordDDMString(final double n, final double n2) {
        final DecimalFormat decimalFormat = new DecimalFormat("00.000");
        return new String[] { Integer.toString((int)n), decimalFormat.format(Math.abs(n % 1.0 * 60.0)), Integer.toString((int)n2), decimalFormat.format(Math.abs(n2 % 1.0 * 60.0)) };
    }
    
    private String[] formatGeoCoordDMSString(final double n, final double n2) {
        final DecimalFormat decimalFormat = new DecimalFormat("##0.00");
        final double abs = Math.abs(n % 1.0);
        final double abs2 = Math.abs(n2 % 1.0);
        return new String[] { Integer.toString((int)n), Integer.toString((int)(abs * 60.0)), decimalFormat.format(Math.round(abs * 60.0 % 1.0 * 60.0 * 100.0) / 100.0), Integer.toString((int)n2), Integer.toString((int)(abs2 * 60.0)), decimalFormat.format(Math.round(abs2 * 60.0 % 1.0 * 60.0 * 100.0) / 100.0) };
    }
    
    private void adjustCoordDisplay() {
        final NITFGeoUtils nitfGeoUtils = this.viewCentral.getNITFGeoUtils();
        final Projection selectedItem = (Projection)this.projType.getSelectedItem();
        if (selectedItem != null) {
            this.projType.setSelectedItem(selectedItem);
            final Point currentLocation = this.currentLocation;
            if (selectedItem == ViewerConst.GEOGRAPHIC_PROJECTION) {
                this.ddmRadioButton.setEnabled(true);
                this.ddRadioButton.setEnabled(true);
                this.dmsRadioButton.setEnabled(true);
                this.getGeoCoords(currentLocation.x, currentLocation.y);
                this.lblCoord1.setText("Lat:");
                this.lblCoord2.setText("Lon:");
                this.lblCoord2.setVisible(true);
                if (this.ddmRadioButton.isSelected()) {
                    this.formatGeoLocationFields(1);
                }
                if (this.ddRadioButton.isSelected()) {
                    this.formatGeoLocationFields(0);
                }
                if (this.dmsRadioButton.isSelected()) {
                    this.formatGeoLocationFields(2);
                }
            }
            else if (selectedItem == ViewerConst.UTM_PROJECTION) {
                this.ddmRadioButton.setEnabled(false);
                this.ddRadioButton.setEnabled(false);
                this.dmsRadioButton.setEnabled(false);
                this.degreesLbl.setVisible(false);
                this.minutesLbl.setVisible(false);
                this.secondsLbl.setVisible(false);
                this.coord1b.setVisible(false);
                this.coord2b.setVisible(false);
                this.coord1c.setVisible(false);
                this.coord2c.setVisible(false);
                final double[] utmCoords = this.getUTMCoords(currentLocation.x, currentLocation.y);
                this.lblCoord1.setText("Easting:");
                this.coord1.setColumns(12);
                this.coord1.setText(this.df.format(utmCoords[0]));
                if (nitfGeoUtils != null && nitfGeoUtils.isReady()) {
                    if (nitfGeoUtils.getNSHemisphere() == 'N') {
                        this.lblCoord2.setText("Northing:");
                    }
                    else {
                        this.lblCoord2.setText("Southing:");
                    }
                }
                this.lblCoord2.setVisible(true);
                this.coord2.setColumns(12);
                this.coord2.setText(this.df.format(utmCoords[1]));
                this.coord2.setVisible(true);
            }
            else if (selectedItem == ViewerConst.MGRS_PROJECTION) {
                this.ddmRadioButton.setEnabled(false);
                this.ddRadioButton.setEnabled(false);
                this.dmsRadioButton.setEnabled(false);
                this.degreesLbl.setVisible(false);
                this.minutesLbl.setVisible(false);
                this.secondsLbl.setVisible(false);
                this.coord1b.setVisible(false);
                this.coord2b.setVisible(false);
                this.coord1c.setVisible(false);
                this.coord2c.setVisible(false);
                final String mgrsCoords = this.getMGRSCoords(currentLocation.x, currentLocation.y);
                this.lblCoord1.setText("MGRS:");
                this.coord1.setColumns(12);
                this.coord1.setText(mgrsCoords);
                this.coord1.removeKeyListener(this.coord1.getKeyListeners()[0]);
                this.coord1.addKeyListener(new KeyAdapter() {
                    public void keyTyped(final KeyEvent keyEvent) {
                        JumpToDialog.this.txtKeyTyped(keyEvent, false, false, false);
                    }
                });
                this.lblCoord2.setVisible(false);
                this.coord2.setVisible(false);
            }
        }
        this.pack();
    }
    
    public void focusGained(final FocusEvent focusEvent) {
        this.lastFocusComponent = focusEvent.getComponent();
    }
    
    public void focusLost(final FocusEvent focusEvent) {
    }
    
    public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
        if (propertyChangeEvent.getPropertyName().equals("DisplayedProjection")) {
            this.handleProjChanged(propertyChangeEvent);
        }
    }
    
    private void handleProjChanged(final PropertyChangeEvent propertyChangeEvent) {
        final Projection proj = this.viewCentral.getPropertyManager().getProj();
        this.projType.setSelectedItem(proj);
        if (proj.toString().equals(ViewerConst.UTM_PROJECTION.toString())) {
            this.projType.setSelectedItem(ViewerConst.UTM_PROJECTION);
        }
        else if (proj.toString().equals(ViewerConst.GEOGRAPHIC_PROJECTION.toString())) {
            this.projType.setSelectedItem(ViewerConst.GEOGRAPHIC_PROJECTION);
        }
        else if (proj.toString().equals(ViewerConst.MGRS_PROJECTION.toString())) {
            this.projType.setSelectedItem(ViewerConst.MGRS_PROJECTION);
        }
    }
    
    private void formatGeoLocationFields(final int n) {
        if (n == 0) {
            this.coord1.getSize();
            final double[] geoCoords = this.getGeoCoords(this.currentLocation.x, this.currentLocation.y);
            this.coord1.setColumns(10);
            this.coord2.setColumns(10);
            this.coord1.setText(this.df.format(geoCoords[1]));
            this.coord2.setText(this.df.format(geoCoords[0]));
            this.coord1.setVisible(true);
            this.coord2.setVisible(true);
            this.degreesLbl.setVisible(true);
            this.minutesLbl.setVisible(false);
            this.secondsLbl.setVisible(false);
            this.coord1b.setVisible(false);
            this.coord2b.setVisible(false);
            this.coord1c.setVisible(false);
            this.coord2c.setVisible(false);
            this.pack();
        }
        else if (n == 1) {
            final double[] geoCoords2 = this.getGeoCoords(this.currentLocation.x, this.currentLocation.y);
            final String[] formatGeoCoordDDMString = this.formatGeoCoordDDMString(geoCoords2[1], geoCoords2[0]);
            this.coord1.setColumns(5);
            this.coord1.setText(formatGeoCoordDDMString[0]);
            this.coord1b.setText(formatGeoCoordDDMString[1]);
            this.coord2.setColumns(5);
            this.coord2.setText(formatGeoCoordDDMString[2]);
            this.coord2b.setText(formatGeoCoordDDMString[3]);
            this.degreesLbl.setVisible(true);
            this.minutesLbl.setVisible(true);
            this.secondsLbl.setVisible(false);
            this.coord1.setVisible(true);
            this.coord2.setVisible(true);
            this.coord1b.setVisible(true);
            this.coord2b.setVisible(true);
            this.coord1c.setVisible(false);
            this.coord2c.setVisible(false);
            this.pack();
        }
        else if (n == 2) {
            final double[] geoCoords3 = this.getGeoCoords(this.currentLocation.x, this.currentLocation.y);
            final String[] formatGeoCoordDMSString = this.formatGeoCoordDMSString(geoCoords3[1], geoCoords3[0]);
            this.coord1.setColumns(5);
            this.coord1.setText(formatGeoCoordDMSString[0]);
            this.coord1b.setText(formatGeoCoordDMSString[1]);
            this.coord1c.setText(formatGeoCoordDMSString[2]);
            this.coord2.setColumns(5);
            this.coord2.setText(formatGeoCoordDMSString[3]);
            this.coord2b.setText(formatGeoCoordDMSString[4]);
            this.coord2c.setText(formatGeoCoordDMSString[5]);
            this.degreesLbl.setVisible(true);
            this.minutesLbl.setVisible(true);
            this.secondsLbl.setVisible(true);
            this.coord1.setVisible(true);
            this.coord2.setVisible(true);
            this.coord1b.setVisible(true);
            this.coord2b.setVisible(true);
            this.coord1c.setVisible(true);
            this.coord2c.setVisible(true);
            this.pack();
        }
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
        JumpToDialog.log = new Log((JumpToDialog.class$com$itt$J2KViewer$gui$JumpToDialog == null) ? (JumpToDialog.class$com$itt$J2KViewer$gui$JumpToDialog = class$("com.itt.J2KViewer.gui.JumpToDialog")) : JumpToDialog.class$com$itt$J2KViewer$gui$JumpToDialog);
    }
}
