// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

import java.util.StringTokenizer;

public class DoubleArrayField extends ArrayField
{
    double[] a;
    
    public void set1Value(final int n, final double n2) throws Shout3DException {
        if (this.a == null || n < 0 || n >= this.a.length) {
            throw new Shout3DException("DoubleArrayField.set1Value got out-of-range index");
        }
        this.a[n] = n2;
        this.a();
    }
    
    public void setValue(final double[] a) {
        this.a = a;
        this.a();
    }
    
    public double[] getValue() {
        return this.a;
    }
    
    public DoubleArrayField(final Node node, final String s, final int n, final double[] a) {
        super(node, s, n);
        this.a = a;
    }
    
    public void setValueByString(final String s) {
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(s);
            final double[] value = new double[stringTokenizer.countTokens()];
            for (int i = 0; i < value.length; ++i) {
                value[i] = Double.valueOf(stringTokenizer.nextToken());
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
        if (!(field instanceof DoubleArrayField)) {
            return;
        }
        ((DoubleArrayField)field).setValue(this.a);
    }
    
    public int getLength() {
        return this.a.length;
    }
}
