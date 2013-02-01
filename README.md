# Wicket Spring Boilerplate

Why should all the HTML5 apps get the cool stuff, but not us Java folks?!  To this end, I've created this very simple Maven archetype that integrates Wicket, Spring, Hibernate and JPA.  

Please note that the WSP was designed to specifically remove all XML files from the web application - there are no Spring XML configuration files, no Hibernate mapping files, and no web.xml!  Everything is driven by annotations.  Please make sure that you are deploying to a servlet 3.0 container, otherwise the WSB will probably explode.  It has been tested via Tomcat 7.0.25/26.

### Usage

The archetype has been released to the Sonatype OSS Nexus, so you can do the following without having to clone this project:

```
mvn archetype:generate -DarchetypeRepository=https://oss.sonatype.org/content/groups/public/ -DarchetypeGroupId=me.limone -DarchetypeArtifactId=wicket-spring-boilerplate -DarchetypeVersion=1.0 -DgroupId=com.mycompany -DartifactId=magic-webapp -Dversion=1.0-SNAPSHOT
```

However, if you want to use the latest code, or can't get to the Sonatype OSS repository, you can still clone the project.  Once you have the code, you run the following to install the archetype to your local repository:

```
mvn clean install
```

Once you have installed it to your local Maven repository, you can use it as follows:

```
mvn archetype:generate -DarchetypeGroupId=me.limone -DarchetypeArtifactId=wicket-spring-boilerplate -DarchetypeVersion=1.1-SNAPSHOT -DgroupId=com.mycompany -DartifactId=magic-webapp -Dversion=1.0-SNAPSHOT
```

### Versions
Java 1.7  
Wicket 1.5  
Spring 3.2  
Hibernate 4.1  
Logback 1.0/SLF4J 1.7 (includes JCL bridge for Spring, also includes JUL and Log4J bridges - uncomment if needed)  

### Database Information
The boilerplate includes a database connection - for testing purposes, it is set to an HSQL in-memory database, but can easily be changed.  To change, simply include the relevant artifact via Maven, and modify the app.properties (driver, username, password, JDBC URL and JPA vendor).

### Deploying/Running
The WSB was designed to have an externalized configuration file, so that changing the properties between the development instance and the production instance shouldn't require any recompilation.  To get this to work, make sure that your application server includes the following argument:
  
```
-Dconfig=/path/to/config/
```

or for Eclipse
```
-Dconfig=${workspace_loc:/{app}/src/main/conf}
```
  
Please not that you do not need to specify the filename - that has been set in the SpringConfiguration class.  If you wish to change this behaviour, you know where to look!