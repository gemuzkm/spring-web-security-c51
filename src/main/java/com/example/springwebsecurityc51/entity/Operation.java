package com.example.springwebsecurityc51.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="history")
@Data
@NoArgsConstructor
public class Operation {
    private static final String MSG_INVALID_VALUE1 = "invalid value1";
    private static final String MSG_INVALID_VALUE2 = "invalid value2";
    private static final String MSG_NO_OPERATOR_IS_SUPPORT = "No operator is supported";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = MSG_INVALID_VALUE1)
    private Double value1;

    @NotNull(message = MSG_INVALID_VALUE2)
    private Double value2;

    @NotNull(message = MSG_NO_OPERATOR_IS_SUPPORT)
    private String operation;

    private Double result;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    public Operation(Double value1, Double value2, String operation) {
        this.value1 = value1;
        this.value2 = value2;
        this.operation = operation;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", value1=" + value1 +
                ", value2=" + value2 +
                ", operation='" + operation + '\'' +
                ", result=" + result +
                '}';
    }
}
