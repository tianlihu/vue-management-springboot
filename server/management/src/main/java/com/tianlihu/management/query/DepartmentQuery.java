package com.tianlihu.management.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("部门查询条件")
public class DepartmentQuery extends PageQuery {

    @ApiModelProperty(value = "简称", notes = "简称")
    private String shortName;
    @ApiModelProperty(value = "全称", notes = "全称")
    private String name;
}
