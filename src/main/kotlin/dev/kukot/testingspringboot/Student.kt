package dev.kukot.testingspringboot

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

data class Student(
    val id: Int,
    val name: String,
    val age: Int
)

interface StudentRepository {
    fun findAllStudent(): List<Student>
    fun findById(id: Int): Student? = findAllStudent().firstOrNull {it.id == id}
}

@Service
class StudentService {
    @Autowired private lateinit var studentRepository: StudentRepository
    fun countAll() : Int {
        return studentRepository.findAllStudent().size
    }

    fun getById(id: Int) = studentRepository.findById(id)

    fun findAll(): List<Student> {
        return studentRepository.findAllStudent()
    }
}

@RestController
class StudentController {
    @Autowired private lateinit var studentService: StudentService

    @GetMapping("/api/v1/students")
    fun getAllStudents() : List<Student>{
        return studentService.findAll()
    }
}