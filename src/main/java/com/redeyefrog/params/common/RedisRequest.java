package com.redeyefrog.params.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class RedisRequest<Q> implements Serializable {

    @JsonProperty("r_header")
    private RedisHeader header;

    @JsonProperty("rq")
    private Q rq;

}
