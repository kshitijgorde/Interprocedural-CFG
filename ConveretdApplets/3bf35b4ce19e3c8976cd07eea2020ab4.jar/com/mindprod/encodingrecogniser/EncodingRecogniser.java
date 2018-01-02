// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.encodingrecogniser;

import com.mindprod.common15.FontFactory;
import com.mindprod.common11.Build;
import com.mindprod.common13.HybridJ;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import java.io.UnsupportedEncodingException;
import com.mindprod.common13.CMPAboutJBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import com.mindprod.common15.Laf;
import javax.swing.JMenuBar;
import java.util.Set;
import java.util.SortedMap;
import java.awt.Component;
import java.security.AccessControlException;
import java.nio.charset.Charset;
import com.mindprod.common13.JEButton;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Container;
import com.mindprod.common11.VersionCheck;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JApplet;

public final class EncodingRecogniser extends JApplet
{
    private static final int APPLET_HEIGHT = 528;
    private static final int APPLET_WIDTH = 636;
    private static final int MAX_BYTES_TO_SAMPLE = 1048576;
    private static final int MAX_HEX_BYTES_TO_SAMPLE = 22;
    private static final int MAX_HEX_CHARS_TO_SAMPLE = 12;
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 1998-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    private static final String RELEASE_DATE = "2010-03-23";
    private static final String TITLE_STRING = "Encoding Recogniser";
    private static final String VERSION_STRING = "1.2";
    private static final Color BACKGROUND_FOR_BODY;
    private static final Color BACKGROUND_FOR_INSTRUCTIONS;
    private static final Color FOREGROUND_FOR_INSTRUCTIONS;
    private static final Color FOREGROUND_FOR_LABEL;
    private static final Color FOREGROUND_FOR_TITLE;
    private static final Font FONT_FOR_INSTRUCTIONS;
    private static final Font FONT_FOR_TITLE;
    private static final Font FONT_FOR_TITLE2;
    private static final char[] hexChar;
    private JButton chooseFile;
    private JComboBox chooseEncoding;
    private JLabel encodingLabel;
    private JLabel fileLabel;
    private JLabel hexByteLabel;
    private JLabel hexCharLabel;
    private JLabel instructions;
    private JLabel title;
    private JLabel title2;
    private JScrollPane scroller;
    private JTextArea displayFileContents;
    private JTextField fileName;
    private JTextField hexBytes;
    private JTextField hexChars;
    private String translatedBytes;
    private byte[] rawByteSample;
    
    public void destroy() {
        this.chooseEncoding = null;
        this.chooseFile = null;
        this.displayFileContents = null;
        this.encodingLabel = null;
        this.fileLabel = null;
        this.fileName = null;
        this.hexByteLabel = null;
        this.hexBytes = null;
        this.hexCharLabel = null;
        this.hexChars = null;
        this.instructions = null;
        this.rawByteSample = null;
        this.scroller = null;
        this.title2 = null;
        this.title = null;
        this.translatedBytes = null;
    }
    
    public void init() {
        if (!VersionCheck.isJavaVersionOK(1, 5, 0, this)) {
            return;
        }
        this.buildMenu();
        final Container contentPane = this.getContentPane();
        contentPane.setLayout(new GridBagLayout());
        contentPane.setBackground(EncodingRecogniser.BACKGROUND_FOR_BODY);
        (this.title = new JLabel("Encoding Recogniser 1.2")).setFont(EncodingRecogniser.FONT_FOR_TITLE);
        this.title.setForeground(EncodingRecogniser.FOREGROUND_FOR_TITLE);
        (this.title2 = new JLabel("released:2010-03-23 build:9411")).setFont(EncodingRecogniser.FONT_FOR_TITLE2);
        this.title2.setForeground(EncodingRecogniser.FOREGROUND_FOR_TITLE);
        (this.chooseFile = new JEButton("Choose File")).setToolTipText("Choose a file to study");
        (this.fileName = new JTextField()).setEditable(false);
        this.fileName.setForeground(Color.RED);
        final SortedMap<String, Charset> sm = Charset.availableCharsets();
        final Set<String> encodings = sm.keySet();
        this.chooseEncoding = new JComboBox((E[])encodings.toArray(new String[encodings.size()]));
        try {
            this.chooseEncoding.setSelectedItem(Charset.defaultCharset().name());
        }
        catch (AccessControlException e) {
            this.chooseEncoding.setSelectedIndex(0);
        }
        (this.fileLabel = new JLabel("file to check", 2)).setForeground(EncodingRecogniser.FOREGROUND_FOR_LABEL);
        (this.encodingLabel = new JLabel("encoding candidate", 4)).setForeground(EncodingRecogniser.FOREGROUND_FOR_LABEL);
        (this.hexByteLabel = new JLabel("raw hex bytes")).setForeground(EncodingRecogniser.FOREGROUND_FOR_LABEL);
        (this.hexCharLabel = new JLabel("decoded hex chars")).setForeground(EncodingRecogniser.FOREGROUND_FOR_LABEL);
        (this.hexBytes = new JTextField()).setEditable(false);
        (this.hexChars = new JTextField()).setEditable(false);
        (this.displayFileContents = new JTextArea()).setEditable(false);
        this.scroller = new JScrollPane(this.displayFileContents, 20, 30);
        (this.instructions = new JLabel("Choose a file, then try various encodings till you find an encoding that displays it properly.", 0)).setFont(EncodingRecogniser.FONT_FOR_INSTRUCTIONS);
        this.instructions.setBackground(EncodingRecogniser.BACKGROUND_FOR_INSTRUCTIONS);
        this.instructions.setForeground(EncodingRecogniser.FOREGROUND_FOR_INSTRUCTIONS);
        this.layoutComponents(contentPane);
        this.hookComponents();
    }
    
    private static String toHexString(final byte[] b, final int length) {
        final StringBuilder sb = new StringBuilder(length * 3);
        for (int i = 0; i < length; ++i) {
            if (i != 0) {
                sb.append(' ');
            }
            sb.append(EncodingRecogniser.hexChar[(b[i] & 0xF0) >>> 4]);
            sb.append(EncodingRecogniser.hexChar[b[i] & 0xF]);
        }
        return sb.toString();
    }
    
    private static String toHexString(final String b, final int length) {
        final StringBuilder sb = new StringBuilder(length * 5);
        for (int i = 0; i < length; ++i) {
            if (i != 0) {
                sb.append(' ');
            }
            final int c = b.charAt(i);
            sb.append(EncodingRecogniser.hexChar[(c & 0xF000) >>> 12]);
            sb.append(EncodingRecogniser.hexChar[(c & 0xF00) >>> 8]);
            sb.append(EncodingRecogniser.hexChar[(c & 0xF0) >>> 4]);
            sb.append(EncodingRecogniser.hexChar[c & 0xF]);
        }
        return sb.toString();
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
                new CMPAboutJBox("Encoding Recogniser", "1.2", "Helps you determine a file's encoding.", "", "freeware", "2010-03-23", 2007, "Roedy Green", "ENCODINGRECOGNISER", "1.5");
            }
        });
    }
    
    private void classifyBOM() {
        if (this.rawByteSample.length < 4) {
            return;
        }
        final int b0 = this.rawByteSample[0] & 0xFF;
        final int b2 = this.rawByteSample[1] & 0xFF;
        final int b3 = this.rawByteSample[2] & 0xFF;
        final int b4 = this.rawByteSample[3] & 0xFF;
        if (b0 == 255 && b2 == 254 && b3 == 0 && b4 == 0) {
            this.instructions.setText("Hint: the file starts with a UTF-32LE BOM.");
        }
        else if (b0 == 0 && b2 == 0 && b3 == 254 && b4 == 255) {
            this.instructions.setText("Hint: the file starts with a UTF-32BE BOM.");
        }
        else if (b0 == 255 && b2 == 254) {
            this.instructions.setText("Hint: the file starts with a UTF-16LE BOM.");
        }
        else if (b0 == 254 && b2 == 255) {
            this.instructions.setText("Hint: the file starts with a UTF-16BE BOM.");
        }
        else if (b0 == 239 && b2 == 187 && b3 == 191) {
            this.instructions.setText("Hint: the file starts with a UTF-8 BOM.");
        }
        else {
            this.instructions.setText("Hint: the file has no Unicode BOM.");
        }
    }
    
    private void displaySampleWithCurrentEncoding() {
        final String encodingName = (String)this.chooseEncoding.getSelectedItem();
        try {
            final int hexByteSampleSize = Math.min(this.rawByteSample.length, 22);
            this.hexBytes.setText(toHexString(this.rawByteSample, hexByteSampleSize));
            this.translatedBytes = new String(this.rawByteSample, encodingName);
            final int hexCharSampleSize = Math.min(this.translatedBytes.length(), 12);
            this.hexChars.setText(toHexString(this.translatedBytes, hexCharSampleSize));
            this.displayFileContents.setText(this.translatedBytes);
        }
        catch (UnsupportedEncodingException e) {
            this.instructions.setText("Unsupported encoding.  Please report program bug.");
        }
    }
    
    private void hookComponents() {
        this.chooseFile.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                final JFileChooser fc = new JFileChooser();
                switch (fc.showOpenDialog(EncodingRecogniser.this)) {
                    case 0: {
                        final File file = fc.getSelectedFile();
                        try {
                            EncodingRecogniser.this.fileName.setText(file.getCanonicalPath());
                        }
                        catch (IOException evt) {
                            EncodingRecogniser.this.fileName.setText(file.getAbsolutePath());
                        }
                        try {
                            final int sampleSize = (int)Math.min(file.length(), 1048576L);
                            final FileInputStream fis = new FileInputStream(file);
                            final byte[] b = new byte[sampleSize];
                            final int bytesRead = fis.read(b, 0, sampleSize);
                            if (bytesRead != sampleSize) {
                                throw new IOException("cannot read file");
                            }
                            EncodingRecogniser.this.rawByteSample = b;
                            fis.close();
                            EncodingRecogniser.this.displaySampleWithCurrentEncoding();
                            EncodingRecogniser.this.classifyBOM();
                        }
                        catch (IOException e2) {
                            EncodingRecogniser.this.fileName.setText("Cannot read " + EncodingRecogniser.this.fileName.getText());
                        }
                    }
                }
            }
        });
        this.chooseEncoding.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent e) {
                EncodingRecogniser.this.displaySampleWithCurrentEncoding();
            }
        });
    }
    
    private void layoutComponents(final Container contentPane) {
        contentPane.add(this.title, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0, 17, 0, new Insets(10, 10, 0, 10), 0, 0));
        contentPane.add(this.title2, new GridBagConstraints(0, 1, 2, 1, 0.0, 0.0, 17, 0, new Insets(0, 10, 5, 10), 0, 0));
        contentPane.add(this.chooseFile, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, 17, 0, new Insets(5, 10, 5, 10), 0, 0));
        contentPane.add(this.fileLabel, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, 17, 0, new Insets(5, 0, 5, 10), 0, 0));
        contentPane.add(this.encodingLabel, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, 13, 0, new Insets(5, 5, 5, 10), 0, 0));
        contentPane.add(this.fileName, new GridBagConstraints(1, 3, 1, 1, 50.0, 0.0, 17, 2, new Insets(5, 0, 5, 10), 250, 0));
        contentPane.add(this.chooseEncoding, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0, 13, 0, new Insets(5, 10, 5, 10), 0, 0));
        contentPane.add(this.hexByteLabel, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, 13, 0, new Insets(5, 10, 5, 10), 0, 0));
        contentPane.add(this.hexBytes, new GridBagConstraints(1, 4, 2, 1, 0.0, 0.0, 13, 2, new Insets(5, 0, 5, 10), 250, 0));
        contentPane.add(this.hexCharLabel, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, 13, 0, new Insets(5, 10, 5, 10), 0, 0));
        contentPane.add(this.hexChars, new GridBagConstraints(1, 5, 2, 1, 0.0, 0.0, 13, 2, new Insets(5, 0, 5, 10), 250, 0));
        contentPane.add(this.scroller, new GridBagConstraints(0, 6, 3, 1, 100.0, 100.0, 10, 1, new Insets(5, 10, 5, 10), 0, 0));
        contentPane.add(this.instructions, new GridBagConstraints(0, 7, 3, 1, 0.0, 0.0, 10, 1, new Insets(5, 10, 10, 10), 0, 0));
    }
    
    public static void main(final String[] args) {
        HybridJ.fireup(new EncodingRecogniser(), "Encoding Recogniser 1.2", 636, 528);
    }
    
    static {
        BACKGROUND_FOR_BODY = Build.BACKGROUND_FOR_BLENDING;
        BACKGROUND_FOR_INSTRUCTIONS = new Color(16316664);
        FOREGROUND_FOR_INSTRUCTIONS = new Color(3381521);
        FOREGROUND_FOR_LABEL = new Color(176);
        FOREGROUND_FOR_TITLE = new Color(14423100);
        FONT_FOR_INSTRUCTIONS = FontFactory.build("Dialog", 0, 12);
        FONT_FOR_TITLE = FontFactory.build("Dialog", 1, 15);
        FONT_FOR_TITLE2 = FontFactory.build("Dialog", 0, 14);
        hexChar = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
    }
}
