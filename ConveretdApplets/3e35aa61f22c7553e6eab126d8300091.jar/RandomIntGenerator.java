// 
// Decompiled by Procyon v0.5.30
// 

public class RandomIntGenerator
{
    private static final int BUFFER_SIZE = 101;
    private static double[] buffer;
    private int low;
    private int high;
    
    public RandomIntGenerator(final int low, final int high) {
        this.low = low;
        this.high = high;
    }
    
    public int draw() {
        int high = this.low + (int)((this.high - this.low + 1) * nextRandom());
        if (high > this.high) {
            high = this.high;
        }
        return high;
    }
    
    public static void main(final String[] array) {
        final RandomIntGenerator randomIntGenerator = new RandomIntGenerator(1, 10);
        final RandomIntGenerator randomIntGenerator2 = new RandomIntGenerator(0, 1);
        for (int i = 1; i <= 100; ++i) {
            System.out.println(String.valueOf(randomIntGenerator.draw()) + " " + randomIntGenerator2.draw());
        }
    }
    
    private static double nextRandom() {
        int n = (int)(Math.random() * 101.0);
        if (n == 101) {
            n = 100;
        }
        final double n2 = RandomIntGenerator.buffer[n];
        RandomIntGenerator.buffer[n] = Math.random();
        return n2;
    }
    
    static {
        RandomIntGenerator.buffer = new double[101];
        for (int i = 0; i < 101; ++i) {
            RandomIntGenerator.buffer[i] = Math.random();
        }
    }
}
