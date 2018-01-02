// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.io;

import java.util.Date;
import prefuse.data.parser.DataParseException;
import org.xml.sax.Attributes;
import prefuse.data.Node;
import prefuse.data.Tree;
import prefuse.data.Table;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParserFactory;
import prefuse.data.Graph;
import java.io.InputStream;
import prefuse.data.parser.ParserFactory;

public class TreeMLReader extends AbstractGraphReader
{
    private ParserFactory m_pf;
    
    public TreeMLReader() {
        this.m_pf = ParserFactory.getDefaultFactory();
    }
    
    public Graph readGraph(final InputStream is) throws DataIOException {
        try {
            final TreeMLHandler dh = new TreeMLHandler();
            SAXParserFactory.newInstance().newSAXParser().parse(is, dh);
            return dh.getTree();
        }
        catch (Exception ex) {
            throw new DataIOException(ex);
        }
    }
    
    public class TreeMLHandler extends DefaultHandler implements Tokens
    {
        private Table m_nodes;
        private Tree m_tree;
        private Node m_activeNode;
        private boolean m_inSchema;
        
        public TreeMLHandler() {
            this.m_nodes = null;
            this.m_tree = null;
            this.m_activeNode = null;
            this.m_inSchema = true;
        }
        
        public void startDocument() {
            this.m_tree = new Tree();
            this.m_nodes = this.m_tree.getNodeTable();
        }
        
        private void schemaCheck() {
            if (this.m_inSchema) {
                this.m_inSchema = false;
            }
        }
        
        public void endElement(final String s, final String s2, final String s3) {
            if (s3.equals("branch") || s3.equals("leaf")) {
                this.m_activeNode = this.m_activeNode.getParent();
            }
        }
        
        public void startElement(final String s, final String s2, final String s3, final Attributes attributes) {
            if (s3.equals("attributeDecl")) {
                if (!this.m_inSchema) {
                    throw new RuntimeException("All declarations must be done before nodes begin");
                }
                this.m_nodes.addColumn(attributes.getValue("name"), this.parseType(attributes.getValue("type")));
            }
            else if (s3.equals("branch") || s3.equals("leaf")) {
                this.schemaCheck();
                Node activeNode;
                if (this.m_activeNode == null) {
                    activeNode = this.m_tree.addRoot();
                }
                else {
                    activeNode = this.m_tree.addChild(this.m_activeNode);
                }
                this.m_activeNode = activeNode;
            }
            else if (s3.equals("attribute")) {
                this.parseAttribute(attributes);
            }
        }
        
        protected void parseAttribute(final Attributes attributes) {
            String value = null;
            String value2 = null;
            for (int i = 0; i < attributes.getLength(); ++i) {
                final String qName = attributes.getQName(i);
                if (qName.equals("name")) {
                    value = attributes.getValue(i);
                }
                else if (qName.equals("value")) {
                    value2 = attributes.getValue(i);
                }
            }
            if (value == null || value2 == null) {
                System.err.println("Attribute under-specified");
                return;
            }
            try {
                this.m_activeNode.set(value, this.parse(value2, this.m_nodes.getColumnType(value)));
            }
            catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        
        protected Object parse(final String s, final Class clazz) throws DataParseException {
            return TreeMLReader.this.m_pf.getParser(clazz).parse(s);
        }
        
        protected Class parseType(String string) {
            string = Character.toUpperCase(string.charAt(0)) + string.substring(1).toLowerCase();
            if (string.equals("Int") || string.equals("Integer")) {
                return Integer.TYPE;
            }
            if (string.equals("Long")) {
                return Long.TYPE;
            }
            if (string.equals("Float")) {
                return Float.TYPE;
            }
            if (string.equals("Double") || string.equals("Real")) {
                return Double.TYPE;
            }
            if (string.equals("Boolean")) {
                return Boolean.TYPE;
            }
            if (string.equals("String")) {
                return String.class;
            }
            if (string.equals("Date")) {
                return Date.class;
            }
            throw new RuntimeException("Unrecognized data type: " + string);
        }
        
        public Tree getTree() {
            return this.m_tree;
        }
    }
    
    public interface Tokens
    {
        public static final String TREE = "tree";
        public static final String BRANCH = "branch";
        public static final String LEAF = "leaf";
        public static final String ATTR = "attribute";
        public static final String NAME = "name";
        public static final String VALUE = "value";
        public static final String TYPE = "type";
        public static final String DECLS = "declarations";
        public static final String DECL = "attributeDecl";
        public static final String INT = "Int";
        public static final String INTEGER = "Integer";
        public static final String LONG = "Long";
        public static final String FLOAT = "Float";
        public static final String REAL = "Real";
        public static final String STRING = "String";
        public static final String DATE = "Date";
        public static final String CATEGORY = "Category";
        public static final String BOOLEAN = "Boolean";
        public static final String DOUBLE = "Double";
    }
}
