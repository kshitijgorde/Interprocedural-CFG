// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.masmbalancer;

import com.mindprod.common15.FontFactory;
import com.mindprod.common11.Build;
import com.mindprod.common13.HybridJ;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingUtilities;
import com.mindprod.common15.Play;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.Clipboard;
import com.mindprod.common11.VersionCheck;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JEditorPane;
import com.mindprod.common13.JEButton;
import javax.swing.JComboBox;
import java.awt.Container;
import java.awt.Font;
import java.awt.Color;
import java.awt.datatransfer.ClipboardOwner;
import javax.swing.JApplet;

public final class MASMBalancer extends JApplet implements ClipboardOwner, Runnable
{
    private static final int APPLET_HEIGHT = 900;
    private static final int APPLET_WIDTH = 758;
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    private static final String RELEASE_DATE = "2011-02-07";
    private static final String TITLE_STRING = "MASM Balancer";
    private static final String VERSION_STRING = "1.2";
    private static final Color BACKGROUND_FOR_BODY;
    private static final Color BACKGROUND_FOR_EDITABLE;
    private static final Color FOREGROUND_FOR_EDITABLE;
    private static final Color FOREGROUND_FOR_INSTRUCTIONS;
    private static final Color FOREGROUND_FOR_TITLE;
    private static final Font FONT_FOR_TITLE;
    private static final Font FONT_FOR_TITLE2;
    private static final Font INSTRUCTIONS_FONT;
    private Balancer balancer;
    private Container contentPane;
    private JComboBox whatToBalance;
    private JEButton paste;
    private JEditorPane instructions;
    private JEditorPane jep;
    private JLabel progress;
    private JLabel title;
    private JLabel title2;
    private JScrollPane jepScrollPane;
    private String rawClipboard;
    
    public void destroy() {
        this.contentPane = null;
        this.whatToBalance = null;
        this.paste = null;
        this.instructions = null;
        this.jep = null;
        this.progress = null;
        this.title2 = null;
        this.title = null;
        this.jepScrollPane = null;
        this.rawClipboard = null;
        this.balancer = null;
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
        final String cookedClipboard = MASMState.colorise(this.rawClipboard, this.balancer);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    MASMBalancer.this.jep.setText(cookedClipboard);
                    MASMBalancer.this.progress.setText("");
                }
                catch (Exception e) {
                    Play.play(MASMBalancer.class, "sound/echo.au");
                    System.err.println("Unable to render that clipboard because of " + e.getMessage());
                    System.err.println("cooked: [" + cookedClipboard + "]");
                    MASMBalancer.this.jep.setText("<html><body>Unable to render that clipboard</body></html>");
                }
            }
        });
    }
    
    private void buildComponents() {
        this.contentPane.setBackground(MASMBalancer.BACKGROUND_FOR_BODY);
        (this.title = new JLabel("MASM Balancer 1.2")).setFont(MASMBalancer.FONT_FOR_TITLE);
        this.title.setForeground(MASMBalancer.FOREGROUND_FOR_TITLE);
        (this.title2 = new JLabel("released:2011-02-07 build:9411")).setFont(MASMBalancer.FONT_FOR_TITLE2);
        this.title2.setForeground(MASMBalancer.FOREGROUND_FOR_TITLE);
        (this.whatToBalance = new JComboBox()).setEditable(false);
        this.whatToBalance.setModel(new DefaultComboBoxModel<Balancer>(Balancer.values()));
        this.whatToBalance.setSelectedItem(Balancer.ALL);
        (this.jep = new JEditorPane()).setEditable(false);
        this.jep.setContentType("text/html");
        this.jep.setForeground(MASMBalancer.FOREGROUND_FOR_EDITABLE);
        this.jep.setBackground(MASMBalancer.BACKGROUND_FOR_EDITABLE);
        this.jep.setMargin(new Insets(5, 5, 5, 5));
        this.jepScrollPane = new JScrollPane(this.jep, 20, 30);
        final FastCat sb = new FastCat(6);
        sb.append("<html><body text=\"#339911\" bgcolor=\"#f8f8f8\" link=\"#ff0000\" vlink=\"#330099\" alink=\"#000800\">");
        sb.append("<font face=\"Dialog,SansSerif,sans-serif\">");
        sb.append("<ul><li>Copy MASM source code with possibly unbalanced segment/ends, macro/endm, proc/endp or if/endif into the clipboard from your editor or IDE. In Windows use Ctrl-C.</li>\n");
        sb.append("<li>Then click <strong>Paste</strong> to paste it into this program.</li>\n");
        sb.append("<li>Anomalies in the background colours of the operators should help you quickly find the error.</li></ul>\n");
        sb.append("</font></body></html>");
        final String instructionsHTML = sb.toString();
        (this.instructions = new JEditorPane()).setContentType("text/html");
        this.instructions.setEditable(false);
        this.instructions.setMargin(new Insets(5, 5, 5, 5));
        try {
            this.instructions.setText(instructionsHTML);
        }
        catch (Exception e) {
            Play.play(MASMBalancer.class, "sound/echo.au");
            System.err.println("Because of Sun bug, unable to display instructions, please exit browser and restart");
            e.printStackTrace(System.err);
            System.err.println(instructionsHTML);
        }
        this.paste = new JEButton("Paste");
        (this.progress = new JLabel()).setFont(MASMBalancer.INSTRUCTIONS_FONT);
        this.progress.setForeground(MASMBalancer.FOREGROUND_FOR_INSTRUCTIONS);
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
                new CMPAboutJBox(Misc.getParentFrame(MASMBalancer.this), "MASM Balancer", "1.2", "Helps you balance mismatched proc/endp segment/ends if/endif in MASM source code.", "", "freeware", "2011-02-07", 2011, "Roedy Green", "MASMBALANCER", "1.5");
            }
        });
    }
    
    private void colouriseAndDisplayClipboard() {
        this.progress.setText("Collecting the clipboard. Please be patient...");
        this.rawClipboard = ClipboardPoker.getClip(this);
        this.progress.setText("Colorising the clipboard. Please be patient...");
        if (this.rawClipboard != null && this.rawClipboard.length() > 0) {
            Play.play(MASMBalancer.class, "sound/cork.au");
            this.balancer = (Balancer)this.whatToBalance.getSelectedItem();
            new Thread(this).start();
        }
        else {
            Play.play(MASMBalancer.class, "sound/echo.au");
            System.err.println("empty clipboard");
            this.jep.setText("<html><body>Empty clipboard</body></html>");
        }
    }
    
    private void hookListeners() {
        this.paste.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                MASMBalancer.this.colouriseAndDisplayClipboard();
            }
        });
        this.whatToBalance.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent e) {
                if (e.getStateChange() == 1) {
                    MASMBalancer.this.recoloriseAndDisplayClipboard();
                }
            }
        });
    }
    
    private void layoutComponents() {
        this.contentPane.setLayout(new GridBagLayout());
        this.contentPane.add(this.title, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, 17, 0, new Insets(10, 10, 5, 5), 0, 0));
        this.contentPane.add(this.title2, new GridBagConstraints(1, 0, 2, 1, 0.0, 0.0, 13, 0, new Insets(10, 5, 5, 10), 0, 0));
        this.contentPane.add(this.whatToBalance, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, 17, 0, new Insets(5, 10, 5, 5), 0, 0));
        this.contentPane.add(this.jepScrollPane, new GridBagConstraints(0, 2, 3, 1, 100.0, 100.0, 10, 1, new Insets(5, 10, 5, 10), 0, 0));
        this.contentPane.add(this.instructions, new GridBagConstraints(0, 3, 2, 1, 90.0, 0.0, 17, 1, new Insets(5, 10, 5, 5), 40, 0));
        this.contentPane.add(this.paste, new GridBagConstraints(2, 3, 1, 1, 10.0, 0.0, 13, 0, new Insets(5, 5, 10, 10), 10, 10));
        this.contentPane.add(this.progress, new GridBagConstraints(0, 4, 3, 1, 100.0, 0.0, 17, 1, new Insets(5, 10, 10, 5), 40, 0));
    }
    
    private void recoloriseAndDisplayClipboard() {
        final Balancer oldBalancer = this.balancer;
        this.balancer = (Balancer)this.whatToBalance.getSelectedItem();
        if (this.balancer != oldBalancer && this.rawClipboard != null && this.rawClipboard.length() > 0) {
            this.progress.setText("Colorising the clipboard. Please be patient...");
            new Thread(this).start();
        }
    }
    
    public static void main(final String[] args) {
        HybridJ.fireup(new MASMBalancer(), "MASM Balancer 1.2", 758, 900);
    }
    
    static {
        BACKGROUND_FOR_BODY = Build.BACKGROUND_FOR_BLENDING;
        BACKGROUND_FOR_EDITABLE = Color.WHITE;
        FOREGROUND_FOR_EDITABLE = Color.BLACK;
        FOREGROUND_FOR_INSTRUCTIONS = new Color(32768);
        FOREGROUND_FOR_TITLE = new Color(14423100);
        FONT_FOR_TITLE = FontFactory.build("Dialog", 1, 15);
        FONT_FOR_TITLE2 = FontFactory.build("Dialog", 0, 14);
        INSTRUCTIONS_FONT = FontFactory.build("Dialog", 0, 13);
    }
}
