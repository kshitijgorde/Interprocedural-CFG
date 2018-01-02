import java.awt.Event;
import java.awt.Font;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class ListItem
{
    String \u0104;
    Color \u00e2;
    Color \u0128;
    Color \u0129;
    boolean \u00e5;
    Font \u012a;
    int \u012b;
    int \u012c;
    int \u012d;
    int x;
    int y;
    int \u00e7;
    int \u00e6;
    final int \u012e = 3;
    final int \u012f = 400;
    
    ListItem(final String s) {
        this.\u012d(s, Color.black);
    }
    
    ListItem(final String s, final Color color) {
        this.\u012d(s, color);
    }
    
    void \u0128(final int x, final int y, final int \u00e6, final int \u00e7) {
        this.x = x;
        this.y = y;
        this.\u00e6 = \u00e6;
        this.\u00e7 = \u00e7;
    }
    
    public void deselect() {
        this.\u00e5 = false;
    }
    
    Font \u0129() {
        return this.\u012a;
    }
    
    int \u012a() {
        return this.\u00e7;
    }
    
    public String getText() {
        return this.\u0104;
    }
    
    int \u012b() {
        return 3;
    }
    
    int \u012c() {
        return this.\u012c;
    }
    
    void \u012d(final String \u0105, final Color \u0129) {
        this.\u0104 = \u0105;
        this.\u00e2 = Color.white;
        this.\u0129 = \u0129;
        this.\u0128 = Color.cyan;
        this.\u00e5 = false;
        this.\u012a = new Font("roman", 0, 11);
        this.\u012b = 0;
        this.\u012c = 0;
        this.\u012d = 0;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this.\u00e5) {
            this.select();
            return false;
        }
        return false;
    }
    
    public void select() {
        this.\u00e5 = true;
    }
    
    void \u012e(final int \u012b) {
        this.\u012b = \u012b;
    }
    
    public void setText(final String \u0105) {
        this.\u0104 = \u0105;
    }
    
    void \u012f(final int \u012d) {
        this.\u012d = \u012d;
    }
    
    void \u0130(final int n) {
        this.\u012c = n + 3;
        if (this.\u012c > 400) {
            this.\u012c = 400;
        }
    }
}
