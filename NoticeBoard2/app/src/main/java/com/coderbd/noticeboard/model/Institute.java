package com.coderbd.noticeboard.model;

public class Institute {
    private String instituteId;
    private String instituteName;
    private String logo;
    private String email;
    private String mobile;
    private String regiCode;
    private String establishedYear;
    private String address;
    private String district;
    private String division;
    private String country;
    private boolean status;


    public Institute() {
    }

    public Institute(String instituteId) {
        this.instituteId = instituteId;
    }

    public Institute(String instituteId, String instituteName, String logoOrphoto, String email, String mobile, String regiCode, String establishedYear, String address, String district, String division, String country, boolean status) {
        this.instituteId = instituteId;
        this.instituteName = instituteName;
        this.logo = logoOrphoto;
        this.email = email;
        this.mobile = mobile;
        this.regiCode = regiCode;
        this.establishedYear = establishedYear;
        this.address = address;
        this.district = district;
        this.division = division;
        this.country = country;
        this.status = status;

    }

    public String getInstituteId() {
        return instituteId;
    }

    public String getInstituteName() {
        return instituteName;
    }

    public String getLogo() {
        return logo;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getRegiCode() {
        return regiCode;
    }

    public String getEstablishedYear() {
        return establishedYear;
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
}
