package org.launchcode.javawebdevtechjobspersistent.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employer extends AbstractEntity {
    //Validation
    @NotBlank
    @Size(min=5, max=50, message="Location must be between 5 and 50 characters")
    private String location;

    //TODO: Use the @OneToMany and @JoinColumn annotations on the jobs list in Employer to declare the relationship between data tables.
    @OneToMany
    @JoinColumn
    private List<Job> jobs = new ArrayList<>();


    //No Arg Constructor
    public Employer(){
    }

    //Getters and Setter
    public List<Job> getJobs() {
        return jobs;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

