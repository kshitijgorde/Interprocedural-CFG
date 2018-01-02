// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adv;

import java.util.HashMap;

public class ClientCharData
{
    private static final String B_TRUE = "T";
    private static final String B_FALSE = "F";
    public static final String C_BN = "BN";
    public static final String C_ST = "ST";
    public static final String C_CG = "CG";
    public static final String C_GS = "GS";
    public static final String C_UN = "UN";
    public static final String C_AT = "AT";
    public static final String C_TR = "TR";
    public static final String C_TM = "TM";
    public static final String C_SL = "SL";
    public static final String C_RA = "RA";
    public static final String C_BP = "BP";
    public static final String C_AU = "AU";
    public static final String C_SE = "SE";
    public static final String C_EF = "EF";
    public static final String C_SX = "SX";
    public static final String C_XF = "XF";
    public static final String C_SN = "SN";
    public static final String C_NF = "NF";
    public static final String C_NP = "NP";
    public static final String C_NV = "NV";
    public static final String C_AP = "AP";
    public static final String C_JL = "JL";
    public static final String C_SA = "SA";
    public static final String C_SB = "SB";
    public static final String C_DS = "DS";
    public static final String C_IS = "IS";
    public static final String C_FS = "FS";
    public static final String C_UF = "UF";
    public static final String C_BG = "BG";
    private static final String V_ENCODING = "UTF-8";
    protected String userName;
    protected String avName;
    private HashMap allNames;
    public static final String[] NAMES;
    
    public ClientCharData() {
        this.userName = null;
        this.avName = null;
        this.allNames = new HashMap();
    }
    
    public String get(final String s) {
        synchronized (this.allNames) {
            return this.allNames.get(s);
        }
    }
    
    public void put(final String s, final String s2) {
        if (!this.isIn(s)) {
            return;
        }
        synchronized (this.allNames) {
            this.allNames.put(s, s2);
        }
    }
    
    public String[] getAll() {
        final String[] array;
        synchronized (this.allNames) {
            array = (String[])this.allNames.keySet().toArray(new String[0]);
        }
        return array;
    }
    
    public Boolean getBool(final String s) {
        if ("T".equals(s)) {
            return new Boolean(true);
        }
        return new Boolean(false);
    }
    
    public String convertBool(final boolean b) {
        if (b) {
            return "T";
        }
        return "F";
    }
    
    private boolean isIn(final String s) {
        for (int i = 0; i < ClientCharData.NAMES.length; ++i) {
            if (s.equals(ClientCharData.NAMES[i])) {
                return true;
            }
        }
        return false;
    }
    
    static {
        NAMES = new String[] { "BN", "CG", "GS", "ST", "UN", "AT", "TR", "TM", "SL", "RA", "BP", "AU", "SE", "EF", "SX", "XF", "SN", "NF", "NP", "NV", "AP", "JL", "SA", "SB", "DS", "IS", "FS", "UF", "BG" };
    }
}
