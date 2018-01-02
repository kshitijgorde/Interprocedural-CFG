import java.io.IOException;
import java.net.Socket;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class123_Sub1 extends Class123
{
    static int anInt4742;
    private PacketSender aClass307_4743;
    static int anInt4744;
    private Class127 aClass127_4745;
    private Socket aSocket4746;
    
    static final void method2211(final byte b) {
        try {
            if (b != -23) {
                Class123_Sub1.anInt4742 = -121;
            }
            Class38.aClass100_357.method1690(1);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "caa.B(" + b + ')');
        }
    }
    
    @Override
    final void method2204(final int n) {
        try {
            this.aClass127_4745.method2219((byte)126);
            if (n != -1) {
                this.aClass307_4743 = null;
            }
            this.aClass307_4743.method3603(true);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "caa.C(" + n + ')');
        }
    }
    
    @Override
    protected final void finalize() {
        try {
            this.method2207(-17);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "caa.finalize()");
        }
    }
    
    @Override
    final int method2208(final byte[] array, final int n, final int n2, final int n3) throws IOException {
        try {
            if (n2 != 2047) {
                this.method2204(38);
            }
            return this.aClass127_4745.method2221(n, array, n3, (byte)59);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "caa.A(" + ((array != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final void method2207(final int n) {
        try {
            try {
                if (n >= -3) {
                    this.aSocket4746 = null;
                }
                this.aSocket4746.close();
            }
            catch (IOException ex2) {}
            this.aClass127_4745.method2220(85);
            this.aClass307_4743.method3606((byte)(-117));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "caa.F(" + n + ')');
        }
    }
    
    @Override
    final void method2202(final int n, final int n2, final byte[] array, final int n3) throws IOException {
        try {
            if (n != -24305) {
                this.method2204(-21);
            }
            this.aClass307_4743.method3605(n3, n2, array, n ^ 0x5EF4);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "caa.E(" + n + ',' + n2 + ',' + ((array != null) ? "{...}" : "null") + ',' + n3 + ')');
        }
    }
    
    @Override
    final boolean method2203(final int n, final int n2) throws IOException {
        try {
            if (n != -1949) {
                method2211((byte)112);
            }
            return this.aClass127_4745.method2222(n2, (byte)(-124));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "caa.D(" + n + ',' + n2 + ')');
        }
    }
    
    Class123_Sub1(final Socket aSocket4746, final int n) throws IOException {
        try {
            (this.aSocket4746 = aSocket4746).setSoTimeout(30000);
            this.aSocket4746.setTcpNoDelay(true);
            this.aClass127_4745 = new Class127(this.aSocket4746.getInputStream(), n);
            this.aClass307_4743 = new PacketSender(this.aSocket4746.getOutputStream(), n);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "caa.<init>(" + ((aSocket4746 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
}
