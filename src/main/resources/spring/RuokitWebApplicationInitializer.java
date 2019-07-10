package spring;

import java.util.EnumSet;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import spring.config.RuokitAspectOrientedConfig;
import spring.config.RuokitBeanPostProcessorConfig;
import spring.config.RuokitDatabaseConfig;
import spring.config.RuokitSecurityWebConfig;
import spring.config.RuokitWebConfig;

public class RuokitWebApplicationInitializer implements WebApplicationInitializer {

  private static final String DISPATCHER_SERVLET_NAME = "dispatcher";

  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {

    // add filter
    addCharacterEncodingFilter("characterEncodingFilter", "UTF-8",
        EnumSet.of(DispatcherType.REQUEST), "/*", true, servletContext);
    // add filter
    addDelegatingFilterProxy("springSecurityFilterChain", EnumSet.of(DispatcherType.REQUEST), "/*",
        true, servletContext);

    // regist Context
    AnnotationConfigWebApplicationContext dispatcherContext = registerContext(servletContext);

    // add Servlet
    setDispatcherServlet(servletContext, dispatcherContext);
  }

  private void addCharacterEncodingFilter(String filerName, String encoding,
      EnumSet<DispatcherType> dispatcherTypeSet, String pattern, boolean isMatchAfter,
      ServletContext servletContext) {
    FilterRegistration.Dynamic characterEncodingFilterRegist =
        servletContext.addFilter(filerName, new CharacterEncodingFilter());
    characterEncodingFilterRegist.setInitParameter("encoding", encoding);
    characterEncodingFilterRegist.addMappingForUrlPatterns(dispatcherTypeSet, isMatchAfter,
        pattern);
  }

  private void addDelegatingFilterProxy(String filerName, EnumSet<DispatcherType> dispatcherTypeSet,
      String pattern, boolean isMatchAfter, ServletContext servletContext) {
    FilterRegistration.Dynamic delegatingFilterRegist =
        servletContext.addFilter(filerName, new DelegatingFilterProxy());
    delegatingFilterRegist.addMappingForUrlPatterns(dispatcherTypeSet, isMatchAfter, pattern);
  }

  private AnnotationConfigWebApplicationContext registerContext(ServletContext servletContext) {
    AnnotationConfigWebApplicationContext dispatcherContext =
        new AnnotationConfigWebApplicationContext();
    dispatcherContext.register(RuokitWebConfig.class);
    dispatcherContext.register(RuokitSecurityWebConfig.class);
    dispatcherContext.register(RuokitDatabaseConfig.class);
    dispatcherContext.register(RuokitBeanPostProcessorConfig.class);
    dispatcherContext.register(RuokitAspectOrientedConfig.class);
    return dispatcherContext;
  }

  private void setDispatcherServlet(ServletContext servletContext,
      AnnotationConfigWebApplicationContext dispatcherContext) {

    // Servlet
    ServletRegistration.Dynamic dispatcherServletRegist;
    dispatcherServletRegist = servletContext.addServlet(DISPATCHER_SERVLET_NAME,
        new DispatcherServlet(dispatcherContext));
    dispatcherServletRegist.setLoadOnStartup(1);
    dispatcherServletRegist.addMapping("/");
  }
}
