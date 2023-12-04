package cm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MartinSarahTestTask2 {

    // Test cases for the Period Class
    @Test
        //Test case 1
    void testValidPeriodConstructor24Hours() {
        // constructor for a valid 24 hour period
        Period period = new Period(0, 24);
        assertNotNull(period);
    }

    @Test // Test case 2
    void testValidLowerBoundaryValueConstructor_Start() {
        // constructor with start time at lower boundary
        Period period = new Period(1, 23);
        assertNotNull(period);
    }

    @Test //Test case 3
    void testValidPeriodConstructor() {
        // Test constructor of a valid Period instance
        Period period = new Period(2, 5);
        assertNotNull(period);
    }


    // test case 4
    @Test
    void testLowerBoundaryValueConstructor_OutOfBounds() {
        // Test construction with negative out of bounds start time at lower boundary
        assertThrows(IllegalArgumentException.class, () -> {
            new Period(-1, 20);
        });
    }

    // test case 5
    @Test
    void testUpperBoundaryValueConstructor_Start() {
        // Test construction with start time at upper boundary
        Period period = new Period(23, 24);
        assertNotNull(period);
    }


    @Test // Test Case 6
    void testPeriodConstructor_InvalidStartTime() {
        //  constructor of an invalid Period, start time is greater than end time
        assertThrows(IllegalArgumentException.class, () -> {
            new Period(15, 10);
        });
    }

//    @Test // Test Case 7
//    void testInvalidPeriodConstructorNullStart() {
//        // start time has null value
//        assertThrows(IllegalArgumentException.class, () -> {
//            new Period(null, 3);
//        });
//    }

    @Test // Test Case 8
    void testInvalidPeriodConstructorUpperOutOfBoundsStart() {
        // start time starts with an out of bounds value in the upper boundary
        assertThrows(IllegalArgumentException.class, () -> {
            new Period(25, 26);
        });
    }


    @Test // Test case 9
    void testValidValueConstructor_endTime() {
        // constructor with end time at lower boundary
        Period period = new Period(0, 24);
        assertNotNull(period);
    }


    @Test // Test case 10
    void testValidUpperBoundaryValueConstructor_endTime() {
        // constructor with end time at upper boundary
        Period period = new Period(20, 23);
        assertNotNull(period);
    }

    @Test // Test case 11
    void testValidLowerBoundaryValueConstructor_endTime() {
        // constructor with end time at lower boundary
        Period period = new Period(0, 3);
        assertNotNull(period);
    }

//    @Test // Test Case 12
//    void testInvalidPeriodConstructorNullEndTime() {
//        // start time has null value
//        assertThrows(IllegalArgumentException.class, () -> {
//            new Period(12, null);
//        });
//    }

    // Test Case 13
    @Test
    void testInvalidLowerBoundaryValueConstructor_EndTime() {
        // Test constructor with negative value end time at lower boundary
        assertThrows(IllegalArgumentException.class, () -> {
            new Period(12, -1);
        });

    }

    @Test // Test case 14
    void testInvalidPeriodConstructorOutOfRange_EndTime() {
        //  End time out of range
        assertThrows(IllegalArgumentException.class, () -> {
            new Period(12, 26);
        });
    }

    @Test // Test case 15
    void testInvalidPeriodConstructorOut_StartTimeIsEqualToEndTime() {
        //  Start time and End Time have the same value
        assertThrows(IllegalArgumentException.class, () -> {
            new Period(5, 5);
        });
    }

    // test 16 and 17 are commented out as a null value is invalid to use
//    @Test // Test case 16
//    void testInvalidPeriodConstructorOut_StartTimeIsNull() {
//        //  Start time is null
//        assertThrows(IllegalArgumentException.class, () -> {
//            new Period(null, 5);
//        });
//    }

//    @Test // Test case 17
//    void testInvalidPeriodConstructorOut_EndTimeIsNull() {
//        //  End time is Null
//        assertThrows(IllegalArgumentException.class, () -> {
//            new Period(5, null);
//        });
//    }

    // Duration method test cases
    @Test // Test Case 1
    void testDurationMaxDuration() {
        // Test the duration method with the max possible duration (24 hours).
        Period period = new Period(0, 24);
        int duration = period.duration();
        assertEquals(24, duration); // duration of 24 hours.
    }

    @Test // Test Case 2
    void testDurationLowerBoundary_Start() {
        // Test the duration method with a low value for start time.
        Period period = new Period(1, 24);
        int duration = period.duration();
        assertEquals(23, duration); // duration of 23 hours.
    }

    @Test // Test Case 3
    void testDuration_Valid() {
        // Test the duration method with a valid Start and End time
        Period period = new Period(12, 24);
        int duration = period.duration();
        assertEquals(12, duration); //  duration of 12 hours.
    }

    @Test // Test Case 4
    void testDurationShortDuration() {
        // Test the duration method with a short duration of 3 hours.
        Period period = new Period(7, 10);
        int duration = period.duration();
        assertEquals(3, duration); // duration of 3 hours.
    }

    @Test // Test Case 5
    void testDurationMinDuration() {
        // Test the duration method with the minimum possible duration
        Period period = new Period(0, 1);
        int duration = period.duration();
        assertEquals(1, duration); // duration of 1 hour.
    }

    @Test // Test Case 6
    void testDuration_LowerBoundary() {
        // Test the duration method with a duration of 11 hours.
        Period period = new Period(1, 12);
        int duration = period.duration();
        assertEquals(11, duration); // duration of 11 hours.
    }

    //Overlap method test cases
    @Test // Test Case 1
    void testOverlapFullOverlap() {
        // Valid overlapping periods (Full Overlap)
        Period period1 = new Period(10, 12);
        Period period2 = new Period(11, 14);
        assertTrue(period1.overlaps(period2));
    }


    @Test // Test Case 2
    void testOverlapNoOverlap() {
        // No overlapping of periods
        Period period1 = new Period(10, 12);
        Period period2 = new Period(12, 14);
        assertFalse(period1.overlaps(period2));
    }


    @Test // Test Case 3: Boundary Testing
    void testOverlapBoundaryEnd() {
        // Overlap at the upper boundary
        Period period1 = new Period(20, 24);
        Period period2 = new Period(22, 24);
        assertTrue(period1.overlaps(period2)); // Expect true for overlap at the boundary.
    }

    @Test // Test Case 4
        // overlapping on the lower boundary
    void testOverlapBoundaryStart() {
        Period period1 = new Period(1, 5);
        Period period2 = new Period(2, 6);
        assertTrue(period1.overlaps(period2));
    }

    // Rate Class Test Cases
    @Test // test case 1
    public void testValidInputNormalRateGreaterThanReducedRate() {
        //  when the normal rate is greater than the reduced rate
        Rate rate = new Rate(CarParkKind.VISITOR, BigDecimal.valueOf(10), BigDecimal.valueOf(5),
                new ArrayList<Period>(), new ArrayList<Period>());
        BigDecimal charge = rate.calculate(new Period(1, 6));

        assertEquals(BigDecimal.valueOf(50), charge);
    }

    @Test //test case 2
    public void testValidInputNormalRateEqualToReducedRate() {
        //  when the normal rate is equal to the reduced rate.

        Rate rate = new Rate(CarParkKind.VISITOR, BigDecimal.valueOf(10), BigDecimal.valueOf(10),
                new ArrayList<Period>(), new ArrayList<Period>());
        BigDecimal charge = rate.calculate(new Period(1, 6));

        assertEquals(BigDecimal.valueOf(50), charge);
    }


    // test case 3
    @Test
    public void testValidInputNormalRateLessThanReducedRate() {
        // when the normal rate is less than the reduced rate.
        Rate rate = new Rate(CarParkKind.VISITOR, BigDecimal.valueOf(5), BigDecimal.valueOf(10),
                new ArrayList<Period>(), new ArrayList<Period>());
        BigDecimal charge = rate.calculate(new Period(1, 6));
        assertEquals(BigDecimal.valueOf(25), charge);
    }

    @Test // test case 4
    public void testInvalidInputNegativeNormalRate() {
        // Checks for exception when the normal rate is set to a negative value.
        assertThrows(IllegalArgumentException.class, () -> new Rate(CarParkKind.VISITOR, BigDecimal.valueOf(-10), BigDecimal.valueOf(5),
                new ArrayList<Period>(), new ArrayList<Period>()));
    }

    @Test // test case 5
    public void testInvalidInputNegativeReducedRate() {
        // Checks for exception when the reduced rate is set to a negative value.
        assertThrows(IllegalArgumentException.class, () -> new Rate(CarParkKind.VISITOR, BigDecimal.valueOf(10), BigDecimal.valueOf(-5),
                new ArrayList<Period>(), new ArrayList<Period>()));
    }

    @Test // test case 6
    public void testInvalidInputOverlappingNormalPeriods() {
        // exception is thrown when there are overlapping normal rate periods.
        assertThrows(IllegalArgumentException.class, () -> new Rate(CarParkKind.VISITOR, BigDecimal.valueOf(10), BigDecimal.valueOf(5),
                new ArrayList<Period>(Arrays.asList(new Period(7, 10), new Period(8, 12))),
                new ArrayList<Period>()));
    }



    @Test // test case 7
    public void testInvalidInputOverlappingNormalAndReducedPeriods() {
        // Ensures an exception is thrown when there are overlapping normal and reduced rate periods
        assertThrows(IllegalArgumentException.class, () -> new Rate(
                CarParkKind.VISITOR,
                BigDecimal.valueOf(10),
                BigDecimal.valueOf(5),
                new ArrayList<>(Arrays.asList(new Period(7, 10), new Period(8, 12))),
                new ArrayList<>(Arrays.asList(new Period(10, 12), new Period(7, 10)))
        ));


    }

    // new test cases added

    @Test // Test case for Rate constructor with null periods
    void testRateConstructorWithNullPeriods() {
        // Ensure that an exception is thrown when attempting to create a Rate instance with null periods
        assertThrows(IllegalArgumentException.class, () -> new Rate(
                CarParkKind.VISITOR,
                BigDecimal.valueOf(5),
                BigDecimal.valueOf(3),
                null, // Setting normalPeriods to null
                new ArrayList<Period>() // Some non-null value for reducedPeriods
        ));

        // Ensure that an exception is thrown when creating a Rate instance with null periods
        assertThrows(IllegalArgumentException.class, () -> new Rate(
                CarParkKind.VISITOR,
                BigDecimal.valueOf(5),
                BigDecimal.valueOf(3),
                new ArrayList<Period>(), // Some non null value for normalPeriods
                null // Setting reducedPeriods to null
        ));
    }



    @Test
    void testConstructorWithNullNormalRate() {
        // Ensure that an exception is thrown when attempting to create a Rate instance with null normalRate
        assertThrows(IllegalArgumentException.class, () -> new Rate(
                CarParkKind.VISITOR,
                null, // Setting normalRate to null
                BigDecimal.valueOf(3),
                new ArrayList<Period>(),
                new ArrayList<Period>()
        ));
    }


    @Test
    void testOccurrencesWithinSinglePeriod() {
        List<Period> periods = Arrays.asList(new Period(10, 15));
        assertEquals(5, new Period(10, 15).occurences(periods));
    }


    @Test
    void testInvalidOverlappingNormalAndReducedPeriods() {
        ArrayList<Period> normalPeriods = new ArrayList<>(Arrays.asList(new Period(8, 12), new Period(10, 14)));
        ArrayList<Period> reducedPeriods = new ArrayList<>(Arrays.asList(new Period(11, 13), new Period(15, 18)));

        normalPeriods.add(new Period(13, 16));

        // Ensure that an exception is thrown when creating a Rate instance with overlapping periods
        assertThrows(IllegalArgumentException.class, () -> {
            new Rate(
                    CarParkKind.VISITOR,
                    BigDecimal.valueOf(10),
                    BigDecimal.valueOf(5),
                    normalPeriods,
                    reducedPeriods
            );
        });
    }


}
