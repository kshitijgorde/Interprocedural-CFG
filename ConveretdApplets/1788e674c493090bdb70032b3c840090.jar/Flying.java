import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class Flying
{
    public int zLevel;
    public boolean shown;
    
    public static Asteroid[] sort(final Asteroid[] array) {
        int i = 0;
        final boolean[] array2 = new boolean[array.length];
        final Asteroid[] array3 = new Asteroid[array.length];
        for (int j = 0; j < array3.length; ++j) {
            array2[j] = false;
            array3[j] = null;
            if (array[j] == null) {
                return null;
            }
        }
        int n = 0;
        while (i < array3.length) {
            if (n >= 1000) {
                break;
            }
            for (int k = 0; k < array.length; ++k) {
                if (!array2[k] && array[k].zLevel == n && array[k].shown) {
                    array3[i++] = array[k];
                    array2[k] = true;
                }
            }
            ++n;
        }
        while (i < array3.length) {
            for (int l = 0; l < array.length; ++l) {
                if (!array2[l]) {
                    array3[i++] = array[l];
                    array2[l] = true;
                }
            }
        }
        return array3;
    }
    
    public Flying() {
        this.zLevel = 0;
        this.shown = false;
    }
    
    public void paint(final Graphics graphics) {
    }
}
