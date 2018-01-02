// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.bracebalancer;

import com.mindprod.common15.FontFactory;
import com.mindprod.common11.Build;
import com.mindprod.common13.HybridJ;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import com.mindprod.common11.ClipboardPoker;
import com.mindprod.common11.Misc;
import com.mindprod.common13.CMPAboutJBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import com.mindprod.common15.Laf;
import javax.swing.JMenuBar;
import com.mindprod.fastcat.FastCat;
import java.awt.Component;
import java.awt.Insets;
import com.mindprod.common11.ST;
import com.mindprod.entities.EntifyStrings;
import javax.swing.SwingUtilities;
import com.mindprod.common15.Play;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.Clipboard;
import com.mindprod.common11.VersionCheck;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JEditorPane;
import com.mindprod.common13.JEButton;
import java.awt.Container;
import java.awt.Font;
import java.awt.Color;
import java.awt.datatransfer.ClipboardOwner;
import javax.swing.JApplet;

public final class BraceBalancer extends JApplet implements ClipboardOwner, Runnable
{
    private static final int APPLET_HEIGHT = 900;
    private static final int APPLET_WIDTH = 758;
    private static final String DOCTYPE = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">";
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 2010-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    private static final String RELEASE_DATE = "2011-01-06";
    private static final String TITLE_STRING = "Brace Balancer";
    private static final String VERSION_STRING = "1.1";
    private static final Color BACKGROUND_FOR_BODY;
    private static final Color BACKGROUND_FOR_EDITABLE;
    private static final Color FOREGROUND_FOR_EDITABLE;
    private static final Color FOREGROUND_FOR_TITLE;
    private static final Font FONT_FOR_TITLE;
    private static final Font FONT_FOR_TITLE2;
    private Container contentPane;
    private JEButton go;
    private JEditorPane instructions;
    private JEditorPane jep;
    private JLabel title;
    private JLabel title2;
    private JScrollPane jepScrollPane;
    private String rawClipboard;
    
    public void destroy() {
        this.contentPane = null;
        this.go = null;
        this.instructions = null;
        this.jep = null;
        this.jepScrollPane = null;
        this.rawClipboard = null;
        this.title = null;
        this.title2 = null;
    }
    
    public void init() {
        this.contentPane = this.getContentPane();
        if (!VersionCheck.isJavaVersionOK(1, 5, 0, this.contentPane)) {
            this.stop();
            this.destroy();
        }
        this.buildMenu();
        this.buildComponents();
        this.layoutComponents();
        this.hookListeners();
        this.validate();
        this.setVisible(true);
    }
    
    public void lostOwnership(final Clipboard clipboard, final Transferable contents) {
    }
    
    public void run() {
        final String cookedClipboard = colouriseBraces(this.rawClipboard);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    BraceBalancer.this.jep.setText(cookedClipboard);
                }
                catch (Exception e) {
                    Play.play(BraceBalancer.class, "sound/echo.au");
                    System.err.println("Unable to render that clipboard because of " + e.getMessage());
                    System.err.println("cooked: [" + cookedClipboard + "]");
                    BraceBalancer.this.jep.setText("<html><body>Unable to render that clipboard</body></html>");
                }
            }
        });
    }
    
    private static String colouriseBraces(final String big) {
        boolean inQuotes = false;
        int braceNesting = 0;
        int bracketNesting = 0;
        int parenthesisNesting = 0;
        final StringBuilder sb = new StringBuilder(big.length() * 5);
        sb.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
        sb.append("<html><body><pre><span>");
        for (int i = 0; i < big.length(); ++i) {
            final char c = big.charAt(i);
            switch (c) {
                case '{': {
                    if (!inQuotes) {
                        ++braceNesting;
                        sb.append(newColour(braceNesting, bracketNesting, parenthesisNesting));
                    }
                    sb.append(c);
                    break;
                }
                case '}': {
                    if (!inQuotes) {
                        sb.append(c);
                        --braceNesting;
                        sb.append(newColour(braceNesting, bracketNesting, parenthesisNesting));
                        break;
                    }
                    sb.append(c);
                    break;
                }
                case '[': {
                    if (!inQuotes) {
                        ++bracketNesting;
                        sb.append(newColour(braceNesting, bracketNesting, parenthesisNesting));
                    }
                    sb.append(c);
                    break;
                }
                case ']': {
                    if (!inQuotes) {
                        sb.append(c);
                        --bracketNesting;
                        sb.append(newColour(braceNesting, bracketNesting, parenthesisNesting));
                        break;
                    }
                    sb.append(c);
                    break;
                }
                case '(': {
                    if (!inQuotes) {
                        ++parenthesisNesting;
                        sb.append(newColour(braceNesting, bracketNesting, parenthesisNesting));
                    }
                    sb.append(c);
                    break;
                }
                case ')': {
                    if (!inQuotes) {
                        sb.append(c);
                        --parenthesisNesting;
                        sb.append(newColour(braceNesting, bracketNesting, parenthesisNesting));
                        break;
                    }
                    sb.append(c);
                    break;
                }
                case '\"': {
                    inQuotes = !inQuotes;
                    sb.append("&quot;");
                    break;
                }
                default: {
                    sb.append(EntifyStrings.toHTMLEntity(c));
                    break;
                }
            }
        }
        sb.append("</span></pre></body></html>");
        return sb.toString();
    }
    
    private static String newColour(int braceNesting, int bracketNesting, int parenthesisNesting) {
        if (braceNesting > 5) {
            braceNesting = 5;
        }
        if (bracketNesting > 5) {
            bracketNesting = 5;
        }
        if (parenthesisNesting > 5) {
            parenthesisNesting = 5;
        }
        int rgb;
        if (braceNesting < 0 || bracketNesting < 0 || parenthesisNesting < 0) {
            rgb = 16776960;
        }
        else {
            final int r = 255 - 32 * braceNesting;
            final int g = 255 - 32 * bracketNesting;
            final int b = 255 - 32 * parenthesisNesting;
            rgb = (r << 16 | g << 8 | b);
        }
        return "</span><span style=\"background-color:#" + ST.toLZHexString(rgb, 6) + "\">";
    }
    
    private void buildComponents() {
        this.contentPane.setBackground(BraceBalancer.BACKGROUND_FOR_BODY);
        (this.title = new JLabel("Brace Balancer 1.1")).setFont(BraceBalancer.FONT_FOR_TITLE);
        this.title.setForeground(BraceBalancer.FOREGROUND_FOR_TITLE);
        (this.title2 = new JLabel("released:2011-01-06 build:9411")).setFont(BraceBalancer.FONT_FOR_TITLE2);
        this.title2.setForeground(BraceBalancer.FOREGROUND_FOR_TITLE);
        (this.jep = new JEditorPane()).setEditable(false);
        this.jep.setContentType("text/html");
        this.jep.setForeground(BraceBalancer.FOREGROUND_FOR_EDITABLE);
        this.jep.setBackground(BraceBalancer.BACKGROUND_FOR_EDITABLE);
        this.jep.setMargin(new Insets(5, 5, 5, 5));
        this.jepScrollPane = new JScrollPane(this.jep, 20, 30);
        final FastCat sb = new FastCat(6);
        sb.append("<html><body text=\"#339911\" bgcolor=\"#f8f8f8\" link=\"#ff0000\" vlink=\"#330099\" alink=\"#000800\">");
        sb.append("<font face=\"Dialog,SansSerif,sans-serif\">");
        sb.append("<ul><li>Copy Java, C, C++, Pascal, Modula etc. source code with possibly unbalanced {}[]() into the clipboard from your editor or IDE. In Windows use Ctrl-C.</li>");
        sb.append("<li>Then click <strong>Paste</strong> to paste it into this program.</li>");
        sb.append("<li>Anomalies in the background colours of the code should help you quickly find the error.</li></ul>");
        sb.append("</font></body></html>");
        final String instructionsHTML = sb.toString();
        (this.instructions = new JEditorPane()).setContentType("text/html");
        this.instructions.setEditable(false);
        this.instructions.setMargin(new Insets(5, 5, 5, 5));
        try {
            this.instructions.setText(instructionsHTML);
        }
        catch (Exception e) {
            Play.play(BraceBalancer.class, "sound/echo.au");
            System.err.println("Because of Sun bug, unable to display instructions, please exit browser and restart");
            e.printStackTrace(System.err);
            System.err.println(instructionsHTML);
        }
        this.go = new JEButton("Paste");
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
                new CMPAboutJBox(Misc.getParentFrame(BraceBalancer.this), "Brace Balancer", "1.1", "Helps you balance mismatched {} [] () in source code.", "", "freeware", "2011-01-06", 2010, "Roedy Green", "BRACEBALANCER", "1.5");
            }
        });
    }
    
    private void colouriseAndDisplayClipboard() {
        this.rawClipboard = ClipboardPoker.getClip(this);
        if (this.rawClipboard != null && this.rawClipboard.length() > 0) {
            Play.play(BraceBalancer.class, "sound/cork.au");
            new Thread(this).start();
        }
        else {
            Play.play(BraceBalancer.class, "sound/echo.au");
            System.err.println("empty clipboard");
            this.jep.setText("<html><body>Empty clipboard</body></html>");
        }
    }
    
    private void hookListeners() {
        this.go.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                BraceBalancer.this.colouriseAndDisplayClipboard();
            }
        });
    }
    
    private void layoutComponents() {
        this.contentPane.setLayout(new GridBagLayout());
        this.contentPane.add(this.title, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, 17, 0, new Insets(10, 10, 5, 5), 0, 0));
        this.contentPane.add(this.title2, new GridBagConstraints(1, 0, 2, 1, 0.0, 0.0, 13, 0, new Insets(10, 5, 5, 10), 0, 0));
        this.contentPane.add(this.jepScrollPane, new GridBagConstraints(0, 1, 3, 1, 100.0, 90.0, 10, 1, new Insets(5, 10, 5, 10), 0, 0));
        this.contentPane.add(this.instructions, new GridBagConstraints(0, 2, 2, 1, 90.0, 5.0, 17, 1, new Insets(5, 10, 10, 5), 40, 10));
        this.contentPane.add(this.go, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, 13, 0, new Insets(5, 5, 1, 10), 10, 10));
    }
    
    public static void main(final String[] args) {
        HybridJ.fireup(new BraceBalancer(), "Brace Balancer 1.1", 758, 900);
    }
    
    static {
        BACKGROUND_FOR_BODY = Build.BACKGROUND_FOR_BLENDING;
        BACKGROUND_FOR_EDITABLE = Color.WHITE;
        FOREGROUND_FOR_EDITABLE = Color.BLACK;
        FOREGROUND_FOR_TITLE = new Color(14423100);
        FONT_FOR_TITLE = FontFactory.build("Dialog", 1, 15);
        FONT_FOR_TITLE2 = FontFactory.build("Dialog", 0, 14);
    }
}
