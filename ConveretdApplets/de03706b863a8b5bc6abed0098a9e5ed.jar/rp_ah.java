import java.awt.Component;
import javax.swing.Spring;
import javax.swing.SpringLayout;
import java.awt.Container;

// 
// Decompiled by Procyon v0.5.30
// 

public class rp_ah
{
    public rp_dt[] a;
    public int a;
    public int b;
    public int c;
    
    public rp_ah(int a) {
        this.a = 0;
        this.b = 0;
        this.c = 0;
        final rp_ah rp_ah = this;
        a = a;
        this = rp_ah;
        if (rp_ah.a == 0) {
            this.a = a;
            this.a = new rp_dt[this.a];
            return;
        }
        System.out.println("Not implemented.");
    }
    
    public final void a(final int n) {
        this.a[n].a();
    }
    
    public rp_ah() {
    }
    
    public static SpringLayout.Constraints a(final int n, final int n2, final Container container, final int n3) {
        return ((SpringLayout)container.getLayout()).getConstraints(container.getComponent(n * n3 + n2));
    }
    
    public static void a(final Container container, final int n, final int n2, int i, int j, int k, int l) {
        SpringLayout springLayout;
        try {
            springLayout = (SpringLayout)container.getLayout();
        }
        catch (ClassCastException ex) {
            System.err.println("The first argument to makeCompactGrid must use SpringLayout.");
            return;
        }
        Spring x = Spring.constant(3);
        Spring width;
        SpringLayout.Constraints a;
        for (i = 0; i < 1; ++i) {
            width = Spring.constant(0);
            for (k = 0; k < 3; ++k) {
                width = Spring.max(width, a(k, 0, container, 1).getWidth());
            }
            for (k = 0; k < 3; ++k) {
                (a = a(k, 0, container, 1)).setX(x);
                a.setWidth(width);
            }
            x = Spring.sum(x, Spring.sum(width, Spring.constant(3)));
        }
        Spring y = Spring.constant(3);
        Spring height;
        SpringLayout.Constraints a2;
        for (j = 0; j < 3; ++j) {
            height = Spring.constant(0);
            for (l = 0; l < 1; ++l) {
                height = Spring.max(height, a(j, l, container, 1).getHeight());
            }
            for (l = 0; l < 1; ++l) {
                (a2 = a(j, l, container, 1)).setY(y);
                a2.setHeight(height);
            }
            y = Spring.sum(y, Spring.sum(height, Spring.constant(3)));
        }
        final SpringLayout.Constraints constraints;
        (constraints = springLayout.getConstraints(container)).setConstraint("South", y);
        constraints.setConstraint("East", x);
    }
}
