import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

public class rp_aC extends MouseAdapter
{
    protected rp_dF a;
    protected String a;
    private List a;
    
    public rp_aC(final rp_dF a, final String a2) {
        this.a = a;
        this.a = a2;
        this.a = new ArrayList();
    }
    
    public final void a(final rp_dw rp_dw) {
        if (rp_dw != null) {
            this.a.add(rp_dw);
        }
    }
    
    protected final void a(final rp_C rp_C) {
        final Iterator<rp_dw> iterator = this.a.iterator();
        while (iterator.hasNext()) {
            iterator.next().a(rp_C);
        }
    }
}
