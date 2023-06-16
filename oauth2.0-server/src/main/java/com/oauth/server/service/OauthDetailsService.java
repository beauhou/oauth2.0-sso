package com.oauth.server.service;

import com.oauth.server.model.OauthDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BeauHou
 */
@Service
public class OauthDetailsService {

    private List<OauthDetails> oauthDetailsList = new ArrayList<>();

    {
        OauthDetails oauthDetails = new OauthDetails().setAppCode("demo")
                .setAppCode("demo").setAppSecret("demo");
        oauthDetailsList.add(oauthDetails);
    }

    /**
     * 验证应用编码是否存在
     *
     * @param appCode 应用编码
     * @return true-应用编码存在，false-应用编码不存在
     */
    public boolean appIdIsExist(String appCode) {
        return oauthDetailsList.stream().filter(val -> val.getAppCode().equals(appCode))
                .count() > 0;
    }


    /**
     * 验证app的编码和秘钥是否存在
     *
     * @param appCode   应用编码
     * @param appSecret 应用秘钥
     * @return true-应用编码和秘钥正确  false-编码与秘钥不正确
     */
    public boolean verifyAppCodeAndAppSecret(String appCode, String appSecret) {
        return oauthDetailsList.stream().filter(val -> val.getAppCode().equals(appCode)
                        && val.getAppSecret().equals(appSecret))
                .count() > 0;
    }
}
