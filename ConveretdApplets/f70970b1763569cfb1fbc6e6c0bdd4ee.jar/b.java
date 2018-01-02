// 
// Decompiled by Procyon v0.5.30
// 

public final class b
{
    private static final int[] h;
    private boolean[] i;
    public int a;
    public int b;
    public int c;
    private int j;
    public int d;
    public long e;
    private byte[] k;
    private byte[] l;
    public long f;
    public int g;
    
    public b() {
        this.i = new boolean[10];
        this.k = new byte[16];
        this.l = new byte[32];
        for (int i = this.i.length - 1; i >= 0; --i) {
            this.i[i] = false;
        }
        this.a = 0;
        this.b = 0;
        this.c = 0;
        this.j = 0;
        this.d = 0;
        this.e = 0L;
        for (int j = this.k.length - 1; j >= 0; --j) {
            this.k[j] = 0;
        }
        for (int k = this.l.length - 1; k >= 0; --k) {
            this.l[k] = 0;
        }
        this.f = 0L;
        this.g = 0;
    }
    
    public final void a(final boolean b) {
        this.a(4, b);
    }
    
    public final boolean a(final int n, final boolean b) {
        final boolean b2;
        if ((b2 = ((this.j & n) > 0)) != b) {
            this.j ^= n;
        }
        return b2;
    }
    
    public final void a(final byte[] array) {
        if (array.length == this.k.length) {
            System.arraycopy(array, 0, this.k, 0, this.k.length);
        }
    }
    
    public final void b(final byte[] array) {
        if (array.length == this.l.length) {
            System.arraycopy(array, 0, this.l, 0, this.l.length);
        }
    }
    
    public final void b(final int n, final boolean b) {
        this.i[n] = b;
    }
    
    public final void a() {
        for (int i = this.i.length - 1; i >= 0; --i) {
            this.i[i] = false;
        }
        this.a = 0;
        this.b = 0;
        this.c = 0;
        this.j = 0;
        this.d = 0;
        this.e = 0L;
        for (int j = this.k.length - 1; j >= 0; --j) {
            this.k[j] = 0;
        }
        for (int k = this.l.length - 1; k >= 0; --k) {
            this.l[k] = 0;
        }
        this.f = 0L;
        this.g = 0;
    }
    
    public final byte[] b() {
        return this.k.clone();
    }
    
    public final byte[] c() {
        return this.l.clone();
    }
    
    public final boolean a(final int n) {
        return this.i[n];
    }
    
    public final int a(final byte[] array, int n) {
        int n2 = 0;
        int n3 = 0;
        this.g = 0;
        for (int i = this.i.length - 1; i >= 0; --i) {
            this.i[i] = false;
        }
        if (array[0] != 17) {
            this.g = 2;
        }
        ++n3;
        --n;
        for (int n4 = 0; n4 < n && this.g == 0; n4 += n2 + 1, n3 += n2) {
            final byte b = array[n3];
            ++n3;
            if (b >= 0 && b < 10) {
                n2 = b.h[b];
                this.i[b] = true;
                switch (b) {
                    case 0: {
                        this.g = (array[n3] & 0xFF);
                        break;
                    }
                    case 1: {
                        this.b = ((array[n3] & 0xFF) << 8) + ((array[n3 + 1] & 0xFF) << 0);
                        break;
                    }
                    case 2: {
                        this.c = ((array[n3] & 0xFF) << 8) + ((array[n3 + 1] & 0xFF) << 0);
                        break;
                    }
                    case 3: {
                        this.j = (array[n3] & 0xFF);
                        break;
                    }
                    case 4: {
                        this.d = ((array[n3] & 0xFF) << 8) + ((array[n3 + 1] & 0xFF) << 0);
                        break;
                    }
                    case 5: {
                        this.e = ((array[n3] & 0xFF) << 24) + ((array[n3 + 1] & 0xFF) << 16) + ((array[n3 + 2] & 0xFF) << 8) + ((array[n3 + 3] & 0xFF) << 0);
                        break;
                    }
                    case 6: {
                        System.arraycopy(array, n3, this.k, 0, this.k.length);
                        break;
                    }
                    case 7: {
                        System.arraycopy(array, n3, this.l, 0, this.l.length);
                        break;
                    }
                    case 8: {
                        this.f = ((array[n3] & 0xFF) << 24) + ((array[n3 + 1] & 0xFF) << 16) + ((array[n3 + 2] & 0xFF) << 8) + ((array[n3 + 3] & 0xFF) << 0);
                        break;
                    }
                    case 9: {
                        this.a = ((array[n3] & 0xFF) << 8) + ((array[n3 + 1] & 0xFF) << 0);
                        break;
                    }
                }
            }
            else {
                this.g = 2;
            }
        }
        if (this.g != 0) {
            return -1;
        }
        return 0;
    }
    
    public final int c(final byte[] array) {
        int length = array.length;
        int n = 1;
        if (length > 129) {
            length = 128;
        }
        --length;
        array[1] = 17;
        ++n;
        int n2 = 1;
        for (int n3 = 0; n3 < 10 && n2 <= length; ++n3) {
            if (this.i[n3]) {
                final int n4 = b.h[n3];
                if ((n2 += n4 + 1) > length) {
                    n2 -= n4 + 1;
                    break;
                }
                array[n] = (byte)n3;
                ++n;
                switch (n3) {
                    case 0: {
                        array[n] = (byte)this.g;
                        break;
                    }
                    case 1: {
                        array[n] = (byte)(this.b >> 8 & 0xFF);
                        array[n + 1] = (byte)(this.b >> 0 & 0xFF);
                        break;
                    }
                    case 2: {
                        array[n] = (byte)(this.c >> 8 & 0xFF);
                        array[n + 1] = (byte)(this.c >> 0 & 0xFF);
                        break;
                    }
                    case 3: {
                        array[n] = (byte)this.j;
                        break;
                    }
                    case 4: {
                        array[n] = (byte)(this.d >> 8 & 0xFF);
                        array[n + 1] = (byte)(this.d >> 0 & 0xFF);
                        break;
                    }
                    case 5: {
                        array[n] = (byte)(this.e >> 24 & 0xFFL);
                        array[n + 1] = (byte)(this.e >> 16 & 0xFFL);
                        array[n + 2] = (byte)(this.e >> 8 & 0xFFL);
                        array[n + 3] = (byte)(this.e >> 0 & 0xFFL);
                        break;
                    }
                    case 6: {
                        System.arraycopy(this.k, 0, array, n, this.k.length);
                        break;
                    }
                    case 7: {
                        System.arraycopy(this.l, 0, array, n, this.l.length);
                        break;
                    }
                    case 8: {
                        array[n] = (byte)(this.f >> 24 & 0xFFL);
                        array[n + 1] = (byte)(this.f >> 16 & 0xFFL);
                        array[n + 2] = (byte)(this.f >> 8 & 0xFFL);
                        array[n + 3] = (byte)(this.f >> 0 & 0xFFL);
                        break;
                    }
                    case 9: {
                        array[n] = (byte)(this.a >> 8 & 0xFF);
                        array[n + 1] = (byte)(this.a >> 0 & 0xFF);
                        break;
                    }
                }
                n += n4;
            }
        }
        array[0] = (byte)(n2 & 0xFF);
        return n2 + 1;
    }
    
    public final String toString() {
        final StringBuffer sb;
        (sb = new StringBuffer(1024)).append("DataProxyProtocol = {\n");
        for (int i = 0; i < 10; ++i) {
            if (this.i[i]) {
                switch (i) {
                    case 0: {
                        sb.append("lastError = " + this.g + "\n");
                        break;
                    }
                    case 1: {
                        sb.append("serviceId = " + this.b + "\n");
                        break;
                    }
                    case 2: {
                        sb.append("serviceType = " + this.c + "\n");
                        break;
                    }
                    case 3: {
                        sb.append("options = " + this.j + "\n");
                        break;
                    }
                    case 4: {
                        sb.append("clientId = " + this.d + "\n");
                        break;
                    }
                    case 5: {
                        sb.append("clientInfo = " + this.e + "\n");
                        break;
                    }
                    case 6: {
                        sb.append("clientData16 = " + this.k + "\n");
                        break;
                    }
                    case 7: {
                        sb.append("clientData32 = " + this.l + "\n");
                        break;
                    }
                    case 8: {
                        sb.append("waitTime = " + this.f + "\n");
                        break;
                    }
                    case 9: {
                        sb.append("streamPacketSize = " + this.a + "\n");
                        break;
                    }
                }
            }
        }
        sb.append("};\n");
        return sb.toString();
    }
    
    static {
        h = new int[] { 1, 2, 2, 1, 2, 4, 16, 32, 4, 2 };
    }
}
