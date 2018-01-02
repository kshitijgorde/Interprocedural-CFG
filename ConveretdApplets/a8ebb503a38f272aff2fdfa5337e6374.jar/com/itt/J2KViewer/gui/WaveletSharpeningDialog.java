// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.gui;

import javax.swing.event.ChangeEvent;
import java.beans.PropertyVetoException;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.Insets;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.Component;
import java.awt.Frame;
import com.itt.J2KViewer.controller.ViewCentral;
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.ChangeListener;
import java.awt.event.ComponentListener;
import javax.swing.JDialog;

public class WaveletSharpeningDialog extends JDialog implements ComponentListener, ChangeListener
{
    private final int LABEL_HEIGHT = 19;
    private final int INSETS_LINE_START = 0;
    private final int INSETS_DESC = 1;
    private final int INSETS_DATA = 2;
    private JPanel m_mainPanel;
    private JButton m_applyButton;
    private JButton m_useLastValueButton;
    private JButton m_closeButton;
    private JButton m_resetButton;
    private JPanel m_buttonPanel;
    private JSlider m_gainSlider;
    private JTextField m_sliderValue;
    private ViewCentral viewCentral;
    private double m_lastGainValue;
    private double m_currentGainValue;
    private boolean m_programaticallySettingValue;
    static final String ZEROES = "000000000000";
    static final String BLANKS = "            ";
    
    public WaveletSharpeningDialog(final ViewCentral viewCentral, final Frame locationRelativeTo, final boolean b) {
        super(locationRelativeTo, b);
        this.m_gainSlider = new JSlider(0, 100);
        this.m_lastGainValue = 1.0;
        this.m_currentGainValue = 1.0;
        this.m_programaticallySettingValue = false;
        this.viewCentral = viewCentral;
        this.setTitle("Wavelet Sharpening");
        this.initComponents(viewCentral);
        this.getRootPane().setDefaultButton(this.m_applyButton);
        this.setLocationRelativeTo(locationRelativeTo);
        this.addComponentListener(this);
    }
    
    public void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
        final Component component = componentEvent.getComponent();
        final Dimension size = component.getSize();
        final Dimension minimumSize = component.getMinimumSize();
        final Dimension size2 = new Dimension(size);
        boolean b = false;
        if (size.width < minimumSize.width) {
            b = true;
            size2.width = minimumSize.width;
        }
        if (size.height < minimumSize.height) {
            b = true;
            size2.height = minimumSize.height;
        }
        if (b) {
            component.setSize(size2);
        }
    }
    
    public Dimension getMinimumSize() {
        final Dimension preferredSize;
        final Dimension dimension = preferredSize = this.m_mainPanel.getPreferredSize();
        preferredSize.height += 40;
        return dimension;
    }
    
    private void initComponents(final ViewCentral viewCentral) {
        (this.m_mainPanel = new JPanel()).setLayout(new BorderLayout());
        this.getContentPane().add(this.m_mainPanel);
        this.m_mainPanel.add(this.buildDialogGuts(viewCentral), "Center");
        this.m_currentGainValue = viewCentral.getPropertyManager().getSharpGain();
        this.setSliderValue(this.m_gainSlider, this.m_currentGainValue);
        (this.m_buttonPanel = new JPanel()).setLayout(new FlowLayout());
        this.m_buttonPanel.setBorder(BorderFactory.createEmptyBorder(4, 6, 4, 6));
        (this.m_closeButton = new JButton("Close")).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                WaveletSharpeningDialog.this.btnCloseActionPerformed();
            }
        });
        (this.m_applyButton = new JButton("Apply")).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                WaveletSharpeningDialog.this.btnApplyActionPerformed();
            }
        });
        (this.m_useLastValueButton = new JButton("Use Last Value")).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                WaveletSharpeningDialog.this.btnUseLastValueActionPerformed();
            }
        });
        (this.m_resetButton = new JButton("No Sharpening")).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                WaveletSharpeningDialog.this.btnResetActionPerformed();
            }
        });
        this.m_buttonPanel.add(this.m_resetButton);
        this.m_buttonPanel.add(this.m_applyButton);
        this.m_buttonPanel.add(this.m_useLastValueButton);
        this.m_buttonPanel.add(this.m_closeButton);
        this.m_mainPanel.add(this.m_buttonPanel, "South");
        this.m_applyButton.setEnabled(false);
        if (this.m_currentGainValue != 1.0) {
            this.btnApplyActionPerformed();
        }
        this.pack();
    }
    
    private JPanel buildDialogGuts(final ViewCentral viewCentral) {
        int n = 1;
        int n2 = 1;
        this.m_gainSlider.setPaintLabels(false);
        this.m_gainSlider.setPaintTicks(true);
        this.m_gainSlider.setMajorTickSpacing(10);
        this.m_gainSlider.setMinorTickSpacing(5);
        this.m_gainSlider.addChangeListener(this);
        final JPanel panel = new JPanel(new GridBagLayout());
        final JLabel label = this.createLabel("1", -1, false, 2);
        final GridBagConstraints buildConstraints = GridBagConstraintsHelper.buildConstraints(n++, n2, 0, 0, 17);
        buildConstraints.insets = this.createInsets(0, -1);
        panel.add(label, buildConstraints);
        this.m_sliderValue = this.createTextField("1.0", 50, false, 0);
        final GridBagConstraints buildConstraints2 = GridBagConstraintsHelper.buildConstraints(n++, n2, 0, 0, 10);
        buildConstraints2.insets = new Insets(0, 40, 0, 40);
        buildConstraints2.weightx = 1.0;
        buildConstraints2.fill = 2;
        panel.add(this.m_sliderValue, buildConstraints2);
        this.m_sliderValue.addKeyListener(new KeyAdapter() {
            public void keyTyped(final KeyEvent keyEvent) {
                if (keyEvent.getKeyChar() == '\n') {
                    WaveletSharpeningDialog.this.btnApplyActionPerformed();
                }
                else {
                    WaveletSharpeningDialog.this.m_applyButton.setEnabled(true);
                }
            }
        });
        final JLabel label2 = this.createLabel("25", -1, false, 2);
        final GridBagConstraints buildConstraints3 = GridBagConstraintsHelper.buildConstraints(n++, n2, 0, 0, 13);
        buildConstraints3.gridwidth = 0;
        buildConstraints3.insets = this.createInsets(2, -1);
        panel.add(label2, buildConstraints3);
        ++n2;
        int n3 = 1;
        final GridBagConstraints buildConstraints4 = GridBagConstraintsHelper.buildConstraints(n3++, n2, 0, 0, 17);
        buildConstraints4.gridwidth = 0;
        buildConstraints4.insets = this.createInsets(2, -1);
        buildConstraints4.weightx = 1.0;
        buildConstraints4.fill = 2;
        panel.add(this.m_gainSlider, buildConstraints4);
        this.m_gainSlider.setMinimumSize(new Dimension(100, 30));
        return panel;
    }
    
    protected JTextField createTextField(final String s, final int n, final boolean b, final int horizontalAlignment) {
        final JTextField textField = new JTextField(s);
        final Dimension preferredSize = textField.getPreferredSize();
        if (n >= 0) {
            textField.setPreferredSize(new Dimension(n, 19));
        }
        else {
            textField.setPreferredSize(new Dimension((int)preferredSize.getWidth(), 19));
        }
        textField.setHorizontalAlignment(horizontalAlignment);
        return textField;
    }
    
    protected JLabel createLabel(final String s, final int n, final boolean b, final int horizontalAlignment) {
        final JLabel label = new JLabel(s);
        if (b) {
            label.setBorder(BorderFactory.createEtchedBorder());
        }
        final Dimension preferredSize = label.getPreferredSize();
        if (n >= 0) {
            label.setPreferredSize(new Dimension(n, 19));
        }
        else {
            label.setPreferredSize(new Dimension((int)preferredSize.getWidth(), 19));
        }
        label.setHorizontalAlignment(horizontalAlignment);
        return label;
    }
    
    protected Insets createInsets(final int n, final int n2) {
        return this.createInsets(n, n2, 1);
    }
    
    protected Insets createInsets(final int n, final int n2, final int n3) {
        int n4 = 0;
        int n5 = 3;
        int n6 = 0;
        int n7 = 0;
        switch (n2) {
            case 1: {
                n5 = n3;
                break;
            }
            case 3: {
                n4 = n3;
                break;
            }
            case 4: {
                n6 = n3;
                break;
            }
            case 2: {
                n7 = n3;
                break;
            }
        }
        if (n2 == 3) {
            n4 = n3;
        }
        else if (n2 == 1) {
            n5 = n3;
        }
        switch (n) {
            case 0: {
                return new Insets(n5, 5 + n7, n4, 3 + n6);
            }
            case 1: {
                return new Insets(n5, 0 + n7, n4, 3 + n6);
            }
            case 2: {
                return new Insets(n5, 0 + n7, n4, 7 + n6);
            }
            default: {
                return null;
            }
        }
    }
    
    private void btnCloseActionPerformed() {
        this.dispose();
    }
    
    private void setButtonPanelSensitivity(final double n) {
        if (n == 1.0) {
            this.m_resetButton.setEnabled(false);
        }
        else {
            this.m_resetButton.setEnabled(true);
        }
        this.m_applyButton.setEnabled(false);
    }
    
    private void btnApplyActionPerformed() {
        try {
            final double double1 = Double.parseDouble(this.m_sliderValue.getText().trim());
            if (double1 != this.m_currentGainValue) {
                this.viewCentral.getPropertyManager().setSharpGain(double1);
                this.setSliderValue(this.m_gainSlider, double1);
                this.setButtonPanelSensitivity(double1);
                this.m_lastGainValue = this.m_currentGainValue;
                this.m_currentGainValue = double1;
            }
        }
        catch (PropertyVetoException ex) {
            this.m_sliderValue.setText(format(this.m_currentGainValue, 3, 6));
        }
        catch (NumberFormatException ex2) {
            this.m_sliderValue.setText(format(this.m_currentGainValue, 3, 6));
        }
    }
    
    private void btnUseLastValueActionPerformed() {
        this.m_sliderValue.setText(format(this.m_lastGainValue, 3, 6));
        this.btnApplyActionPerformed();
    }
    
    private void btnResetActionPerformed() {
        this.m_sliderValue.setText(format(1.0, 3, 6));
        this.btnApplyActionPerformed();
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        if (this.m_programaticallySettingValue) {
            return;
        }
        final JSlider slider = (JSlider)changeEvent.getSource();
        if (!slider.getValueIsAdjusting()) {
            final double sliderValue = this.getSliderValue(slider);
            if (sliderValue != this.viewCentral.getPropertyManager().getSharpGain()) {
                this.m_sliderValue.setText(format(sliderValue, 3, 6));
                this.btnApplyActionPerformed();
            }
        }
    }
    
    static String format(double n, final int n2, int n3) {
        double n4 = 0.5;
        for (int i = n2; i > 0; --i) {
            n4 /= 10.0;
        }
        n += n4;
        String s = Double.toString(n);
        final int index = s.indexOf(46);
        final int n5 = s.length() - index - 1;
        if (n2 > n5) {
            s += "000000000000".substring(0, n2 - n5);
        }
        else if (n5 > n2) {
            s = s.substring(0, index + n2 + 1);
        }
        if (n3 > 0 & n3 > s.length()) {
            s = "            ".substring(0, n3 - s.length()) + s;
        }
        else if (n3 < 0 & -n3 > s.length()) {
            n3 = -n3;
            s += "            ".substring(0, n3 - s.length());
        }
        return s;
    }
    
    private static int percentage(double n, final float n2, float n3) {
        n3 -= n2;
        n -= n2;
        return (int)(n / n3 * 100.0);
    }
    
    private static double value(final int n, final float n2, float n3) {
        n3 -= n2;
        return n / 100.0f * n3 + n2;
    }
    
    private void setSliderValue(final JSlider slider, final double n) {
        final int percentage = percentage(n, 1.0f, 25.0f);
        this.m_programaticallySettingValue = true;
        slider.setValue(percentage);
        this.m_programaticallySettingValue = false;
        this.m_sliderValue.setText(format(n, 3, 6));
    }
    
    private double getSliderValue(final JSlider slider) {
        return value(slider.getValue(), 1.0f, 25.0f);
    }
}
