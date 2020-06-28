/**
 * @author: LinWeiDa
 * @date: 2020-04-26 11:21
 */
package com.enzenith.test.userinfoapi;

import com.enzenith.common.constants.ResultBean;
import com.enzenith.test.entity.Userinfo;
import com.enzenith.test.service.UserinfoService;
import com.enzenith.utils.util.RandomUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * {阐述类的作用}
 * @author: LinWeiDa
 * @date: 2020-04-26 11:21
 */
@RestController
@RequestMapping(value = "/api/test/v1/users")
@Api(value = "/api/test/v1/users",tags = "测试")
public class UserinfoApi {
    @Autowired
    private UserinfoService userinfoService;
    @ApiOperation("添加用户")
    @ApiImplicitParam(name = "userinfo",value = "用户信息",required = true,dataType = "Userinfo",paramType = "body")
    @RequestMapping(value = "/userinfo",method = RequestMethod.POST)
    public ResultBean<Userinfo> addUser(@RequestBody Userinfo userinfo){
        userinfo.setCreateTime(new Date());
        userinfo.setUserId(RandomUtil.UUID());
        userinfoService.insert(userinfo);
        return ResultBean.build(userinfo);
    }
}
