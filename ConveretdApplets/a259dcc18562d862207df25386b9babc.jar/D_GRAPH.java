import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.MenuComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.WindowEvent;
import java.awt.event.MouseEvent;
import java.awt.MenuShortcut;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Label;
import java.awt.CheckboxMenuItem;
import java.awt.MenuItem;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.event.ItemListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class D_GRAPH extends Frame implements WindowListener, ActionListener, MouseListener, MouseMotionListener, ItemListener
{
    public static D_GRAPH GRAPH;
    public static D_SOLVER SolverThread;
    public static dataGraphConstants g;
    public static boolean drawConstantsUndefined;
    public static int windowX;
    public static int windowY;
    public static MenuBar myMenu;
    public static final int muFile = 0;
    public static final int muEdit = 1;
    public static final int muWindow = 2;
    public static final int muSolution = 3;
    public static final int muOptions = 4;
    public static final int muBackground = 5;
    public static final int muFontSize = 6;
    public static final int muDelay = 7;
    public static final int muSolver = 8;
    public static final int muDirection = 9;
    public static final int last_mu = 9;
    public static Menu[] mu;
    public static String[] muName;
    public static final int miQuit = 0;
    public static final int miPrint = 1;
    public static final int miZoomOut = 2;
    public static final int miKeyboardInput = 3;
    public static final int miAddText = 4;
    public static final int miDeleteOrbit = 5;
    public static final int miDeleteAllOrbits = 6;
    public static final int miEraseLastText = 7;
    public static final int miEraseText = 8;
    public static final int miReCalculate = 9;
    public static final int miDFSettings = 10;
    public static final int miStop = 11;
    public static final int last_mi = 11;
    public static MenuItem[] mi;
    public static String[] miName;
    public static final int TotalSolvers = 5;
    public static final int cboxEuler = 0;
    public static final int cboxRungeKutta2 = 1;
    public static final int cboxRungeKutta4 = 2;
    public static final int cboxRKF = 3;
    public static final int cboxDormand = 4;
    public static final int cboxBackgroundWhite = 5;
    public static final int cboxBackgroundBlack = 6;
    public static final int cboxFontSize10 = 7;
    public static final int cboxFontSize12 = 8;
    public static final int cboxFontSize14 = 9;
    public static final int cboxFontSize16 = 10;
    public static final int cboxFontSize18 = 11;
    public static final int cboxNoDelay = 12;
    public static final int cboxMS1 = 13;
    public static final int cboxMS2 = 14;
    public static final int cboxMS5 = 15;
    public static final int cboxMS10 = 16;
    public static final int cboxMS25 = 17;
    public static final int cboxMS50 = 18;
    public static final int cboxMS100 = 19;
    public static final int cboxMS250 = 20;
    public static final int cboxMS500 = 21;
    public static final int cboxMS1000 = 22;
    public static final int cboxDirBoth = 23;
    public static final int cboxDirFoward = 24;
    public static final int cboxDirBack = 25;
    public static final int cboxShowPoints = 26;
    public static final int cboxZoomInBox = 27;
    public static final int cboxZoomInPoint = 28;
    public static final int cboxZoomSquare = 29;
    public static final int cboxShowCrossHairs = 30;
    public static final int last_cbox = 30;
    public static CheckboxMenuItem[] cbox;
    public static String[] cboxName;
    public static Label labelInfo;
    public static Label labelMouse;
    public static String equationString;
    public static int domainArrowSide;
    public static int domainArrowLeft;
    public static int domainArrowTop;
    public static int TextAnnotationIndex;
    public static String[] TextAnnotation;
    public static double[] TextAnnotationX;
    public static double[] TextAnnotationY;
    public static String newText;
    public static boolean addTextMode;
    public static boolean crossHairVisible;
    public static boolean zooming;
    public static boolean deleteOrbitMode;
    public static double[] zoomXmin;
    public static double[] zoomXmax;
    public static double[] zoomYmin;
    public static double[] zoomYmax;
    public static int zoomCount;
    public static double zoomInX1;
    public static double zoomInY1;
    public static Color colorArrows;
    public static Color colorGrid;
    public static Color colorBorderForeground;
    public static Color[] colorSolver;
    
    public static void GRAPH_MAIN(final D_GRAPH graph) {
        D_GRAPH.GRAPH = graph;
        D_GRAPH.g = DFIELD.directionFieldConstants;
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int screenResolution = Toolkit.getDefaultToolkit().getScreenResolution();
        if (D_GRAPH.GRAPH == null) {
            D_GRAPH.GRAPH = new D_GRAPH();
        }
        D_GRAPH.GRAPH.setResizable(true);
        D_GRAPH.GRAPH.setTitle("DFIELD Direction Field Window");
        D_GRAPH.GRAPH.show();
        final int min = Math.min(screenSize.width, screenResolution * 7);
        D_GRAPH.GRAPH.setBounds(screenSize.width - min, D_GRAPH.GRAPH.getInsets().top, min, Math.min(screenSize.height - 2 * D_GRAPH.GRAPH.getInsets().top - D_GRAPH.GRAPH.getInsets().bottom, screenResolution * 7));
        D_GRAPH.GRAPH.addWindowListener(D_GRAPH.GRAPH);
        D_GRAPH.GRAPH.addMouseListener(D_GRAPH.GRAPH);
        D_GRAPH.GRAPH.setLayout(null);
        D_GRAPH.labelInfo = new Label("", 1);
        D_GRAPH.GRAPH.add(D_GRAPH.labelInfo);
        D_GRAPH.labelMouse = new Label("", 0);
        D_GRAPH.GRAPH.add(D_GRAPH.labelMouse);
        D_GRAPH.GRAPH.add(D_GRAPH.g.StopButton);
        D_GRAPH.g.StopButton.addActionListener(D_GRAPH.GRAPH);
        D_GRAPH.GRAPH.addMouseMotionListener(D_GRAPH.GRAPH);
        D_GRAPH.g.StopButton.setEnabled(false);
        MenuInit();
        SetColors();
        SetDrawConstants();
        ClearAll();
    }
    
    public static void MenuInit() {
        D_GRAPH.muName[0] = "File";
        D_GRAPH.muName[1] = "Edit";
        D_GRAPH.muName[2] = "Window";
        D_GRAPH.muName[4] = "Options";
        D_GRAPH.muName[5] = "Background";
        D_GRAPH.muName[6] = "Font Size";
        D_GRAPH.muName[7] = "Delay Time Per Point";
        D_GRAPH.muName[8] = "ODE Solver";
        D_GRAPH.muName[9] = "Orbit Direction";
        D_GRAPH.muName[3] = "Solution";
        D_GRAPH.miName[1] = "Print...";
        D_GRAPH.miName[11] = "Stop ODE solver";
        D_GRAPH.miName[0] = "Quit";
        D_GRAPH.miName[2] = "Zoom-Out";
        D_GRAPH.miName[3] = "Keyboard Input of Initial Value";
        D_GRAPH.miName[4] = "Enter Text Annotation";
        D_GRAPH.miName[5] = "Delete Orbit";
        D_GRAPH.miName[6] = "Delete All Orbits";
        D_GRAPH.miName[7] = "Erase Last Text Annotation";
        D_GRAPH.miName[8] = "Erase All Text Annotations";
        D_GRAPH.miName[10] = "Direction Field Settings";
        D_GRAPH.miName[9] = "Recalculate All Solutions Using Current Settings";
        D_GRAPH.cboxName[5] = "White";
        D_GRAPH.cboxName[6] = "Black";
        D_GRAPH.cboxName[7] = "10";
        D_GRAPH.cboxName[8] = "12";
        D_GRAPH.cboxName[9] = "14";
        D_GRAPH.cboxName[10] = "16";
        D_GRAPH.cboxName[11] = "18";
        D_GRAPH.cboxName[12] = "No Delay";
        D_GRAPH.cboxName[13] = "1 Millisecond";
        D_GRAPH.cboxName[14] = "2 Milliseconds";
        D_GRAPH.cboxName[15] = "5 Milliseconds";
        D_GRAPH.cboxName[16] = "10 Milliseconds";
        D_GRAPH.cboxName[17] = "25 Milliseconds";
        D_GRAPH.cboxName[18] = "50 Milliseconds";
        D_GRAPH.cboxName[19] = "100 Milliseconds";
        D_GRAPH.cboxName[20] = "250 Milliseconds";
        D_GRAPH.cboxName[21] = "1/2 second";
        D_GRAPH.cboxName[22] = "1 second";
        D_GRAPH.cboxName[0] = "Euler";
        D_GRAPH.cboxName[1] = "Runge-Kutta 2";
        D_GRAPH.cboxName[2] = "Runge-Kutta 4";
        D_GRAPH.cboxName[3] = "Runge-Kutta-Fehlberg";
        D_GRAPH.cboxName[4] = "Dormand-Prince";
        D_GRAPH.cboxName[23] = "Both";
        D_GRAPH.cboxName[24] = "Foward";
        D_GRAPH.cboxName[25] = "Backward";
        D_GRAPH.cboxName[26] = "Show Points";
        D_GRAPH.cboxName[27] = "Zoom-in: select rectangle";
        D_GRAPH.cboxName[28] = "Zoom-in: select point";
        D_GRAPH.cboxName[29] = "Zoom-in: square domain";
        D_GRAPH.cboxName[30] = "Show cross-hairs";
        D_GRAPH.myMenu = new MenuBar();
        MakeMenu(0);
        MakeMenuItem(1, 0, 80);
        MakeMenuItem(11, 0, 72);
        D_GRAPH.mu[0].addSeparator();
        MakeMenuItem(0, 0, 81);
        MakeMenu(1);
        MakeCbox(27, false, 1);
        MakeCbox(28, true, 1);
        MakeCbox(29, false, 1);
        MakeMenuItem(2, 1);
        D_GRAPH.mu[1].addSeparator();
        MakeMenuItem(5, 1);
        MakeMenuItem(6, 1, 127);
        D_GRAPH.mu[1].addSeparator();
        MakeMenuItem(4, 1);
        MakeMenuItem(7, 1);
        MakeMenuItem(8, 1);
        MakeMenu(2);
        MakeMenu(3);
        MakeMenuItem(3, 3);
        MakeMenuItem(9, 3);
        MakeMenu(4);
        MakeSubMenu(5, 4);
        MakeCbox(5, false, 5);
        MakeCbox(6, false, 5);
        MakeSubMenu(6, 4);
        MakeCbox(7, false, 6);
        MakeCbox(8, false, 6);
        MakeCbox(9, false, 6);
        MakeCbox(10, false, 6);
        MakeCbox(11, false, 6);
        MakeCbox(26, false, 4);
        MakeCbox(30, false, 4);
        MakeSubMenu(7, 4);
        MakeCbox(12, false, 7);
        MakeCbox(13, true, 7);
        MakeCbox(14, false, 7);
        MakeCbox(15, false, 7);
        MakeCbox(16, false, 7);
        MakeCbox(17, false, 7);
        MakeCbox(18, false, 7);
        MakeCbox(19, false, 7);
        MakeCbox(20, false, 7);
        MakeCbox(21, false, 7);
        MakeCbox(22, false, 7);
        MakeMenuItem(10, 4);
        MakeSubMenu(9, 4);
        MakeCbox(23, true, 9);
        MakeCbox(24, false, 9);
        MakeCbox(25, false, 9);
        MakeSubMenu(8, 4);
        MakeCbox(0, false, 8);
        MakeCbox(1, false, 8);
        MakeCbox(2, false, 8);
        MakeCbox(3, false, 8);
        MakeCbox(4, true, 8);
    }
    
    public static void MakeMenu(final int n) {
        (D_GRAPH.mu[n] = new Menu(D_GRAPH.muName[n])).addActionListener(D_GRAPH.GRAPH);
        D_GRAPH.myMenu.add(D_GRAPH.mu[n]);
    }
    
    public static void MakeSubMenu(final int n, final int n2) {
        (D_GRAPH.mu[n] = new Menu(D_GRAPH.muName[n])).addActionListener(D_GRAPH.GRAPH);
        D_GRAPH.mu[n2].add(D_GRAPH.mu[n]);
    }
    
    public static void MakeCbox(final int n, final boolean b, final int n2) {
        (D_GRAPH.cbox[n] = new CheckboxMenuItem(D_GRAPH.cboxName[n], b)).addItemListener(D_GRAPH.GRAPH);
        D_GRAPH.mu[n2].add(D_GRAPH.cbox[n]);
    }
    
    public static void MakeMenuItem(final int n, final int n2) {
        MakeMenuItem(n, n2, 65535);
    }
    
    public static void MakeMenuItem(final int n, final int n2, final int n3) {
        if (n3 == 65535) {
            D_GRAPH.mi[n] = new MenuItem(D_GRAPH.miName[n]);
        }
        else {
            D_GRAPH.mi[n] = new MenuItem(D_GRAPH.miName[n], new MenuShortcut(n3));
        }
        D_GRAPH.mi[n].addActionListener(D_GRAPH.GRAPH);
        D_GRAPH.mu[n2].add(D_GRAPH.mi[n]);
    }
    
    public static void SetMenuCheckBoxes() {
        for (int i = 7; i <= 11; ++i) {
            D_GRAPH.cbox[i].setState(false);
        }
        if (C_TOOL.fontNormalSize == 10) {
            D_GRAPH.cbox[7].setState(true);
        }
        else if (C_TOOL.fontNormalSize == 12) {
            D_GRAPH.cbox[8].setState(true);
        }
        else if (C_TOOL.fontNormalSize == 14) {
            D_GRAPH.cbox[9].setState(true);
        }
        else if (C_TOOL.fontNormalSize == 16) {
            D_GRAPH.cbox[10].setState(true);
        }
        else if (C_TOOL.fontNormalSize == 18) {
            D_GRAPH.cbox[11].setState(true);
        }
    }
    
    public static void ChangeFont() {
        D_EQU.SetDefaultFontMextrix();
        D_EQU.SetDrawConstants();
        C_MSG.SetFontConstants();
        SetDrawConstants();
        ReDrawEverything();
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        D_GRAPH.GRAPH.setVisible(false);
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public static void QuitD_GRAPH() {
        if (D_GRAPH.GRAPH != null) {
            if (D_GRAPH.g.StopButton.isEnabled()) {
                D_GRAPH.SolverThread.stop();
            }
            D_GRAPH.g.Scr.dispose();
            D_GRAPH.g.Buf.dispose();
            D_GRAPH.GRAPH.dispose();
            D_GRAPH.GRAPH = null;
        }
    }
    
    public static void InitGraph() {
        SetMenuCheckBoxes();
        DFIELD.EulerStepSize = (D_GRAPH.g.xmax - D_GRAPH.g.xmin) / 1000.0;
        DFIELD.RungeKutta2StepSize = (D_GRAPH.g.xmax - D_GRAPH.g.xmin) / 500.0;
        DFIELD.RungeKutta4StepSize = (D_GRAPH.g.xmax - D_GRAPH.g.xmin) / 250.0;
        DFIELD.RKF_Maxh = (D_GRAPH.g.xmax - D_GRAPH.g.xmin) / 20.0;
        DFIELD.RKF_Minh = Math.pow(10.0, -15 + Math.ceil(C_TOOL.log10(Math.abs(D_GRAPH.g.xmax))));
        DFIELD.Dormand_Maxh = (D_GRAPH.g.xmax - D_GRAPH.g.xmin) / 10.0;
        DFIELD.Dormand_Minh = Math.pow(10.0, -15 + Math.ceil(C_TOOL.log10(Math.abs(D_GRAPH.g.xmax))));
        D_GRAPH.zoomCount = 0;
        InfoLabelOff();
        D_GRAPH.TextAnnotationIndex = 0;
        D_GRAPH.g.xmin0 = D_GRAPH.g.xmin;
        D_GRAPH.g.xmax0 = D_GRAPH.g.xmax;
        D_GRAPH.g.ymin0 = D_GRAPH.g.ymin;
        D_GRAPH.g.ymax0 = D_GRAPH.g.ymax;
        D_GRAPH.g.xmin00 = D_GRAPH.g.xmin;
        D_GRAPH.g.xmax00 = D_GRAPH.g.xmax;
        D_GRAPH.g.ymin00 = D_GRAPH.g.ymin;
        D_GRAPH.g.ymax00 = D_GRAPH.g.ymax;
        D_GRAPH.equationString = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(DFIELD.diffyQ.yName))).append("´ = ").append(DFIELD.dydx_EquationStr)));
        if (!DFIELD.diffyQ.kName[0].equals("NULLNAME")) {
            D_GRAPH.equationString = String.valueOf(String.valueOf(D_GRAPH.equationString)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer(",       ").append(DFIELD.diffyQ.kName[0]).append("=").append(DFIELD.EQU_K[0].equStr))))));
        }
        if (!DFIELD.diffyQ.kName[1].equals("NULLNAME")) {
            D_GRAPH.equationString = String.valueOf(String.valueOf(D_GRAPH.equationString)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer(",       ").append(DFIELD.diffyQ.kName[1]).append("=").append(DFIELD.EQU_K[1].equStr))))));
        }
        if (!DFIELD.diffyQ.kName[2].equals("NULLNAME")) {
            D_GRAPH.equationString = String.valueOf(String.valueOf(D_GRAPH.equationString)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer(",       ").append(DFIELD.diffyQ.kName[2]).append("=").append(DFIELD.EQU_K[2].equStr))))));
        }
        if (!DFIELD.diffyQ.kName[3].equals("NULLNAME")) {
            D_GRAPH.equationString = String.valueOf(String.valueOf(D_GRAPH.equationString)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer(",       ").append(DFIELD.diffyQ.kName[3]).append("=").append(DFIELD.EQU_K[3].equStr))))));
        }
        D_GRAPH.equationString = C_TOOL.equationFormat(D_GRAPH.equationString);
        D_GRAPH.GRAPH.setVisible(true);
        D_GRAPH.GRAPH.toFront();
        C_MSG.msgClear();
        if (DFIELD.orbit.count > 0) {
            ReCalculate();
        }
        else {
            DFIELD.orbit.dataFree = 0;
            ReDrawEverything();
        }
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        final String s = (String)itemEvent.getItem();
        int n = -1;
        for (int i = 0; i <= 30; ++i) {
            if (s.equals(D_GRAPH.cboxName[i])) {
                n = i;
            }
        }
        if (n == -1) {
            return;
        }
        if (n >= 23 && n <= 25) {
            for (int j = 23; j <= 25; ++j) {
                D_GRAPH.cbox[j].setState(false);
            }
            D_GRAPH.cbox[n].setState(true);
        }
        else if (n >= 0 && n <= 4) {
            for (int k = 0; k <= 4; ++k) {
                D_GRAPH.cbox[k].setState(false);
            }
            D_GRAPH.cbox[n].setState(true);
            D_OPTION.OPTION_MAIN();
        }
        else if (n >= 12 && n <= 22) {
            for (int l = 12; l <= 22; ++l) {
                D_GRAPH.cbox[l].setState(false);
            }
            if (n == 13) {
                DFIELD.timeStep = 1L;
            }
            else if (n == 14) {
                DFIELD.timeStep = 2L;
            }
            else if (n == 15) {
                DFIELD.timeStep = 5L;
            }
            else if (n == 16) {
                DFIELD.timeStep = 10L;
            }
            else if (n == 17) {
                DFIELD.timeStep = 25L;
            }
            else if (n == 18) {
                DFIELD.timeStep = 50L;
            }
            else if (n == 19) {
                DFIELD.timeStep = 100L;
            }
            else if (n == 20) {
                DFIELD.timeStep = 250L;
            }
            else if (n == 21) {
                DFIELD.timeStep = 500L;
            }
            else if (n == 22) {
                DFIELD.timeStep = 1000L;
            }
            else {
                DFIELD.timeStep = 0L;
            }
            D_GRAPH.cbox[n].setState(true);
        }
        else if (n == 5) {
            C_TOOL.BackgroundColor = Color.white;
            SetColors();
            ReDrawEverything();
        }
        else if (n == 6) {
            C_TOOL.BackgroundColor = Color.black;
            SetColors();
            ReDrawEverything();
        }
        else if (n >= 7 && n <= 11) {
            if (n == 7) {
                C_TOOL.setFonts(10);
            }
            else if (n == 8) {
                C_TOOL.setFonts(12);
            }
            else if (n == 9) {
                C_TOOL.setFonts(14);
            }
            else if (n == 10) {
                C_TOOL.setFonts(16);
            }
            else if (n == 11) {
                C_TOOL.setFonts(18);
            }
            SetMenuCheckBoxes();
            ChangeFont();
        }
        else if (n == 27) {
            SetZoomMode(n);
        }
        else if (n == 28) {
            SetZoomMode(n);
        }
        else if (n == 29) {
            SetZoomMode(n);
        }
        else if (n == 26) {
            ReDrawEverything();
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        String s = actionEvent.getActionCommand();
        if (s == null) {
            s = ((MenuItem)actionEvent.getSource()).getLabel();
        }
        if (s.equals("Stop")) {
            D_SOLVER.stoppedByUser = true;
            D_GRAPH.SolverThread.findingOrbit = false;
            return;
        }
        int n = -1;
        for (int i = 0; i <= 11; ++i) {
            if (s.equals(D_GRAPH.miName[i])) {
                n = i;
            }
        }
        if (n == 9) {
            ReCalculate();
        }
        else if (n == 0) {
            DFIELD.quitDFIELD();
        }
        else {
            if (n == 11) {
                D_SOLVER.stoppedByUser = true;
                D_GRAPH.SolverThread.findingOrbit = false;
                return;
            }
            if (n == 1) {
                D_PRINT.PRINT_MAIN();
            }
            else if (n == 5) {
                DeleteOrbitModeOn();
            }
            else if (n == 6) {
                DeleteAllOrbits();
            }
            else if (n == 7) {
                EraseText(1);
            }
            else if (n == 8) {
                EraseText(-1);
            }
            else if (n == 10) {
                D_OPTIONDF.OPTIONDF_MAIN();
            }
            else if (n == 3) {
                D_KEYBOARD.KEYBOARD_MAIN();
            }
            else if (n == 4) {
                AddTextModeOn();
            }
            else if (n == 2) {
                D_ZOOMOUT.ZOOMOUT_MAIN();
            }
            else if (n == 9) {
                ReCalculate();
            }
            else if (this.IsMenuItemOfWindow(s)) {}
        }
    }
    
    public boolean IsMenuItemOfWindow(final String s) {
        for (int i = 0; i <= DFIELD.menuWindowCount; ++i) {
            if (s.equals(DFIELD.menuWindowName[i])) {
                DFIELD.menuWindowFrame[i].show();
                return true;
            }
        }
        return false;
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (DFIELD.solverThreadActive) {
            return;
        }
        boolean b = false;
        if (mouseEvent.isShiftDown() || mouseEvent.isControlDown()) {
            b = true;
        }
        final double x_from_screen = x_from_screen(mouseEvent.getX());
        final double y_from_screen = y_from_screen(mouseEvent.getY());
        D_GRAPH.zooming = false;
        if (D_GRAPH.addTextMode) {
            ++D_GRAPH.TextAnnotationIndex;
            D_GRAPH.TextAnnotation[D_GRAPH.TextAnnotationIndex] = D_GRAPH.newText;
            D_GRAPH.TextAnnotationX[D_GRAPH.TextAnnotationIndex] = x_from_screen;
            D_GRAPH.TextAnnotationY[D_GRAPH.TextAnnotationIndex] = y_from_screen;
            DisplayText(D_GRAPH.TextAnnotationIndex);
            InfoLabelOff();
            D_GRAPH.g.myRepaint();
            return;
        }
        if (x_from_screen < D_GRAPH.g.xmin || x_from_screen > D_GRAPH.g.xmax) {
            return;
        }
        if (y_from_screen < D_GRAPH.g.ymin || y_from_screen > D_GRAPH.g.ymax) {
            return;
        }
        if (b) {
            if (D_GRAPH.cbox[27].getState()) {
                D_GRAPH.zoomInX1 = x_from_screen;
                D_GRAPH.zoomInY1 = y_from_screen;
                D_GRAPH.zooming = true;
            }
            else {
                ZoomNow(x_from_screen, y_from_screen);
            }
        }
        else if (D_GRAPH.deleteOrbitMode) {
            DFIELD.orbit.DeleteOrbit(DFIELD.orbit.SelectOrbit((float)x_from_screen, (float)y_from_screen));
            InfoLabelOff();
            ReDrawEverything();
        }
        else {
            PlotNewSolution(x_from_screen, y_from_screen);
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        if (DFIELD.solverThreadActive) {
            return;
        }
        final double x_from_screen = x_from_screen(mouseEvent.getX());
        final double y_from_screen = y_from_screen(mouseEvent.getY());
        if (D_GRAPH.crossHairVisible) {
            D_GRAPH.g.myRepaint();
            D_GRAPH.crossHairVisible = false;
        }
        if (x_from_screen < D_GRAPH.g.xmin || x_from_screen > D_GRAPH.g.xmax) {
            return;
        }
        if (y_from_screen < D_GRAPH.g.ymin || y_from_screen > D_GRAPH.g.ymax) {
            return;
        }
        D_GRAPH.labelMouse.setText(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(C_TOOL.Str$(x_from_screen, DFIELD.sigfigs)))).append(",  ").append(C_TOOL.Str$(y_from_screen, DFIELD.sigfigs)))));
        if (!D_GRAPH.cbox[30].getState()) {
            return;
        }
        final int screenX = D_GRAPH.g.screenX(x_from_screen);
        final int screenY = D_GRAPH.g.screenY(y_from_screen);
        final int screenX2 = D_GRAPH.g.screenX(D_GRAPH.g.xmax);
        final int screenY2 = D_GRAPH.g.screenY(D_GRAPH.g.ymax);
        final int screenX3 = D_GRAPH.g.screenX(D_GRAPH.g.xmin);
        final int screenY3 = D_GRAPH.g.screenY(D_GRAPH.g.ymin);
        D_GRAPH.g.myRepaint();
        D_GRAPH.g.Scr.setColor(C_TOOL.ForegroundColor);
        D_GRAPH.g.Scr.drawLine(screenX, screenY - 4, screenX, screenY2);
        D_GRAPH.g.Scr.drawLine(screenX, screenY + 4, screenX, screenY3);
        D_GRAPH.g.Scr.drawLine(screenX3, screenY, screenX - 4, screenY);
        D_GRAPH.g.Scr.drawLine(screenX2, screenY, screenX + 4, screenY);
        D_GRAPH.crossHairVisible = true;
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (!D_GRAPH.zooming) {
            return;
        }
        final double x_from_screen = x_from_screen(mouseEvent.getX());
        final double y_from_screen = y_from_screen(mouseEvent.getY());
        if (x_from_screen < D_GRAPH.g.xmin || x_from_screen > D_GRAPH.g.xmax) {
            return;
        }
        if (y_from_screen < D_GRAPH.g.ymin || y_from_screen > D_GRAPH.g.ymax) {
            return;
        }
        int screenX = D_GRAPH.g.screenX(D_GRAPH.zoomInX1);
        int screenY = D_GRAPH.g.screenY(D_GRAPH.zoomInY1);
        final int screenX2 = D_GRAPH.g.screenX(x_from_screen);
        final int screenY2 = D_GRAPH.g.screenY(y_from_screen);
        final int abs = Math.abs(screenX - screenX2);
        final int abs2 = Math.abs(screenY - screenY2);
        if (screenX > screenX2) {
            screenX = screenX2;
        }
        if (screenY > screenY2) {
            screenY = screenY2;
        }
        D_GRAPH.g.myRepaint();
        D_GRAPH.g.Scr.setColor(C_TOOL.ForegroundColor);
        D_GRAPH.g.Scr.drawRect(screenX, screenY, abs, abs2);
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (!D_GRAPH.zooming) {
            return;
        }
        final double x_from_screen = x_from_screen(mouseEvent.getX());
        final double y_from_screen = y_from_screen(mouseEvent.getY());
        if (x_from_screen < D_GRAPH.g.xmin || x_from_screen > D_GRAPH.g.xmax) {
            D_GRAPH.zooming = false;
        }
        else if (y_from_screen < D_GRAPH.g.ymin || y_from_screen > D_GRAPH.g.ymax) {
            D_GRAPH.zooming = false;
        }
        ZoomNow(x_from_screen, y_from_screen);
    }
    
    public static void SetDrawConstants() {
        if (DFIELD.solverThreadActive) {
            StopSolver();
        }
        D_GRAPH.labelInfo.setFont(C_TOOL.fontBold);
        D_GRAPH.labelMouse.setFont(C_TOOL.fontNormal);
        D_GRAPH.myMenu.setFont(C_TOOL.fontNormal);
        for (int i = 0; i <= 9; ++i) {
            D_GRAPH.mu[i].setFont(C_TOOL.fontNormal);
        }
        for (int j = 0; j <= 11; ++j) {
            D_GRAPH.mi[j].setFont(C_TOOL.fontNormal);
        }
        for (int k = 0; k <= 30; ++k) {
            D_GRAPH.cbox[k].setFont(C_TOOL.fontNormal);
        }
        final MenuBar menuBar = D_GRAPH.GRAPH.getMenuBar();
        if (menuBar != null) {
            D_GRAPH.GRAPH.remove(menuBar);
        }
        D_GRAPH.GRAPH.setMenuBar(D_GRAPH.myMenu);
        if (D_GRAPH.g.Scr != null) {
            D_GRAPH.g.Scr.finalize();
        }
        if (D_GRAPH.g.Scr != null) {
            D_GRAPH.g.Scr.dispose();
        }
        final Dimension size = D_GRAPH.GRAPH.getSize();
        (D_GRAPH.g.Scr = D_GRAPH.GRAPH.getGraphics()).setColor(C_TOOL.BackgroundColor);
        D_GRAPH.g.Scr.fillRect(0, 0, size.width, size.height + 40);
        D_GRAPH.g.windowLeft = D_GRAPH.GRAPH.getInsets().left;
        D_GRAPH.g.windowTop = D_GRAPH.GRAPH.getInsets().top;
        D_GRAPH.g.windowWidth = size.width - D_GRAPH.GRAPH.getInsets().left - D_GRAPH.GRAPH.getInsets().right;
        D_GRAPH.g.windowHeight = size.height - D_GRAPH.GRAPH.getInsets().top - D_GRAPH.GRAPH.getInsets().bottom;
        D_GRAPH.g.Scr.translate(D_GRAPH.g.windowLeft, D_GRAPH.g.windowTop);
        if (D_GRAPH.g.Buf != null) {
            D_GRAPH.g.Buf.finalize();
        }
        if (D_GRAPH.g.Buf != null) {
            D_GRAPH.g.Buf.dispose();
        }
        D_GRAPH.g.GetGraphics(D_GRAPH.GRAPH);
        D_GRAPH.g.StopButton.setFont(C_TOOL.fontNormal);
        if (D_GRAPH.g.StopButton.isEnabled()) {
            StopSolver();
        }
        final Dimension size2 = D_GRAPH.GRAPH.getSize();
        D_GRAPH.windowX = size2.width;
        D_GRAPH.windowY = size2.height;
        D_GRAPH.drawConstantsUndefined = false;
    }
    
    public static void SetColors() {
        if (C_TOOL.BackgroundColor == Color.white) {
            D_GRAPH.cbox[6].setState(false);
            D_GRAPH.cbox[5].setState(true);
            C_TOOL.ForegroundColor = Color.black;
            D_GRAPH.colorArrows = new Color(0, 110, 0);
            D_GRAPH.colorGrid = Color.gray;
            D_GRAPH.colorSolver[0] = Color.red;
            D_GRAPH.colorSolver[1] = new Color(220, 200, 0);
            D_GRAPH.colorSolver[2] = new Color(0, 210, 0);
            D_GRAPH.colorSolver[3] = Color.blue;
            D_GRAPH.colorSolver[4] = new Color(150, 0, 255);
            D_GRAPH.colorBorderForeground = Color.black;
            C_TOOL.borderColor = C_TOOL.gray;
        }
        else {
            D_GRAPH.cbox[6].setState(true);
            D_GRAPH.cbox[5].setState(false);
            C_TOOL.ForegroundColor = Color.white;
            D_GRAPH.colorArrows = Color.green;
            D_GRAPH.colorGrid = C_TOOL.gray;
            D_GRAPH.colorSolver[0] = Color.red;
            D_GRAPH.colorSolver[1] = Color.orange;
            D_GRAPH.colorSolver[2] = Color.yellow;
            D_GRAPH.colorSolver[3] = Color.cyan;
            D_GRAPH.colorSolver[4] = Color.magenta;
            D_GRAPH.colorBorderForeground = Color.white;
            C_TOOL.borderColor = C_TOOL.gray;
        }
        D_GRAPH.GRAPH.setBackground(C_TOOL.BackgroundColor);
        D_GRAPH.GRAPH.setForeground(C_TOOL.ForegroundColor);
        D_GRAPH.labelMouse.setBackground(C_TOOL.borderColor);
        D_GRAPH.labelMouse.setForeground(D_GRAPH.colorBorderForeground);
        D_GRAPH.labelInfo.setBackground(Color.red);
        D_GRAPH.labelInfo.setForeground(Color.black);
    }
    
    public static void GraphDF() {
        if (!DFIELD.ShowDF) {
            D_GRAPH.g.myRepaint();
            return;
        }
        final double n = (D_GRAPH.g.xmax - D_GRAPH.g.xmin) / DFIELD.FieldPointsRow;
        final double n2 = (D_GRAPH.g.ymax - D_GRAPH.g.ymin) / DFIELD.FieldPointsColumn;
        final double n3 = n / 4.0;
        final double n4 = n / 8;
        final double n5 = 0.5235987755982988;
        final double n6 = D_GRAPH.g.scaleX * n3;
        final double n7 = D_GRAPH.g.scaleX * n4;
        D_GRAPH.g.Buf.setClip(0, 0, D_GRAPH.g.windowWidth, D_GRAPH.g.windowHeight);
        D_GRAPH.g.Buf.setColor(D_GRAPH.colorArrows);
        for (double n8 = D_GRAPH.g.xmin + n / 2.0; n8 < D_GRAPH.g.xmax - n / 5.0; n8 += n) {
            for (double n9 = D_GRAPH.g.ymin + n2 / 2.0; n9 < D_GRAPH.g.ymax - n2 / 5.0; n9 += n2) {
                final double functionValue = DFIELD.diffyQ.functionValue(n8, n9);
                if (Double.isNaN(functionValue)) {
                    D_GRAPH.g.Buf.setColor(Color.red);
                    D_GRAPH.g.Buf.fillOval(D_GRAPH.g.screenX(n8) - 4, D_GRAPH.g.screenY(n9) - 4, 9, 9);
                    D_GRAPH.g.Buf.setColor(D_GRAPH.colorArrows);
                }
                else {
                    final double atan = Math.atan(functionValue);
                    final double cos = Math.cos(atan);
                    final double sin = Math.sin(atan);
                    final double n10 = n6 / Math.sqrt(D_GRAPH.g.scaleX * D_GRAPH.g.scaleX * cos * cos + D_GRAPH.g.scaleY * D_GRAPH.g.scaleY * sin * sin);
                    final int screenX = D_GRAPH.g.screenX(n8 - cos * n10);
                    final int screenX2 = D_GRAPH.g.screenX(n8 + cos * n10);
                    final int screenY = D_GRAPH.g.screenY(n9 - sin * n10);
                    final int screenY2 = D_GRAPH.g.screenY(n9 + sin * n10);
                    final double atan2 = Math.atan((screenY2 - screenY) / (screenX2 - screenX));
                    final double cos2 = Math.cos(atan2 + n5);
                    final double sin2 = Math.sin(atan2 + n5);
                    final double n11 = screenX2 + (int)Math.round(n7 * cos2);
                    final double n12 = screenY2 + (int)Math.round(n7 * sin2);
                    final double n13 = screenX2 - (int)Math.round(n7 * cos2);
                    final double n14 = screenY2 - (int)Math.round(n7 * sin2);
                    int n15;
                    if ((screenX - n11) * (screenX - n11) + (screenY - n12) * (screenY - n12) < (screenX - n13) * (screenX - n13) + (screenY - n14) * (screenY - n14)) {
                        n15 = 1;
                    }
                    else {
                        n15 = -1;
                    }
                    final int n16 = screenX2 + (int)Math.round(n7 * cos2) * n15;
                    final int n17 = screenY2 + (int)Math.round(n7 * sin2) * n15;
                    final double cos3 = Math.cos(atan2 - n5);
                    final double sin3 = Math.sin(atan2 - n5);
                    final int n18 = screenX2 + (int)Math.round(n7 * cos3) * n15;
                    final int n19 = screenY2 + (int)Math.round(n7 * sin3) * n15;
                    D_GRAPH.g.Buf.drawLine(screenX, screenY, screenX2, screenY2);
                    if (DFIELD.ShowArrows) {
                        D_GRAPH.g.Buf.drawLine(screenX2, screenY2, n16, n17);
                        D_GRAPH.g.Buf.drawLine(screenX2, screenY2, n18, n19);
                    }
                }
            }
        }
        if (!DFIELD.printing) {
            final double atan3 = Math.atan(D_GRAPH.g.scaleY / D_GRAPH.g.scaleX);
            final double n20 = D_GRAPH.domainArrowSide * Math.cos(atan3);
            final double n21 = D_GRAPH.domainArrowSide * Math.sin(atan3);
            final int n22 = D_GRAPH.domainArrowLeft + D_GRAPH.domainArrowSide + (int)Math.round(n20);
            final int n23 = D_GRAPH.domainArrowLeft + D_GRAPH.domainArrowSide - (int)Math.round(n20);
            final int n24 = D_GRAPH.domainArrowTop + D_GRAPH.domainArrowSide - (int)Math.round(n21);
            final int n25 = D_GRAPH.domainArrowTop + D_GRAPH.domainArrowSide + (int)Math.round(n21);
            final double atan4 = Math.atan((n25 - n24) / (n23 - n22));
            final double cos4 = Math.cos(atan4 + n5);
            final double sin4 = Math.sin(atan4 + n5);
            final double n26 = n23 + (int)Math.round(n7 * cos4);
            final double n27 = n25 + (int)Math.round(n7 * sin4);
            final double n28 = n23 - (int)Math.round(n7 * cos4);
            final double n29 = n25 - (int)Math.round(n7 * sin4);
            final int n30 = n22 - (int)Math.round(3 * n7 * cos4);
            final int n31 = n24 - (int)Math.round(3 * n7 * sin4);
            final double cos5 = Math.cos(atan4 - n5);
            final double sin5 = Math.sin(atan4 - n5);
            final int n32 = n22 - (int)Math.round(3 * n7 * cos5);
            final int n33 = n24 - (int)Math.round(3 * n7 * sin5);
            D_GRAPH.g.Buf.setColor(D_GRAPH.colorBorderForeground);
            D_GRAPH.g.Buf.drawLine(n22, n24, n23, n25);
            D_GRAPH.g.Buf.drawLine(n22, n24, n30, n31);
            D_GRAPH.g.Buf.drawLine(n22, n24, n32, n33);
        }
        D_GRAPH.g.myRepaint();
    }
    
    public static void ShowAxis() {
        D_GRAPH.GRAPH.getFontMetrics(C_TOOL.fontBold);
        final FontMetrics fontMetrics = D_GRAPH.GRAPH.getFontMetrics(C_TOOL.fontNormal);
        final int height = fontMetrics.getHeight();
        final int ascent = fontMetrics.getAscent();
        final int stringWidth = fontMetrics.stringWidth("X");
        D_GRAPH.domainArrowSide = height;
        D_GRAPH.domainArrowLeft = D_GRAPH.g.windowWidth - 2 * D_GRAPH.domainArrowSide - 2;
        D_GRAPH.domainArrowTop = 2;
        final String s = "Arrow of slope +1.0";
        final int n = D_GRAPH.g.windowLeft + D_GRAPH.g.windowWidth - fontMetrics.stringWidth(s) - 2 * D_GRAPH.domainArrowSide;
        final int n2 = 1 + height;
        D_GRAPH.g.Buf.setClip(0, 0, D_GRAPH.g.windowWidth, D_GRAPH.g.windowHeight);
        D_GRAPH.g.Buf.setColor(D_GRAPH.colorBorderForeground);
        D_GRAPH.g.graphTop = 2 * height + 1;
        D_GRAPH.g.graphHeight = D_GRAPH.g.windowHeight - D_GRAPH.g.graphTop - (int)(height * 3.3);
        D_GRAPH.g.Buf.setFont(C_TOOL.fontBold);
        final int n3 = D_GRAPH.g.graphTop + D_GRAPH.g.graphHeight / 2;
        final int stringWidth2 = fontMetrics.stringWidth("XX");
        final int stringWidth3 = fontMetrics.stringWidth(String.valueOf(String.valueOf(DFIELD.diffyQ.yName)).concat(" X"));
        D_GRAPH.g.Buf.drawString(DFIELD.diffyQ.yName, stringWidth2, n3);
        D_GRAPH.g.Buf.setFont(C_TOOL.fontNormal);
        final dataAXIS findAxisStep = C_TOOL.FindAxisStep(D_GRAPH.g.ymin, D_GRAPH.g.ymax, D_GRAPH.g.graphHeight / (2 * height));
        final int n4 = stringWidth2 + stringWidth3;
        int n5 = 0;
        double start = findAxisStep.start;
        for (int i = 0; i <= findAxisStep.count; ++i) {
            final int stringWidth4 = fontMetrics.stringWidth(C_TOOL.AxisValueToString(start, findAxisStep.step));
            if (stringWidth4 > n5) {
                n5 = stringWidth4;
            }
            start += findAxisStep.step;
        }
        D_GRAPH.g.graphLeft = n4 + n5 + 5;
        D_GRAPH.g.graphWidth = D_GRAPH.g.windowWidth - D_GRAPH.g.graphLeft - fontMetrics.stringWidth(C_TOOL.AxisValueToString(D_GRAPH.g.xmax, findAxisStep.step)) / 2 - stringWidth2;
        final int n6 = stringWidth * 5;
        final int n7 = D_GRAPH.g.graphWidth / n6;
        int n8 = 0;
        dataAXIS dataAXIS = C_TOOL.FindAxisStep(D_GRAPH.g.xmin, D_GRAPH.g.xmax, n7);
        for (int j = 0; j <= dataAXIS.count; ++j) {
            final int stringWidth5 = fontMetrics.stringWidth(C_TOOL.AxisValueToString(j * dataAXIS.step, dataAXIS.step));
            if (stringWidth5 > n8) {
                n8 = stringWidth5;
            }
        }
        if (n8 > n6) {
            dataAXIS = C_TOOL.FindAxisStep(D_GRAPH.g.xmin, D_GRAPH.g.xmax, D_GRAPH.g.graphWidth / n8);
        }
        D_GRAPH.g.Buf.setColor(C_TOOL.BackgroundColor);
        D_GRAPH.g.Buf.fillRect(D_GRAPH.g.graphLeft, D_GRAPH.g.graphTop, D_GRAPH.g.graphWidth, D_GRAPH.g.graphHeight);
        final int graphLeft = D_GRAPH.g.graphLeft;
        D_GRAPH.labelInfo.setBounds(graphLeft, D_GRAPH.g.windowTop + 5, n - graphLeft - 10, height);
        D_GRAPH.g.Buf.setFont(C_TOOL.fontNormal);
        D_GRAPH.g.Buf.setColor(D_GRAPH.colorBorderForeground);
        final int n9 = 5 + ascent;
        final int stringWidth6 = fontMetrics.stringWidth(D_GRAPH.equationString);
        final int n10 = D_GRAPH.g.graphLeft + D_GRAPH.g.graphWidth / 2 - stringWidth6 / 2;
        D_GRAPH.g.Buf.drawString(D_GRAPH.equationString, n10, n9);
        if (!DFIELD.printing && n > n10 + stringWidth6 + stringWidth * 3) {
            D_GRAPH.g.Buf.setFont(C_TOOL.fontNormal);
            D_GRAPH.g.Buf.setColor(D_GRAPH.colorBorderForeground);
            D_GRAPH.g.Buf.drawString(s, n, n2);
        }
        D_GRAPH.g.Buf.setFont(C_TOOL.fontNormal);
        D_GRAPH.g.scaleX = D_GRAPH.g.graphWidth / (D_GRAPH.g.xmax - D_GRAPH.g.xmin);
        D_GRAPH.g.scaleY = D_GRAPH.g.graphHeight / (D_GRAPH.g.ymax - D_GRAPH.g.ymin);
        double start2 = findAxisStep.start;
        for (int k = 0; k <= findAxisStep.count; ++k) {
            final int screenY = D_GRAPH.g.screenY(start2);
            D_GRAPH.g.Buf.setColor(D_GRAPH.colorBorderForeground);
            D_GRAPH.g.Buf.drawLine(D_GRAPH.g.graphLeft - 3, screenY, D_GRAPH.g.graphLeft + 1, screenY);
            D_GRAPH.g.Buf.drawString(C_TOOL.AxisValueToString(start2, findAxisStep.step), n4, screenY + ascent / 2);
            D_GRAPH.g.Buf.setColor(D_GRAPH.colorGrid);
            for (int l = D_GRAPH.g.graphLeft; l < D_GRAPH.g.graphLeft + D_GRAPH.g.graphWidth; l += 5) {
                D_GRAPH.g.Buf.drawLine(l, screenY, l + 1, screenY);
            }
            start2 += findAxisStep.step;
        }
        final int n11 = (int)(height * 1.3);
        final int n12 = D_GRAPH.g.windowTop + D_GRAPH.g.windowHeight - height - 6;
        final int stringWidth7 = fontMetrics.stringWidth("  Stop  ");
        D_GRAPH.g.StopButton.setBounds(D_GRAPH.g.windowLeft + D_GRAPH.g.windowWidth - stringWidth7 - 10, n12, stringWidth7, height + 4);
        final int n13 = stringWidth * 23;
        final int n14 = stringWidth * 2;
        D_GRAPH.labelMouse.setBounds(n14, n12, n13, n11);
        int max = D_GRAPH.g.graphLeft + D_GRAPH.g.graphWidth / 2 - fontMetrics.stringWidth(String.valueOf(String.valueOf(DFIELD.diffyQ.xName)).concat(" ")) / 2;
        if (DFIELD.printing) {
            max = Math.max(max, n14 + n13 + stringWidth);
        }
        D_GRAPH.g.Buf.setColor(D_GRAPH.colorBorderForeground);
        D_GRAPH.g.Buf.setFont(C_TOOL.fontBold);
        D_GRAPH.g.Buf.drawString(DFIELD.diffyQ.xName, max, D_GRAPH.g.windowHeight - height / 2);
        D_GRAPH.g.Buf.setFont(C_TOOL.fontNormal);
        double start3 = dataAXIS.start;
        final int n15 = D_GRAPH.g.graphTop + D_GRAPH.g.graphHeight + height + 3;
        for (int n16 = 0; n16 <= dataAXIS.count; ++n16) {
            final int screenX = D_GRAPH.g.screenX(start3);
            final String axisValueToString = C_TOOL.AxisValueToString(start3, dataAXIS.step);
            final int n17 = screenX - fontMetrics.stringWidth(axisValueToString) / 2;
            final int n18 = n15;
            D_GRAPH.g.Buf.setColor(D_GRAPH.colorBorderForeground);
            D_GRAPH.g.Buf.drawString(axisValueToString, n17, n18);
            D_GRAPH.g.Buf.drawLine(screenX, n18 - height, screenX, D_GRAPH.g.graphTop + D_GRAPH.g.graphHeight - 1);
            D_GRAPH.g.Buf.setColor(D_GRAPH.colorGrid);
            for (int graphTop = D_GRAPH.g.graphTop; graphTop < D_GRAPH.g.graphTop + D_GRAPH.g.graphHeight; graphTop += 5) {
                D_GRAPH.g.Buf.drawLine(screenX, graphTop, screenX, graphTop + 1);
            }
            start3 += dataAXIS.step;
        }
        D_GRAPH.g.Buf.setColor(D_GRAPH.colorBorderForeground);
        D_GRAPH.g.Buf.drawRect(D_GRAPH.g.graphLeft, D_GRAPH.g.graphTop, D_GRAPH.g.graphWidth, D_GRAPH.g.graphHeight);
    }
    
    public static void ClearAll() {
        DFIELD.orbit.count = -1;
        DFIELD.orbit.dataFree = 0;
        D_GRAPH.zoomCount = 0;
        InfoLabelOff();
        D_GRAPH.TextAnnotationIndex = 0;
        C_MSG.msgClear();
    }
    
    public static void DeleteAllOrbits() {
        if (D_GRAPH.g.StopButton.isEnabled()) {
            StopSolver();
        }
        DFIELD.orbit.count = -1;
        DFIELD.orbit.dataFree = 0;
        ReDrawEverything();
    }
    
    public static void AddTextModeOn() {
        InfoLabelOff();
        final String s = "Enter text, click OK, then click in the Direction Field Window where you want the Lower Left corner of the text to appear.";
        final dataSample dataSample = new dataSample();
        new C_DIALOG(D_GRAPH.GRAPH, s, dataSample).show();
        if (!dataSample.str.equals("")) {
            D_GRAPH.newText = dataSample.str;
            D_GRAPH.addTextMode = true;
            D_GRAPH.labelInfo.setText("Click where you want the text to appear.");
            D_GRAPH.labelInfo.setVisible(true);
        }
    }
    
    public static void EraseText(final int n) {
        if (n == -1) {
            D_GRAPH.TextAnnotationIndex = 0;
        }
        else {
            if (D_GRAPH.TextAnnotationIndex == 0) {
                return;
            }
            --D_GRAPH.TextAnnotationIndex;
        }
        ReDrawEverything();
    }
    
    public static void ReDrawEverything() {
        if (D_GRAPH.g.StopButton.isEnabled()) {
            StopSolver();
        }
        D_GRAPH.labelMouse.setText("Rendering graph...");
        D_GRAPH.g.Buf.setClip(0, 0, D_GRAPH.g.windowWidth, D_GRAPH.g.windowHeight);
        D_GRAPH.g.Buf.setColor(C_TOOL.borderColor);
        D_GRAPH.g.Buf.fillRect(0, 0, D_GRAPH.g.windowWidth, D_GRAPH.g.windowHeight);
        ShowAxis();
        GraphDF();
        for (int i = 1; i <= D_GRAPH.TextAnnotationIndex; ++i) {
            DisplayText(i);
        }
        D_GRAPH.g.Buf.setClip(D_GRAPH.g.graphLeft, D_GRAPH.g.graphTop, D_GRAPH.g.graphWidth, D_GRAPH.g.graphHeight);
        D_GRAPH.g.Scr.setClip(D_GRAPH.g.graphLeft, D_GRAPH.g.graphTop, D_GRAPH.g.graphWidth, D_GRAPH.g.graphHeight);
        for (int j = 0; j <= DFIELD.orbit.count; ++j) {
            D_GRAPH.g.Buf.setColor(D_GRAPH.colorSolver[DFIELD.orbit.solver[j]]);
            final int screenX = D_GRAPH.g.screenX(DFIELD.orbit.initialX[j]);
            final int screenY = D_GRAPH.g.screenY(DFIELD.orbit.initialY[j]);
            if (D_GRAPH.g.pointType == 1) {
                D_GRAPH.g.Buf.fillOval(screenX - 3, screenY - 3, 6, 6);
            }
            else {
                D_GRAPH.g.Buf.drawOval(screenX - 2, screenY - 2, 4, 4);
            }
            D_GRAPH.g.plotXY(DFIELD.orbit.startPointer[j], DFIELD.orbit.endPointer[j], false, DFIELD.orbit);
            D_GRAPH.g.myRepaint();
        }
        D_GRAPH.labelMouse.setText("Done.");
    }
    
    public static void ReCalculate() {
        if (DFIELD.solverThreadActive) {
            StopSolver();
        }
        D_GRAPH.g.Buf.setClip(0, 0, D_GRAPH.g.windowWidth, D_GRAPH.g.windowHeight);
        D_GRAPH.g.Buf.setColor(C_TOOL.borderColor);
        D_GRAPH.g.Buf.fillRect(0, 0, D_GRAPH.g.windowWidth, D_GRAPH.g.windowHeight);
        ShowAxis();
        GraphDF();
        for (int i = 1; i <= D_GRAPH.TextAnnotationIndex; ++i) {
            DisplayText(i);
        }
        StartUpSolver(0, DFIELD.orbit.count);
    }
    
    public static void InfoLabelOff() {
        D_GRAPH.deleteOrbitMode = false;
        D_GRAPH.addTextMode = false;
        D_GRAPH.labelInfo.setVisible(false);
    }
    
    public static void SetZoomMode(final int n) {
        InfoLabelOff();
        D_GRAPH.cbox[28].setState(false);
        D_GRAPH.cbox[27].setState(false);
        D_GRAPH.cbox[29].setState(false);
        D_GRAPH.cbox[n].setState(true);
        String s = "";
        if (n == 27) {
            s = "To zoom in, hold down the Shift key.  While the Shift key is depressed, move, click, drag, and release the mouse to define a Zoom Rectangle.  You can do this at any time.";
        }
        else if (n == 28) {
            s = "To zoom in, hold down the Shift key.  While the Shift key is depressed, click the mouse on a point within the phase plane for 2x Magnification about that point.  You can do this at any time.";
        }
        else if (n == 29) {
            s = "To zoom in, hold down the Shift key.  While the Shift key is depressed, click the mouse on a point within the direction field to zoom in on that point and make it the center of a square domain.  You can do this at any time.";
        }
        new C_DIALOG(D_GRAPH.GRAPH, s).show();
    }
    
    public static void DeleteOrbitModeOn() {
        D_GRAPH.deleteOrbitMode = true;
        D_GRAPH.labelInfo.setText("Click on an orbit to delete it.");
        D_GRAPH.labelInfo.setVisible(true);
    }
    
    public static void ZoomNow(final double n, final double n2) {
        D_GRAPH.zooming = false;
        boolean b = false;
        if (D_GRAPH.cbox[27].getState()) {
            if ((D_GRAPH.g.xmax00 - D_GRAPH.g.xmin00) / Math.abs(D_GRAPH.zoomInX1 - n) > 10000) {
                b = true;
            }
            if ((D_GRAPH.g.ymax00 - D_GRAPH.g.ymin00) / Math.abs(D_GRAPH.zoomInY1 - n2) > 10000) {
                b = true;
            }
        }
        else if ((D_GRAPH.g.xmax00 - D_GRAPH.g.xmin00) / (D_GRAPH.g.xmax - D_GRAPH.g.xmin) > 5000) {
            b = true;
        }
        if (b) {
            new C_DIALOG(D_GRAPH.GRAPH, "Cannot zoom in more than 10,000x.").show();
            return;
        }
        ++D_GRAPH.zoomCount;
        D_GRAPH.zoomXmin[D_GRAPH.zoomCount] = D_GRAPH.g.xmin;
        D_GRAPH.zoomXmax[D_GRAPH.zoomCount] = D_GRAPH.g.xmax;
        D_GRAPH.zoomYmin[D_GRAPH.zoomCount] = D_GRAPH.g.ymin;
        D_GRAPH.zoomYmax[D_GRAPH.zoomCount] = D_GRAPH.g.ymax;
        if (D_GRAPH.cbox[27].getState()) {
            if (D_GRAPH.zoomInX1 < n) {
                D_GRAPH.g.xmin = D_GRAPH.zoomInX1;
                D_GRAPH.g.xmax = n;
            }
            else {
                D_GRAPH.g.xmax = D_GRAPH.zoomInX1;
                D_GRAPH.g.xmin = n;
            }
            if (D_GRAPH.zoomInY1 < n2) {
                D_GRAPH.g.ymin = D_GRAPH.zoomInY1;
                D_GRAPH.g.ymax = n2;
            }
            else {
                D_GRAPH.g.ymax = D_GRAPH.zoomInY1;
                D_GRAPH.g.ymin = n2;
            }
        }
        else if (D_GRAPH.cbox[28].getState()) {
            D_GRAPH.g.xmax = n + (D_GRAPH.g.xmax - n) / 2.0;
            D_GRAPH.g.ymax = n2 + (D_GRAPH.g.ymax - n2) / 2.0;
            D_GRAPH.g.xmin = n - (n - D_GRAPH.g.xmin) / 2.0;
            D_GRAPH.g.ymin = n2 - (n2 - D_GRAPH.g.ymin) / 2.0;
        }
        else {
            final double n3 = Math.min(D_GRAPH.g.xmax - D_GRAPH.g.xmin, D_GRAPH.g.ymax - D_GRAPH.g.ymin) / 4.0;
            D_GRAPH.g.xmax = n + n3;
            D_GRAPH.g.ymax = n2 + n3;
            D_GRAPH.g.xmin = n - n3;
            D_GRAPH.g.ymin = n2 - n3;
        }
        ReDrawEverything();
    }
    
    public static int GetIndexofSiblingOrbit(final int n) {
        final int n2 = n + DFIELD.orbit.direction[n];
        if (DFIELD.orbit.initialX[n] == DFIELD.orbit.initialX[n2] && DFIELD.orbit.initialY[n] == DFIELD.orbit.initialY[n2] && DFIELD.orbit.direction[n] * DFIELD.orbit.direction[n2] == -1) {
            return n2;
        }
        return -1;
    }
    
    public static void PlotNewSolution(final double n, final double n2, final double n3, final double n4) {
        final int n5 = DFIELD.orbit.count + 1;
        if (n < n4) {
            AddOrbit(n, n2, 1, true, n3, n4);
        }
        if (n > n3) {
            AddOrbit(n, n2, -1, true, n3, n4);
        }
        StartUpSolver(n5, DFIELD.orbit.count);
    }
    
    public static void PlotNewSolution(final double n, final double n2) {
        final int n3 = DFIELD.orbit.count + 1;
        if (D_GRAPH.cbox[23].getState()) {
            AddOrbit(n, n2, 1);
            AddOrbit(n, n2, -1);
        }
        else if (D_GRAPH.cbox[24].getState()) {
            AddOrbit(n, n2, 1);
        }
        else {
            AddOrbit(n, n2, -1);
        }
        StartUpSolver(n3, DFIELD.orbit.count);
    }
    
    public static void AddOrbit(final double n, final double n2, final int n3) {
        AddOrbit(n, n2, n3, false, -1.7976931348623157E308, Double.MAX_VALUE);
    }
    
    public static void AddOrbit(final double n, final double n2, final int n3, final boolean b, final double n4, final double n5) {
        final dataORBIT orbit = DFIELD.orbit;
        ++orbit.count;
        DFIELD.orbit.initialX[DFIELD.orbit.count] = n;
        DFIELD.orbit.initialY[DFIELD.orbit.count] = n2;
        DFIELD.orbit.direction[DFIELD.orbit.count] = n3;
        DFIELD.orbit.specifyRange[DFIELD.orbit.count] = b;
        DFIELD.orbit.rangeMin[DFIELD.orbit.count] = n4;
        DFIELD.orbit.rangeMax[DFIELD.orbit.count] = n5;
        if (D_GRAPH.cbox[0].getState()) {
            DFIELD.orbit.solver[DFIELD.orbit.count] = 0;
        }
        else if (D_GRAPH.cbox[1].getState()) {
            DFIELD.orbit.solver[DFIELD.orbit.count] = 1;
        }
        else if (D_GRAPH.cbox[2].getState()) {
            DFIELD.orbit.solver[DFIELD.orbit.count] = 2;
        }
        else if (D_GRAPH.cbox[3].getState()) {
            DFIELD.orbit.solver[DFIELD.orbit.count] = 3;
        }
        else {
            DFIELD.orbit.solver[DFIELD.orbit.count] = 4;
        }
    }
    
    public static void StartUpSolver(final int indexStart, final int indexEnd) {
        D_GRAPH.g.Buf.setClip(D_GRAPH.g.graphLeft, D_GRAPH.g.graphTop, D_GRAPH.g.graphWidth, D_GRAPH.g.graphHeight);
        D_GRAPH.g.Scr.setClip(D_GRAPH.g.graphLeft, D_GRAPH.g.graphTop, D_GRAPH.g.graphWidth, D_GRAPH.g.graphHeight);
        D_GRAPH.SolverThread = new D_SOLVER();
        D_SOLVER.g = D_GRAPH.g;
        D_SOLVER.orbit = DFIELD.orbit;
        D_GRAPH.SolverThread.indexStart = indexStart;
        D_GRAPH.SolverThread.indexEnd = indexEnd;
        DFIELD.solverThreadActive = true;
        D_GRAPH.g.StopButton.setVisible(true);
        D_GRAPH.labelMouse.setText("Solving ODE...");
        D_GRAPH.SolverThread.start();
    }
    
    public static void StopSolver() {
        D_GRAPH.SolverThread.stop();
        D_GRAPH.g.StopButton.setVisible(false);
        C_MSG.MSG_append("\n******** Solver was interrupted.\n", DFIELD.solverThreadActive = false);
        D_GRAPH.labelMouse.setText("Interrupted.");
    }
    
    public static double sign(final double n) {
        if (n < 0.0) {
            return -1.0;
        }
        if (n > 0.0) {
            return 1.0;
        }
        return 0.0;
    }
    
    public static double x_from_screen(final int n) {
        return D_GRAPH.g.xmin + (n - D_GRAPH.g.graphLeft - D_GRAPH.g.windowLeft) / D_GRAPH.g.scaleX;
    }
    
    public static double y_from_screen(final int n) {
        return D_GRAPH.g.ymin + (D_GRAPH.g.graphHeight + D_GRAPH.g.graphTop + D_GRAPH.g.windowTop - n) / D_GRAPH.g.scaleY;
    }
    
    public static void DisplayText(final int n) {
        D_GRAPH.g.Buf.setClip(0, 0, D_GRAPH.g.windowWidth, D_GRAPH.g.windowHeight);
        D_GRAPH.g.Buf.setFont(C_TOOL.fontSmall);
        final FontMetrics fontMetrics = D_GRAPH.GRAPH.getFontMetrics(C_TOOL.fontSmall);
        final int height = fontMetrics.getHeight();
        final int descent = fontMetrics.getDescent();
        final int stringWidth = fontMetrics.stringWidth(D_GRAPH.TextAnnotation[n]);
        final int screenX = D_GRAPH.g.screenX(D_GRAPH.TextAnnotationX[n]);
        final int screenY = D_GRAPH.g.screenY(D_GRAPH.TextAnnotationY[n]);
        D_GRAPH.g.Buf.setColor(C_TOOL.BackgroundColor);
        D_GRAPH.g.Buf.fillRect(screenX, screenY - (height - descent), stringWidth, height);
        D_GRAPH.g.Buf.setColor(C_TOOL.ForegroundColor);
        D_GRAPH.g.Buf.drawString(D_GRAPH.TextAnnotation[n], screenX, screenY);
    }
    
    public void paint(final Graphics graphics) {
        if (D_GRAPH.drawConstantsUndefined) {
            return;
        }
        final Dimension size = D_GRAPH.GRAPH.getSize();
        if (D_GRAPH.windowX != size.width || D_GRAPH.windowY != size.height) {
            SetDrawConstants();
            ReDrawEverything();
        }
        D_GRAPH.g.myRepaint();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    static {
        D_GRAPH.GRAPH = null;
        D_GRAPH.drawConstantsUndefined = true;
        D_GRAPH.mu = new Menu[10];
        D_GRAPH.muName = new String[10];
        D_GRAPH.mi = new MenuItem[12];
        D_GRAPH.miName = new String[12];
        D_GRAPH.cbox = new CheckboxMenuItem[31];
        D_GRAPH.cboxName = new String[31];
        D_GRAPH.TextAnnotation = new String[50];
        D_GRAPH.TextAnnotationX = new double[50];
        D_GRAPH.TextAnnotationY = new double[50];
        D_GRAPH.newText = "";
        D_GRAPH.zoomXmin = new double[100];
        D_GRAPH.zoomXmax = new double[100];
        D_GRAPH.zoomYmin = new double[100];
        D_GRAPH.zoomYmax = new double[100];
        D_GRAPH.colorSolver = new Color[5];
    }
}
