// 
// Decompiled by Procyon v0.5.30
// 

package medusa.extended;

import medusa.graph.Node;
import java.awt.Color;
import medusa.graph.Edge;
import medusa.graph.Graph;
import medusa.dataio.DataLoader;

public class ExtendedDataLoader extends DataLoader
{
    public Graph readParameters(final String s, final String s2) {
        final Graph graph = new Graph();
        final String[] split = s.split(";");
        final int length = split.length;
        System.out.println("checking " + split.length + " edges");
        for (int i = 0; i < length; ++i) {
            graph.addEdge(this.readEdgeParameter(split[i]));
        }
        System.out.println("checking nodes");
        if (s2 != null) {
            final String[] split2 = s2.split(";\\n?");
            for (int length2 = split2.length, j = 0; j < length2; ++j) {
                try {
                    this.readNodeParameter(graph, split2[j]);
                }
                catch (Exception ex) {
                    System.out.println("Error in line " + split2[j]);
                    System.out.println(ex.getMessage());
                }
            }
        }
        System.out.println("All done");
        return graph;
    }
    
    private String chomp(String trim) {
        trim = trim.trim();
        return trim;
    }
    
    private Edge readEdgeParameter(final String[] array) {
        if (array.length < 5) {
            return null;
        }
        final Edge edge = new Edge(array[0], array[1], Float.parseFloat(array[3]), Integer.parseInt(array[2]), Double.parseDouble(array[4]));
        if (array.length == 9) {
            edge.setWidth(Integer.parseInt(array[5]));
            edge.setDir(Integer.parseInt(array[6]));
            edge.setInteractLabel(array[7]);
            if (array.length >= 9) {
                edge.setURL(array[8]);
            }
            return edge;
        }
        if (array.length >= 6) {
            edge.setDir(Integer.parseInt(array[5]));
        }
        if (array.length >= 7) {
            edge.setInteractLabel(array[6]);
        }
        if (array.length >= 8) {
            edge.setURL(array[7]);
        }
        return edge;
    }
    
    private Edge readEdgeParameter(String chomp) {
        chomp = this.chomp(chomp);
        final String[] split = chomp.split(":");
        if (split.length < 5) {
            return null;
        }
        final Edge edge = new Edge(split[0], split[1], Float.parseFloat(split[3]), Integer.parseInt(split[2]), Double.parseDouble(split[4]));
        int index;
        int n;
        for (index = 0, n = 1; (index = chomp.indexOf(":", index)) != -1; ++index, ++n) {}
        if (n == 9) {
            edge.setWidth(Integer.parseInt(split[5]));
            edge.setDir(Integer.parseInt(split[6]));
            edge.setInteractLabel(split[7]);
            if (split.length >= 9) {
                edge.setURL(split[8]);
            }
            return edge;
        }
        if (split.length >= 6) {
            edge.setDir(Integer.parseInt(split[5]));
        }
        if (split.length >= 7) {
            edge.setInteractLabel(split[6]);
        }
        if (split.length >= 8) {
            edge.setURL(split[7]);
        }
        return edge;
    }
    
    private void readNodeParameter(final Graph graph, String chomp) throws Exception {
        chomp = this.chomp(chomp);
        final String[] split = chomp.split(":");
        if (split.length > 4) {
            final Node node = graph.getNode(split[0]);
            final double double1 = Double.parseDouble(split[1]);
            final double double2 = Double.parseDouble(split[2]);
            final String[] split2 = split[3].split(",");
            final Color color = new Color(Integer.parseInt(split2[0]), Integer.parseInt(split2[1]), Integer.parseInt(split2[2]));
            node.setXY(double1, double2);
            node.setColor(color);
            node.setShape(Integer.parseInt(split[4]));
            if (split.length >= 6) {
                node.setAnnotation(split[5]);
            }
            if (split.length == 7) {
                node.setURL(split[6]);
            }
        }
    }
}
