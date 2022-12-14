package ToDoService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ToDoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToDoServiceApplication.class, args);
    }

}
