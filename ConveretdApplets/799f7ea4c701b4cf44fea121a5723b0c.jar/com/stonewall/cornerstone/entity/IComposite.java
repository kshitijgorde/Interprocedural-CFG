// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity;

import java.util.List;

public interface IComposite
{
    List<EntityReference> getParts();
    
    void addPart(final EntityReference p0);
}
