package com.bookmyconsultation.doctorservice.model.dto;

import java.util.Objects;

public class ApprovalDTO {

    private String approvedBy;
    private String approverComments;

    public ApprovalDTO() {}

    public ApprovalDTO(String approvedBy, String approverComments) {
        this.approvedBy = approvedBy;
        this.approverComments = approverComments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApprovalDTO that = (ApprovalDTO) o;
        return Objects.equals(approvedBy, that.approvedBy) && Objects.equals(approverComments, that.approverComments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(approvedBy, approverComments);
    }

    @Override
    public String toString() {
        return "approvalDTO{" +
                "approvedBy='" + approvedBy + '\'' +
                ", approverComments='" + approverComments + '\'' +
                '}';
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public String getApproverComments() {
        return approverComments;
    }

    public void setApproverComments(String approverComments) {
        this.approverComments = approverComments;
    }
}
