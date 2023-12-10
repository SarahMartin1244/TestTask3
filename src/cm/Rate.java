package cm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Rate {
    private CarParkKind kind;
    private BigDecimal hourlyNormalRate;
    private BigDecimal hourlyReducedRate;
    private ArrayList<Period> reduced = new ArrayList<>();
    private ArrayList<Period> normal = new ArrayList<>();

    public Rate(CarParkKind kind, BigDecimal normalRate, BigDecimal reducedRate, ArrayList<Period> normalPeriods, ArrayList<Period> reducedPeriods) {
        if (reducedPeriods == null || normalPeriods == null) {
            throw new IllegalArgumentException("periods cannot be null");
        }
        if (normalRate == null || reducedRate == null) {
            throw new IllegalArgumentException("The rates cannot be null");
        }
        if (normalRate.compareTo(BigDecimal.ZERO) < 0 || reducedRate.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("A rate cannot be negative");
        }
        if (normalRate.compareTo(reducedRate) < 0) {
            throw new IllegalArgumentException("The normal rate cannot be less than the reduced rate");
        }
        if (!isValidPeriods(reducedPeriods) || !isValidPeriods(normalPeriods)) {
            throw new IllegalArgumentException("The periods are not valid individually");
        }
        if (!isValidPeriods(reducedPeriods, normalPeriods)) {
            throw new IllegalArgumentException("The periods overlaps");
        }
        this.kind = kind;
        this.hourlyNormalRate = normalRate;
        this.hourlyReducedRate = reducedRate;
        this.reduced = reducedPeriods;
        this.normal = normalPeriods;
    }

    /**
     * Checks if two collections of periods are valid together
     * @param periods1
     * @param periods2
     * @return true if the two collections of periods are valid together
     */
    private boolean isValidPeriods(ArrayList<Period> periods1, ArrayList<Period> periods2) {
        Boolean isValid = true;
        int i = 0;
        while (i < periods1.size() && isValid) {
            isValid = isValidPeriod(periods1.get(i), periods2);
            i++;
        }
        return isValid;
    }

    /**
     * checks if a collection of periods is valid
     * @param list the collection of periods to check
     * @return true if the periods do not overlap
     */
    private Boolean isValidPeriods(ArrayList<Period> list) {
        Boolean isValid = true;
        if (list.size() >= 2) {
            Period secondPeriod;
            int i = 0;
            int lastIndex = list.size()-1;
            while (i < lastIndex && isValid) {
                isValid = isValidPeriod(list.get(i), ((List<Period>)list).subList(i + 1, lastIndex+1));
                i++;
            }
        }
        return isValid;
    }

    /**
     * checks if a period is a valid addition to a collection of periods
     * @param period the Period addition
     * @param list the collection of periods to check
     * @return true if the period does not overlap in the collecton of periods
     */
    private Boolean isValidPeriod(Period period, List<Period> list) {
        Boolean isValid = true;
        int i = 0;
        while (i < list.size() && isValid) {
            isValid = !period.overlaps(list.get(i));
            i++;
        }
        return isValid;
    }
//    public BigDecimal calculate(Period periodStay) {
//        int normalRateHours = periodStay.occurences(normal);
//        int reducedRateHours = periodStay.occurences(reduced);
//        return (this.hourlyNormalRate.multiply(BigDecimal.valueOf(normalRateHours))).add(
//                this.hourlyReducedRate.multiply(BigDecimal.valueOf(reducedRateHours)));
//    }

    public BigDecimal calculate(Period periodStay) {
        int normalRateHours = periodStay.occurences(normal);
        int reducedRateHours = periodStay.occurences(reduced);
        BigDecimal totalCost = this.hourlyNormalRate.multiply(BigDecimal.valueOf(normalRateHours))
                .add(this.hourlyReducedRate.multiply(BigDecimal.valueOf(reducedRateHours)));

        if (kind == CarParkKind.VISITOR) {
            // The first 10.00 is free then 50% reduction above that
            BigDecimal visitorReductionThreshold = BigDecimal.valueOf(10.00);

            // Check if the total cost is above the visitor reduction threshold
            if (totalCost.compareTo(visitorReductionThreshold) > 0) {
                BigDecimal reductionAmount = totalCost.subtract(visitorReductionThreshold)
                        .multiply(BigDecimal.valueOf(0.5));
                return visitorReductionThreshold.add(reductionAmount);
            }
        }
        else if (kind == CarParkKind.MANAGEMENT) {
            // Minimum amount payable is 5.00
            return totalCost.max(BigDecimal.valueOf(5.00));
        }

        else if (kind == CarParkKind.STUDENT) {
            // 33 percent reduction on any amount above 5.50
            BigDecimal studentReductionThreshold = BigDecimal.valueOf(5.50);
            if (totalCost.compareTo(studentReductionThreshold) > 0) {
                BigDecimal reductionAmount = totalCost.subtract(studentReductionThreshold)
                        .multiply(BigDecimal.valueOf(0.33));
                return studentReductionThreshold.add(reductionAmount);
            }
        }

        return totalCost;

    }
}