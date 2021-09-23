package dev.kukot.testingspringboot

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
internal class TestStudentFullAppLoad {

    @BeforeEach
    fun setup() {
        Mockito.`when`(studentRepository.findAllStudent()).thenReturn(
            listOf(
                Student(id = 1, name = "Kevin", age = 25),
                Student(id = 2, name = "Peter", age = 28),
                Student(id = 3, name = "Nathan", age = 27)
            )
        )
        Mockito.`when`(studentService.getById(1)).thenReturn(Student(1, "Kevin", 25))
    }

    @MockBean
    private lateinit var studentRepository: StudentRepository

    @Autowired
    private lateinit var studentService: StudentService

    @Test
    fun callingFindAllReturn3Element() {
        assertEquals(3, studentService.countAll())
    }

    @Test
    fun findByIdReturnEitherNullOrOne() {
        assertNull(studentService.getById(10))
        assertEquals(studentService.getById(1), Student(1, "Kevin", 25))
        assertEquals(studentRepository.findById(1), Student(1, "Kevin", 25))
    }

    @Test
    fun findAllReturnAllElements() {
        assertEquals(3, studentService.findAll().size)
    }

}