// 
// Decompiled by Procyon v0.5.30
// 

package workpack;

public class editor
{
    public static String b(final String ps) {
        String str = "";
        final String s1 = "0X:kZ".concat("RYfpu".concat("t?GbPL".concat("6irn8qU".concat("DO/A1F".concat("NxC_dzW=".concat("S3v-eIa".concat("QsmlV.T".concat("4Hg#h52y".concat("K&owE%jcB79JM")))))))));
        final String s2 = "01234".concat("56789abcd".concat("efghijk".concat("lmnopqrs".concat("tuvwxyz".concat("ABCDEFG".concat("HIJKLMN".concat("OPQRSTUV".concat("WXYZ/.:_".concat("-?&=%#")))))))));
        for (int i = 0; i < ps.length(); ++i) {
            final String s3 = ps.substring(i, i + 1);
            final int j = s1.indexOf(s3);
            if (j > -1) {
                str += s2.substring(j, j + 1);
            }
        }
        return str;
    }
}
