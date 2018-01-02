// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.isbn;

import com.mindprod.common13.HybridJ;
import java.awt.GridBagConstraints;
import java.awt.Component;
import com.mindprod.common11.ClipboardPoker;
import java.awt.Insets;
import com.mindprod.common15.FontFactory;
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
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.datatransfer.ClipboardOwner;
import javax.swing.JApplet;

public final class ISBN extends JApplet implements ClipboardOwner
{
    private static final int APPLET_HEIGHT = 381;
    private static final int APPLET_WIDTH = 379;
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 1998-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    private static final String RELEASE_DATE = "2009-03-16";
    private static final String TITLE_STRING = "CMP ISBN Validator";
    private static final String VERSION_STRING = "2.3";
    private static final Color BACKGROUND_FOR_APPLET;
    private static final Color BACKGROUND_FOR_EDITABLE;
    private static final Color FOREGROUND_FOR_INSTRUCTIONS;
    private static final Color FOREGROUND_FOR_LABEL;
    private static final Color FOREGROUND_FOR_TITLE;
    private static final Color UNBACKGROUND_FOR_EDITABLE;
    private static final Font FONT_FOR_LABELS;
    private static final Font FONT_FOR_TITLE;
    private static final Font FONT_FOR_TITLE2;
    private JButton about;
    private JButton clearButton;
    private JButton pasteButton;
    private JButton tidyButton;
    private JLabel isbn10Lab;
    private JLabel isbn13Lab;
    private JLabel isbnRawLab;
    private JLabel title;
    private JLabel title2;
    private JTextArea instructions;
    private JTextField cookedISBN10;
    private JTextField cookedISBN13;
    private JTextField rawISBN;
    private final boolean asApplication;
    
    public ISBN() {
        this.asApplication = false;
    }
    
    public ISBN(final boolean asApplication) {
        this.asApplication = asApplication;
    }
    
    public void destroy() {
        this.about = null;
        this.clearButton = null;
        this.cookedISBN10 = null;
        this.cookedISBN13 = null;
        this.instructions = null;
        this.isbn10Lab = null;
        this.isbn13Lab = null;
        this.isbnRawLab = null;
        this.pasteButton = null;
        this.rawISBN = null;
        this.tidyButton = null;
        this.title2 = null;
        this.title = null;
    }
    
    public void init() {
        if (!VersionCheck.isJavaVersionOK(1, 5, 0, this)) {
            return;
        }
        Common13.setLaf();
        final Container contentPane = this.getContentPane();
        contentPane.setBackground(ISBN.BACKGROUND_FOR_APPLET);
        contentPane.setLayout(new GridBagLayout());
        this.buildComponents();
        this.setDefaultInstructions();
        this.layoutFields(contentPane);
        this.hookClearButton();
        this.hookTidyButton();
        this.hookPasteButton();
        this.validate();
    }
    
    public void lostOwnership(final Clipboard clipboard, final Transferable contents) {
    }
    
    private void buildComponents() {
        (this.title = new JLabel("CMP ISBN Validator 2.3")).setFont(ISBN.FONT_FOR_TITLE);
        this.title.setForeground(ISBN.FOREGROUND_FOR_TITLE);
        (this.title2 = new JLabel("released:2009-03-16 build:9411")).setFont(ISBN.FONT_FOR_TITLE2);
        this.title2.setForeground(ISBN.FOREGROUND_FOR_TITLE);
        (this.about = new JEButton("About")).setToolTipText("About CMP ISBN Validator 2.3");
        this.about.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                new CMPAboutJBox("CMP ISBN Validator", "2.3", "Convert, Tidy and Validate the ISBN-10 and ISBN-13", "numbers used to identify books", "freeware", "2009-03-16", 1999, "Roedy Green", "ISBN", "1.5");
            }
        });
        (this.isbnRawLab = new JLabel("raw")).setFont(ISBN.FONT_FOR_LABELS);
        this.isbnRawLab.setForeground(ISBN.FOREGROUND_FOR_LABEL);
        (this.rawISBN = new JTextField("", 19)).setEditable(true);
        this.rawISBN.setFont(FontFactory.build("Dialog", 0, 15));
        this.rawISBN.setBackground(ISBN.BACKGROUND_FOR_EDITABLE);
        (this.isbn10Lab = new JLabel("ISBN-10")).setFont(ISBN.FONT_FOR_LABELS);
        this.isbn10Lab.setForeground(ISBN.FOREGROUND_FOR_LABEL);
        (this.cookedISBN10 = new JTextField("", 15)).setEditable(false);
        this.cookedISBN10.setBackground(ISBN.UNBACKGROUND_FOR_EDITABLE);
        this.cookedISBN10.setFont(FontFactory.build("Dialog", 0, 15));
        (this.isbn13Lab = new JLabel("ISBN-13")).setFont(ISBN.FONT_FOR_LABELS);
        this.isbn13Lab.setForeground(ISBN.FOREGROUND_FOR_LABEL);
        (this.cookedISBN13 = new JTextField("", 19)).setEditable(false);
        this.cookedISBN13.setBackground(ISBN.UNBACKGROUND_FOR_EDITABLE);
        this.cookedISBN13.setFont(FontFactory.build("Dialog", 0, 15));
        (this.clearButton = new JEButton("Clear")).setToolTipText("Clear everything");
        if (this.asApplication) {
            (this.pasteButton = new JEButton("Paste")).setToolTipText("Paste ISBN, convert and copy resulting tidied ISBN-13 back to the clipboard.");
        }
        (this.tidyButton = new JEButton("<html><div align=\"center\">Tidy<br />Convert<br />Validate</center></html>")).setToolTipText("Convert, Tidy and validate the raw ISBN");
        (this.instructions = new JTextArea("")).setEditable(false);
        this.instructions.setMargin(new Insets(2, 2, 2, 2));
        this.instructions.setBackground(ISBN.UNBACKGROUND_FOR_EDITABLE);
        this.instructions.setForeground(ISBN.FOREGROUND_FOR_INSTRUCTIONS);
    }
    
    private void hookClearButton() {
        this.clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                ISBN.this.rawISBN.setText(null);
                ISBN.this.cookedISBN10.setText(null);
                ISBN.this.cookedISBN13.setText(null);
            }
        });
    }
    
    private void hookPasteButton() {
        if (this.asApplication) {
            this.pasteButton.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    try {
                        final String raw = ClipboardPoker.getClip(ISBN.this);
                        ISBN.this.rawISBN.setText(raw);
                        final String stripped = ISBNValidate.tidyISBN10or13RemovingDashes(raw);
                        ISBN.this.rawISBN.setText(stripped);
                        switch (stripped.length()) {
                            case 10: {
                                final String isbn10WithDashes = ISBNValidate.tidyISBN10or13InsertingDashes(stripped);
                                final String isbn13WithDashes = ISBNValidate.tidyISBN10or13InsertingDashes(ISBNValidate.isbn10To13(stripped));
                                ISBN.this.cookedISBN10.setText(isbn10WithDashes);
                                ISBN.this.cookedISBN13.setText(isbn13WithDashes);
                                ClipboardPoker.setClip(isbn13WithDashes, ISBN.this);
                                break;
                            }
                            case 13: {
                                final String isbn13WithDashes = ISBNValidate.tidyISBN10or13InsertingDashes(stripped);
                                final String isbn10WithoutDashes = ISBNValidate.isbn13To10(stripped);
                                final String isbn10WithDashes = isbn10WithoutDashes.equals("n/a") ? "n/a" : ISBNValidate.tidyISBN10or13InsertingDashes(isbn10WithoutDashes);
                                ISBN.this.cookedISBN10.setText(isbn10WithDashes);
                                ISBN.this.cookedISBN13.setText(isbn13WithDashes);
                                ClipboardPoker.setClip(isbn13WithDashes, ISBN.this);
                                break;
                            }
                            default: {
                                throw new IllegalArgumentException("ISBN must be 10 or 13 digits long.");
                            }
                        }
                        ISBN.this.setDefaultInstructions();
                    }
                    catch (IllegalArgumentException oops) {
                        ISBN.this.cookedISBN10.setText(null);
                        ISBN.this.cookedISBN13.setText(null);
                        ISBN.this.instructions.setText(oops.getMessage());
                    }
                }
            });
        }
    }
    
    private void hookTidyButton() {
        this.tidyButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                try {
                    final String stripped = ISBNValidate.tidyISBN10or13RemovingDashes(ISBN.this.rawISBN.getText());
                    ISBN.this.rawISBN.setText(stripped);
                    switch (stripped.length()) {
                        case 10: {
                            final String isbn10WithDashes = ISBNValidate.tidyISBN10or13InsertingDashes(stripped);
                            final String isbn13WithDashes = ISBNValidate.tidyISBN10or13InsertingDashes(ISBNValidate.isbn10To13(stripped));
                            ISBN.this.cookedISBN10.setText(isbn10WithDashes);
                            ISBN.this.cookedISBN13.setText(isbn13WithDashes);
                            break;
                        }
                        case 13: {
                            final String isbn13WithDashes = ISBNValidate.tidyISBN10or13InsertingDashes(stripped);
                            final String isbn10WithDashes = ISBNValidate.tidyISBN10or13InsertingDashes(ISBNValidate.isbn13To10(stripped));
                            ISBN.this.cookedISBN13.setText(isbn13WithDashes);
                            ISBN.this.cookedISBN10.setText(isbn10WithDashes);
                            break;
                        }
                        default: {
                            throw new IllegalArgumentException("ISBN must be 10 or 13 digits long.");
                        }
                    }
                    ISBN.this.setDefaultInstructions();
                }
                catch (IllegalArgumentException oops) {
                    ISBN.this.cookedISBN10.setText(null);
                    ISBN.this.cookedISBN13.setText(null);
                    ISBN.this.instructions.setText(oops.getMessage());
                }
            }
        });
    }
    
    private void layoutFields(final Container contentPane) {
        contentPane.add(this.title, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0, 17, 2, new Insets(10, 10, 5, 5), 0, 0));
        contentPane.add(this.title2, new GridBagConstraints(0, 1, 2, 1, 0.0, 0.0, 17, 2, new Insets(0, 10, 5, 5), 0, 0));
        contentPane.add(this.about, new GridBagConstraints(2, 0, 1, 2, 0.0, 0.0, 13, 0, new Insets(10, 5, 5, 10), 0, 0));
        contentPane.add(this.isbnRawLab, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, 13, 0, new Insets(5, 10, 5, 5), 0, 0));
        contentPane.add(this.rawISBN, new GridBagConstraints(1, 2, 1, 1, 100.0, 0.0, 17, 1, new Insets(5, 10, 5, 5), 0, 0));
        contentPane.add(this.isbn10Lab, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, 13, 0, new Insets(5, 10, 5, 5), 0, 0));
        contentPane.add(this.cookedISBN10, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, 13, 1, new Insets(5, 10, 5, 5), 0, 0));
        contentPane.add(this.isbn13Lab, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, 13, 0, new Insets(5, 10, 5, 5), 0, 0));
        contentPane.add(this.cookedISBN13, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, 13, 1, new Insets(5, 10, 5, 5), 0, 0));
        contentPane.add(this.clearButton, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, 17, 0, new Insets(10, 10, 5, 5), 0, 0));
        contentPane.add(this.tidyButton, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0, 10, 0, new Insets(10, 5, 5, 5), 0, 0));
        if (this.asApplication) {
            contentPane.add(this.pasteButton, new GridBagConstraints(2, 5, 1, 1, 0.0, 0.0, 13, 0, new Insets(10, 5, 5, 10), 0, 0));
        }
        contentPane.add(this.instructions, new GridBagConstraints(0, 6, 3, 1, 0.0, 0.0, 10, 2, new Insets(5, 10, 10, 10), 0, 0));
    }
    
    private void setDefaultInstructions() {
        if (this.asApplication) {
            this.instructions.setText("Ctrl-C the raw ISBN-10 or 13 to the clipboard;\nclick Paste;\nthen Ctrl-V the tidied ISBN-13 to your application.");
        }
        else {
            this.instructions.setText("Paste the raw ISBN-10 or 13 to the top box;\nclick Tidy;\nthen copy the tidied ISBN-10 or ISBN-13.");
        }
    }
    
    public static void main(final String[] args) {
        HybridJ.fireup(new ISBN(true), "CMP ISBN Validator 2.3", 379, 381);
    }
    
    static {
        BACKGROUND_FOR_APPLET = new Color(14612991);
        BACKGROUND_FOR_EDITABLE = Color.WHITE;
        FOREGROUND_FOR_INSTRUCTIONS = new Color(32768);
        FOREGROUND_FOR_LABEL = new Color(176);
        FOREGROUND_FOR_TITLE = new Color(14423100);
        UNBACKGROUND_FOR_EDITABLE = new Color(16316664);
        FONT_FOR_LABELS = FontFactory.build("Dialog", 1, 15);
        FONT_FOR_TITLE = FontFactory.build("Dialog", 1, 16);
        FONT_FOR_TITLE2 = FontFactory.build("Dialog", 0, 14);
    }
}
