// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.parser;

import com.stonewall.cornerstone.dsp.loader.Loader;

public class ConfigTextParser extends TextParser
{
    public ConfigTextParser(final Loader loader) {
        super(loader, "parser.config.main.xml", "en:deviceConfig");
    }
}
