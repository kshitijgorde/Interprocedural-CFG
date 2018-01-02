// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser.interpreter.builtin;

import java.util.Set;
import com.stonewall.parser.Tuple;
import java.util.HashSet;
import com.stonewall.parser.Function;

class Contained implements Function
{
    @Override
    public String execute(final Context ctx, final String[] args) {
        final String text = args[1];
        final Set<String> set = new HashSet<String>();
        String[] split;
        for (int length = (split = Tuple.split(text)).length, j = 0; j < length; ++j) {
            final String s = split[j];
            set.add(s);
        }
        for (int i = 2; i < args.length; ++i) {
            String[] split2;
            for (int length2 = (split2 = Tuple.split(args[i])).length, k = 0; k < length2; ++k) {
                String arg = split2[k];
                arg = arg.trim();
                if (set.contains(arg)) {
                    return arg;
                }
            }
        }
        return "";
    }
}
