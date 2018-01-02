import java.awt.Graphics;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.Button;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Calculator extends Applet implements ActionListener
{
    private TextField Panel;
    private Button Button0;
    private Button Button1;
    private Button Button2;
    private Button Button3;
    private Button Button4;
    private Button Button5;
    private Button Button6;
    private Button Button7;
    private Button Button8;
    private Button Button9;
    private Button ButtonDiv;
    private Button ButtonMul;
    private Button ButtonAdd;
    private Button ButtonSub;
    private Button ButtonAss;
    private Button ButtonOpar;
    private Button ButtonCpar;
    private Button ButtonPoint;
    private Button ButtonClear;
    private int Flag;
    private int Err_lve;
    
    private String Algorithm(final char c, final String s, final String s2) {
        double n = 0.0;
        final Double n2 = new Double(s);
        final Double n3 = new Double(s2);
        final double doubleValue = n2;
        final double doubleValue2 = n3;
        if (c == '+') {
            n = doubleValue + doubleValue2;
        }
        if (c == '-') {
            n = doubleValue - doubleValue2;
        }
        if (c == '*') {
            n = doubleValue * doubleValue2;
        }
        if (c == '/') {
            n = doubleValue / doubleValue2;
        }
        return Double.toString(n);
    }
    
    private String Calculate(final String s) {
        String s2 = s;
        final int length = s2.length();
        int i = 0;
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        if (this.Err_lve == 0) {
            for (int j = 0; j < length; ++j) {
                final char char1 = s2.charAt(j);
                if (char1 == '(') {
                    if (n == 0) {
                        n2 = j;
                    }
                    ++n;
                    ++i;
                }
                if (char1 == ')' && --n == 0) {
                    n3 = j;
                }
            }
            if (n2 + 1 == n3) {
                this.Err_lve = 3;
            }
            if (this.Err_lve == 0 && i > 0) {
                if (n2 == 0 && n3 == length - 1 && n2 != n3) {
                    s2 = this.Calculate(s2.substring(n2 + 1, n3));
                }
                else if (n2 == 0 && n3 > 0) {
                    if (Character.isDigit(s2.charAt(n3 + 1))) {
                        this.Err_lve = 3;
                    }
                    else {
                        s2 = String.valueOf(this.Calculate(s2.substring(n2 + 1, n3))) + s2.substring(n3 + 1);
                        --i;
                        while (i != 0) {
                            s2 = this.Calculate(s2);
                            --i;
                        }
                    }
                }
                else if (n2 > 0 && n3 > 0 && n3 != length - 1) {
                    if (Character.isDigit(s2.charAt(n3 + 1)) || Character.isDigit(s2.charAt(n2 - 1))) {
                        this.Err_lve = 3;
                    }
                    else {
                        s2 = String.valueOf(s2.substring(0, n2)) + this.Calculate(s2.substring(n2 + 1, n3)) + s2.substring(n3 + 1);
                        --i;
                        while (i != 0) {
                            s2 = this.Calculate(s2);
                            --i;
                        }
                    }
                }
                else if (n3 == length - 1 && n2 > 0) {
                    if (Character.isDigit(s2.charAt(n2 - 1))) {
                        this.Err_lve = 3;
                    }
                    else {
                        s2 = String.valueOf(s2.substring(0, n2)) + this.Calculate(s2.substring(n2 + 1, n3));
                        --i;
                        while (i != 0) {
                            s2 = this.Calculate(s2);
                            --i;
                        }
                    }
                }
            }
            if (i == 0 && this.Err_lve == 0) {
                if ((!Character.isDigit(s2.charAt(0)) && s2.charAt(0) != '-') || !Character.isDigit(s2.charAt(s2.length() - 1))) {
                    this.Err_lve = 3;
                }
                for (int n4 = 0; n4 < s2.length() && this.Err_lve == 0; ++n4) {
                    final char char2 = s2.charAt(n4);
                    if (char2 == '*' || char2 == '/') {
                        if (!Character.isDigit(s2.charAt(n4 - 1)) || (!Character.isDigit(s2.charAt(n4 + 1)) && s2.charAt(n4 + 1) != '-')) {
                            this.Err_lve = 3;
                        }
                        if (s2.charAt(n4 + 1) == '-' && !Character.isDigit(s2.charAt(n4 + 2))) {
                            this.Err_lve = 3;
                        }
                        if (this.Err_lve == 0) {
                            int n5 = n4 - 1;
                            if (n5 > 2 && s2.charAt(n5 - 1) == '-' && s2.charAt(n5 - 2) == 'E') {
                                n5 -= 2;
                            }
                            while (n5 > 0 && (Character.isDigit(s2.charAt(n5 - 1)) || s2.charAt(n5 - 1) == '.' || s2.charAt(n5 - 1) == 'E')) {
                                --n5;
                            }
                            if (n5 == 1 && s2.charAt(n5 - 1) == '-') {
                                --n5;
                            }
                            if (n5 > 2 && s2.charAt(n5 - 1) == '-' && !Character.isDigit(s2.charAt(n5 - 2))) {
                                --n5;
                            }
                            final String substring = s2.substring(n5, n4);
                            int n6;
                            for (n6 = n4 + 1; n6 < s2.length() - 1 && (Character.isDigit(s2.charAt(n6 + 1)) || s2.charAt(n6 + 1) == '.' || s2.charAt(n6 + 1) == 'E'); ++n6) {
                                if (++n6 < s2.length() - 2 && s2.charAt(n6) == 'E' && s2.charAt(n6 + 1) == '-') {}
                            }
                            final String substring2 = s2.substring(n4 + 1, n6 + 1);
                            if (n5 != 0 && n6 != s2.length() - 1) {
                                if (this.Algorithm(char2, substring, substring2).charAt(0) == 'N') {
                                    this.Err_lve = 4;
                                }
                                if (this.Algorithm(char2, substring, substring2).charAt(0) == 'I') {
                                    this.Err_lve = 5;
                                }
                                s2 = String.valueOf(s2.substring(0, n5)) + this.Algorithm(char2, substring, substring2) + s2.substring(n6 + 1);
                                n4 = 0;
                            }
                            else if (n5 == 0 && n6 == s2.length() - 1) {
                                if (this.Algorithm(char2, substring, substring2).charAt(0) == 'N') {
                                    this.Err_lve = 4;
                                }
                                if (this.Algorithm(char2, substring, substring2).charAt(0) == 'I') {
                                    this.Err_lve = 5;
                                }
                                s2 = this.Algorithm(char2, substring, substring2);
                            }
                            else if (n5 == 0) {
                                if (this.Algorithm(char2, substring, substring2).charAt(0) == 'N') {
                                    this.Err_lve = 4;
                                }
                                if (this.Algorithm(char2, substring, substring2).charAt(0) == 'I') {
                                    this.Err_lve = 5;
                                }
                                s2 = String.valueOf(this.Algorithm(char2, substring, substring2)) + s2.substring(n6 + 1);
                                n4 = 0;
                            }
                            else if (n6 == s2.length() - 1) {
                                if (this.Algorithm(char2, substring, substring2).charAt(0) == 'N') {
                                    this.Err_lve = 4;
                                }
                                if (this.Algorithm(char2, substring, substring2).charAt(0) == 'I') {
                                    this.Err_lve = 5;
                                }
                                s2 = String.valueOf(s2.substring(0, n5)) + this.Algorithm(char2, substring, substring2);
                            }
                        }
                    }
                }
                for (int n7 = 0; n7 < s2.length() && this.Err_lve == 0; ++n7) {
                    if (n7 == 0 && s2.charAt(n7) == '-') {
                        n7 = 1;
                    }
                    if (n7 > 0 && s2.charAt(n7) == 'E' && s2.charAt(n7 + 1) == '-') {
                        n7 += 2;
                    }
                    final char char3 = s2.charAt(n7);
                    if (char3 == '+' || char3 == '-') {
                        if (!Character.isDigit(s2.charAt(n7 - 1)) || (!Character.isDigit(s2.charAt(n7 + 1)) && s2.charAt(n7 + 1) != '-')) {
                            this.Err_lve = 3;
                        }
                        if (s2.charAt(n7 + 1) == '-' && !Character.isDigit(s2.charAt(n7 + 2))) {
                            this.Err_lve = 3;
                        }
                        if (this.Err_lve == 0) {
                            final String substring3 = s2.substring(0, n7);
                            int n8;
                            for (n8 = n7 + 1; n8 < s2.length() - 1 && (Character.isDigit(s2.charAt(n8 + 1)) || s2.charAt(n8 + 1) == '.' || s2.charAt(n8 + 1) == 'E'); ++n8) {
                                if (++n8 < s2.length() - 2 && s2.charAt(n8) == 'E' && s2.charAt(n8 + 1) == '-') {}
                            }
                            s2 = String.valueOf(this.Algorithm(char3, substring3, s2.substring(n7 + 1, n8 + 1))) + s2.substring(n8 + 1);
                            n7 = 0;
                        }
                    }
                }
            }
        }
        return s2;
    }
    
    private void ClearPanel() {
        this.Panel.setText("");
    }
    
    private void Compute() {
        String calculate = "";
        int n = 0;
        final String text = this.Panel.getText();
        for (int length = text.length(), i = 0; i < length; ++i) {
            final char char1 = text.charAt(i);
            if (char1 == ')') {
                --n;
            }
            if (n < 0) {
                this.Err_lve = 1;
            }
            if (char1 == '(') {
                ++n;
            }
            if (char1 < '(' || char1 > '9' || char1 == ',') {
                this.Err_lve = 2;
            }
            if (char1 == '.' && i + 1 < text.length()) {
                for (int n2 = i + 1; n2 < text.length() && (Character.isDigit(text.charAt(n2)) || text.charAt(n2) == '.'); ++n2) {
                    if (text.charAt(n2) == '.') {
                        this.Err_lve = 3;
                    }
                }
            }
        }
        if (n != 0) {
            this.Err_lve = 1;
        }
        if (this.Err_lve != 0) {
            this.Err_msg(this.Err_lve);
        }
        else {
            calculate = this.Calculate(text);
        }
        if (this.Err_lve != 0) {
            this.Err_msg(this.Err_lve);
        }
        else {
            this.Panel.setText(calculate);
        }
    }
    
    private void DisplayEx(final Button button) {
        final String text = this.Panel.getText();
        if (button == this.Button0) {
            this.Panel.setText(String.valueOf(text) + "0");
        }
        if (button == this.Button1) {
            this.Panel.setText(String.valueOf(text) + "1");
        }
        if (button == this.Button2) {
            this.Panel.setText(String.valueOf(text) + "2");
        }
        if (button == this.Button3) {
            this.Panel.setText(String.valueOf(text) + "3");
        }
        if (button == this.Button4) {
            this.Panel.setText(String.valueOf(text) + "4");
        }
        if (button == this.Button5) {
            this.Panel.setText(String.valueOf(text) + "5");
        }
        if (button == this.Button6) {
            this.Panel.setText(String.valueOf(text) + "6");
        }
        if (button == this.Button7) {
            this.Panel.setText(String.valueOf(text) + "7");
        }
        if (button == this.Button8) {
            this.Panel.setText(String.valueOf(text) + "8");
        }
        if (button == this.Button9) {
            this.Panel.setText(String.valueOf(text) + "9");
        }
        if (button == this.ButtonDiv) {
            this.Panel.setText(String.valueOf(text) + "/");
        }
        if (button == this.ButtonMul) {
            this.Panel.setText(String.valueOf(text) + "*");
        }
        if (button == this.ButtonAdd) {
            this.Panel.setText(String.valueOf(text) + "+");
        }
        if (button == this.ButtonSub) {
            this.Panel.setText(String.valueOf(text) + "-");
        }
        if (button == this.ButtonOpar) {
            this.Panel.setText(String.valueOf(text) + "(");
        }
        if (button == this.ButtonCpar) {
            this.Panel.setText(String.valueOf(text) + ")");
        }
        if (button == this.ButtonPoint) {
            this.Panel.setText(String.valueOf(text) + ".");
        }
    }
    
    private void Err_msg(final int n) {
        switch (n) {
            case 1: {
                this.Panel.setText("Parentheses do not match");
                break;
            }
            case 2: {
                this.Panel.setText("Invalid input");
                break;
            }
            case 3: {
                this.Panel.setText("Invalid expression");
                break;
            }
            case 4: {
                this.Panel.setText("Not a number exist");
                break;
            }
            case 5: {
                this.Panel.setText("Infinity exist");
                break;
            }
            default: {
                this.Panel.setText("Unknow error");
                break;
            }
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() instanceof Button) {
            final Button button = (Button)actionEvent.getSource();
            if (button == this.ButtonClear) {
                this.ClearPanel();
                this.Flag = 0;
            }
            else if (button == this.ButtonAss) {
                this.Err_lve = 0;
                this.Compute();
                this.Flag = 1;
            }
            else {
                if (this.Flag == 1) {
                    this.ClearPanel();
                    this.Flag = 0;
                }
                this.DisplayEx(button);
            }
        }
        else {
            this.Err_lve = 0;
            this.Compute();
            this.Flag = 1;
        }
    }
    
    public void init() {
        this.setBackground(Color.white);
        this.setLayout(null);
        this.resize(205, 210);
        this.Flag = 0;
        this.Err_lve = 0;
        (this.Panel = new TextField()).setBounds(20, 20, 165, 25);
        this.add(this.Panel);
        (this.Button0 = new Button("0")).setBounds(20, 160, 25, 25);
        this.add(this.Button0);
        (this.Button1 = new Button("1")).setBounds(20, 125, 25, 25);
        this.add(this.Button1);
        (this.Button2 = new Button("2")).setBounds(55, 125, 25, 25);
        this.add(this.Button2);
        (this.Button3 = new Button("3")).setBounds(90, 125, 25, 25);
        this.add(this.Button3);
        (this.Button4 = new Button("4")).setBounds(20, 90, 25, 25);
        this.add(this.Button4);
        (this.Button5 = new Button("5")).setBounds(55, 90, 25, 25);
        this.add(this.Button5);
        (this.Button6 = new Button("6")).setBounds(90, 90, 25, 25);
        this.add(this.Button6);
        (this.Button7 = new Button("7")).setBounds(20, 55, 25, 25);
        this.add(this.Button7);
        (this.Button8 = new Button("8")).setBounds(55, 55, 25, 25);
        this.add(this.Button8);
        (this.Button9 = new Button("9")).setBounds(90, 55, 25, 25);
        this.add(this.Button9);
        (this.ButtonDiv = new Button("/")).setBounds(160, 90, 25, 25);
        this.add(this.ButtonDiv);
        (this.ButtonMul = new Button("*")).setBounds(125, 90, 25, 25);
        this.add(this.ButtonMul);
        (this.ButtonAdd = new Button("+")).setBounds(125, 55, 25, 25);
        this.add(this.ButtonAdd);
        (this.ButtonSub = new Button("-")).setBounds(160, 55, 25, 25);
        this.add(this.ButtonSub);
        (this.ButtonAss = new Button("=")).setBounds(90, 160, 25, 25);
        this.add(this.ButtonAss);
        (this.ButtonOpar = new Button("(")).setBounds(125, 125, 25, 25);
        this.add(this.ButtonOpar);
        (this.ButtonCpar = new Button(")")).setBounds(160, 125, 25, 25);
        this.add(this.ButtonCpar);
        (this.ButtonPoint = new Button(".")).setBounds(55, 160, 25, 25);
        this.add(this.ButtonPoint);
        (this.ButtonClear = new Button("Clear")).setBounds(125, 160, 60, 25);
        this.add(this.ButtonClear);
        this.Panel.addActionListener(this);
        this.Button0.addActionListener(this);
        this.Button1.addActionListener(this);
        this.Button2.addActionListener(this);
        this.Button3.addActionListener(this);
        this.Button4.addActionListener(this);
        this.Button5.addActionListener(this);
        this.Button6.addActionListener(this);
        this.Button7.addActionListener(this);
        this.Button8.addActionListener(this);
        this.Button9.addActionListener(this);
        this.ButtonDiv.addActionListener(this);
        this.ButtonMul.addActionListener(this);
        this.ButtonAdd.addActionListener(this);
        this.ButtonSub.addActionListener(this);
        this.ButtonAss.addActionListener(this);
        this.ButtonOpar.addActionListener(this);
        this.ButtonCpar.addActionListener(this);
        this.ButtonPoint.addActionListener(this);
        this.ButtonClear.addActionListener(this);
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(Color.yellow);
        graphics.drawRoundRect(0, 0, 190, 195, 15, 15);
        graphics.setColor(Color.lightGray);
        graphics.drawRoundRect(1, 1, 191, 196, 15, 15);
        graphics.drawRoundRect(2, 2, 192, 197, 15, 15);
        graphics.drawRoundRect(3, 3, 193, 198, 15, 15);
        graphics.drawRoundRect(4, 4, 194, 199, 15, 15);
        graphics.setColor(Color.black);
        graphics.fillRoundRect(5, 5, 195, 200, 15, 15);
        graphics.setColor(Color.green);
        graphics.drawRoundRect(5, 5, 195, 200, 15, 15);
        graphics.setColor(Color.yellow);
        graphics.drawString("Phoenix's Calculator", 45, 200);
    }
}
