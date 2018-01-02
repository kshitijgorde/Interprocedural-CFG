// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.network.v;
import java.awt.Event;
import com.diginet.digichat.awt.a6;
import java.awt.Frame;
import com.diginet.digichat.awt.m;
import com.diginet.digichat.awt.ap;
import com.diginet.digichat.awt.cg;
import java.awt.Insets;
import com.diginet.digichat.awt.bj;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import com.diginet.digichat.util.a5;
import com.diginet.digichat.common.k;
import com.esial.util.c;
import com.diginet.digichat.awt.r;
import com.diginet.digichat.awt.ao;
import com.diginet.digichat.awt.ag;

public class InviteBox extends ag implements Runnable
{
    private i iUser;
    private ao aoList;
    private Play plyGame;
    private r btnCancel;
    private r btnClose;
    private r btnInvite;
    
    private int findItem(final int[] array, final int n, final int n2) {
        if (array != null) {
            for (int i = 0; i < n; ++i) {
                if (array[i] == n2) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    public InviteBox(final Play plyGame, final i iUser) {
        super((iUser.y == null) ? null : iUser.y.b(), true);
        this.iUser = iUser;
        this.plyGame = plyGame;
        this.setTitle(a5.a(c.a("Invitations to play to %1."), new String[] { ((k)iUser.games.e(plyGame.nGame)).x() }));
        this.setBackground(this.iUser.cc.c);
        this.setForeground(this.iUser.cc.clrOutText);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
        final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
        final boolean b = true;
        gridBagConstraints3.gridheight = (b ? 1 : 0);
        gridBagConstraints2.gridwidth = (b ? 1 : 0);
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final Panel panel = new Panel(gridBagLayout);
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(this.btnCancel = new r(c.a("Cancel")), gridBagConstraints);
        panel.add(this.btnCancel);
        final Panel panel2 = new Panel(new GridLayout(1, 0, 5, 0));
        panel2.add(this.btnClose = new r(c.a("Close")));
        panel2.add(this.btnInvite = new r(c.a("Invite")));
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(panel2, gridBagConstraints);
        panel.add(panel2);
        final bj bj;
        (bj = new bj()).setLayout(gridBagLayout);
        bj.setBackground(this.iUser.cc.d);
        bj.setForeground(this.iUser.cc.clrInnText);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.insets = new Insets(3, 5, 3, 5);
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(this.aoList = new ao(), gridBagConstraints);
        bj.add(this.aoList);
        this.setLayout(gridBagLayout);
        gridBagLayout.setConstraints(bj, gridBagConstraints);
        this.add(bj);
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 2;
        gridBagLayout.setConstraints(panel, gridBagConstraints);
        this.add(panel);
        final cg cg;
        (cg = new cg(c.a("Invite"), "invite")).d(true);
        final ap ap = new ap(null, "icon");
        final ap ap2 = new ap(c.a("Nickname"), "name");
        cg.b(41);
        ap.d(24);
        ap.c(0);
        ap2.c(true);
        this.aoList.a(true);
        this.aoList.b(cg);
        this.aoList.b(ap);
        this.aoList.b(ap2);
        this.aoList.a(ap2);
        this.aoList.l(24);
        iUser.ab.a(false);
        try {
            synchronized (iUser.ab) {
                for (int i = 0; i < iUser.ab.b(); ++i) {
                    final bd bd = (bd)iUser.ab.d(i);
                    final int w;
                    final boolean b2;
                    final InviteRec inviteRec;
                    this.aoList.d(inviteRec = new InviteRec(bd, (b2 = (this.findItem(plyGame.nPlayers, plyGame.mPlayers, w = bd.w()) >= 0)) || this.findItem(plyGame.nInvites, plyGame.mInvites, w) >= 0));
                    bu.setBackground(this.aoList, inviteRec, bd);
                    this.aoList.a(inviteRec, !b2 && w != iUser.w());
                }
            }
            // monitorexit(iUser.ab)
        }
        finally {
            iUser.ab.a();
        }
        this.pack();
        this.resize(350, 300);
        this.show();
    }
    
    private void messMin() {
        new a6((Frame)this.getParent(), c.a("Note"), a5.a(c.a("You must invite at least %1 player(s)."), new String[] { Integer.toString(this.plyGame.nMin) }), this.iUser).setVisible(true);
    }
    
    public void dispose() {
        final int n;
        if ((n = this.plyGame.mPlayers + this.plyGame.mInvites - 1) <= 0) {
            this.iUser.vecPlays.removeElement(this.plyGame);
            new Thread(this).start();
        }
        else if (n < this.plyGame.nMin) {
            this.messMin();
            return;
        }
        super.dispose();
    }
    
    public void run() {
        try {
            Thread.sleep(10L);
        }
        catch (InterruptedException ex) {}
        this.iUser.getScripts().closeWin(this.plyGame.objTop);
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                if (event.target == this.btnCancel) {
                    this.iUser.closePlay(this.plyGame);
                    this.plyGame.mPlayers = 1;
                    this.plyGame.mInvites = 0;
                    this.dispose();
                    return true;
                }
                if (event.target == this.btnClose) {
                    this.dispose();
                    return true;
                }
                if (event.target != this.btnInvite) {
                    break;
                }
                int n = -1;
                int n2 = 0;
                int n3 = 0;
                int mInvites = 0;
                for (int i = 0; i < this.aoList.e(); ++i) {
                    final InviteRec inviteRec;
                    if ((inviteRec = (InviteRec)this.aoList.j(i)).fInvite) {
                        ++n;
                    }
                    final int w;
                    if ((w = inviteRec.w()) != this.iUser.w() && this.findItem(this.plyGame.nPlayers, this.plyGame.mPlayers, w) < 0) {
                        if (inviteRec.fInvite) {
                            ++n2;
                        }
                        if (inviteRec.fInvite != this.findItem(this.plyGame.nInvites, this.plyGame.mInvites, w) >= 0) {
                            ++n3;
                        }
                    }
                }
                if (n < this.plyGame.nMin) {
                    this.messMin();
                    return true;
                }
                if (n > this.plyGame.nMax) {
                    new a6((Frame)this.getParent(), c.a("Note"), a5.a(c.a("You cannot invite more then %1 player(s)."), new String[] { Integer.toString(this.plyGame.nMax) }), this.iUser).setVisible(true);
                    return true;
                }
                final int[] nInvites = (int[])((n2 > 0) ? new int[this.plyGame.nMax + 1] : null);
                final v v3;
                final v v2;
                final v v = v2 = (v3 = new v(67436801, n3));
                final int n4 = -1;
                v2.j = n4;
                v3.k = n4;
                v.a(-1, this.plyGame.nPlay << 32 | this.plyGame.nGame);
                int j = 0;
                int n5 = 0;
                while (j < this.aoList.e()) {
                    final InviteRec inviteRec2;
                    final int w2;
                    if ((w2 = (inviteRec2 = (InviteRec)this.aoList.j(j)).w()) != this.iUser.w() && this.findItem(this.plyGame.nPlayers, this.plyGame.mPlayers, w2) < 0) {
                        if (inviteRec2.fInvite) {
                            nInvites[n5++] = w2;
                        }
                        final boolean b;
                        if (inviteRec2.fInvite != (b = (this.findItem(this.plyGame.nInvites, this.plyGame.mInvites, w2) >= 0))) {
                            v.a(mInvites, 0, w2);
                            v.a(mInvites++, 63, b);
                        }
                    }
                    ++j;
                }
                if (n3 > 0) {
                    this.iUser.an(v);
                }
                this.plyGame.nInvites = nInvites;
                this.plyGame.mInvites = mInvites;
                this.dispose();
                return true;
            }
        }
        return super.handleEvent(event);
    }
}
