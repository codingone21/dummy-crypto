package crypto.core;

import java.math.BigDecimal;
import java.math.MathContext;

public class AverageAndStandardDeviation {
    /* Helper fields used for calculating running stats */
    private BigDecimal sum;
    private BigDecimal sumOfSquares;
    private Long size;

    /* Fields returned when API fetches RunningStats */
    private BigDecimal average;
    private BigDecimal standardDeviation;

    MathContext mc = MathContext.DECIMAL128;

    public AverageAndStandardDeviation() {
        this.sum = BigDecimal.ZERO;
        this.sumOfSquares = BigDecimal.ZERO;
        this.size = 0L;
        this.average = BigDecimal.ZERO;
        this.standardDeviation = BigDecimal.ZERO;
    }

    public BigDecimal getAverage() {
        return this.average;
    }

    public BigDecimal getStandardDeviation() {
        return this.standardDeviation;
    }

    public void updateRunningStats(BigDecimal sample) {
        this.size += 1;
        BigDecimal size = BigDecimal.valueOf(this.size);
        this.sum = this.sum.add(sample);
        this.average = this.sum.divide(BigDecimal.valueOf(this.size), mc);

        this.sumOfSquares = this.sumOfSquares.add(sample.pow(2, mc));
        this.standardDeviation = sumOfSquares.subtract(this.sum.pow(2, mc).divide(size, mc)).divide(size, mc).sqrt(mc);
    }
}