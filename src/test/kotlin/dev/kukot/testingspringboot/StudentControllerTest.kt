package dev.kukot.testingspringboot

import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@ExtendWith(SpringExtension::class)
@WebMvcTest(StudentController::class)
class StudentControllerTest {

    @Autowired private lateinit var mockMvc: MockMvc
    @MockBean private lateinit var studentService: StudentService

    private val students = listOf(
        Student(id = 1, name = "Kevin", age = 25),
        Student(id = 2, name = "Peter", age = 28),
        Student(id = 3, name = "Nathan", age = 27)
    )

    @Test
    fun testFindAll() {
        BDDMockito.given(studentService.findAll()).willReturn(students)

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/students").contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize<String>(3)))
//            .andExpect(jsonPath("$", hasSize(10)))
    }
}