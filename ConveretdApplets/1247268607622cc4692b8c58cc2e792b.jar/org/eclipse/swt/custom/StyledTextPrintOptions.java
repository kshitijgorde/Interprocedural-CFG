// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.custom;

public class StyledTextPrintOptions
{
    public static final String PAGE_TAG = "<page>";
    public static final String SEPARATOR = "\t";
    public String header;
    public String footer;
    public String jobName;
    public boolean printTextForeground;
    public boolean printTextBackground;
    public boolean printTextFontStyle;
    public boolean printLineBackground;
    public boolean printLineNumbers;
    public String[] lineLabels;
    
    public StyledTextPrintOptions() {
        this.header = null;
        this.footer = null;
        this.jobName = null;
        this.printTextForeground = false;
        this.printTextBackground = false;
        this.printTextFontStyle = false;
        this.printLineBackground = false;
        this.printLineNumbers = false;
        this.lineLabels = null;
    }
}
