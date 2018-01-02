// 
// Decompiled by Procyon v0.5.30
// 

package z.A.A.A;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.io.Serializable;

public abstract class A implements Serializable
{
    protected final HashMap A;
    protected B C;
    protected final List B;
    private List D;
    
    public abstract String F();
    
    protected abstract HashMap D();
    
    public A() {
        this.A = new HashMap();
        this.B = new ArrayList();
    }
    
    public boolean N(final int n) {
        return this.A.containsKey(new Integer(n));
    }
    
    public Iterator E() {
        return this.B.iterator();
    }
    
    public int G() {
        return this.B.size();
    }
    
    public void A(final B c) {
        if (c == null) {
            throw new NullPointerException("cannot set a null descriptor");
        }
        this.C = c;
    }
    
    public void A(final String s) {
        if (this.D == null) {
            this.D = new ArrayList();
        }
        this.D.add(s);
    }
    
    public boolean C() {
        return this.D != null && this.D.size() > 0;
    }
    
    public Iterator B() {
        return this.D.iterator();
    }
    
    public int A() {
        return this.D.size();
    }
    
    public void A(final int n, final int n2) {
        this.B(n, new Integer(n2));
    }
    
    public void A(final int n, final double n2) {
        this.B(n, new Double(n2));
    }
    
    public void A(final int n, final float n2) {
        this.B(n, new Float(n2));
    }
    
    public void A(final int n, final String s) {
        this.B(n, s);
    }
    
    public void A(final int n, final boolean b) {
        this.B(n, new Boolean(b));
    }
    
    public void A(final int n, final long n2) {
        this.B(n, new Long(n2));
    }
    
    public void A(final int n, final Date date) {
        this.B(n, date);
    }
    
    public void A(final int n, final z.A.A.C.A a) {
        this.B(n, a);
    }
    
    public void A(final int n, final z.A.A.C.A[] array) {
        this.A(n, (Object)array);
    }
    
    public void A(final int n, final int[] array) {
        this.A(n, (Object)array);
    }
    
    public void A(final int n, final byte[] array) {
        this.A(n, (Object)array);
    }
    
    public void A(final int n, final String[] array) {
        this.A(n, (Object)array);
    }
    
    public void B(final int n, final Object o) {
        if (o == null) {
            throw new NullPointerException("cannot set a null object");
        }
        final Integer n2 = new Integer(n);
        if (!this.A.containsKey(n2)) {
            this.B.add(new G(n, this));
        }
        this.A.put(n2, o);
    }
    
    public void A(final int n, final Object o) {
        this.B(n, o);
    }
    
    public int I(final int n) throws C {
        final Object c = this.C(n);
        if (c == null) {
            throw new C("Tag " + this.J(n) + " has not been set -- check using containsTag() first");
        }
        if (c instanceof String) {
            try {
                return Integer.parseInt((String)c);
            }
            catch (NumberFormatException ex) {
                final byte[] bytes = ((String)c).getBytes();
                long n2 = 0L;
                for (int i = 0; i < bytes.length; ++i) {
                    n2 = (n2 << 8) + bytes[i];
                }
                return (int)n2;
            }
        }
        if (c instanceof Number) {
            return ((Number)c).intValue();
        }
        if (c instanceof z.A.A.C.A[]) {
            final z.A.A.C.A[] array = (z.A.A.C.A[])c;
            if (array.length == 1) {
                return array[0].intValue();
            }
        }
        else if (c instanceof byte[]) {
            final byte[] array2 = (byte[])c;
            if (array2.length == 1) {
                return array2[0];
            }
        }
        else if (c instanceof int[]) {
            final int[] array3 = (int[])c;
            if (array3.length == 1) {
                return array3[0];
            }
        }
        throw new C("Tag '" + n + "' cannot be cast to int.  It is of type '" + ((int[])c).getClass() + "'.");
    }
    
    public String[] D(final int n) throws C {
        final Object c = this.C(n);
        if (c == null) {
            throw new C("Tag " + this.J(n) + " has not been set -- check using containsTag() first");
        }
        if (c instanceof String[]) {
            return (String[])c;
        }
        if (c instanceof String) {
            return new String[] { (String)c };
        }
        if (c instanceof int[]) {
            final int[] array = (int[])c;
            final String[] array2 = new String[array.length];
            for (int i = 0; i < array2.length; ++i) {
                array2[i] = Integer.toString(array[i]);
            }
            return array2;
        }
        if (c instanceof byte[]) {
            final byte[] array3 = (byte[])c;
            final String[] array4 = new String[array3.length];
            for (int j = 0; j < array4.length; ++j) {
                array4[j] = Byte.toString(array3[j]);
            }
            return array4;
        }
        if (c instanceof z.A.A.C.A[]) {
            final z.A.A.C.A[] array5 = (z.A.A.C.A[])c;
            final String[] array6 = new String[array5.length];
            for (int k = 0; k < array6.length; ++k) {
                array6[k] = array5[k].A(false);
            }
            return array6;
        }
        throw new C("Tag '" + n + "' cannot be cast to an String array.  It is of type '" + ((z.A.A.C.A[])c).getClass() + "'.");
    }
    
    public int[] O(final int n) throws C {
        final Object c = this.C(n);
        if (c == null) {
            throw new C("Tag " + this.J(n) + " has not been set -- check using containsTag() first");
        }
        if (c instanceof z.A.A.C.A[]) {
            final z.A.A.C.A[] array = (z.A.A.C.A[])c;
            final int[] array2 = new int[array.length];
            for (int i = 0; i < array2.length; ++i) {
                array2[i] = array[i].intValue();
            }
            return array2;
        }
        if (c instanceof int[]) {
            return (int[])c;
        }
        if (c instanceof byte[]) {
            final byte[] array3 = (byte[])c;
            final int[] array4 = new int[array3.length];
            for (int j = 0; j < array3.length; ++j) {
                array4[j] = array3[j];
            }
            return array4;
        }
        if (c instanceof String) {
            final String s = (String)c;
            final int[] array5 = new int[s.length()];
            for (int k = 0; k < s.length(); ++k) {
                array5[k] = s.charAt(k);
            }
            return array5;
        }
        throw new C("Tag '" + n + "' cannot be cast to an int array.  It is of type '" + ((String)c).getClass() + "'.");
    }
    
    public byte[] G(final int n) throws C {
        final Object c = this.C(n);
        if (c == null) {
            throw new C("Tag " + this.J(n) + " has not been set -- check using containsTag() first");
        }
        if (c instanceof z.A.A.C.A[]) {
            final z.A.A.C.A[] array = (z.A.A.C.A[])c;
            final byte[] array2 = new byte[array.length];
            for (int i = 0; i < array2.length; ++i) {
                array2[i] = array[i].byteValue();
            }
            return array2;
        }
        if (c instanceof byte[]) {
            return (byte[])c;
        }
        if (c instanceof int[]) {
            final int[] array3 = (int[])c;
            final byte[] array4 = new byte[array3.length];
            for (int j = 0; j < array3.length; ++j) {
                array4[j] = (byte)array3[j];
            }
            return array4;
        }
        if (c instanceof String) {
            final String s = (String)c;
            final byte[] array5 = new byte[s.length()];
            for (int k = 0; k < s.length(); ++k) {
                array5[k] = (byte)s.charAt(k);
            }
            return array5;
        }
        throw new C("Tag '" + n + "' cannot be cast to a byte array.  It is of type '" + ((String)c).getClass() + "'.");
    }
    
    public double B(final int n) throws C {
        final Object c = this.C(n);
        if (c == null) {
            throw new C("Tag " + this.J(n) + " has not been set -- check using containsTag() first");
        }
        if (c instanceof String) {
            try {
                return Double.parseDouble((String)c);
            }
            catch (NumberFormatException ex) {
                throw new C("unable to parse string " + c + " as a double", ex);
            }
        }
        if (c instanceof Number) {
            return ((Number)c).doubleValue();
        }
        throw new C("Tag '" + n + "' cannot be cast to a double.  It is of type '" + ((Number)c).getClass() + "'.");
    }
    
    public float H(final int n) throws C {
        final Object c = this.C(n);
        if (c == null) {
            throw new C("Tag " + this.J(n) + " has not been set -- check using containsTag() first");
        }
        if (c instanceof String) {
            try {
                return Float.parseFloat((String)c);
            }
            catch (NumberFormatException ex) {
                throw new C("unable to parse string " + c + " as a float", ex);
            }
        }
        if (c instanceof Number) {
            return ((Number)c).floatValue();
        }
        throw new C("Tag '" + n + "' cannot be cast to a float.  It is of type '" + ((Number)c).getClass() + "'.");
    }
    
    public long A(final int n) throws C {
        final Object c = this.C(n);
        if (c == null) {
            throw new C("Tag " + this.J(n) + " has not been set -- check using containsTag() first");
        }
        if (c instanceof String) {
            try {
                return Long.parseLong((String)c);
            }
            catch (NumberFormatException ex) {
                throw new C("unable to parse string " + c + " as a long", ex);
            }
        }
        if (c instanceof Number) {
            return ((Number)c).longValue();
        }
        throw new C("Tag '" + n + "' cannot be cast to a long.  It is of type '" + ((Number)c).getClass() + "'.");
    }
    
    public boolean M(final int n) throws C {
        final Object c = this.C(n);
        if (c == null) {
            throw new C("Tag " + this.J(n) + " has not been set -- check using containsTag() first");
        }
        if (c instanceof Boolean) {
            return (boolean)c;
        }
        if (c instanceof String) {
            try {
                return Boolean.getBoolean((String)c);
            }
            catch (NumberFormatException ex) {
                throw new C("unable to parse string " + c + " as a boolean", ex);
            }
        }
        if (c instanceof Number) {
            return ((Number)c).doubleValue() != 0.0;
        }
        throw new C("Tag '" + n + "' cannot be cast to a boolean.  It is of type '" + ((Number)c).getClass() + "'.");
    }
    
    public Date E(final int n) throws C {
        final Object c = this.C(n);
        if (c == null) {
            throw new C("Tag " + this.J(n) + " has not been set -- check using containsTag() first");
        }
        if (c instanceof Date) {
            return (Date)c;
        }
        if (c instanceof String) {
            final String[] array = { "yyyy:MM:dd HH:mm:ss", "yyyy:MM:dd HH:mm", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm" };
            final String s = (String)c;
            int i = 0;
            while (i < array.length) {
                try {
                    return new SimpleDateFormat(array[i]).parse(s);
                }
                catch (ParseException ex) {
                    ++i;
                    continue;
                }
                break;
            }
        }
        throw new C("Tag '" + n + "' cannot be cast to a java.util.Date.  It is of type '" + ((String)c).getClass() + "'.");
    }
    
    public z.A.A.C.A F(final int n) throws C {
        final Object c = this.C(n);
        if (c == null) {
            throw new C("Tag " + this.J(n) + " has not been set -- check using containsTag() first");
        }
        if (c instanceof z.A.A.C.A) {
            return (z.A.A.C.A)c;
        }
        throw new C("Tag '" + n + "' cannot be cast to a Rational.  It is of type '" + ((z.A.A.C.A)c).getClass() + "'.");
    }
    
    public z.A.A.C.A[] L(final int n) throws C {
        final Object c = this.C(n);
        if (c == null) {
            throw new C("Tag " + this.J(n) + " has not been set -- check using containsTag() first");
        }
        if (c instanceof z.A.A.C.A[]) {
            return (z.A.A.C.A[])c;
        }
        throw new C("Tag '" + n + "' cannot be cast to a Rational array.  It is of type '" + ((z.A.A.C.A[])c).getClass() + "'.");
    }
    
    public String K(final int n) {
        final Object c = this.C(n);
        if (c == null) {
            return null;
        }
        if (c instanceof z.A.A.C.A) {
            return ((z.A.A.C.A)c).A(true);
        }
        if (((z.A.A.C.A)c).getClass().isArray()) {
            final int length = Array.getLength(c);
            final boolean startsWith = ((z.A.A.C.A)c).getClass().toString().startsWith("class [L");
            final StringBuffer sb = new StringBuffer();
            for (int i = 0; i < length; ++i) {
                if (i != 0) {
                    sb.append(' ');
                }
                if (startsWith) {
                    sb.append(Array.get(c, i).toString());
                }
                else {
                    sb.append(Array.getInt(c, i));
                }
            }
            return sb.toString();
        }
        return c.toString();
    }
    
    public Object C(final int n) {
        return this.A.get(new Integer(n));
    }
    
    public String J(final int n) {
        final Integer n2 = new Integer(n);
        final HashMap d = this.D();
        if (!d.containsKey(n2)) {
            String s;
            for (s = Integer.toHexString(n); s.length() < 4; s = "0" + s) {}
            return "Unknown tag (0x" + s + ")";
        }
        return d.get(n2);
    }
    
    public String P(final int n) throws C {
        if (this.C == null) {
            throw new C("a descriptor must be set using setDescriptor(...) before descriptions can be provided");
        }
        return this.C.A(n);
    }
}
