// 
// Decompiled by Procyon v0.5.30
// 

package lundin.SymbolicMath;

import java.util.Date;
import java.awt.Event;
import java.awt.Frame;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Button;
import java.awt.TextArea;
import java.awt.TextField;
import java.applet.Applet;

public class GUIDerive extends Applet
{
    TextField input;
    TextField vars;
    TextArea display;
    Button go;
    Derive d;
    Panel p;
    Panel p1;
    Panel p2;
    Panel p3;
    Panel p5;
    Label lbl1;
    Label lbl2;
    int lastIndex;
    
    public void init() {
        this.input = new TextField();
        this.vars = new TextField();
        this.display = new TextArea(5, 30);
        this.go = new Button("Derive");
        this.p = new Panel();
        this.p1 = new Panel();
        this.p2 = new Panel();
        this.p3 = new Panel();
        this.p2.setBackground(new Color(6710835));
        this.p3.setBackground(new Color(6710835));
        this.lbl1 = new Label("Expression");
        this.lbl2 = new Label("Variables");
        this.lbl1.setBackground(new Color(6710835));
        this.lbl2.setBackground(new Color(6710835));
        this.d = new Derive();
        this.setLayout(new BorderLayout());
        this.p.setLayout(new GridLayout(5, 1));
        this.p1.setLayout(new GridLayout(1, 3));
        this.p1.add(this.p2);
        this.p1.add(this.go);
        this.p1.add(this.p3);
        this.p.add(this.lbl1);
        this.p.add(this.input);
        this.p.add(this.lbl2);
        this.p.add(this.vars);
        this.p.add(this.p1);
        this.add("North", this.p);
        this.add("Center", this.display);
    }
    
    public static void main(final String[] array) {
        final Frame frame = new Frame("lundin.SymbolicMath.Derive - GUI");
        final GUIDerive guiDerive = new GUIDerive();
        frame.resize(480, 480);
        frame.add((Component)guiDerive);
        guiDerive.init();
        frame.pack();
        frame.show();
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.go) {
            this.differentiate();
            return true;
        }
        return false;
    }
    
    public void differentiate() {
        int n = 0;
        final String skipSpaces = this.SkipSpaces(this.vars.getText());
        final String skipSpaces2 = this.SkipSpaces(this.input.getText());
        final String s = "_______________________________________________\n\n";
        if (skipSpaces2.length() < 1) {
            this.display.appendText("ERROR: no expression to derive\n" + s);
            return;
        }
        if (skipSpaces.length() < 1) {
            this.display.appendText("ERROR: no variables given\n" + s);
            return;
        }
        long time;
        String[] diff;
        long time2;
        try {
            time = new Date().getTime();
            diff = this.d.diff(skipSpaces2, skipSpaces);
            time2 = new Date().getTime();
        }
        catch (Exception ex) {
            this.lastIndex = 0;
            this.display.appendText("ERROR:  " + ex.getMessage() + "\nPlease check syntax of expression\n" + s);
            return;
        }
        if (diff == null) {
            this.display.appendText("ERROR: something went wrong, please check syntax of expression\n" + s);
            return;
        }
        try {
            this.display.appendText("f(" + skipSpaces + ") = " + skipSpaces2 + "\n\n");
            String variable;
            while ((variable = this.findVariable(skipSpaces)) != null) {
                this.display.appendText("df/d{" + variable + "} = " + diff[n] + "\n\n");
                ++n;
            }
            this.display.appendText(s);
            this.display.appendText("Calculated in : " + (time2 - time) / 1000.0 + " seconds.\n");
            this.display.appendText(s);
        }
        catch (Exception ex2) {
            this.lastIndex = 0;
            this.display.appendText("Error: " + skipSpaces);
        }
    }
    
    String findVariable(final String s) {
        if (this.lastIndex >= s.length()) {
            this.lastIndex = 0;
            return null;
        }
        final int index = s.indexOf(";", this.lastIndex);
        if (index == -1) {
            final String substring = s.substring(this.lastIndex, s.length());
            this.lastIndex = s.length();
            return substring;
        }
        final String substring2 = s.substring(this.lastIndex, index);
        this.lastIndex = index + 1;
        return substring2;
    }
    
    public String SkipSpaces(final String s) {
        String string = "";
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) != ' ') {
                string = String.valueOf(string) + s.charAt(i);
            }
        }
        return string;
    }
}
