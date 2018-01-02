import java.awt.event.ActionEvent;
import java.awt.FontMetrics;
import java.util.Properties;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.Component;
import java.awt.CheckboxGroup;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Checkbox;
import java.awt.Dimension;
import java.awt.Button;
import java.awt.Graphics;
import java.awt.PrintJob;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class D_PRINT extends Frame implements WindowListener, ActionListener, MouseListener, MouseMotionListener
{
    public static D_PRINT PRINT;
    public static PrintJob pjob;
    public static Graphics gPRINT;
    public static Graphics gPRINTER;
    public static Button buttonPrint;
    public static Button buttonCancel;
    public static Dimension paper;
    public static int paperX;
    public static int paperY;
    public static int PrintWindowX;
    public static int PrintWindowY;
    public static int PrintWindowLeft;
    public static int PrintWindowTop;
    public static int paperOutlineLeft;
    public static int paperOutlineTop;
    public static int printAreaLeft;
    public static int printAreaTop;
    public static int printAreaX;
    public static int printAreaY;
    public static Checkbox cBoxBW;
    public static Checkbox cBoxColor;
    public static int movingCornor;
    public static int saveFontSize;
    
    public static void PRINT_MAIN() {
        if (D_PRINT.PRINT != null) {
            D_PRINT.PRINT.toFront();
            InitPrintJob();
            return;
        }
        (D_PRINT.PRINT = new D_PRINT()).setResizable(false);
        D_PRINT.PRINT.setTitle("Print Setup");
        D_PRINT.PRINT.show();
        D_PRINT.PRINT.setVisible(false);
        D_PRINT.PRINT.addWindowListener(D_PRINT.PRINT);
        D_PRINT.PRINT.setBackground(Color.lightGray);
        D_PRINT.PRINT.setForeground(Color.black);
        if (D_PRINT.gPRINT != null) {
            D_PRINT.gPRINT.dispose();
        }
        D_PRINT.gPRINT = D_PRINT.PRINT.getGraphics();
        D_PRINT.PRINT.setLayout(null);
        final CheckboxGroup checkboxGroup = new CheckboxGroup();
        D_PRINT.cBoxBW = new Checkbox("Black on White", checkboxGroup, true);
        D_PRINT.cBoxColor = new Checkbox("Color", checkboxGroup, false);
        D_PRINT.PRINT.add(D_PRINT.cBoxBW);
        D_PRINT.PRINT.add(D_PRINT.cBoxColor);
        D_PRINT.buttonPrint = new Button("Print");
        D_PRINT.buttonCancel = new Button("Cancel");
        D_PRINT.PRINT.add(D_PRINT.buttonPrint);
        D_PRINT.buttonPrint.addActionListener(D_PRINT.PRINT);
        D_PRINT.PRINT.add(D_PRINT.buttonCancel);
        D_PRINT.buttonCancel.addActionListener(D_PRINT.PRINT);
        D_PRINT.PRINT.addMouseMotionListener(D_PRINT.PRINT);
        D_PRINT.PRINT.addMouseListener(D_PRINT.PRINT);
        InitPrintJob();
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        QuitD_PRINT();
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        final int n = D_PRINT.paperOutlineLeft + D_PRINT.printAreaLeft / 10;
        final int n2 = D_PRINT.paperOutlineTop + D_PRINT.printAreaTop / 10;
        final int n3 = n + D_PRINT.printAreaX / 10;
        final int n4 = n2 + D_PRINT.printAreaY / 10;
        if (Math.abs(x - n) < 5 && Math.abs(y - n2) < 5) {
            D_PRINT.movingCornor = 1;
        }
        else if (Math.abs(x - n3) < 5 && Math.abs(y - n2) < 5) {
            D_PRINT.movingCornor = 2;
        }
        else if (Math.abs(x - n3) < 5 && Math.abs(y - n4) < 5) {
            D_PRINT.movingCornor = 3;
        }
        else if (Math.abs(x - n) < 5 && Math.abs(y - n4) < 5) {
            D_PRINT.movingCornor = 4;
        }
        else {
            D_PRINT.movingCornor = -1;
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (D_PRINT.movingCornor < 1) {
            return;
        }
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (D_PRINT.movingCornor == 1 || D_PRINT.movingCornor == 4) {
            final int printAreaLeft = (x - D_PRINT.paperOutlineLeft) * 10;
            D_PRINT.printAreaX = D_PRINT.printAreaX + D_PRINT.printAreaLeft - printAreaLeft;
            D_PRINT.printAreaLeft = printAreaLeft;
        }
        if (D_PRINT.movingCornor == 2 || D_PRINT.movingCornor == 3) {
            D_PRINT.printAreaX = (x - D_PRINT.paperOutlineLeft) * 10 - D_PRINT.printAreaLeft;
        }
        if (D_PRINT.movingCornor == 1 || D_PRINT.movingCornor == 2) {
            final int printAreaTop = (y - D_PRINT.paperOutlineTop) * 10;
            D_PRINT.printAreaY = D_PRINT.printAreaY + D_PRINT.printAreaTop - printAreaTop;
            D_PRINT.printAreaTop = printAreaTop;
        }
        if (D_PRINT.movingCornor == 3 || D_PRINT.movingCornor == 4) {
            D_PRINT.printAreaY = (y - D_PRINT.paperOutlineTop) * 10 - D_PRINT.printAreaTop;
        }
        if (D_PRINT.printAreaX < 5) {
            D_PRINT.printAreaX = 5;
        }
        if (D_PRINT.printAreaY < 5) {
            D_PRINT.printAreaY = 5;
        }
        if (D_PRINT.printAreaTop < 0) {
            D_PRINT.printAreaTop = 0;
        }
        if (D_PRINT.printAreaLeft < 0) {
            D_PRINT.printAreaLeft = 0;
        }
        if (D_PRINT.printAreaLeft > D_PRINT.paperX - 6) {
            D_PRINT.printAreaLeft = D_PRINT.paperX - 6;
        }
        if (D_PRINT.printAreaTop > D_PRINT.paperY - 6) {
            D_PRINT.printAreaTop = D_PRINT.paperY - 6;
        }
        if (D_PRINT.printAreaLeft + D_PRINT.printAreaX > D_PRINT.paperX) {
            D_PRINT.printAreaX = D_PRINT.paperX - D_PRINT.printAreaLeft;
        }
        if (D_PRINT.printAreaTop + D_PRINT.printAreaY > D_PRINT.paperY) {
            D_PRINT.printAreaY = D_PRINT.paperY - D_PRINT.printAreaTop;
        }
        this.paint(D_PRINT.gPRINT);
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        D_PRINT.movingCornor = -1;
    }
    
    public static void InitPrintJob() {
        try {
            D_PRINT.pjob = D_PRINT.PRINT.getToolkit().getPrintJob(D_PRINT.PRINT, "DFIELD", null);
            if (D_PRINT.pjob == null) {
                QuitD_PRINT();
                return;
            }
        }
        catch (SecurityException ex) {
            C_MSG.MSG_append("Security Exception: \n  Your browser's Security Manager is not allowing DFIELD to access your \n  Local Printer.\n  Some browsers (like Netscape 6.0 and newer), enable the user to \n  specify whether applets are allowed to print.\n  Consult your browser's documentation for specifics.\n\n", true);
            QuitD_PRINT();
            return;
        }
        D_PRINT.movingCornor = -1;
        final FontMetrics fontMetrics = D_PRINT.PRINT.getFontMetrics(C_TOOL.fontBold);
        final int height = fontMetrics.getHeight();
        final int stringWidth = fontMetrics.stringWidth("X");
        D_PRINT.buttonPrint.setFont(C_TOOL.fontBold);
        D_PRINT.buttonCancel.setFont(C_TOOL.fontBold);
        D_PRINT.cBoxBW.setFont(C_TOOL.fontBold);
        D_PRINT.cBoxColor.setFont(C_TOOL.fontBold);
        D_PRINT.paper = D_PRINT.pjob.getPageDimension();
        D_PRINT.paperX = D_PRINT.paper.width;
        D_PRINT.paperY = D_PRINT.paper.height;
        D_PRINT.PrintWindowX = 22 * stringWidth;
        D_PRINT.PrintWindowY = D_PRINT.paperY / 10 + 9 * height;
        D_PRINT.PRINT.setBounds(100, 100, D_PRINT.PrintWindowX, D_PRINT.PrintWindowY);
        final Dimension size = D_PRINT.PRINT.getSize();
        D_PRINT.PrintWindowX = size.width - D_PRINT.PRINT.getInsets().left - D_PRINT.PRINT.getInsets().right;
        D_PRINT.PrintWindowY = size.height - D_PRINT.PRINT.getInsets().top - D_PRINT.PRINT.getInsets().bottom;
        D_PRINT.PrintWindowLeft = D_PRINT.PRINT.getInsets().left;
        D_PRINT.PrintWindowTop = D_PRINT.PRINT.getInsets().top;
        D_PRINT.paperOutlineLeft = D_PRINT.PrintWindowLeft + (D_PRINT.PrintWindowX - D_PRINT.paperX / 10) / 2;
        D_PRINT.paperOutlineTop = D_PRINT.PrintWindowTop + 10;
        final double n = D_GRAPH.g.windowHeight / D_GRAPH.g.windowWidth;
        if (DFIELD.undefinedPrintArea) {
            DFIELD.undefinedPrintArea = false;
            if (D_PRINT.paperY > D_PRINT.paperX) {
                D_PRINT.printAreaLeft = 30;
                D_PRINT.printAreaTop = 50;
                D_PRINT.printAreaX = D_PRINT.paperX - 60;
                D_PRINT.printAreaY = Math.min(D_PRINT.paperY - 100, (int)(D_PRINT.printAreaX * n));
            }
            else {
                D_PRINT.printAreaTop = 30;
                D_PRINT.printAreaY = D_PRINT.paperY - 60;
                D_PRINT.printAreaX = Math.min(D_PRINT.paperX - 100, (int)(D_PRINT.printAreaY / n));
                D_PRINT.printAreaLeft = (D_PRINT.paperX - D_PRINT.printAreaX) / 2;
            }
        }
        final int n2 = D_PRINT.paperOutlineTop + D_PRINT.paperY / 10 + 5;
        final int n3 = n2 + height + 3;
        D_PRINT.cBoxBW.setBounds(stringWidth, n2, stringWidth * 20, height);
        D_PRINT.cBoxColor.setBounds(stringWidth, n3, stringWidth * 20, height);
        final int n4 = n3 + height + 6;
        final int n5 = stringWidth * 8;
        final int n6 = (int)(height * 1.5);
        final int n7 = stringWidth;
        final int n8 = D_PRINT.PrintWindowX - n5 - stringWidth;
        D_PRINT.buttonPrint.setBounds(n7, n4, n5, n6);
        D_PRINT.buttonCancel.setBounds(n8, n4, n5, n6);
        D_PRINT.PRINT.setVisible(true);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        if (actionCommand.equals("Cancel")) {
            QuitD_PRINT();
        }
        else if (actionCommand.equals("Print")) {
            printGraph();
        }
    }
    
    public static void printGraph() {
        D_PRINT.gPRINTER = D_PRINT.pjob.getGraphics();
        if (D_PRINT.gPRINTER == null) {
            D_PRINT.pjob.end();
            QuitD_PRINT();
            return;
        }
        D_GRAPH.GRAPH.setVisible(false);
        D_PRINT.PRINT.setVisible(false);
        final Color backgroundColor = C_TOOL.BackgroundColor;
        C_TOOL.BackgroundColor = Color.white;
        D_GRAPH.SetColors();
        C_TOOL.borderColor = Color.white;
        D_GRAPH.colorBorderForeground = Color.black;
        if (D_PRINT.cBoxBW.getState()) {
            D_GRAPH.colorArrows = Color.darkGray;
            D_GRAPH.colorGrid = Color.darkGray;
            for (int i = 0; i < 5; ++i) {
                D_GRAPH.colorSolver[i] = Color.black;
            }
        }
        D_GRAPH.g.windowWidth = D_PRINT.printAreaX;
        D_GRAPH.g.windowHeight = D_PRINT.printAreaY;
        final Graphics buf = D_GRAPH.g.Buf;
        D_GRAPH.g.Buf = D_PRINT.gPRINTER;
        D_PRINT.saveFontSize = C_TOOL.fontNormalSize;
        C_TOOL.setFonts(10);
        D_PRINT.gPRINTER.setFont(C_TOOL.fontNormal);
        D_PRINT.gPRINTER.setColor(Color.black);
        D_PRINT.gPRINTER.translate(D_PRINT.printAreaLeft, D_PRINT.printAreaTop);
        DFIELD.printing = true;
        D_GRAPH.ReDrawEverything();
        DFIELD.printing = false;
        C_TOOL.BackgroundColor = backgroundColor;
        D_GRAPH.SetColors();
        D_GRAPH.g.Buf = buf;
        D_GRAPH.SetDrawConstants();
        D_GRAPH.ReDrawEverything();
        if (D_PRINT.gPRINTER != null) {
            D_PRINT.gPRINTER.dispose();
        }
        QuitD_PRINT();
        D_GRAPH.GRAPH.setVisible(true);
    }
    
    public void paint(final Graphics graphics) {
        D_PRINT.gPRINT.setColor(Color.white);
        D_PRINT.gPRINT.fillRect(D_PRINT.paperOutlineLeft, D_PRINT.paperOutlineTop, D_PRINT.paperX / 10, D_PRINT.paperY / 10);
        D_PRINT.gPRINT.setColor(Color.black);
        final int n = D_PRINT.paperOutlineLeft + D_PRINT.printAreaLeft / 10;
        final int n2 = D_PRINT.paperOutlineTop + D_PRINT.printAreaTop / 10;
        final int n3 = n + D_PRINT.printAreaX / 10;
        final int n4 = n2 + D_PRINT.printAreaY / 10;
        D_PRINT.gPRINT.drawLine(n, n2, n3, n2);
        D_PRINT.gPRINT.drawLine(n3, n2, n3, n4);
        D_PRINT.gPRINT.drawLine(n3, n4, n, n4);
        D_PRINT.gPRINT.drawLine(n, n4, n, n2);
        D_PRINT.gPRINT.fillRect(n, n2, 5, 5);
        D_PRINT.gPRINT.fillRect(n, n4 - 4, 5, 5);
        D_PRINT.gPRINT.fillRect(n3 - 4, n2, 5, 5);
        D_PRINT.gPRINT.fillRect(n3 - 4, n4 - 4, 5, 5);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public static void QuitD_PRINT() {
        if (D_PRINT.PRINT == null) {
            return;
        }
        if (D_PRINT.pjob != null) {
            D_PRINT.pjob.end();
        }
        if (D_PRINT.gPRINT != null) {
            D_PRINT.gPRINT.dispose();
        }
        D_PRINT.PRINT.dispose();
        D_PRINT.PRINT = null;
    }
    
    static {
        D_PRINT.PRINT = null;
    }
}
