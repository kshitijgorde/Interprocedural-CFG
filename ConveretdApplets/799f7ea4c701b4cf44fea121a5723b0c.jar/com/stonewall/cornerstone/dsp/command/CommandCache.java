// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.command;

import java.util.ArrayList;
import java.util.List;

public class CommandCache
{
    private List<CommandContainer> commands;
    private static final ThreadLocal<CommandCache> cache;
    
    static {
        cache = new ThreadLocal<CommandCache>();
    }
    
    public CommandCache() {
        this.commands = new ArrayList<CommandContainer>();
    }
    
    public void add(final CommandContainer command) {
        this.commands.add(command);
    }
    
    public List<CommandContainer> getCommands() {
        return this.commands;
    }
    
    public void clear() {
        this.commands = new ArrayList<CommandContainer>();
    }
    
    public static CommandCache getCurrent() {
        CommandCache result = CommandCache.cache.get();
        if (result == null) {
            result = new CommandCache();
            CommandCache.cache.set(result);
        }
        return result;
    }
    
    public static CommandCache swap(final CommandCache newCache) {
        final CommandCache old = getCurrent();
        CommandCache.cache.set(newCache);
        return old;
    }
}
