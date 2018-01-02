// 
// Decompiled by Procyon v0.5.30
// 

package lundin.SymbolicMath;

import java.util.Date;
import java.util.Hashtable;
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

public class GUIEval extends Applet
{
    TextField input;
    TextField vars;
    TextArea display;
    Button go;
    Eval e;
    Panel p;
    Panel p1;
    Panel p2;
    Panel p3;
    Label lbl1;
    Label lbl2;
    int lastIndex;
    
    public void init() {
        this.input = new TextField();
        this.vars = new TextField();
        this.display = new TextArea(5, 30);
        this.go = new Button("Eval");
        this.p = new Panel();
        this.p1 = new Panel();
        this.p2 = new Panel();
        this.p3 = new Panel();
        this.p2.setBackground(new Color(6710835));
        this.p3.setBackground(new Color(6710835));
        this.lbl1 = new Label("Expression");
        this.lbl2 = new Label("Variables=values");
        this.lbl1.setBackground(new Color(6710835));
        this.lbl2.setBackground(new Color(6710835));
        this.e = new Eval();
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
        final Frame frame = new Frame("lundin.SymbolicMath.Eval - GUI");
        final GUIEval guiEval = new GUIEval();
        frame.resize(480, 480);
        frame.add((Component)guiEval);
        guiEval.init();
        frame.pack();
        frame.show();
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.go) {
            this.evaluate();
            return true;
        }
        return false;
    }
    
    public void evaluate() {
        String s = "";
        final String skipSpaces = this.skipSpaces(this.vars.getText());
        final String skipSpaces2 = this.skipSpaces(this.input.getText());
        final String s2 = "_______________________________________________\n\n";
        final Hashtable<String, String> hashtable = new Hashtable<String, String>(503);
        final String lowerCase = skipSpaces.toLowerCase();
        final String lowerCase2 = skipSpaces2.toLowerCase();
        try {
            final long time = new Date().getTime();
            String values;
            while ((values = this.findValues(lowerCase)) != null) {
                if (s != "") {
                    s = String.valueOf(s) + ";";
                }
                final String substring = values.substring(0, values.indexOf("="));
                hashtable.put(substring, values.substring(values.indexOf("=") + 1, values.length()));
                s = String.valueOf(s) + substring;
            }
            final double eval = this.e.eval(lowerCase2, (Hashtable)hashtable);
            final long time2 = new Date().getTime();
            this.display.appendText("f(" + s + ") = " + lowerCase2 + "\n\n");
            this.display.appendText("f(" + lowerCase + ") = \n\n");
            this.display.appendText("= " + eval + "\n\n" + s2);
            this.display.appendText("Calculated in : " + (time2 - time) / 1000.0 + " seconds.\n" + s2);
        }
        catch (StringIndexOutOfBoundsException ex2) {
            this.lastIndex = 0;
            this.display.appendText("Syntax error -> " + lowerCase + "\n\n" + s2);
        }
        catch (Exception ex) {
            this.lastIndex = 0;
            this.display.appendText(String.valueOf(ex.getMessage()) + "\n\n" + s2);
        }
    }
    
    public String skipSpaces(final String s) {
        String string = "";
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) != ' ') {
                string = String.valueOf(string) + s.charAt(i);
            }
        }
        return string;
    }
    
    String findValues(final String s) {
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
}
