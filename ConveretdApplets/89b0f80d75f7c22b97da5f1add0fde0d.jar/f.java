import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.event.WindowListener;
import java.awt.Component;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

class f extends Dialog implements ActionListener
{
    private final esChat a;
    TextField b;
    boolean c;
    private static String[] z;
    
    f(final esChat a, final String s) {
        super(new Frame(), s, true);
        this.a = a;
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(screenSize.width / 2 - 200, screenSize.height / 2 - 150, 400, 300);
        this.setResizable(false);
        this.setBackground(a.g);
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        final Panel panel = new Panel(gridBagLayout);
        final Label label = new Label(s);
        this.b = new TextField(30);
        gridBagLayout.setConstraints(label, gridBagConstraints);
        panel.add(label);
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(this.b, gridBagConstraints);
        panel.add(this.b);
        final Panel panel2 = new Panel();
        a.getClass();
        final c c = new c(a, a.a(1, "", "", ""));
        a.getClass();
        final c c2 = new c(a, a.a(2, "", "", ""));
        c.a(this);
        c2.a(this);
        panel2.add(c);
        panel2.add(c2);
        this.add(panel, f.z[0]);
        this.add(panel2, f.z[1]);
        this.addWindowListener(new o(this));
        this.pack();
        this.show();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals(this.a.a(1, "", "", ""))) {
            this.a.t = this.b.getText();
            this.c = true;
            this.dispose();
            return;
        }
        this.a.t = "";
        this.c = true;
        this.dispose();
    }
    
    static {
        final String[] z = new String[2];
        final int n = 0;
        final char[] charArray = "-\u0014\u0012w-\u001c".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = 'n';
                    break;
                }
                case 1: {
                    c2 = 'q';
                    break;
                }
                case 2: {
                    c2 = '|';
                    break;
                }
                case 3: {
                    c2 = '\u0003';
                    break;
                }
                default: {
                    c2 = 'H';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "=\u001e\tw ".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = 'n';
                    break;
                }
                case 1: {
                    c4 = 'q';
                    break;
                }
                case 2: {
                    c4 = '|';
                    break;
                }
                case 3: {
                    c4 = '\u0003';
                    break;
                }
                default: {
                    c4 = 'H';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        f.z = z;
    }
}
