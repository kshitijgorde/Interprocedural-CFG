// 
// Decompiled by Procyon v0.5.30
// 

package jstella.desktop;

import java.awt.Cursor;
import java.io.InputStream;
import java.awt.Frame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import java.awt.EventQueue;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import javax.swing.UIManager;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import javax.swing.JTextArea;
import javax.swing.JSeparator;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JEditorPane;
import javax.swing.JButton;
import java.io.File;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class JStellaSetupWizard extends JFrame
{
    private JComponent myCurrentWizardComponent;
    private File myDefaultDataDir;
    private JButton ButtonCustomDataDir;
    private JButton ButtonDefaultDataDir;
    private JButton ButtonJStellaClassic;
    private JButton ButtonSelectROMs;
    private JButton ButtonSetup;
    private JButton ButtonSkipImport;
    private JEditorPane EPWelcome;
    private JLabel LabelDefaultDataDir;
    private JLabel LabelStep1;
    private JLabel LabelStep2;
    private JPanel PanelSouth;
    private JPanel PanelStep1;
    private JPanel PanelStep2;
    private JPanel PanelWelcome;
    private JSeparator SepStep1;
    private JSeparator SepStep2;
    private JTextArea TAStep1;
    private JTextArea TAStep2;
    
    public JStellaSetupWizard() {
        this.myCurrentWizardComponent = null;
        this.myDefaultDataDir = null;
        this.initComponents();
        this.initDefaults();
        this.showWizardScreen(0);
    }
    
    private void initComponents() {
        this.PanelWelcome = new JPanel();
        this.EPWelcome = new JEditorPane();
        this.ButtonSetup = new JButton();
        this.PanelStep1 = new JPanel();
        this.LabelStep1 = new JLabel();
        this.TAStep1 = new JTextArea();
        this.SepStep1 = new JSeparator();
        this.ButtonCustomDataDir = new JButton();
        this.ButtonDefaultDataDir = new JButton();
        this.LabelDefaultDataDir = new JLabel();
        this.PanelStep2 = new JPanel();
        this.LabelStep2 = new JLabel();
        this.TAStep2 = new JTextArea();
        this.SepStep2 = new JSeparator();
        this.ButtonSelectROMs = new JButton();
        this.ButtonSkipImport = new JButton();
        this.PanelSouth = new JPanel();
        this.ButtonJStellaClassic = new JButton();
        this.PanelWelcome.setLayout(new GridBagLayout());
        this.EPWelcome.setBackground(UIManager.getDefaults().getColor("Panel.background"));
        this.EPWelcome.setContentType("text/html");
        this.EPWelcome.setText("<html>\r\n  <head>\r\n\r\n  </head>\r\n  <body>\r\n    <p style=\"margin-top: 0\">\r\n      \r<div style=\"text-align: center;\"><font size=\"+3\">JStella</font><br>\n<font size=\"+2\">Java-based Atari 2600 Emulator</font>\n</div>\n<hr style=\"width: 100%; height: 2px;\"><br>\n\n<br>\nThis appears to be your first time running the program.&nbsp;\nJStella\nis an emulator for the Atari 2600, and thus allows you to play Atari\ngames on your computer.&nbsp; &nbsp;<br>\n<br>\nBecause of copyright reasons, JStella does not come with any games\n(&ldquo;ROMs&rdquo;).&nbsp; You must provide your\nown.&nbsp; The ROMs\nare not specific for JStella, and can be run in any 2600\nemulator.&nbsp; You can search the Internet for &ldquo;Atari\nROMs&rdquo; for more information.<br>\n<br>\n\n    </p>\r\n  </body>\r\n</html>\r\n");
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 100.0;
        gridBagConstraints.weighty = 100.0;
        gridBagConstraints.insets = new Insets(15, 15, 15, 15);
        this.PanelWelcome.add(this.EPWelcome, gridBagConstraints);
        this.ButtonSetup.setText("Start Setup");
        this.ButtonSetup.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaSetupWizard.this.ButtonSetupActionPerformed(evt);
            }
        });
        this.PanelWelcome.add(this.ButtonSetup, new GridBagConstraints());
        this.PanelStep1.setLayout(new GridBagLayout());
        this.LabelStep1.setFont(new Font("Tahoma", 1, 36));
        this.LabelStep1.setHorizontalAlignment(2);
        this.LabelStep1.setText("STEP 1");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = 17;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.PanelStep1.add(this.LabelStep1, gridBagConstraints);
        this.TAStep1.setColumns(20);
        this.TAStep1.setEditable(false);
        this.TAStep1.setFont(new Font("Tahoma", 0, 14));
        this.TAStep1.setLineWrap(true);
        this.TAStep1.setRows(5);
        this.TAStep1.setText("First, you must specify where you want to put the JStella data directory.");
        this.TAStep1.setWrapStyleWord(true);
        this.TAStep1.setOpaque(false);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 100.0;
        gridBagConstraints.weighty = 20.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.PanelStep1.add(this.TAStep1, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weighty = 5.0;
        this.PanelStep1.add(this.SepStep1, gridBagConstraints);
        this.ButtonCustomDataDir.setText("Use Custom Directory");
        this.ButtonCustomDataDir.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaSetupWizard.this.ButtonCustomDataDirActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.weighty = 200.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.PanelStep1.add(this.ButtonCustomDataDir, gridBagConstraints);
        this.ButtonDefaultDataDir.setText("Use Default Directory");
        this.ButtonDefaultDataDir.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaSetupWizard.this.ButtonDefaultDataDirActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        this.PanelStep1.add(this.ButtonDefaultDataDir, gridBagConstraints);
        this.LabelDefaultDataDir.setText("<DEFAULT>");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.PanelStep1.add(this.LabelDefaultDataDir, gridBagConstraints);
        this.PanelStep2.setLayout(new GridBagLayout());
        this.LabelStep2.setFont(new Font("Tahoma", 1, 36));
        this.LabelStep2.setText("STEP 2");
        this.LabelStep2.setHorizontalTextPosition(2);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = 17;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.PanelStep2.add(this.LabelStep2, gridBagConstraints);
        this.TAStep2.setColumns(20);
        this.TAStep2.setEditable(false);
        this.TAStep2.setFont(new Font("Tahoma", 0, 14));
        this.TAStep2.setLineWrap(true);
        this.TAStep2.setRows(5);
        this.TAStep2.setText("Next, you need to select the ROMs you wish to import.");
        this.TAStep2.setWrapStyleWord(true);
        this.TAStep2.setOpaque(false);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 100.0;
        gridBagConstraints.weighty = 20.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.PanelStep2.add(this.TAStep2, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weighty = 5.0;
        this.PanelStep2.add(this.SepStep2, gridBagConstraints);
        this.ButtonSelectROMs.setText("Select ROMs");
        this.ButtonSelectROMs.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaSetupWizard.this.ButtonSelectROMsActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weighty = 100.0;
        this.PanelStep2.add(this.ButtonSelectROMs, gridBagConstraints);
        this.ButtonSkipImport.setText("Skip Step");
        this.ButtonSkipImport.setEnabled(false);
        this.ButtonSkipImport.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaSetupWizard.this.ButtonSkipImportActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weighty = 100.0;
        this.PanelStep2.add(this.ButtonSkipImport, gridBagConstraints);
        this.setDefaultCloseOperation(3);
        this.setTitle("JStella Setup Wizard");
        this.PanelSouth.setLayout(new GridBagLayout());
        this.ButtonJStellaClassic.setText("JStella Classic");
        this.ButtonJStellaClassic.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaSetupWizard.this.ButtonJStellaClassicActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = 17;
        gridBagConstraints.weightx = 100.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.PanelSouth.add(this.ButtonJStellaClassic, gridBagConstraints);
        this.getContentPane().add(this.PanelSouth, "South");
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds((screenSize.width - 600) / 2, (screenSize.height - 477) / 2, 600, 477);
    }
    
    private void ButtonJStellaClassicActionPerformed(final ActionEvent evt) {
        this.startJStellaClassic();
    }
    
    private void ButtonSetupActionPerformed(final ActionEvent evt) {
        this.showWizardScreen(1);
    }
    
    private void ButtonCustomDataDirActionPerformed(final ActionEvent evt) {
        this.browseForCustomDataDir();
    }
    
    private void ButtonDefaultDataDirActionPerformed(final ActionEvent evt) {
        final boolean zSuccess = JStellaDesktop.setUserDataDirectory(this.myDefaultDataDir);
        if (zSuccess) {
            this.showWizardScreen(2);
        }
    }
    
    private void ButtonSelectROMsActionPerformed(final ActionEvent evt) {
        this.browseForROMs();
    }
    
    private void ButtonSkipImportActionPerformed(final ActionEvent evt) {
        this.gotoRepository();
    }
    
    public static void main(final String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JStellaSetupWizard().setVisible(true);
            }
        });
    }
    
    private void browseForCustomDataDir() {
        File zCurrentDir = JStellaDesktop.getUserDataDirectory();
        if (!zCurrentDir.isDirectory()) {
            zCurrentDir = this.myDefaultDataDir;
        }
        JStellaDesktop.configureFileBrowser(false, true, false, zCurrentDir, null, null);
        final int zResult = JStellaDesktop.getFileBrowser().showOpenDialog(this);
        if (zResult == 0) {
            final boolean zSuccess = JStellaDesktop.setUserDataDirectory(JStellaDesktop.getFileBrowser().getSelectedFile());
            if (zSuccess) {
                this.showWizardScreen(2);
            }
        }
    }
    
    private void browseForROMs() {
        JStellaDesktop.configureFileBrowser(true, true, true, null, null, null);
        final int zResult = JStellaDesktop.getFileBrowser().showOpenDialog(this);
        if (zResult == 0) {
            System.out.println("Selected file(s)==" + JStellaDesktop.getFileBrowser().getSelectedFiles());
            this.importROMsAndStandardMetadata(JStellaDesktop.getFileBrowser().getSelectedFiles());
        }
    }
    
    public void importROMsAndStandardMetadata(final File[] aROMFiles) {
        try {
            this.setWaitingMode(true);
            final InputStream zStream = JStellaImporter.class.getResourceAsStream("/jstella/resources/metadata/metadata.j26mc");
            if (zStream == null) {
                JOptionPane.showMessageDialog(this, "Could not find metadata for ROMs", "Error", 0);
            }
            JStellaImporter.launchROMAndMetadataImporter(this, aROMFiles, zStream, new ResumeWizard());
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
    
    private void initDefaults() {
        final String zHomeDir = System.getProperty("user.home");
        this.myDefaultDataDir = new File(zHomeDir, "jstella");
        this.LabelDefaultDataDir.setText("" + this.myDefaultDataDir.getPath());
    }
    
    private void setActiveWizardItem(final JComponent aComponent) {
        if (this.myCurrentWizardComponent != null) {
            this.getContentPane().remove(this.myCurrentWizardComponent);
        }
        this.myCurrentWizardComponent = aComponent;
        this.getContentPane().add(aComponent, "Center");
        aComponent.invalidate();
        this.getContentPane().validate();
        this.repaint();
    }
    
    private void showWizardScreen(final int aScreenNumber) {
        switch (aScreenNumber) {
            case 0: {
                this.setActiveWizardItem(this.PanelWelcome);
                break;
            }
            case 1: {
                this.setActiveWizardItem(this.PanelStep1);
                break;
            }
            case 2: {
                this.ButtonSkipImport.setEnabled(JStellaDesktop.hasGamesInRepository());
                this.setActiveWizardItem(this.PanelStep2);
                break;
            }
        }
    }
    
    private void startJStellaClassic() {
        JStellaDesktop.doClassicMode();
        this.setVisible(false);
    }
    
    protected void showWizard() {
        this.showWizardScreen(0);
        this.setVisible(true);
    }
    
    public void setWaitingMode(final boolean aWait) {
        this.setCursor(aWait ? Cursor.getPredefinedCursor(3) : null);
        this.getGlassPane().setVisible(aWait);
    }
    
    private void resumeWelcomeWizard() {
        this.setWaitingMode(false);
        this.gotoRepository();
    }
    
    private void gotoRepository() {
        JStellaDesktop.doRepositoryMode();
        this.setVisible(false);
    }
    
    public class ResumeWizard implements Runnable
    {
        public void run() {
            JStellaSetupWizard.this.resumeWelcomeWizard();
        }
    }
}
