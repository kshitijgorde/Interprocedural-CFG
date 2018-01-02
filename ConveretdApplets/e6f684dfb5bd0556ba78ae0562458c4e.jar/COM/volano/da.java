// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.awt.Event;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;

public class da extends Frame
{
    private static final String xe = "";
    private aa ye;
    private cy ze;
    private int ef;
    private String ff;
    private aq wc;
    private Component gf;
    private boolean hf;
    private cx if;
    
    public da(final aq wc, final Component gf) {
        this.ye = new aa(25000);
        this.hf = true;
        this.wc = wc;
        this.gf = gf;
        this.ze = new cy(wc);
        (this.if = new cx(this.ze)).sc(this.ye);
        WebVolanoChat.ig(this, wc);
        this.ze.wf(WebVolanoChat.yf(wc));
        this.ze.vf(wc.rj);
        this.ye.setEditable(false);
        this.ze.setEditable(true);
        this.ze.enable();
        this.ye.enable();
        this.setLayout(new BorderLayout());
        this.add("Center", this.ye);
        this.add("South", this.ze);
        this.add("North", this.if);
        this.pack();
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            event.target = this;
            this.gf.postEvent(event);
            return true;
        }
        return super.handleEvent(event);
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.ze && this.hf) {
            if (this.ze.getText().trim().length() > 0) {
                event.target = this;
                this.gf.postEvent(event);
            }
            return true;
        }
        return false;
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
    
    public String jc() {
        return this.ff;
    }
    
    public void gc(final String ff) {
        this.ff = ff;
    }
    
    public int hc() {
        return this.ef;
    }
    
    public void ic(final int ef) {
        this.ef = ef;
    }
    
    public void kc(final boolean hf) {
        this.hf = hf;
    }
    
    public boolean zb() {
        return this.hf;
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
}
