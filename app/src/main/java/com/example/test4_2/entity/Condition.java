package com.example.test4_2.entity;

public class Condition {
    private Integer conditionIcon;
    private String conditionName;

    public Integer getConditionIcon() {
        return conditionIcon;
    }

    public void setConditionIcon(Integer conditionIcon) {
        this.conditionIcon = conditionIcon;
    }

    public String getConditionName() {
        return conditionName;
    }

    public void setConditionName(String conditionName) {
        this.conditionName = conditionName;
    }

    @Override
    public String toString() {
        return "Condition{" +
                "conditionIcon=" + conditionIcon +
                ", conditionName='" + conditionName + '\'' +
                '}';
    }
}
