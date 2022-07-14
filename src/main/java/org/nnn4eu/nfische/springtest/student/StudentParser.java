package org.nnn4eu.nfische.springtest.student;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class StudentParser {
    public static Student parseStrToStudent(String str){
        str.replace(";","");
        if(str.endsWith(","))str=str+" ";
        if(str.startsWith(","))str=" "+str;
        String[] one =str.split(",");
        //Files.lines(Path.of("students.csv")))
        Student st=new Student();
        if(one.length!=4)throw new IllegalArgumentException("student is not properly defined");

        if (!one[0].matches("[0-9]+"))log.info("id not set!!! will use null");
        if (!one[3].matches("[0-9]+"))log.info("age not set!!! will use null");
        try {
            st.setAge(Integer.parseInt(one[3]));
            st.setId(Integer.parseInt(one[0]));
        }
        catch (NumberFormatException e) {
            log.info("id or age are not numbers!!! Using null");
        }

        if(!one[1].matches("\\S+")){
            log.info("name not set!!! will use John Doe");
            one[1]="John Doe";
        }
        st.setName(one[1]);

        if(!one[2].matches("\\S+")){
            log.info("name not set!!! will use XXXXX");
            one[2]="XXXXX";
        }
        st.setZip(one[2]);
        return st;
    }

    private static List<Student> parseStrsToStudents(String[] many){
        return Arrays.stream(many).map(a-> parseStrToStudent(a))
                .distinct().sorted().collect(Collectors.toList());
    }
    public static List<Student> parseStrToStudents(String st){
        return parseStrsToStudents(st.split(";"));
    }

    private static String parseStudentToStr(Student st){
        return new StringBuilder().append(st.getId())
                .append(",")
                .append(st.getName())
                .append(",")
                .append(st.getZip())
                .append(",")
                .append(st.getAge())
                .append(System.lineSeparator())
                .toString();
    }

    public static String parseStudentsToStr(List<Student> students){
        return students.stream()
                .map(a->parseStudentToStr(a))
                .reduce("", (a, b) -> a+b);
    }
}
