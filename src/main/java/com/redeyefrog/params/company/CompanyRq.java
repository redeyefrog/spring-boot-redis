package com.redeyefrog.params.company;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CompanyRq {

    private String id;

    private String name;

    private String tel;

}
