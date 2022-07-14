package org.nnn4eu.nfische.springtest.student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Comparator;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Student implements Comparable<Student>{
    private Integer id;
    private String name;
    private String zip;
    private Integer age;

    @Override
    public int compareTo(Student other) {
        int classComparison =
                this.getClass().getName().compareTo(other.getClass().getName());

        if (classComparison != 0) {
            return classComparison;
        }
//.comparing(Student::getId)
        return Comparator.comparing(p-> ((Student)p).getId(), Comparator.nullsLast(Comparator.naturalOrder()))
                .thenComparing(p-> ((Student)p).getId(), Comparator.nullsLast(Comparator.naturalOrder()))
                .thenComparing(p-> ((Student)p).getName(), Comparator.nullsLast(Comparator.naturalOrder()))
                .compare(this, other);

    }
}
