// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.axes;

import org.apache.xalan.stree.Child;
import java.util.StringTokenizer;
import org.w3c.dom.DOMException;
import javax.xml.transform.TransformerException;
import org.apache.xpath.compiler.Compiler;
import org.apache.xpath.patterns.NodeTest;
import org.apache.xpath.patterns.NodeTestFilter;
import org.apache.xpath.XPathContext;
import org.w3c.dom.NamedNodeMap;
import java.util.Vector;
import org.apache.xpath.DOMHelper;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.TreeWalker;

public abstract class AxesWalker extends PredicatedNodeTest implements Cloneable, TreeWalker, NodeFilter
{
    static final boolean DEBUG = false;
    static final boolean DEBUG_WAITING = false;
    static final boolean DEBUG_TRAVERSAL = false;
    static final boolean DEBUG_LOCATED = false;
    static boolean m_didDumpAll;
    public static final String FEATURE_NODETESTFILTER = "NodeTestFilter";
    transient Node m_root;
    transient Node m_currentNode;
    transient Node m_prevReturned;
    private int m_argLen;
    private int m_stepType;
    private transient boolean m_didSwitch;
    transient boolean m_isDone;
    transient boolean m_isFresh;
    protected transient int m_nextLevelAmount;
    protected AxesWalker m_nextWalker;
    AxesWalker m_prevWalker;
    
    static {
        AxesWalker.m_didDumpAll = false;
    }
    
    public AxesWalker(final LocPathIterator locPathIterator) {
        super(locPathIterator);
        this.m_didSwitch = false;
        this.m_isDone = false;
    }
    
    private final void addToWaitList(final AxesWalker walker) {
        super.m_lpi.addToWaitList(walker);
    }
    
    public boolean canTraverseOutsideSubtree() {
        return super.canTraverseOutsideSubtree() || (this.m_nextWalker != null && this.m_nextWalker.canTraverseOutsideSubtree());
    }
    
    AxesWalker checkNeedsToWait(AxesWalker walker) {
        final AxesWalker prevWalker = walker.m_prevWalker;
        if (prevWalker != null && !this.checkOKToTraverse(prevWalker, walker, walker.m_currentNode, walker.m_nextLevelAmount)) {
            if (this.isWaiting(walker)) {
                try {
                    this.addToWaitList((AxesWalker)walker.clone());
                }
                catch (CloneNotSupportedException ex) {}
            }
            else {
                this.addToWaitList(walker);
            }
            walker = walker.m_prevWalker;
        }
        return walker;
    }
    
    protected boolean checkOKToTraverse(final AxesWalker prevStepWalker, final AxesWalker testWalker, final Node currentTestNode, final int nextLevelAmount) {
        final DOMHelper dh = super.m_lpi.getDOMHelper();
        final int level = dh.getLevel(currentTestNode);
        final Node prevNode = prevStepWalker.m_currentNode;
        boolean ok;
        if (!prevStepWalker.m_isDone && prevStepWalker.getLevelMax() > level) {
            final boolean isNodeAfter = dh.isNodeAfter(prevNode, currentTestNode) ^ true;
            if (isNodeAfter) {
                final int prevStepLevel = dh.getLevel(prevNode);
                ok = (prevStepLevel <= level + nextLevelAmount);
            }
            else {
                ok = false;
            }
        }
        else {
            ok = true;
        }
        return ok;
    }
    
    AxesWalker checkWaiting(AxesWalker walker) {
        if (walker != null && walker.m_currentNode == null) {
            return walker;
        }
        for (int nWaiting = super.m_lpi.getWaitingCount(), i = super.m_lpi.m_waitingBottom; i < nWaiting; ++i) {
            final AxesWalker ws = super.m_lpi.getWaiting(i);
            final AxesWalker prevStepWalker = ws.m_prevWalker;
            if (prevStepWalker != null && this.checkOKToTraverse(prevStepWalker, ws, ws.m_currentNode, ws.m_nextLevelAmount)) {
                if (walker != null) {
                    final AxesWalker deferedWalker = walker;
                    if (!this.isWaiting(deferedWalker)) {
                        this.addToWaitList(deferedWalker);
                    }
                }
                walker = ws;
                super.m_lpi.removeFromWaitList(walker);
                walker.printEntryDebug();
                this.m_didSwitch = true;
                break;
            }
        }
        return walker;
    }
    
    public Object clone() throws CloneNotSupportedException {
        final AxesWalker clone = (AxesWalker)super.clone();
        return clone;
    }
    
    AxesWalker cloneDeep(final LocPathIterator cloneOwner, final Vector cloneList) throws CloneNotSupportedException {
        AxesWalker clone = findClone(this, cloneList);
        if (clone != null) {
            return clone;
        }
        clone = (AxesWalker)this.clone();
        clone.setLocPathIterator(cloneOwner);
        if (cloneList != null) {
            cloneList.addElement(this);
            cloneList.addElement(clone);
        }
        if (super.m_lpi.m_lastUsedWalker == this) {
            cloneOwner.m_lastUsedWalker = clone;
        }
        if (this.m_nextWalker != null) {
            clone.m_nextWalker = this.m_nextWalker.cloneDeep(cloneOwner, cloneList);
        }
        if (cloneList != null) {
            if (this.m_prevWalker != null) {
                clone.m_prevWalker = this.m_prevWalker.cloneDeep(cloneOwner, cloneList);
            }
        }
        else if (this.m_nextWalker != null) {
            clone.m_nextWalker.m_prevWalker = clone;
        }
        return clone;
    }
    
    private void dumpAll(final Node node, final int indent) {
        for (int i = 0; i < indent; ++i) {
            System.out.print(" ");
        }
        System.out.print(this.nodeToString(node));
        if (node.getNodeType() == 3) {
            final String value = node.getNodeValue();
            if (value != null) {
                System.out.print("+= -->" + value.trim());
            }
        }
        System.out.println("");
        final NamedNodeMap map = node.getAttributes();
        if (map != null) {
            for (int n = map.getLength(), j = 0; j < n; ++j) {
                for (int k = 0; k < indent; ++k) {
                    System.out.print(" ");
                }
                System.out.print("attr -->");
                System.out.print(this.nodeToString(map.item(j)));
                final String value2 = map.item(j).getNodeValue();
                if (value2 != null) {
                    System.out.print("+= -->" + value2.trim());
                }
                System.out.println("");
            }
        }
        for (Node child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
            this.dumpAll(child, indent + 1);
        }
    }
    
    static AxesWalker findClone(final AxesWalker key, final Vector cloneList) {
        if (cloneList != null) {
            for (int n = cloneList.size(), i = 0; i < n; i += 2) {
                if (key == cloneList.elementAt(i)) {
                    return cloneList.elementAt(i + 1);
                }
            }
        }
        return null;
    }
    
    public Node firstChild() {
        return null;
    }
    
    protected int getArgLen() {
        return this.m_argLen;
    }
    
    public final Node getCurrentNode() {
        return this.m_currentNode;
    }
    
    private AxesWalker getEarliestWaiting() {
        final DOMHelper dh = super.m_lpi.getDOMHelper();
        AxesWalker first = null;
        for (int nWaiting = super.m_lpi.getWaitingCount(), i = super.m_lpi.m_waitingBottom; i < nWaiting; ++i) {
            final AxesWalker ws = super.m_lpi.getWaiting(i);
            if (first == null) {
                first = ws;
            }
            else if (!dh.isNodeAfter(ws.m_currentNode, first.m_currentNode)) {
                first = ws;
            }
        }
        if (first != null) {
            super.m_lpi.removeFromWaitList(first);
            first.printEntryDebug();
        }
        return first;
    }
    
    public boolean getExpandEntityReferences() {
        return true;
    }
    
    public NodeFilter getFilter() {
        return this;
    }
    
    public int getLastPos(final XPathContext xctxt) {
        int pos = this.getProximityPosition();
        AxesWalker walker;
        try {
            walker = (AxesWalker)this.clone();
        }
        catch (CloneNotSupportedException ex) {
            return -1;
        }
        walker.setPredicateCount(walker.getPredicateCount() - 1);
        walker.setNextWalker(null);
        walker.setPrevWalker(null);
        final LocPathIterator lpi = walker.getLocPathIterator();
        final AxesWalker savedWalker = lpi.getLastUsedWalker();
        try {
            lpi.setLastUsedWalker(walker);
            Node next;
            while ((next = walker.nextNode()) != null) {
                ++pos;
            }
        }
        finally {
            lpi.setLastUsedWalker(savedWalker);
        }
        return pos;
    }
    
    protected int getLevelMax() {
        return 0;
    }
    
    protected int getNextLevelAmount() {
        return this.m_nextLevelAmount;
    }
    
    protected Node getNextNode() {
        if (this.m_isFresh) {
            this.m_isFresh = false;
        }
        final Node current = this.getCurrentNode();
        if (current.isSupported("NodeTestFilter", "1.0")) {
            ((NodeTestFilter)current).setNodeTest(this);
        }
        Node next = this.firstChild();
        while (next == null) {
            next = this.nextSibling();
            if (next == null) {
                final Node p = this.parentNode();
                if (p == null) {
                    break;
                }
                continue;
            }
        }
        if (next == null) {
            this.m_isDone = true;
        }
        return next;
    }
    
    public AxesWalker getNextWalker() {
        return this.m_nextWalker;
    }
    
    public AxesWalker getPrevWalker() {
        return this.m_prevWalker;
    }
    
    public Node getRoot() {
        return this.m_root;
    }
    
    protected int getStepType() {
        return this.m_stepType;
    }
    
    public void init(final Compiler compiler, final int opPos, final int stepType) throws TransformerException {
        switch (this.m_stepType = stepType) {
            case 22:
            case 23:
            case 24:
            case 25: {
                this.m_argLen = compiler.getArgLength(opPos);
                break;
            }
            default: {
                this.m_argLen = compiler.getArgLengthOfStep(opPos);
                break;
            }
        }
        this.initPredicateInfo(compiler, opPos);
    }
    
    boolean isAncestorOfRootContext(final Node n) {
        Node parent = this.m_root;
        while ((parent = parent.getParentNode()) != null) {
            if (parent.equals(n)) {
                return true;
            }
        }
        return false;
    }
    
    protected boolean isFastWalker() {
        return false;
    }
    
    boolean isWaiting(final AxesWalker walker) {
        for (int nWaiting = super.m_lpi.getWaitingCount(), i = super.m_lpi.m_waitingBottom; i < nWaiting; ++i) {
            final AxesWalker ws = super.m_lpi.getWaiting(i);
            if (ws == walker) {
                return true;
            }
        }
        return false;
    }
    
    public Node lastChild() {
        throw new RuntimeException("lastChild not supported!");
    }
    
    public Node nextNode() {
        Node nextNode = null;
        AxesWalker walker = super.m_lpi.getLastUsedWalker();
        this.m_didSwitch = false;
        boolean processWaiters = true;
        while (true) {
            if (processWaiters) {
                AxesWalker waiting = this.checkWaiting(walker);
                if (this.m_didSwitch) {
                    this.m_didSwitch = false;
                    walker = waiting;
                }
                else if (walker != null) {
                    waiting = this.checkNeedsToWait(walker);
                    if (waiting != walker) {
                        walker = waiting;
                        continue;
                    }
                }
            }
            else {
                processWaiters = true;
            }
            if (walker != null) {
                nextNode = walker.getNextNode();
                if (nextNode == null) {
                    walker = walker.m_prevWalker;
                    if (walker != null) {
                        walker.printEntryDebug();
                        continue;
                    }
                    walker = this.getEarliestWaiting();
                    if (walker != null) {
                        processWaiters = false;
                        continue;
                    }
                    continue;
                }
                else {
                    if (walker.acceptNode(nextNode) != 1) {
                        continue;
                    }
                    if (walker.m_nextWalker != null) {
                        final AxesWalker prev = walker;
                        walker = walker.m_nextWalker;
                        if (this.isWaiting(walker)) {
                            try {
                                walker = (AxesWalker)walker.clone();
                                walker.setRoot(nextNode);
                            }
                            catch (CloneNotSupportedException ex) {}
                        }
                        else {
                            walker.setRoot(nextNode);
                        }
                        walker.m_prevWalker = prev;
                        walker.printEntryDebug();
                        continue;
                    }
                    super.m_lpi.setLastUsedWalker(walker);
                }
            }
            if (nextNode == null || this.m_prevReturned == null || nextNode.getOwnerDocument() != this.m_prevReturned.getOwnerDocument() || !super.m_lpi.getDOMHelper().isNodeAfter(nextNode, this.m_prevReturned)) {
                break;
            }
        }
        return this.m_prevReturned = nextNode;
    }
    
    public Node nextSibling() {
        return null;
    }
    
    public Node parentNode() {
        return null;
    }
    
    public Node previousNode() {
        throw new RuntimeException("previousNode not supported!");
    }
    
    public Node previousSibling() {
        throw new RuntimeException("previousSibling not supported!");
    }
    
    private void printDebug(final String s) {
    }
    
    private void printDebugAdd(final String s) {
    }
    
    private void printEntryDebug() {
    }
    
    private void printWaiters() {
    }
    
    private Node returnNextNode(final Node n) {
        return n;
    }
    
    protected Node setCurrentIfNotNull(final Node currentNode) throws DOMException {
        if (currentNode != null) {
            this.m_currentNode = currentNode;
        }
        return currentNode;
    }
    
    public void setCurrentNode(final Node currentNode) throws DOMException {
        this.m_currentNode = currentNode;
    }
    
    public void setNextWalker(final AxesWalker walker) {
        this.m_nextWalker = walker;
    }
    
    public void setPrevWalker(final AxesWalker walker) {
        this.m_prevWalker = walker;
    }
    
    public void setRoot(final Node root) {
        this.m_isFresh = true;
        this.m_isDone = false;
        this.m_root = root;
        this.m_currentNode = root;
        this.m_prevReturned = null;
        if (root == null) {
            throw new RuntimeException("\n !!!! Error! Setting the root of a walker to null!!!");
        }
        this.resetProximityPositions();
    }
    
    public String toString() {
        final Class cl = this.getClass();
        String clName = cl.getName();
        final StringTokenizer tokenizer = new StringTokenizer(clName, ".");
        while (tokenizer.hasMoreTokens()) {
            clName = tokenizer.nextToken();
        }
        String rootName;
        String currentNodeName;
        try {
            rootName = ((this.m_root == null) ? "null" : (String.valueOf(this.m_root.getNodeName()) + "{" + ((Child)this.m_root).getUid() + "}"));
            currentNodeName = ((this.m_root == null) ? "null" : (String.valueOf(this.m_currentNode.getNodeName()) + "{" + ((Child)this.m_currentNode).getUid() + "}"));
        }
        catch (ClassCastException ex) {
            rootName = ((this.m_root == null) ? "null" : this.m_root.getNodeName());
            currentNodeName = ((this.m_root == null) ? "null" : this.m_currentNode.getNodeName());
        }
        return String.valueOf(clName) + "[" + rootName + "][" + currentNodeName + "]";
    }
}
