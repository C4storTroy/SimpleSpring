package org.home.accounts.model;

import java.util.Calendar;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Account {

    private Long id;

    private String description;

    private boolean paid;

    private double value;

    private Calendar payDay;

    private TypeAccount type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    @Size(min=5, message="{account.form.description.size}")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public Calendar getPayDay() {
        return payDay;
    }

    public void setPayDay(Calendar payDay) {
        this.payDay = payDay;
    }

    public TypeAccount getType() {
        return type;
    }

    public void setType(TypeAccount type) {
        this.type = type;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

}
