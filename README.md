# http2go

This project is an evaluation how to get http2 running in the following setup:

* Spring Boot
* Undertow
* Java JDK 1.8.0_65

# How to get this project running

## Create your own keystore
In directory /src/main/resources you will find a keystore.jks. It's a self-signed
keypair that is necessary for this server get running.

Do this command to get your own key:
```
keytool -genkeypair -alias mycert -keyalg RSA -sigalg SHA256withRSA -keystore keystore.jks -storepass secret -keypass secret -validity 9999
```

If you change user and password, also change entries in application.properties.
You will be asked for several values. In field for "first and last name",
add 'localhost'.

## Select right alpn bootstrap version for JDK 7 and 8
http2 uses a new protocol ALPN that isn't natively available for JDK 8 and 9.
So, it's necessary to find the right extension. Go to this page:

http://www.eclipse.org/jetty/documentation/current/alpn-chapter.html#alpn-versions

On the page you can find Java versions and their corresponding alpn-boot
versions. When found, get the version number.

Go to build.gradle and replace the apn-boot entry version with the 
version you need. When it's downloaded, you should go to your .gradle 
directory in YOUR USER DIRECTORY, search for alpn-boot and copy the right
jar in this project root directory.

## Start application
The ALPN  implementation needs to be injected during VM start. It is done
by a command for java:

``` 
-Xbootclasspath/p:./alpn-boot-8.1.6.v20151105.jar
```

ATTENTION. I use JDK 1.8.0_65, so alpn-boot-8.1.6.v20151105.jar is the
right version. I put the jar into the root of this project. So I can use
 the path ./ here. Please adapt this to your needs.

### in IntelliJ
I use IntelliJ, search Application.groovy and create a "Run" configuration.
When done, add the following paramater into field 'VM options'. 
 
``` 
-Xbootclasspath/p:...
```

### on command line
Go to the root of this project and do

```
gradle build
```

Do this command

```
java -Xbootclasspath/p:./alpn-boot-8.1.6.v20151105.jar -jar build/libs/http2go-0.1.0.jar
```

### Check in browser
When done, start https://localhost:8443 . Since the certificate has been
self-signed, you will get some warnings. Clarify them by load certificate
anyway. When done, you should see a welcome page. 

Attention: undertow implements some fallback mechanisms. In the case, the correct alpn
configuration hasn't been found, it switches back to https via HTTP1/1.

To verify to use HTTP2, check the headers and search for elements like this (this is
what Firefox shows):

```
HTTP/2.0 200 OK
X-Firefox-Spdy: h2
```

This application is ready to communicate via HTTP2.

# References
* [alpn] http://www.eclipse.org/jetty/documentation/current/alpn-chapter.html
* [bclozel] https://github.com/bclozel/http2-experiments
* [boothttp2] https://vanwilgenburg.wordpress.com/2016/04/01/spring-boot-http2/
* [bootstart] https://spring.io/guides/gs/spring-boot/
* [undertow] http://undertow.io/
