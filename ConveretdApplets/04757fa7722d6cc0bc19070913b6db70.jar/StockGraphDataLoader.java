import java.net.URLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class StockGraphDataLoader
{
    private String URLString;
    private String dataStream;
    private String graphStream;
    private String[] graphDataNames;
    private Vector graphData1;
    private double maxValue;
    private double minValue;
    private boolean origin2Zero;
    private boolean fetchDataStatus;
    
    public StockGraphDataLoader() {
        this.URLString = "";
        this.fetchDataStatus = false;
    }
    
    public void setURL(final String URLString) {
        this.URLString = URLString;
    }
    
    public void setDataStream(final String dataStream) {
        this.dataStream = dataStream;
    }
    
    public void setOrigin(final String origin2Zero) {
        if (origin2Zero.equals("TRUE") || origin2Zero.equals("1")) {
            this.origin2Zero = true;
        }
        else {
            this.origin2Zero = false;
        }
    }
    
    public void refreshData() {
        this.fetchDataStatus = true;
        if (this.URLString.equals("")) {
            this.graphStream = this.dataStream;
        }
        else {
            this.graphStream = this.executeQuery();
        }
        this.parseStream();
        this.fetchDataStatus = false;
    }
    
    private String executeQuery() {
        String resultString = "";
        try {
            final URL url = new URL(this.URLString);
            final URLConnection uc = url.openConnection();
            uc.setDoOutput(true);
            uc.setDoInput(true);
            uc.setUseCaches(false);
            uc.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
            final InputStreamReader in = new InputStreamReader(uc.getInputStream());
            for (int chr = in.read(); chr != -1; chr = in.read()) {
                resultString += String.valueOf((char)chr);
            }
            in.close();
        }
        catch (MalformedURLException e) {
            System.out.println(e.toString());
        }
        catch (IOException e2) {
            System.out.println(e2.toString());
        }
        return resultString;
    }
    
    private Vector getGraphData(final String dataStream, final boolean withName) {
        final Vector graphData = new Vector();
        final String[] stringArray1 = Convert.string2Array(dataStream, ";");
        if (withName) {
            this.graphDataNames = new String[stringArray1.length];
        }
        for (int i = 0; i < stringArray1.length; ++i) {
            final String[] stringArray2 = Convert.string2Array(stringArray1[i], "|");
            final int arrayLength = stringArray2.length - 1;
            if (withName) {
                this.graphDataNames[i] = stringArray2[0];
            }
            final double[] dblArray = new double[arrayLength];
            for (int j = 1; j < stringArray2.length; ++j) {
                final double temp = Double.valueOf(stringArray2[j]);
                dblArray[j - 1] = temp;
                if (!this.origin2Zero && j == 1 && i == 0) {
                    final double n = temp;
                    this.minValue = n;
                    this.maxValue = n;
                }
                if (temp > this.maxValue) {
                    this.maxValue = temp;
                }
                if (temp < this.minValue) {
                    this.minValue = temp;
                }
            }
            graphData.addElement(dblArray);
        }
        System.out.println("Max Value:" + this.maxValue);
        System.out.println("Min Value:" + this.minValue);
        return graphData;
    }
    
    private void adjustGraphData(final Vector graphData) {
        for (int i = 0; i < graphData.size(); ++i) {
            final double[] dblArray = graphData.elementAt(i);
            for (int j = 0; j < dblArray.length; ++j) {
                dblArray[j] -= this.minValue;
            }
            graphData.setElementAt(dblArray, i);
        }
    }
    
    private void parseStream() {
        this.maxValue = 0.0;
        this.minValue = 0.0;
        this.graphData1 = this.getGraphData(this.graphStream, true);
        if (this.maxValue == this.minValue) {
            ++this.maxValue;
            --this.minValue;
        }
    }
    
    public Vector getGraphStream1() {
        return this.graphData1;
    }
    
    public String[] getGraphDataNames() {
        if (this.graphDataNames[0].indexOf("Error") > -1) {
            System.out.println(this.graphDataNames[0]);
            this.graphDataNames[0] = "";
        }
        return this.graphDataNames;
    }
    
    public double getMinValue() {
        return this.minValue;
    }
    
    public double getMaxValue() {
        return this.maxValue;
    }
    
    public boolean fetchingData() {
        return this.fetchDataStatus;
    }
}
