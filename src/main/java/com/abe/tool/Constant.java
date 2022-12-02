package com.abe.tool;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constant {
    public static String ipfsURL;
    public static String eosURL;
    public static String eosUsername;
    public static String eosPrivatekey;
    public static String abeFile;
    @Value("${ipfs.url}")
    public void setIpfsURL(String url) {
        ipfsURL = url;
    }
    @Value("${eos.url}")
    public void setEosURL(String url) {
        eosURL = url;
    }
    @Value("${eos.username}")
    public void setEosUsername(String url) {
        eosUsername = url;
    }
    @Value("${eos.privatekey}")
    public void seteosPrivatekey(String url) {
        eosPrivatekey = url;
    }
    @Value("${abe.file}")
    public void setAbefile(String url) {
        abeFile = url;
    }
}
