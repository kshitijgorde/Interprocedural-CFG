// 
// Decompiled by Procyon v0.5.30
// 

package ActiveNetLib.PointOfSale;

import java.io.Serializable;
import ActiveNetLib.Tools.HTMLList;

public class POSFeature implements HTMLList, Serializable
{
    private static final long serialVersionUID = 201001L;
    private String name;
    private String[] choices;
    private String current_choice;
    
    public POSFeature() {
    }
    
    public POSFeature(final String n, final String[] choices) {
        this.name = n;
        this.choices = choices;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String[] getChoices() {
        return this.choices;
    }
    
    public String getCurrentChoice() {
        return (this.current_choice == null) ? "" : this.current_choice;
    }
    
    public void setCurrentChoice(final String choice) {
        this.current_choice = choice;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public void setChoices(final String[] choices) {
        this.choices = choices;
    }
    
    public String toHTMLSelect(final String field) {
        final StringBuilder options = new StringBuilder();
        for (int i = 0; i < this.choices.length; ++i) {
            options.append("<option ");
            if (this.choices[i].trim().equals(this.current_choice)) {
                options.append(" selected");
            }
            options.append(">").append(this.choices[i]);
        }
        return options.toString();
    }
    
    public String currentValue() {
        return this.current_choice;
    }
    
    public int id() {
        return -1;
    }
    
    public boolean includeEntry() {
        return true;
    }
}
