package com.company.service;

import com.company.GradeMapper;
import com.company.LocalDateComparator;
import com.company.StudentMapper;
import com.company.TotalAveragesStudentComparator;
import com.company.dto.GradeResponse;
import com.company.dto.StudentRequest;
import com.company.dto.StudentResponse;
import com.company.entity.Grade;
import com.company.entity.SchoolClass;
import com.company.entity.Student;
import com.company.entity.Subject;
import com.company.repository.GradeRepository;
import com.company.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private StudentRepository studentRepository;
    private SchoolClassService schoolClassService;
    private SubjectService subjectService;
    private StudentMapper studentMapper;
    private GradeMapper gradeMapper;


    public StudentService(
            StudentRepository studentRepository,
            SchoolClassService schoolClassService,
            SubjectService subjectService,
            StudentMapper mapper,
            GradeMapper gradeMapper
    ) {
        this.studentRepository = studentRepository;
        this.schoolClassService = schoolClassService;
        this.subjectService = subjectService;
        this.studentMapper = mapper;
        this.gradeMapper = gradeMapper;

    }

    public boolean checkIfStudentExistsByName(String firstName, String lastName) {
        return studentRepository.existsByFirstNameAndLastName(firstName, lastName);
    }

    public List<StudentResponse> getAllStudents() {
        List<Student> students = studentRepository.findAll();

        return students.stream()
                .map(student -> studentMapper.mapEntityToResponse(student))
                .collect(Collectors.toList());
    }


    public void saveStudent(Student student) {
        studentRepository.save(student);
    }


    @Transactional
    public StudentResponse addNewStudent(StudentRequest studentRequest) {

        SchoolClass schoolClass = schoolClassService.findById(studentRequest.getClassId());

        Student student = studentMapper.mapRequestToEntity(studentRequest);

        student.addStudentToClass(schoolClass);
        addSubjectsToStudent(student);
        schoolClassService.saveClass(schoolClass);

        return studentMapper.mapEntityToResponse(student);
    }

    @Transactional
    public StudentResponse changeStudentClass(Long studentId, Long classId) {
        SchoolClass newSchoolClass = schoolClassService.findById(classId);
        Student student = findById(studentId);

        if (!(student.getSchoolClass() == null)) {
            if (student.getSchoolClass().getId().equals(classId)) {
                throw new IllegalArgumentException("The student is already assigned to the " + newSchoolClass.getClassName() + " class");
            }
        }
        student.setSchoolClass(newSchoolClass);
        studentRepository.save(student);

        return studentMapper.mapEntityToResponse(student);
    }


    public Student findById(Long studentId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);

        if (studentOptional.isPresent()) {
            return studentOptional.get();
        } else {
            throw new NullPointerException("Student does not exist");
        }
    }

    @Transactional
    public StudentResponse deleteStudentById(Long studentId) {

        Student student = findById(studentId);

        studentRepository.deleteById(studentId);
        return StudentResponse.builder()
                .id(studentId)
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .totalAverage(student.getTotalAverage())
                .classId(student.getSchoolClass().getId())
                .build();

    }

    public void addSubjectsToStudent(Student student) {
        List<Subject> subjects = subjectService.findAll();

        for (Subject subject : subjects) {
            student.addSubjectToStudent(subject);
        }
    }

    @Transactional
    public GradeResponse addNewGradeToStudent(Long studentId, Long subjectId, Integer gradeValue, LocalDate date) {

        if (gradeValue >= 1 && gradeValue <= 6) {

            Student student = findById(studentId);
            Subject subject = subjectService.findById(subjectId);

            Grade grade = new Grade(student, subject, gradeValue, date);
            grade.addGradeToStudentAndSubject();

            studentRepository.save(student);

            return gradeMapper.mapEntityToResponse(grade);

        } else {
            throw new IllegalArgumentException("Grade must be between 1 and 6.");
        }

    }

    @Transactional
    public Map<String, Double> getStudentAveragesForAllSubjects(Long studentId) {
        Student student = findById(studentId);
        return student.calculateAveragesForAllSubjects();
    }

    public Map<Long, Double> getAllStudentsTotalAverages() {
        List<Student> students = studentRepository.findAll().stream()
                .sorted(new TotalAveragesStudentComparator())
                .toList();

        Map<Long, Double> totalAverages = new HashMap<>();

        for (Student student : students) {
            Long id = student.getId();
//            Double totalAverage = student.calculateStudentTotalAverage();
            Double totalAverage = student.getTotalAverage();

            totalAverages.put(id, totalAverage);
        }

        return totalAverages;
    }

    @Transactional
    public List<GradeResponse> getStudentGradesBetweenDates(Long studentId, Long subjectId, LocalDate fromDate, LocalDate toDate) {


        Student student = findById(studentId);
        Subject subject = subjectService.findById(subjectId);

        List<GradeResponse> gradesBetween = student.getGrades().stream()
                .filter(grade -> grade.getSubject().equals(subject))
                .filter(grade -> grade.getDate().isAfter(fromDate) && grade.getDate().isBefore(toDate))
                .sorted(new LocalDateComparator())
                .map(grade -> gradeMapper.mapEntityToResponse(grade))
                .collect(Collectors.toList());

        return gradesBetween;

    }
}
