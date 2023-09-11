package j.vilegeritimen;

import j.vilegeritimen.security.model.User;
import j.vilegeritimen.security.repository.UserRepository;
import j.vilegeritimen.security.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest() // will use H2 database
public class ServiceLayerTest {
    private UserService userService;
    @Autowired
    UserRepository userRepository;

    @BeforeEach // kaldes før hver @Test
    public void setUp()  {
        userService = new UserService(userRepository);
    }

    @Test
    public void testAddUser(){
        User user = new User("ole123","123");
        User savedUser = userService.save(user);
        Assertions.assertNotNull(savedUser);
        Assertions.assertEquals("ole123", savedUser.getUsername());
    }

    @Test
    public void myTest(){
        Assertions.assertNull(null);
    }
}
