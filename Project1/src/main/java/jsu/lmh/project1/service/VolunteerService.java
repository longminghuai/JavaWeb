package jsu.lmh.project1.service;

import jsu.lmh.project1.entity.PageBean;
import jsu.lmh.project1.entity.Volunteer;
import jsu.lmh.project1.entity.college_major;
import jsu.lmh.project1.mapper.VolunteerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VolunteerService {
    @Autowired
    VolunteerMapper volunteerMapper;
    //录取概率在30%-80%,%55-%65是稳得
    public PageBean query_volunteer_intelligent(Integer page, Integer pagesize, String level1Name, String level2Name, String level3Name, String spname, String collegeName, String province, String city, String typeName, String natureName, Integer dualClassName, Integer f985, Integer f211, Integer rank, String fk, Integer type) {
        System.out.println("f211"+f211);
        int minn = 0,maxn=0;
        if(type==null){
            //先计算出最低位次的区间
            minn= (int) (5*rank/7);
            maxn= (int) (2.5*rank);
        }
        else{
            if(type==1){
                //冲30%-55%
                minn= (int) (5*rank/7);
                maxn=(int)(10*rank/9);
            }
            else if(type==2){
//                55%-65%
                //稳
                minn=(int) (10*rank/9);
                maxn=(int)(10*rank/7);
            }
            else if(type==3){
                //保  65%-80%
                minn=(int) (10*rank/7);
                maxn=(int)(2.5*rank);
            }
        }
        int startindex=(page-1)*pagesize;
        List<college_major> list=volunteerMapper.query_volunteer_intelligent(startindex, pagesize, level1Name, level2Name, level3Name, spname, collegeName,
                province, city, typeName, natureName, dualClassName, f985, f211,minn,maxn, fk);
        Long num=volunteerMapper.selectcount(level1Name, level2Name, level3Name, spname, collegeName,
                province, city, typeName, natureName, dualClassName, f985, f211, minn,maxn, fk);
        System.out.println(list);
        List<college_major> ans=new ArrayList<>();
        for(college_major x:list){
            int z;
            if(x.getRank()==0){
                continue;
            }
            if(x.getRank()<rank){
                int c=rank-x.getRank();
                z=50-c*50/x.getRank();
            }
            else{
                int c=x.getRank()-rank;
                z=50+c*50/x.getRank();
            }
            x.setProbability(z);
            ans.add(x);
        }
        PageBean pageBean = new PageBean();
        pageBean.setRows(ans);
        pageBean.setTotal(num);
        System.out.println(ans);
        return pageBean;
    }

    public List<Volunteer> querymyvolunteer(Long id) {
        return volunteerMapper.querymyvolunteer(id);
    }

    public void deletevolunteer(Long id, Long user_volunteerid) {
        volunteerMapper.deletevolunteer(id,user_volunteerid);
    }
    public void insertvolunteer(Volunteer volunteer, Long userid) {
        System.out.println("skkkskll");
        System.out.println(volunteer);
        volunteerMapper.insertvolunteer(volunteer,userid);
    }

    public List<college_major> getquery_volunteer_intelligent(String spname, String college_name, String province, Integer rank, String fk, Integer type) {
        int minn = 0,maxn=0;
        if(type==null){
            //先计算出最低位次的区间
            minn= (int) (5*rank/7);
            maxn= (int) (2.5*rank);
        }
        else{
            if(type==1){
                //冲30%-55%
                minn= (int) (5*rank/7);
                maxn=(int)(10*rank/9);
            }
            else if(type==2){
//                55%-65%
                //稳
                minn=(int) (10*rank/9);
                maxn=(int)(10*rank/7);
            }
            else if(type==3){
                //保  65%-80%
                minn=(int) (10*rank/7);
                maxn=(int)(2.5*rank);
            }
        }
        List<college_major> list=volunteerMapper.getquery_volunteer_intelligent(spname, college_name,
                province,minn,maxn, fk);
        List<college_major> ans=new ArrayList<>();
        for(college_major x:list){
            int z;
            if(x.getRank()==0){
                continue;
            }
            if(x.getRank()<rank){
                int c=rank-x.getRank();
                z=50-c*50/x.getRank();
            }
            else{
                int c=x.getRank()-rank;
                z=50+c*50/x.getRank();
            }
            x.setProbability(z);
            ans.add(x);
        }
        return ans;
    }
}
