package com.coderbd.noticeboard.model;

import java.util.Date;

public class Notice {
    private String noticeId;
    private String title;
    private String details;
    private String pdf;
    private String department;
    private Date createDate;
    private Date publishDate;
    private String createdBy;
    private String approveBy;
    private String instituteId;
    private String status;

    public Notice() {
    }

    public Notice(String noticeId, String title, String details, String pdf, String department, Date createDate, Date publishDate, String createdBy, String approveBy, String instituteId,String status) {
        this.noticeId = noticeId;
        this.title = title;
        this.details = details;
        this.pdf = pdf;
        this.department = department;
        this.createDate = createDate;
        this.publishDate = publishDate;
        this.createdBy = createdBy;
        this.approveBy = approveBy;
        this.instituteId = instituteId;
        this.status = status;
    }

    public String getNoticeId() {
        return noticeId;
    }

    public String getTitle() {
        return title;
    }

    public String getDetails() {
        return details;
    }

    public String getPdf() {
        return pdf;
    }

    public String getDepartment() {
        return department;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getApproveBy() {
        return approveBy;
    }

    public String getInstituteId() {
        return instituteId;
    }
}
