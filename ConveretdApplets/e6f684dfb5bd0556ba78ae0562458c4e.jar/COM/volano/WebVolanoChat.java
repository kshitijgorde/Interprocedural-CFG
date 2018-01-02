// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.util.Enumeration;
import java.util.Date;
import java.net.URL;
import java.awt.List;
import java.awt.TextField;
import java.awt.Button;
import java.io.IOException;
import java.awt.Event;
import java.awt.GridBagConstraints;
import java.awt.Component;
import java.awt.LayoutManager;
import java.util.Vector;
import java.util.Hashtable;
import java.awt.GridBagLayout;
import java.applet.Applet;

public class WebVolanoChat extends Applet implements IClient, Runnable
{
    public static final int uj = 25000;
    private static final String xe = "";
    private static final char vj = '*';
    private static final int wj = 0;
    private static final int xj = 1;
    private static final int yj = 2;
    private static final int zj = -1;
    private String dk;
    private String ek;
    private String fk;
    private GridBagLayout gk;
    private cz hk;
    private db ik;
    private Server jk;
    private aq wc;
    private ap kk;
    private int lk;
    private Hashtable mk;
    private Vector nk;
    private dc nb;
    private boolean ok;
    private Thread pk;
    
    public String getAppletInfo() {
        return this.getClass().getName() + " " + "2.1.10.2" + " " + "(www.volano.com)" + "\n" + "Copyright © 1996-2001 Volano LLC. All rights reserved.";
    }
    
    public void init() {
        System.out.println(this.getAppletInfo());
        this.setLayout(this.gk);
        this.wc = new aq(this);
        this.kk = new ap(this.wc);
        this.dk = this.wc.ff;
        this.ek = this.wc.el;
        this.fk = this.wc.re;
        this.setBackground(this.wc.sl);
    }
    
    public void start() {
        if (this.pk == null) {
            (this.pk = new Thread(this, this.getClass().getName())).start();
        }
        this.kk.ok(0);
        if (this.lk == -1) {
            this.mg();
        }
    }
    
    public void stop() {
        if (this.pk != null) {
            final Thread pk = this.pk;
            this.pk = null;
            pk.interrupt();
        }
        this.kk.ok(1);
        if (this.lk == 1) {
            this.jg();
            return;
        }
        this.kg();
    }
    
    private synchronized void gg() {
        this.ok = true;
        this.notify();
    }
    
    public synchronized void run() {
        final Thread currentThread = Thread.currentThread();
        try {
            while (this.pk == currentThread) {
                while (!this.ok) {
                    this.wait();
                }
                this.ok = false;
                if (this.wc.ul.equals("")) {
                    this.nb.mb("Missing \"group\" applet parameter. Check HTML source.");
                }
                else {
                    this.lg();
                }
            }
        }
        catch (InterruptedException ex) {}
    }
    
    public void sc(final Component component, final int gridx, final int gridy, final int gridwidth, final boolean b, final boolean b2) {
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = 18;
        gridBagConstraints.gridx = gridx;
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = (b ? 1 : 0);
        gridBagConstraints.weighty = (b2 ? 1 : 0);
        gridBagConstraints.gridwidth = gridwidth;
        gridBagConstraints.gridheight = 1;
        this.gk.setConstraints(component, gridBagConstraints);
        this.add(component);
    }
    
    public boolean handleEvent(final Event event) {
        if (!(event.target instanceof da)) {
            return super.handleEvent(event);
        }
        final da da = (da)event.target;
        switch (event.id) {
            case 201: {
                this.ng(da.hc());
                return true;
            }
            case 1001: {
                final String ye = ye(da.ec(), this.wc.ln);
                da.fc(ye);
                try {
                    this.jk.a(da.hc(), this.wc.ff, ye);
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                    this.jg();
                }
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public boolean action(final Event event, final Object o) {
        switch (this.lk) {
            case 0: {
                if (event.target instanceof Button) {
                    this.wc.ff = this.hk.jc();
                    this.wc.el = this.hk.xg();
                    this.wc.re = this.hk.rb();
                    this.gg();
                    return true;
                }
                break;
            }
            case 1: {
                if (event.target instanceof TextField) {
                    final String ye = ye(this.ik.ec(), this.wc.ln);
                    if (this.ik.o() == 2) {
                        this.ik.mb(this.wc.lq);
                    }
                    else {
                        this.ik.fc(ye);
                    }
                    try {
                        this.jk.a(this.wc.ul, this.wc.ff, ye);
                    }
                    catch (IOException ex) {
                        ex.printStackTrace();
                        this.jg();
                    }
                    return true;
                }
                if (event.target instanceof List) {
                    try {
                        final String selectedItem = ((List)event.target).getSelectedItem();
                        if (!selectedItem.equals(this.wc.ff)) {
                            if (this.wc.zl != -1 && this.mk.size() >= this.wc.zl) {
                                this.nb.mb(ce.lk(this.wc.ft, new Integer(this.wc.zl)));
                            }
                            else {
                                this.nb.mb(ce.lk(this.wc.ts, selectedItem));
                                this.jk.d(this.wc.ul, this.wc.ff, selectedItem);
                            }
                        }
                    }
                    catch (IOException ex2) {
                        ex2.printStackTrace();
                        this.jg();
                    }
                    return true;
                }
                break;
            }
            case 2: {
                if (event.target instanceof Button) {
                    this.mg();
                    return true;
                }
                break;
            }
        }
        return false;
    }
    
    private void lg() {
        this.nb.disable();
        (this.nk = new Vector()).addElement(this.wc.il);
        this.nk.addElement(new Integer(this.wc.xl));
        this.nb.mb(ce.lk(this.wc.to, this.nk));
        try {
            this.kg();
            this.jk = new Server(this, this.wc.il, this.wc.xl);
            this.nb.mb(this.wc.uo);
            if (this.wc.se) {
                this.jk.g(this.wc.ul, "2.1.0", this.wc.vg.toExternalForm(), this.wc.kh.toExternalForm(), this.wc.lh, this.wc.mh, this.wc.nh, this.wc.oh, this.wc.ph, this.wc.qh, this.wc.rh, this.wc.se, false, false, this.wc.ff, this.wc.el, "", false, "");
                return;
            }
            this.jk.g(this.wc.ul, "2.1.0", this.wc.vg.toExternalForm(), this.wc.kh.toExternalForm(), this.wc.lh, this.wc.mh, this.wc.nh, this.wc.oh, this.wc.ph, this.wc.qh, this.wc.rh);
        }
        catch (IOException ex) {
            ex.printStackTrace();
            this.fg(this.wc.un);
        }
    }
    
    private void kg() {
        if (this.jk != null) {
            try {
                this.jk.c();
                this.jk = null;
            }
            catch (IOException ex) {}
        }
    }
    
    private void fg(final URL url) {
        this.nb.mb(ce.lk(this.wc.vo, this.nk));
        this.nb.enable();
        if (this.wc.pn) {
            this.wc.lb.showDocument(url, "_blank");
        }
        else {
            this.wc.lb.showDocument(url);
        }
        if (this.nb == this.ik) {
            this.jg();
            return;
        }
        this.kg();
    }
    
    private void zf() {
        this.removeAll();
        this.hk = null;
        this.sc(this.ik = new db(this.wc, this.kk), 0, 0, 1, true, true);
        this.invalidate();
        this.validate();
        this.nb = this.ik;
    }
    
    private void eg() {
        this.removeAll();
        this.ik = null;
        this.sc(this.hk = new cz(this.wc), 0, 0, 2, true, false);
        this.invalidate();
        this.validate();
        this.hk.kk(this.wc.em);
        this.hk.ik(this.wc.fm);
        this.nb = this.hk;
    }
    
    private void mg() {
        this.lk = 0;
        if (this.wc.se) {
            if (this.wc.fm) {
                if (this.dk.equals("") || this.ek.equals("") || this.fk.equals("")) {
                    this.eg();
                    return;
                }
                this.zf();
                this.gg();
            }
            else {
                if (this.dk.equals("") || this.ek.equals("")) {
                    this.eg();
                    return;
                }
                this.zf();
                this.gg();
            }
        }
        else {
            if (this.dk.equals("") || this.fk.equals("")) {
                this.eg();
                return;
            }
            this.zf();
            this.gg();
        }
    }
    
    private void hg(final String s, final int n, final int n2, final String[][] array) {
        this.lk = 1;
        if (this.ik == null) {
            this.zf();
        }
        this.ik.mb(this.wc.jq);
        this.ik.wh(s, n, n2);
        this.ik.xh(array);
    }
    
    private void jg() {
        this.lk = 2;
        if (this.jk != null) {
            try {
                this.jk.k(this.wc.ul, this.wc.ff);
            }
            catch (IOException ex) {}
            this.kg();
        }
        if (this.ik != null) {
            this.hf(ce.lk(this.wc.nt, new Date()));
            this.ik.c();
        }
    }
    
    public void g(final int n, final String[] array, final String re) {
        switch (n) {
            case 1: {
                this.nb.mb(ce.lk(this.wc.ss, this.wc.ul));
                try {
                    if (this.wc.se && this.wc.re.length() == 0) {
                        this.wc.re = re;
                    }
                    this.jk.h(this.wc.ul, this.wc.ff, this.wc.re);
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                    this.nb.mb(ce.lk(this.wc.vo, this.nk));
                }
            }
            case 5: {
                this.fg(this.wc.tn);
            }
            case 2: {
                this.fg(this.wc.sn);
            }
            case 3: {
                this.fg(this.wc.rn);
            }
            case 4: {
                this.fg(this.wc.vn);
                break;
            }
        }
    }
    
    public void j(final String[] array) {
    }
    
    public void f(final int n, final String s, final String[][] array) {
    }
    
    public void h(final int n, final String s, final int n2, final int n3, final String[][] array) {
        switch (n) {
            case 1: {
                this.kk.ok(2);
                this.nb.enable();
                this.hg(s, n2, n3, array);
            }
            case 2: {
                this.nb.mb(ce.lk(this.wc.zs, s));
                this.nb.enable();
                if (this.nb == this.ik) {
                    this.jg();
                    return;
                }
                this.kg();
            }
            case 4: {
                this.nb.mb(ce.lk(this.wc.ys, this.wc.ff));
                this.nb.enable();
                if (this.nb == this.ik) {
                    this.jg();
                    return;
                }
                this.kg();
            }
            case 3: {
                final Vector<String> vector = new Vector<String>();
                vector.addElement(this.wc.ff);
                vector.addElement(s);
                this.nb.mb(ce.lk(this.wc.xs, vector));
                this.nb.enable();
                if (this.nb == this.ik) {
                    this.jg();
                    return;
                }
                this.kg();
            }
            case 5: {
                this.nb.mb(this.wc.us);
                this.nb.enable();
                if (this.nb == this.ik) {
                    this.jg();
                    return;
                }
                this.kg();
            }
            default: {}
        }
    }
    
    public void h(final String s, final String s2, final String s3, final String s4, final boolean b, final boolean b2) {
        if (s2.length() != 0) {
            this.ik.oe(s2, s3, s4, b, b2);
        }
    }
    
    public void k(final String s, final String s2) {
        this.ik.se(s2);
    }
    
    public void d(final int n, final String title, final String s, final String s2) {
        if (this.wc.zl != -1 && this.mk.size() >= this.wc.zl) {
            try {
                this.jk.i(n, this.wc.ff);
                return;
            }
            catch (IOException ex) {
                ex.printStackTrace();
                this.jg();
                return;
            }
        }
        final Integer n2 = new Integer(n);
        final da da = new da(this.wc, this);
        da.setTitle(title);
        da.ic(n);
        da.gc(title);
        this.mk.put(n2, da);
        da.pack();
        da.show();
        this.nb.mb(this.wc.jq);
    }
    
    public void i(final int n, final String s) {
        final da da = this.mk.get(new Integer(n));
        if (da != null) {
            final Vector<Date> vector = new Vector<Date>();
            vector.addElement(new Date());
            vector.addElement((Date)da.jc());
            da.kb(ce.lk(this.wc.mt, vector));
            da.kc(false);
        }
    }
    
    public void a(final String s, final String s2, final String s3) {
        this.ik.fc(s2, s3);
    }
    
    public void a(final int n, final String s, final String s2) {
        final da da = this.mk.get(new Integer(n));
        if (da != null) {
            da.fc(s, s2);
        }
    }
    
    public void a(final String s) {
        final Vector<Date> vector = new Vector<Date>();
        vector.addElement(new Date());
        vector.addElement((Date)s);
        this.hf(ce.lk(this.wc.lt, vector));
    }
    
    public void b(final String s, final String s2, final String s3) {
        this.ik.fc(s2, s3);
    }
    
    public void e(final String s, final String s2) {
        if (this.wc.tm) {
            final Vector<Date> vector = new Vector<Date>();
            vector.addElement(new Date());
            vector.addElement((Date)s2);
            this.hf(ce.lk(this.wc.kt, vector));
            this.kk.pk();
        }
    }
    
    public void c() {
        this.kk.ok(3);
        if (this.lk != 2) {
            this.jg();
        }
    }
    
    public void hf(final String s) {
        if (this.ik != null) {
            this.ik.kb(s);
        }
        final Enumeration<da> elements = this.mk.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().kb(s);
        }
    }
    
    public void ng(final int n) {
        final da da = this.mk.remove(new Integer(n));
        if (da.zb()) {
            try {
                this.jk.i(n, this.wc.ff);
            }
            catch (IOException ex) {
                ex.printStackTrace();
                this.jg();
            }
        }
        if (da != null) {
            da.hide();
            da.dispose();
        }
    }
    
    public static void ig(final Component component, final aq aq) {
        if (aq.pm != null) {
            component.setBackground(aq.pm);
        }
        if (aq.qm != null) {
            component.setForeground(aq.qm);
        }
        if (aq.rm != null) {
            component.setFont(aq.rm);
        }
    }
    
    public static String ye(final String s, final int n) {
        if (s.length() <= n) {
            return s;
        }
        return s.trim().substring(0, n);
    }
    
    public static String[] yf(final aq aq) {
        return new String[] { aq.yo, aq.zo, aq.dp, aq.ep, aq.fp, aq.gp, aq.hp, aq.ip, aq.jp, aq.kp, aq.lp, aq.mp };
    }
    
    public WebVolanoChat() {
        this.gk = new GridBagLayout();
        this.lk = -1;
        this.mk = new Hashtable();
    }
}
