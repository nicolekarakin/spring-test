package org.nnn4eu.nfische.springtest.student;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/student")
public class StudentCtrl {
    private final StudentRepo studentRepo;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Student>> getSudets() throws IOException {
            return new ResponseEntity<>(studentRepo.getStudents(), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("q")
    public void addStudents(@RequestParam (required = false, value = "st") String st)
            throws IOException {
        log.info("we got st: "+st);
        if(st==null || st.trim().isEmpty() )
            throw new IllegalArgumentException("no student(s) in the Query!");
        if(!studentRepo.addStudents(StudentParser.parseStrToStudents(st)))
            throw new RuntimeException("something went very wrong");
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addStudents(@RequestBody List<Student> students) throws IOException {
        log.info("we got students: "+ Arrays.toString(students.toArray()));
        if(students==null || students.isEmpty())
            throw new IllegalArgumentException("no student(s) in the Body!!");
        if(!studentRepo.addStudents(students))
            throw new RuntimeException("something went very wrong");
    }
}


/*
curl -X GET http://localhost:8080/api/v1/student


curl -X POST -d st="25,Uta,1WE33,28;26,Nero,12UZ2,34;27,,12345," \
-H "Content-Type: application/x-www-form-urlencoded"  \
http://localhost:8080/api/v1/student/q

curl -X POST "http://localhost:8080/api/v1/student/q?st=21,NewMarleene,12345,20"

%3B >> ;
%2C >> ,

curl -d '[{"id":51, "name":"Polly","zip":"FG567","age":11},{"id":52, "name":"Stiv","zip":"FG678","age":12},{"name":"NoId","zip":"","age":1}]' \
-X POST -H "Content-Type: application/json"  \
http://localhost:8080/api/v1/student

*/
