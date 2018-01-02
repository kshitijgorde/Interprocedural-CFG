// 
// Decompiled by Procyon v0.5.30
// 

package org.joni;

import org.joni.ast.CClassNode;
import org.jcodings.Encoding;
import org.joni.ast.Node;
import org.joni.ast.ConsAltNode;
import org.joni.ast.StringNode;
import org.jcodings.ApplyAllCaseFoldFunction;

final class ApplyCaseFold implements ApplyAllCaseFoldFunction
{
    static final ApplyCaseFold INSTANCE;
    
    public void apply(final int from, final int[] to, final int length, final Object o) {
        final ApplyCaseFoldArg arg = (ApplyCaseFoldArg)o;
        final ScanEnvironment env = arg.env;
        final Encoding enc = env.enc;
        final CClassNode cc = arg.cc;
        final BitSet bs = cc.bs;
        if (length == 1) {
            final boolean inCC = cc.isCodeInCC(enc, from);
            if ((inCC && !cc.isNot()) || (!inCC && cc.isNot())) {
                if (enc.minLength() > 1 || to[0] >= 256) {
                    cc.addCodeRange(env, to[0], to[0]);
                }
                else {
                    bs.set(to[0]);
                }
            }
        }
        else if (cc.isCodeInCC(enc, from) && !cc.isNot()) {
            StringNode node = null;
            for (int i = 0; i < length; ++i) {
                if (i == 0) {
                    node = new StringNode();
                    node.ensure(7);
                    final StringNode stringNode = node;
                    stringNode.end += enc.codeToMbc(to[i], node.bytes, node.end);
                    node.setAmbig();
                }
                else {
                    node.ensure(7);
                    final StringNode stringNode2 = node;
                    stringNode2.end += enc.codeToMbc(to[i], node.bytes, node.end);
                }
            }
            final ConsAltNode alt = ConsAltNode.newAltNode(node, null);
            if (arg.tail == null) {
                arg.altRoot = alt;
            }
            else {
                arg.tail.setCdr(alt);
            }
            arg.tail = alt;
        }
    }
    
    static {
        INSTANCE = new ApplyCaseFold();
    }
}
