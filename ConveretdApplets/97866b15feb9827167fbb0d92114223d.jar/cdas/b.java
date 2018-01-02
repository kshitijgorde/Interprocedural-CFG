// 
// Decompiled by Procyon v0.5.30
// 

package cdas;

public class b
{
    public static String b(final String s) {
        String string = "";
        final String s2 = "JP/IhWg-cGdqi4:wfT26B0DSuo8nK9yO1NeMrUm_VYA.7zE#j=&aFxpQskbt3Hvl%?XLR5CZ";
        final String s3 = "u6DHmyL=ARE9h_c8SVN7?MYGd3-iWX&zowI1letK%v#bp/JqxBQf:s.2OZF4PU0r5jankTCg";
        for (int i = 0; i < s.length(); ++i) {
            final int index = s2.indexOf(s.substring(i, i + 1));
            if (index > -1) {
                string += s3.substring(index, index + 1);
            }
        }
        return string;
    }
}
