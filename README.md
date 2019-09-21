#学习springboot自动装配

##一、手动装配
###1、模式注解装配
@Component注解，或者@Component注解的拓展，
@Controller、@Service、Repository、@Configruation等，

###2.@Configuration启动容器+@Bean注册Bean 
从Spring3.0，@Configuration用于定义配置类，可替换xml配置文件，
被注解的类内部包含有一个或多个被@Bean注解的方法，
这些方法将会被AnnotationConfigApplicationContext或AnnotationConfigWebApplicationContext类进行扫描，
并用于构建bean定义，初始化Spring容器。

注意：@Configuration注解的配置类有如下要求：
1. @Configuration不可以是final类型；
2. @Configuration不可以是匿名类；
3. 嵌套的configuration必须是静态类。

###3、@Enable模块装配（@Import）
基于接口驱动实现

例如：添加@EnableAsync注解就可以使用@Async注解的异步方法操作。

@EnableAsync的源码：
```
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(AsyncConfigurationSelector.class)
public @interface EnableAsync {
```
最重要是@Import({CachingConfigurationSelector.class})，
其实使用EnableAsync，就是导入AsyncConfigurationSelector.class这配置类。
AsyncConfigurationSelector继承了AdviceModeImportSelector，
AdviceModeImportSelector实现了ImportSelector接口，
ImportSelector接口是spring中导入外部配置的核心接口，只有一个方法selectImports，
其实就是根据EnableCaching的元数据属性（proxyTargetClass、mode、order），选择出需要转配的Configuration。
```
public class AsyncConfigurationSelector extends AdviceModeImportSelector<EnableAsync> {
	private static final String ASYNC_EXECUTION_ASPECT_CONFIGURATION_CLASS_NAME =
			"org.springframework.scheduling.aspectj.AspectJAsyncConfiguration";
	/**
	 * 选择JDK动态代理或AspectJ动态代理
	 */
	@Override
	@Nullable
	public String[] selectImports(AdviceMode adviceMode) {
		switch (adviceMode) {
			case PROXY:
				return new String[] {ProxyAsyncConfiguration.class.getName()};
			case ASPECTJ:
				return new String[] {ASYNC_EXECUTION_ASPECT_CONFIGURATION_CLASS_NAME};
			default:
				return null;
		}
	}
}
```

@EnableXXX模块注入，基于接口驱动实现是实现ImportSelector接口，通过注解参数选择需要导入的配置，而基于注解驱动实现其实就是@Import的派生注解，直接导入某个配置类。

import 注解是什么意思呢？联想到xml形式下有一个<import resource/>形式的注解，就明白它的作用了。

import 就是把多个分来的容器配置合并在一个配置中。在JavaConfig 中所表达的意义是一样的。

@Import 注解可以配置三种不同的 class
1. 基于普通 bean 或者带有@Configuration 的 bean 
2. 实现 ImportSelector 接口进行动态注入
3. 实现 ImportBeanDefinitionRegistrar 接口进行动态注入。

###3、条件装配
条件装配，其实是Bean装配的前置条件，使用@Conditional注解。
@Conditional是Spring4新提供的注解，它的作用是按照一定的条件进行判断，满足条件给容器注册bean。
Condition是个接口，需要实现matches方法，返回true则注入bean，false则不注入。

比如@ConditionalOnBean里面有@Conditional(OnBeanCondition.class) ，OnBeanCondition实现了Condition
```
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(OnBeanCondition.class)
public @interface ConditionalOnBean {
```
常见.@ConditionalOnXXX:

1.@ConditionalOnBean（仅仅在当前上下文中存在某个对象时，才会实例化一个Bean）
2.@ConditionalOnMissingBean（仅仅在当前上下文中不存在某个对象时，才会实例化一个Bean）
3.@ConditionalOnClass（某个class位于类路径上，才会实例化一个Bean）
4.@ConditionalOnMissingClass（某个class类路径上不存在的时候，才会实例化一个Bean）
5.@ConditionalOnWebApplication:当前项目是Web项目的条件下
6.@ConditionalOnNotWebApplication（不是web应用）
7.@ConditionalOnExpression（当表达式为true的时候，才会实例化一个Bean）
8.@ConditionalOnJava:基于JVM版本作为判断条件
9.@ConditionalOnJndi:在JNDI存在的条件下查找指定的位置
10.@ConditionalOnProperty:指定的属性是否有指定的值
11.@ConditionalOnResource:类路径下是否有指定的资源
12.@ConditionalOnSingleCandidate:当指定的Bean在容器中只有一个，或者在有多个Bean的情况下，用来指定首选的Bean

当这些条件注解放在某个bean上面的时候，只有满足了条件才能注入bean，
这也是为什么springboot能这么智能，知道哪些模块需要开启，哪些不需要.


##二、自动装配

自动装配就是手动装配的综合运用，只不过在装配bean或配置类时候，我们不在需要使用@EnableXXX来导入功能，而是通过自动注入方式。这时候自动注入的条件判断（@Conditional）就显得非常重要了。

springboot集成freemaker非常简单，只需要导入starter的jar包就会自动实现注入，这个自动集成就是FreeMarkerAutoConfiguration这里配置的。

这里有个问题，为什么springboot会自动去判断和加载FreeMarkerAutoConfiguration这个配置类吗？我没有写类似的@EnableFreemaker，那项目怎么识别的。

其实如果你看过springboot的源码，你就会发现：

org.springframework.boot.autoconfigure.AutoConfigurationImportSelector#getCandidateConfigurations
```
	protected List<String> getCandidateConfigurations(AnnotationMetadata metadata, AnnotationAttributes attributes) {
		List<String> configurations = SpringFactoriesLoader.loadFactoryNames(getSpringFactoriesLoaderFactoryClass(),
				getBeanClassLoader());
		Assert.notEmpty(configurations, "No auto configuration classes found in META-INF/spring.factories. If you "
				+ "are using a custom packaging, make sure that file is correct.");
		return configurations;
	}
```
上面的意思是去扫描项目下所有的META-INF/spring.factories文件，然后把EnableAutoConfiguration.class作为key找出对应的值，这个值是个List。

那么我们来看下其中一个spring.factories长什么样子的。
spring-boot-autoconfigure/2.1.2.RELEASE/spring-boot-autoconfigure-2.1.2.RELEASE.jar!/META-INF/spring.factories
```
# Auto Configure
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration,\
org.springframework.boot.autoconfigure.aop.AopAutoConfiguration,\
org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration,\
org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration,\
org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration,\
org.springframework.boot.autoconfigure.cassandra.CassandraAutoConfiguration,\
org.springframework.boot.autoconfigure.cloud.CloudServiceConnectorsAutoConfiguration,\
org.springframework.boot.autoconfigure.context.ConfigurationPropertiesAutoConfiguration,\
org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration,\
org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration,\
org.springframework.boot.autoconfigure.couchbase.CouchbaseAutoConfiguration,\
org.springframework.boot.autoconfigure.dao.PersistenceExceptionTranslationAutoConfiguration,\
org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration,\
org.springframework.boot.autoconfigure.data.cassandra.CassandraReactiveDataAutoConfiguration,\
org.springframework.boot.autoconfigure.data.cassandra.CassandraReactiveRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.cassandra.CassandraRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.couchbase.CouchbaseDataAutoConfiguration,\
org.springframework.boot.autoconfigure.data.couchbase.CouchbaseReactiveDataAutoConfiguration,\
org.springframework.boot.autoconfigure.data.couchbase.CouchbaseReactiveRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.couchbase.CouchbaseRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchAutoConfiguration,\
```
可以看到org.springframework.boot.autoconfigure.EnableAutoConfiguration作为key有很多个值，比如RabbitMq的自动配置类等，就能找到FreeMarkerAutoConfiguration这配置类了。
所以情况是这个的，当springboot项目启动时候，项目会去加载所有的spring.factories文件，然后在EnableAutoConfiguration后面的所有配置类其实都是可以实现自动装配的配置，至于需不需要装配，就需要条件装配来判定是否满足特定的条件了。

有了这点基础之后，我们就可以自己去写自动装配了。


##什么是 Starter
Starter是Spring Boot中的一个非常重要的概念，Starter相当于模块，它能将模块所需的依赖整合起来并对模块内的 Bean 根据环境（ 条件）进行自动配置。使用者只需要依赖相应功能的 Starter，无需做过多的配置和依赖，Spring Boot 就能自动扫描并加载相应的模块。

例如：在 Maven 的依赖中加入 spring-boot-starter-web 就能使项目支持 Spring MVC，并且 Spring Boot 还为我们做了很多默认配置，
无需再依赖 spring-webpring-webmvc 等相关包及做相关配置就能够立即使用起来
SpringBoot 存在很多开箱即用的 Starter 依赖，使得我们在开发业务代码时能够非常方便的、不需要过多关注框架的配置，而只需要关注业务即可
 
 
###Starter的命名
官方对Starter项目的jar包定义的 artifactId 是有要求的，当然也可以不遵守。
Spring官方Starter通常命名为spring-boot-starter-{name}如：spring-boot-starter-web，
Spring官方建议非官方的starter命名应遵守{name}-spring-boot-starter的格式。



