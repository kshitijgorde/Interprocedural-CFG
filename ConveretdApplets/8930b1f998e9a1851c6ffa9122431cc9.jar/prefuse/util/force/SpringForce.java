// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.force;

public class SpringForce extends AbstractForce
{
    private static String[] pnames;
    public static final float DEFAULT_SPRING_COEFF = 1.0E-4f;
    public static final float DEFAULT_MAX_SPRING_COEFF = 0.001f;
    public static final float DEFAULT_MIN_SPRING_COEFF = 1.0E-5f;
    public static final float DEFAULT_SPRING_LENGTH = 50.0f;
    public static final float DEFAULT_MIN_SPRING_LENGTH = 0.0f;
    public static final float DEFAULT_MAX_SPRING_LENGTH = 200.0f;
    public static final int SPRING_COEFF = 0;
    public static final int SPRING_LENGTH = 1;
    
    public SpringForce(final float n, final float n2) {
        this.params = new float[] { n, n2 };
        this.minValues = new float[] { 1.0E-5f, 0.0f };
        this.maxValues = new float[] { 0.001f, 200.0f };
    }
    
    public SpringForce() {
        this(1.0E-4f, 50.0f);
    }
    
    public boolean isSpringForce() {
        return true;
    }
    
    protected String[] getParameterNames() {
        return SpringForce.pnames;
    }
    
    public void getForce(final Spring spring) {
        final ForceItem item1 = spring.item1;
        final ForceItem item2 = spring.item2;
        final float n = (spring.length < 0.0f) ? this.params[1] : spring.length;
        final float n2 = item1.location[0];
        final float n3 = item1.location[1];
        final float n4 = item2.location[0];
        final float n5 = item2.location[1];
        float n6 = n4 - n2;
        float n7 = n5 - n3;
        float n8 = (float)Math.sqrt(n6 * n6 + n7 * n7);
        if (n8 == 0.0) {
            n6 = ((float)Math.random() - 0.5f) / 50.0f;
            n7 = ((float)Math.random() - 0.5f) / 50.0f;
            n8 = (float)Math.sqrt(n6 * n6 + n7 * n7);
        }
        final float n9 = ((spring.coeff < 0.0f) ? this.params[0] : spring.coeff) * (n8 - n) / n8;
        final float[] force = item1.force;
        final int n10 = 0;
        force[n10] += n9 * n6;
        final float[] force2 = item1.force;
        final int n11 = 1;
        force2[n11] += n9 * n7;
        final float[] force3 = item2.force;
        final int n12 = 0;
        force3[n12] += -n9 * n6;
        final float[] force4 = item2.force;
        final int n13 = 1;
        force4[n13] += -n9 * n7;
    }
    
    static {
        SpringForce.pnames = new String[] { "SpringCoefficient", "DefaultSpringLength" };
    }
}
