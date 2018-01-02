// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

import java.util.StringTokenizer;

public class FloatArrayField extends ArrayField
{
    float[] a;
    
    public void set1Value(final int n, final float n2) throws Shout3DException {
        if (this.a == null || n < 0 || n >= this.a.length) {
            throw new Shout3DException("FloatArrayField.set1Value got out-of-range index");
        }
        this.a[n] = n2;
        this.a();
    }
    
    public void setValue(final float[] a) {
        this.a = a;
        this.a();
    }
    
    public float[] getValue() {
        return this.a;
    }
    
    public FloatArrayField(final Node node, final String s, final int n, final float[] a) {
        super(node, s, n);
        this.a = a;
    }
    
    public void setValueByString(final String s) {
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(s);
            final float[] value = new float[stringTokenizer.countTokens()];
            for (int i = 0; i < value.length; ++i) {
                value[i] = Float.valueOf(stringTokenizer.nextToken());
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
        if (!(field instanceof FloatArrayField)) {
            return;
        }
        ((FloatArrayField)field).setValue(this.a);
    }
    
    public int getLength() {
        return this.a.length;
    }
}
