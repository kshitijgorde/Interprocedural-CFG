import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class bz implements a0
{
    private static Hashtable a;
    private static String b;
    private static Hashtable c;
    private static final Object d;
    public static final Hashtable e;
    
    public static final Integer a(final az az, final String s) {
        synchronized (bz.a) {
            if (bz.a.size() == 0) {
                bz.a = new Hashtable();
                final a3 a = a3.a(az, az.aj().a("TIMEZONE_LIST_URI"));
                bz.a.put("GMT", new Integer(0));
                if (a.f() && a.g()) {
                    for (int i = 1; i < a.w(); ++i) {
                        final String e = a.e(i, "NAME_TIMEZONE");
                        final String e2 = a.e(i, "ID_TIMEZONE");
                        if (a0.a.k()) {
                            a0.a.i("MDGToolBox.put[" + e + "]->[" + e2 + "]");
                        }
                        if (e != null && e2 != null) {
                            bz.a.put(e, e2);
                        }
                    }
                }
            }
            Integer n = null;
            try {
                n = new Integer(bz.a.get(s));
            }
            catch (Exception ex) {
                if (a0.a.g()) {
                    a0.a.b("Cannot convert " + s + " to integer ", ex);
                }
            }
            if (n == null) {
                if (a0.a.g()) {
                    a0.a.d(az.as() + " MDGToolBox: could not resolve " + s);
                }
                if (bz.b == null) {
                    bz.b = az.aj().a("DEFAULT_TIMEZONE");
                }
                n = new Integer(bz.b);
            }
            return n;
        }
    }
    
    public static final String a(final String s) {
        if (s == null) {
            return null;
        }
        return bz.e.get(s);
    }
    
    public static final long a(final az az) {
        try {
            final a3 a = a3.a(az, az.aj().a("URI_SERVERTIME"));
            if (a.f()) {
                final long n = (long)(double)a.c("DATETIME_SERVER");
                final long currentTimeMillis = System.currentTimeMillis();
                if (a0.a.j()) {
                    a0.a.h("MDGToolBox.getDeltaTime local:" + currentTimeMillis + " server:" + n + " is:" + (currentTimeMillis - n));
                }
                return currentTimeMillis - n;
            }
            if (a0.a.g()) {
                a0.a.d("MDGToolBox.getDeltaTime server_time is not valid " + a.t());
            }
        }
        catch (Exception ex) {
            if (a0.a.f()) {
                a0.a.a("cannot compute delta time", ex);
            }
        }
        return Long.MIN_VALUE;
    }
    
    static {
        bz.a = new Hashtable();
        bz.c = new Hashtable();
        d = new Object();
        bz.c.put("WKN", bz.d);
        bz.c.put("NSIN_DE", bz.d);
        bz.c.put("ID_COMPANY", bz.d);
        bz.c.put("ID_OSI", bz.d);
        bz.c.put("ID_INSTRUMENT", bz.d);
        bz.c.put("ISIN", bz.d);
        bz.c.put("SYMBOL_DE", bz.d);
        bz.c.put("TICKER", bz.d);
        bz.c.put("RIC", bz.d);
        bz.c.put("NSIN_US", bz.d);
        bz.c.put("CUSIP", bz.d);
        bz.c.put("SYMBOL_US", bz.d);
        bz.c.put("SICOVAM", bz.d);
        bz.c.put("NSIN_FR", bz.d);
        bz.c.put("SYMBOL_FR", bz.d);
        bz.c.put("NSIN_GB", bz.d);
        bz.c.put("SEDOL", bz.d);
        bz.c.put("SYMBOL_GB", bz.d);
        bz.c.put("EPIC", bz.d);
        bz.c.put("NSIN_CH", bz.d);
        bz.c.put("SYMBOL_CH", bz.d);
        bz.c.put("ID_NOTATION", bz.d);
        (e = new Hashtable()).put("BAS", "baskets");
        bz.e.put("BON", "bonds");
        bz.e.put("CER", "certificates");
        bz.e.put("CLA", "classoptions");
        bz.e.put("CLI", "clickoptions");
        bz.e.put("COM", "commodities");
        bz.e.put("COU", "countries");
        bz.e.put("CUR", "currencies");
        bz.e.put("DER", "derivatives");
        bz.e.put("FTO", "futureoptions");
        bz.e.put("FUN", "funds");
        bz.e.put("FUT", "futures");
        bz.e.put("GEN", "general");
        bz.e.put("GRA", "grainsoils");
        bz.e.put("IGN", "ignore");
        bz.e.put("INA", "inactiveinstruments");
        bz.e.put("IND", "indices");
        bz.e.put("INT", "interests");
        bz.e.put("IPO", "ipos");
        bz.e.put("MET", "metals");
        bz.e.put("OPT", "options");
        bz.e.put("POR", "portfolio");
        bz.e.put("PRE", "preciousmetals");
        bz.e.put("STA", "statistics");
        bz.e.put("STO", "stocks");
        bz.e.put("UNK", "unknown");
        bz.e.put("WAR", "warrants");
    }
}
