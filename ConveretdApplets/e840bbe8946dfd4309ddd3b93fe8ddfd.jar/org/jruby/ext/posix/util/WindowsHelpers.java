// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.posix.util;

import java.util.HashMap;
import org.jruby.ext.posix.POSIX;
import java.util.StringTokenizer;
import java.util.Map;

public class WindowsHelpers
{
    private static final String COMMAND_DOT_COM = "command.com";
    private static final int CDC_LENGTH;
    private static Map<String, InternalType> INTERNAL_COMMANDS;
    
    private static void joinSingleArgv(final StringBuilder buffer, final String arg, final boolean quote, final boolean escape) {
        int backslashCount = 0;
        int start = 0;
        if (quote) {
            buffer.append('\"');
        }
        for (int i = 0; i < arg.length(); ++i) {
            final char c = arg.charAt(i);
            switch (c) {
                case '\\': {
                    ++backslashCount;
                    continue;
                }
                case '\"': {
                    buffer.append(arg.substring(start, i));
                    for (int j = 0; j < backslashCount + 1; ++j) {
                        buffer.append('\\');
                    }
                    backslashCount = 0;
                    start = i;
                }
                case '<':
                case '>':
                case '^':
                case '|': {
                    if (escape && !quote) {
                        buffer.append(arg.substring(start, i));
                        buffer.append('^');
                        start = i;
                        continue;
                    }
                    break;
                }
            }
            backslashCount = 0;
        }
        buffer.append(arg.substring(start));
        if (quote) {
            buffer.append('\"');
        }
    }
    
    public static String joinArgv(final String command, final String[] argv, final boolean escape) {
        final StringBuilder buffer = new StringBuilder();
        if (command != null) {
            buffer.append(command);
        }
        for (int last_index = argv.length - 1, i = 0; i <= last_index; ++i) {
            joinSingleArgv(buffer, argv[i], quotable(argv[i]), escape);
            if (i != last_index) {
                buffer.append(' ');
            }
        }
        return buffer.toString();
    }
    
    public static boolean quotable(final String value) {
        if (value == null) {
            return false;
        }
        final StringTokenizer toker = new StringTokenizer(value, " \t\"'");
        toker.nextToken();
        return toker.hasMoreTokens();
    }
    
    public static boolean isBatch(final String value) {
        if (value == null) {
            return false;
        }
        final int length = value.length();
        if (length < 5) {
            return false;
        }
        final String end = value.substring(length - 5);
        return end.equalsIgnoreCase(".bat") || end.equalsIgnoreCase(".cmd");
    }
    
    public static String[] processCommandLine(final POSIX posix, String command, final String program, final String path) {
        String shell = null;
        if (program != null) {
            final String fullPath = Finder.findFileInPath(posix, program, path);
            shell = ((fullPath == null) ? program : fullPath.replace('/', '\\'));
        }
        else {
            command = command.substring(firstNonWhitespaceIndex(command));
            shell = System.getenv("COMSPEC");
            boolean notHandledYet = true;
            if (shell != null) {
                final boolean commandDotCom = isCommandDotCom(shell);
                if (hasBuiltinSpecialNeeds(command) || isInternalCommand(command, commandDotCom)) {
                    final String quote = commandDotCom ? "\"" : "";
                    command = shell + "/c " + quote + command + quote;
                    notHandledYet = false;
                }
            }
            if (notHandledYet) {
                final char firstChar = command.charAt(0);
                char quote2 = (firstChar == '\"') ? firstChar : ((firstChar == '\'') ? firstChar : '\0');
                final int commandLength = command.length();
                int i = (quote2 != '\0') ? 1 : 0;
                while (true) {
                    while (i != commandLength) {
                        final char c = command.charAt(i);
                        if (c == quote2) {
                            shell = command.substring(1, i);
                            shell = Finder.findFileInPath(posix, shell, path);
                            if (shell == null) {
                                shell = command.substring(0, i - 1);
                                return new String[] { command, shell };
                            }
                            if (!shell.contains(" ")) {
                                quote2 = '\0';
                            }
                            shell.replace('/', '\\');
                            return new String[] { command, shell };
                        }
                        else {
                            if (quote2 == '\0') {
                                if (Character.isSpaceChar(c) || isFunnyChar(c)) {
                                    shell = command.substring(0, i - 1);
                                }
                            }
                            ++i;
                        }
                    }
                    shell = command;
                    continue;
                }
            }
        }
        return new String[] { command, shell };
    }
    
    public static String[] processCommandArgs(final POSIX posix, String program, String[] argv, final String path) {
        if (program == null || program.length() == 0) {
            program = argv[0];
        }
        boolean addSlashC = false;
        boolean isNotBuiltin = false;
        boolean notHandledYet = true;
        final String shell = System.getenv("COMSPEC");
        String command = null;
        if (shell != null) {
            final boolean commandDotCom = isCommandDotCom(shell);
            if (isInternalCommand(program, commandDotCom)) {
                isNotBuiltin = !commandDotCom;
                program = shell;
                addSlashC = true;
                notHandledYet = false;
            }
        }
        if (notHandledYet) {
            command = Finder.findFileInPath(posix, program, path);
            if (command != null) {
                program = command.replace('/', '\\');
            }
            else if (program.contains("/")) {
                command = (program = program.replace('/', '\\'));
            }
        }
        if (addSlashC || isBatch(program)) {
            if (addSlashC) {
                command = program + " /c ";
            }
            else {
                final String[] newArgv = new String[argv.length - 1];
                System.arraycopy(argv, 1, newArgv, 0, argv.length - 1);
                argv = newArgv;
            }
            if (argv.length > 0) {
                command = joinArgv(command, argv, isNotBuiltin);
            }
            program = (addSlashC ? shell : null);
        }
        else {
            command = joinArgv(null, argv, false);
        }
        return new String[] { command, program };
    }
    
    private static boolean isFunnyChar(final char c) {
        return c == '<' || c == '>' || c == '|' || c == '*' || c == '?' || c == '\"';
    }
    
    private static boolean hasBuiltinSpecialNeeds(final String value) {
        final int length = value.length();
        char quote = '\0';
        for (int i = 0; i < length; ++i) {
            final char c = value.charAt(i);
            switch (c) {
                case '\"':
                case '\'': {
                    if (quote == '\0') {
                        quote = c;
                        break;
                    }
                    if (quote == c) {
                        quote = '\0';
                        break;
                    }
                    break;
                }
                case '\n':
                case '<':
                case '>':
                case '|': {
                    if (quote != '\0') {
                        return true;
                    }
                    break;
                }
                case '%': {
                    if (i + 1 >= length) {
                        break;
                    }
                    ++i;
                    char c2 = value.charAt(i);
                    if (c2 != ' ' && !Character.isLetter(c2)) {
                        break;
                    }
                    for (int j = i; j < length; ++j) {
                        c2 = value.charAt(j);
                        if (c2 != ' ' && !Character.isLetterOrDigit(c2)) {
                            break;
                        }
                    }
                    if (c2 == '%') {
                        return true;
                    }
                    break;
                }
            }
        }
        return false;
    }
    
    private static int firstNonWhitespaceIndex(final String value) {
        int length;
        int i;
        for (length = value.length(), i = 0; i < length && Character.isSpaceChar(value.charAt(i)); ++i) {}
        return i;
    }
    
    private static boolean isDirectorySeparator(final char value) {
        return value == '/' || value == '\\';
    }
    
    private static boolean isCommandDotCom(final String command) {
        final int length = command.length();
        final int i = length - WindowsHelpers.CDC_LENGTH;
        return i == 0 || (i > 0 && isDirectorySeparator(command.charAt(i - 1)) && command.regionMatches(true, i, "command.com", 0, WindowsHelpers.CDC_LENGTH));
    }
    
    private static boolean isInternalCommand(final String command, final boolean hasCommandDotCom) {
        assert command != null && !Character.isSpaceChar(command.charAt(0)) : "Spaces should have been stripped off already";
        final int length = command.length();
        final StringBuilder buf = new StringBuilder();
        int i = 0;
        char c = '\0';
        while (i < length) {
            c = command.charAt(i);
            if (!Character.isLetter(c)) {
                break;
            }
            buf.append(Character.toLowerCase(c));
            ++i;
        }
        if (i < length) {
            if (c == '.' && i + 1 < length) {
                ++i;
            }
            switch (command.charAt(i)) {
                case '<':
                case '>':
                case '|': {
                    return true;
                }
                case '\0':
                case '\t':
                case '\n':
                case ' ': {
                    break;
                }
                default: {
                    return false;
                }
            }
        }
        final InternalType kindOf = WindowsHelpers.INTERNAL_COMMANDS.get(buf.toString());
        return kindOf == InternalType.BOTH || (hasCommandDotCom ? (kindOf == InternalType.COMMAND) : (kindOf == InternalType.SHELL));
    }
    
    static {
        CDC_LENGTH = "command.com".length();
        WindowsHelpers.INTERNAL_COMMANDS = new HashMap<String, InternalType>() {
            {
                this.put("assoc", InternalType.COMMAND);
                this.put("break", InternalType.BOTH);
                this.put("call", InternalType.BOTH);
                this.put("cd", InternalType.BOTH);
                this.put("chcp", InternalType.SHELL);
                this.put("chdir", InternalType.BOTH);
                this.put("cls", InternalType.BOTH);
                this.put("color", InternalType.COMMAND);
                this.put("copy", InternalType.BOTH);
                this.put("ctty", InternalType.SHELL);
                this.put("date", InternalType.BOTH);
                this.put("del", InternalType.BOTH);
                this.put("dir", InternalType.BOTH);
                this.put("echo", InternalType.BOTH);
                this.put("endlocal", InternalType.COMMAND);
                this.put("erase", InternalType.BOTH);
                this.put("exit", InternalType.BOTH);
                this.put("for", InternalType.BOTH);
                this.put("ftype", InternalType.COMMAND);
                this.put("goto", InternalType.BOTH);
                this.put("if", InternalType.BOTH);
                this.put("lfnfor", InternalType.SHELL);
                this.put("lh", InternalType.SHELL);
                this.put("lock", InternalType.SHELL);
                this.put("md", InternalType.BOTH);
                this.put("mkdir", InternalType.BOTH);
                this.put("move", InternalType.COMMAND);
                this.put("path", InternalType.BOTH);
                this.put("pause", InternalType.BOTH);
                this.put("popd", InternalType.COMMAND);
                this.put("prompt", InternalType.BOTH);
                this.put("pushd", InternalType.COMMAND);
                this.put("rd", InternalType.BOTH);
                this.put("rem", InternalType.BOTH);
                this.put("ren", InternalType.BOTH);
                this.put("rename", InternalType.BOTH);
                this.put("rmdir", InternalType.BOTH);
                this.put("set", InternalType.BOTH);
                this.put("setlocal", InternalType.COMMAND);
                this.put("shift", InternalType.BOTH);
                this.put("start", InternalType.COMMAND);
                this.put("time", InternalType.BOTH);
                this.put("title", InternalType.COMMAND);
                this.put("truename", InternalType.SHELL);
                this.put("type", InternalType.BOTH);
                this.put("unlock", InternalType.SHELL);
                this.put("ver", InternalType.BOTH);
                this.put("verify", InternalType.BOTH);
                this.put("vol", InternalType.BOTH);
            }
        };
    }
    
    private enum InternalType
    {
        SHELL, 
        COMMAND, 
        BOTH;
    }
}
