// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.console.command;

import org.apache.activemq.console.command.store.amq.AMQJournalToolCommand;
import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.activemq.console.formatter.OutputFormatter;
import java.io.OutputStream;
import org.apache.activemq.console.formatter.CommandShellOutputFormatter;
import org.apache.activemq.console.CommandContext;
import java.io.PrintStream;
import java.io.InputStream;

public class ShellCommand extends AbstractCommand
{
    private boolean interactive;
    private String[] helpFile;
    
    public ShellCommand() {
        this(false);
    }
    
    public ShellCommand(final boolean interactive) {
        this.interactive = interactive;
        this.helpFile = new String[] { interactive ? "Usage: [task] [task-options] [task data]" : "Usage: Main [--extdir <dir>] [task] [task-options] [task data]", "", "Tasks (default task is start):", "    start           - Creates and starts a broker using a configuration file, or a broker URI.", "    create          - Creates a runnable broker instance in the specified path", "    stop            - Stops a running broker specified by the broker name.", "    list            - Lists all available brokers in the specified JMX context.", "    query           - Display selected broker component's attributes and statistics.", "    browse          - Display selected messages in a specified destination.", "    journal-audit   - Allows you to view records stored in the persistent journal.", "    purge           - Delete selected destination's messages that matches the message selector", "    encrypt         - Encrypts given text", "    decrypt         - Decrypts given text", "", "Task Options (Options specific to each task):", "    --extdir <dir>  - Add the jar files in the directory to the classpath.", "    --version       - Display the version information.", "    -h,-?,--help    - Display this help information. To display task specific help, use " + (interactive ? "" : "Main ") + "[task] -h,-?,--help", "", "Task Data:", "    - Information needed by each specific task.", "" };
    }
    
    public static int main(final String[] args, final InputStream in, final PrintStream out) {
        final CommandContext context = new CommandContext();
        context.setFormatter(new CommandShellOutputFormatter(out));
        final List<String> tokens = new ArrayList<String>(Arrays.asList(args));
        final ShellCommand main = new ShellCommand();
        try {
            main.setCommandContext(context);
            main.execute(tokens);
            return 0;
        }
        catch (Exception e) {
            context.printException(e);
            return -1;
        }
    }
    
    public boolean isInteractive() {
        return this.interactive;
    }
    
    public void setInteractive(final boolean interactive) {
        this.interactive = interactive;
    }
    
    @Override
    protected void runTask(final List<String> tokens) throws Exception {
        if (tokens.size() > 0) {
            Command command = null;
            final String taskToken = tokens.remove(0);
            if (taskToken.equals("start")) {
                command = new StartCommand();
            }
            else if (taskToken.equals("create")) {
                command = new CreateCommand();
            }
            else if (taskToken.equals("stop")) {
                command = new ShutdownCommand();
            }
            else if (taskToken.equals("list")) {
                command = new ListCommand();
            }
            else if (taskToken.equals("query")) {
                command = new QueryCommand();
            }
            else if (taskToken.equals("bstat")) {
                command = new BstatCommand();
            }
            else if (taskToken.equals("browse")) {
                command = new AmqBrowseCommand();
            }
            else if (taskToken.equals("purge")) {
                command = new PurgeCommand();
            }
            else if (taskToken.equals("journal-audit")) {
                command = new AMQJournalToolCommand();
            }
            else if (taskToken.equals("encrypt")) {
                command = new EncryptCommand();
            }
            else if (taskToken.equals("decrypt")) {
                command = new DecryptCommand();
            }
            else if (taskToken.equals("help")) {
                this.printHelp();
            }
            else {
                this.printHelp();
            }
            if (command != null) {
                command.setCommandContext(this.context);
                command.execute(tokens);
            }
        }
        else {
            this.printHelp();
        }
    }
    
    @Override
    protected void printHelp() {
        this.context.printHelp(this.helpFile);
    }
}
