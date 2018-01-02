// 
// Decompiled by Procyon v0.5.30
// 

package jstella.runner;

import javax.swing.JComponent;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import jstella.core.IfcCanvas;
import javax.swing.JOptionPane;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.IOException;
import jstella.core.JSException;
import java.io.ObjectInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.TimerTask;
import java.awt.Frame;
import jstella.core.JSConstants;
import jstella.cart.Cartridge;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.event.FocusListener;
import jstella.core.JSConsole;
import java.util.Timer;
import jstella.core.IfcConsoleClient;

public class Intercessor implements InputMaster.IfcInputMasterClient, IfcConsoleClient
{
    private static final int TIMER_DELAY_NTSC = 17;
    private static final int TIMER_DELAY_PAL = 20;
    private static final int TIMER_DELAY_SNOW = 100;
    private Timer myUtilTimer;
    private JStellaCanvas myCanvas;
    private IfcIntercessorClient myIntercessorClient;
    private JSConsole myConsole;
    private VirtualJoystickDialog myVirtualJoystickDialog;
    private int myCurrentTimerDelay;
    private boolean myAutoPauseMode;
    private boolean myPausedByPlayer;
    private boolean myPausedByFocusLoss;
    private IntercessorKeyboardFocusListener myCanvasFocusListener;
    
    public Intercessor(final IfcIntercessorClient aClient) {
        this.myUtilTimer = null;
        this.myCanvas = null;
        this.myIntercessorClient = null;
        this.myConsole = null;
        this.myVirtualJoystickDialog = null;
        this.myCurrentTimerDelay = 17;
        this.myAutoPauseMode = false;
        this.myPausedByPlayer = false;
        this.myPausedByFocusLoss = false;
        this.myCanvasFocusListener = new IntercessorKeyboardFocusListener();
        InputMaster.setInputMasterClient(this);
        this.myIntercessorClient = aClient;
        this.createCanvas();
        this.initConsole(new JSConsole(this));
    }
    
    private void createCanvas() {
        if (this.myCanvas == null) {
            (this.myCanvas = new JStellaCanvas()).addKeyListener(InputMaster.getKeyListener());
            this.myCanvas.addFocusListener(this.myCanvasFocusListener);
            InputMaster.addPaddleToComponent(0, this.myCanvas);
            if (this.myIntercessorClient != null) {
                this.myIntercessorClient.displayCanvas(this.myCanvas);
            }
        }
    }
    
    private void initConsole(final JSConsole aConsole) {
        if (this.myConsole != null && this.myConsole != aConsole) {
            this.myConsole.destroy();
        }
        (this.myConsole = aConsole).setConsoleClient(this);
        this.updateTelevisionMode();
        this.myCanvas.requestFocusInWindow();
        this.updateTimerDelay();
    }
    
    public Cartridge getCartridge() {
        return this.myConsole.getCartridge();
    }
    
    private void updateTimerDelay() {
        if (this.myConsole.getTelevisionMode() == 3) {
            this.myCurrentTimerDelay = 100;
        }
        else if (this.myConsole.getDisplayFormat() == JSConstants.DisplayFormat.PAL) {
            this.myCurrentTimerDelay = 20;
        }
        else {
            this.myCurrentTimerDelay = 17;
        }
        this.myConsole.getAudio().setRealDisplayFrameRate(1000.0 / this.myCurrentTimerDelay);
    }
    
    public void updateTelevisionMode() {
        if (this.myConsole.getCartridge() != null) {
            this.myConsole.setTelevisionMode(1);
        }
        else {
            final String zDefaultScreen = this.myIntercessorClient.getConfiguration().get("jstella.defaultscreen");
            if ("snow".equals(zDefaultScreen)) {
                this.myConsole.setTelevisionMode(3);
            }
            else {
                this.myConsole.setTelevisionMode(2);
            }
        }
        this.myConsole.updateVideoFrame();
        this.updateTimerDelay();
    }
    
    public boolean isVirtualJoystickEnabled() {
        return this.myVirtualJoystickDialog != null && this.myVirtualJoystickDialog.isVisible();
    }
    
    public void enableVirtualJoystick(final Frame aParent) {
        if (this.myVirtualJoystickDialog == null) {
            this.myVirtualJoystickDialog = new VirtualJoystickDialog(aParent);
        }
        this.myVirtualJoystickDialog.setVisible(true);
    }
    
    public void disableVirtualJoystick() {
        if (this.myVirtualJoystickDialog != null) {
            this.myVirtualJoystickDialog.setVisible(false);
        }
    }
    
    public void toggleVirtualJoystick(final Frame aParent) {
        if (!this.isVirtualJoystickEnabled()) {
            this.enableVirtualJoystick(aParent);
        }
        else {
            this.disableVirtualJoystick();
        }
    }
    
    public void startTimer() {
        this.myIntercessorClient.informUserOfPause(false);
        if (this.myUtilTimer != null) {
            this.myUtilTimer.cancel();
        }
        (this.myUtilTimer = new Timer(true)).scheduleAtFixedRate(new MainTimerTask(), this.myCurrentTimerDelay, this.myCurrentTimerDelay);
    }
    
    public void stopTimer() {
        if (this.myUtilTimer != null) {
            this.myUtilTimer.cancel();
            this.myUtilTimer = null;
        }
        this.myIntercessorClient.informUserOfPause(true);
    }
    
    public void destroy() {
        this.myConsole.destroy();
    }
    
    private void applyConfigurationToSwitches(final Map<String, String> aConfigMap) {
        if (aConfigMap.containsKey("jstella.switchon.player0difficulty")) {
            InputMaster.performSwitchTask(InputMaster.VirtualSwitchTask.SWITCH_DIFFICULTY_P0, JStellaRunnerUtil.isAffirmative(aConfigMap.get("jstella.switchon.player0difficulty")), this);
        }
        if (aConfigMap.containsKey("jstella.switchon.player1difficulty")) {
            InputMaster.performSwitchTask(InputMaster.VirtualSwitchTask.SWITCH_DIFFICULTY_P1, JStellaRunnerUtil.isAffirmative(aConfigMap.get("jstella.switchon.player1difficulty")), this);
        }
        if (aConfigMap.containsKey("jstella.console.switch.tvmode")) {
            InputMaster.performSwitchTask(InputMaster.VirtualSwitchTask.SWITCH_BW, JStellaRunnerUtil.isAffirmative(aConfigMap.get("jstella.console.switch.tvmode")), this);
        }
        if (aConfigMap.containsKey("jstella.console.switch.difficulty.left")) {
            InputMaster.performSwitchTask(InputMaster.VirtualSwitchTask.SWITCH_DIFFICULTY_P0, JStellaRunnerUtil.isAffirmative(aConfigMap.get("jstella.console.switch.difficulty.left")), this);
        }
        if (aConfigMap.containsKey("jstella.console.switch.difficulty.right")) {
            InputMaster.performSwitchTask(InputMaster.VirtualSwitchTask.SWITCH_DIFFICULTY_P1, JStellaRunnerUtil.isAffirmative(aConfigMap.get("jstella.console.switch.difficulty.right")), this);
        }
    }
    
    private void applyConfigurationToAudio(final Map<String, String> aConfigMap) {
        if (aConfigMap.containsKey("jstella.stereomode")) {
            this.setStereoSound(JStellaRunnerUtil.isAffirmative(aConfigMap.get("jstella.stereomode")));
        }
        if (aConfigMap.containsKey("jstella.audio.stereomode")) {
            this.setStereoSound(JStellaRunnerUtil.isAffirmative(aConfigMap.get("jstella.audio.stereomode")));
        }
    }
    
    private void applyConfigurationToDisplay(final Map<String, String> aConfigMap) {
        if (JStellaRunnerUtil.containsAffirmativeValue(aConfigMap, "jstella.display.antiflickermode")) {
            this.setPhosphorEnabled(true);
        }
        if (aConfigMap.containsKey("jstella.display.height")) {
            final int zHeight = this.getConfigInt(aConfigMap, "jstella.display.height");
            if (zHeight > 0) {
                this.myConsole.changeDisplayHeight(zHeight);
            }
        }
        if (aConfigMap.containsKey("jstella.display.ystart")) {
            final int zYStart = this.getConfigInt(aConfigMap, "jstella.display.ystart");
            if (zYStart > 0) {
                this.myConsole.changeYStart(zYStart);
            }
        }
    }
    
    public void applyConfigurationToControls(final Map<String, String> aConfigMap) {
        InputMaster.applyConfigurationToControls(aConfigMap);
    }
    
    public void applyConfiguration(final Map<String, String> aConfigMap) {
        this.applyConfigurationToSwitches(aConfigMap);
        this.applyConfigurationToAudio(aConfigMap);
        this.applyConfigurationToDisplay(aConfigMap);
        this.applyConfigurationToControls(aConfigMap);
        if (this.myIntercessorClient != null) {
            this.myIntercessorClient.updateGUI();
        }
    }
    
    private void resetGameConfiguration() {
        InputMaster.resetGameConfiguration();
    }
    
    private int getConfigInt(final Map<String, String> aConfigMap, final String aKey) {
        int zReturn = 0;
        try {
            if (aConfigMap.containsKey(aKey)) {
                zReturn = Integer.parseInt(aConfigMap.get(aKey));
            }
        }
        catch (NumberFormatException ex) {}
        return zReturn;
    }
    
    public void loadStateFromStream(final InputStream aInputStream) throws IOException, ClassNotFoundException {
        try {
            this.stopTimer();
            final ObjectInputStream zOIS = new ObjectInputStream(aInputStream);
            final Object zObj = zOIS.readObject();
            zOIS.close();
            if (zObj instanceof JSConsole) {
                final JSConsole zNewConsole = (JSConsole)zObj;
                this.initConsole(zNewConsole);
                this.myConsole.doFrame();
                this.updatePause();
                this.refocusKeyboard();
            }
        }
        catch (JSException e) {
            e.printStackTrace();
        }
    }
    
    public void saveStateToStream(final OutputStream aOutputStream) throws IOException {
        final boolean zIsPaused = this.isPausedByPlayer();
        this.setPausedByPlayer(true);
        final ObjectOutputStream zOOS = new ObjectOutputStream(aOutputStream);
        zOOS.writeObject(this.myConsole);
        zOOS.close();
        this.setPausedByPlayer(zIsPaused);
    }
    
    public void showDefaultExceptionResponse(final JSException e, final Component aDialogParent) {
        System.out.println("" + e.getMessage());
        if (e.getExceptionType() == JSException.ExceptionType.INSTRUCTION_NOT_RECOGNIZED) {
            JOptionPane.showMessageDialog(aDialogParent, "There was an error running the ROM.", "JSTELLA ERROR", 0);
        }
        else {
            JOptionPane.showMessageDialog(aDialogParent, e.getJStellaMessage(), "JSTELLA ERROR", 0);
        }
    }
    
    public void refocusKeyboard() {
        if (this.myCanvas != null && this.myCanvas.isVisible()) {
            this.myCanvas.requestFocus();
            this.myCanvas.requestFocusInWindow();
        }
    }
    
    private void runMainLoop() {
        if (this.myConsole != null) {
            try {
                this.myConsole.doFrame();
                if (this.myIntercessorClient != null) {
                    this.myIntercessorClient.runExtraLoopRoutines();
                }
            }
            catch (JSException e) {
                this.stopTimer();
                if (this.myIntercessorClient != null) {
                    this.myIntercessorClient.respondToException(e);
                }
            }
        }
    }
    
    private void updatePause() {
        if (!this.myPausedByFocusLoss && !this.myPausedByPlayer) {
            this.startTimer();
        }
        else {
            this.stopTimer();
            this.myConsole.pauseAudio();
            this.myConsole.grayCurrentFrame();
        }
    }
    
    public void emulateResetPress() {
        new SwitchToButtonAdapter(JSConstants.ConsoleSwitch.SWITCH_RESET);
    }
    
    public void emulateSelectPress() {
        new SwitchToButtonAdapter(JSConstants.ConsoleSwitch.SWITCH_SELECT);
    }
    
    public void setAutoPauseMode(final boolean aEnable) {
        this.myAutoPauseMode = aEnable;
        this.updatePause();
    }
    
    public boolean getAutoPauseMode() {
        return this.myAutoPauseMode;
    }
    
    public void setPausedByPlayer(final boolean aPause) {
        this.myPausedByPlayer = aPause;
        this.updatePause();
    }
    
    public boolean isPausedByPlayer() {
        return this.myPausedByPlayer;
    }
    
    public void lockPaddleMode() {
        InputMaster.setPaddleModeLock(false);
        InputMaster.setPaddleMode(true);
        InputMaster.setPaddleModeLock(true);
    }
    
    public void playJStellaGame(final JStellaGame aGame) {
        final String zCartridgeType = aGame.getGameConfig().get("jstella.game.cartridge.type");
        this.playROM(aGame.getROMData(), zCartridgeType);
        this.applyConfiguration(aGame.getGameConfig());
    }
    
    public void playROM(final InputStream aROMStream) {
        this.playROM(aROMStream, null, -1);
    }
    
    public void playROM(final InputStream aROMStream, final String aCartridgeType) {
        this.playROM(aROMStream, aCartridgeType, -1);
    }
    
    public void playROM(final InputStream aROMStream, final String aCartridgeType, final int aDisplayHeight) {
        try {
            this.stopTimer();
            final Cartridge zCart = JSConsole.createCartridge(aROMStream, aCartridgeType);
            if (zCart != null) {
                this.resetGameConfiguration();
                this.myConsole.insertCartridge(zCart, aDisplayHeight);
                this.updateTimerDelay();
                this.myCanvas.refreshCanvas();
                this.startTimer();
            }
            else {
                System.out.println("Error : could not create a cartridge object");
            }
        }
        catch (JSException e) {
            this.stopTimer();
            if (this.myIntercessorClient != null) {
                this.myIntercessorClient.respondToException(e);
            }
        }
    }
    
    public void playROM(final byte[] aROMData, final String aCartridgeType) {
        try {
            this.stopTimer();
            final Cartridge zCart = JSConsole.createCartridge(aROMData, aCartridgeType);
            if (zCart != null) {
                this.resetGameConfiguration();
                this.myConsole.insertCartridge(zCart);
                this.updateTimerDelay();
                this.myCanvas.refreshCanvas();
                this.startTimer();
            }
            else {
                System.out.println("Error : could not create a cartridge object");
            }
        }
        catch (JSException e) {
            this.stopTimer();
            if (this.myIntercessorClient != null) {
                this.myIntercessorClient.respondToException(e);
            }
        }
    }
    
    public void playROM(final Cartridge aCartridge) {
        this.playROM(aCartridge, -1);
    }
    
    public void playROM(final Cartridge aCartridge, final int aDisplayHeight) {
        try {
            this.stopTimer();
            this.resetGameConfiguration();
            this.myConsole.insertCartridge(aCartridge, aDisplayHeight);
            this.updateTimerDelay();
            this.myCanvas.refreshCanvas();
            this.startTimer();
            this.updateTelevisionMode();
        }
        catch (JSException e) {
            this.stopTimer();
            if (this.myIntercessorClient != null) {
                this.myIntercessorClient.respondToException(e);
            }
        }
    }
    
    public void setSoundEnabled(final boolean aEnabled) {
        this.myConsole.setSoundEnabled(aEnabled);
    }
    
    public boolean isSoundEnabled() {
        return this.myConsole.isSoundEnabled();
    }
    
    public void setPhosphorEnabled(final boolean aEnable) {
        this.myConsole.setPhosphorEnabled(aEnable);
    }
    
    public boolean isPhosphorEnabled() {
        return this.myConsole.isPhosphorEnabled();
    }
    
    public void setStereoSound(final boolean aEnable) {
        this.myConsole.setStereoSound(aEnable);
    }
    
    public boolean isStereoSound() {
        return this.myConsole.isStereoSound();
    }
    
    public boolean isTVTypeBW() {
        return this.myConsole.isSwitchOn(JSConstants.ConsoleSwitch.SWITCH_BW);
    }
    
    public void setTVTypeBW(final boolean aBW) {
        this.myConsole.flipSwitch(JSConstants.ConsoleSwitch.SWITCH_BW, aBW);
    }
    
    public boolean isPlayer0Amateur() {
        return this.myConsole.isSwitchOn(JSConstants.ConsoleSwitch.SWITCH_DIFFICULTY_P0);
    }
    
    public void setPlayer0Amateur(final boolean aAmateur) {
        this.myConsole.flipSwitch(JSConstants.ConsoleSwitch.SWITCH_DIFFICULTY_P0, aAmateur);
    }
    
    public boolean isPlayer1Amateur() {
        return this.myConsole.isSwitchOn(JSConstants.ConsoleSwitch.SWITCH_DIFFICULTY_P1);
    }
    
    public void setPlayer1Amateur(final boolean aAmateur) {
        this.myConsole.flipSwitch(JSConstants.ConsoleSwitch.SWITCH_DIFFICULTY_P1, aAmateur);
    }
    
    public void setSelectDown(final boolean aDown) {
        this.myConsole.flipSwitch(JSConstants.ConsoleSwitch.SWITCH_SELECT, aDown);
    }
    
    public void switchFlipped() {
        if (this.myIntercessorClient != null) {
            this.myIntercessorClient.updateSwitches();
        }
    }
    
    public JSConsole getConsole() {
        return this.myConsole;
    }
    
    public IfcCanvas getCanvas() {
        return this.myCanvas;
    }
    
    public JStellaCanvas getJStellaCanvas() {
        return this.myCanvas;
    }
    
    public void setLetterBoxMode(final boolean aEnable) {
        if (this.myCanvas != null) {
            this.myCanvas.setLetterBoxMode(aEnable);
        }
    }
    
    public boolean getLetterBoxMode() {
        return this.myCanvas != null && this.myCanvas.getLetterBoxMode();
    }
    
    private class MainTimerTask extends TimerTask implements ActionListener
    {
        public void run() {
            Intercessor.this.runMainLoop();
        }
        
        public void actionPerformed(final ActionEvent e) {
            Intercessor.this.runMainLoop();
        }
    }
    
    private class SwitchToButtonAdapter implements ActionListener
    {
        JSConstants.ConsoleSwitch mySwitchType;
        javax.swing.Timer mySwitchTimer;
        
        public SwitchToButtonAdapter(final JSConstants.ConsoleSwitch aSwitchType) {
            this.mySwitchType = null;
            this.mySwitchTimer = new javax.swing.Timer(50, this);
            this.mySwitchType = aSwitchType;
            Intercessor.this.myConsole.flipSwitch(aSwitchType, true);
            this.mySwitchTimer.setRepeats(false);
            this.mySwitchTimer.start();
        }
        
        public void actionPerformed(final ActionEvent e) {
            Intercessor.this.myConsole.flipSwitch(this.mySwitchType, false);
            this.mySwitchTimer.stop();
            this.mySwitchTimer = null;
        }
    }
    
    private class IntercessorKeyboardFocusListener implements FocusListener
    {
        public void focusLost(final FocusEvent e) {
            if (Intercessor.this.myAutoPauseMode && !(e.getOppositeComponent() instanceof JButton)) {
                Intercessor.this.myPausedByFocusLoss = true;
            }
            Intercessor.this.updatePause();
        }
        
        public void focusGained(final FocusEvent e) {
            Intercessor.this.myPausedByFocusLoss = false;
            Intercessor.this.updatePause();
        }
    }
    
    private class IntercessorInputVerifier extends InputVerifier
    {
        public boolean verify(final JComponent jComponent) {
            return false;
        }
    }
    
    public interface IfcIntercessorClient
    {
        void displayCanvas(final JPanel p0);
        
        boolean respondToException(final JSException p0);
        
        void informUserOfPause(final boolean p0);
        
        void updateSwitches();
        
        Map<String, String> getConfiguration();
        
        void updateGUI();
        
        void runExtraLoopRoutines();
    }
}
