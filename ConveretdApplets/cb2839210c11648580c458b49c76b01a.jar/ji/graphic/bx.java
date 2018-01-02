// 
// Decompiled by Procyon v0.5.30
// 

package ji.graphic;

import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Label;
import java.awt.TextField;
import ji.v1base.jiPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import ji.util.e;
import ji.util.d;
import java.awt.Dialog;
import java.awt.Component;
import java.awt.Frame;
import ji.awt.bb;
import java.awt.Panel;
import java.awt.Button;
import ji.v1base.bl;

public class bx extends bl
{
    private static int a;
    private Button b;
    private Button c;
    private boolean d;
    private boolean e;
    private Panel f;
    private s2 g;
    private bj h;
    private bb i;
    private String j;
    
    public bx(final Frame frame, final bj bj, final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final int n, final String s7) {
        super(frame, s, true);
        this.e = true;
        this.f = new Panel();
        this.h = null;
        this.i = null;
        this.j = null;
        this.a(frame, bj, s, s2, s3, s4, s5, s6, n, s7);
    }
    
    public bx(final Dialog dialog, final String s, final boolean b) {
        super(dialog, s, true);
        this.e = true;
        this.f = new Panel();
        this.h = null;
        this.i = null;
        this.j = null;
    }
    
    public bx(final Dialog dialog, final bj bj, final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final int n, final String s7) {
        this(dialog, s, true);
        this.a(dialog, bj, s, s2, s3, s4, s5, s6, n, s7);
    }
    
    public void a(final Component component, final bj h, final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final int n, final String j) {
        this.j = j;
        ji.util.d.b7(false);
        ji.util.e.a(component);
        this.h = h;
        this.b = new Button(ji.util.d.b(232, j));
        this.c = new Button(ji.util.d.b(235, j));
        ji.util.e.a(this.b);
        ji.util.e.a(this.c);
        this.f.add(this.b);
        this.f.add(this.c);
        this.setLayout(new BorderLayout());
        this.add("North", this.g = new s2(this, s2, s3, s4, s5, s6, n));
        this.add("Center", this.f);
        this.pack();
        this.addWindowListener(new wz());
        this.b.addActionListener(new w0());
        this.c.addActionListener(new w1());
        this.c();
        this.setResizable(false);
        this.setBackground(ji.util.e.aq());
    }
    
    private void c() {
        final Dimension size;
        final Dimension dimension = size = this.getSize();
        size.height += 5;
        if (ji.util.d.ay(this.j)) {
            final Dimension dimension2 = dimension;
            dimension2.width += 30;
        }
        final Dimension screenSize = this.getToolkit().getScreenSize();
        this.setBounds((screenSize.width - dimension.width) / 2, (screenSize.height - dimension.height) / 2, dimension.width, dimension.height);
    }
    
    public void show() {
        if (this.i == null) {
            (this.i = new bb(this.j, new w2())).setPriority(1);
            this.i.start();
        }
        this.setBackground(ji.util.e.aq());
        super.show();
        if (ji.util.e.at()) {
            ji.util.e.b(this.g.a());
        }
    }
    
    public boolean a() {
        return this.d;
    }
    
    public void setBackground(final Color background) {
        super.setBackground(background);
        this.g.a().setBackground(background);
        this.g.b().setBackground(background);
        this.g.setBackground(background);
        this.f.setBackground(background);
        this.b.setBackground(background);
        this.c.setBackground(background);
    }
    
    public void b() {
        this.d = true;
        this.f();
    }
    
    private void f() {
        this.e = false;
        ji.util.d.b7(true);
        if (this.h != null) {
            try {
                this.h.a(this, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.g.a().getText()))).append("-").append(this.g.b().getText()))));
            }
            catch (Exception ex) {}
        }
        this.hide();
    }
    
    static {
        bx.a = 20;
    }
    
    class s2 extends jiPanel
    {
        private TextField a;
        private TextField b;
        private bx c;
        private Label d;
        private Label e;
        private Label f;
        
        public s2(final bx c, final String s, final String s2, final String s3, final String s4, final String s5, final int n) {
            super(bx.this.j);
            this.d = null;
            this.e = null;
            this.f = null;
            this.c = c;
            this.setBorderStyle(0);
            this.d = new Label(s);
            this.e = new Label(s2);
            this.f = new Label("      ".concat(String.valueOf(String.valueOf(s3))));
            this.add(this.d);
            this.add(this.e);
            if (s4 != null) {
                if (n != 0) {
                    this.add(this.a = new TextField(s4, n));
                }
                else {
                    this.add(this.a = new TextField(s4));
                }
            }
            else if (n != 0) {
                this.add(this.a = new TextField(n * 3));
            }
            else {
                this.add(this.a = new TextField());
            }
            this.add(this.f);
            if (s5 != null) {
                if (n != 0) {
                    this.add(this.b = new TextField(s5, n));
                }
                else {
                    this.add(this.b = new TextField(s5));
                }
            }
            else if (n != 0) {
                this.add(this.b = new TextField(n));
            }
            else {
                this.add(this.b = new TextField());
            }
            this.a.setFont(new Font("TimesRoman", 0, 14));
            this.a.addActionListener(new adp());
            this.b.setFont(new Font("TimesRoman", 0, 14));
            this.b.addActionListener(new adp());
            if (ji.util.e.at()) {
                ji.util.e.b(this.a);
            }
        }
        
        public TextField a() {
            return this.a;
        }
        
        public TextField b() {
            return this.b;
        }
        
        public Insets getInsets() {
            return new Insets(5, 5, 5, 5);
        }
        
        public void setBackground(final Color color) {
            super.setBackground(color);
            try {
                this.a.setBackground(color);
                this.b.setBackground(color);
                this.d.setBackground(color);
                this.e.setBackground(color);
                this.f.setBackground(color);
            }
            catch (Exception ex) {}
        }
        
        class adp implements ActionListener
        {
            public void actionPerformed(final ActionEvent actionEvent) {
                if (bx.this.g.a().getText().length() == 0 || bx.this.g.b().getText().length() == 0) {
                    bx.this.d = true;
                }
                else {
                    bx.this.d = false;
                }
                bx.this.f();
            }
        }
    }
    
    class wz extends WindowAdapter
    {
        public void windowClosing(final WindowEvent windowEvent) {
            bx.this.d = true;
            bx.this.f();
            ji.util.d.ew();
        }
    }
    
    class w1 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            bx.this.d = true;
            bx.this.f();
        }
    }
    
    class w0 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            if (bx.this.g.a().getText().length() == 0 && bx.this.g.b().getText().length() == 0) {
                bx.this.d = true;
            }
            else {
                bx.this.d = false;
            }
            bx.this.f();
        }
    }
    
    class w2 implements Runnable
    {
        public final void run() {
            try {
                while (bx.this.e) {
                    try {
                        for (int n = 0; n < 100 && bx.this.e; ++n) {
                            ji.util.d.b(10, 109, bx.this.j);
                        }
                        if (!bx.this.e || !ji.util.d.dh()) {
                            continue;
                        }
                        bx.this.toFront();
                    }
                    catch (Exception ex) {}
                }
            }
            catch (Exception ex2) {}
            finally {
                bx.this.i = null;
            }
        }
    }
}
