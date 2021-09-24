package dev.kukot.testingspringboot

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Bean
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.*

@ExtendWith(SpringExtension::class)
class TestWithTestConfiguration {

    @MockBean
    private lateinit var studentRepository: StudentRepository

    @Autowired
    private lateinit var studentService: StudentService

    @TestConfiguration
    class ConfigTest {
        @Bean
        fun studentService() : StudentService {
            return StudentService()
        }
    }
    @BeforeEach
    fun setup() {
        Mockito.`when`(studentRepository.findAll()).thenReturn(
            listOf(
                Student(id = 1, name = "Kevin", age = 25),
                Student(id = 2, name = "Peter", age = 28),
                Student(id = 3, name = "Nathan", age = 27)
            )
        )
        Mockito.`when`(studentService.getById(1)).thenReturn(Optional.of(Student(1, "Kevin", 25)))
    }

    @Test
    fun callingFindAllReturn3Element() {
        Assertions.assertEquals(3, studentService.countAll())
    }

    @Test
    fun findByIdReturnEitherNullOrOne() {
        Assertions.assertNull(studentService.getById(10))
        Assertions.assertEquals(studentService.getById(1), Student(1, "Kevin", 25))
        Assertions.assertEquals(studentRepository.findById(1), Student(1, "Kevin", 25))
    }

}