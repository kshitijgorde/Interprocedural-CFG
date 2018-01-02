// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.TextArea;
import java.util.Hashtable;
import java.awt.Image;

public final class cu
{
    public static int q;
    public static boolean q;
    public static boolean w;
    public static boolean e;
    public static boolean r;
    public static boolean t;
    public static boolean y;
    public static boolean u;
    public static Image q;
    public static Image w;
    public static Image e;
    public static Image r;
    public static Image t;
    public static String q;
    public static ae q;
    public static String w;
    private static Hashtable q;
    public static String e;
    public static String r;
    
    public static boolean q() {
        return cu.q == 1;
    }
    
    public static boolean w() {
        return cu.q == 0;
    }
    
    public static void q(String string, final TextArea textArea) {
        string += " ";
        final String text = textArea.getText();
        String substring = "";
        final int caretPosition;
        String substring2;
        if ((caretPosition = textArea.getCaretPosition()) != text.length()) {
            substring2 = text.substring(0, caretPosition);
            substring = text.substring(caretPosition, text.length());
        }
        else {
            substring2 = text;
        }
        textArea.setText(substring2 + string + substring);
        textArea.setCaretPosition(caretPosition + string.length());
        textArea.requestFocus();
    }
    
    public static void q(final String s, final br br, final int w) {
        final cr cr;
        (cr = new cr(66305, 1)).q = -1;
        cr.w = w;
        cr.q(0, 0, br.q());
        cr.q(0, 0, s);
        br.r(cr);
    }
    
    public static Image q(final Component component, final int n, final int n2, final String s) {
        final String string = n + ";" + n2;
        Image image;
        if ((image = cu.q.get(string)) == null) {
            image = component.createImage(n, n2);
            cu.q.put(string, image);
            System.out.println("Created image, " + s);
            System.gc();
        }
        return image;
    }
    
    static {
        cu.q = -1;
        cu.q = false;
        cu.w = false;
        cu.e = false;
        cu.r = false;
        cu.t = false;
        cu.y = false;
        cu.u = false;
        cu.q = new Hashtable();
        cu.e = ak.q("Spilka");
    }
}
