package com.libman.libman1.model;
import java.util.List;

public class BorrowSlip {
    private int slipId;
    private String readerName;
    private String staffName;
    private String note;
    private List<SlipItemDTO> items;


    public int getSlipId() { return slipId; }
    public void setSlipId(int slipId) { this.slipId = slipId; }
    public String getReaderName() { return readerName; }
    public void setReaderName(String readerName) { this.readerName = readerName; }
    public String getStaffName() { return staffName; }
    public void setStaffName(String staffName) { this.staffName = staffName; }
    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
    public List<SlipItemDTO> getItems() { return items; }
    public void setItems(List<SlipItemDTO> items) { this.items = items; }
}