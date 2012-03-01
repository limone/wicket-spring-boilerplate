package ${package};

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.markup.html.WebPage;
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
  public void renderHead(IHeaderResponse response) {
    response.renderJavaScriptReference("js/jquery-1.7.1.min.js");
    response.renderJavaScriptReference("js/jquery-ui-1.8.17.custom.min.js");
    response.renderJavaScriptReference("js/plugins.js");
    response.renderJavaScriptReference("js/scripts.js");
    
    response.renderCSSReference("css/jquery-ui-1.8.17.custom.css");
    response.renderCSSReference("css/style.css");
  }
}