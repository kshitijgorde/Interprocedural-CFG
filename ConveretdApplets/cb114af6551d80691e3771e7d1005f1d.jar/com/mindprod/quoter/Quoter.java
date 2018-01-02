// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.quoter;

import com.mindprod.common13.HybridJ;
import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import com.mindprod.common11.ClipboardPoker;
import com.mindprod.common15.FontFactory;
import java.awt.Component;
import com.mindprod.common13.CMPAboutJBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.mindprod.common13.JEButton;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.Clipboard;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import com.mindprod.common13.Common13;
import java.awt.Container;
import com.mindprod.common11.VersionCheck;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.datatransfer.ClipboardOwner;
import javax.swing.JApplet;

public final class Quoter extends JApplet implements ClipboardOwner
{
    static final boolean DEBUGGING = false;
    private static final int APPLET_HEIGHT = 528;
    private static final int APPLET_WIDTH = 636;
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 1998-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    private static final String RELEASE_DATE = "2011-01-03";
    private static final String TITLE_STRING = "Quoter Amanuensis";
    private static final String VERSION_STRING = "4.7";
    private static final Color BACKGROUND_FOR_BOX;
    private static final Color FOREGROUND_FOR_INSTRUCTIONS;
    private static final Color FOREGROUND_FOR_TEXT;
    private static final Color FOREGROUND_FOR_TITLE;
    private static final Font FONT_FOR_TITLE;
    private static final Font FONT_FOR_TITLE2;
    private JButton about;
    private JButton clearButton;
    private JButton convertButton;
    private JButton pasteButton;
    private JComboBox targetChoice;
    private JComboBox trimChoice;
    private JLabel instructions;
    private JLabel title;
    private JLabel title2;
    private JTextArea cookedTextArea;
    private JTextArea rawTextArea;
    private TargetOption targetOption;
    private TextProcessor postprocessor;
    private TextProcessor preprocessor;
    private Translator translator;
    private TrimOption trimOption;
    private final boolean asApplet;
    
    public Quoter() {
        this.asApplet = true;
    }
    
    public void destroy() {
        this.about = null;
        this.clearButton = null;
        this.convertButton = null;
        this.cookedTextArea = null;
        this.instructions = null;
        this.pasteButton = null;
        this.postprocessor = null;
        this.preprocessor = null;
        this.rawTextArea = null;
        this.targetChoice = null;
        this.targetOption = null;
        this.title2 = null;
        this.title = null;
        this.translator = null;
        this.trimChoice = null;
        this.trimOption = null;
    }
    
    public void init() {
        if (!VersionCheck.isJavaVersionOK(1, 5, 0, this)) {
            return;
        }
        Common13.setLaf();
        final Container contentPane = this.getContentPane();
        contentPane.setLayout(new GridBagLayout());
        contentPane.setBackground(Quoter.BACKGROUND_FOR_BOX);
        this.targetOption = TargetOption.TO_HTML;
        this.trimOption = TrimOption.TRIM;
        this.buildComponents();
        this.getSelections();
        this.layoutComponents(contentPane);
        this.hookListeners();
        this.validate();
        this.setVisible(true);
    }
    
    public final void lostOwnership(final Clipboard clipboard, final Transferable contents) {
    }
    
    private Quoter(final boolean asApplet) {
        this.asApplet = asApplet;
    }
    
    private void buildComponents() {
        (this.about = new JEButton("About")).setToolTipText("About Quoter Amanuensis 4.7");
        this.about.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                new CMPAboutJBox("Quoter Amanuensis", "4.7", "Does various text manipulations.", "In application mode, the clipboard is automatic.", "freeware", "2011-01-03", 1998, "Roedy Green", "QUOTER", "1.5");
            }
        });
        (this.title = new JLabel("Quoter Amanuensis 4.7")).setFont(Quoter.FONT_FOR_TITLE);
        this.title.setForeground(Quoter.FOREGROUND_FOR_TITLE);
        (this.title2 = new JLabel("released:2011-01-03 build:9410")).setFont(Quoter.FONT_FOR_TITLE2);
        this.title2.setForeground(Quoter.FOREGROUND_FOR_TITLE);
        (this.rawTextArea = new JTextArea("", 3, 50)).setEditable(true);
        this.rawTextArea.setForeground(Quoter.FOREGROUND_FOR_TEXT);
        this.rawTextArea.setBackground(Quoter.BACKGROUND_FOR_BOX);
        (this.cookedTextArea = new JTextArea("", 3, 50)).setEditable(false);
        this.cookedTextArea.setForeground(Quoter.FOREGROUND_FOR_TEXT);
        this.cookedTextArea.setBackground(Quoter.BACKGROUND_FOR_BOX);
        this.decideFonts();
        this.targetChoice = new JComboBox();
        for (final TargetOption possibility : TargetOption.values()) {
            this.targetChoice.addItem(possibility);
        }
        this.targetChoice.setSelectedItem(this.targetOption);
        this.trimChoice = new JComboBox();
        for (final TrimOption possibility2 : TrimOption.values()) {
            this.trimChoice.addItem(possibility2);
        }
        this.trimChoice.setSelectedItem(this.trimOption);
        this.add(this.trimChoice);
        (this.clearButton = new JEButton("Clear")).setToolTipText("Clear everything");
        (this.convertButton = new JEButton("Convert")).setToolTipText("Convert text");
        if (!this.asApplet) {
            (this.pasteButton = new JEButton("Convert Clipboard")).setToolTipText("Paste and convert clipboard");
        }
        (this.instructions = new JLabel("", 0)).setFont(FontFactory.build("Dialog", 0, 10));
        this.instructions.setBackground(Quoter.BACKGROUND_FOR_BOX);
        this.instructions.setForeground(Quoter.FOREGROUND_FOR_INSTRUCTIONS);
    }
    
    private void convertAction() {
        this.getSelections();
        String cooked;
        final String raw = cooked = this.rawTextArea.getText();
        if (this.preprocessor != null) {
            cooked = this.preprocessor.process(cooked);
        }
        if (this.translator != null) {
            cooked = this.translator.process(cooked);
        }
        if (this.postprocessor != null) {
            cooked = this.postprocessor.process(cooked);
        }
        this.cookedTextArea.setText(cooked);
        if (!this.asApplet) {
            ClipboardPoker.setClip(cooked, this);
        }
    }
    
    private void decideFonts() {
        final Font font = this.targetOption.getFont();
        this.rawTextArea.setFont(font);
        this.cookedTextArea.setFont(font);
    }
    
    private void decideInstructions() {
        this.instructions.setText(this.asApplet ? this.targetOption.getAppletInstructions() : this.targetOption.getApplicationInstructions());
    }
    
    private void decidePostprocessor() {
        this.postprocessor = this.targetOption.getProcessor();
    }
    
    private void decidePreprocessor() {
        this.preprocessor = this.trimOption.getProcessor();
    }
    
    private void decideTranslator() {
        this.translator = this.targetOption.getTranslator();
    }
    
    private void getSelections() {
        this.targetOption = (TargetOption)this.targetChoice.getSelectedItem();
        this.trimOption = (TrimOption)this.trimChoice.getSelectedItem();
        this.decideInstructions();
        this.decidePreprocessor();
        this.decideTranslator();
        this.decidePostprocessor();
        this.decideFonts();
    }
    
    private void hookListeners() {
        final TheListener theListener = new TheListener();
        this.clearButton.addActionListener(theListener);
        this.convertButton.addActionListener(theListener);
        if (!this.asApplet) {
            this.pasteButton.addActionListener(theListener);
        }
        this.targetChoice.addActionListener(theListener);
        this.trimChoice.addActionListener(theListener);
    }
    
    private void layoutComponents(final Container contentPane) {
        contentPane.add(this.title, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0, 17, 0, new Insets(10, 10, 5, 10), 0, 0));
        contentPane.add(this.title2, new GridBagConstraints(1, 0, 2, 1, 0.0, 0.0, 17, 0, new Insets(10, 5, 5, 5), 0, 0));
        contentPane.add(this.about, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, 13, 0, new Insets(10, 5, 5, 10), 10, 2));
        contentPane.add(new JScrollPane(this.rawTextArea, 20, 30), new GridBagConstraints(0, 1, 3, 1, 0.5, 1.0, 10, 1, new Insets(0, 10, 10, 10), 0, 0));
        contentPane.add(new JScrollPane(this.cookedTextArea, 20, 30), new GridBagConstraints(0, 2, 3, 1, 0.5, 1.0, 10, 1, new Insets(0, 10, 10, 10), 0, 0));
        contentPane.add(this.targetChoice, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, 17, 1, new Insets(0, 10, 5, 5), 0, 0));
        contentPane.add(this.trimChoice, new GridBagConstraints(1, 3, 2, 1, 0.0, 0.0, 17, 1, new Insets(0, 5, 5, 10), 0, 0));
        contentPane.add(this.clearButton, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, 17, 0, new Insets(5, 10, 5, 5), 0, 0));
        contentPane.add(this.convertButton, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0, 13, 0, new Insets(5, 5, 5, 10), 0, 0));
        if (!this.asApplet) {
            contentPane.add(this.pasteButton, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, 10, 0, new Insets(5, 5, 5, 5), 0, 0));
        }
        contentPane.add(this.instructions, new GridBagConstraints(0, 5, 3, 1, 0.0, 0.0, 10, 0, new Insets(0, 10, 10, 10), 0, 0));
    }
    
    public static void main(final String[] args) {
        HybridJ.fireup(new Quoter(false), "Quoter Amanuensis 4.7", 636, 528);
    }
    
    static {
        BACKGROUND_FOR_BOX = Color.WHITE;
        FOREGROUND_FOR_INSTRUCTIONS = new Color(32768);
        FOREGROUND_FOR_TEXT = Color.BLACK;
        FOREGROUND_FOR_TITLE = new Color(14423100);
        FONT_FOR_TITLE = FontFactory.build("Dialog", 1, 16);
        FONT_FOR_TITLE2 = FontFactory.build("Dialog", 0, 14);
    }
    
    final class TheListener implements ActionListener
    {
        public void actionPerformed(final ActionEvent event) {
            final Object source = event.getSource();
            if (source == Quoter.this.convertButton || source == Quoter.this.targetChoice || source == Quoter.this.trimChoice) {
                Quoter.this.convertAction();
            }
            else if (source == Quoter.this.pasteButton) {
                Quoter.this.rawTextArea.setText(ClipboardPoker.getClip(Quoter.this));
                Quoter.this.convertAction();
            }
            else if (source == Quoter.this.clearButton) {
                Quoter.this.rawTextArea.setText(null);
                Quoter.this.cookedTextArea.setText(null);
            }
        }
    }
}
