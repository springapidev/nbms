package com.coderbd.noticeboard.model;

import java.util.Date;

public class Teacher {
    private String teacherId;
    private String teacherName;
    private String department;
    private String photo;
    private String email;
    private String mobile;
    private String teacherID;
    private Date joiningDate;
    private String address;
    private String district;
    private String division;
    private String country;
    private boolean status;
    private Institute institute;

    public Teacher() {
    }

    public Teacher(String teacherId, String teacherName, String department, String photo, String email, String mobile, String teacherID, Date joiningDate, String address, String district, String division, String country, boolean status, Institute institute) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.department = department;
        this.photo = photo;
        this.email = email;
        this.mobile = mobile;
        this.teacherID = teacherID;
        this.joiningDate = joiningDate;
        this.address = address;
        this.district = district;
        this.division = division;
        this.country = country;
        this.status = status;
        this.institute = institute;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getDepartment() {
        return department;
    }

    public String getPhoto() {
        return photo;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }

    public String getAddress() {
        return address;
    }

    public String getDistrict() {
        return district;
    }

    public String getDivision() {
        return division;
    }

    public String getCountry() {
        return country;
    }

    public boolean isStatus() {
        return status;
    }

    public Institute getInstitute() {
        return institute;
    }
}
