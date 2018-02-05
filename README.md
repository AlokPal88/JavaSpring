# JavaSpring
Simple Spring MVC single page app

To run this example make sure the file path refers to correct sqlite database file.
Examples use Tomcat 7 as runtime environment.
Please, download Tomcat 7 and setup runtime environment in your eclipse befor importing projects.
Also, note, that projects are built using Maven.
Before running examples, please, make sure that the deployment assembly of the example project contains both: Jeneva-*.*.jar and Maven dependencies
(Project - Properties - Deployment Assembly - Add - Java Build Path Entries - Maven Dependencies).

Open this file:
\MyClients\WebContent\WEB-INF\MyClientsServlet-servlet.xml

Correct the "url" element with correct path to sqlite database.

The sqlite database is located here:
\Examples\MyClients\WebContent\WEB-INF\jeneva.sqlite


There is one examples:
MyClients - This is advanced MVC web application, extremely easy to implement and support.
This is also great example on how to create MVC applications with AngularJS and Jeneva.
This example shows you how to use Jeneva to create robust validation.

You can start using Jeneva in your own projects.
The file "jeneva.angular.js" can greatly simplify your efforts.
Copy this file to your project resources folder and start using it.
Don't forget to setup jeneva.baseUrl property to the home directory of your application (see app.js file in examples).
