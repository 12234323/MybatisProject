import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.mapper.UserMapper;
import org.example.mapper.UserMapperImpl;
import org.example.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyTest {
    @Test
    public void test() {
        ApplicationContext context= new ClassPathXmlApplicationContext("spring-dao.xml");
        UserMapper userMapperImpl = context.getBean("userMapper", UserMapper.class);
        userMapperImpl.insertUser(new User(6,"黄宏涛","fsdfas"));
    }
}
