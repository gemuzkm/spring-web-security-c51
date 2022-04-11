package com.example.springwebsecurityc51.dto;

import javax.validation.constraints.NotNull;

public class OperationDTO {
    private static final String MSG_INVALID_VALUE1 = "invalid value1";
    private static final String MSG_INVALID_VALUE2 = "invalid value2";
    private static final String MSG_NO_OPERATOR_IS_SUPPORT = "No operator is supported";

    private static long index = 1;
    private long id;

    @NotNull(message = MSG_INVALID_VALUE1)
    private Double value1;

    @NotNull(message = MSG_INVALID_VALUE2)
    private Double value2;

    @NotNull(message = MSG_NO_OPERATOR_IS_SUPPORT)
    private String operation;

    public OperationDTO() {
    }

    public OperationDTO(Double value1, Double value2, String operation) {
        id = index;
        index++;
        this.value1 = value1;
        this.value2 = value2;
        this.operation = operation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getValue1() {
        return value1;
    }

    public void setValue1(Double value1) {
        this.value1 = value1;
    }

    public Double getValue2() {
        return value2;
    }

    public void setValue2(Double value2) {
        this.value2 = value2;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
