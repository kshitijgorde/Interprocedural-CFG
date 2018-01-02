import java.net.URLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class MarketGraphDataLoader
{
    private String URLString;
    private String dataStream;
    private String graphStream;
    private String[] graphDataNames;
    private Vector graphData1;
    private Vector graphData2;
    private double maxValue;
    private double minValue;
    private double minLineValue;
    private double maxLineValue;
    private double minBarValue;
    private double maxBarValue;
    private boolean origin2Zero;
    private boolean fetchDataStatus;
    private String closeTime;
    
    public MarketGraphDataLoader() {
        this.URLString = "";
        this.fetchDataStatus = false;
        this.closeTime = "";
    }
    
    public void setURL(final String urlString) {
        this.URLString = urlString;
    }
    
    public void setDataStream(final String dataStream) {
        this.dataStream = dataStream;
    }
    
    public void setOrigin(final String s) {
        if (s.equals("TRUE") || s.equals("1")) {
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
        String string = "";
        try {
            final URLConnection openConnection = new URL(this.URLString).openConnection();
            openConnection.setDoOutput(true);
            openConnection.setDoInput(true);
            openConnection.setUseCaches(false);
            openConnection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
            final InputStreamReader inputStreamReader = new InputStreamReader(openConnection.getInputStream());
            for (int i = inputStreamReader.read(); i != -1; i = inputStreamReader.read()) {
                string += String.valueOf((char)i);
            }
            inputStreamReader.close();
        }
        catch (MalformedURLException ex) {
            System.out.println(ex.toString());
        }
        catch (IOException ex2) {
            System.out.println(ex2.toString());
        }
        return string;
    }
    
    private Vector getGraphData(final String strn, final boolean b) {
        final Vector<double[]> vector = new Vector<double[]>();
        final String[] string2Array = Convert.string2Array(strn, ";");
        if (b) {
            this.graphDataNames = new String[string2Array.length];
        }
        for (int i = 0; i < string2Array.length; ++i) {
            final String[] string2Array2 = Convert.string2Array(string2Array[i], "|");
            final int n = string2Array2.length - 1;
            if (b) {
                this.graphDataNames[i] = string2Array2[0];
            }
            final double[] array = new double[n];
            for (int j = 1; j < string2Array2.length; ++j) {
                array[j - 1] = Double.valueOf(string2Array2[j]);
            }
            vector.addElement(array);
        }
        return vector;
    }
    
    private void autoRescaleBarGraph() {
        final double minValue = this.minValue;
        final double minLineValue = this.minLineValue;
        final double n = this.maxBarValue - this.minBarValue;
        final double n2 = minLineValue - minValue;
        for (int i = 0; i < this.graphData1.size(); ++i) {
            final double[] array = this.graphData1.elementAt(i);
            for (int j = 0; j < array.length; ++j) {
                array[j] = minValue + n2 * ((array[j] - this.minBarValue) / n);
            }
            this.graphData1.setElementAt(array, i);
        }
    }
    
    private void getMinMax(final Vector vector) {
        for (int i = 0; i < vector.size(); ++i) {
            final double[] array = vector.elementAt(i);
            for (int j = 0; j < array.length; ++j) {
                if (i == 0 && j == 0) {
                    this.maxValue = array[0];
                    if (this.origin2Zero) {
                        this.minValue = 0.0;
                    }
                    else {
                        this.minValue = array[0];
                    }
                }
                if (array[j] < this.minValue) {
                    this.minValue = array[j];
                }
                if (array[j] > this.maxValue) {
                    this.maxValue = array[j];
                }
            }
        }
    }
    
    private void parseStream() {
        this.maxValue = 0.0;
        this.minValue = 0.0;
        this.closeTime = "";
        final String[] string2Array = Convert.string2Array(this.graphStream, "=");
        if (string2Array.length < 2) {
            return;
        }
        final String[] string2Array2 = Convert.string2Array(string2Array[0], "?");
        this.closeTime = string2Array[1];
        if (string2Array2.length < 2) {
            return;
        }
        this.graphData1 = this.getGraphData(string2Array2[0], true);
        this.graphData2 = this.getGraphData(string2Array2[1], false);
        this.getMinMax(this.graphData1);
        this.minBarValue = this.minValue;
        this.maxBarValue = this.maxValue;
        this.getMinMax(this.graphData2);
        this.minLineValue = this.minValue;
        this.maxLineValue = this.maxValue;
        this.maxValue = this.maxLineValue;
        this.minValue = this.minLineValue - (this.maxLineValue - this.minLineValue);
        this.autoRescaleBarGraph();
    }
    
    public Vector getGraphStream1() {
        return this.graphData1;
    }
    
    public Vector getGraphStream2() {
        return this.graphData2;
    }
    
    public String[] getGraphDataNames() {
        if (this.graphDataNames[0].indexOf("Error") > -1) {
            System.out.println(this.graphDataNames[0]);
            this.graphDataNames[0] = "Retrieving Data from Server...";
        }
        return this.graphDataNames;
    }
    
    public double getMinValue() {
        return this.minValue;
    }
    
    public double getMaxValue() {
        return this.maxValue;
    }
    
    public String getCloseTime() {
        return this.closeTime;
    }
    
    public boolean fetchingData() {
        return this.fetchDataStatus;
    }
}
