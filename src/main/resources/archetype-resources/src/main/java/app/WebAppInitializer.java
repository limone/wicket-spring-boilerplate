package ${package}.app;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextCleanupListener;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class WebAppInitializer implements WebApplicationInitializer {
  private static final Logger log = LoggerFactory.getLogger(WebAppInitializer.class);

  @Override
  public void onStartup(ServletContext sc) throws ServletException {
    SLF4JBridgeHandler.install();
    
    log.debug("${artifactId} starting up...");

    // Create the 'root' Spring application context
    AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
    ctx.register(SpringConfiguration.class);
    ctx.scan("${package}");
    ctx.refresh();

    // Manages the lifecycle
    sc.addListener(new ContextLoaderListener(ctx));
    sc.addListener(new ContextCleanupListener());
    
    // Filters
    sc.addFilter("OpenEntityInViewFilter", OpenEntityManagerInViewFilter.class).addMappingForUrlPatterns(null, true, "/*");
    

    log.debug("${package} initialized.");
  }
}