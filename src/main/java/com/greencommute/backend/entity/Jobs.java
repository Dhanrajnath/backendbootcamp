package com.greencommute.backend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jobs")
public class Jobs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private int jobId;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "job_description")
    private String jobDescription;

    @Column(name = "job_location")
    private String jobLocation;

    @ToString.Exclude
    @ManyToMany
    @JoinColumn(name = "skill_id")
    @JsonManagedReference
    private List<Skills> skillList;

    @ToString.Exclude
    @ManyToMany
    @JoinColumn(name = "skill_id")
    @JsonManagedReference
    private List<CommuteRoutes> commuteList;
}
