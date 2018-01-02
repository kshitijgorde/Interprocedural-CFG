// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.fontshower;

import com.mindprod.common15.FontFactory;
import com.mindprod.common13.HybridJ;
import java.awt.GridBagConstraints;
import javax.swing.JColorChooser;
import java.util.Collection;
import java.util.Arrays;
import java.util.Vector;
import com.mindprod.common11.ST;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import com.mindprod.fontshowerawt.FontSamples;
import java.awt.Component;
import com.mindprod.common11.Misc;
import com.mindprod.common13.CMPAboutJBox;
import com.mindprod.common13.JEButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.SpinnerModel;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.LineMetrics;
import java.awt.font.FontRenderContext;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Container;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import com.mindprod.common13.Common13;
import com.mindprod.common11.VersionCheck;
import javax.swing.SpinnerNumberModel;
import javax.swing.JToggleButton;
import javax.swing.JSpinner;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;
import java.text.DecimalFormat;
import java.awt.Color;
import javax.swing.JApplet;

public final class FontShower extends JApplet
{
    private static final int APPLET_HEIGHT = 660;
    private static final int APPLET_WIDTH = 720;
    private static final int defaultSize = 18;
    private static final String allFontsWording = "«all fonts summary»";
    private static final String defaultFontFamily = "Dialog";
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 2005-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    private static final String RELEASE_DATE = "2009-09-30";
    private static final String TITLE_STRING = "Swing Font Shower";
    private static final String VERSION_STRING = "2.9";
    private static final Color BACKGROUND_FOR_BODY;
    private static final Color BACKGROUND_FOR_DEFAULT;
    private static final Color FOREGROUND_FOR_DEFAULT;
    private static final Color FOREGROUND_FOR_TITLE;
    private static final DecimalFormat fontSizeFormatter;
    private static final Font FONT_FOR_TITLE;
    private static final Font FONT_FOR_TITLE2;
    private AntiAliasedJTextArea allDisplayArea;
    private AntiAliasedJTextArea displayArea;
    private JButton about;
    private JButton chooseBackgroundColor;
    private JButton chooseForegroundColor;
    private JComboBox fontChoices;
    private JLabel title;
    private JLabel title2;
    private JScrollPane allScroller;
    private JScrollPane scroller;
    private JSpinner sizeSpinner;
    private JToggleButton toggleAntiAlias;
    private JToggleButton toggleBold;
    private JToggleButton toggleItalic;
    private SpinnerNumberModel sizeSpinnerModel;
    
    public void destroy() {
        this.about = null;
        this.allDisplayArea = null;
        this.allScroller = null;
        this.chooseBackgroundColor = null;
        this.chooseForegroundColor = null;
        this.displayArea = null;
        this.fontChoices = null;
        this.scroller = null;
        this.sizeSpinner = null;
        this.sizeSpinnerModel = null;
        this.title = null;
        this.title2 = null;
        this.toggleAntiAlias = null;
        this.toggleBold = null;
        this.toggleItalic = null;
    }
    
    public void init() {
        if (!VersionCheck.isJavaVersionOK(1, 5, 0, this.getContentPane())) {
            return;
        }
        Common13.setLaf();
        final Container contentPane = this.getContentPane();
        contentPane.setLayout(new GridBagLayout());
        contentPane.setBackground(FontShower.BACKGROUND_FOR_BODY);
        this.buildComponents();
        this.layoutComponents(contentPane);
    }
    
    private static float getTrueFontHeight(final String FontName) {
        final Font f = new Font(FontName, 0, 10);
        final BufferedImage dummybi = new BufferedImage(200, 200, 7);
        final Graphics2D dummyg2d = dummybi.createGraphics();
        final FontRenderContext fr = dummyg2d.getFontRenderContext();
        final LineMetrics lm = f.getLineMetrics("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghiklmnoprstuvwxyz01234567809()[]{}", fr);
        return lm.getDescent() + lm.getAscent();
    }
    
    private void buildComponents() {
        (this.title = new JLabel("Swing Font Shower 2.9")).setFont(FontShower.FONT_FOR_TITLE);
        this.title.setForeground(FontShower.FOREGROUND_FOR_TITLE);
        (this.title2 = new JLabel("released:2009-09-30 build:9411")).setFont(FontShower.FONT_FOR_TITLE2);
        this.title2.setForeground(FontShower.FOREGROUND_FOR_TITLE);
        this.buildFontChoices();
        this.fontChoices.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                final String newFontFamily = (String)FontShower.this.fontChoices.getSelectedItem();
                if (newFontFamily.equals("«all fonts summary»")) {
                    if (!FontShower.this.allScroller.isVisible()) {
                        FontShower.this.scroller.setVisible(false);
                        FontShower.this.allScroller.setVisible(true);
                        FontShower.this.validate();
                    }
                }
                else {
                    if (!FontShower.this.scroller.isVisible()) {
                        FontShower.this.allScroller.setVisible(false);
                        FontShower.this.scroller.setVisible(true);
                        FontShower.this.validate();
                    }
                    final Font oldFont = FontShower.this.displayArea.getFont();
                    FontShower.this.displayArea.setFont(new Font(newFontFamily, oldFont.getStyle(), oldFont.getSize()));
                }
            }
        });
        this.sizeSpinner = new JSpinner();
        final Dimension d = new Dimension(40, 25);
        this.sizeSpinner.setMinimumSize(d);
        this.sizeSpinner.setPreferredSize(d);
        this.sizeSpinner.setMaximumSize(d);
        this.sizeSpinnerModel = new SpinnerNumberModel(18, 8, 300, 1);
        this.sizeSpinner.setModel(this.sizeSpinnerModel);
        this.sizeSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(final ChangeEvent e) {
                final float fontSize = FontShower.this.sizeSpinnerModel.getNumber().floatValue();
                FontShower.this.displayArea.setFont(FontShower.this.displayArea.getFont().deriveFont(fontSize));
                FontShower.this.allDisplayArea.setFont(FontShower.this.allDisplayArea.getFont().deriveFont(fontSize));
            }
        });
        (this.toggleBold = new JToggleButton("Bold", false)).setMnemonic(66);
        this.toggleBold.setToolTipText("Bold");
        final ActionListener styleChangeListener = new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                FontShower.this.changeStyle();
            }
        };
        this.toggleBold.addActionListener(styleChangeListener);
        (this.toggleItalic = new JToggleButton("Italics", false)).setMnemonic(73);
        this.toggleItalic.setToolTipText("Italic");
        this.toggleItalic.addActionListener(styleChangeListener);
        (this.toggleAntiAlias = new JToggleButton("Anti-alias", false)).setMnemonic(65);
        this.toggleAntiAlias.setToolTipText("Anti-alias font smoothing");
        this.toggleAntiAlias.addActionListener(styleChangeListener);
        (this.chooseBackgroundColor = new JEButton("Background Colour", false)).setMnemonic(67);
        this.chooseBackgroundColor.setToolTipText("Change Background Colour");
        this.chooseBackgroundColor.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                final Color newBackground = FontShower.this.getColorChoice(false);
                FontShower.this.displayArea.setBackground(newBackground);
                FontShower.this.allDisplayArea.setBackground(newBackground);
            }
        });
        (this.chooseForegroundColor = new JEButton("Text Colour", false)).setMnemonic(84);
        this.chooseForegroundColor.setToolTipText("Change Text Colour");
        this.chooseForegroundColor.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                final Color newForeground = FontShower.this.getColorChoice(true);
                FontShower.this.displayArea.setForeground(newForeground);
                FontShower.this.allDisplayArea.setForeground(newForeground);
            }
        });
        (this.about = new JEButton("About")).setToolTipText("About Swing Font Shower 2.9");
        this.about.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                new CMPAboutJBox(Misc.getParentFrame(FontShower.this), "Swing Font Shower", "2.9", "Shows you what fonts are available in Swing and what they", "look like, in various styles, sizes and colours.", "freeware", "2009-09-30", 2004, "Fahd Shariff, Roedy Green, Canadian Mind Products (polishing)", "FONTSHOWER", "1.5");
            }
        });
        (this.displayArea = new AntiAliasedJTextArea(FontSamples.getFontSampleText())).setEditable(false);
        this.displayArea.setBackground(FontShower.BACKGROUND_FOR_DEFAULT);
        this.displayArea.setForeground(FontShower.FOREGROUND_FOR_DEFAULT);
        this.displayArea.setFont(new Font("Dialog", 0, 18));
        this.displayArea.setAntiAlias(false);
        this.displayArea.setMargin(new Insets(3, 3, 3, 3));
        this.scroller = new JScrollPane(this.displayArea);
        final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        final String[] fontNames = ge.getAvailableFontFamilyNames();
        final StringBuffer sb = new StringBuffer(fontNames.length * 25 + 100);
        sb.append("Fonts Available to Java under ");
        sb.append(System.getProperty("os.name", "unknown OS"));
        sb.append("\n");
        sb.append(ST.rightPad("Font Name", 30, false));
        sb.append("True height of a 10 point font");
        sb.append("\n");
        for (final String f : fontNames) {
            sb.append(ST.rightPad(f, 30, false));
            sb.append(ST.leftPad(FontShower.fontSizeFormatter.format(getTrueFontHeight(f)), 4, false));
            sb.append("\n");
        }
        final String allFontsList = sb.toString();
        System.out.println(allFontsList);
        (this.allDisplayArea = new AntiAliasedJTextArea(sb.toString())).setEditable(false);
        this.allDisplayArea.setBackground(FontShower.BACKGROUND_FOR_DEFAULT);
        this.allDisplayArea.setForeground(FontShower.FOREGROUND_FOR_DEFAULT);
        this.allDisplayArea.setFont(new Font("Monospaced", 0, 18));
        this.allDisplayArea.setAntiAlias(true);
        this.allDisplayArea.setMargin(new Insets(3, 3, 3, 3));
        (this.allScroller = new JScrollPane(this.allDisplayArea)).setVisible(false);
    }
    
    private void buildFontChoices() {
        final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        final String[] fontNames = ge.getAvailableFontFamilyNames();
        final Vector<String> v = new Vector<String>(fontNames.length + 1);
        v.add("«all fonts summary»");
        v.addAll(Arrays.asList(fontNames));
        (this.fontChoices = new JComboBox((Vector<E>)v)).setMaximumSize(this.fontChoices.getPreferredSize());
        this.fontChoices.setEditable(true);
        this.fontChoices.setSelectedItem("Dialog");
    }
    
    private void changeStyle() {
        int style = 0;
        if (this.toggleBold.isSelected()) {
            style |= 0x1;
        }
        if (this.toggleItalic.isSelected()) {
            style |= 0x2;
        }
        this.displayArea.setFont(this.displayArea.getFont().deriveFont(style));
        this.displayArea.setAntiAlias(this.toggleAntiAlias.isSelected());
        this.allDisplayArea.setFont(this.allDisplayArea.getFont().deriveFont(style));
        this.allDisplayArea.setAntiAlias(this.toggleAntiAlias.isSelected());
    }
    
    private Color getColorChoice(final boolean foreground) {
        return JColorChooser.showDialog(this, "Choose " + (foreground ? "Text" : "Background") + " Color", foreground ? this.displayArea.getForeground() : this.displayArea.getBackground());
    }
    
    private void layoutComponents(final Container contentPane) {
        contentPane.add(this.title, new GridBagConstraints(0, 0, 4, 1, 0.0, 0.0, 17, 0, new Insets(10, 10, 5, 10), 0, 0));
        contentPane.add(this.title2, new GridBagConstraints(4, 0, 2, 1, 0.0, 0.0, 17, 0, new Insets(10, 10, 5, 10), 0, 0));
        contentPane.add(this.about, new GridBagConstraints(6, 0, 1, 1, 0.0, 0.0, 13, 0, new Insets(10, 10, 5, 10), 0, 0));
        contentPane.add(this.fontChoices, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, 10, 1, new Insets(0, 10, 5, 5), 0, 0));
        contentPane.add(this.sizeSpinner, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, 10, 1, new Insets(0, 0, 5, 5), 5, 0));
        contentPane.add(this.toggleBold, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, 10, 1, new Insets(0, 0, 5, 5), 0, 0));
        contentPane.add(this.toggleItalic, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0, 10, 1, new Insets(0, 0, 5, 5), 0, 0));
        contentPane.add(this.toggleAntiAlias, new GridBagConstraints(4, 1, 1, 1, 0.0, 0.0, 10, 1, new Insets(0, 0, 5, 5), 0, 0));
        contentPane.add(this.chooseBackgroundColor, new GridBagConstraints(5, 1, 1, 1, 0.0, 0.0, 10, 1, new Insets(0, 0, 5, 5), 0, 0));
        contentPane.add(this.chooseForegroundColor, new GridBagConstraints(6, 1, 1, 1, 0.0, 0.0, 10, 1, new Insets(0, 0, 5, 10), 0, 0));
        contentPane.add(this.scroller, new GridBagConstraints(0, 2, 7, 1, 0.0, 100.0, 10, 1, new Insets(0, 10, 10, 10), 0, 0));
        contentPane.add(this.allScroller, new GridBagConstraints(0, 2, 7, 1, 0.0, 100.0, 10, 1, new Insets(0, 10, 10, 10), 0, 0));
    }
    
    public static void main(final String[] args) {
        HybridJ.fireup(new FontShower(), "Swing Font Shower 2.9", 720, 660);
    }
    
    static {
        BACKGROUND_FOR_BODY = new Color(16574719);
        BACKGROUND_FOR_DEFAULT = new Color(16187382);
        FOREGROUND_FOR_DEFAULT = new Color(16384);
        FOREGROUND_FOR_TITLE = new Color(14423100);
        fontSizeFormatter = new DecimalFormat("#0.9");
        FONT_FOR_TITLE = FontFactory.build("Dialog", 1, 16);
        FONT_FOR_TITLE2 = FontFactory.build("Dialog", 0, 14);
    }
}
