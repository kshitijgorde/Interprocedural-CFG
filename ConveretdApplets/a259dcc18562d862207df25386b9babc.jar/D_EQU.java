import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.awt.MenuShortcut;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Toolkit;
import java.awt.MenuComponent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.event.TextEvent;
import java.awt.event.WindowEvent;
import java.awt.MenuItem;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.Checkbox;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Label;
import java.awt.event.TextListener;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class D_EQU extends Frame implements WindowListener, ActionListener, TextListener
{
    public static D_EQU EQU;
    public static dataGraphConstants g;
    public static int windowEquX;
    public static int windowEquY;
    public static int MenuHeight;
    public static Label independentVarL;
    public static final int paramCount = 4;
    public static int errorType;
    public static int errorLocation;
    public static int errorIndex;
    public static String errorStr;
    public static TextField var1minE;
    public static TextField var1maxE;
    public static TextField var2minE;
    public static TextField var2maxE;
    public static TextField equ1E;
    public static TextField variable1E;
    public static TextField variable2E;
    public static TextField[] knameE;
    public static TextField[] kvalueE;
    public static Label parmL;
    public static Label displayL;
    public static Label variable1L;
    public static Label primeEqual1L;
    public static Label equL;
    public static Label errPointer;
    public static Label errMessage;
    public static Label[] kequalL;
    public static Label var1minL;
    public static Label var1maxL;
    public static Label var2minL;
    public static Label var2maxL;
    public static Button buttonGraph;
    public static int errPointerT1;
    public static int errMessageT1;
    public static int errPointerL;
    public static int line1y;
    public static int line2x;
    public static int fontH;
    public static int fontW;
    public static int boxW;
    public static int boxH;
    public static int rowH;
    public static int windowW;
    public static int windowH;
    public static boolean drawConstantsUndefined;
    public static Checkbox initialValue;
    public static final String defaultTitle = "First-order Differential Equation in the form: x´ = f(t,x)";
    public static boolean predefinedSystem;
    public static String predefinedEqu1;
    public static String predefinedEqu2;
    public static String predefinedK0;
    public static String predefinedK1;
    public static String predefinedK2;
    public static String predefinedK3;
    public static MenuBar myMenu;
    public static final int muFile = 0;
    public static final int muEdit = 1;
    public static final int muWindow = 2;
    public static final int muGallery = 3;
    public static final int muRemoveFromGallery = 4;
    public static final int muHelp = 5;
    public static final int last_mu = 5;
    public static Menu[] mu;
    public static String[] muName;
    public static final int miPolking = 0;
    public static final int miProjectile = 1;
    public static final int miAdd = 2;
    public static final int miSyntax = 3;
    public static final int miAbout = 4;
    public static final int miClearEquations = 5;
    public static final int miClearParameters = 6;
    public static final int miClearDisplay = 7;
    public static final int miClearAll = 8;
    public static final int miGraph = 9;
    public static final int miQuit = 10;
    public static final int last_mi = 10;
    public static MenuItem[] mi;
    public static String[] miName;
    private static String[] userName;
    private static String[] userXname;
    private static String[] userYname;
    private static String[] userEqu1;
    private static String[][] userKname;
    private static String[][] userKequ;
    private static double[] userXmin;
    private static double[] userXmax;
    private static double[] userYmin;
    private static double[] userYmax;
    private static MenuItem[] userMenu;
    private static MenuItem[] userMenuDelete;
    public static int userCount;
    private static double xmin;
    private static double xmax;
    private static double ymin;
    private static double ymax;
    private static String xName;
    private static String yName;
    private static String[] kName;
    public static C_INFIX diffyQ1;
    public static C_INFIX[] EQU_K;
    
    public void windowClosing(final WindowEvent windowEvent) {
        DFIELD.quitDFIELD();
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
        if (DFIELD.orbit.count >= 0) {
            D_EQU.initialValue.setEnabled(true);
        }
        else {
            D_EQU.initialValue.setEnabled(false);
        }
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void textValueChanged(final TextEvent textEvent) {
        if (D_EQU.predefinedSystem && (!D_EQU.equ1E.getText().equals(D_EQU.predefinedEqu1) || !D_EQU.kvalueE[0].getText().equals(D_EQU.predefinedK0) || !D_EQU.kvalueE[1].getText().equals(D_EQU.predefinedK1) || !D_EQU.kvalueE[2].getText().equals(D_EQU.predefinedK2) || !D_EQU.kvalueE[3].getText().equals(D_EQU.predefinedK3))) {
            D_EQU.equL.setText("First-order Differential Equation in the form: x´ = f(t,x)");
            D_EQU.predefinedSystem = false;
        }
        displayError(checkEditFields());
    }
    
    public static void QuitD_EQU() {
        if (D_EQU.EQU == null) {
            return;
        }
        SaveGallery();
        D_EQU.EQU.dispose();
        D_EQU.EQU = null;
    }
    
    public static void EQU_MAIN(final D_EQU equ) {
        D_EQU.EQU = equ;
        D_EQU.g = DFIELD.directionFieldConstants;
        D_EQU.EQU.setResizable(true);
        D_EQU.EQU.setTitle("DFIELD Equation Window");
        D_EQU.EQU.setBackground(C_TOOL.borderColor);
        D_EQU.EQU.setForeground(Color.black);
        D_EQU.EQU.addWindowListener(D_EQU.EQU);
        D_EQU.EQU.setLayout(null);
        D_EQU.independentVarL = addLabel("Independent Variable:");
        D_EQU.variable1E = addEdit("");
        D_EQU.variable2E = addEdit("");
        D_EQU.equL = addLabel("");
        D_EQU.equ1E = addEdit("");
        D_EQU.primeEqual1L = addLabel("´ =");
        (D_EQU.errMessage = addLabel("")).setForeground(Color.red);
        for (int i = 0; i < 4; ++i) {
            D_EQU.knameE[i] = addEdit("");
            D_EQU.kvalueE[i] = addEdit("");
            D_EQU.kequalL[i] = addLabel("=");
        }
        D_EQU.parmL = addLabel("Parameter expressions:");
        D_EQU.displayL = addLabel("The Display Window:");
        D_EQU.var1minL = addLabel("");
        D_EQU.var1maxL = addLabel("");
        D_EQU.var2minL = addLabel("");
        D_EQU.var2maxL = addLabel("");
        D_EQU.var1minE = addEdit("");
        D_EQU.var1maxE = addEdit("");
        D_EQU.var2minE = addEdit("");
        D_EQU.var2maxE = addEdit("");
        setPolkingDefults();
        D_EQU.buttonGraph = new Button("Graph Phase Plane");
        D_EQU.initialValue = new Checkbox("Use current initial values in new graph", false);
        D_EQU.EQU.add(D_EQU.initialValue);
        D_EQU.EQU.add(D_EQU.buttonGraph);
        D_EQU.buttonGraph.addActionListener(D_EQU.EQU);
        D_EQU.errMessage.setVisible(false);
        D_EQU.initialValue.setBackground(C_TOOL.borderColor);
        D_EQU.initialValue.setForeground(Color.black);
        D_EQU.EQU.setBounds(0, 0, 400, 200);
        D_EQU.EQU.show();
        C_TOOL.windowTopNoMenu = D_EQU.EQU.getInsets().top;
        C_TOOL.windowBottom = D_EQU.EQU.getInsets().bottom;
        C_TOOL.windowLeft = D_EQU.EQU.getInsets().left;
        C_TOOL.windowRight = D_EQU.EQU.getInsets().right;
        D_EQU.diffyQ1 = new C_INFIX();
        for (int j = 0; j < 4; ++j) {
            D_EQU.EQU_K[j] = new C_INFIX();
        }
        MenuInit();
        LoadGallery();
        D_EQU.EQU.setMenuBar(D_EQU.myMenu);
        SetDefaultFontMextrix();
        SetDrawConstants();
        checkEditFields();
    }
    
    public static Label addLabel(final String s) {
        final Label label = new Label(s, 0);
        D_EQU.EQU.add(label);
        label.setBackground(C_TOOL.borderColor);
        label.setForeground(Color.black);
        return label;
    }
    
    public static TextField addEdit(final String s) {
        final TextField textField = new TextField(s, 30);
        D_EQU.EQU.add(textField);
        textField.setBackground(Color.white);
        textField.setForeground(Color.black);
        textField.setEditable(true);
        textField.addTextListener(D_EQU.EQU);
        return textField;
    }
    
    public static void resizedWindow() {
        final int n = D_EQU.windowW - D_EQU.EQU.getInsets().left - D_EQU.EQU.getInsets().right;
        final int n2 = D_EQU.windowH - D_EQU.EQU.getInsets().bottom - D_EQU.EQU.getInsets().top;
        D_EQU.fontW = n / 90;
        D_EQU.rowH = n2 / 12;
        D_EQU.boxW = D_EQU.fontW * 11;
        D_EQU.boxH = D_EQU.rowH - 3;
        C_TOOL.boxHeight = D_EQU.boxH;
        SetDrawConstants();
    }
    
    public static void SetDefaultFontMextrix() {
        final FontMetrics fontMetrics = D_EQU.EQU.getFontMetrics(C_TOOL.fontNormal);
        C_TOOL.fontHeight = fontMetrics.getLeading() + fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent();
        C_TOOL.boxHeight = C_TOOL.fontHeight + 11;
        C_TOOL.fontWidth = fontMetrics.stringWidth("X");
        D_EQU.fontH = C_TOOL.fontHeight;
        D_EQU.fontW = C_TOOL.fontWidth;
        D_EQU.boxH = C_TOOL.boxHeight;
        D_EQU.boxW = D_EQU.fontW * 11;
        D_EQU.rowH = D_EQU.boxH + 3;
        D_EQU.variable1E.setFont(C_TOOL.fontNormal);
        D_EQU.variable2E.setFont(C_TOOL.fontNormal);
        D_EQU.equL.setFont(C_TOOL.fontNormal);
        D_EQU.equ1E.setFont(C_TOOL.fontNormal);
        D_EQU.primeEqual1L.setFont(C_TOOL.fontNormal);
        D_EQU.errMessage.setFont(C_TOOL.fontBold);
        for (int i = 0; i < 4; ++i) {
            D_EQU.knameE[i].setFont(C_TOOL.fontNormal);
            D_EQU.kvalueE[i].setFont(C_TOOL.fontNormal);
            D_EQU.kequalL[i].setFont(C_TOOL.fontNormal);
        }
        D_EQU.parmL.setFont(C_TOOL.fontNormal);
        D_EQU.displayL.setFont(C_TOOL.fontNormal);
        D_EQU.var1minL.setFont(C_TOOL.fontNormal);
        D_EQU.var1maxL.setFont(C_TOOL.fontNormal);
        D_EQU.var2minL.setFont(C_TOOL.fontNormal);
        D_EQU.var2maxL.setFont(C_TOOL.fontNormal);
        D_EQU.var2minE.setFont(C_TOOL.fontNormal);
        D_EQU.var2maxE.setFont(C_TOOL.fontNormal);
        D_EQU.var1minE.setFont(C_TOOL.fontNormal);
        D_EQU.var1maxE.setFont(C_TOOL.fontNormal);
        D_EQU.initialValue.setFont(C_TOOL.fontNormal);
        D_EQU.buttonGraph.setFont(C_TOOL.fontNormal);
        final MenuBar menuBar = D_EQU.EQU.getMenuBar();
        if (menuBar != null) {
            D_EQU.EQU.remove(menuBar);
        }
        D_EQU.myMenu.setFont(C_TOOL.fontNormal);
        for (int j = 0; j <= 5; ++j) {
            D_EQU.mu[j].setFont(C_TOOL.fontNormal);
        }
        for (int k = 0; k <= 10; ++k) {
            D_EQU.mi[k].setFont(C_TOOL.fontNormal);
        }
        for (int l = 0; l <= D_EQU.userCount; ++l) {
            D_EQU.userMenu[l].setFont(C_TOOL.fontNormal);
            D_EQU.userMenuDelete[l].setFont(C_TOOL.fontNormal);
        }
        D_EQU.EQU.setMenuBar(D_EQU.myMenu);
        D_EQU.windowW = D_EQU.fontW * 80 + D_EQU.EQU.getInsets().left + D_EQU.EQU.getInsets().right;
        D_EQU.EQU.setBounds(0, 0, D_EQU.windowW, 200);
        D_EQU.windowH = D_EQU.rowH * 12 + D_EQU.EQU.getInsets().bottom + D_EQU.EQU.getInsets().top;
        D_EQU.EQU.setBounds(0, Toolkit.getDefaultToolkit().getScreenSize().height - D_EQU.windowH - D_EQU.EQU.getInsets().top, D_EQU.windowW, D_EQU.windowH);
        final Dimension size = D_EQU.EQU.getSize();
        D_EQU.windowW = size.width;
        D_EQU.windowH = size.height;
    }
    
    public static void SetDrawConstants() {
        final int n = D_EQU.EQU.getInsets().left + D_EQU.fontW;
        final int top = D_EQU.EQU.getInsets().top;
        final int n2 = D_EQU.windowW - D_EQU.EQU.getInsets().left - D_EQU.EQU.getInsets().right;
        final int n3 = n + D_EQU.boxW + 1;
        final int n4 = D_EQU.fontW * 3;
        final int n5 = n3 + n4;
        final int n6 = n2 - n5;
        final int n7 = D_EQU.fontW * 20;
        final int n8 = n + n7;
        final int n9 = top + D_EQU.rowH - D_EQU.fontH - 1;
        final int n10 = top + D_EQU.rowH;
        final int n11 = top + D_EQU.rowH * 2;
        final int n12 = top + D_EQU.rowH * 3;
        D_EQU.equL.setBounds(n5, n9, n6, D_EQU.fontH);
        D_EQU.variable1E.setBounds(n, n10, D_EQU.boxW, D_EQU.boxH);
        D_EQU.independentVarL.setBounds(n, n11, n7, D_EQU.boxH);
        D_EQU.variable2E.setBounds(n8, n11, D_EQU.boxW, D_EQU.boxH);
        D_EQU.primeEqual1L.setBounds(n3, n10, n4, D_EQU.boxH);
        D_EQU.equ1E.setBounds(n5, n10, n6, D_EQU.boxH);
        D_EQU.errMessage.setBounds(n, n12, n2, D_EQU.boxH);
        final int n13 = n + D_EQU.boxW + D_EQU.fontW;
        final int n14 = n13 + D_EQU.fontW * 2;
        final int n15 = top + D_EQU.rowH * 4;
        final int n16 = top + D_EQU.rowH * 5;
        final int n17 = n2 / 2 - D_EQU.boxW - D_EQU.fontW * 5;
        D_EQU.parmL.setBounds(n, n15, n14 + n17, D_EQU.boxH);
        for (int i = 0; i < 4; ++i) {
            D_EQU.knameE[i].setBounds(n, n16 + D_EQU.rowH * i, D_EQU.boxW, D_EQU.boxH);
            D_EQU.kequalL[i].setBounds(n13, n16 + D_EQU.rowH * i, D_EQU.fontW, D_EQU.boxH);
            D_EQU.kvalueE[i].setBounds(n14, n16 + D_EQU.rowH * i, n17, D_EQU.boxH);
        }
        final int n18 = n + n2 / 2 + D_EQU.fontW;
        final int n19 = n2 - n14;
        final int n20 = D_EQU.fontW * 17;
        final int n21 = n18 + n20 + D_EQU.fontW;
        D_EQU.displayL.setBounds(n18, n15, n19, D_EQU.boxH);
        D_EQU.var1minL.setBounds(n18, n16, n20, D_EQU.boxH);
        D_EQU.var1maxL.setBounds(n18, n16 + D_EQU.rowH, n20, D_EQU.boxH);
        D_EQU.var2minL.setBounds(n18, n16 + D_EQU.rowH * 2, n20, D_EQU.boxH);
        D_EQU.var2maxL.setBounds(n18, n16 + D_EQU.rowH * 3, n20, D_EQU.boxH);
        D_EQU.var1minE.setBounds(n21, n16, D_EQU.boxW, D_EQU.boxH);
        D_EQU.var1maxE.setBounds(n21, n16 + D_EQU.rowH, D_EQU.boxW, D_EQU.boxH);
        D_EQU.var2minE.setBounds(n21, n16 + D_EQU.rowH * 2, D_EQU.boxW, D_EQU.boxH);
        D_EQU.var2maxE.setBounds(n21, n16 + D_EQU.rowH * 3, D_EQU.boxW, D_EQU.boxH);
        final int n22 = top + D_EQU.rowH * 11;
        final int n23 = D_EQU.fontW * 30;
        final int n24 = n5 + n6 - n23 - D_EQU.fontW * 2;
        D_EQU.initialValue.setBounds(n, n22, D_EQU.fontW * 40, D_EQU.boxH);
        D_EQU.buttonGraph.setBounds(n24, n22, n23, D_EQU.boxH);
        D_EQU.line1y = n15 - 4;
        D_EQU.line2x = n + n2 / 2;
        D_EQU.errMessage.setVisible(false);
        D_EQU.drawConstantsUndefined = false;
    }
    
    public void paint(final Graphics graphics) {
        if (D_EQU.drawConstantsUndefined) {
            return;
        }
        final Dimension size = D_EQU.EQU.getSize();
        if (D_EQU.windowW != size.width || D_EQU.windowH != size.height) {
            D_EQU.windowW = size.width;
            D_EQU.windowH = size.height;
            resizedWindow();
        }
        graphics.setColor(C_TOOL.borderColor);
        graphics.fillRect(0, 0, D_EQU.windowW, D_EQU.windowH);
        graphics.setColor(Color.black);
        graphics.drawLine(0, D_EQU.line1y, D_EQU.windowW, D_EQU.line1y);
        graphics.drawLine(D_EQU.line2x, D_EQU.line1y, D_EQU.line2x, D_EQU.windowH);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        String s = actionEvent.getActionCommand();
        if (s == null) {
            s = ((MenuItem)actionEvent.getSource()).getLabel();
        }
        if (s.equals("Quit")) {
            DFIELD.quitDFIELD();
            return;
        }
        if (s.equals("Graph Direction Field")) {
            callGraph();
            return;
        }
        int n = -1;
        int n2 = -1;
        boolean b = false;
        for (int i = 0; i <= 10; ++i) {
            if (s.equals(D_EQU.miName[i])) {
                n = i;
            }
        }
        if (n == -1) {
            if (s.startsWith("Delete")) {
                b = true;
                s = s.substring(s.indexOf(" ") + 1);
            }
            for (int j = 0; j <= D_EQU.userCount; ++j) {
                if (s.equals(D_EQU.userName[j])) {
                    n2 = j;
                }
            }
            if (b) {
                RemoveFromGallery(n2);
                return;
            }
        }
        if (n == 10) {
            DFIELD.quitDFIELD();
        }
        else if (n == 9) {
            callGraph();
        }
        else if (n == 0) {
            setPolkingDefults();
        }
        else if (n == 1) {
            ClearParameters();
            D_EQU.variable1E.setText("v");
            D_EQU.variable2E.setText("t");
            D_EQU.equL.setText("One-dimensional projectile motion with air resistance.");
            D_EQU.equ1E.setText("9.8 - (k/m)v");
            D_EQU.knameE[0].setText("m");
            D_EQU.knameE[1].setText("k");
            D_EQU.kvalueE[0].setText(".01");
            D_EQU.kvalueE[1].setText("0.0001");
            D_EQU.var1minE.setText("0");
            D_EQU.var1maxE.setText("1000");
            D_EQU.var2minE.setText("0");
            D_EQU.var2maxE.setText("1000");
            SetPredefinedSystem();
        }
        else if (n == 5) {
            ClearEquations();
        }
        else if (n == 6) {
            ClearParameters();
        }
        else if (n == 7) {
            ClearDisplay();
        }
        else if (n == 8) {
            ClearEquations();
            ClearParameters();
            ClearDisplay();
        }
        else if (n == 2) {
            final String s2 = "Provide a name for this system:";
            final dataSample dataSample = new dataSample();
            new C_DIALOG(D_EQU.EQU, s2, dataSample).show();
            if (!dataSample.str.equals("")) {
                AddToGallery(dataSample.str);
            }
        }
        else if (n2 >= 0) {
            D_EQU.equL.setText(D_EQU.userName[n2]);
            D_EQU.variable1E.setText(D_EQU.userXname[n2]);
            D_EQU.variable2E.setText(D_EQU.userYname[n2]);
            D_EQU.equ1E.setText(D_EQU.userEqu1[n2]);
            for (int k = 0; k < 4; ++k) {
                D_EQU.knameE[k].setText(D_EQU.userKname[k][n2]);
                D_EQU.kvalueE[k].setText(D_EQU.userKequ[k][n2]);
            }
            D_EQU.var1minE.setText(Float.toString((float)D_EQU.userXmin[n2]));
            D_EQU.var2minE.setText(Float.toString((float)D_EQU.userYmin[n2]));
            D_EQU.var1maxE.setText(Float.toString((float)D_EQU.userXmax[n2]));
            D_EQU.var2maxE.setText(Float.toString((float)D_EQU.userYmax[n2]));
            SetPredefinedSystem();
        }
        else if (n == 3) {
            C_MSG.MSG_append("Syntax for f(x,t):\n\nConstants:\n   e    base of the Natural Logarithm\n   pi   ratio of circumference to diameter of a circle\n\nArithmetic Operators:\n   +    Addition, 5+x\n   -    Unary Negation: -x, e^-x\n   -    Subtraction: 5-x\n   *    Multiplication: 5*x\n           also 5x, 5(x), (5)(x),  5sin(x),  5xt,  5(x+1)\n   /    Division: 5/x\n   ^    Exponentiation: t^x\n           if t is not an integer, and t<0, then\n           t^x = -(|t|^x)\n           also, 0^0 = 1\n\nLogical Operators:\n   <    x<t     1 if true, 0 if false\n   <=   x<=t    1 if true, 0 if false\n   >    x>t     1 if true, 0 if false\n   >=   x>=t    1 if true, 0 if false\n   ==   x==t    1 if true, 0 if false\n   <>   x<>t    1 if true, 0 if false\n\nTrigonometric Functions (in radians):\n   sin(t),  cos(x),  tan(x)\n   cot(x),  sec(x),  csc(x)\n   asin(x), acos(x), atan(x)\n   acot(x), asec(x), acsc(x)\n\nLogarithm Functions:\n   log(x)    natural logarithm of x\n   log10(x)  logarithm of x to base 10\n   exp(x)    base of the natural logarithm aised \n                 to the power of x\n\nHyperbolic Functions:\n   sinh(x),  cosh(x),  tanh(x)\n   asinh(x), acosh(x), atanh(x)\n\nElementary Functions:\n   abs(x)    absolute value of x\n   sqrt(x)   square root of x (undefined for x<0)\n   sign(x)   0 if x=0, -1 if x<0, and +1 if x>0\n\nVariable, Parameter and Constant names must:\n   1) be 8 or less characters in length.\n   2) begin with a alphabetic character.\n   3) contain only alphabetic and/or numeric characters.\n\n", true);
        }
        else {
            if (n == 4) {
                if (DFIELD.ABOUT == null) {
                    D_ABOUT.ABOUT_MAIN(DFIELD.ABOUT = new D_ABOUT());
                }
                else {
                    DFIELD.ABOUT.toFront();
                }
                return;
            }
            if (this.IsMenuItemOfWindow(s)) {}
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
    
    public static void setPolkingDefults() {
        ClearParameters();
        D_EQU.variable1E.setText("x");
        D_EQU.variable2E.setText("t");
        D_EQU.equL.setText("First-order Differential Equation in the form: x´ = f(t,x)");
        D_EQU.equ1E.setText("x^2 - at + b");
        D_EQU.knameE[0].setText("a");
        D_EQU.knameE[1].setText("b");
        D_EQU.kvalueE[0].setText("1");
        D_EQU.kvalueE[1].setText("0");
        D_EQU.var1minE.setText("-2");
        D_EQU.var1maxE.setText("10");
        D_EQU.var2minE.setText("-4");
        D_EQU.var2maxE.setText("4");
        SetPredefinedSystem();
    }
    
    public static void ClearParameters() {
        D_EQU.equL.setText("First-order Differential Equation in the form: x´ = f(t,x)");
        for (int i = 0; i < 4; ++i) {
            D_EQU.knameE[i].setText("");
            D_EQU.kvalueE[i].setText("");
        }
    }
    
    public static void ClearDisplay() {
        D_EQU.var1minE.setText("");
        D_EQU.var1maxE.setText("");
        D_EQU.var2minE.setText("");
        D_EQU.var2maxE.setText("");
    }
    
    public static void ClearEquations() {
        D_EQU.equL.setText("First-order Differential Equation in the form: x´ = f(t,x)");
        D_EQU.variable1E.setText("");
        D_EQU.variable2E.setText("");
        D_EQU.equ1E.setText("");
    }
    
    public static void SetPredefinedSystem() {
        D_EQU.predefinedSystem = true;
        D_EQU.predefinedEqu1 = D_EQU.equ1E.getText();
        D_EQU.predefinedK0 = D_EQU.kvalueE[0].getText();
        D_EQU.predefinedK1 = D_EQU.kvalueE[1].getText();
        D_EQU.predefinedK2 = D_EQU.kvalueE[2].getText();
        D_EQU.predefinedK3 = D_EQU.kvalueE[3].getText();
    }
    
    public static void AddToGallery(final String s) {
        ++D_EQU.userCount;
        D_EQU.userName[D_EQU.userCount] = s;
        D_EQU.userXname[D_EQU.userCount] = D_EQU.variable1E.getText();
        D_EQU.userYname[D_EQU.userCount] = D_EQU.variable2E.getText();
        D_EQU.userEqu1[D_EQU.userCount] = D_EQU.equ1E.getText();
        for (int i = 0; i < 4; ++i) {
            D_EQU.userKname[i][D_EQU.userCount] = D_EQU.knameE[i].getText();
            D_EQU.userKequ[i][D_EQU.userCount] = D_EQU.kvalueE[i].getText();
        }
        D_EQU.userXmin[D_EQU.userCount] = readDouble(D_EQU.var1minE);
        D_EQU.userXmax[D_EQU.userCount] = readDouble(D_EQU.var1maxE);
        D_EQU.userYmin[D_EQU.userCount] = readDouble(D_EQU.var2minE);
        D_EQU.userYmax[D_EQU.userCount] = readDouble(D_EQU.var2maxE);
        AddUserMenu(D_EQU.userCount);
    }
    
    public static void RemoveFromGallery(final int n) {
        D_EQU.mu[3].remove(D_EQU.userMenu[n]);
        D_EQU.mu[4].remove(D_EQU.userMenuDelete[n]);
        for (int i = n; i < D_EQU.userCount; ++i) {
            D_EQU.userName[i] = D_EQU.userName[i + 1];
            D_EQU.userXname[i] = D_EQU.userXname[i + 1];
            D_EQU.userYname[i] = D_EQU.userYname[i + 1];
            D_EQU.userEqu1[i] = D_EQU.userEqu1[i + 1];
            for (int j = 0; j < 4; ++j) {
                D_EQU.userKname[j][i] = D_EQU.userKname[j][i + 1];
                D_EQU.userKequ[j][i] = D_EQU.userKequ[j][i + 1];
            }
            D_EQU.userXmin[i] = D_EQU.userXmin[i + 1];
            D_EQU.userYmin[i] = D_EQU.userYmin[i + 1];
            D_EQU.userXmax[i] = D_EQU.userXmax[i + 1];
            D_EQU.userYmax[i] = D_EQU.userYmax[i + 1];
            D_EQU.userMenu[i] = D_EQU.userMenu[i + 1];
            D_EQU.userMenuDelete[i] = D_EQU.userMenuDelete[i + 1];
        }
        --D_EQU.userCount;
    }
    
    public static void callGraph() {
        DFIELD.dydx_EquationStr = D_EQU.equ1E.getText();
        DFIELD.diffyQ.equStr = D_EQU.diffyQ1.equStr;
        DFIELD.diffyQ.xName = D_EQU.xName;
        DFIELD.diffyQ.yName = D_EQU.yName;
        for (int i = 0; i < 4; ++i) {
            DFIELD.diffyQ.kName[i] = D_EQU.kName[i];
            if (!D_EQU.kName[i].equals("NULLNAME")) {
                DFIELD.EQU_K[i].equStr = D_EQU.EQU_K[i].equStr;
                if (D_EQU.EQU_K[i].kCostant[i]) {
                    DFIELD.diffyQ.kCostant[i] = true;
                    DFIELD.diffyQ.kValue[i] = D_EQU.EQU_K[i].constantValue();
                }
                else {
                    DFIELD.diffyQ.kCostant[i] = false;
                    DFIELD.diffyQ.kInfix[i] = DFIELD.EQU_K[i];
                    DFIELD.EQU_K[i].xName = D_EQU.xName;
                    DFIELD.EQU_K[i].yName = D_EQU.yName;
                    DFIELD.EQU_K[i].checkSyntax();
                    DFIELD.EQU_K[i].FindPostFix();
                }
            }
        }
        DFIELD.diffyQ.checkSyntax();
        DFIELD.diffyQ.FindPostFix();
        D_EQU.g.xmin = D_EQU.xmin;
        D_EQU.g.xmax = D_EQU.xmax;
        D_EQU.g.ymin = D_EQU.ymin;
        D_EQU.g.ymax = D_EQU.ymax;
        if (!D_EQU.initialValue.getState()) {
            DFIELD.orbit.count = -1;
            DFIELD.orbit.dataFree = 0;
        }
        D_GRAPH.InitGraph();
    }
    
    public static void MenuInit() {
        D_EQU.userCount = -1;
        D_EQU.muName[0] = "File";
        D_EQU.miName[9] = "Graph Phase Plane";
        D_EQU.miName[10] = "Quit DFIELD";
        D_EQU.muName[2] = "Window";
        D_EQU.muName[1] = "Edit";
        D_EQU.miName[5] = "Clear Equations";
        D_EQU.miName[6] = "Clear Parameters";
        D_EQU.miName[7] = "Clear Display Window Settings";
        D_EQU.miName[8] = "Clear All";
        D_EQU.muName[3] = "Gallery";
        D_EQU.muName[4] = "Remove From Gallery";
        D_EQU.miName[2] = "Add current system to the gallery";
        D_EQU.miName[0] = "Polking's 1st order equation";
        D_EQU.miName[1] = "Projectile motion";
        D_EQU.muName[5] = "Help";
        D_EQU.miName[3] = "Syntax and Symbols for Differential Equation";
        D_EQU.miName[4] = "About DFIELD";
        D_EQU.myMenu = new MenuBar();
        MakeMenu(0);
        MakeMenuItem(9, 0, 71);
        MakeMenuItem(10, 0, 81);
        MakeMenu(1);
        MakeMenuItem(5, 1, 69);
        MakeMenuItem(6, 1, 78);
        MakeMenuItem(7, 1, 68);
        D_EQU.mu[1].addSeparator();
        MakeMenuItem(8, 1, 65);
        MakeMenu(2);
        MakeMenu(3);
        MakeSubMenu(4, 3);
        MakeMenuItem(2, 3);
        D_EQU.mu[3].addSeparator();
        MakeMenuItem(0, 3);
        MakeMenuItem(1, 3);
        D_EQU.mu[3].addSeparator();
        MakeMenu(5);
        MakeMenuItem(3, 5);
        MakeMenuItem(4, 5);
        D_EQU.EQU.setMenuBar(D_EQU.myMenu);
    }
    
    public static void MakeMenu(final int n) {
        (D_EQU.mu[n] = new Menu(D_EQU.muName[n])).addActionListener(D_EQU.EQU);
        D_EQU.myMenu.add(D_EQU.mu[n]);
    }
    
    public static void MakeMenuItem(final int n, final int n2) {
        MakeMenuItem(n, n2, 65535);
    }
    
    public static void MakeMenuItem(final int n, final int n2, final int n3) {
        if (n3 == 65535) {
            D_EQU.mi[n] = new MenuItem(D_EQU.miName[n]);
        }
        else {
            D_EQU.mi[n] = new MenuItem(D_EQU.miName[n], new MenuShortcut(n3));
        }
        D_EQU.mi[n].addActionListener(D_EQU.EQU);
        D_EQU.mu[n2].add(D_EQU.mi[n]);
    }
    
    public static void MakeSubMenu(final int n, final int n2) {
        (D_EQU.mu[n] = new Menu(D_EQU.muName[n])).addActionListener(D_EQU.EQU);
        D_EQU.mu[n2].add(D_EQU.mu[n]);
    }
    
    public static void AddUserMenu(final int n) {
        (D_EQU.userMenu[n] = new MenuItem(D_EQU.userName[n])).addActionListener(D_EQU.EQU);
        D_EQU.userMenu[n].setFont(C_TOOL.fontNormal);
        D_EQU.mu[3].add(D_EQU.userMenu[n]);
        (D_EQU.userMenuDelete[n] = new MenuItem("Delete ".concat(String.valueOf(String.valueOf(D_EQU.userName[n]))))).addActionListener(D_EQU.EQU);
        D_EQU.userMenuDelete[n].setFont(C_TOOL.fontNormal);
        D_EQU.mu[4].add(D_EQU.userMenuDelete[n]);
    }
    
    public static boolean checkEditFields() {
        D_EQU.errorType = 5;
        D_EQU.variable1E.setBackground(Color.white);
        D_EQU.variable2E.setBackground(Color.white);
        D_EQU.equ1E.setBackground(Color.white);
        for (int i = 0; i < 4; ++i) {
            D_EQU.knameE[i].setBackground(Color.white);
            D_EQU.kvalueE[i].setBackground(Color.white);
        }
        D_EQU.var1minE.setBackground(Color.white);
        D_EQU.var1maxE.setBackground(Color.white);
        D_EQU.var2minE.setBackground(Color.white);
        D_EQU.var2maxE.setBackground(Color.white);
        final String trim = D_EQU.variable2E.getText().trim();
        if (!checkVariableName(trim)) {
            D_EQU.variable2E.setBackground(Color.red);
            D_EQU.errorStr = String.valueOf(String.valueOf(new StringBuffer("'").append(trim).append("': illegal Variable Name.")));
            return false;
        }
        if (!trim.equals(D_EQU.xName)) {
            D_EQU.xName = trim;
            D_EQU.var1minL.setText(String.valueOf(String.valueOf(new StringBuffer("Minimum ").append(trim).append(" = "))));
            D_EQU.var1maxL.setText(String.valueOf(String.valueOf(new StringBuffer("Maximum ").append(trim).append(" = "))));
        }
        final String trim2 = D_EQU.variable1E.getText().trim();
        if (!checkVariableName(trim2)) {
            D_EQU.variable1E.setBackground(Color.red);
            D_EQU.errorStr = String.valueOf(String.valueOf(new StringBuffer("'").append(trim2).append("': illegal Variable Name.")));
            return false;
        }
        if (!trim2.equals(D_EQU.yName)) {
            D_EQU.yName = trim2;
            D_EQU.var2minL.setText(String.valueOf(String.valueOf(new StringBuffer("Minimum ").append(trim2).append(" = "))));
            D_EQU.var2maxL.setText(String.valueOf(String.valueOf(new StringBuffer("Maximum ").append(trim2).append(" = "))));
        }
        for (int j = 0; j < 4; ++j) {
            if (!checkParameterName(j)) {
                return false;
            }
        }
        D_EQU.xmin = readDouble(D_EQU.var1minE);
        if (Double.isNaN(D_EQU.xmin)) {
            D_EQU.var1minE.setBackground(Color.red);
            D_EQU.errorStr = String.valueOf(String.valueOf(new StringBuffer("'").append(D_EQU.var1minE.getText()).append("':  is not a number.")));
            return false;
        }
        D_EQU.xmax = readDouble(D_EQU.var1maxE);
        if (Double.isNaN(D_EQU.xmax)) {
            D_EQU.var1maxE.setBackground(Color.red);
            D_EQU.errorStr = String.valueOf(String.valueOf(new StringBuffer("'").append(D_EQU.var1maxE.getText()).append("':  is not a number.")));
            return false;
        }
        D_EQU.ymin = readDouble(D_EQU.var2minE);
        if (Double.isNaN(D_EQU.ymin)) {
            D_EQU.var2minE.setBackground(Color.red);
            D_EQU.errorStr = String.valueOf(String.valueOf(new StringBuffer("'").append(D_EQU.var2minE.getText()).append("':  is not a number.")));
            return false;
        }
        D_EQU.ymax = readDouble(D_EQU.var2maxE);
        if (Double.isNaN(D_EQU.ymax)) {
            D_EQU.var2maxE.setBackground(Color.red);
            D_EQU.errorStr = String.valueOf(String.valueOf(new StringBuffer("'").append(D_EQU.var2maxE.getText()).append("':  is not a number.")));
            return false;
        }
        if (D_EQU.xmax <= D_EQU.xmin) {
            D_EQU.var1minE.setBackground(Color.red);
            D_EQU.var1maxE.setBackground(Color.red);
            D_EQU.errorStr = "Maximum must be greater then Minimum";
            return false;
        }
        if (D_EQU.ymax <= D_EQU.ymin) {
            D_EQU.var2minE.setBackground(Color.red);
            D_EQU.var2maxE.setBackground(Color.red);
            D_EQU.errorStr = "Maximum must be greater then Minimum";
            return false;
        }
        for (int k = 0; k < 4; ++k) {
            if (!checkParameterExpression(k)) {
                return false;
            }
        }
        D_EQU.diffyQ1.equStr = D_EQU.equ1E.getText().trim();
        D_EQU.diffyQ1.xName = D_EQU.xName;
        D_EQU.diffyQ1.yName = D_EQU.yName;
        for (int l = 0; l < 4; ++l) {
            D_EQU.diffyQ1.kName[l] = D_EQU.kName[l];
        }
        if (!D_EQU.diffyQ1.checkSyntax()) {
            D_EQU.errorStr = D_EQU.diffyQ1.errorString;
            D_EQU.errorType = D_EQU.diffyQ1.errorType;
            D_EQU.equ1E.setBackground(Color.red);
            return false;
        }
        return true;
    }
    
    public static boolean checkParameterName(final int n) {
        String trim = D_EQU.knameE[n].getText().trim();
        if (trim.equals("")) {
            trim = "NULLNAME";
        }
        if (trim.equals("NULLNAME")) {
            D_EQU.kName[n] = "NULLNAME";
            D_EQU.kvalueE[n].setVisible(false);
            return true;
        }
        D_EQU.kvalueE[n].setVisible(true);
        if (trim.equals(D_EQU.xName) || trim.equals(D_EQU.yName)) {
            D_EQU.errorStr = "Parameter name cannot be the same as one of the variable names.";
            D_EQU.knameE[n].setBackground(Color.red);
            return false;
        }
        if (!checkVariableName(trim)) {
            D_EQU.errorStr = String.valueOf(String.valueOf(new StringBuffer("'").append(trim).append("':  illegal Parameter Name.")));
            D_EQU.knameE[n].setBackground(Color.red);
            return false;
        }
        D_EQU.kName[n] = trim;
        return true;
    }
    
    public static boolean checkVariableName(String lowerCase) {
        lowerCase = lowerCase.toLowerCase();
        final int length = lowerCase.length();
        if (length < 1 || length > 8) {
            return false;
        }
        for (int i = 0; i < length; ++i) {
            final char char1 = lowerCase.charAt(i);
            if (char1 < 'a' || char1 > 'z') {
                if (i == 0) {
                    return false;
                }
                if (char1 < '0' || char1 > '9') {
                    return false;
                }
            }
        }
        return true;
    }
    
    public static boolean checkParameterExpression(final int n) {
        if (D_EQU.kName[n].equals("NULLNAME")) {
            return true;
        }
        D_EQU.EQU_K[n].equStr = D_EQU.kvalueE[n].getText().trim();
        D_EQU.EQU_K[n].xName = D_EQU.xName;
        D_EQU.EQU_K[n].yName = D_EQU.yName;
        if (!D_EQU.EQU_K[n].checkSyntax()) {
            D_EQU.errorStr = D_EQU.EQU_K[n].errorString;
            D_EQU.errorType = 8;
            D_EQU.kvalueE[n].setBackground(Color.red);
            return false;
        }
        D_EQU.EQU_K[n].kCostant[n] = D_EQU.EQU_K[n].isConstant();
        if (D_EQU.EQU_K[n].kCostant[n] && Double.isNaN(D_EQU.EQU_K[n].constantValue())) {
            D_EQU.errorStr = String.valueOf(String.valueOf(new StringBuffer("'").append(D_EQU.EQU_K[n].equStr).append("':  Bad parameter experssion.")));
            D_EQU.errorType = 8;
            D_EQU.kvalueE[n].setBackground(Color.red);
            return false;
        }
        return true;
    }
    
    public static double readDouble(final TextField textField) {
        try {
            return Double.valueOf(textField.getText());
        }
        catch (NumberFormatException ex) {
            return Double.NaN;
        }
    }
    
    public static void displayError(final boolean b) {
        if (b) {
            D_EQU.errMessage.setVisible(false);
            D_EQU.buttonGraph.setEnabled(true);
            D_EQU.mu[2].setEnabled(true);
            return;
        }
        D_EQU.equ1E.getText();
        D_EQU.errMessage.setText(D_EQU.errorStr);
        D_EQU.errMessage.setVisible(true);
        D_EQU.buttonGraph.setEnabled(false);
        D_EQU.mu[2].setEnabled(false);
    }
    
    public static void SaveGallery() {
        if (DFIELD.RunningApplet) {
            return;
        }
        final File file = new File("dfield.ini");
        try {
            final FileOutputStream fileOutputStream = new FileOutputStream(file);
            final DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
            final ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            dataOutputStream.writeUTF("DFIELD 2002.2");
            dataOutputStream.writeInt(D_EQU.userCount);
            for (int i = 0; i <= D_EQU.userCount; ++i) {
                dataOutputStream.writeUTF(D_EQU.userName[i]);
                dataOutputStream.writeUTF(D_EQU.userXname[i]);
                dataOutputStream.writeUTF(D_EQU.userYname[i]);
                dataOutputStream.writeUTF(D_EQU.userEqu1[i]);
                for (int j = 0; j < 4; ++j) {
                    dataOutputStream.writeUTF(D_EQU.userKname[j][i]);
                    dataOutputStream.writeUTF(D_EQU.userKequ[j][i]);
                }
                dataOutputStream.writeDouble(D_EQU.userXmin[i]);
                dataOutputStream.writeDouble(D_EQU.userXmax[i]);
                dataOutputStream.writeDouble(D_EQU.userYmin[i]);
                dataOutputStream.writeDouble(D_EQU.userYmax[i]);
            }
            dataOutputStream.writeInt(C_TOOL.fontNormalSize);
            dataOutputStream.writeUTF("EndOfFile");
            fileOutputStream.close();
        }
        catch (IOException ex) {
            C_MSG.MSG_append("IO Exception: ".concat(String.valueOf(String.valueOf(ex.getMessage()))), true);
        }
        catch (SecurityException ex2) {
            C_MSG.MSG_append("Security Exception: Java Security Manager does not allow Applets to access Local Disk.", true);
        }
    }
    
    public static void LoadGallery() {
        if (DFIELD.RunningApplet) {
            return;
        }
        final File file = new File("dfield.ini");
        try {
            final FileInputStream fileInputStream = new FileInputStream(file);
            final DataInputStream dataInputStream = new DataInputStream(fileInputStream);
            final ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            final String utf = dataInputStream.readUTF();
            if (!utf.startsWith("DFIELD")) {
                return;
            }
            final int length = utf.length();
            final int n = utf.indexOf(32) + 1;
            final int n2 = utf.indexOf(46) + 1;
            if (n <= 0 || n2 <= 0) {
                return;
            }
            if (n2 - n != 3 && n2 - n != 5) {
                return;
            }
            if (n + 3 >= length || n2 >= length) {
                return;
            }
            String s = "";
            if (n2 - n == 3) {
                s = "19";
            }
            final String concat = String.valueOf(String.valueOf(s)).concat(String.valueOf(String.valueOf(utf.substring(n, n2 - 1))));
            final String string = new Character(utf.charAt(n2)).toString();
            String s2;
            if (n2 + 1 < length) {
                final char char1 = utf.charAt(n2 + 1);
                if (Character.isDigit(char1)) {
                    s2 = String.valueOf(String.valueOf(string)).concat(String.valueOf(String.valueOf(char1)));
                }
                else {
                    s2 = "0".concat(String.valueOf(String.valueOf(string)));
                }
            }
            else {
                s2 = "0".concat(String.valueOf(String.valueOf(string)));
            }
            if (C_TOOL.val(String.valueOf(String.valueOf(concat)).concat(String.valueOf(String.valueOf(s2)))) < 199903) {
                return;
            }
            D_EQU.userCount = dataInputStream.readInt();
            for (int i = 0; i <= D_EQU.userCount; ++i) {
                D_EQU.userName[i] = dataInputStream.readUTF();
                D_EQU.userXname[i] = dataInputStream.readUTF();
                D_EQU.userYname[i] = dataInputStream.readUTF();
                D_EQU.userEqu1[i] = dataInputStream.readUTF();
                for (int j = 0; j < 4; ++j) {
                    D_EQU.userKname[j][i] = dataInputStream.readUTF();
                    D_EQU.userKequ[j][i] = dataInputStream.readUTF();
                }
                D_EQU.userXmin[i] = dataInputStream.readDouble();
                D_EQU.userXmax[i] = dataInputStream.readDouble();
                D_EQU.userYmin[i] = dataInputStream.readDouble();
                D_EQU.userYmax[i] = dataInputStream.readDouble();
            }
            final int int1 = dataInputStream.readInt();
            if (!dataInputStream.readUTF().equals("EndOfFile")) {
                C_MSG.MSG_append("IO Exception: Save file corrupt of a different version", true);
                D_EQU.userCount = -1;
                return;
            }
            fileInputStream.close();
            C_TOOL.setFonts(int1);
            for (int k = 0; k <= D_EQU.userCount; ++k) {
                AddUserMenu(k);
            }
        }
        catch (FileNotFoundException ex2) {}
        catch (IOException ex) {
            C_MSG.MSG_append("IO Exception: ".concat(String.valueOf(String.valueOf(ex.getMessage()))), true);
        }
        catch (SecurityException ex3) {
            C_MSG.MSG_append("Security Exception: Java Security Manager does not allow Applets to access Local Disk.", true);
        }
    }
    
    static {
        D_EQU.EQU = null;
        D_EQU.knameE = new TextField[4];
        D_EQU.kvalueE = new TextField[4];
        D_EQU.kequalL = new Label[4];
        D_EQU.drawConstantsUndefined = true;
        D_EQU.predefinedSystem = false;
        D_EQU.mu = new Menu[6];
        D_EQU.muName = new String[6];
        D_EQU.mi = new MenuItem[11];
        D_EQU.miName = new String[11];
        D_EQU.userName = new String[25];
        D_EQU.userXname = new String[25];
        D_EQU.userYname = new String[25];
        D_EQU.userEqu1 = new String[25];
        D_EQU.userKname = new String[4][25];
        D_EQU.userKequ = new String[4][25];
        D_EQU.userXmin = new double[25];
        D_EQU.userXmax = new double[25];
        D_EQU.userYmin = new double[25];
        D_EQU.userYmax = new double[25];
        D_EQU.userMenu = new MenuItem[25];
        D_EQU.userMenuDelete = new MenuItem[25];
        D_EQU.userCount = -1;
        D_EQU.kName = new String[4];
        D_EQU.EQU_K = new C_INFIX[4];
    }
}
