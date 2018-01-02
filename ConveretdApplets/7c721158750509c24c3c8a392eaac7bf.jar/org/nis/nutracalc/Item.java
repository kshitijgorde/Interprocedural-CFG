// 
// Decompiled by Procyon v0.5.30
// 

package org.nis.nutracalc;

import org.nis.nutracalc.nutrasmart.NutritionData;

public class Item
{
    private String name;
    private String serving;
    private NutritionData nutritionData;
    private Item[] substitutions;
    
    public Item(final String name, final String serving, final NutritionData nutritionData, final Item[] substitutions) {
        this.name = name;
        this.serving = serving;
        this.nutritionData = nutritionData;
        this.substitutions = new Item[substitutions.length];
        for (int i = 0; i < substitutions.length; ++i) {
            this.substitutions[i] = substitutions[i];
        }
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getServing() {
        return this.serving;
    }
    
    public NutritionData getNutritionData() {
        return this.nutritionData;
    }
    
    public Item[] getSubstitutions() {
        return this.substitutions;
    }
    
    public String toString() {
        return this.name;
    }
}
