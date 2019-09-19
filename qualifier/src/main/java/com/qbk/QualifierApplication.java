package com.qbk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.lang.annotation.*;
import java.util.Collections;
import java.util.Map;

@SpringBootApplication
public class QualifierApplication {

	@Bean
	public String aBean(){
		return "aaaaa";
	}
	@Bean
	@Qualifier("b")
	public String bBean(){
		return "bbbbb";
	}
	@Bean
	@Qualifier("b")
	public String cBean(){
		return "ccccc";
	}
	@Bean
	@Qualifier()
	public String dBean(){
		return "ddddd";
	}
	@Bean
	@MyQualifier()
	public String eBean(){
		return "eeeee";
	}
	@Bean
	@MyQualifier()
	public String fBean(){
		return "fffff";
	}

	@Autowired
	private String aBean;
	@Autowired
	private String bBean;
	@Autowired
	private String cBean;
	@Autowired
	private String dBean;
	@Autowired
	@Qualifier("eBean")
	private String eBean;
	@Autowired
	private String fBean;
	/**
	 * Field required a single bean, but 2 were found
	 */
//	@Autowired
//	@Qualifier("b")
//	private String errorBean;

	@Autowired
	private Map<String,String> allMap = Collections.emptyMap();
	@Autowired
	@Qualifier("b")
	private Map<String,String> bQualifierMap = Collections.emptyMap();
	@Autowired
	@Qualifier()
	private Map<String,String> qualifierMap = Collections.emptyMap();
	@Autowired
	@MyQualifier()
	private Map<String,String> myQualifierMap = Collections.emptyMap();

	@Bean
	public ApplicationRunner runner(){
		return applicationArguments -> {
			System.out.println(allMap);//{aBean=aaaaa, bBean=bbbbb, cBean=ccccc, dBean=ddddd, eBean=eeeee, fBean=fffff}
			System.out.println(bQualifierMap);//{bBean=bbbbb, cBean=ccccc}
			System.out.println(qualifierMap);//{dBean=ddddd, eBean=eeeee, fBean=fffff}
			System.out.println(myQualifierMap);//{eBean=eeeee, fBean=fffff}

			System.out.println(aBean);
			System.out.println(bBean);
			System.out.println(cBean);
			System.out.println(dBean);
			System.out.println(eBean);
			System.out.println(fBean);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(QualifierApplication.class, args);
	}

}

@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Qualifier
@interface MyQualifier{

}