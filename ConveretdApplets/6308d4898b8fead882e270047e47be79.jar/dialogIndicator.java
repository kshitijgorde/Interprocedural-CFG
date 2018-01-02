import java.awt.Event;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.Label;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class dialogIndicator extends Dialog
{
    Parameters param;
    TextField txtName;
    Button cmdAdd;
    Button cmdClose;
    Plotter plotter;
    TextField txtName1;
    TextField txtTrigger;
    Label lblName1;
    Label lblTrigger;
    Panel myPanel;
    
    public dialogIndicator(final boolean b, final Plotter plotter, final int n) {
        super(new Frame("Indicator"));
        this.myPanel = new Panel();
        this.plotter = plotter;
        this.param = new Parameters();
        this.setBackground(Parameters.dialogBGColor);
        this.setForeground(Parameters.dialogFGColor);
        try {
            this.addNotify();
        }
        catch (Exception ex) {}
        this.resize(this.insets().left + this.insets().right + 300, this.insets().top + this.insets().bottom + 200);
        this.myPanel.setLayout(new GridLayout(4, 2, 0, 0));
        this.myPanel.setBackground(Parameters.dialogBGColor);
        this.myPanel.setForeground(Parameters.dialogFGColor);
        final Panel panel = new Panel();
        panel.setLayout(new FlowLayout(0));
        panel.setBackground(Parameters.dialogBGColor);
        panel.setForeground(Parameters.dialogFGColor);
        switch (n) {
            case 6: {
                this.dialogBOLLINGER();
                break;
            }
            case 4: {
                this.dialogMACD();
                break;
            }
            case 7: {
                this.dialogOBV();
                break;
            }
            case 10: {
                this.dialogStochast();
                break;
            }
            case 11: {
                this.dialogMomentum();
                break;
            }
            default: {
                this.dialogDEFAULT();
                break;
            }
        }
        (this.cmdAdd = new Button("Add")).setFont(Parameters.labelFont);
        panel.add(this.cmdAdd);
        final Button button = new Button("Close");
        button.setFont(Parameters.labelFont);
        panel.add(button);
        this.add("North", this.myPanel);
        this.add("South", panel);
        this.setTitle("Indicator");
        this.setResizable(false);
    }
    
    public boolean action(final Event event, final Object o) {
        event.toString();
        if (event.target instanceof Button) {
            final String upperCase = ((Button)event.target).getLabel().toString().trim().toUpperCase();
            if (upperCase.equals("CLOSE")) {
                this.hide();
            }
            else if (upperCase.equals("ADD")) {
                if (Plotter.indicate == 1) {
                    this.plotter.addIndicators(1, this.txtName.getText());
                }
                else if (Plotter.indicate == 5) {
                    this.plotter.addIndicators(5, this.txtName.getText());
                }
                else if (Plotter.indicate == 3) {
                    this.plotter.addIndicators(3, this.txtName.getText());
                }
                else if (Plotter.indicate == 2) {
                    this.plotter.addIndicators(2, this.txtName.getText());
                }
                else if (Plotter.indicate == 4) {
                    this.plotter.addIndicators(4, this.txtName.getText(), this.txtName1.getText(), this.txtTrigger.getText());
                }
                else if (Plotter.inChartIndicate == 6) {
                    this.plotter.addIndicators(6, this.txtName.getText(), this.txtName1.getText());
                }
                else if (Plotter.indicate == 7) {
                    this.plotter.addIndicators(7, this.txtName.getText());
                }
                else if (Plotter.indicate == 9) {
                    this.plotter.addIndicators(9, this.txtName.getText());
                }
                else if (Plotter.indicate == 10) {
                    this.plotter.addIndicators(10, this.txtName.getText());
                }
                else if (Plotter.indicate == 11) {
                    this.plotter.addIndicators(11, this.txtName.getText());
                }
            }
        }
        this.repaint();
        return false;
    }
    
    protected void dialogBOLLINGER() {
        final Label label = new Label("Number of Days");
        label.setFont(Parameters.labelFont);
        this.myPanel.add(label);
        (this.txtName = new TextField("7", 10)).setFont(Parameters.labelFont);
        this.myPanel.add(this.txtName);
        final Label label2 = new Label("Times the Standard Deviation");
        label2.setFont(Parameters.labelFont);
        this.myPanel.add(label2);
        (this.txtName1 = new TextField("8", 10)).setFont(Parameters.labelFont);
        this.myPanel.add(this.txtName1);
        this.validate();
    }
    
    protected void dialogDEFAULT() {
        final Label label = new Label("Number of Days");
        label.setFont(Parameters.labelFont);
        this.myPanel.add(label);
        (this.txtName = new TextField("7", 10)).setFont(Parameters.labelFont);
        this.myPanel.add(this.txtName);
        this.validate();
    }
    
    protected void dialogMACD() {
        final Label label = new Label("Period One ");
        label.setFont(Parameters.labelFont);
        this.myPanel.add(label);
        (this.txtName = new TextField("7", 10)).setFont(Parameters.labelFont);
        this.myPanel.add(this.txtName);
        final Label label2 = new Label("Period Two ");
        label2.setFont(Parameters.labelFont);
        this.myPanel.add(label2);
        (this.txtName1 = new TextField("7", 10)).setFont(Parameters.labelFont);
        this.myPanel.add(this.txtName1);
        final Label label3 = new Label("Trigger Days ");
        label3.setFont(Parameters.labelFont);
        this.myPanel.add(label3);
        (this.txtTrigger = new TextField("7", 10)).setFont(Parameters.labelFont);
        this.myPanel.add(this.txtTrigger);
        this.validate();
    }
    
    protected void dialogMomentum() {
        this.dialogDEFAULT();
    }
    
    protected void dialogOBV() {
        final Label label = new Label("Volume of Shares");
        label.setFont(Parameters.labelFont);
        this.myPanel.add(label);
        (this.txtName = new TextField("100000", 10)).setFont(Parameters.labelFont);
        this.myPanel.add(this.txtName);
        this.validate();
    }
    
    protected void dialogStochast() {
        this.setTitle("Stochastic Oscillator");
        final Label label = new Label("Number of Days");
        label.setFont(Parameters.labelFont);
        this.myPanel.add(label);
        (this.txtName = new TextField("7", 10)).setFont(Parameters.labelFont);
        this.myPanel.add(this.txtName);
        this.validate();
    }
    
    public synchronized void show() {
        this.move(50, 50);
        super.show();
        this.txtName.requestFocus();
        this.repaint();
    }
}
