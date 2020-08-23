数据解析

   主要用于对t和z两种的历史数据进行分类和总结，方便后期学习，目前在数据分析这块还不是很懂
   对外提供接口方便后期做成android端

一.框架
 springboot+mybatis


二.遇到问题
1. 实体类有构造函数mybatis报错
需要加如下：
<resultMap id="BaseResultMap" type="com.ming.data.entity.Number">
<result column="year" property="year" jdbcType="VARCHAR" />
<result column="periods" property="periods" jdbcType="VARCHAR" />
<result column="number" property="number" jdbcType="INTEGER" />
</resultMap>
另外需要把实体类的无参构造函数也写出来

2. List<Integer> b  =Arrays.asList(2, 14, 26, 38);
这样实例化的类没有remove这个方法，需要转换为list
   
   
   
三。需求分析

1. 午半买卖排序
2. 筛选功能

Excel操作
 http://easypoi.mydoc.io/#text_186900
