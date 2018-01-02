// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.security;

import java.util.Iterator;
import java.util.Collection;
import com.stonewall.cornerstone.entity.Site;
import java.util.ArrayList;
import org.xmodel.IModelObject;
import com.stonewall.cornerstone.db.DbException;
import java.util.Map;
import com.stonewall.cornerstone.entity.SiteTree;
import java.util.List;

public class BranchMutex extends Mutex
{
    private List<Mutex> mutexes;
    private SiteTree siteTree;
    
    public BranchMutex(final FeatureAccess fa, final String targetId, final Map<String, Object> data) {
        super(fa, targetId);
        this.siteTree = null;
        try {
            this.siteTree = data.get("siteTree");
            if (this.siteTree == null) {
                data.put("siteTree", this.siteTree = new SiteTree());
            }
        }
        catch (DbException dbe) {
            BranchMutex.log.error(this, dbe);
        }
    }
    
    public BranchMutex(final IModelObject root) {
        super(root);
        this.siteTree = null;
    }
    
    @Override
    protected List<Mutex> lock(final Map<Mutex, Integer> locks) {
        final List<Mutex> locked = new ArrayList<Mutex>();
        this.mutexes = new ArrayList<Mutex>();
        final SiteTree sparseTree = this.siteTree.createTreeForSite(this.targetId);
        if (sparseTree == null) {
            return locked;
        }
        for (final Site s : sparseTree.getBranchNodes()) {
            final Mutex m = new Mutex(this.fa, s.getId());
            this.mutexes.add(m);
            locked.addAll(m.lock(locks));
        }
        return locked;
    }
    
    @Override
    protected List<Mutex> unlock(final Map<Mutex, Integer> locks) {
        final List<Mutex> unlocked = new ArrayList<Mutex>();
        for (final Mutex m : this.mutexes) {
            unlocked.addAll(m.unlock(locks));
        }
        return unlocked;
    }
}
