// 
// Decompiled by Procyon v0.5.30
// 

package blackmagic.finance.investment.calculators.compound;

import javax.swing.JPopupMenu;
import javax.swing.JComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;

final class CompoundFormFactory
{
    public static BasicCompoundForm getFirstForm() {
        final BasicCompoundForm basicCompoundForm = new BasicCompoundForm("Base Scenario");
        basicCompoundForm.setInitialPrincipleAccelerator('P');
        basicCompoundForm.setAnnualIntRateAccelerator('I');
        basicCompoundForm.setPaymentsPerYearAccelerator('Y');
        basicCompoundForm.setYearsToCompoundAccelerator('M');
        basicCompoundForm.setAnnualTaxRateAccelerator('T');
        basicCompoundForm.setCompoundedAmountAccelerator('U');
        return basicCompoundForm;
    }
    
    public static BasicCompoundForm getSecondForm() {
        final BasicCompoundForm basicCompoundForm = new BasicCompoundForm("Alternate Scenario");
        basicCompoundForm.setInitialPrincipleAccelerator('N');
        basicCompoundForm.setAnnualIntRateAccelerator('L');
        basicCompoundForm.setPaymentsPerYearAccelerator('E');
        basicCompoundForm.setYearsToCompoundAccelerator('O');
        basicCompoundForm.setAnnualTaxRateAccelerator('X');
        basicCompoundForm.setCompoundedAmountAccelerator('D');
        return basicCompoundForm;
    }
    
    public static DiffCompoundForm getDiffForm(final BasicCompoundForm basicCompoundForm, final BasicCompoundForm basicCompoundForm2) {
        final DiffCompoundForm diffCompoundForm = new DiffCompoundForm("Difference", basicCompoundForm.getModel(), basicCompoundForm2.getModel());
        augmentPopups(diffCompoundForm, basicCompoundForm, basicCompoundForm2);
        return diffCompoundForm;
    }
    
    private static void augmentPopups(final DiffCompoundForm diffCompoundForm, final BasicCompoundForm basicCompoundForm, final BasicCompoundForm basicCompoundForm2) {
        final JPopupMenu popupMenu = basicCompoundForm.getPopupMenu();
        final JPopupMenu popupMenu2 = basicCompoundForm2.getPopupMenu();
        final JMenuItem menuItem = new JMenuItem("Synchronise to " + basicCompoundForm2.getTitle(), 83);
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                basicCompoundForm.synchroniseWith(basicCompoundForm2);
            }
        });
        popupMenu.add(menuItem);
        diffCompoundForm.subscribeState(new SyncListener(menuItem));
        final JMenuItem menuItem2 = new JMenuItem("Synchronise to " + basicCompoundForm.getTitle(), 83);
        menuItem2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                basicCompoundForm2.synchroniseWith(basicCompoundForm);
            }
        });
        popupMenu2.add(menuItem2);
        diffCompoundForm.subscribeState(new SyncListener(menuItem2));
    }
}
