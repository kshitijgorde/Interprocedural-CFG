// 
// Decompiled by Procyon v0.5.30
// 

package ABLwidgets;

import java.awt.Image;
import java.awt.Canvas;

public class menu_item extends Canvas
{
    Object a;
    public String b;
    public String c;
    String d;
    int e;
    int f;
    
    public menu_item() {
        this.e = 1000;
        this.f = 5000;
    }
    
    public menu_item(final Image a) {
        this.e = 1000;
        this.f = 5000;
        this.a = a;
    }
    
    public menu_item(final String b, final String c) {
        this.e = 1000;
        this.f = 5000;
        this.b = b;
        this.c = c;
        this.d = String.valueOf(this.c) + "=" + this.b;
    }
    
    public menu_item(final String b, final String c, final String d) {
        this.e = 1000;
        this.f = 5000;
        this.b = b;
        this.c = c;
        this.d = d;
    }
    
    public menu_item(final String b, final String c, final String d, final int e, final int f) {
        this.e = 1000;
        this.f = 5000;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }
    
    public boolean a(final menu_item menu_item) {
        return menu_item.a == this.a && utils.a(menu_item.b, this.b) && utils.a(menu_item.c, this.c) && utils.a(menu_item.d, this.d) && menu_item.e == this.e && menu_item.f == this.f;
    }
}
