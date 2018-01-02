// 
// Decompiled by Procyon v0.5.30
// 

package jstella.desktop;

import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.ObjectStreamException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.util.Map;
import jstella.runner.JStellaRunnerUtil;
import jstella.runner.JStellaGame;
import javax.swing.text.StyledDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.AttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.SimpleAttributeSet;
import java.awt.EventQueue;
import jstella.core.JSException;
import java.awt.Frame;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.AbstractButton;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Cursor;
import javax.swing.border.Border;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextPane;
import javax.swing.JSeparator;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JDialog;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.io.File;
import jstella.runner.Intercessor;
import javax.swing.JFrame;

public class JStellaPlayer extends JFrame implements Intercessor.IfcIntercessorClient
{
    private static final long serialVersionUID = -4623932099666097348L;
    private static final String WINDOW_TITLE = "JStella 0.95 (beta)";
    private String myJavaVersion;
    private Intercessor myIntercessor;
    private File myCurrentROMFile;
    private File myCurrentROMDirectory;
    private String myStateDirectory;
    private String myDefaultStateName;
    private boolean myJoystickEnabled;
    private JButton ButtonAboutOK;
    private ButtonGroup ButtonGroupChannels;
    private ButtonGroup ButtonGroupP0Difficulty;
    private ButtonGroup ButtonGroupP1Difficulty;
    private ButtonGroup ButtonGroupTVType;
    private JCheckBoxMenuItem CBMILetterBoxMode;
    private JCheckBoxMenuItem CBMIPaused;
    private JCheckBoxMenuItem CBMIPhosphorEnabled;
    private JCheckBoxMenuItem CBMISoundEnabled;
    private JDialog DialogAbout;
    private JMenuBar MBMain;
    private JMenuItem MIAbout;
    private JCheckBoxMenuItem MICBJoystickEnabled;
    private JMenuItem MIConfigure;
    private JMenuItem MIExit;
    private JMenuItem MIHelpContents;
    private JMenuItem MILoadROM;
    private JMenuItem MILoadState;
    private JMenuItem MIReset;
    private JMenuItem MISaveState;
    private JMenuItem MISelect;
    private JMenuItem MIVirtualJoystick;
    private JMenu MenuControls;
    private JMenu MenuFile;
    private JMenu MenuHelp;
    private JMenu MenuOptions;
    private JMenu MenuPlayer0Difficulty;
    private JMenu MenuPlayer1Difficulty;
    private JMenu MenuSoundChannels;
    private JMenu MenuSwitches;
    private JMenu MenuTVType;
    private JPanel PanelAboutSouth;
    private JRadioButtonMenuItem RBMIBWTelevision;
    private JRadioButtonMenuItem RBMIColorTelevision;
    private JRadioButtonMenuItem RBMIMonoSound;
    private JRadioButtonMenuItem RBMIPlayer0Amateur;
    private JRadioButtonMenuItem RBMIPlayer0Professional;
    private JRadioButtonMenuItem RBMIPlayer1Amateur;
    private JRadioButtonMenuItem RBMIPlayer1Professional;
    private JRadioButtonMenuItem RBMIStereoSound;
    private JSeparator SepFileA;
    private JSeparator SepFileB;
    private JSeparator SepOptionsA;
    private JSeparator SepOptionsB;
    private JSeparator SepOptionsC;
    private JTextPane TPAbout;
    
    public JStellaPlayer() {
        this.myJavaVersion = "<VERSION>";
        this.myIntercessor = null;
        this.myCurrentROMFile = null;
        this.myCurrentROMDirectory = null;
        this.myStateDirectory = "";
        this.myDefaultStateName = "";
        this.myJoystickEnabled = false;
        System.out.println("Initializing player...");
        this.initComponents();
        this.myJavaVersion = System.getProperty("java.version");
        System.out.println("Java version: " + this.myJavaVersion);
        System.out.println("JStella version: 0.95 (beta)");
        this.setTitle("JStella 0.95 (beta)");
        (this.myIntercessor = new Intercessor(this)).applyConfiguration(this.getConfiguration());
        this.myIntercessor.setAutoPauseMode(true);
        this.initAboutDialog();
        this.updateOptions();
        this.updateCartridgeStatus();
    }
    
    private void initComponents() {
        this.DialogAbout = new JDialog();
        this.PanelAboutSouth = new JPanel();
        this.ButtonAboutOK = new JButton();
        this.TPAbout = new JTextPane();
        this.ButtonGroupChannels = new ButtonGroup();
        this.ButtonGroupTVType = new ButtonGroup();
        this.ButtonGroupP0Difficulty = new ButtonGroup();
        this.ButtonGroupP1Difficulty = new ButtonGroup();
        this.MBMain = new JMenuBar();
        this.MenuFile = new JMenu();
        this.MILoadROM = new JMenuItem();
        this.SepFileA = new JSeparator();
        this.MISaveState = new JMenuItem();
        this.MILoadState = new JMenuItem();
        this.SepFileB = new JSeparator();
        this.MIExit = new JMenuItem();
        this.MenuSwitches = new JMenu();
        this.MIReset = new JMenuItem();
        this.MISelect = new JMenuItem();
        this.MenuTVType = new JMenu();
        this.RBMIBWTelevision = new JRadioButtonMenuItem();
        this.RBMIColorTelevision = new JRadioButtonMenuItem();
        this.MenuPlayer0Difficulty = new JMenu();
        this.RBMIPlayer0Amateur = new JRadioButtonMenuItem();
        this.RBMIPlayer0Professional = new JRadioButtonMenuItem();
        this.MenuPlayer1Difficulty = new JMenu();
        this.RBMIPlayer1Amateur = new JRadioButtonMenuItem();
        this.RBMIPlayer1Professional = new JRadioButtonMenuItem();
        this.MenuOptions = new JMenu();
        this.CBMIPaused = new JCheckBoxMenuItem();
        this.SepOptionsA = new JSeparator();
        this.CBMISoundEnabled = new JCheckBoxMenuItem();
        this.MenuSoundChannels = new JMenu();
        this.RBMIMonoSound = new JRadioButtonMenuItem();
        this.RBMIStereoSound = new JRadioButtonMenuItem();
        this.SepOptionsB = new JSeparator();
        this.CBMIPhosphorEnabled = new JCheckBoxMenuItem();
        this.CBMILetterBoxMode = new JCheckBoxMenuItem();
        this.SepOptionsC = new JSeparator();
        this.MIConfigure = new JMenuItem();
        this.MenuControls = new JMenu();
        this.MICBJoystickEnabled = new JCheckBoxMenuItem();
        this.MIVirtualJoystick = new JMenuItem();
        this.MenuHelp = new JMenu();
        this.MIHelpContents = new JMenuItem();
        this.MIAbout = new JMenuItem();
        this.DialogAbout.setTitle("About JStella");
        this.DialogAbout.setAlwaysOnTop(true);
        this.DialogAbout.setLocationByPlatform(true);
        this.DialogAbout.setModal(true);
        this.DialogAbout.setResizable(false);
        this.ButtonAboutOK.setText("OK");
        this.ButtonAboutOK.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaPlayer.this.ButtonAboutOKActionPerformed(evt);
            }
        });
        this.PanelAboutSouth.add(this.ButtonAboutOK);
        this.DialogAbout.getContentPane().add(this.PanelAboutSouth, "South");
        this.TPAbout.setBorder(null);
        this.TPAbout.setEditable(false);
        this.TPAbout.setFocusable(false);
        this.TPAbout.setOpaque(false);
        this.DialogAbout.getContentPane().add(this.TPAbout, "Center");
        this.setDefaultCloseOperation(0);
        this.setTitle("JStella");
        this.setCursor(new Cursor(0));
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent evt) {
                JStellaPlayer.this.formWindowClosing(evt);
            }
        });
        this.MenuFile.setMnemonic('F');
        this.MenuFile.setText("File");
        this.MILoadROM.setMnemonic('O');
        this.MILoadROM.setText("Open and play cartridge file");
        this.MILoadROM.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaPlayer.this.MILoadROMActionPerformed(evt);
            }
        });
        this.MenuFile.add(this.MILoadROM);
        this.MenuFile.add(this.SepFileA);
        this.MISaveState.setMnemonic('S');
        this.MISaveState.setText("Save current game");
        this.MISaveState.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaPlayer.this.MISaveStateActionPerformed(evt);
            }
        });
        this.MenuFile.add(this.MISaveState);
        this.MILoadState.setMnemonic('L');
        this.MILoadState.setText("Load a saved game");
        this.MILoadState.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaPlayer.this.MILoadStateActionPerformed(evt);
            }
        });
        this.MenuFile.add(this.MILoadState);
        this.MenuFile.add(this.SepFileB);
        this.MIExit.setMnemonic('x');
        this.MIExit.setText("Exit JStella");
        this.MIExit.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaPlayer.this.MIExitActionPerformed(evt);
            }
        });
        this.MenuFile.add(this.MIExit);
        this.MBMain.add(this.MenuFile);
        this.MenuSwitches.setMnemonic('S');
        this.MenuSwitches.setText("Switches");
        this.MenuSwitches.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaPlayer.this.MenuSwitchesActionPerformed(evt);
            }
        });
        this.MIReset.setMnemonic('R');
        this.MIReset.setText("Reset (F1)");
        this.MIReset.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaPlayer.this.MIResetActionPerformed(evt);
            }
        });
        this.MenuSwitches.add(this.MIReset);
        this.MISelect.setMnemonic('S');
        this.MISelect.setText("Select (F2)");
        this.MISelect.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaPlayer.this.MISelectActionPerformed(evt);
            }
        });
        this.MenuSwitches.add(this.MISelect);
        this.MenuTVType.setMnemonic('T');
        this.MenuTVType.setText("TV Type");
        this.ButtonGroupTVType.add(this.RBMIBWTelevision);
        this.RBMIBWTelevision.setMnemonic('B');
        this.RBMIBWTelevision.setText("Black and white (F3)");
        this.RBMIBWTelevision.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaPlayer.this.RBMIBWTelevisionActionPerformed(evt);
            }
        });
        this.MenuTVType.add(this.RBMIBWTelevision);
        this.ButtonGroupTVType.add(this.RBMIColorTelevision);
        this.RBMIColorTelevision.setMnemonic('C');
        this.RBMIColorTelevision.setSelected(true);
        this.RBMIColorTelevision.setText("Color (F4)");
        this.RBMIColorTelevision.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaPlayer.this.RBMIColorTelevisionActionPerformed(evt);
            }
        });
        this.MenuTVType.add(this.RBMIColorTelevision);
        this.MenuSwitches.add(this.MenuTVType);
        this.MenuPlayer0Difficulty.setMnemonic('0');
        this.MenuPlayer0Difficulty.setText("Left player difficulty");
        this.ButtonGroupP0Difficulty.add(this.RBMIPlayer0Amateur);
        this.RBMIPlayer0Amateur.setMnemonic('B');
        this.RBMIPlayer0Amateur.setSelected(true);
        this.RBMIPlayer0Amateur.setText("B (amateur) (F5)");
        this.RBMIPlayer0Amateur.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaPlayer.this.RBMIPlayer0AmateurActionPerformed(evt);
            }
        });
        this.MenuPlayer0Difficulty.add(this.RBMIPlayer0Amateur);
        this.ButtonGroupP0Difficulty.add(this.RBMIPlayer0Professional);
        this.RBMIPlayer0Professional.setMnemonic('A');
        this.RBMIPlayer0Professional.setText("A (professional) (F6)");
        this.RBMIPlayer0Professional.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaPlayer.this.RBMIPlayer0ProfessionalActionPerformed(evt);
            }
        });
        this.MenuPlayer0Difficulty.add(this.RBMIPlayer0Professional);
        this.MenuSwitches.add(this.MenuPlayer0Difficulty);
        this.MenuPlayer1Difficulty.setMnemonic('1');
        this.MenuPlayer1Difficulty.setText("Right player difficulty");
        this.ButtonGroupP1Difficulty.add(this.RBMIPlayer1Amateur);
        this.RBMIPlayer1Amateur.setMnemonic('B');
        this.RBMIPlayer1Amateur.setSelected(true);
        this.RBMIPlayer1Amateur.setText("B (amateur) (F7)");
        this.RBMIPlayer1Amateur.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaPlayer.this.RBMIPlayer1AmateurActionPerformed(evt);
            }
        });
        this.MenuPlayer1Difficulty.add(this.RBMIPlayer1Amateur);
        this.ButtonGroupP1Difficulty.add(this.RBMIPlayer1Professional);
        this.RBMIPlayer1Professional.setMnemonic('A');
        this.RBMIPlayer1Professional.setText("A (professional) (F8)");
        this.RBMIPlayer1Professional.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaPlayer.this.RBMIPlayer1ProfessionalActionPerformed(evt);
            }
        });
        this.MenuPlayer1Difficulty.add(this.RBMIPlayer1Professional);
        this.MenuSwitches.add(this.MenuPlayer1Difficulty);
        this.MBMain.add(this.MenuSwitches);
        this.MenuOptions.setMnemonic('O');
        this.MenuOptions.setText("Options");
        this.CBMIPaused.setMnemonic('P');
        this.CBMIPaused.setText("Pause");
        this.CBMIPaused.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaPlayer.this.CBMIPausedActionPerformed(evt);
            }
        });
        this.MenuOptions.add(this.CBMIPaused);
        this.MenuOptions.add(this.SepOptionsA);
        this.CBMISoundEnabled.setMnemonic('S');
        this.CBMISoundEnabled.setSelected(true);
        this.CBMISoundEnabled.setText("Sound enabled");
        this.CBMISoundEnabled.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaPlayer.this.CBMISoundEnabledActionPerformed(evt);
            }
        });
        this.MenuOptions.add(this.CBMISoundEnabled);
        this.MenuSoundChannels.setMnemonic('c');
        this.MenuSoundChannels.setText("Audio channels");
        this.ButtonGroupChannels.add(this.RBMIMonoSound);
        this.RBMIMonoSound.setMnemonic('M');
        this.RBMIMonoSound.setSelected(true);
        this.RBMIMonoSound.setText("Mono");
        this.RBMIMonoSound.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaPlayer.this.RBMIMonoSoundActionPerformed(evt);
            }
        });
        this.MenuSoundChannels.add(this.RBMIMonoSound);
        this.ButtonGroupChannels.add(this.RBMIStereoSound);
        this.RBMIStereoSound.setMnemonic('S');
        this.RBMIStereoSound.setText("Stereo");
        this.RBMIStereoSound.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaPlayer.this.RBMIStereoSoundActionPerformed(evt);
            }
        });
        this.MenuSoundChannels.add(this.RBMIStereoSound);
        this.MenuOptions.add(this.MenuSoundChannels);
        this.MenuOptions.add(this.SepOptionsB);
        this.CBMIPhosphorEnabled.setMnemonic('f');
        this.CBMIPhosphorEnabled.setText("Anti-flicker mode");
        this.CBMIPhosphorEnabled.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaPlayer.this.CBMIPhosphorEnabledActionPerformed(evt);
            }
        });
        this.MenuOptions.add(this.CBMIPhosphorEnabled);
        this.CBMILetterBoxMode.setMnemonic('L');
        this.CBMILetterBoxMode.setText("Letterbox mode");
        this.CBMILetterBoxMode.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaPlayer.this.CBMILetterBoxModeActionPerformed(evt);
            }
        });
        this.MenuOptions.add(this.CBMILetterBoxMode);
        this.MenuOptions.add(this.SepOptionsC);
        this.MIConfigure.setMnemonic('C');
        this.MIConfigure.setText("Configure...");
        this.MIConfigure.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaPlayer.this.MIConfigureActionPerformed(evt);
            }
        });
        this.MenuOptions.add(this.MIConfigure);
        this.MBMain.add(this.MenuOptions);
        this.MenuControls.setMnemonic('C');
        this.MenuControls.setText("Controls");
        this.MICBJoystickEnabled.setSelected(true);
        this.MICBJoystickEnabled.setText("Joystick enabled");
        this.MICBJoystickEnabled.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaPlayer.this.MICBJoystickEnabledActionPerformed(evt);
            }
        });
        this.MenuControls.add(this.MICBJoystickEnabled);
        this.MIVirtualJoystick.setMnemonic('j');
        this.MIVirtualJoystick.setText("Toggle virtual joystick");
        this.MIVirtualJoystick.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaPlayer.this.MIVirtualJoystickActionPerformed(evt);
            }
        });
        this.MenuControls.add(this.MIVirtualJoystick);
        this.MBMain.add(this.MenuControls);
        this.MenuHelp.setMnemonic('H');
        this.MenuHelp.setText("Help");
        this.MIHelpContents.setMnemonic('H');
        this.MIHelpContents.setText("Help contents");
        this.MIHelpContents.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaPlayer.this.MIHelpContentsActionPerformed(evt);
            }
        });
        this.MenuHelp.add(this.MIHelpContents);
        this.MIAbout.setMnemonic('A');
        this.MIAbout.setText("About");
        this.MIAbout.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaPlayer.this.MIAboutActionPerformed(evt);
            }
        });
        this.MenuHelp.add(this.MIAbout);
        this.MBMain.add(this.MenuHelp);
        this.setJMenuBar(this.MBMain);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds((screenSize.width - 509) / 2, (screenSize.height - 435) / 2, 509, 435);
    }
    
    private void MIExitActionPerformed(final ActionEvent evt) {
        JStellaDesktop.closeFrame(this);
    }
    
    private void CBMILetterBoxModeActionPerformed(final ActionEvent evt) {
        this.myIntercessor.setLetterBoxMode(this.CBMILetterBoxMode.isSelected());
    }
    
    private void MIHelpContentsActionPerformed(final ActionEvent evt) {
        JStellaHelp.runJStellaHelp(this);
    }
    
    private void MIVirtualJoystickActionPerformed(final ActionEvent evt) {
        this.myIntercessor.toggleVirtualJoystick(this);
    }
    
    private void MILoadStateActionPerformed(final ActionEvent evt) {
        this.loadState();
    }
    
    private void MISaveStateActionPerformed(final ActionEvent evt) {
        this.saveState();
    }
    
    private void MISelectActionPerformed(final ActionEvent evt) {
        this.myIntercessor.emulateSelectPress();
    }
    
    private void MIConfigureActionPerformed(final ActionEvent evt) {
        final boolean zChanged = JStellaDesktop.showConfigurationDialog(this);
        if (zChanged) {
            this.myIntercessor.updateTelevisionMode();
        }
    }
    
    private void RBMIColorTelevisionActionPerformed(final ActionEvent evt) {
        this.myIntercessor.setTVTypeBW(false);
    }
    
    private void RBMIBWTelevisionActionPerformed(final ActionEvent evt) {
        this.myIntercessor.setTVTypeBW(true);
    }
    
    private void RBMIPlayer1ProfessionalActionPerformed(final ActionEvent evt) {
        this.myIntercessor.setPlayer1Amateur(false);
    }
    
    private void RBMIPlayer1AmateurActionPerformed(final ActionEvent evt) {
        this.myIntercessor.setPlayer1Amateur(true);
    }
    
    private void RBMIPlayer0ProfessionalActionPerformed(final ActionEvent evt) {
        this.myIntercessor.setPlayer0Amateur(false);
    }
    
    private void RBMIPlayer0AmateurActionPerformed(final ActionEvent evt) {
        this.myIntercessor.setPlayer0Amateur(true);
    }
    
    private void CBMIPausedActionPerformed(final ActionEvent evt) {
        this.myIntercessor.setPausedByPlayer(this.CBMIPaused.isSelected());
    }
    
    private void MenuSwitchesActionPerformed(final ActionEvent evt) {
    }
    
    private void MIResetActionPerformed(final ActionEvent evt) {
        this.myIntercessor.emulateResetPress();
    }
    
    private void RBMIStereoSoundActionPerformed(final ActionEvent evt) {
        this.myIntercessor.setStereoSound(true);
    }
    
    private void RBMIMonoSoundActionPerformed(final ActionEvent evt) {
        this.myIntercessor.setStereoSound(false);
    }
    
    private void CBMIPhosphorEnabledActionPerformed(final ActionEvent evt) {
        if (this.myIntercessor != null) {
            this.myIntercessor.setPhosphorEnabled(this.CBMIPhosphorEnabled.isSelected());
        }
    }
    
    private void CBMISoundEnabledActionPerformed(final ActionEvent evt) {
        this.myIntercessor.setSoundEnabled(this.CBMISoundEnabled.isSelected());
    }
    
    private void ButtonAboutOKActionPerformed(final ActionEvent evt) {
        this.DialogAbout.setVisible(false);
    }
    
    private void MIAboutActionPerformed(final ActionEvent evt) {
        this.DialogAbout.setLocationRelativeTo(this);
        this.DialogAbout.setSize(400, 200);
        this.DialogAbout.setVisible(true);
    }
    
    private void MILoadROMActionPerformed(final ActionEvent evt) {
        try {
            this.loadROM();
        }
        catch (JSException e) {
            this.respondToException(e);
        }
    }
    
    private void formWindowClosing(final WindowEvent evt) {
        JStellaDesktop.closeFrame(this);
    }
    
    private void MICBJoystickEnabledActionPerformed(final ActionEvent evt) {
        this.myJoystickEnabled = this.MICBJoystickEnabled.isSelected();
    }
    
    public static void main(final String[] args) {
        String zROMDir;
        if (args.length > 0) {
            zROMDir = args[0];
        }
        else {
            zROMDir = "";
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                final JStellaPlayer zJSM = new JStellaPlayer();
                zJSM.setCurrentROMDirectory(new File(zROMDir));
                zJSM.setVisible(true);
            }
        });
    }
    
    private void setCurrentROMDirectory(final File aROMDir) {
        this.myCurrentROMDirectory = aROMDir;
    }
    
    private File getDefaultROMDirectory() {
        String zDefaultROMDir = this.getConfiguration().get("jstella.dir.roms");
        if (zDefaultROMDir == null) {
            zDefaultROMDir = "";
        }
        return new File(zDefaultROMDir);
    }
    
    private File getCurrentROMDirectory() {
        if (this.myCurrentROMDirectory == null) {
            this.myCurrentROMDirectory = this.getDefaultROMDirectory();
        }
        return this.myCurrentROMDirectory;
    }
    
    private void initAboutDialog() {
        final SimpleAttributeSet zSAS = new SimpleAttributeSet();
        zSAS.addAttribute(StyleConstants.Alignment, 1);
        zSAS.addAttribute(StyleConstants.Bold, true);
        final StringBuffer zSBuf = new StringBuffer();
        zSBuf.append("JStella - Atari 2600 Emulator\n");
        zSBuf.append("Version: 0.95 (beta)\n");
        zSBuf.append("Stella emulator by Bradford Mott and the Stella team\n");
        zSBuf.append("Java adaptation and GUI by J.L. Allen and the JStella team\n");
        zSBuf.append("http://jstella.sourceforge.net\n");
        try {
            final StyledDocument zSDoc = this.TPAbout.getStyledDocument();
            zSDoc.remove(0, zSDoc.getLength());
            zSDoc.insertString(0, zSBuf.toString(), null);
            zSDoc.setParagraphAttributes(0, zSDoc.getLength(), zSAS, false);
        }
        catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
    
    public boolean respondToException(final JSException e) {
        this.myIntercessor.showDefaultExceptionResponse(e, this);
        return true;
    }
    
    private void updateOptions() {
        this.CBMISoundEnabled.setSelected(this.myIntercessor.isSoundEnabled());
        this.CBMIPhosphorEnabled.setSelected(this.myIntercessor.isPhosphorEnabled());
        if (this.myIntercessor.isStereoSound()) {
            this.RBMIStereoSound.setSelected(true);
        }
        else {
            this.RBMIMonoSound.setSelected(true);
        }
        this.CBMIPaused.setSelected(this.myIntercessor.isPausedByPlayer());
        if (this.myIntercessor.isTVTypeBW()) {
            this.RBMIBWTelevision.setSelected(true);
        }
        else {
            this.RBMIColorTelevision.setSelected(true);
        }
        if (this.myIntercessor.isPlayer0Amateur()) {
            this.RBMIPlayer0Amateur.setSelected(true);
        }
        else {
            this.RBMIPlayer0Professional.setSelected(true);
        }
        if (this.myIntercessor.isPlayer1Amateur()) {
            this.RBMIPlayer1Amateur.setSelected(true);
        }
        else {
            this.RBMIPlayer1Professional.setSelected(true);
        }
        this.CBMILetterBoxMode.setSelected(this.myIntercessor.getLetterBoxMode());
        this.MICBJoystickEnabled.setEnabled(JStellaJoystickInput.myJoystick != null);
        this.MICBJoystickEnabled.setSelected(this.myJoystickEnabled);
    }
    
    private void initDefaultStateName(final String aPlainName) {
        if (!aPlainName.equals("")) {
            this.myDefaultStateName = aPlainName + ".jssg";
        }
        else {
            this.myDefaultStateName = "mysavedgame.jssg";
        }
    }
    
    public void playJStellaGame(final JStellaGame aGame) {
        final String zPlainFilename = JStellaRunnerUtil.getFilenameWithoutExtension(aGame.getGameFilename());
        this.initDefaultStateName(zPlainFilename);
        this.myIntercessor.playJStellaGame(aGame);
        this.setVisible(true);
    }
    
    public void loadROM(final byte[] aROMData, final String aPlainFilename, final Map<String, String> aConfigMap) {
        try {
            this.initDefaultStateName(aPlainFilename);
            final ByteArrayInputStream zBAIS = new ByteArrayInputStream(aROMData);
            this.myIntercessor.playROM(zBAIS);
            if (aConfigMap != null) {
                this.myIntercessor.applyConfiguration(aConfigMap);
            }
            zBAIS.close();
            this.setVisible(true);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private void loadROM(final File aROMFile) throws IOException {
        final FileInputStream zFIS = new FileInputStream(aROMFile);
        this.myCurrentROMFile = aROMFile;
        System.out.println("Loading ROM : " + aROMFile.toString());
        this.myIntercessor.playROM(zFIS);
        zFIS.close();
        final String zFileNameWithoutExtension = JStellaRunnerUtil.getFilenameWithoutExtension(aROMFile);
        this.initDefaultStateName(zFileNameWithoutExtension);
    }
    
    private void loadROM() throws JSException {
        try {
            final String zCurrentDir = "";
            JStellaDesktop.configureFileBrowser(false, false, true, this.getCurrentROMDirectory(), null, JStellaDesktop.JSFileNameExtensionFilter.FILTER_ROMS);
            final int zResult = JStellaDesktop.getFileBrowser().showOpenDialog(this);
            if (zResult == 0) {
                this.loadROM(JStellaDesktop.getFileBrowser().getSelectedFile());
                this.setCurrentROMDirectory(JStellaDesktop.getFileBrowser().getCurrentDirectory());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        this.updateCartridgeStatus();
    }
    
    private File showSaveDialog(final String aDefaultDirectory, final String aDefaultFileName, final JStellaDesktop.JSFileNameExtensionFilter aFilter) {
        File zReturn = null;
        final JFileChooser zFileBrowser = JStellaDesktop.getFileBrowser();
        JStellaDesktop.configureFileBrowser(false, false, true, new File(aDefaultDirectory), new File(aDefaultFileName), aFilter);
        boolean zChooseAgain = false;
        do {
            final int zResult = zFileBrowser.showSaveDialog(this);
            if (zResult == 0) {
                if (zFileBrowser.getSelectedFile().exists()) {
                    final int zConfirmResult = JOptionPane.showConfirmDialog(this, "" + zFileBrowser.getSelectedFile().getName() + " already exists.  Do you wish to overwrite?");
                    if (zConfirmResult == 1) {
                        zChooseAgain = true;
                    }
                    else if (zConfirmResult == 0) {
                        zReturn = zFileBrowser.getSelectedFile();
                        zChooseAgain = false;
                    }
                    else {
                        zChooseAgain = false;
                    }
                }
                else {
                    zReturn = zFileBrowser.getSelectedFile();
                }
            }
            else {
                zChooseAgain = false;
            }
        } while (zChooseAgain);
        return zReturn;
    }
    
    private int isSaveAcceptable(final File aFileToSave) {
        final int zReturn = 0;
        return zReturn;
    }
    
    private void loadState() {
        try {
            String zCurrentDir = "";
            if (!this.myStateDirectory.trim().equals("")) {
                zCurrentDir = this.myStateDirectory;
            }
            else if (this.getConfiguration().containsKey("jstella.dir.states")) {
                zCurrentDir = this.getConfiguration().get("jstella.dir.states");
            }
            JStellaDesktop.configureFileBrowser(false, false, true, new File(zCurrentDir), null, JStellaDesktop.JSFileNameExtensionFilter.FILTER_JSTELLA_STATE);
            final int zResult = JStellaDesktop.getFileBrowser().showOpenDialog(this);
            if (zResult == 0) {
                final FileInputStream zFIS = new FileInputStream(JStellaDesktop.getFileBrowser().getSelectedFile());
                this.myIntercessor.loadStateFromStream(zFIS);
                this.updateOptions();
                this.updateCartridgeStatus();
                this.myStateDirectory = JStellaDesktop.getFileBrowser().getCurrentDirectory().getPath();
                this.myDefaultStateName = JStellaDesktop.getFileBrowser().getSelectedFile().getName();
            }
        }
        catch (ObjectStreamException e) {
            JOptionPane.showMessageDialog(this, "Saved game is incompatible with this version of JStella", "Error loading game", 0);
            e.printStackTrace();
        }
        catch (IOException e2) {
            JOptionPane.showMessageDialog(this, "Could not load saved game: " + JStellaDesktop.getFileBrowser().getSelectedFile().getName(), "Error loading game", 0);
            e2.printStackTrace();
        }
        catch (ClassNotFoundException e3) {
            JOptionPane.showMessageDialog(this, "Could not load saved game: " + JStellaDesktop.getFileBrowser().getSelectedFile().getName(), "Error loading game", 0);
            e3.printStackTrace();
        }
        this.updateCartridgeStatus();
    }
    
    private void saveState() {
        try {
            JOptionPane.showMessageDialog(this, "Because JStella is still in development, saved games may not be compatible with future releases of JStella", "Warning", 2);
            String zDefaultDirectory = "";
            if (!this.myStateDirectory.trim().equals("")) {
                zDefaultDirectory = this.myStateDirectory;
            }
            else if (this.getConfiguration().containsKey("jstella.dir.states")) {
                zDefaultDirectory = this.getConfiguration().get("jstella.dir.states");
            }
            final File zSelectedFile = this.showSaveDialog(zDefaultDirectory, this.myDefaultStateName, JStellaDesktop.JSFileNameExtensionFilter.FILTER_JSTELLA_STATE);
            if (zSelectedFile != null) {
                final FileOutputStream zFOS = new FileOutputStream(zSelectedFile);
                this.myIntercessor.saveStateToStream(zFOS);
                this.myStateDirectory = JStellaDesktop.getFileBrowser().getCurrentDirectory().getPath();
                this.myDefaultStateName = zSelectedFile.getName();
                JOptionPane.showMessageDialog(this, "Game saved");
            }
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Could not save current game to file: " + JStellaDesktop.getFileBrowser().getSelectedFile().getName(), "Error saving game", 0);
            e.printStackTrace();
        }
    }
    
    private void updateCartridgeStatus() {
        final boolean zCartridgeLoaded = this.myIntercessor.getCartridge() != null;
        this.MISaveState.setEnabled(zCartridgeLoaded);
    }
    
    public void displayCanvas(final JPanel aCanvas) {
        this.getContentPane().add(aCanvas, "Center");
        aCanvas.revalidate();
        aCanvas.requestFocusInWindow();
    }
    
    private void updatePauseStatus() {
        if (this.myIntercessor.isPausedByPlayer()) {
            this.setTitle("JStella 0.95 (beta) (paused)");
        }
        else {
            this.setTitle("JStella 0.95 (beta)");
        }
    }
    
    public void informUserOfPause(final boolean aIsPaused) {
        this.updatePauseStatus();
    }
    
    public void updateSwitches() {
        this.updateOptions();
    }
    
    public void updateRepositoryMode(final boolean aRepositoryMode) {
        System.out.println("updateRepositoryMode(" + aRepositoryMode + ")");
        this.MIExit.setText(aRepositoryMode ? "Exit to repository" : "Exit JStella");
    }
    
    public Map<String, String> getConfiguration() {
        return JStellaDesktop.getConfiguration();
    }
    
    public void updateGUI() {
        this.updateOptions();
    }
    
    public void runExtraLoopRoutines() {
        if (this.myJoystickEnabled) {
            JStellaJoystickInput.pollJoystick();
        }
    }
}
