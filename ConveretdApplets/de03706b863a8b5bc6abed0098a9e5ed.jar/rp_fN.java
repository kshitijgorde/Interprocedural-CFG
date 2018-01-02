import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

final class rp_fN implements ActionListener
{
    private /* synthetic */ rp_df a;
    
    rp_fN(final rp_df a) {
        this.a = a;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final rp_df a;
        switch ((a = this.a).a) {
            case 0: {
                a.a = 2;
                break;
            }
            case 2: {
                a.a = 3;
                break;
            }
            case 3: {
                a.a = 1;
                break;
            }
            case 1: {
                a.a = 0;
                break;
            }
        }
        rp_cD.a = a.a;
        a.a();
    }
}
