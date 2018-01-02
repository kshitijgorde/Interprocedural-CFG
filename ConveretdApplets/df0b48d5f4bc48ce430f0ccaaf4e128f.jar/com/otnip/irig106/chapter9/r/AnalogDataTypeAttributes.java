// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.irig106.chapter9.r;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.Collection;
import java.util.Hashtable;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class AnalogDataTypeAttributes
{
    public String numberOfAnalogChannelsPerPacket;
    public String dataPackingOptions;
    public String sampleRate;
    public ArrayList<Measurement> measurements;
    
    public AnalogDataTypeAttributes() {
        this.numberOfAnalogChannelsPerPacket = "";
        this.dataPackingOptions = "";
        this.sampleRate = "";
        this.measurements = new ArrayList<Measurement>();
    }
    
    public void set(final String input, final String rIndex, final String dataIndex) throws Exception {
        Matcher matcher = Pattern.compile("R-" + rIndex + "\\\\ACH\\\\N-" + dataIndex + ":(.*);").matcher(input);
        if (matcher.find()) {
            this.numberOfAnalogChannelsPerPacket = matcher.group(1);
        }
        matcher = Pattern.compile("R-" + rIndex + "\\\\ADP-" + dataIndex + ":(.*);").matcher(input);
        if (matcher.find()) {
            this.dataPackingOptions = matcher.group(1);
        }
        matcher = Pattern.compile("R-" + rIndex + "\\\\ASR-" + dataIndex + ":(.*);").matcher(input);
        if (matcher.find()) {
            this.sampleRate = matcher.group(1);
        }
        final Hashtable<String, Measurement> measurements = new Hashtable<String, Measurement>();
        matcher = Pattern.compile("R-" + rIndex + "\\\\A.*-" + dataIndex + "-(\\d+).*:.*;").matcher(input);
        while (matcher.find()) {
            Measurement measurement = measurements.get(matcher.group(1));
            if (measurement == null) {
                measurement = new Measurement();
                measurements.put(matcher.group(1), measurement);
            }
        }
        for (final String measurementIndex : measurements.keySet()) {
            measurements.get(measurementIndex).set(input, rIndex, dataIndex, measurementIndex);
        }
        this.measurements = new ArrayList<Measurement>(measurements.values());
    }
    
    public class Measurement
    {
        public String measurementName;
        public String dataLength;
        public String bitMask;
        public String measurementTransferOrder;
        public String sampleFactor;
        
        public Measurement() {
            this.measurementName = "";
            this.dataLength = "";
            this.bitMask = "";
            this.measurementTransferOrder = "";
            this.sampleFactor = "";
        }
        
        public void set(final String input, final String rIndex, final String dataIndex, final String measurementIndex) throws Exception {
            Matcher matcher = Pattern.compile("R-" + rIndex + "\\\\AMN-" + dataIndex + "-" + measurementIndex + ":(.*);").matcher(input);
            if (matcher.find()) {
                this.measurementName = matcher.group(1);
            }
            matcher = Pattern.compile("R-" + rIndex + "\\\\ADL-" + dataIndex + "-" + measurementIndex + ":(.*);").matcher(input);
            if (matcher.find()) {
                this.dataLength = matcher.group(1);
            }
            matcher = Pattern.compile("R-" + rIndex + "\\\\AMSK-" + dataIndex + "-" + measurementIndex + ":(.*);").matcher(input);
            if (matcher.find()) {
                this.bitMask = matcher.group(1);
            }
            matcher = Pattern.compile("R-" + rIndex + "\\\\AMTO-" + dataIndex + "-" + measurementIndex + ":(.*);").matcher(input);
            if (matcher.find()) {
                this.measurementTransferOrder = matcher.group(1);
            }
            matcher = Pattern.compile("R-" + rIndex + "\\\\ASF-" + dataIndex + "-" + measurementIndex + ":(.*);").matcher(input);
            if (matcher.find()) {
                this.sampleFactor = matcher.group(1);
            }
        }
    }
}
