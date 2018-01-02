import java.io.BufferedInputStream;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

public final class av
{
    public String a;
    
    public av() {
        this.a = null;
    }
    
    public final int[] a(final int[] array, final String s) {
        try {
            BufferedInputStream bufferedInputStream;
            try {
                bufferedInputStream = new BufferedInputStream(new URL(s).openStream());
            }
            catch (Exception ex2) {
                throw new Exception("Invalid path specified in Applet Tag for IPSURL");
            }
            final int available;
            if ((available = bufferedInputStream.available()) < 0) {
                throw new Exception("IPSURL Tag in Applet points at non-static content");
            }
            final byte[] array2 = new byte[available];
            int read2;
            for (int read = bufferedInputStream.read(array2, 0, array2.length); read < array2.length && (read2 = bufferedInputStream.read(array2, read, array2.length - read)) >= 0; read += read2) {}
            bufferedInputStream.close();
            if (!new String(array2, 0, 5).equals("PATCH")) {
                throw new Exception("Invalid IPS File specified, no PATCH Header");
            }
            if (!new String(array2, array2.length - 3, 3).equals("EOF")) {
                throw new Exception("Invalid IPS File specified, no EOF Marker");
            }
            int i = 5;
            final int[] array3 = new int[array.length];
            for (int j = 0; j < array3.length; ++j) {
                array3[j] = array[j];
            }
            while (i < array2.length - 3) {
                final int n = ((array2[i + 0] & 0xFF) << 16) + ((array2[i + 1] & 0xFF) << 8) + ((array2[i + 2] & 0xFF) << 0);
                final int n2 = ((array2[i + 3] & 0xFF) << 8) + ((array2[i + 4] & 0xFF) << 0);
                for (int k = 0; k < n2; ++k) {
                    if (n + k >= array3.length) {
                        throw new Exception("Invalid data in specified IPS File");
                    }
                    array3[n + k] = (array2[i + 5 + k] & 0xFF);
                }
                i += 5 + n2;
            }
            if (i != array2.length - 3) {
                throw new Exception("Invalid data in IPS File specified");
            }
            return array3;
        }
        catch (Exception ex) {
            this.a = ex.getMessage();
            return null;
        }
    }
}
