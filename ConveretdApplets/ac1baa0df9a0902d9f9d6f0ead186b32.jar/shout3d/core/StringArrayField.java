// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

import java.util.StringTokenizer;

public class StringArrayField extends ArrayField
{
    String[] a;
    
    public void set1Value(final int n, final String s) throws Shout3DException {
        if (this.a == null || n < 0 || n >= this.a.length) {
            throw new Shout3DException("StringArrayField.set1Value got out-of-range index");
        }
        this.a[n] = s;
        this.a();
    }
    
    public void setValue(final String[] a) {
        this.a = a;
        this.a();
    }
    
    public String[] getValue() {
        return this.a;
    }
    
    public StringArrayField(final Node node, final String s, final int n, final String[] a) {
        super(node, s, n);
        this.a = a;
    }
    
    public void setValueByString(final String s) {
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(s);
            final String[] value = new String[stringTokenizer.countTokens()];
            for (int i = 0; i < value.length; ++i) {
                value[i] = stringTokenizer.nextToken();
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
        if (!(field instanceof StringArrayField)) {
            return;
        }
        ((StringArrayField)field).setValue(this.a);
    }
    
    public int getLength() {
        return this.a.length;
    }
}
