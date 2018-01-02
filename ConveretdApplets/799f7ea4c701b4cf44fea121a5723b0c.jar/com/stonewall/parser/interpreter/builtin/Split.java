// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser.interpreter.builtin;

import com.stonewall.parser.Tuple;
import java.util.regex.Pattern;
import com.stonewall.parser.Function;

class Split implements Function
{
    static Pattern p;
    
    static {
        Split.p = Pattern.compile("\\W");
    }
    
    @Override
    public String execute(final Context ctx, final String[] args) {
        return (args.length > 2) ? this.split(args[1], args[2]) : this.split(args[1]);
    }
    
    String split(final String s) {
        return (s == null || s.length() == 0) ? "" : Tuple.join(Split.p.split(s));
    }
    
    String split(final String s, final String regex) {
        return (s == null || s.length() == 0) ? "" : Tuple.join(s.split(regex));
    }
}
