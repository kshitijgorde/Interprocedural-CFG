import java.awt.Font;
import java.awt.Menu;

// 
// Decompiled by Procyon v0.5.30
// 

public class bo
{
    static void a(final Menu menu, final Font font) {
        if (menu == null) {
            return;
        }
        menu.setFont(font);
        int n = 0;
        while (true) {
            Label_0030: {
                if (!bm.dX) {
                    break Label_0030;
                }
                menu.getItem(n).setFont(font);
                ++n;
            }
            if (n == menu.getItemCount()) {
                return;
            }
            continue;
        }
    }
}
