// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.irig106.chapter9.g;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class G
{
    public String programName;
    public String testItem;
    public Information information;
    public ArrayList<DataSourceIdentification> dataSourceIdentifications;
    public TestInformation testInformation;
    public String comments;
    
    public G() {
        this.programName = "";
        this.testItem = "";
        this.information = new Information();
        this.dataSourceIdentifications = new ArrayList<DataSourceIdentification>();
        this.testInformation = new TestInformation();
    }
    
    public void set(final String input) throws Exception {
        Matcher matcher = null;
        matcher = Pattern.compile("G\\\\PN:(.*);").matcher(input);
        if (matcher.find()) {
            this.programName = matcher.group(1);
        }
        matcher = Pattern.compile("G\\\\TA:(.*);").matcher(input);
        if (matcher.find()) {
            this.testItem = matcher.group(1);
        }
        matcher = Pattern.compile("G\\\\COM:(.*);").matcher(input);
        if (matcher.find()) {
            this.comments = matcher.group(1);
        }
        this.information.set(input);
        matcher = Pattern.compile("G\\\\DSI\\\\N:(.*);").matcher(input);
        if (matcher.find()) {
            for (int N = Integer.parseInt(matcher.group(1)), i = 0; i < N; ++i) {
                final DataSourceIdentification dsi = new DataSourceIdentification();
                final String[] patterns = { "G\\\\DSI-" + (i + 1) + ":(.*);", "G\\\\DST-" + (i + 1) + ":(.*);" };
                dsi.set(input, patterns);
                this.dataSourceIdentifications.add(dsi);
            }
        }
        this.testInformation.set(input);
    }
    
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("GENERAL INFORMATION GROUP (G)\n");
        stringBuilder.append("\tProgram Name:  " + this.programName + "\n");
        stringBuilder.append("\tTest Item:  " + this.testItem + "\n");
        stringBuilder.append("\tComments:  " + this.testItem + "\n");
        stringBuilder.append("\t" + this.information.toString().replace("\n", "\n\t"));
        for (final DataSourceIdentification dataSourceIdentification : this.dataSourceIdentifications) {
            stringBuilder.append(dataSourceIdentification.toString().replace("\n", "\n\t") + "\n");
        }
        stringBuilder.append("\t" + this.testInformation.toString().replace("\n", "\n\t"));
        return stringBuilder.toString();
    }
}
