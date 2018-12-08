package cn.itcast.jsontest;


import cn.itcast.domain.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.junit.Test;


import java.beans.Transient;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JsonTest01 {
    @Test
    public void test01() throws Exception {
        Person person = new Person();
        person.setUsername("zhangsan");
        person.setPassword("123");
        person.setAge(123);
        person.setGender("man");
        person.setBirthday(new Date());


        ObjectMapper objectMapper = new ObjectMapper();
       // objectMapper.writeValue(new File("d://a.txt"),person);
       String s = objectMapper.writeValueAsString(person);
       System.out.println(s);

    }
    @Test
    public void test02() throws JsonProcessingException {
        Person person = new Person();
        person.setUsername("zhangsan");
        person.setPassword("123");
        person.setAge(123);
        person.setGender("man");
        person.setBirthday(new Date());

        Person person1 = new Person();
        person.setUsername("zhangsan");
        person.setPassword("123");
        person.setAge(123);
        person.setGender("man");
        person.setBirthday(new Date());

        Person person2 = new Person();
        person.setUsername("zhangsan");
        person.setPassword("123");
        person.setAge(123);
        person.setGender("man");
        person.setBirthday(new Date());

        Person person3 = new Person();
        person.setUsername("zhangsan");
        person.setPassword("123");
        person.setAge(123);
        person.setGender("man");
        person.setBirthday(new Date());

        ArrayList<Person> list = new ArrayList<>();
        list.add(person);
        list.add(person1);
        list.add(person2);
        list.add(person3);

        ObjectMapper objectMapper = new ObjectMapper();
        // objectMapper.writeValue(new File("d://a.txt"),person);
        String s = objectMapper.writeValueAsString(list);
        System.out.println(s);

    }
    @Test
    public void test03() throws IOException {
        String json = "{\"username\":\"zhangsan\",\"password\":\"123\",\"age\":123,\"gender\":\"man\",\"birthday\":\"2018-10-23\"}";
        ObjectMapper objectMapper = new ObjectMapper();
        Person person = objectMapper.readValue(json, Person.class);
        System.out.println(person);
    }
    @Test
    public void test04() throws IOException {
        String json = "{\"username\":\"zhangsan\",\"password\":\"123\",\"age\":123,\"gender\":\"man\",\"birthday\":\"2018-10-23\"}";
        ObjectMapper objectMapper = new ObjectMapper();
        CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, Person.class);

        Person person = objectMapper.readValue(json, Person.class);
        System.out.println(person);
    }
}
