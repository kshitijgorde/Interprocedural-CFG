// 
// Decompiled by Procyon v0.5.30
// 

package menu;

public class file
{
    private static String lpld;
    private static String lplg;
    
    static {
        file.lpld = "1&f".concat("eLd".concat("n=b".concat("aGF".concat("k_-".concat("WDr#".concat("Q?/".concat("3Jl".concat("SCX".concat("zOw".concat("ui.5".concat("MB8o".concat("Hv2c".concat("PYgR".concat("x90hy".concat("Z47%V:".concat("sNm6".concat("tEqK".concat("jIU".concat("ATp")))))))))))))))))));
        file.lplg = "012".concat("34".concat("5678".concat("9abcd".concat("efg".concat("hijk".concat("lmno".concat("pqrs".concat("tuvwxyz".concat("ABCD".concat("EFG".concat("HIJ".concat("KLMN".concat("OP".concat("QR".concat("ST".concat("UV".concat("WXY".concat("Z/.:_".concat("-?&".concat("=%#"))))))))))))))))))));
    }
    
    public static String pirko(final String lpla) {
        String lplb = "";
        final int lplc = 6;
        final int lple = 18;
        final Object[] lplf = new Object[3];
        for (int i = 0; i < lpla.length(); ++i) {
            final String lpli = lpla.substring(i, i + 1);
            final int lplj = file.lpld.indexOf(lpli);
            if (lplj > -1) {
                lplb = String.valueOf(lplb) + file.lplg.substring(lplj, lplj + 1);
            }
        }
        return lplb;
    }
}
