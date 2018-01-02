// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.util.Vector;
import java.util.StringTokenizer;
import java.awt.Event;
import java.awt.TextField;

public class cy extends TextField
{
    private static final int qj = 10;
    private aq wc;
    private cd ed;
    private boolean rj;
    private String[] sj;
    
    public cy(final aq wc) {
        this.wc = wc;
        this.sj = new String[12];
        for (int i = 0; i < this.sj.length; ++i) {
            this.sj[i] = "";
        }
        this.rj = false;
        this.ed = new cd(10);
    }
    
    public boolean xf() {
        return this.rj;
    }
    
    public void vf(final boolean rj) {
        this.rj = rj;
    }
    
    public String[] yf() {
        return this.sj;
    }
    
    public void wf(final String[] sj) {
        this.sj = sj;
    }
    
    private boolean x(final Event event, final int n) {
        boolean b = false;
        if ((event.modifiers & 0x8) != 0x0 && this.wc.lo.indexOf(n) != -1) {
            b = true;
        }
        else if ((event.modifiers & 0x2) != 0x0 && this.wc.mo.indexOf(n) != -1) {
            b = true;
        }
        else if ((event.modifiers & 0x4) != 0x0 && this.wc.no.indexOf(n) != -1) {
            b = true;
        }
        else if ((event.modifiers & 0x1) != 0x0 && this.wc.oo.indexOf(n) != -1) {
            b = true;
        }
        return b;
    }
    
    public boolean keyDown(final Event event, final int n) {
        final String text = this.getText();
        boolean b = false;
        if (this.x(event, n)) {
            b = true;
        }
        else {
            switch (n) {
                case 1004: {
                    if (this.rj) {
                        this.setText(this.ed.uc());
                    }
                    b = true;
                    break;
                }
                case 1005: {
                    if (this.rj) {
                        this.setText(this.ed.vc());
                    }
                    b = true;
                    break;
                }
                case 27: {
                    this.setText("");
                    b = true;
                    break;
                }
                case 1008: {
                    this.setText(this.v(this.sj[0], text));
                    break;
                }
                case 1009: {
                    this.setText(this.v(this.sj[1], text));
                    break;
                }
                case 1010: {
                    this.setText(this.v(this.sj[2], text));
                    break;
                }
                case 1011: {
                    this.setText(this.v(this.sj[3], text));
                    break;
                }
                case 1012: {
                    this.setText(this.v(this.sj[4], text));
                    break;
                }
                case 1013: {
                    this.setText(this.v(this.sj[5], text));
                    break;
                }
                case 1014: {
                    this.setText(this.v(this.sj[6], text));
                    break;
                }
                case 1015: {
                    this.setText(this.v(this.sj[7], text));
                    break;
                }
                case 1016: {
                    this.setText(this.v(this.sj[8], text));
                    break;
                }
                case 1017: {
                    this.setText(this.v(this.sj[9], text));
                    break;
                }
                case 1018: {
                    this.setText(this.v(this.sj[10], text));
                    break;
                }
                case 1019: {
                    this.setText(this.v(this.sj[11], text));
                    break;
                }
            }
        }
        return b;
    }
    
    protected String v(final String s, final String s2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s2);
        final Vector<String> vector = new Vector<String>();
        while (stringTokenizer.hasMoreTokens()) {
            vector.addElement(stringTokenizer.nextToken());
        }
        return ce.lk(s, vector);
    }
}
