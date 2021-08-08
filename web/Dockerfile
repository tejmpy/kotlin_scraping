FROM adoptopenjdk/openjdk11
EXPOSE 8080
ENV JAVA_OPTS='-Duser.timezone=Asia/Tokyo'

# waitライブラリの追加
ADD https://github.com/ufoscout/docker-compose-wait/releases/download/2.6.0/wait /wait
# ライブラリの権限変更
RUN chmod +x /wait

COPY web/build/libs/web-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Dspring.profiles.active=docker -jar /app.jar"]