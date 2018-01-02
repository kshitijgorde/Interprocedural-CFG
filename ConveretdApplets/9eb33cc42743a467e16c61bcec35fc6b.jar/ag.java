import java.awt.Dimension;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import java.awt.Button;
import java.awt.Label;
import java.awt.Dialog;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public final class ag extends Thread implements ad
{
    public String gy;
    public String o;
    public String m;
    public String fg;
    public int ff;
    public as gx;
    public b_ gw;
    public bo c;
    public boolean gv;
    public boolean gu;
    public boolean g;
    public Frame a;
    public String[] gt;
    public Dialog gs;
    public co gr;
    public bz gq;
    public Thread gp;
    public Label go;
    public Label gn;
    public Label gm;
    public Label gl;
    public Label gk;
    public Button gj;
    public long gi;
    public long gh;
    public int gg;
    public int gf;
    public int ge;
    public int gd;
    public int gc;
    public boolean gb;
    
    public ag(final String fg, final int ff, final as gx, final b_ gw, final bo c, final Frame a, String gy, String o, final String m, final boolean gv, final boolean gu, final boolean g) throws Exception {
        this.gt = fg(o);
        if (this.gt == null) {
            throw new Exception("Unbalanced quotes in local files list");
        }
        final File file = new File(this.gt[0]);
        if (file.isAbsolute()) {
            gy = file.getParent();
            if (gy == null) {
                gy = file.getAbsolutePath();
            }
        }
        this.gt = ff(this.gt, gy);
        if (this.gt.length > 1 && !g) {
            throw new Exception("Ambiguos local target");
        }
        if (!g) {
            o = this.gt[0];
        }
        this.fg = fg;
        this.gy = gy;
        this.ff = ff;
        this.gx = gx;
        this.gw = gw;
        this.c = c;
        this.a = a;
        this.o = o;
        this.m = m;
        this.gv = gv;
        this.gu = gu;
        this.g = g;
        this.gc = 0;
        this.gb = false;
        this.gi = 0L;
        this.gh = 0L;
        this.gg = 0;
        this.gf = 0;
        this.gd = 0;
        this.start();
    }
    
    public final void run() {
        String string = "localhost:" + this.o;
        String string2 = String.valueOf(this.fg) + ":" + this.m;
        if (!this.g) {
            final String s = string;
            string = string2;
            string2 = s;
        }
        this.gs = new Dialog(this.a, "MindTerm - File Transfer", false);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.gs.setLayout(layout);
        gridBagConstraints.fill = 2;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        final Label label = new Label("Source:");
        layout.setConstraints(label, gridBagConstraints);
        this.gs.add(label);
        gridBagConstraints.gridwidth = 4;
        layout.setConstraints(this.go = new Label(this.fc(string, 32)), gridBagConstraints);
        this.gs.add(this.go);
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        final Label label2 = new Label("Destination:");
        layout.setConstraints(label2, gridBagConstraints);
        this.gs.add(label2);
        gridBagConstraints.gridwidth = 4;
        layout.setConstraints(this.gn = new Label(this.fc(string2, 32)), gridBagConstraints);
        this.gs.add(this.gn);
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 1;
        final Label label3 = new Label("Current:");
        layout.setConstraints(label3, gridBagConstraints);
        this.gs.add(label3);
        gridBagConstraints.gridwidth = 3;
        layout.setConstraints(this.gl = new Label("connecting..."), gridBagConstraints);
        this.gs.add(this.gl);
        gridBagConstraints.gridwidth = 1;
        layout.setConstraints(this.gm = new Label(""), gridBagConstraints);
        this.gs.add(this.gm);
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.insets = new Insets(4, 12, 4, 4);
        layout.setConstraints(this.gr = new co(512, 160, 20), gridBagConstraints);
        this.gs.add(this.gr);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        gridBagConstraints.fill = 2;
        layout.setConstraints(this.gk = new Label("0.0 kB/sec", 1), gridBagConstraints);
        this.gs.add(this.gk);
        gridBagConstraints.gridy = 4;
        (this.gj = new Button("Cancel")).addActionListener(new af(this));
        gridBagConstraints.fill = 0;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.ipadx = 2;
        layout.setConstraints(this.gj, gridBagConstraints);
        this.gs.add(this.gj);
        a.b(this.gs);
        final Dimension size;
        final Dimension dimension = size = this.gk.getSize();
        size.width += dimension.width * 2;
        this.gk.setSize(dimension);
        this.gm.setSize(dimension);
        this.gs.setResizable(true);
        this.gs.pack();
        a.c(this.gs);
        this.gp = new Thread(new ae(this));
        if (this.gu) {
            this.gp.setPriority(1);
        }
        this.gp.start();
        this.gs.setVisible(true);
    }
    
    public static String[] fg(String trim) {
        int n = 0;
        int n2 = 0;
        final String[] array = new String[trim.length() / 2];
        boolean b = false;
        trim = trim.trim();
        int n3;
    Label_0126:
        while ((n3 = trim.indexOf(32, n)) >= 0) {
            if (trim.charAt(n) == '\"') {
                ++n;
                n3 = trim.indexOf(34, n);
                if (n3 == -1) {
                    return null;
                }
            }
            String s = trim.substring(n, n3);
            if (s.endsWith(File.separator)) {
                s = s.substring(0, s.length() - 1);
            }
            array[n2++] = s;
            n = n3;
            while (++n != trim.length()) {
                if (trim.charAt(n) != ' ') {
                    continue Label_0126;
                }
            }
            b = true;
        }
        if (!b) {
            if (trim.charAt(n) == '\"') {
                ++n;
                if (trim.indexOf(34, n) == -1) {
                    return null;
                }
            }
            String s2 = trim.substring(n);
            if (s2.endsWith(File.separator)) {
                s2 = s2.substring(0, s2.length() - 1);
            }
            array[n2++] = s2;
        }
        final String[] array2 = array;
        final String[] array3 = new String[n2];
        System.arraycopy(array2, 0, array3, 0, n2);
        return array3;
    }
    
    public static String[] ff(final String[] array, final String s) {
        int n = 0;
        final String[] array2 = new String[4096];
        final String[] list = new File(s).list();
        for (int i = 0; i < array.length; ++i) {
            String name = array[i];
            String string = "";
            int n2 = name.indexOf(42);
            if (n2 == -1) {
                n = fe(array2, name, n);
            }
            else {
                final File file = new File(name);
                String[] list2;
                if (!file.isAbsolute()) {
                    list2 = list;
                }
                else {
                    String parent = file.getParent();
                    if (parent == null) {
                        parent = new String(File.separator);
                    }
                    list2 = new File(parent).list();
                    name = file.getName();
                    string = String.valueOf(parent) + File.separator;
                    n2 = name.indexOf(42);
                }
                final String substring = name.substring(0, n2);
                final String substring2 = name.substring(n2 + 1);
                for (int j = 0; j < list2.length; ++j) {
                    final String s2 = list2[j];
                    if (s2.startsWith(substring) && s2.endsWith(substring2)) {
                        n = fe(array2, String.valueOf(string) + s2, n);
                    }
                }
            }
        }
        final String[] array3 = array2;
        final String[] array4 = new String[n];
        System.arraycopy(array3, 0, array4, 0, n);
        return array4;
    }
    
    public static int fe(final String[] array, final String s, int n) {
        int n2;
        for (n2 = 0; n2 < n && !array[n2].equals(s); ++n2) {}
        if (n2 == n) {
            array[n++] = s;
        }
        return n;
    }
    
    public final void fb(final String s) {
        this.gl.setText("...connected");
    }
    
    public final void fa(final String text, final int ge) {
        this.gm.setText(String.valueOf(this.fd(ge / 1024.0)) + " kB");
        this.gl.setText(text);
        this.gr.n_(ge, true);
        if (this.gi == 0L) {
            this.gi = System.currentTimeMillis();
        }
        this.ge = ge;
        this.gf = 0;
        ++this.gc;
    }
    
    public final void e9(String substring) {
        if (this.gi == 0L) {
            this.gi = System.currentTimeMillis();
        }
        if (substring.length() > this.gy.length()) {
            substring = substring.substring(this.gy.length());
        }
        if (this.g) {
            this.go.setText(this.fc("localhost:" + substring, 32));
        }
        else {
            this.gn.setText(this.fc("localhost:" + substring, 32));
        }
    }
    
    public final void e8() {
        this.gr.n0(this.ge, true);
    }
    
    public final void e7() {
    }
    
    public final void e6(final int n) {
        this.gg += n;
        this.gf += n;
        if (this.ge > 0 && (this.gg - this.gd) * 100 / this.ge >= 1) {
            this.gr.n0(this.gf, !this.gu);
            final long currentTimeMillis = System.currentTimeMillis();
            final long n2 = (currentTimeMillis - this.gi) / 1000L;
            double n3 = (n2 != 0L) ? (this.gg / 1024.0 / n2) : 0.0;
            final long n4 = currentTimeMillis - this.gh;
            if (n4 != 0L) {
                n3 = (n3 + (this.gg - this.gd) / 1024.0 / n4) / 2.0;
            }
            this.gk.setText(this.fd(n3) + " kB/sec");
            this.gd = this.gg;
            this.gh = currentTimeMillis;
        }
    }
    
    public final double fd(double floor) {
        floor *= 10.0;
        floor = Math.floor(floor);
        floor /= 10.0;
        return floor;
    }
    
    public final String fc(String string, int n) {
        if (string.length() > n) {
            n -= 3;
            string = String.valueOf(string.substring(0, n / 2)) + "..." + string.substring(string.length() - n / 2);
        }
        return string;
    }
}
