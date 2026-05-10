package com.visa.demo.models;

import com.nojpa.bd.entity.Entity;

public class Information extends Entity<Information> {

    private byte[] pdp;
    private byte[] signature;

    public Information() {
        setNomTable("information");
    }

    public byte[] getPdp() {
        return pdp;
    }

    public void setPdp(byte[] pdp) {
        this.pdp = pdp;
    }

    public byte[] getSignature() {
        return signature;
    }

    public void setSignature(byte[] signature) {
        this.signature = signature;
    }

}
