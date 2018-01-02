// 
// Decompiled by Procyon v0.5.30
// 

package ytdfriends.action;

import edu.berkeley.guir.prefuse.NodeItem;
import edu.berkeley.guir.prefuse.EdgeItem;
import edu.berkeley.guir.prefusex.force.DragForce;
import edu.berkeley.guir.prefusex.force.SpringForce;
import edu.berkeley.guir.prefusex.force.Force;
import edu.berkeley.guir.prefusex.force.NBodyForce;
import edu.berkeley.guir.prefusex.force.ForceSimulator;
import edu.berkeley.guir.prefusex.layout.ForceDirectedLayout;

public class FriendsLayout extends ForceDirectedLayout
{
    private float NO_TENSION;
    private float SINGLETON;
    private float ORBIT;
    private float EXTRACOMM;
    private float NORMAL;
    
    public FriendsLayout() {
        super(false, false);
        this.NO_TENSION = 0.0f;
        this.SINGLETON = 8.0E-5f;
        this.ORBIT = 1.0E-5f;
        this.EXTRACOMM = 1.0E-6f;
        this.NORMAL = 2.0E-5f;
        final ForceSimulator fsim = new ForceSimulator();
        fsim.addForce((Force)new NBodyForce(-3.9f, -1.0f, 0.9f));
        fsim.addForce((Force)new SpringForce(2.0E-5f, 150.0f));
        fsim.addForce((Force)new DragForce(-0.015f));
        this.setForceSimulator(fsim);
        this.setMaxTimeStep(25L);
    }
    
    protected float getSpringLength(final EdgeItem e) {
        final NodeItem n1 = (NodeItem)e.getFirstNode();
        final NodeItem n2 = (NodeItem)e.getSecondNode();
        final int minE = Math.min(n1.getEdgeCount(), n2.getEdgeCount());
        final double doi = Math.max(n1.getDOI(), n2.getDOI());
        final float len = this.lengthFunc(minE);
        return (minE == 1) ? 50.0f : ((doi == 0.0) ? 200.0f : len);
    }
    
    protected float lengthFunc(int numE) {
        numE = ((numE > 10) ? 10 : (numE - 1));
        return 50.0f + numE / 100.0f * 150.0f;
    }
    
    protected float jonoCoeffFunc(final EdgeItem e) {
        final String type = e.getAttribute("type");
        if (type.equals("R")) {
            return 1.0E-4f;
        }
        if (type.equals("PC")) {
            return 5.0E-5f;
        }
        if (type.equals("SF")) {
            return 1.0E-6f;
        }
        return 5.0E-6f;
    }
    
    protected float getSpringCoefficient(final EdgeItem e) {
        final NodeItem n1 = (NodeItem)e.getFirstNode();
        final NodeItem n2 = (NodeItem)e.getSecondNode();
        final int ec1 = n1.getEdgeCount();
        final int ec2 = n2.getEdgeCount();
        final int minE = Math.min(ec1, ec2);
        final double doi1 = n1.getDOI();
        final double doi2 = n2.getDOI();
        final double doi3 = Math.max(n1.getDOI(), n2.getDOI());
        if (doi1 == 0.0 && doi2 == 0.0) {
            return this.NO_TENSION;
        }
        if (minE == 1 && doi3 == 0.0) {
            return this.SINGLETON;
        }
        if (doi3 == 0.0) {
            final int ec3 = (doi1 == 0.0) ? ec2 : ec1;
            final float alpha = this.calcAlpha(ec3);
            return alpha * this.ORBIT;
        }
        final Boolean b = (Boolean)e.getVizAttribute("extraCommunity");
        final boolean v = b != null && b;
        final float alpha2 = this.calcAlpha(minE);
        return alpha2 * (v ? this.EXTRACOMM : this.NORMAL);
    }
    
    protected float getMassValue(final NodeItem n) {
        return (float)n.getSize();
    }
    
    private float calcAlpha(final int ec) {
        final float ainv = (float)Math.max(1.0, 0.5 * Math.log(ec));
        return 1.0f / ainv;
    }
}
