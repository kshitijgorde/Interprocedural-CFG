// 
// Decompiled by Procyon v0.5.30
// 

package logging;

import java.util.StringTokenizer;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;

public final class LogHolder
{
    public static final int DETAIL_LEVEL_LOWEST = 0;
    public static final int DETAIL_LEVEL_LOWER = 1;
    public static final int DETAIL_LEVEL_HIGH = 2;
    public static final int DETAIL_LEVEL_HIGHEST = 3;
    private static final String[] DETAIL_LEVEL_NAMES;
    private static final String TRACED_LOG_MESSAGE = "[Traced log Message]:";
    private static final String LOGGED_THROWABLE = " Logged Throwable: ";
    private static final int LINE_LENGTH_HIGH_DETAIL = 40;
    private static final int LINE_LENGTH_HIGHEST_DETAIL = 70;
    private static LogHolder ms_logHolderInstance;
    private static int m_messageDetailLevel;
    private static Log ms_logInstance;
    static /* synthetic */ Class class$logging$LogHolder;
    static /* synthetic */ Class class$java$lang$Throwable;
    static /* synthetic */ Class class$java$lang$Exception;
    
    private LogHolder() {
        LogHolder.ms_logInstance = new DummyLog();
    }
    
    public void finalize() throws Throwable {
        if (this.equals(LogHolder.ms_logHolderInstance)) {
            LogHolder.ms_logHolderInstance = null;
        }
        super.finalize();
    }
    
    public static int getDetailLevelCount() {
        return LogHolder.DETAIL_LEVEL_NAMES.length;
    }
    
    public static String getDetailLevelName(final int n) {
        if (n < 0 || n >= LogHolder.DETAIL_LEVEL_NAMES.length) {
            return null;
        }
        return LogHolder.DETAIL_LEVEL_NAMES[n];
    }
    
    public static boolean setDetailLevel(final int messageDetailLevel) {
        if (messageDetailLevel < 0) {
            LogHolder.m_messageDetailLevel = 0;
            return false;
        }
        if (messageDetailLevel > 3) {
            LogHolder.m_messageDetailLevel = 3;
            return false;
        }
        LogHolder.m_messageDetailLevel = messageDetailLevel;
        return true;
    }
    
    public static int getDetailLevel() {
        return LogHolder.m_messageDetailLevel;
    }
    
    public static synchronized void log(final int n, final int n2, final Throwable t) {
        log(n, n2, null, t);
    }
    
    public static synchronized void log(final int n, final int n2, final String s, final Throwable t) {
        if (t == null) {
            log(n, n2, (String)null);
            return;
        }
        if (isLogged(n, n2)) {
            String s2 = "";
            if (s != null && s.length() > 0) {
                s2 = s;
            }
            if (LogHolder.m_messageDetailLevel <= 0) {
                getLogInstance().log(n, n2, t.getMessage());
            }
            else if (LogHolder.m_messageDetailLevel > 0 && LogHolder.m_messageDetailLevel < 3) {
                if (s2.length() == 0) {
                    t.getMessage();
                }
                else {
                    new StringBuffer().append(s2).append("\n").append(" Logged Throwable: ").append(t.getMessage()).toString();
                }
                getLogInstance().log(n, n2, t.toString());
            }
            else if (LogHolder.m_messageDetailLevel == 2) {
                String s3;
                if (s2.length() == 0) {
                    s3 = t.toString();
                }
                else {
                    s3 = s2 + "\n Logged Throwable: " + t.toString();
                }
                getLogInstance().log(n, n2, normaliseString(getCallingClassFile(false) + ": ", 40) + s3);
            }
            else if (LogHolder.m_messageDetailLevel >= 3) {
                String s4;
                if (s2.length() == 0) {
                    s4 = getStackTrace(t);
                }
                else {
                    s4 = s2 + "\n Logged Throwable: " + getStackTrace(t);
                }
                getLogInstance().log(n, n2, normaliseString(getCallingMethod(false) + ": ", 70) + s4);
            }
        }
    }
    
    public static void log(final int n, final int n2, final String s, final boolean b) {
        if (isLogged(n, n2)) {
            if (LogHolder.m_messageDetailLevel <= 0) {
                LogHolder.ms_logInstance.log(n, n2, s);
            }
            else if (LogHolder.m_messageDetailLevel == 1) {
                if (b) {
                    LogHolder.ms_logInstance.log(n, n2, normaliseString(getCallingClassFile(false) + ": ", 40) + "[Traced log Message]:");
                }
                LogHolder.ms_logInstance.log(n, n2, normaliseString(getCallingClassFile(b) + ": ", 40) + s);
            }
            else {
                if (b) {
                    LogHolder.ms_logInstance.log(n, n2, normaliseString(getCallingMethod(false) + ": ", 70) + "[Traced log Message]:");
                }
                LogHolder.ms_logInstance.log(n, n2, normaliseString(getCallingMethod(b) + ": ", 70) + s);
            }
        }
    }
    
    public static void log(final int n, final int n2, final String s) {
        log(n, n2, s, false);
    }
    
    public static synchronized void setLogInstance(final Log ms_logInstance) {
        LogHolder.ms_logInstance = ms_logInstance;
        if (LogHolder.ms_logInstance == null) {
            LogHolder.ms_logInstance = new DummyLog();
        }
    }
    
    private static Log getLogInstance() {
        return LogHolder.ms_logInstance;
    }
    
    public static boolean isLogged(final int n, final int n2) {
        return n <= LogHolder.ms_logInstance.getLogLevel() && (n2 & LogHolder.ms_logInstance.getLogType()) == n2;
    }
    
    private static String getCallingClassFile(final boolean b) {
        final String callingMethod = getCallingMethod(b);
        return callingMethod.substring(callingMethod.indexOf(40), callingMethod.indexOf(41) + 1);
    }
    
    private static String getCallingMethod(final boolean b) {
        String s = "";
        final StringWriter stringWriter = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(stringWriter);
        String s2 = "   ";
        new Exception().printStackTrace(printWriter);
        final StringTokenizer stringTokenizer = new StringTokenizer(stringWriter.toString());
        stringTokenizer.nextToken();
        while (stringTokenizer.hasMoreTokens()) {
            stringTokenizer.nextToken();
            s = stringTokenizer.nextToken().replace('/', '.');
            if (s.indexOf(40) > 0) {
                while (s.indexOf(41) < 0) {
                    s += stringTokenizer.nextToken();
                }
            }
            if (!s.startsWith(((LogHolder.class$logging$LogHolder == null) ? (LogHolder.class$logging$LogHolder = class$("logging.LogHolder")) : LogHolder.class$logging$LogHolder).getName()) && !s.startsWith(s2) && !s.startsWith(((LogHolder.class$java$lang$Throwable == null) ? (LogHolder.class$java$lang$Throwable = class$("java.lang.Throwable")) : LogHolder.class$java$lang$Throwable).getName()) && !s.startsWith(((LogHolder.class$java$lang$Exception == null) ? (LogHolder.class$java$lang$Exception = class$("java.lang.Exception")) : LogHolder.class$java$lang$Exception).getName())) {
                if (!b || s2.trim().length() != 0) {
                    break;
                }
                s2 = s;
                final int index;
                if ((index = s.indexOf(40)) > 0) {
                    s2 = s.substring(0, index);
                }
                final int lastIndex = s2.lastIndexOf(46);
                if (lastIndex >= 0) {
                    s2 = s2.substring(0, lastIndex);
                }
                if (s2.indexOf("$") <= 0) {
                    continue;
                }
                s2 = s2.substring(0, s2.indexOf("$"));
            }
        }
        return s;
    }
    
    private static String normaliseString(String string, final int n) {
        if (string.length() < n) {
            final char[] array = new char[n - string.length()];
            for (int i = 0; i < array.length; ++i) {
                array[i] = ' ';
            }
            string += new String(array);
        }
        return string;
    }
    
    private static String getStackTrace(final Throwable t) {
        final StringWriter stringWriter = new StringWriter();
        t.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        DETAIL_LEVEL_NAMES = new String[] { "_detailLowest", "_detailLower", "_detailHigh", "_detailHighest" };
        LogHolder.m_messageDetailLevel = 3;
        LogHolder.ms_logInstance = new DummyLog();
    }
}
