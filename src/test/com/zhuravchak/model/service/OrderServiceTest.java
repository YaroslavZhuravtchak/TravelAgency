package com.zhuravchak.model.service;

import com.zhuravchak.domain.Pass;

import static org.junit.Assert.*;

/**
 * Created by Yaroslav on 01-Oct-17.
 */
public class OrderServiceTest {

    @org.junit.Test
    public void calculatePrice() throws Exception {
      Pass pass = new Pass();
      pass.setPrice(300);
      pass.setDiscountForRegular(25);
      pass.setHot(false);

      boolean isRegular = true;

      double actual = OrderService.getInstance().calculatePrice(isRegular, pass, 2);
      double expected = 450;
        assertEquals(expected, actual, 0.01);

    }

}