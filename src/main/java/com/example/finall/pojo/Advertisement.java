package com.example.finall.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Advertisement {
    //private static final long serialVersionUID = 1L;
    private String ad_name;

    private String ad_text;

    private static final long serialVersionUID = 1L;
    private byte[] longblob_;
    private String longblob;
    //private String longblob;


    public Advertisement(String ad_name, String ad_text, byte[] longblob_) {
        this.ad_name = ad_name;
        this.ad_text = ad_text;
        this.longblob_ = longblob_;
    }
    public Advertisement(String ad_name, String ad_text, String longblob) {
        this.ad_name = ad_name;
        this.ad_text = ad_text;
        this.longblob= longblob;
    }

}
