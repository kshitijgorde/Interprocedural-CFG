// 
// Decompiled by Procyon v0.5.30
// 

package medusa.dataio;

import java.util.HashMap;
import medusa.display.PaintTools;
import medusa.MedusaSettings;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.awt.Color;
import medusa.graph.Node;
import medusa.graph.Edge;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.File;
import medusa.DataFormatException;
import java.io.IOException;
import java.util.regex.Pattern;
import medusa.graph.Graph;

public class DataLoader implements Runnable
{
    private int x;
    private int y;
    private String fileName;
    private int nodeSize;
    public static final int LOAD_MEDUSA = 0;
    public static final int LOAD_TABBED = 1;
    private int loadType;
    Thread me;
    private Graph g;
    private boolean done;
    private boolean success;
    private String status;
    private int progress;
    private boolean busy;
    private String separator;
    Pattern structurePattern;
    Pattern startPattern;
    Pattern basicEdgePattern;
    Pattern confEdgePattern;
    Pattern orientationEdgePattern;
    Pattern visibleEdgePattern;
    Pattern interactionEdgePattern;
    Pattern basicNodePattern;
    Pattern xyNodePattern;
    Pattern colorNodePattern;
    Pattern color2NodePattern;
    Pattern color3NodePattern;
    Pattern fixedNodePattern;
    Pattern shapeNodePattern;
    Pattern dataspace1Pattern;
    Pattern annotationPattern;
    Pattern edgeParaPattern;
    Pattern testEdgeParaPattern;
    Pattern nodeParaPattern;
    String currentFileName;
    final double normalSize = 600.0;
    double max;
    private int fontSize;
    final int docWidth = 500;
    final int docHeight = 500;
    
    public void setLoadType(final int loadType) {
        this.loadType = loadType;
    }
    
    public DataLoader(final int x, final int y) {
        this.nodeSize = 10;
        this.me = null;
        this.status = "idle";
        this.busy = false;
        this.separator = "[\\t\\s]+";
        this.structurePattern = Pattern.compile("\\*nodes");
        this.startPattern = Pattern.compile("\\*edges");
        this.basicEdgePattern = Pattern.compile("^([^\\t]+)\\t([^\\t]+)");
        this.confEdgePattern = Pattern.compile("\\tc +([\\.\\d]+)");
        this.orientationEdgePattern = Pattern.compile("\\to +([\\-\\.\\d]+)");
        this.visibleEdgePattern = Pattern.compile("\\tf +(\\w+)");
        this.interactionEdgePattern = Pattern.compile("\\ti +(\\d+)");
        this.basicNodePattern = Pattern.compile("^([^\\t]+)");
        this.xyNodePattern = Pattern.compile("\\t([\\.\\d]+)\\t([\\.\\d]+)");
        this.colorNodePattern = Pattern.compile("\\tc *\\D*(\\d+)\\D*(\\d+)\\D*(\\d+)\\D*");
        this.color2NodePattern = Pattern.compile("\\tc2 *\\D*(\\d+)\\D*(\\d+)\\D*(\\d+)\\D*");
        this.color3NodePattern = Pattern.compile("\\tc3 *\\D*(\\d+)\\D*(\\d+)\\D*(\\d+)\\D*");
        this.fixedNodePattern = Pattern.compile("\\tf +(\\w+)");
        this.shapeNodePattern = Pattern.compile("\\ts +(\\d)");
        this.dataspace1Pattern = Pattern.compile("\\td1 +(\\d+)");
        this.annotationPattern = Pattern.compile("\\ta \\\"(.+)\\\"");
        this.edgeParaPattern = Pattern.compile("\\s*(\\w[^:^\\s]+):([^:]+):(\\d+):([\\d\\.]+):([\\-\\d\\.]+)");
        this.testEdgeParaPattern = Pattern.compile("\\s*(\\w[^:]+):");
        this.nodeParaPattern = Pattern.compile("\\s*(\\w[^:]+):([\\d\\.]+):([\\d\\.]+):(\\d+),(\\d+),(\\d+):(\\d)");
        this.currentFileName = null;
        this.fontSize = 10;
        this.x = x;
        this.y = y;
    }
    
    public DataLoader(final int x, final int y, final String fileName) {
        this.nodeSize = 10;
        this.me = null;
        this.status = "idle";
        this.busy = false;
        this.separator = "[\\t\\s]+";
        this.structurePattern = Pattern.compile("\\*nodes");
        this.startPattern = Pattern.compile("\\*edges");
        this.basicEdgePattern = Pattern.compile("^([^\\t]+)\\t([^\\t]+)");
        this.confEdgePattern = Pattern.compile("\\tc +([\\.\\d]+)");
        this.orientationEdgePattern = Pattern.compile("\\to +([\\-\\.\\d]+)");
        this.visibleEdgePattern = Pattern.compile("\\tf +(\\w+)");
        this.interactionEdgePattern = Pattern.compile("\\ti +(\\d+)");
        this.basicNodePattern = Pattern.compile("^([^\\t]+)");
        this.xyNodePattern = Pattern.compile("\\t([\\.\\d]+)\\t([\\.\\d]+)");
        this.colorNodePattern = Pattern.compile("\\tc *\\D*(\\d+)\\D*(\\d+)\\D*(\\d+)\\D*");
        this.color2NodePattern = Pattern.compile("\\tc2 *\\D*(\\d+)\\D*(\\d+)\\D*(\\d+)\\D*");
        this.color3NodePattern = Pattern.compile("\\tc3 *\\D*(\\d+)\\D*(\\d+)\\D*(\\d+)\\D*");
        this.fixedNodePattern = Pattern.compile("\\tf +(\\w+)");
        this.shapeNodePattern = Pattern.compile("\\ts +(\\d)");
        this.dataspace1Pattern = Pattern.compile("\\td1 +(\\d+)");
        this.annotationPattern = Pattern.compile("\\ta \\\"(.+)\\\"");
        this.edgeParaPattern = Pattern.compile("\\s*(\\w[^:^\\s]+):([^:]+):(\\d+):([\\d\\.]+):([\\-\\d\\.]+)");
        this.testEdgeParaPattern = Pattern.compile("\\s*(\\w[^:]+):");
        this.nodeParaPattern = Pattern.compile("\\s*(\\w[^:]+):([\\d\\.]+):([\\d\\.]+):(\\d+),(\\d+),(\\d+):(\\d)");
        this.currentFileName = null;
        this.fontSize = 10;
        this.x = x;
        this.y = y;
        this.fileName = fileName;
    }
    
    public DataLoader() {
        this.nodeSize = 10;
        this.me = null;
        this.status = "idle";
        this.busy = false;
        this.separator = "[\\t\\s]+";
        this.structurePattern = Pattern.compile("\\*nodes");
        this.startPattern = Pattern.compile("\\*edges");
        this.basicEdgePattern = Pattern.compile("^([^\\t]+)\\t([^\\t]+)");
        this.confEdgePattern = Pattern.compile("\\tc +([\\.\\d]+)");
        this.orientationEdgePattern = Pattern.compile("\\to +([\\-\\.\\d]+)");
        this.visibleEdgePattern = Pattern.compile("\\tf +(\\w+)");
        this.interactionEdgePattern = Pattern.compile("\\ti +(\\d+)");
        this.basicNodePattern = Pattern.compile("^([^\\t]+)");
        this.xyNodePattern = Pattern.compile("\\t([\\.\\d]+)\\t([\\.\\d]+)");
        this.colorNodePattern = Pattern.compile("\\tc *\\D*(\\d+)\\D*(\\d+)\\D*(\\d+)\\D*");
        this.color2NodePattern = Pattern.compile("\\tc2 *\\D*(\\d+)\\D*(\\d+)\\D*(\\d+)\\D*");
        this.color3NodePattern = Pattern.compile("\\tc3 *\\D*(\\d+)\\D*(\\d+)\\D*(\\d+)\\D*");
        this.fixedNodePattern = Pattern.compile("\\tf +(\\w+)");
        this.shapeNodePattern = Pattern.compile("\\ts +(\\d)");
        this.dataspace1Pattern = Pattern.compile("\\td1 +(\\d+)");
        this.annotationPattern = Pattern.compile("\\ta \\\"(.+)\\\"");
        this.edgeParaPattern = Pattern.compile("\\s*(\\w[^:^\\s]+):([^:]+):(\\d+):([\\d\\.]+):([\\-\\d\\.]+)");
        this.testEdgeParaPattern = Pattern.compile("\\s*(\\w[^:]+):");
        this.nodeParaPattern = Pattern.compile("\\s*(\\w[^:]+):([\\d\\.]+):([\\d\\.]+):(\\d+),(\\d+),(\\d+):(\\d)");
        this.currentFileName = null;
        this.fontSize = 10;
        this.x = 600;
        this.y = 600;
    }
    
    public void start() {
        if (this.me == null) {
            this.me = new Thread(this);
        }
        this.me.start();
    }
    
    public void stop() {
        if (this.me != null) {
            this.me = null;
        }
    }
    
    public Graph getGraph() {
        return this.g;
    }
    
    public void run() {
        if (this.me == Thread.currentThread()) {
            this.g = new Graph();
            this.busy = true;
            this.done = false;
            try {
                switch (this.loadType) {
                    case 0: {
                        this.g = this.load(this.fileName);
                        break;
                    }
                    case 1: {
                        this.g = this.loadSimplest(this.fileName);
                        break;
                    }
                }
                this.success = true;
            }
            catch (IOException ex) {
                this.success = false;
            }
            catch (DataFormatException ex2) {
                this.success = false;
            }
            this.busy = false;
        }
        this.done = true;
    }
    
    public boolean isDone() {
        return this.done;
    }
    
    public String getStatus() {
        return this.status;
    }
    
    public int getProgress() {
        return this.progress;
    }
    
    public boolean isBusy() {
        return this.busy;
    }
    
    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }
    
    public void save(final Graph graph, final String s) throws IOException {
        final FileWriter fileWriter = new FileWriter(new File(s));
        fileWriter.write(graph.report());
        fileWriter.close();
    }
    
    public void save(final Graph graph, final String s, final double n) throws IOException {
        final double n2 = n * 600.0;
        graph.rescaleNodes(1.0 / n2);
        final FileWriter fileWriter = new FileWriter(new File(s));
        fileWriter.write(graph.report());
        graph.rescaleNodes(n2);
        fileWriter.close();
    }
    
    public Graph load(final String currentFileName) throws IOException, DataFormatException {
        this.busy = true;
        this.max = 600.0;
        this.progress = 0;
        this.currentFileName = currentFileName;
        final Graph graph = new Graph();
        String string = "";
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(currentFileName))));
        String line;
        while ((line = bufferedReader.readLine()) != null && !this.startPattern.matcher(line).find()) {
            string = string + line + "\n";
            graph.addComment(string);
        }
        this.status = "Reading edges";
        String line2;
        while ((line2 = bufferedReader.readLine()) != null) {
            if (this.structurePattern.matcher(line2).find()) {
                this.status = "Reading nodes";
                break;
            }
            final Edge edge = this.readEdge(line2);
            if (edge == null) {
                continue;
            }
            graph.addEdge(edge);
            ++this.progress;
        }
        this.status = "Reading nodes";
        String line3;
        while ((line3 = bufferedReader.readLine()) != null) {
            this.readNode(graph, line3);
        }
        bufferedReader.close();
        this.busy = false;
        this.status = "idle";
        return graph;
    }
    
    public Graph loadSimplest(final String s) throws IOException, DataFormatException {
        final Graph graph = new Graph();
        this.progress = 0;
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(s))));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            final String[] split = line.split("\t");
            if (split.length <= 2) {
                throw new DataFormatException("File does not have three columns");
            }
            graph.addEdge(new Node(split[0]), new Node(split[1]), Float.parseFloat(split[2]), 1);
            ++this.progress;
        }
        bufferedReader.close();
        graph.rescaleNodePercentage();
        if (graph.getEdgeSize() == 0) {
            throw new DataFormatException("No edges found");
        }
        return graph;
    }
    
    private void readNode(final Graph graph, final String s) {
        final Matcher matcher = this.basicNodePattern.matcher(s);
        if (!matcher.find()) {
            return;
        }
        final Node node = graph.getNode(matcher.group(1));
        if (node == null) {
            return;
        }
        final Matcher matcher2 = this.xyNodePattern.matcher(s);
        if (matcher2.find()) {
            double double1 = Double.parseDouble(matcher2.group(1));
            double double2 = Double.parseDouble(matcher2.group(2));
            if (double1 <= 1.0) {
                double1 *= this.x;
            }
            if (double2 <= 1.0) {
                double2 *= this.y;
            }
            node.setXY(double1, double2);
        }
        final Matcher matcher3 = this.colorNodePattern.matcher(s);
        if (matcher3.find()) {
            node.setColor(new Color(Integer.parseInt(matcher3.group(1)), Integer.parseInt(matcher3.group(2)), Integer.parseInt(matcher3.group(3))));
        }
        final Matcher matcher4 = this.color2NodePattern.matcher(s);
        if (matcher4.find()) {
            node.setColor2(new Color(Integer.parseInt(matcher4.group(1)), Integer.parseInt(matcher4.group(2)), Integer.parseInt(matcher4.group(3))));
        }
        final Matcher matcher5 = this.color3NodePattern.matcher(s);
        if (matcher5.find()) {
            node.setColor3(new Color(Integer.parseInt(matcher5.group(1)), Integer.parseInt(matcher5.group(2)), Integer.parseInt(matcher5.group(3))));
        }
        final Matcher matcher6 = this.shapeNodePattern.matcher(s);
        if (matcher6.find()) {
            node.setShape(Integer.parseInt(matcher6.group(1)));
        }
        final Matcher matcher7 = this.dataspace1Pattern.matcher(s);
        if (matcher7.find()) {
            node.setDataSpace1(Integer.parseInt(matcher7.group(1)));
        }
        final Matcher matcher8 = this.annotationPattern.matcher(s);
        if (matcher8.find()) {
            node.setAnnotation(matcher8.group(1));
        }
        final Matcher matcher9 = this.fixedNodePattern.matcher(s);
        if (matcher9.find()) {
            node.setFixed(Boolean.getBoolean(matcher9.group(1)));
        }
    }
    
    private Edge readEdge(final String s) throws DataFormatException {
        final Matcher matcher = this.basicEdgePattern.matcher(s);
        if (!matcher.find()) {
            throw new DataFormatException("Error in data file at line:\n " + s + ".\nEdge must contain basic node data." + "If you are using an old data file version," + "consider using the conversion utility\n" + "Offending line: \n" + s);
        }
        final Edge edge = new Edge(matcher.group(1), matcher.group(2));
        final Matcher matcher2 = this.confEdgePattern.matcher(s);
        if (matcher2.find()) {
            final float float1 = Float.parseFloat(matcher2.group(1));
            if (float1 < 0.0f | float1 > 1.0f) {
                throw new DataFormatException("Confidence may not be less than zero or greater than one.\n" + matcher2.group(1) + " in line: \n" + s);
            }
            edge.setConf(float1);
        }
        final Matcher matcher3 = this.orientationEdgePattern.matcher(s);
        if (matcher3.find()) {
            edge.setOrientation(Double.parseDouble(matcher3.group(1)));
        }
        final Matcher matcher4 = this.interactionEdgePattern.matcher(s);
        if (matcher4.find()) {
            edge.setType(Integer.parseInt(matcher4.group(1)));
        }
        return edge;
    }
    
    public Graph readParameters(final String s, final String s2) {
        final Graph graph = new Graph();
        final String[] split = s.split("[;\\n?]");
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
    
    public void normalizePositions(final Graph graph) {
        final Iterator<Node> nodesIterator = graph.nodesIterator();
        while (nodesIterator.hasNext()) {
            final Node node = nodesIterator.next();
            node.setXY(node.getX() / this.x, node.getY() / this.y);
        }
    }
    
    private String chomp(String replaceAll) {
        replaceAll = replaceAll.replaceAll("\\s", "");
        return replaceAll;
    }
    
    private Edge readEdgeParameter(final String[] array) {
        return new Edge(array[0], array[1], Float.parseFloat(array[3]), Integer.parseInt(array[2]), Double.parseDouble(array[4]));
    }
    
    private Edge readEdgeParameter(String chomp) {
        chomp = this.chomp(chomp);
        final String[] split = chomp.split(":");
        return new Edge(split[0], split[1], Float.parseFloat(split[3]), Integer.parseInt(split[2]), Double.parseDouble(split[4]));
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
            if (split.length == 6) {
                node.setAnnotation(split[5]);
            }
        }
    }
    
    private void readNodeParameter_backup(final Graph graph, final String s) {
        final Matcher matcher = this.nodeParaPattern.matcher(s);
        if (!matcher.find()) {
            return;
        }
        final Node node = graph.getNode(matcher.group(1));
        if (node == null) {
            System.out.println("node not found!");
            return;
        }
        double double1 = Double.parseDouble(matcher.group(2));
        double double2 = Double.parseDouble(matcher.group(3));
        if (double1 <= 1.0) {
            double1 *= this.x;
        }
        if (double2 <= 1.0) {
            double2 *= this.x;
        }
        try {
            System.out.println("Setting " + node.getLabel() + " to " + double1 + "," + double2);
            node.setXY(double1, double2);
        }
        catch (NullPointerException ex) {
            System.out.println("Problem with line " + s);
            ex.printStackTrace();
        }
        node.setColor(new Color(Integer.parseInt(matcher.group(4)), Integer.parseInt(matcher.group(5)), Integer.parseInt(matcher.group(6))));
        node.setShape(Integer.parseInt(matcher.group(7)));
        final Matcher matcher2 = Pattern.compile("\\'([^\\']+)\\'").matcher(s);
        if (matcher2.find()) {
            System.out.println(s);
            node.setAnnotation(matcher2.group(1));
        }
    }
    
    public void saveAsPS(final Graph graph, final String s, final MedusaSettings medusaSettings) throws IOException {
        this.saveAsPS(graph, s, medusaSettings, this.nodeSize, this.fontSize);
    }
    
    public void saveAsPS(final Graph graph, final String s, final MedusaSettings medusaSettings, final int nodeSize, final int fontSize) throws IOException {
        this.nodeSize = nodeSize;
        this.fontSize = fontSize;
        final FileWriter fileWriter = new FileWriter(new File(s));
        fileWriter.write("%!PS\n");
        this.drawGraph(graph, fileWriter, medusaSettings);
        fileWriter.write("showpage\n");
        fileWriter.close();
    }
    
    public void saveAsEPS(final Graph graph, final String s, final MedusaSettings medusaSettings, final int nodeSize, final int fontSize) throws IOException {
        this.nodeSize = nodeSize;
        this.fontSize = fontSize;
        final FileWriter fileWriter = new FileWriter(new File(s));
        fileWriter.write("%!PS-Adobe EPSF-3.0\n");
        fileWriter.write("%%Creator: Medusa\n");
        fileWriter.write("%%BoundingBox: 0 0 500 500\n");
        this.drawGraph(graph, fileWriter, medusaSettings);
        fileWriter.close();
    }
    
    private void drawGraph(final Graph graph, final FileWriter fileWriter, final MedusaSettings medusaSettings) throws IOException {
        fileWriter.write("50 50 translate\n");
        fileWriter.write("0.2 setlinewidth\n");
        fileWriter.write("0 0 0 setrgbcolor\n");
        this.drawEdges(fileWriter, graph, medusaSettings);
        this.drawNodes(fileWriter, graph);
        this.drawLabels(fileWriter, graph);
    }
    
    private int psY(final double n) {
        return (int)((600.0 - n) / 600.0 * 500.0);
    }
    
    private int psX(final double n) {
        return (int)(n / 600.0 * 500.0);
    }
    
    private void drawEdges(final FileWriter fileWriter, final Graph graph, final MedusaSettings medusaSettings) throws IOException {
        final String[] array = new String[2];
        fileWriter.write("%% drawing edges\n");
        final Iterator<Edge> edgesIterator = graph.edgesIterator();
        while (edgesIterator.hasNext()) {
            final Edge edge = edgesIterator.next();
            final String[] nodes = edge.getNodes();
            final Node node = graph.getNode(nodes[0]);
            final int psX = this.psX(node.getX());
            final int psY = this.psY(node.getY());
            final Node node2 = graph.getNode(nodes[1]);
            final int psX2 = this.psX(node2.getX());
            final int psY2 = this.psY(node2.getY());
            final Color color = medusaSettings.getColor(new Integer(edge.getType()));
            final float conf = edge.getConf();
            fileWriter.write(this.colorToString(color));
            fileWriter.write(conf + " setalpha\n");
            if (edge.getOrientation() != 0.0) {
                final int[] intControlPoints = PaintTools.intControlPoints(psX, psY, psX2, psY2, edge.getOrientation(), 0.3);
                fileWriter.write(psX + " " + psY + " moveto\n");
                fileWriter.write(intControlPoints[0] + " " + intControlPoints[1] + " " + intControlPoints[2] + " " + intControlPoints[3] + " " + psX2 + " " + psY2 + " curveto stroke\n");
            }
            else {
                fileWriter.write(psX + " " + psY + " moveto\n");
                fileWriter.write(psX2 + " " + psY2 + " lineto stroke\n");
            }
        }
        fileWriter.write("1.0 setalpha\n");
    }
    
    private String colorToString(final Color color) {
        return color.getRed() / 255.0 + " " + color.getGreen() / 255.0 + " " + color.getBlue() / 255.0 + " setrgbcolor\n";
    }
    
    private void drawLabels(final FileWriter fileWriter, final Graph graph) throws IOException {
        final int n = 5;
        fileWriter.write("%% drawing labels\n");
        fileWriter.write("0 0 0 setrgbcolor\n");
        final Iterator<Node> nodesIterator = graph.nodesIterator();
        while (nodesIterator.hasNext()) {
            final Node node = nodesIterator.next();
            final int psX = this.psX(node.getX());
            final int psY = this.psY(node.getY());
            fileWriter.write("/Times-Roman findfont\n" + this.fontSize + " scalefont\nsetfont\nnewpath\n");
            fileWriter.write(psX - n + " " + (psY + n) + " moveto\n");
            fileWriter.write("(" + node.getLabel() + ") show\n");
        }
    }
    
    private void drawNodes(final FileWriter fileWriter, final Graph graph) throws IOException {
        fileWriter.write("%% drawing nodes\n");
        final Iterator<Node> nodesIterator = graph.nodesIterator();
        while (nodesIterator.hasNext()) {
            this.drawNodeShape(fileWriter, nodesIterator.next());
        }
    }
    
    private void drawNodeShape(final FileWriter fileWriter, final Node node) throws IOException {
        final int psX = this.psX(node.getX());
        final int psY = this.psY(node.getY());
        final Color color = node.getColor();
        final int shape = node.getShape();
        fileWriter.write(this.colorToString(color));
        switch (shape) {
            case 1: {
                fileWriter.write(PaintTools.psRectangle(psX, psY, this.nodeSize));
                break;
            }
            case 2: {
                fileWriter.write(PaintTools.psTriangle(psX, psY, this.nodeSize));
                break;
            }
            case 3: {
                fileWriter.write(PaintTools.psRhomb(psX, psY, this.nodeSize));
                break;
            }
            default: {
                fileWriter.write(PaintTools.psCircle(psX, psY, this.nodeSize));
                break;
            }
        }
    }
    
    public void saveAsPajek(final Graph graph, final String s) throws IOException {
        final FileWriter fileWriter = new FileWriter(new File(s));
        fileWriter.write("*Vertices\t" + graph.getNodeSize() + "\n");
        int n = 1;
        final String s2 = "\r\n";
        final HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        final Iterator<Node> nodesIterator = graph.nodesIterator();
        while (nodesIterator.hasNext()) {
            final Node node = nodesIterator.next();
            fileWriter.write(n + "\t\"" + node.getLabel() + "\"\t" + node.getX() + "\t" + node.getY() + s2);
            hashMap.put(node.getLabel(), new Integer(n));
            ++n;
        }
        final String[] array = new String[2];
        fileWriter.write("*Edges\n");
        final Iterator<Edge> edgesIterator = graph.edgesIterator();
        while (edgesIterator.hasNext()) {
            final Edge edge = edgesIterator.next();
            final String[] nodes = edge.getNodes();
            fileWriter.write(hashMap.get(nodes[0]) + "\t" + hashMap.get(nodes[1]) + "\t" + edge.getType() + s2);
        }
        fileWriter.close();
    }
    
    public void saveHTMLParameters(final Graph graph, final String s) throws IOException {
        final StringBuffer sb = new StringBuffer();
        sb.append("<!-- edge parameters generated by Medusa -->\n");
        sb.append("<param name=\"edges\" value=\"\n");
        final Iterator<Edge> edgesIterator = graph.edgesIterator();
        while (edgesIterator.hasNext()) {
            final Edge edge = edgesIterator.next();
            sb.append(edge.getFromName());
            sb.append(":");
            sb.append(edge.getToName());
            sb.append(":");
            sb.append(edge.getType());
            sb.append(":");
            sb.append(edge.getConf());
            sb.append(":");
            sb.append(edge.getOrientation());
            sb.append(";\n");
        }
        sb.append("\"/>\n");
        sb.append("<!-- node parameters generated by Medusa -->\n");
        sb.append("<param name=\"nodes\" value=\"\n");
        final Iterator<Node> nodesIterator = graph.nodesIterator();
        while (nodesIterator.hasNext()) {
            final Node node = nodesIterator.next();
            sb.append(node.getLabel());
            sb.append(":");
            sb.append(node.getX());
            sb.append(":");
            sb.append(node.getY());
            sb.append(":");
            sb.append(node.getAppletColorEntry());
            sb.append(":");
            sb.append(node.getShape());
            sb.append(":");
            sb.append(node.getAnnotation());
            sb.append(";\n");
        }
        sb.append("\"/>\n");
        final FileWriter fileWriter = new FileWriter(s);
        fileWriter.write(sb.toString());
        fileWriter.close();
    }
}
