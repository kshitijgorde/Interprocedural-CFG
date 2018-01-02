// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.models;

import org.apache.xerces.impl.dtd.models.CMNode;
import org.apache.xerces.impl.xs.XSElementDecl;
import org.apache.xerces.impl.xs.XSModelGroupImpl;
import org.apache.xerces.impl.xs.XSParticleDecl;
import org.apache.xerces.impl.xs.XSComplexTypeDecl;
import org.apache.xerces.impl.xs.XSDeclarationPool;

public class CMBuilder
{
    private XSDeclarationPool fDeclPool;
    private static XSEmptyCM fEmptyCM;
    private int fLeafCount;
    private int fParticleCount;
    private CMNodeFactory fNodeFactory;
    
    public CMBuilder(final CMNodeFactory fNodeFactory) {
        this.fDeclPool = null;
        this.fDeclPool = null;
        this.fNodeFactory = fNodeFactory;
    }
    
    public void setDeclPool(final XSDeclarationPool fDeclPool) {
        this.fDeclPool = fDeclPool;
    }
    
    public XSCMValidator getContentModel(final XSComplexTypeDecl xsComplexTypeDecl) {
        final short contentType = xsComplexTypeDecl.getContentType();
        if (contentType == 1 || contentType == 0) {
            return null;
        }
        final XSParticleDecl xsParticleDecl = (XSParticleDecl)xsComplexTypeDecl.getParticle();
        if (xsParticleDecl == null) {
            return CMBuilder.fEmptyCM;
        }
        XSCMValidator xscmValidator;
        if (xsParticleDecl.fType == 3 && ((XSModelGroupImpl)xsParticleDecl.fValue).fCompositor == 103) {
            xscmValidator = this.createAllCM(xsParticleDecl);
        }
        else {
            xscmValidator = this.createDFACM(xsParticleDecl);
        }
        this.fNodeFactory.resetNodeCount();
        if (xscmValidator == null) {
            xscmValidator = CMBuilder.fEmptyCM;
        }
        return xscmValidator;
    }
    
    XSCMValidator createAllCM(final XSParticleDecl xsParticleDecl) {
        if (xsParticleDecl.fMaxOccurs == 0) {
            return null;
        }
        final XSModelGroupImpl xsModelGroupImpl = (XSModelGroupImpl)xsParticleDecl.fValue;
        final XSAllCM xsAllCM = new XSAllCM(xsParticleDecl.fMinOccurs == 0, xsModelGroupImpl.fParticleCount);
        for (int i = 0; i < xsModelGroupImpl.fParticleCount; ++i) {
            xsAllCM.addElement((XSElementDecl)xsModelGroupImpl.fParticles[i].fValue, xsModelGroupImpl.fParticles[i].fMinOccurs == 0);
        }
        return xsAllCM;
    }
    
    XSCMValidator createDFACM(final XSParticleDecl xsParticleDecl) {
        this.fLeafCount = 0;
        this.fParticleCount = 0;
        final CMNode buildSyntaxTree = this.buildSyntaxTree(xsParticleDecl);
        if (buildSyntaxTree == null) {
            return null;
        }
        return new XSDFACM(buildSyntaxTree, this.fLeafCount);
    }
    
    private CMNode buildSyntaxTree(final XSParticleDecl xsParticleDecl) {
        final int fMaxOccurs = xsParticleDecl.fMaxOccurs;
        final int fMinOccurs = xsParticleDecl.fMinOccurs;
        final short fType = xsParticleDecl.fType;
        CMNode cmNode = null;
        if (fType == 2 || fType == 1) {
            cmNode = this.expandContentModel(this.fNodeFactory.getCMLeafNode(xsParticleDecl.fType, xsParticleDecl.fValue, this.fParticleCount++, this.fLeafCount++), fMinOccurs, fMaxOccurs);
        }
        else if (fType == 3) {
            final XSModelGroupImpl xsModelGroupImpl = (XSModelGroupImpl)xsParticleDecl.fValue;
            boolean b = false;
            for (int i = 0; i < xsModelGroupImpl.fParticleCount; ++i) {
                final CMNode buildSyntaxTree = this.buildSyntaxTree(xsModelGroupImpl.fParticles[i]);
                if (buildSyntaxTree != null) {
                    if (cmNode == null) {
                        cmNode = buildSyntaxTree;
                    }
                    else {
                        cmNode = this.fNodeFactory.getCMBinOpNode(xsModelGroupImpl.fCompositor, cmNode, buildSyntaxTree);
                        b = true;
                    }
                }
            }
            if (cmNode != null) {
                if (xsModelGroupImpl.fCompositor == 101 && !b && xsModelGroupImpl.fParticleCount > 1) {
                    cmNode = this.fNodeFactory.getCMUniOpNode(5, cmNode);
                }
                cmNode = this.expandContentModel(cmNode, fMinOccurs, fMaxOccurs);
            }
        }
        return cmNode;
    }
    
    private CMNode expandContentModel(CMNode cmUniOpNode, final int n, final int n2) {
        CMNode cmNode = null;
        if (n == 1 && n2 == 1) {
            cmNode = cmUniOpNode;
        }
        else if (n == 0 && n2 == 1) {
            cmNode = this.fNodeFactory.getCMUniOpNode(5, cmUniOpNode);
        }
        else if (n == 0 && n2 == -1) {
            cmNode = this.fNodeFactory.getCMUniOpNode(4, cmUniOpNode);
        }
        else if (n == 1 && n2 == -1) {
            cmNode = this.fNodeFactory.getCMUniOpNode(6, cmUniOpNode);
        }
        else if (n2 == -1) {
            cmNode = this.fNodeFactory.getCMBinOpNode(102, this.multiNodes(cmUniOpNode, n - 1, true), this.fNodeFactory.getCMUniOpNode(6, cmUniOpNode));
        }
        else {
            if (n > 0) {
                cmNode = this.multiNodes(cmUniOpNode, n, false);
            }
            if (n2 > n) {
                cmUniOpNode = this.fNodeFactory.getCMUniOpNode(5, cmUniOpNode);
                if (cmNode == null) {
                    cmNode = this.multiNodes(cmUniOpNode, n2 - n, false);
                }
                else {
                    cmNode = this.fNodeFactory.getCMBinOpNode(102, cmNode, this.multiNodes(cmUniOpNode, n2 - n, true));
                }
            }
        }
        return cmNode;
    }
    
    private CMNode multiNodes(final CMNode cmNode, final int n, final boolean b) {
        if (n == 0) {
            return null;
        }
        if (n == 1) {
            return b ? this.copyNode(cmNode) : cmNode;
        }
        final int n2 = n / 2;
        return this.fNodeFactory.getCMBinOpNode(102, this.multiNodes(cmNode, n2, b), this.multiNodes(cmNode, n - n2, true));
    }
    
    private CMNode copyNode(CMNode cmNode) {
        final int type = cmNode.type();
        if (type == 101 || type == 102) {
            final XSCMBinOp xscmBinOp = (XSCMBinOp)cmNode;
            cmNode = this.fNodeFactory.getCMBinOpNode(type, this.copyNode(xscmBinOp.getLeft()), this.copyNode(xscmBinOp.getRight()));
        }
        else if (type == 4 || type == 6 || type == 5) {
            cmNode = this.fNodeFactory.getCMUniOpNode(type, this.copyNode(((XSCMUniOp)cmNode).getChild()));
        }
        else if (type == 1 || type == 2) {
            final XSCMLeaf xscmLeaf = (XSCMLeaf)cmNode;
            cmNode = this.fNodeFactory.getCMLeafNode(xscmLeaf.type(), xscmLeaf.getLeaf(), xscmLeaf.getParticleId(), this.fLeafCount++);
        }
        return cmNode;
    }
    
    static {
        CMBuilder.fEmptyCM = new XSEmptyCM();
    }
}
