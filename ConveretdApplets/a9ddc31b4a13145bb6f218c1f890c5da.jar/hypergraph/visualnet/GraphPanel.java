// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.visualnet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import hypergraph.hyperbolic.ModelPoint;
import java.awt.Component;
import javax.swing.JComponent;
import hypergraph.graphApi.GraphException;
import java.awt.event.MouseEvent;
import java.awt.Point;
import java.awt.image.ImageObserver;
import hypergraph.hyperbolic.Renderer;
import hypergraph.graphApi.Edge;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.util.Iterator;
import java.io.IOException;
import java.io.InputStream;
import hypergraph.graphApi.AttributeManager;
import java.awt.Color;
import hypergraph.graphApi.io.CSSColourParser;
import java.awt.Toolkit;
import hypergraph.graphApi.Node;
import hypergraph.graphApi.Element;
import java.awt.Image;
import hypergraph.graphApi.Graph;
import java.awt.event.MouseListener;
import hypergraph.hyperbolic.ModelPanel;

public class GraphPanel extends ModelPanel implements MouseListener, GraphLayoutListener, GraphSelectionListener
{
    public static final String NODE_FOREGROUND = "node.color";
    public static final String NODE_BACKGROUND = "node.bkcolor";
    public static final String NODE_ICON = "node.icon";
    public static final String EDGE_TEXTCOLOR = "edge.textcolor";
    public static final String EDGE_LINECOLOR = "edge.linecolor";
    public static final String EDGE_LINEWIDTH = "edge.linewidth";
    public static final String EDGE_STROKE = "edge.stroke";
    public static final String NODE_EXPANDED = "NODE_EXPANDED";
    public final ExpandAction expandAction;
    public final ExpandAction shrinkAction;
    Graph graph;
    GraphLayout graphLayout;
    GraphSelectionModel selectionModel;
    private Image logo;
    private Image smallLogo;
    private Element hoverElement;
    private Node lastMouseClickNode;
    private NodeRenderer nodeRenderer;
    private EdgeRenderer edgeRenderer;
    
    public GraphPanel(final Graph graph) {
        this.expandAction = new ExpandAction(true);
        this.shrinkAction = new ExpandAction(false);
        this.setGraph(graph);
        this.createGraphLayout();
        this.createGraphSelectionModel();
        this.selectionModel.addSelectionEventListener(this);
        this.setNodeRenderer(new DefaultNodeRenderer());
        this.setEdgeRenderer(new DefaultEdgeRenderer());
        this.initDefaultAttributes();
        this.logo = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/hypergraph/visualnet/logo.png"));
        this.smallLogo = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/hypergraph/visualnet/logo_small.png"));
    }
    
    protected void initDefaultAttributes() {
        final AttributeManager attributeManager = this.graph.getAttributeManager();
        final String string = this.getPropertyManager().getString("hypergraph.hyperbolic.line.color");
        Color color = CSSColourParser.stringToColor(string);
        if (color == null || string == null) {
            color = Color.LIGHT_GRAY;
        }
        attributeManager.setAttribute("edge.linecolor", this.graph, color);
        attributeManager.setAttribute("edge.stroke", this.graph, null);
        attributeManager.setAttribute("edge.linewidth", this.graph, new Float(1.0f));
    }
    
    public void createGraphLayout() {
        GraphLayout graphLayout;
        try {
            graphLayout = this.getPropertyManager().getClass("hypergraph.visualnet.layout.class").newInstance();
        }
        catch (Exception ex) {
            graphLayout = new TreeLayout(this.getGraph(), this.getModel(), this.getPropertyManager());
        }
        this.setGraphLayout(graphLayout);
    }
    
    public void loadProperties(final InputStream inputStream) throws IOException {
        super.loadProperties(inputStream);
        this.createGraphLayout();
        this.initDefaultAttributes();
    }
    
    public void setGraphLayout(final GraphLayout graphLayout) {
        if (this.graphLayout != null) {
            this.graphLayout.getGraphLayoutModel().removeLayoutEventListener(this);
        }
        (this.graphLayout = graphLayout).setGraphLayoutModel(new DefaultGraphLayoutModel());
        this.graphLayout.layout();
        this.graphLayout.getGraphLayoutModel().addLayoutEventListener(this);
    }
    
    public GraphLayout getGraphLayout() {
        return this.graphLayout;
    }
    
    void createGraphSelectionModel() {
        this.setGraphSelectionModel(new DefaultGraphSelectionModel(this.getGraph()));
    }
    
    public void setGraphSelectionModel(final GraphSelectionModel selectionModel) {
        this.selectionModel = selectionModel;
    }
    
    public GraphSelectionModel getSelectionModel() {
        return this.selectionModel;
    }
    
    public void setGraph(final Graph graph) {
        this.graph = graph;
        if (this.graphLayout != null) {
            this.graphLayout.setGraph(this.graph);
        }
    }
    
    public Graph getGraph() {
        return this.graph;
    }
    
    public void valueChanged(final GraphLayoutEvent graphLayoutEvent) {
        this.repaint();
    }
    
    protected void checkLayout() {
        if (!this.getGraphLayout().isValid()) {
            this.getGraphLayout().layout();
        }
    }
    
    public Iterator getVisibleNodeIterator() {
        return this.graphLayout.getGraph().getNodes().iterator();
    }
    
    public Iterator getVisibleEdgeIterator() {
        return this.graphLayout.getGraph().getEdges().iterator();
    }
    
    public void paint(final Graphics graphics) {
        synchronized (this.graph) {
            this.checkLayout();
            final Graphics2D graphics2D = (Graphics2D)graphics;
            if (this.getUI().isDraft()) {
                graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
                graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
                graphics2D.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
            }
            else {
                graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            }
            super.paint(graphics);
            final GraphLayoutModel graphLayoutModel = this.getGraphLayout().getGraphLayoutModel();
            final Iterator visibleEdgeIterator = this.getVisibleEdgeIterator();
            while (visibleEdgeIterator.hasNext()) {
                final Edge edge = visibleEdgeIterator.next();
                if (edge != this.hoverElement) {
                    this.edgeRenderer.configure(this, edge);
                    this.paintRenderer(graphics, this.edgeRenderer);
                }
            }
            final Iterator visibleNodeIterator = this.getVisibleNodeIterator();
            while (visibleNodeIterator.hasNext()) {
                final Node node = visibleNodeIterator.next();
                if (node != this.hoverElement) {
                    this.nodeRenderer.configure(this, graphLayoutModel.getNodePosition(node), node);
                    this.paintRenderer(graphics, this.nodeRenderer);
                }
            }
            if (this.hoverElement != null) {
                if (this.hoverElement.getElementType() == 2) {
                    this.edgeRenderer.configure(this, (Edge)this.hoverElement);
                    this.paintRenderer(graphics, this.edgeRenderer);
                }
                if (this.hoverElement.getElementType() == 1) {
                    this.nodeRenderer.configure(this, graphLayoutModel.getNodePosition((Node)this.hoverElement), (Node)this.hoverElement);
                    this.paintRenderer(graphics, this.nodeRenderer);
                }
            }
        }
        if (this.getWidth() > 300 && this.getHeight() > 300) {
            if (this.logo != null) {
                graphics.drawImage(this.logo, this.getWidth() - this.logo.getWidth(this), this.getHeight() - this.logo.getHeight(this), this);
            }
        }
        else if (this.smallLogo != null) {
            graphics.drawImage(this.smallLogo, this.getWidth() - this.smallLogo.getWidth(this), this.getHeight() - this.smallLogo.getHeight(this), this);
        }
    }
    
    public Element getHoverElement() {
        return this.hoverElement;
    }
    
    protected void setHoverElement(final Element hoverElement, final boolean b) {
        this.hoverElement = hoverElement;
        if (b) {
            this.repaint();
        }
    }
    
    public void setLogo(final Image logo) {
        this.logo = logo;
    }
    
    public void setSmallLogo(final Image smallLogo) {
        this.smallLogo = smallLogo;
    }
    
    protected boolean isOnLogo(final Point point) {
        return point.getX() > this.getWidth() - this.logo.getWidth(this) && point.getY() > this.getHeight() - this.logo.getHeight(this);
    }
    
    protected void logoClicked(final MouseEvent mouseEvent) {
    }
    
    public boolean hasExpander(final Node node) {
        return this.getGraphLayout().isExpandingEnabled() && this.graphLayout.getGraph().getAttributeManager().getAttribute("NODE_EXPANDED", node) != null;
    }
    
    public boolean isExpanded(final Node node) {
        final ExpandAction expandAction = (ExpandAction)this.graphLayout.getGraph().getAttributeManager().getAttribute("NODE_EXPANDED", node);
        return expandAction != null && expandAction == this.shrinkAction;
    }
    
    public void expandNode(final Node node) {
        final AttributeManager attributeManager = this.graphLayout.getGraph().getAttributeManager();
        for (final Edge edge : this.graph.getEdges(node)) {
            if (edge.getSource() != node) {
                continue;
            }
            try {
                this.graphLayout.getGraph().addElement(edge);
            }
            catch (GraphException ex) {}
            final Node otherNode = edge.getOtherNode(node);
            if (this.graph.getEdges(otherNode).size() == 0) {
                continue;
            }
            attributeManager.setAttribute("NODE_EXPANDED", otherNode, this.expandAction);
        }
        attributeManager.setAttribute("NODE_EXPANDED", node, this.shrinkAction);
    }
    
    public void shrinkNode(final Node node) {
        final AttributeManager attributeManager = this.graphLayout.getGraph().getAttributeManager();
        for (final Edge edge : this.graph.getEdges(node)) {
            if (edge.getSource() != node) {
                continue;
            }
            this.graphLayout.getGraph().removeElement(edge.getOtherNode(node));
        }
        attributeManager.setAttribute("NODE_EXPANDED", node, this.expandAction);
    }
    
    public void centerNode(final Node node) {
        this.getUI().center(this.getGraphLayout().getGraphLayoutModel().getNodePosition(node), this);
    }
    
    public Element getElement(final Point point) {
        final GraphLayoutModel graphLayoutModel = this.getGraphLayout().getGraphLayoutModel();
        synchronized (this.graph) {
            synchronized (graphLayoutModel) {
                final NodeRenderer nodeRenderer = this.getNodeRenderer();
                final Point point2 = new Point();
                final Iterator visibleNodeIterator = this.getVisibleNodeIterator();
                while (visibleNodeIterator.hasNext()) {
                    final Node node = visibleNodeIterator.next();
                    nodeRenderer.configure(this, graphLayoutModel.getNodePosition(node), node);
                    final Component component = nodeRenderer.getComponent();
                    point2.setLocation(point.getX() - component.getX(), point.getY() - component.getY());
                    if (component.contains(point2)) {
                        return node;
                    }
                }
                final Iterator visibleEdgeIterator = this.getVisibleEdgeIterator();
                while (visibleEdgeIterator.hasNext()) {
                    final Edge edge = visibleEdgeIterator.next();
                    final ModelPoint nodePosition = graphLayoutModel.getNodePosition(edge.getSource());
                    final ModelPoint nodePosition2 = graphLayoutModel.getNodePosition(edge.getTarget());
                    final ModelPoint unProject = this.unProject(point);
                    if (unProject != null && this.getModel().getDistance(unProject, nodePosition, nodePosition2, true, true) < 0.025) {
                        return edge;
                    }
                }
            }
        }
        return null;
    }
    
    public void nodeClicked(final int n, final Node lastMouseClickNode) {
        if (n == 1) {
            this.lastMouseClickNode = lastMouseClickNode;
            if (this.lastMouseClickNode != null) {
                this.centerNode(this.lastMouseClickNode);
            }
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (this.isOnLogo(mouseEvent.getPoint())) {
            this.logoClicked(mouseEvent);
            return;
        }
        this.setHoverElement(null, false);
        final Element element = this.getElement(mouseEvent.getPoint());
        if (element != null && element.getElementType() == 1) {
            if (mouseEvent.getClickCount() == 1) {
                this.nodeClicked(1, (Node)element);
                return;
            }
            if (mouseEvent.getClickCount() == 2 && this.lastMouseClickNode != null) {
                this.nodeClicked(2, this.lastMouseClickNode);
                return;
            }
            this.getNodeRenderer().configure(this, this.getGraphLayout().getGraphLayoutModel().getNodePosition((Node)element), (Node)element);
        }
        super.mouseClicked(mouseEvent);
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.setHoverElement(this.getElement(mouseEvent.getPoint()), true);
    }
    
    public void valueChanged(final GraphSelectionEvent graphSelectionEvent) {
        this.getSelectionModel().getSelectionElementIterator();
        this.repaint();
    }
    
    public EdgeRenderer getEdgeRenderer() {
        return this.edgeRenderer;
    }
    
    public void setEdgeRenderer(final EdgeRenderer edgeRenderer) {
        this.edgeRenderer = edgeRenderer;
    }
    
    public void setNodeRenderer(final NodeRenderer nodeRenderer) {
        this.nodeRenderer = nodeRenderer;
    }
    
    public NodeRenderer getNodeRenderer() {
        return this.nodeRenderer;
    }
    
    public class ExpandAction implements ActionListener
    {
        private boolean expand;
        
        public ExpandAction(final boolean expand) {
            this.expand = expand;
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            final Node node = (Node)actionEvent.getSource();
            if (this.expand) {
                GraphPanel.this.expandNode(node);
            }
            else {
                GraphPanel.this.shrinkNode(node);
            }
            GraphPanel.this.repaint();
        }
    }
}
