// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.graphApi.io;

import hypergraph.graphApi.Edge;
import hypergraph.graphApi.Node;
import hypergraph.graphApi.GraphException;
import hypergraph.graphApi.Group;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import hypergraph.graphApi.Element;
import hypergraph.graphApi.Graph;
import org.xml.sax.helpers.DefaultHandler;

public class GraphXMLContentHandler extends DefaultHandler implements ContentHandler
{
    private SAXReader reader;
    protected Graph graph;
    protected Element currentElement;
    protected StringBuffer currentText;
    
    public void setReader(final SAXReader reader) {
        this.reader = reader;
    }
    
    public SAXReader getReader() {
        return this.reader;
    }
    
    public void endDocument() throws SAXException {
        this.reader.setGraph(this.graph);
    }
    
    protected void startElementGraph(final Attributes attributes) {
        this.graph = this.reader.getGraphSystem().createGraph();
    }
    
    protected void endElementGraph() {
    }
    
    protected void startElementNode(final Attributes attributes) {
        String value = null;
        Group group = null;
        for (int i = 0; i < attributes.getLength(); ++i) {
            if (attributes.getQName(i).equals("name")) {
                value = attributes.getValue(i);
            }
            else if (attributes.getQName(i).equals("class")) {
                final String value2 = attributes.getValue(i);
                if (value2 != null && value2.length() > 0) {
                    group = (Group)this.graph.getElement(value2);
                    if (group == null) {
                        try {
                            group = this.graph.createGroup(value2);
                        }
                        catch (GraphException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        }
        try {
            this.currentElement = this.graph.createNode(value);
            if (group != null) {
                this.currentElement.setGroup(group);
            }
        }
        catch (GraphException ex2) {
            ex2.printStackTrace();
        }
    }
    
    protected void endElementNode() {
        this.currentElement = null;
    }
    
    protected void startElementEdge(final Attributes attributes) {
        String value = null;
        Group group = null;
        Node node = null;
        Node node2 = null;
        for (int i = 0; i < attributes.getLength(); ++i) {
            if (attributes.getQName(i).equals("name")) {
                value = attributes.getValue(i);
            }
            else if (attributes.getQName(i).equals("class")) {
                final String value2 = attributes.getValue(i);
                if (value2 != null && value2.length() > 0) {
                    group = (Group)this.graph.getElement(value2);
                    if (group == null) {
                        try {
                            group = this.graph.createGroup(value2);
                        }
                        catch (GraphException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
            else if (attributes.getQName(i).equals("source")) {
                final String value3 = attributes.getValue(i);
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
            else if (attributes.getQName(i).equals("target")) {
                final String value4 = attributes.getValue(i);
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
            this.currentElement = this.graph.createEdge(value, node, node2);
            if (group != null) {
                this.currentElement.setGroup(group);
            }
        }
        catch (GraphException ex4) {
            ex4.printStackTrace();
        }
    }
    
    protected void endElementEdge() {
        this.currentElement = null;
    }
    
    protected void startElementLabel(final Attributes attributes) {
        this.currentText = new StringBuffer();
    }
    
    protected void endElementLabel() {
        if (this.currentText != null && this.currentElement != null) {
            if (this.currentElement instanceof Node) {
                ((Node)this.currentElement).setLabel(this.currentText.toString());
            }
            if (this.currentElement instanceof Edge) {
                ((Edge)this.currentElement).setLabel(this.currentText.toString());
            }
        }
    }
    
    public void startElement(final String s, final String s2, final String s3, final Attributes attributes) throws SAXException {
        if (s3.equals("graph")) {
            this.startElementGraph(attributes);
            return;
        }
        if (s3.equals("node")) {
            this.startElementNode(attributes);
            return;
        }
        if (s3.equals("edge")) {
            this.startElementEdge(attributes);
            return;
        }
        if (s3.equals("label")) {
            this.startElementLabel(attributes);
        }
    }
    
    public void endElement(final String s, final String s2, final String s3) throws SAXException {
        if (s3.equals("graph")) {
            this.endElementGraph();
            return;
        }
        if (s3.equals("node")) {
            this.endElementNode();
            return;
        }
        if (s3.equals("edge")) {
            this.endElementEdge();
            return;
        }
        if (s3.equals("label")) {
            this.endElementLabel();
        }
    }
    
    public void characters(final char[] array, final int n, final int n2) throws SAXException {
        if (this.currentText == null) {
            this.currentText = new StringBuffer();
        }
        this.currentText.append(array, n, n2);
    }
}
