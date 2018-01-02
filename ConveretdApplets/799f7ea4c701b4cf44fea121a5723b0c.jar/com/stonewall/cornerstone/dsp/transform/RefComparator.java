// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.transform;

import java.util.Iterator;
import org.xmodel.IModelObject;
import org.xmodel.Xlate;
import java.util.HashSet;
import java.util.Set;
import com.stonewall.cornerstone.entity.policy.ReferenceData;
import java.util.Comparator;

class RefComparator implements Comparator<ReferenceData.RefData>
{
    private Set<String> attributes;
    private Set<String> children;
    
    RefComparator() {
        this.attributes = new HashSet<String>();
        this.children = new HashSet<String>();
    }
    
    void compareAttribute(final String attribute) {
        this.attributes.add(attribute);
    }
    
    void compareChild(final String child) {
        this.children.add(child);
    }
    
    @Override
    public int compare(final ReferenceData.RefData o1, final ReferenceData.RefData o2) {
        final IModelObject ref1 = o1.getRoot();
        final IModelObject ref2 = o2.getRoot();
        for (final String s : this.attributes) {
            final String a1 = Xlate.get(ref1, s, "");
            final String a2 = Xlate.get(ref2, s, "");
            if (!a1.equals(a2)) {
                return -1;
            }
        }
        for (final String s : this.children) {
            final String s2 = Xlate.get(ref1.getFirstChild(s), "");
            final String s3 = Xlate.get(ref2.getFirstChild(s), (String)null);
            if (!s2.equals(s3)) {
                return -1;
            }
        }
        return 0;
    }
}
