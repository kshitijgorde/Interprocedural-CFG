// 
// Decompiled by Procyon v0.5.30
// 

package ji.v1base;

import java.awt.MenuComponent;
import java.awt.PopupMenu;

public class c5 extends PopupMenu
{
    public c5(final String s) {
        super(s);
    }
    
    public static final void a(final c5 c5) {
        try {
            if (c5 != null) {
                int n = c5.getItemCount();
                if (n > 0) {
                    try {
                        final String label = c5.getItem(0).getLabel();
                        if (label != null && label.length() <= 1) {
                            c5.remove(c5.getItem(0));
                            n = c5.getItemCount();
                        }
                    }
                    catch (Exception ex) {}
                    try {
                        final String label2 = c5.getItem(n - 1).getLabel();
                        if (label2 != null && label2.length() <= 1) {
                            c5.remove(c5.getItem(n - 1));
                        }
                    }
                    catch (Exception ex2) {}
                    try {
                        for (int i = 1; i != 0; i = 1) {
                            i = 0;
                            final int itemCount = c5.getItemCount();
                            int n2 = 0;
                            for (int j = 0; j < itemCount; ++j) {
                                final String label3 = c5.getItem(j).getLabel();
                                if (label3 != null) {
                                    if (label3.length() <= 1) {
                                        if (n2 != 0) {
                                            c5.remove(c5.getItem(j));
                                            break;
                                        }
                                        n2 = 1;
                                    }
                                    else {
                                        n2 = 0;
                                    }
                                }
                            }
                        }
                    }
                    catch (Exception ex3) {}
                }
            }
        }
        catch (Exception ex4) {}
    }
}
