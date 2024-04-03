package edu.miu.cs489.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Data
@AllArgsConstructor
public class PensionPlan {
    // Constructor, getters, and setters
    private int planReferenceNumber;
    private LocalDate enrollmentDate;
    private double monthlyContribution;

    public void setPlanReferenceNumber(int planReferenceNumber) {
        this.planReferenceNumber = planReferenceNumber;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public void setMonthlyContribution(double monthlyContribution) {
        this.monthlyContribution = monthlyContribution;
    }

    @Override
    public String toString() {
        return "PensionPlan{" +
                "planReferenceNumber=" + planReferenceNumber +
                ", enrollmentDate=" + enrollmentDate +
                ", monthlyContribution=" + monthlyContribution +
                '}';
    }
}
