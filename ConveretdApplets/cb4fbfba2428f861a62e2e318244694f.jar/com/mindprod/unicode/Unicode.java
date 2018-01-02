// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.unicode;

import javax.swing.JPanel;
import com.mindprod.common13.HybridJ;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import com.mindprod.common11.CMPAboutBox;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import com.mindprod.common15.Laf;
import javax.swing.JMenuBar;
import java.util.Collection;
import java.util.Arrays;
import java.util.Vector;
import java.awt.GraphicsEnvironment;
import com.mindprod.common11.FontFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import com.mindprod.common11.VersionCheck;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Container;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JApplet;

public final class Unicode extends JApplet
{
    private static final int APPLET_HEIGHT = 775;
    private static final int APPLET_WIDTH = 560;
    private static final String defaultFontFamily = "Dialog";
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 1996-2011 Nic Fulton nic.fulton@reuters.com, 1996-2010 Roedy Green, Canadian Mind Products http://mindprod.com";
    private static final String RELEASE_DATE = "2010-11-27";
    private static final String TITLE_STRING = "Unicode Font Viewer";
    private static final String VERSION_STRING = "1.8";
    private static final Color BACKGROUND_FOR_APPLET;
    private static final Color BACKGROUND_FOR_INSTRUCTIONS;
    private static final Color FOREGROUND_FOR_INSTRUCTIONS;
    private static final Color FOREGROUND_FOR_TITLE;
    private static final Font FONT_FOR_TITLE;
    private static final Font FONT_FOR_TITLE2;
    private Container contentPane;
    private JComboBox fontChoices;
    private JLabel title;
    private JLabel title2;
    private JTextArea instructions;
    private RigidPanel theRigidPanel;
    
    public void destroy() {
        this.contentPane = null;
        this.fontChoices = null;
        this.instructions = null;
        this.theRigidPanel = null;
        this.title2 = null;
        this.title = null;
    }
    
    public void init() {
        this.contentPane = this.getContentPane();
        if (!VersionCheck.isJavaVersionOK(1, 5, 0, this.contentPane)) {
            return;
        }
        this.buildMenu();
        this.contentPane.setLayout(new GridBagLayout());
        this.contentPane.setBackground(Unicode.BACKGROUND_FOR_APPLET);
        this.buildComponents();
        this.layoutComponents();
        this.validate();
        this.setVisible(true);
    }
    
    private void buildComponents() {
        (this.title = new JLabel("Unicode Font Viewer 1.8")).setFont(Unicode.FONT_FOR_TITLE);
        this.title.setForeground(Unicode.FOREGROUND_FOR_TITLE);
        (this.title2 = new JLabel("released:2010-11-27 build:9411")).setFont(Unicode.FONT_FOR_TITLE2);
        this.title2.setForeground(Unicode.FOREGROUND_FOR_TITLE);
        this.theRigidPanel = new RigidPanel();
        this.buildFontChoices();
        this.fontChoices.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                Unicode.this.theRigidPanel.setFontFamily((String)Unicode.this.fontChoices.getSelectedItem());
            }
        });
        (this.instructions = new JTextArea("To view a given hex code:\nSelect the first digit with the corresponding white square at the bottom.\nThen select the second digit with the corresponding white square on the right.\nThen sight (but don't click) the third digit with the corresponding black digit on the bottom.\nThen sight (but don't click) the fourth digit with the corresponding black square on the right.\nSelect various fonts to see how the glyphs change.\nYou can also click a grid square to see it magnified in the bottom right corner.", 7, 80)).setBackground(Unicode.BACKGROUND_FOR_INSTRUCTIONS);
        this.instructions.setForeground(Unicode.FOREGROUND_FOR_INSTRUCTIONS);
        this.instructions.setFont(FontFactory.build("Dialog", 0, 12));
        this.instructions.setEditable(false);
    }
    
    private void buildFontChoices() {
        final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        final String[] fontNames = ge.getAvailableFontFamilyNames();
        final Vector<String> v = new Vector<String>(fontNames.length + 1);
        v.addAll(Arrays.asList(fontNames));
        (this.fontChoices = new JComboBox((Vector<E>)v)).setMaximumSize(this.fontChoices.getPreferredSize());
        this.fontChoices.setEditable(true);
        this.fontChoices.setSelectedItem("Dialog");
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
                new CMPAboutBox("Unicode Font Viewer", "1.8", "Display Unicode Characters", "in range 0..FFFF", "freeware", "2010-11-27", 1996, "Roedy Green", "UNICODE", "1.1");
            }
        });
    }
    
    private void layoutComponents() {
        this.contentPane.add(this.title, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, 17, 0, new Insets(10, 10, 10, 5), 0, 0));
        this.contentPane.add(this.title2, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, 17, 0, new Insets(10, 5, 10, 5), 0, 0));
        this.contentPane.add(this.fontChoices, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, 10, 1, new Insets(0, 10, 5, 5), 0, 0));
        this.contentPane.add(this.theRigidPanel, new GridBagConstraints(0, 1, 3, 1, 10.0, 10.0, 10, 1, new Insets(5, 10, 0, 10), 0, 0));
        this.contentPane.add(this.instructions, new GridBagConstraints(0, 2, 3, 1, 0.0, 1.0, 10, 1, new Insets(5, 10, 10, 10), 0, 0));
    }
    
    public static void main(final String[] args) {
        HybridJ.fireup(new Unicode(), "Unicode Font Viewer 1.8", 560, 775);
    }
    
    static {
        BACKGROUND_FOR_APPLET = new Color(16445670);
        BACKGROUND_FOR_INSTRUCTIONS = Color.white;
        FOREGROUND_FOR_INSTRUCTIONS = new Color(25600);
        FOREGROUND_FOR_TITLE = new Color(14423100);
        FONT_FOR_TITLE = FontFactory.build("Dialog", 1, 16);
        FONT_FOR_TITLE2 = FontFactory.build("Dialog", 0, 14);
    }
    
    static final class RigidPanel extends JPanel
    {
        private final TextPanel theTextPanel;
        
        public void setFontFamily(final String theFontFamily) {
            this.theTextPanel.setFontFamily(theFontFamily);
        }
        
        RigidPanel() {
            this.setLayout(null);
            this.setBackground(Color.white);
            this.setSize(475, 475);
            final CharPanel theCharPanel = new CharPanel();
            this.add(theCharPanel);
            theCharPanel.setBounds(400, 400, 75, 75);
            this.add(this.theTextPanel = new TextPanel(theCharPanel));
            this.theTextPanel.setBounds(0, 0, 400, 400);
            final BottomPanel theBottomPanel = new BottomPanel(this.theTextPanel);
            this.add(theBottomPanel);
            theBottomPanel.setBounds(0, 400, 400, 75);
            final RightPanel theRightPanel = new RightPanel(this.theTextPanel);
            this.add(theRightPanel);
            theRightPanel.setBounds(400, 0, 75, 400);
        }
    }
}
