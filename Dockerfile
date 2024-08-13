# Bước 1: Sử dụng image Maven chính thức với JDK 17
FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package

# Bước 2: Sử dụng image JDK nhỏ gọn để chạy ứng dụng
FROM openjdk:17-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
