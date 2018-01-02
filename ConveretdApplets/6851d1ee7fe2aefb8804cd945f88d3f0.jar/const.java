// 
// Decompiled by Procyon v0.5.30
// 

public class const
{
    private static final int[] nqa;
    private static final String[] oqa;
    public static final String[] pqa;
    
    public static String d(final int n) {
        for (int i = 0; i < const.nqa.length; ++i) {
            if (const.nqa[i] == n) {
                return const.oqa[i];
            }
        }
        return null;
    }
    
    public static int l(final String s) {
        for (int i = 0; i < const.oqa.length; ++i) {
            if (const.oqa[i].equals(s)) {
                return const.nqa[i];
            }
        }
        return -1;
    }
    
    public static int b(final o o, final String s) {
        if (o == null || s == null) {
            return -1;
        }
        for (int i = 0; i < const.pqa.length; ++i) {
            if (s.equals(o.b(const.pqa[i]))) {
                return const.nqa[i];
            }
        }
        return -1;
    }
    
    public static String e(final int n) {
        for (int i = 0; i < const.nqa.length; ++i) {
            if (const.nqa[i] == n) {
                return const.pqa[i];
            }
        }
        return null;
    }
    
    static {
        nqa = new int[] { 0, 1, 2, 3, 4, 5, 10, 15, 20, 30, 60, 120, 180, 240, 300, 600, 900, 1200, 1800, 3600, 100001, 100002, 100003 };
        oqa = new String[] { "intraday", "intraday_1s", "intraday_2s", "intraday_3s", "intraday_4s", "intraday_5s", "intraday_10s", "intraday_15s", "intraday_20s", "intraday_30s", "intraday_1", "intraday_2", "intraday_3", "intraday_4", "intraday_5", "intraday_10", "intraday_15", "intraday_20", "intraday_30", "intraday_60", "day", "week", "month" };
        pqa = new String[] { "cbIntraday", "cbIntraday1s", "cbIntraday2s", "cbIntraday3s", "cbIntraday4s", "cbIntraday5s", "cbIntraday10s", "cbIntraday15s", "cbIntraday20s", "cbIntraday30s", "cbIntraday1", "cbIntraday2", "cbIntraday3", "cbIntraday4", "cbIntraday5", "cbIntraday10", "cbIntraday15", "cbIntraday20", "cbIntraday30", "cbIntraday60", "cbDaily", "cbWeekly", "cbMonthly" };
    }
}
