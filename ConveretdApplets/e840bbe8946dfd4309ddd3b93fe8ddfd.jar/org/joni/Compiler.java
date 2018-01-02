// 
// Decompiled by Procyon v0.5.30
// 

package org.joni;

import org.joni.exception.InternalException;
import org.joni.exception.SyntaxException;
import org.joni.ast.Node;
import org.joni.ast.AnchorNode;
import org.joni.ast.EncloseNode;
import org.joni.ast.QuantifierNode;
import org.joni.ast.BackRefNode;
import org.joni.ast.CallNode;
import org.joni.ast.CTypeNode;
import org.joni.ast.CClassNode;
import org.joni.ast.StringNode;
import org.joni.ast.ConsAltNode;
import org.jcodings.Encoding;
import org.joni.exception.ErrorMessages;

abstract class Compiler implements ErrorMessages
{
    protected final Analyser analyser;
    protected final Encoding enc;
    protected final Regex regex;
    
    protected Compiler(final Analyser analyser) {
        this.analyser = analyser;
        this.regex = analyser.regex;
        this.enc = this.regex.enc;
    }
    
    final void compile() {
        this.prepare();
        this.compileTree(this.analyser.root);
        this.finish();
    }
    
    protected abstract void prepare();
    
    protected abstract void finish();
    
    protected abstract void compileAltNode(final ConsAltNode p0);
    
    private void compileStringRawNode(final StringNode sn) {
        if (sn.length() <= 0) {
            return;
        }
        this.addCompileString(sn.bytes, sn.p, 1, sn.length(), false);
    }
    
    private void compileStringNode(final StringNode node) {
        if (node.length() <= 0) {
            return;
        }
        final boolean ambig = node.isAmbig();
        int p;
        int prev = p = node.p;
        final int end = node.end;
        final byte[] bytes = node.bytes;
        int prevLen = this.enc.length(bytes, p, end);
        p += prevLen;
        int slen = 1;
        while (p < end) {
            final int len = this.enc.length(bytes, p, end);
            if (len == prevLen) {
                ++slen;
            }
            else {
                this.addCompileString(bytes, prev, prevLen, slen, ambig);
                prev = p;
                slen = 1;
                prevLen = len;
            }
            p += len;
        }
        this.addCompileString(bytes, prev, prevLen, slen, ambig);
    }
    
    protected abstract void addCompileString(final byte[] p0, final int p1, final int p2, final int p3, final boolean p4);
    
    protected abstract void compileCClassNode(final CClassNode p0);
    
    protected abstract void compileCTypeNode(final CTypeNode p0);
    
    protected abstract void compileAnyCharNode();
    
    protected abstract void compileCallNode(final CallNode p0);
    
    protected abstract void compileBackrefNode(final BackRefNode p0);
    
    protected abstract void compileCECQuantifierNode(final QuantifierNode p0);
    
    protected abstract void compileNonCECQuantifierNode(final QuantifierNode p0);
    
    protected abstract void compileOptionNode(final EncloseNode p0);
    
    protected abstract void compileEncloseNode(final EncloseNode p0);
    
    protected abstract void compileAnchorNode(final AnchorNode p0);
    
    protected final void compileTree(final Node node) {
        switch (node.getType()) {
            case 8: {
                ConsAltNode lin = (ConsAltNode)node;
                do {
                    this.compileTree(lin.car);
                } while ((lin = lin.cdr) != null);
                break;
            }
            case 9: {
                this.compileAltNode((ConsAltNode)node);
                break;
            }
            case 0: {
                final StringNode sn = (StringNode)node;
                if (sn.isRaw()) {
                    this.compileStringRawNode(sn);
                    break;
                }
                this.compileStringNode(sn);
                break;
            }
            case 1: {
                this.compileCClassNode((CClassNode)node);
                break;
            }
            case 2: {
                this.compileCTypeNode((CTypeNode)node);
                break;
            }
            case 3: {
                this.compileAnyCharNode();
                break;
            }
            case 4: {
                this.compileBackrefNode((BackRefNode)node);
                break;
            }
            case 10: {
                this.compileCallNode((CallNode)node);
                break;
            }
            case 5: {
                this.compileNonCECQuantifierNode((QuantifierNode)node);
                break;
            }
            case 6: {
                final EncloseNode enode = (EncloseNode)node;
                if (enode.isOption()) {
                    this.compileOptionNode(enode);
                    break;
                }
                this.compileEncloseNode(enode);
                break;
            }
            case 7: {
                this.compileAnchorNode((AnchorNode)node);
                break;
            }
            default: {
                this.newInternalException("internal parser error (bug)");
                break;
            }
        }
    }
    
    protected final void compileTreeNTimes(final Node node, final int n) {
        for (int i = 0; i < n; ++i) {
            this.compileTree(node);
        }
    }
    
    protected void newSyntaxException(final String message) {
        throw new SyntaxException(message);
    }
    
    protected void newInternalException(final String message) {
        throw new InternalException(message);
    }
}
