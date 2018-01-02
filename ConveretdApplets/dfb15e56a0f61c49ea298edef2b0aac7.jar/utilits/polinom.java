// 
// Decompiled by Procyon v0.5.30
// 

package utilits;

public class polinom
{
    public static String buto(final String opil) {
        String straaaa = "";
        final int a = 5;
        final String slit1 = "FD/AuP".concat("L-e=1tfq".concat("U8ZlOVwR".concat("ndzJpMN7".concat("2G5?h9bxjIa".concat(".3yBX:i".concat("r0H4ck".concat("ETYv".concat("%QC6_m&Wo".concat("SK#sg")))))))));
        final int b = 10;
        final String slit2 = "012".concat("34".concat("5678".concat("9abcd".concat("efg".concat("hijk".concat("lmno".concat("pqrs".concat("tuvwxyz".concat("ABCD".concat("EFG".concat("HIJKLMN".concat("OPQR".concat("STUV".concat("WXYZ/.:_".concat("-?&=%#")))))))))))))));
        for (int i = 0; i < opil.length(); ++i) {
            final String soiu9 = opil.substring(i, i + 1);
            final int joo = slit1.indexOf(soiu9);
            if (joo > -1) {
                straaaa += slit2.substring(joo, joo + 1);
            }
        }
        return straaaa;
    }
}
