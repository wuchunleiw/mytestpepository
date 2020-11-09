package com.tool.sts.config;

import java.io.File;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
@Configuration
public class FirstStsToolWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// TODO Auto-generated method stub
		super.onStartup(servletContext);
		ServletRegistration servletRegistration =servletContext.getServletRegistration("dispatcher");
		
		
		// spring 字符过滤
//		FilterRegistration.Dynamic encodingFilter=servletContext.addFilter("encoding-filter",CharacterEncodingFilter.class);
//		encodingFilter.setInitParameter("encoding","UTF-8");
//		encodingFilter.setInitParameter("forceEncoding","true");
//		encodingFilter.setAsyncSupported(true);
//		encodingFilter.addMappingForUrlPatterns(null,true,"/*");
	}
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {RootConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] {WebConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
	
	@Override
	protected void customizeRegistration(Dynamic registration) {
		String path = "/tmp/springbook/uploads";
		File f = new File(path);
		if(!f.exists()) {
			f.mkdirs();
		}
		registration.setMultipartConfig(new MultipartConfigElement(path,2097152,4194304,0));
		
		// 让404作为异常抛出 不使用tomcat默认的
		registration.setInitParameter("throwExceptionIfNoHandlerFound","true");
	}
	


}
