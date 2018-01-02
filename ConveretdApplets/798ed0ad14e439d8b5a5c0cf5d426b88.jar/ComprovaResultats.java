import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class ComprovaResultats extends Thread
{
    AbstractBox I;
    OmegaClient append;
    MessageBox arreglaError;
    boolean cadenaOmega;
    FormulaEditor classification;
    
    public ComprovaResultats(final FormulaEditor classification, final AbstractBox i, final OmegaClient append, final boolean cadenaOmega) {
        this.classification = classification;
        this.cadenaOmega = cadenaOmega;
        this.I = i;
        this.append = append;
    }
    
    public final void run() {
        System.out.println("Comprovant resultats");
        final int nfills = this.I.fill[0].nfills;
        final String[][] array = new String[nfills][];
        final String[][] array2 = new String[nfills][];
        final int[] array3 = new int[nfills];
        this.arreglaError = null;
        boolean b = false;
        for (int i = 0; i < nfills; ++i) {
            final CapsaComandes capsaComandes = (CapsaComandes)this.I.fill[0].fill[i];
            array3[i] = capsaComandes.fill[0].nfills;
            array[i] = new String[array3[i]];
            array2[i] = new String[array3[i]];
            for (int j = 0; j < array3[i]; ++j) {
                array[i][j] = FormulaEditorCalc.cadenaOmega(capsaComandes.fill[0].fill[j]);
            }
            if (this.arreglaError != null) {
                this.arreglaError.dispose();
            }
            (this.arreglaError = new MessageBox(this.classification.getMainFrame(), "Estat de comprovaci\ufffd", "Calculant bloc: " + (i + 1) + "/" + nfills)).setModal(false);
            this.arreglaError.show();
            try {
                array2[i] = this.append.evaluate(array[i], -2147483645);
            }
            catch (Exception ex) {
                array2[i] = this.append.arreglaError(array[i], ex.getMessage());
            }
            for (int k = 0; k < array2[i].length; ++k) {
                if (this.arreglaError != null) {
                    this.arreglaError.dispose();
                }
                (this.arreglaError = new MessageBox(this.classification.getMainFrame(), "Estat de comprovaci\ufffd", "Comprovant sent\ufffdncia: " + (k + 1) + "/" + array2[i].length)).setModal(false);
                this.arreglaError.show();
                final String script = this.classification.script(capsaComandes.fill[0].fill[k].fill[1]);
                if (!Utils.equals(script, this.classification.script(this.classification.parse(array2[i][k])))) {
                    final String string = "CIS La resposta(" + i + "," + k + "):\"" + script + "\" hauria de ser \"" + array2[i][k] + "\"";
                    final ErrorWarning errorWarning = new ErrorWarning();
                    errorWarning.classification = "Error";
                    errorWarning.column = 0;
                    errorWarning.line = k;
                    errorWarning.text = string;
                    capsaComandes.fill[0].fill[k].fill[1].error = errorWarning.classification;
                    System.out.println(string);
                    if (!this.cadenaOmega) {
                        new MessageBox(this.classification.getMainFrame(), "Error de comprovaci\ufffd", string, 1).show();
                    }
                    b = true;
                }
                else {
                    capsaComandes.fill[0].fill[k].fill[1].error = null;
                }
                System.out.print(".");
            }
        }
        if (b) {
            this.classification.colors[11] = new Color(255, 216, 128);
        }
        else {
            this.classification.colors[11] = Color.white;
        }
        if (this.arreglaError != null) {
            this.arreglaError.dispose();
        }
        if (!this.cadenaOmega) {
            new MessageBox(this.classification.getMainFrame(), "Final de comprovaci\ufffd", "Ja s'ha comprovat tot", 1).show();
        }
        System.out.println("resultats comprovats");
    }
}
