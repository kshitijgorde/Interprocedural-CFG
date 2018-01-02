// 
// Decompiled by Procyon v0.5.30
// 

package org.nis.nutracalc;

import javax.swing.table.AbstractTableModel;
import org.nis.nutracalc.nutrasmart.NutritionData;
import javax.swing.table.TableModel;
import javax.swing.JTable;

public class NutritionLabel extends JTable
{
    private static final long serialVersionUID = -8850623998887409481L;
    NutritionTableModel model;
    
    public NutritionLabel() {
        this.setModel(this.model = new NutritionTableModel());
        this.setRowSelectionAllowed(false);
        this.setColumnSelectionAllowed(false);
        this.setCellSelectionEnabled(false);
        this.setAutoResizeMode(0);
    }
    
    public void setData(final NutritionData data, final String servingSize) {
        this.model.setNutritionData(data, servingSize);
        this.doLayout();
    }
    
    private class NutritionTableModel extends AbstractTableModel
    {
        private static final long serialVersionUID = -4186570622488205709L;
        private String[] columnNames;
        private Object[][] data;
        
        public NutritionTableModel() {
            this.columnNames = new String[] { "", "Amount", "% DV" };
            this.data = new Object[][] { { "Serving Size", "", "" }, { "Calories", "", "" }, { "Calories from Fat", "", "" }, { "Total Fat", "", "" }, { "Saturated Fat", "", "" }, { "Trans Fat", "", "" }, { "Cholesterol", "", "" }, { "Sodium", "", "" }, { "Total Carbohydrates", "", "" }, { "Dietary Fiber", "", "" }, { "Sugars", "", "" }, { "Protein", "", "" }, { "Vitamin A", "", "" }, { "Vitamin C", "", "" }, { "Calcium", "", "" }, { "Iron", "", "" } };
        }
        
        public int getColumnCount() {
            return this.columnNames.length;
        }
        
        public int getRowCount() {
            return this.data.length;
        }
        
        public String getColumnName(final int col) {
            return this.columnNames[col];
        }
        
        public Object getValueAt(final int row, final int col) {
            return this.data[row][col];
        }
        
        public boolean isCellEditable(final int row, final int col) {
            return false;
        }
        
        public void setNutritionData(final NutritionData nutritionData, final String servingSize) {
            this.data[0][1] = new StringBuilder(String.valueOf(servingSize)).toString();
            this.data[1][1] = Math.round(nutritionData.getNutritionValue(NutritionData.Field.TOTAL_CALORIES));
            this.data[2][1] = Math.round(nutritionData.getNutritionValue(NutritionData.Field.FAT_CALORIES));
            this.data[3][1] = String.valueOf(Math.round(nutritionData.getNutritionValue(NutritionData.Field.TOTAL_FAT))) + " g";
            this.data[3][2] = String.valueOf(Math.round(nutritionData.getPercentDailyValue(NutritionData.Field.TOTAL_FAT) * 100.0)) + "%";
            this.data[4][1] = String.valueOf(Math.round(nutritionData.getNutritionValue(NutritionData.Field.SATURATED_FAT))) + " g";
            this.data[4][2] = String.valueOf(Math.round(nutritionData.getPercentDailyValue(NutritionData.Field.SATURATED_FAT) * 100.0)) + "%";
            this.data[5][1] = String.valueOf(Math.round(nutritionData.getNutritionValue(NutritionData.Field.TRANS_FAT))) + " g";
            this.data[6][1] = String.valueOf(Math.round(nutritionData.getNutritionValue(NutritionData.Field.CHOLESTEROL))) + " mg";
            this.data[6][2] = String.valueOf(Math.round(nutritionData.getPercentDailyValue(NutritionData.Field.CHOLESTEROL) * 100.0)) + "%";
            this.data[7][1] = String.valueOf(Math.round(nutritionData.getNutritionValue(NutritionData.Field.SODIUM))) + " mg";
            this.data[7][2] = String.valueOf(Math.round(nutritionData.getPercentDailyValue(NutritionData.Field.SODIUM) * 100.0)) + "%";
            this.data[8][1] = String.valueOf(Math.round(nutritionData.getNutritionValue(NutritionData.Field.TOTAL_CARBS))) + " g";
            this.data[8][2] = String.valueOf(Math.round(nutritionData.getPercentDailyValue(NutritionData.Field.TOTAL_CARBS) * 100.0)) + "%";
            this.data[9][1] = String.valueOf(Math.round(nutritionData.getNutritionValue(NutritionData.Field.FIBER))) + " g";
            this.data[9][2] = String.valueOf(Math.round(nutritionData.getPercentDailyValue(NutritionData.Field.FIBER) * 100.0)) + "%";
            this.data[10][1] = String.valueOf(Math.round(nutritionData.getNutritionValue(NutritionData.Field.SUGARS))) + " g";
            this.data[11][1] = String.valueOf(Math.round(nutritionData.getNutritionValue(NutritionData.Field.PROTEIN))) + " g";
            this.data[12][2] = String.valueOf(Math.round(nutritionData.getPercentDailyValue(NutritionData.Field.VIT_A) * 100.0)) + "%";
            this.data[13][2] = String.valueOf(Math.round(nutritionData.getPercentDailyValue(NutritionData.Field.VIT_C) * 100.0)) + "%";
            this.data[14][2] = String.valueOf(Math.round(nutritionData.getPercentDailyValue(NutritionData.Field.CALCIUM) * 100.0)) + "%";
            this.data[15][2] = String.valueOf(Math.round(nutritionData.getPercentDailyValue(NutritionData.Field.IRON) * 100.0)) + "%";
        }
    }
}
