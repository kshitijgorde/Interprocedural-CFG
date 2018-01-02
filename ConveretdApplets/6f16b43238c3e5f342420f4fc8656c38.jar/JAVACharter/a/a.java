// 
// Decompiled by Procyon v0.5.30
// 

package JAVACharter.a;

import java.util.StringTokenizer;
import java.util.Vector;
import java.util.Date;
import java.util.Hashtable;

public class a
{
    private Hashtable a;
    
    public a() {
        this.a = new Hashtable();
    }
    
    public Vector a(final Date date, final Date date2, final String s) {
        final Vector vector = new Vector<JAVACharter.b.a>();
        final JAVACharter.b.a[] array = this.a.get(s);
        if (array != null) {
            for (int i = 0; i < array.length; ++i) {
                if (array[i] != null) {
                    final Date a = array[i].a();
                    System.out.println(date + "-" + a + "-" + date2);
                    if ((a.after(date) && a.before(date2)) || date == a || date2 == a) {
                        if (vector.size() == 0) {
                            vector.addElement(array[i]);
                        }
                        else {
                            int n = 0;
                            int n2 = vector.size() - 1;
                            boolean b = false;
                            while (!b) {
                                final int n3 = (n + n2) / 2;
                                final Date a2 = vector.elementAt(n3).a();
                                if (a.after(a2)) {
                                    if (a.after(vector.elementAt(n2).a())) {
                                        if (n2 + 1 <= vector.size()) {
                                            vector.insertElementAt(array[i], n2 + 1);
                                            b = true;
                                        }
                                        else {
                                            vector.addElement(array[i]);
                                            b = true;
                                        }
                                    }
                                    else if (n2 - n < 2) {
                                        vector.insertElementAt(array[i], n2);
                                        b = true;
                                    }
                                    else {
                                        n = (n3 + n2) / 2;
                                    }
                                }
                                else if (a.before(a2)) {
                                    if (a.before(vector.elementAt(n).a())) {
                                        vector.insertElementAt(array[i], n);
                                        b = true;
                                    }
                                    else if (n2 - n < 2) {
                                        vector.insertElementAt(array[i], n2);
                                        b = true;
                                    }
                                    else {
                                        n2 = (n + n3) / 2;
                                    }
                                }
                                else {
                                    vector.insertElementAt(array[i], n3);
                                    b = true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return vector;
    }
    
    public void a(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "-");
        if (stringTokenizer.countTokens() == 2) {
            final JAVACharter.b.a[] array = this.a.get(stringTokenizer.nextToken());
            if (array != null) {
                final int int1 = Integer.parseInt(stringTokenizer.nextToken());
                if (int1 < array.length) {
                    array[int1] = null;
                }
            }
        }
    }
    
    public void a() {
        this.a.clear();
    }
    
    public String a(final String s, final JAVACharter.b.a a) {
        final JAVACharter.b.a[] array = this.a.get(s);
        String s2;
        if (array == null) {
            this.a.put(s, new JAVACharter.b.a[] { a });
            s2 = s + "-0";
        }
        else {
            final int length = array.length;
            final JAVACharter.b.a[] array2 = new JAVACharter.b.a[length + 1];
            System.arraycopy(array, 0, array2, 0, length);
            array2[length] = a;
            this.a.put(s, array2);
            s2 = s + "-" + length;
        }
        System.out.println("New ID: " + s2);
        return s2;
    }
}
