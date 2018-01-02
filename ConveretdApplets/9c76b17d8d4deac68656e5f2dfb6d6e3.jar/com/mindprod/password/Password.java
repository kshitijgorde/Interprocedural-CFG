// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.password;

import java.security.SecureRandom;
import com.mindprod.common11.FontFactory;
import com.mindprod.common13.HybridJ;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.AbstractButton;
import javax.swing.SpinnerModel;
import java.awt.Dimension;
import com.mindprod.common13.CMPAboutJBox;
import com.mindprod.common13.JEButton;
import javax.swing.event.ChangeEvent;
import java.awt.Container;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import com.mindprod.common13.Common13;
import com.mindprod.common11.VersionCheck;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import java.util.Random;
import java.awt.Font;
import java.awt.Color;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import javax.swing.JApplet;

public final class Password extends JApplet implements ActionListener, ChangeListener
{
    private static final int APPLET_HEIGHT = 381;
    private static final int APPLET_WIDTH = 480;
    private static final int basicFontSize = 14;
    private static final int DEFAULT_PW_LENGTH = 10;
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 2004-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    private static final String LOWER_CASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String PUNCTUATION_LETTERS = "!@#$%^&*?~`";
    private static final String RELEASE_DATE = "2010-07-28";
    private static final String TITLE_STRING = "Password Generator";
    private static final String UPPER_CASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String VERSION_STRING = "1.6";
    private static final Color BACKGROUND_FOR_APPLET;
    private static final Color BACKGROUND_FOR_INSTRUCTIONS;
    private static final Color BACKGROUND_FOR_LABEL;
    private static final Color BACKGROUND_FOR_RESULT;
    private static final Color FOREGROUND_FOR_APPLET;
    private static final Color FOREGROUND_FOR_INSTRUCTIONS;
    private static final Color FOREGROUND_FOR_LABEL;
    private static final Color FOREGROUND_FOR_RESULT;
    private static final Color FOREGROUND_FOR_TITLE;
    private static final Font FONT_FOR_EDITABLE;
    private static final Font FONT_FOR_INSTRUCTIONS;
    private static final Font FONT_FOR_LABEL;
    private static final Font FONT_FOR_RESULT;
    private static final Font FONT_FOR_TITLE;
    private static final Font FONT_FOR_TITLE2;
    private static final int[] margin;
    private static Random wheel;
    private ButtonGroup caseSensitivity;
    private JButton about;
    private JButton another;
    private JLabel instructions1;
    private JLabel instructions2;
    private JLabel instructions3;
    private JLabel pwLengthLabel;
    private JLabel title;
    private JLabel title2;
    private JRadioButton caseInsensitive;
    private JRadioButton caseSensitive;
    private JRadioButton includePunctuation;
    private JSpinner pwLength;
    private JTextField suggestedPassword;
    private SpinnerNumberModel pwLengthModel;
    
    public void actionPerformed(final ActionEvent e) {
        this.hit();
    }
    
    public void destroy() {
        this.caseSensitivity = null;
        this.about = null;
        this.another = null;
        this.instructions1 = null;
        this.instructions2 = null;
        this.instructions3 = null;
        this.pwLengthLabel = null;
        this.title2 = null;
        this.title = null;
        this.caseInsensitive = null;
        this.caseSensitive = null;
        this.includePunctuation = null;
        this.pwLength = null;
        this.suggestedPassword = null;
        this.pwLengthModel = null;
    }
    
    public void init() {
        final Container contentPane = this.getContentPane();
        if (!VersionCheck.isJavaVersionOK(1, 3, 0, contentPane)) {
            return;
        }
        Common13.setLaf();
        contentPane.setLayout(new GridBagLayout());
        contentPane.setBackground(Password.BACKGROUND_FOR_APPLET);
        contentPane.setForeground(Password.FOREGROUND_FOR_APPLET);
        this.buildComponents();
        this.layoutComponents(contentPane);
        this.installHooks();
    }
    
    public void start() {
        this.hit();
        this.validate();
    }
    
    public void stateChanged(final ChangeEvent e) {
        this.hit();
    }
    
    public void stop() {
    }
    
    private void buildComponents() {
        (this.about = new JEButton("About")).setToolTipText("About Password Generator 1.6");
        this.about.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                new CMPAboutJBox("Password Generator", "1.6", "Generates random passwords.", "", "freeware", "2010-07-28", 2004, "Roedy Green", "PASSWORD", "1.3");
            }
        });
        (this.title = new JLabel("Password Generator 1.6")).setForeground(Password.FOREGROUND_FOR_TITLE);
        this.title.setFont(Password.FONT_FOR_TITLE);
        (this.title2 = new JLabel("released:2010-07-28 build:9410")).setFont(Password.FONT_FOR_TITLE2);
        this.title2.setForeground(Password.FOREGROUND_FOR_TITLE);
        this.pwLength = new JSpinner();
        final Dimension d = new Dimension(60, 25);
        this.pwLength.setMinimumSize(d);
        this.pwLength.setPreferredSize(d);
        this.pwLength.setMaximumSize(d);
        this.pwLengthModel = new SpinnerNumberModel(10, 4, 99, 1);
        this.pwLength.setModel(this.pwLengthModel);
        (this.pwLengthLabel = new JLabel("Length")).setBackground(Password.BACKGROUND_FOR_LABEL);
        this.pwLengthLabel.setFont(Password.FONT_FOR_LABEL);
        this.pwLengthLabel.setForeground(Password.FOREGROUND_FOR_LABEL);
        this.caseSensitivity = new ButtonGroup();
        (this.caseSensitive = new JRadioButton("case sensitive (mixed case)", true)).setBackground(Password.BACKGROUND_FOR_LABEL);
        this.caseSensitive.setFont(Password.FONT_FOR_EDITABLE);
        this.caseSensitive.setForeground(Password.FOREGROUND_FOR_LABEL);
        (this.caseInsensitive = new JRadioButton("case insensitive (lower case)", false)).setBackground(Password.BACKGROUND_FOR_LABEL);
        this.caseInsensitive.setFont(Password.FONT_FOR_EDITABLE);
        this.caseInsensitive.setForeground(Password.FOREGROUND_FOR_LABEL);
        this.caseSensitivity.add(this.caseSensitive);
        this.caseSensitivity.add(this.caseInsensitive);
        (this.includePunctuation = new JRadioButton("include punctuation", false)).setBackground(Password.BACKGROUND_FOR_LABEL);
        this.includePunctuation.setFont(Password.FONT_FOR_EDITABLE);
        this.includePunctuation.setForeground(Password.FOREGROUND_FOR_LABEL);
        (this.another = new JEButton("Another")).setToolTipText("Generate another password");
        (this.suggestedPassword = new JTextField("", 2)).setBackground(Password.BACKGROUND_FOR_RESULT);
        this.suggestedPassword.setEditable(false);
        this.suggestedPassword.setFont(Password.FONT_FOR_RESULT);
        this.suggestedPassword.setForeground(Password.FOREGROUND_FOR_RESULT);
        this.suggestedPassword.setMargin(new Insets(2, 2, 2, 2));
        this.suggestedPassword.setOpaque(true);
        (this.instructions1 = new JLabel("Select whether case matters and password length,")).setBackground(Password.BACKGROUND_FOR_INSTRUCTIONS);
        this.instructions1.setFont(Password.FONT_FOR_INSTRUCTIONS);
        this.instructions1.setForeground(Password.FOREGROUND_FOR_INSTRUCTIONS);
        (this.instructions2 = new JLabel("then click ANOTHER. Then for one final tweak,")).setBackground(Password.BACKGROUND_FOR_INSTRUCTIONS);
        this.instructions2.setFont(Password.FONT_FOR_INSTRUCTIONS);
        this.instructions2.setForeground(Password.FOREGROUND_FOR_INSTRUCTIONS);
        (this.instructions3 = new JLabel("change one or two characters of the result before use.")).setBackground(Password.BACKGROUND_FOR_INSTRUCTIONS);
        this.instructions3.setFont(Password.FONT_FOR_INSTRUCTIONS);
        this.instructions3.setForeground(Password.FOREGROUND_FOR_INSTRUCTIONS);
    }
    
    private void hit() {
        final int pwLengthValue = ((Number)this.pwLengthModel.getValue()).intValue();
        final StringBuffer sb = new StringBuffer(75);
        sb.append("abcdefghijklmnopqrstuvwxyz");
        sb.append("0123456789");
        if (this.caseSensitive.isSelected()) {
            sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        }
        if (this.includePunctuation.isSelected()) {
            sb.append("!@#$%^&*?~`");
        }
        this.suggestedPassword.setText(this.spin(pwLengthValue, sb.toString()));
        this.suggestedPassword.requestFocus();
    }
    
    private void installHooks() {
        this.another.addActionListener(this);
        this.caseInsensitive.addActionListener(this);
        this.caseSensitive.addActionListener(this);
        this.includePunctuation.addActionListener(this);
        this.pwLength.addChangeListener(this);
    }
    
    private void layoutComponents(final Container contentPane) {
        contentPane.add(this.title, new GridBagConstraints(0, 0, 3, 1, 0.0, 0.0, 17, 0, new Insets(Password.margin[0], Password.margin[1], 5, 5), 0, 0));
        contentPane.add(this.title2, new GridBagConstraints(0, 1, 3, 1, 0.0, 0.0, 17, 0, new Insets(Password.margin[0], Password.margin[1], 5, 5), 0, 0));
        contentPane.add(this.about, new GridBagConstraints(3, 0, 1, 2, 0.0, 0.0, 13, 0, new Insets(Password.margin[0], 5, 5, Password.margin[3]), 20, 10));
        contentPane.add(this.caseSensitive, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, 17, 0, new Insets(2, Password.margin[1], 1, 2), 0, 0));
        contentPane.add(this.caseInsensitive, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, 17, 0, new Insets(1, Password.margin[1], 2, 2), 0, 0));
        contentPane.add(this.includePunctuation, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, 17, 0, new Insets(1, Password.margin[1], 2, 2), 0, 0));
        contentPane.add(this.pwLengthLabel, new GridBagConstraints(2, 2, 1, 2, 1.0, 0.0, 13, 0, new Insets(2, 2, 2, 2), 0, 0));
        contentPane.add(this.pwLength, new GridBagConstraints(3, 2, 1, 2, 0.0, 0.0, 13, 0, new Insets(2, 2, 2, Password.margin[3]), 0, 0));
        contentPane.add(this.suggestedPassword, new GridBagConstraints(0, 5, 4, 1, 1.0, 1.0, 17, 2, new Insets(2, Password.margin[1], 5, Password.margin[3]), 40, 20));
        contentPane.add(this.another, new GridBagConstraints(1, 6, 3, 1, 0.0, 0.0, 13, 0, new Insets(5, 5, 5, Password.margin[3]), 20, 10));
        contentPane.add(this.instructions1, new GridBagConstraints(0, 7, 4, 1, 1.0, 0.0, 17, 2, new Insets(5, Password.margin[1], 1, Password.margin[3]), 0, 0));
        contentPane.add(this.instructions2, new GridBagConstraints(0, 8, 4, 1, 1.0, 0.0, 17, 2, new Insets(1, Password.margin[1], 1, Password.margin[3]), 0, 0));
        contentPane.add(this.instructions3, new GridBagConstraints(0, 9, 4, 1, 1.0, 0.0, 17, 2, new Insets(1, Password.margin[1], Password.margin[2], Password.margin[3]), 0, 0));
    }
    
    private String spin(final int pwlength, final String possibleCharacters) {
        boolean needsUpperCase = possibleCharacters.indexOf(65) >= 0;
        boolean needsLowerCase = possibleCharacters.indexOf(97) >= 0;
        boolean needsDigit = possibleCharacters.indexOf(48) >= 0;
        boolean needsPunct = possibleCharacters.indexOf(33) >= 0;
        final char[] accum = new char[pwlength];
        do {
            for (int i = 0; i < pwlength; ++i) {
                final int index = Password.wheel.nextInt(possibleCharacters.length());
                final char letter = possibleCharacters.charAt(index);
                if (needsUpperCase && 'A' <= letter && letter <= 'Z') {
                    needsUpperCase = false;
                }
                if (needsLowerCase && 'a' <= letter && letter <= 'z') {
                    needsLowerCase = false;
                }
                if (needsDigit && '0' <= letter && letter <= '9') {
                    needsDigit = false;
                }
                if (needsPunct && "!@#$%^&*?~`".indexOf(letter) >= 0) {
                    needsPunct = false;
                }
                accum[i] = letter;
            }
        } while (needsUpperCase || needsLowerCase || needsDigit || needsPunct);
        return new String(accum);
    }
    
    public static void main(final String[] args) {
        HybridJ.fireup(new Password(), "Password Generator 1.6", 480, 381);
    }
    
    static {
        BACKGROUND_FOR_APPLET = new Color(16445670);
        BACKGROUND_FOR_INSTRUCTIONS = Color.WHITE;
        BACKGROUND_FOR_LABEL = new Color(16445670);
        BACKGROUND_FOR_RESULT = new Color(15466487);
        FOREGROUND_FOR_APPLET = new Color(2003199);
        FOREGROUND_FOR_INSTRUCTIONS = new Color(8192);
        FOREGROUND_FOR_LABEL = new Color(176);
        FOREGROUND_FOR_RESULT = new Color(2254370);
        FOREGROUND_FOR_TITLE = new Color(14423100);
        FONT_FOR_EDITABLE = FontFactory.build("Dialog", 0, 14);
        FONT_FOR_INSTRUCTIONS = FontFactory.build("Dialog", 0, 14);
        FONT_FOR_LABEL = FontFactory.build("Dialog", 0, 14);
        FONT_FOR_RESULT = FontFactory.build("Dialog", 1, 16);
        FONT_FOR_TITLE = FontFactory.build("Dialog", 1, 16);
        FONT_FOR_TITLE2 = FontFactory.build("Dialog", 0, 14);
        margin = new int[] { 12, 15, 12, 15 };
        try {
            Password.wheel = SecureRandom.getInstance("SHA1PRNG", "SUN");
        }
        catch (Exception e) {
            System.out.println("Reverting to lower quality random number generator");
            Password.wheel = new Random();
        }
    }
}
