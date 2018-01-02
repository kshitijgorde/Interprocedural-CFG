// 
// Decompiled by Procyon v0.5.30
// 

package ChatRoom;

import java.awt.FontMetrics;
import java.awt.Rectangle;
import java.awt.TextArea;

public class d extends TextArea
{
    public ChatRoomApplet a;
    public String b;
    public static String c;
    
    public static int[] a(final Rectangle rectangle, final FontMetrics fontMetrics, final String s) {
        return a(rectangle.width, fontMetrics, s, rectangle.height / fontMetrics.getHeight(), 5);
    }
    
    public static int[] a(final int n, final FontMetrics fontMetrics, final String s, final int n2, final int n3) {
        if (s.length() != 0) {
            final int n4 = n - 2 * n3;
            int n5 = 0;
            final int[] array = new int[n2];
            int n6 = fontMetrics.stringWidth(s.substring(0, 1));
            final int length = s.length();
            for (int n7 = length - 1, n8 = 0; n8 <= n7 && n6 < n4 && n5 < n2; n6 = fontMetrics.stringWidth(s.substring(n8, 1 + n8))) {
                int n9;
                for (n9 = 1; fontMetrics.stringWidth(s.substring(n8, n9 + n8)) < n4 && !s.substring(n8 + n9 - 1, n8 + n9).equals("\n") && n9 + n8 < length; ++n9) {}
                int n10 = n9;
                int n11 = n8 + n9;
                int n12 = 1;
                if (n11 < length && !s.substring(n11 - 1, n11).equals("\n")) {
                    for (n10 = n9 - 1, n11 = n8 + n9 - 1; !Character.isSpace(s.charAt(n11)) && n12 < n10; ++n12, --n11) {}
                }
                if (n12 == n10) {
                    n8 += n10;
                }
                else {
                    n8 = n11;
                }
                if (n8 != 0 && n8 != s.length() && s.substring(n8, n8 + 1).equals("\n") && !s.substring(n8 - 1, n8).equals("\n")) {
                    ++n8;
                }
                array[n5] = n8;
                ++n5;
                if (n8 <= n7) {}
            }
            if (n5 != 0) {
                final int[] array2 = new int[n5];
                try {
                    System.arraycopy(array, 0, array2, 0, n5);
                }
                catch (Exception ex) {
                    System.out.println("LineWrapArraycopy: " + ex);
                }
                return array2;
            }
        }
        return new int[] { -1 };
    }
    
    public d(final ChatRoomApplet a) {
        super(14, 50);
        this.b = "";
        this.a = a;
        this.setFont(ChatRoomApplet.FTEXTFONT);
    }
    
    private static String[] a(final String s, final int[] array, final int n) {
        int n2 = 0;
        int n3 = 0;
        final String[] array2 = new String[n];
        while (n2 < n && array[n2] >= n3 && array[n2] != -1) {
            int n4 = array[n2];
            if (s.substring(n4 - 1, n4).equals("\n")) {
                --n4;
            }
            array2[n2] = s.substring(n3, n4);
            n3 = array[n2];
            ++n2;
        }
        return array2;
    }
    
    public static String[] b(final Rectangle rectangle, final FontMetrics fontMetrics, final String s) {
        final int[] a = a(rectangle, fontMetrics, s);
        if (a != null) {
            return a(s, a, a.length);
        }
        return null;
    }
    
    static {
        d.c = "    ";
    }
    
    private void a() {
        if (this.getSelectionEnd() > 2000) {
            final String text = this.getText();
            final int n = text.indexOf(10, 1000) + 1;
            if (n != 0) {
                this.setText(text.substring(n));
            }
        }
    }
    
    public void a(final String b) {
        this.a();
        final Rectangle bounds = this.a.bounds();
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
        final String[] b2 = b(new Rectangle(0, 0, bounds.width - 6 - 12 - fontMetrics.stringWidth(d.c), bounds.height - 92), fontMetrics, b);
        for (int i = 0; i < b2.length; ++i) {
            String string = b2[i];
            if (string != null) {
                if (i != 0) {
                    string = d.c + string;
                }
                this.appendText(string + "\n");
            }
        }
        this.b = b;
    }
}
