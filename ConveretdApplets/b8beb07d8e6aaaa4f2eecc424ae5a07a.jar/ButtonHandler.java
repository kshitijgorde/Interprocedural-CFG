import java.awt.event.ActionEvent;
import java.net.URL;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

final class ButtonHandler implements ActionListener
{
    public VJpoker details;
    public URL loc_panic;
    
    public ButtonHandler(final VJpoker details) {
        this.details = details;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.details.btn_Deal) {
            this.details.draw_Cards();
            return;
        }
        if (actionEvent.getSource() == this.details.btn_Pass) {
            this.details.scoreHand();
            return;
        }
        if (actionEvent.getSource() == this.details.btn_Betone) {
            if (this.details.bet_Column == 5) {
                this.details.bet_Column = 1;
            }
            else {
                final VJpoker details = this.details;
                ++details.bet_Column;
            }
            this.details.click.play();
            this.details.set_column();
            this.details.lbl_Bet.setText(String.valueOf(this.details.bet_Value * this.details.bet_Column));
            this.details.lbl_Balance.setText(String.valueOf(this.details.balance - this.details.bet_Value * this.details.bet_Column));
            return;
        }
        if (actionEvent.getSource() == this.details.btn_Betmax) {
            this.details.click.play();
            this.details.bet_Column = 5;
            this.details.set_column();
            this.details.lbl_Bet.setText(String.valueOf(this.details.bet_Value * this.details.bet_Column));
            this.details.lbl_Balance.setText(String.valueOf(this.details.balance - this.details.bet_Value * this.details.bet_Column));
        }
    }
}
