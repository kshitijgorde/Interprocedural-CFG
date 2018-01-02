// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.officialencoding;

import com.mindprod.common15.FontFactory;
import com.mindprod.common11.Build;
import com.mindprod.common13.HybridJ;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import com.mindprod.common13.CMPAboutJBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.Charset;
import com.mindprod.common13.JEButton;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import com.mindprod.common13.Common13;
import java.awt.Container;
import com.mindprod.common11.VersionCheck;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JApplet;

public final class OfficialEncoding extends JApplet
{
    private static final int APPLET_HEIGHT = 254;
    private static final int APPLET_WIDTH = 636;
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 1996-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    private static final String RELEASE_DATE = "2008-04-05";
    private static final String TITLE_STRING = "Official Encoding";
    private static final String VERSION_STRING = "1.1";
    private static final Color BACKGROUND_FOR_BODY;
    private static final Color FOREGROUND_FOR_INSTRUCTIONS;
    private static final Color FOREGROUND_FOR_LABEL;
    private static final Color FOREGROUND_FOR_TITLE;
    private static final Color UNBACKGROUND_FOR_EDITABLE;
    private static final Font FONT_FOR_CHARSETS;
    private static final Font FONT_FOR_INSTRUCTIONS;
    private static final Font FONT_FOR_TITLE;
    private static final Font FONT_FOR_TITLE2;
    private static final byte[] byteTestPattern;
    private JButton about;
    private JButton okButton;
    private JLabel aliasLabel;
    private JLabel instructions;
    private JLabel officialLabel;
    private JLabel title;
    private JLabel title2;
    private JTextArea charSetDisplay;
    private JTextField alias;
    private JTextField official;
    
    public void init() {
        if (!VersionCheck.isJavaVersionOK(1, 5, 0, this)) {
            return;
        }
        Common13.setLaf();
        final Container contentPane = this.getContentPane();
        contentPane.setLayout(new GridBagLayout());
        contentPane.setBackground(OfficialEncoding.BACKGROUND_FOR_BODY);
        this.buildComponents();
        this.layoutComponents(contentPane);
        this.hookComponents();
        this.getOfficialName();
    }
    
    private void buildComponents() {
        (this.title = new JLabel("Official Encoding 1.1", 2)).setForeground(OfficialEncoding.FOREGROUND_FOR_TITLE);
        this.title.setFont(OfficialEncoding.FONT_FOR_TITLE);
        (this.title2 = new JLabel("released:2008-04-05 build:9411")).setFont(OfficialEncoding.FONT_FOR_TITLE2);
        this.title2.setForeground(OfficialEncoding.FOREGROUND_FOR_TITLE);
        (this.about = new JEButton("About")).setToolTipText("About Official Encoding 1.1");
        (this.aliasLabel = new JLabel("encoding name")).setForeground(OfficialEncoding.FOREGROUND_FOR_LABEL);
        (this.officialLabel = new JLabel("official encoding name")).setForeground(OfficialEncoding.FOREGROUND_FOR_LABEL);
        (this.alias = new JTextField()).setEditable(true);
        final String defaultEncodingName = Charset.defaultCharset().name();
        this.alias.setText(defaultEncodingName);
        (this.okButton = new JEButton("Convert")).setToolTipText("Convert encoding to official name");
        (this.official = new JTextField()).setBackground(OfficialEncoding.UNBACKGROUND_FOR_EDITABLE);
        this.official.setEditable(false);
        (this.charSetDisplay = new JTextArea(16, 16)).setBackground(OfficialEncoding.UNBACKGROUND_FOR_EDITABLE);
        this.charSetDisplay.setEditable(false);
        this.charSetDisplay.setFont(OfficialEncoding.FONT_FOR_CHARSETS);
        (this.instructions = new JLabel("Enter an encoding name and click OK.  View the corresponding official equivalent.", 0)).setForeground(OfficialEncoding.FOREGROUND_FOR_INSTRUCTIONS);
        this.instructions.setFont(OfficialEncoding.FONT_FOR_INSTRUCTIONS);
    }
    
    private void getOfficialName() {
        try {
            final String canonicalName = Charset.forName(this.alias.getText().trim()).name();
            this.official.setText(canonicalName);
            this.charSetDisplay.setText(new String(OfficialEncoding.byteTestPattern, canonicalName));
        }
        catch (Exception exception) {
            this.official.setText("not supported");
        }
    }
    
    private void hookComponents() {
        this.about.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                new CMPAboutJBox("Official Encoding", "1.1", "Looks up the official name of an encoding.", "", "freeware", "2008-04-05", 2007, "Roedy Green", "OFFICIALENCODING", "1.5");
            }
        });
        this.okButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                OfficialEncoding.this.getOfficialName();
            }
        });
        this.alias.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                OfficialEncoding.this.getOfficialName();
            }
        });
    }
    
    private void layoutComponents(final Container contentPane) {
        contentPane.add(this.title, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, 17, 0, new Insets(10, 10, 5, 5), 0, 0));
        contentPane.add(this.title2, new GridBagConstraints(1, 0, 2, 1, 0.0, 0.0, 17, 0, new Insets(10, 5, 5, 5), 0, 0));
        contentPane.add(this.about, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, 13, 0, new Insets(10, 5, 5, 10), 0, 0));
        contentPane.add(this.aliasLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, 17, 0, new Insets(0, 10, 0, 10), 0, 0));
        contentPane.add(this.officialLabel, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0, 13, 0, new Insets(0, 10, 0, 10), 0, 0));
        contentPane.add(this.alias, new GridBagConstraints(0, 2, 1, 1, 50.0, 0.0, 17, 2, new Insets(5, 10, 5, 10), 0, 0));
        contentPane.add(this.okButton, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, 10, 0, new Insets(5, 10, 5, 10), 0, 0));
        contentPane.add(this.official, new GridBagConstraints(2, 2, 2, 1, 50.0, 0.0, 13, 2, new Insets(5, 10, 5, 10), 0, 0));
        contentPane.add(this.charSetDisplay, new GridBagConstraints(0, 3, 4, 1, 0.0, 100.0, 10, 1, new Insets(0, 10, 5, 10), 0, 0));
        contentPane.add(this.instructions, new GridBagConstraints(0, 4, 4, 1, 0.0, 0.0, 10, 1, new Insets(0, 10, 10, 10), 0, 0));
    }
    
    public static void main(final String[] args) {
        HybridJ.fireup(new OfficialEncoding(), "Official Encoding 1.1", 636, 254);
    }
    
    static {
        BACKGROUND_FOR_BODY = Build.BACKGROUND_FOR_BLENDING;
        FOREGROUND_FOR_INSTRUCTIONS = new Color(3381521);
        FOREGROUND_FOR_LABEL = new Color(176);
        FOREGROUND_FOR_TITLE = new Color(14423100);
        UNBACKGROUND_FOR_EDITABLE = new Color(16316664);
        FONT_FOR_CHARSETS = FontFactory.build("Monospaced", 0, 12);
        FONT_FOR_INSTRUCTIONS = FontFactory.build("Dialog", 0, 12);
        FONT_FOR_TITLE = FontFactory.build("Dialog", 1, 15);
        FONT_FOR_TITLE2 = FontFactory.build("Dialog", 0, 14);
        final int rows = 4;
        final int cols = 64;
        byteTestPattern = new byte[259];
        int b = 0;
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 64; ++j) {
                OfficialEncoding.byteTestPattern[i * 65 + j] = (byte)Math.max(32, b++);
            }
        }
        for (int i = 0; i < 3; ++i) {
            OfficialEncoding.byteTestPattern[i * 65 + 64] = 10;
        }
    }
}
