import java.awt.FontMetrics;
import java.awt.Graphics;
import edu.wise.utils.FormatUtils;
import java.io.PrintStream;

// 
// Decompiled by Procyon v0.5.30
// 

class Graph_Labels
{
    static int accuracy;
    static int spaces;
    static PrintStream p;
    
    static {
        Graph_Labels.accuracy = 12;
        Graph_Labels.spaces = 3;
        Graph_Labels.p = System.out;
    }
    
    public static double[] y_labels_pop(final double[] array, final int n) {
        final double get_max_pop_ht = Distribution.get_max_pop_ht(n);
        for (int i = 0; i < array.length; ++i) {
            array[i] = FormatUtils.rounder(get_max_pop_ht * i, 3);
        }
        return array;
    }
    
    private static double get_ht(final double n, final int n2) {
        return n;
    }
    
    public static void lower_x(final Graphics graphics, final String[] array, int n, final int n2, final FontMetrics fontMetrics) {
        for (int i = 0; i < array.length; ++i) {
            graphics.drawString(array[i], n - fontMetrics.stringWidth(array[i]) / 2, n2);
            n += 50;
        }
    }
}
