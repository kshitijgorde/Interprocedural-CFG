// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.force;

import java.util.Iterator;

public class EulerIntegrator implements Integrator
{
    public void integrate(final ForceSimulator forceSimulator, final long n) {
        final float speedLimit = forceSimulator.getSpeedLimit();
        final Iterator items = forceSimulator.getItems();
        while (items.hasNext()) {
            final ForceItem forceItem = items.next();
            final float[] location = forceItem.location;
            final int n2 = 0;
            location[n2] += n * forceItem.velocity[0];
            final float[] location2 = forceItem.location;
            final int n3 = 1;
            location2[n3] += n * forceItem.velocity[1];
            final float n4 = n / forceItem.mass;
            final float[] velocity = forceItem.velocity;
            final int n5 = 0;
            velocity[n5] += n4 * forceItem.force[0];
            final float[] velocity2 = forceItem.velocity;
            final int n6 = 1;
            velocity2[n6] += n4 * forceItem.force[1];
            final float n7 = forceItem.velocity[0];
            final float n8 = forceItem.velocity[1];
            final float n9 = (float)Math.sqrt(n7 * n7 + n8 * n8);
            if (n9 > speedLimit) {
                forceItem.velocity[0] = speedLimit * n7 / n9;
                forceItem.velocity[1] = speedLimit * n8 / n9;
            }
        }
    }
}
