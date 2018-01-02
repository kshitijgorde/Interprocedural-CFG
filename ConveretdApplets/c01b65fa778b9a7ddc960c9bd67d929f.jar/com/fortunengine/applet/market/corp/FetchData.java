// 
// Decompiled by Procyon v0.5.30
// 

package com.fortunengine.applet.market.corp;

import java.io.IOException;
import java.net.MalformedURLException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.net.URL;
import java.util.Vector;

public class FetchData
{
    private Thread thread;
    private int count;
    private Vector sdate;
    private Vector amt_b;
    private Vector amt_s;
    private Vector amt_net;
    private String hostName;
    private String fileName;
    private boolean updating;
    private Vector ld;
    
    public FetchData(final String hostName, final String s) {
        this.thread = null;
        this.count = 1;
        this.sdate = new Vector();
        this.amt_b = new Vector();
        this.amt_s = new Vector();
        this.amt_net = new Vector();
        this.hostName = null;
        this.fileName = "/charts/mkt_corpv3";
        this.updating = true;
        this.ld = null;
        this.hostName = hostName;
        this.fileName = String.valueOf(this.fileName) + "?scode=" + s;
        this.fetchingData();
    }
    
    public void fetchingData() {
        try {
            int n = -1;
            this.updating = true;
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new GZIPInputStream(new URL("http", this.hostName, this.fileName).openStream())));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (n == -1) {
                    this.ld = new Vector();
                    n = 0;
                }
                else {
                    final int n2 = 0;
                    final int index = line.indexOf(44);
                    final String substring = line.substring(n2, index);
                    final int n3 = index + 1;
                    final int index2 = line.indexOf(44, n3);
                    final Double n4 = new Double(line.substring(n3, index2));
                    final int n5 = index2 + 1;
                    final int index3 = line.indexOf(44, n5);
                    final Double n6 = new Double(line.substring(n5, index3));
                    final Double n7 = new Double(line.substring(index3 + 1, line.length()));
                    this.sdate.addElement(substring);
                    this.amt_b.addElement(n4);
                    this.amt_s.addElement(n6);
                    this.amt_net.addElement(n7);
                    ++n;
                }
            }
            bufferedReader.close();
            this.updating = false;
        }
        catch (MalformedURLException ex) {}
        catch (IOException ex2) {}
    }
    
    public Vector getAmt_b() {
        return this.amt_b;
    }
    
    public Vector getAmt_net() {
        return this.amt_net;
    }
    
    public Vector getAmt_s() {
        return this.amt_s;
    }
    
    public Vector getSDate() {
        return this.sdate;
    }
}
