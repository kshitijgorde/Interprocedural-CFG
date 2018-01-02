// 
// Decompiled by Procyon v0.5.30
// 

package ji.graphic;

import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.TextEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.FlowLayout;
import ji.v1base.ek;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.Insets;
import java.awt.GridLayout;
import java.awt.event.TextListener;
import java.util.StringTokenizer;
import java.awt.Label;
import ji.v1base.b5;
import ji.v1base.jiPanel;
import java.awt.Point;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.KeyListener;
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
import ji.v1base.bn;
import java.awt.event.FocusListener;
import ji.v1base.bl;

public class bw extends bl implements FocusListener
{
    private static int a;
    private bn b;
    private String c;
    private bn d;
    private boolean e;
    private boolean f;
    private boolean g;
    private boolean h;
    private Panel i;
    private tc j;
    private tb k;
    private ta l;
    private bj m;
    private bb n;
    private boolean o;
    private boolean p;
    private boolean q;
    private boolean r;
    private b0 s;
    static int t;
    private String u;
    private int v;
    private int w;
    private boolean x;
    private static int y;
    private static int z;
    
    public bw(final Frame frame, final bj bj, final String s, final String s2, final String s3, final String s4, final int n, final boolean b, final boolean b2, final String s5, final char c, final boolean b3, final boolean b4, final boolean b5, final b0 b6, final boolean b7, final int n2, final int n3) {
        super(frame, s, b5);
        this.c = "";
        this.f = false;
        this.g = false;
        this.h = true;
        this.i = new Panel();
        this.m = null;
        this.n = null;
        this.o = false;
        this.p = false;
        this.q = true;
        this.r = false;
        this.s = null;
        this.u = null;
        this.v = 0;
        this.w = 0;
        this.x = false;
        this.a(frame, bj, s, s2, s3, s4, n, b, b2, s5, c, b3, b4, b6, b7, n2, n3);
    }
    
    public bw(final Dialog dialog, final bj bj, final String s, final String s2, final String s3, final String s4, final int n, final boolean b, final boolean b2, final String s5, final char c, final boolean b3, final boolean b4, final boolean b5, final b0 b6, final boolean b7, final int n2, final int n3) {
        super(dialog, s, b5);
        this.c = "";
        this.f = false;
        this.g = false;
        this.h = true;
        this.i = new Panel();
        this.m = null;
        this.n = null;
        this.o = false;
        this.p = false;
        this.q = true;
        this.r = false;
        this.s = null;
        this.u = null;
        this.v = 0;
        this.w = 0;
        this.x = false;
        this.a(dialog, bj, s, s2, s3, s4, n, b, b2, s5, c, b3, b4, b6, b7, n2, n3);
    }
    
    public void a(final Component component, final bj m, final String s, final String s2, final String s3, final String s4, final int n, final boolean f, final boolean o, final String u, final char c, final boolean q, final boolean r, final b0 s5, final boolean x, final int w, final int n2) {
        try {
            this.u = u;
            ji.util.d.b7(false);
            ji.util.e.a(component);
            this.m = m;
            this.x = x;
            this.w = w;
            this.s = s5;
            this.o = o;
            this.q = q;
            this.r = r;
            this.c = ji.util.d.b(232, u);
            this.b = new bn(u, this.c);
            this.d = new bn(u, ji.util.d.b(235, u));
            this.i.add(this.b);
            if (q) {
                this.i.add(this.d);
            }
            this.d().setLayout(new BorderLayout());
            this.f = f;
            if (s3 != null) {
                this.g = true;
                (this.l = new ta(u, this, s2, s3, s4, n, c, q)).addFocusListener(this);
                this.d().add("Center", this.l);
                this.d().add("South", this.i);
                this.setResizable(false);
            }
            else if (f) {
                (this.k = new tb(u, this, s2, s4, q, n2)).addFocusListener(this);
                this.d().add("Center", this.k);
                this.d().add("South", this.i);
                this.setResizable(true);
            }
            else {
                (this.j = new tc(u, this, s2, s4, n, c, q)).addFocusListener(this);
                this.d().add("Center", this.j);
                this.d().add("South", this.i);
                this.setResizable(false);
            }
            this.pack();
            this.addWindowListener(new vi());
            this.b.addActionListener(new vj());
            this.b.addKeyListener(new vg());
            if (q) {
                this.d.addActionListener(new vk());
                this.d.addKeyListener(new vg());
            }
            this.f();
            this.setBackground(ji.util.e.aq());
            if (w > 0) {
                if (this.l != null) {
                    this.l.a(w);
                }
                else if (this.k != null) {
                    this.k.a(w);
                }
                else if (this.j != null) {
                    this.j.a(w);
                }
            }
            if (s5 != null) {
                s5.a(this);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public int a() {
        if (this.l != null) {
            return this.l.a();
        }
        if (this.k != null) {
            return this.k.a();
        }
        if (this.j != null) {
            return this.j.a();
        }
        return this.v;
    }
    
    public final void requestFocus() {
        if (this.l != null) {
            this.l.requestFocus();
        }
        else if (this.k != null) {
            this.k.requestFocus();
        }
        else if (this.j != null) {
            this.j.requestFocus();
        }
    }
    
    public void focusGained(final FocusEvent focusEvent) {
        if (this.s != null) {
            this.s.a(this, focusEvent);
        }
    }
    
    public void focusLost(final FocusEvent focusEvent) {
        if (this.s != null) {
            this.s.b(this, focusEvent);
        }
    }
    
    public void b() {
        this.e = true;
        this.g();
    }
    
    public void dispose() {
        try {
            if (this.j != null) {
                this.j.removeFocusListener(this);
            }
        }
        catch (Exception ex) {}
        try {
            if (this.l != null) {
                this.l.removeFocusListener(this);
            }
        }
        catch (Exception ex2) {}
        try {
            if (this.k != null) {
                this.k.removeFocusListener(this);
            }
        }
        catch (Exception ex3) {}
        try {
            if (this.s != null) {
                this.s.a();
                this.s = null;
            }
        }
        catch (Exception ex4) {}
        this.m = null;
        this.b = null;
        this.d = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.n = null;
        this.hide();
    }
    
    private void f() {
        final Dimension size;
        final Dimension dimension = size = this.getSize();
        size.height += 5;
        final Dimension screenSize = this.getToolkit().getScreenSize();
        int y = (screenSize.width - dimension.width) / 2;
        int z = (screenSize.height - dimension.height) / 2;
        if (this.x) {
            if (this.w > 0 && bw.y > 0 && bw.z > 0) {
                y = bw.y;
                z = bw.z;
            }
            else {
                ++bw.t;
                if (bw.t >= ji.util.d.l0) {
                    bw.t = 0;
                }
                y += bw.t * ji.util.d.lz;
                z += bw.t * ji.util.d.lz;
            }
        }
        else {
            bw.t = 0;
        }
        this.setBounds(bw.y = y, bw.z = z, dimension.width, dimension.height);
    }
    
    public void show(final boolean b) {
        this.setBackground(ji.util.e.aq());
        if (!b) {
            super.show();
            if (ji.util.e.at()) {
                if (this.f) {
                    ji.util.e.b(this.k.b());
                }
                else if (this.g) {
                    ji.util.e.b(this.l.b());
                }
                else {
                    ji.util.e.b(this.j.b());
                }
            }
        }
        else {
            super.show(b);
        }
    }
    
    public boolean c() {
        return this.e;
    }
    
    public void setBackground(final Color background) {
        try {
            super.setBackground(background);
            if (this.j != null) {
                this.j.setBackground(background);
            }
            if (this.k != null) {
                this.k.setBackground(background);
            }
            if (this.l != null) {
                this.l.setBackground(background);
            }
            this.i.setBackground(background);
            this.b.setBackground(background);
            this.d.setBackground(background);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void g() {
        this.h = false;
        ji.util.d.b7(true);
        this.v = this.a();
        try {
            final Point location = this.getLocation();
            bw.y = location.x;
            bw.z = location.y;
        }
        catch (Exception ex) {}
        if (this.m != null) {
            try {
                if (this.f) {
                    this.k.b().getText();
                    this.m.a(this, this.k.b().getText());
                }
                else if (this.g) {
                    this.m.a(this, this.l.b().getText());
                }
                else {
                    this.m.a(this, this.j.b().getText());
                }
            }
            catch (Exception ex2) {}
        }
        if (this.s != null) {
            try {
                if (this.f) {
                    this.k.b().getText();
                    this.s.a(this, this.k.b().getText(), this.e);
                }
                else if (this.g) {
                    this.s.a(this, this.l.b().getText(), this.e);
                }
                else {
                    this.s.a(this, this.j.b().getText(), this.e);
                }
            }
            catch (Exception ex3) {}
        }
        this.dispose();
    }
    
    static {
        bw.a = 18;
        bw.t = 0;
        bw.y = 0;
        bw.z = 0;
    }
    
    class tb extends jiPanel
    {
        private b5 a;
        private bw b;
        private Label[] c;
        private Panel d;
        private boolean e;
        
        public void requestFocus() {
            if (this.a != null) {
                ji.util.e.b(this.a);
            }
        }
        
        public void addFocusListener(final FocusListener focusListener) {
            if (this.a != null) {
                this.a.addFocusListener(focusListener);
            }
        }
        
        public void removeFocusListener(final FocusListener focusListener) {
            if (this.a != null) {
                this.a.removeFocusListener(focusListener);
            }
        }
        
        public void a(final int n) {
            if (this.a != null) {
                ji.util.d.a(this.a, n);
            }
        }
        
        public int a() {
            if (this.a != null) {
                return ji.util.d.e((Object)this.a);
            }
            return 0;
        }
        
        public tb(final String s, final bw b, final String s2, final String s3, final boolean b2, final int n) {
            super(s);
            this.c = null;
            this.d = null;
            this.b = b;
            this.e = b2;
            this.setBorderStyle(0);
            try {
                if (s2 != null) {
                    final StringTokenizer stringTokenizer = new StringTokenizer(s2, "\n");
                    final int countTokens = stringTokenizer.countTokens();
                    if (countTokens > 0) {
                        this.c = new Label[countTokens];
                    }
                    int n2 = 0;
                    while (stringTokenizer.hasMoreTokens()) {
                        this.c[n2++] = new Label(stringTokenizer.nextToken(), 1);
                    }
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            this.setLayout(new BorderLayout());
            int n3 = 9;
            int n4 = 40;
            if (this.isSwing()) {
                n3 = 8;
                n4 = 25;
            }
            if (s3 != null) {
                this.a = new b5(s3, n3, n4, n);
            }
            else {
                this.a = new b5("", n3, n4, n);
            }
            this.a.setEditable(b2);
            this.a.addKeyListener(new vg());
            this.a.addTextListener(new vh());
            this.add("Center", this.a);
            if (this.c != null) {
                (this.d = new Panel()).setLayout(new GridLayout(this.c.length, 1));
                for (int i = 0; i < this.c.length; ++i) {
                    this.d.add(this.c[i]);
                }
                this.add("South", this.d);
            }
            if (ji.util.e.at()) {
                ji.util.e.b(this.a);
            }
            this.setBackground(ji.util.e.ao());
        }
        
        public b5 b() {
            return this.a;
        }
        
        public Insets getInsets() {
            return new Insets(5, 5, 5, 5);
        }
        
        public void setBackground(final Color background) {
            try {
                super.setBackground(background);
                if (this.c != null) {
                    for (int i = 0; i < this.c.length; ++i) {
                        this.c[i].setBackground(background);
                    }
                }
                if (this.d != null) {
                    this.d.setBackground(background);
                }
            }
            catch (Exception ex) {}
        }
    }
    
    class vg extends KeyAdapter
    {
        public void keyReleased(final KeyEvent keyEvent) {
            try {
                if (bw.this.o && keyEvent.getKeyCode() == 18) {
                    bw.this.p = true;
                }
            }
            catch (Exception ex) {}
        }
        
        public void keyPressed(final KeyEvent keyEvent) {
            try {
                if (keyEvent.getKeyCode() == 10) {
                    if (ji.util.d.bc(((bn)keyEvent.getSource()).getLabel()).toLowerCase().indexOf(ji.util.d.bc(bw.this.c.toLowerCase())) >= 0) {
                        if (bw.this.f) {
                            if (bw.this.k.b().getText().length() == 0 && !bw.this.r) {
                                bw.this.e = true;
                            }
                            else {
                                bw.this.e = false;
                            }
                        }
                        else if (bw.this.g) {
                            if (bw.this.l.b().getText().length() == 0 && !bw.this.r) {
                                bw.this.e = true;
                            }
                            else {
                                bw.this.e = false;
                            }
                        }
                        else if (bw.this.j.b().getText().length() == 0 && !bw.this.r) {
                            bw.this.e = true;
                        }
                        else {
                            bw.this.e = false;
                        }
                        bw.this.g();
                    }
                    else {
                        bw.this.e = true;
                        bw.this.g();
                    }
                }
                else if (keyEvent.getKeyCode() == 27) {
                    bw.this.e = true;
                    bw.this.g();
                }
                else if (keyEvent.getKeyCode() == 155) {
                    bw.this.e = false;
                    bw.this.g();
                }
            }
            catch (Exception ex) {}
        }
    }
    
    class ta extends jiPanel
    {
        private ek a;
        private bw b;
        private Label[] c;
        private Label[] d;
        private Panel e;
        private Panel f;
        private boolean g;
        
        public void requestFocus() {
            if (this.a != null) {
                ji.util.e.b(this.a);
            }
        }
        
        public void addFocusListener(final FocusListener focusListener) {
            if (this.a != null) {
                this.a.addFocusListener(focusListener);
            }
        }
        
        public void removeFocusListener(final FocusListener focusListener) {
            if (this.a != null) {
                this.a.removeFocusListener(focusListener);
            }
        }
        
        public void a(final int n) {
            if (this.a != null) {
                ji.util.d.a(this.a, n);
            }
        }
        
        public int a() {
            if (this.a != null) {
                return ji.util.d.e((Object)this.a);
            }
            return 0;
        }
        
        public ta(final String s, final bw b, final String s2, final String s3, final String s4, final int n, final char echoChar, final boolean b2) {
            super(s);
            this.c = null;
            this.d = null;
            this.e = null;
            this.f = null;
            this.b = b;
            this.g = b2;
            this.setBorderStyle(0);
            this.setLayout(new BorderLayout());
            final Panel panel = new Panel(new FlowLayout());
            try {
                if (s2 != null) {
                    final StringTokenizer stringTokenizer = new StringTokenizer(s2, "\n");
                    final int countTokens = stringTokenizer.countTokens();
                    if (countTokens > 0) {
                        this.c = new Label[countTokens];
                    }
                    int n2 = 0;
                    while (stringTokenizer.hasMoreTokens()) {
                        this.c[n2++] = new Label(stringTokenizer.nextToken(), 1);
                    }
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            if (this.c != null) {
                this.e = new Panel(new GridLayout(this.c.length, 1));
                for (int i = 0; i < this.c.length; ++i) {
                    this.e.add(this.c[i]);
                }
                panel.add(this.e);
            }
            try {
                if (s2 != null) {
                    final StringTokenizer stringTokenizer2 = new StringTokenizer(s3, "\n");
                    final int countTokens2 = stringTokenizer2.countTokens();
                    if (countTokens2 > 0) {
                        this.d = new Label[countTokens2];
                    }
                    int n3 = 0;
                    while (stringTokenizer2.hasMoreTokens()) {
                        this.d[n3++] = new Label(stringTokenizer2.nextToken(), 1);
                    }
                }
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
            }
            if (this.d != null) {
                this.f = new Panel(new GridLayout(this.d.length, 1));
                for (int j = 0; j < this.d.length; ++j) {
                    this.f.add(this.d[j]);
                }
                this.add(this.f, "North");
            }
            if (s4 != null) {
                if (n != 0) {
                    this.a = new ek(s, s4, n);
                }
                else {
                    this.a = new ek(s, s4);
                }
            }
            else if (n != 0) {
                this.a = new ek(s, n);
            }
            else {
                this.a = new ek(s);
            }
            if (echoChar > '\0') {
                this.a.setEchoChar(echoChar);
            }
            this.a.setEditable(b2);
            this.a.addKeyListener(new vg());
            panel.add(this.a);
            this.add(panel, "South");
            this.a.setFont(new Font("TimesRoman", 0, 14));
            this.a.addActionListener(new adm());
            if (ji.util.e.at()) {
                ji.util.e.b(this.a);
            }
            this.setBackground(ji.util.e.ao());
        }
        
        public ek b() {
            return this.a;
        }
        
        public Insets getInsets() {
            return new Insets(5, 5, 5, 5);
        }
        
        public void setBackground(final Color background) {
            try {
                super.setBackground(background);
                for (int i = 0; i < this.d.length; ++i) {
                    this.d[i].setBackground(background);
                }
                for (int j = 0; j < this.c.length; ++j) {
                    this.c[j].setBackground(background);
                }
            }
            catch (Exception ex) {}
        }
        
        class adm implements ActionListener
        {
            public void actionPerformed(final ActionEvent actionEvent) {
                if (bw.this.l.b().getText().length() == 0 && !bw.this.r) {
                    bw.this.e = true;
                }
                else {
                    bw.this.e = false;
                }
                bw.this.g();
            }
        }
    }
    
    class tc extends jiPanel
    {
        private ek a;
        private bw b;
        private Label[] c;
        private Panel d;
        private boolean e;
        
        public void requestFocus() {
            if (this.a != null) {
                ji.util.e.b(this.a);
            }
        }
        
        public void addFocusListener(final FocusListener focusListener) {
            if (this.a != null) {
                this.a.addFocusListener(focusListener);
            }
        }
        
        public void removeFocusListener(final FocusListener focusListener) {
            if (this.a != null) {
                this.a.removeFocusListener(focusListener);
            }
        }
        
        public void a(final int n) {
            if (this.a != null) {
                ji.util.d.a(this.a, n);
            }
        }
        
        public int a() {
            if (this.a != null) {
                return ji.util.d.e((Object)this.a);
            }
            return 0;
        }
        
        public tc(final String s, final bw b, final String s2, final String s3, final int n, final char echoChar, final boolean b2) {
            super(s);
            this.c = null;
            this.d = null;
            this.b = b;
            this.e = b2;
            this.setBorderStyle(0);
            if (this.isSwing()) {
                this.setLayout(new FlowLayout());
            }
            try {
                if (s2 != null) {
                    final StringTokenizer stringTokenizer = new StringTokenizer(s2, "\n");
                    final int countTokens = stringTokenizer.countTokens();
                    if (countTokens > 0) {
                        this.c = new Label[countTokens];
                    }
                    int n2 = 0;
                    while (stringTokenizer.hasMoreTokens()) {
                        this.c[n2++] = new Label(stringTokenizer.nextToken(), 0);
                    }
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            if (this.c != null) {
                this.d = new Panel(new GridLayout(this.c.length, 1));
                for (int i = 0; i < this.c.length; ++i) {
                    this.d.add(this.c[i]);
                }
                this.add(this.d);
            }
            if (s3 != null) {
                if (n != 0) {
                    this.a = new ek(s, s3, n);
                }
                else {
                    this.a = new ek(s, s3);
                }
            }
            else if (n != 0) {
                this.a = new ek(s, n);
            }
            else {
                this.a = new ek(s);
            }
            if (echoChar > '\0') {
                this.a.setEchoChar(echoChar);
            }
            this.a.setEditable(b2);
            this.a.addKeyListener(new vg());
            this.add(this.a);
            this.a.setFont(new Font("TimesRoman", 0, 14));
            this.a.addActionListener(new adn());
            if (ji.util.e.at()) {
                ji.util.e.b(this.a);
            }
            this.setBackground(ji.util.e.ao());
        }
        
        public ek b() {
            return this.a;
        }
        
        public Insets getInsets() {
            return new Insets(5, 5, 5, 5);
        }
        
        public void setBackground(final Color color) {
            try {
                super.setBackground(color);
                if (this.c != null) {
                    for (int i = 0; i < this.c.length; ++i) {
                        this.c[i].setBackground(color);
                    }
                }
            }
            catch (Exception ex) {}
        }
        
        class adn implements ActionListener
        {
            public void actionPerformed(final ActionEvent actionEvent) {
                if (bw.this.j.b().getText().length() == 0 && !bw.this.r) {
                    bw.this.e = true;
                }
                else {
                    bw.this.e = false;
                }
                bw.this.g();
            }
        }
    }
    
    class vh implements TextListener
    {
        public void textValueChanged(final TextEvent textEvent) {
            if (bw.this.p) {
                bw.this.e = false;
                bw.this.g();
            }
        }
    }
    
    class vi extends WindowAdapter
    {
        public void windowClosing(final WindowEvent windowEvent) {
            bw.this.e = true;
            bw.this.g();
            ji.util.d.ew();
        }
    }
    
    class vk implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            bw.this.e = true;
            bw.this.g();
        }
    }
    
    class vj implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            if (bw.this.f) {
                if (bw.this.k.b().getText().length() == 0 && !bw.this.r) {
                    bw.this.e = true;
                }
                else {
                    bw.this.e = false;
                }
            }
            else if (bw.this.g) {
                if (bw.this.l.b().getText().length() == 0 && !bw.this.r) {
                    bw.this.e = true;
                }
                else {
                    bw.this.e = false;
                }
            }
            else if (bw.this.j.b().getText().length() == 0 && !bw.this.r) {
                bw.this.e = true;
            }
            else {
                bw.this.e = false;
            }
            bw.this.g();
        }
    }
}
