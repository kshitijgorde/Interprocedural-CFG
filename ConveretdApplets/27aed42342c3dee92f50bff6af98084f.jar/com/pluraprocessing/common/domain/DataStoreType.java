// 
// Decompiled by Procyon v0.5.30
// 

package com.pluraprocessing.common.domain;

public enum DataStoreType
{
    MYSQL("MYSQL", 0), 
    CACHE("CACHE", 1), 
    JOBCACHE("JOBCACHE", 2);
    
    private DataStoreType(final String s, final int n) {
    }
}
