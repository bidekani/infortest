#Infor Test (CarRental restApi)
How do we build and run it? 
<br>mvn clean test spring-boot:run <br>
<b>Running version in openshift: <br>


<br>
<b> to run the full acceptance tests use this profile: </b>
<br>mvn test -Pacceptance <br>


# What tools did you use?
spring-boot <br>
lombok - to have more readable and clean code<br>
swagger - to generate API documantation with very easy and powerfull interface to use and test by team.(like frontend team or other teams that use service)
javamelody - for monitoring the service and api calls



# Regarding the heavy load test
I used a Jmeter script to simulate forntend. <br>
5 users sending infintive request creating new cars with random numbers and listing the cars
10 users booking cars <br>
<br> <b> to run jmeter test use this script: </b> 
<br> jmeter -n -t  infoTest.jmx   -l  resultfile  -e

<br> <I>[optional] change this line in jmeter.bat for better performance
set HEAP=-Xms1g -Xmx1g -XX:MaxMetaspaceSize=256m ---->>>>  set HEAP=-Xms1g -Xmx1g -XX:MaxMetaspaceSize=1024m
</I>

# Regarding the test case 
we have two category in our tests 
<br> <b>unit 
<br> acceptance</b>

<br>
the unit category will be tested whenever we run normal mvn test <br>
the acceptance category will be tested if we use the exact profile when we run test <br>
mvn test -Pacceptance<br>
