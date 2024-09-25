package com.evaluation.entity;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "课程实体类")
public class CourseEntity {
    private Integer id;

    private String courseName;

    private String courseScore;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseScore() {
        return courseScore;
    }

    public void setCourseScore(String courseScore) {
        this.courseScore = courseScore;
    }
}