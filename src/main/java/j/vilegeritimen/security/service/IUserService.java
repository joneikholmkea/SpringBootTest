package j.security.service;

import j.security.model.User;

import java.util.List;

public interface IUserService extends ICrudService<User,Long>{
    List<User> findByName(String name);
}
