package com.libman.libman1.model;


import java.time.LocalDate;

public class ReaderCard {
    private int id;
    private int readerId;
    private LocalDate issueDate;
    private LocalDate expirationDate;
    private String status;
    private String cardType;


    public ReaderCard() {
    }

    
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
