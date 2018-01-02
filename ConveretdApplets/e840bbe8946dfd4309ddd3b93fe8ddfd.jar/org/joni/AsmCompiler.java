// 
// Decompiled by Procyon v0.5.30
// 

package org.joni;

import org.joni.ast.AnchorNode;
import org.joni.ast.EncloseNode;
import org.joni.ast.QuantifierNode;
import org.joni.ast.CallNode;
import org.joni.ast.BackRefNode;
import org.joni.ast.CTypeNode;
import org.joni.ast.CClassNode;
import org.joni.ast.ConsAltNode;

final class AsmCompiler extends AsmCompilerSupport
{
    public AsmCompiler(final Analyser analyser) {
        super(analyser);
    }
    
    protected void prepare() {
        ++AsmCompiler.REG_NUM;
        this.prepareMachine();
        this.prepareMachineInit();
        this.prepareMachineMatch();
        this.prepareFactory();
        this.prepareFactoryInit();
    }
    
    protected void finish() {
        this.setupFactoryInit();
        this.setupMachineInit();
        this.setupMachineMatch();
        this.setupClasses();
    }
    
    protected void compileAltNode(final ConsAltNode node) {
    }
    
    protected void addCompileString(final byte[] bytes, final int p, final int mbLength, final int strLength, final boolean ignoreCase) {
        final String template = this.installTemplate(bytes, p, strLength);
    }
    
    protected void compileCClassNode(final CClassNode node) {
        if (node.bs != null) {
            final String bitsetName = this.installBitSet(node.bs.bits);
        }
    }
    
    protected void compileCTypeNode(final CTypeNode node) {
    }
    
    protected void compileAnyCharNode() {
    }
    
    protected void compileBackrefNode(final BackRefNode node) {
    }
    
    protected void compileCallNode(final CallNode node) {
    }
    
    protected void compileCECQuantifierNode(final QuantifierNode node) {
    }
    
    protected void compileNonCECQuantifierNode(final QuantifierNode node) {
    }
    
    protected void compileOptionNode(final EncloseNode node) {
    }
    
    protected void compileEncloseNode(final EncloseNode node) {
    }
    
    protected void compileAnchorNode(final AnchorNode node) {
    }
}
