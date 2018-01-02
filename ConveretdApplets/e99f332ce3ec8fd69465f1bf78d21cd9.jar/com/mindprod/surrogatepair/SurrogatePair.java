// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.surrogatepair;

import com.mindprod.common13.HybridJ;
import java.awt.Component;
import java.awt.GridBagConstraints;
import javax.swing.text.Document;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.regex.Matcher;
import javax.swing.JFormattedTextField;
import com.mindprod.common15.FontFactory;
import java.awt.Insets;
import javax.swing.JComponent;
import com.mindprod.spinner.HexNumberEditor;
import javax.swing.SpinnerModel;
import java.awt.Dimension;
import com.mindprod.common13.CMPAboutJBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.mindprod.common13.JEButton;
import com.mindprod.common11.ST;
import java.awt.Container;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import com.mindprod.common13.Common13;
import com.mindprod.common11.VersionCheck;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.util.regex.Pattern;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JApplet;

public final class SurrogatePair extends JApplet
{
    private static final int APPLET_HEIGHT = 440;
    private static final int APPLET_WIDTH = 600;
    private static final int basicFontSize = 12;
    private static final int BIGGEST_REPRESENTABLE_CODEPOINT = 1114111;
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 2009-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    private static final String RELEASE_DATE = "2009-12-30";
    private static final String TITLE_STRING = "Surrogate Pairs";
    private static final String VERSION_STRING = "1.0";
    private static final Color BACKGROUND_FOR_APPLET;
    private static final Color BACKGROUND_FOR_EDITABLE;
    private static final Color BACKGROUND_FOR_RESULT;
    private static final Color FOREGROUND_FOR_ENTER;
    private static final Color FOREGROUND_FOR_INSTRUCTIONS;
    private static final Color FOREGROUND_FOR_LABEL;
    private static final Color FOREGROUND_FOR_RESULT;
    private static final Color FOREGROUND_FOR_TITLE;
    private static final Font FONT_FOR_EDITABLE_FIELDS;
    private static final Font FONT_FOR_INSTRUCTIONS;
    private static final Font FONT_FOR_RESULTS;
    private static final Font FONT_FOR_TITLE;
    private static final Font FONT_FOR_TITLE2;
    private static final Pattern CU8;
    private static final Pattern CU8U4;
    private static final int[] margin;
    private JButton about;
    private JLabel inputTextLabel;
    private JLabel instructions1;
    private JLabel instructions2;
    private JLabel instructions3;
    private JLabel renderTextLabel;
    private JLabel surrogatePairLiteralsLabel;
    private JLabel title;
    private JLabel title2;
    private JSpinner codePointSpinner;
    private JTextArea inputText;
    private JTextArea renderText;
    private JTextArea surrogatePairLiterals;
    private SpinnerNumberModel codePointNumberModel;
    
    public void destroy() {
        this.about = null;
        this.codePointNumberModel = null;
        this.codePointSpinner = null;
        this.inputText = null;
        this.inputTextLabel = null;
        this.instructions1 = null;
        this.instructions2 = null;
        this.instructions3 = null;
        this.renderText = null;
        this.renderTextLabel = null;
        this.surrogatePairLiterals = null;
        this.surrogatePairLiteralsLabel = null;
        this.title2 = null;
        this.title = null;
    }
    
    public void init() {
        final Container contentPane = this.getContentPane();
        if (!VersionCheck.isJavaVersionOK(1, 5, 0, contentPane)) {
            return;
        }
        Common13.setLaf();
        contentPane.setLayout(new GridBagLayout());
        contentPane.setBackground(SurrogatePair.BACKGROUND_FOR_APPLET);
        this.buildComponents();
        this.layoutComponents(contentPane);
        this.installHooks();
    }
    
    public void start() {
        this.inputTextChanged();
        this.validate();
    }
    
    public void stop() {
    }
    
    private static String toCLiteral(final int codePoint) {
        if (0 > codePoint || codePoint > 1114111) {
            throw new IllegalArgumentException("toSurrogatePair toCLiteral must be in range 0x0000..1114111");
        }
        final StringBuilder sb = new StringBuilder(10);
        sb.append("\\U");
        sb.append(ST.toLZHexString(codePoint, 8));
        return sb.toString();
    }
    
    private static String toSurrogatePair(final int codePoint) {
        if (0 > codePoint || codePoint > 1114111) {
            throw new IllegalArgumentException("codePoint parameter for toSurrogatePair must be in range 0x0000..1114111");
        }
        if (codePoint <= 65535) {
            return String.valueOf((char)codePoint);
        }
        final int extract = codePoint - 65536;
        final int high = (extract >>> 10) + 55296;
        final int low = (extract & 0x3FF) + 56320;
        final StringBuilder sb = new StringBuilder(2);
        sb.append((char)high);
        sb.append((char)low);
        return sb.toString();
    }
    
    private static String toSurrogatePairLiteral(final int codePoint) {
        if (0 > codePoint || codePoint > 1114111) {
            throw new IllegalArgumentException("codePoint parameter for toSurrogatePairLiteral must be in range 0x0000..1114111");
        }
        if (codePoint <= 65535) {
            final StringBuilder sb = new StringBuilder(6);
            sb.append("\\u");
            sb.append(ST.toLZHexString(codePoint, 4));
            return sb.toString();
        }
        final int extract = codePoint - 65536;
        final int high = (extract >>> 10) + 55296;
        final int low = (extract & 0x3FF) + 56320;
        final StringBuilder sb2 = new StringBuilder(12);
        sb2.append("\\u");
        sb2.append(ST.toLZHexString(high, 4));
        sb2.append("\\u");
        sb2.append(ST.toLZHexString(low, 4));
        return sb2.toString();
    }
    
    private void buildComponents() {
        (this.about = new JEButton("About")).setToolTipText("About Surrogate Pairs 1.0");
        this.about.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                new CMPAboutJBox("Surrogate Pairs", "1.0", "Converts codePoints to surrogate pair literals", "", "freeware", "2009-12-30", 2009, "Roedy Green", "SURROGATEPAIR", "1.5");
            }
        });
        (this.title = new JLabel("Surrogate Pairs 1.0")).setForeground(SurrogatePair.FOREGROUND_FOR_TITLE);
        this.title.setFont(SurrogatePair.FONT_FOR_TITLE);
        (this.title2 = new JLabel("released:2009-12-30 build:9411")).setFont(SurrogatePair.FONT_FOR_TITLE2);
        this.title2.setForeground(SurrogatePair.FOREGROUND_FOR_TITLE);
        (this.codePointSpinner = new JSpinner()).setForeground(SurrogatePair.FOREGROUND_FOR_ENTER);
        this.codePointSpinner.setBackground(SurrogatePair.BACKGROUND_FOR_EDITABLE);
        this.codePointSpinner.setFont(SurrogatePair.FONT_FOR_EDITABLE_FIELDS);
        final Dimension d = new Dimension(100, 25);
        this.codePointSpinner.setMinimumSize(d);
        this.codePointSpinner.setPreferredSize(d);
        this.codePointSpinner.setMaximumSize(d);
        this.codePointNumberModel = new SpinnerNumberModel(120068, 0, 1114111, 1);
        this.codePointSpinner.setModel(this.codePointNumberModel);
        final HexNumberEditor hne = new HexNumberEditor(this.codePointSpinner, 8);
        this.codePointSpinner.setEditor(hne);
        final JFormattedTextField innerHexSpinner = hne.getTextField();
        innerHexSpinner.setBackground(SurrogatePair.BACKGROUND_FOR_EDITABLE);
        innerHexSpinner.setForeground(SurrogatePair.FOREGROUND_FOR_ENTER);
        innerHexSpinner.setMargin(new Insets(1, 1, 1, 1));
        (this.inputTextLabel = new JLabel("Enter String literal using C-style \\Uxxxxxxxx or \\uxxxx Java-style notation:")).setForeground(SurrogatePair.FOREGROUND_FOR_LABEL);
        this.inputTextLabel.setFont(FontFactory.build("Dialog", 0, 12));
        (this.inputText = new JTextArea()).setText("Sample String literal containing Java-style escape \\u00b5 and unsupported C style code point \\U0001d504.");
        this.inputText.setEditable(true);
        this.inputText.setForeground(SurrogatePair.FOREGROUND_FOR_ENTER);
        this.inputText.setBackground(SurrogatePair.BACKGROUND_FOR_EDITABLE);
        this.inputText.setFont(SurrogatePair.FONT_FOR_EDITABLE_FIELDS);
        this.inputText.setLineWrap(true);
        this.inputText.setWrapStyleWord(true);
        this.inputText.setOpaque(true);
        this.inputText.setMargin(new Insets(2, 2, 2, 2));
        (this.surrogatePairLiteralsLabel = new JLabel("As Java String literal using surrogate pairs:")).setForeground(SurrogatePair.FOREGROUND_FOR_LABEL);
        this.surrogatePairLiteralsLabel.setFont(FontFactory.build("Dialog", 0, 12));
        (this.surrogatePairLiterals = new JTextArea()).setEditable(false);
        this.surrogatePairLiterals.setForeground(SurrogatePair.FOREGROUND_FOR_RESULT);
        this.surrogatePairLiterals.setBackground(SurrogatePair.BACKGROUND_FOR_RESULT);
        this.surrogatePairLiterals.setFont(SurrogatePair.FONT_FOR_RESULTS);
        this.surrogatePairLiterals.setLineWrap(true);
        this.surrogatePairLiterals.setWrapStyleWord(true);
        this.surrogatePairLiterals.setOpaque(true);
        this.surrogatePairLiterals.setMargin(new Insets(2, 2, 2, 2));
        (this.renderTextLabel = new JLabel("How that literal will render on your platform:")).setForeground(SurrogatePair.FOREGROUND_FOR_LABEL);
        this.renderTextLabel.setFont(FontFactory.build("Dialog", 0, 12));
        (this.renderText = new JTextArea()).setEditable(false);
        this.renderText.setForeground(SurrogatePair.FOREGROUND_FOR_RESULT);
        this.renderText.setBackground(SurrogatePair.BACKGROUND_FOR_RESULT);
        this.renderText.setFont(SurrogatePair.FONT_FOR_RESULTS);
        this.renderText.setLineWrap(true);
        this.renderText.setWrapStyleWord(true);
        this.renderText.setOpaque(true);
        this.renderText.setMargin(new Insets(2, 2, 2, 2));
        (this.instructions1 = new JLabel("Either:")).setForeground(SurrogatePair.FOREGROUND_FOR_INSTRUCTIONS);
        this.instructions1.setFont(SurrogatePair.FONT_FOR_INSTRUCTIONS);
        (this.instructions2 = new JLabel("(1) Use the spinner to see a single hex codePoint rendered as a surrogate pair.")).setForeground(SurrogatePair.FOREGROUND_FOR_INSTRUCTIONS);
        this.instructions2.setFont(SurrogatePair.FONT_FOR_INSTRUCTIONS);
        (this.instructions3 = new JLabel("(2) Type a Java string literal using embedded C-style \\Uxxxxxxxx notation for the codePoints.")).setForeground(SurrogatePair.FOREGROUND_FOR_INSTRUCTIONS);
        this.instructions3.setFont(SurrogatePair.FONT_FOR_INSTRUCTIONS);
    }
    
    private void inputTextChanged() {
        final String text = this.inputText.getText();
        StringBuffer sb = new StringBuffer(text.length() + 40);
        Matcher m = SurrogatePair.CU8.matcher(text);
        while (m.find()) {
            final String codePointString = m.group(1);
            int codePoint;
            try {
                codePoint = Integer.parseInt(codePointString, 16);
            }
            catch (NumberFormatException e) {
                System.err.println("program bug: problem parsing " + codePointString);
                codePoint = 63;
            }
            m.appendReplacement(sb, Matcher.quoteReplacement(toSurrogatePairLiteral(codePoint)));
        }
        m.appendTail(sb);
        this.surrogatePairLiterals.setText(sb.toString());
        sb = new StringBuffer(text.length());
        m = SurrogatePair.CU8U4.matcher(text);
        while (m.find()) {
            final String codePointString = (m.group(1) != null) ? m.group(1) : m.group(2);
            int codePoint;
            try {
                codePoint = Integer.parseInt(codePointString, 16);
            }
            catch (NumberFormatException e) {
                System.err.println("program bug: problem parsing " + codePointString);
                codePoint = 63;
            }
            m.appendReplacement(sb, Matcher.quoteReplacement(toSurrogatePair(codePoint)));
        }
        m.appendTail(sb);
        this.renderText.setText(sb.toString());
    }
    
    private void installHooks() {
        this.codePointSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(final ChangeEvent e) {
                SurrogatePair.this.spinnerChanged();
            }
        });
        final Document document = this.inputText.getDocument();
        document.addDocumentListener(new DocumentListener() {
            public void changedUpdate(final DocumentEvent e) {
                SurrogatePair.this.inputTextChanged();
            }
            
            public void insertUpdate(final DocumentEvent e) {
                SurrogatePair.this.inputTextChanged();
            }
            
            public void removeUpdate(final DocumentEvent e) {
                SurrogatePair.this.inputTextChanged();
            }
        });
    }
    
    private void layoutComponents(final Container contentPane) {
        contentPane.add(this.title, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, 17, 0, new Insets(SurrogatePair.margin[0], SurrogatePair.margin[1], 5, 5), 0, 0));
        contentPane.add(this.title2, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, 17, 0, new Insets(5, SurrogatePair.margin[1], 5, 5), 0, 0));
        contentPane.add(this.about, new GridBagConstraints(1, 0, 1, 3, 0.0, 0.0, 13, 0, new Insets(SurrogatePair.margin[0], 5, 5, SurrogatePair.margin[3]), 20, 10));
        contentPane.add(this.inputTextLabel, new GridBagConstraints(0, 3, 2, 1, 0.0, 0.0, 16, 2, new Insets(2, SurrogatePair.margin[1], 0, 2), 40, 20));
        contentPane.add(this.codePointSpinner, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, 13, 0, new Insets(2, 2, 2, SurrogatePair.margin[3]), 0, 0));
        contentPane.add(this.inputText, new GridBagConstraints(0, 4, 2, 1, 1.0, 1.0, 17, 1, new Insets(0, SurrogatePair.margin[1], 5, SurrogatePair.margin[3]), 40, 20));
        contentPane.add(this.surrogatePairLiteralsLabel, new GridBagConstraints(0, 5, 2, 1, 0.0, 0.0, 17, 2, new Insets(2, SurrogatePair.margin[1], 0, SurrogatePair.margin[3]), 40, 20));
        contentPane.add(this.surrogatePairLiterals, new GridBagConstraints(0, 6, 2, 1, 1.0, 1.0, 17, 1, new Insets(0, SurrogatePair.margin[1], 5, SurrogatePair.margin[3]), 40, 20));
        contentPane.add(this.renderTextLabel, new GridBagConstraints(0, 7, 2, 1, 0.0, 0.0, 17, 2, new Insets(2, SurrogatePair.margin[1], 0, SurrogatePair.margin[3]), 40, 20));
        contentPane.add(this.renderText, new GridBagConstraints(0, 8, 2, 1, 1.0, 1.0, 17, 1, new Insets(0, SurrogatePair.margin[1], 5, SurrogatePair.margin[3]), 40, 20));
        contentPane.add(this.instructions1, new GridBagConstraints(0, 9, 2, 1, 1.0, 0.0, 17, 2, new Insets(5, SurrogatePair.margin[1], 1, SurrogatePair.margin[3]), 0, 0));
        contentPane.add(this.instructions2, new GridBagConstraints(0, 10, 2, 1, 1.0, 0.0, 17, 2, new Insets(1, SurrogatePair.margin[1], 1, SurrogatePair.margin[3]), 0, 0));
        contentPane.add(this.instructions3, new GridBagConstraints(0, 11, 2, 1, 1.0, 0.0, 17, 2, new Insets(1, SurrogatePair.margin[1], SurrogatePair.margin[2], SurrogatePair.margin[3]), 0, 0));
    }
    
    private void regexDump(final Matcher m, final String desc) {
        final int count = m.groupCount();
        System.err.println(">>> " + desc + " start:" + m.start() + " end:" + m.end() + " length:" + (m.end() - m.start()) + " groups:" + count);
        for (int i = 0; i <= count; ++i) {
            System.err.println("     " + i + ". " + m.group(i));
        }
    }
    
    private void spinnerChanged() {
        final int codePoint = this.codePointNumberModel.getNumber().intValue();
        final StringBuilder sb = new StringBuilder(12);
        sb.append('\"');
        sb.append(toCLiteral(codePoint));
        sb.append("\"");
        this.inputText.setText(sb.toString());
    }
    
    public static void main(final String[] args) {
        HybridJ.fireup(new SurrogatePair(), "Surrogate Pairs 1.0", 600, 440);
    }
    
    static {
        BACKGROUND_FOR_APPLET = new Color(16445670);
        BACKGROUND_FOR_EDITABLE = Color.WHITE;
        BACKGROUND_FOR_RESULT = new Color(16187382);
        FOREGROUND_FOR_ENTER = Color.BLACK;
        FOREGROUND_FOR_INSTRUCTIONS = new Color(25600);
        FOREGROUND_FOR_LABEL = new Color(176);
        FOREGROUND_FOR_RESULT = new Color(16384);
        FOREGROUND_FOR_TITLE = new Color(14423100);
        FONT_FOR_EDITABLE_FIELDS = FontFactory.build("DialogInput", 0, 13);
        FONT_FOR_INSTRUCTIONS = FontFactory.build("Dialog", 0, 12);
        FONT_FOR_RESULTS = FontFactory.build("Dialog", 0, 12);
        FONT_FOR_TITLE = FontFactory.build("Dialog", 1, 16);
        FONT_FOR_TITLE2 = FontFactory.build("Dialog", 0, 14);
        CU8 = Pattern.compile("\\\\U(\\p{XDigit}{8})", 2);
        CU8U4 = Pattern.compile("\\\\U(\\p{XDigit}{8})|\\\\u(\\p{XDigit}{4})", 2);
        margin = new int[] { 12, 15, 12, 15 };
    }
}
