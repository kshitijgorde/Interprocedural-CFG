// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

public class StringField extends Field
{
    String a;
    
    public void setValue(final String a) {
        this.a = a;
        this.a();
    }
    
    public String getValue() {
        return this.a;
    }
    
    public StringField(final Node node, final String s, final int n, final String a) {
        super(node, s, n);
        this.a = a;
    }
    
    public void setValueByString(final String value) {
        this.setValue(value);
    }
    
    public String getValueByString() {
        return this.a;
    }
    
    protected void a(final Field field) {
        if (!(field instanceof StringField)) {
            return;
        }
        ((StringField)field).setValue(this.a);
    }
}
