package com.customer.database;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
    "spring.datasource.url=jdbc:mysql://localhost:3306/customer_db_test?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
    "spring.jpa.hibernate.ddl-auto=create-drop",
    "spring.sql.init.mode=never"
})
class CustomerDatabaseApplicationTests {

    @Test
    void contextLoads() {
    }
}
