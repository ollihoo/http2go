FROM java:8

ENV builddir=build/docker
ENV workdir=/springapp
ENV alpnbootname=alpn-boot-8.1.7.v20160121.jar
ENV javaversion=1.8.0_91

RUN mkdir ${workdir}
RUN mkdir ${workdir}/logs
RUN mkdir ${workdir}/config

ADD application.properties ${workdir}/config/application.properties
ADD ${alpnbootname} ${workdir}/alpn-boot.jar
ADD http2go-0.1.0.jar ${workdir}/app.jar

EXPOSE 8443
VOLUME ${workdir}/logs
CMD java -Xbootclasspath/p:${workdir}/alpn-boot.jar -jar ${workdir}/app.jar
