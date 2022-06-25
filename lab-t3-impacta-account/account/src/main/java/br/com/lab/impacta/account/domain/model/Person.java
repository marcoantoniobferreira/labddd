package br.com.lab.impacta.account.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

//classe anêmica = não tem características
/*
    O certo seria ter classes separadas para o model e para o entity
    igual como o CSharp faz com o entity framework
*/
@Data
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String document;
    
}
