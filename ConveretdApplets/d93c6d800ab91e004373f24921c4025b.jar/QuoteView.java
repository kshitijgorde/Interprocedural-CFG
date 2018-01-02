import java.awt.Component;
import java.awt.Panel;
import java.awt.Label;

// 
// Decompiled by Procyon v0.5.30
// 

public class QuoteView
{
    public Label dateLab;
    public Label dateVal;
    public Label highLab;
    public Label highVal;
    public Label lowLab;
    public Label lowVal;
    public Label lastPriceLab;
    public Label lastPriceVal;
    public Label changeLab;
    public Label changeVal;
    public Label volumeLab;
    public Label volumeVal;
    
    public QuoteView(final Panel panel) {
        panel.add(this.dateLab = new Label("Date:"));
        panel.add(this.dateVal = new Label());
        panel.add(this.highLab = new Label("High:"));
        panel.add(this.highVal = new Label());
        panel.add(this.lowLab = new Label("Low:"));
        panel.add(this.lowVal = new Label());
        panel.add(this.lastPriceLab = new Label("Last Price:"));
        panel.add(this.lastPriceVal = new Label());
        panel.add(this.changeLab = new Label("Change:"));
        panel.add(this.changeVal = new Label());
        panel.add(this.volumeLab = new Label("Volume:"));
        panel.add(this.volumeVal = new Label());
    }
    
    public void setQuote(final QuoteModel quoteModel) {
        if (quoteModel == null) {
            final String s = "";
            this.dateVal.setText(s);
            this.highVal.setText(s);
            this.lowVal.setText(s);
            this.lastPriceVal.setText(s);
            this.changeVal.setText(s);
            this.volumeVal.setText(s);
            return;
        }
        this.dateVal.setText(Pretty.date(quoteModel.getDate()));
        this.highVal.setText(Pretty.udollars(quoteModel.getHigh()));
        this.lowVal.setText(Pretty.udollars(quoteModel.getLow()));
        this.lastPriceVal.setText(Pretty.udollars(quoteModel.getLastPrice()));
        this.changeVal.setText(Pretty.dollars(quoteModel.getChange()));
        this.volumeVal.setText(Pretty.commas(quoteModel.getVolume()));
    }
}
