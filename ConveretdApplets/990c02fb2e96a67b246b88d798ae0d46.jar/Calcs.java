// 
// Decompiled by Procyon v0.5.30
// 

public class Calcs
{
    public static void main(final String[] array) {
        final Quaternion quaternion = new Quaternion("X", 1.0);
        Quaternion quaternion2 = new Quaternion();
        quaternion2.scalar = 0.0;
        quaternion2.vector = new double[] { 0.0, 1.0, 0.0 };
        for (int i = 1; i < 361; ++i) {
            final Quaternion mult = quaternion.mult(quaternion2).mult(quaternion.inv());
            mult.output();
            quaternion2 = mult;
        }
    }
}
