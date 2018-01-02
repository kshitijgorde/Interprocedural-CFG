// 
// Decompiled by Procyon v0.5.30
// 

package blackmagic.finance.investment.calculators.compound;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JDialog;
import blackmagic.swing.JSimpleDialog;

final class CompounderDialogFactory
{
    private static final JSimpleDialog vUsageDialog;
    
    public static JDialog getUsageDialog() {
        return CompounderDialogFactory.vUsageDialog;
    }
    
    private static JSimpleDialog buildUsageDialog() {
        final JSimpleDialog simpleDialog = new JSimpleDialog(null, getUsageString(), "The Compounder -- Usage Guide", false);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        simpleDialog.setSize(screenSize.width / 3 * 2, screenSize.height / 3 * 2);
        return simpleDialog;
    }
    
    private static String getUsageString() {
        return "<html><h2>Usage Guide</h2><h3>Using the calculator fields</h3>Pressing &lt;ENTER&gt; on a field will cause a re-calculation without losing field focus.<p><p>Pressing &lt;TAB&gt; on a field will cause a re-calculation and move focus to the next field.<p><p>All editable fields (bar the <em>Compounded Amount</em> field) will cause the <em>Compounded Amount</em> field to change.<br> Editing the <em>Compounded Amount</em> field will cause the <em>Annual Interest Rate</em> field to change.<h3>The Scenario Menu</h3>This menu allows you to configure the behaviour of either the Base or the Alternate scenario (see below).<h3>The View Menu</h3>The view menu allows you to select between two modes of operation for the calculator:<ul>  <li> Basic Mode - Shows a base compounding scenario   <li> Comparison Mode - adds an alternate scenario and shows the difference between them.</ul><h3>The Popup Menus</h3>Hitting the right mouse button over a scenario will allow you to select from the following options: <ul>  <li> Reset - re-initialises the relevant scenario  <li> Configure - allows the user to configure the relevant scenario's behaviour (see below)  <li> Synchronise to -  sets values of the relevant scenario to those if its twin.</ul><h3>Configuring Scenario Behaviour</h3>Configuring a scenario involves modifying the behaviour of a compound calculation by choosing which<p> field will alter its value when you finish changing the contents of the current field with focus.<p><p>Note that there will be times where rounding will result in a change in both the selected field and the<p> <em>Compounded Amount</em> field.  Consider the <em>Compounded Amount</em> field as a spillover field, catching<p> whatever result might not quite fit with the values  specified in the other fields.</html>";
    }
    
    static {
        vUsageDialog = buildUsageDialog();
    }
}
