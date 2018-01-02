// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.applications.hexplorer;

import hypergraph.graphApi.AttributeManager;
import hypergraph.graphApi.Node;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.MouseEvent;
import java.applet.AppletContext;
import hypergraph.graphApi.Edge;
import hypergraph.graphApi.Element;
import hypergraph.graphApi.Graph;
import javax.swing.JApplet;

public class GraphPanel extends hypergraph.visualnet.GraphPanel
{
    private static String homepageUrl;
    private JApplet explorer;
    
    public GraphPanel(final Graph graph, final JApplet explorer) {
        super(graph);
        this.explorer = explorer;
        this.getEdgeRenderer().setLabelVisible(true);
    }
    
    public void setHoverElement(final Element element, final boolean b) {
        if (this.getHoverElement() != element) {
            if (element == null) {
                this.explorer.getAppletContext().showStatus("");
                this.setToolTipText(null);
            }
            if (element != null && element.getElementType() == 1) {
                final AppletContext appletContext = this.explorer.getAppletContext();
                String toolTipText = (String)this.getGraph().getAttributeManager().getAttribute("xlink:href", element);
                if (toolTipText == null) {
                    toolTipText = "No reference";
                }
                appletContext.showStatus(toolTipText);
                this.setToolTipText(toolTipText);
            }
            if (element != null && element.getElementType() == 2) {
                this.setToolTipText(((Edge)element).getLabel());
            }
        }
        super.setHoverElement(element, b);
        if (this.getHoverElement() == null) {
            this.explorer.getAppletContext().showStatus("");
        }
    }
    
    protected void logoClicked(final MouseEvent mouseEvent) {
        super.logoClicked(mouseEvent);
        try {
            this.explorer.getAppletContext().showDocument(new URL(GraphPanel.homepageUrl));
        }
        catch (MalformedURLException ex) {}
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        super.mouseMoved(mouseEvent);
        if (this.isOnLogo(mouseEvent.getPoint())) {
            this.explorer.getAppletContext().showStatus(GraphPanel.homepageUrl);
        }
    }
    
    public void nodeClicked(final int n, final Node node) {
        final AttributeManager attributeManager = this.getGraph().getAttributeManager();
        final String s = (String)attributeManager.getAttribute("xlink:href", node);
        if (s != null && s.charAt(0) == '#') {
            final Element element = this.getGraph().getElement(s.substring(1));
            if (element.getElementType() == 1) {
                this.centerNode((Node)element);
            }
        }
        if (s != null && s.charAt(0) != '#') {
            final String s2 = (String)attributeManager.getAttribute("xlink:show", node);
            final String string = this.getPropertyManager().getString("hypergraph.applications.hexplorer.GraphPanel.target");
            String s3 = null;
            if (s2 != null && s2.equals("replace")) {
                s3 = "_self";
            }
            if (s2 != null && s2.equals("new")) {
                s3 = "_blank";
            }
            if (s3 == null && string != null) {
                s3 = string;
            }
            if (s3 == null) {
                s3 = "_self";
            }
            try {
                this.explorer.getAppletContext().showDocument(new URL(this.explorer.getDocumentBase(), s), s3);
            }
            catch (Exception ex) {
                System.out.println(ex);
            }
        }
        if (s == null) {
            super.nodeClicked(n, node);
        }
    }
    
    static {
        GraphPanel.homepageUrl = "http://hypergraph.sourceforge.net";
    }
}
