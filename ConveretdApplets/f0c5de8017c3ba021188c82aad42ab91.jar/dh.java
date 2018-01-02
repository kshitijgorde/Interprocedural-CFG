import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;

// 
// Decompiled by Procyon v0.5.30
// 

public class dh
{
    public static final String a(final Throwable t) {
        final StringWriter stringWriter = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(stringWriter);
        t.printStackTrace(printWriter);
        printWriter.flush();
        stringWriter.flush();
        return stringWriter.toString();
    }
}
