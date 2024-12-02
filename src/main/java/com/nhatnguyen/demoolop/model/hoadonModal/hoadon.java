package com.nhatnguyen.demoolop.model.hoadonModal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class hoadon {
    private long mahoadon;
    private long makh;
    Date ngaymua;
    boolean damua;
}
