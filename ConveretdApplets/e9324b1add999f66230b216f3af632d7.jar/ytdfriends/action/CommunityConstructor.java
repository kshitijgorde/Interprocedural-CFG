// 
// Decompiled by Procyon v0.5.30
// 

package ytdfriends.action;

import edu.berkeley.guir.prefuse.FocusManager;
import edu.berkeley.guir.prefusex.community.CommunitySet;
import edu.berkeley.guir.prefuse.ItemRegistry;
import edu.berkeley.guir.prefuse.action.AbstractAction;

public class CommunityConstructor extends AbstractAction
{
    public static final int INIT = -1;
    public static final int CLEAR = -2;
    private FriendsBrowsingColorFunction m_color;
    private Object m_key;
    private int m_idx;
    
    public CommunityConstructor(final Object communityKey, final FriendsBrowsingColorFunction color) {
        this.m_color = null;
        this.m_idx = -1;
        this.m_key = communityKey;
        this.m_color = color;
    }
    
    public void setIndex(final int idx) {
        this.m_idx = idx;
    }
    
    public void run(final ItemRegistry registry, final double frac) {
        final FocusManager fman = registry.getFocusManager();
        final CommunitySet comm = (CommunitySet)fman.getFocusSet(this.m_key);
        if (this.m_idx == -1) {
            comm.init(registry);
            this.m_color.updateCommunityMap(comm);
        }
        else if (this.m_idx == -2) {
            comm.clear();
            this.m_color.updateCommunityMap(comm);
        }
        else {
            comm.reconstruct(this.m_idx);
        }
    }
}
