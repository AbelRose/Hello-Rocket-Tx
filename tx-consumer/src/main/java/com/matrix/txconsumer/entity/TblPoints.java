package com.matrix.txconsumer.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
* @author yihaosun
* @date 2022/6/30 22:17
*/
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TblPoints implements Serializable {
    /**
    * 主键
    */
    private Long id;

    private Long userId;

    private String orderNo;

    private Integer points;

    private String remarks;

    private static final long serialVersionUID = 1L;
}