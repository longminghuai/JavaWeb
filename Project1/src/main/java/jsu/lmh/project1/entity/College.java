package jsu.lmh.project1.entity;

public class College {
    public Long id;//学校id
    public String name;//学校名称
    public Boolean isd;//是否为双一流
    public Boolean is985;//是否985
    public Boolean is211;//是否为211
    public String Ctype;//院校类型
    public String type;//办学类型

    //院校地址
    public Location location;
}
