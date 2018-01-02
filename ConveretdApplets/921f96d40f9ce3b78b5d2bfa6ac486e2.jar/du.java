import java.awt.Graphics;
import java.awt.Component;
import java.awt.Container;
import java.util.Vector;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Toolkit;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.awt.Image;
import java.util.Random;

// 
// Decompiled by Procyon v0.5.30
// 

public class du
{
    static int p;
    static Random p;
    static double p;
    
    public static final void p(final String s) {
        System.out.println("Error: " + s);
        try {
            throw new Exception();
        }
        catch (Exception ex) {
            p(ex);
        }
    }
    
    public static final void p(final Exception ex) {
        System.out.println("Error: " + ex.getMessage());
        ex.printStackTrace();
    }
    
    public static final int p(final int n) {
        return Math.abs(du.p.nextInt()) % n;
    }
    
    public static final int p(final String s) {
        return p(s, 10);
    }
    
    public static final int p(final String s, final int n) {
        try {
            return Integer.parseInt(s.trim(), n);
        }
        catch (Exception ex) {
            return -1;
        }
    }
    
    public static final long p(final String s) {
        try {
            return Long.parseLong(s.trim());
        }
        catch (Exception ex) {
            return -1L;
        }
    }
    
    public static final String p(final String s, final int n) {
        return p(s, n, ' ');
    }
    
    public static final String p(final String s, final char c, final char c2) {
        final StringBuffer sb = new StringBuffer(s);
        for (int i = 0; i < s.length(); ++i) {
            if (sb.charAt(i) == c) {
                sb.setCharAt(i, c2);
            }
        }
        return sb.toString();
    }
    
    public static final String p(final String s, final int n, final char c) {
        if (s == null) {
            return null;
        }
        if (n < 0) {
            return null;
        }
        int index = 0;
        for (int i = 0; i < n; ++i) {
            index = s.indexOf(c, index);
            if (index == -1) {
                return null;
            }
            ++index;
        }
        final int index2 = s.indexOf(c, index);
        if (index2 != -1) {
            return s.substring(index, index2);
        }
        return s.substring(index);
    }
    
    public static final String d(final String s, final int n, final char c) {
        if (s == null) {
            return null;
        }
        if (n < 0) {
            return null;
        }
        int index = 0;
        for (int i = 0; i < n + 1; ++i) {
            index = s.indexOf(c, index);
            if (index == -1) {
                return null;
            }
            ++index;
        }
        return s.substring(index);
    }
    
    public static final void p(final int n) {
        try {
            Thread.currentThread();
            Thread.sleep(n);
        }
        catch (Exception ex) {}
    }
    
    private static final String d(final int n) {
        if (n < 10) {
            return "0" + n;
        }
        return String.valueOf(n);
    }
    
    public static final String p(final int n) {
        if (n < 0) {
            return "-" + p(-n);
        }
        return String.valueOf(n / 60) + ":" + d(n % 60);
    }
    
    public static final Image p(final String[] array, final int n, final int n2) {
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(array[0]);
            final int p3 = p(stringTokenizer.nextToken(), 10);
            final int p4 = p(stringTokenizer.nextToken(), 10);
            final int p5 = p(stringTokenizer.nextToken(), 10);
            final int p6 = p(stringTokenizer.nextToken(), 10);
            int n3 = stringTokenizer.hasMoreTokens() ? p(stringTokenizer.nextToken(), 16) : -2;
            final Hashtable<String, Integer> hashtable = new Hashtable<String, Integer>();
            for (int i = 0; i < p5; ++i) {
                final String substring = array[i + 1].substring(0, p6);
                int p7 = p(array[i + 1].substring(p6 + 4), 16);
                if (p7 == n) {
                    p7 = n2;
                }
                hashtable.put(substring, new Integer(p7));
            }
            final int[] array2 = new int[p4 * p3];
            for (int j = 0; j < p4; ++j) {
                for (int k = 0; k < p3; ++k) {
                    final int intValue = hashtable.get(array[1 + p5 + j].substring(k * p6, (k + 1) * p6));
                    if (n3 == -2) {
                        n3 = intValue;
                    }
                    array2[j * p3 + k] = ((intValue == n3) ? 16777215 : (du.p | intValue));
                }
            }
            return Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(p3, p4, array2, 0, p3));
        }
        catch (Exception ex) {
            System.out.println("Unknown error in xpm2images");
            return null;
        }
    }
    
    public static final String p(String s, final String s2) {
        s = s.trim();
        if (s.endsWith("!")) {
            s = s.substring(0, s.length() - 1);
        }
        return (String.valueOf(s2) + s).toLowerCase();
    }
    
    public static final String p(final String s) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 == 'u') {
                sb.append("uu");
            }
            else if (char1 <= '\u007f') {
                sb.append(char1);
            }
            else {
                sb.append('u');
                final String hexString = Integer.toHexString(char1);
                for (int j = 3 - hexString.length(); j >= 0; --j) {
                    sb.append('0');
                }
                sb.append(hexString);
            }
        }
        return sb.toString();
    }
    
    public static final String d(final String s) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 != 'u' || i == s.length() - 1) {
                sb.append(char1);
            }
            else if (s.charAt(i + 1) == 'u') {
                ++i;
                sb.append(char1);
            }
            else if (i + 4 < s.length()) {
                final String substring = s.substring(i + 1, i + 5);
                try {
                    sb.append((char)Integer.parseInt(substring, 16));
                    i += 4;
                }
                catch (Exception ex) {
                    sb.append(char1);
                }
            }
            else {
                sb.append(char1);
            }
        }
        return sb.toString();
    }
    
    public static final String p(final Vector vector) {
        if (vector.size() == 0) {
            return "";
        }
        final StringBuffer sb = new StringBuffer(vector.elementAt(0));
        for (int i = 1; i < vector.size(); ++i) {
            sb.append(" ").append(vector.elementAt(i));
        }
        return sb.toString();
    }
    
    public static final Vector p(final Container container) {
        final Vector<Container> vector = new Vector<Container>();
        vector.addElement(container);
        int i = 0;
        while (i < vector.size()) {
            final Container element = vector.elementAt(i);
            ++i;
            if (element instanceof Container) {
                final Component[] components = element.getComponents();
                for (int j = 0; j < components.length; ++j) {
                    vector.addElement((Container)components[j]);
                }
            }
        }
        return vector;
    }
    
    public static final void p(final Component component) {
        component.invalidate();
        Container container;
        for (container = component.getParent(); container.getParent() != null; container = container.getParent()) {
            container.invalidate();
        }
        if (container != null) {
            container.invalidate();
            container.validate();
        }
    }
    
    public static final void p(final Graphics graphics, final int n, final int n2, int i, int j, final int n3, final int n4) {
        while (i <= n3 / 2) {
            graphics.copyArea(n, n2, i, j, i, 0);
            i *= 2;
        }
        if (i < n3) {
            graphics.copyArea(n, n2, n3 - i, j, i, 0);
        }
        while (j <= n4 / 2) {
            graphics.copyArea(n, n2, n3, j, 0, j);
            j *= 2;
        }
        if (j < n4) {
            graphics.copyArea(n, n2, n3, n4 - j, 0, j);
        }
    }
    
    static {
        du.p = -16777216;
        du.p = new Random();
        du.p = -1.0;
    }
}
