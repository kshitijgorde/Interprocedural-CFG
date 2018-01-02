// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.borders;

import com.mindprod.common13.HybridJ;
import java.awt.GridBagConstraints;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Insets;
import com.mindprod.common13.CMPAboutJBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.TitledBorder;
import com.mindprod.common11.FontFactory;
import javax.swing.BorderFactory;
import java.awt.Container;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import com.mindprod.common13.Common13;
import com.mindprod.common11.VersionCheck;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JLabel;
import com.mindprod.common13.JEButton;
import java.awt.Choice;
import javax.swing.border.Border;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JApplet;

public final class Borders extends JApplet
{
    private static final int APPLET_HEIGHT = 336;
    private static final int APPLET_WIDTH = 590;
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 2003-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    private static final String RELEASE_DATE = "2008-04-06";
    private static final String TITLE_STRING = "Borders";
    private static final String VERSION_STRING = "1.5";
    private static final String[] hows;
    private static final String[] names;
    private static final Color DARK_GREEN;
    private static final Color darkBlue;
    private static final Color darkKhaki;
    private static final Color darkOliveGreen;
    private static final Color FOREGROUND_FOR_TITLE;
    private static final Color lightSkyBlue;
    private static final Color midnightBlue;
    private static final Color oliveDrab;
    private static final Color paleGoldenrod;
    private static final Color seashell;
    private static final Color slateBlue;
    private static final Font FONT_FOR_TITLE;
    private static final Font FONT_FOR_TITLE2;
    private static Border[] borders;
    private Choice choice;
    private JEButton about;
    private JLabel instructions;
    private JLabel title;
    private JLabel title2;
    private JPanel panel;
    private JTextArea howDone;
    private JTextArea sampleText;
    
    public void destroy() {
        this.about = null;
        this.choice = null;
        this.howDone = null;
        this.instructions = null;
        this.panel = null;
        this.sampleText = null;
        this.title = null;
        this.title2 = null;
    }
    
    public void init() {
        final Container contentPane = this.getContentPane();
        if (!VersionCheck.isJavaVersionOK(1, 3, 0, contentPane)) {
            return;
        }
        Common13.setLaf();
        contentPane.setLayout(new GridBagLayout());
        this.buildComponents(contentPane);
    }
    
    public void start() {
    }
    
    public void stop() {
    }
    
    private static void buildBorderCollection() {
        Borders.borders = new Border[] { BorderFactory.createBevelBorder(1, Borders.paleGoldenrod, Borders.darkKhaki), BorderFactory.createBevelBorder(0, Borders.paleGoldenrod, Borders.darkKhaki), BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(4, 6, 4, 5, Borders.darkOliveGreen), BorderFactory.createMatteBorder(4, 6, 4, 5, Borders.oliveDrab)), BorderFactory.createEmptyBorder(), BorderFactory.createEtchedBorder(1, Borders.lightSkyBlue, Borders.midnightBlue), BorderFactory.createEtchedBorder(0, Borders.lightSkyBlue, Borders.midnightBlue), BorderFactory.createLineBorder(Borders.slateBlue), BorderFactory.createMatteBorder(4, 6, 4, 5, Borders.seashell), BorderFactory.createLoweredBevelBorder(), BorderFactory.createRaisedBevelBorder(), BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(4, 6, 4, 5, Borders.darkBlue), "the title"), new TitledBorder(BorderFactory.createLineBorder(Borders.slateBlue), " the title ", 4, 2, FontFactory.build("Dialog", 2, 16), Borders.oliveDrab) };
    }
    
    private void buildComponents(final Container contentPane) {
        (this.title = new JLabel("Borders 1.5")).setFont(Borders.FONT_FOR_TITLE);
        this.title.setForeground(Borders.FOREGROUND_FOR_TITLE);
        (this.title2 = new JLabel("released:2008-04-06 build:9410")).setFont(Borders.FONT_FOR_TITLE2);
        this.title2.setForeground(Borders.FOREGROUND_FOR_TITLE);
        (this.about = new JEButton("About")).setToolTipText("About Borders 1.5");
        this.about.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                new CMPAboutJBox("Borders", "1.5", "Teaches how to get various border effects", "using Swing.", "freeware", "2008-04-06", 2004, "Roedy Green", "BORDERS", "1.3");
            }
        });
        this.choice = new Choice();
        for (int i = 0, n = Borders.names.length; i < n; ++i) {
            this.choice.add(Borders.names[i]);
        }
        this.panel = new JPanel();
        (this.sampleText = new JTextArea("The quick brown fox \njumped over the lazy dog's back")).setMargin(new Insets(4, 4, 4, 4));
        this.panel.add(this.sampleText);
        (this.howDone = new JTextArea()).setMargin(new Insets(4, 4, 4, 4));
        this.howDone.setEditable(false);
        this.howDone.setMinimumSize(new Dimension(406, 124));
        (this.instructions = new JLabel("Select type of Border for the JPanel.", 0)).setFont(FontFactory.build("Dialog", 0, 12));
        this.instructions.setForeground(Borders.DARK_GREEN);
        this.instructions.setBackground(Color.white);
        this.layoutComponents(contentPane);
        this.choice.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent event) {
                final Object object = event.getSource();
                if (object == Borders.this.choice) {
                    Borders.this.setSelectedBorder();
                }
            }
        });
        this.setSelectedBorder();
    }
    
    private void layoutComponents(final Container contentPane) {
        contentPane.add(this.title, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, 17, 1, new Insets(10, 10, 5, 5), 0, 0));
        contentPane.add(this.title2, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, 17, 1, new Insets(10, 5, 5, 5), 0, 0));
        contentPane.add(this.about, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, 12, 0, new Insets(10, 5, 5, 10), 10, 2));
        contentPane.add(this.choice, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, 10, 1, new Insets(5, 10, 15, 5), 0, 0));
        contentPane.add(this.panel, new GridBagConstraints(1, 1, 2, 1, 0.0, 0.0, 10, 1, new Insets(5, 5, 5, 10), 0, 0));
        contentPane.add(this.howDone, new GridBagConstraints(0, 2, 3, 1, 100.0, 100.0, 10, 1, new Insets(5, 10, 5, 10), 0, 0));
        contentPane.add(this.instructions, new GridBagConstraints(0, 3, 3, 1, 0.0, 0.0, 10, 1, new Insets(5, 10, 10, 10), 0, 0));
    }
    
    private void setSelectedBorder() {
        final int i = this.choice.getSelectedIndex();
        this.panel.setBorder(Borders.borders[i]);
        this.howDone.setText(Borders.hows[i]);
        this.howDone.requestFocus();
    }
    
    public static void main(final String[] args) {
        HybridJ.fireup(new Borders(), "Borders 1.5", 590, 336);
    }
    
    static {
        hows = new String[] { "jPanel.setBorder\n( BorderFactory.createBevelBorder\n  ( BevelBorder.LOWERED,\n    paleGoldenrod, darkKhaki ) );\n", "jPanel.setBorder\n( BorderFactory.createBevelBorder\n  ( BevelBorder.RAISED,\n    paleGoldenrod, darkKhaki ) );\n", "jPanel.setBorder\n( BorderFactory.createCompoundBorder\n  ( BorderFactory.createMatteBorder\n      ( 4, 6, 4, 5, darkOliveGreen ),\n    BorderFactory.createMatteBorder\n      ( 4, 6, 4, 5, oliveDrab ) ) )\n", "jPanel.setBorder\n( BorderFactory.createEmptyBorder() );\n", "jPanel.setBorder\n( BorderFactory.createEtchedBorder\n  ( EtchedBorder.LOWERED, lightSkyBlue, midnightBlue ) );\n", "jPanel.setBorder\n( BorderFactory.createEtchedBorder\n   ( EtchedBorder.RAISED, lightSkyBlue, midnightBlue ) );\n", "jPanel.setBorder\n( BorderFactory.createLineBorder( slateBlue ) );\n", "jPanel.setBorder\n( BorderFactory.createMatteBorder\n  ( 4, 6, 4, 5,  seashell ) );\n", "jPanel.setBorder\n( BorderFactory.createLoweredBevelBorder() );\n", "jPanel.setBorder\n( BorderFactory.createRaisedBevelBorder() );\n", "jPanel.setBorder\n( BorderFactory.createTitledBorder\n  (  BorderFactory.createMatteBorder( 4, 6, 4, 5, darkBlue ),\n      \"the title\" ) );\n", "jPanel.setBorder\n( new TitledBorder( BorderFactory.createLineBorder( slateBlue ),\n  \" the title \",\n  TitledBorder.LEADING, TitledBorder.TOP,\n  FontFactory.build(\"Dialog\", Font.ITALIC, 16 ),\n  oliveDrab ) )\n;" };
        names = new String[] { "BevelBorder LOWERED", "BevelBorder RAISED", "CompoundBorder", "EmptyBorder", "EtchedBorder LOWERED", "EtchedBorder RAISED", "LineBorder", "MatteBorder", "LoweredBevelBorder", "RaisedBevelBorder", "TitledBorder", "Fancy TitledBorder" };
        DARK_GREEN = new Color(32768);
        darkBlue = new Color(139);
        darkKhaki = new Color(12433259);
        darkOliveGreen = new Color(5597999);
        FOREGROUND_FOR_TITLE = new Color(14423100);
        lightSkyBlue = new Color(8900346);
        midnightBlue = new Color(1644912);
        oliveDrab = new Color(7048739);
        paleGoldenrod = new Color(15657130);
        seashell = new Color(16774638);
        slateBlue = new Color(6970061);
        FONT_FOR_TITLE = FontFactory.build("Dialog", 1, 16);
        FONT_FOR_TITLE2 = FontFactory.build("Dialog", 0, 14);
        buildBorderCollection();
    }
}
