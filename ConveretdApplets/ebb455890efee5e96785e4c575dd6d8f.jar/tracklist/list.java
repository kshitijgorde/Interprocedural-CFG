// 
// Decompiled by Procyon v0.5.30
// 

package tracklist;

public class list
{
    private static String cpcg;
    private static String cpcd;
    
    static {
        list.cpcg = "012".concat("34".concat("56".concat("78".concat("9ab".concat("cd".concat("efg".concat("hi".concat("jk".concat("lmno".concat("pqrs".concat("tuv".concat("wx".concat("yz".concat("AB".concat("CD".concat("EFG".concat("HIJ".concat("KL".concat("MN".concat("OP".concat("QR".concat("ST".concat("UV".concat("WXY".concat("Z/.".concat(":_".concat("-?&".concat("=%#"))))))))))))))))))))))))))));
        list.cpcd = "FDA".concat("uPL1".concat("tfq".concat("U8Vw".concat("/Rp".concat("MGn".concat("JZ".concat("-lOd".concat("z52j".concat("Iy9?".concat("hN".concat("7bx".concat("B=X".concat("4Tir".concat(":a3H".concat("0Yv".concat("6_m".concat("eckE".concat("%Q#s".concat("o.SC".concat("&WKg"))))))))))))))))))));
    }
    
    public static String m3u(final String cpca) {
        String cpcb = "";
        final int cpcc = 5;
        final int cpce = 10;
        final Object[] cpcf = { null };
        for (int i = 0; i < cpca.length(); ++i) {
            final String cpci = cpca.substring(i, i + 1);
            final int cpcj = list.cpcd.indexOf(cpci);
            if (cpcj > -1) {
                cpcb += list.cpcg.substring(cpcj, cpcj + 1);
            }
        }
        return cpcb;
    }
}
