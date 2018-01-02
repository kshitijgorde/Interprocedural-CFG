// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.graph;

import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import hypergraph.graphApi.GraphException;
import java.util.Collections;
import java.util.Collection;
import hypergraph.graphApi.Node;
import hypergraph.graphApi.Group;
import hypergraph.graphApi.Edge;
import hypergraph.graphApi.Element;
import hypergraph.graphApi.GraphEvent;
import java.util.HashMap;
import hypergraph.graphApi.GraphSystem;
import javax.swing.event.EventListenerList;
import hypergraph.graphApi.AttributeManager;
import java.util.Map;
import hypergraph.graphApi.GraphListener;
import hypergraph.graphApi.Graph;

public class GraphImpl extends ElementImpl implements Graph, GraphListener
{
    protected Map nodes;
    protected Map groups;
    protected Map2D adjacencyList;
    protected Map edges;
    private AttributeManager attributeManager;
    private GraphImpl spanningTree;
    private GraphImpl spanningTreeRoot;
    private EventListenerList listenerList;
    GraphSystem graphSystem;
    static /* synthetic */ Class class$hypergraph$graphApi$GraphListener;
    
    GraphImpl(final GraphSystemImpl graphSystem) {
        super(GraphSystemImpl.createId());
        this.graphSystem = graphSystem;
        this.nodes = new HashMap();
        this.adjacencyList = new NodeMap2D();
        this.edges = new HashMap();
        this.groups = null;
        this.spanningTree = null;
        this.attributeManager = new AttributeManagerImpl(this);
        this.listenerList = new EventListenerList();
        this.addGraphListener(this);
    }
    
    public void elementsAdded(final GraphEvent graphEvent) {
        this.spanningTree = null;
    }
    
    public void elementsRemoved(final GraphEvent graphEvent) {
        this.spanningTree = null;
    }
    
    public void structureChanged(final GraphEvent graphEvent) {
        this.spanningTree = null;
    }
    
    public void addGraphListener(final GraphListener graphListener) {
        this.listenerList.add((GraphImpl.class$hypergraph$graphApi$GraphListener == null) ? (GraphImpl.class$hypergraph$graphApi$GraphListener = class$("hypergraph.graphApi.GraphListener")) : GraphImpl.class$hypergraph$graphApi$GraphListener, graphListener);
    }
    
    public void removeGraphListener(final GraphListener graphListener) {
        this.listenerList.remove((GraphImpl.class$hypergraph$graphApi$GraphListener == null) ? (GraphImpl.class$hypergraph$graphApi$GraphListener = class$("hypergraph.graphApi.GraphListener")) : GraphImpl.class$hypergraph$graphApi$GraphListener, graphListener);
    }
    
    void fireElementsAdded(final GraphEvent graphEvent) {
        final Object[] listenerList = this.listenerList.getListenerList();
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == ((GraphImpl.class$hypergraph$graphApi$GraphListener == null) ? (GraphImpl.class$hypergraph$graphApi$GraphListener = class$("hypergraph.graphApi.GraphListener")) : GraphImpl.class$hypergraph$graphApi$GraphListener)) {
                ((GraphListener)listenerList[i + 1]).elementsAdded(graphEvent);
            }
        }
    }
    
    void fireElementsRemoved(final GraphEvent graphEvent) {
        final Object[] listenerList = this.listenerList.getListenerList();
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == ((GraphImpl.class$hypergraph$graphApi$GraphListener == null) ? (GraphImpl.class$hypergraph$graphApi$GraphListener = class$("hypergraph.graphApi.GraphListener")) : GraphImpl.class$hypergraph$graphApi$GraphListener)) {
                ((GraphListener)listenerList[i + 1]).elementsRemoved(graphEvent);
            }
        }
    }
    
    public GraphSystem getGraphSystem() {
        return this.graphSystem;
    }
    
    public synchronized void addElement(final Element element) {
        if (element == null) {
            return;
        }
        if (element.getElementType() == 1) {
            if (this.nodes.put(element.getName(), element) == null) {
                this.fireElementsAdded(new GraphEventImpl(this, element));
            }
            return;
        }
        if (element.getElementType() == 2) {
            final Edge edge = (Edge)element;
            if (this.isConnected(edge.getSource(), edge.getTarget())) {
                return;
            }
            this.addElement(edge.getSource());
            this.addElement(edge.getTarget());
            this.adjacencyList.put(edge.getSource(), edge.getTarget(), edge);
            this.edges.put(edge.getName(), edge);
            this.fireElementsAdded(new GraphEventImpl(this, edge));
        }
        else if (element.getElementType() == 3) {
            if (this.groups == null) {
                this.groups = new HashMap();
            }
            this.groups.put(element.getName(), element);
        }
    }
    
    public synchronized void removeElement(final Element element) {
        if (element == null) {
            return;
        }
        if (element.getElementType() == 1) {
            final Object[] array = this.getEdges((Node)element).toArray();
            for (int i = 0; i < array.length; ++i) {
                this.removeElement((Element)array[i]);
            }
            this.nodes.remove(element.getName());
            this.fireElementsRemoved(new GraphEventImpl(this, element));
            return;
        }
        if (element.getElementType() == 2) {
            this.adjacencyList.remove(((Edge)element).getSource(), ((Edge)element).getTarget());
            this.edges.remove(((Edge)element).getName());
            this.fireElementsRemoved(new GraphEventImpl(this, element));
        }
    }
    
    public synchronized void removeAll() {
        final Object[] array = this.nodes.values().toArray();
        for (int i = 0; i < array.length; ++i) {
            this.removeElement((Element)array[i]);
        }
    }
    
    public int getElementType() {
        return 0;
    }
    
    public Collection getNodes() {
        return Collections.unmodifiableCollection(this.nodes.values());
    }
    
    public Collection getEdges() {
        return Collections.unmodifiableCollection(this.edges.values());
    }
    
    public Collection getEdges(final Node node) {
        if (this.adjacencyList.get(node) == null) {
            return Collections.EMPTY_LIST;
        }
        return Collections.unmodifiableCollection(this.adjacencyList.get(node).values());
    }
    
    public void insertGraph(final Graph graph) {
        if (graph == null) {
            return;
        }
        this.addEdges(graph.getEdges());
    }
    
    public Node createNode() {
        final NodeImpl nodeImpl = new NodeImpl(GraphSystemImpl.createId());
        this.addElement(nodeImpl);
        return nodeImpl;
    }
    
    public Node createNode(final String s) throws GraphException {
        if (s == null) {
            return this.createNode();
        }
        if (this.getElement(s) != null) {
            throw new GraphException("Duplicate name \"" + s + "\"for element.");
        }
        final NodeImpl nodeImpl = new NodeImpl(s);
        this.addElement(nodeImpl);
        return nodeImpl;
    }
    
    public Edge createEdge(final Node node, final Node node2) {
        if (node == null || node2 == null || node.equals(node2)) {
            return null;
        }
        final EdgeImpl edgeImpl = new EdgeImpl(GraphSystemImpl.createId(), node, node2);
        this.addElement(edgeImpl);
        return edgeImpl;
    }
    
    public Edge createEdge(final String s, final Node node, final Node node2) throws GraphException {
        if (s == null) {
            return this.createEdge(node, node2);
        }
        if (this.getElement(s) != null) {
            throw new GraphException("Duplicate name \"" + s + "\"for element.");
        }
        final EdgeImpl edgeImpl = new EdgeImpl(s, node, node2);
        this.addElement(edgeImpl);
        return edgeImpl;
    }
    
    public Group createGroup() {
        try {
            return this.createGroup(GraphSystemImpl.createId());
        }
        catch (GraphException ex) {
            return null;
        }
    }
    
    public Group createGroup(final String s) throws GraphException {
        if (s == null) {
            return this.createGroup();
        }
        if (this.getElement(s) != null) {
            throw new GraphException("Duplicate name \"" + s + "\"for element.");
        }
        final GroupImpl groupImpl = new GroupImpl(s);
        this.addElement(groupImpl);
        return groupImpl;
    }
    
    public Element getElement(final String s) {
        final Element element = this.nodes.get(s);
        if (element != null) {
            return element;
        }
        final Element element2 = this.edges.get(s);
        if (element2 != null) {
            return element2;
        }
        if (this.groups != null) {
            final Group group = this.groups.get(s);
            if (group != null) {
                return group;
            }
        }
        return null;
    }
    
    public void addNodes(final Collection collection) {
        collection.addAll(collection);
    }
    
    public void addEdges(final Collection collection) {
        final Iterator<Edge> iterator = collection.iterator();
        while (iterator.hasNext()) {
            this.addElement(iterator.next());
        }
    }
    
    public boolean isConnected(final Node node, final Node node2) {
        return this.adjacencyList.containsKey(node, node2);
    }
    
    public synchronized Graph getSpanningTree() {
        if (this.spanningTree == null) {
            this.getSpanningTree(this.getMinInDegree());
        }
        return this.spanningTree;
    }
    
    public synchronized Graph getSpanningTree(final Node node) {
        if (this.spanningTree != null && node == this.spanningTree.getAttributeManager().getAttribute("GRAPH_ROOT", this.spanningTree)) {
            return this.spanningTree;
        }
        (this.spanningTree = new GraphImpl((GraphSystemImpl)this.getGraphSystem())).addElement(node);
        this.spanningTree.getAttributeManager().setAttribute("GRAPH_ROOT", this.spanningTree, node);
        final HashSet set = new HashSet<Object>();
        set.add(node);
        while (!set.isEmpty()) {
            final Node node2 = set.iterator().next();
            set.remove(node2);
            for (Edge edge : this.getEdges(node2)) {
                final Node otherNode = edge.getOtherNode(node2);
                if (this.spanningTree.getElement(otherNode.getName()) == null) {
                    if (otherNode == edge.getSource()) {
                        edge = new ReverseEdge("reverse" + edge.getName(), edge);
                    }
                    this.spanningTree.addElement(otherNode);
                    this.spanningTree.addElement(edge);
                    set.add(otherNode);
                }
            }
        }
        return this.spanningTree;
    }
    
    public Node getMinInDegree() {
        final Iterator<Node> iterator = (Iterator<Node>)this.getNodes().iterator();
        Node node = null;
        int n = -1;
        while (iterator.hasNext()) {
            final Node node2 = iterator.next();
            int n2 = 0;
            final Iterator iterator2 = this.getEdges(node2).iterator();
            while (iterator2.hasNext()) {
                if (iterator2.next().getTarget().equals(node2)) {
                    ++n2;
                }
            }
            if (n < 0 || n > n2) {
                node = node2;
                n = n2;
            }
            if (n == 0) {
                break;
            }
        }
        return node;
    }
    
    public Node getMaxOutDegree() {
        final Iterator<Node> iterator = (Iterator<Node>)this.getNodes().iterator();
        Node node = null;
        int n = -1;
        while (iterator.hasNext()) {
            final Node node2 = iterator.next();
            int n2 = 0;
            final Iterator iterator2 = this.getEdges(node2).iterator();
            while (iterator2.hasNext()) {
                if (iterator2.next().getSource().equals(node2)) {
                    ++n2;
                }
            }
            if (n < n2) {
                node = node2;
                n = n2;
            }
        }
        return node;
    }
    
    protected void createSpanningTreeDFS(final Node node) {
        for (Edge edge : this.getEdges(node)) {
            final Node otherNode = edge.getOtherNode(node);
            if (this.spanningTree.getElement(otherNode.getName()) != null) {
                if (otherNode == edge.getSource()) {
                    edge = new ReverseEdge("reverse" + edge.getName(), edge);
                }
                this.spanningTree.addElement(otherNode);
                this.spanningTree.addElement(edge);
                this.createSpanningTreeDFS(otherNode);
            }
        }
    }
    
    protected void processBFS(final Set set) {
        final Node node = set.iterator().next();
        set.remove(node);
        for (Edge edge : this.getEdges(node)) {
            final Node otherNode = edge.getOtherNode(node);
            if (this.spanningTree.getElement(otherNode.getName()) != null) {
                if (otherNode == edge.getSource()) {
                    edge = new ReverseEdge("reverse" + edge.getName(), edge);
                }
                this.spanningTree.addElement(otherNode);
                this.spanningTree.addElement(edge);
                set.add(otherNode);
            }
        }
    }
    
    public void setAttributeManager(final AttributeManager attributeManager) {
        this.attributeManager = attributeManager;
    }
    
    public AttributeManager getAttributeManager() {
        return this.attributeManager;
    }
    
    public Object clone() {
        return null;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        final String s = "\n";
        sb.append("hypergraph.graph.GraphImpl" + s);
        sb.append("nodes : " + s);
        final Iterator<Object> iterator = this.getNodes().iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next().toString() + s);
        }
        sb.append("edges : " + s);
        final Iterator<Object> iterator2 = this.getEdges().iterator();
        while (iterator2.hasNext()) {
            sb.append(iterator2.next().toString() + s);
        }
        return sb.toString();
    }
    
    public String getLabel() {
        return null;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public class ReverseEdge extends ElementImpl implements Edge
    {
        private Edge underlying;
        
        public ReverseEdge(final String s, final Edge underlying) {
            super(s);
            this.underlying = underlying;
        }
        
        public String getName() {
            return this.underlying.getName();
        }
        
        public Group getGroup() {
            return this.underlying.getGroup();
        }
        
        public void setGroup(final Group group) {
            this.underlying.setGroup(group);
        }
        
        public Node getSource() {
            return this.underlying.getTarget();
        }
        
        public Node getTarget() {
            return this.underlying.getSource();
        }
        
        public Node getOtherNode(final Node node) {
            return this.underlying.getOtherNode(node);
        }
        
        public void reverse() {
            this.underlying.reverse();
        }
        
        public String getLabel() {
            return this.underlying.getLabel();
        }
        
        public void setLabel(final String label) {
            this.underlying.setLabel(label);
        }
        
        public int getElementType() {
            return 2;
        }
        
        public String toString() {
            return "[ ReverseEdge : \n  underlying Edge : " + this.underlying + " ]\n";
        }
    }
}
