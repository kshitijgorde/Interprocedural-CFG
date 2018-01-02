import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Event;
import java.awt.TextField;
import java.awt.Button;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class CalculateApplet extends Applet
{
    private boolean button_just_pressed;
    private boolean result_just_pressed;
    private boolean pt_already;
    private CalculateVector cV;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;
    Button ptButton;
    Button negButton;
    Button plusButton;
    Button minusButton;
    Button partButton;
    Button multButton;
    Button powerButton;
    Button resultButton;
    TextField textField;
    Button button10;
    
    void button10_Clicked(final Event event) {
        this.button_just_pressed = false;
        this.result_just_pressed = false;
        this.pt_already = false;
        (this.cV = new CalculateVector()).removeAll();
        this.textField.setText("0");
    }
    
    void powerButton_Clicked(final Event event) {
        this.button_just_pressed = true;
        this.cV.addElement("^");
        final Double d = new Double(this.textField.getText());
        this.cV.addElement(d);
        this.cV.calculate();
        this.textField.setText(String.valueOf(this.cV.lastDouble()));
    }
    
    void multButton_Clicked(final Event event) {
        this.button_just_pressed = true;
        this.cV.addElement("*");
        final Double d = new Double(this.textField.getText());
        this.cV.addElement(d);
        this.cV.calculate();
        this.textField.setText(String.valueOf(this.cV.lastDouble()));
    }
    
    void partButton_Clicked(final Event event) {
        this.button_just_pressed = true;
        this.cV.addElement("/");
        final Double d = new Double(this.textField.getText());
        this.cV.addElement(d);
        this.cV.calculate();
        this.textField.setText(String.valueOf(this.cV.lastDouble()));
    }
    
    void minusButton_Clicked(final Event event) {
        this.button_just_pressed = true;
        this.cV.addElement("-");
        final Double d = new Double(this.textField.getText());
        this.cV.addElement(d);
        this.cV.calculate();
        this.textField.setText(String.valueOf(this.cV.lastDouble()));
    }
    
    void plusButton_Clicked(final Event event) {
        this.button_just_pressed = true;
        this.cV.addElement("+");
        final Double d = new Double(this.textField.getText());
        this.cV.addElement(d);
        this.cV.calculate();
        this.textField.setText(String.valueOf(this.cV.lastDouble()));
    }
    
    void resultButton_Clicked(final Event event) {
        if (!this.result_just_pressed) {
            this.button_just_pressed = true;
            this.cV.addElement("+");
            final Double d = new Double(this.textField.getText());
            this.cV.addElement(d);
            this.cV.calculate();
            this.textField.setText(String.valueOf(this.cV.lastDouble()));
        }
        this.result_just_pressed = true;
    }
    
    void negButton_Clicked(final Event event) {
        if (this.textField.getText().indexOf("-") == -1) {
            this.textField.setText("-" + this.textField.getText());
            return;
        }
        final String temp = this.textField.getText();
        this.textField.setText(temp.substring(1));
    }
    
    void ptButton_Clicked(final Event event) {
        if (this.button_just_pressed) {
            this.textField.setText("0.");
        }
        else if (!this.pt_already) {
            this.textField.setText(this.textField.getText() + ".");
            this.pt_already = true;
        }
        this.button_just_pressed = false;
    }
    
    void button9_Clicked(final Event event) {
        if (!this.result_just_pressed) {
            if (this.textField.getText().equals("0") || this.button_just_pressed) {
                this.textField.setText("9");
            }
            else {
                this.textField.setText(this.textField.getText() + "9");
            }
            this.button_just_pressed = false;
        }
    }
    
    void button8_Clicked(final Event event) {
        if (!this.result_just_pressed) {
            if (this.textField.getText().equals("0") || this.button_just_pressed) {
                this.textField.setText("8");
            }
            else {
                this.textField.setText(this.textField.getText() + "8");
            }
            this.button_just_pressed = false;
        }
    }
    
    void button7_Clicked(final Event event) {
        if (!this.result_just_pressed) {
            if (this.textField.getText().equals("0") || this.button_just_pressed) {
                this.textField.setText("7");
            }
            else {
                this.textField.setText(this.textField.getText() + "7");
            }
            this.button_just_pressed = false;
        }
    }
    
    void button6_Clicked(final Event event) {
        if (!this.result_just_pressed) {
            if (this.textField.getText().equals("0") || this.button_just_pressed) {
                this.textField.setText("6");
            }
            else {
                this.textField.setText(this.textField.getText() + "6");
            }
            this.button_just_pressed = false;
        }
    }
    
    void button5_Clicked(final Event event) {
        if (!this.result_just_pressed) {
            if (this.textField.getText().equals("0") || this.button_just_pressed) {
                this.textField.setText("5");
            }
            else {
                this.textField.setText(this.textField.getText() + "5");
            }
            this.button_just_pressed = false;
        }
    }
    
    void button4_Clicked(final Event event) {
        if (!this.result_just_pressed) {
            if (this.textField.getText().equals("0") || this.button_just_pressed) {
                this.textField.setText("4");
            }
            else {
                this.textField.setText(this.textField.getText() + "4");
            }
            this.button_just_pressed = false;
        }
    }
    
    void button3_Clicked(final Event event) {
        if (!this.result_just_pressed) {
            if (this.textField.getText().equals("0") || this.button_just_pressed) {
                this.textField.setText("3");
            }
            else {
                this.textField.setText(this.textField.getText() + "3");
            }
            this.button_just_pressed = false;
        }
    }
    
    void button2_Clicked(final Event event) {
        if (!this.result_just_pressed) {
            if (this.textField.getText().equals("0") || this.button_just_pressed) {
                this.textField.setText("2");
            }
            else {
                this.textField.setText(this.textField.getText() + "2");
            }
            this.button_just_pressed = false;
        }
    }
    
    void button1_Clicked(final Event event) {
        if (!this.result_just_pressed) {
            if (this.textField.getText().equals("0") || this.button_just_pressed) {
                this.textField.setText("1");
            }
            else {
                this.textField.setText(this.textField.getText() + "1");
            }
            this.button_just_pressed = false;
        }
    }
    
    void button0_Clicked(final Event event) {
        if (!this.result_just_pressed) {
            if (this.textField.getText().equals("0") || this.button_just_pressed) {
                this.textField.setText("0");
            }
            else {
                this.textField.setText(this.textField.getText() + "0");
            }
            this.button_just_pressed = false;
        }
    }
    
    public void init() {
        super.init();
        this.setLayout(null);
        this.addNotify();
        this.resize(170, 191);
        (this.button0 = new Button("0")).reshape(12, 120, 24, 24);
        this.add(this.button0);
        (this.button1 = new Button("1")).reshape(12, 96, 24, 24);
        this.add(this.button1);
        (this.button2 = new Button("2")).reshape(36, 96, 24, 24);
        this.add(this.button2);
        (this.button3 = new Button("3")).reshape(60, 96, 24, 24);
        this.add(this.button3);
        (this.button4 = new Button("4")).reshape(12, 72, 24, 24);
        this.add(this.button4);
        (this.button5 = new Button("5")).reshape(36, 72, 24, 24);
        this.add(this.button5);
        (this.button6 = new Button("6")).reshape(60, 72, 24, 24);
        this.add(this.button6);
        (this.button7 = new Button("7")).reshape(12, 48, 24, 24);
        this.add(this.button7);
        (this.button8 = new Button("8")).reshape(36, 48, 24, 24);
        this.add(this.button8);
        (this.button9 = new Button("9")).reshape(60, 48, 24, 24);
        this.add(this.button9);
        (this.ptButton = new Button(".")).reshape(36, 120, 24, 24);
        this.add(this.ptButton);
        (this.negButton = new Button("+/-")).reshape(60, 120, 24, 24);
        this.add(this.negButton);
        (this.plusButton = new Button("+")).reshape(108, 96, 24, 24);
        this.add(this.plusButton);
        (this.minusButton = new Button("-")).reshape(108, 120, 24, 24);
        this.add(this.minusButton);
        (this.partButton = new Button("/")).reshape(108, 72, 24, 24);
        this.add(this.partButton);
        (this.multButton = new Button("x")).reshape(108, 48, 24, 24);
        this.add(this.multButton);
        (this.powerButton = new Button("^")).reshape(132, 48, 24, 24);
        this.add(this.powerButton);
        (this.resultButton = new Button("=")).reshape(132, 72, 24, 72);
        this.add(this.resultButton);
        (this.textField = new TextField()).setText("0");
        this.textField.reshape(12, 12, 144, 24);
        this.add(this.textField);
        (this.button10 = new Button("Reset")).reshape(12, 156, 142, 19);
        this.add(this.button10);
    }
    
    public boolean handleEvent(final Event event) {
        if (event.target == this.button0 && event.id == 1001) {
            this.button0_Clicked(event);
            return true;
        }
        if (event.target == this.button1 && event.id == 1001) {
            this.button1_Clicked(event);
            return true;
        }
        if (event.target == this.button2 && event.id == 1001) {
            this.button2_Clicked(event);
            return true;
        }
        if (event.target == this.button3 && event.id == 1001) {
            this.button3_Clicked(event);
            return true;
        }
        if (event.target == this.button4 && event.id == 1001) {
            this.button4_Clicked(event);
            return true;
        }
        if (event.target == this.button5 && event.id == 1001) {
            this.button5_Clicked(event);
            return true;
        }
        if (event.target == this.button6 && event.id == 1001) {
            this.button6_Clicked(event);
            return true;
        }
        if (event.target == this.button7 && event.id == 1001) {
            this.button7_Clicked(event);
            return true;
        }
        if (event.target == this.button8 && event.id == 1001) {
            this.button8_Clicked(event);
            return true;
        }
        if (event.target == this.button9 && event.id == 1001) {
            this.button9_Clicked(event);
            return true;
        }
        if (event.target == this.ptButton && event.id == 1001) {
            this.ptButton_Clicked(event);
            return true;
        }
        if (event.target == this.negButton && event.id == 1001) {
            this.negButton_Clicked(event);
            return true;
        }
        if (event.target == this.resultButton && event.id == 1001) {
            this.resultButton_Clicked(event);
            return true;
        }
        if (event.target == this.plusButton && event.id == 1001) {
            this.plusButton_Clicked(event);
            return true;
        }
        if (event.target == this.minusButton && event.id == 1001) {
            this.minusButton_Clicked(event);
            return true;
        }
        if (event.target == this.partButton && event.id == 1001) {
            this.partButton_Clicked(event);
            return true;
        }
        if (event.target == this.multButton && event.id == 1001) {
            this.multButton_Clicked(event);
            return true;
        }
        if (event.target == this.powerButton && event.id == 1001) {
            this.powerButton_Clicked(event);
            return true;
        }
        if (event.target == this.button10 && event.id == 1001) {
            this.button10_Clicked(event);
            return true;
        }
        return super.handleEvent(event);
    }
    
    public CalculateApplet() {
        this.button_just_pressed = false;
        this.result_just_pressed = false;
        this.pt_already = false;
        this.cV = new CalculateVector();
    }
}
