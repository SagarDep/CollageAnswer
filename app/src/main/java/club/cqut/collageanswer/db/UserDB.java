package club.cqut.collageanswer.db;

import com.j256.ormlite.dao.Dao;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.OrmLiteDao;

import java.sql.SQLException;
import java.util.List;

import club.cqut.collageanswer.model.User;

/**
 * userDB
 * Created by fenghao on 2015/6/28.
 */
@EBean(scope= EBean.Scope.Singleton)
public class UserDB {
    @OrmLiteDao(helper = DatabaseHelper.class, model = User.class)
    protected Dao<User, Integer> dao;

    /**
     * 获取用户的个人信息
     * 只存自己的
     * @param id
     * @return
     */
    public User getUser(int id){
        try {
            List<User> users = dao.queryForAll();
            if(users != null && users.size() > 0){
                return users.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 保存用户信息
     * @param user
     */
    public void saveUser(User user){
        try {
            dao.create(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新用户信息
     */

    public void updataUser(User user){
        try {
            dao.createOrUpdate(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
