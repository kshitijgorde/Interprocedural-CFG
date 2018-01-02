import edu.wise.utils.General;

// 
// Decompiled by Procyon v0.5.30
// 

public class Values
{
    static int n;
    static double obt_mean;
    static double s_dev;
    static double z;
    static double sum;
    static double sum_x2;
    static double sum2_x;
    static double d;
    public static int[] cases_arr;
    public static int[] means_arr;
    
    static {
        Values.n = 1;
        Values.cases_arr = new int[101];
        Values.means_arr = new int[101];
    }
    
    Values() {
        Values.obt_mean = 0.0;
        Values.s_dev = -9.0;
        Values.sum = 0.0;
        Values.z = 0.0;
        Values.sum_x2 = 0.0;
        Values.sum2_x = 0.0;
        Values.d = 0.0;
    }
    
    public static void dump_core() {
        Values.obt_mean = 0.0;
        Values.s_dev = 0.0;
        Values.sum = 0.0;
        Values.z = 0.0;
        Values.sum_x2 = 0.0;
        Values.sum2_x = 0.0;
        General.clear_cases(Values.cases_arr);
        General.clear_cases(Values.means_arr);
        DistPanel.draw_arrow = false;
    }
}
