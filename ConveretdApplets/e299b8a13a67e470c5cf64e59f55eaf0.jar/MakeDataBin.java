import java.io.FileOutputStream;
import java.io.File;
import java.io.ByteArrayOutputStream;
import java.util.Random;

// 
// Decompiled by Procyon v0.5.30
// 

public class MakeDataBin
{
    private static Random g_r;
    
    static {
        MakeDataBin.g_r = new Random(System.currentTimeMillis());
    }
    
    private static byte[] gen() {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final boolean[][] hit = new boolean[256][256];
        int from = 0;
        for (int loop = 0; loop < 196608; ++loop) {
            final int to = MakeDataBin.g_r.nextInt() & 0xFF;
            for (int jj = 0; jj < 7; ++jj) {
                final int tt = to + jj & 0xFF;
                if (!hit[from][tt]) {
                    out.write((char)tt);
                    hit[from][tt] = true;
                    from = tt;
                }
            }
        }
        return out.toByteArray();
    }
    
    public static void main(final String[] args) {
        final File file = new File("data.bin");
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            int size = 10485760;
            if (args.length > 0) {
                try {
                    size = Integer.parseInt(args[0]);
                }
                catch (Exception ex) {}
            }
            size = Math.max(size, 1048576);
            while (size > 0) {
                final byte[] by = gen();
                final int use = Math.min(size, by.length);
                out.write(by, 0, use);
                size -= use;
                System.out.print(".");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                out.close();
            }
            catch (Exception ex2) {}
        }
        try {
            out.close();
        }
        catch (Exception ex3) {}
        System.out.println("");
        System.out.println(file + " " + file.length() + " bytes");
    }
}
