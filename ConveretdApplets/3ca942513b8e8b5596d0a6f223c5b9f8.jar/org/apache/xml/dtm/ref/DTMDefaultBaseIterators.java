// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.dtm.ref;

import org.apache.xml.utils.NodeVector;
import org.apache.xml.dtm.DTMAxisTraverser;
import org.apache.xml.dtm.DTMException;
import org.apache.xml.res.XMLMessages;
import org.apache.xml.dtm.Axis;
import org.apache.xml.dtm.DTMAxisIterator;
import org.apache.xml.utils.XMLStringFactory;
import org.apache.xml.dtm.DTMWSFilter;
import javax.xml.transform.Source;
import org.apache.xml.dtm.DTMManager;

public abstract class DTMDefaultBaseIterators extends DTMDefaultBaseTraversers
{
    public DTMDefaultBaseIterators(final DTMManager mgr, final Source source, final int dtmIdentity, final DTMWSFilter whiteSpaceFilter, final XMLStringFactory xstringfactory, final boolean doIndexing) {
        super(mgr, source, dtmIdentity, whiteSpaceFilter, xstringfactory, doIndexing);
    }
    
    public DTMDefaultBaseIterators(final DTMManager mgr, final Source source, final int dtmIdentity, final DTMWSFilter whiteSpaceFilter, final XMLStringFactory xstringfactory, final boolean doIndexing, final int blocksize, final boolean usePrevsib, final boolean newNameTable) {
        super(mgr, source, dtmIdentity, whiteSpaceFilter, xstringfactory, doIndexing, blocksize, usePrevsib, newNameTable);
    }
    
    public DTMAxisIterator getTypedAxisIterator(final int axis, final int type) {
        DTMAxisIterator iterator = null;
        switch (axis) {
            case 13: {
                iterator = new TypedSingletonIterator(type);
                break;
            }
            case 3: {
                iterator = new TypedChildrenIterator(type);
                break;
            }
            case 10: {
                return new ParentIterator().setNodeType(type);
            }
            case 0: {
                return new TypedAncestorIterator(type);
            }
            case 1: {
                return new TypedAncestorIterator(type).includeSelf();
            }
            case 2: {
                return new TypedAttributeIterator(type);
            }
            case 4: {
                iterator = new TypedDescendantIterator(type);
                break;
            }
            case 5: {
                iterator = new TypedDescendantIterator(type).includeSelf();
                break;
            }
            case 6: {
                iterator = new TypedFollowingIterator(type);
                break;
            }
            case 11: {
                iterator = new TypedPrecedingIterator(type);
                break;
            }
            case 7: {
                iterator = new TypedFollowingSiblingIterator(type);
                break;
            }
            case 12: {
                iterator = new TypedPrecedingSiblingIterator(type);
                break;
            }
            case 9: {
                iterator = new TypedNamespaceIterator(type);
                break;
            }
            case 19: {
                iterator = new TypedRootIterator(type);
                break;
            }
            default: {
                throw new DTMException(XMLMessages.createXMLMessage("ER_TYPED_ITERATOR_AXIS_NOT_IMPLEMENTED", new Object[] { Axis.getNames(axis) }));
            }
        }
        return iterator;
    }
    
    public DTMAxisIterator getAxisIterator(final int axis) {
        DTMAxisIterator iterator = null;
        switch (axis) {
            case 13: {
                iterator = new SingletonIterator();
                break;
            }
            case 3: {
                iterator = new ChildrenIterator();
                break;
            }
            case 10: {
                return new ParentIterator();
            }
            case 0: {
                return new AncestorIterator();
            }
            case 1: {
                return new AncestorIterator().includeSelf();
            }
            case 2: {
                return new AttributeIterator();
            }
            case 4: {
                iterator = new DescendantIterator();
                break;
            }
            case 5: {
                iterator = new DescendantIterator().includeSelf();
                break;
            }
            case 6: {
                iterator = new FollowingIterator();
                break;
            }
            case 11: {
                iterator = new PrecedingIterator();
                break;
            }
            case 7: {
                iterator = new FollowingSiblingIterator();
                break;
            }
            case 12: {
                iterator = new PrecedingSiblingIterator();
                break;
            }
            case 9: {
                iterator = new NamespaceIterator();
                break;
            }
            case 19: {
                iterator = new RootIterator();
                break;
            }
            default: {
                throw new DTMException(XMLMessages.createXMLMessage("ER_ITERATOR_AXIS_NOT_IMPLEMENTED", new Object[] { Axis.getNames(axis) }));
            }
        }
        return iterator;
    }
    
    public abstract class InternalAxisIteratorBase extends DTMAxisIteratorBase
    {
        protected int _currentNode;
        
        public void setMark() {
            super._markedNode = this._currentNode;
        }
        
        public void gotoMark() {
            this._currentNode = super._markedNode;
        }
    }
    
    public final class ChildrenIterator extends InternalAxisIteratorBase
    {
        public DTMAxisIterator setStartNode(int node) {
            if (node == 0) {
                node = DTMDefaultBaseIterators.this.getDocument();
            }
            if (super._isRestartable) {
                super._startNode = node;
                super._currentNode = ((node == -1) ? -1 : DTMDefaultBaseIterators.this._firstch(DTMDefaultBaseIterators.this.makeNodeIdentity(node)));
                return this.resetPosition();
            }
            return this;
        }
        
        public int next() {
            if (super._currentNode != -1) {
                final int node = super._currentNode;
                super._currentNode = DTMDefaultBaseIterators.this._nextsib(node);
                return this.returnNode(DTMDefaultBaseIterators.this.makeNodeHandle(node));
            }
            return -1;
        }
    }
    
    public final class ParentIterator extends InternalAxisIteratorBase
    {
        private int _nodeType;
        
        public ParentIterator() {
            this._nodeType = -1;
        }
        
        public DTMAxisIterator setStartNode(int node) {
            if (node == 0) {
                node = DTMDefaultBaseIterators.this.getDocument();
            }
            if (super._isRestartable) {
                super._startNode = node;
                super._currentNode = DTMDefaultBaseIterators.this.getParent(node);
                return this.resetPosition();
            }
            return this;
        }
        
        public DTMAxisIterator setNodeType(final int type) {
            this._nodeType = type;
            return this;
        }
        
        public int next() {
            int result = super._currentNode;
            if (this._nodeType >= 14) {
                if (this._nodeType != DTMDefaultBaseIterators.this.getExpandedTypeID(super._currentNode)) {
                    result = -1;
                }
            }
            else if (this._nodeType != -1 && this._nodeType != DTMDefaultBaseIterators.this.getNodeType(super._currentNode)) {
                result = -1;
            }
            super._currentNode = -1;
            return this.returnNode(result);
        }
    }
    
    public final class TypedChildrenIterator extends InternalAxisIteratorBase
    {
        private final int _nodeType;
        
        public TypedChildrenIterator(final int nodeType) {
            this._nodeType = nodeType;
        }
        
        public DTMAxisIterator setStartNode(int node) {
            if (node == 0) {
                node = DTMDefaultBaseIterators.this.getDocument();
            }
            if (super._isRestartable) {
                super._startNode = node;
                super._currentNode = ((node == -1) ? -1 : DTMDefaultBaseIterators.this._firstch(DTMDefaultBaseIterators.this.makeNodeIdentity(super._startNode)));
                return this.resetPosition();
            }
            return this;
        }
        
        public int next() {
            int node = super._currentNode;
            final int nodeType = this._nodeType;
            if (nodeType >= 14) {
                while (node != -1) {
                    if (DTMDefaultBaseIterators.this._exptype(node) == nodeType) {
                        break;
                    }
                    node = DTMDefaultBaseIterators.this._nextsib(node);
                }
            }
            else {
                while (node != -1) {
                    final int eType = DTMDefaultBaseIterators.this._exptype(node);
                    if (eType < 14) {
                        if (eType == nodeType) {
                            break;
                        }
                    }
                    else if (DTMDefaultBaseIterators.this.m_expandedNameTable.getType(eType) == nodeType) {
                        break;
                    }
                    node = DTMDefaultBaseIterators.this._nextsib(node);
                }
            }
            if (node == -1) {
                return super._currentNode = -1;
            }
            super._currentNode = DTMDefaultBaseIterators.this._nextsib(node);
            return this.returnNode(DTMDefaultBaseIterators.this.makeNodeHandle(node));
        }
    }
    
    public final class NamespaceChildrenIterator extends InternalAxisIteratorBase
    {
        private final int _nsType;
        
        public NamespaceChildrenIterator(final int type) {
            this._nsType = type;
        }
        
        public DTMAxisIterator setStartNode(int node) {
            if (node == 0) {
                node = DTMDefaultBaseIterators.this.getDocument();
            }
            if (super._isRestartable) {
                super._startNode = node;
                super._currentNode = ((node == -1) ? -1 : -2);
                return this.resetPosition();
            }
            return this;
        }
        
        public int next() {
            if (super._currentNode != -1) {
                for (int node = (-2 == super._currentNode) ? DTMDefaultBaseIterators.this._firstch(DTMDefaultBaseIterators.this.makeNodeIdentity(super._startNode)) : DTMDefaultBaseIterators.this._nextsib(super._currentNode); node != -1; node = DTMDefaultBaseIterators.this._nextsib(node)) {
                    if (DTMDefaultBaseIterators.this.m_expandedNameTable.getNamespaceID(DTMDefaultBaseIterators.this._exptype(node)) == this._nsType) {
                        super._currentNode = node;
                        return this.returnNode(node);
                    }
                }
            }
            return -1;
        }
    }
    
    public class NamespaceIterator extends InternalAxisIteratorBase
    {
        public DTMAxisIterator setStartNode(int node) {
            if (node == 0) {
                node = DTMDefaultBaseIterators.this.getDocument();
            }
            if (super._isRestartable) {
                super._startNode = node;
                super._currentNode = DTMDefaultBaseIterators.this.getFirstNamespaceNode(node, true);
                return this.resetPosition();
            }
            return this;
        }
        
        public int next() {
            final int node = super._currentNode;
            if (-1 != node) {
                super._currentNode = DTMDefaultBaseIterators.this.getNextNamespaceNode(super._startNode, node, true);
            }
            return this.returnNode(node);
        }
    }
    
    public class TypedNamespaceIterator extends NamespaceIterator
    {
        private final int _nodeType;
        
        public TypedNamespaceIterator(final int nodeType) {
            this._nodeType = nodeType;
        }
        
        public int next() {
            for (int node = super._currentNode; node != -1; node = DTMDefaultBaseIterators.this.getNextNamespaceNode(super._startNode, node, true)) {
                if (DTMDefaultBaseIterators.this.getExpandedTypeID(node) == this._nodeType || DTMDefaultBaseIterators.this.getNodeType(node) == this._nodeType || DTMDefaultBaseIterators.this.getNamespaceType(node) == this._nodeType) {
                    super._currentNode = node;
                    return this.returnNode(node);
                }
            }
            return super._currentNode = -1;
        }
    }
    
    public class RootIterator extends InternalAxisIteratorBase
    {
        public DTMAxisIterator setStartNode(final int node) {
            if (super._isRestartable) {
                super._startNode = DTMDefaultBaseIterators.this.getDocumentRoot(node);
                super._currentNode = -1;
                return this.resetPosition();
            }
            return this;
        }
        
        public int next() {
            if (super._startNode == super._currentNode) {
                return -1;
            }
            super._currentNode = super._startNode;
            return this.returnNode(super._startNode);
        }
    }
    
    public class TypedRootIterator extends RootIterator
    {
        private final int _nodeType;
        
        public TypedRootIterator(final int nodeType) {
            this._nodeType = nodeType;
        }
        
        public int next() {
            if (super._startNode == super._currentNode) {
                return -1;
            }
            final int nodeType = this._nodeType;
            final int node = super._startNode;
            final int expType = DTMDefaultBaseIterators.this.getExpandedTypeID(node);
            super._currentNode = node;
            if (nodeType >= 14) {
                if (nodeType == expType) {
                    return this.returnNode(node);
                }
            }
            else if (expType < 14) {
                if (expType == nodeType) {
                    return this.returnNode(node);
                }
            }
            else if (DTMDefaultBaseIterators.this.m_expandedNameTable.getType(expType) == nodeType) {
                return this.returnNode(node);
            }
            return -1;
        }
    }
    
    public final class NamespaceAttributeIterator extends InternalAxisIteratorBase
    {
        private final int _nsType;
        
        public NamespaceAttributeIterator(final int nsType) {
            this._nsType = nsType;
        }
        
        public DTMAxisIterator setStartNode(int node) {
            if (node == 0) {
                node = DTMDefaultBaseIterators.this.getDocument();
            }
            if (super._isRestartable) {
                super._startNode = node;
                super._currentNode = DTMDefaultBaseIterators.this.getFirstNamespaceNode(node, false);
                return this.resetPosition();
            }
            return this;
        }
        
        public int next() {
            final int node = super._currentNode;
            if (-1 != node) {
                super._currentNode = DTMDefaultBaseIterators.this.getNextNamespaceNode(super._startNode, node, false);
            }
            return this.returnNode(node);
        }
    }
    
    public class FollowingSiblingIterator extends InternalAxisIteratorBase
    {
        public DTMAxisIterator setStartNode(int node) {
            if (node == 0) {
                node = DTMDefaultBaseIterators.this.getDocument();
            }
            if (super._isRestartable) {
                super._startNode = node;
                super._currentNode = DTMDefaultBaseIterators.this.makeNodeIdentity(node);
                return this.resetPosition();
            }
            return this;
        }
        
        public int next() {
            super._currentNode = ((super._currentNode == -1) ? -1 : DTMDefaultBaseIterators.this._nextsib(super._currentNode));
            return this.returnNode(DTMDefaultBaseIterators.this.makeNodeHandle(super._currentNode));
        }
    }
    
    public final class TypedFollowingSiblingIterator extends FollowingSiblingIterator
    {
        private final int _nodeType;
        
        public TypedFollowingSiblingIterator(final int type) {
            this._nodeType = type;
        }
        
        public int next() {
            if (super._currentNode == -1) {
                return -1;
            }
            int node = super._currentNode;
            final int nodeType = this._nodeType;
            if (nodeType >= 14) {
                do {
                    node = DTMDefaultBaseIterators.this._nextsib(node);
                    if (node != -1) {
                        continue;
                    }
                    break;
                } while (DTMDefaultBaseIterators.this._exptype(node) != nodeType);
            }
            else {
                while ((node = DTMDefaultBaseIterators.this._nextsib(node)) != -1) {
                    final int eType = DTMDefaultBaseIterators.this._exptype(node);
                    if (eType < 14) {
                        if (eType == nodeType) {
                            break;
                        }
                        continue;
                    }
                    else {
                        if (DTMDefaultBaseIterators.this.m_expandedNameTable.getType(eType) == nodeType) {
                            break;
                        }
                        continue;
                    }
                }
            }
            super._currentNode = node;
            return (super._currentNode == -1) ? -1 : this.returnNode(DTMDefaultBaseIterators.this.makeNodeHandle(super._currentNode));
        }
    }
    
    public final class AttributeIterator extends InternalAxisIteratorBase
    {
        public DTMAxisIterator setStartNode(int node) {
            if (node == 0) {
                node = DTMDefaultBaseIterators.this.getDocument();
            }
            if (super._isRestartable) {
                super._startNode = node;
                super._currentNode = DTMDefaultBaseIterators.this.getFirstAttributeIdentity(DTMDefaultBaseIterators.this.makeNodeIdentity(node));
                return this.resetPosition();
            }
            return this;
        }
        
        public int next() {
            final int node = super._currentNode;
            if (node != -1) {
                super._currentNode = DTMDefaultBaseIterators.this.getNextAttributeIdentity(node);
                return this.returnNode(DTMDefaultBaseIterators.this.makeNodeHandle(node));
            }
            return -1;
        }
    }
    
    public final class TypedAttributeIterator extends InternalAxisIteratorBase
    {
        private final int _nodeType;
        
        public TypedAttributeIterator(final int nodeType) {
            this._nodeType = nodeType;
        }
        
        public DTMAxisIterator setStartNode(final int node) {
            if (super._isRestartable) {
                super._startNode = node;
                super._currentNode = DTMDefaultBaseIterators.this.getTypedAttribute(node, this._nodeType);
                return this.resetPosition();
            }
            return this;
        }
        
        public int next() {
            final int node = super._currentNode;
            super._currentNode = -1;
            return this.returnNode(node);
        }
    }
    
    public class PrecedingSiblingIterator extends InternalAxisIteratorBase
    {
        protected int _startNodeID;
        
        public boolean isReverse() {
            return true;
        }
        
        public DTMAxisIterator setStartNode(int node) {
            if (node == 0) {
                node = DTMDefaultBaseIterators.this.getDocument();
            }
            if (!super._isRestartable) {
                return this;
            }
            super._startNode = node;
            final int nodeIdentity = DTMDefaultBaseIterators.this.makeNodeIdentity(node);
            this._startNodeID = nodeIdentity;
            node = nodeIdentity;
            if (node == -1) {
                super._currentNode = node;
                return this.resetPosition();
            }
            final int type = DTMDefaultBaseIterators.this.m_expandedNameTable.getType(DTMDefaultBaseIterators.this._exptype(node));
            if (2 == type || 13 == type) {
                super._currentNode = node;
            }
            else {
                super._currentNode = DTMDefaultBaseIterators.this._parent(node);
                if (-1 != super._currentNode) {
                    super._currentNode = DTMDefaultBaseIterators.this._firstch(super._currentNode);
                }
                else {
                    super._currentNode = node;
                }
            }
            return this.resetPosition();
        }
        
        public int next() {
            if (super._currentNode == this._startNodeID || super._currentNode == -1) {
                return -1;
            }
            final int node = super._currentNode;
            super._currentNode = DTMDefaultBaseIterators.this._nextsib(node);
            return this.returnNode(DTMDefaultBaseIterators.this.makeNodeHandle(node));
        }
    }
    
    public final class TypedPrecedingSiblingIterator extends PrecedingSiblingIterator
    {
        private final int _nodeType;
        
        public TypedPrecedingSiblingIterator(final int type) {
            this._nodeType = type;
        }
        
        public int next() {
            int node = super._currentNode;
            final int nodeType = this._nodeType;
            final int startID = super._startNodeID;
            if (nodeType >= 14) {
                while (node != -1 && node != startID) {
                    if (DTMDefaultBaseIterators.this._exptype(node) == nodeType) {
                        break;
                    }
                    node = DTMDefaultBaseIterators.this._nextsib(node);
                }
            }
            else {
                while (node != -1 && node != startID) {
                    final int expType = DTMDefaultBaseIterators.this._exptype(node);
                    if (expType < 14) {
                        if (expType == nodeType) {
                            break;
                        }
                    }
                    else if (DTMDefaultBaseIterators.this.m_expandedNameTable.getType(expType) == nodeType) {
                        break;
                    }
                    node = DTMDefaultBaseIterators.this._nextsib(node);
                }
            }
            if (node == -1 || node == super._startNodeID) {
                return super._currentNode = -1;
            }
            super._currentNode = DTMDefaultBaseIterators.this._nextsib(node);
            return this.returnNode(DTMDefaultBaseIterators.this.makeNodeHandle(node));
        }
    }
    
    public class PrecedingIterator extends InternalAxisIteratorBase
    {
        private final int _maxAncestors = 8;
        protected int[] _stack;
        protected int _sp;
        protected int _oldsp;
        protected int _markedsp;
        protected int _markedNode;
        protected int _markedDescendant;
        
        public PrecedingIterator() {
            this._stack = new int[8];
        }
        
        public boolean isReverse() {
            return true;
        }
        
        public DTMAxisIterator cloneIterator() {
            super._isRestartable = false;
            try {
                final PrecedingIterator clone = (PrecedingIterator)super.clone();
                final int[] stackCopy = new int[this._stack.length];
                System.arraycopy(this._stack, 0, stackCopy, 0, this._stack.length);
                clone._stack = stackCopy;
                return clone;
            }
            catch (CloneNotSupportedException e) {
                throw new DTMException(XMLMessages.createXMLMessage("ER_ITERATOR_CLONE_NOT_SUPPORTED", null));
            }
        }
        
        public DTMAxisIterator setStartNode(int node) {
            if (node == 0) {
                node = DTMDefaultBaseIterators.this.getDocument();
            }
            if (super._isRestartable) {
                node = DTMDefaultBaseIterators.this.makeNodeIdentity(node);
                if (DTMDefaultBaseIterators.this._type(node) == 2) {
                    node = DTMDefaultBaseIterators.this._parent(node);
                }
                super._startNode = node;
                int index;
                this._stack[index = 0] = node;
                int parent = node;
                while ((parent = DTMDefaultBaseIterators.this._parent(parent)) != -1) {
                    if (++index == this._stack.length) {
                        final int[] stack = new int[index + 4];
                        System.arraycopy(this._stack, 0, stack, 0, index);
                        this._stack = stack;
                    }
                    this._stack[index] = parent;
                }
                if (index > 0) {
                    --index;
                }
                super._currentNode = this._stack[index];
                final int n = index;
                this._sp = n;
                this._oldsp = n;
                return this.resetPosition();
            }
            return this;
        }
        
        public int next() {
            ++super._currentNode;
            while (this._sp >= 0) {
                if (super._currentNode < this._stack[this._sp]) {
                    if (DTMDefaultBaseIterators.this._type(super._currentNode) != 2 && DTMDefaultBaseIterators.this._type(super._currentNode) != 13) {
                        return this.returnNode(DTMDefaultBaseIterators.this.makeNodeHandle(super._currentNode));
                    }
                }
                else {
                    --this._sp;
                }
                ++super._currentNode;
            }
            return -1;
        }
        
        public DTMAxisIterator reset() {
            this._sp = this._oldsp;
            return this.resetPosition();
        }
        
        public void setMark() {
            this._markedsp = this._sp;
            this._markedNode = super._currentNode;
            this._markedDescendant = this._stack[0];
        }
        
        public void gotoMark() {
            this._sp = this._markedsp;
            super._currentNode = this._markedNode;
        }
    }
    
    public final class TypedPrecedingIterator extends PrecedingIterator
    {
        private final int _nodeType;
        
        public TypedPrecedingIterator(final int type) {
            this._nodeType = type;
        }
        
        public int next() {
            int node = super._currentNode;
            final int nodeType = this._nodeType;
            if (nodeType >= 14) {
                while (true) {
                    ++node;
                    if (super._sp < 0) {
                        node = -1;
                        break;
                    }
                    if (node >= super._stack[super._sp]) {
                        if (--super._sp < 0) {
                            node = -1;
                            break;
                        }
                        continue;
                    }
                    else {
                        if (DTMDefaultBaseIterators.this._exptype(node) == nodeType) {
                            break;
                        }
                        continue;
                    }
                }
            }
            else {
                while (true) {
                    ++node;
                    if (super._sp < 0) {
                        node = -1;
                        break;
                    }
                    if (node >= super._stack[super._sp]) {
                        if (--super._sp < 0) {
                            node = -1;
                            break;
                        }
                        continue;
                    }
                    else {
                        final int expType = DTMDefaultBaseIterators.this._exptype(node);
                        if (expType < 14) {
                            if (expType == nodeType) {
                                break;
                            }
                            continue;
                        }
                        else {
                            if (DTMDefaultBaseIterators.this.m_expandedNameTable.getType(expType) == nodeType) {
                                break;
                            }
                            continue;
                        }
                    }
                }
            }
            super._currentNode = node;
            return (node == -1) ? -1 : this.returnNode(DTMDefaultBaseIterators.this.makeNodeHandle(node));
        }
    }
    
    public class FollowingIterator extends InternalAxisIteratorBase
    {
        DTMAxisTraverser m_traverser;
        
        public FollowingIterator() {
            this.m_traverser = DTMDefaultBaseIterators.this.getAxisTraverser(6);
        }
        
        public DTMAxisIterator setStartNode(int node) {
            if (node == 0) {
                node = DTMDefaultBaseIterators.this.getDocument();
            }
            if (super._isRestartable) {
                super._startNode = node;
                super._currentNode = this.m_traverser.first(node);
                return this.resetPosition();
            }
            return this;
        }
        
        public int next() {
            final int node = super._currentNode;
            super._currentNode = this.m_traverser.next(super._startNode, super._currentNode);
            return this.returnNode(node);
        }
    }
    
    public final class TypedFollowingIterator extends FollowingIterator
    {
        private final int _nodeType;
        
        public TypedFollowingIterator(final int type) {
            this._nodeType = type;
        }
        
        public int next() {
            int node;
            do {
                node = super._currentNode;
                super._currentNode = super.m_traverser.next(super._startNode, super._currentNode);
            } while (node != -1 && DTMDefaultBaseIterators.this.getExpandedTypeID(node) != this._nodeType && DTMDefaultBaseIterators.this.getNodeType(node) != this._nodeType);
            return (node == -1) ? -1 : this.returnNode(node);
        }
    }
    
    public class AncestorIterator extends InternalAxisIteratorBase
    {
        NodeVector m_ancestors;
        int m_ancestorsPos;
        int m_markedPos;
        int m_realStartNode;
        
        public AncestorIterator() {
            this.m_ancestors = new NodeVector();
        }
        
        public int getStartNode() {
            return this.m_realStartNode;
        }
        
        public final boolean isReverse() {
            return true;
        }
        
        public DTMAxisIterator cloneIterator() {
            super._isRestartable = false;
            try {
                final AncestorIterator clone = (AncestorIterator)super.clone();
                clone._startNode = super._startNode;
                return clone;
            }
            catch (CloneNotSupportedException e) {
                throw new DTMException(XMLMessages.createXMLMessage("ER_ITERATOR_CLONE_NOT_SUPPORTED", null));
            }
        }
        
        public DTMAxisIterator setStartNode(int node) {
            if (node == 0) {
                node = DTMDefaultBaseIterators.this.getDocument();
            }
            this.m_realStartNode = node;
            if (super._isRestartable) {
                int nodeID = DTMDefaultBaseIterators.this.makeNodeIdentity(node);
                if (!super._includeSelf && node != -1) {
                    nodeID = DTMDefaultBaseIterators.this._parent(nodeID);
                    node = DTMDefaultBaseIterators.this.makeNodeHandle(nodeID);
                }
                super._startNode = node;
                while (nodeID != -1) {
                    this.m_ancestors.addElement(node);
                    nodeID = DTMDefaultBaseIterators.this._parent(nodeID);
                    node = DTMDefaultBaseIterators.this.makeNodeHandle(nodeID);
                }
                this.m_ancestorsPos = this.m_ancestors.size() - 1;
                super._currentNode = ((this.m_ancestorsPos >= 0) ? this.m_ancestors.elementAt(this.m_ancestorsPos) : -1);
                return this.resetPosition();
            }
            return this;
        }
        
        public DTMAxisIterator reset() {
            this.m_ancestorsPos = this.m_ancestors.size() - 1;
            super._currentNode = ((this.m_ancestorsPos >= 0) ? this.m_ancestors.elementAt(this.m_ancestorsPos) : -1);
            return this.resetPosition();
        }
        
        public int next() {
            final int next = super._currentNode;
            final int ancestorsPos = this.m_ancestorsPos - 1;
            this.m_ancestorsPos = ancestorsPos;
            final int pos = ancestorsPos;
            super._currentNode = ((pos >= 0) ? this.m_ancestors.elementAt(this.m_ancestorsPos) : -1);
            return this.returnNode(next);
        }
        
        public void setMark() {
            this.m_markedPos = this.m_ancestorsPos;
        }
        
        public void gotoMark() {
            this.m_ancestorsPos = this.m_markedPos;
            super._currentNode = ((this.m_ancestorsPos >= 0) ? this.m_ancestors.elementAt(this.m_ancestorsPos) : -1);
        }
    }
    
    public final class TypedAncestorIterator extends AncestorIterator
    {
        private final int _nodeType;
        
        public TypedAncestorIterator(final int type) {
            this._nodeType = type;
        }
        
        public DTMAxisIterator setStartNode(int node) {
            if (node == 0) {
                node = DTMDefaultBaseIterators.this.getDocument();
            }
            super.m_realStartNode = node;
            if (super._isRestartable) {
                int nodeID = DTMDefaultBaseIterators.this.makeNodeIdentity(node);
                final int nodeType = this._nodeType;
                if (!super._includeSelf && node != -1) {
                    nodeID = DTMDefaultBaseIterators.this._parent(nodeID);
                }
                super._startNode = node;
                if (nodeType >= 14) {
                    while (nodeID != -1) {
                        final int eType = DTMDefaultBaseIterators.this._exptype(nodeID);
                        if (eType == nodeType) {
                            super.m_ancestors.addElement(DTMDefaultBaseIterators.this.makeNodeHandle(nodeID));
                        }
                        nodeID = DTMDefaultBaseIterators.this._parent(nodeID);
                    }
                }
                else {
                    while (nodeID != -1) {
                        final int eType = DTMDefaultBaseIterators.this._exptype(nodeID);
                        if ((eType >= 14 && DTMDefaultBaseIterators.this.m_expandedNameTable.getType(eType) == nodeType) || (eType < 14 && eType == nodeType)) {
                            super.m_ancestors.addElement(DTMDefaultBaseIterators.this.makeNodeHandle(nodeID));
                        }
                        nodeID = DTMDefaultBaseIterators.this._parent(nodeID);
                    }
                }
                super.m_ancestorsPos = super.m_ancestors.size() - 1;
                super._currentNode = ((super.m_ancestorsPos >= 0) ? super.m_ancestors.elementAt(super.m_ancestorsPos) : -1);
                return this.resetPosition();
            }
            return this;
        }
    }
    
    public class DescendantIterator extends InternalAxisIteratorBase
    {
        public DTMAxisIterator setStartNode(int node) {
            if (node == 0) {
                node = DTMDefaultBaseIterators.this.getDocument();
            }
            if (super._isRestartable) {
                node = DTMDefaultBaseIterators.this.makeNodeIdentity(node);
                super._startNode = node;
                if (super._includeSelf) {
                    --node;
                }
                super._currentNode = node;
                return this.resetPosition();
            }
            return this;
        }
        
        protected boolean isDescendant(final int identity) {
            return DTMDefaultBaseIterators.this._parent(identity) >= super._startNode || super._startNode == identity;
        }
        
        public int next() {
            if (super._startNode == -1) {
                return -1;
            }
            if (super._includeSelf && super._currentNode + 1 == super._startNode) {
                return this.returnNode(DTMDefaultBaseIterators.this.makeNodeHandle(++super._currentNode));
            }
            int node = super._currentNode;
            int type;
            do {
                ++node;
                type = DTMDefaultBaseIterators.this._type(node);
                if (-1 == type || !this.isDescendant(node)) {
                    return super._currentNode = -1;
                }
            } while (2 == type || 3 == type || 13 == type);
            super._currentNode = node;
            return this.returnNode(DTMDefaultBaseIterators.this.makeNodeHandle(node));
        }
        
        public DTMAxisIterator reset() {
            final boolean temp = super._isRestartable;
            super._isRestartable = true;
            this.setStartNode(DTMDefaultBaseIterators.this.makeNodeHandle(super._startNode));
            super._isRestartable = temp;
            return this;
        }
    }
    
    public final class TypedDescendantIterator extends DescendantIterator
    {
        private final int _nodeType;
        
        public TypedDescendantIterator(final int nodeType) {
            this._nodeType = nodeType;
        }
        
        public int next() {
            if (super._startNode == -1) {
                return -1;
            }
            int node = super._currentNode;
            int type;
            do {
                ++node;
                type = DTMDefaultBaseIterators.this._type(node);
                if (-1 == type || !this.isDescendant(node)) {
                    return super._currentNode = -1;
                }
            } while (type != this._nodeType && DTMDefaultBaseIterators.this._exptype(node) != this._nodeType);
            super._currentNode = node;
            return this.returnNode(DTMDefaultBaseIterators.this.makeNodeHandle(node));
        }
    }
    
    public class NthDescendantIterator extends DescendantIterator
    {
        int _pos;
        
        public NthDescendantIterator(final int pos) {
            this._pos = pos;
        }
        
        public int next() {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: goto            82
            //     3: aload_0         /* this */
            //     4: getfield        org/apache/xml/dtm/ref/DTMDefaultBaseIterators$NthDescendantIterator.this$0:Lorg/apache/xml/dtm/ref/DTMDefaultBaseIterators;
            //     7: iload_1        
            //     8: invokevirtual   org/apache/xml/dtm/ref/DTMDefaultBase.makeNodeIdentity:(I)I
            //    11: istore_1        /* node */
            //    12: aload_0         /* this */
            //    13: getfield        org/apache/xml/dtm/ref/DTMDefaultBaseIterators$NthDescendantIterator.this$0:Lorg/apache/xml/dtm/ref/DTMDefaultBaseIterators;
            //    16: iload_1         /* node */
            //    17: invokevirtual   org/apache/xml/dtm/ref/DTMDefaultBase._parent:(I)I
            //    20: istore_2        /* parent */
            //    21: aload_0         /* this */
            //    22: getfield        org/apache/xml/dtm/ref/DTMDefaultBaseIterators$NthDescendantIterator.this$0:Lorg/apache/xml/dtm/ref/DTMDefaultBaseIterators;
            //    25: iload_2         /* parent */
            //    26: invokevirtual   org/apache/xml/dtm/ref/DTMDefaultBase._firstch:(I)I
            //    29: istore_3        /* child */
            //    30: iconst_0       
            //    31: istore          pos
            //    33: aload_0         /* this */
            //    34: getfield        org/apache/xml/dtm/ref/DTMDefaultBaseIterators$NthDescendantIterator.this$0:Lorg/apache/xml/dtm/ref/DTMDefaultBaseIterators;
            //    37: iload_3         /* child */
            //    38: invokevirtual   org/apache/xml/dtm/ref/DTMDefaultBase._type:(I)S
            //    41: istore          type
            //    43: iconst_1       
            //    44: iload           type
            //    46: if_icmpne       52
            //    49: iinc            pos, 1
            //    52: iload           pos
            //    54: aload_0         /* this */
            //    55: getfield        org/apache/xml/dtm/ref/DTMDefaultBaseIterators$NthDescendantIterator._pos:I
            //    58: if_icmpge       75
            //    61: aload_0         /* this */
            //    62: getfield        org/apache/xml/dtm/ref/DTMDefaultBaseIterators$NthDescendantIterator.this$0:Lorg/apache/xml/dtm/ref/DTMDefaultBaseIterators;
            //    65: iload_3         /* child */
            //    66: invokevirtual   org/apache/xml/dtm/ref/DTMDefaultBase._nextsib:(I)I
            //    69: dup            
            //    70: istore_3        /* child */
            //    71: iconst_m1      
            //    72: if_icmpne       33
            //    75: iload_1         /* node */
            //    76: iload_3         /* child */
            //    77: if_icmpne       82
            //    80: iload_1         /* node */
            //    81: ireturn        
            //    82: aload_0         /* this */
            //    83: invokespecial   org/apache/xml/dtm/ref/DTMDefaultBaseIterators$DescendantIterator.next:()I
            //    86: dup            
            //    87: istore_1        /* node */
            //    88: iconst_m1      
            //    89: if_icmpne       3
            //    92: iconst_m1      
            //    93: ireturn        
            //    LocalVariableTable:
            //  Start  Length  Slot  Name    Signature
            //  -----  ------  ----  ------  ----------------------------------------------------------------------
            //  0      94      0     this    Lorg/apache/xml/dtm/ref/DTMDefaultBaseIterators$NthDescendantIterator;
            //  12     82      1     node    I
            //  21     61      2     parent  I
            //  30     52      3     child   I
            //  33     49      4     pos     I
            //  43     9       5     type    I
            // 
            // The error that occurred was:
            // 
            // java.lang.NullPointerException
            //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:2985)
            //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
            //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
            //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
    }
    
    public class SingletonIterator extends InternalAxisIteratorBase
    {
        private boolean _isConstant;
        
        public SingletonIterator(final DTMDefaultBaseIterators this$0) {
            this(this$0, Integer.MIN_VALUE, false);
        }
        
        public SingletonIterator(final DTMDefaultBaseIterators this$0, final int node) {
            this(this$0, node, false);
        }
        
        public SingletonIterator(final int node, final boolean constant) {
            super._startNode = node;
            super._currentNode = node;
            this._isConstant = constant;
        }
        
        public DTMAxisIterator setStartNode(int node) {
            if (node == 0) {
                node = DTMDefaultBaseIterators.this.getDocument();
            }
            if (this._isConstant) {
                super._currentNode = super._startNode;
                return this.resetPosition();
            }
            if (super._isRestartable) {
                if (super._currentNode == Integer.MIN_VALUE) {
                    final int n = node;
                    super._startNode = n;
                    super._currentNode = n;
                }
                return this.resetPosition();
            }
            return this;
        }
        
        public DTMAxisIterator reset() {
            if (this._isConstant) {
                super._currentNode = super._startNode;
                return this.resetPosition();
            }
            final boolean temp = super._isRestartable;
            super._isRestartable = true;
            this.setStartNode(super._startNode);
            super._isRestartable = temp;
            return this;
        }
        
        public int next() {
            final int result = super._currentNode;
            super._currentNode = -1;
            return this.returnNode(result);
        }
    }
    
    public final class TypedSingletonIterator extends SingletonIterator
    {
        private final int _nodeType;
        
        public TypedSingletonIterator(final int nodeType) {
            this._nodeType = nodeType;
        }
        
        public int next() {
            final int result = super._currentNode;
            final int nodeType = this._nodeType;
            super._currentNode = -1;
            if (nodeType >= 14) {
                if (DTMDefaultBaseIterators.this.getExpandedTypeID(result) == nodeType) {
                    return this.returnNode(result);
                }
            }
            else if (DTMDefaultBaseIterators.this.getNodeType(result) == nodeType) {
                return this.returnNode(result);
            }
            return -1;
        }
    }
}
