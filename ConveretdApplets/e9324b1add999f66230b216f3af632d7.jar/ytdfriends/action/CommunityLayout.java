// 
// Decompiled by Procyon v0.5.30
// 

package ytdfriends.action;

import java.awt.geom.Rectangle2D;
import edu.berkeley.guir.prefuse.NodeItem;
import java.util.Iterator;
import java.util.Set;
import edu.berkeley.guir.prefuse.AggregateItem;
import edu.berkeley.guir.prefuse.FocusManager;
import edu.berkeley.guir.prefuse.util.GeometryLib;
import edu.berkeley.guir.prefuse.graph.Entity;
import edu.berkeley.guir.prefuse.VisualItem;
import edu.berkeley.guir.prefuse.graph.Node;
import edu.berkeley.guir.prefusex.community.CommunitySet;
import edu.berkeley.guir.prefuse.ItemRegistry;
import edu.berkeley.guir.prefuse.action.AbstractAction;

public class CommunityLayout extends AbstractAction
{
    private Object m_key;
    private double[] m_pts;
    
    public CommunityLayout(final Object communityKey) {
        this.m_key = communityKey;
    }
    
    public void run(final ItemRegistry registry, final double frac) {
        final FocusManager fman = registry.getFocusManager();
        final CommunitySet comm = (CommunitySet)fman.getFocusSet(this.m_key);
        final int num = comm.getCommunityCount();
        if (num == 0) {
            return;
        }
        int maxsz = 0;
        for (int i = 0; i < num; ++i) {
            maxsz = Math.max(maxsz, 8 * comm.getCommunityMembers(i).size());
        }
        if (this.m_pts == null || maxsz > this.m_pts.length) {
            this.m_pts = new double[maxsz];
        }
        final int growth = 5;
        for (int j = 0; j < num; ++j) {
            AggregateItem aitem = null;
            int idx = 0;
            final Set set = comm.getCommunityMembers(j);
            Node node = null;
            final Iterator iter = set.iterator();
            while (iter.hasNext()) {
                node = iter.next();
                final NodeItem item = registry.getNodeItem(node);
                if (item != null) {
                    this.addPoint(this.m_pts, idx, (VisualItem)item, growth);
                    idx += 8;
                }
            }
            aitem = registry.getAggregateItem((Entity)node);
            if (idx != 0) {
                final double[] nhull = GeometryLib.convexHull(this.m_pts, idx);
                float[] fhull = (float[])aitem.getVizAttribute("polygon");
                if (fhull == null || fhull.length < nhull.length) {
                    fhull = new float[nhull.length];
                }
                else if (fhull.length > nhull.length) {
                    fhull[nhull.length] = Float.NaN;
                }
                for (int k = 0; k < nhull.length; ++k) {
                    fhull[k] = (float)nhull[k];
                }
                aitem.setVizAttribute("polygon", (Object)fhull);
            }
        }
    }
    
    protected void addPoint(final double[] pts, final int idx, final VisualItem item, final int growth) {
        final Rectangle2D b = item.getBounds();
        final double minX = b.getMinX() - growth;
        final double minY = b.getMinY() - growth;
        final double maxX = b.getMaxX() + growth;
        final double maxY = b.getMaxY() + growth;
        pts[idx] = minX;
        pts[idx + 1] = minY;
        pts[idx + 2] = minX;
        pts[idx + 3] = maxY;
        pts[idx + 4] = maxX;
        pts[idx + 5] = minY;
        pts[idx + 6] = maxX;
        pts[idx + 7] = maxY;
    }
}
