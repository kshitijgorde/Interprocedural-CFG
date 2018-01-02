// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.apache.xpath.XPath;
import org.apache.xpath.patterns.UnionPattern;
import org.apache.xpath.patterns.StepPattern;
import javax.xml.transform.TransformerException;
import org.apache.xml.utils.PrefixResolver;
import org.apache.xpath.Expression;
import org.apache.xpath.patterns.NodeTest;
import java.util.Enumeration;
import org.apache.xml.utils.QName;
import org.w3c.dom.Node;
import org.apache.xpath.XPathContext;
import java.util.Vector;
import java.util.Hashtable;
import java.io.Serializable;

public class TemplateList implements Serializable
{
    static boolean DEBUG;
    private Hashtable m_namedTemplates;
    private Hashtable m_patternTable;
    private TemplateSubPatternAssociation m_wildCardPatterns;
    private TemplateSubPatternAssociation m_textPatterns;
    private TemplateSubPatternAssociation m_docPatterns;
    private TemplateSubPatternAssociation m_commentPatterns;
    
    static {
        TemplateList.DEBUG = false;
    }
    
    public TemplateList() {
        this.m_namedTemplates = new Hashtable(89);
        this.m_patternTable = new Hashtable(89);
        this.m_wildCardPatterns = null;
        this.m_textPatterns = null;
        this.m_docPatterns = null;
        this.m_commentPatterns = null;
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
    
    private void checkConflicts(final TemplateSubPatternAssociation head, final XPathContext xctxt, final Node targetNode, final QName mode) {
    }
    
    public void compose() {
        if (TemplateList.DEBUG) {
            System.out.println("Before wildcard insert...");
            this.dumpAssociationTables();
        }
        if (this.m_wildCardPatterns != null) {
            final Enumeration associations = this.m_patternTable.elements();
            while (associations.hasMoreElements()) {
                TemplateSubPatternAssociation head = associations.nextElement();
                for (TemplateSubPatternAssociation wild = this.m_wildCardPatterns; wild != null; wild = wild.getNext()) {
                    try {
                        head = this.insertAssociationIntoList(head, (TemplateSubPatternAssociation)wild.clone(), true);
                    }
                    catch (CloneNotSupportedException ex) {}
                }
            }
        }
        if (TemplateList.DEBUG) {
            System.out.println("After wildcard insert...");
            this.dumpAssociationTables();
        }
    }
    
    void dumpAssociationTables() {
        final Enumeration associations = this.m_patternTable.elements();
        while (associations.hasMoreElements()) {
            for (TemplateSubPatternAssociation head = associations.nextElement(); head != null; head = head.getNext()) {
                System.out.print("(" + head.getTargetString() + ", " + head.getPattern() + ")");
            }
            System.out.println("\n.....");
        }
        TemplateSubPatternAssociation head = this.m_wildCardPatterns;
        System.out.print("wild card list: ");
        while (head != null) {
            System.out.print("(" + head.getTargetString() + ", " + head.getPattern() + ")");
            head = head.getNext();
        }
        System.out.println("\n.....");
    }
    
    private TemplateSubPatternAssociation getHead(final String key) {
        return this.m_patternTable.get(key);
    }
    
    public TemplateSubPatternAssociation getHead(final XPathContext xctxt, final Node targetNode) {
        final short targetNodeType = targetNode.getNodeType();
        TemplateSubPatternAssociation head = null;
        switch (targetNodeType) {
            case 1:
            case 2: {
                head = this.m_patternTable.get(xctxt.getDOMHelper().getLocalNameOfNode(targetNode));
                break;
            }
            case 3:
            case 4: {
                head = this.m_textPatterns;
                break;
            }
            case 5:
            case 6: {
                head = this.m_patternTable.get(targetNode.getNodeName());
                break;
            }
            case 7: {
                head = this.m_patternTable.get(xctxt.getDOMHelper().getLocalNameOfNode(targetNode));
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
                head = this.m_patternTable.get(targetNode.getNodeName());
                break;
            }
        }
        return (head == null) ? this.m_wildCardPatterns : head;
    }
    
    private Hashtable getNamedTemplates() {
        return this.m_namedTemplates;
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
    
    public ElemTemplate getTemplate(final XPathContext xctxt, final Node targetNode, final QName mode, final int maxImportLevel, final boolean quietConflictWarnings) throws TransformerException {
        TemplateSubPatternAssociation head = this.getHead(xctxt, targetNode);
        if (head != null) {
            final PrefixResolver savedPR = xctxt.getNamespaceContext();
            try {
                xctxt.pushCurrentNodeAndExpression(targetNode, targetNode);
                do {
                    if (maxImportLevel <= -1 || head.getImportLevel() <= maxImportLevel) {
                        final ElemTemplate template = head.getTemplate();
                        xctxt.setNamespaceContext(template);
                        if (head.m_stepPattern.execute(xctxt) != NodeTest.SCORE_NONE && head.matchMode(mode)) {
                            if (quietConflictWarnings) {
                                this.checkConflicts(head, xctxt, targetNode, mode);
                            }
                            return template;
                        }
                        continue;
                    }
                } while ((head = head.getNext()) != null);
            }
            finally {
                xctxt.popCurrentNodeAndExpression();
                xctxt.setNamespaceContext(savedPR);
            }
        }
        return null;
    }
    
    public TemplateWalker getWalker() {
        return new TemplateWalker((1)null);
    }
    
    private TemplateSubPatternAssociation insertAssociationIntoList(final TemplateSubPatternAssociation head, final TemplateSubPatternAssociation item, final boolean isWildCardInsert) {
        final double priority = this.getPriorityOrScore(item);
        final int importLevel = item.getImportLevel();
        final int docOrder = item.getDocOrderPos();
        TemplateSubPatternAssociation insertPoint = head;
        TemplateSubPatternAssociation next;
        while (true) {
            next = insertPoint.getNext();
            if (next == null) {
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
        if (next == null || insertPoint == head) {
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
        if (target != null) {
            final String pstring = template.getMatch().getPatternString();
            final TemplateSubPatternAssociation association = new TemplateSubPatternAssociation(template, pattern, pstring);
            final boolean isWildCard = association.isWild();
            final TemplateSubPatternAssociation head = isWildCard ? this.m_wildCardPatterns : this.getHead(target);
            if (head == null) {
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
    
    private void setNamedTemplates(final Hashtable v) {
        this.m_namedTemplates = v;
    }
    
    public void setTemplate(final ElemTemplate template) {
        if (template.getName() != null) {
            final ElemTemplate existingTemplate = this.m_namedTemplates.get(template.getName());
            if (existingTemplate == null) {
                this.m_namedTemplates.put(template.getName(), template);
            }
            else {
                final int existingPrecedence = existingTemplate.getStylesheetComposed().getImportCountComposed();
                final int newPrecedence = template.getStylesheetComposed().getImportCountComposed();
                if (newPrecedence > existingPrecedence) {
                    this.m_namedTemplates.put(template.getName(), template);
                }
                else if (newPrecedence == existingPrecedence) {
                    template.error(105, new Object[] { template.getName() });
                }
            }
        }
        final XPath matchXPath = template.getMatch();
        if (matchXPath != null) {
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
                    if (this.curPattern != null) {
                        this.curPattern = this.curPattern.getNext();
                    }
                    if (this.curPattern != null) {
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
            } while (ct != null);
            this.m_compilerCache.put(new Integer(retValue.getUid()), retValue);
            return retValue;
        }
    }
    
    static class 1
    {
    }
}
