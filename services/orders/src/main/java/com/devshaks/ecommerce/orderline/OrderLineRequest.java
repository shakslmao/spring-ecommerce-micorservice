package com.devshaks.ecommerce.orderline;

import com.ctc.wstx.shaded.msv_core.datatype.xsd.IntegerType;

public record OrderLineRequest(
        Integer id,
        Integer orderId,
        Integer productId,
        double quantity
) {
}
