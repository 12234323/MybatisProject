import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.mapper.StudentMapper;
import org.example.mapper.UserMapper;
import org.example.pojo.Student;
import org.example.pojo.Teacher;
import org.example.pojo.User;
import org.example.utils.MybatisUtils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyTest {
    public static void main(String[] args) throws IOException {
        /*//获取数据库连接
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
        //获取sqlsession对象,开启true 表示自动提价事务，增删改，如果没有提及事务，结果不会改变
        SqlSession sqlSession = sessionFactory.openSession(true);
*/
        //通过工具类获取数据库连接
        SqlSession sqlSession= MybatisUtils.getSqlSession();

        //注意getMapper获取的是接口，不是实现类
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList=mapper.queryAll();
        System.out.println("查询所有用户");
        for (User user:userList)
            System.out.println(user);
        System.out.println("================================");

        System.out.println("采用万能Map修改密码，不用每次传入User对象");
        Map<String, Object> map=new HashMap<String,Object>();
        map.put("password","huanghongtao");
        map.put("id",1);
        int i = mapper.updatePwd(map);
        if (i>0)
            System.out.println("修改成功");
        System.out.println("================================");

        System.out.println("模糊查询,查询所有name以明结尾的数据");
        List<User> userList1 = mapper.queryUserByLike("明");
        for (User user:userList1)
            System.out.println(user);
        System.out.println("================================");

        System.out.println("当实体类和数据库对应字段不一致会输出null");
        System.out.println("User(id=1, name=小明, password=null)数据库中字段为pwd，user类获取不到，为null");
        System.out.println("采用ResultMap解决");
        List<User> userList2=mapper.queryAll();
        System.out.println("查询所有用户");
        for (User user:userList2)
            System.out.println(user);

        System.out.println("================================");
        System.out.println("分页");
        Map<String,Object> map1=new HashMap<String, Object>();
        map1.put("start",1);
        map1.put("pagesize",2);
        //分页 start表示从哪个开始，默认是0，end表示每夜显示几个
        List<User> userList3 = mapper.queryUserByLimit(map1);
        for (User user:userList3)
            System.out.println(user);

        System.out.println("================================");
        System.out.println("多对一：多个学生对一个老师，association");
        StudentMapper mapper1 = sqlSession.getMapper(StudentMapper.class);
        //List<Student> studentList = mapper1.queryAll();
        //for (Student student:studentList)
            //System.out.println(student);
/* 输出结果：Student{id=1, name='黄宏涛', teacher=Teacher(id=1, name=教员)}
        Student{id=2, name='刘亦菲', teacher=Teacher(id=1, name=教员)}*/

        System.out.println("================================");
        System.out.println("一对多：一个老师对多个学生association");
        for (Teacher teacher : mapper1.queryAllTeacher()) {
            System.out.println(teacher);
        }
/*        一对多：一个老师对多个学生association
        Teacher{id=1, name='教员', students=[Student{id=1, name='黄宏涛', t_id=0}]}
        Teacher{id=2, name='教员', students=[Student{id=2, name='刘亦菲', t_id=0}]}*/

        System.out.println("================================");
        System.out.println("动态SQL");
        //使用动态SQL语句
        List<User> userList4 = mapper.queryAllByDynamicSql(3);
        for (User user:userList4)
            System.out.println(user);

        System.out.println("================================");
        System.out.println("动态SQL,choose,when,otherwise");
        Map<String ,Object> map2=new HashMap<String, Object>();
        map2.put("id",1);
        map2.put("name","黄宏涛");
        map2.put("tId",1);
        User user1 = mapper.queryAllByDynamicSql1(map2);
        //使用动态SQL语句
        System.out.println(user1);

        System.out.println("================================");
        System.out.println("动态SQL,foreach");
        List<Integer> idlist=new ArrayList<Integer>();
        idlist.add(1);
        idlist.add(2);
        idlist.add(3);
        for (User user : mapper.queryAllByDynamicSql3(idlist)) {
            System.out.println(user);
        }

        System.out.println("================================");
        System.out.println("一级缓存，默认开启");
        System.out.println("前面已经查询过该方法，若两次查询间没有增删改操作，再次查询时，直接从缓存中获取");
        System.out.println("sqlSession.clearCache();可以用该语句清除缓存，则会再次查询");
        for (User user : mapper.queryAll()) {
            System.out.println(user);
        }

        System.out.println("================================");
        sqlSession.close();
        System.out.println("关闭之前的会话sqlsession");
        System.out.println("================================");
        System.out.println("二级缓存，需要在Mapper文件中添加配置<cache/>，不是配置文件");
        System.out.println("开启新的sqlsession");
        System.out.println("二级缓存必须是同一个mapper映射文件下的查询方法才可以成功使用缓存");
        System.out.println("映射文件中，对于经常更新的select标签，可以用useCache=False关闭缓存");
        SqlSession sqlSession1= MybatisUtils.getSqlSession();
        System.out.println("开启新的sqlsession");
        UserMapper mapper2 = sqlSession1.getMapper(UserMapper.class);
        List<User> userList5 = mapper2.queryAll();
        for (User user:userList5)
            System.out.println(user);

        sqlSession.close();

        //增删改后数据库连接要关闭


    }
}
