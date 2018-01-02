// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.regexp;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.JFrame;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.CharArrayWriter;
import java.awt.event.TextEvent;
import java.awt.Component;
import java.awt.Label;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.TextListener;
import java.applet.Applet;

public class REDemo extends Applet implements TextListener
{
    RE r;
    REDebugCompiler compiler;
    TextField fieldRE;
    TextField fieldMatch;
    TextArea outRE;
    TextArea outMatch;
    
    public REDemo() {
        this.r = new RE();
        this.compiler = new REDebugCompiler();
    }
    
    public void init() {
        final GridBagLayout gb = new GridBagLayout();
        this.setLayout(gb);
        final GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.anchor = 13;
        gb.setConstraints(this.add(new Label("Regular expression:", 2)), c);
        c.gridy = 0;
        c.anchor = 17;
        final GridBagLayout gridBagLayout = gb;
        final TextField fieldRE = new TextField("\\[([:javastart:][:javapart:]*)\\]", 40);
        this.fieldRE = fieldRE;
        gridBagLayout.setConstraints(this.add(fieldRE), c);
        c.gridx = 0;
        c.gridy = -1;
        c.anchor = 13;
        gb.setConstraints(this.add(new Label("String:", 2)), c);
        c.gridy = 1;
        c.gridx = -1;
        c.anchor = 17;
        final GridBagLayout gridBagLayout2 = gb;
        final TextField fieldMatch = new TextField("aaa([foo])aaa", 40);
        this.fieldMatch = fieldMatch;
        gridBagLayout2.setConstraints(this.add(fieldMatch), c);
        c.gridy = 2;
        c.gridx = -1;
        c.fill = 1;
        c.weighty = 1.0;
        c.weightx = 1.0;
        final GridBagLayout gridBagLayout3 = gb;
        final TextArea outRE = new TextArea();
        this.outRE = outRE;
        gridBagLayout3.setConstraints(this.add(outRE), c);
        c.gridy = 2;
        c.gridx = -1;
        final GridBagLayout gridBagLayout4 = gb;
        final TextArea outMatch = new TextArea();
        this.outMatch = outMatch;
        gridBagLayout4.setConstraints(this.add(outMatch), c);
        this.fieldRE.addTextListener(this);
        this.fieldMatch.addTextListener(this);
        this.textValueChanged(null);
    }
    
    void sayRE(final String s) {
        this.outRE.setText(s);
    }
    
    void sayMatch(final String s) {
        this.outMatch.setText(s);
    }
    
    String throwableToString(final Throwable t) {
        String s = t.getClass().getName();
        final String m;
        if ((m = t.getMessage()) != null) {
            s = String.valueOf(s) + "\n" + m;
        }
        return s;
    }
    
    void updateRE(final String expr) {
        try {
            this.r.setProgram(this.compiler.compile(expr));
            final CharArrayWriter w = new CharArrayWriter();
            this.compiler.dumpProgram(new PrintWriter(w));
            this.sayRE(w.toString());
            System.out.println(w);
        }
        catch (Exception e) {
            this.r.setProgram(null);
            this.sayRE(this.throwableToString(e));
        }
        catch (Throwable t) {
            this.r.setProgram(null);
            this.sayRE(this.throwableToString(t));
        }
    }
    
    void updateMatch(final String match) {
        try {
            if (this.r.match(match)) {
                String out = "Matches.\n\n";
                for (int i = 0; i < this.r.getParenCount(); ++i) {
                    out = String.valueOf(out) + "$" + i + " = " + this.r.getParen(i) + "\n";
                }
                this.sayMatch(out);
            }
            else {
                this.sayMatch("Does not match");
            }
        }
        catch (Throwable t) {
            this.sayMatch(this.throwableToString(t));
        }
    }
    
    public void textValueChanged(final TextEvent e) {
        if (e == null || e.getSource() == this.fieldRE) {
            this.updateRE(this.fieldRE.getText());
        }
        this.updateMatch(this.fieldMatch.getText());
    }
    
    public static void main(final String[] arg) {
        final JFrame f = new JFrame("RE Demo");
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent e) {
                System.exit(0);
            }
        });
        final Container c = f.getContentPane();
        c.setLayout(new FlowLayout());
        final REDemo demo = new REDemo();
        c.add(demo);
        demo.init();
        f.pack();
        f.setVisible(true);
    }
}
