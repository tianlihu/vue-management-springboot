package com.tianlihu.management.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("职位查询条件")
public class PositionQuery extends PageQuery {

    @ApiModelProperty(value = "名称", notes = "名称")
    private String name;
    @ApiModelProperty(value = "编码", notes = "编码")
    private String code;
    @ApiModelProperty(value = "状态(0启用, 1停用)", notes = "状态(0启用, 1停用)")
    private String status;
}
