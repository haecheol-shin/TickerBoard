# backend/Dockerfile

FROM openjdk:17-jdk-slim

# 빌드 결과 JAR 파일 경로
ARG JAR_FILE=build/libs/*.jar

# JAR 파일 복사
COPY ${JAR_FILE} app.jar

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "/app.jar"]
