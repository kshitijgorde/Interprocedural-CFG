// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.inwords;

import com.mindprod.common13.HybridJ;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.InputStream;
import com.mindprod.common11.Misc;
import java.io.IOException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.SpinnerModel;
import java.awt.Dimension;
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
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JApplet;

public final class InWords extends JApplet
{
    private static final int APPLET_HEIGHT = 276;
    private static final int APPLET_WIDTH = 525;
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 1999-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    private static final String RELEASE_DATE = "2009-05-01";
    private static final String TITLE_STRING = "InWords Amanuensis";
    private static final String VERSION_STRING = "4.6";
    private static final Color BACKGROUND_FOR_BODY;
    private static final Color FOREGROUND_FOR_DISPLAY;
    private static final Color FOREGROUND_FOR_INSTRUCTIONS;
    private static final Color FOREGROUND_FOR_LABEL;
    private static final Color FOREGROUND_FOR_TITLE;
    private static final Font FONT_FOR_TITLE;
    private static final Font FONT_FOR_TITLE2;
    private static ToWords[] languageDelegate;
    private static String[] languageNames;
    private JButton about;
    private JComboBox language;
    private JLabel instructions;
    private JLabel languageLabel;
    private JLabel theNumberLabel;
    private JLabel title;
    private JLabel title2;
    private JSpinner spinner;
    private JTextArea display;
    private SpinnerNumberModel spinnerNumberModel;
    
    public void destroy() {
        this.about = null;
        this.display = null;
        this.instructions = null;
        this.language = null;
        this.languageLabel = null;
        this.spinner = null;
        this.spinnerNumberModel = null;
        this.theNumberLabel = null;
        this.title2 = null;
        this.title = null;
    }
    
    public void init() {
        if (!VersionCheck.isJavaVersionOK(1, 5, 0, this)) {
            return;
        }
        Common13.setLaf();
        final Container contentPane = this.getContentPane();
        contentPane.setBackground(InWords.BACKGROUND_FOR_BODY);
        contentPane.setLayout(new GridBagLayout());
        (this.about = new JEButton("About")).setToolTipText("About InWords Amanuensis 4.6");
        this.about.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                new CMPAboutJBox("InWords Amanuensis", "4.6", "Spells out numbers in words in many languages.", "Teaches you to count in many languages.", "freeware", "2009-05-01", 1998, "Roedy Green", "INWORDS", "1.5");
            }
        });
        (this.title = new JLabel("InWords Amanuensis 4.6")).setFont(InWords.FONT_FOR_TITLE);
        this.title.setForeground(InWords.FOREGROUND_FOR_TITLE);
        (this.title2 = new JLabel("released:2009-05-01 build:9411")).setFont(InWords.FONT_FOR_TITLE2);
        this.title2.setForeground(InWords.FOREGROUND_FOR_TITLE);
        findLanguages();
        (this.language = new JComboBox((E[])InWords.languageNames)).setEditable(false);
        this.language.setFont(FontFactory.build("Dialog", 1, 12));
        this.language.setSelectedItem("English (North American)");
        this.language.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent event) {
                if (event.getSource() == InWords.this.language) {
                    InWords.this.refresh();
                }
            }
        });
        (this.languageLabel = new JLabel("language")).setFont(FontFactory.build("Dialog", 1, 15));
        this.languageLabel.setForeground(InWords.FOREGROUND_FOR_LABEL);
        (this.spinner = new JSpinner()).setFont(FontFactory.build("Dialog", 1, 15));
        this.spinner.setEnabled(true);
        final Dimension d = new Dimension(200, 30);
        this.spinner.setMinimumSize(d);
        this.spinner.setPreferredSize(d);
        this.spinner.setMaximumSize(d);
        this.spinnerNumberModel = new SpinnerNumberModel(12345.0, -9.223372036854776E18, 9.223372036854776E18, 1.0);
        this.spinner.setModel(this.spinnerNumberModel);
        this.spinner.addChangeListener(new ChangeListener() {
            public void stateChanged(final ChangeEvent e) {
                InWords.this.refresh();
            }
        });
        (this.theNumberLabel = new JLabel("number")).setFont(FontFactory.build("Dialog", 1, 15));
        this.theNumberLabel.setForeground(InWords.FOREGROUND_FOR_LABEL);
        (this.display = new JTextArea()).setLineWrap(true);
        this.display.setWrapStyleWord(true);
        this.display.setEditable(false);
        this.display.setFont(FontFactory.build("Unicode", 0, 15));
        this.display.setForeground(InWords.FOREGROUND_FOR_DISPLAY);
        (this.instructions = new JLabel("Select language, enter number (with or without commas), and hit enter.")).setForeground(InWords.FOREGROUND_FOR_INSTRUCTIONS);
        this.refresh();
        this.layoutComponents(contentPane);
        this.validate();
        this.setVisible(true);
    }
    
    private static void findLanguages() {
        String[][] result = null;
        try {
            final InputStream fis = InWords.class.getResourceAsStream("inwords.properties");
            if (fis == null) {
                throw new IOException("missing resource");
            }
            result = Misc.loadProperties(fis);
        }
        catch (IOException oops) {
            System.out.println(oops + " Problem accessing inwords.properties file.");
            System.exit(1);
        }
        final String[] languageClass = result[0];
        InWords.languageNames = result[1];
        InWords.languageDelegate = new ToWords[languageClass.length];
        for (int i = 0; i < languageClass.length; ++i) {
            try {
                InWords.languageDelegate[i] = (ToWords)Class.forName(languageClass[i]).newInstance();
            }
            catch (Exception oops2) {
                System.out.println(oops2 + " Bug in inwords.properties or class file for " + InWords.languageNames[i]);
                System.exit(1);
            }
        }
    }
    
    private void layoutComponents(final Container contentPane) {
        contentPane.add(this.title, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, 17, 0, new Insets(10, 10, 5, 5), 0, 0));
        contentPane.add(this.title2, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, 17, 0, new Insets(0, 10, 5, 5), 0, 0));
        contentPane.add(this.about, new GridBagConstraints(1, 0, 1, 2, 0.0, 0.0, 13, 0, new Insets(10, 10, 5, 10), 10, 2));
        contentPane.add(this.language, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, 17, 2, new Insets(0, 10, 5, 10), 0, 0));
        contentPane.add(this.spinner, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, 13, 2, new Insets(0, 10, 5, 10), 40, 0));
        contentPane.add(this.languageLabel, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, 10, 0, new Insets(0, 10, 5, 10), 0, 0));
        contentPane.add(this.theNumberLabel, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, 10, 0, new Insets(0, 10, 5, 10), 0, 0));
        contentPane.add(this.display, new GridBagConstraints(0, 4, 2, 1, 0.0, 1.0, 10, 1, new Insets(0, 10, 5, 10), 0, 0));
        contentPane.add(this.instructions, new GridBagConstraints(0, 5, 2, 1, 0.0, 0.0, 10, 0, new Insets(0, 10, 10, 10), 0, 0));
    }
    
    private void refresh() {
        final long setting = this.spinnerNumberModel.getNumber().longValue();
        this.display.setText(InWords.languageDelegate[this.language.getSelectedIndex()].toWords(setting));
    }
    
    public static void main(final String[] args) {
        HybridJ.fireup(new InWords(), "InWords Amanuensis 4.6", 525, 276);
    }
    
    static {
        BACKGROUND_FOR_BODY = Color.WHITE;
        FOREGROUND_FOR_DISPLAY = Color.BLACK;
        FOREGROUND_FOR_INSTRUCTIONS = new Color(32768);
        FOREGROUND_FOR_LABEL = new Color(176);
        FOREGROUND_FOR_TITLE = new Color(14423100);
        FONT_FOR_TITLE = FontFactory.build("Dialog", 1, 16);
        FONT_FOR_TITLE2 = FontFactory.build("Dialog", 0, 14);
    }
}
