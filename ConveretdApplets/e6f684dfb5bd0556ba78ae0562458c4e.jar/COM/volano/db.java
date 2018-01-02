// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.util.Date;
import java.net.URLEncoder;
import java.awt.Event;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.Component;
import java.awt.Button;
import java.awt.List;
import java.awt.Label;
import java.awt.GridBagLayout;
import java.util.Vector;
import java.awt.Panel;

public class db extends Panel implements dc
{
    private static final int sh = 30;
    public static final String xt = "true";
    public static final int uj = 25000;
    private static final int yt = 6;
    private static final String xe = "";
    private aq wc;
    private ap kk;
    private String u;
    private Vector xh;
    private Vector we;
    private GridBagLayout gk;
    private cx if;
    private Label vl;
    private aa ye;
    private List yh;
    private cy ze;
    private ab zt;
    private Button du;
    private int gb;
    private int nd;
    
    public db(final aq wc, final ap kk) {
        this.u = "";
        this.xh = new Vector(30);
        this.we = new Vector(30);
        this.gk = new GridBagLayout();
        this.vl = new Label();
        this.ye = new aa(25000);
        this.wc = wc;
        this.kk = kk;
        this.ze = new cy(wc);
        this.u = wc.ff;
        this.yh = new List(wc.on, false);
        this.zt = new ab(wc.lb);
        WebVolanoChat.ig(this, wc);
        WebVolanoChat.ig(this.vl, wc);
        WebVolanoChat.ig(this.ye, wc);
        WebVolanoChat.ig(this.yh, wc);
        WebVolanoChat.ig(this.ze, wc);
        WebVolanoChat.ig(this.zt, wc);
        this.vl.setText(wc.vl);
        this.zt.ob(wc.jq);
        this.ye.setEditable(false);
        (this.if = new cx(this.ze)).sc(this.ye);
        this.if.sc(this.yh);
        this.setLayout(this.gk);
        this.sc(this.vl, 0, 0, 2, true, false);
        this.sc(this.ye, 0, 1, 1, true, true);
        this.sc(this.yh, 1, 1, 1, false, true);
        this.sc(this.ze, 0, 2, 2, true, false);
        this.sc(this.zt, 0, 3, 2, true, false);
        this.sc(this.if, 0, 4, 2, true, false);
    }
    
    private void sc(final Component component, final int gridx, final int gridy, final int gridwidth, final boolean b, final boolean b2) {
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
    
    public Insets insets() {
        return new Insets(6, 6, 6, 6);
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.ze) {
            return this.ze.getText().length() == 0;
        }
        return event.target != this.yh && event.target != this.du;
    }
    
    public boolean keyDown(final Event event, final int n) {
        boolean b = false;
        if (n == 9) {
            if (event.shiftDown()) {
                this.if.pi();
            }
            else {
                this.if.oi();
            }
            b = true;
        }
        else if (n == 10 || n == 13) {
            this.postEvent(new Event(this.ze, 1001, ""));
            b = true;
        }
        return b;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.target == this.yh && event.id == 701) {
            this.ge(event);
            return true;
        }
        return super.handleEvent(event);
    }
    
    private void ge(final Event event) {
        final String[] array = this.we.elementAt((int)event.arg);
        if (this.wc.dm.length() > 0 && Boolean.valueOf(array[3]) && Boolean.valueOf(array[4])) {
            final Vector<String> vector = new Vector<String>(2);
            vector.addElement(URLEncoder.encode(array[0]));
            vector.addElement(URLEncoder.encode(this.u));
            this.zt.ob(this.gb(this.wc, array), this.wc.kn, ce.lk(this.wc.dm, vector));
        }
        else {
            this.zt.ob(this.gb(this.wc, array));
        }
        this.wc.kk.ok(6);
    }
    
    public void xh(final String[][] array) {
        for (int i = 0; i < array.length; ++i) {
            this.yh.addItem(array[i][0]);
            this.xh.addElement(array[i][0]);
            this.we.addElement(array[i]);
        }
    }
    
    public void wh(final String s, final int gb, final int nd) {
        this.gb = gb;
        this.nd = nd;
    }
    
    public int o() {
        return this.gb;
    }
    
    public void oe(final String s, final String s2, final String s3, final boolean b, final boolean b2) {
        final String[] array = { s, s2, s3, String.valueOf(b), String.valueOf(b2) };
        this.xh.addElement(s);
        this.we.addElement(array);
        this.yh.addItem(s);
        if (this.wc.kl) {
            final Vector<String> vector = new Vector<String>(2);
            vector.addElement((String)new Date());
            vector.addElement(this.gb(this.wc, array));
            this.ye.kb(ce.lk(this.wc.jt, vector), "> ");
            if (this.wc.ll) {
                this.kk.pk();
            }
        }
    }
    
    public void se(final String s) {
        final int index = this.xh.indexOf(s);
        if (index != -1) {
            this.xh.removeElementAt(index);
            this.we.removeElementAt(index);
            this.yh.delItem(index);
        }
    }
    
    protected String u(String replace) {
        for (int i = 0; i < this.wc.po.length(); ++i) {
            replace = replace.replace(this.wc.po.charAt(i), this.wc.qo.charAt(i));
        }
        return replace;
    }
    
    public void fc(final String s, final String s2) {
        this.ye.appendText("<" + s + "> " + this.u(s2));
    }
    
    public void fc(final String s) {
        this.ye.appendText("> " + this.u(s));
    }
    
    public void kb(final String s) {
        this.ye.appendText(this.u(s));
    }
    
    public String ec() {
        final String text = this.ze.getText();
        this.ze.setText("");
        return text;
    }
    
    public void c() {
        this.remove(this.ze);
        WebVolanoChat.ig(this.du = new Button(this.wc.hq), this.wc);
        this.sc(this.du, 0, 2, 2, true, false);
        this.if.ni(this.ze, this.du);
        this.invalidate();
        this.validate();
    }
    
    public void mb(final String s) {
        this.zt.ob(s);
    }
    
    private String gb(final aq aq, final String[] array) {
        final Vector<String> vector = new Vector<String>(3);
        vector.addElement(array[0]);
        vector.addElement(array[2]);
        String s;
        if (array[1].length() == 0) {
            s = ce.lk(aq.gt, vector);
        }
        else {
            vector.addElement(array[1]);
            s = ce.lk(aq.ht, vector);
        }
        return s;
    }
}
