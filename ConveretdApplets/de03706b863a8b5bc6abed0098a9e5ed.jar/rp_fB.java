import java.util.zip.ZipEntry;
import java.io.IOException;
import java.util.zip.ZipInputStream;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_fB
{
    private rp_bR a;
    
    public rp_fB() {
        this.a = new rp_bR(80);
    }
    
    public final int a(final rp_fK rp_fK) {
        try {
            return this.a(rp_fK.a(20, null));
        }
        catch (Throwable t) {
            rp_C.a(1, "BT***TemplatesRepository.readTemplates:Error loading BasicTemplates");
            t.printStackTrace();
            return 0;
        }
    }
    
    public final int a(InputStream name) {
        if (name == null) {
            return 0;
        }
        int n = 0;
        try {
            final ZipInputStream zipInputStream = new ZipInputStream(name);
            ZipEntry nextEntry;
            while ((nextEntry = zipInputStream.getNextEntry()) != null) {
                if (!((String)(name = (InputStream)nextEntry.getName())).endsWith(".svg")) {
                    if (!((String)name).endsWith(".sep")) {
                        continue;
                    }
                }
                try {
                    final rp_aV rp_aV = new rp_aV(rp_C.a(zipInputStream));
                    final InputStream inputStream = name;
                    final rp_aV rp_aV2 = rp_aV;
                    final InputStream inputStream2 = inputStream;
                    if (rp_aV2 != null) {
                        this.a.put(inputStream2, rp_aV2);
                    }
                    ++n;
                }
                catch (Exception ex) {
                    rp_C.a(3, "Error reading " + (String)name);
                    ex.printStackTrace();
                }
            }
            zipInputStream.close();
        }
        catch (IOException ex2) {
            rp_C.a(3, "Error reading BasicTemplates zip stream.");
            ex2.printStackTrace();
            return 0;
        }
        return n;
    }
    
    public final rp_aV a(final String s) {
        return (rp_aV)this.a.get(s);
    }
}
