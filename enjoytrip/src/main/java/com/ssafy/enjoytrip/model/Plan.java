package com.ssafy.enjoytrip.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name="plan")
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int plan_id; // primary key
    @Column(name="user_id")
    private Long user_id; // foreign key
    @Column(name="plan_name")
    private String plan_name;

    @ColumnDefault("false")
    @Column(columnDefinition = "TINYINT(1)")
    private boolean blocked;

    private String thumbnail;


}
