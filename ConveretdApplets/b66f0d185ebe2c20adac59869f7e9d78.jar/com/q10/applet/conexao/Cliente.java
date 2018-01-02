// 
// Decompiled by Procyon v0.5.30
// 

package com.q10.applet.conexao;

import java.net.URL;
import java.io.DataOutputStream;
import java.io.DataInputStream;

public abstract class Cliente
{
    protected DataInputStream in;
    protected DataOutputStream out;
    protected static final int porta = 80;
    public boolean temErro;
    protected boolean ehComprimido;
    
    public void abreConexao(final String s, final URL url) {
    }
    
    public abstract void abreConexao(final String p0);
    
    public abstract void enviaComando(final String p0);
    
    public abstract byte[] recebeResposta();
    
    public abstract void terminaConexao();
    
    public Cliente() {
        this.ehComprimido = false;
    }
}
