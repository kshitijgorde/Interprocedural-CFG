import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class TocDebug
{
    private static boolean m_bDebug;
    private static Frame m_Frame;
    private static int m_nTraceLevel;
    
    public static void Initialize(final boolean b) {
        Initialize(b, 1, null);
    }
    
    public static void Initialize(final boolean b, final Frame frame) {
        Initialize(b, 1, frame);
    }
    
    public static void Initialize(final boolean b, final int n) {
        Initialize(b, n, null);
    }
    
    public static void Initialize(final boolean bDebug, final int nTraceLevel, final Frame frame) {
        TocDebug.m_bDebug = bDebug;
        TocDebug.m_Frame = frame;
        TocDebug.m_nTraceLevel = nTraceLevel;
    }
    
    public static void TraceL2(final String s) {
        if (TocDebug.m_nTraceLevel >= 2) {
            Trace(s);
        }
    }
    
    public static void TraceL1(final String s) {
        if (TocDebug.m_nTraceLevel >= 1) {
            Trace(s);
        }
    }
    
    public static void Assert(final boolean b, final String s) {
        if (TocDebug.m_bDebug && !b) {
            TocMessageBox.Show(TocDebug.m_Frame, s, "Assertion");
        }
    }
    
    public static void Assert(final boolean b) {
        if (TocDebug.m_bDebug) {
            Assert(b, "Assertion Failed");
        }
    }
    
    public static void MessageBox(final Frame frame, final String s, final String s2) {
        if (TocDebug.m_bDebug) {
            TocMessageBox.Show(frame, s, s2);
        }
    }
    
    public static void MessageBox(final String s, final String s2) {
        MessageBox(TocDebug.m_Frame, s, s2);
    }
    
    static {
        TocDebug.m_bDebug = true;
        TocDebug.m_Frame = null;
        TocDebug.m_nTraceLevel = 1;
    }
    
    public static void TraceL3(final String s) {
        if (TocDebug.m_nTraceLevel >= 3) {
            Trace(s);
        }
    }
    
    public static void SetTraceLevel(final int nTraceLevel) {
        TocDebug.m_nTraceLevel = nTraceLevel;
    }
    
    public static boolean getDebug() {
        return TocDebug.m_bDebug;
    }
    
    private static void Trace(final String s) {
        if (TocDebug.m_bDebug) {
            System.err.println(s);
        }
    }
}
