import java.awt.image.ImageObserver;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class c
{
    public static void a(final Vector vector, final k k, final int case1, final int int1, final int n) {
        final b new1 = k.new;
        if (new1.else != null && new1.else.goto != null) {
            vector.addElement(new1.else);
        }
        k.case = case1;
        k.int = int1;
        new1.case = new1.int;
        if (new1.do != null) {
            new1.void = new1.do.getWidth(null);
            new1.goto = new1.do.getHeight(null);
            k.for = int1 + new1.goto - 1 + new1.int + new1.b;
        }
        else {
            k.for = int1 - 1 + new1.int + new1.b;
        }
        if (new1.a < 0) {
            k.byte = case1 + new1.void - 1 + new1.f + new1.g;
            new1.char = new1.f;
            return;
        }
        k.byte = case1 + n - 1;
        if (new1.for == 30) {
            new1.char = new1.f + (k.byte - k.case - new1.f - new1.g - new1.void) / 2;
            return;
        }
        if (new1.for == 50) {
            new1.char = new1.f + (k.byte - k.case - new1.f - new1.g - new1.void);
            return;
        }
        new1.char = new1.f;
    }
}
