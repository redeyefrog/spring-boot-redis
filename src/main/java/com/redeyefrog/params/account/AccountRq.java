package com.redeyefrog.params.account;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class AccountRq {

    private String id;

    private String name;

    private String mobile;

}
