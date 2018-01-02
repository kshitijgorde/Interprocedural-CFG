// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.americantax;

import java.io.IOException;
import java.util.Collection;
import java.util.Arrays;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.zip.GZIPInputStream;
import java.util.Vector;
import java.util.HashMap;
import java.io.Serializable;

final class StateItem implements Serializable
{
    private static final int AVERAGE_DISTRICTS_PER_STATE = 1000;
    public static final long serialVersionUID = 3L;
    private static HashMap<String, StateItem> stateHash;
    private static Vector<StateItem> stateVector;
    private final String fullStateName;
    private final String stateAbbr;
    private final Vector<DistrictItem> districts;
    private final int stateTaxRate;
    
    public static StateItem findStateItem(final String stateAbbr) {
        return StateItem.stateHash.get(stateAbbr);
    }
    
    public static void load() {
        try {
            final GZIPInputStream gzis = new GZIPInputStream(AmericanTaxTable.class.getResourceAsStream("taxtables.ser"), 8096);
            final ObjectInputStream ois = new ObjectInputStream(gzis);
            final StateItem[] stateItems = (StateItem[])ois.readObject();
            StateItem.stateVector = new Vector<StateItem>(Arrays.asList(stateItems));
            StateItem.stateHash = new HashMap<String, StateItem>(stateItems.length + 21);
            for (final StateItem stateItem : stateItems) {
                StateItem.stateHash.put(stateItem.stateAbbr, stateItem);
            }
            ois.close();
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
            System.err.println("Unable to load taxtables.ser resource");
        }
        catch (ClassNotFoundException e2) {
            System.err.println(e2.getMessage());
            System.err.println("Unable to load taxtables.ser resource because of missing class");
        }
    }
    
    public void add(final DistrictItem districtItem) {
        this.districts.add(districtItem);
    }
    
    public Vector<DistrictItem> getDistrictItems() {
        return this.districts;
    }
    
    public String toString() {
        return this.stateAbbr + " " + this.fullStateName;
    }
    
    static Vector<StateItem> getStateChoices() {
        return StateItem.stateVector;
    }
    
    StateItem(final String stateAbbr, final String fullStateName, final double stateTaxPercent) {
        this.districts = new Vector<DistrictItem>(1000);
        this.stateAbbr = stateAbbr.intern();
        this.fullStateName = fullStateName.intern();
        this.stateTaxRate = (int)(stateTaxPercent * 10000.0 + 0.5);
    }
    
    String getStateAbbr() {
        return this.stateAbbr;
    }
    
    int getStateTaxRate() {
        return this.stateTaxRate;
    }
    
    void trimToSize() {
        this.districts.trimToSize();
    }
}
