import javax.swing.DefaultButtonModel;

// 
// Decompiled by Procyon v0.5.30
// 

final class rp_fA extends DefaultButtonModel
{
    private rp_v a;
    
    public rp_fA(final rp_v a) {
        this.a = a;
    }
    
    public final boolean isSelected() {
        return this.a != null && this.a.b(1);
    }
    
    public final void setSelected(final boolean b) {
        if (this.a != null) {
            this.a.b(1, b);
        }
    }
}
