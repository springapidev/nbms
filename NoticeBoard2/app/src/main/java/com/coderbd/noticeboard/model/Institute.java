package com.coderbd.noticeboard.model;

public class Institute {
    private String instituteId;
    private String instituteName;
    private String email;
    private String mobile;
    private String regiCode;
    private String establishedYear;
    private String address;
    private String district;
    private String division;
    private String country;

    public Institute() {
    }

    public Institute(String instituteId, String instituteName, String email, String mobile, String regiCode, String establishedYear, String address, String district, String division, String country) {
        this.instituteId = instituteId;
        this.instituteName = instituteName;
        this.email = email;
        this.mobile = mobile;
        this.regiCode = regiCode;
        this.establishedYear = establishedYear;
        this.address = address;
        this.district = district;
        this.division = division;
        this.country = country;
    }

    public String getInstituteId() {
        return instituteId;
    }

    public String getInstituteName() {
        return instituteName;
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
}
