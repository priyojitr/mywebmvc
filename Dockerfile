#
# Build stage - create war file
# 
FROM maven:3.6.2-jdk-8 AS MAVEN_TOOL_CHAIN
COPY pom.xml /usr/app/mywebmvc/
COPY src /usr/app/mywebmvc/src/
WORKDIR /usr/app/mywebmvc/
RUN mvn package -DskipTests

#
# deploy stage - deploy the war file to tomcat webapp location
#
FROM tomcat:9.0.27-jdk8-openjdk
COPY --from=MAVEN_TOOL_CHAIN /usr/app/mywebmvc/target/*.war $CATALINA_HOME/webapps/mywebmvcapp.war

