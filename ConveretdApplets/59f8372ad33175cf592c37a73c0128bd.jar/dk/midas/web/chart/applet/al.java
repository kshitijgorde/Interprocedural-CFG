// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.util.StringTokenizer;
import java.awt.event.ActionEvent;

public class al
{
    public static final int for = -1;
    private ActionEvent do;
    private StringTokenizer a;
    private int if;
    
    public al(final ActionEvent do1) {
        this.if = -1;
        this.do = do1;
        this.a = new StringTokenizer(do1.getActionCommand(), ";");
        if (this.a.hasMoreTokens()) {
            try {
                this.if = Integer.parseInt(this.a.nextToken());
            }
            catch (NumberFormatException ex) {}
        }
    }
    
    public int new() {
        return this.if;
    }
    
    public Object if() {
        return this.do.getSource();
    }
    
    public String int() {
        return this.do.getActionCommand();
    }
    
    public boolean for() {
        return this.if != -1;
    }
    
    public boolean a() {
        return this.a.hasMoreTokens();
    }
    
    public String do() {
        return this.a.nextToken();
    }
}
