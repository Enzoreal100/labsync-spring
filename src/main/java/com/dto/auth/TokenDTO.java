package com.dto.auth;

import java.security.Timestamp;
import java.util.Date;

public class TokenDTO {
    private int sub;
    private int positionId;
    private int labId;
    private Date iat;
    private Date exp;

    public TokenDTO() {
    }

    public TokenDTO(int sub, int positionId, int labId, Date iat, Date exp) {
        this.sub = sub;
        this.positionId = positionId;
        this.labId = labId;
        this.iat = iat;
        this.exp = exp;
    }

    public int getSub() {
        return sub;
    }

    public void setSub(int sub) {
        this.sub = sub;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public int getLabId() {
        return labId;
    }

    public void setLabId(int labId) {
        this.labId = labId;
    }

    public Date getIat() {
        return iat;
    }

    public void setIat(Date iat) {
        this.iat = iat;
    }

    public Date getExp() {
        return exp;
    }

    public void setExp(Date exp) {
        this.exp = exp;
    }
}
