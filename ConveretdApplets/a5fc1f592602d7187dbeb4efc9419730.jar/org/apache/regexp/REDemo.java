// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.regexp;

import java.io.Writer;
import java.io.PrintWriter;
import java.io.CharArrayWriter;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.JFrame;
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
        final GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        gridBagConstraints.anchor = 13;
        layout.setConstraints(this.add(new Label("Regular expression:", 2)), gridBagConstraints);
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = 17;
        final GridBagLayout gridBagLayout = layout;
        final TextField fieldRE = new TextField("\\[([:javastart:][:javapart:]*)\\]", 40);
        this.fieldRE = fieldRE;
        gridBagLayout.setConstraints(this.add(fieldRE), gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = -1;
        gridBagConstraints.anchor = 13;
        layout.setConstraints(this.add(new Label("String:", 2)), gridBagConstraints);
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridx = -1;
        gridBagConstraints.anchor = 17;
        final GridBagLayout gridBagLayout2 = layout;
        final TextField fieldMatch = new TextField("aaa([foo])aaa", 40);
        this.fieldMatch = fieldMatch;
        gridBagLayout2.setConstraints(this.add(fieldMatch), gridBagConstraints);
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridx = -1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.weightx = 1.0;
        final GridBagLayout gridBagLayout3 = layout;
        final TextArea outRE = new TextArea();
        this.outRE = outRE;
        gridBagLayout3.setConstraints(this.add(outRE), gridBagConstraints);
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridx = -1;
        final GridBagLayout gridBagLayout4 = layout;
        final TextArea outMatch = new TextArea();
        this.outMatch = outMatch;
        gridBagLayout4.setConstraints(this.add(outMatch), gridBagConstraints);
        this.fieldRE.addTextListener(this);
        this.fieldMatch.addTextListener(this);
        this.textValueChanged(null);
    }
    
    public static void main(final String[] array) {
        final JFrame frame = new JFrame("RE Demo");
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        final Container contentPane = frame.getContentPane();
        contentPane.setLayout(new FlowLayout());
        final REDemo reDemo = new REDemo();
        contentPane.add(reDemo);
        reDemo.init();
        frame.pack();
        frame.setVisible(true);
    }
    
    void sayMatch(final String text) {
        this.outMatch.setText(text);
    }
    
    void sayRE(final String text) {
        this.outRE.setText(text);
    }
    
    public void textValueChanged(final TextEvent textEvent) {
        if (textEvent == null || textEvent.getSource() == this.fieldRE) {
            this.updateRE(this.fieldRE.getText());
        }
        this.updateMatch(this.fieldMatch.getText());
    }
    
    String throwableToString(final Throwable t) {
        String s = t.getClass().getName();
        final String message;
        if ((message = t.getMessage()) != null) {
            s = String.valueOf(s) + "\n" + message;
        }
        return s;
    }
    
    void updateMatch(final String s) {
        try {
            if (this.r.match(s)) {
                String string = "Matches.\n\n";
                for (int i = 0; i < this.r.getParenCount(); ++i) {
                    string = String.valueOf(string) + "$" + i + " = " + this.r.getParen(i) + "\n";
                }
                this.sayMatch(string);
            }
            else {
                this.sayMatch("Does not match");
            }
        }
        catch (Throwable t) {
            this.sayMatch(this.throwableToString(t));
        }
    }
    
    void updateRE(final String s) {
        try {
            this.r.setProgram(this.compiler.compile(s));
            final CharArrayWriter charArrayWriter = new CharArrayWriter();
            this.compiler.dumpProgram(new PrintWriter(charArrayWriter));
            this.sayRE(charArrayWriter.toString());
            System.out.println(charArrayWriter);
        }
        catch (Exception ex) {
            this.r.setProgram(null);
            this.sayRE(this.throwableToString(ex));
        }
        catch (Throwable t) {
            this.r.setProgram(null);
            this.sayRE(this.throwableToString(t));
        }
    }
}
