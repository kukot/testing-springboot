package dev.kukot.testingspringboot

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
//import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.TestPropertySource
import javax.persistence.EntityManager
import javax.sql.DataSource

@DataJpaTest
@TestPropertySource(locations = ["classpath:applications.properties"])
class StudentRepositoryTest {

    @Autowired
    private lateinit var dataSource: DataSource
    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate
    @Autowired
    private lateinit var entityManager: EntityManager
    @Autowired
    private lateinit var studentRepository: StudentRepository

    @Test
    @DisplayName("make sure all dependencies are injected")
    fun checkDependencies() {
        assertNotNull(dataSource)
        assertNotNull(jdbcTemplate)
        assertNotNull(entityManager)
        assertNotNull(studentRepository)
    }
}