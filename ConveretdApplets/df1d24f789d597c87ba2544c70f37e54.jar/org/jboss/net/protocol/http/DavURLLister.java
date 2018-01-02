// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.net.protocol.http;

import org.apache.commons.httpclient.HttpURL;
import java.util.List;
import java.net.MalformedURLException;
import org.apache.commons.httpclient.HttpException;
import java.util.ArrayList;
import org.apache.webdav.lib.WebdavResource;
import java.io.IOException;
import java.util.Collection;
import org.jboss.net.protocol.URLLister;
import java.net.URL;
import org.jboss.net.protocol.URLListerBase;

public class DavURLLister extends URLListerBase
{
    public Collection listMembers(final URL baseUrl, final URLLister.URLFilter filter) throws IOException {
        return this.listMembers(baseUrl, filter, false);
    }
    
    public Collection listMembers(final URL baseUrl, final URLLister.URLFilter filter, final boolean scanNonDottedSubDirs) throws IOException {
        WebdavResource resource = null;
        try {
            resource = new WebdavResource(baseUrl.toString());
            final WebdavResource[] resources = resource.listWebdavResources();
            final List urls = new ArrayList(resources.length);
            for (int i = 0; i < resources.length; ++i) {
                final WebdavResource member = resources[i];
                final HttpURL httpURL = member.getHttpURL();
                if (filter.accept(baseUrl, httpURL.getName())) {
                    String uri = httpURL.getURI();
                    if (member.isCollection()) {
                        if (!uri.endsWith("/")) {
                            uri += "/";
                        }
                        final String path = httpURL.getPath();
                        if (scanNonDottedSubDirs && getFilePartFromUrl(path).indexOf(".") == -1) {
                            final URL subUrl = new URL(uri);
                            urls.addAll(this.listMembers(subUrl, filter, scanNonDottedSubDirs));
                        }
                        else {
                            urls.add(new URL(uri));
                        }
                    }
                    else {
                        urls.add(new URL(uri));
                    }
                }
            }
            return urls;
        }
        catch (HttpException e) {
            throw new IOException(e.getMessage());
        }
        catch (MalformedURLException e2) {
            throw new IllegalStateException(e2.getMessage());
        }
        finally {
            if (resource != null) {
                resource.close();
            }
        }
    }
    
    protected static final String getFilePartFromUrl(final String name) {
        final int length = name.length();
        if (name.charAt(length - 1) == '/') {
            final int start = name.lastIndexOf("/", length - 2);
            return name.substring(start, length - 2);
        }
        final int start = name.lastIndexOf("/");
        return name.substring(start);
    }
}
