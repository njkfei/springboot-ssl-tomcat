package com.njp.learn.ssl.utils;

/**
 * Created by njp on 2016/5/19.
 */


import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

import java.net.URL;
import java.nio.charset.Charset;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class HttpsTest {

    public static void main(String[] args) {
        new HttpsTest().doMain();
        log("DONE");
    }

    public void doMain() {
        String hsUrl = "https://hollysys.xyz:8443";
        URL url ;

        try {
            url = new URL(hsUrl);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

            X509TrustManager xtm = new X509TrustManager() {
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    // TODO Auto-generated method stub
                    return null;
                }

                @Override
                public void checkServerTrusted(X509Certificate[] arg0, String arg1)
                        throws CertificateException {
                    // TODO Auto-generated method stub

                }

                @Override
                public void checkClientTrusted(X509Certificate[] arg0, String arg1)
                        throws CertificateException {
                    // TODO Auto-generated method stub

                }
            };

            TrustManager[] tm = { xtm };

            SSLContext ctx = SSLContext.getInstance("TLS");
            ctx.init(null, tm, null);

            con.setSSLSocketFactory(ctx.getSocketFactory());
            con.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });



            log(con.getResponseCode());
            log(con.getCipherSuite());
            log(con.getContent());
            log(IOUtils.toString(con.getInputStream(), Charsets.UTF_8));
            log("");
            Certificate[] certs = con.getServerCertificates();

            int certNum = 1;

            for(Certificate cert : certs) {
                X509Certificate xcert = (X509Certificate) cert;
                log("Cert No. " + certNum ++);
                log(xcert.getType());
                log(xcert.getPublicKey().getAlgorithm());
                log(xcert.getIssuerDN());
                log(xcert.getIssuerDN());
                log(xcert.getNotAfter());
                log(xcert.getNotBefore());
                log("");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static void log(Object o) {
        System.out.println(o);
    }

}