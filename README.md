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






