package com.glong.redis_emailcode.controller;

import com.glong.redis_emailcode.common.ResultJson;
import com.glong.redis_emailcode.common.constant.ResultConstant;
import com.glong.redis_emailcode.service.MailService;
import com.glong.redis_emailcode.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("api/email")
public class MailController {
    @Autowired
    private MailService mailService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 发送验证码
     * @param email 邮箱号
     * @return
     */
    @GetMapping("send/{email}")
    public ResultJson sendEmail(@PathVariable String email) {
        //key 邮箱号  value 验证码
        String code = redisTemplate.opsForValue().get(email);
        //从redis获取验证码，如果获取获取到，返回ok
        if (!StringUtils.isEmpty(code)) {
            return ResultJson.success(ResultConstant.SUCCESS_SEND,null);
        }
        //如果从redis获取不到，生成新的6位验证码
        code = RandomUtil.getSixBitRandom();
        //调用service方法，通过邮箱服务进行发送
        boolean isSend = mailService.sendMail(email, code);
        //生成验证码放到redis里面，设置有效时间为5分钟
        if (isSend) {
            redisTemplate.opsForValue().set(email, code, 5, TimeUnit.MINUTES);
            return ResultJson.success(ResultConstant.SUCCESS_SEND,null);
        } else {
            return ResultJson.error(ResultConstant.ERROR_SEND,null);
        }
    }

    /**
     * 验证验证码
     * @param email 邮箱号
     * @param inputCode 输入的验证码
     * @return
     */
    @GetMapping("validate/{email}/{inputCode}")
    public ResultJson validateEmail(@PathVariable String email, @PathVariable String inputCode) {
        //key 邮箱号  value 验证码
        String code = redisTemplate.opsForValue().get(email);
        //从redis获取验证码，如果获取不到到，邮箱号失效
        if (StringUtils.isEmpty(code)) {
            return ResultJson.error("邮箱验证码失效了！", null);
        }
        //比较验证码是否正确
        if(inputCode.equals(code)){
            return ResultJson.success(ResultConstant.SUCCESS_CODE,null);
        }else{
            return ResultJson.error(ResultConstant.ERROR_SEND,null);
        }

    }
}
