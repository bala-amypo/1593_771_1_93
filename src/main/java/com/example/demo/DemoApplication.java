spring.application.name=demo
server.port = 9001
server.forward-headers-strategy=framework 

spring.datasource.url=jdbc:mysql://localhost:3306/mydb?createDatabaseIfNotExist=true
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=Amypo

spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true