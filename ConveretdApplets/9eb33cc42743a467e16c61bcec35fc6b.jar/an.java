import java.math.BigInteger;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import mindbright.security.KeyPair;

// 
// Decompiled by Procyon v0.5.30
// 

public class an
{
    public int g6;
    public ce g5;
    public String g4;
    public byte[] g3;
    
    public static void fu(final KeyPair keyPair, final String s, final String s2, final String s3) throws IOException {
        final cf cf = keyPair.fr();
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(8192);
        final bu bu = new bu(byteArrayOutputStream);
        final byte[] array = new byte[2];
        ca.mf().nextBytes(array);
        bu.writeByte(array[0]);
        bu.writeByte(array[1]);
        bu.writeByte(array[0]);
        bu.writeByte(array[1]);
        bu.ju(cf.m1());
        bu.ju(cf.m0());
        bu.ju(cf.m_());
        bu.ju(cf.mz());
        final byte[] byteArray = byteArrayOutputStream.toByteArray();
        final byte[] array2 = new byte[8 - byteArray.length % 8 + byteArray.length];
        System.arraycopy(byteArray, 0, array2, 0, byteArray.length);
        final byte[] array3 = array2;
        final int n = 3;
        final ci ne = ci.ne(ca.mm[n][0]);
        ne.m9(s);
        final byte[] nb = ne.nb(array3);
        final bu bu2 = new bu(new FileOutputStream(s2));
        bu2.writeBytes("SSH PRIVATE KEY FILE FORMAT 1.1\n");
        bu2.writeByte(0);
        bu2.writeByte(n);
        bu2.writeInt(0);
        bu2.writeInt(0);
        bu2.ju(keyPair.fs().m2());
        bu2.ju(keyPair.fs().m3());
        bu2.jt(s3);
        bu2.write(nb, 0, nb.length);
        bu2.close();
    }
    
    public an(final String s) throws IOException {
        final bv bv = new bv(new FileInputStream(s));
        final byte[] array = new byte["SSH PRIVATE KEY FILE FORMAT 1.1\n".length()];
        bv.readFully(array);
        final String s2 = new String(array);
        bv.readByte();
        if (!s2.equals("SSH PRIVATE KEY FILE FORMAT 1.1\n")) {
            throw new IOException("RSA key file corrupt");
        }
        this.g6 = bv.readByte();
        if (ca.mm[this.g6][0] == null) {
            throw new IOException("Ciphertype " + this.g6 + " in key-file not supported");
        }
        bv.readInt();
        bv.readInt();
        this.g5 = new ce(bv.jx(), bv.jx());
        this.g4 = bv.jw();
        final byte[] array2 = new byte[8192];
        final int read = bv.read(array2);
        bv.close();
        System.arraycopy(array2, 0, this.g3 = new byte[read], 0, read);
    }
    
    public final String ft() {
        return this.g4;
    }
    
    public final ce fs() {
        return this.g5;
    }
    
    public final cf fr(final String s) {
        final ci ne = ci.ne(ca.mm[this.g6][0]);
        ne.m9(s);
        final bv bv = new bv(new ByteArrayInputStream(ne.na(this.g3)));
        cf cf;
        try {
            final byte byte1 = bv.readByte();
            final byte byte2 = bv.readByte();
            final byte byte3 = bv.readByte();
            final byte byte4 = bv.readByte();
            if (byte1 != byte3 || byte2 != byte4) {
                return null;
            }
            final BigInteger jx = bv.jx();
            final BigInteger jx2 = bv.jx();
            final BigInteger jx3 = bv.jx();
            final BigInteger jx4 = bv.jx();
            bv.close();
            cf = new cf(this.g5.m3(), this.g5.m2(), jx, jx2, jx3, jx4);
        }
        catch (IOException ex) {
            cf = null;
        }
        return cf;
    }
}
