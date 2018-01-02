import java.awt.Event;
import java.util.Enumeration;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.FontMetrics;
import java.awt.Font;
import java.util.Hashtable;
import java.awt.Button;
import java.awt.TextField;
import lundin.SymbolicMath.Derive;
import lundin.SymbolicMath.Eval;
import java.awt.Label;
import java.awt.Panel;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Calculator extends Applet
{
    Panel p;
    Panel p1;
    Panel p2;
    Panel p3;
    Label lbl;
    Eval ev;
    Derive de;
    TextField tf;
    Button info;
    Button clear;
    Button equa;
    Button plus;
    Button minus;
    Button divide;
    Button mult;
    Button zerow;
    Button one;
    Button two;
    Button three;
    Button four;
    Button five;
    Button six;
    Button seven;
    Button eight;
    Button nine;
    Button point;
    Hashtable values;
    Font fo;
    FontMetrics fm;
    
    public void init() {
        final int n = 10066278;
        final GridBagLayout layout = new GridBagLayout();
        final GridBagLayout layout2 = new GridBagLayout();
        final GridBagLayout layout3 = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.ev = new Eval();
        this.de = new Derive();
        this.values = new Hashtable();
        this.setFont(this.fo = new Font("Times Roman", 0, 9));
        this.fm = this.getFontMetrics(this.fo);
        this.p = new Panel();
        this.p1 = new Panel();
        this.p2 = new Panel();
        this.p3 = new Panel();
        this.tf = new TextField();
        this.info = new Button("Info");
        this.clear = new Button("Clear");
        this.plus = new Button("+");
        this.minus = new Button("-");
        this.mult = new Button("*");
        this.divide = new Button("/");
        this.equa = new Button("=");
        this.point = new Button(".");
        this.zerow = new Button("0");
        this.one = new Button("1");
        this.two = new Button("2");
        this.three = new Button("3");
        this.four = new Button("4");
        this.five = new Button("5");
        this.six = new Button("6");
        this.seven = new Button("7");
        this.eight = new Button("8");
        this.nine = new Button("9");
        this.setBackground(new Color(n));
        this.p2.setLayout(layout);
        this.p2.setBackground(new Color(n));
        this.p2.setForeground(Color.black);
        this.p3.setBackground(new Color(n));
        this.p3.setForeground(Color.black);
        this.tf.setBackground(Color.white);
        this.tf.setForeground(Color.black);
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.ipady = 3;
        layout.setConstraints(this.seven, gridBagConstraints);
        this.p2.add(this.seven);
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        layout.setConstraints(this.eight, gridBagConstraints);
        this.p2.add(this.eight);
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        layout.setConstraints(this.nine, gridBagConstraints);
        this.p2.add(this.nine);
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        layout.setConstraints(this.plus, gridBagConstraints);
        this.p2.add(this.plus);
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        layout.setConstraints(this.four, gridBagConstraints);
        this.p2.add(this.four);
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        layout.setConstraints(this.five, gridBagConstraints);
        this.p2.add(this.five);
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        layout.setConstraints(this.six, gridBagConstraints);
        this.p2.add(this.six);
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        layout.setConstraints(this.minus, gridBagConstraints);
        this.p2.add(this.minus);
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        layout.setConstraints(this.one, gridBagConstraints);
        this.p2.add(this.one);
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        layout.setConstraints(this.two, gridBagConstraints);
        this.p2.add(this.two);
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        layout.setConstraints(this.three, gridBagConstraints);
        this.p2.add(this.three);
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        layout.setConstraints(this.mult, gridBagConstraints);
        this.p2.add(this.mult);
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        layout.setConstraints(this.equa, gridBagConstraints);
        this.p2.add(this.equa);
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        layout.setConstraints(this.zerow, gridBagConstraints);
        this.p2.add(this.zerow);
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        layout.setConstraints(this.point, gridBagConstraints);
        this.p2.add(this.point);
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        layout.setConstraints(this.divide, gridBagConstraints);
        this.p2.add(this.divide);
        this.p1.setLayout(layout3);
        this.p1.setBackground(new Color(n));
        this.p1.setForeground(Color.black);
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        layout3.setConstraints(this.clear, gridBagConstraints);
        this.p1.add(this.clear);
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        layout3.setConstraints(this.info, gridBagConstraints);
        this.p1.add(this.info);
        this.p.setLayout(layout2);
        this.p.setBackground(new Color(n));
        this.p.setForeground(Color.black);
        gridBagConstraints.fill = 2;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        layout2.setConstraints(this.tf, gridBagConstraints);
        this.p.add(this.tf);
        gridBagConstraints.fill = 2;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        layout2.setConstraints(this.p2, gridBagConstraints);
        this.p.add(this.p2);
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        layout2.setConstraints(this.p1, gridBagConstraints);
        this.p.add(this.p1);
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        layout2.setConstraints(this.p3, gridBagConstraints);
        this.p.add(this.p3);
        this.add(this.p);
        try {
            System.out.println("\n\nApplet Calculator Made by Patrik Lundin, f95-plu@nada.kth.se\n\n");
        }
        catch (Exception ex) {}
    }
    
    void evaluate() {
        try {
            final String prepare = this.prepare(this.tf.getText().toLowerCase());
            if (prepare.equals("")) {
                return;
            }
            final int index;
            if ((index = prepare.indexOf("diff(")) != -1) {
                final String text = this.de.diff(prepare.substring(index + 5, prepare.lastIndexOf(")")))[0];
                if (this.de.VARIABLES.indexOf(";") != -1) {
                    this.tf.setText("Error: Multiple variables");
                    return;
                }
                this.tf.setText(text);
            }
            else {
                final int index2;
                if ((index2 = prepare.indexOf("set(")) != -1) {
                    try {
                        final String substring = prepare.substring(index2 + 4, prepare.lastIndexOf(")"));
                        this.values.put(substring.substring(0, substring.indexOf("=")), substring.substring(substring.indexOf("=") + 1, substring.length()));
                        this.tf.setText("Value set, " + this.getValues());
                        return;
                    }
                    catch (Exception ex2) {
                        this.tf.setText("Syntax error, " + prepare);
                        return;
                    }
                }
                final int index3;
                if ((index3 = prepare.indexOf("clear(")) != -1) {
                    final int lastIndex;
                    if ((lastIndex = prepare.lastIndexOf(")")) == -1) {
                        this.tf.setText("Non matching brackets");
                        return;
                    }
                    this.values.remove(prepare.substring(index3 + 6, lastIndex));
                    this.tf.setText("Value cleared, " + this.getValues());
                }
                else {
                    if (prepare.indexOf("memory") != -1) {
                        this.tf.setText(this.getValues());
                        return;
                    }
                    this.tf.setText(String.valueOf(this.ev.eval(prepare, this.values)));
                }
            }
        }
        catch (Exception ex) {
            this.tf.setText(ex.getMessage());
        }
    }
    
    String getValues() {
        String s = "";
        final Enumeration<String> keys = this.values.keys();
        while (keys.hasMoreElements()) {
            final String s2 = keys.nextElement();
            if (s.equals("")) {
                s = String.valueOf(s2) + "=" + (String)this.values.get(s2);
            }
            else {
                s = String.valueOf(s) + ";" + s2 + "=" + (String)this.values.get(s2);
            }
        }
        return s;
    }
    
    public boolean keyDown(final Event event, final int n) {
        final char c = (char)n;
        this.tf.requestFocus();
        if (c == '\n') {
            this.evaluate();
            return true;
        }
        return false;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 1001) {
            if (event.target == this.info) {
                new Msg("Info", this.information());
                return true;
            }
            if (event.target == this.equa) {
                this.evaluate();
                return true;
            }
            if (event.target == this.clear) {
                this.tf.setText("");
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof Button) {
            this.tf.setText(String.valueOf(this.tf.getText()) + (String)o);
            return true;
        }
        return false;
    }
    
    String information() {
        return "\t\t\tSimpleCalc\n\t\t\t----------\nThis is a simple Java Calculator with advanced functions :-D\nYou can use the mouse or the keyboard and then press the [=] button or\npress enter to evaluate.\n\nFor the more advanced functions you'll have use the keyboard.\n\n The calculator supports these operators and functions:\n-------------------------------------------------\n\n +              addition\n -              subtraction\n *              multiplication\n /              division\n ^               power to\n %             modulo\n sin()         sinus\n cos()        cosinus\n tan()         tangent\n atan()       arcustangent\n asin()       arcussinus\n acos()       arcuscosinus\n sinh()        hyperbolicsinus\n cosh()       hyperboliccosinus\n tanh()       hyperbolictangens\n exp()        constant E raised to\n ln()           natural logarithm\n [n]log()     any logaritm, base n\n sqrt()       squareroot\n cotan()      cotangens\n acotan()    inverted cotangens\n abs()         absolute value of\n ceil()         ceil, ceil(2.3) = 3\n floor()       floor, floor(1.23) = 1\n fac()         faculty, fac(n) = n*(n-1)*(n-2)*..*1\n sfac()        semifaculty, sfac(n) = n*(n-2)*(n-4)*..4*2 if n even,\n                  sfac(n) = n*(n-2)*(n-4)*..3*1 if n is noteven\n round()      round\n fpart()       decimal part of\n\nLogical operators :\n-----------------\n\n ==             equal, returns 1.0 if arguments are equal, 0.0 otherwise\n !=              not equal, returns 1.0 if arguments is not equal, 0.0 otherwise\n !                not, returns 0.0 if it's argument is 1.0, 1.0 otherwise\n &&            and, returns 1.0 if argumnets both evaluates to 1.0, 0.0 otherwise\n ||               or, returns 1.0 if any argument evaluates to 1.0, 0.0 otherwise\n >               larger than, returns 1.0 if left side is larger than the right side\n <               less than, returns 1.0 if left side is less than the right side\n <=             less than or equal, returns 1.0 if left side is less than or equal the right side\n >=             larger than or equal, returns 1.0 if left side is larger than or equal the right side\n\nSpecial functions :\n-----------------\n\ndiff( )         will symbolically differentiate a mathematical\n                  expression of one variable.\n                  Ex. diff( x^2-2*x ) will return 2*x-2\n                  To evaluate a differentiated expression first use\n                  set( ) to store values in the variable currently used\n                  and then differentiate and then press enter again to evaluate.\n                  Ex. set(x=2) [enter] returns \"Value set\" as confirmation\n                        diff(x^2) [enter] returns 2*x\n                        [enter] evaluates 2*x and returns 4\nset( )          will set a variable so it can be used in a calculation.\n                   Ex. set(x=5) x will be set to 5 so the calculation x^3\n                   will evaluate to 125.\n                   You can also store a calculation directly like : set(x=3*(5-2)/7)\n                    x will be set to 3*(5-2)/7\nclear( )        Clears a stored variable.\n                   Ex. clear(x) will clear the variable x, any calculation\n                   with x in it after calling clear(x) will generate the error:\n                  \"No value associated with x\"\nmemory          Will show all values currently in memory.\n                        values will still be in memory after pressing the [clear] button,\n                        use clear( ) to clear a value in memory.\n\nPlease note that diff( ) , set( ), memory and clear( ) cannot be used as a part of\nan expression, this will not generate an error but will just\ncause the first occurance of any of the commands to be evaluated.\nfor example: 2*5-diff(x^3) will just cause diff(x^3) to be evaluated.\n\nConstants supported:\n-------------------\n\nPi            pi, 3.1415...\nEuler       base for the natural logarithm, 2.7182..\ntrue        1.0\nfalse       0.0\n\nOperator precedence in order of evaluation:\n---------------------------------------\n( you can always change the order of evaluation by using paranthesis. )\n\n!\ncos sin tan sqrt exp.....etc\n^\n* / %\nlog\n+ -\n>  >=  <  <=\n==  !=\n||\n&&\n\nApplet Calculator Made by Patrik Lundin,\npatrik.lundin@ebox.tninet.se \nhttp://user.tninet.se/~jml288p/\n";
    }
    
    String prepare(final String s) {
        String string = "";
        int i = 0;
        int n = 0;
        while (i < s.length()) {
            if (s.charAt(i) == '(') {
                ++n;
            }
            else if (s.charAt(i) == ')') {
                --n;
            }
            if (s.charAt(i) != ' ') {
                string = String.valueOf(string) + s.charAt(i);
            }
            ++i;
        }
        if (n != 0) {
            this.tf.setText("Non matching brackets, " + s);
            return "";
        }
        return string;
    }
}
