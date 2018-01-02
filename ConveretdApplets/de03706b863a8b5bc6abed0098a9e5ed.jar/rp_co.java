import javax.swing.AbstractButton;
import java.util.Enumeration;
import java.awt.event.ActionEvent;
import java.awt.Container;
import java.io.IOException;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.awt.Component;
import javax.swing.JTabbedPane;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_co extends rp_bW implements ActionListener
{
    private JTextArea a;
    private rp_fx a;
    private rp_dC[] a;
    private int a;
    
    public rp_co(final rp_fx a) {
        super("Secret Admin Wnd");
        this.a = new JTextArea();
        this.a = 0;
        this.a = a;
        this.c();
        this.d();
        this.setPreferredSize(new Dimension(500, 600));
        this.a();
        this.b();
    }
    
    public final void a() {
        super.a();
        this.getContentPane().setLayout(new BorderLayout());
        final JTabbedPane tabbedPane = new JTabbedPane();
        this.getContentPane().add(tabbedPane, "Center");
        final JTabbedPane tabbedPane2 = tabbedPane;
        final String s = "Items";
        final JPanel panel;
        (panel = new JPanel(new BorderLayout())).add(this.a, "Center");
        final JPanel panel2 = new JPanel(new GridLayout(4, 1, 5, 5));
        final JButton button;
        (button = new JButton("Prev")).setActionCommand("P");
        button.addActionListener(this);
        panel2.add(button);
        final JButton button2;
        (button2 = new JButton("Next")).setActionCommand("N");
        button2.addActionListener(this);
        panel2.add(button2);
        final JButton button3;
        (button3 = new JButton("Delete")).setActionCommand("D");
        button3.addActionListener(this);
        panel2.add(button3);
        panel.add(panel2, "East");
        tabbedPane2.addTab(s, panel);
        final JTabbedPane tabbedPane3 = tabbedPane;
        final String s2 = "Properties";
        final JTextArea textArea;
        (textArea = new JTextArea(rp_au.a.toString())).setEditable(false);
        tabbedPane3.addTab(s2, new JScrollPane(textArea));
        tabbedPane.addTab("Save", this.a());
        final JButton button4;
        (button4 = new JButton("Close")).setActionCommand("X");
        button4.addActionListener(this);
        this.getContentPane().add(button4, "South");
        this.pack();
    }
    
    private Component a() {
        String s;
        try {
            s = this.a.a(null, rp_fl.c).a.toString();
        }
        catch (IOException ex) {
            s = "Error saving plane: " + ex;
        }
        final JTextArea textArea;
        (textArea = new JTextArea(s)).setEditable(false);
        final Container container;
        (container = new JPanel(new BorderLayout())).add(new JScrollPane(textArea), "Center");
        final Component component;
        ((AbstractButton)(component = new JButton("Copy to Console"))).addActionListener(new rp_bv(this, textArea));
        final JPanel panel;
        (panel = new JPanel()).add(component);
        container.add(panel, "South");
        return container;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("P")) {
            if (this.a > 0) {
                --this.a;
                this.d();
            }
            return;
        }
        if (actionEvent.getActionCommand().equals("N")) {
            if (this.a + 1 < this.a.length) {
                ++this.a;
                this.d();
            }
            return;
        }
        if (actionEvent.getActionCommand().equals("X")) {
            this.dispose();
            return;
        }
        if (!actionEvent.getActionCommand().equals("D")) {
            return;
        }
        if (this.a == null || this.a.length == 0) {
            return;
        }
        final rp_dC rp_dC;
        if ((rp_dC = this.a[this.a]) == null) {
            return;
        }
        this.a.a.a(new rp_fe(new rp_dC[] { rp_dC }));
        this.c();
        if (this.a >= this.a.length) {
            --this.a;
        }
        this.d();
        this.a.repaint();
    }
    
    private void c() {
        this.a = new rp_dC[this.a.a.a.size()];
        int n = 0;
        final Enumeration a = this.a.a.a(3);
        while (a.hasMoreElements()) {
            this.a[n++] = a.nextElement();
        }
    }
    
    private void d() {
        this.a.setText("");
        if (this.a == null || this.a.length == 0) {
            return;
        }
        final rp_dC rp_dC;
        if ((rp_dC = this.a[this.a]) == null) {
            return;
        }
        final StringBuffer sb;
        (sb = new StringBuffer()).append("ID=");
        sb.append(rp_dC.h);
        sb.append('\n');
        rp_dC.a(sb, rp_au.a);
        this.a.setText(sb.toString());
    }
}
