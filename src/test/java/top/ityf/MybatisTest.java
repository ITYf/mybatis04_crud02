package top.ityf;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import top.ityf.dao.UserDao;
import top.ityf.domain.User;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * ClassName:MybatisTest
 * Package: top.ityf
 * Description:
 *
 * @Date: 2019/12/24 16:52
 * @Author: YanFei
 */
public class MybatisTest {
    private InputStream in;
    private SqlSession sqlSession;
    private UserDao userDao;

    @Before
    public void init() throws Exception {
        //1、读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2、创建一个SqlSessionFactory工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);

        //3、使用工厂生产一个SqlSession对象
        sqlSession = factory.openSession();

        //4、使用SqlSession创建UserDao接口的代理对象
        userDao = sqlSession.getMapper(UserDao.class);
    }

    @After
    public void destory() throws Exception {
        sqlSession.commit();
        //释放资源
        sqlSession.close();
        in.close();
    }

    /**
     * 测试查询所有操作
     */
    @Test
    public void testFindAll() {
        List<User> users = userDao.findAll();
        for (User user : users)
            System.out.println(user);
    }

    /**
     * 测试保存用户
     */
    @Test
    public void testSaveUser() {
        User user = new User();
        user.setUserName("parameterMap User");
        user.setUserAddress("昆明市官渡区");
        user.setUserSex("男");
        user.setUserBirthday(new Date());
        System.out.println("保存操作之前：" + user);
        userDao.saveUser(user);
        System.out.println("保存操作之后：" + user);
    }

    /**
     * 测试更新用户
     */
    @Test
    public void testUpdateUser() {
        User user = new User();
        //这里必须要设置id，否则将会更新整张表
        user.setUserId(36);
        user.setUserName("五竹");
        user.setUserAddress("儋州市洪山区");
        user.setUserSex("男");
        user.setUserBirthday(new Date());

        userDao.updateUser(user);
    }


    /**
     * 测试根据Id删除用户
     */
    @Test
    public void testDelete() {
        userDao.deleteUser(37);
    }

    /**
     * 测试根据Id查询用户
     */
    @Test
    public void testFindOne() {
        User user = userDao.findById(36);
        System.out.println(user);
    }

    /**
     * 根据用户名模糊查询
     */
    @Test
    public void testFindByName() {
        List<User> users = userDao.findByName("%小%");
        for (User user : users)
            System.out.println(user);
    }

    /**
     * 测试查询总记录条数
     * */
    @Test
    public void testFindTotal(){
        int count = userDao.findTotal();
        System.out.println(count);
    }
}
