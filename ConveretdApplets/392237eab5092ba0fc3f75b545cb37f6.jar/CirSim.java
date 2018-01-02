import java.awt.event.KeyEvent;
import java.awt.ItemSelectable;
import java.awt.event.ItemEvent;
import java.lang.reflect.Constructor;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.StringTokenizer;
import java.io.File;
import java.io.IOException;
import java.io.FilterInputStream;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.awt.event.AdjustmentEvent;
import java.net.URLEncoder;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.Point;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Color;
import java.awt.Font;
import java.awt.MenuShortcut;
import java.awt.MenuBar;
import java.awt.Component;
import java.awt.LayoutManager;
import java.net.URLDecoder;
import java.util.Vector;
import java.awt.Rectangle;
import java.awt.PopupMenu;
import java.awt.Scrollbar;
import java.awt.CheckboxMenuItem;
import java.awt.Checkbox;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.Button;
import java.awt.Label;
import java.awt.Container;
import java.util.Random;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.AdjustmentListener;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class CirSim extends Frame implements ComponentListener, ActionListener, AdjustmentListener, MouseMotionListener, MouseListener, ItemListener, KeyListener
{
    Thread engine;
    Dimension winSize;
    Image dbimage;
    Random random;
    public static final int sourceRadius = 7;
    public static final double freqMult = 25.1327412;
    static Container main;
    Label titleLabel;
    Button resetButton;
    Button dumpMatrixButton;
    MenuItem exportItem;
    MenuItem exportLinkItem;
    MenuItem importItem;
    MenuItem exitItem;
    MenuItem undoItem;
    MenuItem redoItem;
    MenuItem cutItem;
    MenuItem copyItem;
    MenuItem pasteItem;
    MenuItem selectAllItem;
    MenuItem optionsItem;
    Menu optionsMenu;
    Checkbox stoppedCheck;
    CheckboxMenuItem dotsCheckItem;
    CheckboxMenuItem voltsCheckItem;
    CheckboxMenuItem powerCheckItem;
    CheckboxMenuItem smallGridCheckItem;
    CheckboxMenuItem showValuesCheckItem;
    CheckboxMenuItem conductanceCheckItem;
    CheckboxMenuItem euroResistorCheckItem;
    CheckboxMenuItem printableCheckItem;
    CheckboxMenuItem conventionCheckItem;
    Scrollbar speedBar;
    Scrollbar currentBar;
    Label powerLabel;
    Scrollbar powerBar;
    PopupMenu elmMenu;
    MenuItem elmEditMenuItem;
    MenuItem elmCutMenuItem;
    MenuItem elmCopyMenuItem;
    MenuItem elmDeleteMenuItem;
    MenuItem elmScopeMenuItem;
    PopupMenu scopeMenu;
    PopupMenu transScopeMenu;
    PopupMenu mainMenu;
    CheckboxMenuItem scopeVMenuItem;
    CheckboxMenuItem scopeIMenuItem;
    CheckboxMenuItem scopeMaxMenuItem;
    CheckboxMenuItem scopeMinMenuItem;
    CheckboxMenuItem scopeFreqMenuItem;
    CheckboxMenuItem scopePowerMenuItem;
    CheckboxMenuItem scopeIbMenuItem;
    CheckboxMenuItem scopeIcMenuItem;
    CheckboxMenuItem scopeIeMenuItem;
    CheckboxMenuItem scopeVbeMenuItem;
    CheckboxMenuItem scopeVbcMenuItem;
    CheckboxMenuItem scopeVceMenuItem;
    CheckboxMenuItem scopeVIMenuItem;
    CheckboxMenuItem scopeXYMenuItem;
    CheckboxMenuItem scopeResistMenuItem;
    CheckboxMenuItem scopeVceIcMenuItem;
    MenuItem scopeSelectYMenuItem;
    Class addingClass;
    int mouseMode;
    int tempMouseMode;
    String mouseModeStr;
    static final double pi = 3.141592653589793;
    static final int MODE_ADD_ELM = 0;
    static final int MODE_DRAG_ALL = 1;
    static final int MODE_DRAG_ROW = 2;
    static final int MODE_DRAG_COLUMN = 3;
    static final int MODE_DRAG_SELECTED = 4;
    static final int MODE_DRAG_POST = 5;
    static final int MODE_SELECT = 6;
    static final int infoWidth = 120;
    int dragX;
    int dragY;
    int initDragX;
    int initDragY;
    int selectedSource;
    Rectangle selectedArea;
    int gridSize;
    int gridMask;
    int gridRound;
    boolean dragging;
    boolean analyzeFlag;
    boolean dumpMatrix;
    boolean useBufferedImage;
    boolean isMac;
    String ctrlMetaKey;
    double t;
    int pause;
    int scopeSelected;
    int menuScope;
    int hintType;
    int hintItem1;
    int hintItem2;
    String stopMessage;
    double timeStep;
    static final int HINT_LC = 1;
    static final int HINT_RC = 2;
    static final int HINT_3DB_C = 3;
    static final int HINT_TWINT = 4;
    static final int HINT_3DB_L = 5;
    Vector elmList;
    Vector setupList;
    CircuitElm dragElm;
    CircuitElm menuElm;
    CircuitElm mouseElm;
    CircuitElm stopElm;
    int mousePost;
    CircuitElm plotXElm;
    CircuitElm plotYElm;
    int draggingPost;
    SwitchElm heldSwitchElm;
    double[][] circuitMatrix;
    double[] circuitRightSide;
    double[] origRightSide;
    double[][] origMatrix;
    RowInfo[] circuitRowInfo;
    int[] circuitPermute;
    boolean circuitNonLinear;
    int voltageSourceCount;
    int circuitMatrixSize;
    int circuitMatrixFullSize;
    boolean circuitNeedsMap;
    public boolean useFrame;
    int scopeCount;
    Scope[] scopes;
    int[] scopeColCount;
    static EditDialog editDialog;
    static ImportDialog impDialog;
    Class[] dumpTypes;
    static String muString;
    static String ohmString;
    String clipboard;
    Rectangle circuitArea;
    int circuitBottom;
    Vector undoStack;
    Vector redoStack;
    CircuitCanvas cv;
    Circuit applet;
    String startCircuit;
    String startLabel;
    String startCircuitText;
    String baseURL;
    boolean shown;
    static final int resct = 6;
    long lastTime;
    long lastFrameTime;
    long lastIterTime;
    long secTime;
    int frames;
    int steps;
    int framerate;
    int steprate;
    Vector nodeList;
    CircuitElm[] voltageSources;
    boolean converged;
    int subIterations;
    
    public String getAppletInfo() {
        return "Circuit by Paul Falstad";
    }
    
    int getrand(final int n) {
        int nextInt = this.random.nextInt();
        if (nextInt < 0) {
            nextInt = -nextInt;
        }
        return nextInt % n;
    }
    
    CirSim(final Circuit applet) {
        super("Circuit Simulator v1.5n");
        this.engine = null;
        this.mouseMode = 6;
        this.tempMouseMode = 6;
        this.mouseModeStr = "Select";
        this.pause = 10;
        this.scopeSelected = -1;
        this.menuScope = -1;
        this.hintType = -1;
        this.mousePost = -1;
        this.startCircuit = null;
        this.startLabel = null;
        this.startCircuitText = null;
        this.baseURL = "http://www.falstad.com/circuit/";
        this.shown = false;
        this.lastTime = 0L;
        this.secTime = 0L;
        this.frames = 0;
        this.steps = 0;
        this.framerate = 0;
        this.steprate = 0;
        this.applet = applet;
        this.useFrame = false;
    }
    
    public void init() {
        String parameter = null;
        String parameter2 = null;
        boolean state = false;
        boolean state2 = true;
        CircuitElm.initClass(this);
        try {
            this.baseURL = this.applet.getDocumentBase().getFile();
            final String string = this.applet.getDocumentBase().toString();
            final int index = string.indexOf(35);
            if (index > 0) {
                String startCircuitText = null;
                try {
                    startCircuitText = string.substring(index + 1);
                    startCircuitText = URLDecoder.decode(startCircuitText);
                    this.startCircuitText = startCircuitText;
                }
                catch (Exception ex) {
                    System.out.println("can't decode " + startCircuitText);
                    ex.printStackTrace();
                }
            }
            final int lastIndex = string.lastIndexOf(47);
            if (lastIndex > 0) {
                this.baseURL = string.substring(0, lastIndex + 1);
            }
            final String parameter3 = this.applet.getParameter("PAUSE");
            if (parameter3 != null) {
                this.pause = Integer.parseInt(parameter3);
            }
            this.startCircuit = this.applet.getParameter("startCircuit");
            this.startLabel = this.applet.getParameter("startLabel");
            parameter = this.applet.getParameter("euroResistors");
            parameter2 = this.applet.getParameter("useFrame");
            final String parameter4 = this.applet.getParameter("whiteBackground");
            if (parameter4 != null && parameter4.equalsIgnoreCase("true")) {
                state = true;
            }
            final String parameter5 = this.applet.getParameter("conventionalCurrent");
            if (parameter5 != null && parameter5.equalsIgnoreCase("true")) {
                state2 = false;
            }
        }
        catch (Exception ex2) {}
        final boolean state3 = parameter != null && parameter.equalsIgnoreCase("true");
        this.useFrame = (parameter2 == null || !parameter2.equalsIgnoreCase("false"));
        if (this.useFrame) {
            CirSim.main = this;
        }
        else {
            CirSim.main = this.applet;
        }
        this.isMac = (System.getProperty("os.name").indexOf("Mac ") == 0);
        this.ctrlMetaKey = (this.isMac ? "\u2318" : "Ctrl");
        if (new Double(System.getProperty("java.class.version")) >= 48.0) {
            CirSim.muString = "\u03bc";
            CirSim.ohmString = "\u03a9";
            this.useBufferedImage = true;
        }
        (this.dumpTypes = new Class[300])[111] = Scope.class;
        this.dumpTypes[104] = Scope.class;
        this.dumpTypes[36] = Scope.class;
        this.dumpTypes[37] = Scope.class;
        this.dumpTypes[63] = Scope.class;
        this.dumpTypes[66] = Scope.class;
        CirSim.main.setLayout(new CircuitLayout());
        (this.cv = new CircuitCanvas(this)).addComponentListener(this);
        this.cv.addMouseMotionListener(this);
        this.cv.addMouseListener(this);
        this.cv.addKeyListener(this);
        CirSim.main.add(this.cv);
        this.mainMenu = new PopupMenu();
        MenuBar menuBar = null;
        if (this.useFrame) {
            menuBar = new MenuBar();
        }
        final Menu menu = new Menu("File");
        if (this.useFrame) {
            menuBar.add(menu);
        }
        else {
            this.mainMenu.add(menu);
        }
        menu.add(this.importItem = this.getMenuItem("Import"));
        menu.add(this.exportItem = this.getMenuItem("Export"));
        menu.add(this.exportLinkItem = this.getMenuItem("Export Link"));
        menu.addSeparator();
        menu.add(this.exitItem = this.getMenuItem("Exit"));
        final Menu menu2 = new Menu("Edit");
        menu2.add(this.undoItem = this.getMenuItem("Undo"));
        this.undoItem.setShortcut(new MenuShortcut(90));
        menu2.add(this.redoItem = this.getMenuItem("Redo"));
        this.redoItem.setShortcut(new MenuShortcut(90, true));
        menu2.addSeparator();
        menu2.add(this.cutItem = this.getMenuItem("Cut"));
        this.cutItem.setShortcut(new MenuShortcut(88));
        menu2.add(this.copyItem = this.getMenuItem("Copy"));
        this.copyItem.setShortcut(new MenuShortcut(67));
        menu2.add(this.pasteItem = this.getMenuItem("Paste"));
        this.pasteItem.setShortcut(new MenuShortcut(86));
        this.pasteItem.setEnabled(false);
        menu2.add(this.selectAllItem = this.getMenuItem("Select All"));
        this.selectAllItem.setShortcut(new MenuShortcut(65));
        if (this.useFrame) {
            menuBar.add(menu2);
        }
        else {
            this.mainMenu.add(menu2);
        }
        final Menu menu3 = new Menu("Scope");
        if (this.useFrame) {
            menuBar.add(menu3);
        }
        else {
            this.mainMenu.add(menu3);
        }
        menu3.add(this.getMenuItem("Stack All", "stackAll"));
        menu3.add(this.getMenuItem("Unstack All", "unstackAll"));
        final Menu menu4 = this.optionsMenu = new Menu("Options");
        if (this.useFrame) {
            menuBar.add(menu4);
        }
        else {
            this.mainMenu.add(menu4);
        }
        menu4.add(this.dotsCheckItem = this.getCheckItem("Show Current"));
        this.dotsCheckItem.setState(true);
        menu4.add(this.voltsCheckItem = this.getCheckItem("Show Voltage"));
        this.voltsCheckItem.setState(true);
        menu4.add(this.powerCheckItem = this.getCheckItem("Show Power"));
        menu4.add(this.showValuesCheckItem = this.getCheckItem("Show Values"));
        this.showValuesCheckItem.setState(true);
        menu4.add(this.smallGridCheckItem = this.getCheckItem("Small Grid"));
        menu4.add(this.euroResistorCheckItem = this.getCheckItem("European Resistors"));
        this.euroResistorCheckItem.setState(state3);
        menu4.add(this.printableCheckItem = this.getCheckItem("White Background"));
        this.printableCheckItem.setState(state);
        menu4.add(this.conventionCheckItem = this.getCheckItem("Conventional Current Motion"));
        this.conventionCheckItem.setState(state2);
        menu4.add(this.optionsItem = this.getMenuItem("Other Options..."));
        final Menu menu5 = new Menu("Circuits");
        if (this.useFrame) {
            menuBar.add(menu5);
        }
        else {
            this.mainMenu.add(menu5);
        }
        this.mainMenu.add(this.getClassCheckItem("Add Wire", "WireElm"));
        this.mainMenu.add(this.getClassCheckItem("Add Resistor", "ResistorElm"));
        final Menu menu6 = new Menu("Passive Components");
        this.mainMenu.add(menu6);
        menu6.add(this.getClassCheckItem("Add Capacitor", "CapacitorElm"));
        menu6.add(this.getClassCheckItem("Add Inductor", "InductorElm"));
        menu6.add(this.getClassCheckItem("Add Switch", "SwitchElm"));
        menu6.add(this.getClassCheckItem("Add Push Switch", "PushSwitchElm"));
        menu6.add(this.getClassCheckItem("Add SPDT Switch", "Switch2Elm"));
        menu6.add(this.getClassCheckItem("Add Potentiometer", "PotElm"));
        menu6.add(this.getClassCheckItem("Add Transformer", "TransformerElm"));
        menu6.add(this.getClassCheckItem("Add Tapped Transformer", "TappedTransformerElm"));
        menu6.add(this.getClassCheckItem("Add Transmission Line", "TransLineElm"));
        menu6.add(this.getClassCheckItem("Add Relay", "RelayElm"));
        menu6.add(this.getClassCheckItem("Add Memristor", "MemristorElm"));
        menu6.add(this.getClassCheckItem("Add Spark Gap", "SparkGapElm"));
        final Menu menu7 = new Menu("Inputs/Outputs");
        this.mainMenu.add(menu7);
        menu7.add(this.getClassCheckItem("Add Ground", "GroundElm"));
        menu7.add(this.getClassCheckItem("Add Voltage Source (2-terminal)", "DCVoltageElm"));
        menu7.add(this.getClassCheckItem("Add A/C Source (2-terminal)", "ACVoltageElm"));
        menu7.add(this.getClassCheckItem("Add Voltage Source (1-terminal)", "RailElm"));
        menu7.add(this.getClassCheckItem("Add A/C Source (1-terminal)", "ACRailElm"));
        menu7.add(this.getClassCheckItem("Add Square Wave (1-terminal)", "SquareRailElm"));
        menu7.add(this.getClassCheckItem("Add Analog Output", "OutputElm"));
        menu7.add(this.getClassCheckItem("Add Logic Input", "LogicInputElm"));
        menu7.add(this.getClassCheckItem("Add Logic Output", "LogicOutputElm"));
        menu7.add(this.getClassCheckItem("Add Clock", "ClockElm"));
        menu7.add(this.getClassCheckItem("Add A/C Sweep", "SweepElm"));
        menu7.add(this.getClassCheckItem("Add Var. Voltage", "VarRailElm"));
        menu7.add(this.getClassCheckItem("Add Antenna", "AntennaElm"));
        menu7.add(this.getClassCheckItem("Add Current Source", "CurrentElm"));
        menu7.add(this.getClassCheckItem("Add LED", "LEDElm"));
        menu7.add(this.getClassCheckItem("Add Lamp (beta)", "LampElm"));
        final Menu menu8 = new Menu("Active Components");
        this.mainMenu.add(menu8);
        menu8.add(this.getClassCheckItem("Add Diode", "DiodeElm"));
        menu8.add(this.getClassCheckItem("Add Zener Diode", "ZenerElm"));
        menu8.add(this.getClassCheckItem("Add Transistor (bipolar, NPN)", "NTransistorElm"));
        menu8.add(this.getClassCheckItem("Add Transistor (bipolar, PNP)", "PTransistorElm"));
        menu8.add(this.getClassCheckItem("Add Op Amp (- on top)", "OpAmpElm"));
        menu8.add(this.getClassCheckItem("Add Op Amp (+ on top)", "OpAmpSwapElm"));
        menu8.add(this.getClassCheckItem("Add MOSFET (n-channel)", "NMosfetElm"));
        menu8.add(this.getClassCheckItem("Add MOSFET (p-channel)", "PMosfetElm"));
        menu8.add(this.getClassCheckItem("Add JFET (n-channel)", "NJfetElm"));
        menu8.add(this.getClassCheckItem("Add JFET (p-channel)", "PJfetElm"));
        menu8.add(this.getClassCheckItem("Add Analog Switch (SPST)", "AnalogSwitchElm"));
        menu8.add(this.getClassCheckItem("Add Analog Switch (SPDT)", "AnalogSwitch2Elm"));
        menu8.add(this.getClassCheckItem("Add SCR", "SCRElm"));
        menu8.add(this.getClassCheckItem("Add Tunnel Diode", "TunnelDiodeElm"));
        menu8.add(this.getClassCheckItem("Add Triode", "TriodeElm"));
        menu8.add(this.getClassCheckItem("Add CCII+", "CC2Elm"));
        menu8.add(this.getClassCheckItem("Add CCII-", "CC2NegElm"));
        final Menu menu9 = new Menu("Logic Gates");
        this.mainMenu.add(menu9);
        menu9.add(this.getClassCheckItem("Add Inverter", "InverterElm"));
        menu9.add(this.getClassCheckItem("Add NAND Gate", "NandGateElm"));
        menu9.add(this.getClassCheckItem("Add NOR Gate", "NorGateElm"));
        menu9.add(this.getClassCheckItem("Add AND Gate", "AndGateElm"));
        menu9.add(this.getClassCheckItem("Add OR Gate", "OrGateElm"));
        menu9.add(this.getClassCheckItem("Add XOR Gate", "XorGateElm"));
        final Menu menu10 = new Menu("Chips");
        this.mainMenu.add(menu10);
        menu10.add(this.getClassCheckItem("Add D Flip-Flop", "DFlipFlopElm"));
        menu10.add(this.getClassCheckItem("Add JK Flip-Flop", "JKFlipFlopElm"));
        menu10.add(this.getClassCheckItem("Add 7 Segment LED", "SevenSegElm"));
        menu10.add(this.getClassCheckItem("Add VCO", "VCOElm"));
        menu10.add(this.getClassCheckItem("Add Phase Comparator", "PhaseCompElm"));
        menu10.add(this.getClassCheckItem("Add Counter", "CounterElm"));
        menu10.add(this.getClassCheckItem("Add Decade Counter", "DecadeElm"));
        menu10.add(this.getClassCheckItem("Add 555 Timer", "TimerElm"));
        menu10.add(this.getClassCheckItem("Add DAC", "DACElm"));
        menu10.add(this.getClassCheckItem("Add ADC", "ADCElm"));
        menu10.add(this.getClassCheckItem("Add Latch", "LatchElm"));
        final Menu menu11 = new Menu("Other");
        this.mainMenu.add(menu11);
        menu11.add(this.getClassCheckItem("Add Text", "TextElm"));
        menu11.add(this.getClassCheckItem("Add Scope Probe", "ProbeElm"));
        menu11.add(this.getCheckItem("Drag All (Alt-drag)", "DragAll"));
        menu11.add(this.getCheckItem(this.isMac ? "Drag Row (Alt-S-drag, S-right)" : "Drag Row (S-right)", "DragRow"));
        menu11.add(this.getCheckItem(this.isMac ? "Drag Column (Alt-\u2318-drag, \u2318-right)" : "Drag Column (C-right)", "DragColumn"));
        menu11.add(this.getCheckItem("Drag Selected", "DragSelected"));
        menu11.add(this.getCheckItem("Drag Post (" + this.ctrlMetaKey + "-drag)", "DragPost"));
        this.mainMenu.add(this.getCheckItem("Select/Drag Selected (space or Shift-drag)", "Select"));
        CirSim.main.add(this.mainMenu);
        CirSim.main.add(this.resetButton = new Button("Reset"));
        this.resetButton.addActionListener(this);
        (this.dumpMatrixButton = new Button("Dump Matrix")).addActionListener(this);
        (this.stoppedCheck = new Checkbox("Stopped")).addItemListener(this);
        CirSim.main.add(this.stoppedCheck);
        CirSim.main.add(new Label("Simulation Speed", 1));
        CirSim.main.add(this.speedBar = new Scrollbar(0, 3, 1, 0, 260));
        this.speedBar.addAdjustmentListener(this);
        CirSim.main.add(new Label("Current Speed", 1));
        (this.currentBar = new Scrollbar(0, 50, 1, 1, 100)).addAdjustmentListener(this);
        CirSim.main.add(this.currentBar);
        CirSim.main.add(this.powerLabel = new Label("Power Brightness", 1));
        CirSim.main.add(this.powerBar = new Scrollbar(0, 50, 1, 1, 100));
        this.powerBar.addAdjustmentListener(this);
        this.powerBar.disable();
        this.powerLabel.disable();
        CirSim.main.add(new Label("www.falstad.com"));
        if (this.useFrame) {
            CirSim.main.add(new Label(""));
        }
        final Font font = new Font("SansSerif", 0, 10);
        final Label label = new Label("Current Circuit:");
        label.setFont(font);
        (this.titleLabel = new Label("Label")).setFont(font);
        if (this.useFrame) {
            CirSim.main.add(label);
            CirSim.main.add(this.titleLabel);
        }
        this.setGrid();
        this.elmList = new Vector();
        this.setupList = new Vector();
        this.undoStack = new Vector();
        this.redoStack = new Vector();
        this.scopes = new Scope[20];
        this.scopeColCount = new int[20];
        this.scopeCount = 0;
        this.random = new Random();
        this.cv.setBackground(Color.black);
        this.cv.setForeground(Color.lightGray);
        (this.elmMenu = new PopupMenu()).add(this.elmEditMenuItem = this.getMenuItem("Edit"));
        this.elmMenu.add(this.elmScopeMenuItem = this.getMenuItem("View in Scope"));
        this.elmMenu.add(this.elmCutMenuItem = this.getMenuItem("Cut"));
        this.elmMenu.add(this.elmCopyMenuItem = this.getMenuItem("Copy"));
        this.elmMenu.add(this.elmDeleteMenuItem = this.getMenuItem("Delete"));
        CirSim.main.add(this.elmMenu);
        this.scopeMenu = this.buildScopeMenu(false);
        this.transScopeMenu = this.buildScopeMenu(true);
        this.getSetupList(menu5, false);
        if (this.useFrame) {
            this.setMenuBar(menuBar);
        }
        if (this.startCircuitText != null) {
            this.readSetup(this.startCircuitText);
        }
        else if (this.stopMessage == null && this.startCircuit != null) {
            this.readSetupFile(this.startCircuit, this.startLabel);
        }
        if (this.useFrame) {
            final Dimension screenSize = this.getToolkit().getScreenSize();
            this.resize(860, 640);
            this.handleResize();
            final Dimension size = this.getSize();
            this.setLocation((screenSize.width - size.width) / 2, (screenSize.height - size.height) / 2);
            this.show();
        }
        else {
            if (!this.powerCheckItem.getState()) {
                CirSim.main.remove(this.powerBar);
                CirSim.main.remove(this.powerLabel);
                CirSim.main.validate();
            }
            this.hide();
            this.handleResize();
            this.applet.validate();
        }
        CirSim.main.requestFocus();
    }
    
    public void triggerShow() {
        if (!this.shown) {
            this.show();
        }
        this.shown = true;
    }
    
    PopupMenu buildScopeMenu(final boolean b) {
        final PopupMenu popupMenu = new PopupMenu();
        popupMenu.add(this.getMenuItem("Remove", "remove"));
        popupMenu.add(this.getMenuItem("Speed 2x", "speed2"));
        popupMenu.add(this.getMenuItem("Speed 1/2x", "speed1/2"));
        popupMenu.add(this.getMenuItem("Scale 2x", "scale"));
        popupMenu.add(this.getMenuItem("Max Scale", "maxscale"));
        popupMenu.add(this.getMenuItem("Stack", "stack"));
        popupMenu.add(this.getMenuItem("Unstack", "unstack"));
        popupMenu.add(this.getMenuItem("Reset", "reset"));
        if (b) {
            popupMenu.add(this.scopeIbMenuItem = this.getCheckItem("Show Ib"));
            popupMenu.add(this.scopeIcMenuItem = this.getCheckItem("Show Ic"));
            popupMenu.add(this.scopeIeMenuItem = this.getCheckItem("Show Ie"));
            popupMenu.add(this.scopeVbeMenuItem = this.getCheckItem("Show Vbe"));
            popupMenu.add(this.scopeVbcMenuItem = this.getCheckItem("Show Vbc"));
            popupMenu.add(this.scopeVceMenuItem = this.getCheckItem("Show Vce"));
            popupMenu.add(this.scopeVceIcMenuItem = this.getCheckItem("Show Vce vs Ic"));
        }
        else {
            popupMenu.add(this.scopeVMenuItem = this.getCheckItem("Show Voltage"));
            popupMenu.add(this.scopeIMenuItem = this.getCheckItem("Show Current"));
            popupMenu.add(this.scopePowerMenuItem = this.getCheckItem("Show Power Consumed"));
            popupMenu.add(this.scopeMaxMenuItem = this.getCheckItem("Show Peak Value"));
            popupMenu.add(this.scopeMinMenuItem = this.getCheckItem("Show Negative Peak Value"));
            popupMenu.add(this.scopeFreqMenuItem = this.getCheckItem("Show Frequency"));
            popupMenu.add(this.scopeVIMenuItem = this.getCheckItem("Show V vs I"));
            popupMenu.add(this.scopeXYMenuItem = this.getCheckItem("Plot X/Y"));
            popupMenu.add(this.scopeSelectYMenuItem = this.getMenuItem("Select Y", "selecty"));
            popupMenu.add(this.scopeResistMenuItem = this.getCheckItem("Show Resistance"));
        }
        CirSim.main.add(popupMenu);
        return popupMenu;
    }
    
    MenuItem getMenuItem(final String s) {
        final MenuItem menuItem = new MenuItem(s);
        menuItem.addActionListener(this);
        return menuItem;
    }
    
    MenuItem getMenuItem(final String s, final String actionCommand) {
        final MenuItem menuItem = new MenuItem(s);
        menuItem.setActionCommand(actionCommand);
        menuItem.addActionListener(this);
        return menuItem;
    }
    
    CheckboxMenuItem getCheckItem(final String s) {
        final CheckboxMenuItem checkboxMenuItem = new CheckboxMenuItem(s);
        checkboxMenuItem.addItemListener(this);
        checkboxMenuItem.setActionCommand("");
        return checkboxMenuItem;
    }
    
    CheckboxMenuItem getClassCheckItem(String string, final String s) {
        try {
            final Class<?> forName = Class.forName(s);
            final CircuitElm constructElement = this.constructElement(forName, 0, 0);
            this.register(forName, constructElement);
            if (constructElement.needsShortcut() && constructElement.getDumpClass() == forName) {
                string = string + " (" + (char)constructElement.getDumpType() + ")";
            }
            constructElement.delete();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return this.getCheckItem(string, s);
    }
    
    CheckboxMenuItem getCheckItem(final String s, final String actionCommand) {
        final CheckboxMenuItem checkboxMenuItem = new CheckboxMenuItem(s);
        checkboxMenuItem.addItemListener(this);
        checkboxMenuItem.setActionCommand(actionCommand);
        return checkboxMenuItem;
    }
    
    void register(final Class clazz, final CircuitElm circuitElm) {
        final int dumpType = circuitElm.getDumpType();
        if (dumpType == 0) {
            System.out.println("no dump type: " + clazz);
            return;
        }
        final Class dumpClass = circuitElm.getDumpClass();
        if (this.dumpTypes[dumpType] == dumpClass) {
            return;
        }
        if (this.dumpTypes[dumpType] != null) {
            System.out.println("dump type conflict: " + clazz + " " + this.dumpTypes[dumpType]);
            return;
        }
        this.dumpTypes[dumpType] = dumpClass;
    }
    
    void handleResize() {
        this.winSize = this.cv.getSize();
        if (this.winSize.width == 0) {
            return;
        }
        this.dbimage = CirSim.main.createImage(this.winSize.width, this.winSize.height);
        this.circuitArea = new Rectangle(0, 0, this.winSize.width, this.winSize.height - this.winSize.height / 5);
        int min = 1000;
        int max = 0;
        int min2 = 1000;
        int max2 = 0;
        for (int i = 0; i != this.elmList.size(); ++i) {
            final CircuitElm elm = this.getElm(i);
            if (!elm.isCenteredText()) {
                min = this.min(elm.x, this.min(elm.x2, min));
                max = this.max(elm.x, this.max(elm.x2, max));
            }
            min2 = this.min(elm.y, this.min(elm.y2, min2));
            max2 = this.max(elm.y, this.max(elm.y2, max2));
        }
        int n = this.gridMask & (this.circuitArea.width - (max - min)) / 2 - min;
        int n2 = this.gridMask & (this.circuitArea.height - (max2 - min2)) / 2 - min2;
        if (n + min < 0) {
            n = (this.gridMask & -min);
        }
        if (n2 + min2 < 0) {
            n2 = (this.gridMask & -min2);
        }
        for (int j = 0; j != this.elmList.size(); ++j) {
            this.getElm(j).move(n, n2);
        }
        this.needAnalyze();
        this.circuitBottom = 0;
    }
    
    void destroyFrame() {
        if (this.applet == null) {
            this.dispose();
        }
        else {
            this.applet.destroyFrame();
        }
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.destroyFrame();
            return true;
        }
        return super.handleEvent(event);
    }
    
    public void paint(final Graphics graphics) {
        this.cv.repaint();
    }
    
    public void updateCircuit(final Graphics graphics) {
        if (this.winSize == null || this.winSize.width == 0) {
            return;
        }
        if (this.analyzeFlag) {
            this.analyzeCircuit();
            this.analyzeFlag = false;
        }
        if (CirSim.editDialog != null && CirSim.editDialog.elm instanceof CircuitElm) {
            this.mouseElm = (CircuitElm)CirSim.editDialog.elm;
        }
        final CircuitElm mouseElm = this.mouseElm;
        if (this.mouseElm == null) {
            this.mouseElm = this.stopElm;
        }
        this.setupScopes();
        final Graphics graphics2 = this.dbimage.getGraphics();
        CircuitElm.selectColor = Color.cyan;
        if (this.printableCheckItem.getState()) {
            CircuitElm.whiteColor = Color.black;
            CircuitElm.lightGrayColor = Color.black;
            graphics2.setColor(Color.white);
        }
        else {
            CircuitElm.whiteColor = Color.white;
            CircuitElm.lightGrayColor = Color.lightGray;
            graphics2.setColor(Color.black);
        }
        graphics2.fillRect(0, 0, this.winSize.width, this.winSize.height);
        if (!this.stoppedCheck.getState()) {
            try {
                this.runCircuit();
            }
            catch (Exception ex) {
                ex.printStackTrace();
                this.analyzeFlag = true;
                this.cv.repaint();
                return;
            }
        }
        if (!this.stoppedCheck.getState()) {
            final long currentTimeMillis = System.currentTimeMillis();
            if (this.lastTime != 0L) {
                CircuitElm.currentMult = 1.7 * (int)(currentTimeMillis - this.lastTime) * Math.exp(this.currentBar.getValue() / 3.5 - 14.2);
                if (!this.conventionCheckItem.getState()) {
                    CircuitElm.currentMult = -CircuitElm.currentMult;
                }
            }
            if (currentTimeMillis - this.secTime >= 1000L) {
                this.framerate = this.frames;
                this.steprate = this.steps;
                this.frames = 0;
                this.steps = 0;
                this.secTime = currentTimeMillis;
            }
            this.lastTime = currentTimeMillis;
        }
        else {
            this.lastTime = 0L;
        }
        CircuitElm.powerMult = Math.exp(this.powerBar.getValue() / 4.762 - 7.0);
        final Font font = graphics2.getFont();
        for (int i = 0; i != this.elmList.size(); ++i) {
            if (this.powerCheckItem.getState()) {
                graphics2.setColor(Color.gray);
            }
            this.getElm(i).draw(graphics2);
        }
        if (this.tempMouseMode == 2 || this.tempMouseMode == 3 || this.tempMouseMode == 5 || this.tempMouseMode == 4) {
            for (int j = 0; j != this.elmList.size(); ++j) {
                final CircuitElm elm = this.getElm(j);
                elm.drawPost(graphics2, elm.x, elm.y);
                elm.drawPost(graphics2, elm.x2, elm.y2);
            }
        }
        int n = 0;
        for (int k = 0; k != this.nodeList.size(); ++k) {
            final CircuitNode circuitNode = this.getCircuitNode(k);
            if (!circuitNode.internal && circuitNode.links.size() == 1) {
                int n2 = 0;
                final CircuitNodeLink circuitNodeLink = circuitNode.links.elementAt(0);
                for (int l = 0; l != this.elmList.size(); ++l) {
                    if (circuitNodeLink.elm != this.getElm(l) && this.getElm(l).boundingBox.contains(circuitNode.x, circuitNode.y)) {
                        ++n2;
                    }
                }
                if (n2 > 0) {
                    graphics2.setColor(Color.red);
                    graphics2.fillOval(circuitNode.x - 3, circuitNode.y - 3, 7, 7);
                    ++n;
                }
            }
        }
        if (this.dragElm != null && (this.dragElm.x != this.dragElm.x2 || this.dragElm.y != this.dragElm.y2)) {
            this.dragElm.draw(graphics2);
        }
        graphics2.setFont(font);
        int scopeCount = this.scopeCount;
        if (this.stopMessage != null) {
            scopeCount = 0;
        }
        for (int n3 = 0; n3 != scopeCount; ++n3) {
            this.scopes[n3].draw(graphics2);
        }
        graphics2.setColor(CircuitElm.whiteColor);
        if (this.stopMessage != null) {
            graphics2.drawString(this.stopMessage, 10, this.circuitArea.height);
        }
        else {
            if (this.circuitBottom == 0) {
                this.calcCircuitBottom();
            }
            final String[] array = new String[10];
            if (this.mouseElm != null) {
                if (this.mousePost == -1) {
                    this.mouseElm.getInfo(array);
                }
                else {
                    array[0] = "V = " + CircuitElm.getUnitText(this.mouseElm.getPostVoltage(this.mousePost), "V");
                }
            }
            else {
                CircuitElm.showFormat.setMinimumFractionDigits(2);
                array[0] = "t = " + CircuitElm.getUnitText(this.t, "s");
                CircuitElm.showFormat.setMinimumFractionDigits(0);
            }
            if (this.hintType != -1) {
                int n4;
                for (n4 = 0; array[n4] != null; ++n4) {}
                final String hint = this.getHint();
                if (hint == null) {
                    this.hintType = -1;
                }
                else {
                    array[n4] = hint;
                }
            }
            int n5 = 0;
            if (scopeCount != 0) {
                n5 = this.scopes[scopeCount - 1].rightEdge() + 20;
            }
            final int max = this.max(n5, this.winSize.width * 2 / 3);
            int n6;
            for (n6 = 0; array[n6] != null; ++n6) {}
            if (n > 0) {
                array[n6++] = n + ((n == 1) ? " bad connection" : " bad connections");
            }
            final int max2 = this.max(this.min(this.winSize.height - 15 * n6 - 5, this.circuitArea.height), this.circuitBottom);
            for (int n7 = 0; array[n7] != null; ++n7) {
                graphics2.drawString(array[n7], max, max2 + 15 * (n7 + 1));
            }
        }
        if (this.selectedArea != null) {
            graphics2.setColor(CircuitElm.selectColor);
            graphics2.drawRect(this.selectedArea.x, this.selectedArea.y, this.selectedArea.width, this.selectedArea.height);
        }
        this.mouseElm = mouseElm;
        ++this.frames;
        graphics.drawImage(this.dbimage, 0, 0, this);
        if (!this.stoppedCheck.getState() && this.circuitMatrix != null) {
            final long n8 = 20L - (System.currentTimeMillis() - this.lastFrameTime);
            if (n8 > 0L) {
                try {
                    Thread.sleep(n8);
                }
                catch (InterruptedException ex2) {}
            }
            this.cv.repaint(0L);
        }
        this.lastFrameTime = this.lastTime;
    }
    
    void setupScopes() {
        int position = -1;
        for (int i = 0; i < this.scopeCount; ++i) {
            if (this.locateElm(this.scopes[i].elm) < 0) {
                this.scopes[i].setElm(null);
            }
            if (this.scopes[i].elm == null) {
                for (int j = i; j != this.scopeCount; ++j) {
                    this.scopes[j] = this.scopes[j + 1];
                }
                --this.scopeCount;
                --i;
            }
            else {
                if (this.scopes[i].position > position + 1) {
                    this.scopes[i].position = position + 1;
                }
                position = this.scopes[i].position;
            }
        }
        while (this.scopeCount > 0 && this.scopes[this.scopeCount - 1].elm == null) {
            --this.scopeCount;
        }
        final int n = this.winSize.height - this.circuitArea.height;
        int max = 0;
        for (int k = 0; k != this.scopeCount; ++k) {
            this.scopeColCount[k] = 0;
        }
        for (int l = 0; l != this.scopeCount; ++l) {
            max = this.max(this.scopes[l].position, max);
            final int[] scopeColCount = this.scopeColCount;
            final int position2 = this.scopes[l].position;
            ++scopeColCount[position2];
        }
        final int n2 = max + 1;
        int n3 = 120;
        if (n2 <= 2) {
            n3 = n3 * 3 / 2;
        }
        int n4 = (this.winSize.width - n3) / n2;
        final int n5 = 10;
        if (n4 < n5 * 2) {
            n4 = n5 * 2;
        }
        int position3 = -1;
        int n6 = 0;
        int n7 = 0;
        int speed = 0;
        for (int n8 = 0; n8 != this.scopeCount; ++n8) {
            final Scope scope = this.scopes[n8];
            if (scope.position > position3) {
                position3 = scope.position;
                n6 = n / this.scopeColCount[position3];
                n7 = 0;
                speed = scope.speed;
            }
            if (scope.speed != speed) {
                scope.speed = speed;
                scope.resetGraph();
            }
            final Rectangle rect = new Rectangle(position3 * n4, this.winSize.height - n + n6 * n7, n4 - n5, n6);
            ++n7;
            if (!rect.equals(scope.rect)) {
                scope.setRect(rect);
            }
        }
    }
    
    String getHint() {
        final CircuitElm elm = this.getElm(this.hintItem1);
        final CircuitElm elm2 = this.getElm(this.hintItem2);
        if (elm == null || elm2 == null) {
            return null;
        }
        if (this.hintType == 1) {
            if (!(elm instanceof InductorElm)) {
                return null;
            }
            if (!(elm2 instanceof CapacitorElm)) {
                return null;
            }
            return "res.f = " + CircuitElm.getUnitText(1.0 / (6.283185307179586 * Math.sqrt(((InductorElm)elm).inductance * ((CapacitorElm)elm2).capacitance)), "Hz");
        }
        else if (this.hintType == 2) {
            if (!(elm instanceof ResistorElm)) {
                return null;
            }
            if (!(elm2 instanceof CapacitorElm)) {
                return null;
            }
            return "RC = " + CircuitElm.getUnitText(((ResistorElm)elm).resistance * ((CapacitorElm)elm2).capacitance, "s");
        }
        else if (this.hintType == 3) {
            if (!(elm instanceof ResistorElm)) {
                return null;
            }
            if (!(elm2 instanceof CapacitorElm)) {
                return null;
            }
            return "f.3db = " + CircuitElm.getUnitText(1.0 / (6.283185307179586 * ((ResistorElm)elm).resistance * ((CapacitorElm)elm2).capacitance), "Hz");
        }
        else if (this.hintType == 5) {
            if (!(elm instanceof ResistorElm)) {
                return null;
            }
            if (!(elm2 instanceof InductorElm)) {
                return null;
            }
            return "f.3db = " + CircuitElm.getUnitText(((ResistorElm)elm).resistance / (6.283185307179586 * ((InductorElm)elm2).inductance), "Hz");
        }
        else {
            if (this.hintType != 4) {
                return null;
            }
            if (!(elm instanceof ResistorElm)) {
                return null;
            }
            if (!(elm2 instanceof CapacitorElm)) {
                return null;
            }
            return "fc = " + CircuitElm.getUnitText(1.0 / (6.283185307179586 * ((ResistorElm)elm).resistance * ((CapacitorElm)elm2).capacitance), "Hz");
        }
    }
    
    public void toggleSwitch(int n) {
        for (int i = 0; i != this.elmList.size(); ++i) {
            final CircuitElm elm = this.getElm(i);
            if (elm instanceof SwitchElm && --n == 0) {
                ((SwitchElm)elm).toggle();
                this.analyzeFlag = true;
                this.cv.repaint();
                return;
            }
        }
    }
    
    void needAnalyze() {
        this.analyzeFlag = true;
        this.cv.repaint();
    }
    
    public CircuitNode getCircuitNode(final int n) {
        if (n >= this.nodeList.size()) {
            return null;
        }
        return this.nodeList.elementAt(n);
    }
    
    public CircuitElm getElm(final int n) {
        if (n >= this.elmList.size()) {
            return null;
        }
        return this.elmList.elementAt(n);
    }
    
    void analyzeCircuit() {
        this.calcCircuitBottom();
        if (this.elmList.isEmpty()) {
            return;
        }
        this.stopMessage = null;
        this.stopElm = null;
        int n = 0;
        this.nodeList = new Vector();
        boolean b = false;
        boolean b2 = false;
        CircuitElm circuitElm = null;
        for (int i = 0; i != this.elmList.size(); ++i) {
            final CircuitElm elm = this.getElm(i);
            if (elm instanceof GroundElm) {
                b = true;
                break;
            }
            if (elm instanceof RailElm) {
                b2 = true;
            }
            if (circuitElm == null && elm instanceof VoltageElm) {
                circuitElm = elm;
            }
        }
        if (!b && circuitElm != null && !b2) {
            final CircuitNode circuitNode = new CircuitNode();
            final Point post = circuitElm.getPost(0);
            circuitNode.x = post.x;
            circuitNode.y = post.y;
            this.nodeList.addElement(circuitNode);
        }
        else {
            final CircuitNode circuitNode3;
            final CircuitNode circuitNode2 = circuitNode3 = new CircuitNode();
            final int n2 = -1;
            circuitNode2.y = n2;
            circuitNode3.x = n2;
            this.nodeList.addElement(circuitNode2);
        }
        for (int j = 0; j != this.elmList.size(); ++j) {
            final CircuitElm elm2 = this.getElm(j);
            final int internalNodeCount = elm2.getInternalNodeCount();
            final int voltageSourceCount = elm2.getVoltageSourceCount();
            final int postCount = elm2.getPostCount();
            for (int k = 0; k != postCount; ++k) {
                final Point post2 = elm2.getPost(k);
                int l;
                for (l = 0; l != this.nodeList.size(); ++l) {
                    final CircuitNode circuitNode4 = this.getCircuitNode(l);
                    if (post2.x == circuitNode4.x && post2.y == circuitNode4.y) {
                        break;
                    }
                }
                if (l == this.nodeList.size()) {
                    final CircuitNode circuitNode5 = new CircuitNode();
                    circuitNode5.x = post2.x;
                    circuitNode5.y = post2.y;
                    final CircuitNodeLink circuitNodeLink = new CircuitNodeLink();
                    circuitNodeLink.num = k;
                    circuitNodeLink.elm = elm2;
                    circuitNode5.links.addElement(circuitNodeLink);
                    elm2.setNode(k, this.nodeList.size());
                    this.nodeList.addElement(circuitNode5);
                }
                else {
                    final CircuitNodeLink circuitNodeLink2 = new CircuitNodeLink();
                    circuitNodeLink2.num = k;
                    circuitNodeLink2.elm = elm2;
                    this.getCircuitNode(l).links.addElement(circuitNodeLink2);
                    elm2.setNode(k, l);
                    if (l == 0) {
                        elm2.setNodeVoltage(k, 0.0);
                    }
                }
            }
            for (int n3 = 0; n3 != internalNodeCount; ++n3) {
                final CircuitNode circuitNode8;
                final CircuitNode circuitNode7;
                final CircuitNode circuitNode6 = circuitNode7 = (circuitNode8 = new CircuitNode());
                final int n4 = -1;
                circuitNode7.y = n4;
                circuitNode8.x = n4;
                circuitNode6.internal = true;
                final CircuitNodeLink circuitNodeLink3 = new CircuitNodeLink();
                circuitNodeLink3.num = n3 + postCount;
                circuitNodeLink3.elm = elm2;
                circuitNode6.links.addElement(circuitNodeLink3);
                elm2.setNode(circuitNodeLink3.num, this.nodeList.size());
                this.nodeList.addElement(circuitNode6);
            }
            n += voltageSourceCount;
        }
        this.voltageSources = new CircuitElm[n];
        int voltageSourceCount2 = 0;
        this.circuitNonLinear = false;
        for (int n5 = 0; n5 != this.elmList.size(); ++n5) {
            final CircuitElm elm3 = this.getElm(n5);
            if (elm3.nonLinear()) {
                this.circuitNonLinear = true;
            }
            for (int voltageSourceCount3 = elm3.getVoltageSourceCount(), n6 = 0; n6 != voltageSourceCount3; ++n6) {
                (this.voltageSources[voltageSourceCount2] = elm3).setVoltageSource(n6, voltageSourceCount2++);
            }
        }
        this.voltageSourceCount = voltageSourceCount2;
        final int n7 = this.nodeList.size() - 1 + voltageSourceCount2;
        this.circuitMatrix = new double[n7][n7];
        this.circuitRightSide = new double[n7];
        this.origMatrix = new double[n7][n7];
        this.origRightSide = new double[n7];
        final int n8 = n7;
        this.circuitMatrixFullSize = n8;
        this.circuitMatrixSize = n8;
        this.circuitRowInfo = new RowInfo[n7];
        this.circuitPermute = new int[n7];
        for (int n9 = 0; n9 != n7; ++n9) {
            this.circuitRowInfo[n9] = new RowInfo();
        }
        this.circuitNeedsMap = false;
        for (int n10 = 0; n10 != this.elmList.size(); ++n10) {
            this.getElm(n10).stamp();
        }
        final boolean[] array = new boolean[this.nodeList.size()];
        final boolean[] array2 = new boolean[this.nodeList.size()];
        int n11 = 1;
        array[0] = true;
        while (n11 != 0) {
            n11 = 0;
            for (int n12 = 0; n12 != this.elmList.size(); ++n12) {
                final CircuitElm elm4 = this.getElm(n12);
                for (int n13 = 0; n13 < elm4.getPostCount(); ++n13) {
                    if (!array[elm4.getNode(n13)]) {
                        if (elm4.hasGroundConnection(n13)) {
                            n11 = ((array[elm4.getNode(n13)] = true) ? 1 : 0);
                        }
                    }
                    else {
                        for (int n14 = 0; n14 != elm4.getPostCount(); ++n14) {
                            if (n13 != n14) {
                                final int node = elm4.getNode(n14);
                                if (elm4.getConnection(n13, n14) && !array[node]) {
                                    array[node] = true;
                                    n11 = 1;
                                }
                            }
                        }
                    }
                }
            }
            if (n11 != 0) {
                continue;
            }
            for (int n15 = 0; n15 != this.nodeList.size(); ++n15) {
                if (!array[n15] && !this.getCircuitNode(n15).internal) {
                    System.out.println("node " + n15 + " unconnected");
                    this.stampResistor(0, n15, 1.0E8);
                    array[n15] = true;
                    n11 = 1;
                    break;
                }
            }
        }
        for (int n16 = 0; n16 != this.elmList.size(); ++n16) {
            final CircuitElm elm5 = this.getElm(n16);
            if (elm5 instanceof InductorElm) {
                final FindPathInfo findPathInfo = new FindPathInfo(1, elm5, elm5.getNode(1));
                if (!findPathInfo.findPath(elm5.getNode(0), 5) && !findPathInfo.findPath(elm5.getNode(0))) {
                    System.out.println(elm5 + " no path");
                    elm5.reset();
                }
            }
            if (elm5 instanceof CurrentElm && !new FindPathInfo(1, elm5, elm5.getNode(1)).findPath(elm5.getNode(0))) {
                this.stop("No path for current source!", elm5);
                return;
            }
            if (((elm5 instanceof VoltageElm && elm5.getPostCount() == 2) || elm5 instanceof WireElm) && new FindPathInfo(2, elm5, elm5.getNode(1)).findPath(elm5.getNode(0))) {
                this.stop("Voltage source/wire loop with no resistance!", elm5);
                return;
            }
            if (elm5 instanceof CapacitorElm) {
                if (new FindPathInfo(3, elm5, elm5.getNode(1)).findPath(elm5.getNode(0))) {
                    System.out.println(elm5 + " shorted");
                    elm5.reset();
                }
                else if (new FindPathInfo(4, elm5, elm5.getNode(1)).findPath(elm5.getNode(0))) {
                    this.stop("Capacitor loop with no resistance!", elm5);
                    return;
                }
            }
        }
        for (int n17 = 0; n17 != n7; ++n17) {
            int nodeEq = -1;
            int nodeEq2 = -1;
            double n18 = 0.0;
            final RowInfo rowInfo = this.circuitRowInfo[n17];
            if (!rowInfo.lsChanges && !rowInfo.dropRow) {
                if (!rowInfo.rsChanges) {
                    double n19 = 0.0;
                    int n20;
                    for (n20 = 0; n20 != n7; ++n20) {
                        final double n21 = this.circuitMatrix[n17][n20];
                        if (this.circuitRowInfo[n20].type == 1) {
                            n19 -= this.circuitRowInfo[n20].value * n21;
                        }
                        else if (n21 != 0.0) {
                            if (nodeEq2 == -1) {
                                nodeEq2 = n20;
                                n18 = n21;
                            }
                            else {
                                if (nodeEq != -1 || n21 != -n18) {
                                    break;
                                }
                                nodeEq = n20;
                            }
                        }
                    }
                    if (n20 == n7) {
                        if (nodeEq2 == -1) {
                            this.stop("Matrix error", null);
                            return;
                        }
                        RowInfo rowInfo2 = this.circuitRowInfo[nodeEq2];
                        if (nodeEq == -1) {
                            for (int n22 = 0; rowInfo2.type == 2 && n22 < 100; rowInfo2 = this.circuitRowInfo[nodeEq2], ++n22) {
                                nodeEq2 = rowInfo2.nodeEq;
                            }
                            if (rowInfo2.type == 2) {
                                rowInfo2.type = 0;
                            }
                            else if (rowInfo2.type != 0) {
                                System.out.println("type already " + rowInfo2.type + " for " + nodeEq2 + "!");
                            }
                            else {
                                rowInfo2.type = 1;
                                rowInfo2.value = (this.circuitRightSide[n17] + n19) / n18;
                                this.circuitRowInfo[n17].dropRow = true;
                                n17 = -1;
                            }
                        }
                        else if (this.circuitRightSide[n17] + n19 == 0.0) {
                            if (rowInfo2.type != 0) {
                                final int n23 = nodeEq;
                                nodeEq = nodeEq2;
                                rowInfo2 = this.circuitRowInfo[n23];
                                if (rowInfo2.type != 0) {
                                    System.out.println("swap failed");
                                    continue;
                                }
                            }
                            rowInfo2.type = 2;
                            rowInfo2.nodeEq = nodeEq;
                            this.circuitRowInfo[n17].dropRow = true;
                        }
                    }
                }
            }
        }
        int n24 = 0;
        for (int n25 = 0; n25 != n7; ++n25) {
            final RowInfo rowInfo3 = this.circuitRowInfo[n25];
            if (rowInfo3.type == 0) {
                rowInfo3.mapCol = n24++;
            }
            else {
                if (rowInfo3.type == 2) {
                    for (int n26 = 0; n26 != 100; ++n26) {
                        final RowInfo rowInfo4 = this.circuitRowInfo[rowInfo3.nodeEq];
                        if (rowInfo4.type != 2) {
                            break;
                        }
                        if (n25 == rowInfo4.nodeEq) {
                            break;
                        }
                        rowInfo3.nodeEq = rowInfo4.nodeEq;
                    }
                }
                if (rowInfo3.type == 1) {
                    rowInfo3.mapCol = -1;
                }
            }
        }
        for (int n27 = 0; n27 != n7; ++n27) {
            final RowInfo rowInfo5 = this.circuitRowInfo[n27];
            if (rowInfo5.type == 2) {
                final RowInfo rowInfo6 = this.circuitRowInfo[rowInfo5.nodeEq];
                if (rowInfo6.type == 1) {
                    rowInfo5.type = rowInfo6.type;
                    rowInfo5.value = rowInfo6.value;
                    rowInfo5.mapCol = -1;
                }
                else {
                    rowInfo5.mapCol = rowInfo6.mapCol;
                }
            }
        }
        final int n28 = n24;
        final double[][] circuitMatrix = new double[n28][n28];
        final double[] circuitRightSide = new double[n28];
        int mapRow = 0;
        for (int n29 = 0; n29 != n7; ++n29) {
            final RowInfo rowInfo7 = this.circuitRowInfo[n29];
            if (rowInfo7.dropRow) {
                rowInfo7.mapRow = -1;
            }
            else {
                circuitRightSide[mapRow] = this.circuitRightSide[n29];
                rowInfo7.mapRow = mapRow;
                for (int n30 = 0; n30 != n7; ++n30) {
                    final RowInfo rowInfo8 = this.circuitRowInfo[n30];
                    if (rowInfo8.type == 1) {
                        final double[] array3 = circuitRightSide;
                        final int n31 = mapRow;
                        array3[n31] -= rowInfo8.value * this.circuitMatrix[n29][n30];
                    }
                    else {
                        final double[] array4 = circuitMatrix[mapRow];
                        final int mapCol = rowInfo8.mapCol;
                        array4[mapCol] += this.circuitMatrix[n29][n30];
                    }
                }
                ++mapRow;
            }
        }
        this.circuitMatrix = circuitMatrix;
        this.circuitRightSide = circuitRightSide;
        final int circuitMatrixSize = n28;
        this.circuitMatrixSize = circuitMatrixSize;
        final int n32 = circuitMatrixSize;
        for (int n33 = 0; n33 != n32; ++n33) {
            this.origRightSide[n33] = this.circuitRightSide[n33];
        }
        for (int n34 = 0; n34 != n32; ++n34) {
            for (int n35 = 0; n35 != n32; ++n35) {
                this.origMatrix[n34][n35] = this.circuitMatrix[n34][n35];
            }
        }
        this.circuitNeedsMap = true;
        if (!this.circuitNonLinear && !this.lu_factor(this.circuitMatrix, this.circuitMatrixSize, this.circuitPermute)) {
            this.stop("Singular matrix!", null);
        }
    }
    
    void calcCircuitBottom() {
        this.circuitBottom = 0;
        for (int i = 0; i != this.elmList.size(); ++i) {
            final Rectangle boundingBox = this.getElm(i).boundingBox;
            final int circuitBottom = boundingBox.height + boundingBox.y;
            if (circuitBottom > this.circuitBottom) {
                this.circuitBottom = circuitBottom;
            }
        }
    }
    
    void stop(final String stopMessage, final CircuitElm stopElm) {
        this.stopMessage = stopMessage;
        this.circuitMatrix = null;
        this.stopElm = stopElm;
        this.stoppedCheck.setState(true);
        this.analyzeFlag = false;
        this.cv.repaint();
    }
    
    void stampVCVS(final int n, final int n2, final double n3, final int n4) {
        final int n5 = this.nodeList.size() + n4;
        this.stampMatrix(n5, n, n3);
        this.stampMatrix(n5, n2, -n3);
    }
    
    void stampVoltageSource(final int n, final int n2, final int n3, final double n4) {
        final int n5 = this.nodeList.size() + n3;
        this.stampMatrix(n5, n, -1.0);
        this.stampMatrix(n5, n2, 1.0);
        this.stampRightSide(n5, n4);
        this.stampMatrix(n, n5, 1.0);
        this.stampMatrix(n2, n5, -1.0);
    }
    
    void stampVoltageSource(final int n, final int n2, final int n3) {
        final int n4 = this.nodeList.size() + n3;
        this.stampMatrix(n4, n, -1.0);
        this.stampMatrix(n4, n2, 1.0);
        this.stampRightSide(n4);
        this.stampMatrix(n, n4, 1.0);
        this.stampMatrix(n2, n4, -1.0);
    }
    
    void updateVoltageSource(final int n, final int n2, final int n3, final double n4) {
        this.stampRightSide(this.nodeList.size() + n3, n4);
    }
    
    void stampResistor(final int n, final int n2, final double n3) {
        final double n4 = 1.0 / n3;
        if (Double.isNaN(n4) || Double.isInfinite(n4)) {
            System.out.print("bad resistance " + n3 + " " + n4 + "\n");
        }
        this.stampMatrix(n, n, n4);
        this.stampMatrix(n2, n2, n4);
        this.stampMatrix(n, n2, -n4);
        this.stampMatrix(n2, n, -n4);
    }
    
    void stampConductance(final int n, final int n2, final double n3) {
        this.stampMatrix(n, n, n3);
        this.stampMatrix(n2, n2, n3);
        this.stampMatrix(n, n2, -n3);
        this.stampMatrix(n2, n, -n3);
    }
    
    void stampVCCurrentSource(final int n, final int n2, final int n3, final int n4, final double n5) {
        this.stampMatrix(n, n3, n5);
        this.stampMatrix(n2, n4, n5);
        this.stampMatrix(n, n4, -n5);
        this.stampMatrix(n2, n3, -n5);
    }
    
    void stampCurrentSource(final int n, final int n2, final double n3) {
        this.stampRightSide(n, -n3);
        this.stampRightSide(n2, n3);
    }
    
    void stampCCCS(final int n, final int n2, final int n3, final double n4) {
        final int n5 = this.nodeList.size() + n3;
        this.stampMatrix(n, n5, n4);
        this.stampMatrix(n2, n5, -n4);
    }
    
    void stampMatrix(int mapRow, int mapCol, final double n) {
        if (mapRow > 0 && mapCol > 0) {
            if (this.circuitNeedsMap) {
                mapRow = this.circuitRowInfo[mapRow - 1].mapRow;
                final RowInfo rowInfo = this.circuitRowInfo[mapCol - 1];
                if (rowInfo.type == 1) {
                    final double[] circuitRightSide = this.circuitRightSide;
                    final int n2 = mapRow;
                    circuitRightSide[n2] -= n * rowInfo.value;
                    return;
                }
                mapCol = rowInfo.mapCol;
            }
            else {
                --mapRow;
                --mapCol;
            }
            final double[] array = this.circuitMatrix[mapRow];
            final int n3 = mapCol;
            array[n3] += n;
        }
    }
    
    void stampRightSide(int mapRow, final double n) {
        if (mapRow > 0) {
            if (this.circuitNeedsMap) {
                mapRow = this.circuitRowInfo[mapRow - 1].mapRow;
            }
            else {
                --mapRow;
            }
            final double[] circuitRightSide = this.circuitRightSide;
            final int n2 = mapRow;
            circuitRightSide[n2] += n;
        }
    }
    
    void stampRightSide(final int n) {
        if (n > 0) {
            this.circuitRowInfo[n - 1].rsChanges = true;
        }
    }
    
    void stampNonLinear(final int n) {
        if (n > 0) {
            this.circuitRowInfo[n - 1].lsChanges = true;
        }
    }
    
    double getIterCount() {
        if (this.speedBar.getValue() == 0) {
            return 0.0;
        }
        return 0.1 * Math.exp((this.speedBar.getValue() - 61) / 24.0);
    }
    
    void runCircuit() {
        if (this.circuitMatrix == null || this.elmList.size() == 0) {
            this.circuitMatrix = null;
            return;
        }
        int dumpMatrix = this.dumpMatrix ? 1 : 0;
        this.dumpMatrix = false;
        final long n = (long)(160.0 * this.getIterCount());
        final long currentTimeMillis = System.currentTimeMillis();
        long lastIterTime = this.lastIterTime;
        if (1000L >= n * (currentTimeMillis - this.lastIterTime)) {
            return;
        }
        int n2 = 1;
        while (true) {
            for (int i = 0; i != this.elmList.size(); ++i) {
                this.getElm(i).startIteration();
            }
            ++this.steps;
            int j;
            for (j = 0; j != 5000; ++j) {
                this.converged = true;
                this.subIterations = j;
                for (int k = 0; k != this.circuitMatrixSize; ++k) {
                    this.circuitRightSide[k] = this.origRightSide[k];
                }
                if (this.circuitNonLinear) {
                    for (int l = 0; l != this.circuitMatrixSize; ++l) {
                        for (int n3 = 0; n3 != this.circuitMatrixSize; ++n3) {
                            this.circuitMatrix[l][n3] = this.origMatrix[l][n3];
                        }
                    }
                }
                for (int n4 = 0; n4 != this.elmList.size(); ++n4) {
                    this.getElm(n4).doStep();
                }
                if (this.stopMessage != null) {
                    return;
                }
                final int n5 = dumpMatrix;
                dumpMatrix = 0;
                for (int n6 = 0; n6 != this.circuitMatrixSize; ++n6) {
                    for (int n7 = 0; n7 != this.circuitMatrixSize; ++n7) {
                        final double n8 = this.circuitMatrix[n7][n6];
                        if (Double.isNaN(n8) || Double.isInfinite(n8)) {
                            this.stop("nan/infinite matrix!", null);
                            return;
                        }
                    }
                }
                if (n5 != 0) {
                    for (int n9 = 0; n9 != this.circuitMatrixSize; ++n9) {
                        for (int n10 = 0; n10 != this.circuitMatrixSize; ++n10) {
                            System.out.print(this.circuitMatrix[n9][n10] + ",");
                        }
                        System.out.print("  " + this.circuitRightSide[n9] + "\n");
                    }
                    System.out.print("\n");
                }
                if (this.circuitNonLinear) {
                    if (this.converged && j > 0) {
                        break;
                    }
                    if (!this.lu_factor(this.circuitMatrix, this.circuitMatrixSize, this.circuitPermute)) {
                        this.stop("Singular matrix!", null);
                        return;
                    }
                }
                this.lu_solve(this.circuitMatrix, this.circuitMatrixSize, this.circuitPermute, this.circuitRightSide);
                for (int n11 = 0; n11 != this.circuitMatrixFullSize; ++n11) {
                    final RowInfo rowInfo = this.circuitRowInfo[n11];
                    double value;
                    if (rowInfo.type == 1) {
                        value = rowInfo.value;
                    }
                    else {
                        value = this.circuitRightSide[rowInfo.mapCol];
                    }
                    if (Double.isNaN(value)) {
                        this.converged = false;
                        break;
                    }
                    if (n11 < this.nodeList.size() - 1) {
                        final CircuitNode circuitNode = this.getCircuitNode(n11 + 1);
                        for (int n12 = 0; n12 != circuitNode.links.size(); ++n12) {
                            final CircuitNodeLink circuitNodeLink = circuitNode.links.elementAt(n12);
                            circuitNodeLink.elm.setNodeVoltage(circuitNodeLink.num, value);
                        }
                    }
                    else {
                        final int n13 = n11 - (this.nodeList.size() - 1);
                        this.voltageSources[n13].setCurrent(n13, value);
                    }
                }
                if (!this.circuitNonLinear) {
                    break;
                }
            }
            if (j > 5) {
                System.out.print("converged after " + j + " iterations\n");
            }
            if (j == 5000) {
                this.stop("Convergence failed!", null);
                break;
            }
            this.t += this.timeStep;
            for (int n14 = 0; n14 != this.scopeCount; ++n14) {
                this.scopes[n14].timeStep();
            }
            final long n15 = lastIterTime = System.currentTimeMillis();
            if (n2 * 1000 >= n * (n15 - this.lastIterTime)) {
                break;
            }
            if (n15 - this.lastFrameTime > 500L) {
                break;
            }
            ++n2;
        }
        this.lastIterTime = lastIterTime;
    }
    
    int min(final int n, final int n2) {
        return (n < n2) ? n : n2;
    }
    
    int max(final int n, final int n2) {
        return (n > n2) ? n : n2;
    }
    
    void editFuncPoint(final int n, final int n2) {
        this.cv.repaint(this.pause);
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
        this.cv.repaint();
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
        this.handleResize();
        this.cv.repaint(100L);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        if (actionEvent.getSource() == this.resetButton) {
            this.dbimage = CirSim.main.createImage(this.winSize.width, this.winSize.height);
            for (int i = 0; i != this.elmList.size(); ++i) {
                this.getElm(i).reset();
            }
            for (int j = 0; j != this.scopeCount; ++j) {
                this.scopes[j].resetGraph();
            }
            this.analyzeFlag = true;
            this.t = 0.0;
            this.stoppedCheck.setState(false);
            this.cv.repaint();
        }
        if (actionEvent.getSource() == this.dumpMatrixButton) {
            this.dumpMatrix = true;
        }
        if (actionEvent.getSource() == this.exportItem) {
            this.doImport(false, false);
        }
        if (actionEvent.getSource() == this.optionsItem) {
            this.doEdit(new EditOptions(this));
        }
        if (actionEvent.getSource() == this.importItem) {
            this.doImport(true, false);
        }
        if (actionEvent.getSource() == this.exportLinkItem) {
            this.doImport(false, true);
        }
        if (actionEvent.getSource() == this.undoItem) {
            this.doUndo();
        }
        if (actionEvent.getSource() == this.redoItem) {
            this.doRedo();
        }
        if (actionCommand.compareTo("Cut") == 0) {
            if (actionEvent.getSource() != this.elmCutMenuItem) {
                this.menuElm = null;
            }
            this.doCut();
        }
        if (actionCommand.compareTo("Copy") == 0) {
            if (actionEvent.getSource() != this.elmCopyMenuItem) {
                this.menuElm = null;
            }
            this.doCopy();
        }
        if (actionCommand.compareTo("Paste") == 0) {
            this.doPaste();
        }
        if (actionEvent.getSource() == this.selectAllItem) {
            this.doSelectAll();
        }
        if (actionEvent.getSource() == this.exitItem) {
            this.destroyFrame();
            return;
        }
        if (actionCommand.compareTo("stackAll") == 0) {
            this.stackAll();
        }
        if (actionCommand.compareTo("unstackAll") == 0) {
            this.unstackAll();
        }
        if (actionEvent.getSource() == this.elmEditMenuItem) {
            this.doEdit(this.menuElm);
        }
        if (actionCommand.compareTo("Delete") == 0) {
            if (actionEvent.getSource() != this.elmDeleteMenuItem) {
                this.menuElm = null;
            }
            this.doDelete();
        }
        if (actionEvent.getSource() == this.elmScopeMenuItem && this.menuElm != null) {
            int position;
            for (position = 0; position != this.scopeCount && this.scopes[position].elm != null; ++position) {}
            if (position == this.scopeCount) {
                if (this.scopeCount == this.scopes.length) {
                    return;
                }
                ++this.scopeCount;
                this.scopes[position] = new Scope(this);
                this.scopes[position].position = position;
                this.handleResize();
            }
            this.scopes[position].setElm(this.menuElm);
        }
        if (this.menuScope != -1) {
            if (actionCommand.compareTo("remove") == 0) {
                this.scopes[this.menuScope].setElm(null);
            }
            if (actionCommand.compareTo("speed2") == 0) {
                this.scopes[this.menuScope].speedUp();
            }
            if (actionCommand.compareTo("speed1/2") == 0) {
                this.scopes[this.menuScope].slowDown();
            }
            if (actionCommand.compareTo("scale") == 0) {
                this.scopes[this.menuScope].adjustScale(0.5);
            }
            if (actionCommand.compareTo("maxscale") == 0) {
                this.scopes[this.menuScope].adjustScale(1.0E-50);
            }
            if (actionCommand.compareTo("stack") == 0) {
                this.stackScope(this.menuScope);
            }
            if (actionCommand.compareTo("unstack") == 0) {
                this.unstackScope(this.menuScope);
            }
            if (actionCommand.compareTo("selecty") == 0) {
                this.scopes[this.menuScope].selectY();
            }
            if (actionCommand.compareTo("reset") == 0) {
                this.scopes[this.menuScope].resetGraph();
            }
            this.cv.repaint();
        }
        if (actionCommand.indexOf("setup ") == 0) {
            this.pushUndo();
            this.readSetupFile(actionCommand.substring(6), ((MenuItem)actionEvent.getSource()).getLabel());
        }
    }
    
    void stackScope(int i) {
        if (i == 0) {
            if (this.scopeCount < 2) {
                return;
            }
            i = 1;
        }
        if (this.scopes[i].position == this.scopes[i - 1].position) {
            return;
        }
        this.scopes[i].position = this.scopes[i - 1].position;
        ++i;
        while (i < this.scopeCount) {
            final Scope scope = this.scopes[i];
            --scope.position;
            ++i;
        }
    }
    
    void unstackScope(int i) {
        if (i == 0) {
            if (this.scopeCount < 2) {
                return;
            }
            i = 1;
        }
        if (this.scopes[i].position != this.scopes[i - 1].position) {
            return;
        }
        while (i < this.scopeCount) {
            final Scope scope = this.scopes[i];
            ++scope.position;
            ++i;
        }
    }
    
    void stackAll() {
        for (int i = 0; i != this.scopeCount; ++i) {
            this.scopes[i].position = 0;
            final Scope scope = this.scopes[i];
            final Scope scope2 = this.scopes[i];
            final boolean b = false;
            scope2.showMin = b;
            scope.showMax = b;
        }
    }
    
    void unstackAll() {
        for (int i = 0; i != this.scopeCount; ++i) {
            this.scopes[i].position = i;
            this.scopes[i].showMax = true;
        }
    }
    
    void doEdit(final Editable editable) {
        this.clearSelection();
        this.pushUndo();
        if (CirSim.editDialog != null) {
            this.requestFocus();
            CirSim.editDialog.setVisible(false);
            CirSim.editDialog = null;
        }
        (CirSim.editDialog = new EditDialog(editable, this)).show();
    }
    
    void doImport(final boolean b, final boolean b2) {
        if (CirSim.impDialog != null) {
            this.requestFocus();
            CirSim.impDialog.setVisible(false);
            CirSim.impDialog = null;
        }
        String string = b ? "" : this.dumpCircuit();
        if (b2) {
            string = this.baseURL + "#" + URLEncoder.encode(string);
        }
        (CirSim.impDialog = new ImportDialog(this, string, b2)).show();
        this.pushUndo();
    }
    
    String dumpCircuit() {
        String s = "$ " + ((this.dotsCheckItem.getState() ? 1 : 0) | (this.smallGridCheckItem.getState() ? 2 : 0) | (this.voltsCheckItem.getState() ? 0 : 4) | (this.powerCheckItem.getState() ? 8 : 0) | (this.showValuesCheckItem.getState() ? 0 : 16)) + " " + this.timeStep + " " + this.getIterCount() + " " + this.currentBar.getValue() + " " + CircuitElm.voltageRange + " " + this.powerBar.getValue() + "\n";
        for (int i = 0; i != this.elmList.size(); ++i) {
            s = s + this.getElm(i).dump() + "\n";
        }
        for (int j = 0; j != this.scopeCount; ++j) {
            final String dump = this.scopes[j].dump();
            if (dump != null) {
                s = s + dump + "\n";
            }
        }
        if (this.hintType != -1) {
            s = s + "h " + this.hintType + " " + this.hintItem1 + " " + this.hintItem2 + "\n";
        }
        return s;
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        System.out.print(((Scrollbar)adjustmentEvent.getSource()).getValue() + "\n");
    }
    
    ByteArrayOutputStream readUrlData(final URL url) throws IOException {
        final FilterInputStream filterInputStream = (FilterInputStream)url.getContent();
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(filterInputStream.available());
        final byte[] array = new byte[1024];
        while (true) {
            final int read = filterInputStream.read(array);
            if (read <= 0) {
                break;
            }
            byteArrayOutputStream.write(array, 0, read);
        }
        return byteArrayOutputStream;
    }
    
    URL getCodeBase() {
        try {
            if (this.applet != null) {
                return this.applet.getCodeBase();
            }
            return new URL("file:" + new File(".").getCanonicalPath() + "/");
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    void getSetupList(Menu menu, final boolean b) {
        final Menu[] array = new Menu[6];
        int n = 0;
        array[n++] = menu;
        try {
            final ByteArrayOutputStream urlData = this.readUrlData(new URL(this.getCodeBase() + "setuplist.txt"));
            final byte[] byteArray = urlData.toByteArray();
            final int size = urlData.size();
            if (size == 0 || byteArray[0] != 35) {
                this.getSetupList(menu, true);
                return;
            }
            int j;
            for (int i = 0; i < size; i += j) {
                for (j = 0; j != size - i; ++j) {
                    if (byteArray[j + i] == 10) {
                        ++j;
                        break;
                    }
                }
                final String s = new String(byteArray, i, j - 1);
                if (s.charAt(0) != '#') {
                    if (s.charAt(0) == '+') {
                        final Menu menu2 = new Menu(s.substring(1));
                        menu.add(menu2);
                        final Menu[] array2 = array;
                        final int n2 = n++;
                        final Menu menu3 = menu2;
                        array2[n2] = menu3;
                        menu = menu3;
                    }
                    else if (s.charAt(0) == '-') {
                        menu = array[--n - 1];
                    }
                    else {
                        final int index = s.indexOf(32);
                        if (index > 0) {
                            final String substring = s.substring(index + 1);
                            int n3 = 0;
                            if (s.charAt(0) == '>') {
                                n3 = 1;
                            }
                            final String substring2 = s.substring(n3, index);
                            menu.add(this.getMenuItem(substring, "setup " + substring2));
                            if (n3 && this.startCircuit == null) {
                                this.startCircuit = substring2;
                                this.startLabel = substring;
                            }
                        }
                    }
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            this.stop("Can't read setuplist.txt!", null);
        }
    }
    
    void readSetup(final String s) {
        this.readSetup(s, false);
    }
    
    void readSetup(final String s, final boolean b) {
        this.readSetup(s.getBytes(), s.length(), b);
        this.titleLabel.setText("untitled");
    }
    
    void readSetupFile(final String s, final String text) {
        this.t = 0.0;
        System.out.println(s);
        try {
            final ByteArrayOutputStream urlData = this.readUrlData(new URL(this.getCodeBase() + "circuits/" + s));
            this.readSetup(urlData.toByteArray(), urlData.size(), false);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            this.stop("Unable to read " + s + "!", null);
        }
        this.titleLabel.setText(text);
    }
    
    void readSetup(final byte[] array, final int n, final boolean b) {
        if (!b) {
            for (int i = 0; i != this.elmList.size(); ++i) {
                this.getElm(i).delete();
            }
            this.elmList.removeAllElements();
            this.hintType = -1;
            this.timeStep = 5.0E-6;
            this.dotsCheckItem.setState(true);
            this.smallGridCheckItem.setState(false);
            this.powerCheckItem.setState(false);
            this.voltsCheckItem.setState(true);
            this.showValuesCheckItem.setState(true);
            this.setGrid();
            this.speedBar.setValue(117);
            this.currentBar.setValue(50);
            this.powerBar.setValue(50);
            CircuitElm.voltageRange = 5.0;
            this.scopeCount = 0;
        }
        this.cv.repaint();
        int k;
        for (int j = 0; j < n; j += k) {
            int n2 = 0;
            k = 0;
            while (k != n - j) {
                if (array[k + j] == 10 || array[k + j] == 13) {
                    n2 = k++;
                    if (k + j < array.length && array[k + j] == 10) {
                        ++k;
                        break;
                    }
                    break;
                }
                else {
                    ++k;
                }
            }
            final StringTokenizer stringTokenizer = new StringTokenizer(new String(array, j, n2));
            if (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                int n3 = nextToken.charAt(0);
                try {
                    if (n3 == 111) {
                        final Scope scope = new Scope(this);
                        scope.position = this.scopeCount;
                        scope.undump(stringTokenizer);
                        this.scopes[this.scopeCount++] = scope;
                    }
                    else if (n3 == 104) {
                        this.readHint(stringTokenizer);
                    }
                    else if (n3 == 36) {
                        this.readOptions(stringTokenizer);
                    }
                    else if (n3 != 37 && n3 != 63 && n3 != 66) {
                        if (n3 >= 48 && n3 <= 57) {
                            n3 = new Integer(nextToken);
                        }
                        final int intValue = new Integer(stringTokenizer.nextToken());
                        final int intValue2 = new Integer(stringTokenizer.nextToken());
                        final int intValue3 = new Integer(stringTokenizer.nextToken());
                        final int intValue4 = new Integer(stringTokenizer.nextToken());
                        final int intValue5 = new Integer(stringTokenizer.nextToken());
                        final Class clazz = this.dumpTypes[n3];
                        if (clazz == null) {
                            System.out.println("unrecognized dump type: " + nextToken);
                        }
                        else {
                            final Class[] array3;
                            final Class[] array2 = array3 = new Class[6];
                            final int n4 = 0;
                            final Class[] array4 = array2;
                            final int n5 = 1;
                            final Class[] array5 = array2;
                            final int n6 = 2;
                            final Class[] array6 = array2;
                            final int n7 = 3;
                            final Class[] array7 = array2;
                            final int n8 = 4;
                            final Class<Integer> type = Integer.TYPE;
                            array7[n8] = type;
                            array5[n6] = (array6[n7] = type);
                            array3[n4] = (array4[n5] = type);
                            array2[5] = StringTokenizer.class;
                            final CircuitElm circuitElm = clazz.getConstructor((Class<?>[])array2).newInstance(new Integer(intValue), new Integer(intValue2), new Integer(intValue3), new Integer(intValue4), new Integer(intValue5), stringTokenizer);
                            circuitElm.setPoints();
                            this.elmList.addElement(circuitElm);
                        }
                    }
                }
                catch (InvocationTargetException ex) {
                    ex.getTargetException().printStackTrace();
                }
                catch (Exception ex2) {
                    ex2.printStackTrace();
                }
            }
        }
        this.enableItems();
        if (!b) {
            this.handleResize();
        }
        this.needAnalyze();
    }
    
    void readHint(final StringTokenizer stringTokenizer) {
        this.hintType = new Integer(stringTokenizer.nextToken());
        this.hintItem1 = new Integer(stringTokenizer.nextToken());
        this.hintItem2 = new Integer(stringTokenizer.nextToken());
    }
    
    void readOptions(final StringTokenizer stringTokenizer) {
        final int intValue = new Integer(stringTokenizer.nextToken());
        this.dotsCheckItem.setState((intValue & 0x1) != 0x0);
        this.smallGridCheckItem.setState((intValue & 0x2) != 0x0);
        this.voltsCheckItem.setState((intValue & 0x4) == 0x0);
        this.powerCheckItem.setState((intValue & 0x8) == 0x8);
        this.showValuesCheckItem.setState((intValue & 0x10) == 0x0);
        this.timeStep = new Double(stringTokenizer.nextToken());
        this.speedBar.setValue((int)(Math.log(10.0 * new Double(stringTokenizer.nextToken())) * 24.0 + 61.5));
        this.currentBar.setValue(new Integer(stringTokenizer.nextToken()));
        CircuitElm.voltageRange = new Double(stringTokenizer.nextToken());
        try {
            this.powerBar.setValue(new Integer(stringTokenizer.nextToken()));
        }
        catch (Exception ex) {}
        this.setGrid();
    }
    
    int snapGrid(final int n) {
        return n + this.gridRound & this.gridMask;
    }
    
    boolean doSwitch(final int n, final int n2) {
        if (this.mouseElm == null || !(this.mouseElm instanceof SwitchElm)) {
            return false;
        }
        final SwitchElm heldSwitchElm = (SwitchElm)this.mouseElm;
        heldSwitchElm.toggle();
        if (heldSwitchElm.momentary) {
            this.heldSwitchElm = heldSwitchElm;
        }
        this.needAnalyze();
        return true;
    }
    
    int locateElm(final CircuitElm circuitElm) {
        for (int i = 0; i != this.elmList.size(); ++i) {
            if (circuitElm == this.elmList.elementAt(i)) {
                return i;
            }
        }
        return -1;
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x4) != 0x0 && (mouseEvent.getModifiersEx() & 0x3C0) == 0x0) {
            return;
        }
        if (!this.circuitArea.contains(mouseEvent.getX(), mouseEvent.getY())) {
            return;
        }
        if (this.dragElm != null) {
            this.dragElm.drag(mouseEvent.getX(), mouseEvent.getY());
        }
        boolean b = true;
        switch (this.tempMouseMode) {
            case 1: {
                this.dragAll(this.snapGrid(mouseEvent.getX()), this.snapGrid(mouseEvent.getY()));
                break;
            }
            case 2: {
                this.dragRow(this.snapGrid(mouseEvent.getX()), this.snapGrid(mouseEvent.getY()));
                break;
            }
            case 3: {
                this.dragColumn(this.snapGrid(mouseEvent.getX()), this.snapGrid(mouseEvent.getY()));
                break;
            }
            case 5: {
                if (this.mouseElm != null) {
                    this.dragPost(this.snapGrid(mouseEvent.getX()), this.snapGrid(mouseEvent.getY()));
                    break;
                }
                break;
            }
            case 6: {
                if (this.mouseElm == null) {
                    this.selectArea(mouseEvent.getX(), mouseEvent.getY());
                    break;
                }
                this.tempMouseMode = 4;
                b = this.dragSelected(mouseEvent.getX(), mouseEvent.getY());
                break;
            }
            case 4: {
                b = this.dragSelected(mouseEvent.getX(), mouseEvent.getY());
                break;
            }
        }
        this.dragging = true;
        if (b) {
            if (this.tempMouseMode == 4 && this.mouseElm instanceof TextElm) {
                this.dragX = mouseEvent.getX();
                this.dragY = mouseEvent.getY();
            }
            else {
                this.dragX = this.snapGrid(mouseEvent.getX());
                this.dragY = this.snapGrid(mouseEvent.getY());
            }
        }
        this.cv.repaint(this.pause);
    }
    
    void dragAll(final int n, final int n2) {
        final int n3 = n - this.dragX;
        final int n4 = n2 - this.dragY;
        if (n3 == 0 && n4 == 0) {
            return;
        }
        for (int i = 0; i != this.elmList.size(); ++i) {
            this.getElm(i).move(n3, n4);
        }
        this.removeZeroLengthElements();
    }
    
    void dragRow(final int n, final int n2) {
        final int n3 = n2 - this.dragY;
        if (n3 == 0) {
            return;
        }
        for (int i = 0; i != this.elmList.size(); ++i) {
            final CircuitElm elm = this.getElm(i);
            if (elm.y == this.dragY) {
                elm.movePoint(0, 0, n3);
            }
            if (elm.y2 == this.dragY) {
                elm.movePoint(1, 0, n3);
            }
        }
        this.removeZeroLengthElements();
    }
    
    void dragColumn(final int n, final int n2) {
        final int n3 = n - this.dragX;
        if (n3 == 0) {
            return;
        }
        for (int i = 0; i != this.elmList.size(); ++i) {
            final CircuitElm elm = this.getElm(i);
            if (elm.x == this.dragX) {
                elm.movePoint(0, n3, 0);
            }
            if (elm.x2 == this.dragX) {
                elm.movePoint(1, n3, 0);
            }
        }
        this.removeZeroLengthElements();
    }
    
    boolean dragSelected(int snapGrid, int snapGrid2) {
        boolean b = false;
        if (this.mouseElm != null && !this.mouseElm.isSelected()) {
            this.mouseElm.setSelected(b = true);
        }
        int i;
        for (i = 0; i != this.elmList.size(); ++i) {
            final CircuitElm elm = this.getElm(i);
            if (elm.isSelected() && !(elm instanceof TextElm)) {
                break;
            }
        }
        if (i != this.elmList.size()) {
            snapGrid = this.snapGrid(snapGrid);
            snapGrid2 = this.snapGrid(snapGrid2);
        }
        final int n = snapGrid - this.dragX;
        final int n2 = snapGrid2 - this.dragY;
        if (n == 0 && n2 == 0) {
            if (b) {
                this.mouseElm.setSelected(false);
            }
            return false;
        }
        boolean b2 = true;
        for (int n3 = 0; b2 && n3 != this.elmList.size(); ++n3) {
            final CircuitElm elm2 = this.getElm(n3);
            if (elm2.isSelected() && !elm2.allowMove(n, n2)) {
                b2 = false;
            }
        }
        if (b2) {
            for (int j = 0; j != this.elmList.size(); ++j) {
                final CircuitElm elm3 = this.getElm(j);
                if (elm3.isSelected()) {
                    elm3.move(n, n2);
                }
            }
            this.needAnalyze();
        }
        if (b) {
            this.mouseElm.setSelected(false);
        }
        return b2;
    }
    
    void dragPost(final int n, final int n2) {
        if (this.draggingPost == -1) {
            this.draggingPost = ((this.distanceSq(this.mouseElm.x, this.mouseElm.y, n, n2) > this.distanceSq(this.mouseElm.x2, this.mouseElm.y2, n, n2)) ? 1 : 0);
        }
        final int n3 = n - this.dragX;
        final int n4 = n2 - this.dragY;
        if (n3 == 0 && n4 == 0) {
            return;
        }
        this.mouseElm.movePoint(this.draggingPost, n3, n4);
        this.needAnalyze();
    }
    
    void selectArea(final int n, final int n2) {
        final int min = this.min(n, this.initDragX);
        final int max = this.max(n, this.initDragX);
        final int min2 = this.min(n2, this.initDragY);
        this.selectedArea = new Rectangle(min, min2, max - min, this.max(n2, this.initDragY) - min2);
        for (int i = 0; i != this.elmList.size(); ++i) {
            this.getElm(i).selectRect(this.selectedArea);
        }
    }
    
    void setSelectedElm(final CircuitElm mouseElm) {
        for (int i = 0; i != this.elmList.size(); ++i) {
            final CircuitElm elm = this.getElm(i);
            elm.setSelected(elm == mouseElm);
        }
        this.mouseElm = mouseElm;
    }
    
    void removeZeroLengthElements() {
        for (int i = this.elmList.size() - 1; i >= 0; --i) {
            final CircuitElm elm = this.getElm(i);
            if (elm.x == elm.x2 && elm.y == elm.y2) {
                this.elmList.removeElementAt(i);
                elm.delete();
            }
        }
        this.needAnalyze();
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) != 0x0) {
            return;
        }
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        this.dragX = this.snapGrid(x);
        this.dragY = this.snapGrid(y);
        this.draggingPost = -1;
        final CircuitElm mouseElm = this.mouseElm;
        this.mouseElm = null;
        this.mousePost = -1;
        final CircuitElm circuitElm = null;
        this.plotYElm = circuitElm;
        this.plotXElm = circuitElm;
        int n = 100000;
        int n2 = 100000;
        for (int i = 0; i != this.elmList.size(); ++i) {
            final CircuitElm elm = this.getElm(i);
            if (elm.boundingBox.contains(x, y)) {
                final int n3 = elm.boundingBox.width * elm.boundingBox.height;
                int postCount = elm.getPostCount();
                if (postCount > 2) {
                    postCount = 2;
                }
                for (int j = 0; j != postCount; ++j) {
                    final Point post = elm.getPost(j);
                    final int distanceSq = this.distanceSq(x, y, post.x, post.y);
                    if (distanceSq <= n && n3 <= n2) {
                        n = distanceSq;
                        n2 = n3;
                        this.mouseElm = elm;
                    }
                }
                if (elm.getPostCount() == 0) {
                    this.mouseElm = elm;
                }
            }
        }
        this.scopeSelected = -1;
        if (this.mouseElm == null) {
            for (int k = 0; k != this.scopeCount; ++k) {
                final Scope scope = this.scopes[k];
                if (scope.rect.contains(x, y)) {
                    scope.select();
                    this.scopeSelected = k;
                }
            }
            for (int l = 0; l != this.elmList.size(); ++l) {
                final CircuitElm elm2 = this.getElm(l);
                for (int postCount2 = elm2.getPostCount(), mousePost = 0; mousePost != postCount2; ++mousePost) {
                    final Point post2 = elm2.getPost(mousePost);
                    this.distanceSq(x, y, post2.x, post2.y);
                    if (this.distanceSq(post2.x, post2.y, x, y) < 26) {
                        this.mouseElm = elm2;
                        this.mousePost = mousePost;
                        break;
                    }
                }
            }
        }
        else {
            this.mousePost = -1;
            for (int mousePost2 = 0; mousePost2 != this.mouseElm.getPostCount(); ++mousePost2) {
                final Point post3 = this.mouseElm.getPost(mousePost2);
                if (this.distanceSq(post3.x, post3.y, x, y) < 26) {
                    this.mousePost = mousePost2;
                }
            }
        }
        if (this.mouseElm != mouseElm) {
            this.cv.repaint();
        }
    }
    
    int distanceSq(final int n, final int n2, int n3, int n4) {
        n3 -= n;
        n4 -= n2;
        return n3 * n3 + n4 * n4;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) != 0x0 && (this.mouseMode == 6 || this.mouseMode == 4)) {
            this.clearSelection();
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.scopeSelected = -1;
        final CircuitElm mouseElm = null;
        this.plotYElm = mouseElm;
        this.plotXElm = mouseElm;
        this.mouseElm = mouseElm;
        this.cv.repaint();
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        System.out.println(mouseEvent.getModifiers());
        final int modifiersEx = mouseEvent.getModifiersEx();
        if ((modifiersEx & 0x140) == 0x0 && mouseEvent.isPopupTrigger()) {
            this.doPopupMenu(mouseEvent);
            return;
        }
        if ((mouseEvent.getModifiers() & 0x10) != 0x0) {
            this.tempMouseMode = this.mouseMode;
            if ((modifiersEx & 0x200) != 0x0 && (modifiersEx & 0x100) != 0x0) {
                this.tempMouseMode = 3;
            }
            else if ((modifiersEx & 0x200) != 0x0 && (modifiersEx & 0x40) != 0x0) {
                this.tempMouseMode = 2;
            }
            else if ((modifiersEx & 0x40) != 0x0) {
                this.tempMouseMode = 6;
            }
            else if ((modifiersEx & 0x200) != 0x0) {
                this.tempMouseMode = 1;
            }
            else if ((modifiersEx & 0x180) != 0x0) {
                this.tempMouseMode = 5;
            }
        }
        else if ((mouseEvent.getModifiers() & 0x4) != 0x0) {
            if ((modifiersEx & 0x40) != 0x0) {
                this.tempMouseMode = 2;
            }
            else {
                if ((modifiersEx & 0x180) == 0x0) {
                    return;
                }
                this.tempMouseMode = 3;
            }
        }
        if (this.tempMouseMode != 6 && this.tempMouseMode != 4) {
            this.clearSelection();
        }
        if (this.doSwitch(mouseEvent.getX(), mouseEvent.getY())) {
            return;
        }
        this.pushUndo();
        this.initDragX = mouseEvent.getX();
        this.initDragY = mouseEvent.getY();
        this.dragging = true;
        if (this.tempMouseMode != 0 || this.addingClass == null) {
            return;
        }
        final int snapGrid = this.snapGrid(mouseEvent.getX());
        final int snapGrid2 = this.snapGrid(mouseEvent.getY());
        if (!this.circuitArea.contains(snapGrid, snapGrid2)) {
            return;
        }
        this.dragElm = this.constructElement(this.addingClass, snapGrid, snapGrid2);
    }
    
    CircuitElm constructElement(final Class clazz, final int n, final int n2) {
        final Class[] array = new Class[2];
        array[0] = (array[1] = Integer.TYPE);
        Constructor<CircuitElm> constructor;
        try {
            constructor = clazz.getConstructor((Class<?>[])array);
        }
        catch (NoSuchMethodException ex3) {
            System.out.println("caught NoSuchMethodException " + clazz);
            return null;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        final Object[] array2 = { new Integer(n), new Integer(n2) };
        try {
            return constructor.newInstance(array2);
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
            return null;
        }
    }
    
    void doPopupMenu(final MouseEvent mouseEvent) {
        this.menuElm = this.mouseElm;
        this.menuScope = -1;
        if (this.scopeSelected != -1) {
            final PopupMenu menu = this.scopes[this.scopeSelected].getMenu();
            this.menuScope = this.scopeSelected;
            if (menu != null) {
                menu.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
            }
        }
        else if (this.mouseElm != null) {
            this.elmEditMenuItem.setEnabled(this.mouseElm.getEditInfo(0) != null);
            this.elmScopeMenuItem.setEnabled(this.mouseElm.canViewInScope());
            this.elmMenu.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
        }
        else {
            this.doMainMenuChecks(this.mainMenu);
            this.mainMenu.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
        }
    }
    
    void doMainMenuChecks(final Menu menu) {
        if (menu == this.optionsMenu) {
            return;
        }
        for (int i = 0; i != menu.getItemCount(); ++i) {
            final MenuItem item = menu.getItem(i);
            if (item instanceof Menu) {
                this.doMainMenuChecks((Menu)item);
            }
            if (item instanceof CheckboxMenuItem) {
                final CheckboxMenuItem checkboxMenuItem = (CheckboxMenuItem)item;
                checkboxMenuItem.setState(this.mouseModeStr.compareTo(checkboxMenuItem.getActionCommand()) == 0);
            }
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiersEx() & 0x1C0) == 0x0 && mouseEvent.isPopupTrigger()) {
            this.doPopupMenu(mouseEvent);
            return;
        }
        this.tempMouseMode = this.mouseMode;
        this.selectedArea = null;
        this.dragging = false;
        boolean b = false;
        if (this.heldSwitchElm != null) {
            this.heldSwitchElm.mouseUp();
            this.heldSwitchElm = null;
            b = true;
        }
        if (this.dragElm != null) {
            if (this.dragElm.x == this.dragElm.x2 && this.dragElm.y == this.dragElm.y2) {
                this.dragElm.delete();
            }
            else {
                this.elmList.addElement(this.dragElm);
                b = true;
            }
            this.dragElm = null;
        }
        if (b) {
            this.needAnalyze();
        }
        if (this.dragElm != null) {
            this.dragElm.delete();
        }
        this.dragElm = null;
        this.cv.repaint();
    }
    
    void enableItems() {
        if (this.powerCheckItem.getState()) {
            this.powerBar.enable();
            this.powerLabel.enable();
        }
        else {
            this.powerBar.disable();
            this.powerLabel.disable();
        }
        this.enableUndoRedo();
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        this.cv.repaint(this.pause);
        final ItemSelectable itemSelectable = itemEvent.getItemSelectable();
        if (itemSelectable == this.stoppedCheck) {
            return;
        }
        if (itemSelectable == this.smallGridCheckItem) {
            this.setGrid();
        }
        if (itemSelectable == this.powerCheckItem) {
            if (this.powerCheckItem.getState()) {
                this.voltsCheckItem.setState(false);
            }
            else {
                this.voltsCheckItem.setState(true);
            }
        }
        if (itemSelectable == this.voltsCheckItem && this.voltsCheckItem.getState()) {
            this.powerCheckItem.setState(false);
        }
        this.enableItems();
        if (this.menuScope != -1) {
            this.scopes[this.menuScope].handleMenu(itemEvent, itemSelectable);
        }
        if (itemSelectable instanceof CheckboxMenuItem) {
            final CheckboxMenuItem checkboxMenuItem = (CheckboxMenuItem)itemSelectable;
            this.mouseMode = 0;
            final String actionCommand = checkboxMenuItem.getActionCommand();
            if (actionCommand.length() > 0) {
                this.mouseModeStr = actionCommand;
            }
            if (actionCommand.compareTo("DragAll") == 0) {
                this.mouseMode = 1;
            }
            else if (actionCommand.compareTo("DragRow") == 0) {
                this.mouseMode = 2;
            }
            else if (actionCommand.compareTo("DragColumn") == 0) {
                this.mouseMode = 3;
            }
            else if (actionCommand.compareTo("DragSelected") == 0) {
                this.mouseMode = 4;
            }
            else if (actionCommand.compareTo("DragPost") == 0) {
                this.mouseMode = 5;
            }
            else if (actionCommand.compareTo("Select") == 0) {
                this.mouseMode = 6;
            }
            else if (actionCommand.length() > 0) {
                try {
                    this.addingClass = Class.forName(actionCommand);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            this.tempMouseMode = this.mouseMode;
        }
    }
    
    void setGrid() {
        this.gridSize = (this.smallGridCheckItem.getState() ? 8 : 16);
        this.gridMask = ~(this.gridSize - 1);
        this.gridRound = this.gridSize / 2 - 1;
    }
    
    void pushUndo() {
        this.redoStack.removeAllElements();
        final String dumpCircuit = this.dumpCircuit();
        if (this.undoStack.size() > 0 && dumpCircuit.compareTo((String)this.undoStack.lastElement()) == 0) {
            return;
        }
        this.undoStack.add(dumpCircuit);
        this.enableUndoRedo();
    }
    
    void doUndo() {
        if (this.undoStack.size() == 0) {
            return;
        }
        this.redoStack.add(this.dumpCircuit());
        this.readSetup(this.undoStack.remove(this.undoStack.size() - 1));
        this.enableUndoRedo();
    }
    
    void doRedo() {
        if (this.redoStack.size() == 0) {
            return;
        }
        this.undoStack.add(this.dumpCircuit());
        this.readSetup(this.redoStack.remove(this.redoStack.size() - 1));
        this.enableUndoRedo();
    }
    
    void enableUndoRedo() {
        this.redoItem.setEnabled(this.redoStack.size() > 0);
        this.undoItem.setEnabled(this.undoStack.size() > 0);
    }
    
    void setMenuSelection() {
        if (this.menuElm != null) {
            if (this.menuElm.selected) {
                return;
            }
            this.clearSelection();
            this.menuElm.setSelected(true);
        }
    }
    
    void doCut() {
        this.pushUndo();
        this.setMenuSelection();
        this.clipboard = "";
        for (int i = this.elmList.size() - 1; i >= 0; --i) {
            final CircuitElm elm = this.getElm(i);
            if (elm.isSelected()) {
                this.clipboard = this.clipboard + elm.dump() + "\n";
                elm.delete();
                this.elmList.removeElementAt(i);
            }
        }
        this.enablePaste();
        this.needAnalyze();
    }
    
    void doDelete() {
        this.pushUndo();
        this.setMenuSelection();
        for (int i = this.elmList.size() - 1; i >= 0; --i) {
            final CircuitElm elm = this.getElm(i);
            if (elm.isSelected()) {
                elm.delete();
                this.elmList.removeElementAt(i);
            }
        }
        this.needAnalyze();
    }
    
    void doCopy() {
        this.clipboard = "";
        this.setMenuSelection();
        for (int i = this.elmList.size() - 1; i >= 0; --i) {
            final CircuitElm elm = this.getElm(i);
            if (elm.isSelected()) {
                this.clipboard = this.clipboard + elm.dump() + "\n";
            }
        }
        this.enablePaste();
    }
    
    void enablePaste() {
        this.pasteItem.setEnabled(this.clipboard.length() > 0);
    }
    
    void doPaste() {
        this.pushUndo();
        this.clearSelection();
        Rectangle union = null;
        for (int i = 0; i != this.elmList.size(); ++i) {
            final Rectangle boundingBox = this.getElm(i).getBoundingBox();
            if (union != null) {
                union = union.union(boundingBox);
            }
            else {
                union = boundingBox;
            }
        }
        final int size = this.elmList.size();
        this.readSetup(this.clipboard, true);
        Rectangle union2 = null;
        for (int j = size; j != this.elmList.size(); ++j) {
            final CircuitElm elm = this.getElm(j);
            elm.setSelected(true);
            final Rectangle boundingBox2 = elm.getBoundingBox();
            if (union2 != null) {
                union2 = union2.union(boundingBox2);
            }
            else {
                union2 = boundingBox2;
            }
        }
        if (union != null && union2 != null && union.intersects(union2)) {
            int snapGrid = 0;
            int snapGrid2 = 0;
            if (this.circuitArea.width - union.width - union2.width > this.circuitArea.height - union.height - union2.height) {
                snapGrid = this.snapGrid(union.x + union.width - union2.x + this.gridSize);
            }
            else {
                snapGrid2 = this.snapGrid(union.y + union.height - union2.y + this.gridSize);
            }
            for (int k = size; k != this.elmList.size(); ++k) {
                this.getElm(k).move(snapGrid, snapGrid2);
            }
            this.handleResize();
        }
        this.needAnalyze();
    }
    
    void clearSelection() {
        for (int i = 0; i != this.elmList.size(); ++i) {
            this.getElm(i).setSelected(false);
        }
    }
    
    void doSelectAll() {
        for (int i = 0; i != this.elmList.size(); ++i) {
            this.getElm(i).setSelected(true);
        }
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
        if (keyEvent.getKeyChar() > ' ' && keyEvent.getKeyChar() < '\u007f') {
            final Class addingClass = this.dumpTypes[keyEvent.getKeyChar()];
            if (addingClass == null || addingClass == Scope.class) {
                return;
            }
            final CircuitElm constructElement = this.constructElement(addingClass, 0, 0);
            if (constructElement == null || !constructElement.needsShortcut() || constructElement.getDumpClass() != addingClass) {
                return;
            }
            this.mouseMode = 0;
            this.mouseModeStr = addingClass.getName();
            this.addingClass = addingClass;
        }
        if (keyEvent.getKeyChar() == ' ') {
            this.mouseMode = 6;
            this.mouseModeStr = "Select";
        }
        this.tempMouseMode = this.mouseMode;
    }
    
    boolean lu_factor(final double[][] array, final int n, final int[] array2) {
        final double[] array3 = new double[n];
        for (int i = 0; i != n; ++i) {
            double n2 = 0.0;
            for (int j = 0; j != n; ++j) {
                final double abs = Math.abs(array[i][j]);
                if (abs > n2) {
                    n2 = abs;
                }
            }
            if (n2 == 0.0) {
                return false;
            }
            array3[i] = 1.0 / n2;
        }
        for (int k = 0; k != n; ++k) {
            for (int l = 0; l != k; ++l) {
                double n3 = array[l][k];
                for (int n4 = 0; n4 != l; ++n4) {
                    n3 -= array[l][n4] * array[n4][k];
                }
                array[l][k] = n3;
            }
            double n5 = 0.0;
            int n6 = -1;
            for (int n7 = k; n7 != n; ++n7) {
                double n8 = array[n7][k];
                for (int n9 = 0; n9 != k; ++n9) {
                    n8 -= array[n7][n9] * array[n9][k];
                }
                array[n7][k] = n8;
                final double abs2 = Math.abs(n8);
                if (abs2 >= n5) {
                    n5 = abs2;
                    n6 = n7;
                }
            }
            if (k != n6) {
                for (int n10 = 0; n10 != n; ++n10) {
                    final double n11 = array[n6][n10];
                    array[n6][n10] = array[k][n10];
                    array[k][n10] = n11;
                }
                array3[n6] = array3[k];
            }
            array2[k] = n6;
            if (array[k][k] == 0.0) {
                System.out.println("avoided zero");
                array[k][k] = 1.0E-18;
            }
            if (k != n - 1) {
                final double n12 = 1.0 / array[k][k];
                for (int n13 = k + 1; n13 != n; ++n13) {
                    final double[] array4 = array[n13];
                    final int n14 = k;
                    array4[n14] *= n12;
                }
            }
        }
        return true;
    }
    
    void lu_solve(final double[][] array, final int n, final int[] array2, final double[] array3) {
        int i;
        for (i = 0; i != n; ++i) {
            final int n2 = array2[i];
            final double n3 = array3[n2];
            array3[n2] = array3[i];
            array3[i] = n3;
            if (n3 != 0.0) {
                break;
            }
        }
        for (int n4 = i++; i < n; ++i) {
            final int n5 = array2[i];
            double n6 = array3[n5];
            array3[n5] = array3[i];
            for (int j = n4; j < i; ++j) {
                n6 -= array[i][j] * array3[j];
            }
            array3[i] = n6;
        }
        for (int k = n - 1; k >= 0; --k) {
            double n7 = array3[k];
            for (int l = k + 1; l != n; ++l) {
                n7 -= array[k][l] * array3[l];
            }
            array3[k] = n7 / array[k][k];
        }
    }
    
    static {
        CirSim.muString = "u";
        CirSim.ohmString = "ohm";
    }
    
    class FindPathInfo
    {
        static final int INDUCT = 1;
        static final int VOLTAGE = 2;
        static final int SHORT = 3;
        static final int CAP_V = 4;
        boolean[] used;
        int dest;
        CircuitElm firstElm;
        int type;
        
        FindPathInfo(final int type, final CircuitElm firstElm, final int dest) {
            this.dest = dest;
            this.type = type;
            this.firstElm = firstElm;
            this.used = new boolean[CirSim.this.nodeList.size()];
        }
        
        boolean findPath(final int n) {
            return this.findPath(n, -1);
        }
        
        boolean findPath(final int n, int n2) {
            if (n == this.dest) {
                return true;
            }
            if (n2-- == 0) {
                return false;
            }
            if (this.used[n]) {
                return false;
            }
            this.used[n] = true;
            for (int i = 0; i != CirSim.this.elmList.size(); ++i) {
                final CircuitElm elm = CirSim.this.getElm(i);
                if (elm != this.firstElm) {
                    if (this.type != 1 || !(elm instanceof CurrentElm)) {
                        if (this.type != 2 || elm.isWire() || elm instanceof VoltageElm) {
                            if (this.type != 3 || elm.isWire()) {
                                if (this.type != 4 || elm.isWire() || elm instanceof CapacitorElm || elm instanceof VoltageElm) {
                                    if (n == 0) {
                                        for (int j = 0; j != elm.getPostCount(); ++j) {
                                            if (elm.hasGroundConnection(j) && this.findPath(elm.getNode(j), n2)) {
                                                this.used[n] = false;
                                                return true;
                                            }
                                        }
                                    }
                                    int n3;
                                    for (n3 = 0; n3 != elm.getPostCount() && elm.getNode(n3) != n; ++n3) {}
                                    if (n3 != elm.getPostCount()) {
                                        if (elm.hasGroundConnection(n3) && this.findPath(0, n2)) {
                                            this.used[n] = false;
                                            return true;
                                        }
                                        if (this.type == 1 && elm instanceof InductorElm) {
                                            double current = elm.getCurrent();
                                            if (n3 == 0) {
                                                current = -current;
                                            }
                                            if (Math.abs(current - this.firstElm.getCurrent()) > 1.0E-10) {
                                                continue;
                                            }
                                        }
                                        for (int k = 0; k != elm.getPostCount(); ++k) {
                                            if (n3 != k) {
                                                if (elm.getConnection(n3, k) && this.findPath(elm.getNode(k), n2)) {
                                                    this.used[n] = false;
                                                    return true;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return this.used[n] = false;
        }
    }
}
