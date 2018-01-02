import java.io.PrintStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class Distribution
{
    static PrintStream p;
    public static final int DIST_LENGTH = 101;
    private static int[] base_values;
    private static int[] alt_base_values;
    private static int[] pop_dist;
    private static int[] alt_dist;
    private static double[] pop_z;
    private static int[] pop_dist_samp;
    private static int[] alt_dist_samp;
    private static double[] alt_z;
    static double mu;
    static double alt_mu;
    static double sigma;
    static double alpha;
    static double d;
    static final double z_start = -4.0;
    static double offset;
    static final double INTERVAL = 0.08;
    static int base;
    static double scale;
    
    static {
        Distribution.p = System.out;
        Distribution.base_values = new int[101];
        Distribution.alt_base_values = new int[101];
        Distribution.pop_dist = new int[101];
        Distribution.alt_dist = new int[101];
        Distribution.pop_z = new double[101];
        Distribution.pop_dist_samp = new int[101];
        Distribution.alt_dist_samp = new int[101];
        Distribution.alt_z = new double[101];
        Distribution.mu = 555.0;
        Distribution.alt_mu = 585.0;
        Distribution.sigma = 139.0;
        Distribution.alpha = 0.05;
        Distribution.d = 0.0;
        Distribution.base = DistPanel.get_y_base();
        Distribution.scale = 100.0;
    }
    
    Distribution() {
        Distribution.offset = calc_z(Distribution.alt_mu, Distribution.mu, Distribution.sigma);
        set_pop(-4.0, Distribution.pop_dist, 0.0);
        set_pop_samp(-4.0, Distribution.pop_z, Distribution.pop_dist_samp, 0.0);
        set_pop(-4.0, Distribution.alt_dist, Distribution.offset);
        set_pop_samp(-4.0, Distribution.alt_z, Distribution.alt_dist_samp, Distribution.offset);
        this.set_base_values(DistPanel.get_x_base(), Distribution.base_values);
        this.set_base_values(DistPanel.get_x_base(), Distribution.alt_base_values);
    }
    
    public static double z_to_xi(final double n) {
        return n * Distribution.sigma + Distribution.mu;
    }
    
    public static double calc_z(final double n, final double n2, final double n3, final int n4) {
        return (n - n2) / n3 / Math.sqrt(n4);
    }
    
    public static double calc_z(final double n, final double n2, final double n3) {
        return (n - n2) / n3;
    }
    
    public static double calc_d() {
        return (Distribution.alt_mu - Distribution.mu) / Distribution.sigma;
    }
    
    public static double normPDF(final double n, final double n2, double n3, final int n4) {
        n3 /= Math.sqrt(n4);
        final double n5 = 1.0 / (Math.sqrt(6.283185307179586) * n3);
        final double n6 = (n - n2) / n3;
        return n5 * Math.exp(n6 * n6 * -0.5);
    }
    
    public static double normCDF(final double n) {
        double n2;
        if (n < 0.0) {
            n2 = 0.5 * Math.exp(-1.0 * ((83.0 * n + 351.0) * n + 562.0) / (703.0 / n + 165.0));
        }
        else {
            n2 = 1.0 - 0.5 * Math.exp(-1.0 * ((83.0 * n + 351.0) * n + 562.0) / (703.0 / n + 165.0));
        }
        return n2;
    }
    
    public double get_onetail_p(final double n) {
        if (n > 0.0) {
            return 1.0 - normCDF(n);
        }
        return 1.0 - normCDF(-n);
    }
    
    public double get_twotail_p(final double n) {
        return 2.0 * this.get_onetail_p(n);
    }
    
    public static void set_pop_samp(final double n, final double[] array, final int[] array2, final double n2) {
        double n3 = n - n2;
        for (int i = 0; i < array2.length; ++i) {
            array[i] = n3;
            array2[i] = Distribution.base + 1 - (int)Math.floor(Distribution.scale * normPDF(z_to_xi(n3), Distribution.mu, Distribution.sigma, Values.n));
            n3 += 0.08;
        }
        array2[0] = Distribution.base + 1;
        array2[array2.length - 1] = Distribution.base + 1;
    }
    
    private static void set_pop(final double n, final int[] array, final double n2) {
        double n3 = n - n2;
        final int get_scale = get_scale(1, Distribution.sigma);
        for (int i = 0; i < array.length; ++i) {
            Distribution.pop_z[i] = n3;
            array[i] = 1 + (int)Math.floor(DistPanel.pop_scale - get_scale * normPDF(z_to_xi(n3), Distribution.mu, Distribution.sigma, 1));
            n3 += 0.08;
        }
        array[0] = DistPanel.get_y_pop_base();
        array[array.length - 1] = DistPanel.get_y_pop_base();
    }
    
    private void set_base_values(final int n, final int[] array) {
        for (int i = 0; i < 101; ++i) {
            array[i] = n + DistPanel.x_scale * i;
        }
    }
    
    public void fill_means_arr(final Values values) {
        int n = 0;
        while (Distribution.alt_z[n] < calc_z(Values.obt_mean, Distribution.alt_mu, Distribution.sigma) && ++n != 100) {}
        final int[] means_arr = Values.means_arr;
        final int n2 = n;
        ++means_arr[n2];
    }
    
    public static void fill_cases(final Values values, final double n) {
        int n2 = 0;
        while (Distribution.alt_z[n2] < calc_z(n, Distribution.alt_mu, Distribution.sigma) && ++n2 != 100) {}
        final int[] cases_arr = Values.cases_arr;
        final int n3 = n2;
        ++cases_arr[n3];
    }
    
    public static void clear_means_arr(final Values values) {
        for (int i = 0; i < Values.means_arr.length; ++i) {
            Values.means_arr[i] = 0;
        }
    }
    
    public static void set_alt_dist(final double n) {
        Distribution.alt_mu = z_to_xi(n);
        Distribution.offset = calc_z(Distribution.alt_mu, Distribution.mu, Distribution.sigma);
    }
    
    public static void parameter_change() {
        Distribution.offset = calc_z(Distribution.alt_mu, Distribution.mu, Distribution.sigma);
        if ((int)(Distribution.scale * normPDF(z_to_xi(0.0), Distribution.mu, Distribution.sigma, Values.n)) > 4.0 * DistPanel.y_temp || (int)(Distribution.scale * normPDF(z_to_xi(0.0), Distribution.mu, Distribution.sigma, Values.n)) < DistPanel.y_temp) {
            Distribution.scale = get_scale(Values.n, Distribution.sigma);
            Graph_Labels.y_labels_pop(DistPanel.y_pop_lab, 1);
            Graph_Labels.y_labels_pop(DistPanel.y_alt_lab, Values.n);
        }
        set_pop(-4.0, Distribution.pop_dist, 0.0);
        set_pop_samp(-4.0, Distribution.pop_z, Distribution.pop_dist_samp, 0.0);
        set_pop(-4.0, Distribution.alt_dist, Distribution.offset);
        set_pop_samp(-4.0, Distribution.alt_z, Distribution.alt_dist_samp, Distribution.offset);
        Values.d = calc_z(Distribution.alt_mu, Distribution.mu, Distribution.sigma);
    }
    
    public static void full_parameter_change() {
        parameter_change();
    }
    
    public static void n_size_change(final int n) {
        set_pop_samp(-4.0, Distribution.alt_z, Distribution.pop_dist_samp, 0.0);
        set_pop_samp(-4.0, Distribution.alt_z, Distribution.alt_dist_samp, Distribution.offset);
        parameter_change();
    }
    
    public static void reset_samp() {
        set_pop_samp(-4.0, Distribution.pop_z, Distribution.pop_dist_samp, 0.0);
        set_pop_samp(-4.0, Distribution.alt_z, Distribution.alt_dist_samp, Distribution.offset);
    }
    
    public static int get_scale(final int n, final double n2) {
        int n3;
        int n4;
        for (n3 = 0, n4 = 1; n3 < DistPanel.y_temp; n3 = (int)(n4 * normPDF(z_to_xi(0.0), Distribution.mu, n2, n))) {
            n4 += 10;
        }
        return n4;
    }
    
    public static int[] get_y_base_val() {
        return Distribution.base_values;
    }
    
    public static int[] get_alt_y_base_val() {
        return Distribution.alt_base_values;
    }
    
    public static int[] get_pop_dist() {
        return Distribution.pop_dist;
    }
    
    public static int[] get_pop_dist_samp() {
        return Distribution.pop_dist_samp;
    }
    
    public static double get_pop_z(final int n) {
        return Distribution.pop_z[n];
    }
    
    public static double get_alt_z(final int n) {
        return Distribution.alt_z[n];
    }
    
    public static int[] get_alt_dist() {
        return Distribution.alt_dist;
    }
    
    public static int[] get_alt_dist_samp() {
        return Distribution.alt_dist_samp;
    }
    
    public static double get_mu() {
        return Distribution.mu;
    }
    
    public static double get_alt_mean() {
        return Distribution.alt_mu;
    }
    
    public static double get_sigma() {
        return Distribution.sigma;
    }
    
    public static double get_max_pop_ht(final int n) {
        return normPDF(Distribution.mu, Distribution.mu, Distribution.sigma, n);
    }
}
