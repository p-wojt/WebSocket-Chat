FROM openjdk:11
EXPOSE 8080
ADD target/websocket-chat.jar .
RUN echo "sleep 45; java -jar websocket-chat.jar" > run.sh
ENTRYPOINT ["sh", "./run.sh"]