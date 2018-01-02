// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.irig106.chapter9.g;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.otnip.irig106.chapter9.common.PointOfContact;
import java.util.ArrayList;

public class Information
{
    public String irig106RevisionLevel;
    public String originationDate;
    public String revisionNumber;
    public String revisionDate;
    public String updateNumber;
    public String updateDate;
    public String testNumber;
    public ArrayList<PointOfContact> pointOfContacts;
    
    public Information() {
        this.irig106RevisionLevel = "";
        this.originationDate = "";
        this.revisionNumber = "";
        this.revisionDate = "";
        this.updateNumber = "";
        this.updateDate = "";
        this.testNumber = "";
        this.pointOfContacts = new ArrayList<PointOfContact>();
    }
    
    public void set(final String input) throws Exception {
        Matcher matcher = Pattern.compile("G\\\\106:(.*);").matcher(input);
        if (matcher.find()) {
            this.irig106RevisionLevel = matcher.group(1);
        }
        matcher = Pattern.compile("G\\\\OD:(.*);").matcher(input);
        if (matcher.find()) {
            this.originationDate = matcher.group(1);
        }
        matcher = Pattern.compile("G\\\\RN:(.*);").matcher(input);
        if (matcher.find()) {
            this.revisionNumber = matcher.group(1);
        }
        matcher = Pattern.compile("G\\\\RD:(.*);").matcher(input);
        if (matcher.find()) {
            this.revisionDate = matcher.group(1);
        }
        matcher = Pattern.compile("G\\\\UN:(.*);").matcher(input);
        if (matcher.find()) {
            this.updateNumber = matcher.group(1);
        }
        matcher = Pattern.compile("G\\\\UD:(.*);").matcher(input);
        if (matcher.find()) {
            this.updateDate = matcher.group(1);
        }
        matcher = Pattern.compile("G\\\\TN:(.*);").matcher(input);
        if (matcher.find()) {
            this.testNumber = matcher.group(1);
        }
        matcher = Pattern.compile("G\\\\POC\\\\N:(.*);").matcher(input);
        if (matcher.find()) {
            for (int N = Integer.parseInt(matcher.group(1)), i = 0; i < N; ++i) {
                final PointOfContact poc = new PointOfContact();
                final String[] patterns = { "G\\\\POC1-" + (i + 1) + ":(.*);", "G\\\\POC2-" + (i + 1) + ":(.*);", "G\\\\POC3-" + (i + 1) + ":(.*);", "G\\\\POC4-" + (i + 1) + ":(.*);" };
                poc.set(input, patterns);
                this.pointOfContacts.add(poc);
            }
        }
    }
    
    public String toString() {
        String string = "";
        string += "INFORMATION\n";
        string = string + "\tIRIG 106 Revision Level:  " + this.irig106RevisionLevel + "\n";
        string = string + "\tOrigination Date:  " + this.originationDate + "\n";
        string = string + "\tRevision Number:  " + this.revisionNumber + "\n";
        string = string + "\tRevision Date:  " + this.revisionDate + "\n";
        string = string + "\tUpdate Number:  " + this.updateNumber + "\n";
        string = string + "\tUpdate Date:  " + this.updateDate + "\n";
        string = string + "\tTest Number:  " + this.testNumber + "\n";
        for (final PointOfContact pointOfContact : this.pointOfContacts) {
            string = string + "\t" + pointOfContact.toString().replace("\n", "\n\t") + "\n";
        }
        return string;
    }
}
