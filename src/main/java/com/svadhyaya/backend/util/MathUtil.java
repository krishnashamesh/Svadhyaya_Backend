package com.svadhyaya.backend.util;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

@Service
public class MathUtil {

    public static BigDecimal round(BigDecimal bd, int places) {
        if (places < 0) throw new IllegalArgumentException();
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd;
    }

    public BigDecimal getRandomValueInRange(int minValue, int maxValue) {
        if (Double.valueOf(maxValue - minValue).isInfinite() == false)
            return round(BigDecimal.valueOf(minValue + (maxValue - minValue) * new Random().nextDouble()), 2);
        else
            return round(BigDecimal.ZERO, 2);
    }

    public BigDecimal getVarianceQuantity(BigDecimal meanQuantity, int spreadPercent) {
        return round(meanQuantity.divide(BigDecimal.valueOf(spreadPercent), 6, RoundingMode.HALF_UP), 2);
    }

    public BigDecimal getActualDemand(BigDecimal meanQuantity, BigDecimal varianceQuantity) {
        return round(BigDecimal.valueOf(new Random().nextGaussian())
                .multiply(varianceQuantity)
                .add(meanQuantity), 2);
    }

    public BigDecimal getIdealOrderQuantity(BigDecimal wholeSalePrice, BigDecimal retailPrice, BigDecimal salvagePrice, BigDecimal meanQuantity, BigDecimal varianceQuantity) {
        BigDecimal prlessDemand =
                (retailPrice.subtract(wholeSalePrice)).divide(retailPrice.subtract(salvagePrice), 6, RoundingMode.HALF_UP);
        return round(BigDecimal.valueOf(NormInv.compute(prlessDemand.doubleValue(), meanQuantity.doubleValue(),
                varianceQuantity.doubleValue())), 2);
    }
}
