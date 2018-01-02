// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import com.hw.client.util.c;
import com.hw.client.util.a;
import java.io.ByteArrayOutputStream;
import java.awt.event.ActionListener;

public final class dm extends dy implements au, ActionListener
{
    private ByteArrayOutputStream k;
    private long l;
    private byte[] m;
    private boolean n;
    private final X o;
    
    public dm(final X o, final du du) {
        super(this.o = o, du);
        this.l = 0L;
        this.m = new byte[10240];
        o.a("");
        o.c = false;
        o.a = true;
        o.b = true;
        this.n = false;
        this.b = 3;
    }
    
    public final void a(final dE de, final String s) {
        if (s.equalsIgnoreCase("RECORD_AUTOSTOPPED_OK")) {
            if (de != null) {
                de.a();
            }
        }
        else {
            if (s.equals("RECORD_CONFIRM_OVERWRITE")) {
                if (de != null) {
                    de.a();
                }
                this.o.n().a(false);
                return;
            }
            if (s.equals("RECORD_CANCEL_OVERWRITE")) {
                if (de != null) {
                    de.a();
                }
            }
            else {
                com.hw.client.util.a.e("RecorderController.dlgButtonPressed: unknown cmd=" + s);
            }
        }
    }
    
    public final void a(final boolean b) {
        this.o.a("");
        if (b) {
            this.o.c();
            return;
        }
        this.o.d = false;
        this.i = this.a(this.b());
    }
    
    public final void e() {
        this.i = this.a.h();
        this.l = 0L;
        this.k = new ByteArrayOutputStream();
        super.e();
        if (X.a(this.o) != null) {
            X.a(this.o).a(new B(this.b()));
        }
        this.n = true;
        this.o.j().a(com.hw.client.util.c.a("/images/recorder/cursor_progress_record.png"));
    }
    
    public final void b(final long l) {
        super.b(l);
        if (this.b != 4) {
            return;
        }
        try {
            if (this.n && l - this.l >= 0.2 * this.b().g() && this.i.available() > 0) {
                int i = this.i.available();
                while (i > 0) {
                    int n;
                    int read;
                    for (n = 0; i > n && this.m.length > n; n += read) {
                        if ((read = this.i.read(this.m, n, this.m.length - n)) <= 0) {
                            throw new IOException("RecorderController.recordProgress: recordStream.read returned -1 although available>0");
                        }
                    }
                    i -= n;
                    if (n > 0) {
                        final byte[] array = new byte[n];
                        System.arraycopy(this.m, 0, array, 0, n);
                        this.a(array);
                    }
                }
                this.l = l;
            }
        }
        catch (IOException ex) {
            com.hw.client.util.a.a("RecorderController.recordProgress, IOException:", ex);
        }
    }
    
    private void a(final byte[] array) {
        try {
            this.k.write(array);
        }
        catch (IOException ex) {
            com.hw.client.util.a.b("RecordComponent.sendAudioChunk: IOExc calling BAOS.write", ex);
        }
        if (X.a(this.o) != null) {
            X.a(this.o).a(array);
        }
    }
    
    public final void c(final long n) {
        this.o.j().a(com.hw.client.util.c.a("/images/recorder/cursor_progress.png"));
        this.n = false;
        try {
            super.c(n);
            int i = 0;
            while (i >= 0) {
                int n2;
                for (n2 = 0; (i = this.i.read(this.m, n2, this.m.length - n2)) > 0; n2 += i) {}
                if (n2 > 0) {
                    final byte[] array = new byte[n2];
                    System.arraycopy(this.m, 0, array, 0, n2);
                    this.a(array);
                }
            }
            final byte[] byteArray = this.k.toByteArray();
            if (this.b().c() == 12) {
                if (this.b().d() == 41) {
                    new dF((byteArray.length - 60) / 65, this.b().g()).b(byteArray, 0);
                }
                else if (this.b().d() == 2) {
                    new cy(byteArray.length - 60, this.b().k(), this.b().g(), this.b().f()).b(byteArray, 0);
                }
            }
            ((B)this.b()).f((int)this.f);
            this.o.a(this.b(), new ByteArrayInputStream(byteArray));
            if (X.a(this.o) != null) {
                X.a(this.o).c((int)this.f);
            }
            if (this.o.d && X.a(this.o) != null) {
                X.a(this.o).u();
            }
            if (this.o.c && !this.o.d && X.a(this.o) != null) {
                X.a(this.o).w();
            }
        }
        catch (IOException ex) {
            com.hw.client.util.a.a("RecorderController.recordStopped: IOException", ex);
        }
        if (X.a(this.o) != null) {
            X.a(this.o).repaint();
        }
    }
    
    public final void o() {
        if (com.hw.client.util.a.b()) {
            com.hw.client.util.a.c("RecorderController: recordAutoStopped");
        }
        if (this.o.b) {
            this.o.d();
        }
    }
}
