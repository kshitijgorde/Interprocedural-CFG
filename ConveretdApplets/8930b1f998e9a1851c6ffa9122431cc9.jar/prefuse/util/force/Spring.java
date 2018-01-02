// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.force;

import java.util.ArrayList;

public class Spring
{
    private static SpringFactory s_factory;
    public ForceItem item1;
    public ForceItem item2;
    public float length;
    public float coeff;
    
    public static SpringFactory getFactory() {
        return Spring.s_factory;
    }
    
    public Spring(final ForceItem item1, final ForceItem item2, final float coeff, final float length) {
        this.item1 = item1;
        this.item2 = item2;
        this.coeff = coeff;
        this.length = length;
    }
    
    static {
        Spring.s_factory = new SpringFactory();
    }
    
    public static final class SpringFactory
    {
        private int maxSprings;
        private ArrayList springs;
        
        public SpringFactory() {
            this.maxSprings = 10000;
            this.springs = new ArrayList();
        }
        
        public Spring getSpring(final ForceItem item1, final ForceItem item2, final float coeff, final float length) {
            if (this.springs.size() > 0) {
                final Spring spring = this.springs.remove(this.springs.size() - 1);
                spring.item1 = item1;
                spring.item2 = item2;
                spring.coeff = coeff;
                spring.length = length;
                return spring;
            }
            return new Spring(item1, item2, coeff, length);
        }
        
        public void reclaim(final Spring spring) {
            spring.item1 = null;
            spring.item2 = null;
            if (this.springs.size() < this.maxSprings) {
                this.springs.add(spring);
            }
        }
    }
}
