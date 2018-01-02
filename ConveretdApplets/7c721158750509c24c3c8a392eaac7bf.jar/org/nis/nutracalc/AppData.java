// 
// Decompiled by Procyon v0.5.30
// 

package org.nis.nutracalc;

import java.util.Collection;
import org.nis.nutracalc.nutrasmart.NutritionData;
import java.io.FileReader;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Vector;
import java.util.Map;

public class AppData
{
    public static final String DATA_FILE = "appdata.txt";
    public static final String DELIMITER = ";";
    private Map<String, Vector<Item>> items;
    
    public AppData() throws Exception {
        this.items = new HashMap<String, Vector<Item>>();
        BufferedReader appDataFile = null;
        try {
            appDataFile = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("appdata.txt")));
        }
        catch (NullPointerException e) {
            appDataFile = new BufferedReader(new FileReader("appdata.txt"));
        }
        String appDataLine = appDataFile.readLine();
        int appDataVersion = 1;
        try {
            appDataFile.mark(100);
            appDataVersion = Integer.parseInt(appDataLine);
        }
        catch (Exception e2) {
            appDataFile.reset();
        }
        String name = "Please select an item";
        String category = "All Categories";
        String qty = "1 serving";
        NutritionData data = new NutritionData(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        Vector<Item> substitutionsList = new Vector<Item>();
        final Item blankItem = new Item(name, qty, data, new Item[0]);
        boolean itemInitialized = false;
        while ((appDataLine = appDataFile.readLine()) != null) {
            if (!appDataLine.startsWith("\t")) {
                if (itemInitialized) {
                    final Item[] substitutions = new Item[substitutionsList.size()];
                    for (int i = 0; i < substitutionsList.size(); ++i) {
                        substitutions[i] = substitutionsList.get(i);
                    }
                    this.items.get(category).add(new Item(name, qty, data, substitutions));
                }
                int f = 0;
                final String[] fields = appDataLine.split(";", (appDataVersion > 1) ? 4 : 3);
                name = fields[f++];
                if (appDataVersion > 1) {
                    category = fields[f++];
                }
                qty = fields[f++];
                data = new NutritionData(fields[f].split(";"));
                substitutionsList = new Vector<Item>();
                if (!this.items.containsKey(category)) {
                    this.items.put(category, new Vector<Item>());
                    this.items.get(category).add(blankItem);
                }
                itemInitialized = true;
            }
            else {
                final String[] fields2 = appDataLine.split(";", 2);
                substitutionsList.add(new Item(fields2[0], "1 set", new NutritionData(fields2[1].split(";")), new Item[0]));
            }
        }
        final Item[] substitutions = new Item[substitutionsList.size()];
        for (int i = 0; i < substitutionsList.size(); ++i) {
            substitutions[i] = substitutionsList.get(i);
        }
        this.items.get(category).add(new Item(name, qty, data, substitutions));
        appDataFile.close();
    }
    
    public Vector<String> getCategories() {
        return new Vector<String>(this.items.keySet());
    }
    
    public Vector<Item> getItems(final String category) {
        return this.items.get(category);
    }
}
