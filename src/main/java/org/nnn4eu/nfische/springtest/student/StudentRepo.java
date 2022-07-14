package org.nnn4eu.nfische.springtest.student;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

@Slf4j
@Service
public class StudentRepo {
    private final static String PATHST="students.csv";
    //Paths.get(getClass().getResource("/"+filename).toURI()).toString()

    public List<Student> getStudents() throws IOException {
        Path mpath=ResourceUtils.getFile("classpath:"+PATHST).toPath();
        return
                Files.lines(mpath)
                .skip(1)
                .distinct().filter(a->!a.isEmpty())
                .map(StudentParser::parseStrToStudent)
                .sorted()
                .collect(Collectors.toList());

    }

    public List<Student> getStudentsOld() throws IOException {
        List<Student> students=new ArrayList<>();
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(PATHST);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + PATHST);
        } else {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            int i = 0;
            while(reader.ready()) {
                if(i>0){
                    String line = reader.readLine();
                    if(!line.isEmpty()){
                        students.add(StudentParser.parseStrToStudent(line));
                    }
                }
                i++;
            }
            return students;
        }

    }

    public boolean addStudents(List<Student> students) throws IOException {
        return writeToFile(StudentParser.parseStudentsToStr(students));
    }

    private boolean writeToFile(final String s) throws IOException {
        if (s.isEmpty() || s.trim().isEmpty()) {
            log.info("Your student is empty!!");
            return false;
        }
        Path mpath=ResourceUtils.getFile("classpath:"+PATHST).toPath();
        Path mp=Files.writeString(
                mpath,
                System.lineSeparator()+s,
                CREATE, APPEND
        );
        log.info("written: \n"+s);
        log.info("have written to "+mp.toString());
        //Path.of(mpath)
        //Files.write(path, Arrays.asList("New line to append"), StandardCharsets.UTF_8,
        //        Files.exists(path) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
        return true;
    }
}
