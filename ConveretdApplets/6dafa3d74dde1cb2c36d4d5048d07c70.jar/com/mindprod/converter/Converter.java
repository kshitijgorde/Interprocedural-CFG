// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.converter;

import com.mindprod.common13.HybridJ;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import com.mindprod.common15.FontFactory;
import com.mindprod.common13.CMPAboutJBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.mindprod.common13.JEButton;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import com.mindprod.common13.Common13;
import java.awt.Container;
import com.mindprod.common11.VersionCheck;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JApplet;

public final class Converter extends JApplet
{
    public static final boolean DEBUGGING = false;
    private static final int APPLET_HEIGHT = 552;
    private static final int APPLET_WIDTH = 582;
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 1997-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    private static final String RELEASE_DATE = "2009-03-30";
    private static final String TITLE_STRING = "Conversion Amanuensis";
    private static final String VERSION_STRING = "5.3";
    private static final Color BACKGROUND_FOR_APPLET;
    private static final Color FOREGROUND_FOR_INSTRUCTIONS;
    private static final Color FOREGROUND_FOR_LABEL;
    private static final Color FOREGROUND_FOR_RESULT;
    private static final Color FOREGROUND_FOR_TITLE;
    private static final Font FONT_FOR_TITLE;
    private static final Font FONT_FOR_TITLE2;
    private JButton about;
    private JCheckBox autoboxing;
    private JComboBox from;
    private JComboBox to;
    private JLabel equalLabel;
    private JLabel fromLabel;
    private JLabel instructions1;
    private JLabel instructions2;
    private JLabel instructions3;
    private JLabel title;
    private JLabel title2;
    private JLabel toLabel;
    private JTextArea results;
    
    public void destroy() {
        this.about = null;
        this.autoboxing = null;
        this.equalLabel = null;
        this.from = null;
        this.fromLabel = null;
        this.instructions1 = null;
        this.instructions2 = null;
        this.instructions3 = null;
        this.results = null;
        this.title = null;
        this.title2 = null;
        this.to = null;
        this.toLabel = null;
    }
    
    public void init() {
        if (!VersionCheck.isJavaVersionOK(1, 5, 0, this)) {
            return;
        }
        Common13.setLaf();
        final Container contentPane = this.getContentPane();
        contentPane.setBackground(Converter.BACKGROUND_FOR_APPLET);
        contentPane.setLayout(new GridBagLayout());
        this.createComponents();
        this.hookComponents();
        this.layoutGridBag(contentPane);
        this.validate();
        this.setVisible(true);
    }
    
    private void createComponents() {
        (this.about = new JEButton("About")).setToolTipText("About Conversion Amanuensis 5.3");
        this.about.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                new CMPAboutJBox("Conversion Amanuensis", "5.3", "Generates Java source to interconvert basic types,", "e.g. double <=> String", "freeware", "2009-03-30", 1997, "Roedy Green", "CONVERTER", "1.5");
            }
        });
        this.autoboxing = new JCheckBox("autoboxing", false);
        (this.title = new JLabel("Conversion Amanuensis 5.3")).setFont(Converter.FONT_FOR_TITLE);
        this.title.setForeground(Converter.FOREGROUND_FOR_TITLE);
        (this.title2 = new JLabel("released:2009-03-30 build:9410")).setFont(Converter.FONT_FOR_TITLE2);
        this.title2.setForeground(Converter.FOREGROUND_FOR_TITLE);
        (this.to = new JComboBox((E[])Type.values())).setEditable(false);
        (this.toLabel = new JLabel("to", 0)).setFont(FontFactory.build("Dialog", 1, 15));
        this.toLabel.setForeground(Converter.FOREGROUND_FOR_LABEL);
        (this.from = new JComboBox((E[])Type.values())).setEditable(false);
        (this.fromLabel = new JLabel("from", 0)).setFont(FontFactory.build("Dialog", 1, 15));
        this.fromLabel.setForeground(Converter.FOREGROUND_FOR_LABEL);
        (this.equalLabel = new JLabel("=", 0)).setFont(FontFactory.build("Dialog", 1, 15));
        this.equalLabel.setForeground(Converter.FOREGROUND_FOR_LABEL);
        (this.results = new JTextArea("", 0, 0)).setEditable(false);
        this.results.setFont(FontFactory.build("Dialog", 0, 15));
        this.results.setForeground(Converter.FOREGROUND_FOR_RESULT);
        (this.instructions1 = new JLabel("Select the \"to\" target type on the left and the  \"from\" source type on the right.", 2)).setForeground(Converter.FOREGROUND_FOR_INSTRUCTIONS);
        (this.instructions2 = new JLabel("Then check if you want JDK 1.5+ autoboxing. Then copy/paste one of the", 2)).setForeground(Converter.FOREGROUND_FOR_INSTRUCTIONS);
        (this.instructions3 = new JLabel("suggested conversion techniques into your own code.", 2)).setForeground(Converter.FOREGROUND_FOR_INSTRUCTIONS);
        this.from.setSelectedItem(Type.FLOAT);
        this.to.setSelectedItem(Type.WRAPPEDDOUBLE);
        this.results.setText(Type.how(Type.FLOAT, Type.WRAPPEDDOUBLE, false));
    }
    
    private void hookComponents() {
        final ItemListener theListener = new ItemListener() {
            public void itemStateChanged(final ItemEvent event) {
                Converter.this.results.setText(Type.how((Type)Converter.this.from.getSelectedItem(), (Type)Converter.this.to.getSelectedItem(), Converter.this.autoboxing.isSelected()));
            }
        };
        this.from.addItemListener(theListener);
        this.to.addItemListener(theListener);
        this.autoboxing.addItemListener(theListener);
    }
    
    private void layoutGridBag(final Container contentPane) {
        contentPane.add(this.title, new GridBagConstraints(0, 0, 3, 1, 0.0, 0.0, 17, 0, new Insets(10, 10, 0, 10), 0, 0));
        contentPane.add(this.title2, new GridBagConstraints(0, 1, 3, 1, 0.0, 0.0, 17, 0, new Insets(0, 10, 5, 10), 0, 0));
        contentPane.add(this.about, new GridBagConstraints(3, 0, 1, 2, 0.0, 0.0, 13, 0, new Insets(10, 10, 5, 10), 10, 2));
        contentPane.add(this.to, new GridBagConstraints(0, 2, 2, 1, 0.5, 0.0, 10, 0, new Insets(0, 10, 5, 0), 150, 0));
        contentPane.add(this.from, new GridBagConstraints(3, 2, 1, 1, 0.5, 0.0, 10, 1, new Insets(0, 0, 5, 10), 150, 0));
        contentPane.add(this.autoboxing, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, 18, 0, new Insets(0, 10, 5, 0), 0, 0));
        contentPane.add(this.toLabel, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, 10, 0, new Insets(0, 0, 5, 0), 0, 0));
        contentPane.add(this.equalLabel, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0, 10, 0, new Insets(0, 0, 5, 10), 0, 0));
        contentPane.add(this.fromLabel, new GridBagConstraints(3, 3, 1, 1, 0.0, 0.0, 10, 0, new Insets(0, 0, 5, 0), 0, 0));
        contentPane.add(this.results, new GridBagConstraints(0, 4, 4, 1, 1.0, 1.0, 10, 1, new Insets(0, 10, 5, 10), 0, 0));
        contentPane.add(this.instructions1, new GridBagConstraints(0, 5, 4, 1, 0.0, 0.0, 17, 0, new Insets(0, 10, 2, 10), 0, 0));
        contentPane.add(this.instructions2, new GridBagConstraints(0, 6, 4, 1, 0.0, 0.0, 17, 0, new Insets(0, 10, 2, 10), 0, 0));
        contentPane.add(this.instructions3, new GridBagConstraints(0, 7, 4, 1, 0.0, 0.0, 17, 0, new Insets(0, 10, 10, 10), 0, 0));
    }
    
    public static void main(final String[] args) {
        HybridJ.fireup(new Converter(), "Conversion Amanuensis 5.3", 582, 552);
    }
    
    static {
        BACKGROUND_FOR_APPLET = new Color(16773360);
        FOREGROUND_FOR_INSTRUCTIONS = new Color(32768);
        FOREGROUND_FOR_LABEL = new Color(176);
        FOREGROUND_FOR_RESULT = Color.BLACK;
        FOREGROUND_FOR_TITLE = new Color(14423100);
        FONT_FOR_TITLE = FontFactory.build("Dialog", 1, 16);
        FONT_FOR_TITLE2 = FontFactory.build("Dialog", 0, 14);
    }
}
