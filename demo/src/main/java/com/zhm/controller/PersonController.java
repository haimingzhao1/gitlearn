package com.zhm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlLike;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhm.beans.Person;
import com.zhm.mapper.PersonMapper;
import com.zhm.service.PersonService;
import com.zhm.utils.TypeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static java.util.stream.Collectors.toList;

/**
 * @author zhm
 * @date 2020/4/15 13:06
 */
@RestController
public class PersonController {
    @Autowired
    PersonService personService;
    @Autowired
    PersonMapper personMapper;
    /**
     * 查询列表信息
     *
     * @param searchcondition 查询条件
     * @param searchcontent   查询内容
     * @param page            页数
     * @param rows            每页记录数
     * @return
     */
    @RequestMapping("/list")
    public Map<String,Object> list(@RequestParam(value = "searchcondition", required = false) String searchcondition,
                             @RequestParam(value = "searchcontent", required = false) String searchcontent,
                             @RequestParam(value = "page", required = false) Integer page,
                             @RequestParam(value = "rows", required = false) Integer rows) {
        Map<String, Object> map = new HashMap<>();
        Page<Person> personPage = new Page<>(page,rows);
        IPage<Person> personIPage = new Page<>();
        if (searchcondition!=null && !searchcondition.isEmpty() && searchcontent !=null
        && !searchcontent.isEmpty()){
            if (searchcondition.equals("personname")){
                personIPage = personMapper.selectPage(personPage,new QueryWrapper<Person>().like(true,"personname",searchcontent));
            }
            else if (searchcondition.equals("personage")){
                if (TypeUtil.isNum(searchcontent) || TypeUtil.isNumEx(searchcontent))
                personIPage = personMapper.selectPage(personPage,new QueryWrapper<Person>().eq("personage",
                        Integer.parseInt(searchcontent)));
            }
        }
        else{
            personIPage = personMapper.selectPage(personPage,new QueryWrapper<Person>().orderByAsc("personname"));
        }
        map.put("total", personIPage.getTotal());
        List<Person> personList= personIPage.getRecords();
        List sorted = sortBySearchCondition(searchcondition,personList);
        map.put("rows",sorted.isEmpty() ? personList:sorted);
        return map;
    }

    @PostMapping("/person")
    public Map<String,Object> addOrUpdatePerson(@RequestBody Person person,@RequestParam(required = false) Integer personid){
        if (personid!=null){
            person.setPersonid(personid);
        }
        Map<String, Object> map = new HashMap<>();
        boolean status = personService.saveOrUpdate(person);
        map.put("status", status);
        return map;
    }
    @DeleteMapping("person")
    public Map<String,Object> deletePerson(@RequestBody List<Integer> ids){
        boolean status = false;
        Map<String, Object> map = new HashMap<>();
        if (ids.size()==1){
            status = personService.removeById(ids.get(0));
        }else {
            status = personService.removeByIds(ids);
        }
        map.put("status", status);
        return map;
    }
	/*
		该排序为若按名字检索则根据年龄字典排序，若按年龄检索则
		根据名字字典排序，进排序当前页
	*/
    private List sortBySearchCondition(String searchcondition, List<Person> list) {
        List<Person> sortedList = new ArrayList<>();
        if (searchcondition!=null&&searchcondition.equals("personage")){
            sortedList = list.stream().sorted((person1, person2) -> person1.getPersonname().compareTo(person2.getPersonname())).collect(toList());
        }
        else if (searchcondition!=null&&searchcondition.equals("personname")){
            sortedList = list.stream().sorted(Comparator.comparingInt(Person::getPersonage)).collect(toList());
        }
        return sortedList;
    }
}
