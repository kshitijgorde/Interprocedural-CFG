// 
// Decompiled by Procyon v0.5.30
// 

package com.bullionvault.chart.e;

import java.io.IOException;
import com.bullionvault.chart.c.h;
import com.bullionvault.chart.c.k;
import java.io.DataInputStream;

final class a implements Runnable
{
    private DataInputStream a;
    
    public a(final DataInputStream a) {
        this.a = a;
        k.a(this, "InputStreamCloser");
    }
    
    public final void run() {
        try {
            this.a.close();
            h.e("InputStreamCloser - Input Stream closed.");
        }
        catch (IOException ex) {
            h.d("InputStreamCloser - Unable to close Input Stream");
        }
        k.b(this);
    }
}
