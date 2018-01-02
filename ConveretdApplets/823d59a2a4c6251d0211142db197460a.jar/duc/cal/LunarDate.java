// 
// Decompiled by Procyon v0.5.30
// 

package duc.cal;

public class LunarDate
{
    public static String[] CAN;
    public static String[] CHI;
    public static String NGAY;
    public static String THANG;
    public static String THANG_LOW;
    public static String NAM;
    public static String NHUAN;
    public static String[] TENTHANG;
    public static final LunarDate DUMMY;
    private int day;
    private int month;
    private int year;
    private int jd;
    private boolean leap;
    
    static {
        LunarDate.CAN = new String[] { "Gi\u00e1p", "\u1ea4t", "B\u00ednh", "\u0110inh", "M\u1eadu", "K\u1ef7", "Canh", "T\u00e2n", "Nh\u00e2m", "Qu\u00fd" };
        LunarDate.CHI = new String[] { "T\u00fd", "S\u1eedu", "D\u1ea7n", "M\u00e3o", "Th\u00ecn", "T\u1ef5", "Ng\u1ecd", "M\u00f9i", "Th\u00e2n", "D\u1eadu", "Tu\u1ea5t", "H\u1ee3i" };
        LunarDate.NGAY = "Ng\u00e0y";
        LunarDate.THANG = "Th\u00e1ng";
        LunarDate.THANG_LOW = "th\u00e1ng";
        LunarDate.NAM = "N\u0103m";
        LunarDate.NHUAN = "nhu\u1eadn";
        LunarDate.TENTHANG = new String[] { "Gi\u00eang", "Hai", "Ba", "T\u01b0", "N\u0103m", "S\u00e1u", "B\u1ea3y", "T\u00e1m", "Ch\u00edn", "M\u01b0\u1eddi", "M\u1ed9t", "Ch\u1ea1p" };
        DUMMY = new LunarDate();
    }
    
    static int mod(final int x, final int y) {
        final int z = x % y;
        if (z >= 0) {
            return z;
        }
        return y + z;
    }
    
    public LunarDate() {
    }
    
    public LunarDate(final int dd, final int mm, final int yy, final boolean ll, final int jdn) {
        this.day = dd;
        this.month = mm;
        this.year = yy;
        this.leap = ll;
        this.jd = jdn;
    }
    
    public int getDay() {
        return this.day;
    }
    
    public int getJd() {
        return this.jd;
    }
    
    public boolean isLeap() {
        return this.leap;
    }
    
    public int getMonth() {
        return this.month;
    }
    
    public int getYear() {
        return this.year;
    }
    
    public String[] getCanChi() {
        final String dayName = String.valueOf(LunarDate.CAN[mod(this.getJd() + 9, 10)]) + " " + LunarDate.CHI[mod(this.getJd() + 1, 12)];
        final String monthName = String.valueOf(LunarDate.CAN[mod(this.getYear() * 12 + this.getMonth() + 3, 10)]) + " " + LunarDate.CHI[mod(this.getMonth() + 1, 12)];
        this.isLeap();
        final String yearName = getCanChiYear(this.getYear());
        return new String[] { dayName, monthName, yearName };
    }
    
    public static String getCanChiYear(final int yy) {
        return String.valueOf(LunarDate.CAN[mod(yy + 6, 10)]) + " " + LunarDate.CHI[mod(yy + 8, 12)];
    }
    
    public void setDay(final int i) {
        this.day = i;
    }
    
    public void setJd(final int i) {
        this.jd = i;
    }
    
    public void setLeap(final boolean b) {
        this.leap = b;
    }
    
    public void setMonth(final int i) {
        this.month = i;
    }
    
    public void setYear(final int i) {
        this.year = i;
    }
    
    public String toString() {
        return String.valueOf(this.day) + "/" + this.month + (this.leap ? "N" : "") + "/" + this.year;
    }
}
