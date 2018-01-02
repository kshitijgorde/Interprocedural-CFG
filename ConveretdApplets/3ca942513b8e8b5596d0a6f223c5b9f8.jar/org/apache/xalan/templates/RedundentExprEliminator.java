// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.apache.xpath.axes.SelfIteratorNoPredicate;
import org.apache.xpath.axes.WalkerFactory;
import org.apache.xalan.res.XSLMessages;
import org.w3c.dom.Node;
import org.apache.xpath.XPathVisitor;
import org.apache.xpath.XPath;
import org.apache.xpath.operations.VariableSafeAbsRef;
import org.w3c.dom.DOMException;
import org.apache.xml.utils.WrappedRuntimeException;
import org.apache.xpath.axes.FilterExprIteratorSimple;
import org.apache.xpath.axes.AxesWalker;
import org.apache.xpath.ExpressionNode;
import org.apache.xpath.operations.Variable;
import org.apache.xml.utils.QName;
import org.apache.xpath.axes.FilterExprWalker;
import org.apache.xpath.Expression;
import org.apache.xpath.axes.LocPathIterator;
import org.apache.xpath.axes.WalkingIterator;
import org.apache.xpath.ExpressionOwner;
import java.util.Vector;

public class RedundentExprEliminator extends XSLTVisitor
{
    Vector m_paths;
    Vector m_absPaths;
    boolean m_isSameContext;
    AbsPathChecker m_absPathChecker;
    private static int m_uniquePseudoVarID;
    static final String PSUEDOVARNAMESPACE = "http://xml.apache.org/xalan/psuedovar";
    public static final boolean DEBUG = false;
    public static final boolean DIAGNOSE_NUM_PATHS_REDUCED = false;
    public static final boolean DIAGNOSE_MULTISTEPLIST = false;
    VarNameCollector m_varNameCollector;
    
    public RedundentExprEliminator() {
        this.m_absPathChecker = new AbsPathChecker();
        this.m_varNameCollector = new VarNameCollector();
        this.m_isSameContext = true;
        this.m_absPaths = new Vector();
        this.m_paths = null;
    }
    
    public void eleminateRedundentLocals(final ElemTemplateElement psuedoVarRecipient) {
        this.eleminateRedundent(psuedoVarRecipient, this.m_paths);
    }
    
    public void eleminateRedundentGlobals(final StylesheetRoot stylesheet) {
        this.eleminateRedundent(stylesheet, this.m_absPaths);
    }
    
    protected void eleminateRedundent(final ElemTemplateElement psuedoVarRecipient, final Vector paths) {
        final int n = paths.size();
        int numPathsEliminated = 0;
        int numUniquePathsEliminated = 0;
        for (int i = 0; i < n; ++i) {
            final ExpressionOwner owner = paths.elementAt(i);
            if (null != owner) {
                final int found = this.findAndEliminateRedundant(i + 1, i, owner, psuedoVarRecipient, paths);
                if (found > 0) {
                    ++numUniquePathsEliminated;
                }
                numPathsEliminated += found;
            }
        }
        this.eleminateSharedPartialPaths(psuedoVarRecipient, paths);
    }
    
    protected void eleminateSharedPartialPaths(final ElemTemplateElement psuedoVarRecipient, final Vector paths) {
        MultistepExprHolder list = this.createMultistepExprList(paths);
        if (null != list) {
            final boolean isGlobal = paths == this.m_absPaths;
            final int longestStepsCount = list.m_stepCount;
            for (int i = longestStepsCount - 1; i >= 1; --i) {
                for (MultistepExprHolder next = list; null != next && next.m_stepCount >= i; next = next.m_next) {
                    list = this.matchAndEliminatePartialPaths(next, list, isGlobal, i, psuedoVarRecipient);
                }
            }
        }
    }
    
    protected MultistepExprHolder matchAndEliminatePartialPaths(final MultistepExprHolder testee, final MultistepExprHolder head, final boolean isGlobal, final int lengthToTest, final ElemTemplateElement varScope) {
        if (null == testee.m_exprOwner) {
            return head;
        }
        final WalkingIterator iter1 = (WalkingIterator)testee.m_exprOwner.getExpression();
        if (this.partialIsVariable(testee, lengthToTest)) {
            return head;
        }
        MultistepExprHolder matchedPaths = null;
        MultistepExprHolder matchedPathsTail = null;
        for (MultistepExprHolder meh = head; null != meh; meh = meh.m_next) {
            if (meh != testee && null != meh.m_exprOwner) {
                final WalkingIterator iter2 = (WalkingIterator)meh.m_exprOwner.getExpression();
                if (this.stepsEqual(iter1, iter2, lengthToTest)) {
                    if (null == matchedPaths) {
                        try {
                            matchedPaths = (MultistepExprHolder)testee.clone();
                            testee.m_exprOwner = null;
                        }
                        catch (CloneNotSupportedException ex) {}
                        matchedPathsTail = matchedPaths;
                        matchedPathsTail.m_next = null;
                    }
                    try {
                        matchedPathsTail.m_next = (MultistepExprHolder)meh.clone();
                        meh.m_exprOwner = null;
                    }
                    catch (CloneNotSupportedException ex2) {}
                    matchedPathsTail = matchedPathsTail.m_next;
                    matchedPathsTail.m_next = null;
                }
            }
        }
        final int matchCount = 0;
        if (null != matchedPaths) {
            final ElemTemplateElement root = isGlobal ? varScope : this.findCommonAncestor(matchedPaths);
            final WalkingIterator sharedIter = (WalkingIterator)matchedPaths.m_exprOwner.getExpression();
            final WalkingIterator newIter = this.createIteratorFromSteps(sharedIter, lengthToTest);
            final ElemVariable var = this.createPseudoVarDecl(root, newIter, isGlobal);
            while (null != matchedPaths) {
                final ExpressionOwner owner = matchedPaths.m_exprOwner;
                final WalkingIterator iter3 = (WalkingIterator)owner.getExpression();
                final LocPathIterator newIter2 = this.changePartToRef(var.getName(), iter3, lengthToTest, isGlobal);
                owner.setExpression(newIter2);
                matchedPaths = matchedPaths.m_next;
            }
        }
        return head;
    }
    
    boolean partialIsVariable(final MultistepExprHolder testee, final int lengthToTest) {
        if (1 == lengthToTest) {
            final WalkingIterator wi = (WalkingIterator)testee.m_exprOwner.getExpression();
            if (wi.getFirstWalker() instanceof FilterExprWalker) {
                return true;
            }
        }
        return false;
    }
    
    protected void diagnoseLineNumber(final Expression expr) {
        final ElemTemplateElement e = this.getElemFromExpression(expr);
        System.err.println("   " + e.getSystemId() + " Line " + e.getLineNumber());
    }
    
    protected ElemTemplateElement findCommonAncestor(final MultistepExprHolder head) {
        final int numExprs = head.getLength();
        final ElemTemplateElement[] elems = new ElemTemplateElement[numExprs];
        final int[] ancestorCounts = new int[numExprs];
        MultistepExprHolder next = head;
        int shortestAncestorCount = 10000;
        for (int i = 0; i < numExprs; ++i) {
            final ElemTemplateElement elem = this.getElemFromExpression(next.m_exprOwner.getExpression());
            elems[i] = elem;
            final int numAncestors = this.countAncestors(elem);
            if ((ancestorCounts[i] = numAncestors) < shortestAncestorCount) {
                shortestAncestorCount = numAncestors;
            }
            next = next.m_next;
        }
        for (int j = 0; j < numExprs; ++j) {
            if (ancestorCounts[j] > shortestAncestorCount) {
                for (int numStepCorrection = ancestorCounts[j] - shortestAncestorCount, k = 0; k < numStepCorrection; ++k) {
                    elems[j] = elems[j].getParentElem();
                }
            }
        }
        ElemTemplateElement first = null;
        while (shortestAncestorCount-- >= 0) {
            boolean areEqual = true;
            first = elems[0];
            for (int l = 1; l < numExprs; ++l) {
                if (first != elems[l]) {
                    areEqual = false;
                    break;
                }
            }
            if (areEqual && this.isNotSameAsOwner(head, first) && first.canAcceptVariables()) {
                return first;
            }
            for (int m = 0; m < numExprs; ++m) {
                elems[m] = elems[m].getParentElem();
            }
        }
        assertion(false, "Could not find common ancestor!!!");
        return null;
    }
    
    protected boolean isNotSameAsOwner(final MultistepExprHolder head, final ElemTemplateElement ete) {
        for (MultistepExprHolder next = head; null != next; next = next.m_next) {
            final ElemTemplateElement elemOwner = this.getElemFromExpression(next.m_exprOwner.getExpression());
            if (elemOwner == ete) {
                return false;
            }
        }
        return true;
    }
    
    protected int countAncestors(ElemTemplateElement elem) {
        int count = 0;
        while (null != elem) {
            ++count;
            elem = elem.getParentElem();
        }
        return count;
    }
    
    protected void diagnoseMultistepList(final int matchCount, final int lengthToTest, final boolean isGlobal) {
        if (matchCount > 0) {
            System.err.print("Found multistep matches: " + matchCount + ", " + lengthToTest + " length");
            if (isGlobal) {
                System.err.println(" (global)");
            }
            else {
                System.err.println();
            }
        }
    }
    
    protected LocPathIterator changePartToRef(final QName uniquePseudoVarName, final WalkingIterator wi, final int numSteps, final boolean isGlobal) {
        final Variable var = new Variable();
        var.setQName(uniquePseudoVarName);
        var.setIsGlobal(isGlobal);
        if (isGlobal) {
            final ElemTemplateElement elem = this.getElemFromExpression(wi);
            final StylesheetRoot root = elem.getStylesheetRoot();
            final Vector vars = root.getVariablesAndParamsComposed();
            var.setIndex(vars.size() - 1);
        }
        AxesWalker walker = wi.getFirstWalker();
        for (int i = 0; i < numSteps; ++i) {
            assertion(null != walker, "Walker should not be null!");
            walker = walker.getNextWalker();
        }
        if (null != walker) {
            final FilterExprWalker few = new FilterExprWalker(wi);
            few.setInnerExpression(var);
            few.exprSetParent(wi);
            few.setNextWalker(walker);
            walker.setPrevWalker(few);
            wi.setFirstWalker(few);
            return wi;
        }
        final FilterExprIteratorSimple feis = new FilterExprIteratorSimple(var);
        feis.exprSetParent(wi.exprGetParent());
        return feis;
    }
    
    protected WalkingIterator createIteratorFromSteps(final WalkingIterator wi, final int numSteps) {
        final WalkingIterator newIter = new WalkingIterator(wi.getPrefixResolver());
        try {
            AxesWalker walker = (AxesWalker)wi.getFirstWalker().clone();
            newIter.setFirstWalker(walker);
            walker.setLocPathIterator(newIter);
            for (int i = 1; i < numSteps; ++i) {
                final AxesWalker next = (AxesWalker)walker.getNextWalker().clone();
                walker.setNextWalker(next);
                next.setLocPathIterator(newIter);
                walker = next;
            }
            walker.setNextWalker(null);
        }
        catch (CloneNotSupportedException cnse) {
            throw new WrappedRuntimeException(cnse);
        }
        return newIter;
    }
    
    protected boolean stepsEqual(final WalkingIterator iter1, final WalkingIterator iter2, final int numSteps) {
        AxesWalker aw1 = iter1.getFirstWalker();
        AxesWalker aw2 = iter2.getFirstWalker();
        for (int i = 0; i < numSteps; ++i) {
            if (null == aw1 || null == aw2) {
                return false;
            }
            if (!aw1.deepEquals(aw2)) {
                return false;
            }
            aw1 = aw1.getNextWalker();
            aw2 = aw2.getNextWalker();
        }
        assertion(null != aw1 || null != aw2, "Total match is incorrect!");
        return true;
    }
    
    protected MultistepExprHolder createMultistepExprList(final Vector paths) {
        MultistepExprHolder first = null;
        for (int n = paths.size(), i = 0; i < n; ++i) {
            final ExpressionOwner eo = paths.elementAt(i);
            if (null != eo) {
                final LocPathIterator lpi = (LocPathIterator)eo.getExpression();
                final int numPaths = this.countSteps(lpi);
                if (numPaths > 1) {
                    if (null == first) {
                        first = new MultistepExprHolder(eo, numPaths, null);
                    }
                    else {
                        first = first.addInSortedOrder(eo, numPaths);
                    }
                }
            }
        }
        if (null == first || first.getLength() <= 1) {
            return null;
        }
        return first;
    }
    
    protected int findAndEliminateRedundant(final int start, final int firstOccuranceIndex, final ExpressionOwner firstOccuranceOwner, final ElemTemplateElement psuedoVarRecipient, final Vector paths) throws DOMException {
        MultistepExprHolder head = null;
        MultistepExprHolder tail = null;
        int numPathsFound = 0;
        final int n = paths.size();
        final Expression expr1 = firstOccuranceOwner.getExpression();
        final boolean isGlobal = paths == this.m_absPaths;
        final LocPathIterator lpi = (LocPathIterator)expr1;
        final int stepCount = this.countSteps(lpi);
        for (int j = start; j < n; ++j) {
            final ExpressionOwner owner2 = paths.elementAt(j);
            if (null != owner2) {
                final Expression expr2 = owner2.getExpression();
                final boolean isEqual = expr2.deepEquals(lpi);
                if (isEqual) {
                    final LocPathIterator lpi2 = (LocPathIterator)expr2;
                    if (null == head) {
                        head = (tail = new MultistepExprHolder(firstOccuranceOwner, stepCount, null));
                        ++numPathsFound;
                    }
                    tail.m_next = new MultistepExprHolder(owner2, stepCount, null);
                    tail = tail.m_next;
                    paths.setElementAt(null, j);
                    ++numPathsFound;
                }
            }
        }
        if (0 == numPathsFound && isGlobal) {
            head = new MultistepExprHolder(firstOccuranceOwner, stepCount, null);
            ++numPathsFound;
        }
        if (null != head) {
            final ElemTemplateElement root = isGlobal ? psuedoVarRecipient : this.findCommonAncestor(head);
            final LocPathIterator sharedIter = (LocPathIterator)head.m_exprOwner.getExpression();
            final ElemVariable var = this.createPseudoVarDecl(root, sharedIter, isGlobal);
            final QName uniquePseudoVarName = var.getName();
            while (null != head) {
                final ExpressionOwner owner3 = head.m_exprOwner;
                this.changeToVarRef(uniquePseudoVarName, owner3, paths, root);
                head = head.m_next;
            }
            paths.setElementAt(var.getSelect(), firstOccuranceIndex);
        }
        return numPathsFound;
    }
    
    protected int oldFindAndEliminateRedundant(final int start, final int firstOccuranceIndex, final ExpressionOwner firstOccuranceOwner, final ElemTemplateElement psuedoVarRecipient, final Vector paths) throws DOMException {
        QName uniquePseudoVarName = null;
        boolean foundFirst = false;
        int numPathsFound = 0;
        final int n = paths.size();
        final Expression expr1 = firstOccuranceOwner.getExpression();
        final boolean isGlobal = paths == this.m_absPaths;
        final LocPathIterator lpi = (LocPathIterator)expr1;
        for (int j = start; j < n; ++j) {
            final ExpressionOwner owner2 = paths.elementAt(j);
            if (null != owner2) {
                final Expression expr2 = owner2.getExpression();
                final boolean isEqual = expr2.deepEquals(lpi);
                if (isEqual) {
                    final LocPathIterator lpi2 = (LocPathIterator)expr2;
                    if (!foundFirst) {
                        foundFirst = true;
                        final ElemVariable var = this.createPseudoVarDecl(psuedoVarRecipient, lpi, isGlobal);
                        if (null == var) {
                            return 0;
                        }
                        uniquePseudoVarName = var.getName();
                        this.changeToVarRef(uniquePseudoVarName, firstOccuranceOwner, paths, psuedoVarRecipient);
                        paths.setElementAt(var.getSelect(), firstOccuranceIndex);
                        ++numPathsFound;
                    }
                    this.changeToVarRef(uniquePseudoVarName, owner2, paths, psuedoVarRecipient);
                    paths.setElementAt(null, j);
                    ++numPathsFound;
                }
            }
        }
        if (0 == numPathsFound && paths == this.m_absPaths) {
            final ElemVariable var2 = this.createPseudoVarDecl(psuedoVarRecipient, lpi, true);
            if (null == var2) {
                return 0;
            }
            uniquePseudoVarName = var2.getName();
            this.changeToVarRef(uniquePseudoVarName, firstOccuranceOwner, paths, psuedoVarRecipient);
            paths.setElementAt(var2.getSelect(), firstOccuranceIndex);
            ++numPathsFound;
        }
        return numPathsFound;
    }
    
    protected int countSteps(final LocPathIterator lpi) {
        if (lpi instanceof WalkingIterator) {
            final WalkingIterator wi = (WalkingIterator)lpi;
            AxesWalker aw = wi.getFirstWalker();
            int count = 0;
            while (null != aw) {
                ++count;
                aw = aw.getNextWalker();
            }
            return count;
        }
        return 1;
    }
    
    protected void changeToVarRef(final QName varName, final ExpressionOwner owner, final Vector paths, final ElemTemplateElement psuedoVarRecipient) {
        final Variable varRef = (paths == this.m_absPaths) ? new VariableSafeAbsRef() : new Variable();
        varRef.setQName(varName);
        if (paths == this.m_absPaths) {
            final StylesheetRoot root = (StylesheetRoot)psuedoVarRecipient;
            final Vector globalVars = root.getVariablesAndParamsComposed();
            varRef.setIndex(globalVars.size() - 1);
            varRef.setIsGlobal(true);
        }
        owner.setExpression(varRef);
    }
    
    private static synchronized int getPseudoVarID() {
        return RedundentExprEliminator.m_uniquePseudoVarID++;
    }
    
    protected ElemVariable createPseudoVarDecl(final ElemTemplateElement psuedoVarRecipient, final LocPathIterator lpi, final boolean isGlobal) throws DOMException {
        final QName uniquePseudoVarName = new QName("http://xml.apache.org/xalan/psuedovar", "#" + getPseudoVarID());
        if (isGlobal) {
            return this.createGlobalPseudoVarDecl(uniquePseudoVarName, (StylesheetRoot)psuedoVarRecipient, lpi);
        }
        return this.createLocalPseudoVarDecl(uniquePseudoVarName, psuedoVarRecipient, lpi);
    }
    
    protected ElemVariable createGlobalPseudoVarDecl(final QName uniquePseudoVarName, final StylesheetRoot stylesheetRoot, final LocPathIterator lpi) throws DOMException {
        final ElemVariable psuedoVar = new ElemVariable();
        psuedoVar.setIsTopLevel(true);
        final XPath xpath = new XPath(lpi);
        psuedoVar.setSelect(xpath);
        psuedoVar.setName(uniquePseudoVarName);
        final Vector globalVars = stylesheetRoot.getVariablesAndParamsComposed();
        psuedoVar.setIndex(globalVars.size());
        globalVars.addElement(psuedoVar);
        return psuedoVar;
    }
    
    protected ElemVariable createLocalPseudoVarDecl(final QName uniquePseudoVarName, final ElemTemplateElement psuedoVarRecipient, final LocPathIterator lpi) throws DOMException {
        final ElemVariable psuedoVar = new ElemVariablePsuedo();
        final XPath xpath = new XPath(lpi);
        psuedoVar.setSelect(xpath);
        psuedoVar.setName(uniquePseudoVarName);
        final ElemVariable var = this.addVarDeclToElem(psuedoVarRecipient, lpi, psuedoVar);
        lpi.exprSetParent(var);
        return var;
    }
    
    protected ElemVariable addVarDeclToElem(ElemTemplateElement psuedoVarRecipient, final LocPathIterator lpi, final ElemVariable psuedoVar) throws DOMException {
        ElemTemplateElement ete = psuedoVarRecipient.getFirstChildElem();
        lpi.callVisitors(null, this.m_varNameCollector);
        if (this.m_varNameCollector.getVarCount() > 0) {
            final ElemTemplateElement baseElem = this.getElemFromExpression(lpi);
            for (ElemVariable varElem = this.getPrevVariableElem(baseElem); null != varElem; varElem = this.getPrevVariableElem(varElem)) {
                if (this.m_varNameCollector.doesOccur(varElem.getName())) {
                    psuedoVarRecipient = varElem.getParentElem();
                    ete = varElem.getNextSiblingElem();
                    break;
                }
            }
        }
        if (null != ete && 41 == ete.getXSLToken()) {
            if (this.isParam(lpi)) {
                return null;
            }
            while (null != ete) {
                ete = ete.getNextSiblingElem();
                if (null != ete && 41 != ete.getXSLToken()) {
                    break;
                }
            }
        }
        psuedoVarRecipient.insertBefore(psuedoVar, ete);
        this.m_varNameCollector.reset();
        return psuedoVar;
    }
    
    protected boolean isParam(ExpressionNode expr) {
        while (null != expr && !(expr instanceof ElemTemplateElement)) {
            expr = expr.exprGetParent();
        }
        if (null != expr) {
            ElemTemplateElement ete = (ElemTemplateElement)expr;
            while (null != ete) {
                final int type = ete.getXSLToken();
                switch (type) {
                    case 41: {
                        return true;
                    }
                    case 19:
                    case 25: {
                        return false;
                    }
                    default: {
                        ete = ete.getParentElem();
                        continue;
                    }
                }
            }
        }
        return false;
    }
    
    protected ElemVariable getPrevVariableElem(ElemTemplateElement elem) {
        while (null != (elem = this.getPrevElementWithinContext(elem))) {
            final int type = elem.getXSLToken();
            if (73 == type || 41 == type) {
                return (ElemVariable)elem;
            }
        }
        return null;
    }
    
    protected ElemTemplateElement getPrevElementWithinContext(final ElemTemplateElement elem) {
        ElemTemplateElement prev = elem.getPreviousSiblingElem();
        if (null == prev) {
            prev = elem.getParentElem();
        }
        if (null != prev) {
            final int type = prev.getXSLToken();
            if (28 == type || 19 == type || 25 == type) {
                prev = null;
            }
        }
        return prev;
    }
    
    protected ElemTemplateElement getElemFromExpression(final Expression expr) {
        for (ExpressionNode parent = expr.exprGetParent(); null != parent; parent = parent.exprGetParent()) {
            if (parent instanceof ElemTemplateElement) {
                return (ElemTemplateElement)parent;
            }
        }
        throw new RuntimeException(XSLMessages.createMessage("ER_ASSERT_NO_TEMPLATE_PARENT", null));
    }
    
    public boolean isAbsolute(final LocPathIterator path) {
        final int analysis = path.getAnalysisBits();
        boolean isAbs = WalkerFactory.isSet(analysis, 134217728) || WalkerFactory.isSet(analysis, 536870912);
        if (isAbs) {
            isAbs = this.m_absPathChecker.checkAbsolute(path);
        }
        return isAbs;
    }
    
    public boolean visitLocationPath(final ExpressionOwner owner, final LocPathIterator path) {
        if (path instanceof SelfIteratorNoPredicate) {
            return true;
        }
        if (path instanceof WalkingIterator) {
            final WalkingIterator wi = (WalkingIterator)path;
            final AxesWalker aw = wi.getFirstWalker();
            if (aw instanceof FilterExprWalker && null == aw.getNextWalker()) {
                final FilterExprWalker few = (FilterExprWalker)aw;
                final Expression exp = few.getInnerExpression();
                if (exp instanceof Variable) {
                    return true;
                }
            }
        }
        if (this.isAbsolute(path) && null != this.m_absPaths) {
            this.m_absPaths.addElement(owner);
        }
        else if (this.m_isSameContext && null != this.m_paths) {
            this.m_paths.addElement(owner);
        }
        return true;
    }
    
    public boolean visitPredicate(final ExpressionOwner owner, final Expression pred) {
        final boolean savedIsSame = this.m_isSameContext;
        this.m_isSameContext = false;
        pred.callVisitors(owner, this);
        this.m_isSameContext = savedIsSame;
        return false;
    }
    
    public boolean visitTopLevelInstruction(final ElemTemplateElement elem) {
        final int type = elem.getXSLToken();
        switch (type) {
            case 19: {
                return this.visitInstruction(elem);
            }
            default: {
                return true;
            }
        }
    }
    
    public boolean visitInstruction(final ElemTemplateElement elem) {
        final int type = elem.getXSLToken();
        switch (type) {
            case 17:
            case 19:
            case 28: {
                if (type == 28) {
                    final ElemForEach efe = (ElemForEach)elem;
                    final Expression select = efe.getSelect();
                    select.callVisitors(efe, this);
                }
                final Vector savedPaths = this.m_paths;
                this.m_paths = new Vector();
                elem.callChildVisitors(this, false);
                this.eleminateRedundentLocals(elem);
                this.m_paths = savedPaths;
                return false;
            }
            case 35:
            case 64: {
                final boolean savedIsSame = this.m_isSameContext;
                this.m_isSameContext = false;
                elem.callChildVisitors(this);
                this.m_isSameContext = savedIsSame;
                return false;
            }
            default: {
                return true;
            }
        }
    }
    
    protected void diagnoseNumPaths(final Vector paths, final int numPathsEliminated, final int numUniquePathsEliminated) {
        if (numPathsEliminated > 0) {
            if (paths == this.m_paths) {
                System.err.println("Eliminated " + numPathsEliminated + " total paths!");
                System.err.println("Consolodated " + numUniquePathsEliminated + " redundent paths!");
            }
            else {
                System.err.println("Eliminated " + numPathsEliminated + " total global paths!");
                System.err.println("Consolodated " + numUniquePathsEliminated + " redundent global paths!");
            }
        }
    }
    
    private final void assertIsLocPathIterator(final Expression expr1, final ExpressionOwner eo) throws RuntimeException {
        if (!(expr1 instanceof LocPathIterator)) {
            String errMsg;
            if (expr1 instanceof Variable) {
                errMsg = "Programmer's assertion: expr1 not an iterator: " + ((Variable)expr1).getQName();
            }
            else {
                errMsg = "Programmer's assertion: expr1 not an iterator: " + expr1.getClass().getName();
            }
            throw new RuntimeException(errMsg + ", " + eo.getClass().getName() + " " + expr1.exprGetParent());
        }
    }
    
    private static void validateNewAddition(final Vector paths, final ExpressionOwner owner, final LocPathIterator path) throws RuntimeException {
        assertion(owner.getExpression() == path, "owner.getExpression() != path!!!");
        for (int n = paths.size(), i = 0; i < n; ++i) {
            final ExpressionOwner ew = paths.elementAt(i);
            assertion(ew != owner, "duplicate owner on the list!!!");
            assertion(ew.getExpression() != path, "duplicate expression on the list!!!");
        }
    }
    
    protected static void assertion(final boolean b, final String msg) {
        if (!b) {
            throw new RuntimeException(XSLMessages.createMessage("ER_ASSERT_REDUNDENT_EXPR_ELIMINATOR", new Object[] { msg }));
        }
    }
    
    static {
        RedundentExprEliminator.m_uniquePseudoVarID = 1;
    }
    
    class MultistepExprHolder implements Cloneable
    {
        ExpressionOwner m_exprOwner;
        final int m_stepCount;
        MultistepExprHolder m_next;
        
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
        
        MultistepExprHolder(final ExpressionOwner exprOwner, final int stepCount, final MultistepExprHolder next) {
            this.m_exprOwner = exprOwner;
            RedundentExprEliminator.assertion(null != this.m_exprOwner, "exprOwner can not be null!");
            this.m_stepCount = stepCount;
            this.m_next = next;
        }
        
        MultistepExprHolder addInSortedOrder(final ExpressionOwner exprOwner, final int stepCount) {
            MultistepExprHolder first = this;
            MultistepExprHolder next = this;
            MultistepExprHolder prev = null;
            while (null != next) {
                if (stepCount >= next.m_stepCount) {
                    final MultistepExprHolder newholder = new MultistepExprHolder(exprOwner, stepCount, next);
                    if (null == prev) {
                        first = newholder;
                    }
                    else {
                        prev.m_next = newholder;
                    }
                    return first;
                }
                prev = next;
                next = next.m_next;
            }
            prev.m_next = new MultistepExprHolder(exprOwner, stepCount, null);
            return first;
        }
        
        MultistepExprHolder unlink(final MultistepExprHolder itemToRemove) {
            MultistepExprHolder first = this;
            MultistepExprHolder next = this;
            MultistepExprHolder prev = null;
            while (null != next) {
                if (next == itemToRemove) {
                    if (null == prev) {
                        first = next.m_next;
                    }
                    else {
                        prev.m_next = next.m_next;
                    }
                    next.m_next = null;
                    return first;
                }
                prev = next;
                next = next.m_next;
            }
            RedundentExprEliminator.assertion(false, "unlink failed!!!");
            return null;
        }
        
        int getLength() {
            int count = 0;
            for (MultistepExprHolder next = this; null != next; next = next.m_next) {
                ++count;
            }
            return count;
        }
        
        protected void diagnose() {
            System.err.print("Found multistep iterators: " + this.getLength() + "  ");
            MultistepExprHolder next = this;
            while (null != next) {
                System.err.print("" + next.m_stepCount);
                next = next.m_next;
                if (null != next) {
                    System.err.print(", ");
                }
            }
            System.err.println();
        }
    }
}
