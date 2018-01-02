// 
// Decompiled by Procyon v0.5.30
// 

package cdas;

public class b
{
    public static String b(final String s) {
        String string = "";
        final String s2 = "mwn9/VfPZ-:khvy8rO6?s2WIeRJSpGlHgqx3aN7_u4UQ1jBCAiL=&DboE0XTM.cdY#%F5Kzt";
        final String s3 = "g2cy-=Kn:XzWJj0_649frpLHN3.SelT%RB8Dvt17ZxkFsPuEaImMihGow/d5YqbU&O#CVQ?A";
        for (int i = 0; i < s.length(); ++i) {
            final int index = s2.indexOf(s.substring(i, i + 1));
            if (index > -1) {
                string += s3.substring(index, index + 1);
            }
        }
        return string;
    }
}
