// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.net.ProtocolException;
import java.io.IOException;

final class aI implements bk
{
    private int a;
    
    aI() {
        this.a = 3;
    }
    
    public final int a(final q q) {
        return 0;
    }
    
    public final void a(final bd bd) {
    }
    
    public final int a(final bd bd, final q q) {
        final int a;
        switch (a = bd.a()) {
            case 408: {
                if (this.a-- == 0 || q.f() != null) {
                    new StringBuffer().append("DefM:  Status ").append(a).append(" ").append(bd.b()).append(" not handled - ").append("maximum number of retries exceeded").toString();
                    return 10;
                }
                new StringBuffer().append("DefM:  Handling ").append(a).append(" ").append(bd.b()).append(" - ").append("resending request").toString();
                return 13;
            }
            case 411: {
                if (q.f() != null && q.f().b() == -1) {
                    return 10;
                }
                try {
                    bd.f().close();
                }
                catch (IOException ex) {}
                if (q.e() != null) {
                    throw new ProtocolException("Received status code 411 even though Content-Length was sent");
                }
                new StringBuffer().append("DefM:  Handling ").append(a).append(" ").append(bd.b()).append(" - resending ").append("request with 'Content-length: 0'").toString();
                q.a(new byte[0]);
                return 13;
            }
            case 505: {
                return 10;
            }
            default: {
                return 10;
            }
        }
    }
}
