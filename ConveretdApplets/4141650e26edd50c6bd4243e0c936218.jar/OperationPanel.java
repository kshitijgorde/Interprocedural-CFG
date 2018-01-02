import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class OperationPanel extends Panel implements ActionListener
{
    Double i;
    double temp;
    private short opera;
    private short counter;
    private double result;
    private Button plus;
    private Button minus;
    private Button clear;
    private Button ac;
    private Button times;
    private Button divide;
    private Button equals;
    private Button sto;
    private Button rcl;
    private Button square;
    
    public OperationPanel() {
        this.plus = new Button("  +  ");
        this.minus = new Button("  -  ");
        this.clear = new Button("  C  ");
        this.ac = new Button(" AC ");
        this.times = new Button("  X  ");
        this.divide = new Button("  /  ");
        this.equals = new Button("  =  ");
        this.sto = new Button(" STO ");
        this.rcl = new Button(" RCL ");
        this.square = new Button(" SQRT ");
        this.setLayout(new GridLayout(5, 2, 10, 10));
        this.add(this.clear);
        this.add(this.ac);
        this.add(this.sto);
        this.add(this.rcl);
        this.add(this.plus);
        this.add(this.minus);
        this.add(this.times);
        this.add(this.divide);
        this.add(this.square);
        this.add(this.equals);
        this.clear.addActionListener(this);
        this.ac.addActionListener(this);
        this.plus.addActionListener(this);
        this.minus.addActionListener(this);
        this.times.addActionListener(this);
        this.divide.addActionListener(this);
        this.equals.addActionListener(this);
        this.square.addActionListener(this);
        this.sto.addActionListener(this);
        this.rcl.addActionListener(this);
    }
    
    public void addition() {
        if (this.counter == 0) {
            this.i = new Double(Calc1.display.getText());
            Calc1.display.setText("");
            this.result += this.i;
            ++this.counter;
            this.opera = 1;
            return;
        }
        if (this.counter != 0 && this.opera == 1) {
            this.i = new Double(Calc1.display.getText());
            Calc1.display.setText("");
            this.result += this.i;
            this.opera = 1;
            return;
        }
        if (this.counter != 0 && this.opera == 2) {
            this.i = new Double(Calc1.display.getText());
            Calc1.display.setText("");
            this.result -= this.i;
            this.opera = 1;
            return;
        }
        if (this.counter != 0 && this.opera == 3) {
            this.i = new Double(Calc1.display.getText());
            Calc1.display.setText("");
            this.result *= this.i;
            this.opera = 1;
            return;
        }
        if (this.counter != 0 && this.opera == 4) {
            this.i = new Double(Calc1.display.getText());
            Calc1.display.setText("");
            this.result /= this.i;
            this.opera = 1;
        }
    }
    
    public void minus() {
        if (this.counter == 0) {
            this.i = new Double(Calc1.display.getText());
            Calc1.display.setText("");
            this.result += this.i;
            ++this.counter;
            this.opera = 2;
            return;
        }
        if (this.counter != 0 && this.opera == 2) {
            this.i = new Double(Calc1.display.getText());
            Calc1.display.setText("");
            this.result -= this.i;
            this.opera = 2;
            return;
        }
        if (this.counter != 0 && this.opera == 1) {
            this.i = new Double(Calc1.display.getText());
            Calc1.display.setText("");
            this.result += this.i;
            this.opera = 2;
            return;
        }
        if (this.counter != 0 && this.opera == 3) {
            this.i = new Double(Calc1.display.getText());
            Calc1.display.setText("");
            this.result *= this.i;
            this.opera = 2;
            return;
        }
        if (this.counter != 0 && this.opera == 4) {
            this.i = new Double(Calc1.display.getText());
            Calc1.display.setText("");
            this.result /= this.i;
            this.opera = 2;
        }
    }
    
    public void times() {
        if (this.counter == 0) {
            this.i = new Double(Calc1.display.getText());
            Calc1.display.setText("");
            this.result += this.i;
            ++this.counter;
            this.opera = 3;
            return;
        }
        if (this.counter != 0 && this.opera == 3) {
            this.i = new Double(Calc1.display.getText());
            Calc1.display.setText("");
            this.result *= this.i;
            this.opera = 3;
            return;
        }
        if (this.counter != 0 && this.opera == 1) {
            this.i = new Double(Calc1.display.getText());
            Calc1.display.setText("");
            this.result += this.i;
            this.opera = 3;
            return;
        }
        if (this.counter != 0 && this.opera == 2) {
            this.i = new Double(Calc1.display.getText());
            Calc1.display.setText("");
            this.result -= this.i;
            this.opera = 3;
            return;
        }
        if (this.counter != 0 && this.opera == 4) {
            this.i = new Double(Calc1.display.getText());
            Calc1.display.setText("");
            this.result /= this.i;
            this.opera = 3;
        }
    }
    
    public void divide() {
        if (this.counter == 0) {
            this.i = new Double(Calc1.display.getText());
            Calc1.display.setText("");
            this.result += this.i;
            ++this.counter;
            this.opera = 4;
            return;
        }
        if (this.counter != 0 && this.opera == 4) {
            this.i = new Double(Calc1.display.getText());
            Calc1.display.setText("");
            this.result /= this.i;
            this.opera = 4;
            return;
        }
        if (this.counter != 0 && this.opera == 1) {
            this.i = new Double(Calc1.display.getText());
            Calc1.display.setText("");
            this.result += this.i;
            this.opera = 4;
            return;
        }
        if (this.counter != 0 && this.opera == 2) {
            this.i = new Double(Calc1.display.getText());
            Calc1.display.setText("");
            this.result -= this.i;
            this.opera = 4;
            return;
        }
        if (this.counter != 0 && this.opera == 3) {
            this.i = new Double(Calc1.display.getText());
            Calc1.display.setText("");
            this.result *= this.i;
            this.opera = 4;
        }
    }
    
    public void equals() {
        if (this.opera == 1) {
            this.i = new Double(Calc1.display.getText());
            Calc1.display.setText("");
            this.result += this.i;
            Calc1.display.setText(String.valueOf(this.result));
        }
        else if (this.opera == 2) {
            this.i = new Double(Calc1.display.getText());
            Calc1.display.setText("");
            this.result -= this.i;
            Calc1.display.setText(String.valueOf(this.result));
        }
        else if (this.opera == 3) {
            this.i = new Double(Calc1.display.getText());
            Calc1.display.setText("");
            this.result *= this.i;
            Calc1.display.setText(String.valueOf(this.result));
        }
        else if (this.opera == 4) {
            this.i = new Double(Calc1.display.getText());
            Calc1.display.setText("");
            this.result /= this.i;
            Calc1.display.setText(String.valueOf(this.result));
        }
        this.counter = 0;
        this.result = 0.0;
    }
    
    public void clear() {
        this.plus.setEnabled(true);
        this.minus.setEnabled(true);
        this.times.setEnabled(true);
        this.divide.setEnabled(true);
        this.equals.setEnabled(true);
        this.ac.setEnabled(true);
        Calc1.display.setText("0.0");
        this.i = new Double(Calc1.display.getText());
        Calc1.display.setText("");
        NumPanel.point = false;
        this.result = 0.0;
        this.counter = 0;
        this.opera = 0;
    }
    
    public void square() {
        this.i = new Double(Calc1.display.getText());
        this.result = this.i;
        Calc1.display.setText(String.valueOf(Math.sqrt(this.result)));
        this.clear.addActionListener(this);
        this.plus.setEnabled(false);
        this.minus.setEnabled(false);
        this.times.setEnabled(false);
        this.divide.setEnabled(false);
        this.equals.setEnabled(false);
        this.ac.setEnabled(false);
    }
    
    public void ac() {
        NumPanel.point = false;
        Calc1.display.setText("");
    }
    
    public void sto() {
        this.i = new Double(Calc1.display.getText());
        this.temp = this.i;
    }
    
    public void rcl() {
        NumPanel.point = true;
        Calc1.display.setText(String.valueOf(this.temp));
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source == this.plus) {
            NumPanel.count = 0;
            NumPanel.point = false;
            this.addition();
        }
        if (source == this.minus) {
            NumPanel.count = 0;
            NumPanel.point = false;
            this.minus();
        }
        if (source == this.times) {
            NumPanel.count = 0;
            NumPanel.point = false;
            this.times();
        }
        if (source == this.divide) {
            NumPanel.count = 0;
            NumPanel.point = false;
            this.divide();
        }
        if (source == this.equals) {
            NumPanel.point = true;
            this.equals();
        }
        if (source == this.clear) {
            NumPanel.count = 0;
            this.clear();
        }
        if (source == this.square) {
            NumPanel.point = true;
            this.square();
        }
        if (source == this.ac) {
            NumPanel.count = 0;
            this.ac();
        }
        if (source == this.sto) {
            this.sto();
        }
        if (source == this.rcl) {
            this.rcl();
        }
    }
}
