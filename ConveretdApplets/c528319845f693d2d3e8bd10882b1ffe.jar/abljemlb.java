import java.util.StringTokenizer;
import java.util.Vector;
import java.awt.Point;
import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Insets;
import java.awt.Component;
import java.awt.List;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Panel;
import java.awt.Button;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class abljemlb extends Dialog
{
    public Button a;
    public boolean b;
    public boolean c;
    private abljema d;
    private Object e;
    private abljemtc f;
    private Panel g;
    private GridBagLayout h;
    private GridBagConstraints i;
    private GridBagConstraints j;
    private List k;
    private Button l;
    private Button m;
    private Button n;
    private Component o;
    private String p;
    private Insets q;
    private boolean r;
    private abljemd s;
    private Label t;
    private Button u;
    
    public abljemlb(final abljema d) {
        super(d.fb, "Available Files", false);
        this.c = false;
        this.e = new Object();
        this.d = d;
        this.hide();
        this.b = false;
        this.r = false;
        this.p = new String("--------");
        this.q = new Insets(5, 5, 5, 5);
        if (d.fb != null) {
            this.setBackground(d.fb.o);
            this.setFont(d.fb.n);
        }
        this.f = new abljemtc();
        this.c();
        this.d();
        this.setResizable(false);
        this.pack();
        (this.s = new abljemd(d, d.fb, "BusinessLink File Get", this)).setLayout(new FlowLayout(1, 300, 10));
        if (d.fb != null) {
            this.s.setBackground(d.fb.o);
            this.s.setFont(d.fb.n);
        }
        this.t = new Label("The original browser page must be visible to get files");
        this.s.add(this.t);
        this.u = new Button("OK");
        this.s.add(this.u);
        this.s.resize(320, 120);
    }
    
    private void c() {
        this.k = new List(9, false);
        final int n = this.d.j.ba.countItems() - 1;
        if (n >= 0) {
            this.d.j.ba.delItems(0, n);
        }
    }
    
    private void d() {
        if (this.g != null) {
            this.remove(this.g);
        }
        this.h = new GridBagLayout();
        this.i = new GridBagConstraints();
        (this.g = new Panel()).setLayout(this.h);
        this.setLayout(new BorderLayout());
        this.i.insets = this.q;
        this.j = (GridBagConstraints)this.i.clone();
        this.j.fill = 2;
        this.a = new Button("Retrieve");
        this.l = new Button("Delete");
        this.m = new Button("Refresh");
        this.n = new Button("Close");
        this.a.disable();
        this.l.disable();
        if (this.d.j.hc != null) {
            this.a.enable();
        }
        this.a(this.k, this.h, this.i, 1, 2, 1, 9, 'N');
        this.a(this.a, this.h, this.j, 2, 4, 1, 1, 'C');
        this.a(this.l, this.h, this.j, 3, 4, 1, 1, 'C');
        this.a(this.o, this.h, this.i, 4, 3, 1, 1, 'C');
        this.a(this.o, this.h, this.i, 5, 1, 1, 1, 'C');
        this.a(this.o, this.h, this.i, 5, 4, 1, 1, 'C');
        this.a(this.o, this.h, this.i, 5, 5, 1, 1, 'C');
        this.add("East", this.g);
        this.r = false;
    }
    
    public void paint(final Graphics graphics) {
        super.paint(graphics);
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.a) {
            this.a('B');
        }
        else if (event.target == this.l) {
            this.a(0);
        }
        else if (event.target == this.n) {
            this.e();
        }
        else {
            if (event.target != this.u) {
                return false;
            }
            this.f();
        }
        return true;
    }
    
    public boolean handleEvent(final Event event) {
        if (abljema.b(event)) {
            return super.handleEvent(event);
        }
        if (event.target == this.k && event.id == 701) {
            if (this.d.j.hw.elementAt(this.k.getSelectedIndex()).f) {
                this.l.enable();
            }
            else {
                this.l.disable();
            }
            return true;
        }
        if (event.id == 201) {
            if (event.target == this) {
                this.e();
            }
            else if (event.target == this.s) {
                this.f();
            }
        }
        return super.handleEvent(event);
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (n == 10 && event.modifiers == 0) {
            if (event.target == this.a) {
                this.a('B');
            }
            else if (event.target == this.l) {
                this.a(0);
            }
            else if (event.target == this.n) {
                this.e();
            }
            else if (event.target == this.u) {
                this.f();
            }
            else {
                if ((n != 74 && n != 106) || (event.modifiers & 0x8) == 0x0) {
                    return false;
                }
                abljem.d("------------- Alt + J " + ++this.d.j.bc);
            }
            return true;
        }
        return false;
    }
    
    private void a(final char c) {
        synchronized (this.e) {
            this.b(c);
        }
        // monitorexit(this.e)
    }
    
    private void b(final char c) {
        if (this.d.j.hc == null) {
            abljem.d("jlb error 1");
            return;
        }
        final int selectedIndex = this.k.getSelectedIndex();
        if (selectedIndex < 0) {
            abljem.d("jlb error 3");
            return;
        }
        if (!this.d.e.f) {
            if (!this.isVisible() && !this.d.j.hs) {
                this.a();
                this.toFront();
            }
            this.t.setText("The original browser page must be visible to get files");
            this.t.setAlignment(1);
            this.s.move(this.bounds().x + this.bounds().width / 5, this.bounds().y + this.bounds().height * 7 / 12);
            this.s.show();
            return;
        }
        final String c2 = this.d.j.hw.elementAt(selectedIndex).c;
        final String concat = this.d.j.hc.concat(this.d.j.hw.elementAt(selectedIndex).b);
        try {
            String s = "_self";
            String s2 = concat;
            final URL url = new URL(this.d.f.c(), abljema.f(concat));
            final int length = concat.length();
            if (this.d.b2) {
                s2 = "_blank";
            }
            if (length > 5 && (concat.substring(length - 4).equals(".txt") || concat.substring(length - 4).equals(".htm") || concat.substring(length - 5).equals(".html"))) {
                s = s2;
            }
            if (this.d.b1) {
                s = s2;
            }
            this.a.disable();
            this.d.j.hc = null;
            if (this.a(concat) && this.d.u()) {
                this.a(url, c2, c);
            }
            else {
                this.d.f.b(url, s);
            }
            ((abljemle)this.d.j.hw.elementAt(selectedIndex)).g = false;
            if (!((abljemle)this.d.j.hw.elementAt(selectedIndex)).f) {
                ((abljemle)this.d.j.hw.elementAt(selectedIndex)).f = true;
                this.l.enable();
            }
            if (this.d.j.hd) {
                this.d.j.bm.a();
            }
        }
        catch (MalformedURLException ex) {
            abljem.d("Malformed URL");
        }
    }
    
    private void a(final int n) {
        int selectedIndex = this.k.getSelectedIndex();
        if (selectedIndex < 0) {
            return;
        }
        if (!((abljemle)this.d.j.hw.elementAt(selectedIndex)).f) {
            this.a.requestFocus();
            return;
        }
        final abljemle abljemle = this.d.j.hw.elementAt(selectedIndex);
        this.d.j.hx.put(abljemle.b, new Long(System.currentTimeMillis() + this.d.j.hy));
        this.d.j.bm.a(abljemle.b, n);
        this.k.delItem(selectedIndex);
        this.d.j.ba.delItem(selectedIndex);
        this.d.j.hw.removeElementAt(selectedIndex);
        if (selectedIndex >= this.k.countItems()) {
            selectedIndex = this.k.countItems() - 1;
        }
        if (selectedIndex >= 0) {
            this.k.select(selectedIndex);
            if (!((abljemle)this.d.j.hw.elementAt(selectedIndex)).f) {
                this.l.disable();
            }
        }
        else {
            this.l.disable();
        }
    }
    
    private void e() {
        try {
            if (this.b) {
                this.d.j.ha = this.location();
            }
        }
        catch (Throwable t) {}
        this.hide();
    }
    
    private void f() {
        this.s.hide();
        this.enable();
    }
    
    private void a(final URL url, final String s, final char c) {
        if (this.d.j.hb == null) {
            return;
        }
        final byte[] a = this.d.j.a(this.d.j.a(url), null);
        if (a == null) {
            return;
        }
        if (c == 'A' && this.d.j.hb.length() > 0) {
            try {
                this.d.j.a(a, this.d.j.hb, s);
            }
            catch (Exception ex) {}
            return;
        }
        if (c == 'A') {
            abljem.d("Prompt for initial binary printing path");
            while (!this.d.fb.a(s, a)) {
                if (!abljema.a(100L)) {
                    return;
                }
            }
        }
        else if (!this.d.fb.a(s, a)) {
            return;
        }
    }
    
    public void enable() {
        if (this.d.j.hs) {
            return;
        }
        super.enable();
    }
    
    public void requestFocus() {
        if (this.d.j.hs) {
            return;
        }
        super.requestFocus();
    }
    
    void a() {
        if (this.d.j.hs) {
            return;
        }
        if (!this.r) {
            if (this.d.j.ct != null) {
                try {
                    this.d.j.cv.waitForID(this.d.j.cy);
                }
                catch (InterruptedException ex) {}
                this.f.c(this.d.j.ct);
                this.f.show();
                this.add("Center", this.f);
            }
            this.pack();
            this.r = true;
        }
        final int width = this.k.preferredSize().width;
        final int width2 = this.k.getBounds().width;
        if (width != width2) {
            this.k.resize(width, this.k.bounds().height);
            this.resize(this.getBounds().width + width - width2, this.getBounds().height);
        }
        final int width3 = Toolkit.getDefaultToolkit().getScreenSize().width;
        final int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        if (!this.b) {
            int x = (width3 - this.bounds().width) / 3;
            int y = (height - this.bounds().height) / 3;
            if (this.d.j.ha != null) {
                x = this.d.j.ha.x;
                y = this.d.j.ha.y;
            }
            if (x + this.bounds().width > width3) {
                x = width3 - this.bounds().width - 2;
            }
            if (y + this.bounds().height > height) {
                y = height - this.bounds().height - 2;
            }
            if (x < 0) {
                x = 0;
            }
            if (y < 0) {
                y = 0;
            }
            this.move(x, y);
            this.d.j.ha = new Point(x, y);
            this.b = true;
        }
        if (this.getLocation().x + this.getBounds().width > width3) {
            int n = width3 - this.getBounds().width;
            if (n < 0) {
                n = 0;
            }
            this.setLocation(n, this.getLocation().y);
        }
        this.show();
        this.requestFocus();
    }
    
    public void show() {
        try {
            if (this.d.j.hs) {
                return;
            }
        }
        catch (Throwable t) {
            return;
        }
        try {
            super.show();
        }
        catch (Throwable t2) {}
    }
    
    public void show(final boolean b) {
        try {
            if (this.d.j.hs && b) {
                return;
            }
        }
        catch (Throwable t) {
            return;
        }
        try {
            super.show(b);
        }
        catch (Throwable t2) {}
    }
    
    private void a(Component component, final GridBagLayout gridBagLayout, final GridBagConstraints gridBagConstraints, final int n, final int n2, int gridwidth, int gridheight, final char c) {
        if (component == null) {
            component = new Label("");
        }
        if (n == -1) {
            gridBagConstraints.gridy = -1;
        }
        else {
            gridBagConstraints.gridy = n - 1;
        }
        if (n2 == -1) {
            gridBagConstraints.gridx = -1;
        }
        else {
            gridBagConstraints.gridx = n2 - 1;
        }
        if (gridwidth == -1) {
            gridwidth = 0;
        }
        else if (gridwidth == -2) {
            gridwidth = -1;
        }
        if (gridheight == -1) {
            gridheight = 0;
        }
        else if (gridheight == -2) {
            gridheight = -1;
        }
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.gridwidth = gridwidth;
        gridBagConstraints.gridheight = gridheight;
        switch (c) {
            case 'W': {
                gridBagConstraints.anchor = 17;
                break;
            }
            case 'E': {
                gridBagConstraints.anchor = 13;
                break;
            }
            case 'N': {
                gridBagConstraints.anchor = 11;
                break;
            }
            case 'S': {
                gridBagConstraints.anchor = 15;
                break;
            }
            default: {
                gridBagConstraints.anchor = 10;
                break;
            }
        }
        gridBagLayout.setConstraints(component, gridBagConstraints);
        this.g.add(component);
    }
    
    public void a(final byte[] array, final int n, final int n2) {
        final String s = "\u001f";
        final String s2 = "\u001e";
        this.c = false;
        if (n2 < n + 2) {
            return;
        }
        String selectedItem;
        try {
            selectedItem = this.d.j.ba.getSelectedItem();
        }
        catch (Exception ex) {
            abljem.d("oldcpysel failed " + ex);
            selectedItem = null;
        }
        this.k.clear();
        this.d.j.ba.clear();
        this.a.disable();
        this.l.disable();
        final Vector hw = this.d.j.hw;
        this.d.j.hw = new Vector(10, 10);
        final StringTokenizer stringTokenizer = new StringTokenizer(new String(array, 0, n, n2 - n), s2);
        while (stringTokenizer.hasMoreTokens()) {
            final StringTokenizer stringTokenizer2 = new StringTokenizer(stringTokenizer.nextToken(), s);
            if (stringTokenizer2.countTokens() >= 3) {
                final String nextToken = stringTokenizer2.nextToken();
                String s3 = stringTokenizer2.nextToken();
                final String nextToken2 = stringTokenizer2.nextToken();
                if (nextToken.equals(this.p) || this.d.j.hx.get(nextToken) != null) {
                    continue;
                }
                int n3;
                for (n3 = 0; n3 < nextToken.length() && abljema.c(nextToken.charAt(n3)); ++n3) {}
                if (n3 == 7 || n3 == 10) {
                    ++n3;
                    if (nextToken.length() > n3) {
                        s3 = nextToken.substring(n3);
                    }
                }
                final abljemle abljemle = new abljemle(nextToken, s3, nextToken2, this.d.j.hf);
                int n4;
                for (n4 = 0; n4 < this.d.j.hw.size() && nextToken2.compareTo(((abljemle)this.d.j.hw.elementAt(n4)).d) >= 0; ++n4) {}
                this.d.j.hw.insertElementAt(abljemle, n4);
            }
        }
        for (int i = 0; i < this.d.j.hw.size(); ++i) {
            final String c = this.d.j.hw.elementAt(i).c;
            this.k.addItem(c);
            this.d.j.ba.addItem(c);
            if (c.equals(selectedItem)) {
                this.d.j.ba.select(i);
            }
            if (this.d.j.hq && (!this.d.hn || !this.a(((abljemle)this.d.j.hw.elementAt(i)).b))) {
                ((abljemle)this.d.j.hw.elementAt(i)).g = false;
            }
        }
        if (hw != null) {
            for (int j = 0; j < hw.size(); ++j) {
                final String b = hw.elementAt(j).b;
                for (int k = 0; k < this.d.j.hw.size(); ++k) {
                    if (b.equals(((abljemle)this.d.j.hw.elementAt(k)).b)) {
                        ((abljemle)this.d.j.hw.elementAt(k)).g = hw.elementAt(j).g;
                        ((abljemle)this.d.j.hw.elementAt(k)).f = hw.elementAt(j).f;
                    }
                }
            }
        }
        int l = 0;
        while (l < this.d.j.hw.size()) {
            final abljemle abljemle2 = this.d.j.hw.elementAt(l);
            Label_0950: {
                if (abljemle2.g) {
                    final boolean a = this.a(abljemle2.b);
                    if (this.d.j.hq) {
                        if (!a || !this.d.j.hn) {
                            break Label_0950;
                        }
                    }
                    else if (a) {
                        if (!this.d.j.hm) {
                            break Label_0950;
                        }
                        if (this.d.j.hh <= 0) {
                            if (!this.d.j.ho) {
                                break Label_0950;
                            }
                        }
                    }
                    else if (!this.d.j.hg || this.d.j.hh <= 0) {
                        break Label_0950;
                    }
                }
                else if (this.d.j.hq) {
                    if (!this.d.j.ht) {
                        break Label_0950;
                    }
                }
                ++l;
                continue;
            }
            this.c = true;
            break;
        }
        if (this.k.countItems() > 0) {
            this.k.select(this.k.countItems() - 1);
            this.a.enable();
            if (this.d.j.hw.elementAt(this.k.getSelectedIndex()).f) {
                this.l.enable();
            }
        }
        this.d.j.ba.enable();
    }
    
    private boolean a(final String s) {
        return s != null && s.length() >= 8 && s.substring(s.length() - 7).equals(".prtbin");
    }
    
    public void b() {
        boolean b = false;
        if (this.d.j.hg || this.d.j.hm) {
            for (int i = 0; i < this.k.countItems(); ++i) {
                if (((abljemle)this.d.j.hw.elementAt(i)).g && this.d.j.hc != null) {
                    final boolean a = this.a(this.d.j.hw.elementAt(i).b);
                    if ((this.d.j.hg && !a) || (this.d.j.hm && a)) {
                        this.k.select(i);
                        this.a('A');
                        if (a && this.d.j.ho) {
                            final int countItems = this.k.countItems();
                            this.a(this.d.j.hi);
                            if (this.k.countItems() < countItems) {
                                --i;
                            }
                        }
                    }
                }
            }
        }
        if (this.d.j.hh > 0 && this.k.countItems() > this.d.j.hh) {
            for (int n = 0, countItems2 = this.k.countItems(); n < this.k.countItems() && this.k.countItems() > this.d.j.hh && countItems2 > 0; --countItems2) {
                this.k.select(n);
                final int countItems3 = this.k.countItems();
                this.a(this.d.j.hi);
                if (this.k.countItems() == countItems3) {
                    ++n;
                }
                else {
                    b = true;
                }
            }
        }
        if (this.k.countItems() > 0) {
            if (b || this.k.getSelectedIndex() < 0) {
                this.k.select(this.k.countItems() - 1);
            }
            this.a.enable();
            if (this.d.j.hw.elementAt(this.k.getSelectedIndex()).f) {
                this.l.enable();
            }
        }
    }
    
    class abljemle
    {
        String b;
        String c;
        String d;
        String e;
        boolean f;
        boolean g;
        
        public abljemle(final String s, final String s2, final String s3, final boolean b) {
            this.a(s, s2, s3, b, "   ");
        }
        
        private void a(final String b, final String c, final String d, final boolean f, final String e) {
            this.b = b;
            this.c = c;
            this.d = d;
            this.e = e;
            while (e.length() < 3) {
                e.concat(" ");
            }
            this.f = f;
            this.g = true;
        }
    }
}
