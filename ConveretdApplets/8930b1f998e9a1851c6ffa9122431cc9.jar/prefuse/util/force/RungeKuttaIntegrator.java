// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.force;

import java.util.Iterator;

public class RungeKuttaIntegrator implements Integrator
{
    public void integrate(final ForceSimulator forceSimulator, final long n) {
        final float speedLimit = forceSimulator.getSpeedLimit();
        final Iterator items = forceSimulator.getItems();
        while (items.hasNext()) {
            final ForceItem forceItem = items.next();
            final float n2 = n / forceItem.mass;
            final float[][] k = forceItem.k;
            final float[][] l = forceItem.l;
            forceItem.plocation[0] = forceItem.location[0];
            forceItem.plocation[1] = forceItem.location[1];
            k[0][0] = n * forceItem.velocity[0];
            k[0][1] = n * forceItem.velocity[1];
            l[0][0] = n2 * forceItem.force[0];
            l[0][1] = n2 * forceItem.force[1];
            final float[] location = forceItem.location;
            final int n3 = 0;
            location[n3] += 0.5f * k[0][0];
            final float[] location2 = forceItem.location;
            final int n4 = 1;
            location2[n4] += 0.5f * k[0][1];
        }
        forceSimulator.accumulate();
        final Iterator items2 = forceSimulator.getItems();
        while (items2.hasNext()) {
            final ForceItem forceItem2 = items2.next();
            final float n5 = n / forceItem2.mass;
            final float[][] i = forceItem2.k;
            final float[][] j = forceItem2.l;
            float n6 = forceItem2.velocity[0] + 0.5f * j[0][0];
            float n7 = forceItem2.velocity[1] + 0.5f * j[0][1];
            final float n8 = (float)Math.sqrt(n6 * n6 + n7 * n7);
            if (n8 > speedLimit) {
                n6 = speedLimit * n6 / n8;
                n7 = speedLimit * n7 / n8;
            }
            i[1][0] = n * n6;
            i[1][1] = n * n7;
            j[1][0] = n5 * forceItem2.force[0];
            j[1][1] = n5 * forceItem2.force[1];
            forceItem2.location[0] = forceItem2.plocation[0] + 0.5f * i[1][0];
            forceItem2.location[1] = forceItem2.plocation[1] + 0.5f * i[1][1];
        }
        forceSimulator.accumulate();
        final Iterator items3 = forceSimulator.getItems();
        while (items3.hasNext()) {
            final ForceItem forceItem3 = items3.next();
            final float n9 = n / forceItem3.mass;
            final float[][] m = forceItem3.k;
            final float[][] l2 = forceItem3.l;
            float n10 = forceItem3.velocity[0] + 0.5f * l2[1][0];
            float n11 = forceItem3.velocity[1] + 0.5f * l2[1][1];
            final float n12 = (float)Math.sqrt(n10 * n10 + n11 * n11);
            if (n12 > speedLimit) {
                n10 = speedLimit * n10 / n12;
                n11 = speedLimit * n11 / n12;
            }
            m[2][0] = n * n10;
            m[2][1] = n * n11;
            l2[2][0] = n9 * forceItem3.force[0];
            l2[2][1] = n9 * forceItem3.force[1];
            forceItem3.location[0] = forceItem3.plocation[0] + 0.5f * m[2][0];
            forceItem3.location[1] = forceItem3.plocation[1] + 0.5f * m[2][1];
        }
        forceSimulator.accumulate();
        final Iterator items4 = forceSimulator.getItems();
        while (items4.hasNext()) {
            final ForceItem forceItem4 = items4.next();
            final float n13 = n / forceItem4.mass;
            final float[][] k2 = forceItem4.k;
            final float[][] l3 = forceItem4.l;
            final float[] plocation = forceItem4.plocation;
            float n14 = forceItem4.velocity[0] + l3[2][0];
            float n15 = forceItem4.velocity[1] + l3[2][1];
            final float n16 = (float)Math.sqrt(n14 * n14 + n15 * n15);
            if (n16 > speedLimit) {
                n14 = speedLimit * n14 / n16;
                n15 = speedLimit * n15 / n16;
            }
            k2[3][0] = n * n14;
            k2[3][1] = n * n15;
            l3[3][0] = n13 * forceItem4.force[0];
            l3[3][1] = n13 * forceItem4.force[1];
            forceItem4.location[0] = plocation[0] + (k2[0][0] + k2[3][0]) / 6.0f + (k2[1][0] + k2[2][0]) / 3.0f;
            forceItem4.location[1] = plocation[1] + (k2[0][1] + k2[3][1]) / 6.0f + (k2[1][1] + k2[2][1]) / 3.0f;
            float n17 = (l3[0][0] + l3[3][0]) / 6.0f + (l3[1][0] + l3[2][0]) / 3.0f;
            float n18 = (l3[0][1] + l3[3][1]) / 6.0f + (l3[1][1] + l3[2][1]) / 3.0f;
            final float n19 = (float)Math.sqrt(n17 * n17 + n18 * n18);
            if (n19 > speedLimit) {
                n17 = speedLimit * n17 / n19;
                n18 = speedLimit * n18 / n19;
            }
            final float[] velocity = forceItem4.velocity;
            final int n20 = 0;
            velocity[n20] += n17;
            final float[] velocity2 = forceItem4.velocity;
            final int n21 = 1;
            velocity2[n21] += n18;
        }
    }
}
