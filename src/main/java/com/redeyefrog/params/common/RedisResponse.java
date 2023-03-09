package com.redeyefrog.params.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class RedisResponse<S> implements Serializable {

    @JsonProperty("r_header")
    private RedisHeader header;

    @JsonProperty("rs")
    private S rs;

}
