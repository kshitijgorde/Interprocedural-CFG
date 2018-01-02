// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.masker;

import java.util.Collection;
import java.util.Arrays;
import java.awt.GraphicsEnvironment;
import com.mindprod.common13.HybridJ;
import java.awt.Component;
import java.awt.GridBagConstraints;
import com.mindprod.common13.CMPAboutJBox;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import com.mindprod.common15.Laf;
import javax.swing.JMenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.mindprod.common13.JEButton;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.Insets;
import com.mindprod.common15.FontFactory;
import java.awt.Container;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import com.mindprod.common11.VersionCheck;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.util.HashSet;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JApplet;

public final class Masker extends JApplet
{
    private static final int APPLET_HEIGHT = 275;
    private static final int APPLET_WIDTH = 636;
    private static final int pngFontSize = 18;
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 2003-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    private static final String RELEASE_DATE = "2010-03-23";
    private static final String TITLE_STRING = "CMP Email Masker";
    private static final String VERSION_STRING = "1.9";
    private static final String[] pngFonts;
    private static final Color BACKGROUND_FOR_APPLET;
    private static final Color BACKGROUND_FOR_INPUT;
    private static final Color FOREGROUND_FOR_DEFAULT;
    private static final Color FOREGROUND_FOR_INPUT;
    private static final Color FOREGROUND_FOR_LABEL;
    private static final Color FOREGROUND_FOR_TITLE;
    private static final Color transparent;
    private static final Font FONT_FOR_EDITABLE_FIELDS;
    private static final Font FONT_FOR_TITLE;
    private static final Font FONT_FOR_TITLE2;
    private static final HashSet<String> allFontFamilies;
    private JButton save;
    private JLabel emailLabel;
    private JLabel instructions;
    private JLabel title;
    private JLabel title2;
    private JTextField email;
    private ShroudedImage png;
    
    public void init() {
        final Container contentPane = this.getContentPane();
        if (!VersionCheck.isJavaVersionOK(1, 5, 1, contentPane)) {
            return;
        }
        this.buildMenu();
        contentPane.setLayout(new GridBagLayout());
        contentPane.setBackground(Masker.BACKGROUND_FOR_APPLET);
        this.buildComponents();
        this.layoutFields(contentPane);
    }
    
    private static Font bestFont(final String[] preferredFontNames, final int fontStyle, final int fontSize) {
        for (final String preferredFontName : preferredFontNames) {
            if (Masker.allFontFamilies.contains(preferredFontName.toLowerCase())) {
                return FontFactory.build(preferredFontName, fontStyle, fontSize);
            }
        }
        return FontFactory.build("Dialog", fontStyle, fontSize);
    }
    
    private void buildComponents() {
        (this.title = new JLabel("CMP Email Masker 1.9")).setForeground(Masker.FOREGROUND_FOR_TITLE);
        this.title.setFont(Masker.FONT_FOR_TITLE);
        (this.title2 = new JLabel("released:2010-03-23 build:9411")).setFont(Masker.FONT_FOR_TITLE2);
        this.title2.setForeground(Masker.FOREGROUND_FOR_TITLE);
        (this.emailLabel = new JLabel("email:")).setForeground(Masker.FOREGROUND_FOR_LABEL);
        (this.email = new JTextField("roedyg@mindprod.com", 40)).setEditable(true);
        this.email.setMargin(new Insets(2, 2, 2, 2));
        this.email.setFont(Masker.FONT_FOR_EDITABLE_FIELDS);
        this.email.setBackground(Masker.BACKGROUND_FOR_INPUT);
        this.email.setForeground(Masker.FOREGROUND_FOR_INPUT);
        this.email.addCaretListener(new CaretListener() {
            public void caretUpdate(final CaretEvent e) {
                Masker.this.png.setText(Masker.this.email.getText().trim());
                Masker.this.validate();
            }
        });
        (this.png = new ShroudedImage(this.email.getText().trim())).setForeground(Masker.FOREGROUND_FOR_DEFAULT);
        this.png.setBackground(Masker.transparent);
        this.png.setFont(bestFont(Masker.pngFonts, 0, 18));
        (this.save = new JEButton("Save")).setToolTipText("Save Email image as a PNG file");
        this.save.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                final String text = Masker.this.email.getText().trim();
                Masker.this.png.setText(text);
                int place = text.indexOf(64);
                if (place < 0) {
                    place = text.length();
                }
                final String saveName = text.substring(0, place).toLowerCase() + ".png";
                Masker.this.png.saveImage(saveName);
            }
        });
        (this.instructions = new JLabel("Fill in your email address and hit Save to create a png image of your email address.")).setForeground(Masker.FOREGROUND_FOR_LABEL);
    }
    
    private void buildMenu() {
        final JMenuBar menubar = new JMenuBar();
        this.setJMenuBar(menubar);
        menubar.add(Laf.buildLookAndFeelMenu());
        final JMenu menuHelp = new JMenu("Help");
        menubar.add(menuHelp);
        final JMenuItem aboutItem = new JMenuItem("About");
        menuHelp.add(aboutItem);
        aboutItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                new CMPAboutJBox("CMP Email Masker", "1.9", "Creates png files (like gifs but smaller)", "to hide your email address from spam harvesters.", "freeware", "2010-03-23", 2004, "Roedy Green", "MASKER", "1.5");
            }
        });
    }
    
    private void layoutFields(final Container contentPane) {
        contentPane.add(this.title, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, 17, 0, new Insets(5, 2, 0, 2), 0, 0));
        contentPane.add(this.title2, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, 17, 0, new Insets(0, 2, 0, 2), 0, 0));
        contentPane.add(this.emailLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, 13, 0, new Insets(7, 5, 0, 5), 0, 0));
        contentPane.add(this.email, new GridBagConstraints(1, 2, 2, 1, 0.0, 0.0, 17, 2, new Insets(7, 2, 0, 5), 0, 0));
        contentPane.add(this.png, new GridBagConstraints(1, 3, 2, 1, 0.0, 0.0, 17, 0, new Insets(7, 2, 0, 5), 0, 0));
        contentPane.add(this.save, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0, 13, 0, new Insets(5, 2, 0, 5), 0, 0));
        contentPane.add(this.instructions, new GridBagConstraints(1, 5, 2, 1, 0.0, 0.0, 17, 0, new Insets(5, 2, 5, 5), 0, 0));
    }
    
    public static void main(final String[] args) {
        HybridJ.fireup(new Masker(), "CMP Email Masker 1.9", 636, 275);
    }
    
    static {
        pngFonts = new String[] { "Tiresias PCfont Z", "Tiresias PCfont", "TiresiasScreenfont", "Verdana", "Lucida Console", "Lucida Sans" };
        BACKGROUND_FOR_APPLET = new Color(16774399);
        BACKGROUND_FOR_INPUT = new Color(16777215);
        FOREGROUND_FOR_DEFAULT = new Color(7829367);
        FOREGROUND_FOR_INPUT = new Color(2003199);
        FOREGROUND_FOR_LABEL = new Color(176);
        FOREGROUND_FOR_TITLE = new Color(14423100);
        transparent = new Color(16777215, true);
        FONT_FOR_EDITABLE_FIELDS = FontFactory.build("Dialog", 1, 17);
        FONT_FOR_TITLE = FontFactory.build("Dialog", 1, 16);
        FONT_FOR_TITLE2 = FontFactory.build("Dialog", 0, 14);
        final String[] names = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        for (int i = 0, n = names.length; i < n; ++i) {
            names[i] = names[i].toLowerCase();
        }
        allFontFamilies = new HashSet<String>(Arrays.asList(names));
    }
}
