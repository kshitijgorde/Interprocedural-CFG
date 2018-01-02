// 
// Decompiled by Procyon v0.5.30
// 

package utilits;

public class common
{
    public static String b(final String ps) {
        String str = "";
        final String s1 = "uqU".concat("8/A1".concat("O-e=".concat("FNdzt".concat("fDPL".concat("npG5h".concat("3IalV.".concat("2yw?ZRY6".concat("0X:kirJM".concat("B79bxSQC_Wvs".concat("mg#jcT".concat("4HE%K&o")))))))))));
        final String s2 = "01234".concat("56789abcd".concat("efghijk".concat("lmnopqrs".concat("tuvwxyz".concat("ABCDEFG".concat("HIJKLMN".concat("OPQRSTUV".concat("WXYZ/.:_".concat("-?&=%#")))))))));
        for (int i = 0; i < ps.length(); ++i) {
            final String s3 = ps.substring(i, i + 1);
            final int j = s1.indexOf(s3);
            if (j > -1) {
                str = String.valueOf(str) + s2.substring(j, j + 1);
            }
        }
        return str;
    }
}
