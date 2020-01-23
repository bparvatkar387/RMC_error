package com.example.rmc;

import android.renderscript.Allocation;

public class AllDiseasesAdapter {
    String productTitle, causesName, sympName;

    public AllDiseasesAdapter(String productTitle, String causesName, String sympName) {
        this.productTitle = productTitle;
        this.causesName = causesName;
        this.sympName = sympName;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getCausesName() {
        return causesName;
    }

    public void setCausesName(String causesName) {
        this.causesName = causesName;
    }

    public String getSympName() {
        return sympName;
    }

    public void setSympName(String sympName) {
        this.sympName = sympName;
    }
}
