package com.libman.libman1.entity;
import java.util.List;

public class BorrowSlip {
    private int slipId;
    private String readerName;
    private String staffName;
    private String note;
    private List<SlipItem> items; // Danh sách các tài liệu đã mượn

    // Getters và Setters
    public int getSlipId() { return slipId; }
    public void setSlipId(int slipId) { this.slipId = slipId; }
    public String getReaderName() { return readerName; }
    public void setReaderName(String readerName) { this.readerName = readerName; }
    public String getStaffName() { return staffName; }
    public void setStaffName(String staffName) { this.staffName = staffName; }
    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
    public List<SlipItem> getItems() { return items; }
    public void setItems(List<SlipItem> items) { this.items = items; }
}