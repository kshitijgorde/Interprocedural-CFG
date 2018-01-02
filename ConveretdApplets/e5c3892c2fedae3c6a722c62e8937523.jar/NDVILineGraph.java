import java.awt.print.PrinterException;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.net.URL;
import java.awt.Color;
import java.text.DecimalFormat;
import java.util.Vector;
import java.awt.print.Printable;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class NDVILineGraph extends JPanel implements MouseListener, Printable
{
    private imgViewer applet;
    private MosaicData md;
    private LineGraph lineGraph;
    private NDVIGraphDialog ndviGraphDialog;
    private String[] landcoverNames;
    private int gridCol;
    private int gridRow;
    private int displayYear;
    private int topMargin;
    private int bottomMargin;
    private int leftMargin;
    private int rightMargin;
    private Metadata currentScene;
    private Sensor currSensor;
    private Landcover landcover;
    private Vector availLandcover;
    private int firstYear;
    private DecimalFormat threeDigitFormat;
    private static String[] XAxisValues;
    private boolean dataAvailable;
    
    public NDVILineGraph(final imgViewer applet, final MosaicData md, final NDVIGraphDialog ndviGraphDialog, final String[] landcoverNames) {
        this.displayYear = 0;
        this.applet = applet;
        this.md = md;
        this.ndviGraphDialog = ndviGraphDialog;
        this.landcoverNames = landcoverNames;
        this.availLandcover = new Vector();
        this.setBackground(Color.WHITE);
        this.addMouseListener(this);
        final Color[] array = { new Color(0, 0, 0), new Color(255, 0, 255), new Color(0, 135, 0), new Color(255, 0, 0), new Color(0, 0, 252), new Color(156, 85, 0), new Color(145, 0, 205), new Color(255, 111, 0), new Color(153, 153, 153), new Color(0, 153, 153), new Color(204, 204, 0), new Color(255, 111, 0), new Color(0, 0, 0), new Color(255, 0, 255), new Color(0, 135, 0), new Color(255, 0, 0), new Color(0, 0, 252), new Color(156, 85, 0), new Color(145, 0, 205), new Color(204, 204, 0) };
        this.topMargin = 20;
        this.bottomMargin = 110;
        this.leftMargin = 50;
        this.rightMargin = 30;
        this.lineGraph = new LineGraph(array, this.topMargin, this.bottomMargin, this.leftMargin, this.rightMargin, 20, 1.0, 0.0, 26, 0.1, 10, true);
        this.threeDigitFormat = new DecimalFormat("000");
    }
    
    public Metadata getScene() {
        return this.currentScene;
    }
    
    public Sensor getSensor() {
        return this.currSensor;
    }
    
    public void checkAvailability(final double[][] array) {
        this.dataAvailable = false;
        for (int i = 0; i < array.length; ++i) {
            if (array[i] != null) {
                this.dataAvailable = true;
                break;
            }
        }
    }
    
    public boolean isDataAvailable() {
        return this.dataAvailable;
    }
    
    private void read() {
        this.currSensor = this.applet.sensorMenu.getCurrentSensor();
        final TOC currentCell = this.md.getCurrentCell();
        this.gridCol = currentCell.gridCol;
        this.gridRow = currentCell.gridRow;
        this.currentScene = this.md.getCurrentScene();
        if (this.currentScene == null) {
            this.availLandcover.removeAllElements();
            return;
        }
        this.displayYear = this.currentScene.year;
        BufferedReader bufferedReader = null;
        this.availLandcover.removeAllElements();
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new GZIPInputStream(new URL(this.applet.getCodeBase(), "NDVI/p" + this.threeDigitFormat.format(this.gridCol) + "/r" + this.threeDigitFormat.format(this.gridRow) + "/NDVI.gz").openStream())));
            final String line = bufferedReader.readLine();
            if (line == null) {
                System.out.println("Error reading NDVI Data file for gridCol/gridRow " + this.gridCol + "/" + this.gridRow);
                bufferedReader.close();
                return;
            }
            int int2;
            try {
                final StringTokenizer stringTokenizer = new StringTokenizer(line, ",");
                final int int1 = Integer.parseInt(stringTokenizer.nextToken());
                if (int1 != this.gridCol) {
                    System.out.println("Error in NDVI Data file -- incorrect Path specified.");
                    System.out.println(" " + int1 + " " + this.gridCol + "\n");
                    bufferedReader.close();
                    return;
                }
                if (Integer.parseInt(stringTokenizer.nextToken()) != this.gridRow) {
                    System.out.println("Error in NDVI Data file -- incorrect Row specified.");
                    bufferedReader.close();
                    return;
                }
                int2 = Integer.parseInt(stringTokenizer.nextToken());
            }
            catch (NoSuchElementException ex) {
                System.out.println("Exception:  " + ex.getMessage());
                bufferedReader.close();
                return;
            }
            catch (NumberFormatException ex2) {
                System.out.println("Exception:  " + ex2.getMessage());
                bufferedReader.close();
                return;
            }
            this.firstYear = -1;
            for (int i = 0; i < int2; ++i) {
                final String line2 = bufferedReader.readLine();
                final int int3 = Integer.parseInt(new StringTokenizer(line2, ",").nextToken());
                if (this.firstYear == -1) {
                    this.firstYear = int3;
                }
                if (line2 == null) {
                    System.out.println("Error reading NDVI Data file for gridCol/gridRow " + this.gridCol + "/" + this.gridRow);
                    bufferedReader.close();
                    return;
                }
                try {
                    final Landcover landcover = new Landcover();
                    String name = "";
                    final double[] ndviValues = new double[26];
                    String s = "";
                    final String[] split = line2.split(",");
                    for (int j = 0; j < split.length; ++j) {
                        if (j == 1) {
                            s = split[j];
                        }
                        else if (j == 2) {
                            name = split[j];
                        }
                        else if (j >= 3) {
                            ndviValues[j - 3] = (Integer.parseInt(split[j]) - 100) / 100.0;
                        }
                    }
                    if (name.equals("Grassland/Herbaceous")) {
                        name = "Herb. Grasslands";
                    }
                    else if (name.equals("Emergent Herbaceous Wetlands")) {
                        name = "Herb. Wetlands";
                    }
                    landcover.ndviValues = ndviValues;
                    landcover.year = int3;
                    landcover.name = name;
                    landcover.count = Integer.parseInt(s);
                    this.availLandcover.addElement(landcover);
                }
                catch (NoSuchElementException ex3) {
                    System.out.println("Exception:  " + ex3.getMessage());
                    bufferedReader.close();
                    return;
                }
                catch (NumberFormatException ex4) {
                    System.out.println("Exception:  " + ex4.getMessage());
                    bufferedReader.close();
                    return;
                }
            }
        }
        catch (IOException ex5) {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                }
                catch (Exception ex6) {}
                System.out.println("Exception:  " + ex5.getMessage());
            }
        }
    }
    
    public void paintComponent(final Graphics graphics) {
        final Dimension size = this.getSize();
        String string = "NDVI Data for ";
        if (this.currentScene != null) {
            string = string + "Year " + this.currentScene.year;
        }
        final String string2 = string + " Path " + this.gridCol + " Row " + this.gridRow;
        final String s = "";
        final String s2 = "NDVI Values";
        final Vector<double[]> vector = new Vector<double[]>();
        final Vector<String> vector2 = new Vector<String>();
        final Vector<Integer> vector3 = new Vector<Integer>();
        int n = 0;
        for (int size2 = this.availLandcover.size(), i = 0; i < size2; ++i) {
            final Landcover landcover = this.availLandcover.elementAt(i);
            if (landcover.year == this.currentScene.year && this.ndviGraphDialog.setLandcover(landcover.name + ": (" + landcover.count + ")")) {
                vector.addElement(landcover.ndviValues);
                vector2.addElement(landcover.name);
                vector3.addElement(this.ndviGraphDialog.getColors(landcover.name + ": (" + landcover.count + ")"));
                ++n;
            }
        }
        final double[][] array = new double[vector.size()][];
        vector.toArray(array);
        final String[] array2 = new String[vector2.size()];
        vector2.toArray(array2);
        final Integer[] array3 = new Integer[vector3.size()];
        vector3.toArray(array3);
        this.checkAvailability(array);
        this.lineGraph.drawGraphLayout(graphics, NDVILineGraph.XAxisValues, size, string2, s, s2);
        if (this.currentScene != null && this.isDataAvailable()) {
            this.lineGraph.drawSelectedDataRange(graphics, size, (this.currentScene.jDate - 14.0f) / this.getDaysInScale());
        }
        this.lineGraph.drawPoints(graphics, size, array, array3);
        this.lineGraph.drawLegend(graphics, size, array2, array3);
    }
    
    public void setDefaultLandcover() {
        if (this.applet.sensorMenu.getCurrentSensor().hasNdviLineGraph) {
            this.performAction();
        }
    }
    
    public void performAction() {
        boolean b = true;
        final Metadata currentScene = this.md.getCurrentScene();
        if (currentScene == null || this.gridCol != currentScene.gridCol || this.gridRow != currentScene.gridRow || this.currSensor != this.applet.sensorMenu.getCurrentSensor()) {
            this.read();
        }
        this.currentScene = currentScene;
        if (this.ndviGraphDialog.isLandcoverSelected() && currentScene != null && this.displayYear == currentScene.year) {
            b = false;
        }
        if (currentScene != null) {
            this.displayYear = currentScene.year;
        }
        int n = 0;
        final DecimalFormat decimalFormat = new DecimalFormat("###,###.##");
        int n2 = this.firstYear;
        if (currentScene != null) {
            n2 = currentScene.year;
        }
        for (int size = this.availLandcover.size(), i = 0; i < size; ++i) {
            final Landcover landcover = this.availLandcover.elementAt(i);
            if (landcover.year == n2) {
                final String string = landcover.name + ": (" + decimalFormat.format(landcover.count) + ")";
                if (b && n <= 4) {
                    this.ndviGraphDialog.setDefault(string);
                }
                else if (b) {
                    if (!this.ndviGraphDialog.setLandcover(string)) {
                        this.ndviGraphDialog.setAvailLandcover(string);
                    }
                }
                else {
                    this.ndviGraphDialog.setAvailLandcover(string);
                }
                ++n;
            }
        }
        if (this.ndviGraphDialog.isLandcoverSelected()) {
            this.ndviGraphDialog.enableButtons();
        }
        else {
            this.ndviGraphDialog.disableButtons();
        }
    }
    
    private int getDaysInScale() {
        final int year = this.currentScene.year;
        int n = 351;
        if (year % 4 != 0 || (year % 100 == 0 && year % 400 != 0)) {
            n = 352;
        }
        return n;
    }
    
    @Override
    public void mouseClicked(final MouseEvent mouseEvent) {
        final Dimension size = this.getSize();
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        final int leftMargin = this.leftMargin;
        final int n = size.width - this.rightMargin;
        final int topMargin = this.topMargin;
        final int n2 = size.height - this.bottomMargin;
        if (x > leftMargin && x < n && y > topMargin && y < n2 && this.currentScene != null && this.isDataAvailable()) {
            this.md.setSceneToClosestDate(this.currentScene, (int)(this.getDaysInScale() * this.lineGraph.getXPercentage(x) + 0.5f) + 14, this.currentScene.year);
        }
    }
    
    @Override
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    @Override
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    @Override
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    @Override
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    @Override
    public int print(final Graphics graphics, final PageFormat pageFormat, final int n) throws PrinterException {
        if (n >= 1) {
            return 1;
        }
        final Dimension size = this.getSize();
        double n2 = pageFormat.getImageableWidth() / size.width;
        final double n3 = pageFormat.getImageableHeight() / size.height;
        if (n3 < n2) {
            n2 = n3;
        }
        final Graphics2D graphics2D = (Graphics2D)graphics;
        graphics2D.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
        graphics2D.scale(n2, n2);
        this.paintComponent(graphics2D);
        return 0;
    }
    
    static {
        NDVILineGraph.XAxisValues = new String[] { "Jan 14", "Jan 28", "Feb 11", "Feb 25", "Mar 11", "Mar 25", "Apr 8", "Apr 22", "May 6", "May 20", "Jun 3", "Jun 17", "Jul 1", "Jul 15", "Jul 29", "Aug 12", "Aug 26", "Sep 9", "Sep 23", "Oct 7", "Oct 21", "Nov 4", "Nov 18", "Dec 2", "Dec 16", "Dec 31" };
    }
    
    class Landcover
    {
        String name;
        int year;
        int count;
        double[] ndviValues;
    }
}
