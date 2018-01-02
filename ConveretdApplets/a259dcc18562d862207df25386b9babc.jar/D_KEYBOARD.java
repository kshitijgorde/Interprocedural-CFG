import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Checkbox;
import java.awt.Button;
import java.awt.FontMetrics;
import java.awt.TextField;
import java.awt.Label;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class D_KEYBOARD extends Frame implements WindowListener, ActionListener, ItemListener
{
    public static D_KEYBOARD KEYBOARD;
    public static Label label3;
    public static Label label1;
    public static Label label2;
    public static Label labelRange;
    public static TextField edit1;
    public static TextField edit2;
    public static TextField editMin;
    public static TextField editMax;
    public static int fontHeight;
    public static int letterWidth;
    public static int boxHeight;
    public static int infoLeft;
    public static int windowWidth;
    public static int windowHeight;
    public static int windowTop;
    public static int windowLeft;
    public static FontMetrics fm;
    public static Button buttonSolve;
    public static Checkbox cboxInterval;
    
    public static void KEYBOARD_MAIN() {
        if (D_KEYBOARD.KEYBOARD != null) {
            D_KEYBOARD.KEYBOARD.show();
            return;
        }
        (D_KEYBOARD.KEYBOARD = new D_KEYBOARD()).setResizable(false);
        D_KEYBOARD.KEYBOARD.setTitle("DFIELD:  Keyboard Input");
        DFIELD.AddMenuWindow(D_KEYBOARD.KEYBOARD);
        D_KEYBOARD.KEYBOARD.setBackground(Color.lightGray);
        D_KEYBOARD.KEYBOARD.setLocation(25, 25);
        D_KEYBOARD.KEYBOARD.show();
        D_KEYBOARD.KEYBOARD.addWindowListener(D_KEYBOARD.KEYBOARD);
        D_KEYBOARD.KEYBOARD.setLayout(null);
        D_KEYBOARD.cboxInterval = new Checkbox("Specify a computation interval.", false);
        D_KEYBOARD.buttonSolve = new Button("Solve");
        D_KEYBOARD.KEYBOARD.add(D_KEYBOARD.label3);
        D_KEYBOARD.KEYBOARD.add(D_KEYBOARD.label1);
        D_KEYBOARD.KEYBOARD.add(D_KEYBOARD.label2);
        D_KEYBOARD.KEYBOARD.add(D_KEYBOARD.edit1);
        D_KEYBOARD.KEYBOARD.add(D_KEYBOARD.edit2);
        D_KEYBOARD.KEYBOARD.add(D_KEYBOARD.cboxInterval);
        D_KEYBOARD.cboxInterval.addItemListener(D_KEYBOARD.KEYBOARD);
        D_KEYBOARD.KEYBOARD.add(D_KEYBOARD.editMin);
        D_KEYBOARD.KEYBOARD.add(D_KEYBOARD.editMax);
        D_KEYBOARD.KEYBOARD.add(D_KEYBOARD.labelRange);
        D_KEYBOARD.KEYBOARD.add(D_KEYBOARD.buttonSolve);
        D_KEYBOARD.buttonSolve.addActionListener(D_KEYBOARD.KEYBOARD);
        D_KEYBOARD.edit1.setEditable(true);
        D_KEYBOARD.edit2.setEditable(true);
        D_KEYBOARD.editMin.setVisible(false);
        D_KEYBOARD.editMax.setVisible(false);
        D_KEYBOARD.labelRange.setVisible(false);
        setupD_KEYBOARD();
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        D_KEYBOARD.KEYBOARD.setVisible(false);
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
        setupD_KEYBOARD();
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (!actionEvent.getActionCommand().equals("Solve")) {
            return;
        }
        final double value = this.getValue(D_KEYBOARD.edit1);
        final double value2 = this.getValue(D_KEYBOARD.edit2);
        if (D_KEYBOARD.cboxInterval.getState()) {
            final double value3 = this.getValue(D_KEYBOARD.editMin);
            final double value4 = this.getValue(D_KEYBOARD.editMax);
            String s = "";
            boolean b = false;
            if (value3 > value4) {
                s = String.valueOf(String.valueOf(new StringBuffer("The minimum value of ").append(DFIELD.diffyQ.xName).append(" must be less then the maximum.")));
                b = true;
            }
            else if (value < value3 || value > value4) {
                s = String.valueOf(String.valueOf(new StringBuffer("The initial value of ").append(DFIELD.diffyQ.xName).append(" must be in the computation interval.")));
                b = true;
            }
            if (b) {
                new C_DIALOG(D_KEYBOARD.KEYBOARD, s).show();
                return;
            }
            D_GRAPH.PlotNewSolution(value, value2, value3, value4);
        }
        else {
            D_GRAPH.PlotNewSolution(value, value2);
        }
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (D_KEYBOARD.cboxInterval.getState()) {
            D_KEYBOARD.editMin.setVisible(true);
            D_KEYBOARD.editMax.setVisible(true);
            D_KEYBOARD.labelRange.setVisible(true);
        }
        else {
            D_KEYBOARD.editMin.setVisible(false);
            D_KEYBOARD.editMax.setVisible(false);
            D_KEYBOARD.labelRange.setVisible(false);
        }
    }
    
    public static void setupD_KEYBOARD() {
        final Font fontNormal = C_TOOL.fontNormal;
        D_KEYBOARD.KEYBOARD.setFont(fontNormal);
        D_KEYBOARD.fm = D_KEYBOARD.KEYBOARD.getFontMetrics(fontNormal);
        D_KEYBOARD.fontHeight = (D_KEYBOARD.fontHeight = D_KEYBOARD.fm.getHeight());
        D_KEYBOARD.letterWidth = D_KEYBOARD.fm.stringWidth("X");
        D_KEYBOARD.boxHeight = (int)(D_KEYBOARD.fontHeight * 1.5);
        D_KEYBOARD.KEYBOARD.setSize(D_KEYBOARD.letterWidth * 31, D_KEYBOARD.fontHeight * 15);
        final Dimension size = D_KEYBOARD.KEYBOARD.getSize();
        D_KEYBOARD.windowWidth = size.width - D_KEYBOARD.KEYBOARD.getInsets().left - D_KEYBOARD.KEYBOARD.getInsets().right;
        D_KEYBOARD.windowHeight = size.height - D_KEYBOARD.KEYBOARD.getInsets().top - D_KEYBOARD.KEYBOARD.getInsets().bottom;
        D_KEYBOARD.windowTop = D_KEYBOARD.KEYBOARD.getInsets().top;
        D_KEYBOARD.windowLeft = D_KEYBOARD.KEYBOARD.getInsets().left;
        D_KEYBOARD.label3.setFont(fontNormal);
        D_KEYBOARD.label1.setFont(fontNormal);
        D_KEYBOARD.label2.setFont(fontNormal);
        D_KEYBOARD.edit1.setFont(fontNormal);
        D_KEYBOARD.edit2.setFont(fontNormal);
        D_KEYBOARD.cboxInterval.setFont(fontNormal);
        D_KEYBOARD.editMin.setFont(fontNormal);
        D_KEYBOARD.editMax.setFont(fontNormal);
        D_KEYBOARD.labelRange.setFont(fontNormal);
        D_KEYBOARD.buttonSolve.setFont(fontNormal);
        D_KEYBOARD.label1.setText(String.valueOf(String.valueOf(DFIELD.diffyQ.xName)).concat(" ="));
        D_KEYBOARD.label2.setText(String.valueOf(String.valueOf(DFIELD.diffyQ.yName)).concat(" ="));
        final int stringWidth = D_KEYBOARD.fm.stringWidth(String.valueOf(String.valueOf(D_KEYBOARD.label1.getText())).concat(" "));
        final int stringWidth2 = D_KEYBOARD.fm.stringWidth(String.valueOf(String.valueOf(D_KEYBOARD.label2.getText())).concat(" "));
        final int n = D_KEYBOARD.infoLeft + stringWidth + D_KEYBOARD.letterWidth;
        final int n2 = D_KEYBOARD.infoLeft + stringWidth2 + D_KEYBOARD.letterWidth;
        final int n3 = D_KEYBOARD.windowTop + 2;
        D_KEYBOARD.label3.setBounds(D_KEYBOARD.windowLeft + D_KEYBOARD.letterWidth, n3, D_KEYBOARD.windowWidth - D_KEYBOARD.letterWidth * 2, D_KEYBOARD.fontHeight);
        final int n4 = n3 + D_KEYBOARD.boxHeight + 3;
        D_KEYBOARD.infoLeft = D_KEYBOARD.windowLeft + D_KEYBOARD.letterWidth * 2;
        D_KEYBOARD.label1.setBounds(D_KEYBOARD.infoLeft, n4, stringWidth, D_KEYBOARD.fontHeight);
        D_KEYBOARD.label2.setBounds(D_KEYBOARD.infoLeft, n4 + D_KEYBOARD.boxHeight, stringWidth2, D_KEYBOARD.fontHeight);
        D_KEYBOARD.edit1.setBounds(n, n4 - 3, D_KEYBOARD.letterWidth * 9, D_KEYBOARD.boxHeight);
        D_KEYBOARD.edit2.setBounds(n2, n4 + D_KEYBOARD.boxHeight - 3, D_KEYBOARD.letterWidth * 9, D_KEYBOARD.boxHeight);
        final int n5 = D_KEYBOARD.windowLeft + D_KEYBOARD.letterWidth;
        final int n6 = n4 + (int)(D_KEYBOARD.boxHeight * 2.5);
        D_KEYBOARD.cboxInterval.setBounds(n5, n6, D_KEYBOARD.windowWidth - 4, D_KEYBOARD.boxHeight);
        final int n7 = n6 + D_KEYBOARD.boxHeight;
        D_KEYBOARD.labelRange.setText(String.valueOf(String.valueOf(new StringBuffer(" <=").append(DFIELD.diffyQ.xName).append("<= "))));
        final int stringWidth3 = D_KEYBOARD.fm.stringWidth(D_KEYBOARD.labelRange.getText());
        final int n8 = n5 + D_KEYBOARD.letterWidth * 7;
        final int n9 = n8 + stringWidth3;
        D_KEYBOARD.editMin.setBounds(n5, n7, D_KEYBOARD.letterWidth * 7, D_KEYBOARD.boxHeight);
        D_KEYBOARD.labelRange.setBounds(n8, n7, stringWidth3, D_KEYBOARD.boxHeight);
        D_KEYBOARD.editMax.setBounds(n9, n7, D_KEYBOARD.letterWidth * 7, D_KEYBOARD.boxHeight);
        final int n10 = n7 + D_KEYBOARD.boxHeight + 2;
        D_KEYBOARD.buttonSolve.setBounds(D_KEYBOARD.windowLeft + 2, D_KEYBOARD.windowTop + D_KEYBOARD.windowHeight - D_KEYBOARD.boxHeight - 2, D_KEYBOARD.windowWidth - 4, D_KEYBOARD.boxHeight);
    }
    
    public double getValue(final TextField textField) {
        try {
            return Double.valueOf(textField.getText());
        }
        catch (NumberFormatException ex) {
            return 0.0;
        }
    }
    
    public static void QuitD_KEYBOARD() {
        if (D_KEYBOARD.KEYBOARD == null) {
            return;
        }
        D_KEYBOARD.KEYBOARD.dispose();
        D_KEYBOARD.KEYBOARD = null;
    }
    
    static {
        D_KEYBOARD.KEYBOARD = null;
        D_KEYBOARD.label3 = new Label("Enter the initial conditions:", 0);
        D_KEYBOARD.label1 = new Label("", 0);
        D_KEYBOARD.label2 = new Label("", 0);
        D_KEYBOARD.labelRange = new Label("", 1);
        D_KEYBOARD.edit1 = new TextField("", 8);
        D_KEYBOARD.edit2 = new TextField("", 8);
        D_KEYBOARD.editMin = new TextField("", 6);
        D_KEYBOARD.editMax = new TextField("", 6);
    }
}
