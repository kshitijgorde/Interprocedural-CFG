// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.graphApi.io;

import hypergraph.graphApi.Node;
import hypergraph.graphApi.GraphException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import hypergraph.graphApi.Element;
import hypergraph.graphApi.Graph;
import org.xml.sax.helpers.DefaultHandler;

public class GraphMLContentHandler extends DefaultHandler implements ContentHandler
{
    private SAXReader reader;
    private Graph graph;
    private Element currentElement;
    private StringBuffer currentText;
    
    public void setReader(final SAXReader reader) {
        this.reader = reader;
    }
    
    public SAXReader getReader() {
        return this.reader;
    }
    
    public void endDocument() throws SAXException {
        this.reader.setGraph(this.graph);
    }
    
    public void startElement(final String s, final String s2, final String s3, final Attributes attributes) throws SAXException {
        if (s3.equals("graph")) {
            this.graph = this.getReader().getGraphSystem().createGraph();
            return;
        }
        if (s3.equals("node")) {
            String value = null;
            for (int i = 0; i < attributes.getLength(); ++i) {
                if (attributes.getQName(i).equals("id")) {
                    value = attributes.getValue(i);
                }
            }
            try {
                this.currentElement = this.graph.createNode(value);
            }
            catch (GraphException ex) {
                ex.printStackTrace();
            }
            return;
        }
        if (s3.equals("edge")) {
            String value2 = null;
            Node node = null;
            Node node2 = null;
            for (int j = 0; j < attributes.getLength(); ++j) {
                if (attributes.getQName(j).equals("id")) {
                    value2 = attributes.getValue(j);
                }
                else if (attributes.getQName(j).equals("source")) {
                    final String value3 = attributes.getValue(j);
                    node = (Node)this.graph.getElement(value3);
                    if (node == null) {
                        try {
                            node = this.graph.createNode(value3);
                        }
                        catch (GraphException ex2) {
                            ex2.printStackTrace();
                        }
                    }
                }
                else if (attributes.getQName(j).equals("target")) {
                    final String value4 = attributes.getValue(j);
                    node2 = (Node)this.graph.getElement(value4);
                    if (node2 == null) {
                        try {
                            node2 = this.graph.createNode(value4);
                        }
                        catch (GraphException ex3) {
                            ex3.printStackTrace();
                        }
                    }
                }
            }
            try {
                this.currentElement = this.graph.createEdge(value2, node, node2);
            }
            catch (GraphException ex4) {
                ex4.printStackTrace();
            }
            return;
        }
        if (s3.equals("desc")) {
            this.currentText = new StringBuffer();
        }
    }
    
    public void endElement(final String s, final String s2, final String s3) throws SAXException {
        if (s3.equals("desc") && this.currentText != null && this.currentElement != null && this.currentElement instanceof Node) {
            ((Node)this.currentElement).setLabel(this.currentText.toString());
        }
    }
    
    public void characters(final char[] array, final int n, final int n2) throws SAXException {
        if (this.currentText == null) {
            this.currentText = new StringBuffer();
        }
        this.currentText.append(array, n, n2);
    }
}
