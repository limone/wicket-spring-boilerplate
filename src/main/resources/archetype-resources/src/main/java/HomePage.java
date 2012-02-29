package ${package};

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.internal.HtmlHeaderContainer;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.util.string.UrlUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomePage extends WebPage {
  private static final Logger log = LoggerFactory.getLogger(HomePage.class);
  
  public HomePage() {
    log.debug("Home page!");
    
    add(new WebComponent("ie9js").add(new AttributeModifier("src", new Model<>(UrlUtils.rewriteToContextRelative("js/ie9.js", RequestCycle.get())))));
  }
  

  @Override
  public void renderHead(HtmlHeaderContainer container) {
    super.renderHead(container);
    
    container.getHeaderResponse().renderJavaScriptReference("js/jquery-1.7.1.min.js");
    container.getHeaderResponse().renderJavaScriptReference("js/jquery-ui-1.8.18.min.js");
    container.getHeaderResponse().renderJavaScriptReference("js/plugins.js");
    container.getHeaderResponse().renderJavaScriptReference("js/scripts.js");
    
    container.getHeaderResponse().renderCSSReference("css/jquery-ui-1.8.18.css");
    container.getHeaderResponse().renderCSSReference("css/style.css");
  }
}