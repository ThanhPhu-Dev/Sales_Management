/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SalesManagement.model;

/**
 *
 * @author NghiaDX
 */
public class Customer {
    private int id;
    private String name;
    private String phone;
    private String numberCard;
    private double balance;
    private int saleId;
    private double debtLimited;

    public Customer(int id, String name, String phone, String numberCard, double balance, int saleId, double debtLimited) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.numberCard = numberCard;
        this.balance = balance;
        this.saleId = saleId;
        this.debtLimited = debtLimited;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNumberCard() {
        return numberCard;
    }

    public void setNumberCard(String numberCard) {
        this.numberCard = numberCard;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public double getDebtLimited() {
        return debtLimited;
    }

    public void setDebtLimited(double debtLimited) {
        this.debtLimited = debtLimited;
    }
}
