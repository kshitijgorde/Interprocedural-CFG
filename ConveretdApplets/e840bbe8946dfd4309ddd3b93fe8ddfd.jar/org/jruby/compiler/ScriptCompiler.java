// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler;

import org.jruby.parser.StaticScope;

public interface ScriptCompiler
{
    void startScript(final StaticScope p0);
    
    void endScript(final boolean p0, final boolean p1);
    
    BodyCompiler startMethod(final String p0, final String p1, final CompilerCallback p2, final StaticScope p3, final ASTInspector p4);
    
    BodyCompiler startRoot(final String p0, final String p1, final StaticScope p2, final ASTInspector p3);
    
    BodyCompiler startFileMethod(final CompilerCallback p0, final StaticScope p1, final ASTInspector p2);
}
