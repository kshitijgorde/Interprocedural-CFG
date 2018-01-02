// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.applications.hexplorer;

import hypergraph.graphApi.GraphSystem;
import hypergraph.graphApi.Node;
import hypergraph.graphApi.algorithms.GraphUtilities;
import org.xml.sax.SAXException;
import java.io.FileNotFoundException;
import java.awt.Component;
import javax.swing.JOptionPane;
import hypergraph.graphApi.io.SAXReader;
import java.net.URL;
import java.util.Properties;
import hypergraph.graphApi.GraphSystemFactory;
import hypergraph.graphApi.Graph;
import javax.swing.JApplet;

public class HExplorerApplet extends JApplet
{
    private static Graph graph;
    
    public void init() {
        final String parameter = this.getParameter("file");
        GraphSystem graphSystem = null;
        try {
            graphSystem = GraphSystemFactory.createGraphSystem("hypergraph.graph.GraphSystemImpl", null);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.exit(8);
        }
        HExplorerApplet.graph = null;
        URL url = null;
        try {
            url = new URL(this.getCodeBase(), parameter);
            final SAXReader saxReader = new SAXReader(graphSystem, url);
            final ContentHandlerFactory contentHandlerFactory = new ContentHandlerFactory();
            contentHandlerFactory.setBaseUrl(this.getCodeBase());
            saxReader.setContentHandlerFactory(contentHandlerFactory);
            HExplorerApplet.graph = saxReader.parse();
        }
        catch (FileNotFoundException ex2) {
            JOptionPane.showMessageDialog(null, "Could not find file " + url.getFile() + ". \n" + "Start applet with default graph", "File not found", 0);
            System.out.println("Exception : " + ex2);
            ex2.printStackTrace(System.out);
        }
        catch (SAXException ex3) {
            JOptionPane.showMessageDialog(null, "Error while parsing file" + url.getFile() + ". \n" + "Exception : " + ex3 + ". \n" + "Start applet with default graph", "Parsing error", 0);
            System.out.println("Exception : " + ex3);
            ex3.getException().printStackTrace();
            ex3.printStackTrace(System.out);
        }
        catch (Exception ex4) {
            JOptionPane.showMessageDialog(null, "General error while reading file " + url.getFile() + ". \n" + "Exception : " + ex4 + ". \n" + "Start applet with default graph", "General error", 0);
            System.out.println(url);
            System.out.println("Exception : " + ex4);
            ex4.printStackTrace(System.out);
        }
        if (HExplorerApplet.graph == null) {
            HExplorerApplet.graph = GraphUtilities.createTree(graphSystem, 2, 3);
        }
        final GraphPanel graphPanel = new GraphPanel(HExplorerApplet.graph, this);
        final String parameter2 = this.getParameter("properties");
        if (parameter2 != null) {
            try {
                url = new URL(this.getCodeBase(), parameter2);
                graphPanel.loadProperties(url.openStream());
            }
            catch (FileNotFoundException ex6) {
                JOptionPane.showMessageDialog(null, "Could not find propertyfile " + url.getFile() + ". \n" + "Start applet with default properties", "File not found", 0);
            }
            catch (Exception ex5) {
                JOptionPane.showMessageDialog(null, "General error while reading file " + url.getFile() + ". \n" + "Exception : " + ex5 + ". \n" + "Start applet with default properties", "General error", 0);
                System.out.println(url);
                System.out.println("Exception : " + ex5);
                ex5.printStackTrace(System.out);
            }
        }
        final String parameter3 = this.getParameter("center");
        if (parameter3 != null) {
            final Node node = (Node)HExplorerApplet.graph.getElement(parameter3);
            if (node != null) {
                graphPanel.centerNode(node);
            }
        }
        this.getContentPane().add(graphPanel);
    }
}
