// 
// Decompiled by Procyon v0.5.30
// 

package com.guymcarthur.util;

import java.io.IOException;
import java.io.BufferedReader;
import java.util.Hashtable;

public class CachingWordList extends WordList
{
    private Hashtable cache;
    
    public int has(final String s) {
        if (this.cache.containsKey(s)) {
            return this.cache.get(s);
        }
        final Integer n = new Integer(super.has(s));
        this.cache.put(s, n);
        return n;
    }
    
    public CachingWordList(final BufferedReader bufferedReader) throws IOException {
        super(bufferedReader);
        this.cache = new Hashtable(500);
    }
}
