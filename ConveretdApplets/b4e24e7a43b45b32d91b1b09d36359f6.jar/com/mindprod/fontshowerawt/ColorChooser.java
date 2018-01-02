// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.fontshowerawt;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.mindprod.common11.FontFactory;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.TextArea;
import java.awt.Label;
import java.awt.Color;
import java.awt.Choice;
import java.awt.Button;
import java.awt.Dialog;

public final class ColorChooser extends Dialog
{
    private Button cancel;
    private Button ok;
    private Choice blueDecimalChoice;
    private Choice blueHexChoice;
    private Choice greenDecimalChoice;
    private Choice greenHexChoice;
    private Choice redDecimalChoice;
    private Choice redHexChoice;
    private Color originalColor;
    private Color selectedColor;
    private Label blueLabel;
    private Label decimalLabel;
    private Label greenLabel;
    private Label hexLabel;
    private Label redLabel;
    private TextArea colorSwatch;
    private TextField decimalValue;
    private TextField hexValue;
    private boolean choosingForeground;
    
    public ColorChooser(final Frame owner) {
        super(owner, "Choose Color", true);
        this.decimalLabel = new Label("Decimal", 0);
        this.hexLabel = new Label("Hex", 2);
        this.redDecimalChoice = new Choice();
        this.greenDecimalChoice = new Choice();
        this.blueDecimalChoice = new Choice();
        for (int i = 0; i <= 255; ++i) {
            final String decimal = Integer.toString(i);
            this.redDecimalChoice.addItem(decimal);
            this.greenDecimalChoice.addItem(decimal);
            this.blueDecimalChoice.addItem(decimal);
        }
        final ItemListener theDecimalListener = new ItemListener() {
            public void itemStateChanged(final ItemEvent e) {
                final int red = ColorChooser.this.redDecimalChoice.getSelectedIndex();
                final int green = ColorChooser.this.greenDecimalChoice.getSelectedIndex();
                final int blue = ColorChooser.this.blueDecimalChoice.getSelectedIndex();
                ColorChooser.this.selectedColor = new Color(red, green, blue);
                ColorChooser.this.displayColorSwatch();
            }
        };
        this.redDecimalChoice.addItemListener(theDecimalListener);
        this.greenDecimalChoice.addItemListener(theDecimalListener);
        this.blueDecimalChoice.addItemListener(theDecimalListener);
        (this.redLabel = new Label("Red", 1)).setForeground(Color.white);
        this.redLabel.setBackground(Color.red);
        (this.greenLabel = new Label("Green", 1)).setForeground(Color.white);
        this.greenLabel.setBackground(new Color(39168));
        (this.blueLabel = new Label("Blue", 1)).setForeground(Color.white);
        this.blueLabel.setBackground(Color.blue);
        this.redHexChoice = new Choice();
        this.greenHexChoice = new Choice();
        this.blueHexChoice = new Choice();
        for (int j = 0; j <= 255; ++j) {
            final String hex = Integer.toHexString(j + 256).substring(1);
            this.redHexChoice.addItem(hex);
            this.greenHexChoice.addItem(hex);
            this.blueHexChoice.addItem(hex);
        }
        final ItemListener theHexListener = new ItemListener() {
            public void itemStateChanged(final ItemEvent e) {
                final int red = ColorChooser.this.redHexChoice.getSelectedIndex();
                final int green = ColorChooser.this.greenHexChoice.getSelectedIndex();
                final int blue = ColorChooser.this.blueHexChoice.getSelectedIndex();
                ColorChooser.this.selectedColor = new Color(red, green, blue);
                ColorChooser.this.displayColorSwatch();
            }
        };
        this.redHexChoice.addItemListener(theHexListener);
        this.greenHexChoice.addItemListener(theHexListener);
        this.blueHexChoice.addItemListener(theHexListener);
        (this.decimalValue = new TextField("16777215")).setFont(FontFactory.build("Dialog", 0, 15));
        (this.hexValue = new TextField("#ffffff")).setFont(FontFactory.build("Dialog", 0, 15));
        (this.colorSwatch = new TextArea("The quick brown fox jumps\nover the lazy dog's back.", 2, 25, 3)).setEditable(false);
        (this.ok = new Button("OK")).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                ColorChooser.this.dispose();
            }
        });
        (this.cancel = new Button("Cancel")).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                ColorChooser.this.selectedColor = ColorChooser.this.originalColor;
                ColorChooser.this.dispose();
            }
        });
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent e) {
                ColorChooser.this.selectedColor = ColorChooser.this.originalColor;
                ColorChooser.this.dispose();
            }
        });
        this.setLayout(new GridBagLayout());
        this.add(this.decimalLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, 17, 0, new Insets(10, 10, 5, 5), 0, 0));
        this.add(this.hexLabel, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, 13, 0, new Insets(10, 5, 5, 10), 0, 0));
        this.add(this.redDecimalChoice, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, 17, 0, new Insets(5, 10, 5, 5), 0, 0));
        this.add(this.redLabel, new GridBagConstraints(1, 1, 2, 1, 0.0, 0.0, 10, 2, new Insets(5, 20, 5, 5), 0, 0));
        this.add(this.redHexChoice, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0, 13, 0, new Insets(5, 5, 5, 10), 0, 0));
        this.add(this.greenDecimalChoice, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, 17, 0, new Insets(5, 10, 5, 5), 0, 0));
        this.add(this.greenLabel, new GridBagConstraints(1, 2, 2, 1, 0.0, 0.0, 10, 2, new Insets(5, 20, 5, 5), 0, 0));
        this.add(this.greenHexChoice, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0, 13, 0, new Insets(5, 5, 5, 10), 0, 0));
        this.add(this.blueDecimalChoice, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, 17, 0, new Insets(5, 10, 5, 5), 0, 0));
        this.add(this.blueLabel, new GridBagConstraints(1, 3, 2, 1, 0.0, 0.0, 10, 2, new Insets(5, 20, 5, 5), 0, 0));
        this.add(this.blueHexChoice, new GridBagConstraints(3, 3, 1, 1, 0.0, 0.0, 13, 0, new Insets(5, 5, 5, 10), 0, 0));
        this.add(this.decimalValue, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, 17, 0, new Insets(5, 10, 5, 5), 0, 0));
        this.add(this.hexValue, new GridBagConstraints(3, 4, 1, 1, 0.0, 0.0, 13, 0, new Insets(5, 5, 5, 10), 0, 0));
        this.add(this.colorSwatch, new GridBagConstraints(0, 5, 4, 1, 50.0, 50.0, 10, 1, new Insets(5, 10, 5, 10), 0, 0));
        this.add(this.ok, new GridBagConstraints(0, 6, 2, 1, 0.0, 0.0, 17, 0, new Insets(5, 10, 10, 5), 0, 0));
        this.add(this.cancel, new GridBagConstraints(2, 6, 2, 1, 0.0, 0.0, 13, 0, new Insets(5, 5, 10, 10), 0, 0));
        this.pack();
    }
    
    public Color query(final boolean chooseForeground, final Color originalBackground, final Color originalForeground) {
        this.choosingForeground = chooseForeground;
        if (this.choosingForeground) {
            this.selectedColor = originalForeground;
            this.originalColor = originalForeground;
            this.setTitle("Choose Foreground Color");
        }
        else {
            this.selectedColor = originalBackground;
            this.originalColor = originalBackground;
            this.setTitle("Choose Background Color");
        }
        this.colorSwatch.setForeground(originalForeground);
        this.colorSwatch.setBackground(originalBackground);
        this.displayColorSwatch();
        this.setVisible(true);
        return this.selectedColor;
    }
    
    private void displayColorSwatch() {
        this.redDecimalChoice.select(this.selectedColor.getRed());
        this.greenDecimalChoice.select(this.selectedColor.getGreen());
        this.blueDecimalChoice.select(this.selectedColor.getBlue());
        this.redHexChoice.select(this.selectedColor.getRed());
        this.greenHexChoice.select(this.selectedColor.getGreen());
        this.blueHexChoice.select(this.selectedColor.getBlue());
        final int rgb = this.selectedColor.getRGB() & 0xFFFFFF;
        this.decimalValue.setText(Integer.toString(rgb));
        this.hexValue.setText('#' + Integer.toHexString(rgb + 16777216).substring(1));
        if (this.choosingForeground) {
            this.colorSwatch.setForeground(this.selectedColor);
        }
        else {
            this.colorSwatch.setBackground(this.selectedColor);
        }
        this.validate();
        this.colorSwatch.repaint();
    }
}
