package com.redeyefrog.params.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.redeyefrog.enums.StatusCode;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Data
public class RedisHeader implements Serializable {

    @JsonProperty("txn_seq")
    private String txnSeq;

    @JsonProperty("return_code")
    private String returnCode;

    @JsonProperty("return_desc")
    private String returnDesc;

    public RedisHeader(StatusCode returnCode) {
        this.returnCode = returnCode.getCode();
        this.returnDesc = returnCode.getDesc();
    }

}
