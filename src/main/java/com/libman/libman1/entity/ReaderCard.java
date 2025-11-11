package com.libman.libman1.entity;


import java.time.LocalDate;

public class ReaderCard {
    private int id;
    private int readerId; // Khóa ngoại, trỏ đến Users.id
    private LocalDate issueDate; // Ngày cấp
    private LocalDate expirationDate; // Ngày hết hạn
    private String status;
    private String cardType;

    // Constructor mặc định
    public ReaderCard() {
    }

    // Constructor để TẠO MỚI (chưa có ID)
    public ReaderCard(int readerId, LocalDate issueDate, LocalDate expirationDate, String status, String cardType) {
        this.readerId = readerId;
        this.issueDate = issueDate;
        this.expirationDate = expirationDate;
        this.status = status;
        this.cardType = cardType;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getReaderId() { return readerId; }
    public void setReaderId(int readerId) { this.readerId = readerId; }
    public LocalDate getIssueDate() { return issueDate; }
    public void setIssueDate(LocalDate issueDate) { this.issueDate = issueDate; }
    public LocalDate getExpirationDate() { return expirationDate; }
    public void setExpirationDate(LocalDate expirationDate) { this.expirationDate = expirationDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getCardType() { return cardType; }
    public void setCardType(String cardType) { this.cardType = cardType; }
}
