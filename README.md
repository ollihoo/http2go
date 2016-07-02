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
So, it's necessary to find the right extension. Go to this page([alpn]):

http://www.eclipse.org/jetty/documentation/current/alpn-chapter.html

On the page you can find Java versions and their corresponding alpn-boot
versions. When found, get the version number.

Go to build.gradle and replace the apn-boot entry version with the 
version you need. When it's downloaded, you should go to your .gradle 
directory in YOUR USER DIRECTORY, search for alpn-boot and copy the right
jar in this project root directory.

## Start application
I use IntelliJ, so I explain it how to start it here.

Search Application.groovy and create a "Run" configuration. When done,
add the following paramater to VM options. ATTENTION. I use JDK 1.8.0_65,
so alpn-boot-8.1.6.v20151105.jar is the right version (see above!!):
 
``` 
-Xbootclasspath/p:./alpn-boot-8.1.6.v20151105.jar
```

## Start Application
When done, start https://localhost:8443 . Since the certificate has been
self-signed, you will get some warnings. Clarify them by load certificate
anyway. When done, you should see "Welcome". Hooray, it's done.

# References
* [alpn] http://www.eclipse.org/jetty/documentation/current/alpn-chapter.html
* [bclozel] https://github.com/bclozel/http2-experiments
* [boothttp2] https://vanwilgenburg.wordpress.com/2016/04/01/spring-boot-http2/
* [bootstart] https://spring.io/guides/gs/spring-boot/
* [undertow] http://undertow.io/
