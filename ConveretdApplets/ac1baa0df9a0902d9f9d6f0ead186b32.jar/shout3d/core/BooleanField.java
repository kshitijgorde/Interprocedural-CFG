// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

public class BooleanField extends Field
{
    boolean g;
    
    public void setValue(final boolean g) {
        this.g = g;
        this.a();
    }
    
    public boolean getValue() {
        return this.g;
    }
    
    public BooleanField(final Node node, final String s, final int n, final boolean g) {
        super(node, s, n);
        this.g = g;
    }
    
    public void setValueByString(final String s) {
        try {
            this.setValue(Boolean.valueOf(s));
        }
        catch (Exception ex) {
            System.err.println(ex);
            ex.printStackTrace();
        }
    }
    
    public String getValueByString() {
        return new Boolean(this.g).toString();
    }
    
    protected void a(final Field field) {
        if (!(field instanceof BooleanField)) {
            return;
        }
        ((BooleanField)field).setValue(this.g);
    }
}
