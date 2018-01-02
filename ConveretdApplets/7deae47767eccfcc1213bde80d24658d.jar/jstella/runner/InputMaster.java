// 
// Decompiled by Procyon v0.5.30
// 

package jstella.runner;

import jstella.core.JSConsole;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseWheelEvent;
import javax.swing.SwingUtilities;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputListener;
import java.util.HashMap;
import java.util.ArrayList;
import jstella.core.JSController;
import jstella.core.JSConstants;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Toolkit;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Component;
import java.awt.event.KeyListener;
import java.util.Map;
import java.awt.Cursor;

public class InputMaster
{
    public static Cursor INVISIBLE_CURSOR;
    private static final int DEFAULT_PADDLE_SHIFT_PERCENTAGE = 3;
    public static final float ANALOG_POSITIVE_THRESHOLD = 0.75f;
    public static final float ANALOG_NEGATIVE_THRESHOLD = -0.75f;
    private static PaddleMouse[] myPaddleMouse;
    public static final InputControlBinder[] DEFAULT_CONTROL_BINDERS;
    public static final InputSwitchBinder[] DEFAULT_SWITCH_BINDERS;
    private static Map<Integer, InputSwitchBinder> myInputSwitchMap;
    private static Map<Integer, InputControlBinder> myInputControlMap;
    private static boolean myMouseMovementPaddleMode;
    private static boolean myPaddleModeLock;
    private static IfcInputMasterClient myInputMasterClient;
    private static KeyListener myKeyListener;
    private static Component myPaddleMouseComponent;
    private static PaddleMouseAxis myPaddleMouseAxis;
    private static int myPaddleShiftPercentage;
    
    public static void addPaddleToComponent(final int aPaddleIndex, final Component aComp) {
        InputMaster.myPaddleMouseComponent = aComp;
        final PaddleMouse zPM = getPaddleMouse(aPaddleIndex);
        changeCursor(aComp, !InputMaster.myMouseMovementPaddleMode);
        aComp.addMouseListener(zPM);
        aComp.addMouseMotionListener(zPM);
        aComp.addMouseWheelListener(zPM);
    }
    
    private static void createInvisibleCursor() {
        final int[] pixels = new int[256];
        final Image image = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(16, 16, pixels, 0, 16));
        final Cursor zCursor = InputMaster.INVISIBLE_CURSOR = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(0, 0), "invisibleCursor");
    }
    
    public static void setPaddleModeLock(final boolean aLocked) {
        InputMaster.myPaddleModeLock = aLocked;
    }
    
    public static void setPaddleMode(final boolean aEnabled) {
        setPaddleMode(InputMaster.myPaddleMouseComponent, aEnabled);
    }
    
    public static void setPaddleMode(final Component aComp, final boolean aEnabled) {
        if (!InputMaster.myPaddleModeLock) {
            InputMaster.myMouseMovementPaddleMode = aEnabled;
        }
        if (aComp != null) {
            changeCursor(aComp, !InputMaster.myMouseMovementPaddleMode);
        }
    }
    
    public static boolean getPaddleMode() {
        return InputMaster.myMouseMovementPaddleMode;
    }
    
    private static void toggleMouseMode(final Component aComp) {
        setPaddleMode(aComp, !getPaddleMode());
    }
    
    public static void setPaddleMouseAxis(final PaddleMouseAxis aAxis) {
        InputMaster.myPaddleMouseAxis = aAxis;
    }
    
    public static PaddleMouseAxis getPaddleMouseAxis() {
        return InputMaster.myPaddleMouseAxis;
    }
    
    public static boolean isPaddles(final String aConfigValue) {
        boolean zReturn = false;
        if (aConfigValue != null) {
            final String zLowerCaseConfigValue = aConfigValue.toLowerCase().trim();
            if (zLowerCaseConfigValue.equals("paddles") || zLowerCaseConfigValue.equals("paddle")) {
                zReturn = true;
            }
        }
        return zReturn;
    }
    
    public static void resetGameConfiguration() {
        setPaddleMode(false);
    }
    
    public static void applyConfigurationToControls(final Map<String, String> aConfigMap) {
        final boolean zClearPreviousControls = JStellaRunnerUtil.containsAffirmativeValue(aConfigMap, "jstella.control.clearprevious");
        final List<InputControlBinder> zCIList = convertConfigMapToControlBinderList(aConfigMap);
        setControls(zCIList, zClearPreviousControls);
        if (isPaddles(aConfigMap.get("jstella.controller.left.type"))) {
            setPaddleMode(true);
        }
    }
    
    public static void setControls(final List<InputControlBinder> aControlItemList, final boolean aClearPrevious) {
        if (aClearPrevious) {
            InputMaster.myInputControlMap.clear();
        }
        for (final InputControlBinder zItem : aControlItemList) {
            InputMaster.myInputControlMap.put(new Integer(zItem.getVKCode()), zItem);
        }
    }
    
    public static void setControls(final InputControlBinder[] aControlItemArray, final boolean aClearPrevious) {
        setControls(Arrays.asList(aControlItemArray), aClearPrevious);
    }
    
    public static void setSwitches(final List<InputSwitchBinder> aSwitchMappingList) {
        InputMaster.myInputSwitchMap.clear();
        for (final InputSwitchBinder zItem : aSwitchMappingList) {
            InputMaster.myInputSwitchMap.put(new Integer(zItem.getVKCode()), zItem);
        }
    }
    
    private static void changeCursor(final Component aComp, final boolean aVisible) {
        if (aVisible) {
            aComp.setCursor(Cursor.getDefaultCursor());
        }
        else {
            aComp.setCursor(InputMaster.INVISIBLE_CURSOR);
        }
    }
    
    public static void setInputMasterClient(final IfcInputMasterClient aClient) {
        InputMaster.myInputMasterClient = aClient;
    }
    
    public static PaddleMouse getPaddleMouse(final int aIndex) {
        return InputMaster.myPaddleMouse[aIndex];
    }
    
    public static KeyListener getKeyListener() {
        return InputMaster.myKeyListener;
    }
    
    public static void addDefaultControlItemsToConfigMap(final Map<String, String> aConfigMap) {
        addControlBinderListToConfigMap(Arrays.asList(InputMaster.DEFAULT_CONTROL_BINDERS), aConfigMap);
    }
    
    public static boolean checkConfigMapForControls(final Map<String, String> aConfigMap) {
        boolean zFoundControl = false;
        final VirtualControlTask[] zVCI = VirtualControlTask.values();
        for (int i = 0; i < zVCI.length; ++i) {
            if (aConfigMap.get(zVCI[i].getConfigKey()) != null) {
                zFoundControl = true;
                break;
            }
        }
        return zFoundControl;
    }
    
    public static boolean processInputKeyEvent(final int aVKCode, final boolean aPressed) {
        boolean zReturn = false;
        final Integer zVK = new Integer(aVKCode);
        final InputControlBinder zCI = InputMaster.myInputControlMap.get(zVK);
        if (zCI != null) {
            performControlItemAction(zCI.getTarget(), aPressed);
        }
        final InputSwitchBinder zSM = InputMaster.myInputSwitchMap.get(zVK);
        if (zSM != null) {
            if (zSM.myFullControl) {
                performSwitchTask(zSM.getVirtualSwitchTask(), aPressed, InputMaster.myInputMasterClient);
                zReturn = true;
            }
            else if (zSM.myKeyPressed == aPressed) {
                performSwitchTask(zSM.getVirtualSwitchTask(), zSM.myTurnOn, InputMaster.myInputMasterClient);
                zReturn = true;
            }
        }
        return zReturn;
    }
    
    private static void performControlItemAction(final VirtualControlTask aVItem, final boolean aPressed) {
        final JSController zSControllerA = InputMaster.myInputMasterClient.getConsole().getController(JSConstants.Jack.LEFT);
        final JSController zSControllerB = InputMaster.myInputMasterClient.getConsole().getController(JSConstants.Jack.RIGHT);
        switch (aVItem) {
            case JOYSTICK_A_UP: {
                zSControllerA.changeControllerState(0, aPressed);
                break;
            }
            case JOYSTICK_A_DOWN: {
                zSControllerA.changeControllerState(1, aPressed);
                break;
            }
            case JOYSTICK_A_LEFT: {
                zSControllerA.changeControllerState(2, aPressed);
                break;
            }
            case JOYSTICK_A_RIGHT: {
                zSControllerA.changeControllerState(3, aPressed);
                break;
            }
            case JOYSTICK_A_BUTTON: {
                zSControllerA.changeControllerState(5, aPressed);
                break;
            }
            case JOYSTICK_B_UP: {
                zSControllerB.changeControllerState(0, aPressed);
                break;
            }
            case JOYSTICK_B_DOWN: {
                zSControllerB.changeControllerState(1, aPressed);
                break;
            }
            case JOYSTICK_B_LEFT: {
                zSControllerB.changeControllerState(2, aPressed);
                break;
            }
            case JOYSTICK_B_RIGHT: {
                zSControllerB.changeControllerState(3, aPressed);
                break;
            }
            case JOYSTICK_B_BUTTON: {
                zSControllerB.changeControllerState(5, aPressed);
                break;
            }
            case PADDLE_A_BUTTON: {
                zSControllerA.changeControllerState(3, aPressed);
                break;
            }
            case PADDLE_B_BUTTON: {
                zSControllerA.changeControllerState(2, aPressed);
                break;
            }
            case PADDLE_C_BUTTON: {
                zSControllerB.changeControllerState(3, aPressed);
                break;
            }
            case PADDLE_D_BUTTON: {
                zSControllerB.changeControllerState(2, aPressed);
                break;
            }
            case PADDLE_A_CW: {
                if (aPressed) {
                    zSControllerA.changePaddlePosition(JSController.PaddleID.PADDLE_ALPHA, InputMaster.myPaddleShiftPercentage);
                    break;
                }
                break;
            }
            case PADDLE_B_CW: {
                if (aPressed) {
                    zSControllerA.changePaddlePosition(JSController.PaddleID.PADDLE_BETA, InputMaster.myPaddleShiftPercentage);
                    break;
                }
                break;
            }
            case PADDLE_C_CW: {
                if (aPressed) {
                    zSControllerB.changePaddlePosition(JSController.PaddleID.PADDLE_ALPHA, InputMaster.myPaddleShiftPercentage);
                    break;
                }
                break;
            }
            case PADDLE_D_CW: {
                if (aPressed) {
                    zSControllerB.changePaddlePosition(JSController.PaddleID.PADDLE_BETA, InputMaster.myPaddleShiftPercentage);
                    break;
                }
                break;
            }
            case PADDLE_A_CCW: {
                if (aPressed) {
                    zSControllerA.changePaddlePosition(JSController.PaddleID.PADDLE_ALPHA, -1 * InputMaster.myPaddleShiftPercentage);
                    break;
                }
                break;
            }
            case PADDLE_B_CCW: {
                if (aPressed) {
                    zSControllerA.changePaddlePosition(JSController.PaddleID.PADDLE_BETA, -1 * InputMaster.myPaddleShiftPercentage);
                    break;
                }
                break;
            }
            case PADDLE_C_CCW: {
                if (aPressed) {
                    zSControllerB.changePaddlePosition(JSController.PaddleID.PADDLE_ALPHA, -1 * InputMaster.myPaddleShiftPercentage);
                    break;
                }
                break;
            }
            case PADDLE_D_CCW: {
                if (aPressed) {
                    zSControllerB.changePaddlePosition(JSController.PaddleID.PADDLE_BETA, -1 * InputMaster.myPaddleShiftPercentage);
                    break;
                }
                break;
            }
            case BOOSTERGRIP_A_BOOSTER: {
                zSControllerA.setBoosterGripBooster(aPressed);
                break;
            }
            case BOOSTERGRIP_B_BOOSTER: {
                zSControllerB.setBoosterGripBooster(aPressed);
                break;
            }
            case BOOSTERGRIP_A_TRIGGER: {
                zSControllerA.setBoosterGripTrigger(aPressed);
                break;
            }
            case BOOSTERGRIP_B_TRIGGER: {
                zSControllerB.setBoosterGripTrigger(aPressed);
                break;
            }
        }
    }
    
    public static void performSwitchTask(final VirtualSwitchTask aTask, final boolean aTurnOn, final IfcInputMasterClient aMonitor) {
        final JSConstants.ConsoleSwitch zConsoleSwitch = aTask.getConsoleSwitch();
        InputMaster.myInputMasterClient.getConsole().flipSwitch(zConsoleSwitch, aTurnOn);
        if (aMonitor != null) {
            aMonitor.switchFlipped();
        }
    }
    
    public static void setControllerAButton(final boolean aButtonPressed) {
        final JSController zSControllerA = InputMaster.myInputMasterClient.getConsole().getController(JSConstants.Jack.LEFT);
        zSControllerA.changeControllerState(5, aButtonPressed);
    }
    
    public static void setControllerADirections(final boolean[] aUpDownLeftRight) {
        final JSController zSControllerA = InputMaster.myInputMasterClient.getConsole().getController(JSConstants.Jack.LEFT);
        zSControllerA.changeControllerState(0, aUpDownLeftRight[0]);
        zSControllerA.changeControllerState(1, aUpDownLeftRight[1]);
        zSControllerA.changeControllerState(2, aUpDownLeftRight[2]);
        zSControllerA.changeControllerState(3, aUpDownLeftRight[3]);
    }
    
    public static void setXAxis(final float aPosition) {
        final JSController zSControllerA = InputMaster.myInputMasterClient.getConsole().getController(JSConstants.Jack.LEFT);
        zSControllerA.changeControllerState(2, aPosition < -0.75f);
        zSControllerA.changeControllerState(3, aPosition > 0.75f);
    }
    
    public static void setYAxis(final float aPosition) {
        final JSController zSControllerA = InputMaster.myInputMasterClient.getConsole().getController(JSConstants.Jack.LEFT);
        zSControllerA.changeControllerState(0, aPosition < -0.75f);
        zSControllerA.changeControllerState(1, aPosition > 0.75f);
    }
    
    public static int[] convertIntegerStringToArray(final String aIntString) throws NumberFormatException {
        int[] zReturn = new int[0];
        String zCurrentString = aIntString;
        final List<Integer> zIntList = new ArrayList<Integer>();
        boolean zBreak = false;
        do {
            final int zLeftIndex = zCurrentString.indexOf("(");
            final int zRightIndex = zCurrentString.indexOf(")");
            if (zLeftIndex != -1 && zRightIndex != -1 && zLeftIndex < zRightIndex) {
                final String zSubStr = zCurrentString.substring(zLeftIndex + 1, zRightIndex);
                final int zParsedInt = Integer.parseInt(zSubStr);
                zIntList.add(new Integer(zParsedInt));
                zCurrentString = zCurrentString.substring(zRightIndex + 1);
            }
            else {
                zBreak = true;
            }
        } while (!zBreak);
        zReturn = new int[zIntList.size()];
        for (int i = 0; i < zReturn.length; ++i) {
            zReturn[i] = zIntList.get(i);
        }
        return zReturn;
    }
    
    public static String convertIntegerArrayToString(final int[] aArray) {
        final StringBuffer zSB = new StringBuffer();
        for (int i = 0; i < aArray.length; ++i) {
            zSB.append("(" + aArray[i] + ")");
        }
        return zSB.toString();
    }
    
    public static void addControlBinderListToConfigMap(final List<InputControlBinder> aControlBinderList, final Map<String, String> aConfigMap) {
        final int zCount = aControlBinderList.size();
        for (final InputControlBinder zCI : aControlBinderList) {
            String zStrToAdd = "(" + zCI.getVKCode() + ")";
            if (aConfigMap.containsKey(zCI.getTarget().getConfigKey())) {
                zStrToAdd = "" + aConfigMap.get(zCI.getTarget().getConfigKey()) + zStrToAdd;
            }
            aConfigMap.put(zCI.getTarget().getConfigKey(), zStrToAdd);
        }
    }
    
    public static List<InputControlBinder> convertConfigMapToControlBinderList(final Map<String, String> aConfigMap) {
        final List<InputControlBinder> zReturn = new ArrayList<InputControlBinder>();
        final VirtualControlTask[] zVCIArray = VirtualControlTask.values();
        for (int iItem = 0; iItem < zVCIArray.length; ++iItem) {
            if (aConfigMap.containsKey(zVCIArray[iItem].getConfigKey())) {
                final String zValue = aConfigMap.get(zVCIArray[iItem].getConfigKey());
                final int[] zValueInts = convertIntegerStringToArray(zValue);
                for (int iValue = 0; iValue < zValueInts.length; ++iValue) {
                    zReturn.add(new InputControlBinder(zValueInts[iValue], zVCIArray[iItem]));
                }
            }
        }
        return zReturn;
    }
    
    static {
        createInvisibleCursor();
        InputMaster.myPaddleMouse = new PaddleMouse[] { new PaddleMouse(JSConstants.Jack.LEFT, JSController.PaddleID.PADDLE_ALPHA), new PaddleMouse(JSConstants.Jack.LEFT, JSController.PaddleID.PADDLE_BETA), new PaddleMouse(JSConstants.Jack.RIGHT, JSController.PaddleID.PADDLE_ALPHA), new PaddleMouse(JSConstants.Jack.RIGHT, JSController.PaddleID.PADDLE_BETA) };
        DEFAULT_CONTROL_BINDERS = new InputControlBinder[] { new InputControlBinder(38, VirtualControlTask.JOYSTICK_A_UP), new InputControlBinder(40, VirtualControlTask.JOYSTICK_A_DOWN), new InputControlBinder(37, VirtualControlTask.JOYSTICK_A_LEFT), new InputControlBinder(39, VirtualControlTask.JOYSTICK_A_RIGHT), new InputControlBinder(32, VirtualControlTask.JOYSTICK_A_BUTTON), new InputControlBinder(10, VirtualControlTask.JOYSTICK_A_BUTTON), new InputControlBinder(87, VirtualControlTask.JOYSTICK_B_UP), new InputControlBinder(83, VirtualControlTask.JOYSTICK_B_DOWN), new InputControlBinder(65, VirtualControlTask.JOYSTICK_B_LEFT), new InputControlBinder(68, VirtualControlTask.JOYSTICK_B_RIGHT), new InputControlBinder(17, VirtualControlTask.JOYSTICK_B_BUTTON), new InputControlBinder(89, VirtualControlTask.JOYSTICK_B_BUTTON), new InputControlBinder(44, VirtualControlTask.PADDLE_A_CCW), new InputControlBinder(46, VirtualControlTask.PADDLE_A_CW), new InputControlBinder(90, VirtualControlTask.PADDLE_A_BUTTON), new InputControlBinder(71, VirtualControlTask.BOOSTERGRIP_A_BOOSTER), new InputControlBinder(70, VirtualControlTask.BOOSTERGRIP_A_TRIGGER) };
        DEFAULT_SWITCH_BINDERS = new InputSwitchBinder[] { new InputSwitchBinder(112, VirtualSwitchTask.SWITCH_RESET), new InputSwitchBinder(113, VirtualSwitchTask.SWITCH_SELECT), new InputSwitchBinder(114, true, VirtualSwitchTask.SWITCH_BW, true), new InputSwitchBinder(115, true, VirtualSwitchTask.SWITCH_BW, false), new InputSwitchBinder(116, true, VirtualSwitchTask.SWITCH_DIFFICULTY_P0, true), new InputSwitchBinder(117, true, VirtualSwitchTask.SWITCH_DIFFICULTY_P0, false), new InputSwitchBinder(118, true, VirtualSwitchTask.SWITCH_DIFFICULTY_P1, true), new InputSwitchBinder(119, true, VirtualSwitchTask.SWITCH_DIFFICULTY_P1, false) };
        InputMaster.myInputSwitchMap = new HashMap<Integer, InputSwitchBinder>();
        InputMaster.myInputControlMap = new HashMap<Integer, InputControlBinder>();
        InputMaster.myMouseMovementPaddleMode = false;
        InputMaster.myPaddleModeLock = false;
        InputMaster.myInputMasterClient = null;
        InputMaster.myKeyListener = null;
        InputMaster.myPaddleMouseComponent = null;
        InputMaster.myPaddleMouseAxis = PaddleMouseAxis.MOUSE_X;
        InputMaster.myPaddleShiftPercentage = 3;
        InputMaster.myKeyListener = new JSKeyListener();
        setControls(InputMaster.DEFAULT_CONTROL_BINDERS, false);
        setSwitches(Arrays.asList(InputMaster.DEFAULT_SWITCH_BINDERS));
    }
    
    public enum PaddleMouseAxis
    {
        MOUSE_X, 
        MOUSE_Y, 
        MOUSE_XY;
    }
    
    private static class PaddleMouse implements MouseInputListener, MouseWheelListener
    {
        private JSConstants.Jack myJack;
        private JSController.PaddleID myPaddleID;
        private int myLastX;
        private double myScrollFactor;
        
        private PaddleMouse(final JSConstants.Jack aJack, final JSController.PaddleID aPaddleID) {
            this.myJack = JSConstants.Jack.LEFT;
            this.myPaddleID = JSController.PaddleID.PADDLE_ALPHA;
            this.myLastX = 0;
            this.myScrollFactor = 3.0;
            this.myJack = aJack;
            this.myPaddleID = aPaddleID;
        }
        
        private void paddleMouseMoved(final MouseEvent e) {
            if (InputMaster.myMouseMovementPaddleMode) {
                final Component zComp = e.getComponent();
                if (zComp != null) {
                    this.myLastX = e.getX();
                    int zPercentage = 0;
                    if (InputMaster.getPaddleMouseAxis() == PaddleMouseAxis.MOUSE_X) {
                        zPercentage = (int)(this.myLastX / zComp.getWidth() * 100.0);
                    }
                    else {
                        zPercentage = (int)(e.getY() / zComp.getHeight() * 100.0);
                    }
                    final JSController zCtrl = InputMaster.myInputMasterClient.getConsole().getController(this.myJack);
                    if (zCtrl != null) {
                        zCtrl.setPaddlePosition(this.myPaddleID, zPercentage);
                    }
                }
            }
        }
        
        public void mouseMoved(final MouseEvent e) {
            this.paddleMouseMoved(e);
        }
        
        public void mouseDragged(final MouseEvent e) {
            this.paddleMouseMoved(e);
        }
        
        public void mouseReleased(final MouseEvent e) {
            final boolean zLMB = SwingUtilities.isLeftMouseButton(e);
            if (zLMB) {
                final JSController zCtrl = InputMaster.myInputMasterClient.getConsole().getController(this.myJack);
                if (zCtrl != null) {
                    zCtrl.setPaddleTrigger(this.myPaddleID, false);
                }
            }
        }
        
        public void mousePressed(final MouseEvent e) {
            final boolean zLMB = SwingUtilities.isLeftMouseButton(e);
            if (zLMB) {
                final JSController zCtrl = InputMaster.myInputMasterClient.getConsole().getController(this.myJack);
                if (zCtrl != null) {
                    zCtrl.setPaddleTrigger(this.myPaddleID, true);
                }
            }
            else if (SwingUtilities.isRightMouseButton(e)) {
                toggleMouseMode(e.getComponent());
            }
        }
        
        public void mouseExited(final MouseEvent e) {
        }
        
        public void mouseEntered(final MouseEvent e) {
        }
        
        public void mouseClicked(final MouseEvent e) {
        }
        
        public void mouseWheelMoved(final MouseWheelEvent e) {
            final JSController zCtrl = InputMaster.myInputMasterClient.getConsole().getController(this.myJack);
            zCtrl.changePaddlePosition(this.myPaddleID, (int)(e.getWheelRotation() * this.myScrollFactor));
        }
    }
    
    private static class JSKeyListener extends KeyAdapter
    {
        public void keyPressed(final KeyEvent e) {
            super.keyPressed(e);
            final boolean zMatch = InputMaster.processInputKeyEvent(e.getKeyCode(), true);
            if (zMatch) {
                e.consume();
            }
        }
        
        public void keyReleased(final KeyEvent e) {
            super.keyReleased(e);
            final boolean zMatch = InputMaster.processInputKeyEvent(e.getKeyCode(), false);
            if (zMatch) {
                e.consume();
            }
        }
    }
    
    public enum VirtualSwitchTask
    {
        SWITCH_RESET(JSConstants.ConsoleSwitch.SWITCH_RESET, "Switch : Reset"), 
        SWITCH_SELECT(JSConstants.ConsoleSwitch.SWITCH_SELECT, "Switch : Select"), 
        SWITCH_BW(JSConstants.ConsoleSwitch.SWITCH_BW, "Switch : B/W mode"), 
        SWITCH_DIFFICULTY_P0(JSConstants.ConsoleSwitch.SWITCH_DIFFICULTY_P0, "Switch : Left player difficulty"), 
        SWITCH_DIFFICULTY_P1(JSConstants.ConsoleSwitch.SWITCH_DIFFICULTY_P1, "Switch : Right player difficulty");
        
        private String myDescription;
        private JSConstants.ConsoleSwitch myConsoleSwitch;
        
        private VirtualSwitchTask(final JSConstants.ConsoleSwitch aSwitch, final String aDescription) {
            this.myDescription = "";
            this.myConsoleSwitch = null;
            this.myConsoleSwitch = aSwitch;
            this.myDescription = aDescription;
        }
        
        public JSConstants.ConsoleSwitch getConsoleSwitch() {
            return this.myConsoleSwitch;
        }
    }
    
    public static class InputSwitchBinder
    {
        private boolean myTurnOn;
        private boolean myFullControl;
        private VirtualSwitchTask myVirtualSwitchTask;
        private int myVKCode;
        private boolean myKeyPressed;
        
        public InputSwitchBinder(final int aVKCode, final boolean aKeyPressed, final VirtualSwitchTask aSwitch, final boolean aTurnOn) {
            this.myTurnOn = true;
            this.myFullControl = true;
            this.myVirtualSwitchTask = VirtualSwitchTask.SWITCH_RESET;
            this.myVKCode = 0;
            this.myKeyPressed = true;
            this.myFullControl = false;
            this.myVKCode = aVKCode;
            this.myKeyPressed = aKeyPressed;
            this.myVirtualSwitchTask = aSwitch;
            this.myTurnOn = aTurnOn;
        }
        
        public InputSwitchBinder(final int aVKCode, final VirtualSwitchTask aSwitch) {
            this.myTurnOn = true;
            this.myFullControl = true;
            this.myVirtualSwitchTask = VirtualSwitchTask.SWITCH_RESET;
            this.myVKCode = 0;
            this.myKeyPressed = true;
            this.myFullControl = true;
            this.myVKCode = aVKCode;
            this.myVirtualSwitchTask = aSwitch;
        }
        
        public int getVKCode() {
            return this.myVKCode;
        }
        
        public VirtualSwitchTask getVirtualSwitchTask() {
            return this.myVirtualSwitchTask;
        }
    }
    
    public enum VirtualControlTask
    {
        JOYSTICK_A_UP("Joystick A : up", "joystick.a.up"), 
        JOYSTICK_A_DOWN("Joystick A : down", "joystick.a.down"), 
        JOYSTICK_A_LEFT("Joystick A : left", "joystick.a.left"), 
        JOYSTICK_A_RIGHT("Joystick A : right", "joystick.a.right"), 
        JOYSTICK_A_BUTTON("Joystick A : button", "joystick.a.button"), 
        JOYSTICK_B_UP("Joystick B : up", "joystick.b.up"), 
        JOYSTICK_B_DOWN("Joystick B : down", "joystick.b.down"), 
        JOYSTICK_B_LEFT("Joystick B : left", "joystick.b.left"), 
        JOYSTICK_B_RIGHT("Joystick B : right", "joystick.b.right"), 
        JOYSTICK_B_BUTTON("Joystick B : button", "joystick.b.button"), 
        PADDLE_A_CW("Paddle A : clockwise", "paddle.a.cw"), 
        PADDLE_A_CCW("Paddle A : counter-clockwise", "paddle.a.ccw"), 
        PADDLE_A_BUTTON("Paddle A : button", "paddle.a.button"), 
        PADDLE_B_CW("Paddle B : clockwise", "paddle.b.cw"), 
        PADDLE_B_CCW("Paddle B : counter-clockwise", "paddle.b.ccw"), 
        PADDLE_B_BUTTON("Paddle B : button", "paddle.b.button"), 
        PADDLE_C_CW("Paddle C : clockwise", "paddle.c.cw"), 
        PADDLE_C_CCW("Paddle C : counter-clockwise", "paddle.c.ccw"), 
        PADDLE_C_BUTTON("Paddle C : button", "paddle.c.button"), 
        PADDLE_D_CW("Paddle D : clockwise", "paddle.d.cw"), 
        PADDLE_D_CCW("Paddle D : counter-clockwise", "paddle.d.ccw"), 
        PADDLE_D_BUTTON("Paddle D : button", "paddle.d.button"), 
        BOOSTERGRIP_A_BOOSTER("Booster Grip A : booster", "boostergrip.a.booster"), 
        BOOSTERGRIP_A_TRIGGER("Booster Grip A : trigger", "boostergrip.a.trigger"), 
        BOOSTERGRIP_B_BOOSTER("Booster Grip B : booster", "boostergrip.b.booster"), 
        BOOSTERGRIP_B_TRIGGER("Booster Grip B : trigger", "boostergrip.b.trigger");
        
        private String myDescription;
        private String myConfigKey;
        
        private VirtualControlTask(final String aDescription, final String aConfigKey) {
            this.myDescription = "";
            this.myConfigKey = "";
            this.myDescription = aDescription;
            this.myConfigKey = "jstella.control." + aConfigKey;
        }
        
        public String toString() {
            return this.myDescription;
        }
        
        public String getConfigKey() {
            return this.myConfigKey;
        }
    }
    
    public static class InputControlBinder
    {
        private int myVKCode;
        private VirtualControlTask myTarget;
        
        public InputControlBinder(final int aVKCode, final VirtualControlTask aTarget) {
            this.myVKCode = 0;
            this.myTarget = null;
            this.myVKCode = aVKCode;
            this.myTarget = aTarget;
        }
        
        public String getActualKeyText() {
            return KeyEvent.getKeyText(this.myVKCode);
        }
        
        public VirtualControlTask getTarget() {
            return this.myTarget;
        }
        
        public int getVKCode() {
            return this.myVKCode;
        }
        
        public int hashCode() {
            if (this.myTarget != null) {
                return this.myVKCode << 8 ^ this.myTarget.hashCode();
            }
            return this.myVKCode << 8;
        }
        
        public boolean equals(final Object object) {
            boolean zReturn = false;
            if (object instanceof InputControlBinder) {
                final InputControlBinder zOther = (InputControlBinder)object;
                if (zOther.myVKCode == this.myVKCode && zOther.myTarget == this.myTarget) {
                    zReturn = true;
                }
            }
            return zReturn;
        }
    }
    
    public interface IfcInputMasterClient
    {
        void switchFlipped();
        
        JSConsole getConsole();
    }
}
