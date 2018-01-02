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

class NumPanel extends Panel implements ActionListener
{
    static boolean point;
    static short count;
    private Button b0;
    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;
    private Button b5;
    private Button b6;
    private Button b7;
    private Button b8;
    private Button b9;
    private Button bpoint;
    
    public NumPanel() {
        this.b0 = new Button(" 7 ");
        this.b1 = new Button(" 8 ");
        this.b2 = new Button(" 9 ");
        this.b3 = new Button(" 4 ");
        this.b4 = new Button(" 5 ");
        this.b5 = new Button(" 6 ");
        this.b6 = new Button(" 1 ");
        this.b7 = new Button(" 2 ");
        this.b8 = new Button(" 3 ");
        this.b9 = new Button(" 0 ");
        this.bpoint = new Button(" . ");
        this.b0.addActionListener(this);
        this.b1.addActionListener(this);
        this.b2.addActionListener(this);
        this.b3.addActionListener(this);
        this.b4.addActionListener(this);
        this.b5.addActionListener(this);
        this.b6.addActionListener(this);
        this.b7.addActionListener(this);
        this.b8.addActionListener(this);
        this.b9.addActionListener(this);
        this.bpoint.addActionListener(this);
        final Panel panel = new Panel();
        panel.setLayout(new GridLayout(4, 3, 10, 10));
        panel.add(this.b0);
        panel.add(this.b1);
        panel.add(this.b2);
        panel.add(this.b3);
        panel.add(this.b4);
        panel.add(this.b5);
        panel.add(this.b6);
        panel.add(this.b7);
        panel.add(this.b8);
        panel.add(this.b9);
        panel.add(this.bpoint);
        this.add(panel);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source == this.b0) {
            ++NumPanel.count;
            Calc1.s = Calc1.display.getText();
            Calc1.s = String.valueOf(Calc1.s) + 7;
            Calc1.display.setText(Calc1.s);
            return;
        }
        if (source == this.b1) {
            ++NumPanel.count;
            Calc1.s = Calc1.display.getText();
            Calc1.s = String.valueOf(Calc1.s) + 8;
            Calc1.display.setText(Calc1.s);
            return;
        }
        if (source == this.b2) {
            ++NumPanel.count;
            Calc1.s = Calc1.display.getText();
            Calc1.s = String.valueOf(Calc1.s) + 9;
            Calc1.display.setText(Calc1.s);
            return;
        }
        if (source == this.b3) {
            ++NumPanel.count;
            Calc1.s = Calc1.display.getText();
            Calc1.s = String.valueOf(Calc1.s) + 4;
            Calc1.display.setText(Calc1.s);
            return;
        }
        if (source == this.b4) {
            ++NumPanel.count;
            Calc1.s = Calc1.display.getText();
            Calc1.s = String.valueOf(Calc1.s) + 5;
            Calc1.display.setText(Calc1.s);
            return;
        }
        if (source == this.b5) {
            ++NumPanel.count;
            Calc1.s = Calc1.display.getText();
            Calc1.s = String.valueOf(Calc1.s) + 6;
            Calc1.display.setText(Calc1.s);
            return;
        }
        if (source == this.b6) {
            ++NumPanel.count;
            Calc1.s = Calc1.display.getText();
            Calc1.s = String.valueOf(Calc1.s) + 1;
            Calc1.display.setText(Calc1.s);
            return;
        }
        if (source == this.b7) {
            ++NumPanel.count;
            Calc1.s = Calc1.display.getText();
            Calc1.s = String.valueOf(Calc1.s) + 2;
            Calc1.display.setText(Calc1.s);
            return;
        }
        if (source == this.b8) {
            ++NumPanel.count;
            Calc1.s = Calc1.display.getText();
            Calc1.s = String.valueOf(Calc1.s) + 3;
            Calc1.display.setText(Calc1.s);
            return;
        }
        if (source == this.b9) {
            if (NumPanel.count == 0) {
                Calc1.s = Calc1.display.getText();
                Calc1.s = String.valueOf(Calc1.s) + "0.";
                Calc1.display.setText(Calc1.s);
                ++NumPanel.count;
                return;
            }
            Calc1.s = Calc1.display.getText();
            Calc1.s = String.valueOf(Calc1.s) + 0;
            Calc1.display.setText(Calc1.s);
            ++NumPanel.count;
        }
        else {
            if (source != this.bpoint) {
                return;
            }
            if (!NumPanel.point) {
                ++NumPanel.count;
                Calc1.s = Calc1.display.getText();
                Calc1.s = String.valueOf(Calc1.s) + ".";
                Calc1.display.setText(Calc1.s);
                NumPanel.point = true;
            }
        }
    }
}
