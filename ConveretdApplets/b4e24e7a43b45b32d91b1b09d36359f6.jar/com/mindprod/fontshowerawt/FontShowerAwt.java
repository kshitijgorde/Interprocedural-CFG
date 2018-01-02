// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.fontshowerawt;

import com.mindprod.common11.Hybrid;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridBagLayout;
import com.mindprod.common11.CMPAboutBox;
import com.mindprod.common11.FontFactory;
import java.awt.Component;
import com.mindprod.common11.Misc;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.CheckboxGroup;
import java.awt.GraphicsEnvironment;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Container;
import com.mindprod.common11.VersionCheck;
import java.awt.TextArea;
import java.awt.ScrollPane;
import java.awt.Panel;
import java.awt.Label;
import java.awt.Choice;
import java.awt.Checkbox;
import java.awt.Button;
import java.awt.Font;
import java.awt.Color;
import java.applet.Applet;

public final class FontShowerAwt extends Applet
{
    private static final int APPLET_HEIGHT = 504;
    private static final int APPLET_WIDTH = 1008;
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 2005-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    private static final String RELEASE_DATE = "2009-01-23";
    private static final String TITLE_STRING = "AWT Font Shower";
    private static final String VERSION_STRING = "2.9";
    private static final Color BACKGROUND_FOR_BUTTON;
    private static final Color FOREGROUND_FOR_TITLE;
    private static final Font FONT_FOR_TITLE;
    private static Color currentBackground;
    private static String currentFontName;
    private static int currentFontSize;
    private static Color currentForeground;
    private AntiAliastedFontedTextArea displayCanvas;
    private Button about;
    private Button chooseBackgroundColor;
    private Button chooseForegroundColor;
    private Checkbox toggleAntialias;
    private Checkbox toggleBold;
    private Checkbox toggleItalics;
    private Checkbox withCanvas;
    private Checkbox withTextArea;
    private Choice allFontChoices;
    private Choice logicalFontChoices;
    private Choice sizeChoices;
    private ColorChooser colorChooser;
    private Label ptLabel;
    private Label title;
    private Panel toolBar;
    private ScrollPane canvasScroller;
    private TextArea displayTextArea;
    private boolean usingCanvas;
    
    public FontShowerAwt() {
        this.usingCanvas = false;
    }
    
    public void destroy() {
        this.about = null;
        this.allFontChoices = null;
        this.canvasScroller = null;
        this.chooseBackgroundColor = null;
        this.chooseForegroundColor = null;
        this.colorChooser = null;
        this.displayCanvas = null;
        this.displayTextArea = null;
        this.logicalFontChoices = null;
        this.ptLabel = null;
        this.sizeChoices = null;
        this.title = null;
        this.toggleAntialias = null;
        this.toggleBold = null;
        this.toggleItalics = null;
        this.toolBar = null;
        this.withCanvas = null;
        this.withTextArea = null;
    }
    
    public void init() {
        if (!VersionCheck.isJavaVersionOK(1, 2, 0, this)) {
            return;
        }
        this.setLayout(new BorderLayout());
        this.toolBar = new Panel();
        final ItemListener itemListener = new ItemListener() {
            public void itemStateChanged(final ItemEvent e) {
                FontShowerAwt.this.displayFontSample();
            }
        };
        (this.title = new Label("AWT Font Shower 2.9 build 9411")).setFont(FontShowerAwt.FONT_FOR_TITLE);
        this.title.setForeground(FontShowerAwt.FOREGROUND_FOR_TITLE);
        final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        final String[] possibleFontNames = ge.getAvailableFontFamilyNames();
        this.allFontChoices = new Choice();
        for (int i = 0; i < possibleFontNames.length; ++i) {
            this.allFontChoices.add(possibleFontNames[i]);
        }
        this.allFontChoices.select(FontShowerAwt.currentFontName);
        this.allFontChoices.addItemListener(itemListener);
        (this.logicalFontChoices = new Choice()).add("Dialog");
        this.logicalFontChoices.add("DialogInput");
        this.logicalFontChoices.add("Monospaced");
        this.logicalFontChoices.add("SansSerif");
        this.logicalFontChoices.add("Serif");
        this.logicalFontChoices.select("Dialog");
        this.logicalFontChoices.addItemListener(itemListener);
        this.sizeChoices = new Choice();
        for (int i = 8; i <= 30; ++i) {
            this.sizeChoices.add(Integer.toString(i));
        }
        for (int i = 35; i <= 70; i += 5) {
            this.sizeChoices.add(Integer.toString(i));
        }
        this.sizeChoices.select(Integer.toString(FontShowerAwt.currentFontSize));
        this.sizeChoices.addItemListener(itemListener);
        this.ptLabel = new Label("pts", 0);
        (this.toggleBold = new Checkbox("Bold", false)).addItemListener(itemListener);
        (this.toggleItalics = new Checkbox("Italics", false)).addItemListener(itemListener);
        final CheckboxGroup renderMode = new CheckboxGroup();
        (this.withCanvas = new Checkbox("Canvas", renderMode, false)).addItemListener(itemListener);
        (this.withTextArea = new Checkbox("TextArea", renderMode, true)).addItemListener(itemListener);
        (this.toggleAntialias = new Checkbox("Anti-alias", false)).addItemListener(itemListener);
        (this.chooseBackgroundColor = new Button("Background Colour")).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                FontShowerAwt.currentBackground = FontShowerAwt.this.colorChooser.query(false, FontShowerAwt.currentBackground, FontShowerAwt.currentForeground);
                FontShowerAwt.this.displayFontSample();
            }
        });
        this.colorChooser = new ColorChooser(Misc.getParentFrame(this));
        (this.chooseForegroundColor = new Button("Text Colour")).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                FontShowerAwt.currentForeground = FontShowerAwt.this.colorChooser.query(true, FontShowerAwt.currentBackground, FontShowerAwt.currentForeground);
                FontShowerAwt.this.displayFontSample();
            }
        });
        (this.about = new Button("About")).setForeground(Color.white);
        this.about.setBackground(FontShowerAwt.BACKGROUND_FOR_BUTTON);
        this.about.setFont(FontFactory.build("Dialog", 1, 16));
        this.about.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                new CMPAboutBox("AWT Font Shower", "2.9", "Shows you what fonts are available in AWT and what", "they look like in various styles, sizes and colours.", "freeware", "2009-01-23", 2005, "Roedy Green", "FONTSHOWERAWT", "1.2");
            }
        });
        this.displayTextArea = new TextArea(FontSamples.getFontSampleText());
        (this.displayCanvas = new AntiAliastedFontedTextArea()).setTextLines(FontSamples.getFontSampleLines());
        (this.canvasScroller = new ScrollPane(0)).add(this.displayCanvas);
        this.layoutToolbar();
        this.flipFromCanvas();
        this.flipToTextArea();
        this.displayFontSample();
    }
    
    private void displayFontSample() {
        final boolean wasUsingCanvas = this.usingCanvas;
        this.usingCanvas = this.withCanvas.getState();
        if (this.usingCanvas != wasUsingCanvas) {
            if (this.usingCanvas) {
                this.flipFromTextArea();
                this.flipToCanvas();
            }
            else {
                this.flipFromCanvas();
                this.flipToTextArea();
            }
        }
        int style = 0;
        if (this.toggleBold.getState()) {
            style |= 0x1;
        }
        if (this.toggleItalics.getState()) {
            style |= 0x2;
        }
        FontShowerAwt.currentFontSize = Integer.parseInt(this.sizeChoices.getSelectedItem());
        if (this.usingCanvas) {
            FontShowerAwt.currentFontName = this.allFontChoices.getSelectedItem();
            final Font font = new Font(FontShowerAwt.currentFontName, style, FontShowerAwt.currentFontSize);
            this.displayCanvas.setFont(font);
            this.displayCanvas.setBackground(FontShowerAwt.currentBackground);
            this.displayCanvas.setForeground(FontShowerAwt.currentForeground);
            this.displayCanvas.setAntialias(this.toggleAntialias.getState());
            this.canvasScroller.getVAdjustable().setUnitIncrement(FontShowerAwt.currentFontSize + 5);
        }
        else {
            FontShowerAwt.currentFontName = this.logicalFontChoices.getSelectedItem();
            final Font font = new Font(FontShowerAwt.currentFontName, style, FontShowerAwt.currentFontSize);
            this.displayTextArea.setFont(font);
            this.displayTextArea.setBackground(FontShowerAwt.currentBackground);
            this.displayTextArea.setForeground(FontShowerAwt.currentForeground);
        }
        this.validate();
    }
    
    private void flipFromCanvas() {
        this.allFontChoices.setVisible(false);
        this.remove(this.canvasScroller);
    }
    
    private void flipFromTextArea() {
        this.logicalFontChoices.setVisible(false);
        this.remove(this.displayTextArea);
    }
    
    private void flipToCanvas() {
        this.add(this.canvasScroller, "Center");
        this.allFontChoices.setVisible(true);
        this.toggleAntialias.setEnabled(true);
        this.allFontChoices.select(this.logicalFontChoices.getSelectedItem());
    }
    
    private void flipToTextArea() {
        this.add(this.displayTextArea, "Center");
        this.logicalFontChoices.setVisible(true);
        this.toggleAntialias.setEnabled(false);
        this.logicalFontChoices.select(this.allFontChoices.getSelectedItem());
    }
    
    private void layoutToolbar() {
        this.toolBar.setLayout(new GridBagLayout());
        this.toolBar.add(this.title, new GridBagConstraints(0, 0, 1, 1, 10.0, 0.0, 17, 2, new Insets(5, 10, 5, 5), 0, 0));
        this.toolBar.add(this.logicalFontChoices, new GridBagConstraints(0, 1, 1, 1, 10.0, 0.0, 10, 2, new Insets(5, 10, 5, 5), 0, 0));
        this.toolBar.add(this.allFontChoices, new GridBagConstraints(0, 0, 1, 2, 10.0, 0.0, 10, 2, new Insets(5, 10, 5, 5), 0, 0));
        this.toolBar.add(this.withCanvas, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, 17, 0, new Insets(5, 5, 0, 5), 0, 0));
        this.toolBar.add(this.withTextArea, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, 17, 0, new Insets(5, 5, 5, 5), 0, 0));
        this.toolBar.add(this.toggleAntialias, new GridBagConstraints(2, 0, 2, 1, 0.0, 0.0, 17, 0, new Insets(5, 10, 0, 5), 0, 0));
        this.toolBar.add(this.sizeChoices, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, 17, 0, new Insets(5, 10, 5, 5), 0, 0));
        this.toolBar.add(this.ptLabel, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0, 17, 0, new Insets(5, 0, 5, 5), 0, 0));
        this.toolBar.add(this.toggleBold, new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0, 17, 0, new Insets(5, 5, 0, 5), 0, 0));
        this.toolBar.add(this.toggleItalics, new GridBagConstraints(4, 1, 1, 1, 0.0, 0.0, 17, 0, new Insets(5, 5, 5, 5), 0, 0));
        this.toolBar.add(this.chooseBackgroundColor, new GridBagConstraints(5, 0, 1, 1, 0.0, 0.0, 17, 0, new Insets(5, 5, 0, 5), 0, 0));
        this.toolBar.add(this.chooseForegroundColor, new GridBagConstraints(5, 1, 1, 1, 0.0, 0.0, 17, 0, new Insets(5, 5, 5, 5), 0, 0));
        this.toolBar.add(this.about, new GridBagConstraints(6, 0, 1, 2, 0.0, 0.0, 12, 0, new Insets(5, 5, 5, 10), 10, 2));
        this.add(this.toolBar, "North");
    }
    
    public static void main(final String[] args) {
        Hybrid.fireup(new FontShowerAwt(), "AWT Font Shower 2.9", 1008, 504);
    }
    
    static {
        BACKGROUND_FOR_BUTTON = new Color(32768);
        FOREGROUND_FOR_TITLE = new Color(14423100);
        FONT_FOR_TITLE = FontFactory.build("Dialog", 1, 14);
        FontShowerAwt.currentBackground = new Color(16187382);
        FontShowerAwt.currentFontName = "Dialog";
        FontShowerAwt.currentFontSize = 18;
        FontShowerAwt.currentForeground = new Color(16384);
    }
}
