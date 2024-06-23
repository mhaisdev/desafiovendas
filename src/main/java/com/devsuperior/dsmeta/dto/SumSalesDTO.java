package com.devsuperior.dsmeta.dto;

public class SumSalesDTO {
    private String sellerName;
    private Double total;

    public SumSalesDTO() {
    }

    public SumSalesDTO(String sellerName, Double totalAmount) {
        this.sellerName = sellerName;
        this.total = totalAmount;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double totalAmount) {
        this.total = totalAmount;
    }
}

