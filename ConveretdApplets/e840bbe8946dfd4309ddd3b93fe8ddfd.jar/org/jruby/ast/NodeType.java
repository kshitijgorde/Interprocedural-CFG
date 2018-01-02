// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

public enum NodeType
{
    ALIASNODE, 
    ANDNODE, 
    ARGSCATNODE, 
    ARGSNODE, 
    ARGUMENTNODE, 
    ARRAYNODE, 
    ASSIGNABLENODE, 
    BACKREFNODE, 
    BEGINNODE, 
    BIGNUMNODE, 
    BINARYOPERATORNODE, 
    BLOCKARGNODE, 
    BLOCKNODE, 
    BLOCKPASSNODE, 
    BREAKNODE, 
    CALLNODE, 
    CASENODE, 
    CLASSNODE, 
    CLASSVARASGNNODE, 
    CLASSVARDECLNODE, 
    CLASSVARNODE, 
    COLON2NODE, 
    COLON3NODE, 
    CONSTDECLNODE, 
    CONSTNODE, 
    DASGNNODE, 
    DEFINEDNODE, 
    DEFNNODE, 
    DEFSNODE, 
    DOTNODE, 
    DREGEXPNODE, 
    DSTRNODE, 
    DSYMBOLNODE, 
    DVARNODE, 
    DXSTRNODE, 
    ENSURENODE, 
    EVSTRNODE, 
    FALSENODE, 
    FCALLNODE, 
    FIXNUMNODE, 
    FLIPNODE, 
    FLOATNODE, 
    FORNODE, 
    GLOBALASGNNODE, 
    GLOBALVARNODE, 
    HASHNODE, 
    IFNODE, 
    INSTASGNNODE, 
    INSTVARNODE, 
    ISCOPINGNODE, 
    ITERNODE, 
    LISTNODE, 
    LOCALASGNNODE, 
    LOCALVARNODE, 
    MATCH2NODE, 
    MATCH3NODE, 
    MATCHNODE, 
    MODULENODE, 
    MULTIPLEASGNNODE, 
    NEWLINENODE, 
    NEXTNODE, 
    NILNODE, 
    NOTNODE, 
    NTHREFNODE, 
    OPASGNANDNODE, 
    OPASGNNODE, 
    OPASGNORNODE, 
    OPELEMENTASGNNODE, 
    ORNODE, 
    PREEXENODE, 
    POSTEXENODE, 
    REDONODE, 
    REGEXPNODE, 
    RESCUEBODYNODE, 
    RESCUENODE, 
    RETRYNODE, 
    RETURNNODE, 
    SCLASSNODE, 
    SCOPENODE, 
    SELFNODE, 
    SPLATNODE, 
    STARNODE, 
    STRNODE, 
    SUPERNODE, 
    SVALUENODE, 
    SYMBOLNODE, 
    TOARYNODE, 
    TRUENODE, 
    UNDEFNODE, 
    UNTILNODE, 
    VALIASNODE, 
    VCALLNODE, 
    WHENNODE, 
    WHILENODE, 
    XSTRNODE, 
    YIELDNODE, 
    ZARRAYNODE, 
    ZEROARGNODE, 
    ZSUPERNODE, 
    COMMENTNODE, 
    ROOTNODE, 
    ATTRASSIGNNODE, 
    ARGSPUSHNODE, 
    OPTARGNODE, 
    ARGAUXILIARYNODE, 
    LAMBDANODE, 
    MULTIPLEASGN19NODE, 
    RESTARG, 
    ENCODINGNODE, 
    LITERALNODE, 
    BLOCKARG18NODE;
    
    public boolean alwaysTrue() {
        switch (this) {
            case TRUENODE:
            case FIXNUMNODE:
            case FLOATNODE:
            case REGEXPNODE:
            case STRNODE:
            case DOTNODE:
            case SYMBOLNODE:
            case BIGNUMNODE:
            case ARRAYNODE:
            case HASHNODE: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public boolean alwaysFalse() {
        switch (this) {
            case NILNODE:
            case FALSENODE: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public boolean isImmediate() {
        switch (this) {
            case TRUENODE:
            case FIXNUMNODE:
            case FLOATNODE:
            case STRNODE:
            case BIGNUMNODE:
            case NILNODE:
            case FALSENODE:
            case SELFNODE: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
}
