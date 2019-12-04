FROM java:8
ADD . .
EXPOSE 80

CMD export IP=$(curl http://169.254.169.254/latest/meta-data/local-ipv4); wget -O dd-java-agent.jar 'https://search.maven.org/classic/remote_content?g=com.datadoghq&a=dd-java-agent&v=LATEST'; java -javaagent:dd-java-agent.jar -Ddd.agent.host=$IP -Ddd.agent.port=8126 -Ddd.service.name=credit-score -jar rest-apis-0.0.1-SNAPSHOT.jar --server.port=80;
