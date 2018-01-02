// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.parser;

import java.util.ArrayList;
import org.jruby.ast.PreExeNode;
import org.jruby.runtime.DynamicScope;
import org.jruby.ast.Node;
import java.util.List;

public class RubyParserResult
{
    public static final List<Node> EMPTY_BEGIN_LIST;
    private List<Node> beginNodes;
    private Node ast;
    private int endOffset;
    private DynamicScope scope;
    
    public RubyParserResult() {
        this.endOffset = -1;
    }
    
    public Node getAST() {
        return this.ast;
    }
    
    public DynamicScope getScope() {
        return this.scope;
    }
    
    public void setScope(final DynamicScope scope) {
        this.scope = scope;
    }
    
    public void setAST(final Node ast) {
        this.ast = ast;
    }
    
    public void addBeginNode(final PreExeNode node) {
        if (this.beginNodes == null) {
            this.beginNodes = new ArrayList<Node>();
        }
        this.beginNodes.add(node);
    }
    
    public List<Node> getBeginNodes() {
        return (this.beginNodes == null) ? RubyParserResult.EMPTY_BEGIN_LIST : this.beginNodes;
    }
    
    public int getEndOffset() {
        return this.endOffset;
    }
    
    public void setEndOffset(final int endOffset) {
        this.endOffset = endOffset;
    }
    
    static {
        EMPTY_BEGIN_LIST = new ArrayList<Node>();
    }
}
