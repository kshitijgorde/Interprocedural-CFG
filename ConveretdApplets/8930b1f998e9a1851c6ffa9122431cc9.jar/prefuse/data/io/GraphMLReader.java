// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.io;

import java.util.Date;
import prefuse.data.parser.DataParseException;
import org.xml.sax.Attributes;
import prefuse.util.collections.IntIterator;
import org.xml.sax.SAXException;
import java.util.HashMap;
import prefuse.data.Table;
import prefuse.data.Schema;
import prefuse.data.parser.ParserFactory;
import javax.xml.parsers.SAXParser;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParserFactory;
import prefuse.data.Graph;
import java.io.InputStream;

public class GraphMLReader extends AbstractGraphReader implements GraphReader
{
    public Graph readGraph(final InputStream is) throws DataIOException {
        try {
            final SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
            final GraphMLHandler dh = new GraphMLHandler();
            saxParser.parse(is, dh);
            return dh.getGraph();
        }
        catch (Exception ex) {
            if (ex instanceof DataIOException) {
                throw (DataIOException)ex;
            }
            throw new DataIOException(ex);
        }
    }
    
    public static class GraphMLHandler extends DefaultHandler implements Tokens
    {
        protected ParserFactory m_pf;
        protected static final String SRC;
        protected static final String TRG;
        protected static final String SRCID;
        protected static final String TRGID;
        protected Schema m_nsch;
        protected Schema m_esch;
        protected String m_graphid;
        protected Graph m_graph;
        protected Table m_nodes;
        protected Table m_edges;
        protected String m_id;
        protected String m_for;
        protected String m_name;
        protected String m_type;
        protected String m_dflt;
        protected StringBuffer m_sbuf;
        private String m_key;
        private int m_row;
        private Table m_table;
        protected HashMap m_nodeMap;
        protected HashMap m_idMap;
        private boolean m_directed;
        private boolean inSchema;
        
        public GraphMLHandler() {
            this.m_pf = ParserFactory.getDefaultFactory();
            this.m_nsch = new Schema();
            this.m_esch = new Schema();
            this.m_graph = null;
            this.m_sbuf = new StringBuffer();
            this.m_row = -1;
            this.m_table = null;
            this.m_nodeMap = new HashMap();
            this.m_idMap = new HashMap();
            this.m_directed = false;
        }
        
        public void startDocument() {
            this.m_nodeMap.clear();
            this.inSchema = true;
            this.m_esch.addColumn(GraphMLHandler.SRC, Integer.TYPE);
            this.m_esch.addColumn(GraphMLHandler.TRG, Integer.TYPE);
            this.m_esch.addColumn(GraphMLHandler.SRCID, String.class);
            this.m_esch.addColumn(GraphMLHandler.TRGID, String.class);
        }
        
        public void endDocument() throws SAXException {
            final IntIterator rows = this.m_edges.rows();
            while (rows.hasNext()) {
                final int nextInt = rows.nextInt();
                final String string = this.m_edges.getString(nextInt, GraphMLHandler.SRCID);
                if (!this.m_nodeMap.containsKey(string)) {
                    throw new SAXException("Tried to create edge with source node id=" + string + " which does not exist.");
                }
                this.m_edges.setInt(nextInt, GraphMLHandler.SRC, (int)this.m_nodeMap.get(string));
                final String string2 = this.m_edges.getString(nextInt, GraphMLHandler.TRGID);
                if (!this.m_nodeMap.containsKey(string2)) {
                    throw new SAXException("Tried to create edge with target node id=" + string2 + " which does not exist.");
                }
                this.m_edges.setInt(nextInt, GraphMLHandler.TRG, (int)this.m_nodeMap.get(string2));
            }
            this.m_edges.removeColumn(GraphMLHandler.SRCID);
            this.m_edges.removeColumn(GraphMLHandler.TRGID);
            this.m_graph = new Graph(this.m_nodes, this.m_edges, this.m_directed);
            if (this.m_graphid != null) {
                this.m_graph.putClientProperty("id", this.m_graphid);
            }
        }
        
        public void startElement(final String s, final String s2, final String s3, final Attributes attributes) {
            this.m_sbuf.delete(0, this.m_sbuf.length());
            if (s3.equals("graph")) {
                this.m_directed = "directed".equalsIgnoreCase(attributes.getValue("edgedefault"));
                this.m_graphid = attributes.getValue("id");
            }
            else if (s3.equals("key")) {
                if (!this.inSchema) {
                    this.error("\"key\" elements can not occur after the first node or edge declaration.");
                }
                this.m_for = attributes.getValue("for");
                this.m_id = attributes.getValue("id");
                this.m_name = attributes.getValue("attr.name");
                this.m_type = attributes.getValue("attr.type");
            }
            else if (s3.equals("node")) {
                this.schemaCheck();
                this.m_row = this.m_nodes.addRow();
                this.m_nodeMap.put(attributes.getValue("id"), new Integer(this.m_row));
                this.m_table = this.m_nodes;
            }
            else if (s3.equals("edge")) {
                this.schemaCheck();
                this.m_row = this.m_edges.addRow();
                this.m_edges.setString(this.m_row, GraphMLHandler.SRCID, attributes.getValue(GraphMLHandler.SRC));
                this.m_edges.setString(this.m_row, GraphMLHandler.TRGID, attributes.getValue(GraphMLHandler.TRG));
                this.m_table = this.m_edges;
            }
            else if (s3.equals("data")) {
                this.m_key = attributes.getValue("key");
            }
        }
        
        public void endElement(final String s, final String s2, final String s3) {
            if (s3.equals("default")) {
                this.m_dflt = this.m_sbuf.toString();
            }
            else if (s3.equals("key")) {
                this.addToSchema();
            }
            else if (s3.equals("data")) {
                final String string = this.m_sbuf.toString();
                final String s4 = this.m_idMap.get(this.m_key);
                final Class columnType = this.m_table.getColumnType(s4);
                try {
                    this.m_table.set(this.m_row, s4, this.parse(string, columnType));
                }
                catch (DataParseException ex) {
                    this.error(ex);
                }
            }
            else if (s3.equals("node") || s3.equals("edge")) {
                this.m_row = -1;
                this.m_table = null;
            }
        }
        
        public void characters(final char[] array, final int n, final int n2) throws SAXException {
            this.m_sbuf.append(array, n, n2);
        }
        
        protected void schemaCheck() {
            if (this.inSchema) {
                this.m_nsch.lockSchema();
                this.m_esch.lockSchema();
                this.m_nodes = this.m_nsch.instantiate();
                this.m_edges = this.m_esch.instantiate();
                this.inSchema = false;
            }
        }
        
        protected void addToSchema() {
            if (this.m_name == null || this.m_name.length() == 0) {
                this.error("Empty key name.");
            }
            if (this.m_type == null || this.m_type.length() == 0) {
                this.error("Empty key type.");
            }
            try {
                final Class type = this.parseType(this.m_type);
                final Object o = (this.m_dflt == null) ? null : this.parse(this.m_dflt, type);
                if (this.m_for == null || this.m_for.equals("all")) {
                    this.m_nsch.addColumn(this.m_name, type, o);
                    this.m_esch.addColumn(this.m_name, type, o);
                }
                else if (this.m_for.equals("node")) {
                    this.m_nsch.addColumn(this.m_name, type, o);
                }
                else if (this.m_for.equals("edge")) {
                    this.m_esch.addColumn(this.m_name, type, o);
                }
                else {
                    this.error("Unrecognized \"for\" value: " + this.m_for);
                }
                this.m_idMap.put(this.m_id, this.m_name);
                this.m_dflt = null;
            }
            catch (DataParseException ex) {
                this.error(ex);
            }
        }
        
        protected Class parseType(String lowerCase) {
            lowerCase = lowerCase.toLowerCase();
            if (lowerCase.equals("int") || lowerCase.equals("integer")) {
                return Integer.TYPE;
            }
            if (lowerCase.equals("long")) {
                return Long.TYPE;
            }
            if (lowerCase.equals("float")) {
                return Float.TYPE;
            }
            if (lowerCase.equals("double") || lowerCase.equals("real")) {
                return Double.TYPE;
            }
            if (lowerCase.equals("boolean")) {
                return Boolean.TYPE;
            }
            if (lowerCase.equals("string")) {
                return String.class;
            }
            if (lowerCase.equals("date")) {
                return Date.class;
            }
            this.error("Unrecognized data type: " + lowerCase);
            return null;
        }
        
        protected Object parse(final String s, final Class clazz) throws DataParseException {
            return this.m_pf.getParser(clazz).parse(s);
        }
        
        public Graph getGraph() {
            return this.m_graph;
        }
        
        protected void error(final String s) {
            throw new RuntimeException(s);
        }
        
        protected void error(final Exception ex) {
            throw new RuntimeException(ex);
        }
        
        static {
            SRC = Graph.DEFAULT_SOURCE_KEY;
            TRG = Graph.DEFAULT_TARGET_KEY;
            SRCID = GraphMLHandler.SRC + '_' + "id";
            TRGID = GraphMLHandler.TRG + '_' + "id";
        }
    }
    
    public interface Tokens
    {
        public static final String ID = "id";
        public static final String GRAPH = "graph";
        public static final String EDGEDEF = "edgedefault";
        public static final String DIRECTED = "directed";
        public static final String UNDIRECTED = "undirected";
        public static final String KEY = "key";
        public static final String FOR = "for";
        public static final String ALL = "all";
        public static final String ATTRNAME = "attr.name";
        public static final String ATTRTYPE = "attr.type";
        public static final String DEFAULT = "default";
        public static final String NODE = "node";
        public static final String EDGE = "edge";
        public static final String SOURCE = "source";
        public static final String TARGET = "target";
        public static final String DATA = "data";
        public static final String TYPE = "type";
        public static final String INT = "int";
        public static final String INTEGER = "integer";
        public static final String LONG = "long";
        public static final String FLOAT = "float";
        public static final String DOUBLE = "double";
        public static final String REAL = "real";
        public static final String BOOLEAN = "boolean";
        public static final String STRING = "string";
        public static final String DATE = "date";
    }
}
