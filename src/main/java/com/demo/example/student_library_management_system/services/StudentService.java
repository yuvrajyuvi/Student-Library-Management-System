package com.demo.example.student_library_management_system.services;

import com.demo.example.student_library_management_system.Enums.CardStatus;
import com.demo.example.student_library_management_system.converters.StudentConverter;
import com.demo.example.student_library_management_system.model.Card;
import com.demo.example.student_library_management_system.model.Student;
import com.demo.example.student_library_management_system.repository.StudentRepository;
import com.demo.example.student_library_management_system.requestdto.StudentRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public String saveStudent(StudentRequestDto studentRequestDto){
        Student student= StudentConverter.convertStudentRequestDtoIntoStudent(studentRequestDto);

        Card card = new Card();
        card.setCardStatus(CardStatus.ACTIVE);
        card.setStudent(student);

        student.setCard(card);
        studentRepository.save(student);
        return "student saved successfully";
    }

    public Student findStudentById(int id){
        Optional<Student>studentOptional=studentRepository.findById(id);
        if(studentOptional.isPresent()){
            return studentOptional.get();
        }
        else{
            return null;
        }
    }
    public List<Student> findAllStudent(){
        List<Student> studentList= studentRepository.findAll();
        return studentList;
    }
    public List<Student> findStudentByPageAndSort(int pageNo, int pageSize){
        List<Student>studentList=studentRepository.findAll(PageRequest.of(pageNo,pageSize, Sort.by("name").ascending())).getContent();
        return studentList;
    }

    public List<Student> findBySemAndDept(String inSem,String inDept){
        List<Student> studentList=studentRepository.findBySemAndDept(inSem,inDept);
        return studentList;
    }
    public String deleteStudentById(int id){
        studentRepository.deleteById(id);
        return "student deleted successfully";
    }
    public String updateStudent(int id,StudentRequestDto studentRequestDto){
        Student student=findStudentById(id);
        if(student!=null){
            student.setName(studentRequestDto.getName());
            student.setDept(studentRequestDto.getDept());
            student.setSem(studentRequestDto.getSem());
            student.setDob(studentRequestDto.getDob());
            student.setEmail(studentRequestDto.getEmail());
            student.setMobile(studentRequestDto.getMobile());
            student.setGender(studentRequestDto.getGender());
            student.setAddress(studentRequestDto.getAddress());

            studentRepository.save(student);
            return "student update is success";
        }else{
            return "student is not present updating  failed";
        }
    }
    public String countStudent(){
        long totalCount = studentRepository.count();
        return "total count of students "+totalCount;
    }
}
