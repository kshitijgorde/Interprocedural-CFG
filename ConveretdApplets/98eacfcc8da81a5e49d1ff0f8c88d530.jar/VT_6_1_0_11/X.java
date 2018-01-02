// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import com.hw.client.util.a;
import javax.swing.JComponent;
import com.hw.client.util.c;
import java.awt.Dimension;

public final class X extends cY
{
    private int e;
    private l f;
    
    public X(final l f, final du du) {
        super(0, du, f.a());
        this.e = 0;
        this.f = f;
        this.a(new M(f, this));
        com.hw.client.util.c.a(this, new Dimension(200, 48));
    }
    
    protected final dy a() {
        return new dm(this, this.e());
    }
    
    public final void a(final int e) {
        this.e = e;
        if (this.n().b() != null) {
            this.n().g(e * this.n().b().g());
        }
    }
    
    public final void a(final B e) {
        this.n().e = e;
        if (com.hw.client.util.a.b()) {
            com.hw.client.util.a.c("RecorderController.setRecordAudioFormat:\n\t" + e);
        }
        this.n().g(this.e * this.n().b().g());
    }
    
    public final void a(final boolean b) {
        this.n().s();
        this.n().c = !b;
        if (b) {
            this.n().j = null;
        }
        this.n().c();
        this.a("");
    }
    
    public final boolean b() {
        return this.n().r();
    }
    
    protected final void c() {
        if (this.f != null) {
            this.f.a((au)this.n(), "RECORD_CONFIRM_OVERWRITE", "RECORD_CANCEL_OVERWRITE");
        }
    }
    
    protected final void d() {
        if (this.f == null) {
            return;
        }
        this.f.F().a((au)this.n(), this.f.e("comp_dlg_record_time_over_title"), this.f.e("comp_dlg_record_time_over_msg"), this.f.e("btn_ok"), "RECORD_AUTOSTOPPED_OK");
    }
    
    static l a(final X x) {
        return x.f;
    }
}
