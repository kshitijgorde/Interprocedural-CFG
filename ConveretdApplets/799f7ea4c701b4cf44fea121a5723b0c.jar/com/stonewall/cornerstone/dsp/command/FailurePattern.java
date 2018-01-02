// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FailurePattern
{
    private String regx;
    
    public FailurePattern(final String regx) {
        this.regx = regx;
    }
    
    public boolean match(final String s) {
        final Pattern p = Pattern.compile(this.regx);
        final Matcher m = p.matcher(s);
        return m.find();
    }
}
