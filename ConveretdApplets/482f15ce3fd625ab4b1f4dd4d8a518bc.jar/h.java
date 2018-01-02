import java.io.InputStream;
import java.io.ByteArrayInputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public final class h
{
    private au a;
    public int a;
    public S[] a;
    
    public h(final au a) {
        this.a = 0;
        this.a = a;
        this.a();
    }
    
    public final void a() {
        if (!this.a.a.k) {
            return;
        }
        this.a = new S[this.a.a.i + 1];
        this.a = 0;
    }
    
    public final void a(final byte[] array) {
        if (!this.a.a.k) {
            return;
        }
        try {
            if (this.a >= this.a()) {
                for (int i = 1; i < this.a(); ++i) {
                    this.a[i - 1] = this.a[i];
                }
                this.a = this.a() - 1;
            }
            this.a[this.a++] = new S(array);
        }
        catch (Exception ex) {
            this.a.a("Error occurred in TimeShiftBuffer.record()");
            this.a.a(ex.toString());
        }
    }
    
    public final byte[] a(final int n) {
        if (!this.a.a.k) {
            return null;
        }
        if (this.a[n] != null) {
            return this.a[n].a;
        }
        return null;
    }
    
    public final void a(final int a) {
        if (!this.a.a.k) {
            return;
        }
        if (this.a[a] != null && this.a[a].a != null) {
            try {
                this.a.a(new ByteArrayInputStream(this.a[a].a));
                this.a = a;
            }
            catch (Exception ex) {}
        }
    }
    
    public final int a() {
        if (!this.a.a.k) {
            return 0;
        }
        return this.a.length;
    }
}
