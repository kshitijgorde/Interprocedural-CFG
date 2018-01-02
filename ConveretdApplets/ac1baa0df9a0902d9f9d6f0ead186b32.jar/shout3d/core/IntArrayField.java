// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

import java.util.StringTokenizer;

public class IntArrayField extends ArrayField
{
    int[] a;
    
    public void set1Value(final int n, final int n2) throws Shout3DException {
        if (this.a == null || n < 0 || n >= this.a.length) {
            throw new Shout3DException("IntArrayField.set1Value got out-of-range index");
        }
        this.a[n] = n2;
        this.a();
    }
    
    public void setValue(final int[] a) {
        this.a = a;
        this.a();
    }
    
    public int[] getValue() {
        return this.a;
    }
    
    public IntArrayField(final Node node, final String s, final int n, final int[] a) {
        super(node, s, n);
        this.a = a;
    }
    
    public void setValueByString(final String s) {
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(s);
            final int[] value = new int[stringTokenizer.countTokens()];
            for (int i = 0; i < value.length; ++i) {
                value[i] = Integer.valueOf(stringTokenizer.nextToken());
            }
            this.setValue(value);
        }
        catch (Exception ex) {
            System.err.println(ex);
            ex.printStackTrace();
        }
    }
    
    public String getValueByString() {
        String string = new String();
        for (int i = 0; i < this.a.length; ++i) {
            string = string + this.a[i] + " ";
        }
        return string;
    }
    
    protected void a(final Field field) {
        if (!(field instanceof IntArrayField)) {
            return;
        }
        ((IntArrayField)field).setValue(this.a);
    }
    
    public int getLength() {
        return this.a.length;
    }
}
