// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.gui;

import java.awt.Toolkit;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import java.beans.PropertyVetoException;
import java.awt.FlowLayout;
import javax.swing.Icon;
import com.itt.J2KViewer.util.Helper;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import com.itt.J2KViewer.georvm.Projection;
import com.itt.J2KViewer.util.ViewerConst;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.colorchooser.DefaultColorSelectionModel;
import javax.swing.BorderFactory;
import java.awt.Insets;
import java.util.Dictionary;
import java.util.Hashtable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.GridBagLayout;
import java.awt.Component;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.Frame;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JColorChooser;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Font;
import com.itt.J2KViewer.imagetools.DRAManager;
import com.itt.J2KViewer.controller.ViewCentral;
import com.itt.J2KViewer.util.Log;
import javax.swing.JDialog;

public class UserPreferencesDialog extends JDialog
{
    private static final long serialVersionUID = 1L;
    private static Log log;
    private ViewCentral viewCentral;
    private DRAManager draManager;
    private static Font PANEL_TITLE_FONT;
    private JFrame parent;
    private JPanel mainPanel;
    private JTabbedPane tabs;
    private JPanel slidePanel;
    private JSlider slideQualityLayers;
    private JSlider slideQualityLayersOverview;
    private JPanel slideTxtPanel;
    private JLabel slideTxt;
    private JLabel mainSliderLbl;
    private JLabel overviewSliderLbl;
    private JPanel sliderHoldingPanel;
    private JDialog colorFrame;
    private JPanel colorPanel;
    private JPanel swatchPanel;
    private JPanel overviewSwatchPanel;
    private JPanel gotoSwatchPanel;
    private JPanel colorPickButtonPanel;
    private JButton overviewColorPickButton;
    private JButton gotoColorPickButton;
    private JColorChooser jColorChooser;
    private JPanel colorHoldingPanel;
    private JLabel stretchTxt;
    private JTextField stretchTextField;
    private JPanel stretchHoldingPanel;
    private JCheckBox stretchCheckBox;
    private JCheckBox useDraIgnoreValueCheckBox;
    private JTextField draIgnoreValueField;
    private JLabel draIgnoreLabel;
    private JLabel defaultDRALabel;
    private JComboBox defaultDRACombo;
    private JLabel projTxt;
    private JPanel projTxtPanel;
    private JPanel projDropdownPanel;
    private JPanel projPanel;
    private JComboBox projectionComboBox;
    private JCheckBox defaultProjCheckBox;
    private JCheckBox overviewCheckBox;
    private JPanel overviewPanel;
    private JPanel stretchAndProjectionPanel;
    private JPanel northArrowImagePanel;
    private JPanel btnPanel;
    private JButton buttonClose;
    private JButton okBtn;
    private JButton cancelBtn;
    private int chosenPercent;
    private int currentQualityLayers;
    private int currentQualityLayersOverview;
    private Color newColor;
    private boolean qualityChanged;
    private boolean stretchChanged;
    private boolean gotoColorChanged;
    private boolean overviewColorChanged;
    private String defaultDraPanelVal;
    static /* synthetic */ Class class$com$itt$J2KViewer$gui$UserPreferencesDialog;
    
    public UserPreferencesDialog(final Frame frame, final boolean b, final ViewCentral viewCentral) {
        super(frame, b);
        this.viewCentral = null;
        this.qualityChanged = true;
        this.stretchChanged = false;
        this.viewCentral = viewCentral;
        this.parent = (JFrame)frame;
        this.draManager = viewCentral.getDRAManager();
        this.initComponents();
        this.defaultDraPanelVal = this.getDRAPanelVal();
        this.getRootPane().setDefaultButton(this.buttonClose);
    }
    
    public void initComponents() {
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.mainPanel = new JPanel(new BorderLayout());
        (this.tabs = new JTabbedPane()).setBorder(new EmptyBorder(10, 7, 5, 7));
        this.mainPanel.isOpaque();
        this.mainPanel.setBackground(Color.LIGHT_GRAY);
        this.setTitle("User Preferences");
        this.makeSlider(gridBagConstraints);
        this.tabs.add(this.sliderHoldingPanel, "Quality Layers");
        this.makeColorPicker(gridBagConstraints);
        this.tabs.add(this.colorHoldingPanel, "Color");
        this.makeStretch(gridBagConstraints);
        this.tabs.add(this.stretchHoldingPanel, "DRA");
        this.makeProjection(gridBagConstraints);
        this.tabs.add(this.projPanel, "Projection");
        this.makeNorthArrowPanel(gridBagConstraints);
        this.tabs.add(this.northArrowImagePanel, "North Arrow");
        this.makeOverview(gridBagConstraints);
        this.tabs.add(this.overviewPanel, "Overview Image");
        this.makeButtons(gridBagConstraints);
        this.mainPanel.add(this.tabs, "Center");
        this.mainPanel.add(this.btnPanel, "South");
        this.getContentPane().add(this.mainPanel);
        this.pack();
    }
    
    private void makeSlider(final GridBagConstraints gridBagConstraints) {
        this.currentQualityLayers = this.viewCentral.getPropertyManager().getQualityLayersPercentToDownload();
        if (this.currentQualityLayers >= 0) {
            this.currentQualityLayers /= 10;
        }
        else {
            this.currentQualityLayers = 10;
        }
        this.currentQualityLayersOverview = this.viewCentral.getPropertyManager().getOverviewQualityLayersPercentToDownload();
        if (this.currentQualityLayersOverview >= 0) {
            this.currentQualityLayersOverview /= 10;
        }
        else {
            this.currentQualityLayersOverview = 10;
        }
        this.sliderHoldingPanel = new JPanel(new GridBagLayout());
        (this.slidePanel = new JPanel()).setLayout(new GridBagLayout());
        this.slideQualityLayers = new JSlider(0, 1, 10, this.currentQualityLayers);
        this.mainSliderLbl = new JLabel("Main Image");
        this.slideQualityLayersOverview = new JSlider(0, 1, 10, this.currentQualityLayersOverview);
        this.overviewSliderLbl = new JLabel("Overview Image");
        this.slideQualityLayers.setToolTipText("<html>Select the maximum quality layer for the main image<br>Selecting a smaller value results in less use of bandwidth.</html>");
        this.slideQualityLayers.addChangeListener(new ChangeListener() {
            public void stateChanged(final ChangeEvent changeEvent) {
                UserPreferencesDialog.this.qualityChanged = true;
            }
        });
        this.slideQualityLayersOverview.addChangeListener(new ChangeListener() {
            public void stateChanged(final ChangeEvent changeEvent) {
                UserPreferencesDialog.this.qualityChanged = true;
            }
        });
        this.slideQualityLayersOverview.setToolTipText("<html>Select the maximum quality layer for the overview image <br> Selecting a smaller value results in less use of bandwidth.</html>");
        final Hashtable<Integer, JLabel> hashtable = new Hashtable<Integer, JLabel>();
        hashtable.put(new Integer(0), new JLabel("0%"));
        hashtable.put(new Integer(2), new JLabel("20%"));
        hashtable.put(new Integer(4), new JLabel("40%"));
        hashtable.put(new Integer(6), new JLabel("60%"));
        hashtable.put(new Integer(8), new JLabel("80%"));
        hashtable.put(new Integer(10), new JLabel("100%"));
        this.slideQualityLayers.setLabelTable(hashtable);
        this.slideQualityLayersOverview.setLabelTable(hashtable);
        this.slideQualityLayers.setMajorTickSpacing(2);
        this.slideQualityLayers.setMinorTickSpacing(1);
        this.slideQualityLayers.setPaintTicks(true);
        this.slideQualityLayers.setPaintLabels(true);
        this.slideQualityLayers.setSnapToTicks(true);
        this.slideQualityLayers.setPaintTrack(true);
        this.slideQualityLayersOverview.setMajorTickSpacing(2);
        this.slideQualityLayersOverview.setMinorTickSpacing(1);
        this.slideQualityLayersOverview.setPaintTicks(true);
        this.slideQualityLayersOverview.setPaintLabels(true);
        this.slideQualityLayersOverview.setSnapToTicks(true);
        this.slideQualityLayersOverview.setPaintTrack(true);
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(5, 7, 5, 5);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        this.slidePanel.add(this.mainSliderLbl, gridBagConstraints);
        gridBagConstraints.insets = new Insets(5, 0, 5, 0);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        this.slidePanel.add(this.slideQualityLayers, gridBagConstraints);
        gridBagConstraints.insets = new Insets(5, 7, 5, 5);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        this.slidePanel.add(this.overviewSliderLbl, gridBagConstraints);
        gridBagConstraints.insets = new Insets(5, 0, 5, 0);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        this.slidePanel.add(this.slideQualityLayersOverview, gridBagConstraints);
        this.slideTxtPanel = new JPanel();
        (this.slideTxt = new JLabel("<html>Max Percentage <br>Quality Layers <br>to Download...</html>", 4)).setFont(this.slideTxt.getFont().deriveFont(12.0f));
        this.slideTxt.setVerticalTextPosition(0);
        this.slideTxt.setHorizontalTextPosition(0);
        this.slideTxtPanel.add(this.slideTxt);
        this.sliderHoldingPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.lightGray), " Quality Layers "));
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        this.sliderHoldingPanel.add(this.slidePanel, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        this.sliderHoldingPanel.add(this.slideTxtPanel, gridBagConstraints);
    }
    
    private void makeColorPicker(final GridBagConstraints gridBagConstraints) {
        (this.jColorChooser = new JColorChooser(new DefaultColorSelectionModel())).setChooserPanels(new AbstractColorChooserPanel[] { this.jColorChooser.getChooserPanels()[0] });
        (this.colorFrame = JColorChooser.createDialog(this, "Choose Color", true, this.jColorChooser, new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                UserPreferencesDialog.this.newColor = UserPreferencesDialog.this.jColorChooser.getColor();
                if (UserPreferencesDialog.this.overviewColorChanged) {
                    UserPreferencesDialog.this.overviewSwatchPanel.setBackground(UserPreferencesDialog.this.newColor);
                }
                if (UserPreferencesDialog.this.gotoColorChanged) {
                    UserPreferencesDialog.this.gotoSwatchPanel.setBackground(UserPreferencesDialog.this.newColor);
                }
                UserPreferencesDialog.this.colorFrame.setVisible(false);
            }
        }, new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                UserPreferencesDialog.this.colorFrame.setVisible(false);
            }
        })).setVisible(false);
        (this.colorHoldingPanel = new JPanel(new GridBagLayout())).setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.lightGray), " Color Preferences "));
        this.colorPickButtonPanel = new JPanel(new GridBagLayout());
        gridBagConstraints.gridwidth = 1;
        (this.overviewColorPickButton = new JButton()).setMnemonic('G');
        this.overviewColorPickButton.setText("Select Overview Box Color");
        this.overviewColorPickButton.setToolTipText("Choose a New Overview Window Box Color");
        this.overviewColorPickButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                if ("overview".equals(actionEvent.getActionCommand())) {
                    UserPreferencesDialog.this.overviewColorChanged = true;
                    UserPreferencesDialog.this.gotoColorChanged = false;
                }
                new ColorChooserAction().actionPerformed(actionEvent);
            }
        });
        this.overviewColorPickButton.setActionCommand("overview");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        this.colorPickButtonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        this.colorPickButtonPanel.add(this.overviewColorPickButton, gridBagConstraints);
        (this.gotoColorPickButton = new JButton()).setMnemonic('G');
        this.gotoColorPickButton.setText("Select GoTo Crosshairs Color");
        this.gotoColorPickButton.setToolTipText("Choose a New GoTo Color");
        this.gotoColorPickButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                if ("goto".equals(actionEvent.getActionCommand())) {
                    UserPreferencesDialog.this.gotoColorChanged = true;
                    UserPreferencesDialog.this.overviewColorChanged = false;
                }
                new ColorChooserAction().actionPerformed(actionEvent);
            }
        });
        this.gotoColorPickButton.setActionCommand("goto");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        this.colorPickButtonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        this.colorPickButtonPanel.add(this.gotoColorPickButton, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        this.colorHoldingPanel.add(this.colorPickButtonPanel, gridBagConstraints);
        this.swatchPanel = new JPanel(new GridBagLayout());
        (this.overviewSwatchPanel = new JPanel(new BorderLayout())).setPreferredSize(new Dimension(20, 20));
        this.overviewSwatchPanel.setOpaque(true);
        this.overviewSwatchPanel.setBackground(this.viewCentral.getPropertyManager().getOverviewColor());
        this.overviewSwatchPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        (this.gotoSwatchPanel = new JPanel(new BorderLayout())).setPreferredSize(new Dimension(20, 20));
        this.gotoSwatchPanel.setOpaque(true);
        this.gotoSwatchPanel.setBackground(this.viewCentral.getPropertyManager().getGotoColor());
        this.gotoSwatchPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        gridBagConstraints.insets = new Insets(8, 0, 8, 5);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        this.swatchPanel.add(this.overviewSwatchPanel, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        this.swatchPanel.add(this.gotoSwatchPanel, gridBagConstraints);
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        this.colorHoldingPanel.add(this.swatchPanel, gridBagConstraints);
    }
    
    private void makeStretchAndProjectionPanel(final GridBagConstraints gridBagConstraints) {
        (this.stretchAndProjectionPanel = new JPanel()).setLayout(new GridBagLayout());
        this.makeStretch(gridBagConstraints);
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = 19;
        this.stretchAndProjectionPanel.add(this.stretchHoldingPanel, gridBagConstraints);
        this.makeProjection(gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = 20;
        this.stretchAndProjectionPanel.add(this.projPanel, gridBagConstraints);
    }
    
    private void makeStretch(final GridBagConstraints gridBagConstraints) {
        this.stretchHoldingPanel = new JPanel();
        final JPanel panel = new JPanel(new GridBagLayout());
        final JPanel panel2 = new JPanel(new GridBagLayout());
        new JPanel().setLayout(new GridBagLayout());
        this.stretchHoldingPanel.setLayout(new BoxLayout(this.stretchHoldingPanel, 3));
        this.stretchTxt = new JLabel("Default Value for % Stretch : ");
        final JLabel label = new JLabel("(0-49%)");
        this.stretchTxt.setFont(this.stretchTxt.getFont().deriveFont(11.0f));
        this.stretchTxt.setVerticalTextPosition(0);
        this.stretchTxt.setHorizontalTextPosition(0);
        (this.stretchTextField = new JTextField()).setHorizontalAlignment(0);
        this.stretchTextField.addKeyListener(new KeyAdapter() {
            public void keyTyped(final KeyEvent keyEvent) {
                UserPreferencesDialog.this.txtKeyTyped(keyEvent);
            }
        });
        this.stretchTextField.setText(null);
        this.stretchTextField.setText(new Double(this.viewCentral.getPropertyManager().getDefaultDRAPercent()).toString());
        this.stretchCheckBox = new JCheckBox("Apply DRA on Load", this.viewCentral.getPropertyManager().isUponLoadDoAutoDRA());
        this.draIgnoreLabel = new JLabel("Ignore Value ");
        (this.draIgnoreValueField = new JTextField(Integer.toString(this.viewCentral.getPropertyManager().getIgnoreValueDRA()), 3)).setHorizontalAlignment(0);
        (this.useDraIgnoreValueCheckBox = new JCheckBox("Use Ignore Value")).setSelected(this.viewCentral.getPropertyManager().isUseIgnoreValueDRA());
        this.defaultDRALabel = new JLabel("Default DRA Stretch Type");
        (this.defaultDRACombo = new JComboBox((E[])ViewerConst.stretchTypes)).setSelectedIndex(this.viewCentral.getPropertyManager().getDefaultDRAType());
        this.stretchHoldingPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.lightGray), " DRA "));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = 21;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        panel.add(this.stretchCheckBox, gridBagConstraints);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.insets = new Insets(5, 10, 5, 5);
        panel.add(this.stretchTxt, gridBagConstraints);
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        panel.add(this.stretchTextField, gridBagConstraints);
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = 22;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        panel.add(label, gridBagConstraints);
        this.stretchHoldingPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = 21;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        panel.add(this.useDraIgnoreValueCheckBox, gridBagConstraints);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.insets = new Insets(5, 10, 5, 5);
        panel.add(this.draIgnoreLabel, gridBagConstraints);
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = 22;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        panel.add(this.draIgnoreValueField, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = 21;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        panel.add(this.defaultDRACombo, gridBagConstraints);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = 21;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        panel.add(this.defaultDRALabel, gridBagConstraints);
        this.stretchHoldingPanel.add(panel);
    }
    
    private void makeProjection(final GridBagConstraints gridBagConstraints) {
        (this.projPanel = new JPanel()).setLayout(new GridBagLayout());
        this.projTxtPanel = new JPanel();
        (this.projTxt = new JLabel("Default projection: ", 0)).setFont(this.projTxt.getFont().deriveFont(12.0f));
        this.projTxt.setVerticalTextPosition(0);
        this.projTxt.setHorizontalTextPosition(0);
        this.projTxtPanel.add(this.projTxt);
        (this.defaultProjCheckBox = new JCheckBox("Use Default Projection", this.viewCentral.getPropertyManager().getIsDefaultProjSelected())).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                UserPreferencesDialog.this.defaultProjCheckBox = (JCheckBox)actionEvent.getSource();
                UserPreferencesDialog.this.projectionComboBox.setEnabled(UserPreferencesDialog.this.defaultProjCheckBox.isSelected());
            }
        });
        this.projDropdownPanel = new JPanel();
        (this.projectionComboBox = new JComboBox()).setModel(new DefaultComboBoxModel<Projection>(new Projection[] { ViewerConst.GEOGRAPHIC_PROJECTION, ViewerConst.UTM_PROJECTION, ViewerConst.MGRS_PROJECTION }));
        final Projection proj = this.viewCentral.getPropertyManager().getProj();
        if (proj != null) {
            if (proj.toString().equals(ViewerConst.UTM_PROJECTION.toString())) {
                this.projectionComboBox.setSelectedItem(ViewerConst.UTM_PROJECTION);
            }
            else if (proj.toString().equals(ViewerConst.GEOGRAPHIC_PROJECTION.toString())) {
                this.projectionComboBox.setSelectedItem(ViewerConst.GEOGRAPHIC_PROJECTION);
            }
            else if (proj.toString().equals(ViewerConst.MGRS_PROJECTION.toString())) {
                this.projectionComboBox.setSelectedItem(ViewerConst.MGRS_PROJECTION);
            }
        }
        if (this.viewCentral.getPropertyManager().getIsDefaultProjSelected()) {
            this.projectionComboBox.setEnabled(true);
        }
        else {
            this.projectionComboBox.setEnabled(false);
        }
        this.projDropdownPanel.add(this.projectionComboBox);
        this.projPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.lightGray), " Default Projection "));
        this.projPanel.add(this.projTxtPanel, new GridBagConstraints());
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        gridBagConstraints.gridx = -1;
        gridBagConstraints.gridy = -1;
        this.projPanel.add(this.projDropdownPanel, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        this.projPanel.add(this.defaultProjCheckBox, gridBagConstraints);
    }
    
    private void makeNorthArrowPanel(final GridBagConstraints gridBagConstraints) {
        (this.northArrowImagePanel = new JPanel()).setLayout(new GridBagLayout());
        final JButton button = new JButton();
        button.setText("Choose North Arrow Image");
        button.setToolTipText("Choose a New North Arrow Image");
        button.setActionCommand("northarrow");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                if ("northarrow".equals(actionEvent.getActionCommand())) {
                    new ChooseNorthArrowDialog(UserPreferencesDialog.this.parent, true, UserPreferencesDialog.this.viewCentral).setVisible(true);
                }
            }
        });
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        final JLabel label = new JLabel(Helper.loadImage(this.viewCentral.getPropertyManager().getNorthArrowImage(), "North Arrow Image"));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        this.northArrowImagePanel.add(button, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        this.northArrowImagePanel.add(label, gridBagConstraints);
        this.northArrowImagePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.lightGray), " North Arrow Image "));
    }
    
    private void makeOverview(final GridBagConstraints gridBagConstraints) {
        (this.overviewPanel = new JPanel()).setLayout(new GridBagLayout());
        final JLabel label = new JLabel("Show overview image on startup");
        (this.overviewCheckBox = new JCheckBox()).setSelected(this.viewCentral.getPropertyManager().isShowOverviewImage());
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        this.overviewPanel.add(label, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        this.overviewPanel.add(this.overviewCheckBox, gridBagConstraints);
    }
    
    private void makeButtons(final GridBagConstraints gridBagConstraints) {
        (this.buttonClose = new JButton()).setMnemonic('C');
        this.buttonClose.setText("Close");
        this.buttonClose.setToolTipText("Close window");
        (this.btnPanel = new JPanel()).setLayout(new FlowLayout(2));
        this.btnPanel.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
        (this.okBtn = new JButton()).setMnemonic('O');
        this.okBtn.setText("OK");
        this.okBtn.setToolTipText("Close dialog and save changes");
        this.okBtn.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                UserPreferencesDialog.this.closeDialog(true);
            }
        });
        this.btnPanel.add(this.okBtn);
        (this.cancelBtn = new JButton()).setMnemonic('C');
        this.cancelBtn.setText("Cancel");
        this.cancelBtn.setToolTipText("Close dialog and cancel changes");
        this.cancelBtn.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                UserPreferencesDialog.this.closeDialog(false);
            }
        });
        this.btnPanel.add(this.cancelBtn);
    }
    
    private void closeDialog(final boolean b) {
        if (b) {
            if (!this.setPreferences()) {
                UserPreferencesDialog.log.info("Save failed. Returning to dialog.");
                return;
            }
            this.viewCentral.getPropertyManager().saveProperties();
        }
        this.dispose();
    }
    
    private boolean setPreferences() {
        if (this.qualityChanged) {
            int qualityLayers = this.viewCentral.getPropertyManager().getQualityLayers();
            final int totalQualityLayers = this.viewCentral.getPropertyManager().getTotalQualityLayers();
            int overviewQualityLayersPercentToDownload = -1;
            int overviewQualityLayers = this.viewCentral.getPropertyManager().getOverviewQualityLayers();
            try {
                if (totalQualityLayers != 0) {
                    this.chosenPercent = this.slideQualityLayers.getValue() * 10;
                    qualityLayers = this.chosenPercent * totalQualityLayers / 100;
                    overviewQualityLayersPercentToDownload = this.slideQualityLayersOverview.getValue() * 10;
                    overviewQualityLayers = overviewQualityLayersPercentToDownload * totalQualityLayers / 100;
                }
                this.viewCentral.getPropertyManager().setOverviewQualityLayers(overviewQualityLayers);
                this.viewCentral.getPropertyManager().setOverviewQualityLayersPercentToDownload(overviewQualityLayersPercentToDownload);
                this.viewCentral.getPropertyManager().setQualityLayers(qualityLayers);
                this.viewCentral.getPropertyManager().setQualityLayersPercentToDownload(this.chosenPercent);
                this.qualityChanged = false;
            }
            catch (PropertyVetoException ex) {
                UserPreferencesDialog.log.error("Error setting quality layer.", ex);
                return false;
            }
        }
        if (!this.defaultDraPanelVal.equals(this.getDRAPanelVal())) {
            if (Double.parseDouble(this.stretchTextField.getText()) != this.viewCentral.getPropertyManager().getDefaultDRAPercent()) {
                if (!this.validateStretchPct()) {
                    return false;
                }
                this.viewCentral.getPropertyManager().setDefaultDRAPercent(Double.parseDouble(this.stretchTextField.getText()));
                this.viewCentral.getViewerMainPanel().updateStretchPercent();
            }
            this.viewCentral.getPropertyManager().setUponLoadDoAutoDRA(this.stretchCheckBox.isSelected());
            this.viewCentral.getPropertyManager().setUseIgnoreValueDRA(this.useDraIgnoreValueCheckBox.isSelected());
            if (!this.validateIgnoreDRAValue()) {
                return false;
            }
            try {
                this.viewCentral.getPropertyManager().setIgnoreValueDRA(Integer.parseInt(this.draIgnoreValueField.getText().trim()));
            }
            catch (PropertyVetoException ex5) {}
            this.viewCentral.getPropertyManager().setDefaultDRAType(this.defaultDRACombo.getSelectedIndex());
        }
        this.viewCentral.getPropertyManager().setIsDefaultProjSelected(this.defaultProjCheckBox.isSelected());
        final Projection proj = (Projection)this.projectionComboBox.getSelectedItem();
        try {
            this.viewCentral.getPropertyManager().setProj(proj);
        }
        catch (PropertyVetoException ex2) {
            UserPreferencesDialog.log.error("Error setting quality layer.", ex2);
            return false;
        }
        final Color background = this.gotoSwatchPanel.getBackground();
        try {
            this.viewCentral.getPropertyManager().setGotoColor(background);
        }
        catch (PropertyVetoException ex3) {
            UserPreferencesDialog.log.error("Error changing quality", ex3);
        }
        this.gotoColorChanged = false;
        final Color background2 = this.overviewSwatchPanel.getBackground();
        try {
            this.viewCentral.getPropertyManager().setOverviewColor(background2);
        }
        catch (PropertyVetoException ex4) {
            UserPreferencesDialog.log.error("Error changing quality", ex4);
        }
        this.overviewColorChanged = false;
        this.viewCentral.getPropertyManager().setShowOverviewImage(this.overviewCheckBox.isSelected());
        return true;
    }
    
    private boolean validateIgnoreDRAValue() {
        final String trim = this.draIgnoreValueField.getText().trim();
        try {
            Integer.parseInt(trim);
            return true;
        }
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ignore value must be an integer!", "Ignore value invalid", 0);
            return false;
        }
    }
    
    private boolean validateStretchPct() {
        final double double1 = Double.parseDouble(this.stretchTextField.getText().trim());
        if (double1 < 0.0 || double1 > 49.0) {
            JOptionPane.showMessageDialog(this, "Invalid stretch percentage. Must be between 0 and 49.", "Error Setting DRA", 0);
            return false;
        }
        return true;
    }
    
    private String getDRAPanelVal() {
        final StringBuffer sb = new StringBuffer();
        sb.append(this.stretchTextField.getText());
        sb.append(this.stretchCheckBox.isSelected());
        sb.append(this.draIgnoreValueField.getText());
        sb.append(this.useDraIgnoreValueCheckBox.isSelected());
        sb.append(this.defaultDRACombo.getSelectedIndex());
        return sb.toString();
    }
    
    private void txtKeyTyped(final KeyEvent keyEvent) {
        if (!Helper.validateChar(keyEvent.getKeyChar(), true, false, true)) {
            keyEvent.consume();
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
        UserPreferencesDialog.log = new Log((UserPreferencesDialog.class$com$itt$J2KViewer$gui$UserPreferencesDialog == null) ? (UserPreferencesDialog.class$com$itt$J2KViewer$gui$UserPreferencesDialog = class$("com.itt.J2KViewer.gui.UserPreferencesDialog")) : UserPreferencesDialog.class$com$itt$J2KViewer$gui$UserPreferencesDialog);
        UserPreferencesDialog.PANEL_TITLE_FONT = new Font("SansSerif", 1, 12);
    }
    
    private class ColorChooserAction extends AbstractAction
    {
        static final long serialVersionUID = 1L;
        
        public void actionPerformed(final ActionEvent actionEvent) {
            UserPreferencesDialog.this.colorFrame.setSize(450, 315);
            Toolkit.getDefaultToolkit().getScreenSize();
            UserPreferencesDialog.this.getLocation();
            UserPreferencesDialog.this.colorFrame.setVisible(true);
        }
    }
}
