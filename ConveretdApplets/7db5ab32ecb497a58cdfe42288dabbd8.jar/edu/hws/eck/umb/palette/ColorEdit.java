// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.eck.umb.palette;

import javax.swing.event.ChangeEvent;
import javax.swing.JOptionPane;
import java.awt.Point;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.Action;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import javax.swing.event.ChangeListener;
import java.awt.event.MouseListener;
import javax.swing.JColorChooser;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import edu.hws.eck.umb.util.I18n;
import java.awt.Dialog;
import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;

class ColorEdit extends JDialog
{
    private float[] originalColorComponents;
    private double originalPosition;
    private Palette palette;
    private int index;
    private int createdIndex;
    private JButton okButton;
    private JButton closeButton;
    private JButton applyButton;
    private JButton revertButton;
    private JTextField[] input;
    private JTextField positionInput;
    private JPanel colorPatch;
    
    public static void showDialog(Component parent, final Palette palette, final int n) {
        while (parent != null && !(parent instanceof Dialog)) {
            parent = parent.getParent();
        }
        new ColorEdit((Dialog)parent, palette, n).setVisible(true);
    }
    
    private ColorEdit(final Dialog dialog, final Palette palette, final int index) {
        super(dialog, (index == -1) ? I18n.tr("colorEditDialog.titleForAddingNewColor", new Object[0]) : I18n.tr("colorEditDialog.title", new Object[0]), true);
        this.createdIndex = -1;
        this.palette = palette;
        this.index = index;
        if (index == -1) {
            this.originalColorComponents = new float[] { 0.5f, 0.5f, 0.5f };
        }
        else {
            this.originalColorComponents = palette.getDivisionPointColorComponents(index);
            this.originalPosition = palette.getDivisionPoint(index);
        }
        final JPanel contentPane = new JPanel();
        this.setContentPane(contentPane);
        contentPane.setBackground(Color.DARK_GRAY);
        contentPane.setLayout(new BorderLayout(3, 3));
        contentPane.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 3));
        this.okButton = new JButton(I18n.tr("buttonName.OK", new Object[0]));
        this.closeButton = new JButton(I18n.tr("buttonName.Close", new Object[0]));
        this.applyButton = new JButton(I18n.tr("buttonName.Apply", new Object[0]));
        this.revertButton = new JButton(I18n.tr("buttonName.Revert", new Object[0]));
        final Listener listener = new Listener();
        this.okButton.addActionListener(listener);
        this.closeButton.addActionListener(listener);
        this.applyButton.addActionListener(listener);
        this.revertButton.addActionListener(listener);
        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2, 1, 1));
        panel.setBackground(Color.LIGHT_GRAY);
        panel.add(this.revertButton);
        panel.add(this.applyButton);
        panel.add(this.closeButton);
        panel.add(this.okButton);
        contentPane.add(panel, "South");
        final JPanel panel2 = new JPanel();
        final JPanel panel3 = new JPanel();
        final JPanel panel4 = new JPanel();
        panel2.setLayout(new BorderLayout(5, 5));
        panel2.setBackground(Color.LIGHT_GRAY);
        panel2.add(panel3, "West");
        panel2.add(panel4, "Center");
        panel3.setBackground(Color.LIGHT_GRAY);
        panel4.setBackground(Color.LIGHT_GRAY);
        panel3.setLayout(new GridLayout(3, 1, 3, 3));
        panel4.setLayout(new GridLayout(3, 1, 3, 3));
        this.input = new JTextField[3];
        if (palette.getColorType() == 1) {
            panel3.add(new JLabel(I18n.tr("colorComponent.Hue", new Object[0]) + " = ", 4));
            panel3.add(new JLabel(I18n.tr("colorComponent.Saturation", new Object[0]) + " = ", 4));
            panel3.add(new JLabel(I18n.tr("colorComponent.Brightness", new Object[0]) + " = ", 4));
        }
        else {
            panel3.add(new JLabel(I18n.tr("colorComponent.Red", new Object[0]) + " = ", 4));
            panel3.add(new JLabel(I18n.tr("colorComponent.Green", new Object[0]) + " = ", 4));
            panel3.add(new JLabel(I18n.tr("colorComponent.Blue", new Object[0]) + " = ", 4));
        }
        for (int i = 0; i < 3; ++i) {
            (this.input[i] = new JTextField("" + this.originalColorComponents[i], 9)).setMargin(new Insets(0, 4, 0, 4));
            panel4.add(this.input[i]);
        }
        panel2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        contentPane.add(panel2, "West");
        if (index == -1 || (index > 0 && index < palette.getDivisionPointCount() - 1)) {
            final JPanel panel5 = new JPanel();
            panel5.setLayout(new BorderLayout(5, 5));
            panel5.setBackground(Color.LIGHT_GRAY);
            panel5.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
            panel5.add(new JLabel(I18n.tr("colorEditDialog.positionOfColorBar", String.format("%1.4f", (index == -1) ? 0.0 : palette.getDivisionPoint(index - 1)), String.format("%1.4f", (index == -1) ? 1.0 : palette.getDivisionPoint(index + 1))) + " = "), "West");
            if (index == -1) {
                this.positionInput = new JTextField(9);
            }
            else {
                this.positionInput = new JTextField(String.format("%1.4f", palette.getDivisionPoint(index)), 9);
            }
            this.positionInput.setMargin(new Insets(4, 4, 4, 4));
            panel5.add(this.positionInput, "Center");
            contentPane.add(panel5, "North");
        }
        (this.colorPatch = new JPanel()).setPreferredSize(new Dimension(100, 100));
        if (index == -1) {
            if (palette.getColorType() == 1) {
                this.colorPatch.setBackground(Color.getHSBColor(0.5f, 0.5f, 0.5f));
            }
            else {
                this.colorPatch.setBackground(new Color(0.5f, 0.5f, 0.5f));
            }
        }
        else {
            this.colorPatch.setBackground(palette.getDivisionPointColor(index));
        }
        this.colorPatch.setToolTipText(I18n.tr("colorEditDialog.tooltip.clickToEditColor", new Object[0]));
        this.colorPatch.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                final Color showDialog = JColorChooser.showDialog(ColorEdit.this, I18n.tr("colorEditDialog.colorChooserTitl", new Object[0]), ColorEdit.this.colorPatch.getBackground());
                if (showDialog != null) {
                    float[] array;
                    if (ColorEdit.this.palette.getColorType() == 1) {
                        array = Color.RGBtoHSB(showDialog.getRed(), showDialog.getGreen(), showDialog.getBlue(), null);
                    }
                    else {
                        array = showDialog.getRGBColorComponents(null);
                    }
                    if (ColorEdit.this.index != -1) {
                        ColorEdit.this.palette.setDivisionPointColorComponents(ColorEdit.this.index, array[0], array[1], array[2]);
                    }
                    else {
                        ColorEdit.this.colorPatch.setBackground(showDialog);
                    }
                    for (int i = 0; i < 3; ++i) {
                        ColorEdit.this.input[i].setText("" + array[i]);
                    }
                }
            }
        });
        palette.addChangeListener(listener);
        contentPane.add(this.colorPatch, "Center");
        this.setDefaultCloseOperation(0);
        contentPane.getInputMap(2).put(KeyStroke.getKeyStroke("ESCAPE"), "cancel");
        contentPane.getActionMap().put("cancel", new AbstractAction() {
            public void actionPerformed(final ActionEvent actionEvent) {
                ColorEdit.this.closeButton.doClick();
            }
        });
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                ColorEdit.this.doCancel();
            }
            
            public void windowClosed(final WindowEvent windowEvent) {
                ColorEdit.this.palette.removeChangeListener(listener);
            }
        });
        this.getRootPane().setDefaultButton(this.okButton);
        this.pack();
        this.setResizable(false);
        if (dialog != null) {
            final Point location = dialog.getLocation();
            this.setLocation(location.x + 25, location.y + 100);
        }
    }
    
    private void doOK() {
        if (this.doApply()) {
            this.dispose();
        }
    }
    
    private void doCancel() {
        this.dispose();
    }
    
    private void doRevert() {
        if (this.createdIndex != -1) {
            this.index = -1;
            this.palette.join(this.createdIndex);
            this.createdIndex = -1;
            this.positionInput.setText("");
            final JTextField[] input = this.input;
            for (int length = input.length, i = 0; i < length; ++i) {
                input[i].setText("0.5");
            }
            Color hsbColor;
            if (this.palette.getColorType() == 1) {
                hsbColor = Color.getHSBColor(0.5f, 0.5f, 0.5f);
            }
            else {
                hsbColor = new Color(0.5f, 0.5f, 0.5f);
            }
            this.colorPatch.setBackground(hsbColor);
        }
        else if (this.index != -1) {
            this.palette.setDivisionPointColorComponents(this.index, this.originalColorComponents[0], this.originalColorComponents[1], this.originalColorComponents[2]);
            if (this.index > 0 && this.index < this.palette.getDivisionPointCount() - 1) {
                this.palette.setDivisionPoint(this.index, this.originalPosition);
            }
            for (int j = 0; j < 3; ++j) {
                this.input[j].setText(String.format("%1.4f", this.originalColorComponents[j]));
            }
        }
    }
    
    private boolean doApply() {
        final float[] array = new float[3];
        for (int i = 0; i < 3; ++i) {
            final String trim = this.input[i].getText().trim();
            try {
                array[i] = Float.parseFloat(trim);
            }
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, I18n.tr("colorEditDialog.badFloatValue", trim));
                this.input[i].requestFocus();
                this.input[i].selectAll();
                return false;
            }
        }
        if (this.positionInput != null) {
            try {
                final double double1 = Double.parseDouble(this.positionInput.getText().trim());
                final double n = (this.index == -1) ? 0.0 : this.palette.getDivisionPoint(this.index - 1);
                final double n2 = (this.index == -1) ? 1.0 : this.palette.getDivisionPoint(this.index + 1);
                if (double1 <= n || double1 >= n2) {
                    JOptionPane.showMessageDialog(this, I18n.tr("colorEditDialog.positionOutOfRange", String.format("%1.4f", n), String.format("%1.4f", n2)));
                    return false;
                }
                if (this.index == -1) {
                    this.createdIndex = this.palette.split(double1);
                    if (this.createdIndex == -1) {
                        JOptionPane.showMessageDialog(this, I18n.tr("colorEditDialog.positionAlreadyExistsInPalette", "" + double1));
                    }
                    else {
                        this.index = this.createdIndex;
                    }
                }
                else {
                    this.palette.setDivisionPoint(this.index, double1);
                }
            }
            catch (NumberFormatException ex2) {
                JOptionPane.showMessageDialog(this, I18n.tr("colorEditDialog.badFloatValue", this.positionInput.getText().trim()));
                return false;
            }
        }
        this.palette.setDivisionPointColorComponents(this.index, array[0], array[1], array[2]);
        return true;
    }
    
    private class Listener implements ActionListener, ChangeListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            final Object source = actionEvent.getSource();
            if (source == ColorEdit.this.okButton) {
                ColorEdit.this.doOK();
            }
            else if (source == ColorEdit.this.closeButton) {
                ColorEdit.this.doCancel();
            }
            else if (source == ColorEdit.this.revertButton) {
                ColorEdit.this.doRevert();
            }
            else if (source == ColorEdit.this.applyButton) {
                ColorEdit.this.doApply();
            }
        }
        
        public void stateChanged(final ChangeEvent changeEvent) {
            if (ColorEdit.this.index != -1) {
                ColorEdit.this.colorPatch.setBackground(ColorEdit.this.palette.getDivisionPointColor(ColorEdit.this.index));
            }
        }
    }
}
