package com.qisicheng.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author qinsicheng
 * @Description 内容：
 * @Date 31/01/2022 16:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("users")
public class user {
    private Integer id;
    private String name;
    private String password;
}
