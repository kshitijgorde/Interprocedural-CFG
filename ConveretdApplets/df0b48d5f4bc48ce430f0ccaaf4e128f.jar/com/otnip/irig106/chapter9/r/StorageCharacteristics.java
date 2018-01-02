// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.irig106.chapter9.r;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StorageCharacteristics
{
    public String storageType;
    public String storageManufacturer;
    public String storageCode;
    public String tapeWidth;
    public String tapeHousing;
    public String typeOfTracks;
    public String numberOfTracks;
    public String recordSpeed;
    public String dataPackingDensity;
    public String tapeRewound;
    public String numberOfSourceBits;
    
    public StorageCharacteristics() {
        this.storageType = "";
        this.storageManufacturer = "";
        this.storageCode = "";
        this.tapeWidth = "";
        this.tapeHousing = "";
        this.typeOfTracks = "";
        this.numberOfTracks = "";
        this.recordSpeed = "";
        this.dataPackingDensity = "";
        this.tapeRewound = "";
        this.numberOfSourceBits = "";
    }
    
    void set(final String input, final String rIndex) throws Exception {
        Matcher matcher = Pattern.compile("R-" + rIndex + "\\\\TC1:(.*);").matcher(input);
        if (matcher.find()) {
            this.storageType = matcher.group(1);
        }
        matcher = Pattern.compile("R-" + rIndex + "\\\\TC2:(.*);").matcher(input);
        if (matcher.find()) {
            this.storageManufacturer = matcher.group(1);
        }
        matcher = Pattern.compile("R-" + rIndex + "\\\\TC3:(.*);").matcher(input);
        if (matcher.find()) {
            this.storageCode = matcher.group(1);
        }
        matcher = Pattern.compile("R-" + rIndex + "\\\\TC4:(.*);").matcher(input);
        if (matcher.find()) {
            this.tapeWidth = matcher.group(1);
        }
        matcher = Pattern.compile("R-" + rIndex + "\\\\TC5:(.*);").matcher(input);
        if (matcher.find()) {
            this.tapeHousing = matcher.group(1);
        }
        matcher = Pattern.compile("R-" + rIndex + "\\\\TT:(.*);").matcher(input);
        if (matcher.find()) {
            this.typeOfTracks = matcher.group(1);
        }
        matcher = Pattern.compile("R-" + rIndex + "\\\\N:(.*);").matcher(input);
        if (matcher.find()) {
            this.numberOfTracks = matcher.group(1);
        }
        matcher = Pattern.compile("R-" + rIndex + "\\\\TC6:(.*);").matcher(input);
        if (matcher.find()) {
            this.recordSpeed = matcher.group(1);
        }
        matcher = Pattern.compile("R-" + rIndex + "\\\\TC7:(.*);").matcher(input);
        if (matcher.find()) {
            this.dataPackingDensity = matcher.group(1);
        }
        matcher = Pattern.compile("R-" + rIndex + "\\\\TC8:(.*);").matcher(input);
        if (matcher.find()) {
            this.tapeRewound = matcher.group(1);
        }
        matcher = Pattern.compile("R-" + rIndex + "\\\\NSB:(.*);").matcher(input);
        if (matcher.find()) {
            this.numberOfSourceBits = matcher.group(1);
        }
    }
    
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("STORAGE CHARACTERISTICS\n");
        stringBuilder.append("\tTape/Storage Type:  " + this.storageType + "\n");
        stringBuilder.append("\tTape/Storage Manufacturer:  " + this.storageManufacturer + "\n");
        stringBuilder.append("\tTape/Storage Code:  " + this.storageCode + "\n");
        stringBuilder.append("\tTape Width:  " + this.tapeWidth + "\n");
        stringBuilder.append("\tTape Housing:  " + this.tapeHousing + "\n");
        stringBuilder.append("\tType Of Tracks:  " + this.typeOfTracks + "\n");
        stringBuilder.append("\tNumber Of Tracks:  " + this.numberOfTracks + "\n");
        stringBuilder.append("\tRecord Speed:  " + this.recordSpeed + "\n");
        stringBuilder.append("\tData Packing Density:  " + this.dataPackingDensity + "\n");
        stringBuilder.append("\tTape Rewound:  " + this.tapeRewound + "\n");
        stringBuilder.append("\tNumber Of Source Bits:  " + this.numberOfSourceBits);
        return stringBuilder.toString();
    }
}
