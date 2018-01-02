// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.filter;

import java.util.Iterator;
import java.util.HashSet;
import java.util.Set;
import java.util.Collection;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

public class DestinationMapNode implements DestinationNode
{
    protected static final String ANY_CHILD = "*";
    protected static final String ANY_DESCENDENT = ">";
    private DestinationMapNode parent;
    private List values;
    private Map childNodes;
    private String path;
    private int pathLength;
    
    public DestinationMapNode(final DestinationMapNode parent) {
        this.values = new ArrayList();
        this.childNodes = new HashMap();
        this.path = "Root";
        this.parent = parent;
        if (parent == null) {
            this.pathLength = 0;
        }
        else {
            this.pathLength = parent.pathLength + 1;
        }
    }
    
    @Override
    public DestinationMapNode getChild(final String path) {
        return this.childNodes.get(path);
    }
    
    @Override
    public Collection getChildren() {
        return this.childNodes.values();
    }
    
    public int getChildCount() {
        return this.childNodes.size();
    }
    
    public DestinationMapNode getChildOrCreate(final String path) {
        DestinationMapNode answer = this.childNodes.get(path);
        if (answer == null) {
            answer = this.createChildNode();
            answer.path = path;
            this.childNodes.put(path, answer);
        }
        return answer;
    }
    
    @Override
    public List getValues() {
        return this.values;
    }
    
    @Override
    public List removeValues() {
        final ArrayList v = new ArrayList(this.values);
        this.values.clear();
        this.pruneIfEmpty();
        return v;
    }
    
    @Override
    public Set removeDesendentValues() {
        final Set answer = new HashSet();
        this.removeDesendentValues(answer);
        return answer;
    }
    
    protected void removeDesendentValues(final Set answer) {
        answer.addAll(this.removeValues());
    }
    
    @Override
    public Set getDesendentValues() {
        final Set answer = new HashSet();
        this.appendDescendantValues(answer);
        return answer;
    }
    
    public void add(final String[] paths, final int idx, final Object value) {
        if (idx >= paths.length) {
            this.values.add(value);
        }
        else {
            this.getChildOrCreate(paths[idx]).add(paths, idx + 1, value);
        }
    }
    
    public void remove(final String[] paths, int idx, final Object value) {
        if (idx >= paths.length) {
            this.values.remove(value);
            this.pruneIfEmpty();
        }
        else {
            this.getChildOrCreate(paths[idx]).remove(paths, ++idx, value);
        }
    }
    
    public void removeAll(final Set answer, final String[] paths, final int startIndex) {
        DestinationNode node = this;
        for (int size = paths.length, i = startIndex; i < size && node != null; ++i) {
            final String path = paths[i];
            if (path.equals(">")) {
                answer.addAll(node.removeDesendentValues());
                break;
            }
            node.appendMatchingWildcards(answer, paths, i);
            if (path.equals("*")) {
                node = new AnyChildDestinationNode(node);
            }
            else {
                node = node.getChild(path);
            }
        }
        if (node != null) {
            answer.addAll(node.removeValues());
        }
    }
    
    @Override
    public void appendDescendantValues(final Set answer) {
        answer.addAll(this.values);
        for (final DestinationNode child : this.childNodes.values()) {
            child.appendDescendantValues(answer);
        }
    }
    
    protected DestinationMapNode createChildNode() {
        return new DestinationMapNode(this);
    }
    
    @Override
    public void appendMatchingWildcards(final Set answer, final String[] paths, final int idx) {
        if (idx - 1 > this.pathLength) {
            return;
        }
        DestinationMapNode wildCardNode = this.getChild("*");
        if (wildCardNode != null) {
            wildCardNode.appendMatchingValues(answer, paths, idx + 1);
        }
        wildCardNode = this.getChild(">");
        if (wildCardNode != null) {
            answer.addAll(wildCardNode.getDesendentValues());
        }
    }
    
    @Override
    public void appendMatchingValues(final Set answer, final String[] paths, final int startIndex) {
        DestinationNode node = this;
        boolean couldMatchAny = true;
        for (int size = paths.length, i = startIndex; i < size && node != null; ++i) {
            final String path = paths[i];
            if (path.equals(">")) {
                answer.addAll(node.getDesendentValues());
                couldMatchAny = false;
                break;
            }
            node.appendMatchingWildcards(answer, paths, i);
            if (path.equals("*")) {
                node = new AnyChildDestinationNode(node);
            }
            else {
                node = node.getChild(path);
            }
        }
        if (node != null) {
            answer.addAll(node.getValues());
            if (couldMatchAny) {
                final DestinationNode child = node.getChild(">");
                if (child != null) {
                    answer.addAll(child.getValues());
                }
            }
        }
    }
    
    public String getPath() {
        return this.path;
    }
    
    protected void pruneIfEmpty() {
        if (this.parent != null && this.childNodes.isEmpty() && this.values.isEmpty()) {
            this.parent.removeChild(this);
        }
    }
    
    protected void removeChild(final DestinationMapNode node) {
        this.childNodes.remove(node.getPath());
        this.pruneIfEmpty();
    }
}
