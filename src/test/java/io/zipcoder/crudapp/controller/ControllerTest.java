package io.zipcoder.crudapp.controller;

import com.sun.deploy.config.WebStartConfig;
import io.zipcoder.crudapp.CRUDApplication;
import io.zipcoder.crudapp.model.Person;
import io.zipcoder.crudapp.repository.PersonRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CRUDApplication.class)
public class ControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PersonRepository repository;

    private PersonController controller;

    @Before
    public void setUp() { controller = new PersonController(repository); }

    @Test
    public void testCreate() {
        //Given
        Person expected = new Person("Amy", "June");
        BDDMockito.given(repository.save(expected)).willReturn(expected);

        //When
        String givenJson = "{\"id\":null,\"firstName\":\"Amy\",\"lastName\":\"June\"}";
        try {
            this.mvc.perform(MockMvcRequestBuilders
                    .post("/people")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(givenJson))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().string(givenJson));
        } catch (Exception e) {
            System.out.println(e);
            Assert.fail();
        }
    }

    @Test
    public void testGet() {

        //Given
        Long givenId = 1L;
        Person expected = new Person("Amy", "June");
        BDDMockito.given(repository.findOne(givenId)).willReturn(expected);

        //When
        String givenJson = "{\"id\":null,\"firstName\":\"Amy\",\"lastName\":\"June\"}";
        try {
            this.mvc.perform(MockMvcRequestBuilders
                    .get("/people/" + givenId))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().string(givenJson));
        } catch (Exception e) {
            System.out.println(e);
            Assert.fail();
        }
    }

    @Test
    public void testGetList() {
        Assert.fail();
    }

    @Test
    public void testUpdate() {
        Assert.fail();
    }

    @Test
    public void testDelete() {
        //Given
        Long givenId = 1L;
        Person expected = new Person("Amy", "June");
        BDDMockito.given(repository.findOne(givenId)).willReturn(expected);

        //When
        String givenJson = "{\"id\":null,\"firstName\":\"Amy\",\"lastName\":\"June\"}";
        try {
            this.mvc.perform(MockMvcRequestBuilders
                    .get("/people/" + givenId))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().string(givenJson));
        } catch (Exception e) {
            System.out.println(e);
            Assert.fail();
        }
    }
}
