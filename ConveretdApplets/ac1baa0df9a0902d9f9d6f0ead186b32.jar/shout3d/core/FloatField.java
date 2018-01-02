// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

public class FloatField extends Field
{
    float a;
    
    public void setValue(final float a) {
        this.a = a;
        this.a();
    }
    
    public float getValue() {
        return this.a;
    }
    
    public FloatField(final Node node, final String s, final int n, final float a) {
        super(node, s, n);
        this.a = a;
    }
    
    public void setValueByString(final String s) {
        try {
            this.setValue(Float.valueOf(s));
        }
        catch (Exception ex) {
            System.err.println(ex);
            ex.printStackTrace();
        }
    }
    
    public String getValueByString() {
        return Float.toString(this.a);
    }
    
    protected void a(final Field field) {
        if (!(field instanceof FloatField)) {
            return;
        }
        ((FloatField)field).setValue(this.a);
    }
}
