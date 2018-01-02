// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.xaction;

import com.stonewall.cornerstone.dsp.command.CliCommand;
import com.stonewall.cornerstone.dsp.command.DeviceCommand;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xaction.XActionDocument;
import org.xmodel.xpath.expression.IExpression;

public class CliAction extends CommandAction
{
    private IExpression cmd;
    
    public CliAction() {
        this.cmd = null;
    }
    
    @Override
    public void configure(final XActionDocument document) {
        super.configure(document);
        this.cmd = document.getExpression("cmd", false);
        if (this.cmd == null) {
            this.cmd = document.getExpression();
        }
    }
    
    public DeviceCommand createCommand(final IContext context) {
        final CliCommand command = new CliCommand();
        command.setCommand(this.cmd.evaluateString(context));
        return command;
    }
}
