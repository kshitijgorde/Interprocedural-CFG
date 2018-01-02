import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_fU
{
    public ByteArrayOutputStream a;
    private byte[] a;
    
    public rp_fU(final ByteArrayOutputStream a, final byte[] a2) {
        this.a = a;
        this.a = a2;
    }
    
    public final ByteArrayOutputStream a() {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        if (this.a != null && this.a.length > 0) {
            dataOutputStream.writeInt(this.a.length);
            dataOutputStream.write(this.a);
            dataOutputStream.writeInt(this.a.size());
        }
        this.a.writeTo(dataOutputStream);
        dataOutputStream.flush();
        return byteArrayOutputStream;
    }
}
