// 
// Decompiled by Procyon v0.5.30
// 

package net.sf.jmimemagic.detectors;

import jmaster.util.log.B;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import net.sf.jmimemagic.A;

public class TextFileDetector implements A
{
    private static jmaster.util.log.A A;
    static /* synthetic */ Class class$net$sf$jmimemagic$detectors$TextFileDetector;
    
    public String getDisplayName() {
        return "Text File Detector";
    }
    
    public String getVersion() {
        return "0.1";
    }
    
    public String[] getHandledExtensions() {
        return new String[] { "txt", "text" };
    }
    
    public String[] getHandledTypes() {
        return new String[] { "text/plain" };
    }
    
    public String getName() {
        return "textfiledetector";
    }
    
    public String[] process(final byte[] array, final int n, final int n2, final long n3, final char c, final String s, final Map map) {
        TextFileDetector.A.D("processing stream data");
        final z.C.A.A.B.B.A a = new z.C.A.A.B.B.A();
        try {
            if (!a.C("/[^[:ascii:][:space:]]/", new String(array, "UTF-8"))) {
                return new String[] { "text/plain" };
            }
            return null;
        }
        catch (UnsupportedEncodingException ex) {
            TextFileDetector.A.E("TextFileDetector: failed to process data");
            return null;
        }
    }
    
    public String[] process(final File file, final int n, final int n2, final long n3, final char c, final String s, final Map map) {
        TextFileDetector.A.D("processing file data");
        return new String[0];
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
    
    static {
        TextFileDetector.A = B.getInstance().getLog((TextFileDetector.class$net$sf$jmimemagic$detectors$TextFileDetector == null) ? (TextFileDetector.class$net$sf$jmimemagic$detectors$TextFileDetector = class$("net.sf.jmimemagic.detectors.TextFileDetector")) : TextFileDetector.class$net$sf$jmimemagic$detectors$TextFileDetector);
    }
}
