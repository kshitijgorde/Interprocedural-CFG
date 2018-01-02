// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

import java.util.StringTokenizer;

public class BooleanArrayField extends ArrayField
{
    boolean[] a;
    
    public void set1Value(final int n, final boolean b) throws Shout3DException {
        if (this.a == null || n < 0 || n >= this.a.length) {
            throw new Shout3DException("BooleanArrayField.set1Value got out-of-range index");
        }
        this.a[n] = b;
        this.a();
    }
    
    public void setValue(final boolean[] a) {
        this.a = a;
        this.a();
    }
    
    public boolean[] getValue() {
        return this.a;
    }
    
    public BooleanArrayField(final Node node, final String s, final int n, final boolean[] a) {
        super(node, s, n);
        this.a = a;
    }
    
    public void setValueByString(final String s) {
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(s);
            final boolean[] value = new boolean[stringTokenizer.countTokens()];
            for (int i = 0; i < value.length; ++i) {
                value[i] = Boolean.valueOf(stringTokenizer.nextToken());
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
        if (!(field instanceof BooleanArrayField)) {
            return;
        }
        ((BooleanArrayField)field).setValue(this.a);
    }
    
    public int getLength() {
        return this.a.length;
    }
}
