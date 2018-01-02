// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.awt.TextField;
import java.awt.Choice;
import java.awt.Frame;

public class bd extends ba
{
    public static String goto;
    public static String else;
    public static String long;
    
    public bd(final Frame frame, final ak[] array, final a a) {
        super(frame, bd.goto, array, null, a);
    }
    
    protected void if() {
        for (int i = 0; i < super.char.length; ++i) {
            if (super.char[i].new.compareTo("Choice") == 0) {
                int n = 0;
                if (super.do[i] != null) {
                    for (int j = 0; j < ((d)super.char[i]).byte.length; ++j) {
                        if (super.do[i].equals(((d)super.char[i]).byte[j])) {
                            n = j;
                            break;
                        }
                    }
                }
                ((Choice)super.case[i]).select(n);
            }
            else if (super.char[i].new.compareTo("Integer") == 0) {
                ((TextField)super.case[i]).setText(((Integer)((super.do[i] != null) ? super.do[i] : new Integer((int)super.char[i].a))).toString());
            }
            else if (super.char[i].new.compareTo("Float") == 0) {
                ((TextField)super.case[i]).setText(((Float)((super.do[i] != null) ? super.do[i] : new Float((int)super.char[i].a))).toString());
            }
        }
    }
    
    static {
        bd.goto = new String("Input Period");
        bd.else = new String("Period:");
        bd.long = new String("Leading space:");
    }
}
