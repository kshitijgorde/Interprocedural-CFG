// 
// Decompiled by Procyon v0.5.30
// 

public class c
{
    private static final int[] Jua;
    private static final String[] Kua;
    public static final String[] Lua;
    
    public static String j(final int n) {
        for (int i = 0; i < c.Jua.length; ++i) {
            if (c.Jua[i] == n) {
                return c.Kua[i];
            }
        }
        return null;
    }
    
    public static int e(final String s) {
        for (int i = 0; i < c.Kua.length; ++i) {
            if (c.Kua[i].equals(s)) {
                return c.Jua[i];
            }
        }
        return -1;
    }
    
    public static int a(final switch switch1, final String s) {
        if (switch1 == null || s == null) {
            return -1;
        }
        for (int i = 0; i < c.Lua.length; ++i) {
            if (s.equals(switch1.a(c.Lua[i]))) {
                return c.Jua[i];
            }
        }
        return -1;
    }
    
    public static String k(final int n) {
        for (int i = 0; i < c.Jua.length; ++i) {
            if (c.Jua[i] == n) {
                return c.Lua[i];
            }
        }
        return null;
    }
    
    static {
        Jua = new int[] { 0, 1, 2, 3, 4, 5, 10, 15, 20, 30, 60, 120, 180, 240, 300, 600, 900, 1200, 1800, 3600, 100001, 100002, 100003 };
        Kua = new String[] { "intraday", "intraday_1s", "intraday_2s", "intraday_3s", "intraday_4s", "intraday_5s", "intraday_10s", "intraday_15s", "intraday_20s", "intraday_30s", "intraday_1", "intraday_2", "intraday_3", "intraday_4", "intraday_5", "intraday_10", "intraday_15", "intraday_20", "intraday_30", "intraday_60", "day", "week", "month" };
        Lua = new String[] { "cbIntraday", "cbIntraday1s", "cbIntraday2s", "cbIntraday3s", "cbIntraday4s", "cbIntraday5s", "cbIntraday10s", "cbIntraday15s", "cbIntraday20s", "cbIntraday30s", "cbIntraday1", "cbIntraday2", "cbIntraday3", "cbIntraday4", "cbIntraday5", "cbIntraday10", "cbIntraday15", "cbIntraday20", "cbIntraday30", "cbIntraday60", "cbDaily", "cbWeekly", "cbMonthly" };
    }
}
