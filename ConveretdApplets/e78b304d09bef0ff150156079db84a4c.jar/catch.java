import java.util.Enumeration;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.util.Vector;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.Stack;

// 
// Decompiled by Procyon v0.5.30
// 

public class catch implements const
{
    private Stack Oa;
    private Rectangle Ha;
    private int Pa;
    private int Qa;
    private Color Ra;
    private int[] Sa;
    private int Ta;
    private int Ua;
    
    public catch(final int n, final int n2, final Dimension dimension, final int pa, final int ua) {
        this.Ra = Color.blue;
        this.Pa = pa;
        this.Ua = ua;
        this.Qa = dimension.height;
        this.Ha = new Rectangle(n, n2, dimension.width, this.Qa);
        this.reset();
    }
    
    public void b(final class class1) {
        if (class1 != null) {
            this.Oa.push(class1);
            this.d();
            this.e();
        }
    }
    
    public void reset() {
        this.Oa = new Stack();
        this.Ha.height = this.Qa;
    }
    
    public void b() {
        if (this.Oa.size() > 0) {
            this.Oa.pop();
            this.d();
            this.e();
        }
    }
    
    public boolean b(final class class1) {
        if (this._(class1)) {
            this.Oa.push(class1);
            this.d();
            this.e();
            return true;
        }
        return false;
    }
    
    public boolean _(final class class1) {
        return class1 != null && (this.Oa.size() == 0 || this._(this.Oa.peek(), class1));
    }
    
    private final boolean _(final class class1, final class class2) {
        return class1.a() != class2.a() && class1.getID() - 1 == class2.getID();
    }
    
    public class[] _(final catch catch1, int length) {
        if (++length > 1 && length < 6) {
            final class[] _ = this._();
            if (length > _.length) {
                length = _.length;
            }
            final class[] array = new class[length];
            for (int i = _.length - length; i < _.length; ++i) {
                array[i - (_.length - length)] = _[i];
            }
            final Vector vector = new Vector<class>();
            vector.addElement(array[array.length - 1]);
            for (int n = array.length - 1; n >= 1 && this._(array[n - 1], array[n]); --n) {
                vector.addElement(array[n - 1]);
            }
            final int size = vector.size();
            class[] array2 = null;
            if (size > 1) {
                array2 = new class[size];
                for (int j = 0; j < size; ++j) {
                    array2[j] = vector.elementAt(j);
                }
            }
            if (array2 != null) {
                final Vector vector2 = new Vector<class>();
                final class b;
                if ((b = catch1.b()) != null) {
                    int n2 = 0;
                    for (int k = array2.length - 1; k >= 0; --k) {
                        if (n2 != 0 || this._(b, array2[k])) {
                            n2 = 1;
                            vector2.addElement(array2[k]);
                        }
                    }
                    final int size2 = vector2.size();
                    if (size2 > 1) {
                        final class[] array3 = new class[size2];
                        for (int l = 0; l < size2; ++l) {
                            array3[l] = vector2.elementAt(l);
                        }
                        return array3;
                    }
                }
            }
        }
        return null;
    }
    
    public void a(final Graphics graphics) {
        final Enumeration elements = this.Oa.elements();
        int n = 0;
        int n2 = 0;
        while (elements.hasMoreElements()) {
            final class class1 = elements.nextElement();
            n2 += this.Sa[n];
            graphics.drawImage(class1.b(), this.Ha.x, this.Ha.y + n2, null);
            if (class1.g()) {
                graphics.setColor(this.Ra);
                default._(this.Ha.x, this.Ha.y + this.Ta, this.Ha.width - 1, this.Qa - 1, graphics);
                default._(this.Ha.x + 1, this.Ha.y + 1 + this.Ta, this.Ha.width - 3, this.Qa - 3, graphics);
            }
            ++n;
        }
    }
    
    private void e() {
        this.Ha.height = this.Ta + this.Qa;
    }
    
    private void d() {
        final int size = this.Oa.size();
        this.Sa = new int[size];
        if (size > 8) {
            this.Sa[0] = 0;
            final int n = this.Ua / (size - 1);
            for (int i = 1; i < size; ++i) {
                this.Sa[i] = n;
            }
            final int n2 = this.Ua - (size - 1) * n;
            for (int n3 = 1, n4 = n2; n3 < size && n4 > 0; ++n3, --n4) {
                final int[] sa = this.Sa;
                final int n5 = n3;
                ++sa[n5];
            }
            this.Ta = (size - 1) * n + n2;
        }
        else if (size > 0) {
            this.Sa[0] = 0;
            for (int j = 1; j < size; ++j) {
                this.Sa[j] = this.Pa;
            }
            this.Ta = (size - 1) * this.Pa;
        }
    }
    
    public boolean contains(final int n, final int n2) {
        return this.Ha.contains(n, n2);
    }
    
    public class b() {
        if (this.Oa.size() > 0) {
            return this.Oa.peek();
        }
        return null;
    }
    
    public class[] _() {
        final class[] array = new class[this.Oa.size()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = (class)this.Oa.elementAt(i);
        }
        return array;
    }
    
    public boolean isEmpty() {
        return this.Oa.isEmpty();
    }
}
