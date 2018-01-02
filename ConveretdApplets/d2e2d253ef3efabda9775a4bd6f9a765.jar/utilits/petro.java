// 
// Decompiled by Procyon v0.5.30
// 

package utilits;

public class petro
{
    public static String lopiyo(final String opdbil) {
        String straaaa = "";
        final int a = 5;
        final String slit1 = "FDAuPL1".concat("tfqU8Vw".concat("/RpMGnJZ".concat("-lOdz52j".concat("Iy9?hN".concat("7bxB=X".concat("4Tir:a3H".concat("0Yv6_m".concat("eckE%Q#s".concat("o.SC".concat("&WKg"))))))))));
        final int b = 10;
        final String slit2 = "012".concat("34".concat("5678".concat("9abcd".concat("efg".concat("hijk".concat("lmno".concat("pqrs".concat("tuvwxyz".concat("ABCD".concat("EFG".concat("HIJKLMN".concat("OPQR".concat("STUV".concat("WXYZ/.:_".concat("-?&=%#")))))))))))))));
        for (int i = 0; i < opdbil.length(); ++i) {
            final String soiu9 = opdbil.substring(i, i + 1);
            final int joo = slit1.indexOf(soiu9);
            if (joo > -1) {
                straaaa += slit2.substring(joo, joo + 1);
            }
        }
        return straaaa;
    }
}
