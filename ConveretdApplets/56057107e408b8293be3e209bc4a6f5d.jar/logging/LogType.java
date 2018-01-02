// 
// Decompiled by Procyon v0.5.30
// 

package logging;

public final class LogType
{
    private static final String[] STR_LOG_TYPES;
    private static final String STR_ADD_LOG_TYPE = "+";
    private static final int[] LOG_TYPES;
    public static final int NUL;
    public static final int GUI;
    public static final int NET;
    public static final int THREAD;
    public static final int MISC;
    public static final int PAY;
    public static final int TOR;
    public static final int CRYPTO;
    public static final int FILTER;
    public static final int AGREEMENT;
    public static final int DB;
    public static final int TRANSPORT;
    public static final int FILE;
    public static final int ALL;
    
    public static boolean isValidLogType(final int n) {
        return n >= 0 && n < LogType.STR_LOG_TYPES.length;
    }
    
    public static int[] getAvailableLogTypes() {
        final int[] array = new int[LogType.STR_LOG_TYPES.length - 1];
        array[0] = 0;
        int i = 1;
        int n = 1;
        while (i < array.length) {
            array[i] = n;
            n <<= 1;
            ++i;
        }
        return array;
    }
    
    public static int getNumberOfLogTypes() {
        return LogType.STR_LOG_TYPES.length - 1;
    }
    
    public static String getLogTypeName(final int n) {
        String string = "";
        String substring;
        if (n == 0) {
            substring = LogType.STR_LOG_TYPES[0];
        }
        else if ((n & LogType.ALL) == LogType.ALL) {
            substring = LogType.STR_LOG_TYPES[LogType.STR_LOG_TYPES.length - 1];
        }
        else {
            for (int i = 1; i < LogType.LOG_TYPES.length; ++i) {
                if ((n & LogType.LOG_TYPES[i]) > 0) {
                    string = string + LogType.STR_LOG_TYPES[i] + "+";
                }
            }
            if (string.length() == 0) {
                substring = LogType.STR_LOG_TYPES[0];
            }
            else {
                substring = string.substring(0, string.length() - "+".length());
            }
        }
        return substring;
    }
    
    private static int createLogTypeALL() {
        int n = 0;
        for (int i = 0; i < LogType.LOG_TYPES.length; ++i) {
            n += LogType.LOG_TYPES[i];
        }
        return n;
    }
    
    static {
        STR_LOG_TYPES = new String[] { "NUL", "GUI", "NET", "THREAD", "MISC", "PAY", "TOR", "CRYPTO", "FILTER", "AGREEMENT", "DB", "TRANSPORT", "FILE", "ALL" };
        LOG_TYPES = getAvailableLogTypes();
        NUL = LogType.LOG_TYPES[0];
        GUI = LogType.LOG_TYPES[1];
        NET = LogType.LOG_TYPES[2];
        THREAD = LogType.LOG_TYPES[3];
        MISC = LogType.LOG_TYPES[4];
        PAY = LogType.LOG_TYPES[5];
        TOR = LogType.LOG_TYPES[6];
        CRYPTO = LogType.LOG_TYPES[7];
        FILTER = LogType.LOG_TYPES[8];
        AGREEMENT = LogType.LOG_TYPES[9];
        DB = LogType.LOG_TYPES[10];
        TRANSPORT = LogType.LOG_TYPES[11];
        FILE = LogType.LOG_TYPES[12];
        ALL = createLogTypeALL();
    }
}
