// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import javax.xml.transform.TransformerException;
import java.util.Vector;

public class StylesheetComposed extends Stylesheet
{
    private int m_importNumber;
    private int m_importCountComposed;
    private int m_endImportCountComposed;
    private transient Vector m_includesComposed;
    
    public StylesheetComposed(final Stylesheet parent) {
        super(parent);
        this.m_importNumber = -1;
    }
    
    public boolean isAggregatedType() {
        return true;
    }
    
    public void recompose(final Vector recomposableElements) throws TransformerException {
        for (int n = this.getIncludeCountComposed(), i = -1; i < n; ++i) {
            final Stylesheet included = this.getIncludeComposed(i);
            for (int s = included.getOutputCount(), j = 0; j < s; ++j) {
                recomposableElements.addElement(included.getOutput(j));
            }
            for (int s = included.getAttributeSetCount(), k = 0; k < s; ++k) {
                recomposableElements.addElement(included.getAttributeSet(k));
            }
            for (int s = included.getDecimalFormatCount(), l = 0; l < s; ++l) {
                recomposableElements.addElement(included.getDecimalFormat(l));
            }
            for (int s = included.getKeyCount(), m = 0; m < s; ++m) {
                recomposableElements.addElement(included.getKey(m));
            }
            for (int s = included.getNamespaceAliasCount(), j2 = 0; j2 < s; ++j2) {
                recomposableElements.addElement(included.getNamespaceAlias(j2));
            }
            for (int s = included.getTemplateCount(), j3 = 0; j3 < s; ++j3) {
                recomposableElements.addElement(included.getTemplate(j3));
            }
            for (int s = included.getVariableOrParamCount(), j4 = 0; j4 < s; ++j4) {
                recomposableElements.addElement(included.getVariableOrParam(j4));
            }
            for (int s = included.getStripSpaceCount(), j5 = 0; j5 < s; ++j5) {
                recomposableElements.addElement(included.getStripSpace(j5));
            }
            for (int s = included.getPreserveSpaceCount(), j6 = 0; j6 < s; ++j6) {
                recomposableElements.addElement(included.getPreserveSpace(j6));
            }
        }
    }
    
    void recomposeImports() {
        this.m_importNumber = this.getStylesheetRoot().getImportNumber(this);
        final StylesheetRoot root = this.getStylesheetRoot();
        final int globalImportCount = root.getGlobalImportCount();
        this.m_importCountComposed = globalImportCount - this.m_importNumber - 1;
        int count = this.getImportCount();
        if (count > 0) {
            this.m_endImportCountComposed += count;
            while (count > 0) {
                this.m_endImportCountComposed += this.getImport(--count).getEndImportCountComposed();
            }
        }
        count = this.getIncludeCountComposed();
        while (count > 0) {
            int imports = this.getIncludeComposed(--count).getImportCount();
            this.m_endImportCountComposed += imports;
            while (imports > 0) {
                this.m_endImportCountComposed += this.getIncludeComposed(count).getImport(--imports).getEndImportCountComposed();
            }
        }
    }
    
    public StylesheetComposed getImportComposed(final int i) throws ArrayIndexOutOfBoundsException {
        final StylesheetRoot root = this.getStylesheetRoot();
        return root.getGlobalImport(1 + this.m_importNumber + i);
    }
    
    public int getImportCountComposed() {
        return this.m_importCountComposed;
    }
    
    public int getEndImportCountComposed() {
        return this.m_endImportCountComposed;
    }
    
    void recomposeIncludes(final Stylesheet including) {
        final int n = including.getIncludeCount();
        if (n > 0) {
            if (null == this.m_includesComposed) {
                this.m_includesComposed = new Vector();
            }
            for (int i = 0; i < n; ++i) {
                final Stylesheet included = including.getInclude(i);
                this.m_includesComposed.addElement(included);
                this.recomposeIncludes(included);
            }
        }
    }
    
    public Stylesheet getIncludeComposed(final int i) throws ArrayIndexOutOfBoundsException {
        if (-1 == i) {
            return this;
        }
        if (null == this.m_includesComposed) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.m_includesComposed.elementAt(i);
    }
    
    public int getIncludeCountComposed() {
        return (null != this.m_includesComposed) ? this.m_includesComposed.size() : 0;
    }
    
    public void recomposeTemplates(final boolean flushFirst) throws TransformerException {
    }
}
