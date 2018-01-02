// 
// Decompiled by Procyon v0.5.30
// 

package utilits;

public class compil
{
    public static String b(final String ps) {
        String str = "";
        final String s1 = "FD/AuPL".concat("-e=1tfqU".concat("8ZlOV".concat("wRndzJpMN7".concat("2G5?h9".concat("bxjIa.3y".concat("BX:ir0".concat("H4ckETYv%".concat("QC6_m&Wo".concat("SK#sg")))))))));
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
