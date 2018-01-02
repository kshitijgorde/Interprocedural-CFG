// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class cy
{
    private static String q;
    private static String w;
    private static String e;
    private static String r;
    private static String t;
    private static String y;
    
    public static String[] q() {
        final String[] array;
        (array = new String[6])[0] = cy.q;
        array[1] = cy.w;
        array[2] = cy.e;
        array[3] = cy.r;
        array[4] = cy.t;
        array[5] = cy.y;
        return array;
    }
    
    public static int q(final String s) {
        if (s.equals(cy.q)) {
            return 1;
        }
        if (s.equals(cy.w)) {
            return 2;
        }
        if (s.equals(cy.e)) {
            return 3;
        }
        if (s.equals(cy.r)) {
            return 4;
        }
        if (s.equals(cy.t)) {
            return 5;
        }
        if (s.equals(cy.y)) {
            return 6;
        }
        return 0;
    }
    
    public static boolean q(final String s) {
        for (int i = 0; i < 6; ++i) {
            if (s.equalsIgnoreCase(q()[i])) {
                return true;
            }
        }
        return false;
    }
    
    public static es q(final boolean b, final boolean b2, final boolean b3, final boolean b4, final int n, final int n2, final int n3) {
        final es es;
        (es = new es(4198480, 1)).w = -1;
        es.q = -1;
        es.q(0, 0, b);
        if (a.q()) {
            es.q(0, 1, b2);
            es.q(0, 2, b3);
            es.q(0, 3, b4);
        }
        es.q(0, 0, n << 16 | n2);
        es.q(0, 1, n3);
        return es;
    }
    
    static {
        cy.q = eb.q("online");
        cy.w = eb.q("busy");
        cy.e = eb.q("be right back");
        cy.r = eb.q("away");
        cy.t = eb.q("in a call");
        cy.y = eb.q("out to lunch");
        eb.q("Visible");
        eb.q("Invisible");
        eb.q("Ghost");
    }
}
