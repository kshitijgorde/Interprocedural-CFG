import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

class wb
{
    private wb a;
    private Rectangle b;
    private int c;
    
    wb() {
        this.b = null;
        this.c = 0;
    }
    
    public boolean a(final Rectangle rectangle) {
        final int u = a.u;
        boolean a = false;
        Label_0090: {
            if (this.b == null) {
                this.b = new Rectangle(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
                a = true;
                if (u == 0) {
                    break Label_0090;
                }
            }
            if (this.b.intersects(rectangle)) {
                a = false;
                if (u == 0) {
                    break Label_0090;
                }
            }
            if (this.a == null) {
                this.a = new wb();
            }
            a = this.a.a(rectangle);
        }
        if (a) {
            ++this.c;
        }
        return a;
    }
    
    public int a() {
        return this.c;
    }
    
    public Rectangle b() {
        return new Rectangle(this.b.x, this.b.y, this.b.width, this.b.height);
    }
    
    public wb c() {
        return this.a;
    }
}
