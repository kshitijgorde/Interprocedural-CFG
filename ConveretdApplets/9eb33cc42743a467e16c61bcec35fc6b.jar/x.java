import java.awt.Container;
import java.awt.event.WindowListener;
import java.awt.Button;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.Label;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Frame;
import java.awt.Choice;
import java.awt.TextField;
import java.awt.List;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

public final class x
{
    public static Dialog fi;
    public static List fh;
    public static TextField fg;
    public static TextField ff;
    public static TextField fe;
    public static Choice fc;
    public static final String[] fb;
    public static final int[] fa;
    public static as f;
    public static Frame a;
    public static b5 e9;
    
    public static void d4(final String title, final b5 e9, final as f, final Frame a) {
        x.f = f;
        x.a = a;
        x.e9 = e9;
        if (x.fi == null) {
            x.fi = new Dialog(x.a, title, true);
            final GridBagLayout layout = new GridBagLayout();
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            x.fi.setLayout(layout);
            gridBagConstraints.fill = 0;
            gridBagConstraints.anchor = 17;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.insets = new Insets(4, 4, 0, 4);
            final Label label = new Label("Current local tunnels:");
            gridBagConstraints.gridwidth = 2;
            layout.setConstraints(label, gridBagConstraints);
            x.fi.add(label);
            gridBagConstraints.fill = 1;
            gridBagConstraints.anchor = 17;
            gridBagConstraints.insets = new Insets(4, 4, 4, 4);
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            gridBagConstraints.gridwidth = 4;
            gridBagConstraints.gridy = 1;
            layout.setConstraints(x.fh = new List(8), gridBagConstraints);
            x.fi.add(x.fh);
            x.fh.addActionListener(new w());
            gridBagConstraints.fill = 0;
            gridBagConstraints.weighty = 0.0;
            gridBagConstraints.gridy = 2;
            gridBagConstraints.gridwidth = 1;
            final Label label2 = new Label("Local port:");
            layout.setConstraints(label2, gridBagConstraints);
            x.fi.add(label2);
            gridBagConstraints.fill = 2;
            gridBagConstraints.weightx = 1.0;
            layout.setConstraints(x.fe = new TextField("", 5), gridBagConstraints);
            x.fi.add(x.fe);
            final Label label3 = new Label("Protocol:");
            layout.setConstraints(label3, gridBagConstraints);
            gridBagConstraints.fill = 0;
            x.fi.add(label3);
            x.fc = new Choice();
            for (int i = 0; i < x.fb.length; ++i) {
                x.fc.add(x.fb[i]);
            }
            x.fc.select("general");
            layout.setConstraints(x.fc, gridBagConstraints);
            x.fi.add(x.fc);
            x.fc.addItemListener(new v());
            gridBagConstraints.gridy = 3;
            final Label label4 = new Label("Remote host:");
            layout.setConstraints(label4, gridBagConstraints);
            x.fi.add(label4);
            gridBagConstraints.fill = 2;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.gridwidth = 3;
            layout.setConstraints(x.fg = new TextField("", 16), gridBagConstraints);
            x.fi.add(x.fg);
            gridBagConstraints.gridy = 4;
            gridBagConstraints.fill = 0;
            gridBagConstraints.gridwidth = 1;
            final Label label5 = new Label("Remote port:");
            layout.setConstraints(label5, gridBagConstraints);
            x.fi.add(label5);
            x.ff = new TextField("", 5);
            gridBagConstraints.fill = 2;
            gridBagConstraints.weightx = 0.9;
            layout.setConstraints(x.ff, gridBagConstraints);
            x.fi.add(x.ff);
            final Button button = new Button("Add");
            button.addActionListener(new u());
            gridBagConstraints.fill = 2;
            gridBagConstraints.weightx = 0.1;
            layout.setConstraints(button, gridBagConstraints);
            x.fi.add(button);
            final Button button2 = new Button("Delete");
            button2.addActionListener(new t());
            layout.setConstraints(button2, gridBagConstraints);
            x.fi.add(button2);
            final Button button3 = new Button("Close Dialog");
            button3.addActionListener(new f(x.fi));
            gridBagConstraints.gridy = 5;
            gridBagConstraints.fill = 0;
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.anchor = 10;
            gridBagConstraints.ipady = 2;
            gridBagConstraints.ipadx = 2;
            layout.setConstraints(button3, gridBagConstraints);
            x.fi.add(button3);
            x.fi.addWindowListener(new e(button3));
            a.b(x.fi);
            x.fi.setResizable(true);
            x.fi.pack();
        }
        d3();
        x.fi.setTitle(title);
        a.c(x.fi);
        x.fe.requestFocus();
        x.fi.setVisible(true);
    }
    
    private static void d3() {
        x.fh.removeAll();
        for (int i = 0; i < x.e9.lq.size(); ++i) {
            final b1 b1 = x.e9.lq.elementAt(i);
            x.fh.add("local: " + b1.fe + " -> remote: " + b1.fg + "/" + b1.ff + (b1.lb.equals("general") ? "" : (" (plugin: " + b1.lb + ")")));
        }
    }
    
    public static void d2() {
        d3();
    }
    
    static {
        x.fi = null;
        fb = new String[] { "general", "ftp", "telnet", "smtp", "http", "pop2", "pop3", "nntp", "imap" };
        fa = new int[] { 0, 21, 23, 25, 80, 109, 110, 119, 143 };
    }
}
