// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.console.command.store.amq;

import java.io.File;
import java.util.List;
import org.apache.activemq.console.CommandContext;
import org.apache.activemq.console.command.Command;

public class AMQJournalToolCommand implements Command
{
    private CommandContext context;
    
    @Override
    public void execute(final List<String> tokens) throws Exception {
        final AMQJournalTool consumerTool = new AMQJournalTool();
        final String[] args = new String[tokens.size()];
        tokens.toArray(args);
        final String[] directories = CommandLineSupport.setOptions(consumerTool, args);
        for (int i = 0; i < directories.length; ++i) {
            consumerTool.getDirs().add(new File(directories[i]));
        }
        consumerTool.execute();
    }
    
    @Override
    public void setCommandContext(final CommandContext context) {
        this.context = context;
    }
}
