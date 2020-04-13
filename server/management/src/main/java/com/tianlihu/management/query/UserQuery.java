package com.tianlihu.management.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("用户查询条件")
public class UserQuery extends PageQuery {

    @ApiModelProperty(value = "部门ID", notes = "部门ID")
    private String departmentId;
    @ApiModelProperty(value = "登录名", notes = "登录名")
    private String account;
    @ApiModelProperty(value = "姓名", notes = "姓名")
    private String name;
    @ApiModelProperty(value = "电话", notes = "电话")
    private String phone;
    @ApiModelProperty(value = "管理员(0非管理员, 1管理员)", notes = "管理员(0非管理员, 1管理员)")
    private String admin;
    @ApiModelProperty(value = "最后登录时间", notes = "最后登录时间")
    private String lastLoginTime;
    @ApiModelProperty(value = "登录次数", notes = "登录次数")
    private String loginTimes;
    @ApiModelProperty(value = "状态(0未启用, 1启用)", notes = "状态(0未启用, 1启用)")
    private String status;
}
