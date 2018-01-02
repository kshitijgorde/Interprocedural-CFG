// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.gui;

import com.itt.J2KViewer.util.Helper;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.event.FocusAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import com.itt.J2KViewer.exception.InvalidDRAException;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.FocusEvent;
import java.beans.PropertyChangeEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.Container;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.Frame;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Component;
import com.itt.J2KViewer.imagetools.DRAManager;
import com.itt.J2KViewer.controller.PropertyManager;
import com.itt.J2KViewer.controller.ViewCentral;
import java.beans.PropertyChangeListener;
import javax.swing.JDialog;

public class DynamicRangeDialog extends JDialog implements PropertyChangeListener
{
    private static final long serialVersionUID = 1L;
    private static final String STRETCH_PCT_TIP = "Set value from 0 to 49 percent for pixel range adjustment.";
    private static final String MIN_MAX_TIP = "Shows actual min/max raw pixel values for band.";
    private static final String STRETCH_MAX_TIP = "Set maximum value for pixel range of band.";
    private static final String STRETCH_MIN_TIP = "Set minimum value for pixel range of band.";
    private ViewCentral viewCentral;
    private PropertyManager propertyManager;
    private DRAManager draManager;
    private Component lastFocusComponent;
    private JLabel manualInputLabel;
    private JLabel percentInputLabel;
    private JLabel redLabel;
    private JLabel greenLabel;
    private JLabel blueLabel;
    private JLabel stretchMinLabel;
    private JLabel stretchMaxLabel;
    private JLabel rangeLabel;
    private JTextField redStretchMin;
    private JTextField greenStretchMin;
    private JTextField blueStretchMin;
    private JTextField redStretchMax;
    private JTextField greenStretchMax;
    private JTextField blueStretchMax;
    private JLabel redRangeLabel;
    private JLabel greenRangeLabel;
    private JLabel blueRangeLabel;
    private JLabel percentLabel;
    private JTextField percentValue;
    private JTextField draIgnoreVal;
    private JCheckBox useDRAIgnore;
    private JButton autoButton;
    private JButton applyButton;
    private JButton undoButton;
    private JButton resetButton;
    private JButton closeButton;
    
    public DynamicRangeDialog(final Frame frame, final boolean b, final ViewCentral viewCentral) {
        super(frame, b);
        this.viewCentral = null;
        this.propertyManager = null;
        this.draIgnoreVal = null;
        this.useDRAIgnore = null;
        this.viewCentral = viewCentral;
        this.propertyManager = viewCentral.getPropertyManager();
        this.draManager = viewCentral.getDRAManager();
        viewCentral.eventController().addPropertyChangeListener(this);
        this.initDialog();
        this.addItemListener();
    }
    
    private void initDialog() {
        this.setDefaultCloseOperation(2);
        this.setTitle("Dynamic Range");
        final JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        this.manualInputLabel = new JLabel("Enter manual stretch values:");
        this.percentInputLabel = new JLabel("Or an automatic stretch percent:");
        final JLabel label = new JLabel("Ignore Value:");
        this.redLabel = new JLabel("Red:");
        this.greenLabel = new JLabel("Green:");
        this.blueLabel = new JLabel("Blue:");
        this.stretchMinLabel = new JLabel("Min");
        this.stretchMaxLabel = new JLabel("Max");
        this.rangeLabel = new JLabel("Range");
        this.redRangeLabel = new JLabel();
        this.greenRangeLabel = new JLabel();
        this.blueRangeLabel = new JLabel();
        (this.redStretchMin = new JTextField(5)).setToolTipText("Set minimum value for pixel range of band.");
        (this.greenStretchMin = new JTextField(5)).setToolTipText("Set minimum value for pixel range of band.");
        (this.blueStretchMin = new JTextField(5)).setToolTipText("Set minimum value for pixel range of band.");
        (this.redStretchMax = new JTextField(5)).setToolTipText("Set maximum value for pixel range of band.");
        (this.greenStretchMax = new JTextField(5)).setToolTipText("Set maximum value for pixel range of band.");
        (this.blueStretchMax = new JTextField(5)).setToolTipText("Set maximum value for pixel range of band.");
        this.useDRAIgnore = new JCheckBox();
        this.draIgnoreVal = new JTextField(5);
        (this.redRangeLabel = new JLabel()).setToolTipText("Shows actual min/max raw pixel values for band.");
        (this.greenRangeLabel = new JLabel()).setToolTipText("Shows actual min/max raw pixel values for band.");
        (this.blueRangeLabel = new JLabel()).setToolTipText("Shows actual min/max raw pixel values for band.");
        this.percentLabel = new JLabel("Percent:");
        (this.percentValue = new JTextField(5)).setToolTipText("Set value from 0 to 49 percent for pixel range adjustment.");
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.insets = new Insets(20, 2, 10, 2);
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        gridBagConstraints2.weightx = 0.0;
        gridBagConstraints2.anchor = 13;
        gridBagConstraints2.fill = 0;
        gridBagConstraints2.insets = new Insets(0, 2, 0, 2);
        final GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
        gridBagConstraints3.weightx = 0.0;
        gridBagConstraints3.anchor = 17;
        gridBagConstraints3.insets = new Insets(0, 2, 0, 2);
        int gridy = 0;
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.gridx = 0;
        panel.add(this.manualInputLabel, gridBagConstraints);
        ++gridy;
        gridBagConstraints3.gridy = gridy;
        gridBagConstraints3.gridx = 1;
        panel.add(this.stretchMinLabel, gridBagConstraints3);
        gridBagConstraints3.gridx = 2;
        panel.add(this.stretchMaxLabel, gridBagConstraints3);
        gridBagConstraints3.gridx = 3;
        panel.add(this.rangeLabel, gridBagConstraints3);
        ++gridy;
        gridBagConstraints2.gridy = gridy;
        gridBagConstraints2.gridx = 0;
        panel.add(this.redLabel, gridBagConstraints2);
        gridBagConstraints3.gridy = gridy;
        gridBagConstraints3.gridx = 1;
        panel.add(this.redStretchMin, gridBagConstraints3);
        gridBagConstraints3.gridx = 2;
        panel.add(this.redStretchMax, gridBagConstraints3);
        gridBagConstraints3.gridy = gridy;
        gridBagConstraints3.gridx = 3;
        panel.add(this.redRangeLabel, gridBagConstraints3);
        if (this.propertyManager.isShowRGB()) {
            ++gridy;
            gridBagConstraints2.gridy = gridy;
            gridBagConstraints2.gridx = 0;
            panel.add(this.greenLabel, gridBagConstraints2);
            gridBagConstraints3.gridy = gridy;
            gridBagConstraints3.gridx = 1;
            panel.add(this.greenStretchMin, gridBagConstraints3);
            gridBagConstraints3.gridx = 2;
            panel.add(this.greenStretchMax, gridBagConstraints3);
            gridBagConstraints3.gridy = gridy;
            gridBagConstraints3.gridx = 3;
            panel.add(this.greenRangeLabel, gridBagConstraints3);
            ++gridy;
            gridBagConstraints2.gridy = gridy;
            gridBagConstraints2.gridx = 0;
            panel.add(this.blueLabel, gridBagConstraints2);
            gridBagConstraints3.gridy = gridy;
            gridBagConstraints3.gridx = 1;
            panel.add(this.blueStretchMin, gridBagConstraints3);
            gridBagConstraints3.gridx = 2;
            panel.add(this.blueStretchMax, gridBagConstraints3);
            gridBagConstraints3.gridy = gridy;
            gridBagConstraints3.gridx = 3;
            panel.add(this.blueRangeLabel, gridBagConstraints3);
        }
        ++gridy;
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.gridx = 0;
        panel.add(this.percentInputLabel, gridBagConstraints);
        ++gridy;
        gridBagConstraints2.gridy = gridy;
        gridBagConstraints2.gridx = 0;
        panel.add(this.percentLabel, gridBagConstraints2);
        gridBagConstraints3.gridy = gridy;
        gridBagConstraints3.gridx = 1;
        panel.add(this.percentValue, gridBagConstraints3);
        ++gridy;
        gridBagConstraints2.insets = new Insets(2, 2, 0, 2);
        gridBagConstraints2.gridy = gridy;
        gridBagConstraints2.gridx = 0;
        panel.add(label, gridBagConstraints2);
        gridBagConstraints3.insets = new Insets(2, 2, 0, 2);
        gridBagConstraints3.gridy = gridy;
        gridBagConstraints3.gridx = 1;
        panel.add(this.draIgnoreVal, gridBagConstraints3);
        gridBagConstraints3.gridy = gridy;
        gridBagConstraints3.gridx = 2;
        panel.add(this.useDRAIgnore, gridBagConstraints3);
        this.setDRAValues();
        this.setDRARangeValues();
        this.useDRAIgnore.setSelected(this.draManager.useIgnoreValue());
        this.draIgnoreVal.setText(String.valueOf(this.draManager.getIgnoreValue()));
        this.percentValue.setText(String.valueOf(this.draManager.getStretchPercent() * 100.0));
        this.getContentPane().add(panel, "Center");
        this.addTextListeners();
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(2));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout(2));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new BoxLayout(panel4, 1));
        panel4.setBorder(new EmptyBorder(new Insets(3, 3, 3, 3)));
        panel4.add(panel2);
        panel4.add(panel3);
        (this.applyButton = new JButton("Apply")).setMnemonic('A');
        this.applyButton.setToolTipText("Apply the specified values");
        this.applyButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                DynamicRangeDialog.this.applyButtonPressed(actionEvent);
            }
        });
        panel2.add(this.applyButton);
        (this.undoButton = new JButton("Undo")).setMnemonic('U');
        this.undoButton.setToolTipText("Undo the last change");
        this.undoButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                DynamicRangeDialog.this.undoButtonPressed(actionEvent);
            }
        });
        panel2.add(this.undoButton);
        (this.resetButton = new JButton("Reset")).setMnemonic('R');
        this.resetButton.setToolTipText("Reset to default values");
        this.resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                DynamicRangeDialog.this.resetButtonPressed(actionEvent);
            }
        });
        panel2.add(this.resetButton);
        (this.closeButton = new JButton("Close")).setMnemonic('C');
        this.closeButton.setToolTipText("Close this dialog");
        this.closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                DynamicRangeDialog.this.closeButtonPressed(actionEvent);
            }
        });
        panel3.add(this.closeButton);
        this.getContentPane().add(panel4, "South");
        this.pack();
    }
    
    public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
        final String propertyName = propertyChangeEvent.getPropertyName();
        if (propertyName.equals("NewDRA")) {
            if (this.propertyManager.isOpen()) {
                this.setDRAValues();
            }
        }
        else if (propertyName.equals("BytesTransferred")) {
            this.setDRARangeValues();
        }
    }
    
    private void setDRAValues() {
        if (this.draManager != null) {
            this.redStretchMin.setText(String.valueOf(this.draManager.getStretchMinAsInt(0)));
            this.greenStretchMin.setText(String.valueOf(this.draManager.getStretchMinAsInt(1)));
            this.blueStretchMin.setText(String.valueOf(this.draManager.getStretchMinAsInt(2)));
            this.redStretchMax.setText(String.valueOf(this.draManager.getStretchMaxAsInt(0)));
            this.greenStretchMax.setText(String.valueOf(this.draManager.getStretchMaxAsInt(1)));
            this.blueStretchMax.setText(String.valueOf(this.draManager.getStretchMaxAsInt(2)));
        }
    }
    
    private void setDRARangeValues() {
        if (this.draManager != null) {
            this.redRangeLabel.setText(this.getMinMaxText(0));
            this.greenRangeLabel.setText(this.getMinMaxText(1));
            this.blueRangeLabel.setText(this.getMinMaxText(2));
        }
        this.repaint();
    }
    
    private String getMinMaxText(final int n) {
        final int[] dataRange = this.draManager.getDataRange(n);
        final String value = String.valueOf(dataRange[0]);
        final String value2 = String.valueOf(dataRange[1]);
        String s;
        if (value.length() + value2.length() < 8) {
            s = value + "/" + value2;
        }
        else {
            s = value + "/" + value2;
        }
        return s;
    }
    
    public void focusLost(final FocusEvent focusEvent) {
    }
    
    private void addItemListener() {
        this.useDRAIgnore.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent itemEvent) {
                DynamicRangeDialog.this.draIgnoreVal.setEnabled(DynamicRangeDialog.this.useDRAIgnore.isSelected());
            }
        });
    }
    
    private void applyButtonPressed(final ActionEvent actionEvent) {
        try {
            if (this.lastFocusComponent == this.percentValue || this.lastFocusComponent == this.draIgnoreVal || this.lastFocusComponent == this.useDRAIgnore) {
                final ArrayList list = new ArrayList();
                ArrayList list2;
                if (this.useDRAIgnore.isSelected() && this.validateIgnoreDRAValue()) {
                    list2 = this.viewCentral.getImageStream().calculateHistogram(true, this.draManager.getIgnoreValue());
                }
                else {
                    list2 = this.viewCentral.getImageStream().calculateHistogram(false, this.draManager.getIgnoreValue());
                }
                this.draManager.setHistograms(list2.get(0), list2.get(1), list2.get(2));
                if (this.validateStretchPct()) {
                    this.draManager.auto(Double.parseDouble(this.percentValue.getText().trim()));
                }
            }
            else {
                this.validateAndSetDRA(this.redStretchMin.getText(), this.redStretchMax.getText(), 0);
                this.validateAndSetDRA(this.greenStretchMin.getText(), this.greenStretchMax.getText(), 1);
                this.validateAndSetDRA(this.blueStretchMin.getText(), this.blueStretchMax.getText(), 2);
            }
        }
        catch (InvalidDRAException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error Setting DRA", 2);
        }
        this.viewCentral.getPropertyManager().setNewDRA(true);
    }
    
    private void validateAndSetDRA(final String s, final String s2, final int n) throws InvalidDRAException {
        final float maxValue = this.draManager.getMaxValue();
        final float minValue = this.draManager.getMinValue();
        final float float1 = Float.parseFloat(s);
        final float float2 = Float.parseFloat(s2);
        if (float2 > maxValue) {
            throw new InvalidDRAException("Max stretch value must be less than " + (int)maxValue);
        }
        if (float1 < minValue) {
            throw new InvalidDRAException("Min stretch value must be greater than " + (int)minValue);
        }
        if (float1 >= float2) {
            throw new InvalidDRAException("Min stretch value must be less than or equal to Max stretch value.");
        }
        this.draManager.setStretchMin(n, float1);
        this.draManager.setStretchMax(n, float2);
    }
    
    private boolean validateIgnoreDRAValue() {
        final String trim = this.draIgnoreVal.getText().trim();
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
        final double double1 = Double.parseDouble(this.percentValue.getText().trim());
        if (double1 < 0.0 || double1 > 49.0) {
            JOptionPane.showMessageDialog(this, "Invalid stretch percentage. Must be between 0 and 49.", "Error Setting DRA", 0);
            return false;
        }
        return true;
    }
    
    private void addTextListeners() {
        final KeyAdapter keyAdapter = new KeyAdapter() {
            public void keyTyped(final KeyEvent keyEvent) {
                DynamicRangeDialog.this.txtKeyTyped(keyEvent);
            }
        };
        final FocusAdapter focusAdapter = new FocusAdapter() {
            public void focusGained(final FocusEvent focusEvent) {
                DynamicRangeDialog.this.txtFocusGained(focusEvent);
            }
        };
        this.redStretchMin.addKeyListener(keyAdapter);
        this.redStretchMax.addKeyListener(keyAdapter);
        this.greenStretchMin.addKeyListener(keyAdapter);
        this.greenStretchMax.addKeyListener(keyAdapter);
        this.blueStretchMin.addKeyListener(keyAdapter);
        this.blueStretchMax.addKeyListener(keyAdapter);
        this.redStretchMin.addFocusListener(focusAdapter);
        this.redStretchMax.addFocusListener(focusAdapter);
        this.greenStretchMin.addFocusListener(focusAdapter);
        this.greenStretchMax.addFocusListener(focusAdapter);
        this.blueStretchMin.addFocusListener(focusAdapter);
        this.blueStretchMax.addFocusListener(focusAdapter);
        this.percentValue.addKeyListener(keyAdapter);
        this.percentValue.addFocusListener(focusAdapter);
        this.draIgnoreVal.addKeyListener(keyAdapter);
        this.draIgnoreVal.addFocusListener(focusAdapter);
        this.useDRAIgnore.addFocusListener(focusAdapter);
    }
    
    private void txtKeyTyped(final KeyEvent keyEvent) {
        if (!Helper.validateChar(keyEvent.getKeyChar(), true, false, true)) {
            keyEvent.consume();
        }
    }
    
    private void txtFocusGained(final FocusEvent focusEvent) {
        this.lastFocusComponent = focusEvent.getComponent();
        if (focusEvent.getComponent() instanceof JTextField) {
            final JTextField textField = (JTextField)focusEvent.getComponent();
            textField.setCaretPosition(0);
            textField.moveCaretPosition(textField.getText().length());
        }
    }
    
    private void undoButtonPressed(final ActionEvent actionEvent) {
        this.draManager.undo();
    }
    
    private void resetButtonPressed(final ActionEvent actionEvent) {
        this.draManager.reset();
    }
    
    private void autoButtonPressed(final ActionEvent actionEvent) {
        this.draManager.useIgnoreValue(this.viewCentral.getPropertyManager().isUseIgnoreValueDRA());
        this.useDRAIgnore.setSelected(this.viewCentral.getPropertyManager().isUseIgnoreValueDRA());
        this.draManager.setIgnoreValue(this.viewCentral.getPropertyManager().getIgnoreValueDRA());
        this.draIgnoreVal.setText(Integer.toString(this.viewCentral.getPropertyManager().getIgnoreValueDRA()));
        final ArrayList list = new ArrayList();
        final ArrayList calculateHistogram = this.viewCentral.getImageStream().calculateHistogram(this.viewCentral.getPropertyManager().isUseIgnoreValueDRA(), this.draManager.getIgnoreValue());
        this.draManager.setHistograms(calculateHistogram.get(0), calculateHistogram.get(1), calculateHistogram.get(2));
        this.draManager.auto(this.viewCentral.getPropertyManager().getDefaultDRAPercent());
    }
    
    private void closeButtonPressed(final ActionEvent actionEvent) {
        this.dispose();
    }
}
