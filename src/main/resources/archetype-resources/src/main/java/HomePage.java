package ${package};

import org.apache.wicket.markup.html.WebPage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomePage extends WebPage {
  private static final long serialVersionUID = 1L;
  
  private static final Logger log = LoggerFactory.getLogger(HomePage.class);
  
  public HomePage() {
    log.debug("Home page!");
  }
}