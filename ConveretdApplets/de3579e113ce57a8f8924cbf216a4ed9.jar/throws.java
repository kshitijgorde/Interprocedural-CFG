import java.awt.Dimension;
import java.awt.event.WindowListener;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Color;
import java.applet.AudioClip;
import java.util.Hashtable;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class throws extends Frame
{
    transient X;
    private static String o = "\u3ef9\u3ef6\u3efa\u3ef3\u3efb\u3edc\u3ef0\u3ef3\u3ef0\u3eed";
    private static String p = "\u3eef\u3ef6\u3efa\u3efc\u3efa\u3edc\u3ef0\u3ef3\u3ef0\u3eed";
    private static String q = "\u3efd\u3efe\u3efc\u3ef4\u3edc\u3ef0\u3ef3\u3ef0\u3eed";
    private static String C = "\u3eeb\u3efa\u3ee7\u3eeb\u3edc\u3ef0\u3ef3\u3ef0\u3eed";
    private static String D = "\u3eae\u3eb3\u3ebf\u3eae\u3eb3\u3ebf\u3eae\u3eb3\u3ebf\u3eae";
    private static String E = "\u3eae\u3eb3\u3ebf\u3eac\u3eb3\u3ebf\u3eae\u3eb3\u3ebf\u3eac";
    private static String F = "\u3eae\u3eb3\u3ebf\u3eaa\u3eb3\u3ebf\u3eae\u3eb3\u3ebf\u3eaa";
    
    public throws(final int n, final Hashtable hashtable, final super super1, final String[] array, final boolean b, final synchronized synchronized1, final AudioClip audioClip) {
        final Color background = hashtable.get(throws.o);
        final Color color = hashtable.get(throws.p);
        this.setBackground(hashtable.get(throws.q));
        this.setResizable(false);
        final double n2 = 8.0;
        this.setLayout(new try(new double[][] { { n2, -1.0, n2 }, { n2, 25.0, 2.0, -1.0, 2.0, 25.0, n2 } }));
        final var var = new var();
        this.X = new transient(hashtable, super1, array, b, audioClip);
        final volatile volatile1 = new volatile(var, this.X, synchronized1);
        var.setBackground(background);
        var.setForeground(hashtable.get(throws.C));
        this.X.setBackground(background);
        this.X.setForeground(hashtable.get(throws.C));
        volatile1.setBackground(background);
        volatile1.setForeground(hashtable.get(throws.C));
        this.add(var, throws.D);
        this.add(this.X, throws.E);
        this.add(volatile1, throws.F);
        this.addWindowListener(new while(this));
        this.setVisible(true);
    }
    
    public void setVisible(final boolean b) {
        this.pack();
        final Dimension screenSize = this.getToolkit().getScreenSize();
        final Dimension preferredSize = this.getPreferredSize();
        if (screenSize.width > preferredSize.width) {
            this.setLocation((screenSize.width - preferredSize.width) / 2, (screenSize.height - preferredSize.height) / 2);
        }
        else {
            this.setLocation(0, 0);
        }
        super.setVisible(true);
    }
    
    public Dimension getPreferredSize() {
        return this.getMinimumSize();
    }
    
    public synchronized Dimension getMinimumSize() {
        return new Dimension(800, 570);
    }
    
    public void dispose() {
        if (this.X != null) {
            this.X.n();
        }
        super.dispose();
    }
    
    private static String _(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFF3E9F);
        }
        return new String(array);
    }
    
    static {
        throws.o = _(throws.o);
        throws.p = _(throws.p);
        throws.q = _(throws.q);
        throws.C = _(throws.C);
        throws.D = _(throws.D);
        throws.E = _(throws.E);
        throws.F = _(throws.F);
    }
}
