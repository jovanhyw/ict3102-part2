package com.example._Zuul_server.Beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class CacheSingleton {

    Logger logger = LoggerFactory.getLogger(CacheSingleton.class);

    private HashMap<String, String> cache;

    public CacheSingleton() {
        cache = new HashMap<>();
        logger.info("CacheSingleton Instatiated.");
    }

    public boolean isCached(String target) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashbytes = digest.digest(target.getBytes());
            String hash = bytesToHex(hashbytes);
            logger.info("cacheSize = " + cache.size());
            logger.info("Hash: " + hash);
            return cache.containsKey(hash);

        } catch (NoSuchAlgorithmException nsae) {
            logger.error(nsae.toString());
            return false;
        }
    }

    public String getCachedResponse(String target) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashbytes = digest.digest(target.getBytes());
            String hash = bytesToHex(hashbytes);
            return cache.get(hash);
        } catch (NoSuchAlgorithmException nsae) {
            logger.error(nsae.toString());
            return null;
        }
    }

    public void cacheResponse(String target, String response) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashbytes = digest.digest(target.getBytes());
            String hash = bytesToHex(hashbytes);
            cache.put(hash, response);
            logger.info("response: " + response);
            logger.info("Cache successful");
            logger.info("cacheSize = " + cache.size());
        } catch (NoSuchAlgorithmException nsae) {
            logger.error("NoSuchAlogorithm");
        }
    }

    private String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
