// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a.b;

public class j implements a
{
    private a.a.a.a.e.a new;
    private String try;
    
    public j(final a.a.a.a.e.a new1, final String try1) {
        this.new = new1;
        this.try = try1;
    }
    
    public void a() {
        final a.a.a.a.c.a.a a = (a.a.a.a.c.a.a)this.new.try("statemachine");
        if (a == null) {
            return;
        }
        a.new();
        if (this.try.equals("zoomin")) {
            a.a(2);
        }
        else if (this.try.equals("zoomout")) {
            a.a(3);
        }
        else if (this.try.equals("pan")) {
            a.a(4);
        }
        else if (this.try.equals("rotate")) {
            a.a(5);
        }
        else if (this.try.equals("hotspots")) {
            a.else();
        }
        else if (this.try.equals("magnifier")) {
            a.case();
        }
        else if (this.try.equals("toolbar")) {
            a.try();
        }
    }
}
