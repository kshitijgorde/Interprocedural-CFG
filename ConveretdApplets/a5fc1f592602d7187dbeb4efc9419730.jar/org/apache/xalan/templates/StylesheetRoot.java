// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.apache.xml.utils.IntStack;
import org.apache.xml.dtm.ref.ExpandedNameTable;
import org.apache.xml.dtm.DTM;
import org.apache.xpath.XPathContext;
import java.text.DecimalFormatSymbols;
import org.apache.xml.utils.QName;
import java.util.Properties;
import org.apache.xalan.transformer.TransformerImpl;
import javax.xml.transform.Transformer;
import org.apache.xalan.processor.XSLTSchema;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerConfigurationException;
import org.apache.xalan.res.XSLMessages;
import org.apache.xml.utils.PrefixResolver;
import javax.xml.transform.SourceLocator;
import javax.xml.transform.ErrorListener;
import org.apache.xpath.XPath;
import java.util.Vector;
import org.apache.xalan.extensions.ExtensionNamespacesManager;
import java.util.Hashtable;
import javax.xml.transform.Templates;
import java.io.Serializable;

public class StylesheetRoot extends StylesheetComposed implements Serializable, Templates
{
    private Hashtable m_availElems;
    private ExtensionNamespacesManager m_extNsMgr;
    private StylesheetComposed[] m_globalImportList;
    private OutputProperties m_outputProperties;
    private boolean m_outputMethodSet;
    private Hashtable m_attrSets;
    private Hashtable m_decimalFormatSymbols;
    private Vector m_keyDecls;
    private Hashtable m_namespaceAliasComposed;
    private TemplateList m_templateList;
    private Vector m_variables;
    private TemplateList m_whiteSpaceInfoList;
    private ElemTemplate m_defaultTextRule;
    private ElemTemplate m_defaultRule;
    private ElemTemplate m_defaultRootRule;
    private ElemTemplate m_startRule;
    XPath m_selectDefault;
    private ComposeState m_composeState;
    
    public StylesheetRoot(final ErrorListener errorListener) throws TransformerConfigurationException {
        super(null);
        this.m_extNsMgr = null;
        this.m_outputMethodSet = false;
        this.setStylesheetRoot(this);
        try {
            this.m_selectDefault = new XPath("node()", this, this, 0, errorListener);
            this.initDefaultRule(errorListener);
        }
        catch (TransformerException se) {
            throw new TransformerConfigurationException(XSLMessages.createMessage("ER_CANNOT_INIT_DEFAULT_TEMPLATES", null), se);
        }
    }
    
    public StylesheetRoot(final XSLTSchema schema, final ErrorListener listener) throws TransformerConfigurationException {
        this(listener);
        this.m_availElems = schema.getElemsAvailable();
    }
    
    public boolean isRoot() {
        return true;
    }
    
    public Hashtable getAvailableElements() {
        return this.m_availElems;
    }
    
    public ExtensionNamespacesManager getExtensionNamespacesManager() {
        if (this.m_extNsMgr == null) {
            this.m_extNsMgr = new ExtensionNamespacesManager();
        }
        return this.m_extNsMgr;
    }
    
    public Vector getExtensions() {
        return (this.m_extNsMgr != null) ? this.m_extNsMgr.getExtensions() : null;
    }
    
    public Transformer newTransformer() {
        return new TransformerImpl(this);
    }
    
    public Properties getDefaultOutputProps() {
        return this.m_outputProperties.getProperties();
    }
    
    public Properties getOutputProperties() {
        return (Properties)this.getDefaultOutputProps().clone();
    }
    
    public void recompose() throws TransformerException {
        final Vector recomposableElements = new Vector();
        if (null == this.m_globalImportList) {
            final Vector importList = new Vector();
            this.addImports(this, true, importList);
            this.m_globalImportList = new StylesheetComposed[importList.size()];
            int i = 0;
            int j = importList.size() - 1;
            while (i < importList.size()) {
                (this.m_globalImportList[j] = importList.elementAt(i)).recomposeIncludes(this.m_globalImportList[j]);
                this.m_globalImportList[j--].recomposeImports();
                ++i;
            }
        }
        int n;
        for (n = this.getGlobalImportCount(), int i = 0; i < n; ++i) {
            final StylesheetComposed imported = this.getGlobalImport(i);
            imported.recompose(recomposableElements);
        }
        this.QuickSort2(recomposableElements, 0, recomposableElements.size() - 1);
        this.m_outputProperties = new OutputProperties("");
        this.m_attrSets = new Hashtable();
        this.m_decimalFormatSymbols = new Hashtable();
        this.m_keyDecls = new Vector();
        this.m_namespaceAliasComposed = new Hashtable();
        this.m_templateList = new TemplateList();
        this.m_variables = new Vector();
        for (int k = recomposableElements.size() - 1; k >= 0; --k) {
            recomposableElements.elementAt(k).recompose(this);
        }
        this.initComposeState();
        this.m_templateList.compose(this);
        this.m_outputProperties.compose(this);
        this.m_outputProperties.endCompose(this);
        n = this.getGlobalImportCount();
        for (int l = 0; l < n; ++l) {
            final StylesheetComposed imported2 = this.getGlobalImport(l);
            for (int includedCount = imported2.getIncludeCountComposed(), m = -1; m < includedCount; ++m) {
                final Stylesheet included = imported2.getIncludeComposed(m);
                this.composeTemplates(included);
            }
        }
        if (this.m_extNsMgr != null) {
            this.m_extNsMgr.registerUnregisteredNamespaces();
        }
        this.clearComposeState();
    }
    
    void composeTemplates(final ElemTemplateElement templ) throws TransformerException {
        templ.compose(this);
        for (ElemTemplateElement child = templ.getFirstChildElem(); child != null; child = child.getNextSiblingElem()) {
            this.composeTemplates(child);
        }
        templ.endCompose(this);
    }
    
    protected void addImports(final Stylesheet stylesheet, final boolean addToList, final Vector importList) {
        int n = stylesheet.getImportCount();
        if (n > 0) {
            for (int i = 0; i < n; ++i) {
                final Stylesheet imported = stylesheet.getImport(i);
                this.addImports(imported, true, importList);
            }
        }
        n = stylesheet.getIncludeCount();
        if (n > 0) {
            for (int i = 0; i < n; ++i) {
                final Stylesheet included = stylesheet.getInclude(i);
                this.addImports(included, false, importList);
            }
        }
        if (addToList) {
            importList.addElement(stylesheet);
        }
    }
    
    public StylesheetComposed getGlobalImport(final int i) {
        return this.m_globalImportList[i];
    }
    
    public int getGlobalImportCount() {
        return (this.m_globalImportList != null) ? this.m_globalImportList.length : 1;
    }
    
    public int getImportNumber(final StylesheetComposed sheet) {
        if (this == sheet) {
            return 0;
        }
        for (int n = this.getGlobalImportCount(), i = 0; i < n; ++i) {
            if (sheet == this.getGlobalImport(i)) {
                return i;
            }
        }
        return -1;
    }
    
    void recomposeOutput(final OutputProperties oprops) throws TransformerException {
        this.m_outputProperties.copyFrom(oprops);
    }
    
    public OutputProperties getOutputComposed() {
        return this.m_outputProperties;
    }
    
    public boolean isOutputMethodSet() {
        return this.m_outputMethodSet;
    }
    
    void recomposeAttributeSets(final ElemAttributeSet attrSet) {
        Vector attrSetList = this.m_attrSets.get(attrSet.getName());
        if (null == attrSetList) {
            attrSetList = new Vector();
            this.m_attrSets.put(attrSet.getName(), attrSetList);
        }
        attrSetList.addElement(attrSet);
    }
    
    public Vector getAttributeSetComposed(final QName name) throws ArrayIndexOutOfBoundsException {
        return this.m_attrSets.get(name);
    }
    
    void recomposeDecimalFormats(final DecimalFormatProperties dfp) {
        final DecimalFormatSymbols oldDfs = this.m_decimalFormatSymbols.get(dfp.getName());
        if (null == oldDfs) {
            this.m_decimalFormatSymbols.put(dfp.getName(), dfp.getDecimalFormatSymbols());
        }
        else if (!dfp.getDecimalFormatSymbols().equals(oldDfs)) {
            String themsg;
            if (dfp.getName().equals(new QName(""))) {
                themsg = XSLMessages.createWarning("WG_ONE_DEFAULT_XSLDECIMALFORMAT_ALLOWED", new Object[0]);
            }
            else {
                themsg = XSLMessages.createWarning("WG_XSLDECIMALFORMAT_NAMES_MUST_BE_UNIQUE", new Object[] { dfp.getName() });
            }
            this.error(themsg);
        }
    }
    
    public DecimalFormatSymbols getDecimalFormatComposed(final QName name) {
        return this.m_decimalFormatSymbols.get(name);
    }
    
    void recomposeKeys(final KeyDeclaration keyDecl) {
        this.m_keyDecls.addElement(keyDecl);
    }
    
    public Vector getKeysComposed() {
        return this.m_keyDecls;
    }
    
    void recomposeNamespaceAliases(final NamespaceAlias nsAlias) {
        this.m_namespaceAliasComposed.put(nsAlias.getStylesheetNamespace(), nsAlias);
    }
    
    public NamespaceAlias getNamespaceAliasComposed(final String uri) {
        return (null == this.m_namespaceAliasComposed) ? null : this.m_namespaceAliasComposed.get(uri);
    }
    
    void recomposeTemplates(final ElemTemplate template) {
        this.m_templateList.setTemplate(template);
    }
    
    public final TemplateList getTemplateListComposed() {
        return this.m_templateList;
    }
    
    public final void setTemplateListComposed(final TemplateList templateList) {
        this.m_templateList = templateList;
    }
    
    public ElemTemplate getTemplateComposed(final XPathContext xctxt, final int targetNode, final QName mode, final boolean quietConflictWarnings, final DTM dtm) throws TransformerException {
        return this.m_templateList.getTemplate(xctxt, targetNode, mode, quietConflictWarnings, dtm);
    }
    
    public ElemTemplate getTemplateComposed(final XPathContext xctxt, final int targetNode, final QName mode, final int maxImportLevel, final int endImportLevel, final boolean quietConflictWarnings, final DTM dtm) throws TransformerException {
        return this.m_templateList.getTemplate(xctxt, targetNode, mode, maxImportLevel, endImportLevel, quietConflictWarnings, dtm);
    }
    
    public ElemTemplate getTemplateComposed(final QName qname) {
        return this.m_templateList.getTemplate(qname);
    }
    
    void recomposeVariables(final ElemVariable elemVar) {
        if (this.getVariableOrParamComposed(elemVar.getName()) == null) {
            elemVar.setIsTopLevel(true);
            elemVar.setIndex(this.m_variables.size());
            this.m_variables.addElement(elemVar);
        }
    }
    
    public ElemVariable getVariableOrParamComposed(final QName qname) {
        if (null != this.m_variables) {
            for (int n = this.m_variables.size(), i = 0; i < n; ++i) {
                final ElemVariable var = this.m_variables.elementAt(i);
                if (var.getName().equals(qname)) {
                    return var;
                }
            }
        }
        return null;
    }
    
    public Vector getVariablesAndParamsComposed() {
        return this.m_variables;
    }
    
    void recomposeWhiteSpaceInfo(final WhiteSpaceInfo wsi) {
        if (null == this.m_whiteSpaceInfoList) {
            this.m_whiteSpaceInfoList = new TemplateList();
        }
        this.m_whiteSpaceInfoList.setTemplate(wsi);
    }
    
    public boolean shouldCheckWhitespace() {
        return null != this.m_whiteSpaceInfoList;
    }
    
    public WhiteSpaceInfo getWhiteSpaceInfo(final XPathContext support, final int targetElement, final DTM dtm) throws TransformerException {
        if (null != this.m_whiteSpaceInfoList) {
            return (WhiteSpaceInfo)this.m_whiteSpaceInfoList.getTemplate(support, targetElement, null, false, dtm);
        }
        return null;
    }
    
    public boolean shouldStripWhiteSpace(final XPathContext support, int targetElement) throws TransformerException {
        if (null != this.m_whiteSpaceInfoList) {
            while (-1 != targetElement) {
                final DTM dtm = support.getDTM(targetElement);
                final WhiteSpaceInfo info = (WhiteSpaceInfo)this.m_whiteSpaceInfoList.getTemplate(support, targetElement, null, false, dtm);
                if (null != info) {
                    return info.getShouldStripSpace();
                }
                final int parent = dtm.getParent(targetElement);
                if (-1 != parent && 1 == dtm.getNodeType(parent)) {
                    targetElement = parent;
                }
                else {
                    targetElement = -1;
                }
            }
        }
        return false;
    }
    
    public boolean canStripWhiteSpace() {
        return null != this.m_whiteSpaceInfoList;
    }
    
    public final ElemTemplate getDefaultTextRule() {
        return this.m_defaultTextRule;
    }
    
    public final ElemTemplate getDefaultRule() {
        return this.m_defaultRule;
    }
    
    public final ElemTemplate getDefaultRootRule() {
        return this.m_defaultRootRule;
    }
    
    public final ElemTemplate getStartRule() {
        return this.m_startRule;
    }
    
    private void initDefaultRule(final ErrorListener errorListener) throws TransformerException {
        (this.m_defaultRule = new ElemTemplate()).setStylesheet(this);
        XPath defMatch = new XPath("*", this, this, 1, errorListener);
        this.m_defaultRule.setMatch(defMatch);
        ElemApplyTemplates childrenElement = new ElemApplyTemplates();
        childrenElement.setIsDefaultTemplate(true);
        childrenElement.setSelect(this.m_selectDefault);
        this.m_defaultRule.appendChild(childrenElement);
        this.m_startRule = this.m_defaultRule;
        (this.m_defaultTextRule = new ElemTemplate()).setStylesheet(this);
        defMatch = new XPath("text() | @*", this, this, 1, errorListener);
        this.m_defaultTextRule.setMatch(defMatch);
        final ElemValueOf elemValueOf = new ElemValueOf();
        this.m_defaultTextRule.appendChild(elemValueOf);
        final XPath selectPattern = new XPath(".", this, this, 0, errorListener);
        elemValueOf.setSelect(selectPattern);
        (this.m_defaultRootRule = new ElemTemplate()).setStylesheet(this);
        defMatch = new XPath("/", this, this, 1, errorListener);
        this.m_defaultRootRule.setMatch(defMatch);
        childrenElement = new ElemApplyTemplates();
        childrenElement.setIsDefaultTemplate(true);
        this.m_defaultRootRule.appendChild(childrenElement);
        childrenElement.setSelect(this.m_selectDefault);
    }
    
    private void QuickSort2(final Vector v, final int lo0, final int hi0) {
        int lo = lo0;
        int hi = hi0;
        if (hi0 > lo0) {
            final ElemTemplateElement midNode = v.elementAt((lo0 + hi0) / 2);
            while (lo <= hi) {
                while (lo < hi0) {
                    if (v.elementAt(lo).compareTo(midNode) >= 0) {
                        break;
                    }
                    ++lo;
                }
                while (hi > lo0 && v.elementAt(hi).compareTo(midNode) > 0) {
                    --hi;
                }
                if (lo <= hi) {
                    final ElemTemplateElement node = v.elementAt(lo);
                    v.setElementAt(v.elementAt(hi), lo);
                    v.setElementAt(node, hi);
                    ++lo;
                    --hi;
                }
            }
            if (lo0 < hi) {
                this.QuickSort2(v, lo0, hi);
            }
            if (lo < hi0) {
                this.QuickSort2(v, lo, hi0);
            }
        }
    }
    
    void initComposeState() {
        this.m_composeState = new ComposeState();
    }
    
    ComposeState getComposeState() {
        return this.m_composeState;
    }
    
    private void clearComposeState() {
        this.m_composeState = null;
    }
    
    class ComposeState
    {
        private ExpandedNameTable m_ent;
        private Vector m_variableNames;
        IntStack m_marks;
        private int m_maxStackFrameSize;
        
        ComposeState() {
            this.m_ent = new ExpandedNameTable();
            this.m_variableNames = new Vector();
            this.m_marks = new IntStack();
            for (int size = StylesheetRoot.this.m_variables.size(), i = 0; i < size; ++i) {
                final ElemVariable ev = StylesheetRoot.this.m_variables.elementAt(i);
                this.m_variableNames.addElement(ev.getName());
            }
        }
        
        public int getQNameID(final QName qname) {
            return this.m_ent.getExpandedTypeID(qname.getNamespace(), qname.getLocalName(), 1);
        }
        
        int addVariableName(final QName qname) {
            final int pos = this.m_variableNames.size();
            this.m_variableNames.addElement(qname);
            final int frameSize = this.m_variableNames.size() - this.getGlobalsSize();
            if (frameSize > this.m_maxStackFrameSize) {
                ++this.m_maxStackFrameSize;
            }
            return pos;
        }
        
        void resetStackFrameSize() {
            this.m_maxStackFrameSize = 0;
        }
        
        int getFrameSize() {
            return this.m_maxStackFrameSize;
        }
        
        int getCurrentStackFrameSize() {
            return this.m_variableNames.size();
        }
        
        void setCurrentStackFrameSize(final int sz) {
            this.m_variableNames.setSize(sz);
        }
        
        int getGlobalsSize() {
            return StylesheetRoot.this.m_variables.size();
        }
        
        void pushStackMark() {
            this.m_marks.push(this.getCurrentStackFrameSize());
        }
        
        void popStackMark() {
            final int mark = this.m_marks.pop();
            this.setCurrentStackFrameSize(mark);
        }
        
        Vector getVariableNames() {
            return this.m_variableNames;
        }
    }
}
