// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.TextArea;
import java.awt.Image;

public final class a
{
    public static int q;
    public static boolean q;
    public static boolean w;
    public static boolean e;
    public static boolean r;
    public static boolean t;
    public static boolean y;
    public static Image q;
    public static Image w;
    public static Image e;
    public static Image r;
    public static Image t;
    public static String q;
    public static aX q;
    public static boolean u;
    public static String w;
    public static String e;
    public static String r;
    
    public static boolean q() {
        return a.q == 1;
    }
    
    public static boolean w() {
        return a.q == 0;
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
    
    public static void q(final String s, final bz bz, final int w) {
        final cJ cj;
        (cj = new cJ(66305, 1)).q = -1;
        cj.w = w;
        cj.q(0, 0, bz.q());
        cj.q(0, 0, s);
        bz.q(cj);
    }
    
    static {
        a.q = -1;
        a.w = false;
        a.e = false;
        a.y = false;
        a.e = cv.q("Spilka");
    }
}
