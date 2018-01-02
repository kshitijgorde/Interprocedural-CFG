// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.canadiantax;

import com.mindprod.common11.BigDate;
import java.util.Arrays;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;

enum Province
{
    BC("BC : British Columbia"), 
    AB("AB : Alberta"), 
    SK("SK : Saskatchewan"), 
    MB("MB : Manitoba"), 
    ON("ON : Ontario"), 
    QC("QC : Quebec"), 
    PE("PE : Prince Edward Island"), 
    NB("NB : New Brunswick"), 
    NS("NS : Nova Scotia"), 
    NL("NL : Newfoundland/Labrador"), 
    YT("YT : Yukon"), 
    NT("NT : NWT"), 
    NU("NU : Nunavut"), 
    OC("out of country");
    
    private ProvincialTaxFact[] taxFacts;
    private final String longName;
    
    public String toString() {
        return this.longName;
    }
    
    static void exportTaxResource() throws IOException {
        final FileOutputStream fos = new FileOutputStream("E:/com/mindprod/canadiantax/cantaxhistory.ser", false);
        final ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(1L);
        for (final Province province : values()) {
            oos.writeObject(province.taxFacts);
        }
        oos.close();
    }
    
    static void importTaxResource() {
        try {
            final InputStream is = Province.class.getResourceAsStream("cantaxhistory.ser");
            final ObjectInputStream ois = new ObjectInputStream(is);
            if ((long)ois.readObject() != 1L) {
                throw new IllegalArgumentException("Program bug: mismatched versions in Tax history resource");
            }
            for (final Province province : values()) {
                province.taxFacts = (ProvincialTaxFact[])ois.readObject();
            }
            ois.close();
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Program bug: Unable to load cantaxhistory.ser tax fact resource ");
        }
    }
    
    private Province(final String longName) {
        this.taxFacts = new ProvincialTaxFact[0];
        this.longName = longName;
    }
    
    void add(final ProvincialTaxFact taxFact) {
        final int length = this.taxFacts.length;
        final ProvincialTaxFact[] grownTaxFacts = new ProvincialTaxFact[length + 1];
        System.arraycopy(this.taxFacts, 0, grownTaxFacts, 0, length);
        grownTaxFacts[length] = taxFact;
        Arrays.sort(grownTaxFacts);
        this.taxFacts = grownTaxFacts;
    }
    
    ProvincialTaxFact findTaxFactForDate(final BigDate date) {
        for (final ProvincialTaxFact candidate : this.taxFacts) {
            if (candidate.getDate().compareTo(date) <= 0) {
                return candidate;
            }
        }
        throw new IllegalArgumentException("Missing tax data for date " + date.toString() + " in province " + this.toString());
    }
}
