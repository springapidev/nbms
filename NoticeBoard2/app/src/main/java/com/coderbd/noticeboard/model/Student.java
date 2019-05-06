package com.coderbd.noticeboard.model;

import java.util.Date;

public class Student {
    private String studentId;
    private String studentName;
    private String department;
    private String photo;
    private String email;
    private String mobile;
    private String registrationID;
    private Date admissionDate;
    private String address;
    private String district;
    private String division;
    private String country;
    private boolean status;

    private Institute institute;


    public Student() {
    }

    public Student(String studentId, String studentName, String department, String photo, String email, String mobile, String registrationID, Date admissionDate, String address, String district, String division, String country, boolean status, Institute institute) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.department = department;
        this.photo = photo;
        this.email = email;
        this.mobile = mobile;
        this.registrationID = registrationID;
        this.admissionDate = admissionDate;
        this.address = address;
        this.district = district;
        this.division = division;
        this.country = country;
        this.status = status;
        this.institute = institute;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
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

    public String getRegistrationID() {
        return registrationID;
    }

    public Date getAdmissionDate() {
        return admissionDate;
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
