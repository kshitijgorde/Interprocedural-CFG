// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.irig106.chapter9.r;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.otnip.irig106.chapter9.common.PointOfContact;

public class RecorderInformation
{
    public String storageManufacturer;
    public String storageModel;
    public String originalStorage;
    public String dateAndTimeCreated;
    public PointOfContact creatingOrganization;
    public String dateOfDub;
    public PointOfContact dubbingOrganization;
    
    public RecorderInformation() {
        this.storageManufacturer = "";
        this.storageModel = "";
        this.originalStorage = "";
        this.dateAndTimeCreated = "";
        this.creatingOrganization = new PointOfContact();
        this.dateOfDub = "";
        this.dubbingOrganization = new PointOfContact();
    }
    
    public void set(final String input, final String rIndex) throws Exception {
        Matcher matcher = Pattern.compile("R-" + rIndex + "\\\\RI1:(.*);").matcher(input);
        if (matcher.find()) {
            this.storageManufacturer = matcher.group(1);
        }
        matcher = Pattern.compile("R-" + rIndex + "\\\\RI2:(.*);").matcher(input);
        if (matcher.find()) {
            this.storageModel = matcher.group(1);
        }
        matcher = Pattern.compile("R-" + rIndex + "\\\\RI3:(.*);").matcher(input);
        if (matcher.find()) {
            this.originalStorage = matcher.group(1);
        }
        matcher = Pattern.compile("R-" + rIndex + "\\\\RI4:(.*);").matcher(input);
        if (matcher.find()) {
            this.dateAndTimeCreated = matcher.group(1);
        }
        matcher = Pattern.compile("R-" + rIndex + "\\\\RI5:(.*);").matcher(input);
        if (matcher.find()) {
            this.dateOfDub = matcher.group(1);
        }
        this.creatingOrganization.set(input, new String[] { "R-\\" + rIndex + "\\\\POC1:(.*);", "R-\\" + rIndex + "\\\\POC2:(.*);", "R-\\" + rIndex + "\\\\POC3:(.*);", "R-\\" + rIndex + "\\\\POC4:(.*);" });
        this.creatingOrganization.set(input, new String[] { "R-\\" + rIndex + "\\\\DPOC1:(.*);", "R-\\" + rIndex + "\\\\DPOC2:(.*);", "R-\\" + rIndex + "\\\\DPOC3:(.*);", "R-\\" + rIndex + "\\\\DPOC4:(.*);" });
    }
    
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("RECORDER INFORMATION\n");
        stringBuilder.append("\tStorage Manufacturer:  " + this.storageManufacturer + "\n");
        stringBuilder.append("\tStorage Model:  " + this.storageModel + "\n");
        stringBuilder.append("\tOriginal Storage:  " + this.originalStorage + "\n");
        stringBuilder.append("\tDate And Time Created:  " + this.dateAndTimeCreated + "\n");
        stringBuilder.append("\tCreating Organization -> " + this.creatingOrganization.toString().replace("\n", "\n\t") + "\n");
        stringBuilder.append("\tDate Of Dub:  " + this.dateOfDub + "\n");
        stringBuilder.append("\tDubbing Organization -> " + this.dubbingOrganization.toString().replace("\n", "\n\t"));
        return stringBuilder.toString();
    }
}
