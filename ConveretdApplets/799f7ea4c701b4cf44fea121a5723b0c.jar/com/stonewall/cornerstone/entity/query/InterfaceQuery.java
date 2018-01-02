// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.query;

import com.stonewall.cornerstone.tp.query.GraphQuery;

public class InterfaceQuery
{
    public boolean exists(final String id) {
        final GraphQuery query = new GraphQuery();
        return query.endpointExists(id);
    }
}
