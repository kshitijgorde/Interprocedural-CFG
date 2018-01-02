// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

public class IntField extends Field
{
    int b;
    
    public void setValue(final int b) {
        this.b = b;
        this.a();
    }
    
    public int getValue() {
        return this.b;
    }
    
    public IntField(final Node node, final String s, final int n, final int b) {
        super(node, s, n);
        this.b = b;
    }
    
    public void setValueByString(final String s) {
        try {
            this.setValue(Integer.valueOf(s));
        }
        catch (Exception ex) {
            System.err.println(ex);
            ex.printStackTrace();
        }
    }
    
    public String getValueByString() {
        return Integer.toString(this.b);
    }
    
    protected void a(final Field field) {
        if (!(field instanceof IntField)) {
            return;
        }
        ((IntField)field).setValue(this.b);
    }
}
