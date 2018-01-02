import java.awt.Button;
import java.awt.TextField;
import java.awt.Label;

// 
// Decompiled by Procyon v0.5.30
// 

class Parameter
{
    public String name;
    public double value;
    public double step;
    public int pow;
    public boolean par;
    public boolean active;
    public boolean fixed;
    public boolean controls;
    public boolean changed;
    public Label prompt;
    public Label powlabel;
    public TextField input;
    public TextField setpow;
    public Button toggle;
    public Button plus;
    public Button minus;
    public Button powup;
    public Button powdn;
    public Button inc;
    public Button dec;
    public Button stop;
    
    public Parameter(final String name, final double value) {
        this.name = name;
        this.value = value;
        this.pow = 0;
        this.step = 0.0;
        this.par = true;
        this.active = true;
        this.fixed = true;
        this.controls = false;
        this.changed = false;
        this.prompt = new Label(this.name);
        this.input = new TextField(this.showbrief(Double.toString(this.value)));
        this.powlabel = new Label("E");
        this.setpow = new TextField("0");
        this.plus = new Button("+");
        this.minus = new Button("-");
        this.inc = new Button("^^");
        this.dec = new Button("vv");
        this.powup = new Button("^");
        this.powdn = new Button("v");
        this.toggle = new Button("");
        this.stop = new Button("stop");
    }
    
    public String showbrief(final String s) {
        final String s2 = new String();
        String s3;
        if (s.length() <= 7) {
            s3 = s;
        }
        else if (s.indexOf(69) != -1) {
            s3 = s.substring(0, 7) + s.substring(s.indexOf(69));
        }
        else {
            s3 = s.substring(0, 7);
        }
        return s3;
    }
    
    public void setValue(final double value) {
        this.value = value;
    }
    
    public double getValue() {
        return this.value;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
}
