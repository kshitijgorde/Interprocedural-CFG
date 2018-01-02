// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.io.IOException;
import com.hw.client.util.a;
import java.net.URL;

public final class bV implements Runnable
{
    private aD a;
    private URL b;
    
    public bV(final aD a, final URL b) {
        this.a = a;
        this.b = b;
        new Thread(this).start();
    }
    
    public final void run() {
        final aj aj;
        (aj = new aj(this.b.getHost(), this.b.getPort())).a(10000);
        final String file = this.b.getFile();
        try {
            final cM a;
            if ((a = aj.a(file)).a() != 200) {
                com.hw.client.util.a.d("Test1.run: got sc=" + a.a());
                return;
            }
            com.hw.client.util.a.d("Test1.run: HTTPConnection succeeded, switching channel");
            this.a.f();
        }
        catch (bw bw) {
            com.hw.client.util.a.d("Test1.run: failed: ModuleException=" + bw);
        }
        catch (IOException ex) {
            com.hw.client.util.a.d("Test1.run: failed: " + ex);
        }
        finally {
            aj.a();
        }
    }
}
