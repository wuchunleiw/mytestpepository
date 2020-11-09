package com.tool.sts.config;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

/**
 * 继承 WebMvcConfigurerAdapter 类，重写其方法可对 spring mvc 进行配置
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.tool.sts.controller")
public class WebConfig extends WebMvcConfigurerAdapter{//extends WebMvcConfigurerAdapter
	@Bean
	public ViewResolver viewResolver() {
		
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		
//		resolver.setSuffix(".jsp");
//		resolver.setViewClass(JstlView.class);
//		resolver.setContentType("text/html");
		
		// 可以在JSP页面中通过${}访问beans
		resolver.setExposeContextBeansAsAttributes(true);
		return resolver;
	}
	// 重写 addViewControllers 简化页面快捷转向，这样就可以不用配置 Controller 了
//	@Override
//	public void addViewControllers(ViewControllerRegistry registry) {
//	  registry.addViewController("/").setViewName("index");
//      registry.addViewController("/error").setViewName("error/error");
//      registry.addViewController("/excel").setViewName("excel/excel");
//      // 文件上传下载
//      registry.addViewController("/upload").setViewName("fileupload/upload");
//      registry.addViewController("/ImageValidateCodeLogin").setViewName("login/imageValidateCodeLogin");
//      registry.addViewController("/restfulapi").setViewName("restful/user");
//      registry.addViewController("/jaxwsri").setViewName("jaxwsri/wsri");
//      registry.addViewController("/redis").setViewName("redis/jedis");
//      registry.addViewController("/mybatisPage").setViewName("db/mybatis");
//      registry.addViewController("/messageconverter").setViewName("httpmessageconverter/customconverter");
//      registry.addViewController("/sse").setViewName("serverpushmessage/sse");
//	}
	
	// 配置springMVC处理上传文件的信息
//	  @Bean
//	  public MultipartResolver multipartResolver() {
//	      CommonsMultipartResolver resolver = new CommonsMultipartResolver();
//	      resolver.setDefaultEncoding("UTF-8");
//	      resolver.setMaxUploadSize(10485760000L);
//	      resolver.setMaxInMemorySize(40960);
//	      return resolver;
//	  }

	// 配置静态文件处理
//      @Override
//      public void addResourceHandlers(ResourceHandlerRegistry registry){
//  
//          /**
//           * addResourceHandler 指的是对外暴露的访问路径
//           * addResourceLocations 指的是文件放置的目录
//           * */
//          registry.addResourceHandler("/assets/**")
//                 .addResourceLocations("classpath:/assets/");
//  
//          // href 链接方式 下载文件
//          registry.addResourceHandler("/files/**")
//                  .addResourceLocations("classpath:/files/");
//  
//          /**
//           * 解决 No handler found for GET /favicon.ico 异常
//          * */
//         registry.addResourceHandler("/favicon.ico")
//                 .addResourceLocations("classpath:/favicon.ico");
// 
//     }
	
	// 重写 configurePathMatch ，改变路径参数匹配
//     @Override
//     public void configurePathMatch(PathMatchConfigurer configurer) {
// 
//         /**
//          * Spring mvc 默认 如果路径参数后面带点，如 “/mm/nn/xx.yy” 后面的yy值将被忽略
//          * 加入下面的配置，就不会忽略“.”后面的参数了
//          * */
//         configurer.setUseSuffixPatternMatch(false);
// 
//     }
	
	// 负责读取字符串格式的数据和写出字符串格式的数据；
//     @Bean
//     public StringHttpMessageConverter stringHttpMessageConverter() {
// 
//         StringHttpMessageConverter messageConverter = new StringHttpMessageConverter();
//         messageConverter.setDefaultCharset(Charset.forName("UTF-8"));
//         return messageConverter;
// 
//     }
//      @Bean
//      public CustomMessageConverter customMessageConverter(){
//  
//          return new CustomMessageConverter();
//  
//      }
     
      // 负责读取和写入json格式的数据；
      /**
       * 配置 fastjson 中实现 HttpMessageConverter 接口的转换器
       * FastJsonHttpMessageConverter 是 fastjson 中实现了 HttpMessageConverter 接口的类
       */
      @Bean(name = "fastJsonHttpMessageConverter")
      public FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {
          //
          String txtHtml = "text/html;charset=UTF-8";
          String txtJson = "text/json;charset=UTF-8";
          String appJson = "application/json;charset=UTF-8";
  
          // 这里顺序不能反，一定先写 text/html,不然 IE 下会出现下载提示
          List<MediaType> mediaTypes = new ArrayList<>();
          mediaTypes.add(MediaType.parseMediaType(txtHtml));
          mediaTypes.add(MediaType.parseMediaType(txtJson));
          mediaTypes.add(MediaType.parseMediaType(appJson));
  
          // 加入支持的媒体类型，返回 contentType
          FastJsonHttpMessageConverter fastjson = new FastJsonHttpMessageConverter();
          fastjson.setSupportedMediaTypes(mediaTypes);
          return fastjson;
  
      }
  
      /**
       * 重写 extendMessageConverters 方法，仅添加自定义 HttpMessageConverter
       * 不覆盖默认注册的 HttpMessageConverter
       * */
      @Override
      public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
  
          converters.add(fastJsonHttpMessageConverter());
  
      }
      
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
}
