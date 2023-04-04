import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.mapper.UserMapper;
import org.example.pojo.User;
import org.example.utils.MybatisUtils;

import java.io.IOException;
import java.io.InputStream;
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

        //增删改后数据库连接要关闭
        sqlSession.close();

    }
}
