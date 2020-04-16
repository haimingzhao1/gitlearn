package com.zhm.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhm.beans.Person;
import com.zhm.mapper.PersonMapper;
import com.zhm.service.PersonService;
import org.springframework.stereotype.Service;

/**
 * @author zhm
 * @date 2020/4/15 13:04
 */
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements PersonService {
}
