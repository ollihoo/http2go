# http2go

In this project I follow the steps described in [undertow] instructions. Goal is
to get a service running that supports http 2.0.

Unfortunately, I found instructions for jetty. To be frank. What a bloody
mess. I almost spend one day to understand and tidy up dependencies. 

Just have a look on build.gradle and study exclusions.

Furthermore you need to include 'alpn-boot-8.1.7.v20160121.jar' to your
boot classpath. This is done by this command

-Xbootclasspath/p:<PATH_TO_YOUR_JAR>/alpn-boot-8.1.7.v20160121.jar

Yes, that's crappy. No good job by jetty guys, sorry!!!

## References
* [alpn] http://www.eclipse.org/jetty/documentation/current/alpn-chapter.html
* [bclozel] https://github.com/bclozel/http2-experiments
* [boothttp2] https://vanwilgenburg.wordpress.com/2016/04/01/spring-boot-http2/
* [bootstart] https://spring.io/guides/gs/spring-boot/
* [undertow] http://undertow.io/
