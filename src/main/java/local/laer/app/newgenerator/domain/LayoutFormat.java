/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.laer.app.newgenerator.domain;

import java.util.Arrays;
import java.util.Set;
import java.util.Spliterators;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 *
 * @author Lars Eriksson (larsq.eriksson@gmail.com)
 */
public enum LayoutFormat {

    HALF(1, 1), NORMAL(2, 2), DOUBLE(4, 4), WIDE(1, 2), TALL(2, 1), EXTRA_WIDE(2, 4), EXTRA_TALL(4, 2);

    private final int rows;
    private final int cols;

    private LayoutFormat(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    public int rows() {
        return rows;
    }

    public int cols() {
        return cols;
    }

    public static Predicate<LayoutFormat> sameSize(int rows, int cols) {
        return f -> f.rows == rows && f.cols == cols;
    }

    public static LayoutFormat findBySize(int rows, int cols) {
        return Arrays.stream(values())
                .filter(sameSize(rows, cols))
                .findFirst().orElse(null);
    }

    public LayoutFormat scaleDown() {
        if (rows % 2 > 0 || cols % 2 > 0) {
            return null;
        }

        return findBySize(rows / 2, cols / 2);
    }

    
    /**
     * Return the set that at least has the specified dimensions.
     * @param rows the number of rows
     * @param cols the number of columns
     * @return the set of LayoutFormat that covers the specified dimension. Returns
     * the empty set if no formats matches.
     */
    public static Set<LayoutFormat> covers(int rows, int cols) {
        return Arrays.stream(values())
                .filter(f-> f.rows<=rows && f.cols<=cols)
                .collect(Collectors.toSet());
    }
    
   
    public static LayoutFormat matchBest(LayoutFormat format, Iterable<LayoutFormat> iterable) {
        LayoutFormat current = format;
        while (current != null) {
            boolean doMatch = StreamSupport.stream(iterable.spliterator(), false)
                    .anyMatch(Predicate.isEqual(current));
            
            if(doMatch) {
                return current;
            }
            
            current = current.scaleDown();
        }
        
        return null;
    }
    
}
