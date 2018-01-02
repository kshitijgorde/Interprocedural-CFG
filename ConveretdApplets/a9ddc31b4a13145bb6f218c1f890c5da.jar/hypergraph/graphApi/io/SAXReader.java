// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.graphApi.io;

import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import javax.xml.parsers.SAXParserFactory;
import java.net.URL;
import java.io.IOException;
import java.io.FileNotFoundException;
import org.xml.sax.XMLReader;
import hypergraph.graphApi.Graph;
import org.xml.sax.InputSource;
import hypergraph.graphApi.GraphSystem;

public class SAXReader
{
    private GraphSystem graphSystem;
    private InputSource inputSource;
    private Graph graph;
    private LexicalHandler lexicalHandler;
    private XMLReader reader;
    private ContentHandlerFactory contentHandlerFactory;
    
    public SAXReader(final GraphSystem graphSystem, final String systemId) throws FileNotFoundException, IOException {
        this.graphSystem = graphSystem;
        this.inputSource = new InputSource(systemId);
        this.createLexicalHandler();
    }
    
    public SAXReader(final GraphSystem graphSystem, final URL url) throws IOException {
        this.graphSystem = graphSystem;
        (this.inputSource = new InputSource(url.openStream())).setSystemId(url.toString());
        this.createLexicalHandler();
    }
    
    void createLexicalHandler() {
        this.lexicalHandler = new DefaultLexicalHandler();
    }
    
    public GraphSystem getGraphSystem() {
        return this.graphSystem;
    }
    
    public XMLReader getReader() {
        return this.reader;
    }
    
    public void setContentHandlerFactory(final ContentHandlerFactory contentHandlerFactory) {
        this.contentHandlerFactory = contentHandlerFactory;
    }
    
    public ContentHandlerFactory getContentHandlerFactory() {
        return this.contentHandlerFactory;
    }
    
    public void setGraph(final Graph graph) {
        this.graph = graph;
    }
    
    public Graph getGraph() {
        return this.graph;
    }
    
    public Graph parse() throws SAXException, IOException, ParserConfigurationException {
        this.reader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
        this.lexicalHandler.setSAXReader(this);
        this.reader.setProperty("http://xml.org/sax/properties/lexical-handler", this.lexicalHandler);
        this.reader.parse(this.inputSource);
        return this.getGraph();
    }
}
