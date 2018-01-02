// 
// Decompiled by Procyon v0.5.30
// 

package wordle.a;

import java.net.HttpURLConnection;
import java.awt.Component;
import javax.swing.Spring;
import javax.swing.SpringLayout;
import java.awt.Container;
import java.io.DataOutputStream;

public class b
{
    private final DataOutputStream a;
    private final String b;
    
    public b() {
    }
    
    private static SpringLayout.Constraints a(final int n, final int n2, final Container container, final int n3) {
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
        Spring x = Spring.constant(0);
        Spring width;
        SpringLayout.Constraints a;
        for (i = 0; i < 2; ++i) {
            width = Spring.constant(0);
            for (k = 0; k < 4; ++k) {
                width = Spring.max(width, a(k, i, container, 2).getWidth());
            }
            for (k = 0; k < 4; ++k) {
                (a = a(k, i, container, 2)).setX(x);
                a.setWidth(width);
            }
            x = Spring.sum(x, Spring.sum(width, Spring.constant(6)));
        }
        Spring y = Spring.constant(0);
        Spring height;
        SpringLayout.Constraints a2;
        for (j = 0; j < 4; ++j) {
            height = Spring.constant(0);
            for (l = 0; l < 2; ++l) {
                height = Spring.max(height, a(j, l, container, 2).getHeight());
            }
            for (l = 0; l < 2; ++l) {
                (a2 = a(j, l, container, 2)).setY(y);
                a2.setHeight(height);
            }
            y = Spring.sum(y, Spring.sum(height, Spring.constant(6)));
        }
        final SpringLayout.Constraints constraints;
        (constraints = springLayout.getConstraints(container)).setConstraint("South", y);
        constraints.setConstraint("East", x);
    }
    
    public b(final HttpURLConnection httpURLConnection) {
        this.b = "--------------------" + Long.toString(System.currentTimeMillis(), 16);
        httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + this.b);
        this.a = new DataOutputStream(httpURLConnection.getOutputStream());
    }
    
    public void a() {
        this.a.writeBytes("--");
        this.a.writeBytes(this.b);
        this.a.writeBytes("--");
        this.a.writeBytes("\r\n");
        this.a.flush();
        this.a.close();
    }
    
    public void a(final String s, final String s2) {
        if (s == null) {
            throw new NullPointerException("Name");
        }
        this.a.writeBytes("--");
        this.a.writeBytes(this.b);
        this.a.writeBytes("\r\n");
        this.a.writeBytes("Content-Disposition: form-data; name=\"" + s + "\"");
        this.a.writeBytes("\r\n");
        this.a.writeBytes("\r\n");
        this.a.write((s2 == null) ? "".getBytes("UTF-8") : s2.getBytes("UTF-8"));
        this.a.writeBytes("\r\n");
        this.a.flush();
    }
    
    public void a(final String s, final String s2, final byte[] array) {
        if (s == null) {
            throw new NullPointerException("Name");
        }
        if (array == null) {
            throw new NullPointerException("data");
        }
        this.a.writeBytes("--");
        this.a.writeBytes(this.b);
        this.a.writeBytes("\r\n");
        this.a.writeBytes("Content-Disposition: form-data; name=\"" + s + "\"");
        this.a.writeBytes("\r\n");
        if (s2 != null) {
            this.a.writeBytes("Content-Type: " + s2);
            this.a.writeBytes("\r\n");
        }
        this.a.writeBytes("\r\n");
        this.a.write(array);
        this.a.writeBytes("\r\n");
        this.a.flush();
    }
}
