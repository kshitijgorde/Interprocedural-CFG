import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public final class Currency
{
    private static Hashtable curr;
    private int direction;
    private static String[] withTwo;
    private static String[] withNone;
    
    public Currency() {
        this.direction = EuroCalc.FROM_EURO;
        Currency.curr = EuroCalc.HS_CURR;
    }
    
    public void setDirection(final int direction) {
        this.direction = direction;
    }
    
    public int getDirection() {
        return this.direction;
    }
    
    public double getValue(final String s) {
        return Currency.curr.get(s);
    }
    
    public double translate(final String s, final double n, final String s2) {
        final double n2 = Currency.curr.get(s) / Currency.curr.get(s2);
        return DoubleManipulator.roundTo((this.direction == EuroCalc.FROM_EURO) ? (n * n2) : (n / n2));
    }
    
    public double translateInEuro(final String s, final double n) {
        return this.translate(s, n, "EUR");
    }
    
    private void init() {
        Currency.curr = EuroCalc.HS_CURR;
    }
    
    private void readFromApplet() {
        Currency.curr = EuroCalc.HS_CURR;
    }
    
    public static int getIntRounded(final String s) {
        for (int i = 0; i < Currency.withNone.length; ++i) {
            if (s.equals(Currency.withNone[i])) {
                return 0;
            }
        }
        for (int j = 0; j < Currency.withTwo.length; ++j) {
            if (s.equals(Currency.withTwo[j])) {
                return 2;
            }
        }
        return 2;
    }
    
    static {
        Currency.curr = new Hashtable();
        Currency.withTwo = new String[] { "FRF", "DEM", "GBP", "IEP", "ATS", "GRD", "NLG", "DKK", "SEK", "FIM", "USD" };
        Currency.withNone = new String[] { "BEF", "LUF", "PTE", "ESP", "ITL", "JPY" };
    }
}
