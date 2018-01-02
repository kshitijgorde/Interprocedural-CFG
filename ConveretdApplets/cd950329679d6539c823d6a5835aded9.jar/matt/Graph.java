// 
// Decompiled by Procyon v0.5.30
// 

package matt;

import java.util.Enumeration;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.awt.Component;
import java.awt.Color;
import java.util.Vector;
import javax.swing.JScrollPane;

public class Graph extends JScrollPane
{
    GraphPanel panel;
    private float scalingFactor;
    Vector series;
    
    public void setBackground(final Color c) {
        super.setBackground(c);
        if (this.panel != null) {
            this.panel.setBackground(c);
        }
    }
    
    public Graph() {
        this.scalingFactor = 0.0f;
        this.series = new Vector();
        (this.panel = new GraphPanel(this)).setDoubleBuffered(false);
        this.getViewport().add(this.panel, null);
        this.scalingFactor = MattProperties.getFloat("scaleGraphFactor");
        this.setHorizontalScrollBarPolicy(30);
        this.setVerticalScrollBarPolicy(21);
    }
    
    public void save() {
        String fileName = null;
        try {
            while (true) {
                final SimpleDateFormat dateFormat = new SimpleDateFormat();
                dateFormat.applyPattern("dd-MM-yyyy HHmmssSS");
                final long start = System.currentTimeMillis();
                final Date now = new Date();
                now.setTime(start);
                fileName = MattProperties.instance().getProperty("resultsFolder") + System.getProperty("file.separator") + "graph " + dateFormat.format(now) + ".txt";
                if (!new File(fileName).exists()) {
                    break;
                }
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex) {}
            }
            final FileWriter fw = new FileWriter(fileName);
            final Enumeration en = this.series.elements();
            while (en.hasMoreElements()) {
                final Series series = en.nextElement();
                final float[] data = series.getData();
                for (int i = 0; i < data.length; ++i) {
                    fw.write("" + data[i]);
                    if (i < data.length - 1) {
                        fw.write(",");
                    }
                }
                fw.write(System.getProperty("line.separator"));
            }
            fw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void clear() {
        final Enumeration en = this.series.elements();
        while (en.hasMoreElements()) {
            final Series series = en.nextElement();
            series.clear();
        }
    }
    
    public Series getSeries(final int series) {
        return this.series.elementAt(series);
    }
    
    public Series getDefaultSeries() {
        if (this.series.size() == 0) {
            final Series series = new Series(this);
            this.series.add(series);
        }
        return this.series.elementAt(0);
    }
    
    public void addSeries(final Series series) {
        this.series.add(series);
    }
    
    public void removeAllSeries() {
        this.series.removeAllElements();
    }
    
    public int countSeries() {
        return this.series.size();
    }
    
    public GraphPanel getGraphPanel() {
        return this.panel;
    }
    
    public float getScalingFactor() {
        return this.scalingFactor;
    }
    
    public void setScalingFactor(final float scalingFactor) {
        this.scalingFactor = scalingFactor;
    }
}
