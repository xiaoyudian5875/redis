package com.hzit.redis.bean;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
/*@Getter  //get
@Setter  //set
@EqualsAndHashCode //equals 和 hashcode
@NoArgsConstructor //无参构造
@AllArgsConstructor //所有参数构造 */
@Accessors(chain = true) //设置链式写法
public class User {
    private Integer id;
    private  String name;
    private Integer age;
    private  String pwd;

    /**
     * 测试data注解
     * @param args
     */
    public static void main(String[] args) {
        User user = new User();
        //链式写法
        user.setAge(12).setPwd("123").setName("zhagsan").setId(1001);
        System.err.println(user);

    }
}
