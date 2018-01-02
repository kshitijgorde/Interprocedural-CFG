// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import java.util.Vector;
import javax.xml.transform.TransformerException;
import org.apache.xml.utils.PrefixResolver;
import org.apache.xml.dtm.DTM;
import org.apache.xpath.XPathContext;
import org.apache.xml.utils.QName;
import org.apache.xpath.patterns.NodeTest;
import java.util.Enumeration;
import org.apache.xpath.Expression;
import org.apache.xpath.XPath;
import org.apache.xpath.patterns.UnionPattern;
import org.apache.xpath.patterns.StepPattern;
import java.util.Hashtable;
import java.io.Serializable;

public class TemplateList implements Serializable
{
    static final long serialVersionUID = 5803675288911728791L;
    static final boolean DEBUG = false;
    private Hashtable m_namedTemplates;
    private Hashtable m_patternTable;
    private TemplateSubPatternAssociation m_wildCardPatterns;
    private TemplateSubPatternAssociation m_textPatterns;
    private TemplateSubPatternAssociation m_docPatterns;
    private TemplateSubPatternAssociation m_commentPatterns;
    
    public TemplateList() {
        this.m_namedTemplates = new Hashtable(89);
        this.m_patternTable = new Hashtable(89);
        this.m_wildCardPatterns = null;
        this.m_textPatterns = null;
        this.m_docPatterns = null;
        this.m_commentPatterns = null;
    }
    
    public void setTemplate(final ElemTemplate template) {
        final XPath matchXPath = template.getMatch();
        if (null == template.getName() && null == matchXPath) {
            template.error("ER_NEED_NAME_OR_MATCH_ATTRIB", new Object[] { "xsl:template" });
        }
        if (null != template.getName()) {
            final ElemTemplate existingTemplate = this.m_namedTemplates.get(template.getName());
            if (null == existingTemplate) {
                this.m_namedTemplates.put(template.getName(), template);
            }
            else {
                final int existingPrecedence = existingTemplate.getStylesheetComposed().getImportCountComposed();
                final int newPrecedence = template.getStylesheetComposed().getImportCountComposed();
                if (newPrecedence > existingPrecedence) {
                    this.m_namedTemplates.put(template.getName(), template);
                }
                else if (newPrecedence == existingPrecedence) {
                    template.error("ER_DUPLICATE_NAMED_TEMPLATE", new Object[] { template.getName() });
                }
            }
        }
        if (null != matchXPath) {
            final Expression matchExpr = matchXPath.getExpression();
            if (matchExpr instanceof StepPattern) {
                this.insertPatternInTable((StepPattern)matchExpr, template);
            }
            else if (matchExpr instanceof UnionPattern) {
                final UnionPattern upat = (UnionPattern)matchExpr;
                final StepPattern[] pats = upat.getPatterns();
                for (int n = pats.length, i = 0; i < n; ++i) {
                    this.insertPatternInTable(pats[i], template);
                }
            }
        }
    }
    
    void dumpAssociationTables() {
        final Enumeration associations = this.m_patternTable.elements();
        while (associations.hasMoreElements()) {
            for (TemplateSubPatternAssociation head = associations.nextElement(); null != head; head = head.getNext()) {
                System.out.print("(" + head.getTargetString() + ", " + head.getPattern() + ")");
            }
            System.out.println("\n.....");
        }
        TemplateSubPatternAssociation head = this.m_wildCardPatterns;
        System.out.print("wild card list: ");
        while (null != head) {
            System.out.print("(" + head.getTargetString() + ", " + head.getPattern() + ")");
            head = head.getNext();
        }
        System.out.println("\n.....");
    }
    
    public void compose(final StylesheetRoot sroot) {
        if (null != this.m_wildCardPatterns) {
            final Enumeration associations = this.m_patternTable.elements();
            while (associations.hasMoreElements()) {
                TemplateSubPatternAssociation head = associations.nextElement();
                for (TemplateSubPatternAssociation wild = this.m_wildCardPatterns; null != wild; wild = wild.getNext()) {
                    try {
                        head = this.insertAssociationIntoList(head, (TemplateSubPatternAssociation)wild.clone(), true);
                    }
                    catch (CloneNotSupportedException ex) {}
                }
            }
        }
    }
    
    private TemplateSubPatternAssociation insertAssociationIntoList(final TemplateSubPatternAssociation head, final TemplateSubPatternAssociation item, final boolean isWildCardInsert) {
        final double priority = this.getPriorityOrScore(item);
        final int importLevel = item.getImportLevel();
        final int docOrder = item.getDocOrderPos();
        TemplateSubPatternAssociation insertPoint = head;
        TemplateSubPatternAssociation next;
        while (true) {
            next = insertPoint.getNext();
            if (null == next) {
                break;
            }
            final double workPriority = this.getPriorityOrScore(next);
            if (importLevel > next.getImportLevel()) {
                break;
            }
            if (importLevel < next.getImportLevel()) {
                insertPoint = next;
            }
            else {
                if (priority > workPriority) {
                    break;
                }
                if (priority < workPriority) {
                    insertPoint = next;
                }
                else {
                    if (docOrder >= next.getDocOrderPos()) {
                        break;
                    }
                    insertPoint = next;
                }
            }
        }
        boolean insertBefore;
        if (null == next || insertPoint == head) {
            final double workPriority = this.getPriorityOrScore(insertPoint);
            insertBefore = (importLevel > insertPoint.getImportLevel() || (importLevel >= insertPoint.getImportLevel() && (priority > workPriority || (priority >= workPriority && docOrder >= insertPoint.getDocOrderPos()))));
        }
        else {
            insertBefore = false;
        }
        if (isWildCardInsert) {
            if (insertBefore) {
                item.setNext(insertPoint);
                final String key = insertPoint.getTargetString();
                item.setTargetString(key);
                this.putHead(key, item);
                return item;
            }
            item.setNext(next);
            insertPoint.setNext(item);
            return head;
        }
        else {
            if (insertBefore) {
                item.setNext(insertPoint);
                if (insertPoint.isWild() || item.isWild()) {
                    this.m_wildCardPatterns = item;
                }
                else {
                    this.putHead(item.getTargetString(), item);
                }
                return item;
            }
            item.setNext(next);
            insertPoint.setNext(item);
            return head;
        }
    }
    
    private void insertPatternInTable(final StepPattern pattern, final ElemTemplate template) {
        final String target = pattern.getTargetString();
        if (null != target) {
            final String pstring = template.getMatch().getPatternString();
            final TemplateSubPatternAssociation association = new TemplateSubPatternAssociation(template, pattern, pstring);
            final boolean isWildCard = association.isWild();
            final TemplateSubPatternAssociation head = isWildCard ? this.m_wildCardPatterns : this.getHead(target);
            if (null == head) {
                if (isWildCard) {
                    this.m_wildCardPatterns = association;
                }
                else {
                    this.putHead(target, association);
                }
            }
            else {
                this.insertAssociationIntoList(head, association, false);
            }
        }
    }
    
    private double getPriorityOrScore(final TemplateSubPatternAssociation matchPat) {
        final double priority = matchPat.getTemplate().getPriority();
        if (priority == Double.NEGATIVE_INFINITY) {
            final Expression ex = matchPat.getStepPattern();
            if (ex instanceof NodeTest) {
                return ((NodeTest)ex).getDefaultScore();
            }
        }
        return priority;
    }
    
    public ElemTemplate getTemplate(final QName qname) {
        return this.m_namedTemplates.get(qname);
    }
    
    public TemplateSubPatternAssociation getHead(final XPathContext xctxt, final int targetNode, final DTM dtm) {
        final short targetNodeType = dtm.getNodeType(targetNode);
        TemplateSubPatternAssociation head = null;
        switch (targetNodeType) {
            case 1:
            case 2: {
                head = this.m_patternTable.get(dtm.getLocalName(targetNode));
                break;
            }
            case 3:
            case 4: {
                head = this.m_textPatterns;
                break;
            }
            case 5:
            case 6: {
                head = this.m_patternTable.get(dtm.getNodeName(targetNode));
                break;
            }
            case 7: {
                head = this.m_patternTable.get(dtm.getLocalName(targetNode));
                break;
            }
            case 8: {
                head = this.m_commentPatterns;
                break;
            }
            case 9:
            case 11: {
                head = this.m_docPatterns;
                break;
            }
            default: {
                head = this.m_patternTable.get(dtm.getNodeName(targetNode));
                break;
            }
        }
        return (null == head) ? this.m_wildCardPatterns : head;
    }
    
    public ElemTemplate getTemplateFast(final XPathContext xctxt, final int targetNode, final int expTypeID, final QName mode, final int maxImportLevel, final boolean quietConflictWarnings, final DTM dtm) throws TransformerException {
        TemplateSubPatternAssociation head = null;
        switch (dtm.getNodeType(targetNode)) {
            case 0:
            case 1: {
                head = this.m_patternTable.get(dtm.getLocalNameFromExpandedNameID(expTypeID));
                break;
            }
            case 2:
            case 3: {
                head = this.m_textPatterns;
                break;
            }
            case 4:
            case 5: {
                head = this.m_patternTable.get(dtm.getNodeName(targetNode));
                break;
            }
            case 6: {
                head = this.m_patternTable.get(dtm.getLocalName(targetNode));
                break;
            }
            case 7: {
                head = this.m_commentPatterns;
                break;
            }
            case 8:
            case 10: {
                head = this.m_docPatterns;
                break;
            }
            default: {
                head = this.m_patternTable.get(dtm.getNodeName(targetNode));
                break;
            }
        }
        if (null == head) {
            head = this.m_wildCardPatterns;
            if (null == head) {
                return null;
            }
        }
        xctxt.pushNamespaceContextNull();
        try {
            do {
                if (maxImportLevel > -1 && head.getImportLevel() > maxImportLevel) {
                    continue;
                }
                final ElemTemplate template = head.getTemplate();
                xctxt.setNamespaceContext(template);
                if (head.m_stepPattern.execute(xctxt, targetNode, dtm, expTypeID) != NodeTest.SCORE_NONE && head.matchMode(mode)) {
                    if (quietConflictWarnings) {
                        this.checkConflicts(head, xctxt, targetNode, mode);
                    }
                    return template;
                }
            } while (null != (head = head.getNext()));
        }
        finally {
            xctxt.popNamespaceContext();
        }
        return null;
    }
    
    public ElemTemplate getTemplate(final XPathContext xctxt, final int targetNode, final QName mode, final boolean quietConflictWarnings, final DTM dtm) throws TransformerException {
        TemplateSubPatternAssociation head = this.getHead(xctxt, targetNode, dtm);
        if (null != head) {
            xctxt.pushNamespaceContextNull();
            xctxt.pushCurrentNodeAndExpression(targetNode, targetNode);
            try {
                do {
                    final ElemTemplate template = head.getTemplate();
                    xctxt.setNamespaceContext(template);
                    if (head.m_stepPattern.execute(xctxt, targetNode) != NodeTest.SCORE_NONE && head.matchMode(mode)) {
                        if (quietConflictWarnings) {
                            this.checkConflicts(head, xctxt, targetNode, mode);
                        }
                        return template;
                    }
                } while (null != (head = head.getNext()));
            }
            finally {
                xctxt.popCurrentNodeAndExpression();
                xctxt.popNamespaceContext();
            }
        }
        return null;
    }
    
    public ElemTemplate getTemplate(final XPathContext xctxt, final int targetNode, final QName mode, final int maxImportLevel, final int endImportLevel, final boolean quietConflictWarnings, final DTM dtm) throws TransformerException {
        TemplateSubPatternAssociation head = this.getHead(xctxt, targetNode, dtm);
        if (null != head) {
            xctxt.pushNamespaceContextNull();
            xctxt.pushCurrentNodeAndExpression(targetNode, targetNode);
            try {
                do {
                    if (maxImportLevel > -1 && head.getImportLevel() > maxImportLevel) {
                        continue;
                    }
                    if (head.getImportLevel() <= maxImportLevel - endImportLevel) {
                        return null;
                    }
                    final ElemTemplate template = head.getTemplate();
                    xctxt.setNamespaceContext(template);
                    if (head.m_stepPattern.execute(xctxt, targetNode) != NodeTest.SCORE_NONE && head.matchMode(mode)) {
                        if (quietConflictWarnings) {
                            this.checkConflicts(head, xctxt, targetNode, mode);
                        }
                        return template;
                    }
                } while (null != (head = head.getNext()));
            }
            finally {
                xctxt.popCurrentNodeAndExpression();
                xctxt.popNamespaceContext();
            }
        }
        return null;
    }
    
    public TemplateWalker getWalker() {
        return new TemplateWalker();
    }
    
    private void checkConflicts(final TemplateSubPatternAssociation head, final XPathContext xctxt, final int targetNode, final QName mode) {
    }
    
    private void addObjectIfNotFound(final Object obj, final Vector v) {
        final int n = v.size();
        boolean addIt = true;
        for (int i = 0; i < n; ++i) {
            if (v.elementAt(i) == obj) {
                addIt = false;
                break;
            }
        }
        if (addIt) {
            v.addElement(obj);
        }
    }
    
    private Hashtable getNamedTemplates() {
        return this.m_namedTemplates;
    }
    
    private void setNamedTemplates(final Hashtable v) {
        this.m_namedTemplates = v;
    }
    
    private TemplateSubPatternAssociation getHead(final String key) {
        return this.m_patternTable.get(key);
    }
    
    private void putHead(final String key, final TemplateSubPatternAssociation assoc) {
        if (key.equals("#text")) {
            this.m_textPatterns = assoc;
        }
        else if (key.equals("/")) {
            this.m_docPatterns = assoc;
        }
        else if (key.equals("#comment")) {
            this.m_commentPatterns = assoc;
        }
        this.m_patternTable.put(key, assoc);
    }
    
    public class TemplateWalker
    {
        private Enumeration hashIterator;
        private boolean inPatterns;
        private TemplateSubPatternAssociation curPattern;
        private Hashtable m_compilerCache;
        
        private TemplateWalker() {
            this.m_compilerCache = new Hashtable();
            this.hashIterator = TemplateList.this.m_patternTable.elements();
            this.inPatterns = true;
            this.curPattern = null;
        }
        
        public ElemTemplate next() {
            ElemTemplate retValue = null;
            ElemTemplate ct;
            do {
                if (this.inPatterns) {
                    if (null != this.curPattern) {
                        this.curPattern = this.curPattern.getNext();
                    }
                    if (null != this.curPattern) {
                        retValue = this.curPattern.getTemplate();
                    }
                    else if (this.hashIterator.hasMoreElements()) {
                        this.curPattern = this.hashIterator.nextElement();
                        retValue = this.curPattern.getTemplate();
                    }
                    else {
                        this.inPatterns = false;
                        this.hashIterator = TemplateList.this.m_namedTemplates.elements();
                    }
                }
                if (!this.inPatterns) {
                    if (!this.hashIterator.hasMoreElements()) {
                        return null;
                    }
                    retValue = this.hashIterator.nextElement();
                }
                ct = this.m_compilerCache.get(new Integer(retValue.getUid()));
            } while (null != ct);
            this.m_compilerCache.put(new Integer(retValue.getUid()), retValue);
            return retValue;
        }
    }
}
