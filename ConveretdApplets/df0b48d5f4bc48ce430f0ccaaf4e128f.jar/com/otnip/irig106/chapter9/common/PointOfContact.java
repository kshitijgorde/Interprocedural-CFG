// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.irig106.chapter9.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PointOfContact
{
    public String name;
    public String agency;
    public String address;
    public String telephone;
    
    public PointOfContact() {
        this.name = "";
        this.agency = "";
        this.address = "";
        this.telephone = "";
    }
    
    public void set(final String input, final String[] patterns) {
        Matcher matcher = Pattern.compile(patterns[0]).matcher(input);
        if (matcher.find()) {
            this.name = matcher.group(1);
        }
        matcher = Pattern.compile(patterns[1]).matcher(input);
        if (matcher.find()) {
            this.agency = matcher.group(1);
        }
        matcher = Pattern.compile(patterns[2]).matcher(input);
        if (matcher.find()) {
            this.address = matcher.group(1);
        }
        matcher = Pattern.compile(patterns[3]).matcher(input);
        if (matcher.find()) {
            this.telephone = matcher.group(1);
        }
    }
    
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("POINT OF CONTACT\n");
        stringBuilder.append("\tName:  " + this.name + "\n");
        stringBuilder.append("\tAgency:  " + this.agency + "\n");
        stringBuilder.append("\tAddress:  " + this.address + "\n");
        stringBuilder.append("\tTelephone:  " + this.telephone);
        return stringBuilder.toString();
    }
}
