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
    
    public CMBuilder() {
        this.fDeclPool = null;
        this.fDeclPool = null;
    }
    
    public CMBuilder(final XSDeclarationPool pool) {
        this.fDeclPool = null;
        this.fDeclPool = pool;
    }
    
    public void setDeclPool(final XSDeclarationPool declPool) {
        this.fDeclPool = declPool;
    }
    
    public XSCMValidator getContentModel(final XSComplexTypeDecl typeDecl) {
        final short contentType = typeDecl.getContentType();
        if (contentType == 1 || contentType == 0) {
            return null;
        }
        final XSParticleDecl particle = (XSParticleDecl)typeDecl.getParticle();
        if (particle == null) {
            return CMBuilder.fEmptyCM;
        }
        XSCMValidator cmValidator = null;
        if (particle.fType == 3 && ((XSModelGroupImpl)particle.fValue).fCompositor == 103) {
            cmValidator = this.createAllCM(particle);
        }
        else {
            cmValidator = this.createDFACM(particle);
        }
        if (cmValidator == null) {
            cmValidator = CMBuilder.fEmptyCM;
        }
        return cmValidator;
    }
    
    XSCMValidator createAllCM(final XSParticleDecl particle) {
        if (particle.fMaxOccurs == 0) {
            return null;
        }
        final XSModelGroupImpl group = (XSModelGroupImpl)particle.fValue;
        final XSAllCM allContent = new XSAllCM(particle.fMinOccurs == 0, group.fParticleCount);
        for (int i = 0; i < group.fParticleCount; ++i) {
            if (group.fParticles[i].fType != 0 && group.fParticles[i].fMaxOccurs != 0) {
                allContent.addElement((XSElementDecl)group.fParticles[i].fValue, group.fParticles[i].fMinOccurs == 0);
            }
        }
        return allContent;
    }
    
    XSCMValidator createDFACM(final XSParticleDecl particle) {
        this.fLeafCount = 0;
        this.fParticleCount = 0;
        final CMNode node = this.buildSyntaxTree(particle);
        if (node == null) {
            return null;
        }
        return new XSDFACM(node, this.fLeafCount);
    }
    
    private CMNode buildSyntaxTree(final XSParticleDecl particle) {
        final int maxOccurs = particle.fMaxOccurs;
        final int minOccurs = particle.fMinOccurs;
        final short type = particle.fType;
        CMNode nodeRet = null;
        if (type == 2 || type == 1) {
            nodeRet = new XSCMLeaf(particle.fType, particle.fValue, this.fParticleCount++, this.fLeafCount++);
            nodeRet = this.expandContentModel(nodeRet, minOccurs, maxOccurs);
        }
        else if (type == 3) {
            final XSModelGroupImpl group = (XSModelGroupImpl)particle.fValue;
            CMNode temp = null;
            boolean twoChildren = false;
            for (int i = 0; i < group.fParticleCount; ++i) {
                temp = this.buildSyntaxTree(group.fParticles[i]);
                if (temp != null) {
                    if (nodeRet == null) {
                        nodeRet = temp;
                    }
                    else {
                        nodeRet = new XSCMBinOp(group.fCompositor, nodeRet, temp);
                        twoChildren = true;
                    }
                }
            }
            if (nodeRet != null) {
                if (group.fCompositor == 101 && !twoChildren && group.fParticleCount > 1) {
                    nodeRet = new XSCMUniOp(5, nodeRet);
                }
                nodeRet = this.expandContentModel(nodeRet, minOccurs, maxOccurs);
            }
        }
        return nodeRet;
    }
    
    private CMNode expandContentModel(CMNode node, final int minOccurs, final int maxOccurs) {
        CMNode nodeRet = null;
        if (minOccurs == 1 && maxOccurs == 1) {
            nodeRet = node;
        }
        else if (minOccurs == 0 && maxOccurs == 1) {
            nodeRet = new XSCMUniOp(5, node);
        }
        else if (minOccurs == 0 && maxOccurs == -1) {
            nodeRet = new XSCMUniOp(4, node);
        }
        else if (minOccurs == 1 && maxOccurs == -1) {
            nodeRet = new XSCMUniOp(6, node);
        }
        else if (maxOccurs == -1) {
            nodeRet = new XSCMUniOp(6, node);
            for (int i = 0; i < minOccurs - 1; ++i) {
                nodeRet = new XSCMBinOp(102, this.copyNode(node), nodeRet);
            }
        }
        else {
            if (minOccurs > 0) {
                nodeRet = node;
                for (int i = 0; i < minOccurs - 1; ++i) {
                    nodeRet = new XSCMBinOp(102, nodeRet, this.copyNode(node));
                }
            }
            if (maxOccurs > minOccurs) {
                node = new XSCMUniOp(5, node);
                if (nodeRet == null) {
                    nodeRet = node;
                }
                else {
                    nodeRet = new XSCMBinOp(102, nodeRet, this.copyNode(node));
                }
                for (int i = minOccurs; i < maxOccurs - 1; ++i) {
                    nodeRet = new XSCMBinOp(102, nodeRet, this.copyNode(node));
                }
            }
        }
        return nodeRet;
    }
    
    private CMNode copyNode(CMNode node) {
        final int type = node.type();
        if (type == 101 || type == 102) {
            final XSCMBinOp bin = (XSCMBinOp)node;
            node = new XSCMBinOp(type, this.copyNode(bin.getLeft()), this.copyNode(bin.getRight()));
        }
        else if (type == 4 || type == 6 || type == 5) {
            final XSCMUniOp uni = (XSCMUniOp)node;
            node = new XSCMUniOp(type, this.copyNode(uni.getChild()));
        }
        else if (type == 1 || type == 2) {
            final XSCMLeaf leaf = (XSCMLeaf)node;
            node = new XSCMLeaf(leaf.type(), leaf.getLeaf(), leaf.getParticleId(), this.fLeafCount++);
        }
        return node;
    }
    
    static {
        CMBuilder.fEmptyCM = new XSEmptyCM();
    }
}
