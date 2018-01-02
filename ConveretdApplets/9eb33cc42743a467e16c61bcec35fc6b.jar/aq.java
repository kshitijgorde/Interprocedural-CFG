import java.awt.Container;
import java.awt.event.WindowListener;
import java.awt.event.ActionListener;
import java.awt.Button;
import java.awt.Panel;
import java.awt.FlowLayout;
import java.awt.event.ItemListener;
import java.awt.Component;
import java.awt.Label;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

public final class aq
{
    public static Dialog he;
    public static Choice hd;
    public static Checkbox hc;
    public static TextField hb;
    public static TextField ha;
    public static TextField g9;
    public static TextField g8;
    public static String[] g7;
    public static as f;
    
    public static void d4(final String title, final Frame frame, final as f) {
        aq.f = f;
        if (aq.he == null) {
            aq.g7 = ca.mj;
            aq.he = new Dialog(frame, title, true);
            final GridBagLayout layout = new GridBagLayout();
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            aq.he.setLayout(layout);
            gridBagConstraints.fill = 2;
            gridBagConstraints.anchor = 17;
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.insets = new Insets(4, 4, 0, 4);
            gridBagConstraints.gridy = 0;
            final Label label = new Label("Proxy type:");
            gridBagConstraints.gridwidth = 2;
            layout.setConstraints(label, gridBagConstraints);
            aq.he.add(label);
            aq.hd = new Choice();
            for (int i = 0; i < aq.g7.length; ++i) {
                aq.hd.add(aq.g7[i]);
            }
            layout.setConstraints(aq.hd, gridBagConstraints);
            aq.he.add(aq.hd);
            final ap ap;
            aq.hd.addItemListener(ap = new ap());
            gridBagConstraints.gridy = 1;
            final Label label2 = new Label("Server:");
            layout.setConstraints(label2, gridBagConstraints);
            aq.he.add(label2);
            gridBagConstraints.gridwidth = 4;
            layout.setConstraints(aq.hb = new TextField("", 16), gridBagConstraints);
            aq.he.add(aq.hb);
            gridBagConstraints.gridwidth = 1;
            final Label label3 = new Label("Port:");
            layout.setConstraints(label3, gridBagConstraints);
            aq.he.add(label3);
            layout.setConstraints(aq.ha = new TextField("", 4), gridBagConstraints);
            aq.he.add(aq.ha);
            gridBagConstraints.gridy = 2;
            gridBagConstraints.gridwidth = 2;
            final Label label4 = new Label("Username:");
            layout.setConstraints(label4, gridBagConstraints);
            aq.he.add(label4);
            layout.setConstraints(aq.g9 = new TextField("", 10), gridBagConstraints);
            aq.he.add(aq.g9);
            final Label label5 = new Label("Password:");
            layout.setConstraints(label5, gridBagConstraints);
            aq.he.add(label5);
            (aq.g8 = new TextField("", 10)).setEchoChar('*');
            layout.setConstraints(aq.g8, gridBagConstraints);
            aq.he.add(aq.g8);
            gridBagConstraints.gridy = 3;
            gridBagConstraints.gridwidth = 4;
            layout.setConstraints(aq.hc = new Checkbox("Need authentication"), gridBagConstraints);
            aq.he.add(aq.hc);
            aq.hc.addItemListener(ap);
            final Panel panel = new Panel(new FlowLayout());
            final Button button;
            panel.add(button = new Button("OK"));
            button.addActionListener(new ao());
            final Button button2;
            panel.add(button2 = new Button("Cancel"));
            button2.addActionListener(new f(aq.he));
            gridBagConstraints.gridy = 4;
            gridBagConstraints.anchor = 10;
            gridBagConstraints.fill = 0;
            gridBagConstraints.gridwidth = 0;
            layout.setConstraints(panel, gridBagConstraints);
            aq.he.add(panel);
            aq.he.addWindowListener(new e(button2));
            a.b(aq.he);
            aq.he.setResizable(true);
            aq.he.pack();
        }
        aq.he.setTitle(title);
        aq.hd.select(aq.f.ce("proxytype"));
        final String ce = aq.f.ce("proxyuser");
        aq.hc.setState(ce != null && ce.trim().length() > 0);
        aq.hb.setText(aq.f.ce("proxyhost"));
        aq.ha.setText(aq.f.ce("proxyport"));
        aq.g9.setText(aq.f.ce("proxyuser"));
        fw();
        a.c(aq.he);
        aq.he.setVisible(true);
    }
    
    private static void fw() {
        boolean b = false;
        boolean enabled = false;
        final String selectedItem = aq.hd.getSelectedItem();
        int mr = 0;
        try {
            mr = ca.mr(selectedItem);
            switch (mr) {
                case 1:
                case 3:
                case 4: {
                    enabled = true;
                }
                case 2: {
                    b = true;
                }
            }
        }
        catch (Exception ex) {}
        aq.hb.setEnabled(b);
        aq.ha.setEnabled(b);
        aq.hc.setEnabled(enabled);
        if (!enabled) {
            aq.hc.setState(false);
        }
        final boolean state = aq.hc.getState();
        aq.g9.setEnabled(state);
        aq.g8.setEnabled(state);
        if (b) {
            if (aq.hb.getText().length() == 0) {
                aq.hb.setText(aq.f.ce("proxyhost"));
            }
            if (aq.ha.getText().length() == 0) {
                aq.ha.setText(aq.f.ce("proxyport"));
            }
        }
        else {
            aq.hb.setText("");
            aq.ha.setText("");
        }
        if (state) {
            if (aq.g9.getText().length() == 0) {
                aq.g9.setText(aq.f.ce("proxyuser"));
            }
        }
        else {
            aq.g9.setText("");
            aq.g8.setText("");
        }
        if (mr == 2) {
            aq.g9.setEnabled(true);
            String ce = aq.f.ce("proxyuser");
            if (aq.g9.getText().length() == 0) {
                if (ce == null) {
                    ce = "anonymous";
                }
                aq.g9.setText(ce);
            }
        }
        if (b) {
            aq.hb.requestFocus();
        }
    }
    
    public static void fv() {
        fw();
    }
    
    static {
        aq.he = null;
    }
}
