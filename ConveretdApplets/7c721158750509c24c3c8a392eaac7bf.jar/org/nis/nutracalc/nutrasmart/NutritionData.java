// 
// Decompiled by Procyon v0.5.30
// 

package org.nis.nutracalc.nutrasmart;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.EnumMap;
import java.util.Map;

public class NutritionData
{
    private Map<Field, Double> nutritionValues;
    
    public NutritionData() {
        this.nutritionValues = new EnumMap<Field, Double>(Field.class);
        for (final Field f : EnumSet.allOf(Field.class)) {
            this.nutritionValues.put(f, 0.0);
        }
    }
    
    public NutritionData(final double total_calories, final double fat_calories, final double total_fat, final double sat_fat, final double trans_fat, final double cholesterol, final double sodium, final double total_carbs, final double fiber, final double sugars, final double protein, final double vit_a, final double vit_c, final double calcium, final double iron) {
        this();
        this.nutritionValues.put(Field.TOTAL_CALORIES, total_calories);
        this.nutritionValues.put(Field.FAT_CALORIES, fat_calories);
        this.nutritionValues.put(Field.TOTAL_FAT, total_fat);
        this.nutritionValues.put(Field.TRANS_FAT, trans_fat);
        this.nutritionValues.put(Field.SATURATED_FAT, sat_fat);
        this.nutritionValues.put(Field.CHOLESTEROL, cholesterol);
        this.nutritionValues.put(Field.SODIUM, sodium);
        this.nutritionValues.put(Field.TOTAL_CARBS, total_carbs);
        this.nutritionValues.put(Field.FIBER, fiber);
        this.nutritionValues.put(Field.SUGARS, sugars);
        this.nutritionValues.put(Field.PROTEIN, protein);
        this.nutritionValues.put(Field.VIT_A, vit_a);
        this.nutritionValues.put(Field.VIT_C, vit_c);
        this.nutritionValues.put(Field.CALCIUM, calcium);
        this.nutritionValues.put(Field.IRON, iron);
    }
    
    public NutritionData(final String[] fields) {
        this(Double.parseDouble(fields[0]), Double.parseDouble(fields[1]), Double.parseDouble(fields[2]), Double.parseDouble(fields[3]), Double.parseDouble(fields[4]), Double.parseDouble(fields[5]), Double.parseDouble(fields[6]), Double.parseDouble(fields[7]), Double.parseDouble(fields[8]), Double.parseDouble(fields[9]), Double.parseDouble(fields[10]), Double.parseDouble(fields[11]), Double.parseDouble(fields[12]), Double.parseDouble(fields[13]), Double.parseDouble(fields[14]));
    }
    
    public double getNutritionValue(final Field field) {
        return this.nutritionValues.get(field);
    }
    
    public double getPercentDailyValue(final Field field) {
        return field.calculatePercentDailyValue(this.nutritionValues.get(field));
    }
    
    public NutritionData aggregate(final NutritionData other) {
        final NutritionData result = new NutritionData();
        for (final Field f : EnumSet.allOf(Field.class)) {
            result.nutritionValues.put(f, this.nutritionValues.get(f) + other.nutritionValues.get(f));
        }
        return result;
    }
    
    public NutritionData scale(final double factor) {
        final NutritionData result = new NutritionData();
        for (final Field f : EnumSet.allOf(Field.class)) {
            result.nutritionValues.put(f, this.nutritionValues.get(f) * factor);
        }
        return result;
    }
    
    public enum Field
    {
        TOTAL_CALORIES("TOTAL_CALORIES", 0, "Total Calories", "kcal", 0.0), 
        FAT_CALORIES("FAT_CALORIES", 1, "Fat Calories", "kcal", 0.0), 
        TOTAL_FAT("TOTAL_FAT", 2, "Total Fat", "g", 65.0), 
        SATURATED_FAT("SATURATED_FAT", 3, "Saturated Fat", "g", 20.0), 
        TRANS_FAT("TRANS_FAT", 4, "Trans Fat", "g", 0.0), 
        CHOLESTEROL("CHOLESTEROL", 5, "Cholesterol", "mg", 300.0), 
        SODIUM("SODIUM", 6, "Sodium", "mg", 2400.0), 
        TOTAL_CARBS("TOTAL_CARBS", 7, "Total Carbs", "g", 300.0), 
        FIBER("FIBER", 8, "Fiber", "g", 25.0), 
        SUGARS("SUGARS", 9, "Sugars", "g", 0.0), 
        PROTEIN("PROTEIN", 10, "Protein", "g", 0.0), 
        VIT_A("VIT_A", 11, "Vit A", "iu", 5000.0), 
        VIT_C("VIT_C", 12, "Vit C", "mg", 60.0), 
        CALCIUM("CALCIUM", 13, "Calcium", "mg", 1000.0), 
        IRON("IRON", 14, "Iron", "mg", 18.0);
        
        private String name;
        private String unit;
        private double dailyReferenceValue;
        
        private Field(final String s, final int n, final String name, final String unit, final double dailyReferenceValue) {
            this.name = name;
            this.unit = unit;
            this.dailyReferenceValue = dailyReferenceValue;
        }
        
        public String getName() {
            return this.name;
        }
        
        public String getUnit() {
            return this.unit;
        }
        
        public double getDailyReferenceValue() {
            return this.dailyReferenceValue;
        }
        
        public double calculatePercentDailyValue(final double amount) {
            if (this.dailyReferenceValue > 0.0) {
                return amount / this.dailyReferenceValue;
            }
            return -1.0;
        }
    }
}
