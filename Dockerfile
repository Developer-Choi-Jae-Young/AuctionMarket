FROM openjdk:17

WORKDIR /app

COPY build/libs/AuctionMarket-0.0.1-SNAPSHOT.jar simpleProj.jar

CMD ["java", "-jar", "simpleProj.jar"]