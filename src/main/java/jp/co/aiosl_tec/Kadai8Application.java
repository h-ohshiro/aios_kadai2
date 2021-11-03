package jp.co.aiosl_tec;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@SpringBootApplication(exclude = SecurityAutoConfiguration.class) // springSecurity無効化
public class Kadai8Application {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		SpringApplication.run(Kadai8Application.class, args);
	}

}
