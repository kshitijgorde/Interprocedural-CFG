// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser;

import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.Map;

public class PatternStore
{
    private Map<String, Pattern> patterns;
    
    public PatternStore() {
        this.patterns = new HashMap<String, Pattern>();
    }
    
    public Pattern get(final String regex) {
        Pattern p = this.patterns.get(regex);
        if (p == null) {
            p = this.compile(regex);
            this.patterns.put(regex, p);
        }
        return p;
    }
    
    private Pattern compile(final String regex) {
        return Boolean.getBoolean("org.parser.regex.matchcase") ? Pattern.compile(regex) : Pattern.compile(regex, 2);
    }
}
