// 
// Decompiled by Procyon v0.5.30
// 

package ytdfriends.action;

import java.util.Iterator;
import edu.berkeley.guir.prefuse.FocusManager;
import edu.berkeley.guir.prefuse.graph.Node;
import edu.berkeley.guir.prefuse.NodeItem;
import edu.berkeley.guir.prefuse.EdgeItem;
import edu.berkeley.guir.prefusex.community.CommunitySet;
import edu.berkeley.guir.prefuse.ItemRegistry;
import edu.berkeley.guir.prefuse.action.AbstractAction;

public class CommunityEdgeLabeler extends AbstractAction
{
    private Object m_key;
    
    public CommunityEdgeLabeler(final Object communityKey) {
        this.m_key = communityKey;
    }
    
    public void run(final ItemRegistry registry, final double frac) {
        final FocusManager fman = registry.getFocusManager();
        final CommunitySet comm = (CommunitySet)fman.getFocusSet(this.m_key);
        if (comm.getCommunityCount() == 0) {
            return;
        }
        final Iterator iter = registry.getEdgeItems();
        while (iter.hasNext()) {
            final EdgeItem ei = iter.next();
            final NodeItem n1 = (NodeItem)ei.getFirstNode();
            final NodeItem n2 = (NodeItem)ei.getSecondNode();
            final int c1 = comm.getCommunity((Node)n1.getEntity());
            final int c2 = comm.getCommunity((Node)n2.getEntity());
            final boolean b = c1 != c2 && c1 != -1 && c2 != -1;
            final Boolean val = b ? Boolean.TRUE : Boolean.FALSE;
            ei.setVizAttribute("extraCommunity", (Object)val);
        }
    }
}
