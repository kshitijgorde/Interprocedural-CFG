import java.awt.event.ActionEvent;
import java.awt.Point;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Label;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.TextArea;
import java.awt.Button;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class FourierControls extends Panel implements ActionListener
{
    SigPanel panel;
    int fNumCoefs;
    TextField fCoefField;
    Button fPlusButton;
    Button fMinusButton;
    Button fShowCoefsButton;
    Button calculate;
    TextArea fTextArea;
    Window fWin;
    
    public FourierControls(final SigPanel panel) {
        this.fNumCoefs = 10;
        this.fCoefField = new TextField("10", 3);
        this.fPlusButton = new Button("+");
        this.fMinusButton = new Button("-");
        this.fShowCoefsButton = new Button("Table");
        this.calculate = new Button("Calculate");
        this.panel = panel;
        this.setLayout(new FlowLayout());
        this.setBackground(Color.lightGray);
        this.add(new Label("Fourier series coefficients:"));
        this.add(this.fCoefField);
        this.add(this.calculate);
        (this.fWin = new CoefFrame("Fourier Series Coefficients")).setLayout(new BorderLayout());
        (this.fTextArea = new TextArea(this.fNumCoefs, 50)).setEditable(false);
        this.fTextArea.setFont(new Font("Courier", 0, 12));
        this.fWin.add("Center", this.fTextArea);
        this.fWin.resize(300, 200);
        final Point location = this.location();
        this.fWin.move(location.x + 50, location.y + 50);
        this.disablePlusMinus();
        this.add(new Label(" "));
        this.add(this.fMinusButton);
        this.add(this.fPlusButton);
        this.add(new Label(" "));
        this.add(this.fShowCoefsButton);
        this.fMinusButton.addActionListener(this);
        this.fPlusButton.addActionListener(this);
        this.fShowCoefsButton.addActionListener(this);
        this.calculate.addActionListener(this);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("Calculate")) {
            try {
                this.panel.go(this.fNumCoefs = Integer.parseInt(this.fCoefField.getText()));
                this.panel.getCoefs(this.fTextArea);
                this.fPlusButton.enable();
                this.fShowCoefsButton.enable();
                if (this.fNumCoefs >= 1) {
                    this.fMinusButton.enable();
                }
            }
            catch (NumberFormatException ex) {}
        }
        else if (actionEvent.getActionCommand().equals("Table")) {
            this.panel.getCoefs(this.fTextArea);
            this.fWin.show();
        }
        else if (actionEvent.getActionCommand().equals("+")) {
            this.fCoefField.setText(new Integer(++this.fNumCoefs).toString());
            this.panel.go(this.fNumCoefs);
            this.panel.getCoefs(this.fTextArea);
            this.fMinusButton.enable();
        }
        else if (actionEvent.getActionCommand().equals("-") && this.fNumCoefs >= 1) {
            this.fCoefField.setText(new Integer(--this.fNumCoefs).toString());
            this.panel.go(this.fNumCoefs);
            this.panel.getCoefs(this.fTextArea);
            if (this.fNumCoefs == 0) {
                this.fMinusButton.disable();
            }
        }
    }
    
    public void disablePlusMinus() {
        this.fPlusButton.disable();
        this.fMinusButton.disable();
        this.fShowCoefsButton.disable();
        this.fTextArea.setText("");
    }
}
