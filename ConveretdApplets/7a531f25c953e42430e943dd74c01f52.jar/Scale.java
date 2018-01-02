// 
// Decompiled by Procyon v0.5.30
// 

public class Scale
{
    private static final int MAX_SCALE;
    
    static {
        MAX_SCALE = DistPanel.get_y_base() - (int)DistPanel.pop_scale - 20;
    }
    
    public static double get_scale(final int n, final double n2, final int[] array) {
        int i = 0;
        int n3 = 0;
        while (i <= 241) {
            Distribution.scale *= 2.0;
            ++n3;
            i = (int)(Distribution.scale * Distribution.normPDF(Distribution.z_to_xi(0.0), Distribution.mu, Distribution.sigma, Values.n));
        }
        System.out.print("[iters = " + n3 + "]: ");
        return i;
    }
}
