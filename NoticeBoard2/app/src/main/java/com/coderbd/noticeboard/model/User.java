package com.coderbd.noticeboard.model;

import java.util.Date;

public class User {
    private String userId;
    private String name;
    private String logoOrPhoto;
    private String email;
    private String mobile;

    private String address;
    private String district;
    private String division;
    private String country;
    private boolean status;
    private Date createDate;
    private String instituteId;
    //only for Institute
    private String regiCode;
    private String establishedYear;
    private String userType;
    //only for teacher and student
    private String department;

    //only for student
    private String registrationID;
    private String sessionOrYear;


    //only for Teacher
    private String designation;


    public User() {
    }

    public User(String userId, String name, String logoOrPhoto, String email, String mobile, String address, String district, String division, String country, boolean status, Date createDate, String instituteId, String regiCode, String establishedYear, String userType, String department, String registrationID, String sessionOrYear, String designation) {
        this.userId = userId;
        this.name = name;
        this.logoOrPhoto = logoOrPhoto;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
        this.district = district;
        this.division = division;
        this.country = country;
        this.status = status;
        this.createDate = createDate;
        this.instituteId = instituteId;
        this.regiCode = regiCode;
        this.establishedYear = establishedYear;
        this.userType = userType;
        this.department = department;
        this.registrationID = registrationID;
        this.sessionOrYear = sessionOrYear;
        this.designation = designation;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getLogoOrPhoto() {
        return logoOrPhoto;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
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

    public Date getCreateDate() {
        return createDate;
    }

    public String getInstituteId() {
        return instituteId;
    }

    public String getRegiCode() {
        return regiCode;
    }

    public String getEstablishedYear() {
        return establishedYear;
    }

    public String getUserType() {
        return userType;
    }

    public String getDepartment() {
        return department;
    }

    public String getRegistrationID() {
        return registrationID;
    }

    public String getSessionOrYear() {
        return sessionOrYear;
    }

    public String getDesignation() {
        return designation;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", logoOrPhoto='" + logoOrPhoto + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", address='" + address + '\'' +
                ", district='" + district + '\'' +
                ", division='" + division + '\'' +
                ", country='" + country + '\'' +
                ", status=" + status +
                ", createDate=" + createDate +
                ", instituteId='" + instituteId + '\'' +
                ", regiCode='" + regiCode + '\'' +
                ", establishedYear='" + establishedYear + '\'' +
                ", userType='" + userType + '\'' +
                ", department='" + department + '\'' +
                ", registrationID='" + registrationID + '\'' +
                ", sessionOrYear='" + sessionOrYear + '\'' +
                ", designation='" + designation + '\'' +
                '}';
    }
}
