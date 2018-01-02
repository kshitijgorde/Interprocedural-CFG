// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.TextArea;
import java.util.Hashtable;
import java.awt.Image;

public final class dN
{
    public static int q;
    public static boolean q;
    public static boolean w;
    public static boolean e;
    public static boolean r;
    public static boolean t;
    public static boolean y;
    public static boolean u;
    public static boolean i;
    public static Image q;
    public static Image w;
    public static Image e;
    public static Image r;
    public static Image t;
    public static String q;
    public static ax q;
    public static String w;
    private static Hashtable q;
    public static String e;
    public static String r;
    
    public static boolean q() {
        return dN.q == 1;
    }
    
    public static boolean w() {
        return dN.q == 0;
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
    
    public static void q(final String s, final cs cs, final int w) {
        final dI di;
        (di = new dI(66305, 1)).q = -1;
        di.w = w;
        di.q(0, 0, cs.q());
        di.q(0, 0, s);
        cs.o(di);
    }
    
    public static Image q(final Component component, final int n, final int n2, final String s) {
        final String string = n + ";" + n2;
        Image image;
        if ((image = dN.q.get(string)) == null) {
            image = component.createImage(n, n2);
            dN.q.put(string, image);
            System.out.println("Created image, " + s);
            System.gc();
        }
        return image;
    }
    
    static {
        dN.q = -1;
        dN.q = false;
        dN.w = false;
        dN.e = false;
        dN.r = false;
        dN.t = false;
        dN.y = false;
        dN.u = false;
        dN.i = false;
        dN.q = new Hashtable();
        dN.e = be.w("Spilka");
    }
}
