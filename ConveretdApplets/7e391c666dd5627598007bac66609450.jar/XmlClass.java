import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import javax.xml.transform.dom.DOMSource;
import java.io.Reader;
import org.xml.sax.InputSource;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import java.io.InputStream;
import java.io.BufferedInputStream;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.NodeList;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.Document;

// 
// Decompiled by Procyon v0.5.30
// 

public class XmlClass
{
    public final String TAG_ROOT;
    protected Document _document;
    
    public XmlClass() {
        this.TAG_ROOT = this.getClass().getName().toLowerCase();
        this.clearMembers();
    }
    
    public Element getRoot() {
        if (this._document == null) {
            this.createDOM();
        }
        final Element root = this._document.getDocumentElement();
        return root;
    }
    
    public void addNote(final Element child) {
        if (this._document == null) {
            this.createDOM();
        }
        final Element root = this._document.getDocumentElement();
        root.appendChild(child);
    }
    
    protected String getTag(final String tag) {
        String result = "";
        final NodeList list = this._document.getElementsByTagName(tag);
        if (list == null || list.getLength() <= 0) {
            return "";
        }
        Node node = list.item(0);
        if (node == null) {
            return "";
        }
        node = node.getFirstChild();
        if (node == null) {
            return "";
        }
        try {
            result = node.getNodeValue();
        }
        catch (DOMException e) {
            return "";
        }
        return result.trim();
    }
    
    protected String getValue(Node node) {
        String result = "";
        node = node.getFirstChild();
        if (node == null) {
            return "";
        }
        try {
            result = node.getNodeValue();
        }
        catch (DOMException e) {
            return "";
        }
        return result.trim();
    }
    
    protected void createTag(final Element parent, final String tag, final String value, final int indent) {
        final Element child = this._document.createElement(tag);
        child.appendChild(this._document.createTextNode(value));
        final StringBuffer spaces = new StringBuffer();
        for (int i = 0; i < indent; ++i) {
            spaces.append(' ');
        }
        parent.appendChild(child);
        parent.appendChild(this._document.createTextNode("\n" + spaces.toString()));
    }
    
    protected void createDOM() {
        this._document = null;
        try {
            this._document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        }
        catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        final Element root = this._document.createElement(this.TAG_ROOT);
        root.appendChild(this._document.createTextNode("\n    "));
        this._document.appendChild(root);
    }
    
    protected void setMembers() {
        if (this._document == null) {
            return;
        }
    }
    
    protected void clearMembers() {
        this._document = null;
    }
    
    public boolean fromFile(final String pathFile) {
        this.clearMembers();
        try {
            final BufferedInputStream file = new BufferedInputStream(this.getClass().getResourceAsStream(pathFile));
            final DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            this._document = builder.parse(file);
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        this.setMembers();
        return true;
    }
    
    public boolean fromXML(final String xml) {
        this.clearMembers();
        try {
            final DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            this._document = builder.parse(new InputSource(new StringReader(xml)));
        }
        catch (Exception e) {
            return false;
        }
        this.setMembers();
        return true;
    }
    
    public void toFile(final String pathFile) {
        this.createDOM();
        final NodeList list = this._document.getElementsByTagName(this.TAG_ROOT);
        final DOMSource source = new DOMSource(list.item(0));
        if (source.getNode() == null) {
            return;
        }
        final StreamResult file = new StreamResult(new File(pathFile));
        Transformer transformer = null;
        try {
            transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, file);
        }
        catch (TransformerException e) {
            e.printStackTrace();
        }
    }
    
    public String toString() {
        this.createDOM();
        final Element root = this._document.getDocumentElement();
        return root.toString();
    }
}
