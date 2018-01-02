// 
// Decompiled by Procyon v0.5.30
// 

package anon.tor;

import java.util.Hashtable;
import anon.util.ByteArrayUtil;
import java.io.IOException;
import logging.LogHolder;
import logging.LogType;

public class TorSocksChannel extends TorChannel
{
    private static final int SOCKS_WAIT_FOR_VERSION = 0;
    private static final int SOCKS5_WAIT_FOR_METHODS = 1;
    private static final int SOCKS5_WAIT_FOR_REQUEST = 2;
    private static final int SOCKS4_WAIT_FOR_REQUEST = 3;
    private static final int DATA_MODE = 4;
    private static final int SOCKS_5 = 5;
    private static final int SOCKS_4 = 4;
    private int m_state;
    private int m_version;
    private byte[] m_data;
    private Tor m_Tor;
    
    public TorSocksChannel(final Tor tor) throws IOException {
        this.m_state = 0;
        this.m_data = null;
        this.m_Tor = tor;
        LogHolder.log(7, LogType.TOR, "new TorSocksChannel() - object created.");
    }
    
    protected void send(final byte[] array, final int n) throws IOException {
        switch (this.m_state) {
            case 0: {
                this.state_WaitForVersion(array, n);
                break;
            }
            case 1: {
                this.state_WaitForMethods(array, n);
                break;
            }
            case 2: {
                this.state_WaitForRequest_Socks5(array, n);
                break;
            }
            case 3: {
                this.state_WaitForRequest_Socks4(array, n);
                break;
            }
            case 4: {
                super.send(array, n);
                break;
            }
            default: {
                throw new IOException("illegal status");
            }
        }
    }
    
    private void state_WaitForVersion(final byte[] array, final int n) throws IOException {
        if (array != null && n > 0) {
            this.m_data = ByteArrayUtil.conc(this.m_data, array, n);
        }
        if (this.m_data.length > 1) {
            this.m_version = this.m_data[0];
            if (this.m_version != 5 && this.m_version != 4) {
                this.close();
                throw new IOException("Wrong Sock Protocol number");
            }
            this.m_data = ByteArrayUtil.copy(this.m_data, 1, this.m_data.length - 1);
            if (this.m_version == 5) {
                this.m_state = 1;
            }
            else {
                this.m_state = 3;
            }
            this.send(null, 0);
        }
    }
    
    private void state_WaitForMethods(final byte[] array, final int n) throws IOException {
        if (array != null && n > 0) {
            this.m_data = ByteArrayUtil.conc(this.m_data, array, n);
        }
        if (this.m_data.length > 1) {
            final int n2 = this.m_data[0] & 0xFF;
            final int n3 = n2 + 1;
            if (this.m_data.length >= n3) {
                boolean b = false;
                byte[] array2 = null;
                for (int i = 0; i < n2; ++i) {
                    if (this.m_data[i + 1] == 0) {
                        b = true;
                        array2 = new byte[] { 5, 0 };
                        this.m_state = 2;
                        break;
                    }
                }
                if (!b) {
                    array2 = new byte[] { 5, -1 };
                }
                super.recv(array2, 0, array2.length);
                if (!b) {
                    return;
                }
                this.m_data = ByteArrayUtil.copy(this.m_data, n3, this.m_data.length - n3);
                if (this.m_data.length > 0) {
                    this.send(null, 0);
                }
            }
        }
    }
    
    private void state_WaitForRequest_Socks4(final byte[] array, final int n) throws IOException {
        if (array != null && n > 0) {
            this.m_data = ByteArrayUtil.conc(this.m_data, array, n);
        }
        if (this.m_data.length <= 0) {
            return;
        }
        if (this.m_data[0] != 1) {
            final byte[] array2 = { 0, 91, 0, 0, 0, 0, 0, 0 };
            this.m_data = null;
            super.recv(array2, 0, array2.length);
            return;
        }
        if (this.m_data.length >= 8) {
            int n2 = 1;
            String s = Integer.toString(this.m_data[3] & 0xFF) + "." + Integer.toString(this.m_data[4] & 0xFF) + "." + Integer.toString(this.m_data[5] & 0xFF) + "." + Integer.toString(this.m_data[6] & 0xFF);
            final int n3 = (this.m_data[1] & 0xFF) << 8 | (this.m_data[2] & 0xFF);
            int n4;
            for (n2 += 6, n4 = 7; n4 < this.m_data.length && this.m_data[n4] != 0; ++n4, ++n2) {}
            if (this.m_data[n4] != 0) {
                return;
            }
            ++n2;
            if (s.startsWith("0.0.0")) {
                ++n4;
                final StringBuffer sb = new StringBuffer();
                while (n4 < this.m_data.length && this.m_data[n4] != 0) {
                    sb.append((char)this.m_data[n4]);
                    ++n4;
                    ++n2;
                }
                if (this.m_data[n4] != 0) {
                    return;
                }
                ++n2;
                s = sb.toString();
            }
            boolean b = false;
            this.setDoNotCloseChannelOnErrorDuringConnect(true);
            int n5 = 0;
            final Hashtable<Circuit, Circuit> hashtable = new Hashtable<Circuit, Circuit>();
            while (!b && n5 < 3) {
                b = true;
                Circuit circuitForDestination = null;
                try {
                    circuitForDestination = this.m_Tor.getCircuitForDestination(s, n3, hashtable);
                    if (circuitForDestination == null) {
                        final byte[] array3 = { 0, 91, 0, 0, 0, 0, 0, 0 };
                        super.recv(array3, 0, array3.length);
                        this.closedByPeer();
                        return;
                    }
                    if (circuitForDestination.connectChannel(this, s, n3) != 0) {
                        b = false;
                        hashtable.put(circuitForDestination, circuitForDestination);
                    }
                }
                catch (IOException ex) {
                    if (circuitForDestination != null) {
                        hashtable.put(circuitForDestination, circuitForDestination);
                    }
                    b = false;
                }
                ++n5;
            }
            if (!b) {
                final byte[] array4 = { 0, 91, 0, 0, 0, 0, 0, 0 };
                super.recv(array4, 0, array4.length);
                this.closedByPeer();
                return;
            }
            final byte[] array5 = { 0, 90, 0, 0, 0, 0, 0, 0 };
            super.recv(array5, 0, array5.length);
            this.m_data = ByteArrayUtil.copy(this.m_data, n2, this.m_data.length - n2);
            this.m_state = 4;
            if (this.m_data.length > 0) {
                this.send(this.m_data, this.m_data.length);
                this.m_data = null;
            }
        }
    }
    
    private void state_WaitForRequest_Socks5(final byte[] array, final int n) throws IOException {
        if (array != null && n > 0) {
            this.m_data = ByteArrayUtil.conc(this.m_data, array, n);
        }
        if (this.m_data.length > 6) {
            int n2 = 0;
            String string = null;
            final byte b = this.m_data[1];
            final byte b2 = this.m_data[3];
            int n3 = 0;
            if (b != 1) {
                final byte[] conc = ByteArrayUtil.conc(new byte[] { 5, 7, 0 }, ByteArrayUtil.copy(this.m_data, 3, this.m_data.length - 3));
                this.m_data = null;
                super.recv(conc, 0, conc.length);
                return;
            }
            switch (b2) {
                case 1: {
                    if (this.m_data.length > 9) {
                        string = Integer.toString(this.m_data[4] & 0xFF) + "." + Integer.toString(this.m_data[5] & 0xFF) + "." + Integer.toString(this.m_data[6] & 0xFF) + "." + Integer.toString(this.m_data[7] & 0xFF);
                        n2 = ((this.m_data[8] & 0xFF) << 8 | (this.m_data[9] & 0xFF));
                        n3 = 10;
                        break;
                    }
                    break;
                }
                case 3: {
                    final int n4 = this.m_data[4] & 0xFF;
                    if (this.m_data.length >= 7 + n4) {
                        string = new String(this.m_data, 5, n4);
                        n2 = ((this.m_data[5 + n4] & 0xFF) << 8 | (this.m_data[6 + n4] & 0xFF));
                        n3 = n4 + 7;
                        break;
                    }
                    break;
                }
                default: {
                    final byte[] conc2 = ByteArrayUtil.conc(new byte[] { 5, 8, 0 }, ByteArrayUtil.copy(this.m_data, 3, this.m_data.length - 3));
                    super.recv(conc2, 0, conc2.length);
                    this.m_data = null;
                    break;
                }
            }
            if (string != null) {
                final Hashtable<Circuit, Circuit> hashtable = new Hashtable<Circuit, Circuit>();
                boolean b3 = false;
                this.setDoNotCloseChannelOnErrorDuringConnect(true);
                for (int i = 0; i < 3; ++i) {
                    final Circuit circuitForDestination = this.m_Tor.getCircuitForDestination(string, n2, hashtable);
                    if (circuitForDestination == null) {
                        break;
                    }
                    if (circuitForDestination.connectChannel(this, string, n2) == 0) {
                        b3 = true;
                        break;
                    }
                    hashtable.put(circuitForDestination, circuitForDestination);
                }
                if (!b3) {
                    final byte[] conc3 = ByteArrayUtil.conc(new byte[] { 5, 1, 0 }, ByteArrayUtil.copy(this.m_data, 3, n3 - 3));
                    super.recv(conc3, 0, conc3.length);
                    this.closedByPeer();
                    return;
                }
                final byte[] conc4 = ByteArrayUtil.conc(new byte[] { 5, 0, 0 }, ByteArrayUtil.copy(this.m_data, 3, n3 - 3));
                super.recv(conc4, 0, conc4.length);
                this.m_data = ByteArrayUtil.copy(this.m_data, n3, this.m_data.length - n3);
                this.m_state = 4;
                if (this.m_data.length > 0) {
                    this.send(this.m_data, this.m_data.length);
                    this.m_data = null;
                }
            }
        }
    }
}
