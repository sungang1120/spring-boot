package org.examples.spring.domain.manytomany.jointable.unidirectional;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * Description : TODO()
 * User: h819
 * Date: 2014/11/18
 * Time: 16:55
 * To change this template use File | Settings | File Templates.
 */
//@Entity
//@Table(name = "example_teacher1")
//@NamedEntityGraph(name = "examples.entity.manytomany.TeacherEntity1",//唯一id ,jpa 2.1属性
//        attributeNodes = {@NamedAttributeNode("students")})
@Getter
@Setter
@AllArgsConstructor
public class TeacherEntity2 {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    /**
     * 单向，发出端
     * 单向多对多，只在发出方设置，接收方不做设置，接收端不能操作关系表
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "example_ref_teacher_student1", //指定关联表名
            joinColumns = {@JoinColumn(name = "teacher_id", referencedColumnName = "id")},////生成的中间表的字段，对应关系的发出端(主表) id
            inverseJoinColumns = {@JoinColumn(name = "student_id", referencedColumnName = "id")}, //生成的中间表的字段，对应关系的接收端(从表) id
            uniqueConstraints = {@UniqueConstraint(columnNames = {"teacher_id", "student_id"})}) // 唯一性约束，是从表的联合字段
    private Set<StudentEntity2> students = Sets.newHashSet(); //set 可以过滤重复元素


    public TeacherEntity2() {

    }

    public TeacherEntity2(String name) {

        this.name = name;
    }

    /**
     * 增加几个增删关联关系表中数据的方法
     *
     * 1. 双向都是发出端的，双方都可以维护关联关系表中数据
     *
     * 2. 单向是发出端的，仅发出端可以维护关联关系表中数据
     * */

    /**
     * add , remove , clear 方法同 many to many TeacherEntity1
     */

}
