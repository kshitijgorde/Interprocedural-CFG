// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.irig106.chapter9.r;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.Collection;
import java.util.regex.Pattern;
import java.util.Hashtable;
import java.util.ArrayList;

public class R
{
    public String dataSourceID;
    public String storageID;
    public String storageDescription;
    public String comments;
    public StorageCharacteristics storageCharacteristics;
    public RecorderInformation recorderInformation;
    public RecordingEventDefinitions recordingEventDefinitions;
    public RecordingIndex recordingIndex;
    public ArrayList<Data> datas;
    
    public R() {
        this.dataSourceID = "";
        this.storageID = "";
        this.storageDescription = "";
        this.comments = "";
        this.storageCharacteristics = new StorageCharacteristics();
        this.recorderInformation = new RecorderInformation();
        this.recordingEventDefinitions = new RecordingEventDefinitions();
        this.recordingIndex = new RecordingIndex();
        this.datas = new ArrayList<Data>();
    }
    
    public static ArrayList<R> parse(final String input) throws Exception {
        final Hashtable<String, R> rs = new Hashtable<String, R>();
        final Matcher matcher = Pattern.compile("R-(\\d+)\\\\(.*);").matcher(input);
        while (matcher.find()) {
            R r = rs.get(matcher.group(1));
            if (r == null) {
                r = new R();
                rs.put(matcher.group(1), r);
            }
        }
        for (final String index : rs.keySet()) {
            rs.get(index).set(input, index);
        }
        return new ArrayList<R>(rs.values());
    }
    
    private void set(final String input, final String rIndex) throws Exception {
        Matcher matcher = Pattern.compile("R-" + rIndex + "\\\\ID:(.*);").matcher(input);
        if (matcher.find()) {
            this.dataSourceID = matcher.group(1);
        }
        matcher = Pattern.compile("R-" + rIndex + "\\\\RID:(.*);").matcher(input);
        if (matcher.find()) {
            this.storageID = matcher.group(1);
        }
        matcher = Pattern.compile("R-" + rIndex + "\\\\R1:(.*);").matcher(input);
        if (matcher.find()) {
            this.storageDescription = matcher.group(1);
        }
        matcher = Pattern.compile("R-" + rIndex + "\\\\COM:(.*);").matcher(input);
        if (matcher.find()) {
            this.comments = matcher.group(1);
        }
        this.storageCharacteristics.set(input, rIndex);
        this.recorderInformation.set(input, rIndex);
        this.recordingEventDefinitions.set(input, rIndex);
        final Hashtable<String, Data> datas = new Hashtable<String, Data>();
        matcher = Pattern.compile("R-" + rIndex + "\\\\.*-(\\d+).*:.*;").matcher(input);
        while (matcher.find()) {
            Data data = datas.get(matcher.group(1));
            if (data == null) {
                data = new Data();
                datas.put(matcher.group(1), data);
            }
        }
        for (final String dataIndex : datas.keySet()) {
            datas.get(dataIndex).set(input, rIndex, dataIndex);
        }
        this.datas = new ArrayList<Data>(datas.values());
    }
    
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("TAPE/STORAGE SOURCE ATTRIBUTES GROUP (R)\n");
        stringBuilder.append("\tData Source ID:  " + this.dataSourceID + "\n");
        stringBuilder.append("\tTape/Storage ID:  " + this.storageID + "\n");
        stringBuilder.append("\tTape/Storage Description:  " + this.storageDescription + "\n");
        stringBuilder.append("\tComments:  " + this.comments + "\n");
        stringBuilder.append("\t" + this.storageCharacteristics.toString().replace("\n", "\n\t") + "\n");
        stringBuilder.append("\t" + this.recorderInformation.toString().replace("\n", "\n\t") + "\n");
        for (final Data data : this.datas) {
            stringBuilder.append("\t" + data.toString().replace("\n", "\n\t") + "\n");
        }
        return stringBuilder.toString();
    }
}
