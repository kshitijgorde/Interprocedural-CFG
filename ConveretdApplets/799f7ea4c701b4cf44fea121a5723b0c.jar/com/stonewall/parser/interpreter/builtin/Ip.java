// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser.interpreter.builtin;

import com.stonewall.parser.Tuple;
import com.stonewall.parser.Function;
import com.stonewall.parser.ClassFunction;

public class Ip extends ClassFunction
{
    public String bits(final Function.Context ctx, final String[] args) {
        return String.valueOf(new IpAddr(args[1]).netbits());
    }
    
    public String contains(final Function.Context ctx, final String[] args) {
        boolean matched = false;
        String[] split;
        for (int length = (split = Tuple.split(args[2])).length, i = 0; i < length; ++i) {
            final String B = split[i];
            matched = false;
            String[] split2;
            for (int length2 = (split2 = Tuple.split(args[1])).length, j = 0; j < length2; ++j) {
                final String A = split2[j];
                matched = this.contains(A, B);
                if (matched) {
                    break;
                }
            }
        }
        return Boolean.toString(matched);
    }
    
    boolean contains(final String A, final String B) {
        final String[] a = A.split("/");
        final String[] b = B.split("/");
        final IpAddr rhs = new IpAddr(b);
        if (a.length == 2) {
            final IpNet net = new IpNet(a);
            return net.contains(rhs);
        }
        final IpAddr addr = new IpAddr(a);
        return addr.contains(rhs);
    }
}
