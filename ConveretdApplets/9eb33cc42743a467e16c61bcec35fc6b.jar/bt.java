import java.net.Socket;
import java.io.IOException;

// 
// Decompiled by Procyon v0.5.30
// 

public class bt extends bm
{
    public bt(final String s, final int n, final String s2, final int n2, final b7 b7) throws IOException {
        super(s, n, s2, n2, b7);
    }
    
    public final y i5(final Socket socket, final int n, final int n2, final b7 b7) throws IOException {
        return new br(socket, n, n2, b7);
    }
}
