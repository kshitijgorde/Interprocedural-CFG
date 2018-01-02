// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.Type;

final class NamespaceAlias extends TopLevelElement
{
    private String sPrefix;
    private String rPrefix;
    
    public void parseContents(final Parser parser) {
        this.sPrefix = this.getAttribute("stylesheet-prefix");
        this.rPrefix = this.getAttribute("result-prefix");
        parser.getSymbolTable().addPrefixAlias(this.sPrefix, this.rPrefix);
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        return Type.Void;
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
    }
}
