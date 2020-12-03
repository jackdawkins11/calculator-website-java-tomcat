
# Simple calculator website
Try it out here: http://52.15.174.58/frontend This is the 5th implementation of this website. The others are on my github.

## Tools used
* Apache Tomcat
* React
* MySQL

## Host it yourself
Do the following
* Set up Tomcat
* Clone this repository
* Compile the java code in this repository, putting the output class files into `/WEB-INF/classes`
* Compile the JSX code (the javascript in `/react`), putting the output javascript into `/react_compiled`
* Create the WAR file: `jar -cvf app1.war *css *html react_compiled/ WEB-INF/ *css`
* Deploy the WAR file on tomcat