package com.visa.demo.utils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class NetworkUtils {
    public static String getLocalIpAddress() {

        try {

            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

            while (interfaces.hasMoreElements()) {

                NetworkInterface ni = interfaces.nextElement();

                if (ni.isLoopback() || !ni.isUp()) {
                    continue;
                }

                Enumeration<InetAddress> addresses = ni.getInetAddresses();

                while (addresses.hasMoreElements()) {

                    InetAddress addr = addresses.nextElement();

                    if (addr instanceof Inet4Address
                            && addr.isSiteLocalAddress()) {

                        return addr.getHostAddress();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "127.0.0.1";
    }
}
