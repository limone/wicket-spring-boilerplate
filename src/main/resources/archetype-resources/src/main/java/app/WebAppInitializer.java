package ${package}.app;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextCleanupListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class WebAppInitializer implements WebApplicationInitializer {
  private static final Logger log = LoggerFactory.getLogger(WebAppInitializer.class);

  @Override
  public void onStartup(ServletContext sc) throws ServletException {
    /**
     * Uncomment this out if you wish to reroute JUL logs to SLF4J/Logback
     * 
     * Please note that you'll need to ensure the jul-to-slf4j dependency is added to Maven.
     */
    // SLF4JBridgeHandler.install();
    
    log.debug("${artifactId} starting up...");

    // Create the 'root' Spring application context
    AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
    ctx.register(SpringConfiguration.class);
    ctx.refresh();
    
    // Register the Spring Context in the ServletContext
    sc.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, ctx);

    // Manages the lifecycle
    sc.addListener(new ContextCleanupListener());
    
    // Filters
    sc.addFilter("OpenEntityInViewFilter", OpenEntityManagerInViewFilter.class).addMappingForUrlPatterns(null, true, "/*"); // Used for JPA, to ensure there's an EntityManager available
    
    log.debug("${artifactId} initialized.");
  }
}