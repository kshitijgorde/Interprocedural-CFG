// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.util.ByteList;
import org.jruby.lexer.yacc.ISourcePosition;

public class FileNode extends StrNode
{
    public FileNode(final ISourcePosition position, final ByteList value) {
        super(position, value);
    }
}
