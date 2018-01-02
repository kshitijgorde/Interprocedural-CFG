import java.awt.TextArea;
import java.awt.event.KeyListener;
import java.awt.Panel;
import java.awt.FlowLayout;
import java.awt.Container;
import java.awt.event.WindowListener;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.Button;
import java.awt.Label;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

public final class a1
{
    public static Dialog io;
    public static Label im;
    public static Button il;
    public static Dialog ik;
    public static Label ij;
    public static String ii;
    public static TextField ih;
    public static Dialog ig;
    public static Label if;
    public static String ie;
    public static TextField ic;
    public static TextField ib;
    public static Dialog ia;
    public static Label h9;
    public static boolean h8;
    public static Button h7;
    public static Button h6;
    
    public static void h9(final String title, final String text, final Frame frame) {
        if (a1.io == null) {
            a1.io = new Dialog(frame, title, true);
            final GridBagLayout layout = new GridBagLayout();
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            a1.io.setLayout(layout);
            gridBagConstraints.fill = 2;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.anchor = 10;
            gridBagConstraints.insets = new Insets(8, 4, 4, 8);
            gridBagConstraints.gridy = 0;
            layout.setConstraints(a1.im = new Label(), gridBagConstraints);
            a1.io.add(a1.im);
            (a1.il = new Button("OK")).addActionListener(new f(a1.io));
            gridBagConstraints.fill = 0;
            gridBagConstraints.gridy = 1;
            layout.setConstraints(a1.il, gridBagConstraints);
            a1.io.add(a1.il);
            a1.io.addWindowListener(new e(a1.il));
            a.b(a1.io);
            a1.io.setResizable(true);
        }
        a1.io.setTitle(title);
        a1.io.remove(a1.im);
        a1.im.setText(text);
        a1.io.add(a1.im);
        a1.io.pack();
        a.c(a1.io);
        a1.il.requestFocus();
        a1.io.setVisible(true);
    }
    
    public static String h8(final String title, final String text, final Frame frame) {
        if (a1.ik == null) {
            a1.ik = new Dialog(frame, title, true);
            final GridBagLayout layout = new GridBagLayout();
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            a1.ik.setLayout(layout);
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.anchor = 10;
            gridBagConstraints.insets = new Insets(8, 4, 4, 8);
            gridBagConstraints.gridy = 0;
            layout.setConstraints(a1.ij = new Label(), gridBagConstraints);
            a1.ik.add(a1.ij);
            gridBagConstraints.gridy = 1;
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.anchor = 17;
            final Label label = new Label("Password:");
            layout.setConstraints(label, gridBagConstraints);
            a1.ik.add(label);
            a1.ih = new TextField();
            gridBagConstraints.gridwidth = 0;
            layout.setConstraints(a1.ih, gridBagConstraints);
            a1.ih.setEchoChar('*');
            a1.ik.add(a1.ih);
            final Panel panel = new Panel(new FlowLayout());
            final Button button;
            panel.add(button = new Button("OK"));
            final a0 a0;
            button.addActionListener(a0 = new a0());
            final Button button2;
            panel.add(button2 = new Button("Cancel"));
            button2.addActionListener(a0);
            gridBagConstraints.gridy = 2;
            gridBagConstraints.gridwidth = 0;
            layout.setConstraints(panel, gridBagConstraints);
            a1.ik.add(panel);
            a1.ik.addWindowListener(new e(button2));
            a.a(a1.ik, new d(button, button2), null);
            a.b(a1.ik);
            a1.ik.setResizable(true);
        }
        a1.ik.setTitle(title);
        a1.ik.remove(a1.ij);
        a1.ij.setText(text);
        a1.ih.setText("");
        a1.ik.add(a1.ij);
        a1.ik.pack();
        a.c(a1.ik);
        a1.ik.setVisible(true);
        return a1.ii;
    }
    
    public static String h7(final String title, final String text, final Frame frame) {
        if (a1.ig == null) {
            a1.ig = new Dialog(frame, title, true);
            final GridBagLayout layout = new GridBagLayout();
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            a1.ig.setLayout(layout);
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.anchor = 10;
            gridBagConstraints.insets = new Insets(8, 4, 4, 8);
            gridBagConstraints.gridy = 0;
            layout.setConstraints(a1.if = new Label(), gridBagConstraints);
            a1.ig.add(a1.if);
            gridBagConstraints.gridy = 1;
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.anchor = 17;
            final Label label = new Label("Password:");
            layout.setConstraints(label, gridBagConstraints);
            a1.ig.add(label);
            layout.setConstraints(a1.ic = new TextField("", 12), gridBagConstraints);
            a1.ic.setEchoChar('*');
            a1.ig.add(a1.ic);
            gridBagConstraints.gridy = 2;
            final Label label2 = new Label("Password again:");
            layout.setConstraints(label2, gridBagConstraints);
            a1.ig.add(label2);
            layout.setConstraints(a1.ib = new TextField("", 12), gridBagConstraints);
            a1.ib.setEchoChar('*');
            a1.ig.add(a1.ib);
            final Panel panel = new Panel(new FlowLayout());
            final Button button;
            panel.add(button = new Button("OK"));
            final a_ a_;
            button.addActionListener(a_ = new a_());
            final Button button2;
            panel.add(button2 = new Button("Cancel"));
            button2.addActionListener(a_);
            gridBagConstraints.gridy = 3;
            gridBagConstraints.gridwidth = 0;
            layout.setConstraints(panel, gridBagConstraints);
            a1.ig.add(panel);
            a1.ig.addWindowListener(new e(button2));
            a.a(a1.ig, new d(button, button2), null);
            a.b(a1.ig);
            a1.ig.setResizable(true);
        }
        a1.ig.setTitle(title);
        a1.ig.remove(a1.if);
        a1.if.setText(text);
        a1.ic.setText("");
        a1.ib.setText("");
        a1.ig.add(a1.if);
        a1.ig.pack();
        a.c(a1.ig);
        a1.ig.setVisible(true);
        return a1.ie;
    }
    
    public static boolean h6(final String s, final String text, final boolean b, final Frame frame) {
        if (a1.ia == null) {
            a1.ia = new Dialog(frame, s, true);
            final GridBagLayout layout = new GridBagLayout();
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            a1.ia.setLayout(layout);
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.anchor = 10;
            gridBagConstraints.insets = new Insets(8, 4, 4, 8);
            gridBagConstraints.gridy = 0;
            layout.setConstraints(a1.h9 = new Label(), gridBagConstraints);
            a1.ia.add(a1.h9);
            final Panel panel = new Panel(new FlowLayout());
            panel.add(a1.h7 = new Button("Yes"));
            final az az;
            a1.h7.addActionListener(az = new az());
            panel.add(a1.h6 = new Button("No"));
            a1.h6.addActionListener(az);
            gridBagConstraints.gridy = 1;
            gridBagConstraints.gridwidth = 0;
            layout.setConstraints(panel, gridBagConstraints);
            a1.ia.add(panel);
            a1.ia.addWindowListener(new e(a1.h6));
            a.b(a1.ia);
            a1.ia.setResizable(true);
        }
        a1.ia.remove(a1.h9);
        a1.h9.setText(text);
        a1.ia.add(a1.h9);
        a1.ia.pack();
        a.c(a1.ia);
        if (b) {
            a1.h7.requestFocus();
        }
        else {
            a1.h6.requestFocus();
        }
        a1.ia.setVisible(true);
        return a1.h8;
    }
    
    public static void h5(final String s, final String s2, final int n, final int n2, final boolean b, final Frame frame) {
        final Dialog dialog = new Dialog(frame, s, true);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        dialog.setLayout(layout);
        gridBagConstraints.fill = 0;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        final TextArea textArea = new TextArea(s2, n, n2, b ? 1 : 3);
        layout.setConstraints(textArea, gridBagConstraints);
        dialog.add(textArea);
        textArea.setEditable(false);
        final Button button = new Button("OK");
        button.addActionListener(new f(dialog));
        gridBagConstraints.fill = 0;
        layout.setConstraints(button, gridBagConstraints);
        dialog.add(button);
        dialog.addWindowListener(new e(button));
        a.b(dialog);
        dialog.setResizable(true);
        dialog.pack();
        a.c(dialog);
        button.requestFocus();
        dialog.setVisible(true);
    }
    
    static {
        a1.io = null;
        a1.ik = null;
        a1.ig = null;
        a1.ia = null;
    }
}
