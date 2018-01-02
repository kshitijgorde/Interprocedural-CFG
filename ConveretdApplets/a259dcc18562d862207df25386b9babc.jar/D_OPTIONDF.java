import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Component;
import java.awt.Button;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.TextField;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class D_OPTIONDF extends Frame implements WindowListener, ActionListener
{
    public static D_OPTIONDF DF;
    public static Label label3;
    public static Label label1;
    public static Label label2;
    public static Label label4;
    public static Label label5;
    public static Label label6;
    public static TextField edit1;
    public static TextField edit2;
    public static TextField edit3;
    public static TextField edit4;
    public static CheckboxGroup g;
    public static Checkbox cboxArrow;
    public static Checkbox cboxLine;
    public static Checkbox cboxNone;
    public static int fontHeight;
    public static int letterWidth;
    public static int boxHeight;
    public static int infoTop;
    public static int infoLeft;
    public static int windowWidth;
    public static int windowHeight;
    
    public static void OPTIONDF_MAIN() {
        if (D_OPTIONDF.DF != null) {
            D_OPTIONDF.DF.show();
            return;
        }
        (D_OPTIONDF.DF = new D_OPTIONDF()).setResizable(false);
        D_OPTIONDF.DF.setTitle("DFIELD:  Direction Field Settings");
        DFIELD.AddMenuWindow(D_OPTIONDF.DF);
        D_OPTIONDF.DF.setBackground(Color.lightGray);
        final Font fontNormal = C_TOOL.fontNormal;
        D_OPTIONDF.DF.setFont(fontNormal);
        final FontMetrics fontMetrics = D_OPTIONDF.DF.getFontMetrics(fontNormal);
        D_OPTIONDF.fontHeight = (D_OPTIONDF.fontHeight = fontMetrics.getHeight());
        D_OPTIONDF.letterWidth = fontMetrics.stringWidth("X");
        D_OPTIONDF.windowWidth = D_OPTIONDF.letterWidth * 37;
        D_OPTIONDF.boxHeight = (int)(D_OPTIONDF.fontHeight * 1.5);
        final int n = (int)(D_OPTIONDF.fontHeight * 1.8);
        D_OPTIONDF.DF.setBounds(25, 25, D_OPTIONDF.windowWidth, D_OPTIONDF.fontHeight * 18);
        D_OPTIONDF.DF.show();
        D_OPTIONDF.DF.addWindowListener(D_OPTIONDF.DF);
        final Dimension size = D_OPTIONDF.DF.getSize();
        D_OPTIONDF.windowWidth = size.width - D_OPTIONDF.DF.getInsets().left - D_OPTIONDF.DF.getInsets().right;
        D_OPTIONDF.windowHeight = size.height - D_OPTIONDF.DF.getInsets().top - D_OPTIONDF.DF.getInsets().bottom;
        final int top = D_OPTIONDF.DF.getInsets().top;
        final int left = D_OPTIONDF.DF.getInsets().left;
        D_OPTIONDF.DF.setLayout(null);
        final Button button = new Button("Apply");
        D_OPTIONDF.DF.add(D_OPTIONDF.label1);
        D_OPTIONDF.DF.add(D_OPTIONDF.label2);
        D_OPTIONDF.DF.add(D_OPTIONDF.label3);
        D_OPTIONDF.DF.add(D_OPTIONDF.edit1);
        D_OPTIONDF.DF.add(D_OPTIONDF.edit2);
        D_OPTIONDF.DF.add(button);
        button.addActionListener(D_OPTIONDF.DF);
        D_OPTIONDF.DF.add(D_OPTIONDF.cboxArrow);
        D_OPTIONDF.DF.add(D_OPTIONDF.cboxLine);
        D_OPTIONDF.DF.add(D_OPTIONDF.cboxNone);
        D_OPTIONDF.DF.add(D_OPTIONDF.label4);
        D_OPTIONDF.DF.add(D_OPTIONDF.label5);
        D_OPTIONDF.DF.add(D_OPTIONDF.label6);
        D_OPTIONDF.DF.add(D_OPTIONDF.edit3);
        D_OPTIONDF.DF.add(D_OPTIONDF.edit4);
        D_OPTIONDF.label1.setFont(fontNormal);
        D_OPTIONDF.label2.setFont(fontNormal);
        D_OPTIONDF.label3.setFont(fontNormal);
        D_OPTIONDF.edit1.setFont(fontNormal);
        D_OPTIONDF.edit2.setFont(fontNormal);
        button.setFont(fontNormal);
        D_OPTIONDF.cboxArrow.setFont(fontNormal);
        D_OPTIONDF.cboxLine.setFont(fontNormal);
        D_OPTIONDF.cboxNone.setFont(fontNormal);
        D_OPTIONDF.label4.setFont(fontNormal);
        D_OPTIONDF.label5.setFont(fontNormal);
        D_OPTIONDF.label6.setFont(fontNormal);
        D_OPTIONDF.edit3.setFont(fontNormal);
        D_OPTIONDF.edit4.setFont(fontNormal);
        D_OPTIONDF.label6.setText(String.valueOf(String.valueOf(new StringBuffer("times ").append(DFIELD.diffyQ.xName).append(" view window."))));
        D_OPTIONDF.label5.setText(String.valueOf(String.valueOf(new StringBuffer("times ").append(DFIELD.diffyQ.yName).append(" view window."))));
        D_OPTIONDF.label3.setBounds(left + D_OPTIONDF.letterWidth, top + 2, D_OPTIONDF.windowWidth - D_OPTIONDF.letterWidth * 2, D_OPTIONDF.fontHeight);
        button.setBounds(left + 2, top + D_OPTIONDF.windowHeight - D_OPTIONDF.boxHeight - 2, D_OPTIONDF.windowWidth - 4, D_OPTIONDF.boxHeight);
        D_OPTIONDF.infoTop = top + n;
        D_OPTIONDF.infoLeft = left + D_OPTIONDF.letterWidth * 2;
        D_OPTIONDF.label1.setBounds(D_OPTIONDF.infoLeft, D_OPTIONDF.infoTop, D_OPTIONDF.letterWidth * 7, D_OPTIONDF.fontHeight);
        D_OPTIONDF.label2.setBounds(D_OPTIONDF.infoLeft, D_OPTIONDF.infoTop + n, D_OPTIONDF.letterWidth * 7, D_OPTIONDF.fontHeight);
        D_OPTIONDF.edit1.setBounds(D_OPTIONDF.infoLeft + D_OPTIONDF.letterWidth * 8, D_OPTIONDF.infoTop - 3, D_OPTIONDF.letterWidth * 9, D_OPTIONDF.boxHeight);
        D_OPTIONDF.edit2.setBounds(D_OPTIONDF.infoLeft + D_OPTIONDF.letterWidth * 8, D_OPTIONDF.infoTop + n - 3, D_OPTIONDF.letterWidth * 9, D_OPTIONDF.boxHeight);
        D_OPTIONDF.cboxArrow.setBounds(D_OPTIONDF.infoLeft + D_OPTIONDF.letterWidth * 23, D_OPTIONDF.infoTop + D_OPTIONDF.boxHeight, D_OPTIONDF.letterWidth * 9, D_OPTIONDF.fontHeight);
        D_OPTIONDF.cboxLine.setBounds(D_OPTIONDF.infoLeft + D_OPTIONDF.letterWidth * 23, D_OPTIONDF.infoTop + D_OPTIONDF.boxHeight * 2, D_OPTIONDF.letterWidth * 9, D_OPTIONDF.fontHeight);
        D_OPTIONDF.cboxNone.setBounds(D_OPTIONDF.infoLeft + D_OPTIONDF.letterWidth * 23, D_OPTIONDF.infoTop + D_OPTIONDF.boxHeight * 3, D_OPTIONDF.letterWidth * 9, D_OPTIONDF.fontHeight);
        final int n2 = D_OPTIONDF.infoTop + n * 3;
        D_OPTIONDF.label4.setBounds(left + D_OPTIONDF.letterWidth, n2, D_OPTIONDF.windowWidth - D_OPTIONDF.letterWidth * 2, D_OPTIONDF.fontHeight);
        D_OPTIONDF.label5.setBounds(D_OPTIONDF.infoLeft + D_OPTIONDF.letterWidth * 10, n2 + n, D_OPTIONDF.letterWidth * 19, D_OPTIONDF.fontHeight);
        D_OPTIONDF.label6.setBounds(D_OPTIONDF.infoLeft + D_OPTIONDF.letterWidth * 10, n2 + n * 2, D_OPTIONDF.letterWidth * 19, D_OPTIONDF.fontHeight);
        D_OPTIONDF.edit3.setBounds(D_OPTIONDF.infoLeft, n2 + n - 3, D_OPTIONDF.letterWidth * 9, D_OPTIONDF.boxHeight);
        D_OPTIONDF.edit4.setBounds(D_OPTIONDF.infoLeft, n2 + n * 2 - 3, D_OPTIONDF.letterWidth * 9, D_OPTIONDF.boxHeight);
        D_OPTIONDF.edit1.setEditable(true);
        D_OPTIONDF.edit2.setEditable(true);
        D_OPTIONDF.edit3.setEditable(true);
        D_OPTIONDF.edit4.setEditable(true);
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        D_OPTIONDF.DF.setVisible(false);
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
        updateValues();
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (!actionEvent.getActionCommand().equals("Apply")) {
            return;
        }
        DFIELD.FieldPointsRow = this.getValue(D_OPTIONDF.edit1);
        DFIELD.FieldPointsColumn = this.getValue(D_OPTIONDF.edit2);
        DFIELD.ComputationWindowY = this.getDoubleValue(D_OPTIONDF.edit3);
        DFIELD.ComputationWindowX = this.getDoubleValue(D_OPTIONDF.edit4);
        if (D_OPTIONDF.cboxArrow.getState()) {
            DFIELD.ShowDF = true;
            DFIELD.ShowArrows = true;
        }
        else if (D_OPTIONDF.cboxLine.getState()) {
            DFIELD.ShowDF = true;
            DFIELD.ShowArrows = false;
        }
        else {
            DFIELD.ShowDF = false;
            DFIELD.ShowArrows = false;
        }
        updateValues();
        D_GRAPH.ReDrawEverything();
    }
    
    public static void updateValues() {
        D_OPTIONDF.edit1.setText(Integer.toString(DFIELD.FieldPointsRow));
        D_OPTIONDF.edit2.setText(Integer.toString(DFIELD.FieldPointsColumn));
        D_OPTIONDF.edit3.setText(Double.toString(DFIELD.ComputationWindowY));
        D_OPTIONDF.edit4.setText(Double.toString(DFIELD.ComputationWindowX));
    }
    
    public int getValue(final TextField textField) {
        int n;
        try {
            n = (int)(double)Double.valueOf(textField.getText());
        }
        catch (NumberFormatException ex) {
            return 5;
        }
        if (n < 5) {
            n = 5;
        }
        return n;
    }
    
    public double getDoubleValue(final TextField textField) {
        double doubleValue;
        try {
            doubleValue = Double.valueOf(textField.getText());
        }
        catch (NumberFormatException ex) {
            return 100.0;
        }
        if (doubleValue < 1.0) {
            doubleValue = 1.0;
        }
        return doubleValue;
    }
    
    public static void QuitD_OPTIONDF() {
        if (D_OPTIONDF.DF == null) {
            return;
        }
        D_OPTIONDF.DF.dispose();
        D_OPTIONDF.DF = null;
    }
    
    static {
        D_OPTIONDF.DF = null;
        D_OPTIONDF.label3 = new Label("Number of Field Points Per:", 0);
        D_OPTIONDF.label1 = new Label("Row:", 0);
        D_OPTIONDF.label2 = new Label("Column:", 0);
        D_OPTIONDF.label4 = new Label("Computation window is:", 0);
        D_OPTIONDF.label5 = new Label("", 0);
        D_OPTIONDF.label6 = new Label("", 0);
        D_OPTIONDF.edit1 = new TextField("", 8);
        D_OPTIONDF.edit2 = new TextField("", 8);
        D_OPTIONDF.edit3 = new TextField("", 8);
        D_OPTIONDF.edit4 = new TextField("", 8);
        D_OPTIONDF.g = new CheckboxGroup();
        D_OPTIONDF.cboxArrow = new Checkbox("Arrows", D_OPTIONDF.g, true);
        D_OPTIONDF.cboxLine = new Checkbox("Lines", D_OPTIONDF.g, false);
        D_OPTIONDF.cboxNone = new Checkbox("none", D_OPTIONDF.g, false);
    }
}
