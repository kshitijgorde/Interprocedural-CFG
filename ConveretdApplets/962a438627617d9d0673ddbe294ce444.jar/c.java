import java.util.Random;

// 
// Decompiled by Procyon v0.5.30
// 

public class c extends f
{
    public String getName() {
        return "Dissolve Plugin";
    }
    
    public void transform(final int[] array, final int[] array2, final int n, final int n2, final int n3, final int n4, final int[] array3) {
        final Random random = new Random(83271L);
        for (int i = 0; i < n2; ++i) {
            final int n5 = i * n;
            for (int j = 0; j < n; ++j) {
                final int n6 = n5 + j;
                int nextInt = random.nextInt();
                if (nextInt < 0) {
                    nextInt *= -1;
                }
                if (n3 >= nextInt % n4) {
                    array3[n6] = array[n6];
                }
                else {
                    array3[n6] = array2[n6];
                }
            }
        }
    }
}
