import java.util.Random;
import java.io.PrintStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class Sampler
{
    static PrintStream p;
    Random rand;
    double xi;
    
    Sampler() {
        this.rand = new Random();
        Sampler.p = System.out;
    }
    
    public double drawSample(final Distribution distribution, final Values values) {
        Values.sum = 0.0;
        Values.sum_x2 = 0.0;
        Values.sum2_x = 0.0;
        for (int i = 0; i < Values.n; ++i) {
            Distribution.fill_cases(values, this.xi = Distribution.get_alt_mean() + this.rand.nextGaussian() * Distribution.get_sigma());
            Values.sum += this.xi;
            Values.sum_x2 += Math.pow(this.xi, 2.0);
        }
        Values.sum2_x = Math.pow(Values.sum, 2.0);
        Values.obt_mean = Values.sum / Values.n;
        Values.z = Distribution.calc_z(Values.obt_mean, Distribution.mu, Distribution.sigma / Math.sqrt(Values.n));
        if (Values.n > 1) {
            Values.s_dev = Math.sqrt((Values.sum_x2 - Values.sum2_x / Values.n) / (Values.n - 1));
        }
        else {
            Values.s_dev = 0.0;
        }
        distribution.fill_means_arr(values);
        return Values.obt_mean;
    }
}
