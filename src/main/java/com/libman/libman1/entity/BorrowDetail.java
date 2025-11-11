package com.libman.libman1.entity;

import java.time.LocalDate;

public class BorrowDetail {
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private String status;
    private String readerName;
    private int borrowSlipId;

    // Getters và Setters (Alt + Insert)
    public LocalDate getBorrowDate() { return borrowDate; }
    public void setBorrowDate(LocalDate borrowDate) { this.borrowDate = borrowDate; }
    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getReaderName() { return readerName; }
    public void setReaderName(String readerName) { this.readerName = readerName; }
    public int getBorrowSlipId() { return borrowSlipId; }
    public void setBorrowSlipId(int borrowSlipId) { this.borrowSlipId = borrowSlipId; }
}