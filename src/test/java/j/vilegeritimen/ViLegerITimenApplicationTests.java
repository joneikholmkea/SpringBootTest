package j.vilegeritimen;

import j.vilegeritimen.security.repository.UserRepository;
import j.vilegeritimen.security.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DataJpaTest() // bruger H2 automatisk
class ViLegerITimenApplicationTests {
    private MockMvc mockMvc;
    private UserService userService;
    @Autowired
    UserRepository userRepository;

    @BeforeEach
    public void setUp()  {
        userService = new UserService(userRepository);
        mockMvc = MockMvcBuilders.standaloneSetup(new LegeKontroller(userService)).build();
    }

//    @Test
//    public void index() throws Exception {
//        mockMvc.perform(get("/"))
//                .andExpect(status().isOk())  // http 200 kode for "ok"
//                .andExpect(view().name("index"));
//    }

    @Test
    public void addUser() throws Exception {
//        mockMvc.perform(post("/addUser")
//                .param("id", "123")
//                .param("username", "anna")
//                .param("password", "444"))
//                .andExpect(status().isOk())
//                .andDo(print());

        mockMvc.perform(post("/addUser") // integration test
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {"id":"1",
                     "username":"anna",
                     "password": "123"
                    }
                """)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());


// .content("{\"username\" : \"anna\"}")
//        mockMvc.perform(post("/addUser")
//                        .param("id", "123")
//                        .param("username", "anna")
//                        .param("password", "444"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("index"))
//                .andExpect(model().attribute("name","anna"))
//                .andDo(print());
    }


}
