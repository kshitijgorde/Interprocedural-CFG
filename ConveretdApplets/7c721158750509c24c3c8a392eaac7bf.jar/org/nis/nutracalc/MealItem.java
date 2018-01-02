// 
// Decompiled by Procyon v0.5.30
// 

package org.nis.nutracalc;

import java.util.List;

public class MealItem
{
    private Item item;
    private List<Item> substitutions;
    
    public MealItem(final Item item, final List<Item> substitutions) {
        this.item = item;
        this.substitutions = substitutions;
    }
    
    public Item getItem() {
        return this.item;
    }
    
    public void setItem(final Item item) {
        this.item = item;
    }
    
    public List<Item> getSubstitutions() {
        return this.substitutions;
    }
    
    public void setSubstitutions(final List<Item> substitutions) {
        this.substitutions = substitutions;
    }
    
    public String toString() {
        return this.item.getName();
    }
}
