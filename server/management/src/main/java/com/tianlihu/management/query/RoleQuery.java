package com.tianlihu.management.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("角色查询条件")
public class RoleQuery extends PageQuery {

    @ApiModelProperty(value = "名称", notes = "名称")
    private String name;
}
