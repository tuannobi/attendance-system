java -jar -DCLOUDINARY_URL=cloudinary://562956475484166:s3gVNbd7HW0MGrUqcWpCPfkZAVk@tuan-tran attendance-system-0.0.1-SNAPSHOT.jar
mvn -DCLOUDINARY_URL=cloudinary://562956475484166:s3gVNbd7HW0MGrUqcWpCPfkZAVk@tuan-tran spring-boot:run

mvn clean package
mvn liquibase:dropAll
mvn liquibase:update
mvn liquibase:generateChangeLog