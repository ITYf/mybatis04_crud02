package top.ityf.dao;

import top.ityf.domain.User;

import java.io.InputStream;
import java.util.List;

/**
 * ClassName:UserDao
 * Package: top.ityf.dao
 * Description: 用户的持久层接口
 *
 * @Date: 2019/12/24 16:34
 * @Author: YanFei
 */
public interface UserDao {
    /**
     * 查询所有的操作
     * */
    List<User> findAll();

    /**
     * 保存用户
     * */
    void saveUser(User user);

    /**
     * 更新用户
     * */
    void updateUser(User user);

    /**
     * 根据Id删除用户
     * */
    void deleteUser(Integer userId);


    /**
     * 根据Id查询用户信息
     * */
    User findById(Integer userId);


    /**
     *根据名称模糊查询用户信息
     * */
    List<User> findByName(String uname);

    /**
     * 查询用户总数
     * */
    int findTotal();



    /**
     *
     * */
}
