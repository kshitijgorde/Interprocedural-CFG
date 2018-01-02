// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.io;

import java.util.Iterator;
import prefuse.data.Schema;
import prefuse.data.Edge;
import prefuse.data.Node;
import java.util.Date;
import prefuse.util.io.XMLWriter;
import java.io.PrintWriter;
import java.io.OutputStream;
import prefuse.data.Graph;
import java.util.HashMap;

public class GraphMLWriter extends AbstractGraphWriter
{
    private static final HashMap TYPES;
    
    public void writeGraph(final Graph graph, final OutputStream outputStream) throws DataIOException {
        final Schema schema = graph.getNodeTable().getSchema();
        final Schema schema2 = graph.getEdgeTable().getSchema();
        this.checkGraphMLSchema(schema);
        this.checkGraphMLSchema(schema2);
        final XMLWriter xmlWriter = new XMLWriter(new PrintWriter(outputStream));
        xmlWriter.begin("<graphml xmlns=\"http://graphml.graphdrawing.org/xmlns\"\n  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n  xsi:schemaLocation=\"http://graphml.graphdrawing.org/xmlns\n  http://graphml.graphdrawing.org/xmlns/1.0/graphml.xsd\">\n\n", 2);
        xmlWriter.comment("prefuse GraphML Writer | " + new Date(System.currentTimeMillis()));
        this.printSchema(xmlWriter, "node", schema, null);
        this.printSchema(xmlWriter, "edge", schema2, new String[] { graph.getEdgeSourceField(), graph.getEdgeTargetField() });
        xmlWriter.println();
        xmlWriter.start("graph", "edgedefault", graph.isDirected() ? "directed" : "undirected");
        xmlWriter.comment("nodes");
        final Iterator nodes = graph.nodes();
        while (nodes.hasNext()) {
            final Node node = nodes.next();
            if (schema.getColumnCount() > 0) {
                xmlWriter.start("node", "id", String.valueOf(node.getRow()));
                for (int i = 0; i < schema.getColumnCount(); ++i) {
                    final String columnName = schema.getColumnName(i);
                    xmlWriter.contentTag("data", "key", columnName, node.getString(columnName));
                }
                xmlWriter.end();
            }
            else {
                xmlWriter.tag("node", "id", String.valueOf(node.getRow()));
            }
        }
        xmlWriter.println();
        final String[] array = { "id", "source", "target" };
        final String[] array2 = new String[3];
        xmlWriter.comment("edges");
        final Iterator edges = graph.edges();
        while (edges.hasNext()) {
            final Edge edge = edges.next();
            array2[0] = String.valueOf(edge.getRow());
            array2[1] = String.valueOf(edge.getSourceNode().getRow());
            array2[2] = String.valueOf(edge.getTargetNode().getRow());
            if (schema2.getColumnCount() > 2) {
                xmlWriter.start("edge", array, array2, 3);
                for (int j = 0; j < schema2.getColumnCount(); ++j) {
                    final String columnName2 = schema2.getColumnName(j);
                    if (!columnName2.equals(graph.getEdgeSourceField())) {
                        if (!columnName2.equals(graph.getEdgeTargetField())) {
                            xmlWriter.contentTag("data", "key", columnName2, edge.getString(columnName2));
                        }
                    }
                }
                xmlWriter.end();
            }
            else {
                xmlWriter.tag("edge", array, array2, 3);
            }
        }
        xmlWriter.end();
        xmlWriter.finish("</graphml>\n");
    }
    
    private void printSchema(final XMLWriter xmlWriter, final String s, final Schema schema, final String[] array) {
        final String[] array2 = { "id", "for", "attr.name", "attr.type" };
        final String[] array3 = new String[4];
        int i = 0;
    Label_0035:
        while (i < schema.getColumnCount()) {
            array3[0] = schema.getColumnName(i);
            while (true) {
                for (int n = 0; array != null && n < array.length; ++n) {
                    if (array3[0].equals(array[n])) {
                        ++i;
                        continue Label_0035;
                    }
                }
                array3[1] = s;
                array3[2] = array3[0];
                array3[3] = (String)GraphMLWriter.TYPES.get(schema.getColumnType(i));
                final Object default1 = schema.getDefault(i);
                if (default1 == null) {
                    xmlWriter.tag("key", array2, array3, 4);
                    continue;
                }
                xmlWriter.start("key", array2, array3, 4);
                xmlWriter.contentTag("default", default1.toString());
                xmlWriter.end();
                continue;
            }
        }
    }
    
    private void checkGraphMLSchema(final Schema schema) throws DataIOException {
        for (int i = 0; i < schema.getColumnCount(); ++i) {
            final Class columnType = schema.getColumnType(i);
            if (GraphMLWriter.TYPES.get(columnType) == null) {
                throw new DataIOException("Data type unsupported by the GraphML format: " + columnType.getName());
            }
        }
    }
    
    static {
        (TYPES = new HashMap()).put(Integer.TYPE, "int");
        GraphMLWriter.TYPES.put(Long.TYPE, "long");
        GraphMLWriter.TYPES.put(Float.TYPE, "float");
        GraphMLWriter.TYPES.put(Double.TYPE, "double");
        GraphMLWriter.TYPES.put(Boolean.TYPE, "boolean");
        GraphMLWriter.TYPES.put(String.class, "string");
    }
    
    public interface Tokens extends GraphMLReader.Tokens
    {
        public static final String GRAPHML = "graphml";
        public static final String GRAPHML_HEADER = "<graphml xmlns=\"http://graphml.graphdrawing.org/xmlns\"\n  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n  xsi:schemaLocation=\"http://graphml.graphdrawing.org/xmlns\n  http://graphml.graphdrawing.org/xmlns/1.0/graphml.xsd\">\n\n";
    }
}
