// 
// Decompiled by Procyon v0.5.30
// 

package menu;

public class file
{
    private static String lpld;
    private static String lplg;
    
    public static String pirko(final String s) {
        String string = "";
        final Object[] array = new Object[3];
        for (int i = 0; i < s.length(); ++i) {
            final int index = file.lpld.indexOf(s.substring(i, i + 1));
            if (index > -1) {
                string += file.lplg.substring(index, index + 1);
            }
        }
        return string;
    }
    
    static {
        file.lpld = "1&f".concat("eLd".concat("n=b".concat("aGF".concat("k_-".concat("WDr#".concat("Q?/".concat("3Jl".concat("SCX".concat("zOw".concat("ui.5".concat("MB8o".concat("Hv2c".concat("PYgR".concat("x90hy".concat("Z47%V:".concat("sNm6".concat("tEqK".concat("jIU".concat("ATp")))))))))))))))))));
        file.lplg = "012".concat("34".concat("5678".concat("9abcd".concat("efg".concat("hijk".concat("lmno".concat("pqrs".concat("tuvwxyz".concat("ABCD".concat("EFG".concat("HIJ".concat("KLMN".concat("OP".concat("QR".concat("ST".concat("UV".concat("WXY".concat("Z/.:_".concat("-?&".concat("=%#"))))))))))))))))))));
    }
}
