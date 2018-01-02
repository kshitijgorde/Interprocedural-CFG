import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Button;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class DFIELD extends Applet implements ActionListener
{
    public static final String DFIELDVersion = "DFIELD 2002.2";
    public static final String me = "Copyright 1994 - 2002, John C. Polking, Rice University";
    public static final int paramCount = 4;
    public static String dydx_EquationStr;
    public static C_INFIX diffyQ;
    public static C_INFIX[] EQU_K;
    public static D_EQU EQU;
    public static D_ABOUT ABOUT;
    public static C_MSG MSG;
    public static D_GRAPH GRAPH;
    public static dataGraphConstants directionFieldConstants;
    public static boolean solverThreadActive;
    public static double EulerStepSize;
    public static double ImprovedEulerStepSize;
    public static double RungeKutta2StepSize;
    public static double RungeKutta4StepSize;
    public static double RKF_Epsilon;
    public static double RKF_Maxh;
    public static double RKF_Minh;
    public static double Dormand_Epsilon;
    public static double Dormand_Maxh;
    public static double Dormand_Minh;
    public static int Dormand_refine;
    public static dataORBIT orbit;
    public static long timeStep;
    public static int FieldPointsRow;
    public static int FieldPointsColumn;
    public static double ComputationWindowX;
    public static double ComputationWindowY;
    public static boolean ShowDF;
    public static boolean ShowArrows;
    public static String[] menuWindowName;
    public static Frame[] menuWindowFrame;
    public static int menuWindowCount;
    public static int sigfigs;
    public static boolean printing;
    public static boolean undefinedPrintArea;
    public static boolean RunningApplet;
    
    public void init() {
        final Font font = new Font("SansSerif", 1, 24);
        final Button button = new Button("DFIELD 2002.2");
        button.setFont(font);
        this.add(button);
        button.addActionListener(this);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("DFIELD 2002.2")) {
            StartUpDfield();
        }
    }
    
    public void destroy() {
        quitDFIELD_DoneWithPrompt();
    }
    
    public static void main(final String[] array) {
        DFIELD.RunningApplet = false;
        StartUpDfield();
    }
    
    public static void StartUpDfield() {
        C_TOOL.setFonts(C_TOOL.getDefaultFontSize());
        DFIELD.EQU = new D_EQU();
        DFIELD.ABOUT = new D_ABOUT();
        DFIELD.MSG = new C_MSG();
        DFIELD.GRAPH = new D_GRAPH();
        DFIELD.diffyQ = new C_INFIX();
        for (int i = 0; i < 4; ++i) {
            DFIELD.EQU_K[i] = new C_INFIX();
        }
        D_EQU.EQU_MAIN(DFIELD.EQU);
        C_MSG.MSG_MAIN("DFIELD Messages", DFIELD.MSG);
        D_GRAPH.GRAPH_MAIN(DFIELD.GRAPH);
        D_EQU.callGraph();
        D_ABOUT.ABOUT_MAIN(DFIELD.ABOUT);
        InitMenuWindow();
    }
    
    public static void quitDFIELD() {
        final dataSample dataSample = new dataSample();
        new C_DIALOG(D_GRAPH.GRAPH, "Are you sure you want to Quit DFIELD?", "Quit", dataSample).show();
        if (dataSample.flag) {
            quitDFIELD_DoneWithPrompt();
        }
    }
    
    public static void quitDFIELD_DoneWithPrompt() {
        D_OPTIONDF.QuitD_OPTIONDF();
        D_KEYBOARD.QuitD_KEYBOARD();
        D_PRINT.QuitD_PRINT();
        C_MSG.QuitC_MSG();
        D_ZOOMOUT.QuitD_ZOOMOUT();
        D_ABOUT.QuitD_ABOUT();
        D_GRAPH.QuitD_GRAPH();
        D_EQU.QuitD_EQU();
        if (!DFIELD.RunningApplet) {
            System.exit(0);
        }
    }
    
    public static void InitMenuWindow() {
        DFIELD.menuWindowCount = -1;
        AddMenuWindow(C_MSG.MSG);
        AddMenuWindow(D_EQU.EQU);
        AddMenuWindow(D_GRAPH.GRAPH);
        D_GRAPH.mu[2].addSeparator();
        D_EQU.mu[2].addSeparator();
    }
    
    public static void AddMenuWindow(final Frame frame) {
        ++DFIELD.menuWindowCount;
        final int menuWindowCount = DFIELD.menuWindowCount;
        DFIELD.menuWindowName[menuWindowCount] = frame.getTitle();
        DFIELD.menuWindowFrame[menuWindowCount] = frame;
        MenuItem menuItem;
        MenuItem menuItem2;
        if (DFIELD.menuWindowCount < 9) {
            final int n = 49 + menuWindowCount;
            menuItem = new MenuItem(DFIELD.menuWindowName[menuWindowCount], new MenuShortcut(n));
            menuItem2 = new MenuItem(DFIELD.menuWindowName[menuWindowCount], new MenuShortcut(n));
        }
        else {
            menuItem = new MenuItem(DFIELD.menuWindowName[menuWindowCount]);
            menuItem2 = new MenuItem(DFIELD.menuWindowName[menuWindowCount]);
        }
        D_GRAPH.mu[2].add(menuItem);
        menuItem.addActionListener(D_GRAPH.GRAPH);
        menuItem.setFont(C_TOOL.fontNormal);
        D_EQU.mu[2].add(menuItem2);
        menuItem2.addActionListener(D_EQU.EQU);
        menuItem2.setFont(C_TOOL.fontNormal);
    }
    
    static {
        DFIELD.EQU_K = new C_INFIX[4];
        DFIELD.directionFieldConstants = new dataGraphConstants();
        DFIELD.solverThreadActive = false;
        DFIELD.RKF_Epsilon = 1.0E-6;
        DFIELD.Dormand_Epsilon = 1.0E-6;
        DFIELD.Dormand_refine = 4;
        DFIELD.orbit = new dataORBIT(200, 500000);
        DFIELD.timeStep = 1L;
        DFIELD.FieldPointsRow = 20;
        DFIELD.FieldPointsColumn = 20;
        DFIELD.ComputationWindowX = 1.0;
        DFIELD.ComputationWindowY = 100.0;
        DFIELD.ShowDF = true;
        DFIELD.ShowArrows = true;
        DFIELD.menuWindowName = new String[35];
        DFIELD.menuWindowFrame = new Frame[35];
        DFIELD.menuWindowCount = -1;
        DFIELD.sigfigs = 5;
        DFIELD.printing = false;
        DFIELD.undefinedPrintArea = true;
        DFIELD.RunningApplet = true;
    }
}
