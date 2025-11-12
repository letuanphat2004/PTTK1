package com.libman.libman1.model;


public class DocumentStatistic {
    private int documentId;
    private String documentTitle;
    private String documentAuthor;
    private int borrowCount;


    public DocumentStatistic() {}

    // Getters và Setters (Alt + Insert)
    public int getDocumentId() { return documentId; }
    public void setDocumentId(int documentId) { this.documentId = documentId; }
    public String getDocumentTitle() { return documentTitle; }
    public void setDocumentTitle(String documentTitle) { this.documentTitle = documentTitle; }
    public String getDocumentAuthor() { return documentAuthor; }
    public void setDocumentAuthor(String documentAuthor) { this.documentAuthor = documentAuthor; }
    public int getBorrowCount() { return borrowCount; }
    public void setBorrowCount(int borrowCount) { this.borrowCount = borrowCount; }
}