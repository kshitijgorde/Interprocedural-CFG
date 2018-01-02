import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Choice;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Label;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class D_OPTION extends Frame implements WindowListener, ActionListener, ItemListener
{
    public static D_OPTION OPTION;
    public static Label label1;
    public static Label label2;
    public static Label label3;
    public static Label label4;
    public static TextField edit1;
    public static TextField edit2;
    public static TextField edit3;
    public static TextField edit4;
    public static Button buttonApply;
    public static Button buttonDefault;
    public static int fontHeight;
    public static int letterWidth;
    public static int boxHeight;
    public static int infoTop;
    public static int infoLeft;
    public static int windowWidth;
    public static int windowHeight;
    public static Choice solver;
    public static final String solverEuler = "Euler's Tangent Line";
    public static final String solverRungeKutta2 = "Runge-Kutta 2 (Improved Euler)";
    public static final String solverRungeKutta4 = "Runge-Kutta fourth-order";
    public static final String solverRKF = "Runge-Kutta-Fehlberg Adaptive Step";
    public static final String solverDormand = "Dormand-Prince Adaptive Step";
    public static dataGraphConstants g;
    
    public static void OPTION_MAIN() {
        D_OPTION.g = D_GRAPH.g;
        if (D_OPTION.OPTION != null) {
            D_OPTION.OPTION.show();
            return;
        }
        (D_OPTION.OPTION = new D_OPTION()).setResizable(false);
        D_OPTION.OPTION.setTitle("DFIELD:  Solver Settings Window");
        DFIELD.AddMenuWindow(D_OPTION.OPTION);
        D_OPTION.OPTION.setBackground(Color.lightGray);
        final Font fontNormal = C_TOOL.fontNormal;
        D_OPTION.OPTION.setFont(fontNormal);
        final FontMetrics fontMetrics = D_OPTION.OPTION.getFontMetrics(fontNormal);
        D_OPTION.fontHeight = (D_OPTION.fontHeight = fontMetrics.getHeight());
        D_OPTION.letterWidth = fontMetrics.stringWidth("X");
        D_OPTION.windowWidth = D_OPTION.letterWidth * 34;
        D_OPTION.boxHeight = (int)(D_OPTION.fontHeight * 1.5);
        D_OPTION.OPTION.setBounds(25, 25, D_OPTION.windowWidth, D_OPTION.fontHeight * 14);
        D_OPTION.OPTION.show();
        D_OPTION.OPTION.addWindowListener(D_OPTION.OPTION);
        final Dimension size = D_OPTION.OPTION.getSize();
        D_OPTION.windowWidth = size.width - D_OPTION.OPTION.getInsets().left - D_OPTION.OPTION.getInsets().right;
        D_OPTION.windowHeight = size.height - D_OPTION.OPTION.getInsets().top - D_OPTION.OPTION.getInsets().bottom;
        final int top = D_OPTION.OPTION.getInsets().top;
        final int left = D_OPTION.OPTION.getInsets().left;
        D_OPTION.OPTION.setLayout(null);
        (D_OPTION.solver = new Choice()).add("Euler's Tangent Line");
        D_OPTION.solver.add("Runge-Kutta 2 (Improved Euler)");
        D_OPTION.solver.add("Runge-Kutta fourth-order");
        D_OPTION.solver.add("Runge-Kutta-Fehlberg Adaptive Step");
        D_OPTION.solver.add("Dormand-Prince Adaptive Step");
        D_OPTION.OPTION.add(D_OPTION.solver);
        D_OPTION.solver.addItemListener(D_OPTION.OPTION);
        D_OPTION.OPTION.add(D_OPTION.label1);
        D_OPTION.OPTION.add(D_OPTION.label2);
        D_OPTION.OPTION.add(D_OPTION.label3);
        D_OPTION.OPTION.add(D_OPTION.label4);
        D_OPTION.OPTION.add(D_OPTION.edit1);
        D_OPTION.OPTION.add(D_OPTION.edit2);
        D_OPTION.OPTION.add(D_OPTION.edit3);
        D_OPTION.OPTION.add(D_OPTION.edit4);
        D_OPTION.OPTION.add(D_OPTION.buttonApply);
        D_OPTION.buttonApply.addActionListener(D_OPTION.OPTION);
        D_OPTION.OPTION.add(D_OPTION.buttonDefault);
        D_OPTION.buttonDefault.addActionListener(D_OPTION.OPTION);
        D_OPTION.solver.setFont(fontNormal);
        D_OPTION.label1.setFont(fontNormal);
        D_OPTION.label2.setFont(fontNormal);
        D_OPTION.label3.setFont(fontNormal);
        D_OPTION.label4.setFont(fontNormal);
        D_OPTION.edit1.setFont(fontNormal);
        D_OPTION.edit2.setFont(fontNormal);
        D_OPTION.edit3.setFont(fontNormal);
        D_OPTION.edit4.setFont(fontNormal);
        D_OPTION.buttonApply.setFont(fontNormal);
        D_OPTION.buttonDefault.setFont(fontNormal);
        D_OPTION.solver.setBounds(left, top + 2, D_OPTION.windowWidth, D_OPTION.fontHeight);
        final int n = top + D_OPTION.windowHeight - D_OPTION.boxHeight - 2;
        final int n2 = D_OPTION.windowWidth / 2 - 4;
        D_OPTION.buttonDefault.setBounds(left + 2, n, n2, D_OPTION.boxHeight);
        D_OPTION.buttonApply.setBounds(left + 4 + n2, n, n2, D_OPTION.boxHeight);
        D_OPTION.infoTop = top + D_OPTION.fontHeight * 2;
        D_OPTION.infoLeft = left + D_OPTION.letterWidth;
        D_OPTION.edit1.setEditable(true);
        D_OPTION.edit2.setEditable(true);
        D_OPTION.edit3.setEditable(true);
        D_OPTION.edit4.setEditable(true);
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        D_OPTION.OPTION.setVisible(false);
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
        if (D_GRAPH.cbox[0].getState()) {
            D_OPTION.solver.select(0);
        }
        else if (D_GRAPH.cbox[1].getState()) {
            D_OPTION.solver.select(1);
        }
        else if (D_GRAPH.cbox[2].getState()) {
            D_OPTION.solver.select(2);
        }
        else if (D_GRAPH.cbox[3].getState()) {
            D_OPTION.solver.select(3);
        }
        else if (D_GRAPH.cbox[4].getState()) {
            D_OPTION.solver.select(4);
        }
        this.setSolverValues();
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public static void QuitD_OPTION() {
        if (D_OPTION.OPTION == null) {
            return;
        }
        D_OPTION.OPTION.dispose();
        D_OPTION.OPTION = null;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        final String selectedItem = D_OPTION.solver.getSelectedItem();
        if (actionCommand.equals("Apply")) {
            if (selectedItem.equals("Euler's Tangent Line")) {
                DFIELD.EulerStepSize = this.getValue(D_OPTION.edit1, DFIELD.EulerStepSize);
            }
            else if (selectedItem.equals("Runge-Kutta 2 (Improved Euler)")) {
                DFIELD.RungeKutta2StepSize = this.getValue(D_OPTION.edit1, DFIELD.RungeKutta2StepSize);
            }
            else if (selectedItem.equals("Runge-Kutta fourth-order")) {
                DFIELD.RungeKutta4StepSize = this.getValue(D_OPTION.edit1, DFIELD.RungeKutta4StepSize);
            }
            else if (selectedItem.equals("Runge-Kutta-Fehlberg Adaptive Step")) {
                DFIELD.RKF_Epsilon = this.getValue(D_OPTION.edit1, DFIELD.RKF_Epsilon);
                DFIELD.RKF_Maxh = this.getValue(D_OPTION.edit2, DFIELD.RKF_Maxh);
                DFIELD.RKF_Minh = this.getValue(D_OPTION.edit3, DFIELD.RKF_Minh);
                if (DFIELD.RKF_Maxh < DFIELD.RKF_Minh) {
                    DFIELD.RKF_Maxh = (D_OPTION.g.xmax - D_OPTION.g.xmin) / 20.0;
                    DFIELD.RKF_Minh = 1.0E-14;
                }
            }
            else if (selectedItem.equals("Dormand-Prince Adaptive Step")) {
                DFIELD.Dormand_Epsilon = this.getValue(D_OPTION.edit1, DFIELD.Dormand_Epsilon);
                DFIELD.Dormand_Maxh = this.getValue(D_OPTION.edit2, DFIELD.Dormand_Maxh);
                DFIELD.Dormand_Minh = this.getValue(D_OPTION.edit3, DFIELD.Dormand_Minh);
                if (DFIELD.Dormand_Maxh < DFIELD.Dormand_Minh) {
                    DFIELD.Dormand_Maxh = (D_OPTION.g.xmax - D_OPTION.g.xmin) / 10.0;
                    DFIELD.Dormand_Minh = 1.0E-14;
                }
                DFIELD.Dormand_refine = (int)this.getValue(D_OPTION.edit4, DFIELD.Dormand_Minh);
                if (DFIELD.Dormand_refine < 1) {
                    DFIELD.Dormand_refine = 1;
                }
            }
            this.setSolverValues();
            return;
        }
        if (selectedItem.equals("Euler's Tangent Line")) {
            D_OPTION.edit1.setText(Double.toString((D_GRAPH.g.xmax - D_GRAPH.g.xmin) / 1000.0));
        }
        else if (selectedItem.equals("Runge-Kutta 2 (Improved Euler)")) {
            D_OPTION.edit1.setText(Double.toString((D_GRAPH.g.xmax - D_GRAPH.g.xmin) / 500.0));
        }
        else if (selectedItem.equals("Runge-Kutta fourth-order")) {
            D_OPTION.edit1.setText(Double.toString((D_GRAPH.g.xmax - D_GRAPH.g.xmin) / 250.0));
        }
        else if (selectedItem.equals("Runge-Kutta-Fehlberg Adaptive Step")) {
            final double n = (D_GRAPH.g.xmax - D_GRAPH.g.xmin) / 20.0;
            final double pow = Math.pow(10.0, -15 + Math.ceil(C_TOOL.log10(Math.abs(D_GRAPH.g.xmax))));
            D_OPTION.edit1.setText("1E-6");
            D_OPTION.edit2.setText(Double.toString(n));
            D_OPTION.edit3.setText(Double.toString(pow));
        }
        else if (selectedItem.equals("Dormand-Prince Adaptive Step")) {
            final double n2 = (D_GRAPH.g.xmax - D_GRAPH.g.xmin) / 10.0;
            final double pow2 = Math.pow(10.0, -15 + Math.ceil(C_TOOL.log10(Math.abs(D_GRAPH.g.xmax))));
            D_OPTION.edit1.setText("1E-6");
            D_OPTION.edit2.setText(Double.toString(n2));
            D_OPTION.edit3.setText(Double.toString(pow2));
            D_OPTION.edit4.setText("4");
        }
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getStateChange() == 2) {
            return;
        }
        this.setSolverValues();
    }
    
    public double getValue(final TextField textField, final double n) {
        try {
            return Double.valueOf(textField.getText());
        }
        catch (NumberFormatException ex) {
            return n;
        }
    }
    
    public void setSolverValues() {
        final String selectedItem = D_OPTION.solver.getSelectedItem();
        D_OPTION.label1.setText("Step Size:");
        D_OPTION.label1.setBounds(D_OPTION.infoLeft, D_OPTION.infoTop + D_OPTION.fontHeight, D_OPTION.letterWidth * 8, D_OPTION.fontHeight);
        D_OPTION.edit1.setBounds(D_OPTION.infoLeft + D_OPTION.letterWidth * 9, D_OPTION.infoTop + D_OPTION.fontHeight - 3, D_OPTION.letterWidth * 12, D_OPTION.boxHeight);
        D_OPTION.label1.setVisible(true);
        D_OPTION.label2.setVisible(false);
        D_OPTION.label3.setVisible(false);
        D_OPTION.label4.setVisible(false);
        D_OPTION.edit1.setVisible(true);
        D_OPTION.edit2.setVisible(false);
        D_OPTION.edit3.setVisible(false);
        D_OPTION.edit4.setVisible(false);
        for (int i = 0; i <= 4; ++i) {
            D_GRAPH.cbox[i].setState(false);
        }
        if (selectedItem.equals("Euler's Tangent Line")) {
            D_OPTION.edit1.setText(Double.toString(DFIELD.EulerStepSize));
            D_GRAPH.cbox[0].setState(true);
        }
        else if (selectedItem.equals("Runge-Kutta 2 (Improved Euler)")) {
            D_OPTION.edit1.setText(Double.toString(DFIELD.RungeKutta2StepSize));
            D_GRAPH.cbox[1].setState(true);
        }
        else if (selectedItem.equals("Runge-Kutta fourth-order")) {
            D_OPTION.edit1.setText(Double.toString(DFIELD.RungeKutta4StepSize));
            D_GRAPH.cbox[2].setState(true);
        }
        else if (selectedItem.equals("Runge-Kutta-Fehlberg Adaptive Step")) {
            D_OPTION.label1.setText("Error Tolerance:");
            D_OPTION.label2.setText("Maximum Step Size:");
            D_OPTION.label3.setText("Minimum Step Size:");
            D_OPTION.label1.setBounds(D_OPTION.infoLeft, D_OPTION.infoTop + 2, D_OPTION.letterWidth * 17, D_OPTION.fontHeight);
            D_OPTION.label2.setBounds(D_OPTION.infoLeft, D_OPTION.infoTop + D_OPTION.fontHeight * 2, D_OPTION.letterWidth * 17, D_OPTION.fontHeight);
            D_OPTION.label3.setBounds(D_OPTION.infoLeft, D_OPTION.infoTop + D_OPTION.fontHeight * 4, D_OPTION.letterWidth * 17, D_OPTION.fontHeight);
            D_OPTION.edit1.setBounds(D_OPTION.infoLeft + D_OPTION.letterWidth * 17, D_OPTION.infoTop, D_OPTION.letterWidth * 11, D_OPTION.boxHeight);
            D_OPTION.edit2.setBounds(D_OPTION.infoLeft + D_OPTION.letterWidth * 17, D_OPTION.infoTop + D_OPTION.fontHeight * 2 - 3, D_OPTION.letterWidth * 11, D_OPTION.boxHeight);
            D_OPTION.edit3.setBounds(D_OPTION.infoLeft + D_OPTION.letterWidth * 17, D_OPTION.infoTop + D_OPTION.fontHeight * 4 - 3, D_OPTION.letterWidth * 11, D_OPTION.boxHeight);
            D_OPTION.edit1.setText(Double.toString(DFIELD.RKF_Epsilon));
            D_OPTION.edit2.setText(Double.toString(DFIELD.RKF_Maxh));
            D_OPTION.edit3.setText(Double.toString(DFIELD.RKF_Minh));
            D_OPTION.label1.setVisible(true);
            D_OPTION.label2.setVisible(true);
            D_OPTION.label3.setVisible(true);
            D_OPTION.edit1.setVisible(true);
            D_OPTION.edit2.setVisible(true);
            D_OPTION.edit3.setVisible(true);
            D_GRAPH.cbox[3].setState(true);
        }
        else if (selectedItem.equals("Dormand-Prince Adaptive Step")) {
            D_OPTION.label1.setText("Error Tolerance:");
            D_OPTION.label2.setText("Maximum Step Size:");
            D_OPTION.label3.setText("Minimum Step Size:");
            D_OPTION.label4.setText("Plot Steps / Computation Step:");
            final int n = D_OPTION.boxHeight + 4;
            D_OPTION.label1.setBounds(D_OPTION.infoLeft, D_OPTION.infoTop + 2, D_OPTION.letterWidth * 17, D_OPTION.fontHeight);
            D_OPTION.label2.setBounds(D_OPTION.infoLeft, D_OPTION.infoTop + n, D_OPTION.letterWidth * 17, D_OPTION.fontHeight);
            D_OPTION.label3.setBounds(D_OPTION.infoLeft, D_OPTION.infoTop + n * 2, D_OPTION.letterWidth * 17, D_OPTION.fontHeight);
            D_OPTION.label4.setBounds(D_OPTION.infoLeft, D_OPTION.infoTop + n * 3, D_OPTION.letterWidth * 25, D_OPTION.fontHeight);
            D_OPTION.edit1.setBounds(D_OPTION.infoLeft + D_OPTION.letterWidth * 17, D_OPTION.infoTop, D_OPTION.letterWidth * 11, D_OPTION.boxHeight);
            D_OPTION.edit2.setBounds(D_OPTION.infoLeft + D_OPTION.letterWidth * 17, D_OPTION.infoTop + n - 3, D_OPTION.letterWidth * 11, D_OPTION.boxHeight);
            D_OPTION.edit3.setBounds(D_OPTION.infoLeft + D_OPTION.letterWidth * 17, D_OPTION.infoTop + n * 2 - 3, D_OPTION.letterWidth * 11, D_OPTION.boxHeight);
            D_OPTION.edit4.setBounds(D_OPTION.infoLeft + D_OPTION.letterWidth * 25, D_OPTION.infoTop + n * 3 - 3, D_OPTION.letterWidth * 6, D_OPTION.boxHeight);
            D_OPTION.edit1.setText(Double.toString(DFIELD.Dormand_Epsilon));
            D_OPTION.edit2.setText(Double.toString(DFIELD.Dormand_Maxh));
            D_OPTION.edit3.setText(Double.toString(DFIELD.Dormand_Minh));
            D_OPTION.edit4.setText(Integer.toString(DFIELD.Dormand_refine));
            D_OPTION.label1.setVisible(true);
            D_OPTION.label2.setVisible(true);
            D_OPTION.label3.setVisible(true);
            D_OPTION.label4.setVisible(true);
            D_OPTION.edit1.setVisible(true);
            D_OPTION.edit2.setVisible(true);
            D_OPTION.edit3.setVisible(true);
            D_OPTION.edit4.setVisible(true);
            D_GRAPH.cbox[4].setState(true);
        }
    }
    
    static {
        D_OPTION.OPTION = null;
        D_OPTION.label1 = new Label("", 0);
        D_OPTION.label2 = new Label("", 0);
        D_OPTION.label3 = new Label("", 0);
        D_OPTION.label4 = new Label("", 0);
        D_OPTION.edit1 = new TextField("", 8);
        D_OPTION.edit2 = new TextField("", 8);
        D_OPTION.edit3 = new TextField("", 8);
        D_OPTION.edit4 = new TextField("", 8);
        D_OPTION.buttonApply = new Button("Apply");
        D_OPTION.buttonDefault = new Button("Default");
    }
}
