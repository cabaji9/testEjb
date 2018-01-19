## EJB Standalone connect remote ejb


1. To connect and test first ensure the ejb has an @Remote interface.
2. Search for the jndi on the log of the jboss for the bean with this naming format:

   ```
   java:jboss/exported/ear/ejb/Beani!com.service.InterfaceBean
   ```

3. The java:jboss/exported part is where the @Remote its working. 

4. Search on your jboss installation the jboss-client.jar, generally on :

   ```
   ../jboss-eap-6.2/bin/client/jboss-client.jar
   ```

5. Install it on your maven with the command:

   ```bash
mvn install:install-file -Dfile=jboss-client.jar -DgeneratePom=true -DgroupId=com.ec.mutualistapichincha -DartifactId=jboss-client -Dversion=1.0.0 -Dpackaging=jar
    ```

6. Or deploy it to your nexus with: 

   ```bash
mvn deploy:deploy-file -DgroupId=org.jboss -DartifactId=client -Dversion=6.2  -Dfile=jboss-client.jar  -DrepositoryId=[id repository in settings.xml] -Durl=[hosted url of nexus repository]
   ```

7. Add the dependency on your pom.xml of your project

   ```xml
<dependency>
    <groupId>org.jboss</groupId>
    <artifactId>client</artifactId>
    <version>6.2</version>
    <scope>test</scope>
</dependency>
   ```

8. Create a unit test and add this to obtain the InitialContext

   ```
   Properties prop = new Properties();
   prop.put(Context.INITIAL_CONTEXT_FACTORY,
   "org.jboss.naming.remote.client.InitialContextFactory");
   prop.put(Context.PROVIDER_URL, "remote://localhost:4947");
   prop.put(Context.SECURITY_PRINCIPAL, "admin");
   prop.put(Context.SECURITY_CREDENTIALS, "advance$1234");
   prop.put("jboss.naming.client.ejb.context", true);

   Context context = new InitialContext(prop);
   ```

   

9. Then with the context obtained just search for the jndi minus the java:jboss/exported

10. Obtain the ejb with lookup:

   ```java
Beani bean =  (Beani) context.lookup("ear/ejb/Beani!com.service.InterfaceBean”);
   ```

11. Call method

   ```java
   beani.start();
   ```
