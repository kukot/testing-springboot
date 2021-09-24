package dev.kukot.testingspringboot

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Controller
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Student(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    val name: String,
    val age: Int
)

interface StudentRepository : JpaRepository<Student, Int>

@Service
class StudentService {
    @Autowired private lateinit var studentRepository: StudentRepository
    fun countAll() : Long {
        return studentRepository.count()
    }

    fun getById(id: Int) = studentRepository.findById(id)

    fun findAll(): List<Student> {
        return studentRepository.findAll()
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