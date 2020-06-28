/**
 * @author: ZhangRenHuo
 * @date: 2019-12-09 10:38
 */
package com.enzenith.common.sms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author: ZhangRenHuo
 * @date: 2019-12-09 10:38
 */
@Component
public class SmsConfig {

    @Value(value = "${sms.index:1}")
    private Integer index;

    @Value(value = "${sms.accessKeyID:0A5309CE7F8190EE510D553B4FBEEBB6}")
    private String accessKeyID; //accessKeyId

    @Value(value = "${sms.secretAccessKey:F6023EF6491BE389B84AB0046AD78675}")
    private String secretAccessKey; //secretAccessKey

    @Value(value = "${sms.singId:qm_712036e7fb3b4c6ba77125a3ce2937b2}")
    private String singId; //短信签名Id

    public String getAccessKeyID() {
        return accessKeyID;
    }

    public void setAccessKeyID(String accessKeyID) {
        this.accessKeyID = accessKeyID;
    }

    public String getSecretAccessKey() {
        return secretAccessKey;
    }

    public void setSecretAccessKey(String secretAccessKey) {
        this.secretAccessKey = secretAccessKey;
    }

    public String getSingId() {
        return singId;
    }

    public void setSingId(String singId) {
        this.singId = singId;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
