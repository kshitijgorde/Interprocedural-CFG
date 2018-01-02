// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

public class DoubleField extends Field
{
    double a;
    
    public void setValue(final double a) {
        this.a = a;
        this.a();
    }
    
    public double getValue() {
        return this.a;
    }
    
    public DoubleField(final Node node, final String s, final int n, final double a) {
        super(node, s, n);
        this.a = a;
    }
    
    public void setValueByString(final String s) {
        try {
            this.setValue(Double.valueOf(s));
        }
        catch (Exception ex) {
            System.err.println(ex);
            ex.printStackTrace();
        }
    }
    
    public String getValueByString() {
        return Double.toString(this.a);
    }
    
    protected void a(final Field field) {
        if (!(field instanceof DoubleField)) {
            return;
        }
        ((DoubleField)field).setValue(this.a);
    }
}
