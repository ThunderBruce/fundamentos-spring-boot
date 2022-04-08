package com.fundamentos.springboot.fundamentos;

import com.fundamentos.springboot.fundamentos.bean.MyBean;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentos.springboot.fundamentos.bean.MyOperationSaludarWithDependency;
import com.fundamentos.springboot.fundamentos.component.ComponentDependency;
import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.pojo.UserPojo;
import com.fundamentos.springboot.fundamentos.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);

	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;
	private MyOperationSaludarWithDependency myOperationSaludarWithDependency;
	private MyBeanWithProperties myBeanWithProperties;
	private UserPojo userPojo;
	private UserRepository userRepository;

	public FundamentosApplication(@Qualifier("componentImplement") ComponentDependency componentDependency,
								  MyBean myBean, MyBeanWithDependency myBeanWithDependency,
								  MyOperationSaludarWithDependency myOperationSaludarWithDependency, MyBeanWithProperties myBeanWithProperties,
								  UserPojo userPojo, UserRepository userRepository) {
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myOperationSaludarWithDependency = myOperationSaludarWithDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;

	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) {
		//ejemplosAnteriores();
		saveUsersInDataBase();
		getInformationJpqlFromUser();
	}

	private void getInformationJpqlFromUser() {
		LOGGER.info("Usuario con el metodo findByUserEmail "+
				userRepository.findByUserEmail("julie@domain.com")
						.orElseThrow(()->new RuntimeException("No se encontro el usuario")));

		userRepository.findAndSort("user", Sort.by("id").ascending())
				.stream()
				.forEach(user -> LOGGER.info("Usuario con metodo sort "+user));
		userRepository.findByName("John")
				.stream()
				.forEach(user -> LOGGER.info("Usuario con query method "+user));
		LOGGER.info("Usuario con query method findByEmailAndName "+
				userRepository.findByEmailAndName("daniela@domain.com","Daniela")
				.orElseThrow(()-> new RuntimeException("Usuario no encontrado")));

	}
	private void saveUsersInDataBase(){
		User user1 = new User("John","john@domain.com", LocalDate.of(2021,03,20));
		User user2 = new User("John","julie@domain.com", LocalDate.of(2021,05,21));
		User user3 = new User("Daniela","daniela@domain.com", LocalDate.of(2021,07,21));
		User user4 = new User("user4","user4@domain.com", LocalDate.of(2021,07,7));
		User user5 = new User("user5","user5@domain.com", LocalDate.of(2021,11,11));
		User user6 = new User("user6","user6@domain.com", LocalDate.of(2021,2,25));
		User user7 = new User("user7","user7@domain.com", LocalDate.of(2021,3,11));
		User user8 = new User("user8","user8@domain.com", LocalDate.of(2021,4,12));
		User user9 = new User("user9","user9@domain.com", LocalDate.of(2021,5,22));
		User user10 = new User("user10","user10@domain.com", LocalDate.of(2021,8,3));
		User user11 = new User("user11","user11@domain.com", LocalDate.of(2021,1,12));
		User user12 = new User("user12","user12@domain.com", LocalDate.of(2021,2,2));
		List<User> list = Arrays.asList(user1,user2,user3,user4,user5,user6,user7,user8,user9,user10,user11,user12);
		list.stream().forEach(userRepository::save);

	}

	private void ejemplosAnteriores() {
		componentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
		myOperationSaludarWithDependency.saludar();
		System.out.println(myBeanWithProperties.function());
		System.out.println(userPojo.getEmail()+"-"+userPojo.getPassword()+"-"+ userPojo.getAge());
		try {
			//error
			int value = 10/0;
			LOGGER.info("Mi valor: "+value);
		} catch (Exception e ) { //Capturamos el error
			LOGGER.error("Esto es un error al dividir por cero "+e.getMessage()); //mostramos el error en el servidor - e.getStackTrace()
		}
	}
}
