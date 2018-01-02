// 
// Decompiled by Procyon v0.5.30
// 

package jstella.applet;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.net.MalformedURLException;
import java.io.ObjectInputStream;
import jstella.runner.JStellaGame;
import java.util.Enumeration;
import jstella.runner.JStellaRunnerUtil;
import jstella.core.JSException;
import javax.swing.SwingUtilities;
import java.io.InputStream;
import java.io.IOException;
import java.net.URL;
import jstella.runner.InputMaster;
import javax.swing.text.StyledDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.AttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.SimpleAttributeSet;
import java.awt.event.MouseListener;
import javax.swing.border.Border;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.EventQueue;
import java.util.HashMap;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JDialog;
import javax.swing.JButton;
import jstella.cart.Cartridge;
import java.util.Map;
import jstella.runner.Intercessor;
import javax.swing.JApplet;

public class JStellaApplet extends JApplet implements Intercessor.IfcIntercessorClient, JavaScriptInterface
{
    private static final long serialVersionUID = -1619358045036125411L;
    private static final String JSTELLA_APPLET_VERSION = "0.5";
    public static final String PARAMETER_ROM = "ROM";
    public static final String PARAMETER_GAME = "GAME";
    public static final String PARAMETER_CARTRIDGE_TYPE = "CARTRIDGETYPE";
    public static final String PARAMETER_DISPLAY_HEIGHT = "DISPLAYHEIGHT";
    public static final String PARAMETER_PADDLE_MODE = "PADDLEMODE";
    public static final String PARAMETER_PADDLE_MOUSE_AXIS = "PADDLEMOUSEAXIS";
    public static final String PARAMETER_OPTIONS_PANEL_ENABLED = "OPTIONSPANELENABLED";
    public static final String PARAMETER_CONFIG_FILE = "CONFIGFILE";
    private Map<String, String> myConfiguration;
    private Intercessor myIntercessor;
    private JStellaOptionsApplet myOptionsApplet;
    private Cartridge myCartridge;
    private int myDisplayHeight;
    private StringBuffer myDebugOutput;
    private JButton ButtonHelp;
    private JButton ButtonHelpOK;
    private JButton ButtonReset;
    private JButton ButtonSelect;
    private JButton ButtonShowDebugInfo;
    private JDialog DialogHelp;
    private JLabel LabelPause;
    private JPanel PanelHelpSouth;
    private JPanel PanelOptions;
    private JScrollPane SPHelp;
    private JTextPane TPHelp;
    
    public JStellaApplet() {
        this.myConfiguration = new HashMap<String, String>();
        this.myIntercessor = null;
        this.myOptionsApplet = null;
        this.myCartridge = null;
        this.myDisplayHeight = -1;
        this.myDebugOutput = new StringBuffer();
    }
    
    public void init() {
        try {
            EventQueue.invokeAndWait(new Runnable() {
                public void run() {
                    JStellaApplet.this.initComponents();
                    JStellaApplet.this.initStellaApplet();
                    JStellaApplet.this.initHelpDialog();
                }
            });
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void initComponents() {
        this.DialogHelp = new JDialog();
        this.PanelHelpSouth = new JPanel();
        this.ButtonHelpOK = new JButton();
        this.ButtonShowDebugInfo = new JButton();
        this.SPHelp = new JScrollPane();
        this.TPHelp = new JTextPane();
        this.PanelOptions = new JPanel();
        this.ButtonReset = new JButton();
        this.ButtonSelect = new JButton();
        this.ButtonHelp = new JButton();
        this.LabelPause = new JLabel();
        this.DialogHelp.setTitle("JStella - Help");
        this.DialogHelp.setResizable(false);
        this.ButtonHelpOK.setText("OK");
        this.ButtonHelpOK.setFocusable(false);
        this.ButtonHelpOK.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaApplet.this.ButtonHelpOKActionPerformed(evt);
            }
        });
        this.PanelHelpSouth.add(this.ButtonHelpOK);
        this.ButtonShowDebugInfo.setText("Debug info");
        this.ButtonShowDebugInfo.setFocusable(false);
        this.ButtonShowDebugInfo.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaApplet.this.ButtonShowDebugInfoActionPerformed(evt);
            }
        });
        this.PanelHelpSouth.add(this.ButtonShowDebugInfo);
        this.DialogHelp.getContentPane().add(this.PanelHelpSouth, "South");
        this.TPHelp.setBorder(null);
        this.TPHelp.setEditable(false);
        this.TPHelp.setFocusable(false);
        this.TPHelp.setOpaque(false);
        this.SPHelp.setViewportView(this.TPHelp);
        this.DialogHelp.getContentPane().add(this.SPHelp, "Center");
        this.PanelOptions.setFocusable(false);
        this.ButtonReset.setText("Reset");
        this.ButtonReset.setDefaultCapable(false);
        this.ButtonReset.setFocusable(false);
        this.ButtonReset.setRequestFocusEnabled(false);
        this.ButtonReset.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaApplet.this.ButtonResetActionPerformed(evt);
            }
        });
        this.PanelOptions.add(this.ButtonReset);
        this.ButtonSelect.setText("Select");
        this.ButtonSelect.setDefaultCapable(false);
        this.ButtonSelect.setFocusable(false);
        this.ButtonSelect.setRequestFocusEnabled(false);
        this.ButtonSelect.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaApplet.this.ButtonSelectActionPerformed(evt);
            }
        });
        this.PanelOptions.add(this.ButtonSelect);
        this.ButtonHelp.setText("Help");
        this.ButtonHelp.setDefaultCapable(false);
        this.ButtonHelp.setFocusable(false);
        this.ButtonHelp.setRequestFocusEnabled(false);
        this.ButtonHelp.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaApplet.this.ButtonHelpActionPerformed(evt);
            }
        });
        this.PanelOptions.add(this.ButtonHelp);
        this.PanelOptions.add(this.LabelPause);
    }
    
    private void ButtonShowDebugInfoActionPerformed(final ActionEvent evt) {
        this.debugOut("YStart=" + this.myIntercessor.getConsole().getYStart() + ", displayHeight=" + this.myIntercessor.getConsole().getDisplayHeight());
        this.debugOut("Canvas size=" + this.myIntercessor.getCanvas().toString());
        this.TPHelp.setText("" + this.myDebugOutput.toString());
    }
    
    private void ButtonHelpOKActionPerformed(final ActionEvent evt) {
        this.DialogHelp.setVisible(false);
        this.refocusKeyboard();
    }
    
    private void ButtonHelpActionPerformed(final ActionEvent evt) {
        this.doHelp();
    }
    
    private void ButtonSelectActionPerformed(final ActionEvent evt) {
        this.doSelect();
    }
    
    private void ButtonResetActionPerformed(final ActionEvent evt) {
        this.doReset();
    }
    
    private void initStellaApplet() {
        try {
            this.myIntercessor = new Intercessor(this);
            final String zJavaVersion = System.getProperty("java.version");
            this.debugOut("Java version: " + zJavaVersion);
            this.debugOut("JStella version: 0.95 (beta)");
            this.debugOut("JStellaApplet version 0.5");
            this.readAppletConfig();
            this.doAppletConfig();
            this.addMouseListener(new AppletRefocuser());
            this.setFocusable(true);
            this.myIntercessor.setAutoPauseMode(false);
            this.refocusKeyboard();
            this.loadParameters();
        }
        catch (Exception e) {
            this.debugOut("JStella error : caught exception - " + e.toString());
        }
    }
    
    private void debugOut(final String aLine) {
        this.myDebugOutput.append(aLine + "\n");
        System.out.println("" + aLine);
    }
    
    private void initHelpDialog() {
        final SimpleAttributeSet zSAS = new SimpleAttributeSet();
        zSAS.addAttribute(StyleConstants.Alignment, 1);
        zSAS.addAttribute(StyleConstants.Bold, true);
        final StringBuffer zSBuf = new StringBuffer();
        zSBuf.append("JStella - Atari 2600 Emulator\n");
        zSBuf.append("Version: 0.95 (beta)\n");
        zSBuf.append("Applet version: 0.5\n");
        zSBuf.append("Stella emulator by Bradford Mott and the Stella team\n");
        zSBuf.append("http://jstella.sourceforge.net\n");
        try {
            final StyledDocument zSDoc = this.TPHelp.getStyledDocument();
            zSDoc.remove(0, zSDoc.getLength());
            zSDoc.insertString(0, zSBuf.toString(), null);
            zSDoc.setParagraphAttributes(0, zSDoc.getLength(), zSAS, false);
        }
        catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
    
    private void loadParameters() {
        final String zROM = this.getParameter("ROM");
        final String zCartridgeType = this.getParameter("CARTRIDGETYPE");
        final String zDisplayHeightStr = this.getParameter("DISPLAYHEIGHT");
        final String zPaddleMode = this.getParameter("PADDLEMODE");
        final String zPaddleMouseAxis = this.getParameter("PADDLEMOUSEAXIS");
        final String zOptionsPanelEnabled = this.getParameter("OPTIONSPANELENABLED");
        final String zJStellaGameFilename = this.getParameter("GAME");
        if (zOptionsPanelEnabled == null) {
            this.setOptionsPanelVisible(true);
            this.debugOut("Using default options panel setting");
        }
        else if (isAffirmative(zOptionsPanelEnabled)) {
            this.setOptionsPanelVisible(true);
            this.debugOut("Enabling options panel");
        }
        else {
            this.setOptionsPanelVisible(false);
            this.debugOut("Disabling options panel");
        }
        if (zCartridgeType != null) {
            this.debugOut("Cartridge type=" + zCartridgeType + " (user specified)");
        }
        else {
            this.debugOut("Cartridge type=<auto-detect>");
        }
        this.myDisplayHeight = -1;
        if (zDisplayHeightStr != null) {
            try {
                this.myDisplayHeight = Integer.parseInt(zDisplayHeightStr);
                this.debugOut("Display height=" + this.myDisplayHeight + " (user specified)");
            }
            catch (NumberFormatException e) {
                this.debugOut("Error : invalid height parameter - " + zDisplayHeightStr);
            }
        }
        if (zPaddleMouseAxis != null) {
            if (zPaddleMouseAxis.toUpperCase().trim().equals("Y")) {
                InputMaster.setPaddleMouseAxis(InputMaster.PaddleMouseAxis.MOUSE_Y);
                System.out.println("Setting paddle mouse axis to Y axis");
            }
            else {
                InputMaster.setPaddleMouseAxis(InputMaster.PaddleMouseAxis.MOUSE_X);
                System.out.println("Setting paddle mouse axis to X axis");
            }
        }
        if (zPaddleMode != null) {
            this.myIntercessor.lockPaddleMode();
            this.debugOut("Locking paddle mode");
        }
        String zFilename = zJStellaGameFilename;
        if (zFilename == null) {
            zFilename = zROM;
        }
        this.loadGame(zFilename, this.myDisplayHeight, zCartridgeType);
    }
    
    private void loadROM(final String aROMName, final int aDisplayHeight, final String aCartridgeType) {
        try {
            this.debugOut("Attempting to load: codebase=" + this.getCodeBase() + ", ROM=" + aROMName);
            final URL zURL = new URL(this.getCodeBase(), aROMName);
            this.debugOut("Loading URL: " + zURL);
            final InputStream zIS = zURL.openStream();
            this.myIntercessor.playROM(zIS, aCartridgeType, this.myDisplayHeight);
            this.debugOut("Loaded : " + this.myIntercessor.getCartridge());
            this.myCartridge = this.myIntercessor.getCartridge();
            zIS.close();
        }
        catch (IOException e) {
            this.debugOut("JStella Applet: error, unable to load ROM");
            this.debugOut(e.toString());
            e.printStackTrace();
        }
    }
    
    private void loadGame(final String aFilename, final int aDisplayHeight, final String aCartridgeType) {
        if (aFilename.toLowerCase().trim().endsWith("j26")) {
            this.loadJStellaGame(aFilename);
        }
        else {
            this.loadROM(aFilename, aDisplayHeight, aCartridgeType);
        }
        this.myCartridge = this.myIntercessor.getCartridge();
        this.myDisplayHeight = aDisplayHeight;
    }
    
    private static boolean isAffirmative(final String aValue) {
        boolean zAffirmative = false;
        if (aValue != null) {
            final String zStr = aValue.trim().toLowerCase();
            if (zStr.equals("true") || zStr.equals("yes")) {
                zAffirmative = true;
            }
        }
        return zAffirmative;
    }
    
    public void refocusKeyboard() {
        this.myIntercessor.refocusKeyboard();
    }
    
    public void stop() {
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    JStellaApplet.this.myIntercessor.stopTimer();
                }
            });
        }
        catch (Exception e) {
            this.debugOut("Error in stop() : " + e.toString());
        }
    }
    
    public void start() {
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    JStellaApplet.this.myIntercessor.refocusKeyboard();
                    JStellaApplet.this.myIntercessor.startTimer();
                }
            });
        }
        catch (Exception e) {
            this.debugOut("Error in start() : " + e.toString());
        }
    }
    
    private void detectOptionsApplet() {
        if (this.getOptionsApplet() == null) {
            this.debugOut("No options applet detected");
            this.setOptionsPanelVisible(true);
        }
        else {
            this.debugOut("Detected options applet");
            this.setOptionsPanelVisible(false);
        }
    }
    
    public void setOptionsPanelVisible(final boolean aVisible) {
        if (aVisible) {
            if (this.PanelOptions.getParent() == null) {
                this.getContentPane().add(this.PanelOptions, "South");
                this.getContentPane().validate();
                this.repaint();
            }
        }
        else if (!aVisible && this.PanelOptions.getParent() != null) {
            this.getContentPane().remove(this.PanelOptions);
            this.getContentPane().validate();
            this.repaint();
        }
    }
    
    public void destroy() {
        this.myIntercessor.stopTimer();
        this.myIntercessor.destroy();
        super.destroy();
    }
    
    public void displayCanvas(final JPanel aCanvas) {
        this.add(aCanvas, "Center");
    }
    
    public boolean respondToException(final JSException e) {
        this.debugOut("Responding to exception: " + e);
        this.myIntercessor.showDefaultExceptionResponse(e, this);
        return true;
    }
    
    public void informUserOfPause(final boolean aIsPaused) {
        if (aIsPaused) {
            this.LabelPause.setText("PAUSED");
        }
        else {
            this.LabelPause.setText("");
        }
    }
    
    public void updateSwitches() {
        this.informJavaScriptOfChange();
    }
    
    public Map<String, String> getConfiguration() {
        return this.myConfiguration;
    }
    
    private void doAppletConfig() {
        InputMaster.addDefaultControlItemsToConfigMap(this.myConfiguration);
        this.myIntercessor.applyConfiguration(this.myConfiguration);
    }
    
    private boolean readAppletConfig() {
        boolean zSuccess = false;
        try {
            final String zParamCfgFile = this.getParameter("CONFIGFILE");
            if (zParamCfgFile != null && !zParamCfgFile.trim().equals("")) {
                final URL zURL = new URL(this.getCodeBase(), zParamCfgFile);
                this.debugOut("Reading applet config file");
                final InputStream zIS = zURL.openStream();
                final Map<String, String> zLoadedConfig = JStellaRunnerUtil.readConfigFromStream(zIS);
                this.myConfiguration.putAll(zLoadedConfig);
                zIS.close();
                zSuccess = true;
            }
        }
        catch (IOException e) {
            System.out.println("JStella : There was an error while attempting to read the configuration file");
            e.printStackTrace();
        }
        return zSuccess;
    }
    
    private JStellaOptionsApplet getOptionsApplet() {
        if (this.myOptionsApplet == null) {
            final Enumeration zApplets = this.getAppletContext().getApplets();
            while (zApplets.hasMoreElements()) {
                final Object zObj = zApplets.nextElement();
                if (zObj instanceof JStellaOptionsApplet) {
                    this.myOptionsApplet = (JStellaOptionsApplet)zObj;
                    break;
                }
            }
        }
        return this.myOptionsApplet;
    }
    
    public void updateGUI() {
        this.updateSwitches();
        this.informJavaScriptOfChange();
    }
    
    private void loadJStellaGame(final JStellaGame aGame) {
        if (aGame != null) {
            this.myIntercessor.playJStellaGame(aGame);
        }
    }
    
    public void doHelp() {
        this.DialogHelp.setLocationRelativeTo(this);
        this.DialogHelp.setSize(400, 200);
        this.DialogHelp.setVisible(true);
    }
    
    public Intercessor getIntercessor() {
        return this.myIntercessor;
    }
    
    public void setStereoSound(final boolean aEnable) {
        this.myIntercessor.setStereoSound(aEnable);
    }
    
    public void setManualPause(final boolean aPaused) {
        this.myIntercessor.setPausedByPlayer(aPaused);
        this.myIntercessor.getJStellaCanvas().refreshCanvas();
    }
    
    public void toggleManualPause() {
        final boolean zOld = this.myIntercessor.isPausedByPlayer();
        this.myIntercessor.setPausedByPlayer(!zOld);
    }
    
    public void setSoundEnabled(final boolean aEnabled) {
        this.myIntercessor.setSoundEnabled(aEnabled);
    }
    
    public boolean isSoundEnabled() {
        return this.myIntercessor.isSoundEnabled();
    }
    
    public void setStereoSoundEnabled(final boolean aEnable) {
        this.myIntercessor.setStereoSound(aEnable);
    }
    
    public boolean isStereoSoundEnabled() {
        return this.myIntercessor.isStereoSound();
    }
    
    public void setAntiFlickerEnabled(final boolean aEnable) {
        this.myIntercessor.setPhosphorEnabled(aEnable);
    }
    
    public boolean isAntiFlickerEnabled() {
        return this.myIntercessor.isPhosphorEnabled();
    }
    
    public boolean isTVTypeBW() {
        return this.myIntercessor.isTVTypeBW();
    }
    
    public void setTVTypeBW(final boolean aBW) {
        this.myIntercessor.setTVTypeBW(aBW);
    }
    
    public boolean isPlayer0Amateur() {
        return this.myIntercessor.isPlayer0Amateur();
    }
    
    public void setPlayer0Amateur(final boolean aAmateur) {
        this.myIntercessor.setPlayer0Amateur(aAmateur);
    }
    
    public boolean isPlayer1Amateur() {
        return this.myIntercessor.isPlayer1Amateur();
    }
    
    public void setPlayer1Amateur(final boolean aAmateur) {
        this.myIntercessor.setPlayer1Amateur(aAmateur);
    }
    
    public void removeCartridge() {
        this.setConsolePowerOn(false);
    }
    
    public void reinsertCartridge() {
        this.setConsolePowerOn(true);
    }
    
    public void setDefaultScreenModeToSnow() {
        this.getConfiguration().put("jstella.defaultscreen", "snow");
        this.myIntercessor.updateTelevisionMode();
    }
    
    public void setDefaultScreenModeToTestPattern() {
        this.getConfiguration().put("jstella.defaultscreen", "testpattern");
        this.myIntercessor.updateTelevisionMode();
    }
    
    public void doSelect() {
        this.myIntercessor.emulateSelectPress();
        this.refocusKeyboard();
    }
    
    public void setSelectDown(final boolean aSelectSwitchDown) {
        this.myIntercessor.setSelectDown(aSelectSwitchDown);
        this.refocusKeyboard();
    }
    
    public void doReset() {
        this.myIntercessor.emulateResetPress();
        this.refocusKeyboard();
    }
    
    public void setPausedByUser(final boolean aPaused) {
        this.setManualPause(aPaused);
    }
    
    public boolean isPausedByUser() {
        return this.myIntercessor.isPausedByPlayer();
    }
    
    public boolean loadJStellaGame(final String aJStellaGameFilename) {
        boolean zSuccess = false;
        try {
            final URL zURL = new URL(this.getCodeBase(), aJStellaGameFilename);
            if (zURL == null) {
                return false;
            }
            final InputStream zIS = zURL.openStream();
            if (zIS == null) {
                return false;
            }
            final ObjectInputStream zOIS = new ObjectInputStream(zIS);
            final Object zObj = zOIS.readObject();
            if (zObj instanceof JStellaGame) {
                zSuccess = true;
                this.loadJStellaGame((JStellaGame)zObj);
            }
            zOIS.close();
        }
        catch (IOException e) {}
        catch (ClassNotFoundException ex) {}
        return zSuccess;
    }
    
    private void informJavaScriptOfChange() {
        try {
            this.getAppletContext().showDocument(new URL("javascript:updateJStellaGUI()"));
        }
        catch (MalformedURLException ex) {}
    }
    
    public void runExtraLoopRoutines() {
    }
    
    public void setConsolePowerOn(final boolean aPowerOn) {
        if (!aPowerOn) {
            this.myIntercessor.playROM(null, this.myDisplayHeight);
        }
        else if (aPowerOn != this.isConsolePowerOn()) {
            this.myIntercessor.playROM(this.myCartridge, this.myDisplayHeight);
        }
    }
    
    public boolean isConsolePowerOn() {
        return this.myIntercessor.getCartridge() != null;
    }
    
    public void insertCartridge(final String aROMFilename, final int aDisplayHeight) {
        this.debugOut("Loading game from file: " + aROMFilename);
        this.loadGame(aROMFilename, aDisplayHeight, null);
    }
    
    public void insertCartridge(final String aROMFilename) {
        this.insertCartridge(aROMFilename, -1);
    }
    
    private class AppletRefocuser extends MouseAdapter
    {
        public void mouseClicked(final MouseEvent e) {
            JStellaApplet.this.myIntercessor.refocusKeyboard();
        }
    }
}
