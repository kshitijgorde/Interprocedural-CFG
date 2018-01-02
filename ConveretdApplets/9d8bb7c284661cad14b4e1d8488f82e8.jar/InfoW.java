import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.Panel;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class InfoW extends Frame
{
    boolean showWindow;
    Panel[][] \u011f;
    TextArea[][] \u0120;
    Label[][] \u0121;
    GridBagLayout \u0122;
    
    InfoW(final String s) {
        super(s);
        this.showWindow = false;
        this.\u0122 = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final double[] rowWeights = { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 };
        final double[] columnWeights = { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 };
        final int[] rowHeights = { 10, 10, 10, 10, 10, 10 };
        final int[] columnWidths = { 10, 10, 10, 10, 10, 10 };
        this.\u0122.rowWeights = rowWeights;
        this.\u0122.columnWeights = columnWeights;
        this.\u0122.rowHeights = rowHeights;
        this.\u0122.columnWidths = columnWidths;
        this.setLayout(this.\u0122);
        gridBagConstraints.fill = 1;
        this.\u011f = new Panel[6][6];
        this.\u0120 = new TextArea[6][6];
        this.\u0121 = new Label[6][6];
        for (int i = 0; i < 6; ++i) {
            for (int j = 0; j < 6; ++j) {
                this.\u011f[i][j] = new Panel();
                (this.\u0121[i][j] = new Label("         [EMPTY]         ", 1)).setBackground(Color.lightGray);
                (this.\u0120[i][j] = new TextArea()).setBackground(Color.lightGray);
                this.\u011f[i][j].setLayout(new BorderLayout());
                this.\u011f[i][j].add("North", this.\u0121[i][j]);
                this.\u011f[i][j].add("South", this.\u0120[i][j]);
                this.\u011f[i][j].show();
                this.add(this.\u011f[i][j]);
                gridBagConstraints.gridx = i;
                gridBagConstraints.gridy = j;
                this.\u0122.setConstraints(this.\u011f[i][j], gridBagConstraints);
            }
        }
        this.pack();
        this.setBackground(Color.gray);
        this.reshape(0, 0, 600, 650);
    }
    
    public void add(final String text, final String s) {
        if (this.showWindow) {
            boolean b = false;
            for (int i = 0; i < 6; ++i) {
                for (int j = 0; j < 6; ++j) {
                    if (this.\u0121[i][j].getText().equals(text)) {
                        this.\u0120[i][j].appendText(String.valueOf(s) + "\n");
                        b = true;
                    }
                }
            }
            if (!b) {
                for (int k = 0; k < 6; ++k) {
                    for (int l = 0; l < 6; ++l) {
                        if (this.\u0121[k][l].getText().equals("         [EMPTY]         ")) {
                            this.\u0121[k][l].setText(text);
                            this.\u0120[k][l].appendText(String.valueOf(s) + "\n");
                            k = 6;
                            l = 6;
                        }
                    }
                }
            }
        }
    }
    
    public void showWindow() {
        this.show();
        this.showWindow = true;
    }
    
    public void hideWindow() {
        this.hide();
        this.showWindow = false;
    }
}
