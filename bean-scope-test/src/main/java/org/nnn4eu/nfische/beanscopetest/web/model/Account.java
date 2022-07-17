package org.nnn4eu.nfische.beanscopetest.web.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Account {
    @Id
    @GeneratedValue
    private Long id;
    @Version
    private Long version;
    private String name;
    private String color;

    public String toString(){
        return id+" "+name+" "+color;
    }
}
