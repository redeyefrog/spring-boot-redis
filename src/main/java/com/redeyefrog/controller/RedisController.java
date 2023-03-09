package com.redeyefrog.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.redeyefrog.enums.ConstantCode;
import com.redeyefrog.enums.StatusCode;
import com.redeyefrog.factory.RedisFactory;
import com.redeyefrog.params.account.AccountRq;
import com.redeyefrog.params.account.AccountRs;
import com.redeyefrog.params.common.RedisHeader;
import com.redeyefrog.params.common.RedisRequest;
import com.redeyefrog.params.common.RedisResponse;
import com.redeyefrog.params.company.CompanyRq;
import com.redeyefrog.params.company.CompanyRs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/v1")
@RestController
public class RedisController {

    @Autowired
    private RedisFactory factory;

    @PostMapping("/addAccount2Redis")
    public RedisResponse<AccountRs> addAccount(@RequestBody RedisRequest<AccountRq> accountRequest) {
        RedisResponse<AccountRs> response = new RedisResponse<>();

        long count  = factory.del();
        try {
            log.info("accountRequest: {}", accountRequest);

//            factory.set(ConstantCode.ACCOUNT.name(), accountRequest);
//            log.info("ACCOUNT: {}", factory.get(ConstantCode.ACCOUNT.name(), new TypeReference<RedisRequest<AccountRq>>() {
//            }));

//            Map<String, Object> params = JsonUtils.convert(accountRequest, Map.class);
//            factory.hset(ConstantCode.ACCOUNT.name(), params);
//            log.info("ACCOUNT MAP: {}", factory.hget(ConstantCode.ACCOUNT.name(), new TypeReference<RedisRequest<AccountRq>>() {
//            }));

            response.setHeader(new RedisHeader(StatusCode.SUCCESS));
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage(), e);
            response.setHeader(new RedisHeader(StatusCode.UNKNOWN));
        }

        return response;
    }

    @PostMapping("/addCompany2Redis")
    public RedisResponse addCompany(@RequestBody RedisRequest<CompanyRq> companyRequest) {
        RedisResponse<CompanyRs> response = new RedisResponse<>();

        try {
            log.info("companyRequest: {}", companyRequest);

            factory.set(ConstantCode.COMPANY.name(), companyRequest);
            log.info("COMPANY: {}", factory.get(ConstantCode.COMPANY.name(), new TypeReference<RedisRequest<CompanyRq>>() {
            }));

//            Map<String, Object> params = JsonUtils.convert(rq, Map.class);
//            factory.hmset(ConstantCode.COMPANY.name(), params);
//            log.info("ACCOUNT MAP: {}", factory.hget(ConstantCode.COMPANY.name(), RedisRequest.class));

            response.setHeader(new RedisHeader(StatusCode.SUCCESS));
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage(), e);
            response.setHeader(new RedisHeader(StatusCode.UNKNOWN));
        }

        return response;
    }

}
