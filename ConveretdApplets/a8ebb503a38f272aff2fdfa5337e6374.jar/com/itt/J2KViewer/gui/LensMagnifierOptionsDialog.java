// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.gui;

import javax.swing.event.ChangeEvent;
import java.awt.Color;
import javax.swing.JColorChooser;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.border.TitledBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.JLabel;
import com.itt.J2KViewer.controller.ViewCentral;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.ChangeListener;
import java.awt.event.ComponentListener;
import javax.swing.JDialog;

public class LensMagnifierOptionsDialog extends JDialog implements ComponentListener, ChangeListener
{
    private JPanel m_mainPanel;
    private JButton m_okButton;
    private ViewCentral m_viewCentral;
    JLabel m_lensBorderColorLabel;
    private JSlider m_slider;
    private JTextField m_TFdiameter;
    private JRadioButton m_RBcircle;
    private JRadioButton m_RBsquare;
    private JRadioButton m_RB2;
    private JRadioButton m_RB4;
    private JRadioButton m_RB8;
    private JRadioButton m_RB16;
    private static Font PANEL_TITLE_FONT;
    private static final int DIA_MIN = 40;
    private static final int DIA_MAX = 320;
    private boolean m_programaticallySettingValue;
    static final String ZEROES = "000000000000";
    static final String BLANKS = "            ";
    
    public LensMagnifierOptionsDialog(final ViewCentral viewCentral, final Frame locationRelativeTo, final boolean b) {
        super(locationRelativeTo, b);
        this.m_slider = new JSlider(0, 100);
        this.m_TFdiameter = new JTextField(9);
        this.m_RBcircle = new JRadioButton("Circle");
        this.m_RBsquare = new JRadioButton("Square");
        this.m_RB2 = new JRadioButton("2x");
        this.m_RB4 = new JRadioButton("4x");
        this.m_RB8 = new JRadioButton("8x");
        this.m_RB16 = new JRadioButton("16x");
        this.m_programaticallySettingValue = false;
        this.m_viewCentral = viewCentral;
        this.setTitle("Magnifying Glass Properties");
        this.initComponents(viewCentral);
        this.getRootPane().setDefaultButton(this.m_okButton);
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
        viewCentral.getMainImagePanel().getImageDisplayPanel().getLensMagnifier();
        switch (this.m_viewCentral.getPropertyManager().getLensType()) {
            case 0: {
                this.m_RBcircle.setSelected(true);
                break;
            }
            case 1: {
                this.m_RBsquare.setSelected(true);
                break;
            }
        }
        this.m_TFdiameter.setText(String.valueOf(this.m_viewCentral.getPropertyManager().getLensRadius() * 2));
        this.setSliderValue(this.m_slider, this.m_viewCentral.getPropertyManager().getLensRadius() * 2);
        switch (this.m_viewCentral.getPropertyManager().getLensMagnification()) {
            case 2: {
                this.m_RB2.setSelected(true);
                break;
            }
            case 4: {
                this.m_RB4.setSelected(true);
                break;
            }
            case 8: {
                this.m_RB8.setSelected(true);
                break;
            }
            case 16: {
                this.m_RB16.setSelected(true);
                break;
            }
        }
        final ActionListener actionListener = new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                try {
                    if (actionEvent.getSource() == LensMagnifierOptionsDialog.this.m_RBcircle) {
                        LensMagnifierOptionsDialog.this.m_viewCentral.getPropertyManager().setLensType(0);
                    }
                    else if (actionEvent.getSource() == LensMagnifierOptionsDialog.this.m_RBsquare) {
                        LensMagnifierOptionsDialog.this.m_viewCentral.getPropertyManager().setLensType(1);
                    }
                }
                catch (Exception ex) {}
            }
        };
        this.m_RBcircle.addActionListener(actionListener);
        this.m_RBsquare.addActionListener(actionListener);
        final ActionListener actionListener2 = new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                if (actionEvent.getSource() == LensMagnifierOptionsDialog.this.m_RB2) {
                    LensMagnifierOptionsDialog.this.m_viewCentral.getPropertyManager().setLensMagnification(2);
                }
                else if (actionEvent.getSource() == LensMagnifierOptionsDialog.this.m_RB4) {
                    LensMagnifierOptionsDialog.this.m_viewCentral.getPropertyManager().setLensMagnification(4);
                }
                else if (actionEvent.getSource() == LensMagnifierOptionsDialog.this.m_RB8) {
                    LensMagnifierOptionsDialog.this.m_viewCentral.getPropertyManager().setLensMagnification(8);
                }
                else if (actionEvent.getSource() == LensMagnifierOptionsDialog.this.m_RB16) {
                    LensMagnifierOptionsDialog.this.m_viewCentral.getPropertyManager().setLensMagnification(16);
                }
            }
        };
        this.m_RB2.addActionListener(actionListener2);
        this.m_RB4.addActionListener(actionListener2);
        this.m_RB8.addActionListener(actionListener2);
        this.m_RB16.addActionListener(actionListener2);
        this.m_TFdiameter.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                LensMagnifierOptionsDialog.this.applyNewRadiusFromTextField();
            }
        });
        this.pack();
    }
    
    public void applyNewRadiusFromTextField() {
        int int1 = Integer.parseInt(this.m_TFdiameter.getText().trim());
        if (int1 < 40) {
            int1 = 40;
            this.m_TFdiameter.setText(String.valueOf(int1));
        }
        else if (int1 > 320) {
            int1 = 320;
            this.m_TFdiameter.setText(String.valueOf(int1));
        }
        this.m_viewCentral.getPropertyManager().setLensRadius(int1 / 2);
        this.setSliderValue(this.m_slider, int1);
    }
    
    private JPanel newSection(final String s) {
        final JPanel panel = new JPanel(new RiverLayout());
        final TitledBorder titledBorder = BorderFactory.createTitledBorder(s);
        titledBorder.setTitleFont(LensMagnifierOptionsDialog.PANEL_TITLE_FONT);
        panel.setBorder(new CompoundBorder(titledBorder, new EmptyBorder(0, 0, 0, 0)));
        return panel;
    }
    
    private JPanel buildDialogGuts(final ViewCentral viewCentral) {
        final JPanel panel = new JPanel(new RiverLayout());
        final JPanel section = this.newSection("Zoom Factor");
        final ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(this.m_RB2);
        buttonGroup.add(this.m_RB4);
        buttonGroup.add(this.m_RB8);
        buttonGroup.add(this.m_RB16);
        section.add("left", this.m_RB2);
        section.add("tab", this.m_RB8);
        section.add("br left", this.m_RB4);
        section.add("tab", this.m_RB16);
        panel.add("left", section);
        final JPanel section2 = this.newSection("Shape");
        final ButtonGroup buttonGroup2 = new ButtonGroup();
        buttonGroup2.add(this.m_RBcircle);
        buttonGroup2.add(this.m_RBsquare);
        section2.add("left", this.m_RBcircle);
        section2.add("br left", this.m_RBsquare);
        panel.add("tab left", section2);
        final JPanel section3 = this.newSection("Diameter");
        section3.add("left", this.m_TFdiameter);
        section3.add("br left", this.m_slider);
        panel.add("br left", section3);
        this.m_slider.setPaintLabels(false);
        this.m_slider.setPaintTicks(true);
        this.m_slider.setMajorTickSpacing(10);
        this.m_slider.setMinorTickSpacing(5);
        this.m_slider.addChangeListener(this);
        this.m_slider.setPreferredSize(this.m_TFdiameter.getPreferredSize());
        (this.m_okButton = new JButton("Close")).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                LensMagnifierOptionsDialog.this.setVisible(false);
            }
        });
        final JPanel section4 = this.newSection("Lens Border Color");
        (this.m_lensBorderColorLabel = new JLabel("Lens")).setOpaque(true);
        final LensMagnifier lensMagnifier = this.m_viewCentral.getMainImagePanel().getImageDisplayPanel().getLensMagnifier();
        this.m_lensBorderColorLabel.setBackground(lensMagnifier.getLensOutlineColor());
        this.m_lensBorderColorLabel.setForeground(lensMagnifier.getLensOutlineColor());
        final JButton button = new JButton("Choose...");
        section4.add("left", this.m_lensBorderColorLabel);
        section4.add("left", button);
        panel.add("left", section4);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                final Color showDialog = JColorChooser.showDialog(LensMagnifierOptionsDialog.this.m_viewCentral.getMainImagePanel().getImageDisplayPanel(), "Choose Lens Outline Color", LensMagnifierOptionsDialog.this.m_viewCentral.getPropertyManager().getLensColor());
                if (showDialog != null) {
                    try {
                        LensMagnifierOptionsDialog.this.m_viewCentral.getPropertyManager().setLensColor(showDialog);
                    }
                    catch (Exception ex) {}
                    LensMagnifierOptionsDialog.this.m_lensBorderColorLabel.setBackground(showDialog);
                    LensMagnifierOptionsDialog.this.m_lensBorderColorLabel.setForeground(showDialog);
                }
            }
        });
        final JPanel section5 = this.newSection("Tips");
        section5.add("left", new JLabel("Drag the left mouse button to move the"));
        section5.add("br left", new JLabel("magnifying glass.  Using the shift key"));
        section5.add("br left", new JLabel("while dragging will keep content in the glass."));
        panel.add("br left", section5);
        final Dimension preferredSize2;
        final Dimension preferredSize = preferredSize2 = this.m_okButton.getPreferredSize();
        preferredSize2.width *= 2;
        this.m_okButton.setPreferredSize(preferredSize);
        panel.add("br center", this.m_okButton);
        return panel;
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        if (this.m_programaticallySettingValue) {
            return;
        }
        final double sliderValue = this.getSliderValue((JSlider)changeEvent.getSource());
        if (sliderValue != this.m_viewCentral.getPropertyManager().getLensRadius() * 2) {
            this.m_TFdiameter.setText(format(sliderValue, 0, 6));
            this.applyNewRadiusFromTextField();
        }
    }
    
    private void setSliderValue(final JSlider slider, final double n) {
        final int percentage = percentage(n, 40.0f, 320.0f);
        this.m_programaticallySettingValue = true;
        this.m_slider.setValue(percentage);
        this.m_programaticallySettingValue = false;
        this.m_TFdiameter.setText(format(n, 0, 6));
    }
    
    private double getSliderValue(final JSlider slider) {
        return value(slider.getValue(), 40.0f, 320.0f);
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
        return s.substring(0, s.length() - 1).trim();
    }
    
    static {
        LensMagnifierOptionsDialog.PANEL_TITLE_FONT = new Font("SansSerif", 1, 12);
    }
}
