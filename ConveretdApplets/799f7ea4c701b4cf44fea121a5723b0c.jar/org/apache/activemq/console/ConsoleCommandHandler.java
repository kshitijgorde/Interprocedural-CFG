// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.console;

import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;
import org.apache.activemq.console.formatter.OutputFormatter;
import java.io.OutputStream;
import org.apache.activemq.console.formatter.CommandShellOutputFormatter;
import java.io.ByteArrayOutputStream;
import javax.jms.TextMessage;
import org.apache.activemq.console.command.ShellCommand;
import org.apache.activemq.broker.util.CommandHandler;

public class ConsoleCommandHandler implements CommandHandler
{
    private ShellCommand command;
    
    public ConsoleCommandHandler() {
        this.command = new ShellCommand(true);
    }
    
    @Override
    public void processCommand(final TextMessage request, final TextMessage response) throws Exception {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final CommandContext ctx = new CommandContext();
        ctx.setFormatter(new CommandShellOutputFormatter(out));
        final String requestText = request.getText();
        final List<String> tokens = this.tokenize(requestText);
        this.command.setCommandContext(ctx);
        this.command.execute(tokens);
        out.flush();
        final byte[] bytes = out.toByteArray();
        final String answer = new String(bytes);
        response.setText(answer);
    }
    
    protected List<String> tokenize(final String text) {
        final List<String> answer = new ArrayList<String>();
        final StringTokenizer iter = new StringTokenizer(text);
        while (iter.hasMoreTokens()) {
            answer.add(iter.nextToken());
        }
        return answer;
    }
}
