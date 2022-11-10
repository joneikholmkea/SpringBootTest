package j.vilegeritimen.security.service;

import j.vilegeritimen.security.model.User;

import java.util.List;

public interface IUserService extends ICrudService<User,Long>{
    List<User> findByName(String name);
}
