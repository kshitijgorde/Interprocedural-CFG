// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.io;

import prefuse.data.Node;
import prefuse.data.Schema;
import java.util.Date;
import prefuse.util.io.XMLWriter;
import java.io.PrintWriter;
import java.io.OutputStream;
import prefuse.data.Graph;
import java.util.HashMap;

public class TreeMLWriter extends AbstractGraphWriter
{
    private static final HashMap TYPES;
    
    public void writeGraph(final Graph graph, final OutputStream outputStream) throws DataIOException {
        final Schema schema = graph.getNodeTable().getSchema();
        this.checkTreeMLSchema(schema);
        final XMLWriter xmlWriter = new XMLWriter(new PrintWriter(outputStream));
        xmlWriter.begin();
        xmlWriter.comment("prefuse TreeML Writer | " + new Date(System.currentTimeMillis()));
        xmlWriter.start("tree");
        xmlWriter.start("declarations");
        final String[] array = { "name", "type" };
        final String[] array2 = new String[2];
        for (int i = 0; i < schema.getColumnCount(); ++i) {
            array2[0] = schema.getColumnName(i);
            array2[1] = (String)TreeMLWriter.TYPES.get(schema.getColumnType(i));
            xmlWriter.tag("attributeDecl", array, array2, 2);
        }
        xmlWriter.end();
        xmlWriter.println();
        array[0] = "name";
        array[1] = "value";
        for (Node node = graph.getSpanningTree().getRoot(); node != null; node = this.nextNode(node, xmlWriter)) {
            if (node.getChildCount() == 0) {
                xmlWriter.start("leaf");
            }
            else {
                xmlWriter.start("branch");
            }
            if (schema.getColumnCount() > 0) {
                for (int j = 0; j < schema.getColumnCount(); ++j) {
                    array2[0] = schema.getColumnName(j);
                    array2[1] = node.getString(array2[0]);
                    xmlWriter.tag("attribute", array, array2, 2);
                }
            }
        }
        xmlWriter.end();
        xmlWriter.finish();
    }
    
    private Node nextNode(final Node node, final XMLWriter xmlWriter) {
        Node node2;
        if ((node2 = node.getChild(0)) == null) {
            if ((node2 = node.getNextSibling()) != null) {
                xmlWriter.end();
            }
            else {
                node2 = node.getParent();
                xmlWriter.end();
                while (node2 != null) {
                    final Node nextSibling;
                    if ((nextSibling = node2.getNextSibling()) != null) {
                        node2 = nextSibling;
                        xmlWriter.end();
                        break;
                    }
                    node2 = node2.getParent();
                    xmlWriter.end();
                }
            }
        }
        return node2;
    }
    
    private void checkTreeMLSchema(final Schema schema) throws DataIOException {
        for (int i = 0; i < schema.getColumnCount(); ++i) {
            final Class columnType = schema.getColumnType(i);
            if (TreeMLWriter.TYPES.get(columnType) == null) {
                throw new DataIOException("Data type unsupported by the TreeML format: " + columnType.getName());
            }
        }
    }
    
    static {
        (TYPES = new HashMap()).put(Integer.TYPE, "Int");
        TreeMLWriter.TYPES.put(Long.TYPE, "Long");
        TreeMLWriter.TYPES.put(Float.TYPE, "Float");
        TreeMLWriter.TYPES.put(Double.TYPE, "Real");
        TreeMLWriter.TYPES.put(Boolean.TYPE, "Boolean");
        TreeMLWriter.TYPES.put(String.class, "String");
        TreeMLWriter.TYPES.put(Date.class, "Date");
    }
    
    public interface Tokens extends TreeMLReader.Tokens
    {
    }
}
