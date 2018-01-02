// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.awt.Component;
import javax.swing.event.ChangeListener;
import javax.swing.AbstractButton;
import javax.swing.event.ChangeEvent;
import javax.swing.Icon;
import javax.swing.BoundedRangeModel;
import com.wimba.clients.recorder.c;
import javax.swing.ImageIcon;

public final class bq extends cP
{
    private ImageIcon[] a;
    private ImageIcon[] b;
    private c c;
    private BoundedRangeModel d;
    private ap e;
    
    public bq(final BoundedRangeModel d) {
        this.a = new ImageIcon[4];
        this.b = new ImageIcon[4];
        this.d = d;
        this.a().a(d);
        this.e = new ap(this);
        for (int i = 0; i < 4; ++i) {
            this.a[i] = com.hw.client.util.c.a("/images/recorder/btn_speaker_" + i + ".png");
        }
        for (int j = 0; j < 4; ++j) {
            this.b[j] = com.hw.client.util.c.a("/images/recorder/btn_speaker_pressed_" + j + ".png");
        }
        this.setDisabledIcon(com.hw.client.util.c.a("/images/recorder/btn_speaker_disabled.png"));
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setRolloverEnabled(true);
        this.e.stateChanged(null);
        com.hw.client.util.c.a(this);
        this.d.addChangeListener(this.e);
    }
    
    public final c a() {
        if (this.c == null) {
            this.c = new c(this, this.d);
        }
        return this.c;
    }
    
    public final BoundedRangeModel b() {
        return this.d;
    }
    
    static void a(bq bq, int n) {
        final bq bq2 = bq;
        n = n;
        bq = bq2;
        bq2.setIcon(bq.a[n]);
        bq.setPressedIcon(bq.b[n]);
    }
}
