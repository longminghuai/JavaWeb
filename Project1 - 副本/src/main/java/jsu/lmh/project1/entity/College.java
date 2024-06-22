package jsu.lmh.project1.entity;

import lombok.Data;

@Data
public class College {
    public Long id;//学校id
    public String name;//学校名称
    public Integer dual_class_name;//是否为双一流
    public int f985;//是否985
    public int f211;//是否为211
    public String type_name;//院校类型
    public String nature_name;//办学类型
    public String level_name;//院校层次
    //院校地址
    public Location location;
    public String logo;//学校的logo
    public String address;//具体地址
    public String email;//电子邮件
    public String site;//官网
    public String phone;//招生电话
    public String content;//学校简介
}
