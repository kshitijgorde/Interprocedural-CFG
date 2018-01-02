// 
// Decompiled by Procyon v0.5.30
// 

package JAVACharter.StyleManage;

import java.awt.Font;
import java.awt.Color;

public class b
{
    private Color for;
    private Font try;
    private int[] int;
    private int new;
    private int[] if;
    private int a;
    private String do;
    
    public b(final String s, final Color color, final Font font, final int[] array, final int[] array2) {
        this.new = 0;
        this.a = 0;
        this.a(color);
        this.a(font);
        this.a(s);
        final int length = array.length;
        System.arraycopy(array, 0, this.int = new int[length], 0, length);
        int n = 0;
        int n2 = 0;
        for (final int n3 : array) {
            if (n3 > n) {
                n = n3;
            }
            else if (n3 < n2) {
                n2 = n3;
            }
        }
        this.new = n - n2;
        int n4 = 0;
        int n5 = 0;
        for (final int n6 : array2) {
            if (n6 > n4) {
                n4 = n6;
            }
            else if (n6 < n5) {
                n5 = n6;
            }
        }
        this.a = n4 - n5;
        final int length2 = array2.length;
        System.arraycopy(array2, 0, this.if = new int[length2], 0, length2);
    }
    
    public int[] if() {
        final int[] array = new int[this.int.length];
        System.arraycopy(this.int, 0, array, 0, this.int.length);
        return array;
    }
    
    public int[] try() {
        final int[] array = new int[this.if.length];
        System.arraycopy(this.if, 0, array, 0, this.if.length);
        return array;
    }
    
    public Color int() {
        return this.for;
    }
    
    public void a(final Color for1) {
        this.for = for1;
    }
    
    public Font a() {
        return this.try;
    }
    
    public void a(final Font try1) {
        this.try = try1;
    }
    
    public void a(final String do1) {
        this.do = do1;
    }
    
    public String do() {
        return this.do;
    }
    
    public int for() {
        return this.new;
    }
    
    public int new() {
        return this.a;
    }
}
