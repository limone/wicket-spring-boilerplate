package ${package}.app;

import org.apache.wicket.Application;
import org.apache.wicket.Page;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.resource.AbstractResource.ResourceResponse;
import org.apache.wicket.request.resource.caching.IResourceCachingStrategy;
import org.apache.wicket.request.resource.caching.IStaticCacheableResource;
import org.apache.wicket.request.resource.caching.ResourceUrl;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.util.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;
import org.wicketstuff.annotation.scan.AnnotatedMountScanner;

import ${package}.HomePage;

@Component
public class WicketApplication extends WebApplication {
  protected static final Logger log = LoggerFactory.getLogger(WicketApplication.class);
  private static final String DEFAULT_ENCODING = "UTF-8";
  
  public WicketApplication() {
    // empty
  }
  
  public static WicketApplication get()
  {
    return (WicketApplication) Application.get();
  }

  @Override
  protected void init() {
    log.debug("Bringing Wicket application online.");
    
    super.init();
    getComponentInstantiationListeners().add(new SpringComponentInjector(this));
    new AnnotatedMountScanner().scanPackage("${package}").mount(this);

    getMarkupSettings().setDefaultMarkupEncoding(DEFAULT_ENCODING);
    getRequestCycleSettings().setResponseRequestEncoding(DEFAULT_ENCODING);
    
    if (getConfigurationType().equals(RuntimeConfigurationType.DEVELOPMENT)) {
      getMarkupSettings().setStripWicketTags(true);
      getMarkupSettings().setStripComments(true);
      getMarkupSettings().setCompressWhitespace(true);

      getDebugSettings().setAjaxDebugModeEnabled(true);
      getDebugSettings().setComponentUseCheck(true);
      getDebugSettings().setDevelopmentUtilitiesEnabled(true);

      getResourceSettings().setResourcePollFrequency(Duration.ONE_SECOND);
    }
    
    getResourceSettings().setCachingStrategy(new IResourceCachingStrategy() {
      @Override
      public void undecorateUrl(ResourceUrl url) {
        // empty
      }
      
      @Override
      public void decorateUrl(ResourceUrl url, IStaticCacheableResource resource) {
        // empty
      }
      
      @Override
      public void decorateResponse(ResourceResponse response, IStaticCacheableResource resource) {
        // empty
      }
    });
    
    log.debug("Wicket application up and running.");
  }
  
  @Override
  public Class<? extends Page> getHomePage() {
    return HomePage.class;
  }
}