package com.hof.wovenyautoproductentry.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductMapperTest {

    @InjectMocks
    ProductMapper productMapper;

    @Test
    public void it_should_Name() {
        //given
        String c = "RUGS///HANDWOVEN KILIM RUGS:::RUGS///HANDWOVEN KILIM RUGS///Colorful Kilims:::RUGS:::MINI RUGS | DOOR MATS";
        //when
        productMapper.getProductType(c);
        //then
    }
}